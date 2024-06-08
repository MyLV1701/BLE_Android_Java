package d.p;

import d.p.o;

/* loaded from: classes.dex */
public class b {
    public static d.q.c k = d.q.c.b(b.class);
    public static final a l = new a(o.x);
    public static final a m = new a(o.y);
    public static final a n = new a(o.z);
    public static final a o = new a(o.A);
    public static final a p = new a(o.B);
    public static final a q = new a(o.C);
    public static final a r = new a(o.D);
    public static final a s = new a(o.E);

    /* renamed from: a, reason: collision with root package name */
    private String f2832a;

    /* renamed from: b, reason: collision with root package name */
    private double f2833b;

    /* renamed from: c, reason: collision with root package name */
    private double f2834c;

    /* renamed from: d, reason: collision with root package name */
    private d.p.q0.i f2835d;

    /* renamed from: e, reason: collision with root package name */
    private d.p.q0.h f2836e;

    /* renamed from: f, reason: collision with root package name */
    private s f2837f;
    private o g;
    private boolean h;
    private boolean i;
    private d.t.o.j j;

    /* loaded from: classes.dex */
    protected static class a {

        /* renamed from: a, reason: collision with root package name */
        private static a[] f2838a = new a[0];

        a(o.a aVar) {
            a[] aVarArr = f2838a;
            f2838a = new a[aVarArr.length + 1];
            System.arraycopy(aVarArr, 0, f2838a, 0, aVarArr.length);
            f2838a[aVarArr.length] = this;
        }
    }

    private void i() {
        this.f2837f = null;
        this.g = null;
        this.h = false;
        this.f2836e = null;
        this.i = false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String a() {
        return this.f2832a;
    }

    public double b() {
        return this.f2834c;
    }

    public double c() {
        return this.f2833b;
    }

    public o d() {
        o oVar = this.g;
        if (oVar != null) {
            return oVar;
        }
        s sVar = this.f2837f;
        if (sVar == null) {
            return null;
        }
        this.g = new o(sVar.v());
        return this.g;
    }

    public boolean e() {
        return this.i;
    }

    public boolean f() {
        return this.h;
    }

    public void g() {
        this.f2832a = null;
        d.p.q0.i iVar = this.f2835d;
        if (iVar != null) {
            this.j.a(iVar);
            this.f2835d = null;
        }
    }

    public void h() {
        if (this.i) {
            o d2 = d();
            if (d2.b()) {
                k.b("Cannot remove data validation from " + d.c.a(this.j) + " as it is part of the shared reference " + d.c.a(d2.d(), d2.e()) + "-" + d.c.a(d2.f(), d2.g()));
                return;
            }
            this.j.y();
            i();
        }
    }

    public final void a(d.t.o.j jVar) {
        this.j = jVar;
    }

    public final void a(d.p.q0.i iVar) {
        this.f2835d = iVar;
    }

    public void a(d.p.q0.h hVar) {
        this.f2836e = hVar;
    }

    public void a(b bVar) {
        if (this.i) {
            k.b("Attempting to share a data validation on cell " + d.c.a(this.j) + " which already has a data validation");
            return;
        }
        i();
        this.g = bVar.d();
        this.f2837f = null;
        this.i = true;
        this.h = bVar.h;
        this.f2836e = bVar.f2836e;
    }
}
