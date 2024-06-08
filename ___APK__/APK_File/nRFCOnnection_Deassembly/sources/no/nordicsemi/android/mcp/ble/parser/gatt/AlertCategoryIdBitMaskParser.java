package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class AlertCategoryIdBitMaskParser implements ICharacteristicParser {
    private static final int CALL_BIT = 8;
    private static final int EMAIL_BIT = 2;
    private static final int HIGH_PRIORITIZED_ALERT_BIT = 1;
    private static final int INSTANT_MESSAGE_BIT = 2;
    private static final int MISSED_CALL_BIT = 16;
    private static final int NEWS_BIT = 4;
    private static final int SCHEDULE_BIT = 128;
    private static final int SIMPLE_ALERT_BIT = 1;
    private static final int SMS_MMS_BIT = 32;
    private static final int VOICE_MAIL_BIT = 64;

    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic);
    }

    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        byte[] value = bluetoothGattCharacteristic.getValue();
        if (value.length != 0 && value.length <= 2) {
            int intValue = bluetoothGattCharacteristic.getIntValue(17, 0).intValue();
            StringBuilder sb = new StringBuilder();
            if ((intValue & 1) > 0) {
                sb.append("Simple Alert supported\n");
            }
            if ((intValue & 2) > 0) {
                sb.append("Email supported\n");
            }
            if ((intValue & 4) > 0) {
                sb.append("News supported\n");
            }
            if ((intValue & 8) > 0) {
                sb.append("Call supported\n");
            }
            if ((intValue & 16) > 0) {
                sb.append("Missed Call supported\n");
            }
            if ((intValue & 32) > 0) {
                sb.append("SMS/MMS supported\n");
            }
            if ((intValue & 64) > 0) {
                sb.append("Voice Mail supported\n");
            }
            if ((intValue & 128) > 0) {
                sb.append("Schedule supported\n");
            }
            Integer intValue2 = bluetoothGattCharacteristic.getIntValue(17, 1);
            if (intValue2 != null) {
                if ((intValue2.intValue() & 1) > 0) {
                    sb.append("High Prioritized Alert supported\n");
                }
                if ((intValue2.intValue() & 2) > 0) {
                    sb.append("Instant Message supported\n");
                }
            }
            if (sb.length() > 0) {
                sb.setLength(sb.length() - 1);
            } else {
                sb.append("All bits clear");
            }
            return sb.toString();
        }
        return "Invalid value: " + GeneralCharacteristicParser.parse(bluetoothGattCharacteristic);
    }
}
