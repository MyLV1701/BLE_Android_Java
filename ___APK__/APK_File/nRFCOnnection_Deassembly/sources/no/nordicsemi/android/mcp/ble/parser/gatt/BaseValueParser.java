package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import no.nordicsemi.android.mcp.ble.parser.utils.ParserUtils;

/* loaded from: classes.dex */
class BaseValueParser {
    BaseValueParser() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int add(StringBuilder sb, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i, int i2, float f2, String str, String str2) {
        return addWhenFlagSet(sb, bluetoothGattCharacteristic, 1, 0, i, i2, f2, str, str2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int addFlag(StringBuilder sb, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i, int i2, String str) {
        if ((ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(17, i)) & (1 << i2)) != 0) {
            sb.append(str);
            sb.append("\n");
        }
        return i2 == 7 ? 1 : 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int addWhenFlagNotSet(StringBuilder sb, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i, int i2, int i3, int i4, float f2, String str, String str2) {
        if ((i & (1 << i2)) != 0) {
            return 0;
        }
        int intOrThrow = ParserUtils.intOrThrow(Integer.valueOf(ParserUtils.getIntValue(bluetoothGattCharacteristic.getValue(), i4, i3)));
        sb.append(str);
        sb.append(": ");
        if (f2 != 1.0f) {
            sb.append(intOrThrow * f2);
        } else {
            sb.append(intOrThrow);
        }
        if (str2 != null) {
            sb.append(" ");
            sb.append(str2);
        }
        sb.append("\n");
        return i4 & 15;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int addWhenFlagSet(StringBuilder sb, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i, int i2, int i3, int i4, float f2, String str, String str2) {
        if ((i & (1 << i2)) == 0) {
            return 0;
        }
        int intOrThrow = ParserUtils.intOrThrow(Integer.valueOf(ParserUtils.getIntValue(bluetoothGattCharacteristic.getValue(), i4, i3)));
        sb.append(str);
        sb.append(": ");
        if (f2 != 1.0f) {
            sb.append(intOrThrow * f2);
        } else {
            sb.append(intOrThrow);
        }
        if (str2 != null) {
            sb.append(" ");
            sb.append(str2);
        }
        sb.append("\n");
        return i4 & 15;
    }
}
