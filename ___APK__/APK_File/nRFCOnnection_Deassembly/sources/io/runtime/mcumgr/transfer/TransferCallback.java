package io.runtime.mcumgr.transfer;

import io.runtime.mcumgr.exception.McuMgrException;

/* loaded from: classes.dex */
public interface TransferCallback {
    void onCanceled();

    void onCompleted();

    void onFailed(McuMgrException mcuMgrException);

    void onProgressChanged(int i, int i2, long j);
}
