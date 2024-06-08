package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class SensorLocationParser implements ICharacteristicParser {
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
                return "Top of shoe";
            case 2:
                return "In shoe";
            case 3:
                return "Hip";
            case 4:
                return "Front Wheel";
            case 5:
                return "Left Crank";
            case 6:
                return "Right Crank";
            case 7:
                return "Left Pedal";
            case 8:
                return "Right Pedal";
            case 9:
                return "Front Hub";
            case 10:
                return "Rear Dropout";
            case 11:
                return "Chainstay";
            case 12:
                return "Rear Wheel";
            case 13:
                return "Rear Hub";
            case 14:
                return "Chest";
            default:
                return "Other";
        }
    }
}
