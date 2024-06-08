package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class ThingyExtPinsParser implements ICharacteristicParser {
    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic);
    }

    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (bluetoothGattCharacteristic.getValue().length != 4) {
            return "Invalid value: " + GeneralCharacteristicParser.parse(bluetoothGattCharacteristic);
        }
        return "MOS_1: " + bluetoothGattCharacteristic.getIntValue(17, 0).intValue() + "\nMOS_2: " + bluetoothGattCharacteristic.getIntValue(17, 1).intValue() + "\nMOS_3: " + bluetoothGattCharacteristic.getIntValue(17, 2).intValue() + "\nMOS_4: " + bluetoothGattCharacteristic.getIntValue(17, 3).intValue();
    }
}
