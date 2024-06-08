package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import no.nordicsemi.android.mcp.ble.parser.utils.ParserUtils;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class IntermediateCuffPressureParser implements ICharacteristicParser {
    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic);
    }

    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        StringBuilder sb = new StringBuilder();
        int intOrThrow = ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(17, 0));
        int i = intOrThrow & 1;
        boolean z = (intOrThrow & 2) > 0;
        boolean z2 = (intOrThrow & 4) > 0;
        boolean z3 = (intOrThrow & 8) > 0;
        boolean z4 = (intOrThrow & 16) > 0;
        float floatOrThrow = ParserUtils.floatOrThrow(bluetoothGattCharacteristic.getFloatValue(50, 1));
        String str = i == 0 ? " mmHg" : " kPa";
        int i2 = 7;
        sb.append("Cuff pressure: ");
        sb.append(floatOrThrow);
        sb.append(str);
        if (z) {
            sb.append("\nTimestamp: ");
            sb.append(DateTimeParser.parse(bluetoothGattCharacteristic, 7));
            i2 = 14;
        }
        if (z2) {
            float floatOrThrow2 = ParserUtils.floatOrThrow(bluetoothGattCharacteristic.getFloatValue(50, i2));
            i2 += 2;
            sb.append("\nPulse: ");
            sb.append(floatOrThrow2);
            sb.append(" bpm");
        }
        if (z3) {
            int intOrThrow2 = ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(17, i2));
            i2++;
            sb.append("\nUser ID: ");
            sb.append(intOrThrow2);
        }
        if (z4) {
            int intOrThrow3 = ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(18, i2));
            if ((intOrThrow3 & 1) > 0) {
                sb.append("\nBody movement detected");
            }
            if ((intOrThrow3 & 2) > 0) {
                sb.append("\nCuff too lose");
            }
            if ((intOrThrow3 & 4) > 0) {
                sb.append("\nIrregular pulse detected");
            }
            int i3 = intOrThrow3 & 24;
            if (i3 == 8) {
                sb.append("\nPulse rate exceeds upper limit");
            }
            if (i3 == 16) {
                sb.append("\nPulse rate is less than lower limit");
            }
            if (i3 == 24) {
                sb.append("\nPulse rate range: Reserved for future use ");
            }
            if ((intOrThrow3 & 32) > 0) {
                sb.append("\nImproper measurement position");
            }
        }
        return sb.toString();
    }
}
