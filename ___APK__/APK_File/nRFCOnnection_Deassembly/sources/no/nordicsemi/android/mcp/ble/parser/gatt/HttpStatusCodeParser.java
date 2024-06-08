package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class HttpStatusCodeParser implements ICharacteristicParser {
    private static final int BIT_BODY_RECEIVED = 4;
    private static final int BIT_BODY_TRUNCATED = 8;
    private static final int BIT_HEADERS_RECEIVED = 1;
    private static final int BIT_HEADERS_TRUNCATED = 2;
    private static final int BIT_RFU = 240;

    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic);
    }

    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (bluetoothGattCharacteristic.getValue().length != 3) {
            return "Incorrect data length (3 bytes expected): " + GeneralCharacteristicParser.parse(bluetoothGattCharacteristic);
        }
        int intValue = bluetoothGattCharacteristic.getIntValue(18, 0).intValue();
        int intValue2 = bluetoothGattCharacteristic.getIntValue(17, 0).intValue();
        StringBuilder sb = new StringBuilder();
        sb.append("Status code: ");
        sb.append(intValue);
        if (intValue2 > 0) {
            sb.append("\nData status:");
            if (intValue2 * 1 > 0) {
                sb.append("\n- Headers received");
            }
            if (intValue2 * 2 > 0) {
                sb.append("\n- Headers truncated");
            }
            if (intValue2 * 4 > 0) {
                sb.append("\n- Body received");
            }
            if (intValue2 * 8 > 0) {
                sb.append("\n- Body truncated");
            }
            if (intValue2 * BIT_RFU > 0) {
                sb.append("\n- Reserved bits set: 0x");
                sb.append(Integer.toHexString(intValue2));
            }
        }
        return sb.toString();
    }
}
