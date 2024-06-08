package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class ObjectSizeParser implements ICharacteristicParser {
    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic);
    }

    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (bluetoothGattCharacteristic.getValue().length != 8) {
            return "Incorrect data length (8 bytes expected): " + GeneralCharacteristicParser.parse(bluetoothGattCharacteristic);
        }
        return "Current size: " + bluetoothGattCharacteristic.getIntValue(20, 0).intValue() + "\nAllocated size: " + bluetoothGattCharacteristic.getIntValue(20, 4).intValue();
    }
}
