package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import java.util.Locale;
import no.nordicsemi.android.mcp.ble.parser.utils.ParserUtils;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class CGMFeatureParser implements ICharacteristicParser {
    private static final int CALIBRATION_SUPPORTED = 1;
    private static final int CGM_QUALITY_SUPPORTED = 65536;
    private static final int CGM_TREND_INFORMATION_SUPPORTED = 32768;
    private static final int DEVICE_SPECIFIC_ALERT_SUPPORTED = 32;
    private static final int E2E_CRC_SUPPORTED = 4096;
    private static final int GENERAL_DEVICE_FAULT_SUPPORTED = 2048;
    private static final int HYPER_ALERTS_SUPPORTED = 8;
    private static final int HYPO_ALERTS_SUPPORTED = 4;
    private static final int LOW_BATTERY_DETECTION_SUPPORTED = 512;
    private static final int MULTIPLE_BOND_SUPPORTED = 8192;
    private static final int MULTIPLE_SESSIONS_SUPPORTED = 16384;
    private static final int PATIENT_HIGH_LOW_ALERTS_SUPPORTED = 2;
    private static final int RATE_OF_INCREASE_DECREASE_ALERTS_SUPPORTED = 16;
    private static final int SENSOR_MALFUNCTION_DETECTION_SUPPORTED = 64;
    private static final int SENSOR_RESULT_HIGH_LOW_DETECTION_SUPPORTED = 256;
    private static final int SENSOR_TEMPERATURE_HIGH_LOW_DETECTION_SUPPORTED = 128;
    private static final int SENSOR_TYPE_ERROR_DETECTION_SUPPORTED = 1024;

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

    private static String parseType(int i) {
        switch (i) {
            case 1:
                return "Capillary Whole blood";
            case 2:
                return "Capillary Plasma";
            case 3:
                return "Venous Whole blood";
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
        int intValue = ParserUtils.getIntValue(bluetoothGattCharacteristic.getValue(), 19, 0);
        StringBuilder sb = new StringBuilder();
        sb.append("CGM Features:");
        if ((intValue & 1) > 0) {
            sb.append("\n- Calibration Supported");
        }
        if ((intValue & 2) > 0) {
            sb.append("\n- Patient High-low Alerts Supported");
        }
        if ((intValue & 4) > 0) {
            sb.append("\n- Hypo Alerts Supported");
        }
        if ((intValue & 8) > 0) {
            sb.append("\n- Hyper Alerts Supported");
        }
        if ((intValue & 16) > 0) {
            sb.append("\n- Rate Of Increase/decrease Alerts Supported");
        }
        if ((intValue & 32) > 0) {
            sb.append("\n- Device Specific Alert Supported");
        }
        if ((intValue & 64) > 0) {
            sb.append("\n- Sensor Malfunction Detection Supported");
        }
        if ((intValue & 128) > 0) {
            sb.append("\n- Sensor Temperature High-low Detection Supported");
        }
        if ((intValue & 256) > 0) {
            sb.append("\n- Sensor Result High-low Detection Supported");
        }
        if ((intValue & 512) > 0) {
            sb.append("\n- Low Battery Detection Supported");
        }
        if ((intValue & 1024) > 0) {
            sb.append("\n- Sensor Type Error Detection Supported");
        }
        if ((intValue & 2048) > 0) {
            sb.append("\n- General Device Fault Supported");
        }
        if ((intValue & 4096) > 0) {
            sb.append("\n- E2E-CRC Supported");
        }
        if ((intValue & 8192) > 0) {
            sb.append("\n- Multiple Bond Supported");
        }
        if ((intValue & 16384) > 0) {
            sb.append("\n- Multiple Sessions Supported");
        }
        if ((32768 & intValue) > 0) {
            sb.append("\n- Cgm Trend Information Supported");
        }
        if ((CGM_QUALITY_SUPPORTED & intValue) > 0) {
            sb.append("\n- Cgm Quality Supported");
        }
        if ((16646144 & intValue) > 0) {
            sb.append("\n- Reserved (");
            sb.append(intValue);
            sb.append(")");
        }
        if (intValue == 0) {
            sb.append(" None");
        }
        int intValue2 = bluetoothGattCharacteristic.getIntValue(17, 3).intValue();
        sb.append("\nType: ");
        sb.append(parseType(intValue2 & 15));
        sb.append("\nSample Location: ");
        sb.append(parseSampleLocation((intValue2 & 240) >> 4));
        if (bluetoothGattCharacteristic.getValue().length >= 6) {
            sb.append(String.format(Locale.US, "\nE2E-CRC: %04X", Integer.valueOf(bluetoothGattCharacteristic.getIntValue(18, 4).intValue())));
        }
        return sb.toString();
    }
}
