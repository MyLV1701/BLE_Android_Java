package io.runtime.mcumgr.transfer;

import io.runtime.mcumgr.McuMgrErrorCode;
import io.runtime.mcumgr.exception.McuMgrErrorException;
import io.runtime.mcumgr.exception.McuMgrException;
import io.runtime.mcumgr.response.DownloadResponse;
import io.runtime.mcumgr.response.McuMgrResponse;

/* loaded from: classes.dex */
public abstract class Download extends Transfer {
    private DownloadCallback mCallback;

    protected Download() {
        this(null);
    }

    @Override // io.runtime.mcumgr.transfer.TransferCallback
    public void onCanceled() {
        DownloadCallback downloadCallback = this.mCallback;
        if (downloadCallback != null) {
            downloadCallback.onDownloadCanceled();
        }
    }

    @Override // io.runtime.mcumgr.transfer.TransferCallback
    public void onCompleted() {
        DownloadCallback downloadCallback = this.mCallback;
        if (downloadCallback != null) {
            byte[] bArr = this.mData;
            if (bArr != null) {
                downloadCallback.onDownloadCompleted(bArr);
                return;
            }
            throw new NullPointerException("Transfer data cannot be null.");
        }
    }

    @Override // io.runtime.mcumgr.transfer.TransferCallback
    public void onFailed(McuMgrException mcuMgrException) {
        DownloadCallback downloadCallback = this.mCallback;
        if (downloadCallback != null) {
            downloadCallback.onDownloadFailed(mcuMgrException);
        }
    }

    @Override // io.runtime.mcumgr.transfer.TransferCallback
    public void onProgressChanged(int i, int i2, long j) {
        DownloadCallback downloadCallback = this.mCallback;
        if (downloadCallback != null) {
            downloadCallback.onDownloadProgressChanged(i, i2, j);
        }
    }

    protected abstract DownloadResponse read(int i);

    @Override // io.runtime.mcumgr.transfer.Transfer
    public void reset() {
        this.mOffset = 0;
        this.mData = null;
    }

    @Override // io.runtime.mcumgr.transfer.Transfer
    public McuMgrResponse send(int i) {
        DownloadResponse read = read(i);
        int i2 = read.rc;
        if (i2 == 0) {
            if (read.off == 0) {
                this.mData = new byte[read.len];
            }
            byte[] bArr = read.data;
            if (bArr != null) {
                byte[] bArr2 = this.mData;
                if (bArr2 != null) {
                    System.arraycopy(bArr, 0, bArr2, read.off, bArr.length);
                    this.mOffset = read.off + read.data.length;
                    return read;
                }
                throw new McuMgrException("Download data is null.");
            }
            throw new McuMgrException("Download response data is null.");
        }
        throw new McuMgrErrorException(McuMgrErrorCode.valueOf(i2));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Download(DownloadCallback downloadCallback) {
        super(null, 0);
        this.mCallback = downloadCallback;
    }
}
