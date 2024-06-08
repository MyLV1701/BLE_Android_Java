package io.runtime.mcumgr;

import io.runtime.mcumgr.util.ByteUtil;
import io.runtime.mcumgr.util.Endian;

/* loaded from: classes.dex */
public class McuMgrHeader {
    public static final int HEADER_LENGTH = 8;
    private int mCommandId;
    private int mFlags;
    private int mGroupId;
    private int mLen;
    private int mOp;
    private int mSequenceNum;

    public McuMgrHeader(int i, int i2, int i3, int i4, int i5, int i6) {
        this.mOp = i;
        this.mFlags = i2;
        this.mLen = i3;
        this.mGroupId = i4;
        this.mSequenceNum = i5;
        this.mCommandId = i6;
    }

    public static byte[] build(int i, int i2, int i3, int i4, int i5, int i6) {
        return new byte[]{(byte) i, (byte) i2, (byte) (i3 >>> 8), (byte) i3, (byte) (i4 >>> 8), (byte) i4, (byte) i5, (byte) i6};
    }

    public static McuMgrHeader fromBytes(byte[] bArr) {
        if (bArr == null || bArr.length != 8) {
            return null;
        }
        return new McuMgrHeader(ByteUtil.byteArrayToUnsignedInt(bArr, 0, Endian.BIG, 1), ByteUtil.byteArrayToUnsignedInt(bArr, 1, Endian.BIG, 1), ByteUtil.byteArrayToUnsignedInt(bArr, 2, Endian.BIG, 2), ByteUtil.byteArrayToUnsignedInt(bArr, 4, Endian.BIG, 2), ByteUtil.byteArrayToUnsignedInt(bArr, 6, Endian.BIG, 1), ByteUtil.byteArrayToUnsignedInt(bArr, 7, Endian.BIG, 1));
    }

    public int getCommandId() {
        return this.mCommandId;
    }

    public int getFlags() {
        return this.mFlags;
    }

    public int getGroupId() {
        return this.mGroupId;
    }

    public int getLen() {
        return this.mLen;
    }

    public int getOp() {
        return this.mOp;
    }

    public int getSequenceNum() {
        return this.mSequenceNum;
    }

    public void setCommandId(int i) {
        this.mCommandId = i;
    }

    public void setFlags(int i) {
        this.mFlags = i;
    }

    public void setGroupId(int i) {
        this.mGroupId = i;
    }

    public void setLen(int i) {
        this.mLen = i;
    }

    public void setOp(int i) {
        this.mOp = i;
    }

    public void setSequenceNum(int i) {
        this.mSequenceNum = i;
    }

    public byte[] toBytes() {
        return build(this.mOp, this.mFlags, this.mLen, this.mGroupId, this.mSequenceNum, this.mCommandId);
    }

    public String toString() {
        return "Header (Op: " + this.mOp + ", Flags: " + this.mFlags + ", Len: " + this.mLen + ", Group: " + this.mGroupId + ", Seq: " + this.mSequenceNum + ", Command: " + this.mCommandId + ")";
    }
}
