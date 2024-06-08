package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import java.util.Calendar;
import java.util.Locale;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class DateTimeParser implements ICharacteristicParser {
    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic);
    }

    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic, 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
        if (bluetoothGattCharacteristic.getValue().length + i >= 7) {
            int intValue = bluetoothGattCharacteristic.getIntValue(18, i).intValue();
            int intValue2 = bluetoothGattCharacteristic.getIntValue(17, i + 2).intValue();
            int intValue3 = bluetoothGattCharacteristic.getIntValue(17, i + 3).intValue();
            int intValue4 = bluetoothGattCharacteristic.getIntValue(17, i + 4).intValue();
            int intValue5 = bluetoothGattCharacteristic.getIntValue(17, i + 5).intValue();
            int intValue6 = bluetoothGattCharacteristic.getIntValue(17, i + 6).intValue();
            Calendar calendar = Calendar.getInstance();
            calendar.set(intValue, intValue2 - 1, intValue3, intValue4, intValue5, intValue6);
            return (intValue <= 0 || intValue2 <= 0 || intValue3 <= 0) ? (intValue == 0 && intValue2 == 0 && intValue3 == 0) ? String.format(Locale.US, "%1$tH:%1$tM:%1$tS", calendar) : (intValue == 0 && intValue2 == 0) ? String.format(Locale.US, "Day %1$te, %1$tH:%1$tM:%1$tS", calendar) : (intValue2 == 0 && intValue3 == 0) ? String.format(Locale.US, "Year %1$tY, %1$tH:%1$tM:%1$tS", calendar) : (intValue == 0 && intValue3 == 0) ? String.format(Locale.US, "%1$tb, %1$tH:%1$tM:%1$tS", calendar) : intValue == 0 ? String.format(Locale.US, "%1$te %1$tb, %1$tH:%1$tM:%1$tS", calendar) : intValue3 == 0 ? String.format(Locale.US, "%1$tb %1$tY, %1$tH:%1$tM:%1$tS", calendar) : String.format(Locale.US, "%1$te Unknown Month %1$tY, %1$tH:%1$tM:%1$tS", calendar) : String.format(Locale.US, "%1$te %1$tb %1$tY, %1$tH:%1$tM:%1$tS", calendar);
        }
        throw new NullPointerException();
    }
}
