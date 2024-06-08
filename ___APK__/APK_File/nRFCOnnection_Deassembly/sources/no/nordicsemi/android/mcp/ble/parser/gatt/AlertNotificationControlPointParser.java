package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class AlertNotificationControlPointParser implements ICharacteristicParser {
    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic);
    }

    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (bluetoothGattCharacteristic.getValue().length != 2) {
            return "Invalid value: " + GeneralCharacteristicParser.parse(bluetoothGattCharacteristic);
        }
        StringBuilder sb = new StringBuilder();
        int intValue = bluetoothGattCharacteristic.getIntValue(17, 0).intValue();
        if (intValue == 0) {
            sb.append("Enable New Incoming Alert Notification");
        } else if (intValue == 1) {
            sb.append("Enable Unread Category Status Notification");
        } else if (intValue == 2) {
            sb.append("Disable New Incoming Alert Notification");
        } else if (intValue == 3) {
            sb.append("Disable Unread Category Status Notification");
        } else if (intValue == 4) {
            sb.append("Notify New Incoming Alert immediately");
        } else if (intValue != 5) {
            sb.append("Reserved command (");
            sb.append(intValue);
            sb.append(")");
        } else {
            sb.append("Notify Unread Category Status immediately");
        }
        sb.append(": ");
        sb.append(AlertCategoryIdParser.parse(bluetoothGattCharacteristic, 1));
        return sb.toString();
    }
}
