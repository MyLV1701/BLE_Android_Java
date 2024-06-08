package io.runtime.mcumgr;

import io.runtime.mcumgr.exception.McuMgrException;
import io.runtime.mcumgr.response.McuMgrResponse;

/* loaded from: classes.dex */
public interface McuMgrCallback<T extends McuMgrResponse> {
    void onError(McuMgrException mcuMgrException);

    void onResponse(T t);
}
