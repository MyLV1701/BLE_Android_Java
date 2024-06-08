package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattDescriptor;
import java.util.Locale;
import no.nordicsemi.android.mcp.ble.parser.utils.ParserUtils;

/* loaded from: classes.dex */
public class ESMeasurementDescriptorParser {
    public static String parse(BluetoothGattDescriptor bluetoothGattDescriptor) {
        byte[] value = bluetoothGattDescriptor.getValue();
        if (value.length != 11) {
            return "Invalid data length (11 bytes expected): " + GeneralDescriptorParser.parse(bluetoothGattDescriptor);
        }
        StringBuilder sb = new StringBuilder();
        int intValue = ParserUtils.getIntValue(value, 18, 0);
        if (intValue == 0) {
            sb.append("No flags");
        } else {
            sb.append(String.format(Locale.US, "Unsupported flags: 0x%04X", Integer.valueOf(intValue)));
        }
        int intValue2 = ParserUtils.getIntValue(value, 17, 2);
        sb.append("\nSampling function: ");
        sb.append(parseSamplingFunction(intValue2));
        int intValue3 = ParserUtils.getIntValue(value, 19, 3);
        sb.append("\nMeasurement period: ");
        if (intValue3 > 0) {
            sb.append(intValue3);
            sb.append(" sec.");
        } else {
            sb.append("Not in use");
        }
        int intValue4 = ParserUtils.getIntValue(value, 19, 6);
        sb.append("\nUpdate interval: ");
        if (intValue4 > 0) {
            sb.append(intValue4);
            sb.append(" sec.");
        } else {
            sb.append("Not in use");
        }
        int intValue5 = ParserUtils.getIntValue(value, 17, 9);
        sb.append("\nApplication: ");
        sb.append(parseApplication(intValue5));
        int intValue6 = ParserUtils.getIntValue(value, 17, 10);
        sb.append("\nUncertainty: ");
        if (intValue6 < 255) {
            sb.append(intValue6 * 0.5f);
            sb.append("%");
        } else {
            sb.append("Information not available");
        }
        return sb.toString();
    }

    private static String parseApplication(int i) {
        switch (i) {
            case 0:
                return "Unspecified";
            case 1:
                return "Air";
            case 2:
                return "Water";
            case 3:
                return "Barometric";
            case 4:
                return "Soil";
            case 5:
                return "Infrared";
            case 6:
                return "Map Database";
            case 7:
                return "Barometric Elevation Source";
            case 8:
                return "GPS only Elevation Source";
            case 9:
                return "GPS and Map database Elevation Source";
            case 10:
                return "Vertical datum Elevation Source";
            case 11:
                return "Onshore";
            case 12:
                return "Onboard vessel or vehicle";
            case 13:
                return "Front";
            case 14:
                return "Back/Rear";
            case 15:
                return "Upper";
            case 16:
                return "Lower";
            case 17:
                return "Primary";
            case 18:
                return "Secondary";
            case 19:
                return "Outdoor";
            case 20:
                return "Indoor";
            case 21:
                return "Top";
            case 22:
                return "Bottom";
            case 23:
                return "Main";
            case 24:
                return "Backup";
            case 25:
                return "Auxiliary";
            case 26:
                return "Supplementary";
            case 27:
                return "Inside";
            case 28:
                return "Outside";
            case 29:
                return "Left";
            case 30:
                return "Right";
            case 31:
                return "Internal";
            case 32:
                return "External";
            case 33:
                return "Solar";
            default:
                return "Reserved for future use (" + i + ")";
        }
    }

    private static String parseSamplingFunction(int i) {
        switch (i) {
            case 0:
                return "Unspecified";
            case 1:
                return "Instantaneous";
            case 2:
                return "Arithmetic Mean";
            case 3:
                return "RMS";
            case 4:
                return "Maximum";
            case 5:
                return "Minimum";
            case 6:
                return "Accumulated";
            case 7:
                return "Count";
            default:
                return "Reserved for future use (" + i + ")";
        }
    }
}
