package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import java.util.Locale;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class CGMStatusParser implements ICharacteristicParser {
    private static final int SSA_CALIBRATION_NOT_ALLOWED = 2;
    private static final int SSA_CALIBRATION_RECOMMENDED = 4;
    private static final int SSA_CALIBRATION_REQUIRED = 4;
    private static final int SSA_DEVICE_BATTERY_LOW = 2;
    private static final int SSA_DEVICE_SPEC_ALERT = 16;
    private static final int SSA_GENERAL_DEVICE_FAULT = 32;
    private static final int SSA_RESULT_HIGHER_THAN_DEVICE_CAN_PROCESS = 128;
    private static final int SSA_RESULT_HIGHER_THAN_HYPER_LEVEL = 8;
    private static final int SSA_RESULT_HIGHER_THAN_PATIENT_HIGH_LEVEL = 2;
    private static final int SSA_RESULT_LOWER_THAN_DEVICE_CAN_PROCESS = 64;
    private static final int SSA_RESULT_LOWER_THAN_HYPO_LEVEL = 4;
    private static final int SSA_RESULT_LOWER_THAN_PATIENT_LOW_LEVEL = 1;
    private static final int SSA_SENSOR_MALFUNCTION = 8;
    private static final int SSA_SENSOR_RATE_OF_DECREASE_EXCEEDED = 16;
    private static final int SSA_SENSOR_RATE_OF_INCREASE_EXCEEDED = 32;
    private static final int SSA_SENSOR_TEMP_TOO_HIGH = 8;
    private static final int SSA_SENSOR_TEMP_TOO_LOW = 16;
    private static final int SSA_SENSOR_TYPE_INCORRECT = 4;
    private static final int SSA_SESSION_STOPPED = 1;
    private static final int SSA_TIME_SYNC_REQUIRED = 1;

    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic);
    }

    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        int intValue = bluetoothGattCharacteristic.getIntValue(18, 0).intValue();
        StringBuilder sb = new StringBuilder();
        sb.append("Time offset: ");
        sb.append(intValue);
        sb.append(" (minutes since Session Start Time)\n");
        int intValue2 = bluetoothGattCharacteristic.getIntValue(17, 2).intValue();
        if (intValue2 > 0) {
            sb.append("Warnings:\n");
        }
        if ((intValue2 & 1) > 0) {
            sb.append("- Session Stopped\n");
        }
        if ((intValue2 & 2) > 0) {
            sb.append("- Device Battery Low\n");
        }
        if ((intValue2 & 4) > 0) {
            sb.append("- Sensor Type Incorrect\n");
        }
        if ((intValue2 & 8) > 0) {
            sb.append("- Sensor Malfunction\n");
        }
        if ((intValue2 & 16) > 0) {
            sb.append("- Device Specific Alert\n");
        }
        if ((intValue2 & 32) > 0) {
            sb.append("- General Device Fault\n");
        }
        int intValue3 = bluetoothGattCharacteristic.getIntValue(17, 3).intValue();
        if (intValue3 > 0) {
            sb.append("Cal/Temp Info:\n");
        }
        if ((intValue3 & 1) > 0) {
            sb.append("- Time Synchronization Required\n");
        }
        if ((intValue3 & 2) > 0) {
            sb.append("- Calibration Not Allowed\n");
        }
        int i = intValue3 & 4;
        if (i > 0) {
            sb.append("- Calibration Recommended\n");
        }
        if (i > 0) {
            sb.append("- Calibration Required\n");
        }
        if ((intValue3 & 8) > 0) {
            sb.append("- Sensor Temp Too High\n");
        }
        if ((intValue3 & 16) > 0) {
            sb.append("- Sensor Temp Too Low\n");
        }
        int intValue4 = bluetoothGattCharacteristic.getIntValue(17, 4).intValue();
        if (intValue4 > 0) {
            sb.append("Status:\n");
        }
        if ((intValue4 & 1) > 0) {
            sb.append("- Result Lower then Patient Low Level\n");
        }
        if ((intValue4 & 2) > 0) {
            sb.append("- Result Higher then Patient High Level\n");
        }
        if ((intValue4 & 4) > 0) {
            sb.append("- Result Lower then Hypo Level\n");
        }
        if ((intValue4 & 8) > 0) {
            sb.append("- Result Higher then Hyper Level\n");
        }
        if ((intValue4 & 16) > 0) {
            sb.append("- Sensor Rate of Decrease Exceeded\n");
        }
        if ((intValue4 & 32) > 0) {
            sb.append("- Sensor Rate of Increase Exceeded\n");
        }
        if ((intValue4 & 64) > 0) {
            sb.append("- Result Lower then Device Can Process\n");
        }
        if ((intValue4 & 128) > 0) {
            sb.append("- Result Higher then Device Can Process\n");
        }
        if (bluetoothGattCharacteristic.getValue().length >= 7) {
            sb.append(String.format(Locale.US, "E2E-CRC: 0x%04X\n", Integer.valueOf(bluetoothGattCharacteristic.getIntValue(18, 5).intValue())));
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }
}
