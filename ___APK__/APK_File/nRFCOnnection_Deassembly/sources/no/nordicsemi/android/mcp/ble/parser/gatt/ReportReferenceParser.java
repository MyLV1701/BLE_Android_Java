package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattDescriptor;
import no.nordicsemi.android.mcp.ble.parser.gap.FlagsParser;

/* loaded from: classes.dex */
public class ReportReferenceParser {
    public static String parse(BluetoothGattDescriptor bluetoothGattDescriptor) {
        byte[] value = bluetoothGattDescriptor.getValue();
        if (value.length != 1) {
            return "Incorrect data length (1 byte expected): " + GeneralDescriptorParser.parse(bluetoothGattDescriptor);
        }
        int unsignedByteToInt = unsignedByteToInt(value[0]);
        if (unsignedByteToInt == 1) {
            return "Input report";
        }
        if (unsignedByteToInt == 2) {
            return "Output report";
        }
        if (unsignedByteToInt == 3) {
            return "Feature report";
        }
        return "Unsupported value: " + GeneralDescriptorParser.parse(bluetoothGattDescriptor);
    }

    private static int unsignedByteToInt(byte b2) {
        return b2 & FlagsParser.UNKNOWN_FLAGS;
    }
}
