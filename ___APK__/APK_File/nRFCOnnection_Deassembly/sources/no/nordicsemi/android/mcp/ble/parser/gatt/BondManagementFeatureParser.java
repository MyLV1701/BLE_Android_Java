package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import java.util.Locale;
import no.nordicsemi.android.mcp.ble.parser.utils.ParserUtils;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class BondManagementFeatureParser implements ICharacteristicParser {
    private static final int DELETE_BOND_OF_CURRENT_BR_EDR_CONNECTION_AUTH_REQUIRED = 8;
    private static final int DELETE_BOND_OF_CURRENT_BR_EDR_CONNECTION_SUPPORTED = 4;
    private static final int DELETE_BOND_OF_CURRENT_BR_EDR_LE_CONNECTION_AUTH_REQUIRED = 2;
    private static final int DELETE_BOND_OF_CURRENT_BR_EDR_LE_CONNECTION_SUPPORTED = 1;
    private static final int DELETE_BOND_OF_CURRENT_LE_CONNECTION_AUTH_REQUIRED = 32;
    private static final int DELETE_BOND_OF_CURRENT_LE_CONNECTION_SUPPORTED = 16;
    private static final int FEATURE_EXTENSION = 8388608;
    private static final int IDENTIFY_YOURSELF_SUPPORTED = 262144;
    private static final int REMOVE_ALL_BONDS_BUT_ACTIVE_ON_SERVER_BR_EDR_CONNECTION_AUTH_REQUIRED = 32768;
    private static final int REMOVE_ALL_BONDS_BUT_ACTIVE_ON_SERVER_BR_EDR_CONNECTION_SUPPORTED = 16384;
    private static final int REMOVE_ALL_BONDS_BUT_ACTIVE_ON_SERVER_BR_EDR_LE_CONNECTION_AUTH_REQUIRED = 8192;
    private static final int REMOVE_ALL_BONDS_BUT_ACTIVE_ON_SERVER_BR_EDR_LE_CONNECTION_SUPPORTED = 4096;
    private static final int REMOVE_ALL_BONDS_BUT_ACTIVE_ON_SERVER_LE_CONNECTION_AUTH_REQUIRED = 131072;
    private static final int REMOVE_ALL_BONDS_BUT_ACTIVE_ON_SERVER_LE_CONNECTION_SUPPORTED = 65536;
    private static final int REMOVE_ALL_BONDS_ON_SERVER_BR_EDR_CONNECTION_AUTH_REQUIRED = 512;
    private static final int REMOVE_ALL_BONDS_ON_SERVER_BR_EDR_CONNECTION_SUPPORTED = 256;
    private static final int REMOVE_ALL_BONDS_ON_SERVER_BR_EDR_LE_CONNECTION_AUTH_REQUIRED = 128;
    private static final int REMOVE_ALL_BONDS_ON_SERVER_BR_EDR_LE_CONNECTION_SUPPORTED = 64;
    private static final int REMOVE_ALL_BONDS_ON_SERVER_LE_CONNECTION_AUTH_REQUIRED = 2048;
    private static final int REMOVE_ALL_BONDS_ON_SERVER_LE_CONNECTION_SUPPORTED = 1024;

    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic);
    }

    public static String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        byte[] value = bluetoothGattCharacteristic.getValue();
        if (value.length != 3) {
            return "Invalid value: " + GeneralCharacteristicParser.parse(bluetoothGattCharacteristic);
        }
        int intValue = ParserUtils.getIntValue(value, 19, 0);
        StringBuilder sb = new StringBuilder();
        sb.append("Features:");
        if ((intValue & 1) > 0) {
            sb.append("\nDelete Bond of current connection (BR/EDR and LE) supported");
        }
        if ((intValue & 2) > 0) {
            sb.append("\nDelete Bond of current connection with authorization code (BR/EDR and LE) supported");
        }
        if ((intValue & 4) > 0) {
            sb.append("\nDelete bond of current connection (BR/EDR transport only) supported");
        }
        if ((intValue & 8) > 0) {
            sb.append("\nDelete bond of current connection with authorization code (BR/EDR transport only) supported");
        }
        if ((intValue & 16) > 0) {
            sb.append("\nDelete bond of current connection (LE transport only) supported");
        }
        if ((intValue & 32) > 0) {
            sb.append("\nDelete bond of current connection with authorization code (LE transport only) supported");
        }
        if ((intValue & 64) > 0) {
            sb.append("\nRemove all bonds on server (BR/EDR and LE) supported");
        }
        if ((intValue & 128) > 0) {
            sb.append("\nRemove all bonds on server with authorization code (BR/EDR and LE) supported");
        }
        if ((intValue & 256) > 0) {
            sb.append("\nRemove all bonds on server (BR/EDR transport only) supported");
        }
        if ((intValue & 512) > 0) {
            sb.append("\nRemove all bonds on server with authorization code (BR/EDR transport only) supported");
        }
        if ((intValue & 1024) > 0) {
            sb.append("\nRemove all bonds on server (LE transport only) supported");
        }
        if ((intValue & 2048) > 0) {
            sb.append("\nRemove all bonds on server with authorization code (LE transport only) supported");
        }
        if ((intValue & 4096) > 0) {
            sb.append("\nRemove all but the active bond on server (BR/EDR and LE) supported");
        }
        if ((intValue & 8192) > 0) {
            sb.append("\nRemove all but the active bond on server with authorization code (BR/EDR and LE) supported");
        }
        if ((intValue & 16384) > 0) {
            sb.append("\nRemove all but the active bond on server (BR/EDR transport only) supported");
        }
        if ((32768 & intValue) > 0) {
            sb.append("\nRemove all but the active bond on server with authorization code (BR/EDR transport only) supported");
        }
        if ((REMOVE_ALL_BONDS_BUT_ACTIVE_ON_SERVER_LE_CONNECTION_SUPPORTED & intValue) > 0) {
            sb.append("\nRemove all but the active bond on server (LE transport only) supported");
        }
        if ((REMOVE_ALL_BONDS_BUT_ACTIVE_ON_SERVER_LE_CONNECTION_AUTH_REQUIRED & intValue) > 0) {
            sb.append("\nRemove all but the active bond on server with authorization code (LE transport only) supported");
        }
        if ((IDENTIFY_YOURSELF_SUPPORTED & intValue) > 0) {
            sb.append("\nIdentify yourself supported");
        }
        if ((FEATURE_EXTENSION & intValue) > 0) {
            sb.append("\nFeature Extension");
        }
        if (intValue == 0) {
            sb.append(" none");
        }
        if ((7864320 & intValue) > 0) {
            sb.append(String.format(Locale.US, "\nUnknown features: %04X", Integer.valueOf(intValue)));
        }
        return sb.toString();
    }
}
