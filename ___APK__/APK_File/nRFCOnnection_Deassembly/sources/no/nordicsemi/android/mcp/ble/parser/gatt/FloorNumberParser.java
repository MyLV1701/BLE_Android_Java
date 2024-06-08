package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class FloorNumberParser implements ICharacteristicParser {
    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic);
    }

    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        int intValue = bluetoothGattCharacteristic.getIntValue(17, 0).intValue();
        if (intValue == 0) {
            return "Floor: -20 or below";
        }
        if (intValue == 252) {
            return "Floor: 232 or above";
        }
        if (intValue == 253) {
            return "Floor: 0 (ground floor)";
        }
        if (intValue == 254) {
            return "Floor: 1 (ground floor)";
        }
        if (intValue == 255) {
            return "No floor configured";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Floor: ");
        sb.append(intValue - 20);
        return sb.toString();
    }
}
