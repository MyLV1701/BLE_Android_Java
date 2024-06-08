package io.runtime.mcumgr.transfer;

import io.runtime.mcumgr.McuMgrErrorCode;
import io.runtime.mcumgr.exception.McuMgrErrorException;
import io.runtime.mcumgr.exception.McuMgrException;
import io.runtime.mcumgr.response.McuMgrResponse;
import io.runtime.mcumgr.response.UploadResponse;

/* loaded from: classes.dex */
public abstract class Upload extends Transfer {
    private UploadCallback mCallback;

    protected Upload(byte[] bArr) {
        this(bArr, null);
    }

    @Override // io.runtime.mcumgr.transfer.TransferCallback
    public void onCanceled() {
        UploadCallback uploadCallback = this.mCallback;
        if (uploadCallback != null) {
            uploadCallback.onUploadCanceled();
        }
    }

    @Override // io.runtime.mcumgr.transfer.TransferCallback
    public void onCompleted() {
        UploadCallback uploadCallback = this.mCallback;
        if (uploadCallback != null) {
            uploadCallback.onUploadCompleted();
        }
    }

    @Override // io.runtime.mcumgr.transfer.TransferCallback
    public void onFailed(McuMgrException mcuMgrException) {
        UploadCallback uploadCallback = this.mCallback;
        if (uploadCallback != null) {
            uploadCallback.onUploadFailed(mcuMgrException);
        }
    }

    @Override // io.runtime.mcumgr.transfer.TransferCallback
    public void onProgressChanged(int i, int i2, long j) {
        UploadCallback uploadCallback = this.mCallback;
        if (uploadCallback != null) {
            uploadCallback.onUploadProgressChanged(i, i2, j);
        }
    }

    @Override // io.runtime.mcumgr.transfer.Transfer
    public void reset() {
        this.mOffset = 0;
    }

    @Override // io.runtime.mcumgr.transfer.Transfer
    public McuMgrResponse send(int i) {
        byte[] bArr = this.mData;
        if (bArr != null) {
            UploadResponse write = write(bArr, i);
            int i2 = write.rc;
            if (i2 == 0) {
                this.mOffset = write.off;
                return write;
            }
            throw new McuMgrErrorException(McuMgrErrorCode.valueOf(i2));
        }
        throw new NullPointerException("Upload data cannot be null!");
    }

    protected abstract UploadResponse write(byte[] bArr, int i);

    /* JADX INFO: Access modifiers changed from: protected */
    public Upload(byte[] bArr, UploadCallback uploadCallback) {
        super(bArr, 0);
        this.mCallback = uploadCallback;
    }
}
