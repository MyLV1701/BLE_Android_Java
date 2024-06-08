package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import no.nordicsemi.android.ble.error.GattError;
import no.nordicsemi.android.mcp.ble.parser.utils.ParserUtils;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class UriBeaconFlagsParser implements ICharacteristicParser {
    private static String decodeFlags(int i) {
        if (i == 0) {
            return "No flags";
        }
        StringBuilder sb = new StringBuilder();
        if ((i & 1) > 0) {
            sb.append("Invisible Hint, ");
        }
        if ((i & GattError.GATT_PROCEDURE_IN_PROGRESS) > 0) {
            sb.append("Reserved, ");
        }
        sb.setLength(sb.length() - 2);
        return sb.toString();
    }

    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic);
    }

    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic, 0);
    }

    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
        return decodeFlags(ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(17, i)));
    }
}
