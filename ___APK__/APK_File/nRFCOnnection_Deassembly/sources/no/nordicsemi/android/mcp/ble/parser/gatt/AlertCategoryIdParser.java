package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import no.nordicsemi.android.ble.error.GattError;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class AlertCategoryIdParser implements ICharacteristicParser {
    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic);
    }

    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic, 0);
    }

    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
        if (bluetoothGattCharacteristic.getValue().length < 1) {
            return "Invalid value: " + GeneralCharacteristicParser.parse(bluetoothGattCharacteristic);
        }
        int intValue = bluetoothGattCharacteristic.getIntValue(17, i).intValue();
        switch (intValue) {
            case 0:
                return "Simple Alert";
            case 1:
                return "Email";
            case 2:
                return "News";
            case 3:
                return "Call";
            case 4:
                return "Missed call";
            case 5:
                return "SMS/MMS";
            case 6:
                return "Voice mail";
            case 7:
                return "Schedule";
            case 8:
                return "High Prioritized Alert";
            case 9:
                return "Instant Message";
            default:
                switch (intValue) {
                    case 251:
                    case 252:
                    case GattError.GATT_CCCD_CFG_ERROR /* 253 */:
                    case GattError.GATT_PROCEDURE_IN_PROGRESS /* 254 */:
                    case 255:
                        return "Defined by service specification";
                    default:
                        return "Reserved value (" + intValue + ")";
                }
        }
    }
}
