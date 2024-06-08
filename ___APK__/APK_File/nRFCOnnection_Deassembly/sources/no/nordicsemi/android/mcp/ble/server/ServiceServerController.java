package no.nordicsemi.android.mcp.ble.server;

import a.l.a.a;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattServer;
import android.bluetooth.BluetoothGattServerCallback;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import no.nordicsemi.android.mcp.ble.BluetoothLeService;
import no.nordicsemi.android.mcp.ble.IBluetoothLeBasicConnection;
import no.nordicsemi.android.mcp.ble.ServiceConstants;
import no.nordicsemi.android.mcp.ble.model.Device;
import no.nordicsemi.android.mcp.ble.parser.CharacteristicParser;
import no.nordicsemi.android.mcp.ble.parser.DescriptorParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.GeneralCharacteristicParser;
import no.nordicsemi.android.mcp.ble.parser.utils.ParserUtils;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;
import no.nordicsemi.android.mcp.server.domain.Characteristic;
import no.nordicsemi.android.mcp.server.domain.Descriptor;
import no.nordicsemi.android.mcp.server.domain.ServerConfiguration;
import no.nordicsemi.android.mcp.server.domain.Service;
import no.nordicsemi.android.mcp.settings.SettingsFragment;
import no.nordicsemi.android.mcp.util.NotificationHelper;

/* loaded from: classes.dex */
public class ServiceServerController extends BluetoothGattServerCallback implements ServiceConstants, NotificationHelper.NewConnectionListener {
    private static final boolean DEBUG = false;
    public static final int ERROR_BLUETOOTH_DISABLED = -1;
    public static final int ERROR_SERVER_CLOSED = -2;
    private static final String TAG = "SSController";
    private boolean mBackgroundMode;
    private boolean mConfirm;
    private final List<BluetoothDevice> mConnectedDevices;
    private final Map<BluetoothDevice, IBluetoothLeBasicConnection> mConnections;
    private final Context mContext;
    private final ControllerCallback mControllerCallback;
    private final DatabaseHelper mDatabase;
    private final ServiceMap mDeviceServices;
    private final Handler mHandler;
    private BluetoothGattServer mServer;
    private ServerConfiguration mServerConfiguration;
    private final BluetoothLeService.ServiceBinder mServiceBinder;
    private final Map<BluetoothGattService, IServerCallback> mServiceCallbackMap;
    private BluetoothGattCharacteristic mTmpCharacteristic;

    /* loaded from: classes.dex */
    public interface ControllerCallback {
        void onDatabaseChanged();

        void onError(int i);
    }

    public ServiceServerController(Context context, DatabaseHelper databaseHelper, BluetoothLeService.ServiceBinder serviceBinder, ControllerCallback controllerCallback) {
        if (controllerCallback != null) {
            this.mHandler = new Handler();
            this.mContext = context;
            this.mDatabase = databaseHelper;
            this.mServiceBinder = serviceBinder;
            this.mControllerCallback = controllerCallback;
            this.mServiceCallbackMap = new HashMap();
            this.mConnections = new HashMap();
            this.mConnectedDevices = new LinkedList();
            this.mDeviceServices = new ServiceMap();
            return;
        }
        throw new NullPointerException("ControllerCallback is null");
    }

    private void addNextService() {
        IServerCallback newCallback;
        ServerConfiguration serverConfiguration = this.mServerConfiguration;
        if (serverConfiguration == null) {
            this.mControllerCallback.onError(-2);
            return;
        }
        List<Service> services = serverConfiguration.getServices();
        if (services.isEmpty()) {
            this.mServerConfiguration = null;
            this.mDeviceServices.updateServerServices(this.mServer.getServices());
            this.mControllerCallback.onDatabaseChanged();
            NotificationHelper.registerNewConnectionListener(this.mContext, this);
            return;
        }
        Service remove = services.remove(0);
        if (remove.isEnabled()) {
            BluetoothGattService initService = initService(remove);
            if (!this.mServiceCallbackMap.containsKey(initService) && (newCallback = ServerCallbackProvider.newCallback(this.mContext, this.mHandler, this, remove, this.mDeviceServices, initService)) != null) {
                this.mServiceCallbackMap.put(initService, newCallback);
            }
            saveLogAndFlash(0, "server.addService(" + initService.getUuid() + ")");
            this.mServer.addService(initService);
            return;
        }
        addNextService();
    }

