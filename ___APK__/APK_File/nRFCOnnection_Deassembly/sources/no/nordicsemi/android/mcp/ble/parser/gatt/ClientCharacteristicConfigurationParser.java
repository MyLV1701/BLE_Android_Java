package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattDescriptor;
import no.nordicsemi.android.mcp.ble.parser.gap.FlagsParser;

/* loaded from: classes.dex */
public class ClientCharacteristicConfigurationParser {
    public static int getValue(BluetoothGattDescriptor bluetoothGattDescriptor) {
        byte[] value = bluetoothGattDescriptor.getValue();
        if (value == null || value.length != 2) {
            return 0;
        }
        return unsignedToSigned(unsignedBytesToInt(value[0], value[1]), 16);
    }

    public static String parse(BluetoothGattDescriptor bluetoothGattDescriptor) {
        byte[] value = bluetoothGattDescriptor.getValue();
        if (value == null) {
            return "";
        }
        if (value.length != 2) {
            return "Incorrect data length (16bit expected): " + GeneralDescriptorParser.parse(bluetoothGattDescriptor);
        }
        int unsignedToSigned = unsignedToSigned(unsignedBytesToInt(value[0], value[1]), 16);
        return unsignedToSigned != 0 ? unsignedToSigned != 1 ? unsignedToSigned != 2 ? unsignedToSigned != 3 ? GeneralDescriptorParser.parse(bluetoothGattDescriptor) : "Notifications and indications enabled" : "Indications enabled" : "Notifications enabled" : "Notifications and indications disabled";
    }

    private static int unsignedByteToInt(byte b2) {
        return b2 & FlagsParser.UNKNOWN_FLAGS;
    }

    private static int unsignedBytesToInt(byte b2, byte b3) {
        return unsignedByteToInt(b2) + (unsignedByteToInt(b3) << 8);
    }

    private static int unsignedToSigned(int i, int i2) {
        int i3 = 1 << (i2 - 1);
        return (i & i3) != 0 ? (i3 - (i & (i3 - 1))) * (-1) : i;
    }
}
