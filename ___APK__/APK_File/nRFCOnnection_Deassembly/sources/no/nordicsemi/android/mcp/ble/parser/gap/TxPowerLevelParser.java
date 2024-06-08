package no.nordicsemi.android.mcp.ble.parser.gap;

import java.util.Locale;
import no.nordicsemi.android.mcp.ble.model.DataUnion;

/* loaded from: classes.dex */
public class TxPowerLevelParser {
    public static final String TX_POWER_LEVEL = "Tx Power Level";

    private static int decodePowerLevel(byte[] bArr, int i) {
        return bArr[i];
    }

    public static void parse(DataUnion dataUnion, byte[] bArr, int i, int i2, boolean z, int i3) {
        Locale locale = Locale.US;
        Object[] objArr = new Object[1];
        if (!z) {
            i3 = decodePowerLevel(bArr, i);
        }
        objArr[0] = Integer.valueOf(i3);
        dataUnion.addData(TX_POWER_LEVEL, String.format(locale, "%d dBm", objArr));
    }
}
