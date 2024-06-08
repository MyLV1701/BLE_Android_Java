package io.runtime.mcumgr.crash;

/* loaded from: classes.dex */
public class CoreDump {
    protected static final int MAGIC = 1762412483;
    protected static final int TLV_TYPE_IMAGE = 1;
    protected static final int TLV_TYPE_MEM = 2;
    protected static final int TLV_TYPE_REG = 3;
    private CoreDumpHeader mHeader;
    private CoreDumpTlv mTlv;

    public CoreDump(CoreDumpHeader coreDumpHeader, CoreDumpTlv coreDumpTlv) {
        this.mHeader = coreDumpHeader;
        this.mTlv = coreDumpTlv;
    }

    public static CoreDump fromBytes(byte[] bArr) {
        return new CoreDump(CoreDumpHeader.fromBytes(bArr), CoreDumpTlv.fromBytes(bArr));
    }

    public CoreDumpHeader getHeader() {
        return this.mHeader;
    }

    public byte[] getImageHash() {
        CoreDumpTlvEntry entryOfType = this.mTlv.getEntryOfType(1);
        if (entryOfType == null) {
            return null;
        }
        return entryOfType.getValue();
    }

    public byte[] getRegisters() {
        CoreDumpTlvEntry entryOfType = this.mTlv.getEntryOfType(3);
        if (entryOfType == null) {
            return null;
        }
        return entryOfType.getValue();
    }

    public CoreDumpTlv getTlv() {
        return this.mTlv;
    }
}
