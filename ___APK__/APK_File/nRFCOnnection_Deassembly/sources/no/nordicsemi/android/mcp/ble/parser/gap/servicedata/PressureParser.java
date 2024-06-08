package no.nordicsemi.android.mcp.ble.parser.gap.servicedata;

import java.util.Locale;
import no.nordicsemi.android.mcp.ble.model.DataUnion;
import no.nordicsemi.android.mcp.ble.parser.gap.FlagsParser;

/* loaded from: classes.dex */
public class PressureParser {
    public static void parse(DataUnion dataUnion, byte[] bArr, int i, int i2) {
        float unsignedBytesToInt;
        boolean z;
        if (i2 != 4) {
            z = false;
            unsignedBytesToInt = 0.0f;
        } else {
            unsignedBytesToInt = unsignedBytesToInt(bArr[i], bArr[i + 1], bArr[i + 2], bArr[i + 3]) / 10.0f;
            z = true;
        }
        if (z) {
            dataUnion.addData("Pressure", String.format(Locale.US, "%.3f hPa", Float.valueOf(unsignedBytesToInt / 100.0f)), 20, 0.001f);
        }
    }

    private static int unsignedByteToInt(byte b2) {
        return b2 & FlagsParser.UNKNOWN_FLAGS;
    }

    private static int unsignedBytesToInt(byte b2, byte b3, byte b4, byte b5) {
        return unsignedByteToInt(b2) + (unsignedByteToInt(b3) << 8) + (unsignedByteToInt(b4) << 16) + (unsignedByteToInt(b5) << 24);
    }
}
