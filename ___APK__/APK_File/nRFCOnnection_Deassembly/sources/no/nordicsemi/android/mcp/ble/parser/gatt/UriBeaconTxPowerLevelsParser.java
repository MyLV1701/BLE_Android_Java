package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class UriBeaconTxPowerLevelsParser implements ICharacteristicParser {
    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic);
    }

    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (bluetoothGattCharacteristic.getValue().length != 4) {
            return "Incorrect data length (4 bytes expected): " + GeneralCharacteristicParser.parse(bluetoothGattCharacteristic);
        }
        return "Power levels:\n- Lowest: " + bluetoothGattCharacteristic.getIntValue(33, 0).intValue() + " dBm\n- Low: " + bluetoothGattCharacteristic.getIntValue(33, 1).intValue() + " dBm\n- Medium: " + bluetoothGattCharacteristic.getIntValue(33, 2).intValue() + " dBm\n- High: " + bluetoothGattCharacteristic.getIntValue(33, 3).intValue() + " dBm";
    }
}
