package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import no.nordicsemi.android.mcp.ble.parser.utils.ParserUtils;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class EddystoneReadWriteAdvSlotParser implements ICharacteristicParser {
    private static final int TYPE_EID = 48;
    private static final int TYPE_TLM = 32;
    private static final int TYPE_UID = 0;
    private static final int TYPE_URL = 16;

    private static long getUnsignedInt(int i) {
        return i & 4294967295L;
    }

    public static String parseValue(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        byte[] value = bluetoothGattCharacteristic.getValue();
        StringBuilder sb = new StringBuilder();
        if (value.length > 0) {
            int intValue = bluetoothGattCharacteristic.getIntValue(17, 0).intValue();
            if (intValue == 0) {
                if (value.length == 1) {
                    return "Empty slot";
                }
                if (value.length < 17) {
                    return "Frame type: UID <0x00>\nIncorrect data: " + GeneralCharacteristicParser.parse(bluetoothGattCharacteristic);
                }
                return no.nordicsemi.android.mcp.ble.parser.gap.BeaconParser.parseEddystoneUIDBeacon(value, 0, value.length);
            }
            if (intValue == 16) {
                if (value.length >= 3 && value.length <= 20) {
                    return no.nordicsemi.android.mcp.ble.parser.gap.BeaconParser.parseEddystoneURLBeacon(value, 0, value.length);
                }
                return "Frame type: URL <0x10>\nIncorrect data: " + GeneralCharacteristicParser.parse(bluetoothGattCharacteristic);
            }
            if (intValue == 32) {
                if (value.length == 1) {
                    return "Frame type: TLM <0x20>";
                }
                if ((value[1] == 0 && value.length < 14) || (value[1] == 1 && value.length < 18)) {
                    return "Frame type: TLM <0x20>\nIncorrect data: " + GeneralCharacteristicParser.parse(bluetoothGattCharacteristic);
                }
                return no.nordicsemi.android.mcp.ble.parser.gap.BeaconParser.parseEddystoneTLMBeacon(databaseHelper, value, 0, value.length);
            }
            if (intValue != 48) {
                return sb.toString();
            }
            sb.append("Frame type: EID <0x30>");
            if (value.length == 14) {
                sb.append("\nExponent: ");
                sb.append(bluetoothGattCharacteristic.getIntValue(17, 1));
                sb.append("\nClock value: ");
                sb.append(getUnsignedInt(ParserUtils.getIntValue(value, 100, 2)));
                sb.append("\nEID data: ");
                sb.append(ParserUtils.bytesToHex(value, 6, 8, true));
                String nameForEddystoneKey = databaseHelper.getNameForEddystoneKey(value, 6);
                if (nameForEddystoneKey != null) {
                    sb.append("\nResolved name: ");
                    sb.append(nameForEddystoneKey);
                }
                return sb.toString();
            }
            if (value.length == 18) {
                sb.append("\nEncrypted EIK: ");
                sb.append(ParserUtils.bytesToHex(value, 1, 16, true));
                sb.append("\nExponent: ");
                sb.append(bluetoothGattCharacteristic.getIntValue(17, 17));
                return sb.toString();
            }
            if (value.length == 34) {
                sb.append("\nPublic ECDH Key: ");
                sb.append(ParserUtils.bytesToHex(value, 1, 32, true));
                sb.append("\nExponent: ");
                sb.append(bluetoothGattCharacteristic.getIntValue(17, 33));
                return sb.toString();
            }
            return "\nFrame type: EID <0x30>\nIncorrect data: " + GeneralCharacteristicParser.parse(bluetoothGattCharacteristic);
        }
        return "Incorrect data length: " + GeneralCharacteristicParser.parse(bluetoothGattCharacteristic);
    }

    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parseValue(databaseHelper, bluetoothGattCharacteristic);
    }
}
