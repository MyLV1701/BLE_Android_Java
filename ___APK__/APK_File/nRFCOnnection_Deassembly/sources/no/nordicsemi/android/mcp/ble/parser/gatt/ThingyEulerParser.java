package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class ThingyEulerParser implements ICharacteristicParser {
    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic);
    }

    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (bluetoothGattCharacteristic.getValue().length != 12) {
            return "Invalid value: " + GeneralCharacteristicParser.parse(bluetoothGattCharacteristic);
        }
        return "Roll: " + (bluetoothGattCharacteristic.getIntValue(36, 0).intValue() / 65536.0f) + "°\nPitch: " + (bluetoothGattCharacteristic.getIntValue(36, 4).intValue() / 65536.0f) + "°\nYaw: " + (bluetoothGattCharacteristic.getIntValue(36, 8).intValue() / 65536.0f) + "°";
    }
}
