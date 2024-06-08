package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import java.util.Locale;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class ThingyConnectionParamParser implements ICharacteristicParser {
    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic);
    }

    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (bluetoothGattCharacteristic.getValue().length != 8) {
            return "Invalid value: " + GeneralCharacteristicParser.parse(bluetoothGattCharacteristic);
        }
        return String.format(Locale.US, "Connection Interval: %.02f ms - %.02f ms,\nSlave Latency: %d\nSupervision Timeout: %d ms", Float.valueOf(bluetoothGattCharacteristic.getIntValue(18, 0).intValue() * 1.25f), Float.valueOf(bluetoothGattCharacteristic.getIntValue(18, 2).intValue() * 1.25f), Integer.valueOf(bluetoothGattCharacteristic.getIntValue(18, 4).intValue()), Integer.valueOf(bluetoothGattCharacteristic.getIntValue(18, 6).intValue() * 10));
    }
}
