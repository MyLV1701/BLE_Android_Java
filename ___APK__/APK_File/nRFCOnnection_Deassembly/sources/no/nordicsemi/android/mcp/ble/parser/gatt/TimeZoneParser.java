package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import no.nordicsemi.android.mcp.ble.parser.utils.ParserUtils;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class TimeZoneParser implements ICharacteristicParser {
    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic);
    }

    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic, 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
        int intOrThrow = ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(17, i));
        if (intOrThrow == 128) {
            return "Time Zone offset is not known";
        }
        StringBuilder sb = new StringBuilder("Time Zone: UTC");
        int i2 = intOrThrow / 4;
        int i3 = (intOrThrow % 4) * 15;
        if (i2 >= 0) {
            sb.append("+");
        }
        sb.append(i2);
        sb.append(":");
        sb.append(i3);
        return sb.toString();
    }
}
