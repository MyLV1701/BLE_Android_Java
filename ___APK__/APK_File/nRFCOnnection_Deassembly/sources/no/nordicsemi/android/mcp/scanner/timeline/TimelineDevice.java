package no.nordicsemi.android.mcp.scanner.timeline;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import no.nordicsemi.android.mcp.ble.model.AdvDataWithStats;
import no.nordicsemi.android.mcp.ble.model.Device;
import no.nordicsemi.android.mcp.ble.model.PacketData;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class TimelineDevice {
    private final LinkedList<MarkedAdvDataWithStats> mPackets = new LinkedList<>();
    private final LinkedList<PacketData> mPacketsMetaData = new LinkedList<>();

    /* JADX INFO: Access modifiers changed from: package-private */
    public TimelineDevice(List<Device> list) {
        ListIterator[] listIteratorArr = new ListIterator[list.size()];
        for (int i = 0; i < list.size(); i++) {
            listIteratorArr[i] = list.get(i).getPacketsMetaData().listIterator(list.get(i).getPacketsMetaData().size());
        }
        ListIterator[] listIteratorArr2 = new ListIterator[list.size()];
        for (int i2 = 0; i2 < list.size(); i2++) {
            listIteratorArr2[i2] = list.get(i2).getPacketsHistory().listIterator(list.get(i2).getPacketsHistory().size());
        }
        PacketData[] packetDataArr = new PacketData[list.size()];
        for (int i3 = 0; i3 < list.size(); i3++) {
            packetDataArr[i3] = (PacketData) listIteratorArr[i3].previous();
        }
        AdvDataWithStats[] advDataWithStatsArr = new AdvDataWithStats[list.size()];
        for (int i4 = 0; i4 < list.size(); i4++) {
            advDataWithStatsArr[i4] = (AdvDataWithStats) listIteratorArr2[i4].previous();
        }
        int[] iArr = new int[list.size()];
        int i5 = -1;
        while (true) {
            int findOldest = findOldest(packetDataArr);
            if (findOldest == -1) {
                return;
            }
            this.mPacketsMetaData.addFirst(packetDataArr[findOldest]);
            try {
                packetDataArr[findOldest] = (PacketData) listIteratorArr[findOldest].previous();
            } catch (NoSuchElementException unused) {
                packetDataArr[findOldest] = null;
            }
            if (iArr[findOldest] == advDataWithStatsArr[findOldest].getCount()) {
                advDataWithStatsArr[findOldest] = (AdvDataWithStats) listIteratorArr2[findOldest].previous();
                iArr[findOldest] = 1;
                this.mPackets.addFirst(new MarkedAdvDataWithStats(advDataWithStatsArr[findOldest], list.get(findOldest).getDeviceIndex()));
            } else {
                if (findOldest == i5) {
                    this.mPackets.getFirst().increaseCount();
                } else {
                    this.mPackets.addFirst(new MarkedAdvDataWithStats(advDataWithStatsArr[findOldest], list.get(findOldest).getDeviceIndex()));
                }
                iArr[findOldest] = iArr[findOldest] + 1;
            }
            i5 = findOldest;
        }
    }

    private int findOldest(PacketData[] packetDataArr) {
        int i = -1;
        long j = Long.MAX_VALUE;
        for (int i2 = 0; i2 < packetDataArr.length; i2++) {
            if (packetDataArr[i2] != null && packetDataArr[i2].timestampNanos < j) {
                j = packetDataArr[i2].timestampNanos;
                i = i2;
            }
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<MarkedAdvDataWithStats> getPacketsHistory() {
        return this.mPackets;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<PacketData> getPacketsMetaData() {
        return this.mPacketsMetaData;
    }
}
