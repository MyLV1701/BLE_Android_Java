package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import java.util.Locale;
import no.nordicsemi.android.mcp.ble.parser.utils.ParserUtils;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class RCControlPointParser implements ICharacteristicParser {
    private static final int OP_CODE_ACTIVATE_STORED_SETTINGS = 3;
    private static final int OP_CODE_CLIENT_PARAMETER_INDICATION = 17;
    private static final int OP_CODE_COMMUNICATION_PARAMETER_RESPONSE = 15;
    private static final int OP_CODE_ENABLE_DISCONNECT = 0;
    private static final int OP_CODE_GET_ACTUAL_COMMUNICATION_PARAMETERS = 1;
    private static final int OP_CODE_GET_MAX_VALUES = 4;
    private static final int OP_CODE_GET_MIN_VALUES = 5;
    private static final int OP_CODE_GET_STORED_VALUES = 6;
    private static final int OP_CODE_GET_WHITE_LIST_TIMER = 8;
    private static final int OP_CODE_LIMITED_ACCESS = 12;
    private static final int OP_CODE_PROCEDURE_RESPONSE = 14;
    private static final int OP_CODE_PROPOSE_SETTINGS = 2;
    private static final int OP_CODE_SET_ADVERTISEMENT_CONFIGURATION = 9;
    private static final int OP_CODE_SET_WHITE_LIST_TIMER = 7;
    private static final int OP_CODE_SWITCH_OOB_PAIRING = 11;
    private static final int OP_CODE_UPGRADE_TO_LESC_ONLY = 10;
    private static final int OP_CODE_WHITE_LIST_TIMER_RESPONSE = 16;

    private static String parseAdvertisingConfiguration(int i) {
        if (i > 3) {
            return "RFU (" + i + ")";
        }
        return String.valueOf(i + 1);
    }

    private static int parseCommunicationParameters(StringBuilder sb, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
        int length = bluetoothGattCharacteristic.getValue().length;
        if (length < i + 16) {
            sb.append("- Invalid parameters: ");
            sb.append(ParserUtils.bytesToHex(bluetoothGattCharacteristic.getValue(), i, 16, true));
            sb.append("\n");
            return length - i;
        }
        int intValue = bluetoothGattCharacteristic.getIntValue(18, i).intValue();
        float intValue2 = bluetoothGattCharacteristic.getIntValue(18, i + 2).intValue() * 1.25f;
        float intValue3 = bluetoothGattCharacteristic.getIntValue(18, i + 4).intValue() * 1.25f;
        int intValue4 = bluetoothGattCharacteristic.getIntValue(18, i + 6).intValue();
        int intValue5 = bluetoothGattCharacteristic.getIntValue(18, i + 8).intValue() * 10;
        float intValue6 = bluetoothGattCharacteristic.getIntValue(18, i + 10).intValue() * 0.625f;
        int intValue7 = bluetoothGattCharacteristic.getIntValue(18, i + 12).intValue();
        int intValue8 = bluetoothGattCharacteristic.getIntValue(18, i + 14).intValue();
        sb.append("- Reconnection Timeout: ");
        sb.append(intValue);
        sb.append(" s\n");
        sb.append("- Minimum Connection Interval: ");
        sb.append(String.format(Locale.US, "%.2f", Float.valueOf(intValue2)));
        sb.append(" ms\n");
        sb.append("- Maximum Connection Interval: ");
        sb.append(String.format(Locale.US, "%.2f", Float.valueOf(intValue3)));
        sb.append(" ms\n");
        sb.append("- Slave Latency: ");
        sb.append(intValue4);
        sb.append("\n");
        sb.append("- Supervision Timeout Multiplier: ");
        sb.append(intValue5);
        sb.append(" ms\n");
        sb.append("- Advertisement Interval: ");
        sb.append(String.format(Locale.US, "%.2f", Float.valueOf(intValue6)));
        sb.append(" ms\n");
        sb.append("- Advertisement Count: ");
        sb.append(intValue7);
        sb.append(" s\n");
        sb.append("- Advertisement Repetition Time: ");
        sb.append(intValue8);
        sb.append(" s\n");
        return 16;
    }

    private static String parseOpCode(int i) {
        switch (i) {
            case 0:
                return "Enable Disconnect";
            case 1:
                return "Get actual Communication Parameters";
            case 2:
                return "Propose Settings";
            case 3:
                return "Activate stored Settings";
            case 4:
                return "Get max values";
            case 5:
                return "Get min values";
            case 6:
                return "Get stored values";
            case 7:
                return "Set White List timer";
            case 8:
                return "Get White List timer";
            case 9:
                return "Set Advertisement Configuration";
            case 10:
                return "Upgrade to LESC only";
            case 11:
                return "Switch OOB pairing";
            case 12:
                return "Limited access";
            case 13:
            default:
                return "RFU (" + i + ")";
            case 14:
                return "Procedure response";
            case 15:
                return "Communication parameter response";
            case 16:
                return "White List timer response";
            case 17:
                return "Client parameter indication";
        }
    }

    private static String parseResultCode(int i) {
        switch (i) {
            case 1:
                return "Success";
            case 2:
                return "Opcode not supported";
            case 3:
                return "Invalid Operand";
            case 4:
                return "Operation failed";
            case 5:
                return "Communication Parameter out of range";
            case 6:
                return "Invalid Parameter combination";
            case 7:
                return "Device Busy";
            case 8:
                return "Communication Parameters rejected";
            default:
                return "RFU (" + i + ")";
        }
    }

    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        StringBuilder sb = new StringBuilder();
        int intValue = bluetoothGattCharacteristic.getIntValue(17, 0).intValue();
        int i = 5;
        switch (intValue) {
            case 0:
            case 1:
            case 4:
            case 5:
            case 8:
                sb.append(parseOpCode(intValue));
                sb.append("\n");
                i = 1;
                break;
            case 2:
            case 15:
            case 17:
                sb.append(parseOpCode(intValue));
                sb.append(":\n");
                i = 1 + parseCommunicationParameters(sb, bluetoothGattCharacteristic, 1);
                break;
            case 3:
            case 6:
                int intOrThrow = ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(17, 1));
                sb.append(parseOpCode(intValue));
                sb.append(": ");
                sb.append(intOrThrow);
                sb.append("\n");
                i = 2;
                break;
            case 7:
                int intOrThrow2 = ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(20, 1));
                sb.append(parseOpCode(intValue));
                sb.append(": ");
                sb.append(intOrThrow2);
                sb.append("\n");
                break;
            case 9:
                int intOrThrow3 = ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(17, 1));
                sb.append(parseOpCode(intValue));
                sb.append(": ");
                sb.append(parseAdvertisingConfiguration(intOrThrow3));
                sb.append("\n");
                i = 2;
                break;
            case 10:
            case 11:
            case 12:
                int intOrThrow4 = ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(17, 1));
                sb.append(parseOpCode(intValue));
                sb.append(": ");
                sb.append(intOrThrow4 == 255);
                sb.append("\n");
                i = 2;
                break;
            case 13:
            default:
                i = 1;
                break;
            case 14:
                int intOrThrow5 = ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(17, 1));
                int intOrThrow6 = ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(17, 2));
                sb.append(parseOpCode(intValue));
                sb.append(" for ");
                sb.append(parseOpCode(intOrThrow5));
                sb.append(": ");
                sb.append(parseResultCode(intOrThrow6));
                sb.append("\n");
                i = 3;
                break;
            case 16:
                int intOrThrow7 = ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(20, 1));
                int intOrThrow8 = ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(20, 5));
                int intOrThrow9 = ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(20, 9));
                sb.append(parseOpCode(intValue));
                sb.append(":\n- Current: ");
                sb.append(intOrThrow7);
                sb.append(" s\n- Min: ");
                sb.append(intOrThrow8);
                sb.append(" s\n- Max: ");
                sb.append(intOrThrow9);
                sb.append(" s\n");
                i = 13;
                break;
        }
        if (i == bluetoothGattCharacteristic.getValue().length - 2) {
            int intValue2 = bluetoothGattCharacteristic.getIntValue(18, i).intValue();
            sb.append("E2E-CRC: ");
            sb.append(String.format(Locale.US, "%04X", Integer.valueOf(intValue2)));
            sb.append("\n");
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }
}
