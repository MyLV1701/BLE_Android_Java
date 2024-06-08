package no.nordicsemi.android.mcp.ble.parser.gap;

import java.util.Locale;
import no.nordicsemi.android.mcp.ble.model.AdvData;
import no.nordicsemi.android.mcp.ble.model.DataUnion;
import no.nordicsemi.android.mcp.ble.parser.utils.ParserUtils;

/* loaded from: classes.dex */
public class AnkiManufacturerDataParser {
    public static void parse(AdvData advData, DataUnion dataUnion, byte[] bArr, int i, int i2) {
        StringBuilder sb = new StringBuilder();
        sb.append("\nProduct ID: ");
        sb.append(ParserUtils.bytesToHex(bArr, i, 2, true));
        byte b2 = bArr[i + 3];
        String str = b2 != 1 ? b2 != 2 ? b2 != 3 ? b2 != 4 ? "Unknown" : "Katal" : "Rho" : "Boson" : "Kourai";
        sb.append("\nModel: ");
        sb.append(str);
        sb.append("\nVehicle identifier: ");
        sb.append(ParserUtils.bytesToHex(bArr, i + 4, 4, true));
        dataUnion.addData("ANKI Drive Manufacturer data", sb.toString());
        advData.setName(str.toUpperCase(Locale.US));
    }
}
