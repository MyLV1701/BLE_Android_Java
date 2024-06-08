package io.runtime.mcumgr.image.tlv;

import io.runtime.mcumgr.image.McuMgrImageHeader;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class McuMgrImageTlv {
    public static final int IMG_TLV_INFO_MAGIC = 26887;
    public static final int IMG_TLV_SHA256 = 16;
    public static final int IMG_TLV_SHA256_V1 = 1;
    private byte[] mData;
    private McuMgrImageHeader mHeader;
    private McuMgrImageTlvInfo mTlvInfo;
    private List<McuMgrImageTlvTrailerEntry> mTrailerEntries = new ArrayList();

    private McuMgrImageTlv(byte[] bArr, McuMgrImageHeader mcuMgrImageHeader) {
        this.mData = bArr;
        this.mHeader = mcuMgrImageHeader;
    }

    public static McuMgrImageTlv fromBytes(byte[] bArr, McuMgrImageHeader mcuMgrImageHeader) {
        McuMgrImageTlv mcuMgrImageTlv = new McuMgrImageTlv(bArr, mcuMgrImageHeader);
        int hdrSize = mcuMgrImageHeader.getHdrSize() + mcuMgrImageHeader.getImgSize();
        int length = mcuMgrImageTlv.mData.length;
        if (!mcuMgrImageHeader.isLegacy()) {
            mcuMgrImageTlv.mTlvInfo = McuMgrImageTlvInfo.fromBytes(mcuMgrImageTlv.mData, hdrSize);
            hdrSize += McuMgrImageTlvInfo.getSize();
        }
        while (McuMgrImageTlvTrailerEntry.getMinSize() + hdrSize < length) {
            McuMgrImageTlvTrailerEntry fromBytes = McuMgrImageTlvTrailerEntry.fromBytes(bArr, hdrSize);
            mcuMgrImageTlv.mTrailerEntries.add(fromBytes);
            hdrSize += fromBytes.getEntryLength();
        }
        return mcuMgrImageTlv;
    }

    public byte[] getData() {
        return this.mData;
    }

    public byte[] getHash() {
        for (McuMgrImageTlvTrailerEntry mcuMgrImageTlvTrailerEntry : getTrailerEntries()) {
            if ((this.mHeader.isLegacy() && mcuMgrImageTlvTrailerEntry.type == 1) || (!this.mHeader.isLegacy() && mcuMgrImageTlvTrailerEntry.type == 16)) {
                return mcuMgrImageTlvTrailerEntry.value;
            }
        }
        return null;
    }

    public McuMgrImageHeader getHeader() {
        return this.mHeader;
    }

    public McuMgrImageTlvInfo getTlvInfo() {
        return this.mTlvInfo;
    }

    public List<McuMgrImageTlvTrailerEntry> getTrailerEntries() {
        return this.mTrailerEntries;
    }
}
