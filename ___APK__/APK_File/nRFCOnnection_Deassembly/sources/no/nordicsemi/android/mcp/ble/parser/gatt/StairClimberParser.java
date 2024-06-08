package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import java.util.Locale;
import no.nordicsemi.android.mcp.ble.parser.utils.ParserUtils;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class StairClimberParser implements ICharacteristicParser {
    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic);
    }

    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        int intOrThrow = ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(18, 0));
        StringBuilder sb = new StringBuilder();
        int addWhenFlagNotSet = 2 + BaseValueParser.addWhenFlagNotSet(sb, bluetoothGattCharacteristic, intOrThrow, 0, 2, 18, 1.0f, "Floors", null);
        int addWhenFlagSet = addWhenFlagNotSet + BaseValueParser.addWhenFlagSet(sb, bluetoothGattCharacteristic, intOrThrow, 1, addWhenFlagNotSet, 18, 1.0f, "Step Per Minute", null);
        int addWhenFlagSet2 = addWhenFlagSet + BaseValueParser.addWhenFlagSet(sb, bluetoothGattCharacteristic, intOrThrow, 2, addWhenFlagSet, 18, 1.0f, "Average Step Rate", "steps/min");
        int addWhenFlagSet3 = addWhenFlagSet2 + BaseValueParser.addWhenFlagSet(sb, bluetoothGattCharacteristic, intOrThrow, 3, addWhenFlagSet2, 18, 1.0f, "Positive Elevation Gain", "m");
        int addWhenFlagSet4 = addWhenFlagSet3 + BaseValueParser.addWhenFlagSet(sb, bluetoothGattCharacteristic, intOrThrow, 4, addWhenFlagSet3, 18, 1.0f, "Stride Count", null);
        int addWhenFlagSet5 = addWhenFlagSet4 + BaseValueParser.addWhenFlagSet(sb, bluetoothGattCharacteristic, intOrThrow, 5, addWhenFlagSet4, 18, 1.0f, "Total Energy", "kcal");
        int addWhenFlagSet6 = addWhenFlagSet5 + BaseValueParser.addWhenFlagSet(sb, bluetoothGattCharacteristic, intOrThrow, 5, addWhenFlagSet5, 18, 1.0f, "Energy Per Hour", "kcal");
        int addWhenFlagSet7 = addWhenFlagSet6 + BaseValueParser.addWhenFlagSet(sb, bluetoothGattCharacteristic, intOrThrow, 5, addWhenFlagSet6, 17, 1.0f, "Energy Per Minute", "kcal");
        int addWhenFlagSet8 = addWhenFlagSet7 + BaseValueParser.addWhenFlagSet(sb, bluetoothGattCharacteristic, intOrThrow, 6, addWhenFlagSet7, 17, 1.0f, "Heart Rate", "bpm");
        int addWhenFlagSet9 = addWhenFlagSet8 + BaseValueParser.addWhenFlagSet(sb, bluetoothGattCharacteristic, intOrThrow, 7, addWhenFlagSet8, 17, 0.1f, "Metabolic Equivalent", null);
        BaseValueParser.addWhenFlagSet(sb, bluetoothGattCharacteristic, intOrThrow, 9, addWhenFlagSet9 + BaseValueParser.addWhenFlagSet(sb, bluetoothGattCharacteristic, intOrThrow, 8, addWhenFlagSet9, 18, 1.0f, "Elapsed Time", "s"), 18, 1.0f, "Remaining Time", "s");
        int i = intOrThrow & (-1024);
        if (i != 0) {
            sb.append(String.format(Locale.US, "Reserved Flags for Future Use: 0x%04X\n", Integer.valueOf(i)));
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }
}
