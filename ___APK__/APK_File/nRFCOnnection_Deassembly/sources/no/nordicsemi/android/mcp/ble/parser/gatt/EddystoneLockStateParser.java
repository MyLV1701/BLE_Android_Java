package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import no.nordicsemi.android.mcp.ble.parser.utils.ParserUtils;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class EddystoneLockStateParser implements ICharacteristicParser {
    private static final int LOCKED = 0;
    private static final int UNLOCKED = 1;
    private static final int UNLOCKED_AUTOMATIC_RELOCK_DISABLED = 2;

    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic);
    }

    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        byte[] value = bluetoothGattCharacteristic.getValue();
        if (value.length != 1 && value.length != 17) {
            return "Incorrect data length (1 or 17 bytes expected): " + GeneralCharacteristicParser.parse(bluetoothGattCharacteristic);
        }
        int intValue = bluetoothGattCharacteristic.getIntValue(33, 0).intValue();
        if (intValue == 0) {
            if (value.length == 1) {
                return "Locked";
            }
            return "Locked, lock code changed\nNew encrypted lock code: " + ParserUtils.bytesToHex(value, 1, 16, true);
        }
        if (intValue == 1) {
            if (value.length <= 1) {
                return "Unlocked";
            }
            return "Incorrect data length (1 byte expected): " + GeneralCharacteristicParser.parse(bluetoothGattCharacteristic);
        }
        if (intValue != 2) {
            return "Unsupported value: " + ParserUtils.bytesToHex(value, 0, value.length, true);
        }
        if (value.length <= 1) {
            return "Unlocked and automatic re-lock disabled";
        }
        return "Incorrect data length (1 byte expected): " + GeneralCharacteristicParser.parse(bluetoothGattCharacteristic);
    }
}
