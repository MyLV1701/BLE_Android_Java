package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import java.util.Locale;
import no.nordicsemi.android.mcp.ble.parser.utils.ParserUtils;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class FitnessMachineStatusParser implements ICharacteristicParser {
    private static String parseOpCode(int i) {
        int i2 = i & 255;
        if (i2 == 255) {
            return "Control Permission Lost";
        }
        switch (i2) {
            case 1:
                return "Reset";
            case 2:
                return "Fitness Machine Stopped or Paused by the User";
            case 3:
                return "Fitness Machine Stopped by Safety Key";
            case 4:
                return "Fitness Machine Started or Resumed by the User";
            case 5:
                return "Target Speed Changed";
            case 6:
                return "Target Incline Changed";
            case 7:
                return "Target Resistance Level Changed";
            case 8:
                return "Target Power Changed";
            case 9:
                return "Target Heart Rate Changed";
            case 10:
                return "Targeted Expended Energy Changed";
            case 11:
                return "Targeted Number of Steps Changed";
            case 12:
                return "Targeted Number of Strides Changed";
            case 13:
                return "Targeted Distance Changed";
            case 14:
                return "Targeted Training Time Changed";
            case 15:
                return "Targeted Time in Two Heart Rate Zones Changed";
            case 16:
                return "Targeted Time in Three Heart Rate Zones Changed";
            case 17:
                return "Targeted Time in Five Heart Rate Zones Changed";
            case 18:
                return "Indoor Bike Simulation Parameters Changed";
            case 19:
                return "Wheel Circumference Changed";
            case 20:
                return "Spin Down Status";
            case 21:
                return "Targeted Cadence Changed";
            default:
                return String.format(Locale.US, "Reserved for Future Use (0x%02X)", Integer.valueOf(i));
        }
    }

    private static String parseParameters(int i, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        int length = bluetoothGattCharacteristic.getValue().length - 1;
        if (i != 2) {
            switch (i) {
                case 5:
                    return "\nNew Target Value: " + (ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(18, 1)) * 0.01f) + " km/h";
                case 6:
                    return "\nNew Target Value: " + (ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(34, 1)) * 0.1f) + " %";
                case 7:
                    return "\nNew Target Value: " + (ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(17, 1)) * 0.1f);
                case 8:
                    return "\nNew Target Value: " + ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(34, 1)) + " W";
                case 9:
                    return "\nNew Target Value: " + ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(17, 1)) + " bpm";
                case 10:
                    return "\nNew Target Value: " + ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(18, 1)) + " kcal";
                case 11:
                    return "\nNew Target Value: " + ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(18, 1)) + " steps";
                case 12:
                    return "\nNew Target Value: " + ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(18, 1)) + " strides";
                case 13:
                    return "\nNew Target Value: " + ParserUtils.intOrThrow(Integer.valueOf(ParserUtils.getIntValue(bluetoothGattCharacteristic.getValue(), 19, 1))) + " m";
                case 14:
                    return "\nNew Target Value: " + ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(18, 1)) + " s";
                case 15:
                    return "\nNew Targeted Times:\nFat Burn Zone: " + ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(18, 1)) + " s\nFitness Zone: " + ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(18, 3)) + " s";
                case 16:
                    return "\nNew Targeted Times:\nLight Zone: " + ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(18, 1)) + " s\nModerate Zone: " + ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(18, 3)) + " s\nHard Zone: " + ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(18, 5)) + " s";
                case 17:
                    return "\nNew Targeted Times:\nVery Light Zone: " + ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(18, 1)) + " s\nLight Zone: " + ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(18, 3)) + " s\nModerate Zone: " + ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(18, 5)) + " s\nHard Zone: " + ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(18, 7)) + " s\nMaximum Zone: " + ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(18, 9)) + " s";
                case 18:
                    int intOrThrow = ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(34, 1));
                    int intOrThrow2 = ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(34, 3));
                    int intOrThrow3 = ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(17, 5));
                    int intOrThrow4 = ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(17, 6));
                    StringBuilder sb = new StringBuilder();
                    sb.append("\nNew Indoor Bike Simulation Parameters:\nWind Speed: ");
                    sb.append(intOrThrow * 0.001f);
                    sb.append(" m/s\nGrade: ");
                    sb.append(intOrThrow2 * 0.01f);
                    sb.append(" %\nCrr (Coefficient of Rolling Resistance): ");
                    double d2 = intOrThrow3;
                    Double.isNaN(d2);
                    sb.append(d2 * 1.0E-4d);
                    sb.append("\nCw (Wind Resistance Coefficient): ");
                    double d3 = intOrThrow4;
                    Double.isNaN(d3);
                    sb.append(d3 * 0.01d);
                    sb.append(" kg/m");
                    return sb.toString();
                case 19:
                    int intOrThrow5 = ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(18, 1));
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("\nNew Wheel Circumference: ");
                    double d4 = intOrThrow5;
                    Double.isNaN(d4);
                    sb2.append(d4 * 0.1d);
                    sb2.append(" mm");
                    return sb2.toString();
                case 20:
                    return "\nSpin Down Status Value: " + parseSpinDownStatusValue(ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(17, 1)));
                case 21:
                    int intOrThrow6 = ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(18, 1));
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("\nNew Targeted Cadence: ");
                    double d5 = intOrThrow6;
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
        int intOrThrow7 = ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(17, 1));
        return intOrThrow7 != 1 ? intOrThrow7 != 2 ? String.format(Locale.US, "\nControl Information: Reserved for Future Use (0x%02X)", Integer.valueOf(intOrThrow7)) : "\nControl Information: Pause" : "\nControl Information: Stop";
    }

    private static String parseSpinDownStatusValue(int i) {
        int i2 = i & 255;
        return i2 != 1 ? i2 != 2 ? i2 != 3 ? i2 != 4 ? String.format(Locale.US, "Reserved for Future Use (0x%02X)", Integer.valueOf(i)) : "Stop Pedaling" : "Error" : "Success" : "Spin Down Requested";
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