    private void broadcastAction(BluetoothDevice bluetoothDevice, String str) {
        a.a(this.mContext).a(new Intent(str + bluetoothDevice.getAddress()));
    }

    private void broadcastWriteExecuted(BluetoothDevice bluetoothDevice) {
        Intent intent = new Intent(ServiceConstants.ACTION_GATT_SERVER_DATA_SEND + bluetoothDevice.getAddress());
        intent.putExtra(ServiceConstants.EXTRA_DATA, true);
        a.a(this.mContext).a(intent);
    }

    private void flashLog(BluetoothDevice bluetoothDevice, boolean z) {
        IBluetoothLeBasicConnection iBluetoothLeBasicConnection = this.mConnections.get(bluetoothDevice);
        if (iBluetoothLeBasicConnection != null) {
            iBluetoothLeBasicConnection.flashLog(z);
        }
    }

    private byte[] getPrepareWriteResponse(byte[] bArr) {
        char c2;
        String string = PreferenceManager.getDefaultSharedPreferences(this.mContext).getString(SettingsFragment.SETTINGS_SERVER_RESPONSE_TYPE, "received");
        int hashCode = string.hashCode();
        if (hashCode == -808719903) {
            if (string.equals("received")) {
                c2 = 3;
            }
            c2 = 65535;
        } else if (hashCode != 96634189) {
            if (hashCode == 814978525 && string.equals("hex1234")) {
                c2 = 1;
            }
            c2 = 65535;
        } else {
            if (string.equals("empty")) {
                c2 = 0;
            }
            c2 = 65535;
        }
        if (c2 != 0) {
            return c2 != 1 ? bArr : new byte[]{18, 52};
        }
        return null;
    }

    private BluetoothGattCharacteristic initCharacteristic(Characteristic characteristic) {
        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(characteristic.getUuid(), characteristic.getProperties(), characteristic.getPermissions());
        bluetoothGattCharacteristic.setValue(characteristic.getValue());
        for (Descriptor descriptor : characteristic.getDescriptors()) {
            if (descriptor.isEnabled()) {
                bluetoothGattCharacteristic.addDescriptor(initDescriptor(descriptor));
            }
        }
        return bluetoothGattCharacteristic;
    }

    private BluetoothGattDescriptor initDescriptor(Descriptor descriptor) {
        BluetoothGattDescriptor bluetoothGattDescriptor = new BluetoothGattDescriptor(descriptor.getUuid(), descriptor.getPermissions());
        bluetoothGattDescriptor.setValue(descriptor.getValue());
        return bluetoothGattDescriptor;
    }

    private BluetoothGattService initService(Service service) {
        BluetoothGattService bluetoothGattService = new BluetoothGattService(service.getUuid(), 0);
        for (Characteristic characteristic : service.getCharacteristics()) {
            if (characteristic.isEnabled()) {
                bluetoothGattService.addCharacteristic(initCharacteristic(characteristic));
            }
        }
        return bluetoothGattService;
    }

    private void openConnection(BluetoothDevice bluetoothDevice, boolean z) {
        saveLogBulk(bluetoothDevice, 1, "[Server] Creating server connection...");
        saveLogAndFlash(bluetoothDevice, 0, "server.connect(device, autoConnect = " + z + ")");
        this.mServer.connect(bluetoothDevice, z);
    }

    private String phyToText(int i) {
        if (i == 1) {
            return "LE 1M";
        }
        if (i == 2) {
            return "LE 2M";
        }
        if (i == 3) {
            return "LE Coded";
        }
        return "UNKNOWN (" + i + ")";
    }

    private void saveLogAndFlash(int i, String str) {
        Iterator<IBluetoothLeBasicConnection> it = this.mConnections.values().iterator();
        while (it.hasNext()) {
            it.next().saveLogAndFlash(i, str);
        }
    }

    private void saveLogBulk(BluetoothDevice bluetoothDevice, int i, String str) {
        IBluetoothLeBasicConnection iBluetoothLeBasicConnection = this.mConnections.get(bluetoothDevice);
        if (iBluetoothLeBasicConnection != null) {
            iBluetoothLeBasicConnection.saveLogBulk(i, str);
        }
    }

