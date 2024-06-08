package no.nordicsemi.android.mcp.ble.parser.gap.servicedata;

import java.io.UnsupportedEncodingException;
import no.nordicsemi.android.mcp.ble.model.DataUnion;

/* loaded from: classes.dex */
public class StringParser {
    public static void parse(DataUnion dataUnion, byte[] bArr, int i, int i2) {
        try {
            dataUnion.addData("Text (UTF-8)", new String(bArr, i, i2, "UTF-8"));
        } catch (UnsupportedEncodingException unused) {
            dataUnion.addData("Text", new String(bArr, i, i2));
        }
    }
}
