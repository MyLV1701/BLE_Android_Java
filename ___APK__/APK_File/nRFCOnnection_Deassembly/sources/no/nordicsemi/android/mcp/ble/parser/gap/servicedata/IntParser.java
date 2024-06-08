package no.nordicsemi.android.mcp.ble.parser.gap.servicedata;

import java.util.Locale;
import no.nordicsemi.android.mcp.ble.model.DataUnion;
import no.nordicsemi.android.mcp.ble.parser.gap.FlagsParser;

/* loaded from: classes.dex */
public class IntParser {
    public static void parse(DataUnion dataUnion, byte[] bArr, int i, int i2) {
        int i3 = i + 1;
        int i4 = i + 2;
        int i5 = i + 3;
        dataUnion.addData("Unsigned Int32", String.format(Locale.US, "%d", Long.valueOf(toUnsignedInt(unsignedBytesToInt(bArr[i], bArr[i3], bArr[i4], bArr[i5])))), 20);
        dataUnion.addData("Signed Int32", String.format(Locale.US, "%d", Integer.valueOf(unsignedBytesToInt(bArr[i], bArr[i3], bArr[i4], bArr[i5]))), 36);
    }

    private static long toUnsignedInt(int i) {
        return i & 4294967295L;
    }

    private static int unsignedByteToInt(byte b2) {
        return b2 & FlagsParser.UNKNOWN_FLAGS;
    }

    private static int unsignedBytesToInt(byte b2, byte b3, byte b4, byte b5) {
        return unsignedByteToInt(b2) + (unsignedByteToInt(b3) << 8) + (unsignedByteToInt(b4) << 16) + (unsignedByteToInt(b5) << 24);
    }
}