    private void saveLogConnectionError(BluetoothDevice bluetoothDevice, int i) {
        saveLogAndFlash(bluetoothDevice, 20, "[Server] Error " + i + " (0x" + Integer.toHexString(i) + "): " + e.a.a.a.a.b(i));
    }

    private void saveLogError(BluetoothDevice bluetoothDevice, int i) {
        saveLogAndFlash(bluetoothDevice, 20, "[Server] Error " + i + " (0x" + Integer.toHexString(i) + "): " + e.a.a.a.a.a(i));
    }

    private void saveServicesUpdate(IBluetoothLeBasicConnection iBluetoothLeBasicConnection, List<BluetoothGattService> list) {
        if (list != null && !list.isEmpty()) {
            iBluetoothLeBasicConnection.saveLogBulk(1, list);
        } else if (this.mServerConfiguration == null) {
            iBluetoothLeBasicConnection.saveLogAndFlash(1, "[Server] No services");
        } else {
            iBluetoothLeBasicConnection.saveLogAndFlash(1, "[Server] Creating services...");
        }
    }

    private String stateToString(int i) {
        return i != 1 ? i != 2 ? i != 3 ? "DISCONNECTED" : "DISCONNECTING" : "CONNECTED" : "CONNECTING";
    }

    public void addExistingConnection(BluetoothDevice bluetoothDevice, IBluetoothLeBasicConnection iBluetoothLeBasicConnection) {
        if (this.mConnections.containsKey(bluetoothDevice)) {
            return;
        }
        this.mConnections.put(bluetoothDevice, iBluetoothLeBasicConnection);
        this.mDeviceServices.onConnectionCreated(bluetoothDevice);
        if (this.mServer == null) {
            iBluetoothLeBasicConnection.saveLogAndFlash(5, "[Server] Server started");
            saveServicesUpdate(iBluetoothLeBasicConnection, getServices(bluetoothDevice, false));
        }
    }

    public void addNewConnection(BluetoothDevice bluetoothDevice, IBluetoothLeBasicConnection iBluetoothLeBasicConnection) {
        this.mConnections.put(bluetoothDevice, iBluetoothLeBasicConnection);
        this.mDeviceServices.onConnectionCreated(bluetoothDevice);
        iBluetoothLeBasicConnection.saveLogAndFlash(5, "[Server] Server started");
        saveServicesUpdate(iBluetoothLeBasicConnection, getServices(bluetoothDevice, false));
    }

    public void cancelConnection(BluetoothDevice bluetoothDevice) {
        saveLogBulk(bluetoothDevice, 1, "[Server] Cancelling server connection...");
        saveLogAndFlash(bluetoothDevice, 0, "server.cancelConnection(device)");
        this.mServer.cancelConnection(bluetoothDevice);
    }

    public void close() {
        if (this.mServer != null) {
            this.mServerConfiguration = null;
            Iterator<IServerCallback> it = this.mServiceCallbackMap.values().iterator();
            while (it.hasNext()) {
                try {
                    it.next().onServerClosed();
                } catch (Exception unused) {
                }
            }
            this.mServiceCallbackMap.clear();
            NotificationHelper.unregisterNewConnectionListener(this.mContext, this);
            this.mDeviceServices.onServerClosed();
            for (IBluetoothLeBasicConnection iBluetoothLeBasicConnection : this.mConnections.values()) {
                iBluetoothLeBasicConnection.saveLogBulk(0, "server.clearServices()");
                iBluetoothLeBasicConnection.saveLogBulk(0, "server.close()");
            }
            try {
                this.mServer.clearServices();
                this.mServer.close();
            } catch (Exception e2) {
                Log.e(TAG, "Server already closed!", e2);
            }
            this.mServer = null;
            saveLogAndFlash(5, "[Server] Server closed");
        }
        this.mConnections.clear();
        this.mConnectedDevices.clear();
    }

    public List<BluetoothDevice> getConnectedDevices() {
        return this.mConnectedDevices;
    }

    public IBluetoothLeBasicConnection getConnection(BluetoothDevice bluetoothDevice) {
        return this.mConnections.get(bluetoothDevice);
    }

    public List<BluetoothGattService> getServices(BluetoothDevice bluetoothDevice, boolean z) {
        return this.mDeviceServices.getDeviceServices(bluetoothDevice, z);
    }

