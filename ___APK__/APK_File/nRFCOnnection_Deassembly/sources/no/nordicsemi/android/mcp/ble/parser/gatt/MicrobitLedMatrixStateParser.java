package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class MicrobitLedMatrixStateParser implements ICharacteristicParser {
    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic);
    }

    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (bluetoothGattCharacteristic.getValue().length != 5) {
            return "Incorrect data length (5 bytes expected): " + GeneralCharacteristicParser.parse(bluetoothGattCharacteristic);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            int intValue = bluetoothGattCharacteristic.getIntValue(17, i).intValue();
            sb.append("\n");
            for (int i2 = 0; i2 < 5; i2++) {
                if (((16 >> i2) & intValue) > 0) {
                    sb.append("●");
                } else {
                    sb.append("○");
                }
            }
        }
        return sb.toString();
    }
}
