package no.nordicsemi.android.support.v18.scanner;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.os.Handler;
import android.os.SystemClock;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import no.nordicsemi.android.support.v18.scanner.a;

/* JADX INFO: Access modifiers changed from: package-private */
@TargetApi(21)
/* loaded from: classes.dex */
public class c extends no.nordicsemi.android.support.v18.scanner.a {

    /* renamed from: b, reason: collision with root package name */
    private final Map<j, b> f3931b = new HashMap();

    /* loaded from: classes.dex */
    static class b extends a.C0099a {
        private final ScanCallback o;

        /* loaded from: classes.dex */
        class a extends ScanCallback {

            /* renamed from: a, reason: collision with root package name */
            private long f3932a;

            /* renamed from: no.nordicsemi.android.support.v18.scanner.c$b$a$a, reason: collision with other inner class name */
            /* loaded from: classes.dex */
            class RunnableC0103a implements Runnable {

                /* renamed from: b, reason: collision with root package name */
                final /* synthetic */ ScanResult f3934b;

                /* renamed from: c, reason: collision with root package name */
                final /* synthetic */ int f3935c;

                RunnableC0103a(ScanResult scanResult, int i) {
                    this.f3934b = scanResult;
                    this.f3935c = i;
                }

                @Override // java.lang.Runnable
                public void run() {
                    b.this.a(this.f3935c, ((c) no.nordicsemi.android.support.v18.scanner.a.a()).a(this.f3934b));
                }
            }

            /* renamed from: no.nordicsemi.android.support.v18.scanner.c$b$a$b, reason: collision with other inner class name */
            /* loaded from: classes.dex */
            class RunnableC0104b implements Runnable {

                /* renamed from: b, reason: collision with root package name */
                final /* synthetic */ List f3937b;

                RunnableC0104b(List list) {
                    this.f3937b = list;
                }

                @Override // java.lang.Runnable
                public void run() {
                    long elapsedRealtime = SystemClock.elapsedRealtime();
                    if (a.this.f3932a > (elapsedRealtime - b.this.g.n()) + 5) {
                        return;
                    }
                    a.this.f3932a = elapsedRealtime;
                    b.this.a(((c) no.nordicsemi.android.support.v18.scanner.a.a()).a(this.f3937b));
                }
            }

            /* renamed from: no.nordicsemi.android.support.v18.scanner.c$b$a$c, reason: collision with other inner class name */
            /* loaded from: classes.dex */
            class RunnableC0105c implements Runnable {

                /* renamed from: b, reason: collision with root package name */
                final /* synthetic */ int f3939b;

                RunnableC0105c(int i) {
                    this.f3939b = i;
                }

                @Override // java.lang.Runnable
                public void run() {
                    if (b.this.g.q() && b.this.g.e() != 1) {
                        b.this.g.d();
                        no.nordicsemi.android.support.v18.scanner.a a2 = no.nordicsemi.android.support.v18.scanner.a.a();
                        try {
                            a2.a(b.this.h);
                        } catch (Exception unused) {
                        }
                        try {
                            a2.a(b.this.f3916f, b.this.g, b.this.h, b.this.i);
                            return;
                        } catch (Exception unused2) {
                            return;
                        }
                    }
                    b.this.a(this.f3939b);
                }
            }

            a() {
            }

            @Override // android.bluetooth.le.ScanCallback
            public void onBatchScanResults(List<ScanResult> list) {
                b.this.i.post(new RunnableC0104b(list));
            }

            @Override // android.bluetooth.le.ScanCallback
            public void onScanFailed(int i) {
                b.this.i.post(new RunnableC0105c(i));
            }

            @Override // android.bluetooth.le.ScanCallback
            public void onScanResult(int i, ScanResult scanResult) {
                b.this.i.post(new RunnableC0103a(scanResult, i));
            }
        }

        private b(boolean z, boolean z2, List<k> list, n nVar, j jVar, Handler handler) {
            super(z, z2, list, nVar, jVar, handler);
            this.o = new a();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // no.nordicsemi.android.support.v18.scanner.a
    public void a(List<k> list, n nVar, j jVar, Handler handler) {
        b bVar;
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        BluetoothLeScanner bluetoothLeScanner = defaultAdapter.getBluetoothLeScanner();
        if (bluetoothLeScanner != null) {
            boolean isOffloadedScanBatchingSupported = defaultAdapter.isOffloadedScanBatchingSupported();
            boolean isOffloadedFilteringSupported = defaultAdapter.isOffloadedFilteringSupported();
            synchronized (this.f3931b) {
                if (!this.f3931b.containsKey(jVar)) {
                    bVar = new b(isOffloadedScanBatchingSupported, isOffloadedFilteringSupported, list, nVar, jVar, handler);
                    this.f3931b.put(jVar, bVar);
                } else {
                    throw new IllegalArgumentException("scanner already started with given callback");
                }
            }
            ScanSettings a2 = a(defaultAdapter, nVar, false);
            ArrayList<ScanFilter> arrayList = null;
            if (!list.isEmpty() && isOffloadedFilteringSupported && nVar.r()) {
                arrayList = b(list);
            }
            bluetoothLeScanner.startScan(arrayList, a2, bVar.o);
            return;
        }
        throw new IllegalStateException("BT le scanner not available");
    }

    @Override // no.nordicsemi.android.support.v18.scanner.a
    void b(j jVar) {
        b remove;
        BluetoothLeScanner bluetoothLeScanner;
        synchronized (this.f3931b) {
            remove = this.f3931b.remove(jVar);
        }
        if (remove == null) {
            return;
        }
        remove.a();
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter == null || (bluetoothLeScanner = defaultAdapter.getBluetoothLeScanner()) == null) {
            return;
        }
        bluetoothLeScanner.stopScan(remove.o);
    }

    ArrayList<ScanFilter> b(List<k> list) {
        ArrayList<ScanFilter> arrayList = new ArrayList<>();
        Iterator<k> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(a(it.next()));
        }
        return arrayList;
    }

    ScanSettings a(BluetoothAdapter bluetoothAdapter, n nVar, boolean z) {
        ScanSettings.Builder builder = new ScanSettings.Builder();
        if (z || (bluetoothAdapter.isOffloadedScanBatchingSupported() && nVar.p())) {
            builder.setReportDelay(nVar.n());
        }
        if (nVar.o() != -1) {
            builder.setScanMode(nVar.o());
        } else {
            builder.setScanMode(0);
        }
        nVar.d();
        return builder.build();
    }

    ScanFilter a(k kVar) {
        ScanFilter.Builder builder = new ScanFilter.Builder();
        builder.setDeviceAddress(kVar.d()).setDeviceName(kVar.e()).setServiceUuid(kVar.l(), kVar.m()).setManufacturerData(kVar.h(), kVar.f(), kVar.g());
        if (kVar.k() != null) {
            builder.setServiceData(kVar.k(), kVar.i(), kVar.j());
        }
        return builder.build();
    }

    m a(ScanResult scanResult) {
        return new m(scanResult.getDevice(), l.a(scanResult.getScanRecord() != null ? scanResult.getScanRecord().getBytes() : null), scanResult.getRssi(), scanResult.getTimestampNanos());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ArrayList<m> a(List<ScanResult> list) {
        ArrayList<m> arrayList = new ArrayList<>();
        Iterator<ScanResult> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(a(it.next()));
        }
        return arrayList;
    }
}
