package no.nordicsemi.android.mcp.ble.parser.gap;

import no.nordicsemi.android.mcp.ble.model.AdvData;
import no.nordicsemi.android.mcp.ble.model.DataUnion;
import no.nordicsemi.android.mcp.ble.parser.utils.ParserUtils;

/* loaded from: classes.dex */
public class ShortenedLocalNameParser {
    public static void parse(AdvData advData, DataUnion dataUnion, byte[] bArr, int i, int i2) {
        String decodeLocalName = CompleteLocalNameParser.decodeLocalName(bArr, i, i2);
        dataUnion.addData("Shortened Local Name", decodeLocalName);
        dataUnion.addData(CompleteLocalNameParser.BYTES, ParserUtils.bytesToHex(bArr, i, i2, true));
        CompleteLocalNameParser.updateAppearanceByName(advData, decodeLocalName);
        advData.setName(decodeLocalName);
    }
}
