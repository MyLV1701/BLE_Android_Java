package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class WeightScaleFeatureParser implements ICharacteristicParser {
    private static final int BMI_SUPPORTED = 4;
    private static final int HEIGHT_MEASUREMENT_RESOLUTION = 896;
    private static final int MULTIPLE_USERS_SUPPORTED = 2;
    private static final int TIMESTAMP_SUPPORTED = 1;
    private static final int WEIGHT_MEASUREMENT_RESOLUTION = 120;

    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic);
    }

    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (bluetoothGattCharacteristic.getValue().length != 4) {
            return "Incorrect data length (4 bytes expected): " + GeneralCharacteristicParser.parse(bluetoothGattCharacteristic);
        }
        int intValue = bluetoothGattCharacteristic.getIntValue(20, 0).intValue();
        boolean z = (intValue & 1) > 0;
        boolean z2 = (intValue & 2) > 0;
        boolean z3 = (intValue & 4) > 0;
        int i = (intValue & 120) >> 3;
        int i2 = (intValue & 896) >> 7;
        StringBuilder sb = new StringBuilder();
        sb.append("Flags:\n");
        if (z) {
            sb.append("Timestamp Supported\n");
        }
        if (z2) {
            sb.append("Multiple Users Supported\n");
        }
        if (z3) {
            sb.append("BMI Supported\n");
        }
        sb.append("Weight Measurement Resolution: ");
        switch (i) {
            case 0:
                sb.append("Not Specified\n");
                break;
            case 1:
                sb.append("0.5 kg or 1 lb\n");
                break;
            case 2:
                sb.append("0.2 kg or 0.5 lb\n");
                break;
            case 3:
                sb.append("0.1 kg or 0.2 lb\n");
                break;
            case 4:
                sb.append("0.05 kg or 0.1 lb\n");
                break;
            case 5:
                sb.append("0.02 kg or 0.05 lb\n");
                break;
            case 6:
                sb.append("0.01 kg or 0.02 lb\n");
                break;
            case 7:
                sb.append("0.005 kg or 0.01 lb\n");
                break;
            default:
                sb.append("Reserved for future use\n");
                break;
        }
        sb.append("Height Measurement Resolution: ");
        if (i2 == 0) {
            sb.append("Not Specified\n");
        } else if (i2 == 1) {
            sb.append("0.01 meter or 1 inch\n");
        } else if (i2 == 2) {
            sb.append("0.005 meter or 0.5 inch\n");
        } else if (i2 != 3) {
            sb.append("Reserved for future use\n");
        } else {
            sb.append("0.001 meter or 0.1 inch\n");
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }
}
