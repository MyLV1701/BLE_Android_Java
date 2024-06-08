package io.runtime.mcumgr.image;

import io.runtime.mcumgr.exception.McuMgrException;
import io.runtime.mcumgr.util.ByteUtil;
import io.runtime.mcumgr.util.Endian;

/* loaded from: classes.dex */
public class McuMgrImageVersion {
    private int mBuildNum;
    private byte mMajor;
    private byte mMinor;
    private short mRevision;

    private McuMgrImageVersion() {
    }

    public static McuMgrImageVersion fromBytes(byte[] bArr) {
        return fromBytes(bArr, 0);
    }

    public static int getSize() {
        return 8;
    }

    public int getBuildNum() {
        return this.mBuildNum;
    }

    public byte getMajor() {
        return this.mMajor;
    }

    public byte getMinor() {
        return this.mMinor;
    }

    public short getRevision() {
        return this.mRevision;
    }

    public static McuMgrImageVersion fromBytes(byte[] bArr, int i) {
        if (bArr.length - i >= getSize()) {
            McuMgrImageVersion mcuMgrImageVersion = new McuMgrImageVersion();
            int i2 = i + 1;
            mcuMgrImageVersion.mMajor = bArr[i];
            int i3 = i2 + 1;
            mcuMgrImageVersion.mMinor = bArr[i2];
            mcuMgrImageVersion.mRevision = (short) ByteUtil.byteArrayToUnsignedInt(bArr, i3, Endian.LITTLE, 2);
            mcuMgrImageVersion.mBuildNum = ByteUtil.byteArrayToUnsignedInt(bArr, i3 + 2, Endian.LITTLE, 4);
            return mcuMgrImageVersion;
        }
        throw new McuMgrException("The byte array is too short to be a McuMgrImageVersion");
    }
}
