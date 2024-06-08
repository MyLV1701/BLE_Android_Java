package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import java.util.Locale;
import no.nordicsemi.android.mcp.ble.parser.utils.ParserUtils;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class TemperatureMeasurementParser implements ICharacteristicParser {
    private static final byte TEMPERATURE_TYPE_FLAG = 4;
    private static final byte TEMPERATURE_UNIT_FLAG = 1;
    private static final byte TIMESTAMP_FLAG = 2;

    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic);
    }

    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        String str;
        int intOrThrow = ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(17, 0));
        boolean z = (intOrThrow & 1) > 0;
        boolean z2 = (intOrThrow & 2) > 0;
        boolean z3 = (intOrThrow & 4) > 0;
        float floatOrThrow = ParserUtils.floatOrThrow(bluetoothGattCharacteristic.getFloatValue(52, 1));
        int i = 5;
        if (z2) {
            str = DateTimeParser.parse(bluetoothGattCharacteristic, 5);
            i = 12;
        } else {
            str = null;
        }
        String parse = z3 ? TemperatureTypeParser.parse(bluetoothGattCharacteristic, i) : null;
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(Locale.US, "%.02f", Float.valueOf(floatOrThrow)));
        if (z) {
            sb.append("°F");
        } else {
            sb.append("°C");
        }
        if (z2) {
            sb.append("\nTime: ");
            sb.append(str);
        }
        if (z3) {
            sb.append("\nType: ");
            sb.append(parse);
        }
        return sb.toString();
    }
}
