package no.nordicsemi.android.mcp.scanner.timeline;

import no.nordicsemi.android.mcp.ble.model.AdvDataWithStats;

/* loaded from: classes.dex */
class MarkedAdvDataWithStats extends AdvDataWithStats {
    private int deviceIndex;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MarkedAdvDataWithStats(AdvDataWithStats advDataWithStats, int i) {
        super(advDataWithStats);
        this.deviceIndex = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getDeviceIndex() {
        return this.deviceIndex;
    }
}
