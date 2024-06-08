package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import no.nordicsemi.android.mcp.ble.parser.utils.ParserUtils;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class BigEndianUIntParser implements ICharacteristicParser {
    private final String unit;

    public BigEndianUIntParser(String str) {
        this.unit = str;
    }

    public static String parseBigEndian(BluetoothGattCharacteristic bluetoothGattCharacteristic, String str) {
        return parseBigEndian(bluetoothGattCharacteristic.getValue(), 0, bluetoothGattCharacteristic.getValue().length, str);
    }

    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parseBigEndian(bluetoothGattCharacteristic, this.unit);
    }

    private static String parseBigEndian(byte[] bArr, int i, int i2, String str) {
        String str2;
        if (str == null) {
            str2 = "";
        } else {
            str2 = " " + str;
        }
        if (i2 == 1) {
            return String.valueOf(ParserUtils.getIntValue(bArr, 17, i)) + str2;
        }
        if (i2 == 2) {
            return String.valueOf(ParserUtils.getIntValue(bArr, 98, i)) + str2;
        }
        if (i2 != 4) {
            return "Invalid data syntax: " + ParserUtils.bytesToHex(bArr, i, i2, true);
        }
        return String.valueOf(ParserUtils.getIntValue(bArr, 100, i) & 4294967295L) + str2;
    }
}
