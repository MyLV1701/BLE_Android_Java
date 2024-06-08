package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class ThingyMotionConfigurationParser implements ICharacteristicParser {
    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic);
    }

    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (bluetoothGattCharacteristic.getValue().length != 9) {
            return "Invalid value: " + GeneralCharacteristicParser.parse(bluetoothGattCharacteristic);
        }
        int intValue = bluetoothGattCharacteristic.getIntValue(18, 0).intValue();
        int intValue2 = bluetoothGattCharacteristic.getIntValue(18, 2).intValue();
        int intValue3 = bluetoothGattCharacteristic.getIntValue(18, 4).intValue();
        int intValue4 = bluetoothGattCharacteristic.getIntValue(18, 6).intValue();
        boolean z = bluetoothGattCharacteristic.getIntValue(17, 7).intValue() == 1;
        StringBuilder sb = new StringBuilder();
        sb.append("Step counter interval: ");
        sb.append(intValue);
        sb.append(" ms\nTemperature compensation interval: ");
        sb.append(intValue2);
        sb.append(" ms\nMagnetometer compensation interval: ");
        sb.append(intValue3);
        sb.append(" ms\nMotion processing unit frequency: ");
        sb.append(intValue4);
        sb.append(" Hz\nWake on Motion: ");
        sb.append(z ? "Enabled" : "Disabled");
        return sb.toString();
    }
}
