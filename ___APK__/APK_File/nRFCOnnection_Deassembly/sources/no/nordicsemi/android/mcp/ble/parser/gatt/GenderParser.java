package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class GenderParser implements ICharacteristicParser {
    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic);
    }

    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        int intValue = bluetoothGattCharacteristic.getIntValue(17, 0).intValue();
        if (intValue == 0) {
            return "Male";
        }
        if (intValue == 1) {
            return "Female";
        }
        if (intValue == 2) {
            return "Unspecified";
        }
        return "Reserved value (" + intValue + ")";
    }
}
