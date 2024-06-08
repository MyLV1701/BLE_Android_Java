package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class BodySensorLocationParser implements ICharacteristicParser {
    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic);
    }

    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (bluetoothGattCharacteristic.getValue().length != 1) {
            return "Invalid value: " + GeneralCharacteristicParser.parse(bluetoothGattCharacteristic);
        }
        switch (bluetoothGattCharacteristic.getIntValue(17, 0).intValue()) {
            case 1:
                return "Chest";
            case 2:
                return "Wrist";
            case 3:
                return "Finger";
            case 4:
                return "Hand";
            case 5:
                return "Ear Lobe";
            case 6:
                return "Foot";
            default:
                return "Other";
        }
    }
}
