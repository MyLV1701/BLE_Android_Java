package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import java.util.Locale;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class ClientSupportedFeaturesParser implements ICharacteristicParser {
    private static final int ROBUST_CACHING_BIT = 1;

    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic);
    }

    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        byte[] value = bluetoothGattCharacteristic.getValue();
        if (value.length == 0) {
            return "Invalid value: empty";
        }
        StringBuilder sb = new StringBuilder();
        if (value.length == 1 && value[0] == 0) {
            sb.append("No features\n");
        }
        if ((value[0] & 1) != 0) {
            sb.append("Robust Caching supported\n");
        }
        if ((value[0] & 254) != 0) {
            sb.append(String.format(Locale.US, "Unknown features: %02X\n", Byte.valueOf(value[0])));
        }
        for (int i = 1; i < value.length; i++) {
            if (value[i] != 0) {
                sb.append(String.format(Locale.US, "Unknown features (octet %d): %02X\n", Integer.valueOf(i), Byte.valueOf(value[i])));
            }
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }
}
