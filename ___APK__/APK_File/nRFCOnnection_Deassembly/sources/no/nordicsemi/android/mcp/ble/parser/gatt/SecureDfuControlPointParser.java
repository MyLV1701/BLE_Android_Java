package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import java.util.Locale;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class SecureDfuControlPointParser implements ICharacteristicParser {
    private static final int EXT_ERROR_FW_VERSION_FAILURE = 5;
    private static final int EXT_ERROR_HASH_FAILED = 10;
    private static final int EXT_ERROR_HW_VERSION_FAILURE = 6;
    private static final int EXT_ERROR_INIT_COMMAND_INVALID = 4;
    private static final int EXT_ERROR_INSUFFICIENT_SPACE = 13;
    private static final int EXT_ERROR_NO_ERROR = 0;
    private static final int EXT_ERROR_SD_VERSION_FAILURE = 7;
    private static final int EXT_ERROR_SIGNATURE_MISSING = 8;
    private static final int EXT_ERROR_UNKNOWN_COMMAND = 3;
    private static final int EXT_ERROR_VERIFICATION_FAILED = 12;
    private static final int EXT_ERROR_WRONG_COMMAND_FORMAT = 2;
    private static final int EXT_ERROR_WRONG_HASH_TYPE = 9;
    private static final int EXT_ERROR_WRONG_SIGNATURE_TYPE = 11;
    private static final int OBJECT_TYPE_COMMAND = 1;
    private static final int OBJECT_TYPE_DATA = 2;
    private static final int REQ_CALCULATE_CHECKSUM = 3;
    private static final int REQ_CREATE_OBJECT = 1;
    private static final int REQ_EXECUTE = 4;
    private static final int REQ_READ_OBJECT_INFO = 6;
    private static final int REQ_RESPONSE_CODE = 96;
    private static final int REQ_SET_PRN_VALUE = 2;
    private static final int STATUS_EXTENDED_ERROR = 11;
    private static final int STATUS_INSUFFICIENT_RESOURCES = 4;
    private static final int STATUS_INVALID_OBJECT = 5;
    private static final int STATUS_INVALID_PARAMETER = 3;
    private static final int STATUS_OPERATION_FAILED = 10;
    private static final int STATUS_OPERATION_NOT_PERMITTED = 8;
    private static final int STATUS_OP_CODE_NOT_SUPPORTED = 2;
    private static final int STATUS_SIGNATURE_MISMATCH = 6;
    private static final int STATUS_SUCCESS = 1;
    private static final int STATUS_UNSUPPORTED_TYPE = 7;

    private static String getExtendedError(int i) {
        if (i == 0) {
            return "No error";
        }
        switch (i) {
            case 2:
                return "Wrong command format";
            case 3:
                return "Unknown command";
            case 4:
                return "Init command invalid";
            case 5:
                return "FW version failure";
            case 6:
                return "HW version failure";
            case 7:
                return "SD version failure";
            case 8:
                return "Signature mismatch";
            case 9:
                return "Wrong hash type";
            case 10:
                return "Hash failed";
            case 11:
                return "Wring signature type";
            case 12:
                return "Verification failed";
            case 13:
                return "Insufficient space";
            default:
                return "Reserved for future use";
        }
    }

    private static String getObjectType(int i) {
        return i != 1 ? i != 2 ? "Reserved for future use" : "Data" : "Command";
    }

    private static String getOpCode(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 6 ? i != 96 ? "Reserved for future use" : "Response for: " : "Read Object Info" : "Execute" : "Calculate Checksum" : "Set PRN value" : "Create Object";
    }

    private static String getStatus(int i) {
        switch (i) {
            case 1:
                return "Success";
            case 2:
                return "Op Code not supported";
            case 3:
                return "Invalid parameter";
            case 4:
                return "Insufficient resources";
            case 5:
                return "Invalid object";
            case 6:
                return "Signature mismatch";
            case 7:
                return "Unsupported type";
            case 8:
                return "Operation not permitted";
            case 9:
            default:
                return "Reserved for future use";
            case 10:
                return "Operation failed";
            case 11:
                return "Extended error";
        }
    }

    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic);
    }

    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        byte[] value = bluetoothGattCharacteristic.getValue();
        StringBuilder sb = new StringBuilder();
        int intValue = bluetoothGattCharacteristic.getIntValue(17, 0).intValue();
        sb.append(getOpCode(intValue));
        if (value.length > 1) {
            if (intValue == 1) {
                int intValue2 = bluetoothGattCharacteristic.getIntValue(17, 1).intValue();
                int intValue3 = bluetoothGattCharacteristic.getIntValue(20, 2).intValue();
                sb.append(" with type: ");
                sb.append(getObjectType(intValue2));
                sb.append("\nSize: ");
                sb.append(intValue3);
                sb.append(" bytes");
            } else if (intValue == 2) {
                int intValue4 = bluetoothGattCharacteristic.getIntValue(18, 1).intValue();
                sb.append(" to: ");
                sb.append(intValue4);
            } else if (intValue == 6) {
                int intValue5 = bluetoothGattCharacteristic.getIntValue(17, 1).intValue();
                sb.append("\nType: ");
                sb.append(getObjectType(intValue5));
            } else if (intValue == 96) {
                int intValue6 = bluetoothGattCharacteristic.getIntValue(17, 1).intValue();
                sb.append(getOpCode(intValue6));
                sb.append("\n");
                int intValue7 = bluetoothGattCharacteristic.getIntValue(17, 2).intValue();
                sb.append("Status: ");
                sb.append(getStatus(intValue7));
                if (intValue7 == 1) {
                    if (intValue6 == 3) {
                        int intValue8 = bluetoothGattCharacteristic.getIntValue(20, 3).intValue();
                        int intValue9 = bluetoothGattCharacteristic.getIntValue(20, 7).intValue();
                        sb.append("\nCurrent offset: ");
                        sb.append(intValue8);
                        sb.append(" bytes");
                        sb.append("\nCRC-32: ");
                        sb.append(String.format(Locale.US, "%08X", Integer.valueOf(intValue9)));
                    } else if (intValue6 == 6) {
                        int intValue10 = bluetoothGattCharacteristic.getIntValue(20, 3).intValue();
                        int intValue11 = bluetoothGattCharacteristic.getIntValue(20, 7).intValue();
                        int intValue12 = bluetoothGattCharacteristic.getIntValue(20, 11).intValue();
                        sb.append("\nMax object size: ");
                        sb.append(intValue10);
                        sb.append(" bytes");
                        sb.append("\nCurrent offset: ");
                        sb.append(intValue11);
                        sb.append(" bytes");
                        sb.append("\nCRC-32: ");
                        sb.append(String.format(Locale.US, "%08X", Integer.valueOf(intValue12)));
                    }
                } else if (intValue7 == 11) {
                    int intValue13 = bluetoothGattCharacteristic.getIntValue(17, 3).intValue();
                    sb.append("\nDetails: ");
                    sb.append(getExtendedError(intValue13));
                }
            }
        }
        return sb.toString();
    }
}
