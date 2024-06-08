package no.nordicsemi.android.mcp.ble.parser.gap.servicedata;

import java.util.Locale;
import no.nordicsemi.android.mcp.ble.model.DataUnion;
import no.nordicsemi.android.mcp.ble.parser.gap.FlagsParser;

/* loaded from: classes.dex */
public class FloatParser {
    private static float bytesToFloat(byte b2, byte b3) {
        double unsignedToSigned = unsignedToSigned(unsignedByteToInt(b2) + ((unsignedByteToInt(b3) & 15) << 8), 12);
        double pow = Math.pow(10.0d, unsignedToSigned(unsignedByteToInt(b3) >> 4, 4));
        Double.isNaN(unsignedToSigned);
        return (float) (unsignedToSigned * pow);
    }

    private static float decodeFloat16(byte[] bArr, int i) {
        return bytesToFloat(bArr[i], bArr[i + 1]);
    }

    private static float decodeFloat32(byte[] bArr, int i) {
        return bytesToFloat(bArr[i], bArr[i + 1], bArr[i + 2], bArr[i + 3]);
    }

    public static void parse(DataUnion dataUnion, byte[] bArr, int i, int i2) {
        if (i2 == 4) {
            dataUnion.addData("Float32", String.format(Locale.US, "%.2f", Float.valueOf(decodeFloat32(bArr, i))), 52);
        } else if (i2 == 2) {
            dataUnion.addData("Float16", String.format(Locale.US, "%.2f", Float.valueOf(decodeFloat16(bArr, i))), 50);
        }
    }

    private static int unsignedByteToInt(byte b2) {
        return b2 & FlagsParser.UNKNOWN_FLAGS;
    }

    private static int unsignedToSigned(int i, int i2) {
        int i3 = 1 << (i2 - 1);
        return (i & i3) != 0 ? (i3 - (i & (i3 - 1))) * (-1) : i;
    }

    private static float bytesToFloat(byte b2, byte b3, byte b4, byte b5) {
        double unsignedToSigned = unsignedToSigned(unsignedByteToInt(b2) + (unsignedByteToInt(b3) << 8) + (unsignedByteToInt(b4) << 16), 24);
        double pow = Math.pow(10.0d, b5);
        Double.isNaN(unsignedToSigned);
        return (float) (unsignedToSigned * pow);
    }
}
