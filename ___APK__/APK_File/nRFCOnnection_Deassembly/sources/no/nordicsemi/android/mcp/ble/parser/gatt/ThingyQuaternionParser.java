package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class ThingyQuaternionParser implements ICharacteristicParser {
    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic);
    }

    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (bluetoothGattCharacteristic.getValue().length != 16) {
            return "Invalid value: " + GeneralCharacteristicParser.parse(bluetoothGattCharacteristic);
        }
        return "W: " + (bluetoothGattCharacteristic.getIntValue(36, 0).intValue() / 1.0737418E9f) + "°\nX: " + (bluetoothGattCharacteristic.getIntValue(36, 4).intValue() / 1.0737418E9f) + "°\nY: " + (bluetoothGattCharacteristic.getIntValue(36, 8).intValue() / 1.0737418E9f) + "°\nZ: " + (bluetoothGattCharacteristic.getIntValue(36, 12).intValue() / 1.0737418E9f) + "°";
    }
}
