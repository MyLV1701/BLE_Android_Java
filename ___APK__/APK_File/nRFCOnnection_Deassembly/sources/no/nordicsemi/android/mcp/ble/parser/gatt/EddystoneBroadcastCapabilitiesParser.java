package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import no.nordicsemi.android.mcp.ble.parser.utils.ParserUtils;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class EddystoneBroadcastCapabilitiesParser implements ICharacteristicParser {
    private static final int IS_VARIABLE_ADV_SUPPORTED = 1;
    private static final int IS_VARIABLE_TX_POWER_SUPPORTED = 2;
    private static final int TYPE_EID = 8;
    private static final int TYPE_TLM = 4;
    private static final int TYPE_UID = 1;
    private static final int TYPE_URL = 2;

    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic);
    }

    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        byte[] value = bluetoothGattCharacteristic.getValue();
        if (value.length < 7) {
            return "Incorrect data length (7 bytes expected): " + GeneralCharacteristicParser.parse(bluetoothGattCharacteristic);
        }
        int intValue = bluetoothGattCharacteristic.getIntValue(17, 0).intValue();
        int intValue2 = bluetoothGattCharacteristic.getIntValue(17, 1).intValue();
        int intValue3 = bluetoothGattCharacteristic.getIntValue(17, 2).intValue();
        int intValue4 = bluetoothGattCharacteristic.getIntValue(17, 3).intValue();
        boolean z = (intValue4 & 1) > 0;
        boolean z2 = (intValue4 & 2) > 0;
        boolean z3 = (intValue4 & (-4)) > 0;
        int intValue5 = ParserUtils.getIntValue(value, 98, 4);
        boolean z4 = (intValue5 & 1) > 0;
        boolean z5 = (intValue5 & 2) > 0;
        boolean z6 = (intValue5 & 4) > 0;
        boolean z7 = (intValue5 & 8) > 0;
        boolean z8 = (intValue5 & (-16)) > 0;
        StringBuilder sb = new StringBuilder();
        sb.append("Version: ");
        sb.append(intValue);
        sb.append("\nTotal slots: ");
        sb.append(intValue2);
        sb.append("\nTotal EID slots: ");
        sb.append(intValue3);
        sb.append("\nCapabilities:");
        if (z) {
            sb.append("\n- Variable advertising interval supported");
        } else {
            sb.append("\n- Variable advertising interval not supported");
        }
        if (z2) {
            sb.append("\n- Variable Tx radio power supported");
        } else {
            sb.append("\n- Variable Tx radio power not supported");
        }
        if (z3) {
            sb.append("\n- Other (not supported)");
        }
        sb.append("\nSupported frame types:");
        if (z4) {
            sb.append(" UID,");
        }
        if (z5) {
            sb.append(" URL,");
        }
        if (z6) {
            sb.append(" TLM,");
        }
        if (z7) {
            sb.append(" EID,");
        }
        if (z8) {
            sb.append(" Unknown");
        }
        if (sb.toString().endsWith(",")) {
            sb.setLength(sb.length() - 1);
        }
        sb.append("\nSupported Tx radio power:");
        for (int i = 6; i < value.length; i++) {
            sb.append(" ");
            sb.append(bluetoothGattCharacteristic.getIntValue(33, i));
            sb.append(",");
        }
        if (sb.toString().endsWith(",")) {
            sb.setLength(sb.length() - 1);
        }
        sb.append(" dBm");
        return sb.toString();
    }
}
