package io.runtime.mcumgr.transfer;

import io.runtime.mcumgr.exception.McuMgrException;

/* loaded from: classes.dex */
public interface UploadCallback {
    void onUploadCanceled();

    void onUploadCompleted();

    void onUploadFailed(McuMgrException mcuMgrException);

    void onUploadProgressChanged(int i, int i2, long j);
}
