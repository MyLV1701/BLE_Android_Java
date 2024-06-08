package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import java.util.Calendar;
import java.util.Locale;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class DateParser implements ICharacteristicParser {
    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic);
    }

    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (bluetoothGattCharacteristic.getValue().length != 4) {
            return "Incorrect data length (4 bytes expected): " + GeneralCharacteristicParser.parse(bluetoothGattCharacteristic);
        }
        int intValue = bluetoothGattCharacteristic.getIntValue(18, 0).intValue();
        int intValue2 = bluetoothGattCharacteristic.getIntValue(17, 2).intValue();
        int intValue3 = bluetoothGattCharacteristic.getIntValue(17, 3).intValue();
        Calendar calendar = Calendar.getInstance();
        calendar.set(intValue, intValue2 - 1, intValue3);
        return String.format(Locale.US, "%1$te %1$tb %1$tY", calendar);
    }
}
