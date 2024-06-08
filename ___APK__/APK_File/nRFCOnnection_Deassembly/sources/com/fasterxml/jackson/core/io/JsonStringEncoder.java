package com.fasterxml.jackson.core.io;

import androidx.recyclerview.widget.j;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import java.util.Arrays;
import no.nordicsemi.android.mcp.ble.parser.AppearanceLibrary;

/* loaded from: classes.dex */
public final class JsonStringEncoder {
    private static final char[] HC = CharTypes.copyHexChars();
    private static final byte[] HB = CharTypes.copyHexBytes();
    private static final JsonStringEncoder instance = new JsonStringEncoder();

    private int _appendByte(int i, int i2, ByteArrayBuilder byteArrayBuilder, int i3) {
        byteArrayBuilder.setCurrentSegmentLength(i3);
        byteArrayBuilder.append(92);
        if (i2 < 0) {
            byteArrayBuilder.append(117);
            if (i > 255) {
                int i4 = i >> 8;
                byteArrayBuilder.append(HB[i4 >> 4]);
                byteArrayBuilder.append(HB[i4 & 15]);
                i &= 255;
            } else {
                byteArrayBuilder.append(48);
                byteArrayBuilder.append(48);
            }
            byteArrayBuilder.append(HB[i >> 4]);
            byteArrayBuilder.append(HB[i & 15]);
        } else {
            byteArrayBuilder.append((byte) i2);
        }
        return byteArrayBuilder.getCurrentSegmentLength();
    }

    private int _appendNamed(int i, char[] cArr) {
        cArr[1] = (char) i;
        return 2;
    }

    private int _appendNumeric(int i, char[] cArr) {
        cArr[1] = 'u';
        char[] cArr2 = HC;
        cArr[4] = cArr2[i >> 4];
        cArr[5] = cArr2[i & 15];
        return 6;
    }

    private static int _convert(int i, int i2) {
        if (i2 >= 56320 && i2 <= 57343) {
            return ((i - 55296) << 10) + 65536 + (i2 - 56320);
        }
        throw new IllegalArgumentException("Broken surrogate pair: first char 0x" + Integer.toHexString(i) + ", second 0x" + Integer.toHexString(i2) + "; illegal combination");
    }

    private static void _illegal(int i) {
        throw new IllegalArgumentException(UTF8Writer.illegalSurrogateDesc(i));
    }

    private char[] _qbuf() {
        return new char[]{'\\', 0, '0', '0'};
    }

