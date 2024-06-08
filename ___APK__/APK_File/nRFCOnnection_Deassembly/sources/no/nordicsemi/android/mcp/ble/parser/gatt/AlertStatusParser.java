package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class AlertStatusParser implements ICharacteristicParser {
    private static final byte DISPLAY_ALERT_STATE = 4;
    private static final byte RINGER_STATE = 1;
    private static final byte VIBRATE_STATE = 2;

    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic);
    }

    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (bluetoothGattCharacteristic.getValue().length != 2) {
            return "Invalid value: " + GeneralCharacteristicParser.parse(bluetoothGattCharacteristic);
        }
        int intValue = bluetoothGattCharacteristic.getIntValue(18, 0).intValue();
        boolean z = (intValue & 1) > 0;
        boolean z2 = (intValue & 2) > 0;
        boolean z3 = (intValue & 4) > 0;
        StringBuilder sb = new StringBuilder();
        sb.append("State:\n");
        if (z) {
            sb.append("Ringer State active\n");
        } else {
            sb.append("Ringer State NOT active\n");
        }
        if (z2) {
            sb.append("Vibrate State active\n");
        } else {
            sb.append("Vibrate State NOT active\n");
        }
        if (z3) {
            sb.append("Display Alert Status State active");
        } else {
            sb.append("Display Alert Status State NOT active");
        }
        return sb.toString();
    }
}
