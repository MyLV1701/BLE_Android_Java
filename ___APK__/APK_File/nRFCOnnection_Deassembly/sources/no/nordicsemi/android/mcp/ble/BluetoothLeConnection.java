package no.nordicsemi.android.mcp.ble;

import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.util.Pair;
import android.util.SparseArray;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.crypto.spec.SecretKeySpec;
import no.nordicsemi.android.log.LocalLogSession;
import no.nordicsemi.android.log.Logger;
import no.nordicsemi.android.mcp.ble.model.Device;
import no.nordicsemi.android.mcp.ble.parser.utils.ParserUtils;
import no.nordicsemi.android.mcp.ble.server.ServiceServerController;
import no.nordicsemi.android.mcp.connection.macro.domain.Macro;
import no.nordicsemi.android.mcp.connection.macro.domain.MacroHandler;
import no.nordicsemi.android.mcp.connection.macro.domain.UnlockEddystone;
import no.nordicsemi.android.mcp.database.init.ThirdPartyCharacteristics;
import no.nordicsemi.android.mcp.database.init.ThirdPartyServices;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;
import no.nordicsemi.android.mcp.database.provider.EddystoneUtils;
import no.nordicsemi.android.mcp.log.LocalLogContract;
import no.nordicsemi.android.mcp.settings.SettingsFragment;
import no.nordicsemi.android.mcp.test.exception.DeviceNotConnectedException;

/* loaded from: classes.dex */
public class BluetoothLeConnection extends BluetoothLeBasicConnection implements IBluetoothLeConnection {
    private static final String TAG = "BluetoothLeConnection";
    private final Device mDevice;
    private SparseArray<MacroHandler> mMacroHandlers;
    private final OnConnectionClosedListener mOnConnectionClosedListener;
    private Map<String, Object> mStorage;

    /* loaded from: classes.dex */
    public interface OnConnectionClosedListener {
        void onConnectionClosed(IBluetoothLeConnection iBluetoothLeConnection);
    }

    public BluetoothLeConnection(Context context, Handler handler, DatabaseHelper databaseHelper, Device device, OnConnectionClosedListener onConnectionClosedListener) {
        super(context, handler, databaseHelper, device.getBluetoothDevice(), null);
        this.mStorage = new HashMap();
        this.mDevice = device;
        this.mBluetoothDeviceName = device.getName();
        this.mOnConnectionClosedListener = onConnectionClosedListener;
        this.mMacroHandlers = new SparseArray<>(4);
    }

