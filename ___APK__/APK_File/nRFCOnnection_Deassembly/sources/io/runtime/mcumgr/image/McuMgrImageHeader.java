package io.runtime.mcumgr.image;

import io.runtime.mcumgr.exception.McuMgrException;
import io.runtime.mcumgr.util.ByteUtil;
import io.runtime.mcumgr.util.Endian;

/* loaded from: classes.dex */
public class McuMgrImageHeader {
    private static final int HEADER_LENGTH = 24;
    private static final int IMG_HEADER_MAGIC = -1762412483;
    private static final int IMG_HEADER_MAGIC_V1 = -1762412484;
    private short __mPad1;
    private int __mPad2;
    private int mFlags;
    private short mHdrSize;
    private int mImgSize;
    private int mLoadAddr;
    private int mMagic;
    private McuMgrImageVersion mVersion;

    private McuMgrImageHeader() {
    }

    public static McuMgrImageHeader fromBytes(byte[] bArr) {
        return fromBytes(bArr, 0);
    }

    public static int getSize() {
        return McuMgrImageVersion.getSize() + 24;
    }

    public int getFlags() {
        return this.mFlags;
    }

    public short getHdrSize() {
        return this.mHdrSize;
    }

    public int getImgSize() {
        return this.mImgSize;
    }

    public int getLoadAddr() {
        return this.mLoadAddr;
    }

    public int getMagic() {
        return this.mMagic;
    }

    public McuMgrImageVersion getVersion() {
        return this.mVersion;
    }

    public boolean isLegacy() {
        return this.mMagic == IMG_HEADER_MAGIC_V1;
    }

    public static McuMgrImageHeader fromBytes(byte[] bArr, int i) {
        if (bArr.length - i >= getSize()) {
            McuMgrImageHeader mcuMgrImageHeader = new McuMgrImageHeader();
            mcuMgrImageHeader.mMagic = ByteUtil.byteArrayToUnsignedInt(bArr, i, Endian.LITTLE, 4);
            int i2 = mcuMgrImageHeader.mMagic;
            if (i2 != IMG_HEADER_MAGIC && i2 != IMG_HEADER_MAGIC_V1) {
                throw new McuMgrException("Wrong magic number: header=" + mcuMgrImageHeader.mMagic + ", magic=" + IMG_HEADER_MAGIC + " or " + IMG_HEADER_MAGIC_V1);
            }
            mcuMgrImageHeader.mLoadAddr = ByteUtil.byteArrayToUnsignedInt(bArr, i + 4, Endian.LITTLE, 4);
            mcuMgrImageHeader.mHdrSize = (short) ByteUtil.byteArrayToUnsignedInt(bArr, i + 8, Endian.LITTLE, 2);
            mcuMgrImageHeader.mImgSize = ByteUtil.byteArrayToUnsignedInt(bArr, i + 12, Endian.LITTLE, 4);
            mcuMgrImageHeader.mFlags = ByteUtil.byteArrayToUnsignedInt(bArr, i + 16, Endian.LITTLE, 4);
            mcuMgrImageHeader.mVersion = McuMgrImageVersion.fromBytes(bArr, i + 20);
            return mcuMgrImageHeader;
        }
        throw new McuMgrException("The byte array is too short to be a McuMgrImageHeader");
    }
}
