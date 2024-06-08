package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import no.nordicsemi.android.mcp.ble.parser.utils.ParserUtils;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class BatteryPowerStateParser implements ICharacteristicParser {
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
        int i2 = intOrThrow & 3;
        int i3 = (intOrThrow >> 2) & 3;
        int i4 = (intOrThrow >> 4) & 3;
        int i5 = (intOrThrow >> 6) & 3;
        StringBuilder sb = new StringBuilder();
        sb.append("\nBattery Power Information: ");
        if (i2 == 0) {
            sb.append("Unknown");
        } else if (i2 == 1) {
            sb.append("Not Supported");
        } else if (i2 == 2) {
            sb.append("Not Present");
        } else if (i2 == 3) {
            sb.append("Present");
        }
        sb.append("\nDischarging State: ");
        if (i3 == 0) {
            sb.append("Unknown");
        } else if (i3 == 1) {
            sb.append("Not Supported");
        } else if (i3 == 2) {
            sb.append("Not Discharging");
        } else if (i3 == 3) {
            sb.append("Discharging");
        }
        sb.append("\nCharging State: ");
        if (i4 == 0) {
            sb.append("Unknown");
        } else if (i4 == 1) {
            sb.append("Not Chargeable");
        } else if (i4 == 2) {
            sb.append("Not Charging (Chargeable)");
        } else if (i4 == 3) {
            sb.append("Charging (Chargeable)");
        }
        sb.append("\nLevel: ");
        if (i5 == 0) {
            sb.append("Unknown");
        } else if (i5 == 1) {
            sb.append("Not Supported");
        } else if (i5 == 2) {
            sb.append("Good Level");
        } else if (i5 == 3) {
            sb.append(" \tCritically Low Level");
        }
        return sb.toString();
    }
}
