package no.nordicsemi.android.ble;

import android.bluetooth.BluetoothDevice;

/* loaded from: classes.dex */
public interface BleManagerCallbacks {
    @Deprecated
    void onBatteryValueReceived(BluetoothDevice bluetoothDevice, int i);

    void onBonded(BluetoothDevice bluetoothDevice);

    void onBondingFailed(BluetoothDevice bluetoothDevice);

    void onBondingRequired(BluetoothDevice bluetoothDevice);

    void onDeviceConnected(BluetoothDevice bluetoothDevice);

    void onDeviceConnecting(BluetoothDevice bluetoothDevice);

    void onDeviceDisconnected(BluetoothDevice bluetoothDevice);

    void onDeviceDisconnecting(BluetoothDevice bluetoothDevice);

    void onDeviceNotSupported(BluetoothDevice bluetoothDevice);

    void onDeviceReady(BluetoothDevice bluetoothDevice);

    void onError(BluetoothDevice bluetoothDevice, String str, int i);

    void onLinkLossOccurred(BluetoothDevice bluetoothDevice);

    void onServicesDiscovered(BluetoothDevice bluetoothDevice, boolean z);

    @Deprecated
    boolean shouldEnableBatteryLevelNotifications(BluetoothDevice bluetoothDevice);
}
