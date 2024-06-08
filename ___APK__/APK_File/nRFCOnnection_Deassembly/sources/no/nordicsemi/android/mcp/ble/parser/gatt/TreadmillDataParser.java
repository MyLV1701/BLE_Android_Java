package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import java.util.Locale;
import no.nordicsemi.android.mcp.ble.parser.utils.ParserUtils;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class TreadmillDataParser implements ICharacteristicParser {
    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic);
    }

    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        int intOrThrow = ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(18, 0));
        StringBuilder sb = new StringBuilder();
        int addWhenFlagNotSet = 2 + BaseValueParser.addWhenFlagNotSet(sb, bluetoothGattCharacteristic, intOrThrow, 0, 2, 18, 0.01f, "Instantaneous Speed", "km/h");
        int addWhenFlagSet = addWhenFlagNotSet + BaseValueParser.addWhenFlagSet(sb, bluetoothGattCharacteristic, intOrThrow, 1, addWhenFlagNotSet, 18, 0.01f, "Average Speed", "km/h");
        int addWhenFlagSet2 = addWhenFlagSet + BaseValueParser.addWhenFlagSet(sb, bluetoothGattCharacteristic, intOrThrow, 2, addWhenFlagSet, 19, 1.0f, "Total Distance", "m");
        int addWhenFlagSet3 = addWhenFlagSet2 + BaseValueParser.addWhenFlagSet(sb, bluetoothGattCharacteristic, intOrThrow, 3, addWhenFlagSet2, 34, 0.1f, "Inclination", "%");
        int addWhenFlagSet4 = addWhenFlagSet3 + BaseValueParser.addWhenFlagSet(sb, bluetoothGattCharacteristic, intOrThrow, 3, addWhenFlagSet3, 34, 0.1f, "Ramp Angle Setting", "°");
        int addWhenFlagSet5 = addWhenFlagSet4 + BaseValueParser.addWhenFlagSet(sb, bluetoothGattCharacteristic, intOrThrow, 4, addWhenFlagSet4, 18, 0.1f, "Positive Elevation Gain", "m");
        int addWhenFlagSet6 = addWhenFlagSet5 + BaseValueParser.addWhenFlagSet(sb, bluetoothGattCharacteristic, intOrThrow, 4, addWhenFlagSet5, 18, 0.1f, "Negative Elevation Gain", "m");
        int addWhenFlagSet7 = addWhenFlagSet6 + BaseValueParser.addWhenFlagSet(sb, bluetoothGattCharacteristic, intOrThrow, 5, addWhenFlagSet6, 17, 0.1f, "Instantaneous Pace", "km/min");
        int addWhenFlagSet8 = addWhenFlagSet7 + BaseValueParser.addWhenFlagSet(sb, bluetoothGattCharacteristic, intOrThrow, 6, addWhenFlagSet7, 17, 0.1f, "Average Pace", "km/min");
        int addWhenFlagSet9 = addWhenFlagSet8 + BaseValueParser.addWhenFlagSet(sb, bluetoothGattCharacteristic, intOrThrow, 7, addWhenFlagSet8, 18, 1.0f, "Total Energy", "kcal");
        int addWhenFlagSet10 = addWhenFlagSet9 + BaseValueParser.addWhenFlagSet(sb, bluetoothGattCharacteristic, intOrThrow, 7, addWhenFlagSet9, 18, 1.0f, "Energy Per Hour", "kcal");
        int addWhenFlagSet11 = addWhenFlagSet10 + BaseValueParser.addWhenFlagSet(sb, bluetoothGattCharacteristic, intOrThrow, 7, addWhenFlagSet10, 17, 1.0f, "Energy Per Minute", "kcal");
        int addWhenFlagSet12 = addWhenFlagSet11 + BaseValueParser.addWhenFlagSet(sb, bluetoothGattCharacteristic, intOrThrow, 8, addWhenFlagSet11, 17, 1.0f, "Heart Rate", "bpm");
        int addWhenFlagSet13 = addWhenFlagSet12 + BaseValueParser.addWhenFlagSet(sb, bluetoothGattCharacteristic, intOrThrow, 9, addWhenFlagSet12, 17, 0.1f, "Metabolic Equivalent", null);
        int addWhenFlagSet14 = addWhenFlagSet13 + BaseValueParser.addWhenFlagSet(sb, bluetoothGattCharacteristic, intOrThrow, 10, addWhenFlagSet13, 18, 1.0f, "Elapsed Time", "s");
        int addWhenFlagSet15 = addWhenFlagSet14 + BaseValueParser.addWhenFlagSet(sb, bluetoothGattCharacteristic, intOrThrow, 11, addWhenFlagSet14, 18, 1.0f, "Remaining Time", "s");
        BaseValueParser.addWhenFlagSet(sb, bluetoothGattCharacteristic, intOrThrow, 12, addWhenFlagSet15 + BaseValueParser.addWhenFlagSet(sb, bluetoothGattCharacteristic, intOrThrow, 12, addWhenFlagSet15, 34, 1.0f, "Force on Belt", "N"), 34, 1.0f, "Power Output", "W");
        int i = intOrThrow & (-8192);
        if (i != 0) {
            sb.append(String.format(Locale.US, "Reserved Flags for Future Use: 0x%04X\n", Integer.valueOf(i)));
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }
}
