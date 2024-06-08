package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import java.util.Locale;
import no.nordicsemi.android.mcp.ble.parser.utils.ParserUtils;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class CrossTrainerParser implements ICharacteristicParser {
    private static final int MOVEMENT_DIRECTION = 32768;

    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic);
    }

    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        int intOrThrow = ParserUtils.intOrThrow(Integer.valueOf(ParserUtils.getIntValue(bluetoothGattCharacteristic.getValue(), 19, 0)));
        boolean z = (32768 & intOrThrow) == 0;
        StringBuilder sb = new StringBuilder();
        sb.append("Movement Direction: ");
        sb.append(z ? "Forward\n" : "Backward\n");
        int addWhenFlagNotSet = 3 + BaseValueParser.addWhenFlagNotSet(sb, bluetoothGattCharacteristic, intOrThrow, 0, 3, 18, 0.01f, "Instantaneous Speed", "km/h");
        int addWhenFlagSet = addWhenFlagNotSet + BaseValueParser.addWhenFlagSet(sb, bluetoothGattCharacteristic, intOrThrow, 1, addWhenFlagNotSet, 18, 0.01f, "Average Speed", "km/h");
        int addWhenFlagSet2 = addWhenFlagSet + BaseValueParser.addWhenFlagSet(sb, bluetoothGattCharacteristic, intOrThrow, 2, addWhenFlagSet, 19, 1.0f, "Total Distance", "m");
        int addWhenFlagSet3 = addWhenFlagSet2 + BaseValueParser.addWhenFlagSet(sb, bluetoothGattCharacteristic, intOrThrow, 3, addWhenFlagSet2, 18, 1.0f, "Step Per Minute", null);
        int addWhenFlagSet4 = addWhenFlagSet3 + BaseValueParser.addWhenFlagSet(sb, bluetoothGattCharacteristic, intOrThrow, 3, addWhenFlagSet3, 18, 1.0f, "Average Step Rate", "steps/min");
        int addWhenFlagSet5 = addWhenFlagSet4 + BaseValueParser.addWhenFlagSet(sb, bluetoothGattCharacteristic, intOrThrow, 4, addWhenFlagSet4, 18, 0.1f, "Stride Count", null);
        int addWhenFlagSet6 = addWhenFlagSet5 + BaseValueParser.addWhenFlagSet(sb, bluetoothGattCharacteristic, intOrThrow, 5, addWhenFlagSet5, 18, 1.0f, "Positive Elevation Gain", "m");
        int addWhenFlagSet7 = addWhenFlagSet6 + BaseValueParser.addWhenFlagSet(sb, bluetoothGattCharacteristic, intOrThrow, 5, addWhenFlagSet6, 18, 1.0f, "Negative Elevation Gain", "m");
        int addWhenFlagSet8 = addWhenFlagSet7 + BaseValueParser.addWhenFlagSet(sb, bluetoothGattCharacteristic, intOrThrow, 6, addWhenFlagSet7, 34, 0.1f, "Inclination", "%");
        int addWhenFlagSet9 = addWhenFlagSet8 + BaseValueParser.addWhenFlagSet(sb, bluetoothGattCharacteristic, intOrThrow, 6, addWhenFlagSet8, 34, 0.1f, "Ramp Angle Setting", "°");
        int addWhenFlagSet10 = addWhenFlagSet9 + BaseValueParser.addWhenFlagSet(sb, bluetoothGattCharacteristic, intOrThrow, 7, addWhenFlagSet9, 34, 1.0f, "Resistance Level", null);
        int addWhenFlagSet11 = addWhenFlagSet10 + BaseValueParser.addWhenFlagSet(sb, bluetoothGattCharacteristic, intOrThrow, 8, addWhenFlagSet10, 34, 1.0f, "Instantaneous Power", "W");
        int addWhenFlagSet12 = addWhenFlagSet11 + BaseValueParser.addWhenFlagSet(sb, bluetoothGattCharacteristic, intOrThrow, 9, addWhenFlagSet11, 34, 1.0f, "Average Power", "W");
        int addWhenFlagSet13 = addWhenFlagSet12 + BaseValueParser.addWhenFlagSet(sb, bluetoothGattCharacteristic, intOrThrow, 10, addWhenFlagSet12, 18, 1.0f, "Total Energy", "kcal");
        int addWhenFlagSet14 = addWhenFlagSet13 + BaseValueParser.addWhenFlagSet(sb, bluetoothGattCharacteristic, intOrThrow, 10, addWhenFlagSet13, 18, 1.0f, "Energy Per Hour", "kcal");
        int addWhenFlagSet15 = addWhenFlagSet14 + BaseValueParser.addWhenFlagSet(sb, bluetoothGattCharacteristic, intOrThrow, 10, addWhenFlagSet14, 17, 1.0f, "Energy Per Minute", "kcal");
        int addWhenFlagSet16 = addWhenFlagSet15 + BaseValueParser.addWhenFlagSet(sb, bluetoothGattCharacteristic, intOrThrow, 11, addWhenFlagSet15, 17, 1.0f, "Heart Rate", "bpm");
        int addWhenFlagSet17 = addWhenFlagSet16 + BaseValueParser.addWhenFlagSet(sb, bluetoothGattCharacteristic, intOrThrow, 12, addWhenFlagSet16, 17, 0.1f, "Metabolic Equivalent", null);
        BaseValueParser.addWhenFlagSet(sb, bluetoothGattCharacteristic, intOrThrow, 14, addWhenFlagSet17 + BaseValueParser.addWhenFlagSet(sb, bluetoothGattCharacteristic, intOrThrow, 13, addWhenFlagSet17, 18, 1.0f, "Elapsed Time", "s"), 18, 1.0f, "Remaining Time", "s");
        int i = (-65536) & intOrThrow;
        if (i != 0) {
            sb.append(String.format(Locale.US, "Reserved Flags for Future Use: 0x%06X\n", Integer.valueOf(i)));
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }
}
