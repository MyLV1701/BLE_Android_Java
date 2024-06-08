package io.runtime.mcumgr.exception;

/* loaded from: classes.dex */
public class InsufficientMtuException extends McuMgrException {
    private int mDataLength;
    private int mMtu;

    public InsufficientMtuException(int i, int i2) {
        super("Payload (" + i + " bytes) too long for MTU: " + i2);
        this.mDataLength = i;
        this.mMtu = i2;
    }

    public int getDataLength() {
        return this.mDataLength;
    }

    public int getMtu() {
        return this.mMtu;
    }
}
