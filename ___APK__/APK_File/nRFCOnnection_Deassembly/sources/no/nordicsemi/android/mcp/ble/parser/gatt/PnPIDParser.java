package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import no.nordicsemi.android.mcp.ble.parser.utils.CompanyIdentifier2;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class PnPIDParser implements ICharacteristicParser {
    private static final int BLUETOOTH_SIG_COMPANY_SOURCE = 1;

    private static String getSIGVendorName(int i) {
        return CompanyIdentifier2.withId(i);
    }

    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic);
    }

    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (bluetoothGattCharacteristic.getValue().length != 7) {
            return "Incorrect data length (7 bytes expected): " + GeneralCharacteristicParser.parse(bluetoothGattCharacteristic);
        }
        int intValue = bluetoothGattCharacteristic.getIntValue(17, 0).intValue();
        int intValue2 = bluetoothGattCharacteristic.getIntValue(18, 1).intValue();
        int intValue3 = bluetoothGattCharacteristic.getIntValue(18, 3).intValue();
        int intValue4 = bluetoothGattCharacteristic.getIntValue(18, 5).intValue();
        StringBuilder sb = new StringBuilder();
        if (intValue == 1) {
            sb.append("Bluetooth SIG Company: ");
            sb.append(getSIGVendorName(intValue2));
        } else {
            sb.append("USB Implementer's Forum Vendor ID: ");
            sb.append(intValue2);
            sb.append(" (0x");
            sb.append(Integer.toHexString(intValue2));
            sb.append(")");
        }
        sb.append("\nProduct Id: ");
        sb.append(intValue3);
        sb.append("\nProduct Version: ");
        sb.append(intValue4);
        return sb.toString();
    }
}
