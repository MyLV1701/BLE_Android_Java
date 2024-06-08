package no.nordicsemi.android.mcp.ble.parser.gap;

import java.util.Locale;
import no.nordicsemi.android.mcp.ble.model.DataUnion;
import no.nordicsemi.android.mcp.ble.parser.AppearanceLibrary;

/* loaded from: classes.dex */
public class LERoleParser implements AppearanceLibrary {
    public static String getRoleAsString(byte b2) {
        return b2 != 0 ? b2 != 1 ? b2 != 2 ? b2 != 3 ? String.format(Locale.US, "Reserved for future use (0x%02X)", Byte.valueOf(b2)) : "Peripheral and Central Role supported,\nCentral Role preferred for connection establishment" : "Peripheral and Central Role supported,\nPeripheral Role preferred for connection establishment" : "Only Central Role supported" : "Only Peripheral Role supported";
    }

    public static void parse(DataUnion dataUnion, byte[] bArr, int i, int i2) {
        dataUnion.addData("LE Role", getRoleAsString(bArr[i]));
    }
}
