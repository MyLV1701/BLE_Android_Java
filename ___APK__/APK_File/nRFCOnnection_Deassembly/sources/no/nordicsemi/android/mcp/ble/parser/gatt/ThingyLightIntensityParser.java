package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import java.util.Locale;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class ThingyLightIntensityParser implements ICharacteristicParser {
    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic);
    }

    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (bluetoothGattCharacteristic.getValue().length != 8) {
            return "Invalid value: " + GeneralCharacteristicParser.parse(bluetoothGattCharacteristic);
        }
        return String.format(Locale.US, "Raw light data:\nRed: %d\nGreen: %d\nBlue: %d\nClear: %d", Integer.valueOf(bluetoothGattCharacteristic.getIntValue(18, 0).intValue()), Integer.valueOf(bluetoothGattCharacteristic.getIntValue(18, 2).intValue()), Integer.valueOf(bluetoothGattCharacteristic.getIntValue(18, 4).intValue()), Integer.valueOf(bluetoothGattCharacteristic.getIntValue(18, 6).intValue()));
    }
}
