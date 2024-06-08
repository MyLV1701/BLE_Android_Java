package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import java.util.Locale;
import no.nordicsemi.android.mcp.ble.parser.utils.ParserUtils;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class FitnessMachineControlPointParser implements ICharacteristicParser {
    private static String parseOpCode(int i) {
        int i2 = i & 255;
        if (i2 == 128) {
            return "Response for ";
        }
        switch (i2) {
            case 0:
                return "Request Control";
            case 1:
                return "Reset";
            case 2:
                return "Set Target Speed";
            case 3:
                return "Set Target Inclination";
            case 4:
                return "Set Target Resistance Level";
            case 5:
                return "Set Target Power";
            case 6:
                return "Set Target Heart Rate";
            case 7:
                return "Start or Resume";
            case 8:
                return "Stop or Pause";
            case 9:
                return "Set Targeted Expended Energy";
            case 10:
                return "Set Targeted Number of Steps";
            case 11:
                return "Set Targeted Number of Strides";
            case 12:
                return "Set Targeted Distance";
            case 13:
                return "Set Targeted Training Time";
            case 14:
                return "Set Targeted Time in Two Heart Rate Zones";
            case 15:
                return "Set Targeted Time in Three Heart Rate Zones";
            case 16:
                return "Set Targeted Time in Five Heart Rate Zones";
            case 17:
                return "Set Indoor Bike Simulation Parameters";
            case 18:
                return "Set Wheel Circumference";
            case 19:
                return "Spin Down Control";
            case 20:
                return "Set Targeted Cadence";
            default:
                return String.format(Locale.US, "Reserved for Future Use (0x%02X)", Integer.valueOf(i));
        }
    }

    private static String parseParameters(int i, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        int length = bluetoothGattCharacteristic.getValue().length - 1;
        if (i == 2) {
            return "\nTarget Speed: " + (ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(18, 1)) * 0.01f) + " km/h";
        }
        if (i == 3) {
            return "\nTarget Inclination: " + (ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(34, 1)) * 0.1f) + " %";
        }
        if (i == 4) {
            return "\nTarget Resistance Level: " + (ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(17, 1)) * 0.1f);
        }
        if (i == 5) {
            return "\nTarget Power: " + ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(34, 1)) + " W";
        }
        if (i == 6) {
            return "\nTarget Heart Rate: " + ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(17, 1)) + " bpm";
        }
        if (i != 128) {
            switch (i) {
                case 8:
                    int intOrThrow = ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(17, 1));
                    return intOrThrow != 1 ? intOrThrow != 2 ? String.format(Locale.US, "\nControl Information: Reserved for Future Use (0x%02X)", Integer.valueOf(intOrThrow)) : "\nControl Information: Pause" : "\nControl Information: Stop";
                case 9:
                    return "\nTargeted Expended Energy: " + ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(18, 1)) + " kcal";
                case 10:
                    return "\nTargeted Number of Steps: " + ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(18, 1));
                case 11:
                    return "\nTargeted Number of Strides: " + ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(18, 1));
                case 12:
                    return "\nTargeted Distance: " + ParserUtils.intOrThrow(Integer.valueOf(ParserUtils.getIntValue(bluetoothGattCharacteristic.getValue(), 19, 1))) + " m";
                case 13:
                    return "\nTargeted Training Time: " + ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(18, 1)) + " s";
                case 14:
                    return "\nNew Targeted Times:\nFat Burn Zone: " + ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(18, 1)) + " s\nFitness Zone: " + ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(18, 3)) + " s";
                case 15:
                    return "\nNew Targeted Times:\nLight Zone: " + ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(18, 1)) + " s\nModerate Zone: " + ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(18, 3)) + " s\nHard Zone: " + ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(18, 5)) + " s";
                case 16:
                    return "\nNew Targeted Times:\nVery Light Zone: " + ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(18, 1)) + " s\nLight Zone: " + ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(18, 3)) + " s\nModerate Zone: " + ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(18, 5)) + " s\nHard Zone: " + ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(18, 7)) + " s\nMaximum Zone: " + ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(18, 9)) + " s";
                case 17:
                    int intOrThrow2 = ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(34, 1));
                    int intOrThrow3 = ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(34, 3));
                    int intOrThrow4 = ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(17, 5));
                    int intOrThrow5 = ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(17, 6));
                    StringBuilder sb = new StringBuilder();
                    sb.append("\nSimulation Parameter Array:\nWind Speed: ");
                    sb.append(intOrThrow2 * 0.001f);
                    sb.append(" m/s\nGrade: ");
                    sb.append(intOrThrow3 * 0.01f);
                    sb.append(" %\nCrr (Coefficient of Rolling Resistance): ");
                    double d2 = intOrThrow4;
                    Double.isNaN(d2);
                    sb.append(d2 * 1.0E-4d);
                    sb.append("\nCw (Wind Resistance Coefficient): ");
                    double d3 = intOrThrow5;
                    Double.isNaN(d3);
                    sb.append(d3 * 0.01d);
                    sb.append(" kg/m");
                    return sb.toString();
                case 18:
                    int intOrThrow6 = ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(18, 1));
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("\nNew Wheel Circumference: ");
                    double d4 = intOrThrow6;
                    Double.isNaN(d4);
                    sb2.append(d4 * 0.1d);
                    sb2.append(" mm");
                    return sb2.toString();
                case 19:
                    return "\nControl Parameter: " + parseSpinDownControlValue(ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(17, 1)));
                case 20:
                    int intOrThrow7 = ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(18, 1));
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("\nNew Targeted Cadence: ");
                    double d5 = intOrThrow7;
                    Double.isNaN(d5);
                    sb3.append(d5 * 0.5d);
                    sb3.append(" per min");
                    return sb3.toString();
                default:
                    if (length <= 0) {
                        return "";
                    }
                    return "\nUnknown parameters: " + GeneralCharacteristicParser.parse(bluetoothGattCharacteristic.getValue(), 1);
            }
        }
        int intOrThrow8 = ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(17, 1));
        int intOrThrow9 = ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(17, 2));
        if (intOrThrow8 == 19 && intOrThrow9 == 1) {
            int intOrThrow10 = ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(18, 3));
            int intOrThrow11 = ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(18, 5));
            StringBuilder sb4 = new StringBuilder();
            sb4.append(parseOpCode(intOrThrow8));
            sb4.append("\nResult: ");
            sb4.append(parseResultCode(intOrThrow9));
            sb4.append("\nTarget Speed Low: ");
            double d6 = intOrThrow10;
            Double.isNaN(d6);
            sb4.append(d6 * 0.01d);
            sb4.append(" km/h\nTarget Speed High: ");
            double d7 = intOrThrow11;
            Double.isNaN(d7);
            sb4.append(d7 * 0.01d);
            sb4.append(" km/h");
            return sb4.toString();
        }
        return parseOpCode(intOrThrow8) + "\nResult: " + parseResultCode(intOrThrow9);
    }

    private static String parseResultCode(int i) {
        int i2 = i & 255;
        return i2 != 1 ? i2 != 2 ? i2 != 3 ? i2 != 4 ? i2 != 5 ? String.format(Locale.US, "Reserved for Future Use (0x%02X)", Integer.valueOf(i)) : "Control Not Permitted" : "Operation Failed" : "Invalid Parameter" : "Op Code Not Supported" : "Success";
    }

    private static String parseSpinDownControlValue(int i) {
        int i2 = i & 255;
        return i2 != 1 ? i2 != 2 ? String.format(Locale.US, "Reserved for Future Use (0x%02X)", Integer.valueOf(i)) : "Ignore" : "Start";
    }

    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic);
    }

    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        int intOrThrow = ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(17, 0));
        return parseOpCode(intOrThrow) + parseParameters(intOrThrow, bluetoothGattCharacteristic);
    }
}
