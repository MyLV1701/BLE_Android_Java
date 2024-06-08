package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import java.util.Locale;
import no.nordicsemi.android.mcp.ble.parser.utils.ParserUtils;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class RSCMeasurementParser implements ICharacteristicParser {
    private static final byte INSTANTANEOUS_STRIDE_LENGTH_PRESENT = 1;
    private static final byte TOTAL_DISTANCE_PRESENT = 2;
    private static final byte WALKING_OR_RUNNING_STATUS_BITS = 4;

    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic);
    }

    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        float f2;
        byte b2 = bluetoothGattCharacteristic.getValue()[0];
        boolean z = (b2 & INSTANTANEOUS_STRIDE_LENGTH_PRESENT) > 0;
        boolean z2 = (b2 & TOTAL_DISTANCE_PRESENT) > 0;
        int i = 4;
        boolean z3 = !((b2 & WALKING_OR_RUNNING_STATUS_BITS) > 0);
        float intOrThrow = ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(18, 1)) / 256.0f;
        int intOrThrow2 = ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(17, 3));
        if (z) {
            f2 = ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(18, 4)) / 100.0f;
            i = 6;
        } else {
            f2 = 0.0f;
        }
        float intOrThrow3 = z2 ? ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(20, i)) / 10.0f : 0.0f;
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(Locale.US, "Speed: %.2f m/s, Candence: %d RPM,\n", Float.valueOf(intOrThrow), Integer.valueOf(intOrThrow2)));
        if (z) {
            sb.append(String.format(Locale.US, "Instantaneous Stride Length: %.2f m,\n", Float.valueOf(f2)));
        }
        if (z2) {
            sb.append(String.format(Locale.US, "Total Distance: %.1f m,\n", Float.valueOf(intOrThrow3)));
        }
        if (z3) {
            sb.append("Status: WALKING");
        } else {
            sb.append("Status: RUNNING");
        }
        return sb.toString();
    }
}
