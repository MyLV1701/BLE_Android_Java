package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import java.util.Locale;
import no.nordicsemi.android.mcp.ble.parser.utils.ParserUtils;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class RCFeaturesParser implements ICharacteristicParser {
    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic);
    }

    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        byte[] value = bluetoothGattCharacteristic.getValue();
        if (value.length < 5) {
            return "Invalid value: " + GeneralCharacteristicParser.parse(bluetoothGattCharacteristic);
        }
        int intValue = bluetoothGattCharacteristic.getIntValue(18, 0).intValue();
        StringBuilder sb = new StringBuilder();
        if (intValue != 65535) {
            sb.append("E2E-CRC: ");
            sb.append(String.format(Locale.US, "%04X", Integer.valueOf(intValue)));
            sb.append("\n");
        }
        int addFlag = BaseValueParser.addFlag(sb, bluetoothGattCharacteristic, 2, 0, "E2E-CRC Supported") + 2;
        int addFlag2 = addFlag + BaseValueParser.addFlag(sb, bluetoothGattCharacteristic, addFlag, 1, "Enable Disconnect Supported");
        int addFlag3 = addFlag2 + BaseValueParser.addFlag(sb, bluetoothGattCharacteristic, addFlag2, 2, "Ready for Disconnect Supported");
        int addFlag4 = addFlag3 + BaseValueParser.addFlag(sb, bluetoothGattCharacteristic, addFlag3, 3, "Propose Reconnection Timeout Supported");
        int addFlag5 = addFlag4 + BaseValueParser.addFlag(sb, bluetoothGattCharacteristic, addFlag4, 4, "Propose Connection Interval Supported");
        int addFlag6 = addFlag5 + BaseValueParser.addFlag(sb, bluetoothGattCharacteristic, addFlag5, 5, "Propose Slave Latency Supported");
        int addFlag7 = addFlag6 + BaseValueParser.addFlag(sb, bluetoothGattCharacteristic, addFlag6, 6, "Propose Supervision Timeout Supported");
        int addFlag8 = addFlag7 + BaseValueParser.addFlag(sb, bluetoothGattCharacteristic, addFlag7, 7, "Propose Advertisement Interval Supported");
        int addFlag9 = addFlag8 + BaseValueParser.addFlag(sb, bluetoothGattCharacteristic, addFlag8, 0, "Propose Advertisement Count Supported");
        int addFlag10 = addFlag9 + BaseValueParser.addFlag(sb, bluetoothGattCharacteristic, addFlag9, 1, "Propose Advertisement Repetition Time Supported");
        int addFlag11 = addFlag10 + BaseValueParser.addFlag(sb, bluetoothGattCharacteristic, addFlag10, 2, "Advertisement Configuration 1 Supported");
        int addFlag12 = addFlag11 + BaseValueParser.addFlag(sb, bluetoothGattCharacteristic, addFlag11, 3, "Advertisement Configuration 2 Supported");
        int addFlag13 = addFlag12 + BaseValueParser.addFlag(sb, bluetoothGattCharacteristic, addFlag12, 4, "Advertisement Configuration 3 Supported");
        int addFlag14 = addFlag13 + BaseValueParser.addFlag(sb, bluetoothGattCharacteristic, addFlag13, 5, "Advertisement Configuration 4 Supported");
        int addFlag15 = addFlag14 + BaseValueParser.addFlag(sb, bluetoothGattCharacteristic, addFlag14, 6, "Upgrade to LESC Only Supported");
        int addFlag16 = addFlag15 + BaseValueParser.addFlag(sb, bluetoothGattCharacteristic, addFlag15, 7, "Next Pairing OOB Supported");
        int addFlag17 = addFlag16 + BaseValueParser.addFlag(sb, bluetoothGattCharacteristic, addFlag16, 0, "Use of White List Supported");
        int addFlag18 = addFlag17 + BaseValueParser.addFlag(sb, bluetoothGattCharacteristic, addFlag17, 1, "Limited Access Supported");
        int addFlag19 = addFlag18 + BaseValueParser.addFlag(sb, bluetoothGattCharacteristic, addFlag18, 2, "RFU (bit 2)");
        int addFlag20 = addFlag19 + BaseValueParser.addFlag(sb, bluetoothGattCharacteristic, addFlag19, 3, "RFU (bit 3)");
        int addFlag21 = addFlag20 + BaseValueParser.addFlag(sb, bluetoothGattCharacteristic, addFlag20, 4, "RFU (bit 4)");
        int addFlag22 = addFlag21 + BaseValueParser.addFlag(sb, bluetoothGattCharacteristic, addFlag21, 5, "RFU (bit 5)");
        if ((bluetoothGattCharacteristic.getIntValue(17, addFlag22 + BaseValueParser.addFlag(sb, bluetoothGattCharacteristic, addFlag22, 6, "RFU (bit 6)")).intValue() & 128) != 0) {
            sb.append("More unsupported features: ");
            sb.append(ParserUtils.bytesToHex(value, 5, value.length - 5, true));
            sb.append("\n");
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }
}
