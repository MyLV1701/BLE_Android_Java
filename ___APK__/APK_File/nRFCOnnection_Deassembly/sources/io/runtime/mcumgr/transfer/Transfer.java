package io.runtime.mcumgr.transfer;

import io.runtime.mcumgr.response.McuMgrResponse;

/* loaded from: classes.dex */
public abstract class Transfer implements TransferCallback {
    byte[] mData;
    int mOffset;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Transfer(byte[] bArr, int i) {
        this.mData = bArr;
        this.mOffset = i;
    }

    public byte[] getData() {
        return this.mData;
    }

    public int getOffset() {
        return this.mOffset;
    }

    public boolean isFinished() {
        byte[] bArr = this.mData;
        return bArr != null && this.mOffset == bArr.length;
    }

    public abstract void reset();

    public abstract McuMgrResponse send(int i);

    public McuMgrResponse sendNext() {
        return send(this.mOffset);
    }

    public void setData(byte[] bArr) {
        this.mData = bArr;
    }

    public void setOffset(int i) {
        this.mOffset = i;
    }
}
