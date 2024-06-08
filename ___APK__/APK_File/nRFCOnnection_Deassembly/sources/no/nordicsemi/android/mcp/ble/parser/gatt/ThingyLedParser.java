package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import java.util.Locale;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class ThingyLedParser implements ICharacteristicParser {
    private static final int MODE_BREATHE = 2;
    private static final int MODE_CONSTANT = 1;
    private static final int MODE_OFF = 0;
    private static final int MODE_ONE_SHOT = 3;

    private static String color2String(int i) {
        switch (i) {
            case 1:
                return "Red";
            case 2:
                return "Green";
            case 3:
                return "Yellow";
            case 4:
                return "Blue";
            case 5:
                return "Purple";
            case 6:
                return "Cyan";
            case 7:
                return "White";
            default:
                return "Unknown: " + i;
        }
    }

    private static String mode2String(int i) {
        if (i == 0) {
            return "Off";
        }
        if (i == 1) {
            return "Constant";
        }
        if (i == 2) {
            return "Breathe";
        }
        if (i == 3) {
            return "One Shot";
        }
        return "Unknown: " + i;
    }

    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic);
    }

    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        byte[] value = bluetoothGattCharacteristic.getValue();
        if (value.length != 0 && value.length != 2 && value.length <= 5) {
            StringBuilder sb = new StringBuilder();
            int intValue = bluetoothGattCharacteristic.getIntValue(17, 0).intValue();
            sb.append("Mode: ");
            sb.append(mode2String(intValue));
            if (intValue != 0) {
                if (intValue == 1) {
                    int intValue2 = bluetoothGattCharacteristic.getIntValue(17, 1).intValue();
                    int intValue3 = bluetoothGattCharacteristic.getIntValue(17, 2).intValue();
                    int intValue4 = bluetoothGattCharacteristic.getIntValue(17, 3).intValue();
                    sb.append("\nColor: ");
                    sb.append(String.format(Locale.US, "#%02X%02X%02X", Integer.valueOf(intValue2), Integer.valueOf(intValue3), Integer.valueOf(intValue4)));
                } else if (intValue == 2 || intValue == 3) {
                    int intValue5 = bluetoothGattCharacteristic.getIntValue(17, 1).intValue();
                    sb.append("\nColor: ");
                    sb.append(color2String(intValue5));
                    int intValue6 = bluetoothGattCharacteristic.getIntValue(17, 2).intValue();
                    sb.append("\nIntensity: ");
                    sb.append(intValue6);
                    sb.append("%");
                    if (value.length == 5) {
                        int intValue7 = bluetoothGattCharacteristic.getIntValue(18, 3).intValue();
                        sb.append("\nDelay: ");
                        sb.append(intValue7);
                        sb.append(" ms");
                    }
                }
            }
            return sb.toString();
        }
        return "Invalid value: " + GeneralCharacteristicParser.parse(bluetoothGattCharacteristic);
    }
}
