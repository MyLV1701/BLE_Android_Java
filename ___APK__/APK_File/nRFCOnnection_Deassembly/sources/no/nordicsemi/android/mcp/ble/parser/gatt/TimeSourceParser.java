package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class TimeSourceParser implements ICharacteristicParser {
    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic);
    }

    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (bluetoothGattCharacteristic.getValue().length != 1) {
            return "Incorrect data length (1 byte expected): " + GeneralCharacteristicParser.parse(bluetoothGattCharacteristic);
        }
        switch (bluetoothGattCharacteristic.getIntValue(17, 0).intValue()) {
            case 1:
                return "Network Time Protocol";
            case 2:
                return "GPS";
            case 3:
                return "Radio Time Signal";
            case 4:
                return "Manual";
            case 5:
                return "Atomic Clock";
            case 6:
                return "Cellular Network";
            default:
                return "Unknown";
        }
    }
}
