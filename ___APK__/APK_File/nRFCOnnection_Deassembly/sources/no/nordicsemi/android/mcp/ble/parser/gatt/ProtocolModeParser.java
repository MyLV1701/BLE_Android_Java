package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class ProtocolModeParser implements ICharacteristicParser {
    private static final int BOOT_PROTOCOL_MODE = 0;
    private static final int REPORT_PROTOCOL_MODE = 1;

    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic);
    }

    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        int intValue = bluetoothGattCharacteristic.getIntValue(17, 0).intValue();
        if (intValue == 0) {
            return "Boot Protocol Mode";
        }
        if (intValue == 1) {
            return "Report Protocol Mode";
        }
        return "Reserved for future use (" + intValue + ")";
    }
}
