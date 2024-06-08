package io.runtime.mcumgr.exception;

/* loaded from: classes.dex */
public class McuMgrCoapException extends McuMgrException {
    private byte[] mBytes;
    private int mCodeClass;
    private int mCodeDetail;

    public McuMgrCoapException(byte[] bArr, int i, int i2) {
        super("McuManager CoAP request resulted in an error response: " + i + ".0" + i2);
        this.mBytes = bArr;
        this.mCodeClass = i;
        this.mCodeDetail = i2;
    }

    public byte[] getBytes() {
        return this.mBytes;
    }

    public int getCodeClass() {
        return this.mCodeClass;
    }

    public int getCodeDetail() {
        return this.mCodeDetail;
    }
}
