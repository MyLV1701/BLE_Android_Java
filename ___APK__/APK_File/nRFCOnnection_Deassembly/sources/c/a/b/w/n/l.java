package c.a.b.w.n;

import c.a.b.p;
import c.a.b.q;
import c.a.b.t;
import c.a.b.u;

/* loaded from: classes.dex */
public final class l<T> extends t<T> {

    /* renamed from: a, reason: collision with root package name */
    private final q<T> f2282a;

    /* renamed from: b, reason: collision with root package name */
    private final c.a.b.i<T> f2283b;

    /* renamed from: c, reason: collision with root package name */
    final c.a.b.e f2284c;

    /* renamed from: d, reason: collision with root package name */
    private final c.a.b.x.a<T> f2285d;

    /* renamed from: e, reason: collision with root package name */
    private final u f2286e;

    /* renamed from: f, reason: collision with root package name */
    private final l<T>.b f2287f = new b();
    private t<T> g;

    /* loaded from: classes.dex */
    private final class b implements p, c.a.b.h {
        private b(l lVar) {
        }
    }

    public l(q<T> qVar, c.a.b.i<T> iVar, c.a.b.e eVar, c.a.b.x.a<T> aVar, u uVar) {
        this.f2282a = qVar;
        this.f2283b = iVar;
        this.f2284c = eVar;
        this.f2285d = aVar;
        this.f2286e = uVar;
    }

    private t<T> b() {
        t<T> tVar = this.g;
        if (tVar != null) {
            return tVar;
        }
        t<T> a2 = this.f2284c.a(this.f2286e, this.f2285d);
        this.g = a2;
        return a2;
    }

    @Override // c.a.b.t
    /* renamed from: a */
    public T a2(c.a.b.y.a aVar) {
        if (this.f2283b == null) {
            return b().a2(aVar);
        }
        c.a.b.j a2 = c.a.b.w.l.a(aVar);
        if (a2.e()) {
            return null;
        }
        return this.f2283b.a(a2, this.f2285d.b(), this.f2287f);
    }

    @Override // c.a.b.t
    public void a(c.a.b.y.c cVar, T t) {
        q<T> qVar = this.f2282a;
        if (qVar == null) {
            b().a(cVar, t);
        } else if (t == null) {
            cVar.g();
        } else {
            c.a.b.w.l.a(qVar.a(t, this.f2285d.b(), this.f2287f), cVar);
        }
    }
}
