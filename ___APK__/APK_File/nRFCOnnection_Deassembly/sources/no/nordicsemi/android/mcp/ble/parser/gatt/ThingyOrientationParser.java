package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class ThingyOrientationParser implements ICharacteristicParser {
    private static String orientation2String(int i) {
        if (i == 0) {
            return "Portrait";
        }
        if (i == 1) {
            return "Landscape";
        }
        if (i == 2) {
            return "Reverse portrait";
        }
        if (i == 3) {
            return "Reverse landscape";
        }
        return "Unknown: " + i;
    }

    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic);
    }

    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (bluetoothGattCharacteristic.getValue().length != 1) {
            return "Invalid value: " + GeneralCharacteristicParser.parse(bluetoothGattCharacteristic);
        }
        return orientation2String(bluetoothGattCharacteristic.getIntValue(17, 0).intValue());
    }
}
