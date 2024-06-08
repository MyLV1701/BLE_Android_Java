package io.runtime.mcumgr;

/* loaded from: classes.dex */
public enum McuMgrErrorCode {
    OK(0),
    UNKNOWN(1),
    NO_MEMORY(2),
    IN_VALUE(3),
    TIMEOUT(4),
    NO_ENTRY(5),
    BAD_STATE(6),
    TOO_LARGE(7),
    NOT_SUPPORTED(8),
    PER_USER(256);

    private int mCode;

    McuMgrErrorCode(int i) {
        this.mCode = i;
    }

    @Override // java.lang.Enum
    public String toString() {
        return super.toString() + " (" + this.mCode + ")";
    }

    public int value() {
        return this.mCode;
    }

    public static McuMgrErrorCode valueOf(int i) {
        if (i != 256) {
            switch (i) {
                case 0:
                    return OK;
                case 1:
                    return UNKNOWN;
                case 2:
                    return NO_MEMORY;
                case 3:
                    return IN_VALUE;
                case 4:
                    return TIMEOUT;
                case 5:
                    return NO_ENTRY;
                case 6:
                    return BAD_STATE;
                case 7:
                    return TOO_LARGE;
                case 8:
                    return NOT_SUPPORTED;
                default:
                    return UNKNOWN;
            }
        }
        return PER_USER;
    }
}
