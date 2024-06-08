package no.nordicsemi.android.mcp.ble.dfu.initiator;

import android.bluetooth.BluetoothDevice;

/* loaded from: classes.dex */
public class ExtendedBluetoothDevice {
    public BluetoothDevice device;
    public boolean isBonded;
    public int rssi;

    /* loaded from: classes.dex */
    public static class AddressComparator {
        public String address;

        public boolean equals(Object obj) {
            if (obj instanceof ExtendedBluetoothDevice) {
                return this.address.equals(((ExtendedBluetoothDevice) obj).device.getAddress());
            }
            return super.equals(obj);
        }
    }

    public ExtendedBluetoothDevice(BluetoothDevice bluetoothDevice, int i, boolean z) {
        this.device = bluetoothDevice;
        this.rssi = i;
        this.isBonded = z;
    }

    public boolean equals(Object obj) {
        if (obj instanceof ExtendedBluetoothDevice) {
            return this.device.getAddress().equals(((ExtendedBluetoothDevice) obj).device.getAddress());
        }
        return super.equals(obj);
    }
}
