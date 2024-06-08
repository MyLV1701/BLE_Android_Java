package no.nordicsemi.android.mcp.ble.parser.gatt;

import java.math.BigDecimal;
import java.math.MathContext;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import no.nordicsemi.android.mcp.ble.parser.utils.ParserUtils;

/* loaded from: classes.dex */
public class FormatParser {
    public static String name(int i) {
        switch (i) {
            case 1:
                return "Boolean";
            case 2:
                return "unsigned 2-bit integer";
            case 3:
                return "unsigned 4-bit integer";
            case 4:
                return "unsigned 8-bit integer";
            case 5:
                return "unsigned 12-bit integer";
            case 6:
                return "unsigned 16-bit integer";
            case 7:
                return "unsigned 24-bit integer";
            case 8:
                return "unsigned 32-bit integer";
            case 9:
                return "unsigned 48-bit integer";
            case 10:
                return "unsigned 64-bit integer";
            case 11:
                return "unsigned 128-bit integer";
            case 12:
                return "signed 8-bit integer";
            case 13:
                return "signed 12-bit integer";
            case 14:
                return "signed 16-bit integer";
            case 15:
                return "signed 24-bit integer";
            case 16:
                return "signed 32-bit integer";
            case 17:
                return "signed 48-bit integer";
            case 18:
                return "signed 64-bit integer";
            case 19:
                return "signed 128-bit integer";
            case 20:
                return "IEEE-754 32-bit floating point";
            case 21:
                return "IEEE-754 64-bit floating point";
            case 22:
                return "IEEE-11073 16-bit SFLOAT";
            case 23:
                return "IEEE-11073 32-bit FLOAT";
            case 24:
                return "IEEE-20601 format";
            case 25:
                return "UTF-8 string";
            case 26:
                return "UTF-16 string";
            case 27:
                return "Opaque Structure";
            default:
                return "Format reserved for future use (" + i + ")";
        }
    }

    public static String parse(byte[] bArr, int i, int i2) {
        switch (i) {
            case 1:
                return bArr[0] != 0 ? "True" : "False";
            case 2:
                return pow(bArr[0] & 3, i2);
            case 3:
                return pow(ParserUtils.getIntValue(bArr, 66, 0), i2);
            case 4:
                return pow(ParserUtils.getIntValue(bArr, 17, 0), i2);
            case 5:
                return pow(ParserUtils.getIntValue(bArr, 66, 0), i2);
            case 6:
                return pow(ParserUtils.getIntValue(bArr, 18, 0), i2);
            case 7:
                return pow(ParserUtils.getIntValue(bArr, 19, 0), i2);
            case 8:
                return pow(ParserUtils.getLongValue(bArr, 20, 0), i2);
            case 9:
                return pow(ParserUtils.getLongValue(bArr, 22, 0), i2);
            case 10:
            case 11:
            case 18:
            case 19:
            default:
                return null;
            case 12:
                return pow(ParserUtils.getIntValue(bArr, 33, 0), i2);
            case 13:
                return pow(ParserUtils.getIntValue(bArr, 82, 0), i2);
            case 14:
                return pow(ParserUtils.getIntValue(bArr, 34, 0), i2);
            case 15:
                return pow(ParserUtils.getIntValue(bArr, 34, 0), i2);
            case 16:
                return pow(ParserUtils.getIntValue(bArr, 36, 0), i2);
            case 17:
                return pow(ParserUtils.getLongValue(bArr, 38, 0), i2);
            case 20:
                ByteBuffer wrap = ByteBuffer.wrap(bArr);
                wrap.order(ByteOrder.LITTLE_ENDIAN);
                return pow(wrap.getFloat(), i2);
            case 21:
                ByteBuffer wrap2 = ByteBuffer.wrap(bArr);
                wrap2.order(ByteOrder.LITTLE_ENDIAN);
                return pow(wrap2.getDouble(), i2);
            case 22:
                return pow(ParserUtils.getMantissa(bArr, 50, 0), -ParserUtils.getExponent(bArr, 50, 0), i2);
            case 23:
                return pow(ParserUtils.getMantissa(bArr, 52, 0), -ParserUtils.getExponent(bArr, 52, 0), i2);
            case 24:
                return null;
            case 25:
                return new String(bArr, Charset.forName("UTF-8"));
            case 26:
                return new String(bArr, Charset.forName("UTF-16"));
        }
    }

    private static String pow(long j, int i) {
        return BigDecimal.valueOf(j).scaleByPowerOfTen(i).toPlainString();
    }

    private static String pow(float f2, int i) {
        return BigDecimal.valueOf(f2).round(MathContext.DECIMAL32).scaleByPowerOfTen(i).stripTrailingZeros().toPlainString();
    }

    private static String pow(double d2, int i) {
        return BigDecimal.valueOf(d2).round(MathContext.DECIMAL64).scaleByPowerOfTen(i).stripTrailingZeros().toPlainString();
    }

    private static String pow(int i, int i2, int i3) {
        return BigDecimal.valueOf(i, i2).scaleByPowerOfTen(i3).toPlainString();
    }
}
