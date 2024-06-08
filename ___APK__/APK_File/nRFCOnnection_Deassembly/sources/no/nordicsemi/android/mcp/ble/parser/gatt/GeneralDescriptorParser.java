package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattDescriptor;

/* loaded from: classes.dex */
public class GeneralDescriptorParser {
    public static String parse(BluetoothGattDescriptor bluetoothGattDescriptor) {
        return GeneralCharacteristicParser.parse(bluetoothGattDescriptor.getValue(), 0);
    }
}
