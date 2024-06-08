package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import java.util.Locale;
import no.nordicsemi.android.mcp.ble.parser.utils.ParserUtils;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class IndoorBikeParser implements ICharacteristicParser {
    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic);
    }

    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        int intOrThrow = ParserUtils.intOrThrow(Integer.valueOf(ParserUtils.getIntValue(bluetoothGattCharacteristic.getValue(), 18, 0)));
        StringBuilder sb = new StringBuilder();
        int addWhenFlagNotSet = 2 + BaseValueParser.addWhenFlagNotSet(sb, bluetoothGattCharacteristic, intOrThrow, 0, 2, 18, 0.01f, "Instantaneous Speed", "km/h");
        int addWhenFlagSet = addWhenFlagNotSet + BaseValueParser.addWhenFlagSet(sb, bluetoothGattCharacteristic, intOrThrow, 1, addWhenFlagNotSet, 18, 0.01f, "Average Speed", "km/h");
        int addWhenFlagSet2 = addWhenFlagSet + BaseValueParser.addWhenFlagSet(sb, bluetoothGattCharacteristic, intOrThrow, 2, addWhenFlagSet, 18, 0.5f, "Instantaneous Cadence", "per min");
        int addWhenFlagSet3 = addWhenFlagSet2 + BaseValueParser.addWhenFlagSet(sb, bluetoothGattCharacteristic, intOrThrow, 3, addWhenFlagSet2, 18, 0.5f, "Average Cadence", "per min");
        int addWhenFlagSet4 = addWhenFlagSet3 + BaseValueParser.addWhenFlagSet(sb, bluetoothGattCharacteristic, intOrThrow, 4, addWhenFlagSet3, 19, 1.0f, "Total Distance", "m");
        int addWhenFlagSet5 = addWhenFlagSet4 + BaseValueParser.addWhenFlagSet(sb, bluetoothGattCharacteristic, intOrThrow, 5, addWhenFlagSet4, 34, 1.0f, "Resistance Level", null);
        int addWhenFlagSet6 = addWhenFlagSet5 + BaseValueParser.addWhenFlagSet(sb, bluetoothGattCharacteristic, intOrThrow, 6, addWhenFlagSet5, 34, 1.0f, "Instantaneous Power", "W");
        int addWhenFlagSet7 = addWhenFlagSet6 + BaseValueParser.addWhenFlagSet(sb, bluetoothGattCharacteristic, intOrThrow, 7, addWhenFlagSet6, 34, 1.0f, "Average Power", "W");
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
