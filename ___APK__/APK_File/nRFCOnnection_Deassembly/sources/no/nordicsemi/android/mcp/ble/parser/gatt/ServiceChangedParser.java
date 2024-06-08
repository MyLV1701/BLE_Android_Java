package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import java.util.Locale;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class ServiceChangedParser implements ICharacteristicParser {
    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic);
    }

    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (bluetoothGattCharacteristic.getValue().length != 4) {
            return "Incorrect data length (4 bytes expected): " + GeneralCharacteristicParser.parse(bluetoothGattCharacteristic);
        }
        return String.format(Locale.US, "Affected Attribute Handle Range: 0x%04X - 0x%04X", Integer.valueOf(bluetoothGattCharacteristic.getIntValue(18, 0).intValue()), Integer.valueOf(bluetoothGattCharacteristic.getIntValue(18, 2).intValue()));
    }
}
