package no.nordicsemi.android.support.v18.scanner;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import no.nordicsemi.android.support.v18.scanner.n;

/* loaded from: classes.dex */
public abstract class a {

    /* renamed from: a, reason: collision with root package name */
    private static a f3910a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: no.nordicsemi.android.support.v18.scanner.a$a, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static class C0099a {

        /* renamed from: b, reason: collision with root package name */
        private final boolean f3912b;

        /* renamed from: c, reason: collision with root package name */
        private final boolean f3913c;

        /* renamed from: d, reason: collision with root package name */
        private final boolean f3914d;

        /* renamed from: f, reason: collision with root package name */
        final List<k> f3916f;
        final n g;
        final j h;
        final Handler i;

        /* renamed from: a, reason: collision with root package name */
        private final Object f3911a = new Object();
        private final List<m> j = new ArrayList();
        private final Set<String> k = new HashSet();
        private final Map<String, m> l = new HashMap();
        private final Runnable m = new RunnableC0100a();
        private final Runnable n = new b();

        /* renamed from: e, reason: collision with root package name */
        private boolean f3915e = false;

        /* renamed from: no.nordicsemi.android.support.v18.scanner.a$a$a, reason: collision with other inner class name */
        /* loaded from: classes.dex */
        class RunnableC0100a implements Runnable {
            RunnableC0100a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                if (C0099a.this.f3915e) {
                    return;
                }
                C0099a.this.b();
                C0099a c0099a = C0099a.this;
                c0099a.i.postDelayed(this, c0099a.g.n());
            }
        }

        /* renamed from: no.nordicsemi.android.support.v18.scanner.a$a$b */
        /* loaded from: classes.dex */
        class b implements Runnable {

            /* renamed from: no.nordicsemi.android.support.v18.scanner.a$a$b$a, reason: collision with other inner class name */
            /* loaded from: classes.dex */
            class RunnableC0101a implements Runnable {

                /* renamed from: b, reason: collision with root package name */
                final /* synthetic */ m f3919b;

                RunnableC0101a(m mVar) {
                    this.f3919b = mVar;
                }

                @Override // java.lang.Runnable
                public void run() {
                    C0099a.this.h.onScanResult(4, this.f3919b);
                }
            }

            b() {
            }

            @Override // java.lang.Runnable
            public void run() {
                long elapsedRealtimeNanos = SystemClock.elapsedRealtimeNanos();
                synchronized (C0099a.this.f3911a) {
                    Iterator it = C0099a.this.l.values().iterator();
                    while (it.hasNext()) {
                        m mVar = (m) it.next();
                        if (mVar.g() < elapsedRealtimeNanos - C0099a.this.g.g()) {
                            it.remove();
                            C0099a.this.i.post(new RunnableC0101a(mVar));
                        }
                    }
                    if (!C0099a.this.l.isEmpty()) {
                        C0099a.this.i.postDelayed(this, C0099a.this.g.h());
                    }
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public C0099a(boolean z, boolean z2, List<k> list, n nVar, j jVar, Handler handler) {
            this.f3916f = Collections.unmodifiableList(list);
            this.g = nVar;
            this.h = jVar;
            this.i = handler;
            boolean z3 = false;
            this.f3914d = (nVar.e() == 1 || ((Build.VERSION.SDK_INT >= 23) && nVar.q())) ? false : true;
            this.f3912b = (list.isEmpty() || (z2 && nVar.r())) ? false : true;
            long n = nVar.n();
            if (n > 0 && (!z || !nVar.p())) {
                z3 = true;
            }
            this.f3913c = z3;
            if (this.f3913c) {
                handler.postDelayed(this.m, n);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void a() {
            this.f3915e = true;
            this.i.removeCallbacksAndMessages(null);
            synchronized (this.f3911a) {
                this.l.clear();
                this.k.clear();
                this.j.clear();
            }
        }

        void b() {
            if (!this.f3913c || this.f3915e) {
                return;
            }
            synchronized (this.f3911a) {
                this.h.onBatchScanResults(new ArrayList(this.j));
                this.j.clear();
                this.k.clear();
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void a(int i, m mVar) {
            boolean isEmpty;
            m put;
            if (this.f3915e) {
                return;
            }
            if (this.f3916f.isEmpty() || a(mVar)) {
                String address = mVar.d().getAddress();
                if (this.f3914d) {
                    synchronized (this.l) {
                        isEmpty = this.l.isEmpty();
                        put = this.l.put(address, mVar);
                    }
                    if (put == null && (this.g.e() & 2) > 0) {
                        this.h.onScanResult(2, mVar);
                    }
                    if (!isEmpty || (this.g.e() & 4) <= 0) {
                        return;
                    }
                    this.i.removeCallbacks(this.n);
                    this.i.postDelayed(this.n, this.g.h());
                    return;
                }
                if (this.f3913c) {
                    synchronized (this.f3911a) {
                        if (!this.k.contains(address)) {
                            this.j.add(mVar);
                            this.k.add(address);
                        }
                    }
                    return;
                }
                this.h.onScanResult(i, mVar);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void a(List<m> list) {
            if (this.f3915e) {
                return;
            }
            if (this.f3912b) {
                ArrayList arrayList = new ArrayList();
                for (m mVar : list) {
                    if (a(mVar)) {
                        arrayList.add(mVar);
                    }
                }
                list = arrayList;
            }
            this.h.onBatchScanResults(list);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void a(int i) {
            this.h.onScanFailed(i);
        }

        private boolean a(m mVar) {
            Iterator<k> it = this.f3916f.iterator();
            while (it.hasNext()) {
                if (it.next().a(mVar)) {
                    return true;
                }
            }
            return false;
        }
    }

    public static synchronized a a() {
        synchronized (a.class) {
            if (f3910a != null) {
                return f3910a;
            }
            if (Build.VERSION.SDK_INT >= 26) {
                e eVar = new e();
                f3910a = eVar;
                return eVar;
            }
            if (Build.VERSION.SDK_INT >= 23) {
                d dVar = new d();
                f3910a = dVar;
                return dVar;
            }
            if (Build.VERSION.SDK_INT >= 21) {
                c cVar = new c();
                f3910a = cVar;
                return cVar;
            }
            b bVar = new b();
            f3910a = bVar;
            return bVar;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void a(List<k> list, n nVar, j jVar, Handler handler);

    abstract void b(j jVar);

    public final void a(List<k> list, n nVar, j jVar) {
        if (jVar != null) {
            Handler handler = new Handler(Looper.getMainLooper());
            if (list == null) {
                list = Collections.emptyList();
            }
            if (nVar == null) {
                nVar = new n.b().a();
            }
            a(list, nVar, jVar, handler);
            return;
        }
        throw new IllegalArgumentException("callback is null");
    }

    public final void a(j jVar) {
        if (jVar != null) {
            b(jVar);
            return;
        }
        throw new IllegalArgumentException("callback is null");
    }
}
