package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class IndoorPositioningConfigurationParser implements ICharacteristicParser {
    private static final int ALTITUDE_IN_ADV_PRESENT = 8;
    private static final int COORDS_IN_ADV_PRESENT = 1;
    private static final int COORDS_TYPE_LOCAL = 1;
    private static final int COORDS_TYPE_WGS84 = 0;
    private static final int COORD_TYPE_IN_ADV_PRESENT = 2;
    private static final int FLOOR_NUMBRE_IN_ADV_PRESENT = 16;
    private static final int LOCATION_NAME_PRESENT = 32;
    private static final int TX_POWER_IN_ADV_PRESENT = 4;

    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic);
    }

    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (bluetoothGattCharacteristic.getValue().length != 1) {
            return "Incorrect data length (1 byte expected): " + GeneralCharacteristicParser.parse(bluetoothGattCharacteristic);
        }
        int intValue = bluetoothGattCharacteristic.getIntValue(17, 0).intValue();
        int i = intValue & 1;
        boolean z = i > 0;
        boolean z2 = (intValue & 4) > 0;
        boolean z3 = (intValue & 8) > 0;
        boolean z4 = (intValue & 16) > 0;
        boolean z5 = (intValue & 32) > 0;
        StringBuilder sb = new StringBuilder();
        sb.append("Flags:\n");
        if (z) {
            sb.append("Coordinates present in advertising packets\n");
        }
        sb.append("Coordinate system: ");
        if (i == 0) {
            sb.append("WGS84\n");
        } else {
            sb.append("local\n");
        }
        if (z2) {
            sb.append("Tx Power present in advertising packets\n");
        }
        if (z3) {
            sb.append("Altitude present in advertising packets\n");
        }
        if (z4) {
            sb.append("Floor Number present in advertising packets\n");
        }
        if (z5) {
            sb.append("Location Name present in GATT database\n");
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }
}
