package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class HttpControlPointParser implements ICharacteristicParser {
    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic);
    }

    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (bluetoothGattCharacteristic.getValue().length != 1) {
            return "Incorrect data length (1 byte expected): " + GeneralCharacteristicParser.parse(bluetoothGattCharacteristic);
        }
        int intValue = bluetoothGattCharacteristic.getIntValue(17, 0).intValue();
        switch (intValue) {
            case 1:
                return "HTTP GET Request";
            case 2:
                return "HTTP HEAD Request";
            case 3:
                return "HTTP POST Request";
            case 4:
                return "HTTP PUT Request";
            case 5:
                return "HTTP DELETE Request";
            case 6:
                return "HTTPS GET Request";
            case 7:
                return "HTTPS HEAD Request";
            case 8:
                return "HTTPS POST Request";
            case 9:
                return "HTTPS PUT Request";
            case 10:
                return "HTTPS DELETE Request";
            case 11:
                return "HTTP Request Cancel";
            default:
                return "Reserved for future use (" + intValue + ")";
        }
    }
}
