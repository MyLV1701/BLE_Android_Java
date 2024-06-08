package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import no.nordicsemi.android.mcp.ble.parser.utils.ParserUtils;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class HeartRateMeasurementParser implements ICharacteristicParser {
    private static final byte ENERGY_EXPENDED_STATUS = 8;
    private static final byte HEART_RATE_VALUE_FORMAT = 1;
    private static final byte RR_INTERVAL = 16;
    private static final byte SENSOR_CONTACT_STATUS = 6;

    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic);
    }

    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        int intOrThrow = ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(17, 0));
        boolean z = (intOrThrow & 1) > 0;
        int i = (intOrThrow & 6) >> 1;
        boolean z2 = (intOrThrow & 8) > 0;
        boolean z3 = (intOrThrow & 16) > 0;
        int intOrThrow2 = ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(z ? 18 : 17, 1));
        int i2 = z ? 3 : 2;
        int intOrThrow3 = z2 ? ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(18, i2)) : -1;
        ArrayList arrayList = new ArrayList();
        if (z3) {
            for (int i3 = i2 + 2; i3 < bluetoothGattCharacteristic.getValue().length; i3 += 2) {
                arrayList.add(Float.valueOf((ParserUtils.intOrThrow(bluetoothGattCharacteristic.getIntValue(18, i3)) * 1000.0f) / 1024.0f));
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Heart Rate Measurement: ");
        sb.append(intOrThrow2);
        sb.append(" bpm");
        if (i == 0 || i == 1) {
            sb.append(",\nSensor Contact Not Supported");
        } else if (i == 2) {
            sb.append(",\nContact is NOT Detected");
        } else if (i == 3) {
            sb.append(",\nContact is Detected");
        }
        if (z2) {
            sb.append(",\nEnergy Expended: ");
            sb.append(intOrThrow3);
            sb.append(" kJ");
        }
        if (z3) {
            sb.append(",\nRR Interval: ");
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                sb.append(String.format(Locale.US, "%.02f ms, ", (Float) it.next()));
            }
            sb.setLength(sb.length() - 2);
        }
        return sb.toString();
    }
}
