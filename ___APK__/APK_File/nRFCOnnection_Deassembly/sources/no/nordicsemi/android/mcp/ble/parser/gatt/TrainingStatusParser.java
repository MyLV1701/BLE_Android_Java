package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import java.util.Locale;
import no.nordicsemi.android.mcp.ble.parser.utils.ParserUtils;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class TrainingStatusParser implements ICharacteristicParser {
    private static String parseStatus(int i) {
        switch (i) {
            case 0:
                return "Other";
            case 1:
                return "Idle";
            case 2:
                return "Warming Up";
            case 3:
                return "Low Intensity Interval";
            case 4:
                return "High Intensity Interval";
            case 5:
                return "Recovery Interval";
            case 6:
                return "Isometric";
            case 7:
                return "Heart Rate Control";
            case 8:
                return "Fitness Test";
            case 9:
                return "Speed Outside of Control Region - Low";
            case 10:
                return "Speed Outside of Control Region - High";
            case 11:
                return "Cool Down";
            case 12:
                return "Watt Control";
            case 13:
                return "Manual Mode (Quick Start)";
            case 14:
                return "Pre-Workout";
            case 15:
                return "PostWorkout";
            default:
                return String.format(Locale.US, "Reserved for Future Use (0x%02X)", Integer.valueOf(i));
        }
    }

    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic);
    }

    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        int intOrThrow = ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(17, 0));
        boolean z = (intOrThrow & 1) > 0;
        boolean z2 = (intOrThrow & 2) > 0;
        int intOrThrow2 = ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(17, 1));
        StringBuilder sb = new StringBuilder();
        sb.append("Status: ");
        sb.append(parseStatus(intOrThrow2));
        if (z) {
            sb.append("\nDetails: ");
            sb.append(bluetoothGattCharacteristic.getStringValue(2));
        }
        if (z && z2) {
            sb.append("... (more string available)");
        } else if (z2) {
            sb.append("\nExtended String present");
        }
        return sb.toString();
    }
}
