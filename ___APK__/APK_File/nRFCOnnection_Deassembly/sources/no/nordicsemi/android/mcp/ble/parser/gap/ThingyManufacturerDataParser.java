package no.nordicsemi.android.mcp.ble.parser.gap;

import no.nordicsemi.android.mcp.ble.model.AdvData;
import no.nordicsemi.android.mcp.ble.model.DataUnion;
import no.nordicsemi.android.mcp.ble.parser.utils.ParserUtils;

/* loaded from: classes.dex */
public class ThingyManufacturerDataParser {
    private static final String THINGY = "Thingy NFC tag";

    public static void parse(AdvData advData, DataUnion dataUnion, byte[] bArr, int i, int i2) {
        if (advData.getAppearance() == 21 && i2 == 6 && bArr[i] == 89 && bArr[i + 1] == 0) {
            dataUnion.addData(THINGY, ParserUtils.bytesToHex(bArr, i + 2, i2 - 2, true));
        }
    }
}
