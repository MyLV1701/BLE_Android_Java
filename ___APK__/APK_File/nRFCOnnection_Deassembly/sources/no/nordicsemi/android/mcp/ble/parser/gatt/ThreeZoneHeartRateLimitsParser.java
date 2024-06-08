package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class ThreeZoneHeartRateLimitsParser implements ICharacteristicParser {
    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic);
    }

    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return "Light (Fat burn) / Moderate (Aerobic) limit: " + HeartRateParser.parse(bluetoothGattCharacteristic, 0) + "\nModerate (Aerobic) / Hard (Anaerobic) limit: " + HeartRateParser.parse(bluetoothGattCharacteristic, 1);
    }
}
