package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class DfuControlPointParser implements ICharacteristicParser {
    private static final int ACTIVATE_IMAGE_AND_RESET = 5;
    private static final int CRC_ERROR = 5;
    private static final int DATA_SIZE_EXCEEDS_LIMIT = 4;
    private static final int INITIALIZE_DFU_PARAMETERS = 2;
    private static final int INVALID_STATE = 2;
    private static final int MODE_APPLICATION = 4;
    private static final int MODE_BOOTLOADER = 2;
    private static final int MODE_SOFT_DEVICE = 1;
    private static final int NOT_SUPPORTED = 3;
    private static final int OPERATION_FAILED = 6;
    private static final int PACKET_RECEIPT_NOTIFICATION = 17;
    private static final int PACKET_RECEIPT_NOTIFICATION_REQUEST = 8;
    private static final int RECEIVE_FIRMWARE_IMAGE = 3;
    private static final int REPORT_RECEIVED_IMAGE_SIZE = 7;
    private static final int RESET_SYSTEM = 6;
    private static final int RESPONSE_CODE = 16;
    private static final int RESPONSE_SUCCESS = 1;
    private static final int START_DFU = 1;
    private static final int VALIDATION_FIRMWARE = 4;

    private static String getError(int i) {
        switch (i) {
            case 1:
                return "Success";
            case 2:
                return "Invalid state";
            case 3:
                return "Not supported";
            case 4:
                return "Data size exceeds limit";
            case 5:
                return "CRC error";
            case 6:
                return "Operation failed";
            default:
                return "Reserved for future use";
        }
    }

    private static String getOpCode(int i) {
        if (i == 16) {
            return "Response for: ";
        }
        if (i == 17) {
            return "Packet receipt notification";
        }
        switch (i) {
            case 1:
                return "Start DFU";
            case 2:
                return "Initialize DFU Parameters";
            case 3:
                return "Receive firmware image";
            case 4:
                return "Validation firmware";
            case 5:
                return "Activate image and reset";
            case 6:
                return "Reset system";
            case 7:
                return "Report received Image Size";
            case 8:
                return "Packet receipt notification request";
            default:
                return "Reserved for future use";
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
                sb.append("\nUpload mode: ");
                int intValue2 = bluetoothGattCharacteristic.getIntValue(17, 1).intValue();
                if (intValue2 != 0 && intValue2 <= 7) {
                    if ((intValue2 & 1) > 0) {
                        sb.append("SOFT DEVICE, ");
                    }
                    if ((intValue2 & 2) > 0) {
                        sb.append("BOOTLOADER, ");
                    }
                    if ((intValue2 & 4) > 0) {
                        sb.append("APPLICATION, ");
                    }
                    sb.setLength(sb.length() - 2);
                } else {
                    sb.append("UNKNOWN");
                }
            } else if (intValue == 2) {
                sb.append("\nParameter: ");
                int intValue3 = bluetoothGattCharacteristic.getIntValue(17, 1).intValue();
                if (intValue3 == 0) {
                    sb.append("START");
                } else if (intValue3 == 1) {
                    sb.append("COMPLETE");
                } else {
                    sb.append("Unknown (");
                    sb.append(intValue3);
                    sb.append(")");
                }
            } else if (intValue == 17 || intValue == 7) {
                sb.append("\nNumber of bytes received: ");
                sb.append(bluetoothGattCharacteristic.getIntValue(20, 1));
            } else if (intValue == 8) {
                sb.append("\nNumber of packets: ");
                sb.append(bluetoothGattCharacteristic.getIntValue(18, 1));
            } else if (intValue == 16) {
                sb.append(getOpCode(bluetoothGattCharacteristic.getIntValue(17, 1).intValue()));
                if (value.length < 3) {
                    sb.append("\nInvalid syntax: No value");
                } else {
                    sb.append("\nValue: ");
                    sb.append(getError(bluetoothGattCharacteristic.getIntValue(17, 2).intValue()));
                    if (value.length > 3) {
                        sb.append("\nResponse parameters: ");
                        sb.append(GeneralCharacteristicParser.parse(bluetoothGattCharacteristic, 3));
                    }
                }
            }
        }
        return sb.toString();
    }
}
