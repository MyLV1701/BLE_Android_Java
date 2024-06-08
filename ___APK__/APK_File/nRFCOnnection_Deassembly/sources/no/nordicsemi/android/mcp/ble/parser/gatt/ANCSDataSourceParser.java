package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class ANCSDataSourceParser implements ICharacteristicParser {
    private static final int COMMAND_GET_APP_ATTR = 1;
    private static final int COMMAND_GET_NOTIF_ATTR = 0;

    private static boolean isAscii(int i) {
        return i >= 32 || i < 0;
    }

    private static boolean isAttributeId(int i) {
        return i >= 0 && i <= 7;
    }

    private static String parseAttributeId(int i) {
        switch (i) {
            case 0:
                return "App Identifier/Display Name";
            case 1:
                return "Title";
            case 2:
                return "Subtitle";
            case 3:
                return "Message";
            case 4:
                return "MessageSize";
            case 5:
                return "Date";
            case 6:
                return "Positive Action Label";
            case 7:
                return "Negative Action Label";
            default:
                return "Reserved Attribute ID (" + i + ")";
        }
    }

    private static void parseAttributes(StringBuilder sb, byte[] bArr, int i, boolean z) {
        if (z) {
            sb.append("\nAttributes:");
        }
        while (i < bArr.length) {
            int i2 = i + 1;
            byte b2 = bArr[i];
            sb.append("\n- ");
            sb.append(parseAttributeId(b2));
            sb.append(": ");
            int i3 = i2 + 2;
            if (bArr.length < i3) {
                return;
            }
            int i4 = (bArr[i2] & 255) | ((bArr[i2 + 1] & 255) << 8);
            if (bArr.length - i3 <= i4) {
                i4 = bArr.length - i3;
            }
            sb.append(new String(bArr, i3, i4));
            i = i4 + i3;
        }
    }

    private static String parseCommandId(int i) {
        if (i == 0) {
            return "Get Notification Attributes";
        }
        if (i == 1) {
            return "Get App Attributes";
        }
        if (i == 2) {
            return "Perform Notification Action";
        }
        return "Reserved Command ID (" + i + ")";
    }

    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic);
    }

    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        int i;
        byte[] value = bluetoothGattCharacteristic.getValue();
        if (value.length < 1) {
            return "Invalid value: Empty packet";
        }
        StringBuilder sb = new StringBuilder();
        if ((value[0] == 0 && value.length >= 8 && isAttributeId(value[5]) && value[7] < 5) || (value[0] == 1 && value.length >= 2 && value[1] >= 65)) {
            int intValue = bluetoothGattCharacteristic.getIntValue(17, 0).intValue();
            sb.append("Response to: ");
            sb.append(parseCommandId(intValue));
            if (intValue != 0) {
                if (intValue == 1) {
                    int i2 = 1;
                    while (i2 < value.length && value[i2] != 0) {
                        i2++;
                    }
                    if (i2 == value.length) {
                        sb.append("\nInvalid value: App ID (");
                        sb.append(bluetoothGattCharacteristic.getStringValue(1));
                        sb.append(") not NULL terminated");
                        return sb.toString();
                    }
                    int i3 = i2 - 1;
                    String str = new String(value, 1, i3);
                    sb.append("\nApp ID: ");
                    sb.append(str);
                    parseAttributes(sb, value, i3 + 1 + 1, true);
                }
            } else {
                if (value.length < 5) {
                    sb.append("\nInvalid value: Notification ID too short");
                    return sb.toString();
                }
                int intValue2 = bluetoothGattCharacteristic.getIntValue(20, 1).intValue();
                sb.append("\nNotification UID: ");
                sb.append(intValue2);
                parseAttributes(sb, value, 5, true);
            }
        } else {
            if (value.length <= 0 || value[0] != 0) {
                i = 0;
            } else {
                if (value.length == 2 && value[1] == 0) {
                    return "";
                }
                i = (value.length <= 2 || value[1] != 0) ? 0 : 1;
                if (value.length <= 4 || !isAscii(value[3])) {
                    i++;
                }
            }
            if (value.length >= i + 3 && isAttributeId(value[i])) {
                parseAttributes(sb, value, i, false);
                return sb.toString();
            }
            int i4 = 0;
            int i5 = i;
            while (i5 < value.length && isAscii(value[i5])) {
                i4++;
                i5++;
            }
            if (i4 > 0) {
                sb.append(new String(value, i, i4));
            }
            if (i5 < value.length - 1) {
                parseAttributes(sb, value, i5, false);
            }
        }
        return sb.toString();
    }
}
