package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import java.util.Locale;
import no.nordicsemi.android.mcp.ble.parser.utils.ParserUtils;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class MagneticFluxDensityParser implements ICharacteristicParser {
    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic);
    }

    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic.getValue(), 0, bluetoothGattCharacteristic.getValue().length);
    }

    public static String parse(BluetoothGattDescriptor bluetoothGattDescriptor, int i, int i2) {
        return parse(bluetoothGattDescriptor.getValue(), i, i2);
    }

    private static String parse(byte[] bArr, int i, int i2) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(Locale.US, "\nX-Axis: %.1f µT\n", Float.valueOf(ParserUtils.getIntValue(bArr, 34, i) / 10.0f)));
        int i3 = i2 - 2;
        int i4 = i + 2;
        if (i3 >= 2) {
            sb.append(String.format(Locale.US, "Y-Axis: %.1f µT\n", Float.valueOf(ParserUtils.getIntValue(bArr, 34, i4) / 10.0f)));
            i3 -= 2;
            i4 += 2;
        }
        if (i3 >= 2) {
            sb.append(String.format(Locale.US, "Z-Axis: %.1f µT\n", Float.valueOf(ParserUtils.getIntValue(bArr, 34, i4) / 10.0f)));
            i3 -= 2;
            i4 += 2;
        }
        if (i3 > 0) {
            sb.append("Unknown data: ");
            sb.append(ParserUtils.bytesToHex(bArr, i4, i3, true));
            sb.append("\n");
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }
}
