package no.nordicsemi.android.mcp.ble.parser.gap.servicedata;

import java.util.Locale;
import no.nordicsemi.android.mcp.ble.model.DataUnion;
import no.nordicsemi.android.mcp.ble.parser.gap.FlagsParser;

/* loaded from: classes.dex */
public class ByteParser {
    public static void parse(DataUnion dataUnion, byte[] bArr, int i, int i2) {
        dataUnion.addData("HEX", String.format(Locale.US, "0x%02X", Integer.valueOf(unsignedByteToInt(bArr[i]))));
        dataUnion.addData("Unsigned Int8", String.format(Locale.US, "%d", Integer.valueOf(unsignedByteToInt(bArr[i]))), 17);
        dataUnion.addData("Signed Int8", String.format(Locale.US, "%d", Integer.valueOf(unsignedToSigned(unsignedByteToInt(bArr[i]), 8))), 33);
    }

    private static int unsignedByteToInt(byte b2) {
        return b2 & FlagsParser.UNKNOWN_FLAGS;
    }

    private static int unsignedToSigned(int i, int i2) {
        int i3 = 1 << (i2 - 1);
        return (i & i3) != 0 ? (i3 - (i & (i3 - 1))) * (-1) : i;
    }
}
