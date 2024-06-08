package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import java.util.Locale;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class CGMSessionStartTimeParser implements ICharacteristicParser {
    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic);
    }

    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        StringBuilder sb = new StringBuilder();
        sb.append("Session Start Time: ");
        sb.append(DateTimeParser.parse(bluetoothGattCharacteristic, 0));
        sb.append("\n");
        sb.append(TimeZoneParser.parse(bluetoothGattCharacteristic, 7));
        sb.append("\n");
        sb.append(DSTOffsetParser.parse(bluetoothGattCharacteristic, 8));
        if (bluetoothGattCharacteristic.getValue().length >= 11) {
            sb.append(String.format(Locale.US, "\nE2E-CRC: %04X", Integer.valueOf(bluetoothGattCharacteristic.getIntValue(18, 9).intValue())));
        }
        return sb.toString();
    }
}
