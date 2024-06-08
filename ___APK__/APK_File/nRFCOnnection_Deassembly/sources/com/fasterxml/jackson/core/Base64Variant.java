package com.fasterxml.jackson.core;

import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import java.io.Serializable;
import java.util.Arrays;
import no.nordicsemi.android.mcp.ble.parser.gap.FlagsParser;

/* loaded from: classes.dex */
public final class Base64Variant implements Serializable {
    private final transient int[] _asciiToBase64;
    private final transient byte[] _base64ToAsciiB;
    private final transient char[] _base64ToAsciiC;
    private final transient int _maxLineLength;
    final String _name;
    private final transient char _paddingChar;
    private final transient boolean _usesPadding;

    public Base64Variant(String str, String str2, boolean z, char c2, int i) {
        this._asciiToBase64 = new int[128];
        this._base64ToAsciiC = new char[64];
        this._base64ToAsciiB = new byte[64];
        this._name = str;
        this._usesPadding = z;
        this._paddingChar = c2;
        this._maxLineLength = i;
        int length = str2.length();
        if (length == 64) {
            str2.getChars(0, length, this._base64ToAsciiC, 0);
            Arrays.fill(this._asciiToBase64, -1);
            for (int i2 = 0; i2 < length; i2++) {
                char c3 = this._base64ToAsciiC[i2];
                this._base64ToAsciiB[i2] = (byte) c3;
                this._asciiToBase64[c3] = i2;
            }
            if (z) {
                this._asciiToBase64[c2] = -2;
                return;
            }
            return;
        }
        throw new IllegalArgumentException("Base64Alphabet length must be exactly 64 (was " + length + ")");
    }

    protected void _reportBase64EOF() {
        throw new IllegalArgumentException(missingPaddingMessage());
    }

    protected void _reportInvalidBase64(char c2, int i, String str) {
        String str2;
        if (c2 <= ' ') {
            str2 = "Illegal white space character (code 0x" + Integer.toHexString(c2) + ") as character #" + (i + 1) + " of 4-char base64 unit: can only used between units";
        } else if (usesPaddingChar(c2)) {
            str2 = "Unexpected padding character ('" + getPaddingChar() + "') as character #" + (i + 1) + " of 4-char base64 unit: padding only legal as 3rd or 4th character";
        } else if (Character.isDefined(c2) && !Character.isISOControl(c2)) {
            str2 = "Illegal character '" + c2 + "' (code 0x" + Integer.toHexString(c2) + ") in base64 content";
        } else {
            str2 = "Illegal character (code 0x" + Integer.toHexString(c2) + ") in base64 content";
        }
        if (str != null) {
            str2 = str2 + ": " + str;
        }
        throw new IllegalArgumentException(str2);
    }

    public byte[] decode(String str) {
        ByteArrayBuilder byteArrayBuilder = new ByteArrayBuilder();
        decode(str, byteArrayBuilder);
        return byteArrayBuilder.toByteArray();
    }

    public int decodeBase64Char(char c2) {
        if (c2 <= 127) {
            return this._asciiToBase64[c2];
        }
        return -1;
    }

    public String encode(byte[] bArr) {
        return encode(bArr, false);
    }

    public int encodeBase64Chunk(int i, char[] cArr, int i2) {
        int i3 = i2 + 1;
        char[] cArr2 = this._base64ToAsciiC;
        cArr[i2] = cArr2[(i >> 18) & 63];
        int i4 = i3 + 1;
        cArr[i3] = cArr2[(i >> 12) & 63];
        int i5 = i4 + 1;
        cArr[i4] = cArr2[(i >> 6) & 63];
        int i6 = i5 + 1;
        cArr[i5] = cArr2[i & 63];
        return i6;
    }

    public int encodeBase64Partial(int i, int i2, char[] cArr, int i3) {
        int i4 = i3 + 1;
        char[] cArr2 = this._base64ToAsciiC;
        cArr[i3] = cArr2[(i >> 18) & 63];
        int i5 = i4 + 1;
        cArr[i4] = cArr2[(i >> 12) & 63];
        if (this._usesPadding) {
            int i6 = i5 + 1;
            cArr[i5] = i2 == 2 ? cArr2[(i >> 6) & 63] : this._paddingChar;
            int i7 = i6 + 1;
            cArr[i6] = this._paddingChar;
            return i7;
        }
        if (i2 != 2) {
            return i5;
        }
        int i8 = i5 + 1;
        cArr[i5] = cArr2[(i >> 6) & 63];
        return i8;
    }

