package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class BatteryLevelStateParser implements ICharacteristicParser {
    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic);
    }

    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic, 0);
    }

    static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
        return "\nBattery Level: " + BatteryLevelParser.parse(bluetoothGattCharacteristic) + BatteryPowerStateParser.parse(bluetoothGattCharacteristic, 1);
    }
}
