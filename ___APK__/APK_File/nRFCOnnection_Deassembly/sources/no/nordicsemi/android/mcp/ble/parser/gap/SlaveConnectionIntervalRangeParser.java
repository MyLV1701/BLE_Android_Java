package no.nordicsemi.android.mcp.ble.parser.gap;

import java.util.Locale;
import no.nordicsemi.android.mcp.ble.model.DataUnion;

/* loaded from: classes.dex */
public class SlaveConnectionIntervalRangeParser {
    public static void parse(DataUnion dataUnion, byte[] bArr, int i, int i2) {
        dataUnion.addData("Slave Connection Interval Range", valueToString(unsignedBytesToInt(bArr[i], bArr[i + 1])) + " - " + valueToString(unsignedBytesToInt(bArr[i + 2], bArr[i + 3])));
    }

    private static int unsignedByteToInt(byte b2) {
        return b2 & FlagsParser.UNKNOWN_FLAGS;
    }

    private static int unsignedBytesToInt(byte b2, byte b3) {
        return unsignedByteToInt(b2) + (unsignedByteToInt(b3) << 8);
    }

    private static String valueToString(int i) {
        return (i < 6 || i > 3200) ? i == 65535 ? "NOT SPEC" : String.format(Locale.US, "OUTSIDE THE RANGE (0x%04X)", Integer.valueOf(i)) : String.format(Locale.US, "%.02fms", Float.valueOf(i * 1.25f));
    }
}
