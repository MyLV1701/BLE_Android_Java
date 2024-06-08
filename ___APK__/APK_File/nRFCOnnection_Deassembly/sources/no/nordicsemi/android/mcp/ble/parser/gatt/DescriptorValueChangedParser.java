package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import java.util.Locale;
import java.util.UUID;
import no.nordicsemi.android.mcp.ble.parser.gap.FlagsParser;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;
import no.nordicsemi.android.mcp.util.FileHelper;

/* loaded from: classes.dex */
public class DescriptorValueChangedParser implements ICharacteristicParser {
    private static final int FLAG_MASK_CHANGE_TO_CHARACTERISTIC_USER_DESCRIPTION_DESCRIPTOR = 16;
    private static final int FLAG_MASK_CHANGE_TO_ES_CONFIGURATION_DESCRIPTOR = 4;
    private static final int FLAG_MASK_CHANGE_TO_ES_MEASUREMENT_DESCRIPTOR = 8;
    private static final int FLAG_MASK_CHANGE_TO_ONE_OR_MORE_ES_TRIGGER_SETTING_DESCRIPTORS = 2;
    private static final int FLAG_MASK_RESERVED = 65504;
    private static final int FLAG_MASK_SOURCE = 1;

    private static int decodeUuid32(byte[] bArr, int i) {
        int i2 = bArr[i] & FlagsParser.UNKNOWN_FLAGS;
        int i3 = bArr[i + 1] & FlagsParser.UNKNOWN_FLAGS;
        return ((bArr[i + 3] & FlagsParser.UNKNOWN_FLAGS) << 24) | ((bArr[i + 2] & FlagsParser.UNKNOWN_FLAGS) << 16) | (i3 << 8) | i2;
    }

    private static boolean flagSet(int i, int i2) {
        return (i & i2) > 0;
    }

    public static String parseValue(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        byte[] value = bluetoothGattCharacteristic.getValue();
        if (value.length != 4 && value.length != 18) {
            return "Invalid value length (expected 4 or 18 bytes): " + GeneralCharacteristicParser.parse(bluetoothGattCharacteristic);
        }
        int intValue = bluetoothGattCharacteristic.getIntValue(18, 0).intValue();
        StringBuilder sb = new StringBuilder();
        sb.append("Source of Change: ");
        sb.append(flagSet(intValue, 1) ? "Client" : FileHelper.SERVER_FOLDER);
        if (flagSet(intValue, 2)) {
            sb.append("\n- One or more ES Trigger Setting descriptors changed");
        }
        if (flagSet(intValue, 4)) {
            sb.append("\n- ES Configuration descriptor changed");
        }
        if (flagSet(intValue, 8)) {
            sb.append("\n- ES Measurement descriptor changed");
        }
        if (flagSet(intValue, 16)) {
            sb.append("\n- Characteristic User Description descriptor changed");
        }
        if (flagSet(intValue, FLAG_MASK_RESERVED)) {
            sb.append("\n- Reserved bits set! (flags: ");
            sb.append(String.format(Locale.US, "0x%04X)", Integer.valueOf(intValue)));
        }
        sb.append("\nCharacteristic changed: ");
        if (value.length == 4) {
            int intValue2 = bluetoothGattCharacteristic.getIntValue(18, 2).intValue();
            String characteristicName = databaseHelper.getCharacteristicName(intValue2);
            if (characteristicName != null) {
                sb.append(characteristicName);
            } else {
                sb.append("0x");
                sb.append(intValue2);
            }
        } else {
            UUID uuid = new UUID((decodeUuid32(value, 14) << 32) + (decodeUuid32(value, 10) & 4294967295L), (decodeUuid32(value, 6) << 32) + (decodeUuid32(value, 2) & 4294967295L));
            String characteristicName2 = databaseHelper.getCharacteristicName(uuid);
            if (characteristicName2 != null) {
                sb.append(characteristicName2);
            } else {
                sb.append(uuid);
            }
        }
        return sb.toString();
    }

    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parseValue(databaseHelper, bluetoothGattCharacteristic);
    }
}
