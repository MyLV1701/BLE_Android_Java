package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class ANCSControlPointParser implements ICharacteristicParser {
    private static final int COMMAND_GET_APP_ATTR = 1;
    private static final int COMMAND_GET_NOTIF_ATTR = 0;
    private static final int COMMAND_PERFORM_NOTIF_ACTION = 2;

    private static String parseActionId(int i) {
        if (i == 0) {
            return "Positive";
        }
        if (i == 1) {
            return "Negative";
        }
        return "Reserved Action ID (" + i + ")";
    }

    private static String parseAppAttributeId(int i) {
        if (i == 0) {
            return "Display Name";
        }
        return "Reserved App ID (" + i + ")";
    }

    private static String parseAttributeId(int i) {
        switch (i) {
            case 0:
                return "App Identifier";
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

    private static void parseAttributes(StringBuilder sb, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i, boolean z) {
        byte[] value = bluetoothGattCharacteristic.getValue();
        sb.append("\nAttributes:");
        if (i == value.length) {
            sb.append(" No attributes provided");
        }
        while (i < value.length) {
            int i2 = i + 1;
            int intValue = bluetoothGattCharacteristic.getIntValue(17, i).intValue();
            sb.append("\n- ");
            sb.append(z ? parseAppAttributeId(intValue) : parseAttributeId(intValue));
            if (intValue >= 1 && intValue <= 3) {
                int i3 = i2 + 2;
                if (value.length < i3) {
                    sb.append(" (Error: Max length not provided)");
                } else {
                    int intValue2 = bluetoothGattCharacteristic.getIntValue(18, i2).intValue();
                    sb.append(" (Max length: ");
                    sb.append(intValue2);
                    sb.append(" bytes)");
                    i = i3;
                }
            }
            i = i2;
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
        byte[] value = bluetoothGattCharacteristic.getValue();
        if (value.length < 1) {
            return "Invalid value: Empty packet";
        }
        int intValue = bluetoothGattCharacteristic.getIntValue(17, 0).intValue();
        StringBuilder sb = new StringBuilder();
        sb.append("Command: ");
        sb.append(parseCommandId(intValue));
        if (intValue != 0) {
            if (intValue == 1) {
                int i = 1;
                while (i < value.length && value[i] != 0) {
                    i++;
                }
                if (i == value.length) {
                    sb.append("\nInvalid value: App ID (");
                    sb.append(bluetoothGattCharacteristic.getStringValue(1));
                    sb.append(") not NULL terminated");
                    return sb.toString();
                }
                int i2 = i - 1;
                String str = new String(value, 1, i2);
                sb.append("\nApp ID: ");
                sb.append(str);
                parseAttributes(sb, bluetoothGattCharacteristic, i2 + 1 + 1, true);
            } else if (intValue == 2) {
                if (value.length < 5) {
                    sb.append("\nInvalid value: Notification ID too short");
                    return sb.toString();
                }
                int intValue2 = bluetoothGattCharacteristic.getIntValue(20, 1).intValue();
                sb.append("\nNotification UID: ");
                sb.append(intValue2);
                if (value.length < 6) {
                    sb.append("\nInvalid value: Missing Action ID");
                } else {
                    int intValue3 = bluetoothGattCharacteristic.getIntValue(17, 5).intValue();
                    sb.append("\nAction: ");
                    sb.append(parseActionId(intValue3));
                    if (value.length > 6) {
                        sb.append("\nUnsupported bytes: ");
                        sb.append(GeneralCharacteristicParser.parse(bluetoothGattCharacteristic, 6));
                    }
                }
            }
        } else {
            if (value.length < 5) {
                sb.append("\nInvalid value: Notification ID too short");
                return sb.toString();
            }
            int intValue4 = bluetoothGattCharacteristic.getIntValue(20, 1).intValue();
            sb.append("\nNotification UID: ");
            sb.append(intValue4);
            parseAttributes(sb, bluetoothGattCharacteristic, 5, false);
        }
        return sb.toString();
    }
}
