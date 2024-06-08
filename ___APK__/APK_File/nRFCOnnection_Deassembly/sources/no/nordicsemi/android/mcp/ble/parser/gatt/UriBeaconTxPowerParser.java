package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class UriBeaconTxPowerParser implements ICharacteristicParser {
    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic);
    }

    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (bluetoothGattCharacteristic.getValue().length != 1) {
            return "Incorrect data length (1 byte expected): " + GeneralCharacteristicParser.parse(bluetoothGattCharacteristic);
        }
        int intValue = bluetoothGattCharacteristic.getIntValue(17, 0).intValue();
        if (intValue == 0) {
            return "TX_POWER_MODE_LOWEST";
        }
        if (intValue == 1) {
            return "TX_POWER_MODE_LOW";
        }
        if (intValue == 2) {
            return "TX_POWER_MODE_MEDIUM";
        }
        if (intValue == 3) {
            return "TX_POWER_MODE_HIGH";
        }
        return "Invalid data syntax: " + GeneralCharacteristicParser.parse(bluetoothGattCharacteristic);
    }
}
