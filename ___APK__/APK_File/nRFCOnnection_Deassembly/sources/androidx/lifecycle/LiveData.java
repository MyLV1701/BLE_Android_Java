package androidx.lifecycle;

import androidx.lifecycle.g;

/* loaded from: classes.dex */
public abstract class LiveData<T> {
    static final Object j = new Object();
    private boolean g;
    private boolean h;

    /* renamed from: a, reason: collision with root package name */
    final Object f1508a = new Object();

    /* renamed from: b, reason: collision with root package name */
    private a.b.a.b.b<p<? super T>, LiveData<T>.b> f1509b = new a.b.a.b.b<>();

    /* renamed from: c, reason: collision with root package name */
    int f1510c = 0;

    /* renamed from: e, reason: collision with root package name */
    volatile Object f1512e = j;
    private final Runnable i = new a();

    /* renamed from: d, reason: collision with root package name */
    private volatile Object f1511d = j;

    /* renamed from: f, reason: collision with root package name */
    private int f1513f = -1;

    /* loaded from: classes.dex */
    class a implements Runnable {
        a() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.lang.Runnable
        public void run() {
            Object obj;
            synchronized (LiveData.this.f1508a) {
                obj = LiveData.this.f1512e;
                LiveData.this.f1512e = LiveData.j;
            }
            LiveData.this.b((LiveData) obj);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public abstract class b {

        /* renamed from: a, reason: collision with root package name */
        final p<? super T> f1517a;

        /* renamed from: b, reason: collision with root package name */
        boolean f1518b;

        /* renamed from: c, reason: collision with root package name */
        int f1519c = -1;

        b(p<? super T> pVar) {
            this.f1517a = pVar;
        }

        void a() {
        }

        void a(boolean z) {
            if (z == this.f1518b) {
                return;
            }
            this.f1518b = z;
            boolean z2 = LiveData.this.f1510c == 0;
            LiveData.this.f1510c += this.f1518b ? 1 : -1;
            if (z2 && this.f1518b) {
                LiveData.this.c();
            }
            LiveData liveData = LiveData.this;
            if (liveData.f1510c == 0 && !this.f1518b) {
                liveData.d();
            }
            if (this.f1518b) {
                LiveData.this.a(this);
            }
        }

        boolean a(j jVar) {
            return false;
        }

        abstract boolean b();
    }

    private void b(LiveData<T>.b bVar) {
        if (bVar.f1518b) {
            if (!bVar.b()) {
                bVar.a(false);
                return;
            }
            int i = bVar.f1519c;
            int i2 = this.f1513f;
            if (i >= i2) {
                return;
            }
            bVar.f1519c = i2;
            bVar.f1517a.a((Object) this.f1511d);
        }
    }

    void a(LiveData<T>.b bVar) {
        if (this.g) {
            this.h = true;
            return;
        }
        this.g = true;
        do {
            this.h = false;
            if (bVar != null) {
                b((b) bVar);
                bVar = null;
            } else {
                a.b.a.b.b<p<? super T>, LiveData<T>.b>.d b2 = this.f1509b.b();
                while (b2.hasNext()) {
                    b((b) b2.next().getValue());
                    if (this.h) {
                        break;
                    }
                }
            }
        } while (this.h);
        this.g = false;
    }

    protected void c() {
    }

    protected void d() {
    }

    /* loaded from: classes.dex */
    class LifecycleBoundObserver extends LiveData<T>.b implements h {

        /* renamed from: e, reason: collision with root package name */
        final j f1514e;

        LifecycleBoundObserver(j jVar, p<? super T> pVar) {
            super(pVar);
            this.f1514e = jVar;
        }

        @Override // androidx.lifecycle.h
        public void a(j jVar, g.a aVar) {
            if (this.f1514e.getLifecycle().a() == g.b.DESTROYED) {
                LiveData.this.a((p) this.f1517a);
            } else {
                a(b());
            }
        }

        @Override // androidx.lifecycle.LiveData.b
        boolean b() {
            return this.f1514e.getLifecycle().a().a(g.b.STARTED);
        }

        @Override // androidx.lifecycle.LiveData.b
        boolean a(j jVar) {
            return this.f1514e == jVar;
        }

        @Override // androidx.lifecycle.LiveData.b
        void a() {
            this.f1514e.getLifecycle().b(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void b(T t) {
        a("setValue");
        this.f1513f++;
        this.f1511d = t;
        a((b) null);
    }

    public boolean b() {
        return this.f1510c > 0;
    }

    public void a(j jVar, p<? super T> pVar) {
        a("observe");
        if (jVar.getLifecycle().a() == g.b.DESTROYED) {
            return;
        }
        LifecycleBoundObserver lifecycleBoundObserver = new LifecycleBoundObserver(jVar, pVar);
        LiveData<T>.b b2 = this.f1509b.b(pVar, lifecycleBoundObserver);
        if (b2 != null && !b2.a(jVar)) {
            throw new IllegalArgumentException("Cannot add the same observer with different lifecycles");
        }
        if (b2 != null) {
            return;
        }
        jVar.getLifecycle().a(lifecycleBoundObserver);
    }

    public void a(p<? super T> pVar) {
        a("removeObserver");
        LiveData<T>.b remove = this.f1509b.remove(pVar);
        if (remove == null) {
            return;
        }
        remove.a();
        remove.a(false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(T t) {
        boolean z;
        synchronized (this.f1508a) {
            z = this.f1512e == j;
            this.f1512e = t;
        }
        if (z) {
            a.b.a.a.a.b().b(this.i);
        }
    }

    public T a() {
        T t = (T) this.f1511d;
        if (t != j) {
            return t;
        }
        return null;
    }

    static void a(String str) {
        if (a.b.a.a.a.b().a()) {
            return;
        }
        throw new IllegalStateException("Cannot invoke " + str + " on a background thread");
    }
}
