package no.nordicsemi.android.mcp.ble.parser.gap;

import no.nordicsemi.android.mcp.ble.model.DataUnion;

/* loaded from: classes.dex */
public class AdvertisingIntervalParser {
    private static short decodeUint16(byte[] bArr, int i) {
        return (short) (((bArr[i + 1] & FlagsParser.UNKNOWN_FLAGS) << 8) | (bArr[i] & FlagsParser.UNKNOWN_FLAGS));
    }

    public static void parse(DataUnion dataUnion, byte[] bArr, int i, int i2) {
        dataUnion.addData("Advertising Interval", (decodeUint16(bArr, i) * 0.625f) + " ms");
    }
}
