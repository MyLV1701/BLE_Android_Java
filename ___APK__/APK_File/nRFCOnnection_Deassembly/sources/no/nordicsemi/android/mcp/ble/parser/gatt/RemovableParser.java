package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class RemovableParser implements ICharacteristicParser {
    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic);
    }

    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        int intValue = bluetoothGattCharacteristic.getIntValue(17, 0).intValue();
        if (intValue == 0) {
            return "Unknown";
        }
        if (intValue == 1) {
            return "Not Removable";
        }
        if (intValue == 2) {
            return "Removable";
        }
        return "Reserved value (" + intValue + ")";
    }
}
