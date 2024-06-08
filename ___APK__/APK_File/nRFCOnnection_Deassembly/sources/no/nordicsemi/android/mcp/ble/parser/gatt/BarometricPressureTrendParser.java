package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import no.nordicsemi.android.mcp.ble.parser.utils.ParserUtils;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class BarometricPressureTrendParser implements ICharacteristicParser {
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
        switch (ParserUtils.getIntValue(bArr, 17, i)) {
            case 0:
                return "Unknown";
            case 1:
                return "Continuously falling";
            case 2:
                return "Continuously rising";
            case 3:
                return "Falling, then steady";
            case 4:
                return "Rising, then steady";
            case 5:
                return "Falling before a lesser rise";
            case 6:
                return "Falling before a greater rise";
            case 7:
                return "Rising before a greater fall";
            case 8:
                return "Rising before a lesser fall";
            case 9:
                return "Steady";
            default:
                return "Reserved for future use ";
        }
    }
}
