package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import java.util.Locale;
import no.nordicsemi.android.mcp.ble.parser.utils.ParserUtils;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class ElevationParser implements ICharacteristicParser {
    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic);
    }

    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic.getValue(), 0);
    }

    public static String parse(BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
        return parse(bluetoothGattDescriptor.getValue(), i);
    }

    private static String parse(byte[] bArr, int i) {
        return String.format(Locale.US, "%.2f m", Float.valueOf(ParserUtils.getIntValue(bArr, 35, i) / 100.0f));
    }
}
