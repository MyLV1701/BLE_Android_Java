package no.nordicsemi.android.support.v18.scanner;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.SystemClock;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import no.nordicsemi.android.support.v18.scanner.a;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class b extends no.nordicsemi.android.support.v18.scanner.a {

    /* renamed from: c, reason: collision with root package name */
    private HandlerThread f3922c;

    /* renamed from: d, reason: collision with root package name */
    private Handler f3923d;

    /* renamed from: e, reason: collision with root package name */
    private long f3924e;

    /* renamed from: f, reason: collision with root package name */
    private long f3925f;

    /* renamed from: b, reason: collision with root package name */
    private final Map<j, a.C0099a> f3921b = new HashMap();
    private final Runnable g = new a();
    private final Runnable h = new RunnableC0102b();
    private final BluetoothAdapter.LeScanCallback i = new c();

    /* loaded from: classes.dex */
    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            if (defaultAdapter == null || b.this.f3924e <= 0 || b.this.f3925f <= 0) {
                return;
            }
            defaultAdapter.stopLeScan(b.this.i);
            b.this.f3923d.postDelayed(b.this.h, b.this.f3924e);
        }
    }

    /* renamed from: no.nordicsemi.android.support.v18.scanner.b$b, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    class RunnableC0102b implements Runnable {
        RunnableC0102b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            if (defaultAdapter == null || b.this.f3924e <= 0 || b.this.f3925f <= 0) {
                return;
            }
            defaultAdapter.startLeScan(b.this.i);
            b.this.f3923d.postDelayed(b.this.g, b.this.f3925f);
        }
    }

    /* loaded from: classes.dex */
    class c implements BluetoothAdapter.LeScanCallback {

        /* loaded from: classes.dex */
        class a implements Runnable {

            /* renamed from: b, reason: collision with root package name */
            final /* synthetic */ a.C0099a f3929b;

            /* renamed from: c, reason: collision with root package name */
            final /* synthetic */ m f3930c;

            a(c cVar, a.C0099a c0099a, m mVar) {
                this.f3929b = c0099a;
                this.f3930c = mVar;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.f3929b.a(1, this.f3930c);
            }
        }

        c() {
        }

        @Override // android.bluetooth.BluetoothAdapter.LeScanCallback
        public void onLeScan(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
            m mVar = new m(bluetoothDevice, l.a(bArr), i, SystemClock.elapsedRealtimeNanos());
            synchronized (b.this.f3921b) {
                for (a.C0099a c0099a : b.this.f3921b.values()) {
                    c0099a.i.post(new a(this, c0099a, mVar));
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // no.nordicsemi.android.support.v18.scanner.a
    public void a(List<k> list, n nVar, j jVar, Handler handler) {
        boolean isEmpty;
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        synchronized (this.f3921b) {
            if (!this.f3921b.containsKey(jVar)) {
                a.C0099a c0099a = new a.C0099a(false, false, list, nVar, jVar, handler);
                isEmpty = this.f3921b.isEmpty();
                this.f3921b.put(jVar, c0099a);
            } else {
                throw new IllegalArgumentException("scanner already started with given scanCallback");
            }
        }
        if (this.f3922c == null) {
            this.f3922c = new HandlerThread(b.class.getName());
            this.f3922c.start();
            this.f3923d = new Handler(this.f3922c.getLooper());
        }
        b();
        if (isEmpty) {
            defaultAdapter.startLeScan(this.i);
        }
    }

    @Override // no.nordicsemi.android.support.v18.scanner.a
    void b(j jVar) {
        a.C0099a remove;
        boolean isEmpty;
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        synchronized (this.f3921b) {
            remove = this.f3921b.remove(jVar);
            isEmpty = this.f3921b.isEmpty();
        }
        if (remove == null) {
            return;
        }
        remove.a();
        b();
        if (isEmpty) {
            defaultAdapter.stopLeScan(this.i);
            Handler handler = this.f3923d;
            if (handler != null) {
                handler.removeCallbacksAndMessages(null);
            }
            HandlerThread handlerThread = this.f3922c;
            if (handlerThread != null) {
                handlerThread.quitSafely();
                this.f3922c = null;
            }
        }
    }

    private void b() {
        long j;
        long j2;
        synchronized (this.f3921b) {
            Iterator<a.C0099a> it = this.f3921b.values().iterator();
            j = Long.MAX_VALUE;
            j2 = Long.MAX_VALUE;
            while (it.hasNext()) {
                n nVar = it.next().g;
                if (nVar.s()) {
                    if (j > nVar.l()) {
                        j = nVar.l();
                    }
                    if (j2 > nVar.m()) {
                        j2 = nVar.m();
                    }
                }
            }
        }
        if (j < Long.MAX_VALUE && j2 < Long.MAX_VALUE) {
            this.f3924e = j;
            this.f3925f = j2;
            Handler handler = this.f3923d;
            if (handler != null) {
                handler.removeCallbacks(this.h);
                this.f3923d.removeCallbacks(this.g);
                this.f3923d.postDelayed(this.g, this.f3925f);
                return;
            }
            return;
        }
        this.f3925f = 0L;
        this.f3924e = 0L;
        Handler handler2 = this.f3923d;
        if (handler2 != null) {
            handler2.removeCallbacks(this.h);
            this.f3923d.removeCallbacks(this.g);
        }
    }
}
