package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class ThingyTapCounterParser implements ICharacteristicParser {
    private static String direction2String(int i) {
        switch (i) {
            case 1:
                return "X-UP";
            case 2:
                return "X-DOWN";
            case 3:
                return "Y-UP";
            case 4:
                return "Y-DOWN";
            case 5:
                return "Z-UP";
            case 6:
                return "Z-DOWN";
            default:
                return "Unknown: " + i;
        }
    }

    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic);
    }

    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (bluetoothGattCharacteristic.getValue().length != 2) {
            return "Invalid value: " + GeneralCharacteristicParser.parse(bluetoothGattCharacteristic);
        }
        int intValue = bluetoothGattCharacteristic.getIntValue(17, 0).intValue();
        return "Direction: " + direction2String(intValue) + "\nCount: " + bluetoothGattCharacteristic.getIntValue(17, 1).intValue();
    }
}