    public /* synthetic */ void a(byte[] bArr, String str) {
        boolean z;
        byte[] value;
        BluetoothGattCharacteristic bluetoothGattCharacteristic = null;
        BluetoothGattCharacteristic bluetoothGattCharacteristic2 = null;
        BluetoothGattCharacteristic bluetoothGattCharacteristic3 = null;
        BluetoothGattCharacteristic bluetoothGattCharacteristic4 = null;
        BluetoothGattCharacteristic bluetoothGattCharacteristic5 = null;
        for (BluetoothGattService bluetoothGattService : getSupportedGattServices()) {
            if (ThirdPartyServices.isEddystoneConfigService(bluetoothGattService)) {
                bluetoothGattCharacteristic = bluetoothGattService.getCharacteristic(ThirdPartyCharacteristics.EDDYSTONE_ACTIVE_SLOT_UUID);
                bluetoothGattCharacteristic2 = bluetoothGattService.getCharacteristic(ThirdPartyCharacteristics.EDDYSTONE_LOCK_STATE_UUID);
                bluetoothGattCharacteristic3 = bluetoothGattService.getCharacteristic(ThirdPartyCharacteristics.EDDYSTONE_UNLOCK_UUID);
                bluetoothGattCharacteristic4 = bluetoothGattService.getCharacteristic(ThirdPartyCharacteristics.EDDYSTONE_EID_IDENTITY_KEY_UUID);
                bluetoothGattCharacteristic5 = bluetoothGattService.getCharacteristic(ThirdPartyCharacteristics.EDDYSTONE_ADV_SLOT_DATA_UUID);
            }
        }
        if (bluetoothGattCharacteristic != null && bluetoothGattCharacteristic2 != null && bluetoothGattCharacteristic3 != null && bluetoothGattCharacteristic4 != null && bluetoothGattCharacteristic5 != null) {
            try {
                saveLogBulk(10, "Checking Lock state...");
                readCharacteristic(bluetoothGattCharacteristic2);
                waitUntilOperationCompletedSafe();
                if (bluetoothGattCharacteristic2.getIntValue(17, 0).intValue() == 0) {
                    saveLogBulk(10, "Service is locked. Unlocking...");
                    readCharacteristic(bluetoothGattCharacteristic3);
                    waitUntilOperationCompletedSafe();
                    writeCharacteristic(bluetoothGattCharacteristic3, EddystoneUtils.aes128Encrypt(bluetoothGattCharacteristic3.getValue(), new SecretKeySpec(bArr, "AES")));
                    waitUntilOperationCompletedSafe();
                    readCharacteristic(bluetoothGattCharacteristic2);
                    waitUntilOperationCompletedSafe();
                    if (bluetoothGattCharacteristic2.getIntValue(17, 0).intValue() == 0) {
                        saveLogAndFlash(15, "Unlocking Eddystone Configuration Service failed");
                        return;
                    }
                    z = true;
                } else {
                    z = false;
                }
                saveLogBulk(10, "Eddystone Configuration Service unlocked");
                saveLogBulk(10, "Reading active slot number...");
                readCharacteristic(bluetoothGattCharacteristic);
                waitUntilOperationCompletedSafe();
                value = bluetoothGattCharacteristic.getValue();
            } catch (DeviceNotConnectedException unused) {
            }
            if (value != null && value.length == 1) {
                saveLogBulk(10, "Reading slot " + bluetoothGattCharacteristic.getIntValue(17, 0) + " advertising data...");
                readCharacteristic(bluetoothGattCharacteristic5);
                waitUntilOperationCompletedSafe();
                byte[] value2 = bluetoothGattCharacteristic5.getValue();
                if (value2 != null && value2.length != 0) {
                    if (bluetoothGattCharacteristic5.getIntValue(17, 0).intValue() == 48 && value2.length == 14) {
                        int intValue = bluetoothGattCharacteristic5.getIntValue(17, 1).intValue();
                        long intValue2 = ParserUtils.getIntValue(value2, 100, 2) & 4294967295L;
                        saveLogBulk(10, "Reading EID Identity Key...");
                        readCharacteristic(bluetoothGattCharacteristic4);
                        waitUntilOperationCompletedSafe();
                        byte[] value3 = bluetoothGattCharacteristic4.getValue();
                        if (value3 != null && value3.length != 0) {
                            if (value3.length != 16) {
                                saveLogAndFlash(15, "Identity Key is not 16-byte long");
                                return;
                            }
                            this.mDatabase.registerEddystoneKey(str, EddystoneUtils.aes128Decode(value3, new SecretKeySpec(bArr, "AES")), intValue2, intValue);
                            saveLogBulk(10, "EID paired successfully");
                            if (z) {
                                saveLogBulk(10, "Locking Eddystone Configuration Service...");
                                writeCharacteristic(bluetoothGattCharacteristic2, new byte[]{0});
                                waitUntilOperationCompletedSafe();
                            }
                            flashLog(true);
                            return;
                        }
                        saveLogAndFlash(15, "EID Identity Key characteristic returned empty value");
                        return;
                    }
                    saveLogAndFlash(15, "Slot is not configured as EID");
                    return;
                }
                saveLogAndFlash(15, "Slot is empty");
                return;
            }
            saveLogAndFlash(15, "Invalid active slot value returned");
            return;
        }
        saveLogAndFlash(15, "Required characteristics not found");
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeConnection
    public boolean areServicesDiscovered() {
        return this.mServicesDiscovered;
    }

    @Override // no.nordicsemi.android.mcp.ble.BluetoothLeBasicConnection
    protected void broadcastAction(String str) {
        a.l.a.a.a(this.mContext).a(new Intent(str + this.mBluetoothDeviceAddress));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // no.nordicsemi.android.mcp.ble.BluetoothLeBasicConnection
    public void broadcastError(int i) {
        super.broadcastError(i);
        Intent intent = new Intent(ServiceConstants.ACTION_GATT_ERROR + this.mBluetoothDeviceAddress);
        intent.putExtra(ServiceConstants.EXTRA_ERROR_NO, i);
        a.l.a.a.a(this.mContext).a(intent);
    }

    public /* synthetic */ void c() {
        List<BluetoothGattService> supportedGattServices = getSupportedGattServices();
        if (supportedGattServices != null && !supportedGattServices.isEmpty()) {
            saveLogBulk(1, "Enabling services...");
            Iterator<BluetoothGattService> it = supportedGattServices.iterator();
            while (it.hasNext()) {
                for (BluetoothGattCharacteristic bluetoothGattCharacteristic : it.next().getCharacteristics()) {
                    if (this.mConnectionState != 2) {
                        return;
                    }
                    boolean z = (bluetoothGattCharacteristic.getProperties() & 16) > 0;
                    boolean z2 = (bluetoothGattCharacteristic.getProperties() & 32) > 0;
                    if (z || z2) {
                        if (z) {
                            try {
                                setCharacteristicNotification(bluetoothGattCharacteristic, true);
                            } catch (DeviceNotConnectedException unused) {
                            }
                        } else {
                            setCharacteristicIndication(bluetoothGattCharacteristic, true);
                        }
                        try {
                            waitUntilOperationCompleted();
                        } catch (InterruptedException e2) {
                            Log.w(TAG, "Sleeping interrupted", e2);
                        }
                    }
                }
            }
            saveLogAndFlash(1, "All services enabled");
            return;
        }
        saveLogAndFlash(15, "No services found");
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeConnection
    public boolean canDisconnect() {
        return (this.mBluetoothGatt != null && this.mConnectionState == 2) || this.mConnectionState == 1;
    }

    @Override // no.nordicsemi.android.mcp.ble.BluetoothLeBasicConnection
    public void close() {
        OnConnectionClosedListener onConnectionClosedListener;
        super.close();
        if (this.mDfuInProgress || (onConnectionClosedListener = this.mOnConnectionClosedListener) == null) {
            return;
        }
        onConnectionClosedListener.onConnectionClosed(this);
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeConnection
    public boolean containsKey(String str) {
        return this.mStorage.containsKey(str);
    }

    public /* synthetic */ void d() {
        List<BluetoothGattService> supportedGattServices = getSupportedGattServices();
        if (supportedGattServices == null || supportedGattServices.isEmpty()) {
            return;
        }
        saveLogBulk(1, "Reading all characteristics...");
        int i = 0;
        Iterator<BluetoothGattService> it = supportedGattServices.iterator();
        while (it.hasNext()) {
            for (BluetoothGattCharacteristic bluetoothGattCharacteristic : it.next().getCharacteristics()) {
                if (this.mConnectionState != 2) {
                    return;
                }
                if ((bluetoothGattCharacteristic.getProperties() & 2) > 0) {
                    bluetoothGattCharacteristic.setValue((byte[]) null);
                    try {
                        readCharacteristic(bluetoothGattCharacteristic);
                        i++;
                    } catch (DeviceNotConnectedException unused) {
                    }
                    try {
                        waitUntilOperationCompleted();
                    } catch (InterruptedException e2) {
                        Log.w(TAG, "Sleeping interrupted", e2);
                    }
                }
            }
        }
        if (i > 0) {
            saveLogAndFlash(1, i + " characteristics read");
            return;
        }
        saveLogAndFlash(15, "No readable characteristics found");
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeConnection
    public void enableAllServices() {
        if (this.mOperationInProgress) {
            return;
        }
        new Thread(new Runnable() { // from class: no.nordicsemi.android.mcp.ble.g
            @Override // java.lang.Runnable
            public final void run() {
                BluetoothLeConnection.this.c();
            }
        }).start();
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeConnection
    public Object get(String str) {
        return this.mStorage.get(str);
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeConnection
    public Context getContext() {
        return this.mContext;
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeConnection
    public Macro getCurrentlyRecordingMacro() {
        return this.mCurrentlyRecordingMacro;
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeConnection
    public Device getDevice() {
        return this.mDevice;
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeConnection
    public Pair<List<Integer>, List<Float>> getDfuAvgSpeedValues() {
        return new Pair<>(this.mDfuProgressIndexes, this.mDfuAvgSpeedValues);
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeConnection
    public int getDfuMaxAvgSpeed() {
        return this.mDfuMaxAvgSpeed;
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeConnection
    public Pair<List<Integer>, List<Float>> getDfuSpeedValues() {
        return new Pair<>(this.mDfuProgressIndexes, this.mDfuSpeedValues);
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeConnection
    public String getLogContent() {
        Cursor query = this.mContext.getContentResolver().query(this.mLogSession.getSessionContentUri(), null, null, null, null);
        try {
            if (query.moveToNext()) {
                return query.getString(0);
            }
            return null;
        } finally {
            query.close();
        }
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeConnection
    public Uri getLogSessionEntriesUri() {
        return this.mLogSession.getSessionEntriesUri();
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeConnection
    public MacroHandler getMacroStatus(int i) {
        return this.mMacroHandlers.get(i);
    }

    @Override // no.nordicsemi.android.mcp.ble.BluetoothLeBasicConnection
    protected boolean isAutoMtuRequestEnabled() {
        return PreferenceManager.getDefaultSharedPreferences(this.mContext).getBoolean(SettingsFragment.SETTINGS_AUTO_MTU_REQUEST, false);
    }

    @Override // no.nordicsemi.android.mcp.ble.BluetoothLeBasicConnection
    protected boolean isAutoStartServiceDiscovery() {
        return PreferenceManager.getDefaultSharedPreferences(this.mContext).getBoolean(SettingsFragment.SETTINGS_AUTO_SERVICE_DISCOVERY, true);
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeConnection
    public boolean isConnected() {
        return this.mBluetoothGatt != null && this.mConnectionState == 2;
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeConnection
    public boolean isConnectedToServer() {
        ServiceServerController serviceServerController = this.mServerController;
        return serviceServerController != null && serviceServerController.isConnected(this.mBluetoothDevice);
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeConnection
    public boolean isConnectionAttemptDone() {
        return this.mConnectionAttemptDone;
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeConnection
    public boolean isMacroRunning() {
        return this.mMacroHandlers.size() == 1 && this.mMacroHandlers.valueAt(0).isMacroRunning();
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeConnection
    public boolean isMacroTracked(int i) {
        return this.mMacroHandlers.get(i) != null;
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeConnection
    public boolean isPredefinedServerService(BluetoothGattService bluetoothGattService) {
        ServiceServerController serviceServerController = this.mServerController;
        return serviceServerController != null && serviceServerController.isPredefinedServerService(this.mBluetoothDevice, bluetoothGattService);
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeConnection
    public boolean isRecordingMacro() {
        return this.mCurrentlyRecordingMacro != null;
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeConnection
    public void newLogSession(boolean z) {
        if (!z) {
            deleteLogSession();
        }
        this.mLogSession = Logger.newSession(this.mContext, this.mBluetoothDeviceAddress, this.mBluetoothDeviceName);
        if (this.mLogSession == null) {
            this.mLogSession = LocalLogSession.newSession(this.mContext, LocalLogContract.AUTHORITY_URI, this.mBluetoothDeviceAddress, this.mBluetoothDeviceName);
        }
        broadcastAction(ServiceConstants.ACTION_NEW_LOG_SESSION);
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeConnection
    public void notifyDatasetChanged(boolean z) {
        broadcastAction(z ? ServiceConstants.ACTION_DATA_SEND : ServiceConstants.ACTION_GATT_SERVER_DATA_SEND);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // no.nordicsemi.android.mcp.ble.BluetoothLeBasicConnection
    public void onDeviceDisconnected() {
        super.onDeviceDisconnected();
        for (int i = 0; i < this.mMacroHandlers.size(); i++) {
            this.mMacroHandlers.valueAt(i).close();
        }
        this.mMacroHandlers.clear();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // no.nordicsemi.android.mcp.ble.BluetoothLeBasicConnection
    public void onDfuFinished() {
        super.onDfuFinished();
        if (this.mBluetoothGatt != null) {
            if (this.mConnectionState == 2) {
                broadcastAction(ServiceConstants.ACTION_GATT_SERVICES_DISCOVERED);
                return;
            } else {
                connect();
                return;
            }
        }
        close();
    }

    @Override // no.nordicsemi.android.mcp.ble.BluetoothLeBasicConnection, no.nordicsemi.android.mcp.ble.IBluetoothLeBasicConnection
    public void onExecuteWrite(boolean z) {
        if (z) {
            for (int i = 0; i < this.mMacroHandlers.size(); i++) {
                this.mMacroHandlers.valueAt(i).getMacro().invalidate();
            }
        }
        super.onExecuteWrite(z);
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeConnection
    public Object put(String str, Object obj) {
        return this.mStorage.put(str, obj);
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeConnection
    public void readAllCharacteristics() {
        if (this.mOperationInProgress) {
            return;
        }
        new Thread(new Runnable() { // from class: no.nordicsemi.android.mcp.ble.f
            @Override // java.lang.Runnable
            public final void run() {
                BluetoothLeConnection.this.d();
            }
        }).start();
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeConnection
    public void registerEddystoneSlot(final String str, final byte[] bArr) {
        new Thread(new Runnable() { // from class: no.nordicsemi.android.mcp.ble.h
            @Override // java.lang.Runnable
            public final void run() {
                BluetoothLeConnection.this.a(bArr, str);
            }
        }).start();
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeConnection
    public Object remove(String str) {
        return this.mStorage.remove(str);
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeConnection
    public void setAsCentral(boolean z) {
        this.mAsCentral = z;
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeConnection
    public void setAutoConnect(boolean z) {
        this.mAutoConnect = z;
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeConnection
    public void setPreferredPhy(Integer num) {
        this.mInitialPreferredPhy = num;
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeConnection
    public Macro startRecordingMacro() {
        if (this.mCurrentlyRecordingMacro == null) {
            this.mCurrentlyRecordingMacro = new Macro();
        }
        return this.mCurrentlyRecordingMacro;
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeConnection
    public Macro stopRecordingMacro() {
        try {
            return this.mCurrentlyRecordingMacro;
        } finally {
            this.mCurrentlyRecordingMacro = null;
        }
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeConnection
    public MacroHandler trackMacro(int i, Macro macro) {
        MacroHandler macroHandler = new MacroHandler(macro, i, this);
        this.mMacroHandlers.put(i, macroHandler);
        return macroHandler;
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeConnection
    public void unlockEddystone(BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr) {
        if ((this.mOperationInProgress && this.mAwaitingCharacteristic == null) || this.mBluetoothGatt == null) {
            return;
        }
        Macro macro = this.mCurrentlyRecordingMacro;
        if (macro != null) {
            DatabaseHelper databaseHelper = this.mDatabase;
            macro.addOperation(databaseHelper, new UnlockEddystone(databaseHelper, this, bluetoothGattCharacteristic, bArr));
        }
        bluetoothGattCharacteristic.setValue(EddystoneUtils.aes128Encrypt(bluetoothGattCharacteristic.getValue(), new SecretKeySpec(bArr, "AES")));
        int writeType = bluetoothGattCharacteristic.getWriteType();
        saveLogBulk(1, "Writing " + (writeType != 1 ? writeType != 4 ? "request" : "signed request" : "command") + " to characteristic " + bluetoothGattCharacteristic.getUuid());
        if (bluetoothGattCharacteristic.getValue() != null) {
            saveLogBulk(0, "gatt.writeCharacteristic(" + bluetoothGattCharacteristic.getUuid() + ", value=" + ParserUtils.bytesToHex(bluetoothGattCharacteristic.getValue(), 0, bluetoothGattCharacteristic.getValue().length, true) + ")");
        } else {
            saveLogBulk(0, "gatt.writeCharacteristic(" + bluetoothGattCharacteristic.getUuid() + ", value=null)");
        }
        startOperation();
        try {
            this.mBluetoothGatt.writeCharacteristic(bluetoothGattCharacteristic);
        } catch (Exception e2) {
            operationFailed(-1);
            broadcastError(e2);
        }
        flashLog(true);
    }

    @Override // no.nordicsemi.android.mcp.ble.IBluetoothLeConnection
    public void untrackMacro(int i) {
        MacroHandler macroHandler = this.mMacroHandlers.get(i);
        if (macroHandler != null) {
            macroHandler.close();
            this.mMacroHandlers.remove(i);
        }
    }

    @Override // no.nordicsemi.android.mcp.ble.BluetoothLeBasicConnection
    protected void broadcastAction(String str, int i) {
        Intent intent = new Intent(str + this.mBluetoothDeviceAddress);
        intent.putExtra(ServiceConstants.EXTRA_DATA, i);
        a.l.a.a.a(this.mContext).a(intent);
    }
}
