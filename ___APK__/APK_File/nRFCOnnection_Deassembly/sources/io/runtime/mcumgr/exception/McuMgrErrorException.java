package io.runtime.mcumgr.exception;

import io.runtime.mcumgr.McuMgrErrorCode;
import io.runtime.mcumgr.response.McuMgrResponse;

/* loaded from: classes.dex */
public class McuMgrErrorException extends McuMgrException {
    private McuMgrErrorCode mCode;

    public McuMgrErrorException(McuMgrErrorCode mcuMgrErrorCode) {
        super("Mcu Mgr Error: " + mcuMgrErrorCode);
        this.mCode = mcuMgrErrorCode;
    }

    public McuMgrErrorCode getCode() {
        return this.mCode;
    }

    public McuMgrErrorException(McuMgrResponse mcuMgrResponse) {
        this(McuMgrErrorCode.valueOf(mcuMgrResponse.rc));
    }
}
