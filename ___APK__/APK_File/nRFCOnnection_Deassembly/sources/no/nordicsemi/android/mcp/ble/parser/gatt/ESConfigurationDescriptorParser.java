package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattDescriptor;

/* loaded from: classes.dex */
public class ESConfigurationDescriptorParser {
    public static String parse(BluetoothGattDescriptor bluetoothGattDescriptor) {
        byte b2 = bluetoothGattDescriptor.getValue()[0];
        if (b2 == 0) {
            return "Boolean AND";
        }
        if (b2 == 1) {
            return "Boolean OR";
        }
        return "Unknown value: " + ((int) b2);
    }
}
