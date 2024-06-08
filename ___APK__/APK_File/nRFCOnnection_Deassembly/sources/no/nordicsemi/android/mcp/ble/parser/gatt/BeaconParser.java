package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import java.util.UUID;
import no.nordicsemi.android.mcp.ble.parser.gap.FlagsParser;
import no.nordicsemi.android.mcp.ble.parser.utils.CompanyIdentifier2;
import no.nordicsemi.android.mcp.ble.parser.utils.ParserUtils;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class BeaconParser implements ICharacteristicParser {
    public static final int TYPE_CALIBRATION = 2;
    public static final int TYPE_CONN_INTERVAL = 4;
    public static final int TYPE_LED_CONFIG = 5;
    public static final int TYPE_MAJOR_MINOR = 1;
    public static final int TYPE_MANUFACTURER_ID = 3;
    public static final int TYPE_UUID = 0;
    private final int type;

    public BeaconParser(int i) {
        this.type = i;
    }

    private static int decodeUint16LittleEndian(byte[] bArr, int i) {
        return (bArr[i + 1] & FlagsParser.UNKNOWN_FLAGS) | ((bArr[i] & FlagsParser.UNKNOWN_FLAGS) << 8);
    }

    public static String parseCalibration(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (bluetoothGattCharacteristic.getValue().length != 1) {
            return "Invalid value: " + GeneralCharacteristicParser.parse(bluetoothGattCharacteristic);
        }
        return bluetoothGattCharacteristic.getIntValue(33, 0).intValue() + " dBm";
    }

    public static String parseConnInterval(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (bluetoothGattCharacteristic.getValue().length != 2) {
            return "Invalid value: " + GeneralCharacteristicParser.parse(bluetoothGattCharacteristic);
        }
        return bluetoothGattCharacteristic.getIntValue(18, 0).intValue() + " ms";
    }

    public static String parseLEDConfig(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return LEDParser.parse(bluetoothGattCharacteristic);
    }

    public static String parseMajorMinor(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        byte[] value = bluetoothGattCharacteristic.getValue();
        if (value.length != 4) {
            return "Invalid value: " + GeneralCharacteristicParser.parse(bluetoothGattCharacteristic);
        }
        return "Major: " + decodeUint16LittleEndian(value, 0) + ", minor: " + decodeUint16LittleEndian(value, 2);
    }

    public static String parseManufacturerId(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (bluetoothGattCharacteristic.getValue().length != 2) {
            return "Invalid value: " + GeneralCharacteristicParser.parse(bluetoothGattCharacteristic);
        }
        return CompanyIdentifier2.withId(bluetoothGattCharacteristic.getIntValue(18, 0).intValue());
    }

    public static String parseUuid(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        UUID bytesToUUID = ParserUtils.bytesToUUID(bluetoothGattCharacteristic.getValue(), 0, 16);
        if (bytesToUUID != null) {
            return bytesToUUID.toString();
        }
        return "Invalid UUID: " + GeneralCharacteristicParser.parse(bluetoothGattCharacteristic);
    }

    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        int i = this.type;
        if (i == 0) {
            return parseUuid(bluetoothGattCharacteristic);
        }
        if (i == 1) {
            return parseMajorMinor(bluetoothGattCharacteristic);
        }
        if (i == 2) {
            return parseCalibration(bluetoothGattCharacteristic);
        }
        if (i == 3) {
            return parseManufacturerId(bluetoothGattCharacteristic);
        }
        if (i != 4) {
            return i != 5 ? "Unsupported type" : parseLEDConfig(bluetoothGattCharacteristic);
        }
        return parseConnInterval(bluetoothGattCharacteristic);
    }
}
