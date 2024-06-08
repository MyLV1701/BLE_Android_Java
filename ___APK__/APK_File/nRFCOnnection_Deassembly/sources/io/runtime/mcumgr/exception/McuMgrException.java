package io.runtime.mcumgr.exception;

/* loaded from: classes.dex */
public class McuMgrException extends Exception {
    public McuMgrException() {
    }

    public McuMgrException(String str) {
        super(str);
    }

    public McuMgrException(String str, Throwable th) {
        super(str, th);
    }

    public McuMgrException(Throwable th) {
        super(th);
    }
}
