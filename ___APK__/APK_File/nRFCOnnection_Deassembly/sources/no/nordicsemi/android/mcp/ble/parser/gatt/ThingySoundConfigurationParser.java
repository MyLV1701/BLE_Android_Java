package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class ThingySoundConfigurationParser implements ICharacteristicParser {
    private static String micMode2String(int i) {
        if (i == 1) {
            return "ADPCM";
        }
        if (i == 2) {
            return "SPL";
        }
        return "Unknown: " + i;
    }

    private static String speakerMode2String(int i) {
        if (i == 1) {
            return "Frequency and duration";
        }
        if (i == 2) {
            return "8-bit PCM";
        }
        if (i == 3) {
            return "Sample";
        }
        return "Unknown: " + i;
    }

    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic);
    }

    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (bluetoothGattCharacteristic.getValue().length != 2) {
            return "Invalid value: " + GeneralCharacteristicParser.parse(bluetoothGattCharacteristic);
        }
        return "Speaker mode: " + speakerMode2String(bluetoothGattCharacteristic.getIntValue(17, 0).intValue()) + "\nMicrophone mode: " + micMode2String(bluetoothGattCharacteristic.getIntValue(17, 1).intValue());
    }
}
