package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import java.util.Locale;
import no.nordicsemi.android.dfu.DfuBaseService;
import no.nordicsemi.android.mcp.ble.parser.utils.ParserUtils;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class FitnessMachineFeatureParser implements ICharacteristicParser {
    private static final int AVERAGE_SPEED_SUPPORTED = 1;
    private static final int CADENCE_SUPPORTED = 2;
    private static final int ELAPSED_TIME_SUPPORTED = 4096;
    private static final int ELEVATION_GAIN_SUPPORTED = 16;
    private static final int EXPENDED_ENERGY_SUPPORTED = 512;
    private static final int FITNESS_MACHINE_RFU = -131072;
    private static final int FORCE_ON_BELT_AND_POWER_OUTPUT_SUPPORTED = 32768;
    private static final int HEART_RATE_MEASUREMENT_SUPPORTED = 1024;
    private static final int HEART_RATE_TARGET_SETTING_SUPPORTED = 16;
    private static final int INCLINATION_SUPPORTED = 8;
    private static final int INCLINATION_TARGET_SETTING_SUPPORTED = 2;
    private static final int INDOOR_BIKE_SIMULATION_PARAMETERS_SUPPORTED = 8192;
    private static final int METABOLIC_EQUIVALENT_SUPPORTED = 2048;
    private static final int PACE_SUPPORTED = 32;
    private static final int POWER_MEASUREMENT_SUPPORTED = 16384;
    private static final int POWER_TARGET_SETTING_SUPPORTED = 8;
    private static final int REMAINING_TIME_SUPPORTED = 8192;
    private static final int RESISTANCE_LEVEL_SUPPORTED = 128;
    private static final int RESISTANCE_TARGET_SETTING_SUPPORTED = 4;
    private static final int SPEED_TARGET_SETTING_SUPPORTED = 1;
    private static final int SPIN_DOWN_CONTROL_SUPPORTED = 32768;
    private static final int STEP_COUNT_SUPPORTED = 64;
    private static final int STRIDE_COUNT_SUPPORTED = 256;
    private static final int TARGETED_CADENCE_CONFIGURATION_SUPPORTED = 65536;
    private static final int TARGETED_DISTANCE_CONFIGURATION_SUPPORTED = 256;
    private static final int TARGETED_EXPENDED_ENERGY_CONFIGURATION_SUPPORTED = 32;
    private static final int TARGETED_STEP_NUMBER_CONFIGURATION_SUPPORTED = 64;
    private static final int TARGETED_STRIDE_NUMBER_CONFIGURATION_SUPPORTED = 128;
    private static final int TARGETED_TIME_IN_FIVE_HEART_RATE_ZONES_CONFIGURATION_SUPPORTED = 4096;
    private static final int TARGETED_TIME_IN_THREE_HEART_RATE_ZONES_CONFIGURATION_SUPPORTED = 2048;
    private static final int TARGETED_TIME_IN_TWO_HEART_RATE_ZONES_CONFIGURATION_SUPPORTED = 1024;
    private static final int TARGETED_TRAINING_TIME_CONFIGURATION_SUPPORTED = 512;
    private static final int TARGET_SETTING_RFU = -131072;
    private static final int TOTAL_DISTANCE_SUPPORTED = 4;
    private static final int USER_DATA_RETENTION_SUPPORTED = 65536;
    private static final int WHEEL_CIRCUMFERENCE_CONFIGURATION_SUPPORTED = 16384;

    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic);
    }

    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (bluetoothGattCharacteristic.getValue().length != 8) {
            return "Invalid value: " + GeneralCharacteristicParser.parse(bluetoothGattCharacteristic);
        }
        StringBuilder sb = new StringBuilder();
        int intOrThrow = ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(20, 0));
        sb.append("Fitness Machine Features:\n");
        if ((intOrThrow & 1) > 0) {
            sb.append("Average Speed Supported\n");
        }
        if ((intOrThrow & 2) > 0) {
            sb.append("Cadence Supported\n");
        }
        if ((intOrThrow & 4) > 0) {
            sb.append("Total Distance Supported\n");
        }
        if ((intOrThrow & 8) > 0) {
            sb.append("Inclination Supported\n");
        }
        if ((intOrThrow & 16) > 0) {
            sb.append("Elevation Gain Supported\n");
        }
        if ((intOrThrow & 32) > 0) {
            sb.append("Pace Supported\n");
        }
        if ((intOrThrow & 64) > 0) {
            sb.append("Step Count Supported\n");
        }
        if ((intOrThrow & 128) > 0) {
            sb.append("Resistance Level Supported\n");
        }
        if ((intOrThrow & 256) > 0) {
            sb.append("Stride Count Supported\n");
        }
        if ((intOrThrow & DfuBaseService.ERROR_REMOTE_TYPE_SECURE) > 0) {
            sb.append("Expended Energy Supported\n");
        }
        if ((intOrThrow & 1024) > 0) {
            sb.append("Heart Rate Measurement Supported\n");
        }
        if ((intOrThrow & DfuBaseService.ERROR_REMOTE_TYPE_SECURE_BUTTONLESS) > 0) {
            sb.append("Metabolic Equivalent Supported\n");
        }
        if ((intOrThrow & 4096) > 0) {
            sb.append("Elapsed Time Supported\n");
        }
        if ((intOrThrow & DfuBaseService.ERROR_REMOTE_MASK) > 0) {
            sb.append("Remaining Time Supported\n");
        }
        if ((intOrThrow & DfuBaseService.ERROR_CONNECTION_MASK) > 0) {
            sb.append("Power Measurement Supported\n");
        }
        if ((32768 & intOrThrow) > 0) {
            sb.append("Force on Belt and Power Output Supported\n");
        }
        if ((65536 & intOrThrow) > 0) {
            sb.append("User Data Retention Supported\n");
        }
        int i = (-131072) & intOrThrow;
        if (i != 0) {
            sb.append(String.format(Locale.US, "Reserved for Future Use: 0x%08X\n", Integer.valueOf(i)));
        }
        if (intOrThrow == 0) {
            sb.append("No features supported\n");
        }
        sb.append("\nTarget Setting Features:\n");
        int intOrThrow2 = ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(20, 4));
        if ((intOrThrow2 & 1) > 0) {
            sb.append("Speed Target Setting Supported\n");
        }
        if ((intOrThrow2 & 2) > 0) {
            sb.append("Inclination Target Setting Supported\n");
        }
        if ((intOrThrow2 & 4) > 0) {
            sb.append("Resistance Target Setting Supported\n");
        }
        if ((intOrThrow2 & 8) > 0) {
            sb.append("Power Target Setting Supported\n");
        }
        if ((intOrThrow2 & 16) > 0) {
            sb.append("Heart Rate Target Setting Supported\n");
        }
        if ((intOrThrow2 & 32) > 0) {
            sb.append("Targeted Expended Energy Configuration Supported\n");
        }
        if ((intOrThrow2 & 64) > 0) {
            sb.append("Targeted Step Number Configuration Supported\n");
        }
        if ((intOrThrow2 & 128) > 0) {
            sb.append("Targeted Stride Number Configuration Supported\n");
        }
        if ((intOrThrow2 & 256) > 0) {
            sb.append("Targeted Distance Configuration Supported\n");
        }
        if ((intOrThrow2 & DfuBaseService.ERROR_REMOTE_TYPE_SECURE) > 0) {
            sb.append("Targeted Training Time Configuration Supported\n");
        }
        if ((intOrThrow2 & 1024) > 0) {
            sb.append("Targeted Time in Two Heart Rate Zones Configuration Supported\n");
        }
        if ((intOrThrow2 & DfuBaseService.ERROR_REMOTE_TYPE_SECURE_BUTTONLESS) > 0) {
            sb.append("Targeted Time in Three Heart Rate Zones Configuration Supported\n");
        }
        if ((intOrThrow2 & 4096) > 0) {
            sb.append("Targeted Time in Five Heart Rate Zones Configuration Supported\n");
        }
        if ((intOrThrow2 & DfuBaseService.ERROR_REMOTE_MASK) > 0) {
            sb.append("Indoor Bike Simulation Parameters Supported\n");
        }
        if ((intOrThrow2 & DfuBaseService.ERROR_CONNECTION_MASK) > 0) {
            sb.append("Wheel Circumference Configuration Supported\n");
        }
        if ((32768 & intOrThrow2) > 0) {
            sb.append("Spin Down Control Supported\n");
        }
        if ((65536 & intOrThrow2) > 0) {
            sb.append("Targeted Cadence Configuration Supported\n");
        }
        int i2 = (-131072) & intOrThrow2;
        if (i2 != 0) {
            sb.append(String.format(Locale.US, "Reserved for Future Use: 0x%08X\n", Integer.valueOf(i2)));
        }
        if (intOrThrow2 == 0) {
            sb.append("No features supported\n");
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }
}
