package io.runtime.mcumgr.image;

import io.runtime.mcumgr.exception.McuMgrException;
import io.runtime.mcumgr.image.tlv.McuMgrImageTlv;

/* loaded from: classes.dex */
public class McuMgrImage {
    public static final int IMG_HASH_LEN = 32;
    private final byte[] mData;
    private final byte[] mHash;
    private final McuMgrImageHeader mHeader;
    private final McuMgrImageTlv mTlv;

    public McuMgrImage(byte[] bArr) {
        this.mData = bArr;
        this.mHeader = McuMgrImageHeader.fromBytes(bArr);
        this.mTlv = McuMgrImageTlv.fromBytes(bArr, this.mHeader);
        this.mHash = this.mTlv.getHash();
        if (this.mHash == null) {
            throw new McuMgrException("Image TLV trailer does not contain an image hash!");
        }
    }

    public byte[] getData() {
        return this.mData;
    }

    public byte[] getHash() {
        return this.mHash;
    }

    public McuMgrImageHeader getHeader() {
        return this.mHeader;
    }

    public McuMgrImageTlv getTlv() {
        return this.mTlv;
    }

    public static byte[] getHash(byte[] bArr) {
        return new McuMgrImage(bArr).getHash();
    }
}
