package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import java.util.Locale;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class RCSettingsParser implements ICharacteristicParser {
    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic);
    }

    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        byte[] value = bluetoothGattCharacteristic.getValue();
        if (value.length < 3) {
            return "Invalid value: " + GeneralCharacteristicParser.parse(bluetoothGattCharacteristic);
        }
        int intValue = bluetoothGattCharacteristic.getIntValue(17, 0).intValue();
        if (intValue < 3) {
            return "Invalid length in: " + GeneralCharacteristicParser.parse(bluetoothGattCharacteristic);
        }
        StringBuilder sb = new StringBuilder();
        BaseValueParser.addFlag(sb, bluetoothGattCharacteristic, 1, 0, "RFU (bit 0)");
        BaseValueParser.addFlag(sb, bluetoothGattCharacteristic, 1, 1, "LESC Only");
        BaseValueParser.addFlag(sb, bluetoothGattCharacteristic, 1, 2, "Use OOB Pairing");
        BaseValueParser.addFlag(sb, bluetoothGattCharacteristic, 1, 3, "RFU (bit 3)");
        BaseValueParser.addFlag(sb, bluetoothGattCharacteristic, 1, 4, "Ready for Disconnect");
        BaseValueParser.addFlag(sb, bluetoothGattCharacteristic, 1, 5, "Limited Access");
        BaseValueParser.addFlag(sb, bluetoothGattCharacteristic, 1, 6, "Access Permitted");
        BaseValueParser.addFlag(sb, bluetoothGattCharacteristic, 1, 7, "RFU (bit 7)");
        int intValue2 = bluetoothGattCharacteristic.getIntValue(17, 2).intValue() & 3;
        if (intValue2 == 0) {
            sb.append("Connectable undirected advertising (ADV_IND)\n");
        } else if (intValue2 == 1) {
            sb.append("Scannable undirected advertising (ADV_SCAN_IND)\n");
        } else if (intValue2 == 2) {
            sb.append("Non-connectable undirected advertising (ADV_NONCONN_IND)\n");
        } else if (intValue2 == 3) {
            sb.append("Connectable low duty cycle directed advertising (ADV_DIRECT_IND, low duty cycle)\n");
        }
        BaseValueParser.addFlag(sb, bluetoothGattCharacteristic, 2, 2, "RFU (bit 2)");
        BaseValueParser.addFlag(sb, bluetoothGattCharacteristic, 2, 3, "RFU (bit 3)");
        BaseValueParser.addFlag(sb, bluetoothGattCharacteristic, 2, 4, "RFU (bit 4)");
        BaseValueParser.addFlag(sb, bluetoothGattCharacteristic, 2, 5, "RFU (bit 5)");
        BaseValueParser.addFlag(sb, bluetoothGattCharacteristic, 2, 6, "RFU (bit 6)");
        BaseValueParser.addFlag(sb, bluetoothGattCharacteristic, 2, 7, "RFU (bit 7)");
        if (intValue == 5 && value.length >= 5) {
            int intValue3 = bluetoothGattCharacteristic.getIntValue(18, 3).intValue();
            sb.append("E2E-CRC: ");
            sb.append(String.format(Locale.US, "%04X", Integer.valueOf(intValue3)));
            sb.append("\n");
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }
}
