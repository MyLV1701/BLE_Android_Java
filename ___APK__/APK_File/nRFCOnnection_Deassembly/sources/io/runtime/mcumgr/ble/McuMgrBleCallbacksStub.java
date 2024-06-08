package io.runtime.mcumgr.ble;

import android.bluetooth.BluetoothDevice;
import no.nordicsemi.android.ble.BleManagerCallbacks;

/* loaded from: classes.dex */
public class McuMgrBleCallbacksStub implements BleManagerCallbacks {
    @Override // no.nordicsemi.android.ble.BleManagerCallbacks
    public void onBatteryValueReceived(BluetoothDevice bluetoothDevice, int i) {
    }

    @Override // no.nordicsemi.android.ble.BleManagerCallbacks
    public void onBonded(BluetoothDevice bluetoothDevice) {
    }

    @Override // no.nordicsemi.android.ble.BleManagerCallbacks
    public void onBondingFailed(BluetoothDevice bluetoothDevice) {
    }

    @Override // no.nordicsemi.android.ble.BleManagerCallbacks
    public void onBondingRequired(BluetoothDevice bluetoothDevice) {
    }

    @Override // no.nordicsemi.android.ble.BleManagerCallbacks
    public void onDeviceConnected(BluetoothDevice bluetoothDevice) {
    }

    @Override // no.nordicsemi.android.ble.BleManagerCallbacks
    public void onDeviceConnecting(BluetoothDevice bluetoothDevice) {
    }

    @Override // no.nordicsemi.android.ble.BleManagerCallbacks
    public void onDeviceDisconnected(BluetoothDevice bluetoothDevice) {
    }

    @Override // no.nordicsemi.android.ble.BleManagerCallbacks
    public void onDeviceDisconnecting(BluetoothDevice bluetoothDevice) {
    }

    @Override // no.nordicsemi.android.ble.BleManagerCallbacks
    public void onDeviceNotSupported(BluetoothDevice bluetoothDevice) {
    }

    @Override // no.nordicsemi.android.ble.BleManagerCallbacks
    public void onDeviceReady(BluetoothDevice bluetoothDevice) {
    }

    @Override // no.nordicsemi.android.ble.BleManagerCallbacks
    public void onError(BluetoothDevice bluetoothDevice, String str, int i) {
    }

    @Override // no.nordicsemi.android.ble.BleManagerCallbacks
    public void onLinkLossOccurred(BluetoothDevice bluetoothDevice) {
    }

    @Override // no.nordicsemi.android.ble.BleManagerCallbacks
    public void onServicesDiscovered(BluetoothDevice bluetoothDevice, boolean z) {
    }

    @Override // no.nordicsemi.android.ble.BleManagerCallbacks
    public boolean shouldEnableBatteryLevelNotifications(BluetoothDevice bluetoothDevice) {
        return false;
    }
}