    public boolean equals(Object obj) {
        return obj == this;
    }

    public int getMaxLineLength() {
        return this._maxLineLength;
    }

    public String getName() {
        return this._name;
    }

    public char getPaddingChar() {
        return this._paddingChar;
    }

    public int hashCode() {
        return this._name.hashCode();
    }

    public String missingPaddingMessage() {
        return String.format("Unexpected end of base64-encoded String: base64 variant '%s' expects padding (one or more '%c' characters) at the end", getName(), Character.valueOf(getPaddingChar()));
    }

    public String toString() {
        return this._name;
    }

    public boolean usesPadding() {
        return this._usesPadding;
    }

    public boolean usesPaddingChar(char c2) {
        return c2 == this._paddingChar;
    }

    public int decodeBase64Char(int i) {
        if (i <= 127) {
            return this._asciiToBase64[i];
        }
        return -1;
    }

    public String encode(byte[] bArr, boolean z) {
        int length = bArr.length;
        StringBuilder sb = new StringBuilder((length >> 2) + length + (length >> 3));
        if (z) {
            sb.append('\"');
        }
        int maxLineLength = getMaxLineLength() >> 2;
        int i = 0;
        int i2 = length - 3;
        while (i <= i2) {
            int i3 = i + 1;
            int i4 = i3 + 1;
            int i5 = ((bArr[i] << 8) | (bArr[i3] & FlagsParser.UNKNOWN_FLAGS)) << 8;
            int i6 = i4 + 1;
            encodeBase64Chunk(sb, i5 | (bArr[i4] & FlagsParser.UNKNOWN_FLAGS));
            maxLineLength--;
            if (maxLineLength <= 0) {
                sb.append('\\');
                sb.append('n');
                maxLineLength = getMaxLineLength() >> 2;
            }
            i = i6;
        }
        int i7 = length - i;
        if (i7 > 0) {
            int i8 = i + 1;
            int i9 = bArr[i] << 16;
            if (i7 == 2) {
                i9 |= (bArr[i8] & FlagsParser.UNKNOWN_FLAGS) << 8;
            }
            encodeBase64Partial(sb, i9, i7);
        }
        if (z) {
            sb.append('\"');
        }
        return sb.toString();
    }

    public boolean usesPaddingChar(int i) {
        return i == this._paddingChar;
    }

    public void decode(String str, ByteArrayBuilder byteArrayBuilder) {
        int length = str.length();
        int i = 0;
        while (i < length) {
            int i2 = i + 1;
            char charAt = str.charAt(i);
            if (charAt > ' ') {
                int decodeBase64Char = decodeBase64Char(charAt);
                if (decodeBase64Char < 0) {
                    _reportInvalidBase64(charAt, 0, null);
                    throw null;
                }
                if (i2 < length) {
                    int i3 = i2 + 1;
                    char charAt2 = str.charAt(i2);
                    int decodeBase64Char2 = decodeBase64Char(charAt2);
                    if (decodeBase64Char2 < 0) {
                        _reportInvalidBase64(charAt2, 1, null);
                        throw null;
                    }
                    int i4 = (decodeBase64Char << 6) | decodeBase64Char2;
                    if (i3 >= length) {
                        if (!usesPadding()) {
                            byteArrayBuilder.append(i4 >> 4);
                            return;
                        } else {
                            _reportBase64EOF();
                            throw null;
                        }
                    }
                    int i5 = i3 + 1;
                    char charAt3 = str.charAt(i3);
                    int decodeBase64Char3 = decodeBase64Char(charAt3);
                    if (decodeBase64Char3 >= 0) {
                        int i6 = (i4 << 6) | decodeBase64Char3;
                        if (i5 >= length) {
                            if (!usesPadding()) {
                                byteArrayBuilder.appendTwoBytes(i6 >> 2);
                                return;
                            } else {
                                _reportBase64EOF();
                                throw null;
                            }
                        }
                        i2 = i5 + 1;
                        char charAt4 = str.charAt(i5);
                        int decodeBase64Char4 = decodeBase64Char(charAt4);
                        if (decodeBase64Char4 >= 0) {
                            byteArrayBuilder.appendThreeBytes((i6 << 6) | decodeBase64Char4);
                        } else if (decodeBase64Char4 == -2) {
                            byteArrayBuilder.appendTwoBytes(i6 >> 2);
                        } else {
                            _reportInvalidBase64(charAt4, 3, null);
                            throw null;
                        }
                    } else {
                        if (decodeBase64Char3 != -2) {
                            _reportInvalidBase64(charAt3, 2, null);
                            throw null;
                        }
                        if (i5 < length) {
                            i = i5 + 1;
                            char charAt5 = str.charAt(i5);
                            if (usesPaddingChar(charAt5)) {
                                byteArrayBuilder.append(i4 >> 4);
                            } else {
                                _reportInvalidBase64(charAt5, 3, "expected padding character '" + getPaddingChar() + "'");
                                throw null;
                            }
                        } else {
                            _reportBase64EOF();
                            throw null;
                        }
                    }
                } else {
                    _reportBase64EOF();
                    throw null;
                }
            }
            i = i2;
        }
    }

