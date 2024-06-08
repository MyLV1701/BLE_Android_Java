package com.fasterxml.jackson.dataformat.cbor;

/* loaded from: classes.dex */
public final class CBORConstants {
    public static final int[] sUtf8UnitLengths;

    static {
        int[] iArr = new int[256];
        for (int i = 128; i < 256; i++) {
            iArr[i] = (i & 224) == 192 ? 1 : (i & 240) == 224 ? 2 : (i & 248) == 240 ? 3 : -1;
        }
        sUtf8UnitLengths = iArr;
    }
}
