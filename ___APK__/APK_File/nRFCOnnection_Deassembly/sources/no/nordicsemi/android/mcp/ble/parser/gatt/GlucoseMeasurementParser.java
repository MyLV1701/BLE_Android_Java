package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import no.nordicsemi.android.mcp.ble.parser.utils.ParserUtils;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class GlucoseMeasurementParser implements ICharacteristicParser {
    private static final int STATUS_DEVICE_BATTERY_LOW = 1;
    private static final int STATUS_GENERAL_DEVICE_FAULT = 1024;
    private static final int STATUS_SAMPLE_SIZE_FOR_BLOOD_OR_CONTROL_SOLUTION_INSUFFICIENT = 4;
    private static final int STATUS_SENSOR_MALFUNCTION = 2;
    private static final int STATUS_SENSOR_READ_INTERRUPTED = 512;
    private static final int STATUS_SENSOR_RESULT_TOO_HIGH = 32;
    private static final int STATUS_SENSOR_RESULT_TOO_LOW = 64;
    private static final int STATUS_SENSOR_TEMPERATURE_TOO_HIGH = 128;
    private static final int STATUS_SENSOR_TEMPERATURE_TOO_LOW = 256;
    private static final int STATUS_STRIP_INSERTION_ERROR = 8;
    private static final int STATUS_STRIP_TYPE_INCORRECT_FOR_DEVICE = 16;
    private static final int STATUS_TIME_FAULT = 2048;
    private static final int UNIT_kgpl = 0;
    private static final int UNIT_molpl = 1;

    private static String getLocation(int i) {
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
        if (i == 15) {
            return "Value not available";
        }
        return "Reserved for future use (" + i + ")";
    }

    private static String getStatusAnnunciation(int i) {
        StringBuilder sb = new StringBuilder();
        if ((i & 1) > 0) {
            sb.append("\nDevice battery low at time of measurement");
        }
        if ((i & 2) > 0) {
            sb.append("\nSensor malfunction or faulting at time of measurement");
        }
        if ((i & 4) > 0) {
            sb.append("\nSample size for blood or control solution insufficient at time of measurement");
        }
        if ((i & 8) > 0) {
            sb.append("\nStrip insertion error");
        }
        if ((i & 16) > 0) {
            sb.append("\nStrip type incorrect for device");
        }
        if ((i & 32) > 0) {
            sb.append("\nSensor result higher than the device can process");
        }
        if ((i & 64) > 0) {
            sb.append("\nSensor result lower than the device can process");
        }
        if ((i & 128) > 0) {
            sb.append("\nSensor temperature too high for valid test/result at time of measurement");
        }
        if ((i & 256) > 0) {
            sb.append("\nSensor temperature too low for valid test/result at time of measurement");
        }
        if ((i & 512) > 0) {
            sb.append("\nSensor read interrupted because strip was pulled too soon at time of measurement");
        }
        if ((i & 1024) > 0) {
            sb.append("\nGeneral device fault has occurred in the sensor");
        }
        if ((i & 2048) > 0) {
            sb.append("\nTime fault has occurred in the sensor and time may be inaccurate");
        }
        return sb.toString();
    }

    private static String getType(int i) {
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
        StringBuilder sb = new StringBuilder();
        int intOrThrow = ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(17, 0));
        boolean z = (intOrThrow & 1) > 0;
        boolean z2 = (intOrThrow & 2) > 0;
        boolean z3 = (intOrThrow & 4) > 0;
        boolean z4 = (intOrThrow & 8) > 0;
        boolean z5 = (intOrThrow & 16) > 0;
        int intOrThrow2 = ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(18, 1));
        sb.append("Sequence Number: ");
        sb.append(intOrThrow2);
        sb.append("\nBase Time: ");
        sb.append(DateTimeParser.parse(bluetoothGattCharacteristic, 3));
        int i = 10;
        if (z) {
            int intOrThrow3 = ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(34, 10));
            sb.append("\nTime Offset: ");
            sb.append(intOrThrow3);
            sb.append(" min");
            i = 12;
        }
        if (z2) {
            float floatOrThrow = ParserUtils.floatOrThrow(bluetoothGattCharacteristic.getFloatValue(50, i));
            int intOrThrow4 = ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(17, i + 2));
            int i2 = (intOrThrow4 & 15) >> 4;
            int i3 = intOrThrow4 & 240;
            sb.append("\nGlucose Concentration: ");
            sb.append(floatOrThrow);
            sb.append(!z3 ? " kg/l" : " mol/l");
            sb.append("\nSample Type: ");
            sb.append(getType(i2));
            sb.append("\nSample Location: ");
            sb.append(getLocation(i3));
            i += 3;
        }
        if (z4) {
            int intOrThrow5 = ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(18, i));
            sb.append("Status:\n");
            sb.append(getStatusAnnunciation(intOrThrow5));
        }
        sb.append("\nContext information follows: ");
        sb.append(z5);
        return sb.toString();
    }
}
