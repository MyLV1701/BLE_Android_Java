package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class SupportedPowerRangeParser implements ICharacteristicParser {
    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic);
    }

    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        StringBuilder sb = new StringBuilder();
        int add = BaseValueParser.add(sb, bluetoothGattCharacteristic, 0, 34, 1.0f, "Minimum Power", "W") + 0;
        BaseValueParser.add(sb, bluetoothGattCharacteristic, add + BaseValueParser.add(sb, bluetoothGattCharacteristic, add, 34, 1.0f, "Maximum Power", "W"), 18, 1.0f, "Minimum Increment", "W");
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }
}
