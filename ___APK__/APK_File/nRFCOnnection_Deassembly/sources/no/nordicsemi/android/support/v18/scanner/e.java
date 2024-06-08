package no.nordicsemi.android.support.v18.scanner;

import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.os.Handler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import no.nordicsemi.android.support.v18.scanner.a;
import no.nordicsemi.android.support.v18.scanner.k;
import no.nordicsemi.android.support.v18.scanner.n;

/* JADX INFO: Access modifiers changed from: package-private */
@TargetApi(26)
/* loaded from: classes.dex */
public class e extends d {

    /* renamed from: c, reason: collision with root package name */
    private final HashMap<PendingIntent, a> f3941c = new HashMap<>();

    /* loaded from: classes.dex */
    static class a extends a.C0099a {
        final i o;

        /* JADX INFO: Access modifiers changed from: package-private */
        public a(boolean z, boolean z2, List<k> list, n nVar, PendingIntent pendingIntent) {
            super(z, z2, list, nVar, new i(pendingIntent, nVar), new Handler());
            this.o = (i) this.h;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public a a(PendingIntent pendingIntent) {
        synchronized (this.f3941c) {
            if (!this.f3941c.containsKey(pendingIntent)) {
                return null;
            }
            a aVar = this.f3941c.get(pendingIntent);
            if (aVar != null) {
                return aVar;
            }
            throw new IllegalStateException("Scanning has been stopped");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ArrayList<k> c(List<ScanFilter> list) {
        ArrayList<k> arrayList = new ArrayList<>();
        Iterator<ScanFilter> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(a(it.next()));
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(PendingIntent pendingIntent, a aVar) {
        synchronized (this.f3941c) {
            this.f3941c.put(pendingIntent, aVar);
        }
    }

    @Override // no.nordicsemi.android.support.v18.scanner.d, no.nordicsemi.android.support.v18.scanner.c
    ScanSettings a(BluetoothAdapter bluetoothAdapter, n nVar, boolean z) {
        ScanSettings.Builder builder = new ScanSettings.Builder();
        if (z || (bluetoothAdapter.isOffloadedScanBatchingSupported() && nVar.p())) {
            builder.setReportDelay(nVar.n());
        }
        if (z || nVar.q()) {
            builder.setCallbackType(nVar.e()).setMatchMode(nVar.i()).setNumOfMatches(nVar.j());
        }
        builder.setScanMode(nVar.o()).setLegacy(nVar.f()).setPhy(nVar.k());
        return builder.build();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public n a(ScanSettings scanSettings, boolean z, boolean z2, boolean z3, long j, long j2, int i, int i2) {
        n.b bVar = new n.b();
        bVar.a(scanSettings.getLegacy());
        bVar.d(scanSettings.getPhy());
        bVar.a(scanSettings.getCallbackType());
        bVar.e(scanSettings.getScanMode());
        bVar.a(scanSettings.getReportDelayMillis());
        bVar.b(z);
        bVar.d(z2);
        bVar.c(z3);
        bVar.a(j, j2);
        bVar.b(i);
        bVar.c(i2);
        return bVar.a();
    }

    k a(ScanFilter scanFilter) {
        k.b bVar = new k.b();
        bVar.a(scanFilter.getDeviceAddress());
        bVar.b(scanFilter.getDeviceName());
        bVar.a(scanFilter.getServiceUuid(), scanFilter.getServiceUuidMask());
        bVar.a(scanFilter.getManufacturerId(), scanFilter.getManufacturerData(), scanFilter.getManufacturerDataMask());
        if (scanFilter.getServiceDataUuid() != null) {
            bVar.a(scanFilter.getServiceDataUuid(), scanFilter.getServiceData(), scanFilter.getServiceDataMask());
        }
        return bVar.a();
    }

    @Override // no.nordicsemi.android.support.v18.scanner.c
    m a(ScanResult scanResult) {
        return new m(scanResult.getDevice(), (scanResult.getDataStatus() << 5) | (scanResult.isLegacy() ? 16 : 0) | scanResult.isConnectable(), scanResult.getPrimaryPhy(), scanResult.getSecondaryPhy(), scanResult.getAdvertisingSid(), scanResult.getTxPower(), scanResult.getRssi(), scanResult.getPeriodicAdvertisingInterval(), l.a(scanResult.getScanRecord() != null ? scanResult.getScanRecord().getBytes() : null), scanResult.getTimestampNanos());
    }
}
