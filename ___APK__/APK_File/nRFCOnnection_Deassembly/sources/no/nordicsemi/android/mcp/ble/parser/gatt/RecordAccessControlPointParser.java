package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import no.nordicsemi.android.mcp.ble.parser.utils.ParserUtils;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class RecordAccessControlPointParser implements ICharacteristicParser {
    private static final int OPERATOR_ALL_RECORDS = 1;
    private static final int OPERATOR_FIRST_RECORD = 5;
    private static final int OPERATOR_GREATER_THEN_OR_EQUAL = 3;
    private static final int OPERATOR_LAST_RECORD = 6;
    private static final int OPERATOR_LESS_THEN_OR_EQUAL = 2;
    private static final int OPERATOR_NULL = 0;
    private static final int OPERATOR_WITHING_RANGE = 4;
    private static final int OP_CODE_ABORT_OPERATION = 3;
    private static final int OP_CODE_DELETE_STORED_RECORDS = 2;
    private static final int OP_CODE_NUMBER_OF_STORED_RECORDS_RESPONSE = 5;
    private static final int OP_CODE_REPORT_NUMBER_OF_RECORDS = 4;
    private static final int OP_CODE_REPORT_STORED_RECORDS = 1;
    private static final int OP_CODE_RESPONSE_CODE = 6;
    private static final int RESPONSE_ABORT_UNSUCCESSFUL = 7;
    private static final int RESPONSE_INVALID_OPERAND = 5;
    private static final int RESPONSE_INVALID_OPERATOR = 3;
    private static final int RESPONSE_NO_RECORDS_FOUND = 6;
    private static final int RESPONSE_OPERAND_NOT_SUPPORTED = 9;
    private static final int RESPONSE_OPERATOR_NOT_SUPPORTED = 4;
    private static final int RESPONSE_OP_CODE_NOT_SUPPORTED = 2;
    private static final int RESPONSE_PROCEDURE_NOT_COMPLETED = 8;
    private static final int RESPONSE_SUCCESS = 1;

    private static String getOpCode(int i) {
        switch (i) {
            case 1:
                return "Report stored records";
            case 2:
                return "Delete stored records";
            case 3:
                return "Abort operation";
            case 4:
                return "Report number of stored records";
            case 5:
                return "Number of stored records response";
            case 6:
                return "Response Code";
            default:
                return "Reserved for future use";
        }
    }

    private static String getOperator(int i) {
        switch (i) {
            case 0:
                return "Null";
            case 1:
                return "All records";
            case 2:
                return "Less than or equal to";
            case 3:
                return "Greater than or equal to";
            case 4:
                return "Within range of";
            case 5:
                return "First record(i.e. oldest record)";
            case 6:
                return "Last record (i.e. most recent record)";
            default:
                return "Reserved for future use";
        }
    }

    private static String getStatus(int i) {
        switch (i) {
            case 1:
                return "Success";
            case 2:
                return "Operation not supported";
            case 3:
                return "Invalid operator";
            case 4:
                return "Operator not supported";
            case 5:
                return "Invalid operand";
            case 6:
                return "No records found";
            case 7:
                return "Abort unsuccessful";
            case 8:
                return "Procedure not completed";
            case 9:
                return "Operand not supported";
            default:
                return "Reserved for future use";
        }
    }

    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic);
    }

    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        StringBuilder sb = new StringBuilder();
        int intOrThrow = ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(17, 0));
        int intOrThrow2 = ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(17, 1));
        switch (intOrThrow) {
            case 1:
            case 2:
            case 3:
            case 4:
                sb.append(getOpCode(intOrThrow));
                sb.append("\n");
                break;
            case 5:
                sb.append(getOpCode(intOrThrow));
                sb.append(": ");
                sb.append(ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(18, 2)));
                sb.append("\n");
                break;
            case 6:
                sb.append(getOpCode(intOrThrow));
                sb.append(" for ");
                sb.append(getOpCode(ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(17, 2))));
                sb.append(": ");
                sb.append(getStatus(ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(17, 3))));
                sb.append("\n");
                break;
        }
        switch (intOrThrow2) {
            case 1:
            case 5:
            case 6:
                sb.append("Operator: ");
                sb.append(getOperator(intOrThrow2));
                sb.append("\n");
                break;
            case 2:
            case 3:
                int intOrThrow3 = ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(17, 2));
                int intOrThrow4 = ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(18, 3));
                sb.append("Operator: ");
                sb.append(getOperator(intOrThrow2));
                sb.append(" ");
                sb.append(intOrThrow4);
                sb.append(" (filter: ");
                sb.append(intOrThrow3);
                sb.append(")\n");
                break;
            case 4:
                int intOrThrow5 = ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(17, 2));
                int intOrThrow6 = ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(18, 3));
                int intOrThrow7 = ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(18, 5));
                sb.append("Operator: ");
                sb.append(getOperator(intOrThrow2));
                sb.append(" ");
                sb.append(intOrThrow6);
                sb.append("-");
                sb.append(intOrThrow7);
                sb.append(" (filter: ");
                sb.append(intOrThrow5);
                sb.append(")\n");
                break;
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }
}
