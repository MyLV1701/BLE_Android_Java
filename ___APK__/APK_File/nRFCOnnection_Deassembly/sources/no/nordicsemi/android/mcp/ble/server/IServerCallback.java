package no.nordicsemi.android.mcp.ble.server;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattServer;

/* loaded from: classes.dex */
public interface IServerCallback {
    boolean onCharacteristicReadRequest(BluetoothGattServer bluetoothGattServer, BluetoothDevice bluetoothDevice, int i, int i2, BluetoothGattCharacteristic bluetoothGattCharacteristic);

    boolean onCharacteristicWriteRequest(BluetoothGattServer bluetoothGattServer, BluetoothDevice bluetoothDevice, int i, BluetoothGattCharacteristic bluetoothGattCharacteristic, boolean z, boolean z2, int i2, byte[] bArr);

    void onConnectionLost(BluetoothGattServer bluetoothGattServer, BluetoothDevice bluetoothDevice);

    void onConnectionStateChange(BluetoothGattServer bluetoothGattServer, BluetoothDevice bluetoothDevice, int i, int i2);

    boolean onDescriptorReadRequest(BluetoothGattServer bluetoothGattServer, BluetoothDevice bluetoothDevice, int i, int i2, BluetoothGattDescriptor bluetoothGattDescriptor);

    boolean onDescriptorWriteRequest(BluetoothGattServer bluetoothGattServer, BluetoothDevice bluetoothDevice, int i, BluetoothGattDescriptor bluetoothGattDescriptor, boolean z, boolean z2, int i2, byte[] bArr);

    boolean onExecuteWrite(BluetoothGattServer bluetoothGattServer, BluetoothDevice bluetoothDevice, int i, boolean z);

    void onServerClosed();
}