    public boolean isConnected(BluetoothDevice bluetoothDevice) {
        return this.mConnectedDevices.contains(bluetoothDevice);
    }

    public boolean isPredefinedServerService(BluetoothDevice bluetoothDevice, BluetoothGattService bluetoothGattService) {
        return this.mServiceCallbackMap.containsKey(this.mDeviceServices.getServerService(bluetoothDevice, bluetoothGattService));
    }

    public boolean isStarted() {
        return this.mServer != null;
    }

    @Override // android.bluetooth.BluetoothGattServerCallback
    public void onCharacteristicReadRequest(BluetoothDevice bluetoothDevice, int i, int i2, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        byte[] bArr;
        saveLogBulk(bluetoothDevice, 0, "[Server callback] Read request for characteristic " + bluetoothGattCharacteristic.getUuid() + " (requestId=" + i + ", offset=" + i2 + ")");
        IServerCallback iServerCallback = this.mServiceCallbackMap.get(bluetoothGattCharacteristic.getService());
        BluetoothGattCharacteristic deviceCharacteristic = this.mDeviceServices.getDeviceCharacteristic(bluetoothDevice, bluetoothGattCharacteristic, false);
        if (iServerCallback == null || !iServerCallback.onCharacteristicReadRequest(this.mServer, bluetoothDevice, i, i2, deviceCharacteristic)) {
            saveLogBulk(bluetoothDevice, 5, "[Server] READ request for characteristic " + deviceCharacteristic.getUuid() + " received");
            byte[] value = deviceCharacteristic.getValue();
            if (value == null || i2 <= 0) {
                bArr = value;
            } else {
                byte[] bArr2 = new byte[value.length - i2];
                System.arraycopy(value, i2, bArr2, 0, bArr2.length);
                bArr = bArr2;
            }
            if (bArr != null) {
                saveLogBulk(bluetoothDevice, 0, "server.sendResponse(GATT_SUCCESS, value=" + ParserUtils.bytesToHex(bArr, true) + ")");
            } else {
                saveLogBulk(bluetoothDevice, 0, "server.sendResponse(GATT_SUCCESS, value=null)");
            }
            this.mServer.sendResponse(bluetoothDevice, i, 0, i2, bArr);
            saveLogAndFlash(bluetoothDevice, 1, "[Server] Response sent");
            if (i2 == 0) {
                String valueAsString = CharacteristicParser.getValueAsString(this.mDatabase, deviceCharacteristic, null, true);
                if (!TextUtils.isEmpty(valueAsString)) {
                    saveLogAndFlash(bluetoothDevice, 10, "[Server] \"" + valueAsString + "\" sent");
                }
            }
        }
        IBluetoothLeBasicConnection iBluetoothLeBasicConnection = this.mConnections.get(bluetoothDevice);
        if (iBluetoothLeBasicConnection != null) {
            iBluetoothLeBasicConnection.onReadRequest(deviceCharacteristic);
        }
        if (iServerCallback != null) {
            broadcastAction(bluetoothDevice, ServiceConstants.ACTION_GATT_SERVER_DATA_SEND);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:13:0x01aa  */
    /* JADX WARN: Removed duplicated region for block: B:16:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0198  */
    /* JADX WARN: Type inference failed for: r11v1 */
    /* JADX WARN: Type inference failed for: r11v2, types: [int, boolean] */
    /* JADX WARN: Type inference failed for: r11v3 */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v2, types: [int, boolean] */
    /* JADX WARN: Type inference failed for: r8v3 */
    /* JADX WARN: Type inference failed for: r8v4 */
    @Override // android.bluetooth.BluetoothGattServerCallback
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onCharacteristicWriteRequest(android.bluetooth.BluetoothDevice r20, int r21, android.bluetooth.BluetoothGattCharacteristic r22, boolean r23, boolean r24, int r25, byte[] r26) {
        /*
            Method dump skipped, instructions count: 432
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: no.nordicsemi.android.mcp.ble.server.ServiceServerController.onCharacteristicWriteRequest(android.bluetooth.BluetoothDevice, int, android.bluetooth.BluetoothGattCharacteristic, boolean, boolean, int, byte[]):void");
    }

    public void onConnectionLost(BluetoothDevice bluetoothDevice) {
        Iterator<IServerCallback> it = this.mServiceCallbackMap.values().iterator();
        while (it.hasNext()) {
            it.next().onConnectionLost(this.mServer, bluetoothDevice);
        }
    }

    @Override // android.bluetooth.BluetoothGattServerCallback
    public void onConnectionStateChange(BluetoothDevice bluetoothDevice, int i, int i2) {
        boolean z = false;
        saveLogBulk(bluetoothDevice, 0, "[Server callback] Connection state changed with status: " + i + " and new state: " + stateToString(i2) + " (" + i2 + ")");
        if (i != 0) {
            saveLogConnectionError(bluetoothDevice, i);
            if (i2 == 0) {
                this.mConnectedDevices.remove(bluetoothDevice);
                if (!this.mConnections.containsKey(bluetoothDevice)) {
                    this.mDeviceServices.onConnectionClosed(bluetoothDevice);
                }
            }
        } else if (i2 == 2) {
            if (this.mConnectedDevices.contains(bluetoothDevice)) {
                return;
            }
            this.mConnectedDevices.add(bluetoothDevice);
            saveLogBulk(bluetoothDevice, 5, "[Server] Device with address " + bluetoothDevice.getAddress() + " connected");
            if (!this.mConnections.containsKey(bluetoothDevice)) {
                boolean z2 = PreferenceManager.getDefaultSharedPreferences(this.mContext).getBoolean(SettingsFragment.SETTINGS_SHOW_INCOMING_CONNECTION, true);
                Iterator<Map.Entry<BluetoothDevice, IBluetoothLeBasicConnection>> it = this.mConnections.entrySet().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    Map.Entry<BluetoothDevice, IBluetoothLeBasicConnection> next = it.next();
                    if (next.getValue().isDfuInProgress() && next.getKey().getAddress().startsWith(bluetoothDevice.getAddress().substring(0, 15))) {
                        z = true;
                        break;
                    }
                }
                if (!z && bluetoothDevice.getBondState() != 11) {
                    if (z2) {
                        if (this.mBackgroundMode) {
                            this.mDeviceServices.onConnectionCreated(bluetoothDevice);
                            NotificationHelper.showIncomingConnectionNotification(this.mContext, bluetoothDevice);
                        } else {
                            onNewConnection(bluetoothDevice);
                        }
                    } else {
                        this.mDeviceServices.onConnectionCreated(bluetoothDevice);
                    }
                }
            } else {
                IBluetoothLeBasicConnection iBluetoothLeBasicConnection = this.mConnections.get(bluetoothDevice);
                if (!iBluetoothLeBasicConnection.isDfuInProgress()) {
                    int i3 = Build.VERSION.SDK_INT;
                    iBluetoothLeBasicConnection.connect(false);
                }
            }
            broadcastAction(bluetoothDevice, ServiceConstants.ACTION_GATT_SERVER_CONNECTED);
        } else if (i2 == 0) {
            this.mConnectedDevices.remove(bluetoothDevice);
            if (this.mConnections.containsKey(bluetoothDevice)) {
                saveLogAndFlash(bluetoothDevice, 5, "[Server] Device disconnected");
            } else {
                NotificationHelper.cancelNotification(this.mContext, bluetoothDevice);
                this.mDeviceServices.onConnectionClosed(bluetoothDevice);
            }
            broadcastAction(bluetoothDevice, ServiceConstants.ACTION_GATT_SERVER_DISCONNECTED);
        }
        Iterator<IServerCallback> it2 = this.mServiceCallbackMap.values().iterator();
        while (it2.hasNext()) {
            it2.next().onConnectionStateChange(this.mServer, bluetoothDevice, i, i2);
        }
    }

    public void onConnectionUpdated(BluetoothDevice bluetoothDevice, int i, int i2, int i3, int i4) {
    }

    @Override // android.bluetooth.BluetoothGattServerCallback
    public void onDescriptorReadRequest(BluetoothDevice bluetoothDevice, int i, int i2, BluetoothGattDescriptor bluetoothGattDescriptor) {
        byte[] bArr;
        saveLogBulk(bluetoothDevice, 0, "[Server callback] Read request for descriptor " + bluetoothGattDescriptor.getUuid() + " (requestId=" + i + ", offset=" + i2 + ")");
        IServerCallback iServerCallback = this.mServiceCallbackMap.get(bluetoothGattDescriptor.getCharacteristic().getService());
        BluetoothGattDescriptor deviceDescriptor = this.mDeviceServices.getDeviceDescriptor(bluetoothDevice, bluetoothGattDescriptor, false);
        if (iServerCallback == null || !iServerCallback.onDescriptorReadRequest(this.mServer, bluetoothDevice, i, i2, deviceDescriptor)) {
            saveLogBulk(bluetoothDevice, 5, "[Server] READ request for descriptor " + deviceDescriptor.getUuid() + " received");
            byte[] value = deviceDescriptor.getValue();
            if (value == null || i2 <= 0) {
                bArr = value;
            } else {
                byte[] bArr2 = new byte[value.length - i2];
                System.arraycopy(value, i2, bArr2, 0, bArr2.length);
                bArr = bArr2;
            }
            if (bArr != null) {
                saveLogBulk(bluetoothDevice, 0, "server.sendResponse(GATT_SUCCESS, value=" + ParserUtils.bytesToHex(bArr, true) + ")");
            } else {
                saveLogBulk(bluetoothDevice, 0, "server.sendResponse(GATT_SUCCESS, value=null)");
            }
            this.mServer.sendResponse(bluetoothDevice, i, 0, i2, bArr);
            saveLogAndFlash(bluetoothDevice, 1, "[Server] Response sent");
            if (i2 == 0) {
                String valueAsString = DescriptorParser.getValueAsString(deviceDescriptor, null, true);
                if (!TextUtils.isEmpty(valueAsString)) {
                    saveLogAndFlash(bluetoothDevice, 10, "[Server] \"" + valueAsString + "\" sent");
                }
            }
        }
        IBluetoothLeBasicConnection iBluetoothLeBasicConnection = this.mConnections.get(bluetoothDevice);
        if (iBluetoothLeBasicConnection != null) {
            iBluetoothLeBasicConnection.onReadRequest(deviceDescriptor);
        }
        if (iServerCallback != null) {
            broadcastAction(bluetoothDevice, ServiceConstants.ACTION_GATT_SERVER_DATA_SEND);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:13:0x01aa  */
    /* JADX WARN: Removed duplicated region for block: B:16:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0198  */
    /* JADX WARN: Type inference failed for: r11v1 */
    /* JADX WARN: Type inference failed for: r11v2, types: [int, boolean] */
    /* JADX WARN: Type inference failed for: r11v3 */
    @Override // android.bluetooth.BluetoothGattServerCallback
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onDescriptorWriteRequest(android.bluetooth.BluetoothDevice r20, int r21, android.bluetooth.BluetoothGattDescriptor r22, boolean r23, boolean r24, int r25, byte[] r26) {
        /*
            Method dump skipped, instructions count: 432
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: no.nordicsemi.android.mcp.ble.server.ServiceServerController.onDescriptorWriteRequest(android.bluetooth.BluetoothDevice, int, android.bluetooth.BluetoothGattDescriptor, boolean, boolean, int, byte[]):void");
    }

    @Override // android.bluetooth.BluetoothGattServerCallback
    public void onExecuteWrite(BluetoothDevice bluetoothDevice, int i, boolean z) {
        boolean z2;
        saveLogBulk(bluetoothDevice, 0, "[Server callback] Execute write request (requestId=" + i + ", execute=" + z + ")");
        Iterator<IServerCallback> it = this.mServiceCallbackMap.values().iterator();
        loop0: while (true) {
            while (it.hasNext()) {
                z2 = it.next().onExecuteWrite(this.mServer, bluetoothDevice, i, z) || z2;
            }
        }
        if (!z2) {
            if (z) {
                this.mDeviceServices.executeTemporaryServices(bluetoothDevice);
                saveLogBulk(bluetoothDevice, 5, "[Server] Execute write request received");
            } else {
                this.mDeviceServices.abortTemporaryServices(bluetoothDevice);
                saveLogBulk(bluetoothDevice, 5, "[Server] Cancel write request received");
            }
            saveLogBulk(bluetoothDevice, 0, "server.sendResponse(GATT_SUCCESS, offset=0, value=null)");
            this.mServer.sendResponse(bluetoothDevice, i, 0, 0, null);
            saveLogAndFlash(bluetoothDevice, 1, "[Server] Response sent");
        }
        IBluetoothLeBasicConnection iBluetoothLeBasicConnection = this.mConnections.get(bluetoothDevice);
        if (iBluetoothLeBasicConnection != null) {
            iBluetoothLeBasicConnection.onExecuteWrite(z);
        }
        broadcastWriteExecuted(bluetoothDevice);
    }

    @Override // android.bluetooth.BluetoothGattServerCallback
    public void onMtuChanged(BluetoothDevice bluetoothDevice, int i) {
        saveLogAndFlash(bluetoothDevice, 5, "[Server] MTU changed to " + i);
        IBluetoothLeBasicConnection iBluetoothLeBasicConnection = this.mConnections.get(bluetoothDevice);
        if (iBluetoothLeBasicConnection != null) {
            iBluetoothLeBasicConnection.setMtu(i);
        }
    }

    @Override // no.nordicsemi.android.mcp.util.NotificationHelper.NewConnectionListener
    public void onNewConnection(BluetoothDevice bluetoothDevice) {
        this.mServiceBinder.createConnection(new Device(bluetoothDevice), false);
        saveLogBulk(bluetoothDevice, 0, "[Server callback] Connection state changed with status: 0 and new state: CONNECTED (2)");
        saveLogBulk(bluetoothDevice, 5, "[Server] Device with address " + bluetoothDevice.getAddress() + " connected");
        int i = Build.VERSION.SDK_INT;
    }

    @Override // android.bluetooth.BluetoothGattServerCallback
    public void onNotificationSent(BluetoothDevice bluetoothDevice, int i) {
        if (i == 0) {
            BluetoothGattCharacteristic bluetoothGattCharacteristic = this.mTmpCharacteristic;
            String parse = (bluetoothGattCharacteristic.getValue() == null || bluetoothGattCharacteristic.getValue().length <= 0) ? "0 bytes" : GeneralCharacteristicParser.parse(bluetoothGattCharacteristic);
            StringBuilder sb = new StringBuilder();
            sb.append("[Server] ");
            sb.append(this.mConfirm ? "Indication" : "Notification");
            sb.append(" sent to ");
            sb.append(bluetoothGattCharacteristic.getUuid());
            sb.append(", value: ");
            sb.append(parse);
            saveLogBulk(bluetoothDevice, 5, sb.toString());
            String valueAsString = CharacteristicParser.getValueAsString(this.mDatabase, bluetoothGattCharacteristic, null, true);
            if (!TextUtils.isEmpty(valueAsString)) {
                saveLogBulk(bluetoothDevice, 10, "[Server] \"" + valueAsString + "\" sent");
            }
        } else {
            saveLogError(bluetoothDevice, i);
        }
        this.mTmpCharacteristic = null;
        IBluetoothLeBasicConnection iBluetoothLeBasicConnection = this.mConnections.get(bluetoothDevice);
        if (iBluetoothLeBasicConnection != null) {
            iBluetoothLeBasicConnection.onNotificationSent(i);
            iBluetoothLeBasicConnection.flashLog(false);
        }
    }

    @Override // android.bluetooth.BluetoothGattServerCallback
    public void onPhyRead(BluetoothDevice bluetoothDevice, int i, int i2, int i3) {
        if (i3 == 0) {
            saveLogAndFlash(bluetoothDevice, 5, "[Server] PHY read (TX: " + phyToText(i) + ", RX: " + phyToText(i2) + ")");
            return;
        }
        saveLogError(bluetoothDevice, i3);
    }

    @Override // android.bluetooth.BluetoothGattServerCallback
    public void onPhyUpdate(BluetoothDevice bluetoothDevice, int i, int i2, int i3) {
        if (i3 == 0) {
            saveLogAndFlash(bluetoothDevice, 5, "[Server] PHY updated (TX: " + phyToText(i) + ", RX: " + phyToText(i2) + ")");
            return;
        }
        saveLogError(bluetoothDevice, i3);
    }

    @Override // android.bluetooth.BluetoothGattServerCallback
    public void onServiceAdded(int i, BluetoothGattService bluetoothGattService) {
        saveLogAndFlash(0, "[Server callback] Service " + bluetoothGattService.getUuid() + " added with status: " + i);
        if (i == 0) {
            addNextService();
        } else {
            close();
            this.mControllerCallback.onError(i);
        }
    }

    public void removeConnection(BluetoothDevice bluetoothDevice) {
        this.mDeviceServices.onConnectionClosed(bluetoothDevice);
        this.mConnections.remove(bluetoothDevice);
    }

    public void sendCharacteristicNotification(BluetoothDevice bluetoothDevice, BluetoothGattCharacteristic bluetoothGattCharacteristic, boolean z) {
        if (z) {
            saveLogBulk(bluetoothDevice, 1, "[Server] Sending indication from " + bluetoothGattCharacteristic.getUuid());
        } else {
            saveLogBulk(bluetoothDevice, 1, "[Server] Sending notification from " + bluetoothGattCharacteristic.getUuid());
        }
        if (this.mServer != null) {
            BluetoothGattCharacteristic serverCharacteristic = this.mDeviceServices.getServerCharacteristic(bluetoothDevice, bluetoothGattCharacteristic);
            this.mTmpCharacteristic = serverCharacteristic;
            if (serverCharacteristic != null) {
                serverCharacteristic.setValue(bluetoothGattCharacteristic.getValue());
                this.mConfirm = z;
                saveLogAndFlash(bluetoothDevice, 0, "server.notifyCharacteristicChanged(" + serverCharacteristic.getUuid() + ", value=" + ParserUtils.bytesToHex(serverCharacteristic.getValue(), true) + ", confirm= " + z + ")");
                try {
                    this.mServer.notifyCharacteristicChanged(bluetoothDevice, serverCharacteristic, z);
                    return;
                } catch (Exception unused) {
                    saveLogAndFlash(bluetoothDevice, 20, "[Server] Error: Sending notification failed");
                    return;
                }
            }
            throw new NullPointerException("Server char not found: " + bluetoothGattCharacteristic.getUuid() + " (" + bluetoothGattCharacteristic.getInstanceId() + ") in\n" + this.mDeviceServices.dump(bluetoothDevice));
        }
        saveLogAndFlash(bluetoothDevice, 20, "[Server] Error: Server not started");
        IBluetoothLeBasicConnection iBluetoothLeBasicConnection = this.mConnections.get(bluetoothDevice);
        if (iBluetoothLeBasicConnection != null) {
            iBluetoothLeBasicConnection.onNotificationSent(-1);
        }
    }

    public void setBackgroundMode(boolean z) {
        this.mBackgroundMode = z;
    }

    public void setServerConfiguration(ServerConfiguration serverConfiguration) {
        this.mServerConfiguration = serverConfiguration;
    }

    public void start(Context context) {
        if (this.mServer == null) {
            this.mServer = ((BluetoothManager) context.getSystemService("bluetooth")).openGattServer(context, this);
        } else {
            Iterator<IServerCallback> it = this.mServiceCallbackMap.values().iterator();
            while (it.hasNext()) {
                try {
                    it.next().onServerClosed();
                } catch (Exception unused) {
                }
            }
            this.mServiceCallbackMap.clear();
            for (IBluetoothLeBasicConnection iBluetoothLeBasicConnection : this.mConnections.values()) {
                iBluetoothLeBasicConnection.saveLogBulk(1, "Updating server database...");
                iBluetoothLeBasicConnection.saveLogAndFlash(0, "server.clearServices()");
            }
            this.mServer.clearServices();
        }
        if (this.mServer != null) {
            addNextService();
        } else {
            this.mControllerCallback.onError(-1);
        }
    }

    public void updateConnection(BluetoothDevice bluetoothDevice, IBluetoothLeBasicConnection iBluetoothLeBasicConnection) {
        this.mDeviceServices.onConnectionCreated(bluetoothDevice);
        iBluetoothLeBasicConnection.saveLogAndFlash(5, "[Server] Database changed");
        saveServicesUpdate(iBluetoothLeBasicConnection, getServices(bluetoothDevice, false));
    }

    private void saveLogAndFlash(BluetoothDevice bluetoothDevice, int i, String str) {
        IBluetoothLeBasicConnection iBluetoothLeBasicConnection = this.mConnections.get(bluetoothDevice);
        if (iBluetoothLeBasicConnection != null) {
            iBluetoothLeBasicConnection.saveLogAndFlash(i, str);
        }
    }
}
