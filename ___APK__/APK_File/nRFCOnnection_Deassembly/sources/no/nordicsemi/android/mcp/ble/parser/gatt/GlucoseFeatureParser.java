package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import java.util.Locale;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class GlucoseFeatureParser implements ICharacteristicParser {
    private static final int GENERAL_DEVICE_FAULT_SUPPORTED_BIT = 256;
    private static final int LOW_BATTERY_DETECTION_DURING_MEASUREMENT_SUPPORTED_BIT = 1;
    private static final int MULTIPLE_BOND_SUPPORTED_BIT = 1024;
    private static final int RFU = 63488;
    private static final int SENSOR_MALFUNCTION_DETECTION_SUPPORTED_BIT = 2;
    private static final int SENSOR_READ_INTERRUPT_DETECTION_SUPPORTED_BIT = 128;
    private static final int SENSOR_RESULT_HIGH_LOW_DETECTION_SUPPORTED_BIT = 32;
    private static final int SENSOR_SAMPLE_SIZE_SUPPORTED_BIT = 4;
    private static final int SENSOR_STRIP_INSERTION_ERROR_DETECTION_SUPPORTED_BIT = 8;
    private static final int SENSOR_STRIP_TYPE_ERROR_DETECTION_SUPPORTED_BIT = 16;
    private static final int SENSOR_TEMPERATURE_HIGH_LOW_DETECTION_SUPPORTED_BIT = 64;
    private static final int TIME_FAULT_SUPPORTED_BIT = 512;

    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic);
    }

    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (bluetoothGattCharacteristic.getValue().length != 2) {
            return "Invalid value: " + GeneralCharacteristicParser.parse(bluetoothGattCharacteristic);
        }
        StringBuilder sb = new StringBuilder();
        int intValue = bluetoothGattCharacteristic.getIntValue(18, 0).intValue();
        if ((intValue & 1) > 0) {
            sb.append("Low Battery Detection During Measurement Supported\n");
        }
        if ((intValue & 2) > 0) {
            sb.append("Sensor Malfunction Detection Supported\n");
        }
        if ((intValue & 4) > 0) {
            sb.append("Sensor Sample Size Supported\n");
        }
        if ((intValue & 8) > 0) {
            sb.append("Sensor Strip Insertion Error Detection Supported\n");
        }
        if ((intValue & 16) > 0) {
            sb.append("Sensor Strip Type Error Detection Supported\n");
        }
        if ((intValue & 32) > 0) {
            sb.append("Sensor Result High-Low Detection Supported\n");
        }
        if ((intValue & 64) > 0) {
            sb.append("Sensor Temperature High-Low Detection Supported\n");
        }
        if ((intValue & 128) > 0) {
            sb.append("Sensor Read Interrupt Detection Supported\n");
        }
        if ((intValue & 256) > 0) {
            sb.append("General Device Fault Supported\n");
        }
        if ((intValue & 512) > 0) {
            sb.append("Time Fault Supported\n");
        }
        if ((intValue & 1024) > 0) {
            sb.append("Multiple Bond Supported\n");
        }
        int i = intValue & RFU;
        if (i != 0) {
            sb.append(String.format(Locale.US, "Reserved for Future Use: 0x%04X\n", Integer.valueOf(i)));
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1);
        } else {
            sb.append("No features supported");
        }
        return sb.toString();
    }
}