    public static JsonStringEncoder getInstance() {
        return instance;
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x00ef, code lost:
    
        if (r5 != null) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x00f5, code lost:
    
        return java.util.Arrays.copyOfRange(r2, 0, r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x00fa, code lost:
    
        return r5.completeAndCoalesce(r6);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public byte[] encodeAsUTF8(java.lang.String r12) {
        /*
            Method dump skipped, instructions count: 251
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.io.JsonStringEncoder.encodeAsUTF8(java.lang.String):byte[]");
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0026, code lost:
    
        r9 = r0 + 1;
        r0 = r13.charAt(r0);
        r10 = r1[r0];
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x002e, code lost:
    
        if (r10 >= 0) goto L13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0030, code lost:
    
        r0 = _appendNumeric(r0, r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0039, code lost:
    
        r10 = r6 + r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x003c, code lost:
    
        if (r10 <= r4.length) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x003e, code lost:
    
        r10 = r4.length - r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0040, code lost:
    
        if (r10 <= 0) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0042, code lost:
    
        java.lang.System.arraycopy(r8, 0, r4, r6, r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0045, code lost:
    
        if (r7 != null) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0047, code lost:
    
        r7 = com.fasterxml.jackson.core.util.TextBuffer.fromInitial(r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x004b, code lost:
    
        r4 = r7.finishCurrentSegment();
        r0 = r0 - r10;
        java.lang.System.arraycopy(r8, r10, r4, 0, r0);
        r6 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0055, code lost:
    
        java.lang.System.arraycopy(r8, 0, r4, r6, r0);
        r6 = r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0035, code lost:
    
        r0 = _appendNamed(r10, r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0020, code lost:
    
        if (r8 != null) goto L10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0022, code lost:
    
        r8 = _qbuf();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public char[] quoteAsString(java.lang.String r13) {
        /*
            r12 = this;
            r0 = 120(0x78, float:1.68E-43)
            char[] r0 = new char[r0]
            int[] r1 = com.fasterxml.jackson.core.io.CharTypes.get7BitOutputEscapes()
            int r2 = r1.length
            int r3 = r13.length()
            r4 = 0
            r5 = 0
            r7 = r4
            r8 = r7
            r6 = 0
            r4 = r0
            r0 = 0
        L14:
            if (r0 >= r3) goto L75
        L16:
            char r9 = r13.charAt(r0)
            if (r9 >= r2) goto L5b
            r10 = r1[r9]
            if (r10 == 0) goto L5b
            if (r8 != 0) goto L26
            char[] r8 = r12._qbuf()
        L26:
            int r9 = r0 + 1
            char r0 = r13.charAt(r0)
            r10 = r1[r0]
            if (r10 >= 0) goto L35
            int r0 = r12._appendNumeric(r0, r8)
            goto L39
        L35:
            int r0 = r12._appendNamed(r10, r8)
        L39:
            int r10 = r6 + r0
            int r11 = r4.length
            if (r10 <= r11) goto L55
            int r10 = r4.length
            int r10 = r10 - r6
            if (r10 <= 0) goto L45
            java.lang.System.arraycopy(r8, r5, r4, r6, r10)
        L45:
            if (r7 != 0) goto L4b
            com.fasterxml.jackson.core.util.TextBuffer r7 = com.fasterxml.jackson.core.util.TextBuffer.fromInitial(r4)
        L4b:
            char[] r4 = r7.finishCurrentSegment()
            int r0 = r0 - r10
            java.lang.System.arraycopy(r8, r10, r4, r5, r0)
            r6 = r0
            goto L59
        L55:
            java.lang.System.arraycopy(r8, r5, r4, r6, r0)
            r6 = r10
        L59:
            r0 = r9
            goto L14
        L5b:
            int r10 = r4.length
            if (r6 < r10) goto L69
            if (r7 != 0) goto L64
            com.fasterxml.jackson.core.util.TextBuffer r7 = com.fasterxml.jackson.core.util.TextBuffer.fromInitial(r4)
        L64:
            char[] r4 = r7.finishCurrentSegment()
            r6 = 0
        L69:
            int r10 = r6 + 1
            r4[r6] = r9
            int r0 = r0 + 1
            if (r0 < r3) goto L73
            r6 = r10
            goto L75
        L73:
            r6 = r10
            goto L16
        L75:
            if (r7 != 0) goto L7c
            char[] r13 = java.util.Arrays.copyOfRange(r4, r5, r6)
            return r13
        L7c:
            r7.setCurrentLength(r6)
            char[] r13 = r7.contentsAsArray()
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.io.JsonStringEncoder.quoteAsString(java.lang.String):char[]");
    }

    public byte[] quoteAsUTF8(String str) {
        int i;
        int i2;
        int length = str.length();
        byte[] bArr = new byte[j.f.DEFAULT_DRAG_ANIMATION_DURATION];
        ByteArrayBuilder byteArrayBuilder = null;
        int i3 = 0;
        int i4 = 0;
        loop0: while (true) {
            if (i3 >= length) {
                break;
            }
            int[] iArr = CharTypes.get7BitOutputEscapes();
            while (true) {
                char charAt = str.charAt(i3);
                if (charAt > 127 || iArr[charAt] != 0) {
                    break;
                }
                if (i4 >= bArr.length) {
                    if (byteArrayBuilder == null) {
                        byteArrayBuilder = ByteArrayBuilder.fromInitial(bArr, i4);
                    }
                    bArr = byteArrayBuilder.finishCurrentSegment();
                    i4 = 0;
                }
                int i5 = i4 + 1;
                bArr[i4] = (byte) charAt;
                i3++;
                if (i3 >= length) {
                    i4 = i5;
                    break loop0;
                }
                i4 = i5;
            }
            if (byteArrayBuilder == null) {
                byteArrayBuilder = ByteArrayBuilder.fromInitial(bArr, i4);
            }
            if (i4 >= bArr.length) {
                bArr = byteArrayBuilder.finishCurrentSegment();
                i4 = 0;
            }
            int i6 = i3 + 1;
            char charAt2 = str.charAt(i3);
            if (charAt2 <= 127) {
                i4 = _appendByte(charAt2, iArr[charAt2], byteArrayBuilder, i4);
                bArr = byteArrayBuilder.getCurrentSegment();
            } else {
                if (charAt2 <= 2047) {
                    bArr[i4] = (byte) ((charAt2 >> 6) | AppearanceLibrary.APPEARANCE_GENERIC_WATCH);
                    i2 = (charAt2 & '?') | 128;
                    i = i4 + 1;
                } else if (charAt2 < 55296 || charAt2 > 57343) {
                    int i7 = i4 + 1;
                    bArr[i4] = (byte) ((charAt2 >> '\f') | 224);
                    if (i7 >= bArr.length) {
                        bArr = byteArrayBuilder.finishCurrentSegment();
                        i7 = 0;
                    }
                    i = i7 + 1;
                    bArr[i7] = (byte) (((charAt2 >> 6) & 63) | 128);
                    i2 = (charAt2 & '?') | 128;
                } else {
                    if (charAt2 > 56319) {
                        _illegal(charAt2);
                        throw null;
                    }
                    if (i6 < length) {
                        int i8 = i6 + 1;
                        int _convert = _convert(charAt2, str.charAt(i6));
                        if (_convert <= 1114111) {
                            int i9 = i4 + 1;
                            bArr[i4] = (byte) ((_convert >> 18) | 240);
                            if (i9 >= bArr.length) {
                                bArr = byteArrayBuilder.finishCurrentSegment();
                                i9 = 0;
                            }
                            int i10 = i9 + 1;
                            bArr[i9] = (byte) (((_convert >> 12) & 63) | 128);
                            if (i10 >= bArr.length) {
                                bArr = byteArrayBuilder.finishCurrentSegment();
                                i10 = 0;
                            }
                            bArr[i10] = (byte) (((_convert >> 6) & 63) | 128);
                            i2 = (_convert & 63) | 128;
                            i = i10 + 1;
                            i6 = i8;
                        } else {
                            _illegal(_convert);
                            throw null;
                        }
                    } else {
                        _illegal(charAt2);
                        throw null;
                    }
                }
                if (i >= bArr.length) {
                    bArr = byteArrayBuilder.finishCurrentSegment();
                    i = 0;
                }
                bArr[i] = (byte) i2;
                i4 = i + 1;
            }
            i3 = i6;
        }
        if (byteArrayBuilder == null) {
            return Arrays.copyOfRange(bArr, 0, i4);
        }
        return byteArrayBuilder.completeAndCoalesce(i4);
    }
}
