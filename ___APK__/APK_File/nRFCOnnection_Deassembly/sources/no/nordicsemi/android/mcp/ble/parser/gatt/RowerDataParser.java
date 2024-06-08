package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import java.util.Locale;
import no.nordicsemi.android.mcp.ble.parser.utils.ParserUtils;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class RowerDataParser implements ICharacteristicParser {
    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic);
    }

    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        int intOrThrow = ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(18, 0));
        StringBuilder sb = new StringBuilder();
        int addWhenFlagNotSet = 2 + BaseValueParser.addWhenFlagNotSet(sb, bluetoothGattCharacteristic, intOrThrow, 0, 2, 17, 0.5f, "Stroke Rate", "stroke/min");
        int addWhenFlagNotSet2 = addWhenFlagNotSet + BaseValueParser.addWhenFlagNotSet(sb, bluetoothGattCharacteristic, intOrThrow, 0, addWhenFlagNotSet, 18, 1.0f, "Stroke Count", null);
        int addWhenFlagSet = addWhenFlagNotSet2 + BaseValueParser.addWhenFlagSet(sb, bluetoothGattCharacteristic, intOrThrow, 1, addWhenFlagNotSet2, 17, 0.5f, "Average Stroke Rate", "stroke/min");
        int addWhenFlagSet2 = addWhenFlagSet + BaseValueParser.addWhenFlagSet(sb, bluetoothGattCharacteristic, intOrThrow, 2, addWhenFlagSet, 19, 1.0f, "Total Distance", "m");
        int addWhenFlagSet3 = addWhenFlagSet2 + BaseValueParser.addWhenFlagSet(sb, bluetoothGattCharacteristic, intOrThrow, 3, addWhenFlagSet2, 18, 1.0f, "Instantaneous Pace", "s");
        int addWhenFlagSet4 = addWhenFlagSet3 + BaseValueParser.addWhenFlagSet(sb, bluetoothGattCharacteristic, intOrThrow, 4, addWhenFlagSet3, 18, 1.0f, "Average Pace", "s");
        int addWhenFlagSet5 = addWhenFlagSet4 + BaseValueParser.addWhenFlagSet(sb, bluetoothGattCharacteristic, intOrThrow, 5, addWhenFlagSet4, 34, 1.0f, "Instantaneous Power", "W");
        int addWhenFlagSet6 = addWhenFlagSet5 + BaseValueParser.addWhenFlagSet(sb, bluetoothGattCharacteristic, intOrThrow, 6, addWhenFlagSet5, 34, 1.0f, "Average Power", "W");
        int addWhenFlagSet7 = addWhenFlagSet6 + BaseValueParser.addWhenFlagSet(sb, bluetoothGattCharacteristic, intOrThrow, 7, addWhenFlagSet6, 34, 1.0f, "Resistance Level", null);
        int addWhenFlagSet8 = addWhenFlagSet7 + BaseValueParser.addWhenFlagSet(sb, bluetoothGattCharacteristic, intOrThrow, 8, addWhenFlagSet7, 18, 1.0f, "Total Energy", "kcal");
        int addWhenFlagSet9 = addWhenFlagSet8 + BaseValueParser.addWhenFlagSet(sb, bluetoothGattCharacteristic, intOrThrow, 8, addWhenFlagSet8, 18, 1.0f, "Energy Per Hour", "kcal");
        int addWhenFlagSet10 = addWhenFlagSet9 + BaseValueParser.addWhenFlagSet(sb, bluetoothGattCharacteristic, intOrThrow, 8, addWhenFlagSet9, 17, 1.0f, "Energy Per Minute", "kcal");
        int addWhenFlagSet11 = addWhenFlagSet10 + BaseValueParser.addWhenFlagSet(sb, bluetoothGattCharacteristic, intOrThrow, 9, addWhenFlagSet10, 17, 1.0f, "Heart Rate", "bpm");
        int addWhenFlagSet12 = addWhenFlagSet11 + BaseValueParser.addWhenFlagSet(sb, bluetoothGattCharacteristic, intOrThrow, 10, addWhenFlagSet11, 17, 0.1f, "Metabolic Equivalent", null);
        BaseValueParser.addWhenFlagSet(sb, bluetoothGattCharacteristic, intOrThrow, 12, addWhenFlagSet12 + BaseValueParser.addWhenFlagSet(sb, bluetoothGattCharacteristic, intOrThrow, 11, addWhenFlagSet12, 18, 1.0f, "Elapsed Time", "s"), 18, 1.0f, "Remaining Time", "s");
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
