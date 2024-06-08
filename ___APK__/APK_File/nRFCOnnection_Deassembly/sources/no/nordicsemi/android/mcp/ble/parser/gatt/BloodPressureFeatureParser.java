package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import java.util.Locale;
import no.nordicsemi.android.mcp.ble.parser.utils.ParserUtils;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class BloodPressureFeatureParser implements ICharacteristicParser {
    private static final int BODY_MOVEMENT_DETECTION_SUPPORT_BIT = 1;
    private static final int CUFF_FIT_DETECTION_SUPPORT_BIT = 2;
    private static final int IRREGULAR_PULSE_DETECTION_SUPPORT_BIT = 4;
    private static final int MEASUREMENT_POSITION_DETECTION_SUPPORT_BIT = 16;
    private static final int MULTIPLE_BOND_SUPPORT_BIT = 32;
    private static final int PULSE_RATE_RANGE_DETECTION_SUPPORT_BIT = 8;
    private static final int RFU = 65472;

    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic);
    }

    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (bluetoothGattCharacteristic.getValue().length != 2) {
            return "Invalid value: " + GeneralCharacteristicParser.parse(bluetoothGattCharacteristic);
        }
        StringBuilder sb = new StringBuilder();
        int intOrThrow = ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(18, 0));
        if ((intOrThrow & 1) > 0) {
            sb.append("Body Movement Detection feature supported\n");
        }
        if ((intOrThrow & 2) > 0) {
            sb.append("Cuff Fit Detection feature supported\n");
        }
        if ((intOrThrow & 4) > 0) {
            sb.append("Irregular Pulse Detection feature supported\n");
        }
        if ((intOrThrow & 8) > 0) {
            sb.append("Pulse Rate Range Detection feature supported\n");
        }
        if ((intOrThrow & 16) > 0) {
            sb.append("Measurement Position Detection feature supported\n");
        }
        if ((intOrThrow & 32) > 0) {
            sb.append("Multiple Bonds supported\n");
        }
        int i = intOrThrow & RFU;
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
