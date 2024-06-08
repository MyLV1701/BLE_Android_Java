package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import no.nordicsemi.android.mcp.ble.parser.gap.FlagsParser;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class GeneralCharacteristicParser implements ICharacteristicParser {
    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();

    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic);
    }

    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic.getValue(), 0);
    }

    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
        return parse(bluetoothGattCharacteristic.getValue(), i);
    }

    public static String parse(byte[] bArr, int i) {
        int length;
        if (bArr == null || bArr.length == 0 || (length = bArr.length - i) == 0) {
            return "";
        }
        char[] cArr = new char[(length * 3) - 1];
        boolean z = true;
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = bArr[i2 + i] & FlagsParser.UNKNOWN_FLAGS;
            z = z && i3 >= 32 && i3 <= 126;
            int i4 = i2 * 3;
            char[] cArr2 = HEX_ARRAY;
            cArr[i4] = cArr2[i3 >>> 4];
            cArr[i4 + 1] = cArr2[i3 & 15];
            if (i2 != length - 1) {
                cArr[i4 + 2] = '-';
            }
        }
        if (!z) {
            return "(0x) " + new String(cArr);
        }
        return "(0x) " + new String(cArr) + ", \"" + new String(bArr, i, length) + "\"";
    }
}
