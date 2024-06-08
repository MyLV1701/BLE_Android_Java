package io.runtime.mcumgr.crash;

import io.runtime.mcumgr.util.ByteUtil;
import io.runtime.mcumgr.util.Endian;
import java.io.IOException;

/* loaded from: classes.dex */
public class CoreDumpHeader {
    private static final int OFFSET = 0;
    private int mMagic;
    private int mSize;

    public CoreDumpHeader(int i, int i2) {
        this.mMagic = i;
        this.mSize = i2;
    }

    public static CoreDumpHeader fromBytes(byte[] bArr) {
        return fromBytes(bArr, 0);
    }

    public int getMagic() {
        return this.mMagic;
    }

    public int getSize() {
        return this.mSize;
    }

    public static CoreDumpHeader fromBytes(byte[] bArr, int i) {
        int byteArrayToUnsignedInt = ByteUtil.byteArrayToUnsignedInt(bArr, i, Endian.LITTLE, 4);
        if (byteArrayToUnsignedInt == 1762412483) {
            return new CoreDumpHeader(byteArrayToUnsignedInt, ByteUtil.byteArrayToUnsignedInt(bArr, i + 4, Endian.LITTLE, 4));
        }
        throw new IOException("Illegal magic number: actual=" + String.format("0x%x", Integer.valueOf(byteArrayToUnsignedInt)) + ", expected=" + String.format("0x%x", 1762412483));
    }
}
