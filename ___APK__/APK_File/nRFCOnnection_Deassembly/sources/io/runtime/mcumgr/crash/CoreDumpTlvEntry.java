package io.runtime.mcumgr.crash;

import io.runtime.mcumgr.util.ByteUtil;
import io.runtime.mcumgr.util.Endian;
import java.io.IOException;
import java.util.Arrays;

/* loaded from: classes.dex */
public class CoreDumpTlvEntry {
    private static final int MIN_SIZE = 8;
    private int mLength;
    private long mOff;
    private int mType;
    private byte[] mValue;

    public CoreDumpTlvEntry(int i, int i2, long j, byte[] bArr) {
        this.mType = i;
        this.mLength = i2;
        this.mOff = j;
        this.mValue = bArr;
    }

    public static CoreDumpTlvEntry fromBytes(byte[] bArr, int i) {
        int i2 = i + 8;
        if (i2 <= bArr.length) {
            int byteToUnsignedInt = ByteUtil.byteToUnsignedInt(bArr[i]);
            int byteArrayToUnsignedInt = ByteUtil.byteArrayToUnsignedInt(bArr, i + 2, Endian.LITTLE, 2);
            long byteArrayToUnsignedLong = ByteUtil.byteArrayToUnsignedLong(bArr, i + 4, Endian.LITTLE, 4);
            int i3 = i2 + byteArrayToUnsignedInt;
            if (i3 <= bArr.length) {
                return new CoreDumpTlvEntry(byteToUnsignedInt, byteArrayToUnsignedInt, byteArrayToUnsignedLong, Arrays.copyOfRange(bArr, i2, i3));
            }
            throw new IOException("Insufficient data. TLV Value length out of bounds. (data length=" + bArr.length + ", offset=" + i + ", entry length=" + byteArrayToUnsignedInt + ").");
        }
        throw new IOException("Insufficient data. TLV entry requires at least 8 bytes. (length=" + bArr.length + ", offset=" + i + ").");
    }

    public int getLength() {
        return this.mLength;
    }

    public long getOff() {
        return this.mOff;
    }

    public int getSize() {
        return this.mLength + 8;
    }

    public int getType() {
        return this.mType;
    }

    public byte[] getValue() {
        return this.mValue;
    }

    public String toString() {
        return String.format("{type=%s, length=%s, off=%s, value=%s}", Integer.valueOf(this.mType), Integer.valueOf(this.mLength), Long.valueOf(this.mOff), ByteUtil.byteArrayToHex(this.mValue));
    }
}
