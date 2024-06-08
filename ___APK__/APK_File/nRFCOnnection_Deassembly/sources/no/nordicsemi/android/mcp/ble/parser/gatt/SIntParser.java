package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import no.nordicsemi.android.mcp.ble.parser.utils.ParserUtils;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class SIntParser implements ICharacteristicParser {
    private final String unit;

    public SIntParser(String str) {
        this.unit = str;
    }

    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic, this.unit);
    }

    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic, String str) {
        return parse(bluetoothGattCharacteristic.getValue(), 0, bluetoothGattCharacteristic.getValue().length, str);
    }

    public static String parse(BluetoothGattDescriptor bluetoothGattDescriptor, int i, int i2, String str) {
        return parse(bluetoothGattDescriptor.getValue(), i, i2, str);
    }

    private static String parse(byte[] bArr, int i, int i2, String str) {
        String str2;
        if (str == null) {
            str2 = "";
        } else {
            str2 = " " + str;
        }
        if (i2 == 1) {
            return String.valueOf(ParserUtils.getIntValue(bArr, 33, i)) + str2;
        }
        if (i2 == 2) {
            return String.valueOf(ParserUtils.getIntValue(bArr, 34, i)) + str2;
        }
        if (i2 == 3) {
            return String.valueOf(ParserUtils.getIntValue(bArr, 35, i)) + str2;
        }
        if (i2 == 4) {
            return String.valueOf(ParserUtils.getIntValue(bArr, 36, i)) + str2;
        }
        if (i2 != 16) {
            return "Invalid data syntax: " + ParserUtils.bytesToHex(bArr, i, i2, true);
        }
        return ParserUtils.bytesToHex(bArr, i, i2, true);
    }
}
