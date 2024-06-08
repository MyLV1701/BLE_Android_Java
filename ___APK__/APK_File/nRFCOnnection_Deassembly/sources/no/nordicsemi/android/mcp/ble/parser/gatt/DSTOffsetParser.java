package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import no.nordicsemi.android.mcp.ble.parser.utils.ParserUtils;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class DSTOffsetParser implements ICharacteristicParser {
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
        if (intOrThrow == 0) {
            return "Standard Time";
        }
        if (intOrThrow == 2) {
            return "Half An Hour Daylight Time (+0.5h)";
        }
        if (intOrThrow == 4) {
            return "Daylight Time (+1h)";
        }
        if (intOrThrow == 8) {
            return "Double Daylight Time (+2h)";
        }
        if (intOrThrow == 255) {
            return "DST is unknown";
        }
        return "Reserved value (" + intOrThrow + ")";
    }
}
