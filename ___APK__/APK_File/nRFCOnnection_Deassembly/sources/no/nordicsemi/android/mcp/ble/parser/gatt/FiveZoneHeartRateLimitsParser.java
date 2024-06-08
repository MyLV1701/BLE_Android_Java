package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class FiveZoneHeartRateLimitsParser implements ICharacteristicParser {
    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic);
    }

    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return "Very light/Light limit: " + HeartRateParser.parse(bluetoothGattCharacteristic, 0) + "\nLight/Moderate limit: " + HeartRateParser.parse(bluetoothGattCharacteristic, 1) + "\nModerate/Hard limit: " + HeartRateParser.parse(bluetoothGattCharacteristic, 2) + "\nHard/Very Hard limit: " + HeartRateParser.parse(bluetoothGattCharacteristic, 3);
    }
}
