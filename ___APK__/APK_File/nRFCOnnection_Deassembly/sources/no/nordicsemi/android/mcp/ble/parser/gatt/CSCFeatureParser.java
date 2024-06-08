package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import java.util.Locale;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class CSCFeatureParser implements ICharacteristicParser {
    private static final byte CRANK_REV_DATA_SUPPORTED = 2;
    private static final byte MULTIPLE_SENSOR_LOCATIONS_SUPPORTED = 4;
    private static final byte WHEEL_REV_DATA_SUPPORTED = 1;

    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic);
    }

    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (bluetoothGattCharacteristic.getValue().length != 2) {
            return "Invalid value: " + GeneralCharacteristicParser.parse(bluetoothGattCharacteristic);
        }
        int intValue = bluetoothGattCharacteristic.getIntValue(18, 0).intValue();
        boolean z = (intValue & 1) > 0;
        boolean z2 = (intValue & 2) > 0;
        boolean z3 = (intValue & 4) > 0;
        StringBuilder sb = new StringBuilder();
        sb.append("Flags:\n");
        if (z) {
            sb.append("Wheel Revolution Data Supported\n");
        }
        if (z2) {
            sb.append("Crank Revolution Data Supported\n");
        }
        if (z3) {
            sb.append("Multiple Sensor Locations Supported\n");
        }
        if (intValue == 0) {
            sb.append("No flags\n");
        }
        if ((65528 & intValue) > 0) {
            sb.append(String.format(Locale.US, "Unknown flags: %04X\n", Integer.valueOf(intValue)));
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }
}
