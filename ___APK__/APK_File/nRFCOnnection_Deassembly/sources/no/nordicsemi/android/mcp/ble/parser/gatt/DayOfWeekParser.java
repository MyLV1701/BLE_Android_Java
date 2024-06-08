package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class DayOfWeekParser implements ICharacteristicParser {
    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic);
    }

    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic, 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
        byte b2 = bluetoothGattCharacteristic.getValue()[i];
        StringBuilder sb = new StringBuilder("Day of week: ");
        switch (b2) {
            case 1:
                sb.append("Monday");
                break;
            case 2:
                sb.append("Tuesday");
                break;
            case 3:
                sb.append("Wednesday");
                break;
            case 4:
                sb.append("Thursday");
                break;
            case 5:
                sb.append("Friday");
                break;
            case 6:
                sb.append("Saturday");
                break;
            case 7:
                sb.append("Sunday");
                break;
            default:
                sb.append("Unknown");
                break;
        }
        return sb.toString();
    }
}
