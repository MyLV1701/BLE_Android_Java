package no.nordicsemi.android.mcp.ble.server.impl;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattServer;
import android.bluetooth.BluetoothGattService;
import android.content.Context;
import android.os.Handler;
import java.util.List;
import no.nordicsemi.android.mcp.ble.IBluetoothLeBasicConnection;
import no.nordicsemi.android.mcp.ble.server.IServerCallback;
import no.nordicsemi.android.mcp.ble.server.ServiceMap;
import no.nordicsemi.android.mcp.ble.server.ServiceServerController;
import no.nordicsemi.android.mcp.test.exception.DeviceNotConnectedException;
import no.nordicsemi.android.mcp.util.AdoptedDescriptorsHelper;

/* loaded from: classes.dex */
public abstract class ServerCallbackAdapter implements IServerCallback {
    private final Context mContext;
    private final ServiceMap mDeviceServices;
    protected final Handler mHandler;
    private final ServiceServerController mServerController;
    private final BluetoothGattService mService;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ServerCallbackAdapter(Context context, Handler handler, ServiceServerController serviceServerController, ServiceMap serviceMap, BluetoothGattService bluetoothGattService) {
        this.mHandler = handler;
        this.mContext = context;
        this.mServerController = serviceServerController;
        this.mService = bluetoothGattService;
        this.mDeviceServices = serviceMap;
    }

    private void sendCharacteristicNotification(BluetoothDevice bluetoothDevice, BluetoothGattCharacteristic bluetoothGattCharacteristic, boolean z) {
        IBluetoothLeBasicConnection connection = this.mServerController.getConnection(bluetoothDevice);
        if (connection != null) {
            try {
                connection.waitUntilOperationCompleted();
            } catch (InterruptedException unused) {
            }
            try {
                if (z) {
                    connection.sendCharacteristicIndication(bluetoothGattCharacteristic);
                } else {
                    connection.sendCharacteristicNotification(bluetoothGattCharacteristic);
                }
                return;
            } catch (DeviceNotConnectedException unused2) {
                return;
            }
        }
        this.mServerController.sendCharacteristicNotification(bluetoothDevice, bluetoothGattCharacteristic, z);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Context getContext() {
        return this.mContext;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BluetoothGattService getDeviceService(BluetoothDevice bluetoothDevice) {
        return this.mDeviceServices.getDeviceService(bluetoothDevice, this.mService);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BluetoothGattService getServerService() {
        return this.mService;
    }

    @Override // no.nordicsemi.android.mcp.ble.server.IServerCallback
    public boolean onCharacteristicReadRequest(BluetoothGattServer bluetoothGattServer, BluetoothDevice bluetoothDevice, int i, int i2, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return false;
    }

    @Override // no.nordicsemi.android.mcp.ble.server.IServerCallback
    public boolean onCharacteristicWriteRequest(BluetoothGattServer bluetoothGattServer, BluetoothDevice bluetoothDevice, int i, BluetoothGattCharacteristic bluetoothGattCharacteristic, boolean z, boolean z2, int i2, byte[] bArr) {
        return false;
    }

    @Override // no.nordicsemi.android.mcp.ble.server.IServerCallback
    public void onConnectionLost(BluetoothGattServer bluetoothGattServer, BluetoothDevice bluetoothDevice) {
    }

    @Override // no.nordicsemi.android.mcp.ble.server.IServerCallback
    public void onConnectionStateChange(BluetoothGattServer bluetoothGattServer, BluetoothDevice bluetoothDevice, int i, int i2) {
    }

    @Override // no.nordicsemi.android.mcp.ble.server.IServerCallback
    public boolean onDescriptorReadRequest(BluetoothGattServer bluetoothGattServer, BluetoothDevice bluetoothDevice, int i, int i2, BluetoothGattDescriptor bluetoothGattDescriptor) {
        return false;
    }

    @Override // no.nordicsemi.android.mcp.ble.server.IServerCallback
    public boolean onDescriptorWriteRequest(BluetoothGattServer bluetoothGattServer, BluetoothDevice bluetoothDevice, int i, BluetoothGattDescriptor bluetoothGattDescriptor, boolean z, boolean z2, int i2, byte[] bArr) {
        return false;
    }

    @Override // no.nordicsemi.android.mcp.ble.server.IServerCallback
    public boolean onExecuteWrite(BluetoothGattServer bluetoothGattServer, BluetoothDevice bluetoothDevice, int i, boolean z) {
        return false;
    }

    @Override // no.nordicsemi.android.mcp.ble.server.IServerCallback
    public void onServerClosed() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void sendCharacteristicNotification(BluetoothGattCharacteristic bluetoothGattCharacteristic, boolean z) {
        sendCharacteristicNotification(this.mServerController.getConnectedDevices(), bluetoothGattCharacteristic, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void sendCharacteristicNotification(List<BluetoothDevice> list, BluetoothGattCharacteristic bluetoothGattCharacteristic, boolean z) {
        for (BluetoothDevice bluetoothDevice : list) {
            BluetoothGattCharacteristic deviceCharacteristic = this.mDeviceServices.getDeviceCharacteristic(bluetoothDevice, bluetoothGattCharacteristic, false);
            BluetoothGattDescriptor cccd = AdoptedDescriptorsHelper.getCCCD(deviceCharacteristic);
            if (cccd != null && cccd.getValue() != null && cccd.getValue().length == 2 && (cccd.getValue()[0] == 1 || cccd.getValue()[1] == 2)) {
                deviceCharacteristic.setValue(bluetoothGattCharacteristic.getValue());
                sendCharacteristicNotification(bluetoothDevice, deviceCharacteristic, z);
                IBluetoothLeBasicConnection connection = this.mServerController.getConnection(bluetoothDevice);
                if (connection != null) {
                    try {
                        connection.waitUntilOperationCompleted();
                    } catch (InterruptedException unused) {
                    }
                }
            }
        }
    }
}
