package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class ThingyEnvironmentConfigurationParser implements ICharacteristicParser {
    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic);
    }

    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        byte[] value = bluetoothGattCharacteristic.getValue();
        if (value.length != 12 && value.length != 9) {
            return "Invalid value: " + GeneralCharacteristicParser.parse(bluetoothGattCharacteristic);
        }
        int intValue = bluetoothGattCharacteristic.getIntValue(18, 0).intValue();
        int intValue2 = bluetoothGattCharacteristic.getIntValue(18, 2).intValue();
        int intValue3 = bluetoothGattCharacteristic.getIntValue(18, 4).intValue();
        int intValue4 = bluetoothGattCharacteristic.getIntValue(18, 6).intValue();
        int intValue5 = bluetoothGattCharacteristic.getIntValue(17, 8).intValue();
        StringBuilder sb = new StringBuilder();
        sb.append("Temperature interval: ");
        sb.append(intValue);
        sb.append(" ms\n");
        sb.append("Pressure interval: ");
        sb.append(intValue2);
        sb.append(" ms\n");
        sb.append("Humidity interval: ");
        sb.append(intValue3);
        sb.append(" ms\n");
        sb.append("Light sensor interval: ");
        sb.append(intValue4);
        sb.append(" ms\n");
        sb.append("Air quality interval: ");
        if (intValue5 == 1) {
            sb.append("1 s");
        } else if (intValue5 == 2) {
            sb.append("10 s");
        } else if (intValue5 == 3) {
            sb.append("60 s");
        }
        if (value.length >= 12) {
            int intValue6 = bluetoothGattCharacteristic.getIntValue(17, 9).intValue();
            int intValue7 = bluetoothGattCharacteristic.getIntValue(17, 10).intValue();
            int intValue8 = bluetoothGattCharacteristic.getIntValue(17, 11).intValue();
            sb.append("\nLED calibration: R: ");
            sb.append(intValue6);
            sb.append(" G: ");
            sb.append(intValue7);
            sb.append(" B: ");
            sb.append(intValue8);
        }
        return sb.toString();
    }
}
