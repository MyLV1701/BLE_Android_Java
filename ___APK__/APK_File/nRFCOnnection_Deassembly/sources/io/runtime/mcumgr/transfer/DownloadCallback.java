package io.runtime.mcumgr.transfer;

import io.runtime.mcumgr.exception.McuMgrException;

/* loaded from: classes.dex */
public interface DownloadCallback {
    void onDownloadCanceled();

    void onDownloadCompleted(byte[] bArr);

    void onDownloadFailed(McuMgrException mcuMgrException);

    void onDownloadProgressChanged(int i, int i2, long j);
}
