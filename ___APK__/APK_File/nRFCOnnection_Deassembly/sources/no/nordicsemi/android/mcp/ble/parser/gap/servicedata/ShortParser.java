package no.nordicsemi.android.mcp.ble.parser.gap.servicedata;

import java.util.Locale;
import no.nordicsemi.android.mcp.ble.model.DataUnion;
import no.nordicsemi.android.mcp.ble.parser.gap.FlagsParser;

/* loaded from: classes.dex */
public class ShortParser {
    public static void parse(DataUnion dataUnion, byte[] bArr, int i, int i2) {
        int i3 = i + 1;
        dataUnion.addData("Unsigned Int16", String.format(Locale.US, "%d", Integer.valueOf(unsignedBytesToInt(bArr[i], bArr[i3]))), 18);
        dataUnion.addData("Signed Int16", String.format(Locale.US, "%d", Integer.valueOf(unsignedToSigned(unsignedBytesToInt(bArr[i], bArr[i3]), 16))), 34);
    }

    private static int unsignedByteToInt(byte b2) {
        return b2 & FlagsParser.UNKNOWN_FLAGS;
    }

    private static int unsignedBytesToInt(byte b2, byte b3) {
        return unsignedByteToInt(b2) + (unsignedByteToInt(b3) << 8);
    }

    private static int unsignedToSigned(int i, int i2) {
        int i3 = 1 << (i2 - 1);
        return (i & i3) != 0 ? (i3 - (i & (i3 - 1))) * (-1) : i;
    }
}
