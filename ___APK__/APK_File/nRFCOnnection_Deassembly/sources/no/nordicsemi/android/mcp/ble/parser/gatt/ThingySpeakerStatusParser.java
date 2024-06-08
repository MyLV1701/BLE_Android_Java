package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class ThingySpeakerStatusParser implements ICharacteristicParser {
    private static String status2String(int i) {
        if (i == 1) {
            return "Finished";
        }
        if (i == 2) {
            return "Buffer warning";
        }
        if (i == 3) {
            return "Buffer ready";
        }
        if (i == 4) {
            return "Packet disregarded";
        }
        if (i == 5) {
            return "Invalid command";
        }
        return "Unknown: " + i;
    }

    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic);
    }

    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (bluetoothGattCharacteristic.getValue().length != 1) {
            return "Invalid value: " + GeneralCharacteristicParser.parse(bluetoothGattCharacteristic);
        }
        return status2String(bluetoothGattCharacteristic.getIntValue(17, 0).intValue());
    }
}
