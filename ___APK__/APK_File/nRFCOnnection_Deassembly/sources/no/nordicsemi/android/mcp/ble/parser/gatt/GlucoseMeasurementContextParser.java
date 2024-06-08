package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import no.nordicsemi.android.mcp.ble.parser.utils.ParserUtils;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class GlucoseMeasurementContextParser implements ICharacteristicParser {
    private static final int UNIT_kg = 0;
    private static final int UNIT_l = 1;

    private static String getCarbohydrate(int i) {
        switch (i) {
            case 1:
                return "Breakfast";
            case 2:
                return "Lunch";
            case 3:
                return "Dinner";
            case 4:
                return "Snack";
            case 5:
                return "Drink";
            case 6:
                return "Supper";
            case 7:
                return "Brunch";
            default:
                return "Reserved for future use (" + i + ")";
        }
    }

    private static String getHealth(int i) {
        if (i == 1) {
            return "Minor health issues";
        }
        if (i == 2) {
            return "Major health issues";
        }
        if (i == 3) {
            return "During menses";
        }
        if (i == 4) {
            return "Under stress";
        }
        if (i == 5) {
            return "No health issues";
        }
        if (i == 15) {
            return "Health value not available";
        }
        return "Reserved for future use (" + i + ")";
    }

    private static String getMeal(int i) {
        if (i == 1) {
            return "Preprandial (before meal)";
        }
        if (i == 2) {
            return "Postprandial (after meal)";
        }
        if (i == 3) {
            return "Fasting";
        }
        if (i == 4) {
            return "Casual (snacks, drinks, etc.)";
        }
        if (i == 5) {
            return "Bedtime";
        }
        return "Reserved for future use (" + i + ")";
    }

    private static String getMedicationId(int i) {
        if (i == 1) {
            return "Rapid acting insulin";
        }
        if (i == 2) {
            return "Short acting insulin";
        }
        if (i == 3) {
            return "Intermediate acting insulin";
        }
        if (i == 4) {
            return "Long acting insulin";
        }
        if (i == 5) {
            return "Pre-mixed insulin";
        }
        return "Reserved for future use (" + i + ")";
    }

    private static String getTester(int i) {
        if (i == 1) {
            return "Self";
        }
        if (i == 2) {
            return "Health Care Professional";
        }
        if (i == 3) {
            return "Lab test";
        }
        if (i == 4) {
            return "Casual (snacks, drinks, etc.)";
        }
        if (i == 15) {
            return "Tester value not available";
        }
        return "Reserved for future use (" + i + ")";
    }

    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic);
    }

    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        StringBuilder sb = new StringBuilder();
        int intOrThrow = ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(17, 0));
        boolean z = (intOrThrow & 1) > 0;
        boolean z2 = (intOrThrow & 2) > 0;
        boolean z3 = (intOrThrow & 4) > 0;
        boolean z4 = (intOrThrow & 8) > 0;
        boolean z5 = (intOrThrow & 16) > 0;
        boolean z6 = (intOrThrow & 32) > 0;
        boolean z7 = (intOrThrow & 64) > 0;
        boolean z8 = (intOrThrow & 128) > 0;
        int intOrThrow2 = ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(18, 1));
        int i = z8 ? 4 : 3;
        sb.append("Sequence number: ");
        sb.append(intOrThrow2);
        if (z) {
            int intOrThrow3 = ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(17, i));
            float floatOrThrow = ParserUtils.floatOrThrow(bluetoothGattCharacteristic.getFloatValue(50, i + 1));
            sb.append("\nCarbohydrate: ");
            sb.append(getCarbohydrate(intOrThrow3));
            sb.append(" (");
            sb.append(floatOrThrow);
            sb.append("g)");
            i += 3;
        }
        if (z2) {
            int intOrThrow4 = ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(17, i));
            sb.append("\nMeal: ");
            sb.append(getMeal(intOrThrow4));
            i++;
        }
        if (z3) {
            int intOrThrow5 = ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(17, i));
            sb.append("\nTester: ");
            sb.append(getTester((intOrThrow5 & 240) >> 4));
            sb.append("\nHealth: ");
            sb.append(getHealth(intOrThrow5 & 15));
            i++;
        }
        if (z4) {
            int intOrThrow6 = ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(18, i));
            int intOrThrow7 = ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(17, i + 2));
            sb.append("\nExercise duration: ");
            sb.append(intOrThrow6);
            sb.append("s (intensity ");
            sb.append(intOrThrow7);
            sb.append("%)");
            i += 3;
        }
        if (z5) {
            int intOrThrow8 = ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(17, i));
            float floatOrThrow2 = ParserUtils.floatOrThrow(bluetoothGattCharacteristic.getFloatValue(50, i + 1)) / 1000.0f;
            sb.append("\nMedication: ");
            sb.append(getMedicationId(intOrThrow8));
            sb.append(" (");
            sb.append(floatOrThrow2);
            sb.append(z6 ? "l)" : "g)");
            i += 3;
        }
        if (z7) {
            float floatOrThrow3 = ParserUtils.floatOrThrow(bluetoothGattCharacteristic.getFloatValue(50, i));
            sb.append("\nHbA1c: ");
            sb.append(floatOrThrow3);
            sb.append("%");
        }
        return sb.toString();
    }
}