    public void encodeBase64Chunk(StringBuilder sb, int i) {
        sb.append(this._base64ToAsciiC[(i >> 18) & 63]);
        sb.append(this._base64ToAsciiC[(i >> 12) & 63]);
        sb.append(this._base64ToAsciiC[(i >> 6) & 63]);
        sb.append(this._base64ToAsciiC[i & 63]);
    }

    public void encodeBase64Partial(StringBuilder sb, int i, int i2) {
        sb.append(this._base64ToAsciiC[(i >> 18) & 63]);
        sb.append(this._base64ToAsciiC[(i >> 12) & 63]);
        if (this._usesPadding) {
            sb.append(i2 == 2 ? this._base64ToAsciiC[(i >> 6) & 63] : this._paddingChar);
            sb.append(this._paddingChar);
        } else if (i2 == 2) {
            sb.append(this._base64ToAsciiC[(i >> 6) & 63]);
        }
    }

    public int encodeBase64Chunk(int i, byte[] bArr, int i2) {
        int i3 = i2 + 1;
        byte[] bArr2 = this._base64ToAsciiB;
        bArr[i2] = bArr2[(i >> 18) & 63];
        int i4 = i3 + 1;
        bArr[i3] = bArr2[(i >> 12) & 63];
        int i5 = i4 + 1;
        bArr[i4] = bArr2[(i >> 6) & 63];
        int i6 = i5 + 1;
        bArr[i5] = bArr2[i & 63];
        return i6;
    }

    public int encodeBase64Partial(int i, int i2, byte[] bArr, int i3) {
        int i4 = i3 + 1;
        byte[] bArr2 = this._base64ToAsciiB;
        bArr[i3] = bArr2[(i >> 18) & 63];
        int i5 = i4 + 1;
        bArr[i4] = bArr2[(i >> 12) & 63];
        if (!this._usesPadding) {
            if (i2 != 2) {
                return i5;
            }
            int i6 = i5 + 1;
            bArr[i5] = bArr2[(i >> 6) & 63];
            return i6;
        }
        byte b2 = (byte) this._paddingChar;
        int i7 = i5 + 1;
        bArr[i5] = i2 == 2 ? bArr2[(i >> 6) & 63] : b2;
        int i8 = i7 + 1;
        bArr[i7] = b2;
        return i8;
    }

    public Base64Variant(Base64Variant base64Variant, String str, int i) {
        this(base64Variant, str, base64Variant._usesPadding, base64Variant._paddingChar, i);
    }

    public Base64Variant(Base64Variant base64Variant, String str, boolean z, char c2, int i) {
        this._asciiToBase64 = new int[128];
        this._base64ToAsciiC = new char[64];
        this._base64ToAsciiB = new byte[64];
        this._name = str;
        byte[] bArr = base64Variant._base64ToAsciiB;
        System.arraycopy(bArr, 0, this._base64ToAsciiB, 0, bArr.length);
        char[] cArr = base64Variant._base64ToAsciiC;
        System.arraycopy(cArr, 0, this._base64ToAsciiC, 0, cArr.length);
        int[] iArr = base64Variant._asciiToBase64;
        System.arraycopy(iArr, 0, this._asciiToBase64, 0, iArr.length);
        this._usesPadding = z;
        this._paddingChar = c2;
        this._maxLineLength = i;
    }
}
