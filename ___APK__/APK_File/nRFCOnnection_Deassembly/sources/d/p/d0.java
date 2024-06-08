package d.p;

import no.nordicsemi.android.mcp.ble.parser.gap.FlagsParser;

/* loaded from: classes.dex */
public final class d0 {
    public static int a(byte b2, byte b3) {
        return (b2 & FlagsParser.UNKNOWN_FLAGS) | ((b3 & FlagsParser.UNKNOWN_FLAGS) << 8);
    }

    public static int a(byte b2, byte b3, byte b4, byte b5) {
        return a(b2, b3) | (a(b4, b5) << 16);
    }

    public static short b(byte b2, byte b3) {
        return (short) (((short) (b2 & FlagsParser.UNKNOWN_FLAGS)) | (((short) (b3 & FlagsParser.UNKNOWN_FLAGS)) << 8));
    }

    public static void b(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) (i & 255);
        bArr[i2 + 1] = (byte) ((i & 65280) >> 8);
    }

    public static byte[] a(int i) {
        byte[] bArr = new byte[4];
        b(65535 & i, bArr, 0);
        b((i & (-65536)) >> 16, bArr, 2);
        return bArr;
    }

    public static void a(int i, byte[] bArr, int i2) {
        byte[] a2 = a(i);
        bArr[i2] = a2[0];
        bArr[i2 + 1] = a2[1];
        bArr[i2 + 2] = a2[2];
        bArr[i2 + 3] = a2[3];
    }
}
