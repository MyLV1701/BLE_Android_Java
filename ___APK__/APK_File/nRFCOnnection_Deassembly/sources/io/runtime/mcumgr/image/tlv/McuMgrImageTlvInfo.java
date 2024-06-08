package io.runtime.mcumgr.image.tlv;

import io.runtime.mcumgr.exception.McuMgrException;
import io.runtime.mcumgr.util.ByteUtil;
import io.runtime.mcumgr.util.Endian;

/* loaded from: classes.dex */
public class McuMgrImageTlvInfo {
    private short mMagic;
    private short mTotal;

    private McuMgrImageTlvInfo() {
    }

    public static McuMgrImageTlvInfo fromBytes(byte[] bArr) {
        return fromBytes(bArr, 0);
    }

    public static int getSize() {
        return 4;
    }

    public short getMagic() {
        return this.mMagic;
    }

    public short getTotal() {
        return this.mTotal;
    }

    public static McuMgrImageTlvInfo fromBytes(byte[] bArr, int i) {
        if (bArr.length - i >= getSize()) {
            McuMgrImageTlvInfo mcuMgrImageTlvInfo = new McuMgrImageTlvInfo();
            mcuMgrImageTlvInfo.mMagic = (short) ByteUtil.byteArrayToUnsignedInt(bArr, i, Endian.LITTLE, 2);
            mcuMgrImageTlvInfo.mTotal = (short) ByteUtil.byteArrayToUnsignedInt(bArr, i + 2, Endian.LITTLE, 2);
            if (mcuMgrImageTlvInfo.mMagic == 26887) {
                return mcuMgrImageTlvInfo;
            }
            throw new McuMgrException("Wrong magic number, magic= " + ((int) mcuMgrImageTlvInfo.mMagic) + " instead of " + McuMgrImageTlv.IMG_TLV_INFO_MAGIC);
        }
        throw new McuMgrException("The byte array is too short to be a McuMgrImageTlvInfo: length= " + bArr.length + ", offset= " + i + ", min size= " + getSize());
    }
}
