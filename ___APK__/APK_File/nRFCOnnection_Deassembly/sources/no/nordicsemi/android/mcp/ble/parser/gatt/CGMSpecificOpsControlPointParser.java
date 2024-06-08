package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class CGMSpecificOpsControlPointParser implements ICharacteristicParser {
    private static final int OP_CGM_COMMUNICATION_INTERVAL_RESPONSE = 3;
    private static final int OP_CODE_RESPONSE_CODE = 28;
    private static final int OP_CODE_START_SESSION = 26;
    private static final int OP_CODE_STOP_SESSION = 27;
    private static final int OP_GET_CGM_COMMUNICATION_INTERVAL = 2;
    private static final int OP_GET_GLUCOSE_CALIBRATION_VALUE = 5;
    private static final int OP_GET_HYPER_ALERT_LEVEL = 17;
    private static final int OP_GET_HYPO_ALERT_LEVEL = 14;
    private static final int OP_GET_PATIENT_HIGH_ALERT_LEVEL = 8;
    private static final int OP_GET_PATIENT_LOW_ALERT_LEVEL = 11;
    private static final int OP_GET_RATE_OF_DECREASE_ALERT_LEVEL = 20;
    private static final int OP_GET_RATE_OF_INCREASE_ALERT_LEVEL = 23;
    private static final int OP_GLUCOSE_CALIBRATION_VALUE_RESPONSE = 6;
    private static final int OP_HYPER_ALERT_LEVEL_RESPONSE = 18;
    private static final int OP_HYPO_ALERT_LEVEL_RESPONSE = 15;
    private static final int OP_PATIENT_HIGH_ALERT_LEVEL_RESPONSE = 9;
    private static final int OP_PATIENT_LOW_ALERT_LEVEL_RESPONSE = 12;
    private static final int OP_RATE_OF_DECREASE_ALERT_LEVEL_RESPONSE = 21;
    private static final int OP_RATE_OF_INCREASE_ALERT_LEVEL_RESPONSE = 24;
    private static final int OP_RESET_DEVICE_SPECIFIC_ALERT = 25;
    private static final int OP_SET_CGM_COMMUNICATION_INTERVAL = 1;
    private static final int OP_SET_GLUCOSE_CALIBRATION_VALUE = 4;
    private static final int OP_SET_HYPER_ALERT_LEVEL = 16;
    private static final int OP_SET_HYPO_ALERT_LEVEL = 13;
    private static final int OP_SET_PATIENT_HIGH_ALERT_LEVEL = 7;
    private static final int OP_SET_PATIENT_LOW_ALERT_LEVEL = 10;
    private static final int OP_SET_RATE_OF_DECREASE_ALERT_LEVEL = 19;
    private static final int OP_SET_RATE_OF_INCREASE_ALERT_LEVEL = 22;

    private static String parseNextCalibrationTime(int i) {
        if (i == 0) {
            return "Calibration Required Instantly";
        }
        return i + " min";
    }

    private static String parseOpCode(int i) {
        switch (i) {
            case 1:
                return "Set CGM Communication Interval";
            case 2:
                return "Get CGM Communication Interval";
            case 3:
                return "CGM Communication Interval";
            case 4:
                return "Set CGM Calibration Value";
            case 5:
                return "Get CGM Calibration Value";
            case 6:
                return "CGM Calibration Value";
            case 7:
                return "Set Patient High Alert Level";
            case 8:
                return "Get Patient High Alert Level";
            case 9:
                return "Patient High Alert Level";
            case 10:
                return "Set Patient Low Alert Level";
            case 11:
                return "Get Patient Low Alert Level";
            case 12:
                return "Patient Low Alert Level";
            case 13:
                return "Set Hypo Alert Level";
            case 14:
                return "Get Hypo Alert Level";
            case 15:
                return "Hypo Alert Level";
            case 16:
                return "Set Hyper Alert Level";
            case 17:
                return "Get Hyper Alert Level";
            case 18:
                return "Hyper Alert Level";
            case 19:
                return "Set Rate of Decrease Alert Level";
            case 20:
                return "Get Rate of Decrease Alert Level";
            case 21:
                return "Rate of Decrease Alert Level";
            case 22:
                return "Set Rate of Increase Alert Level";
            case 23:
                return "Get Rate of Increase Alert Level";
            case 24:
                return "Rate of Increase Alert Level";
            case 25:
                return "Reset Device Specific Alert";
            case 26:
                return "Start Session";
            case 27:
                return "Stop Session";
            case 28:
                return "Response";
            default:
                return "Reserved for future use (" + i + ")";
        }
    }

    private static String parseRecordNumber(int i) {
        return i == 65535 ? "Last Calibration Data" : String.valueOf(i);
    }

    private static String parseResponseCode(int i) {
        if (i == 1) {
            return "Success";
        }
        if (i == 2) {
            return "Op Code not supported";
        }
        if (i == 3) {
            return "Invalid Operand";
        }
        if (i == 4) {
            return "Procedure not completed";
        }
        if (i == 5) {
            return "Parameter out of range";
        }
        return "Reserved for future use (" + i + ")";
    }

    private static String parseSampleLocation(int i) {
        if (i == 1) {
            return "Finger";
        }
        if (i == 2) {
            return "Alternate Site Test (AST)";
        }
        if (i == 3) {
            return "Earlobe";
        }
        if (i == 4) {
            return "Control solution";
        }
        if (i == 5) {
            return "Subcutaneous tissue";
        }
        if (i == 15) {
            return "Sample Location value not available";
        }
        return "Reserved for future use (" + i + ")";
    }

    private static void parseStatus(StringBuilder sb, int i) {
        if (i == 0) {
            return;
        }
        sb.append("\nStatus:\n");
        if ((i & 1) > 0) {
            sb.append("- Calibration Data rejected");
        }
        if ((i & 2) > 0) {
            sb.append("- Calibration Data out of range");
        }
        if ((i & 4) > 0) {
            sb.append("- Calibration Process pending");
        }
        if ((i & 248) > 0) {
            sb.append("- Reserved for future use (");
            sb.append(i);
            sb.append(")");
        }
    }

    private static String parseType(int i) {
        switch (i) {
            case 1:
                return "Capillary Whole blood";
            case 2:
                return "Capillary Plasma";
            case 3:
                return "Capillary Whole blood";
            case 4:
                return "Venous Plasma";
            case 5:
                return "Arterial Whole blood";
            case 6:
                return "Arterial Plasma";
            case 7:
                return "Undetermined Whole blood";
            case 8:
                return "Undetermined Plasma";
            case 9:
                return "Interstitial Fluid (ISF)";
            case 10:
                return "Control Solution";
            default:
                return "Reserved for future use (" + i + ")";
        }
    }

    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic);
    }

    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        int intValue = bluetoothGattCharacteristic.getIntValue(17, 0).intValue();
        StringBuilder sb = new StringBuilder();
        sb.append(parseOpCode(intValue));
        switch (intValue) {
            case 1:
            case 3:
                int intValue2 = bluetoothGattCharacteristic.getIntValue(17, 1).intValue();
                sb.append(" to ");
                sb.append(intValue2);
                sb.append(" min");
                break;
            case 4:
                float floatValue = bluetoothGattCharacteristic.getFloatValue(50, 1).floatValue();
                int intValue3 = bluetoothGattCharacteristic.getIntValue(18, 3).intValue();
                int intValue4 = bluetoothGattCharacteristic.getIntValue(17, 5).intValue();
                sb.append(" to:\n");
                sb.append("Glucose Concentration of Calibration: ");
                sb.append(floatValue);
                sb.append(" mg/dL\n");
                sb.append("Time: ");
                sb.append(intValue3);
                sb.append(" min\n");
                sb.append("Type: ");
                sb.append(parseType(intValue4 & 15));
                sb.append("\n");
                sb.append("Sample Location: ");
                sb.append(parseSampleLocation((intValue4 & 240) >> 4));
                sb.append("\n");
                break;
            case 5:
                int intValue5 = bluetoothGattCharacteristic.getIntValue(18, 1).intValue();
                sb.append(": ");
                sb.append(parseRecordNumber(intValue5));
                break;
            case 6:
                float floatValue2 = bluetoothGattCharacteristic.getFloatValue(50, 1).floatValue();
                int intValue6 = bluetoothGattCharacteristic.getIntValue(18, 3).intValue();
                int intValue7 = bluetoothGattCharacteristic.getIntValue(17, 5).intValue();
                int i = intValue7 & 15;
                int i2 = (intValue7 & 240) >> 4;
                int intValue8 = bluetoothGattCharacteristic.getIntValue(18, 6).intValue();
                int intValue9 = bluetoothGattCharacteristic.getIntValue(18, 8).intValue();
                int intValue10 = bluetoothGattCharacteristic.getIntValue(17, 10).intValue();
                sb.append(":\n");
                if (intValue9 > 0) {
                    sb.append("Glucose Concentration of Calibration: ");
                    sb.append(floatValue2);
                    sb.append(" mg/dL\n");
                    sb.append("Time: ");
                    sb.append(intValue6);
                    sb.append(" min\n");
                    sb.append("Type: ");
                    sb.append(parseType(i));
                    sb.append("\n");
                    sb.append("Sample Location: ");
                    sb.append(parseSampleLocation(i2));
                    sb.append("\n");
                    sb.append("Next Calibration Time: ");
                    sb.append(parseNextCalibrationTime(intValue8));
                    sb.append("\n");
                    sb.append("Data Record Number: ");
                    sb.append(intValue9);
                    parseStatus(sb, intValue10);
                    break;
                } else {
                    sb.append("No Calibration Data Stored");
                    break;
                }
            case 7:
            case 10:
            case 13:
            case 16:
                float floatValue3 = bluetoothGattCharacteristic.getFloatValue(50, 1).floatValue();
                sb.append(" to: ");
                sb.append(floatValue3);
                sb.append(" mg/dL");
                break;
            case 9:
            case 12:
            case 15:
            case 18:
                float floatValue4 = bluetoothGattCharacteristic.getFloatValue(50, 1).floatValue();
                sb.append(": ");
                sb.append(floatValue4);
                sb.append(" mg/dL");
                break;
            case 19:
            case 22:
                float floatValue5 = bluetoothGattCharacteristic.getFloatValue(50, 1).floatValue();
                sb.append(" to: ");
                sb.append(floatValue5);
                sb.append(" mg/dL/min");
                break;
            case 21:
            case 24:
                float floatValue6 = bluetoothGattCharacteristic.getFloatValue(50, 1).floatValue();
                sb.append(": ");
                sb.append(floatValue6);
                sb.append(" mg/dL/min");
                break;
            case 28:
                int intValue11 = bluetoothGattCharacteristic.getIntValue(17, 1).intValue();
                int intValue12 = bluetoothGattCharacteristic.getIntValue(17, 2).intValue();
                sb.append(" to ");
                sb.append(parseOpCode(intValue11));
                sb.append(": ");
                sb.append(parseResponseCode(intValue12));
                break;
        }
        return sb.toString();
    }
}
