package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class TemperatureTypeParser implements ICharacteristicParser {
    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic);
    }

    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic, 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
        switch (bluetoothGattCharacteristic.getValue()[i]) {
            case 1:
                return "Armpit";
            case 2:
                return "Body (general)";
            case 3:
                return "Ear (usually ear lobe)";
            case 4:
                return "Finger";
            case 5:
                return "Gastro-intestinal Tract";
            case 6:
                return "Mouth";
            case 7:
                return "Rectum";
            case 8:
                return "Toe";
            case 9:
                return "Tympanum (ear drum)";
            default:
                return "Unknown";
        }
    }
}
