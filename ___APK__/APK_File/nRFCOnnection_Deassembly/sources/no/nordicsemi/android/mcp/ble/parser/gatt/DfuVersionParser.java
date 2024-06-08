package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class DfuVersionParser implements ICharacteristicParser {
    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic);
    }

    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (bluetoothGattCharacteristic.getValue().length != 2) {
            return "Incorrect data length (2 bytes expected): " + GeneralCharacteristicParser.parse(bluetoothGattCharacteristic);
        }
        int intValue = bluetoothGattCharacteristic.getIntValue(18, 0).intValue();
        int intValue2 = bluetoothGattCharacteristic.getIntValue(17, 0).intValue();
        int intValue3 = bluetoothGattCharacteristic.getIntValue(17, 1).intValue();
        StringBuilder sb = new StringBuilder();
        sb.append("Version: ");
        sb.append(intValue3);
        sb.append(".");
        sb.append(intValue2);
        sb.append("\nSupported features:");
        if (intValue3 >= 1) {
            intValue = 100;
        }
        if (intValue >= 9) {
            sb.append("\n- Firmware type and size included in the Init packet");
        }
        if (intValue >= 8) {
            sb.append("\n- Signed init packet required");
        }
        if (intValue >= 7) {
            sb.append("\n- SHA-256 firmware hash in the Extended DFU Init packet");
        }
        if (intValue >= 6) {
            sb.append("\n- Service Changed characteristic supported\n- Service Changed indication when bonded\n- Different device address in non-buttonless DFU");
        }
        if (intValue >= 5) {
            sb.append("\n- Extended DFU Init packet required\n- SoftDevice OTA update\n- Bootloader OTA update\n- Application OTA update");
        }
        if (intValue == 1) {
            sb.append("\n- Shared Long Term Key\n- Buttonless update\nSend DFU Start to jump to the bootloader");
        }
        return sb.toString();
    }
}
