package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class SupportedInclinationRangeParser implements ICharacteristicParser {
    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic);
    }

    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        StringBuilder sb = new StringBuilder();
        int add = BaseValueParser.add(sb, bluetoothGattCharacteristic, 0, 34, 0.1f, "Minimum Inclination", null) + 0;
        BaseValueParser.add(sb, bluetoothGattCharacteristic, add + BaseValueParser.add(sb, bluetoothGattCharacteristic, add, 34, 0.1f, "Maximum Inclination", null), 18, 0.1f, "Minimum Increment", null);
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }
}
