package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class SportTypeForAerobicAndAnaerobicThresholdParser implements ICharacteristicParser {
    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic);
    }

    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (bluetoothGattCharacteristic.getValue().length != 1) {
            return "Incorrect data length (1 byte expected): " + GeneralCharacteristicParser.parse(bluetoothGattCharacteristic);
        }
        int intValue = bluetoothGattCharacteristic.getIntValue(17, 0).intValue();
        switch (intValue) {
            case 0:
                return "Unspecified";
            case 1:
                return "Running (Treadmill)";
            case 2:
                return "Cycling (Ergometer)";
            case 3:
                return "Rowing (Ergometer)";
            case 4:
                return "Cross Training (Elliptical)";
            case 5:
                return "Climbing";
            case 6:
                return "Skiing";
            case 7:
                return "Skating";
            case 8:
                return "Arm exercising";
            case 9:
                return "Lower body exercising";
            case 10:
                return "Upper body exercising";
            case 11:
                return "Whole body exercising";
            default:
                return "Reserved value (" + intValue + ")";
        }
    }
}
