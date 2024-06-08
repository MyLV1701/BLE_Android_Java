package no.nordicsemi.android.mcp.ble.parser.gap.servicedata;

import java.util.Locale;
import no.nordicsemi.android.mcp.ble.model.DataUnion;
import no.nordicsemi.android.mcp.ble.parser.gap.FlagsParser;

/* loaded from: classes.dex */
public class TemperatureParser {
    private static final String TEMP = "Temperature";

    private static float float32ToFloat(byte b2, byte b3, byte b4, byte b5) {
        double unsignedToSigned = unsignedToSigned(unsignedByteToInt(b2) + (unsignedByteToInt(b3) << 8) + (unsignedByteToInt(b4) << 16), 24);
        double pow = Math.pow(10.0d, b5);
        Double.isNaN(unsignedToSigned);
        return (float) (unsignedToSigned * pow);
    }

    public static void parse(DataUnion dataUnion, byte[] bArr, int i, int i2) {
        if (i2 == 1) {
            dataUnion.addData(TEMP, String.format(Locale.US, "%d°C", Byte.valueOf(bArr[i])), 33);
        } else if (i2 == 2) {
            dataUnion.addData(TEMP, String.format(Locale.US, "%.2f°C", Float.valueOf(sint16ToFloat(bArr[i], bArr[i + 1]) / 100.0f)), 34, 0.01f);
        } else {
            if (i2 != 4) {
                return;
            }
            dataUnion.addData(TEMP, String.format(Locale.US, "%.2f°C", Float.valueOf(float32ToFloat(bArr[i], bArr[i + 1], bArr[i + 2], bArr[i + 3]))), 52);
        }
    }

    private static float sint16ToFloat(byte b2, byte b3) {
        return unsignedToSigned(unsignedBytesToInt(b2, b3), 16);
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
