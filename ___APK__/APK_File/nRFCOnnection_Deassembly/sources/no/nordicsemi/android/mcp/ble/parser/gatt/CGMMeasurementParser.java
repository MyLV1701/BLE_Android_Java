package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import java.util.Locale;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class CGMMeasurementParser implements ICharacteristicParser {
    private static final int FLAGS_CGM_QUALITY_PRESENT = 2;
    private static final int FLAGS_CGM_TREND_INFO_PRESENT = 1;
    private static final int FLAGS_SENSOR_STATUS_ANNUNCIATION_CAL_TEMP_OCTET_PRESENT = 8;
    private static final int FLAGS_SENSOR_STATUS_ANNUNCIATION_STATUS_OCTET_PRESENT = 16;
    private static final int FLAGS_SENSOR_STATUS_ANNUNCIATION_WARNING_OCTET_PRESENT = 4;
    private static final int SSA_CALIBRATION_NOT_ALLOWED = 512;
    private static final int SSA_CALIBRATION_RECOMMENDED = 1024;
    private static final int SSA_CALIBRATION_REQUIRED = 2048;
    private static final int SSA_DEVICE_BATTERY_LOW = 2;
    private static final int SSA_DEVICE_SPEC_ALERT = 16;
    private static final int SSA_GENERAL_DEVICE_FAULT = 32;
    private static final int SSA_RESULT_HIGHER_THAN_DEVICE_CAN_PROCESS = 8388608;
    private static final int SSA_RESULT_HIGHER_THAN_HYPER_LEVEL = 524288;
    private static final int SSA_RESULT_HIGHER_THAN_PATIENT_HIGH_LEVEL = 131072;
    private static final int SSA_RESULT_LOWER_THAN_DEVICE_CAN_PROCESS = 4194304;
    private static final int SSA_RESULT_LOWER_THAN_HYPO_LEVEL = 262144;
    private static final int SSA_RESULT_LOWER_THAN_PATIENT_LOW_LEVEL = 65536;
    private static final int SSA_SENSOR_MALFUNCTION = 8;
    private static final int SSA_SENSOR_RATE_OF_DECREASE_EXCEEDED = 1048576;
    private static final int SSA_SENSOR_RATE_OF_INCREASE_EXCEEDED = 2097152;
    private static final int SSA_SENSOR_TEMP_TOO_HIGH = 4096;
    private static final int SSA_SENSOR_TEMP_TOO_LOW = 8192;
    private static final int SSA_SENSOR_TYPE_INCORRECT = 4;
    private static final int SSA_SESSION_STOPPED = 1;
    private static final int SSA_TIME_SYNC_REQUIRED = 256;

    private static int parseRecord(StringBuilder sb, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
        int i2;
        int i3 = i + 1;
        int intValue = bluetoothGattCharacteristic.getIntValue(17, i).intValue();
        int i4 = i3 + 1;
        int intValue2 = bluetoothGattCharacteristic.getIntValue(17, i3).intValue();
        boolean z = (intValue2 & 1) > 0;
        boolean z2 = (intValue2 & 2) > 0;
        boolean z3 = (intValue2 & 4) > 0;
        boolean z4 = (intValue2 & 8) > 0;
        boolean z5 = (intValue2 & 16) > 0;
        float floatValue = bluetoothGattCharacteristic.getFloatValue(50, i4).floatValue();
        int i5 = i4 + 2;
        int intValue3 = bluetoothGattCharacteristic.getIntValue(18, i5).intValue();
        int i6 = i5 + 2;
        sb.append("Glucose concentration: ");
        sb.append(floatValue);
        sb.append(" mg/dL\n");
        sb.append("Sequence number: ");
        sb.append(intValue3);
        sb.append(" (Time Offset in min)\n");
        if (z3) {
            int i7 = i6 + 1;
            int intValue4 = bluetoothGattCharacteristic.getIntValue(17, i6).intValue();
            sb.append("Warnings:\n");
            if ((intValue4 & 1) > 0) {
                sb.append("- Session Stopped\n");
            }
            if ((intValue4 & 2) > 0) {
                sb.append("- Device Battery Low\n");
            }
            if ((intValue4 & 4) > 0) {
                sb.append("- Sensor Type Incorrect\n");
            }
            if ((intValue4 & 8) > 0) {
                sb.append("- Sensor Malfunction\n");
            }
            if ((intValue4 & 16) > 0) {
                sb.append("- Device Specific Alert\n");
            }
            if ((intValue4 & 32) > 0) {
                sb.append("- General Device Fault\n");
            }
            i6 = i7;
        }
        if (z4) {
            int i8 = i6 + 1;
            int intValue5 = bluetoothGattCharacteristic.getIntValue(17, i6).intValue();
            sb.append("Cal/Temp Info:\n");
            if ((intValue5 & 256) > 0) {
                sb.append("- Time Synchronization Required\n");
            }
            if ((intValue5 & 512) > 0) {
                sb.append("- Calibration Not Allowed\n");
            }
            if ((intValue5 & 1024) > 0) {
                sb.append("- Calibration Recommended\n");
            }
            if ((intValue5 & 2048) > 0) {
                sb.append("- Calibration Required\n");
            }
            if ((intValue5 & 4096) > 0) {
                sb.append("- Sensor Temp Too High\n");
            }
            if ((intValue5 & 8192) > 0) {
                sb.append("- Sensor Temp Too Low\n");
            }
            i6 = i8;
        }
        if (z5) {
            i2 = i6 + 1;
            int intValue6 = bluetoothGattCharacteristic.getIntValue(17, i6).intValue();
            sb.append("Status:\n");
            if ((SSA_RESULT_LOWER_THAN_PATIENT_LOW_LEVEL & intValue6) > 0) {
                sb.append("- Result Lower then Patient Low Level\n");
            }
            if ((SSA_RESULT_HIGHER_THAN_PATIENT_HIGH_LEVEL & intValue6) > 0) {
                sb.append("- Result Higher then Patient High Level\n");
            }
            if ((SSA_RESULT_LOWER_THAN_HYPO_LEVEL & intValue6) > 0) {
                sb.append("- Result Lower then Hypo Level\n");
            }
            if ((SSA_RESULT_HIGHER_THAN_HYPER_LEVEL & intValue6) > 0) {
                sb.append("- Result Higher then Hyper Level\n");
            }
            if ((1048576 & intValue6) > 0) {
                sb.append("- Sensor Rate of Decrease Exceeded\n");
            }
            if ((SSA_SENSOR_RATE_OF_INCREASE_EXCEEDED & intValue6) > 0) {
                sb.append("- Sensor Rate of Increase Exceeded\n");
            }
            if ((SSA_RESULT_LOWER_THAN_DEVICE_CAN_PROCESS & intValue6) > 0) {
                sb.append("- Result Lower then Device Can Process\n");
            }
            if ((intValue6 & SSA_RESULT_HIGHER_THAN_DEVICE_CAN_PROCESS) > 0) {
                sb.append("- Result Higher then Device Can Process\n");
            }
        } else {
            i2 = i6;
        }
        if (z) {
            float floatValue2 = bluetoothGattCharacteristic.getFloatValue(50, i2).floatValue();
            i2 += 2;
            sb.append("Trend: ");
            sb.append(floatValue2);
            sb.append(" mg/dL/min\n");
        }
        if (z2) {
            float floatValue3 = bluetoothGattCharacteristic.getFloatValue(50, i2).floatValue();
            i2 += 2;
            sb.append("Quality: ");
            sb.append(floatValue3);
            sb.append("%\n");
        }
        if (intValue > i2 + 1) {
            sb.append(String.format(Locale.US, "E2E-CRC: 0x%04X\n", Integer.valueOf(bluetoothGattCharacteristic.getIntValue(18, i2).intValue())));
        }
        sb.setLength(sb.length() - 1);
        return intValue;
    }

    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic);
    }

    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        int length = bluetoothGattCharacteristic.getValue().length;
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < length) {
            i += parseRecord(sb, bluetoothGattCharacteristic, i);
            if (i < length) {
                sb.append("\n\n");
            }
        }
        return sb.toString();
    }
}
