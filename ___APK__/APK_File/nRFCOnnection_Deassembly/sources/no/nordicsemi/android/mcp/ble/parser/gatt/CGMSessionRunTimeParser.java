package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import java.util.Locale;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class CGMSessionRunTimeParser implements ICharacteristicParser {
    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic);
    }

    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        StringBuilder sb = new StringBuilder();
        int intValue = bluetoothGattCharacteristic.getIntValue(18, 0).intValue();
        sb.append("Session Run Time: ");
        sb.append(intValue);
        sb.append(" hours");
        if (bluetoothGattCharacteristic.getValue().length >= 4) {
            sb.append(String.format(Locale.US, "\nE2E-CRC: %04X", Integer.valueOf(bluetoothGattCharacteristic.getIntValue(18, 2).intValue())));
        }
        return sb.toString();
    }
}
