package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import java.util.Locale;
import no.nordicsemi.android.mcp.ble.parser.utils.ParserUtils;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class CSCMeasurementParser implements ICharacteristicParser {
    private static final byte CRANK_REV_DATA_PRESENT = 2;
    private static final byte WHEEL_REV_DATA_PRESENT = 1;

    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic);
    }

    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int intOrThrow = ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(17, 0));
        boolean z = (intOrThrow & 1) > 0;
        boolean z2 = (intOrThrow & 2) > 0;
        if (z) {
            i = ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(20, 1));
            i2 = ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(18, 5));
            i3 = 7;
        } else {
            i = 0;
            i2 = 0;
            i3 = 1;
        }
        if (z2) {
            i5 = ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(18, i3));
            i4 = ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(18, i3 + 2));
        } else {
            i4 = 0;
            i5 = 0;
        }
        StringBuilder sb = new StringBuilder();
        if (z) {
            sb.append(String.format(Locale.US, "Wheel rev: %d,\n", Integer.valueOf(i)));
            sb.append(String.format(Locale.US, "Last wheel event time: %d ms,\n", Integer.valueOf(i2)));
        }
        if (z2) {
            sb.append(String.format(Locale.US, "Crank rev: %d,\n", Integer.valueOf(i5)));
            sb.append(String.format(Locale.US, "Last crank event time: %d ms,\n", Integer.valueOf(i4)));
        }
        sb.setLength(sb.length() - 2);
        return sb.toString();
    }
}
