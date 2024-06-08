package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class RSCFeatureParser implements ICharacteristicParser {
    private static final byte CALIBRATION_PROCEDURE_SUPPORTED = 8;
    private static final byte INSTANTANEOUS_STRIDE_LENGTH_MEASUREMENT_SUPPORTED = 1;
    private static final byte MULTIPLE_SENSOR_LOCATIONS_SUPPORTED = 16;
    private static final byte TOTAL_DISTANCE_MEASUREMENT_SUPPORTED = 2;
    private static final byte WALKING_OR_RUNNING_STATUS_SUPPORTED = 4;

    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic);
    }

    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (bluetoothGattCharacteristic.getValue().length != 2) {
            return "Incorrect data length (2 bytes expected): " + GeneralCharacteristicParser.parse(bluetoothGattCharacteristic);
        }
        int intValue = bluetoothGattCharacteristic.getIntValue(18, 0).intValue();
        boolean z = (intValue & 1) > 0;
        boolean z2 = (intValue & 2) > 0;
        boolean z3 = (intValue & 4) > 0;
        boolean z4 = (intValue & 8) > 0;
        boolean z5 = (intValue & 16) > 0;
        StringBuilder sb = new StringBuilder();
        sb.append("Flags:\n");
        if (z) {
            sb.append("Instantaneous Stride Length Measurement Supported\n");
        }
        if (z2) {
            sb.append("Total Distance Measurement Supported\n");
        }
        if (z3) {
            sb.append("Walking or Running Status Supported\n");
        }
        if (z4) {
            sb.append("Calibration Procedure Supported\n");
        }
        if (z5) {
            sb.append("Multiple Sensor Locations Supported\n");
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }
}
