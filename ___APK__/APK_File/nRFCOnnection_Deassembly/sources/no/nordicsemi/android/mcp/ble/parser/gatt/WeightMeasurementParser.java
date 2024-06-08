package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import java.util.Locale;
import no.nordicsemi.android.mcp.ble.parser.utils.ParserUtils;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class WeightMeasurementParser implements ICharacteristicParser {
    private static final int BMI_AND_HEIGHT_PRESENT = 8;
    private static final int TIMESTAMP_PRESENT = 2;
    private static final int UNIT = 1;
    private static final int UNIT_IMP = 1;
    private static final int UNIT_SI = 0;
    private static final int USER_ID_PRESENT = 4;

    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic);
    }

    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        int intOrThrow = ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(17, 0));
        int i = intOrThrow & 1;
        boolean z = (intOrThrow & 2) > 0;
        boolean z2 = (intOrThrow & 4) > 0;
        boolean z3 = (intOrThrow & 8) > 0;
        StringBuilder sb = new StringBuilder();
        int intOrThrow2 = ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(18, 1));
        int i2 = 3;
        sb.append("Weight: ");
        if (i == 0) {
            sb.append(String.format(Locale.US, "%.3f kg\n", Float.valueOf(intOrThrow2 / 200.0f)));
        } else {
            sb.append(String.format(Locale.US, "%.2f pounds\n", Float.valueOf(intOrThrow2 / 100.0f)));
        }
        if (z) {
            sb.append("Time: ");
            sb.append(DateTimeParser.parse(bluetoothGattCharacteristic, 3));
            sb.append("\n");
            i2 = 10;
        }
        if (z2) {
            int intOrThrow3 = ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(17, i2));
            i2++;
            sb.append("UserId: ");
            if (intOrThrow3 != 255) {
                sb.append(intOrThrow3);
                sb.append("\n");
            } else {
                sb.append("unknown user\n");
            }
        }
        if (z3) {
            sb.append(String.format(Locale.US, "BMI: %.1f\n", Float.valueOf(ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(18, i2)) / 10.0f)));
            int intOrThrow4 = ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(18, i2 + 2));
            sb.append("Height: ");
            if (i == 0) {
                sb.append(String.format(Locale.US, "%.1f cm\n", Float.valueOf(intOrThrow4 / 10.0f)));
            } else {
                sb.append(String.format(Locale.US, "%.1f inches\n", Float.valueOf(intOrThrow4 / 10.0f)));
            }
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }
}
