package androidx.lifecycle;

import androidx.lifecycle.g;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes.dex */
public class k extends g {

    /* renamed from: c, reason: collision with root package name */
    private final WeakReference<j> f1543c;

    /* renamed from: a, reason: collision with root package name */
    private a.b.a.b.a<i, b> f1541a = new a.b.a.b.a<>();

    /* renamed from: d, reason: collision with root package name */
    private int f1544d = 0;

    /* renamed from: e, reason: collision with root package name */
    private boolean f1545e = false;

    /* renamed from: f, reason: collision with root package name */
    private boolean f1546f = false;
    private ArrayList<g.b> g = new ArrayList<>();

    /* renamed from: b, reason: collision with root package name */
    private g.b f1542b = g.b.INITIALIZED;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static /* synthetic */ class a {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f1547a;

        /* renamed from: b, reason: collision with root package name */
        static final /* synthetic */ int[] f1548b = new int[g.b.values().length];

        static {
            try {
                f1548b[g.b.INITIALIZED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1548b[g.b.CREATED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1548b[g.b.STARTED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1548b[g.b.RESUMED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f1548b[g.b.DESTROYED.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            f1547a = new int[g.a.values().length];
            try {
                f1547a[g.a.ON_CREATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f1547a[g.a.ON_STOP.ordinal()] = 2;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f1547a[g.a.ON_START.ordinal()] = 3;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f1547a[g.a.ON_PAUSE.ordinal()] = 4;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f1547a[g.a.ON_RESUME.ordinal()] = 5;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f1547a[g.a.ON_DESTROY.ordinal()] = 6;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f1547a[g.a.ON_ANY.ordinal()] = 7;
            } catch (NoSuchFieldError unused12) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class b {

        /* renamed from: a, reason: collision with root package name */
        g.b f1549a;

        /* renamed from: b, reason: collision with root package name */
        h f1550b;

        b(i iVar, g.b bVar) {
            this.f1550b = m.a(iVar);
            this.f1549a = bVar;
        }

        void a(j jVar, g.a aVar) {
            g.b b2 = k.b(aVar);
            this.f1549a = k.a(this.f1549a, b2);
            this.f1550b.a(jVar, aVar);
            this.f1549a = b2;
        }
    }

    public k(j jVar) {
        this.f1543c = new WeakReference<>(jVar);
    }

    private g.b c(i iVar) {
        Map.Entry<i, b> a2 = this.f1541a.a(iVar);
        g.b bVar = null;
        g.b bVar2 = a2 != null ? a2.getValue().f1549a : null;
        if (!this.g.isEmpty()) {
            bVar = this.g.get(r0.size() - 1);
        }
        return a(a(this.f1542b, bVar2), bVar);
    }

    private void d(g.b bVar) {
        if (this.f1542b == bVar) {
            return;
        }
        this.f1542b = bVar;
        if (!this.f1545e && this.f1544d == 0) {
            this.f1545e = true;
            d();
            this.f1545e = false;
            return;
        }
        this.f1546f = true;
    }

    private void e(g.b bVar) {
        this.g.add(bVar);
    }

    private static g.a f(g.b bVar) {
        int i = a.f1548b[bVar.ordinal()];
        if (i != 1) {
            if (i == 2) {
                return g.a.ON_START;
            }
            if (i == 3) {
                return g.a.ON_RESUME;
            }
            if (i == 4) {
                throw new IllegalArgumentException();
            }
            if (i != 5) {
                throw new IllegalArgumentException("Unexpected state value " + bVar);
            }
        }
        return g.a.ON_CREATE;
    }

    @Deprecated
    public void a(g.b bVar) {
        b(bVar);
    }

    public void b(g.b bVar) {
        d(bVar);
    }

    private boolean b() {
        if (this.f1541a.size() == 0) {
            return true;
        }
        g.b bVar = this.f1541a.a().getValue().f1549a;
        g.b bVar2 = this.f1541a.c().getValue().f1549a;
        return bVar == bVar2 && this.f1542b == bVar2;
    }

    public void a(g.a aVar) {
        d(b(aVar));
    }

    @Override // androidx.lifecycle.g
    public void a(i iVar) {
        j jVar;
        g.b bVar = this.f1542b;
        g.b bVar2 = g.b.DESTROYED;
        if (bVar != bVar2) {
            bVar2 = g.b.INITIALIZED;
        }
        b bVar3 = new b(iVar, bVar2);
        if (this.f1541a.b(iVar, bVar3) == null && (jVar = this.f1543c.get()) != null) {
            boolean z = this.f1544d != 0 || this.f1545e;
            g.b c2 = c(iVar);
            this.f1544d++;
            while (bVar3.f1549a.compareTo(c2) < 0 && this.f1541a.contains(iVar)) {
                e(bVar3.f1549a);
                bVar3.a(jVar, f(bVar3.f1549a));
                c();
                c2 = c(iVar);
            }
            if (!z) {
                d();
            }
            this.f1544d--;
        }
    }

    private void c() {
        this.g.remove(r0.size() - 1);
    }

    private static g.a c(g.b bVar) {
        int i = a.f1548b[bVar.ordinal()];
        if (i == 1) {
            throw new IllegalArgumentException();
        }
        if (i == 2) {
            return g.a.ON_DESTROY;
        }
        if (i == 3) {
            return g.a.ON_STOP;
        }
        if (i == 4) {
            return g.a.ON_PAUSE;
        }
        if (i != 5) {
            throw new IllegalArgumentException("Unexpected state value " + bVar);
        }
        throw new IllegalArgumentException();
    }

    @Override // androidx.lifecycle.g
    public void b(i iVar) {
        this.f1541a.remove(iVar);
    }

    static g.b b(g.a aVar) {
        switch (a.f1547a[aVar.ordinal()]) {
            case 1:
            case 2:
                return g.b.CREATED;
            case 3:
            case 4:
                return g.b.STARTED;
            case 5:
                return g.b.RESUMED;
            case 6:
                return g.b.DESTROYED;
            default:
                throw new IllegalArgumentException("Unexpected event value " + aVar);
        }
    }

    private void d() {
        j jVar = this.f1543c.get();
        if (jVar != null) {
            while (!b()) {
                this.f1546f = false;
                if (this.f1542b.compareTo(this.f1541a.a().getValue().f1549a) < 0) {
                    a(jVar);
                }
                Map.Entry<i, b> c2 = this.f1541a.c();
                if (!this.f1546f && c2 != null && this.f1542b.compareTo(c2.getValue().f1549a) > 0) {
                    b(jVar);
                }
            }
            this.f1546f = false;
            return;
        }
        throw new IllegalStateException("LifecycleOwner of this LifecycleRegistry is alreadygarbage collected. It is too late to change lifecycle state.");
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void b(j jVar) {
        a.b.a.b.b<i, b>.d b2 = this.f1541a.b();
        while (b2.hasNext() && !this.f1546f) {
            Map.Entry next = b2.next();
            b bVar = (b) next.getValue();
            while (bVar.f1549a.compareTo(this.f1542b) < 0 && !this.f1546f && this.f1541a.contains(next.getKey())) {
                e(bVar.f1549a);
                bVar.a(jVar, f(bVar.f1549a));
                c();
            }
        }
    }

    @Override // androidx.lifecycle.g
    public g.b a() {
        return this.f1542b;
    }

    private void a(j jVar) {
        Iterator<Map.Entry<i, b>> descendingIterator = this.f1541a.descendingIterator();
        while (descendingIterator.hasNext() && !this.f1546f) {
            Map.Entry<i, b> next = descendingIterator.next();
            b value = next.getValue();
            while (value.f1549a.compareTo(this.f1542b) > 0 && !this.f1546f && this.f1541a.contains(next.getKey())) {
                g.a c2 = c(value.f1549a);
                e(b(c2));
                value.a(jVar, c2);
                c();
            }
        }
    }

    static g.b a(g.b bVar, g.b bVar2) {
        return (bVar2 == null || bVar2.compareTo(bVar) >= 0) ? bVar : bVar2;
    }
}
