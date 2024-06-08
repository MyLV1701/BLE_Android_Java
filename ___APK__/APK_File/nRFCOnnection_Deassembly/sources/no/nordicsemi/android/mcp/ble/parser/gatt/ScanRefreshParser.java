package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class ScanRefreshParser implements ICharacteristicParser {
    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic);
    }

    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (bluetoothGattCharacteristic.getValue().length == 1) {
            return bluetoothGattCharacteristic.getIntValue(17, 0).intValue() == 0 ? "Server requires refresh" : "Reserved for future use";
        }
        return "Incorrect data length (1 byte expected): " + GeneralCharacteristicParser.parse(bluetoothGattCharacteristic);
    }
}
