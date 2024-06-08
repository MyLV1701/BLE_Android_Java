package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import no.nordicsemi.android.mcp.ble.parser.utils.ParserUtils;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class HIDInformationParser implements ICharacteristicParser {
    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic);
    }

    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        int intOrThrow = ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(18, 0));
        int intOrThrow2 = ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(17, 2));
        int intOrThrow3 = ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(17, 3));
        StringBuilder sb = new StringBuilder();
        sb.append("Base USB HID Specification version: ");
        sb.append(intOrThrow);
        sb.append(" (0x");
        sb.append(Integer.toHexString(intOrThrow));
        sb.append(")");
        sb.append("\nCountry: ");
        sb.append(intOrThrow2);
        if (intOrThrow3 > 0) {
            sb.append("\nFlags: ");
        }
        if ((intOrThrow3 & 1) > 0) {
            sb.append("RemoteWake, ");
        }
        if ((intOrThrow3 & 2) > 0) {
            sb.append("Normally Connectible, ");
        }
        if (intOrThrow3 > 0) {
            sb.setLength(sb.length() - 2);
        }
        return sb.toString();
    }
}
