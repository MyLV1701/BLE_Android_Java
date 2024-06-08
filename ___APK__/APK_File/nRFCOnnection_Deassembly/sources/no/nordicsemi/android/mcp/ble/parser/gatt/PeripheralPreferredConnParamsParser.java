package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import java.util.Locale;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class PeripheralPreferredConnParamsParser implements ICharacteristicParser {
    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic);
    }

    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (bluetoothGattCharacteristic.getValue().length != 8) {
            return "Incorrect data length (8 bytes expected): " + GeneralCharacteristicParser.parse(bluetoothGattCharacteristic);
        }
        int intValue = bluetoothGattCharacteristic.getIntValue(18, 0).intValue();
        int intValue2 = bluetoothGattCharacteristic.getIntValue(18, 2).intValue();
        int intValue3 = bluetoothGattCharacteristic.getIntValue(18, 4).intValue();
        int intValue4 = bluetoothGattCharacteristic.getIntValue(18, 6).intValue();
        StringBuilder sb = new StringBuilder();
        sb.append("Connection Interval: ");
        if (intValue == 65535) {
            sb.append("no specific minimum");
        } else {
            sb.append(String.format(Locale.US, "%.02fms", Float.valueOf(intValue * 1.25f)));
        }
        sb.append(" - ");
        if (intValue2 == 65535) {
            sb.append("no specific maximum");
        } else {
            sb.append(String.format(Locale.US, "%.02fms", Float.valueOf(intValue2 * 1.25f)));
        }
        sb.append(",\nSlave Latency: ");
        if (intValue3 == 65535) {
            sb.append("undefined");
        } else {
            sb.append(intValue3);
        }
        sb.append(",\nSupervision Timeout Multiplier: ");
        if (intValue4 == 65535) {
            sb.append("no specific value requested");
        } else {
            sb.append(intValue4);
        }
        return sb.toString();
    }
}
