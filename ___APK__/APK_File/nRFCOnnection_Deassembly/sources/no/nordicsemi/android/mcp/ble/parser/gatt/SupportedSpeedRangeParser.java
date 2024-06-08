package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class SupportedSpeedRangeParser implements ICharacteristicParser {
    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic);
    }

    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        StringBuilder sb = new StringBuilder();
        int add = BaseValueParser.add(sb, bluetoothGattCharacteristic, 0, 18, 0.01f, "Minimum Speed", "km/h") + 0;
        BaseValueParser.add(sb, bluetoothGattCharacteristic, add + BaseValueParser.add(sb, bluetoothGattCharacteristic, add, 18, 0.01f, "Maximum Speed", "km/h"), 18, 0.01f, "Minimum Increment", "km/h");
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }
}
