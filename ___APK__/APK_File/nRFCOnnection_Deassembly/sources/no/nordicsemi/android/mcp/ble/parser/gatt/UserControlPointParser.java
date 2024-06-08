package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import no.nordicsemi.android.mcp.ble.parser.utils.ParserUtils;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class UserControlPointParser implements ICharacteristicParser {
    public static final int OP_CODE_CONSENT = 2;
    public static final int OP_CODE_DELETE_USER = 3;
    public static final int OP_CODE_NEW_USER = 1;
    public static final int OP_CODE_RESPONSE = 32;

    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic);
    }

    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        StringBuilder sb = new StringBuilder();
        int length = bluetoothGattCharacteristic.getValue().length;
        int intOrThrow = ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(17, 0));
        int i = 1;
        if (intOrThrow == 1) {
            sb.append("Op Code: Register New User <0x01>");
            if (length >= 3) {
                sb.append("\nConsent: ");
                sb.append(ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(18, 1)));
                i = 3;
            }
        } else if (intOrThrow == 2) {
            sb.append("Op Code: Consent <0x02>");
            if (length > 1) {
                sb.append("\nUser Index: ");
                sb.append(ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(17, 1)));
                i = 2;
            }
            if (length >= 4) {
                sb.append("\nConsent: ");
                sb.append(ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(18, i)));
                i += 2;
            }
        } else if (intOrThrow == 3) {
            sb.append("Op Code: Delete User Data <0x03>");
        } else if (intOrThrow != 32) {
            sb.append("Unsupported Op Code: ");
            sb.append(intOrThrow);
        } else {
            sb.append("Op Code: Response <0x20>");
            if (length >= 2) {
                int intOrThrow2 = ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(17, 1));
                sb.append("\nRequest Op Code: ");
                if (intOrThrow2 == 1) {
                    sb.append("Register New User <0x01>");
                } else if (intOrThrow2 == 2) {
                    sb.append("Consent <0x02>");
                } else if (intOrThrow2 != 3) {
                    sb.append("Unknown: ");
                    sb.append(intOrThrow2);
                } else {
                    sb.append("Delete User Data <0x03>");
                }
                sb.append("\nResult: ");
                int intOrThrow3 = ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(17, 2));
                if (intOrThrow3 == 1) {
                    sb.append("Success");
                    if (length >= 3 && intOrThrow2 == 1) {
                        int intValue = bluetoothGattCharacteristic.getIntValue(17, 3).intValue();
                        sb.append("\nUser Index: ");
                        sb.append(intValue != 255 ? String.valueOf(intValue) : "Unknown User");
                        i = 4;
                    }
                } else if (intOrThrow3 == 2) {
                    sb.append("Op Code Not Supported");
                } else if (intOrThrow3 == 3) {
                    sb.append("Invalid Parameter");
                } else if (intOrThrow3 == 4) {
                    sb.append("Operation Failed");
                } else if (intOrThrow3 != 5) {
                    sb.append("Reserved for future use");
                } else {
                    sb.append("User Not Authorized");
                }
                i = 3;
            }
        }
        if (length > i) {
            sb.append("\nUnsupported data: ");
            sb.append(GeneralCharacteristicParser.parse(bluetoothGattCharacteristic, i));
        }
        return sb.toString();
    }
}
