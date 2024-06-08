package io.runtime.mcumgr.crash;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class CoreDumpTlv {
    private static final int OFFSET = 8;
    private List<CoreDumpTlvEntry> mEntries;

    public CoreDumpTlv(List<CoreDumpTlvEntry> list) {
        this.mEntries = list;
    }

    public static CoreDumpTlv fromBytes(byte[] bArr) {
        return fromBytes(bArr, 8);
    }

    public List<CoreDumpTlvEntry> getEntries() {
        return this.mEntries;
    }

    public List<CoreDumpTlvEntry> getEntriesOfType(int i) {
        ArrayList arrayList = new ArrayList();
        for (CoreDumpTlvEntry coreDumpTlvEntry : this.mEntries) {
            if (coreDumpTlvEntry.getType() == i) {
                arrayList.add(coreDumpTlvEntry);
            }
        }
        return arrayList;
    }

    public CoreDumpTlvEntry getEntryOfType(int i) {
        for (CoreDumpTlvEntry coreDumpTlvEntry : this.mEntries) {
            if (coreDumpTlvEntry.getType() == i) {
                return coreDumpTlvEntry;
            }
        }
        return null;
    }

    public int getSize() {
        Iterator<CoreDumpTlvEntry> it = this.mEntries.iterator();
        int i = 0;
        while (it.hasNext()) {
            i += it.next().getSize();
        }
        return i;
    }

    public static CoreDumpTlv fromBytes(byte[] bArr, int i) {
        ArrayList arrayList = new ArrayList();
        while (i < bArr.length) {
            CoreDumpTlvEntry fromBytes = CoreDumpTlvEntry.fromBytes(bArr, i);
            arrayList.add(fromBytes);
            i += fromBytes.getSize();
        }
        return new CoreDumpTlv(arrayList);
    }
}
