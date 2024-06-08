package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class ThingyPressureParser implements ICharacteristicParser {
    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic);
    }

    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (bluetoothGattCharacteristic.getValue().length != 5) {
            return "Invalid value: " + GeneralCharacteristicParser.parse(bluetoothGattCharacteristic);
        }
        return bluetoothGattCharacteristic.getIntValue(36, 0).intValue() + "." + bluetoothGattCharacteristic.getIntValue(17, 4).intValue() + " hPa";
    }
}
