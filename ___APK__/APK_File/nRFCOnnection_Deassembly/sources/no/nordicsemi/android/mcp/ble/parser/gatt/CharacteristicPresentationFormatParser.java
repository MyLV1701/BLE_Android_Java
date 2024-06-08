package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import no.nordicsemi.android.mcp.ble.parser.gap.FlagsParser;

/* loaded from: classes.dex */
public class CharacteristicPresentationFormatParser {
    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic, BluetoothGattDescriptor bluetoothGattDescriptor) {
        byte[] value;
        byte[] value2 = bluetoothGattCharacteristic.getValue();
        if (value2 == null || value2.length == 0) {
            return "";
        }
        if (bluetoothGattDescriptor == null || (value = bluetoothGattDescriptor.getValue()) == null || value.length < 4) {
            return null;
        }
        int unsignedByteToInt = unsignedByteToInt(value[0]);
        byte b2 = value[1];
        int unsignedBytesToInt = unsignedBytesToInt(value[2], value[3]);
        String parse = FormatParser.parse(value2, unsignedByteToInt, b2);
        if (parse == null) {
            return null;
        }
        return parse + " " + UnitParser.toUnit(unsignedBytesToInt);
    }

    private static int unsignedByteToInt(byte b2) {
        return b2 & FlagsParser.UNKNOWN_FLAGS;
    }

    private static int unsignedBytesToInt(byte b2, byte b3) {
        return unsignedByteToInt(b2) + (unsignedByteToInt(b3) << 8);
    }

    public static String parse(BluetoothGattDescriptor bluetoothGattDescriptor) {
        byte[] value = bluetoothGattDescriptor.getValue();
        if (value == null) {
            return "";
        }
        if (value.length != 7) {
            return "Incorrect data length (7 bytes expected): " + GeneralDescriptorParser.parse(bluetoothGattDescriptor);
        }
        StringBuilder sb = new StringBuilder();
        int unsignedByteToInt = unsignedByteToInt(value[0]);
        sb.append("Format: ");
        sb.append(FormatParser.name(unsignedByteToInt));
        byte b2 = value[1];
        sb.append("\nExponent: ");
        sb.append((int) b2);
        int unsignedBytesToInt = unsignedBytesToInt(value[2], value[3]);
        sb.append("\nUnit: ");
        sb.append(UnitParser.name(unsignedBytesToInt));
        byte b3 = value[4];
        int unsignedBytesToInt2 = unsignedBytesToInt(value[5], value[6]);
        if (b3 == 1) {
            sb.append("\nNamespace: Bluetooth SIG Assigned Numbers");
            sb.append("\nDescription: ");
            sb.append(SIGDescriptionParser.parse(unsignedBytesToInt2));
        } else {
            sb.append("\nNamespace: Reserved for future use (");
            sb.append((int) b3);
            sb.append(")");
            sb.append("\nDescription: ");
            sb.append(unsignedBytesToInt2);
        }
        return sb.toString();
    }
}
