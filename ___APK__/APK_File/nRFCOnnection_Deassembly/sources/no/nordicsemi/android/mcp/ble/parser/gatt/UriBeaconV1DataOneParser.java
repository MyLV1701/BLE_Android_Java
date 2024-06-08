package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import java.util.Locale;
import no.nordicsemi.android.mcp.ble.parser.gap.servicedata.URIBeaconParser;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class UriBeaconV1DataOneParser implements ICharacteristicParser {
    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic);
    }

    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        int intValue = bluetoothGattCharacteristic.getIntValue(18, 2).intValue();
        int intValue2 = bluetoothGattCharacteristic.getIntValue(17, 4).intValue();
        int intValue3 = bluetoothGattCharacteristic.getIntValue(18, 6).intValue();
        return String.format(Locale.US, "Service Complete List UUID: %04X\nService data UUID: %04X\nFlags: %s\nTx Power: %s\nURI: %s", Integer.valueOf(intValue), Integer.valueOf(intValue3), UriBeaconFlagsParser.parse(bluetoothGattCharacteristic, 8), TxPowerLevelParser.parse(bluetoothGattCharacteristic, 9), URIBeaconParser.decodeUri(bluetoothGattCharacteristic.getValue(), 10, Math.min(intValue2 - 5, 10)));
    }
}
