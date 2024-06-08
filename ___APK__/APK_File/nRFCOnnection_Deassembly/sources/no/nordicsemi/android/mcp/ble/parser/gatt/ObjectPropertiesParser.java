package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class ObjectPropertiesParser implements ICharacteristicParser {
    private static final int BIT_APPEND = 16;
    private static final int BIT_DELETE = 1;
    private static final int BIT_EXECUTE = 2;
    private static final int BIT_MARK = 128;
    private static final int BIT_PATCH = 64;
    private static final int BIT_READ = 4;
    private static final int BIT_RFU = 65520;
    private static final int BIT_TRUNCATE = 32;
    private static final int BIT_WRITE = 8;

    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic);
    }

    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (bluetoothGattCharacteristic.getValue().length != 4) {
            return "Incorrect data length (4 bytes expected): " + GeneralCharacteristicParser.parse(bluetoothGattCharacteristic);
        }
        int intValue = bluetoothGattCharacteristic.getIntValue(20, 0).intValue();
        StringBuilder sb = new StringBuilder();
        sb.append("Permitted operations:\n");
        if ((intValue & 1) > 0) {
            sb.append("- DELETE\n");
        }
        if ((intValue & 2) > 0) {
            sb.append("- EXECUTE\n");
        }
        if ((intValue & 4) > 0) {
            sb.append("- READ\n");
        }
        if ((intValue & 8) > 0) {
            sb.append("- WRITE\n");
        }
        if ((intValue & 16) > 0) {
            sb.append("- APPEND\n");
        }
        if ((intValue & 32) > 0) {
            sb.append("- TRUNCATE\n");
        }
        if ((intValue & 64) > 0) {
            sb.append("- PATCH\n");
        }
        if (sb.length() < 24) {
            sb.append("- No operations permitted\n");
        }
        if ((intValue & 128) > 0) {
            sb.append("This object is marked\n");
        }
        if ((BIT_RFU & intValue) > 0) {
            sb.append("Reserved bits set: 0x");
            sb.append(Integer.toHexString(intValue));
            sb.append("\n");
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }
}
