package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class CurrentTimeParser implements ICharacteristicParser {
    private static final byte CHANGE_OF_DST = 8;
    private static final byte CHANGE_OF_TIME_ZONE = 4;
    private static final byte EXTERNAL_REFERENCE_TIME = 2;
    private static final byte MANUAL_TIME_UPDATE = 1;

    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic);
    }

    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        StringBuilder sb = new StringBuilder();
        sb.append(ExactTime256Parser.parse(bluetoothGattCharacteristic));
        byte b2 = bluetoothGattCharacteristic.getValue()[9];
        boolean z = (b2 & MANUAL_TIME_UPDATE) > 0;
        boolean z2 = (b2 & EXTERNAL_REFERENCE_TIME) > 0;
        boolean z3 = (b2 & CHANGE_OF_TIME_ZONE) > 0;
        boolean z4 = (b2 & CHANGE_OF_DST) > 0;
        if (z) {
            sb.append("\nManual time update");
        }
        if (z2) {
            sb.append("\nExternal reference time update");
        }
        if (z3) {
            sb.append("\nChange of time zone");
        }
        if (z4) {
            sb.append("\nChange of DST");
        }
        return sb.toString();
    }
}
