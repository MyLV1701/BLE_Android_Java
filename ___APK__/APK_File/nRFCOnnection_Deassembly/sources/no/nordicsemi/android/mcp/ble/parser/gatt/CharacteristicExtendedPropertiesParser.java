package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattDescriptor;
import no.nordicsemi.android.mcp.ble.parser.gap.FlagsParser;

/* loaded from: classes.dex */
public class CharacteristicExtendedPropertiesParser {
    public static String parse(BluetoothGattDescriptor bluetoothGattDescriptor) {
        byte[] value = bluetoothGattDescriptor.getValue();
        if (value == null) {
            return "";
        }
        if (value.length != 2) {
            return "Incorrect data length (2 bytes expected): " + GeneralDescriptorParser.parse(bluetoothGattDescriptor);
        }
        int unsignedToSigned = unsignedToSigned(unsignedBytesToInt(value[1], value[0]), 16);
        return unsignedToSigned != 0 ? unsignedToSigned != 1 ? unsignedToSigned != 2 ? unsignedToSigned != 3 ? GeneralDescriptorParser.parse(bluetoothGattDescriptor) : "Reliable Write and Writable Auxiliaries enabled" : "Writable Auxiliaries enabled" : "Reliable Write enabled" : "Reliable Write and Writable Auxiliaries disabled";
    }

    private static int unsignedByteToInt(byte b2) {
        return b2 & FlagsParser.UNKNOWN_FLAGS;
    }

    private static int unsignedBytesToInt(byte b2, byte b3) {
        return (unsignedByteToInt(b2) << 8) + unsignedByteToInt(b3);
    }

    private static int unsignedToSigned(int i, int i2) {
        int i3 = 1 << (i2 - 1);
        return (i & i3) != 0 ? (i3 - (i & (i3 - 1))) * (-1) : i;
    }
}
