package io.runtime.mcumgr.util;

import no.nordicsemi.android.mcp.ble.parser.gap.FlagsParser;

/* loaded from: classes.dex */
public class ByteUtil {
    public static final int BYTE_WIDTH = 1;
    public static final int INT_WIDTH = 4;
    public static final int LONG_WIDTH = 8;
    public static final int SHORT_WIDTH = 2;

    public static String byteArrayToHex(byte[] bArr) {
        return byteArrayToHex(bArr, 0, bArr.length, "%02x ");
    }

    public static int byteArrayToUnsignedInt(byte[] bArr) {
        return byteArrayToUnsignedInt(bArr, 0, Endian.BIG, 4);
    }

    public static long byteArrayToUnsignedLong(byte[] bArr) {
        return byteArrayToUnsignedLong(bArr, 0, Endian.BIG, 8);
    }

    public static int byteToUnsignedInt(byte b2) {
        return b2 & FlagsParser.UNKNOWN_FLAGS;
    }

    public static long byteToUnsignedLong(byte b2) {
        return b2 & FlagsParser.UNKNOWN_FLAGS;
    }

    public static String byteArrayToHex(byte[] bArr, String str) {
        return byteArrayToHex(bArr, 0, bArr.length, str);
    }

    public static int byteArrayToUnsignedInt(byte[] bArr, int i) {
        return byteArrayToUnsignedInt(bArr, i, Endian.BIG, 4);
    }

    public static long byteArrayToUnsignedLong(byte[] bArr, int i) {
        return byteArrayToUnsignedLong(bArr, i, Endian.BIG, 8);
    }

    public static String byteArrayToHex(byte[] bArr, int i, int i2, String str) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        while (i < i2) {
            sb.append(String.format(str, Byte.valueOf(bArr[i])));
            i++;
        }
        return sb.toString();
    }

    public static int byteArrayToUnsignedInt(byte[] bArr, int i, Endian endian) {
        return byteArrayToUnsignedInt(bArr, i, endian, 4);
    }

    public static long byteArrayToUnsignedLong(byte[] bArr, int i, Endian endian) {
        return byteArrayToUnsignedLong(bArr, i, endian, 8);
    }

    public static int byteArrayToUnsignedInt(byte[] bArr, int i, Endian endian, int i2) {
        if (i2 < 0 || i2 > 4) {
            throw new IllegalArgumentException("Length must be between 0 and 4 inclusive (length=" + i2 + ").");
        }
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            i3 += byteToUnsignedInt(bArr[i4 + i]) << ((endian == null || endian == Endian.BIG) ? ((i2 - 1) - i4) * 8 : i4 * 8);
        }
        return i3;
    }

    public static long byteArrayToUnsignedLong(byte[] bArr, int i, Endian endian, int i2) {
        if (i2 < 0 || i2 > 4) {
            throw new IllegalArgumentException("Length must be between 0 and 4 inclusive (length=" + i2 + ").");
        }
        long j = 0;
        for (int i3 = 0; i3 < i2; i3++) {
            j += byteToUnsignedInt(bArr[i3 + i]) << ((endian == null || endian == Endian.BIG) ? ((i2 - 1) - i3) * 8 : i3 * 8);
        }
        return j;
    }
}
