package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class ANCSNotificationSourceParser implements ICharacteristicParser {
    private static String parseCategoryId(int i) {
        switch (i) {
            case 0:
                return "Other";
            case 1:
                return "Incoming Call";
            case 2:
                return "Missed Call";
            case 3:
                return "Voicemail";
            case 4:
                return "Social";
            case 5:
                return "Schedule";
            case 6:
                return "Email";
            case 7:
                return "News";
            case 8:
                return "Health And Fitness";
            case 9:
                return "Business And Finance";
            case 10:
                return "Location";
            case 11:
                return "Entertainment";
            default:
                return "Reserved Event ID (" + i + ")";
        }
    }

    private static String parseEventFlags(int i) {
        StringBuilder sb = new StringBuilder();
        if ((i & 1) > 0) {
            sb.append("Silent, ");
        }
        if ((i & 2) > 0) {
            sb.append("Important, ");
        }
        if ((i & 4) > 0) {
            sb.append("Pre Existing, ");
        }
        if ((i & 8) > 0) {
            sb.append("Positive Action, ");
        }
        if ((i & 16) > 0) {
            sb.append("Negative Action, ");
        }
        int i2 = i & 224;
        if (i2 > 0) {
            sb.append("Reserved flag (");
            sb.append(i2);
            sb.append("), ");
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 2);
        } else {
            sb.append("No flags");
        }
        return sb.toString();
    }

    private static String parseEventId(int i) {
        if (i == 0) {
            return "Notification added";
        }
        if (i == 1) {
            return "Notification modified";
        }
        if (i == 2) {
            return "Notification removed";
        }
        return "Reserved Event ID (" + i + ")";
    }

    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic);
    }

    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (bluetoothGattCharacteristic.getValue().length != 8) {
            return "Invalid value: " + GeneralCharacteristicParser.parse(bluetoothGattCharacteristic);
        }
        int intValue = bluetoothGattCharacteristic.getIntValue(17, 0).intValue();
        int intValue2 = bluetoothGattCharacteristic.getIntValue(17, 1).intValue();
        int intValue3 = bluetoothGattCharacteristic.getIntValue(17, 2).intValue();
        return "Event: " + parseEventId(intValue) + "\nFlags: " + parseEventFlags(intValue2) + "\nCategory: " + parseCategoryId(intValue3) + "\nCategory count: " + bluetoothGattCharacteristic.getIntValue(17, 3).intValue() + "\nNotification UID: " + bluetoothGattCharacteristic.getIntValue(20, 4).intValue();
    }
}
