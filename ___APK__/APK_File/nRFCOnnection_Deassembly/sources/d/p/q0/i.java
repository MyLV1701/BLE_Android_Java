package d.p.q0;

/* loaded from: classes.dex */
public class i implements r {
    private static d.q.c w = d.q.c.b(i.class);

    /* renamed from: a, reason: collision with root package name */
    private t f2990a;

    /* renamed from: b, reason: collision with root package name */
    private t f2991b;

    /* renamed from: c, reason: collision with root package name */
    private z f2992c;

    /* renamed from: d, reason: collision with root package name */
    private b0 f2993d;

    /* renamed from: e, reason: collision with root package name */
    private boolean f2994e;

    /* renamed from: f, reason: collision with root package name */
    private int f2995f;
    private int g;
    private int h;
    private int i;
    private o m;
    private int o;
    private z p;
    private l0 q;
    private a0 r;
    private d.p.m s;
    private d.p.m t;
    private String u;
    private d.o v;
    private d0 l = d0.f2963b;
    private e0 n = e0.f2973f;
    private double j = 3.0d;
    private double k = 4.0d;

    public i(String str, int i, int i2) {
        this.f2994e = false;
        this.f2994e = true;
        this.h = i;
        this.i = i2;
        this.u = str;
    }

    private t h() {
        if (!this.f2994e) {
            i();
        }
        return this.f2990a;
    }

    private void i() {
        this.f2990a = this.m.a(this.o);
        d.q.a.a(this.f2990a != null);
        u[] i = this.f2990a.i();
        g0 g0Var = (g0) this.f2990a.i()[0];
        this.f2995f = this.f2993d.v();
        this.g = g0Var.i();
        this.n = e0.a(g0Var.j());
        if (this.n == e0.g) {
            w.b("Unknown shape type");
        }
        e eVar = null;
        for (int i2 = 0; i2 < i.length && eVar == null; i2++) {
            if (i[i2].h() == w.o) {
                eVar = (e) i[i2];
            }
        }
        if (eVar == null) {
            w.b("client anchor not found");
        } else {
            this.h = ((int) eVar.j()) - 1;
            this.i = ((int) eVar.l()) + 1;
            this.j = eVar.k() - eVar.j();
            this.k = eVar.m() - eVar.l();
        }
        this.f2994e = true;
    }

    @Override // d.p.q0.r
    public final void a(int i, int i2, int i3) {
        this.f2995f = i;
        this.g = i3;
        if (this.l == d0.f2962a) {
            this.l = d0.f2964c;
        }
    }

    @Override // d.p.q0.r
    public void a(q qVar) {
    }

    @Override // d.p.q0.r
    public t b() {
        if (!this.f2994e) {
            i();
        }
        if (this.l == d0.f2962a) {
            return h();
        }
        if (this.f2991b == null) {
            this.f2991b = new h0();
            this.f2991b.a(new g0(this.n, this.g, 2560));
            c0 c0Var = new c0();
            c0Var.a(344, false, false, 0);
            c0Var.a(385, false, false, 134217808);
            c0Var.a(387, false, false, 134217808);
            c0Var.a(959, false, false, 131074);
            this.f2991b.a(c0Var);
            double d2 = this.h;
            Double.isNaN(d2);
            double d3 = d2 + 1.3d;
            double d4 = this.i;
            Double.isNaN(d4);
            double max = Math.max(0.0d, d4 - 0.6d);
            double d5 = this.h;
            Double.isNaN(d5);
            double d6 = d5 + 1.3d + this.j;
            double d7 = this.i;
            double d8 = this.k;
            Double.isNaN(d7);
            this.f2991b.a(new e(d3, max, d6, d7 + d8, 1));
            this.f2991b.a(new f());
            this.f2991b.a(new g());
        }
        return this.f2991b;
    }

    @Override // d.p.q0.r
    public boolean c() {
        return this.f2992c.v();
    }

    @Override // d.p.q0.r
    public boolean d() {
        return true;
    }

    @Override // d.p.q0.r
    public d0 e() {
        return this.l;
    }

    @Override // d.p.q0.r
    public String f() {
        d.q.a.a(false);
        return null;
    }

    public String g() {
        if (this.u == null) {
            d.q.a.a(this.s != null);
            byte[] u = this.s.u();
            if (u[0] == 0) {
                this.u = d.p.j0.a(u, u.length - 1, 1, this.v);
            } else {
                this.u = d.p.j0.a(u, (u.length - 1) / 2, 1);
            }
        }
        return this.u;
    }

    public int hashCode() {
        return this.u.hashCode();
    }

    @Override // d.p.q0.r
    public z a() {
        return this.f2992c;
    }

    public void a(double d2) {
        if (this.l == d0.f2962a) {
            if (!this.f2994e) {
                i();
            }
            this.l = d0.f2964c;
        }
        this.k = d2;
    }

    @Override // d.p.q0.r
    public void a(d.t.o.e0 e0Var) {
        if (this.l == d0.f2962a) {
            e0Var.a(this.f2993d);
            z zVar = this.p;
            if (zVar != null) {
                e0Var.a(zVar);
            }
            e0Var.a(this.q);
            e0Var.a(this.s);
            d.p.m mVar = this.t;
            if (mVar != null) {
                e0Var.a(mVar);
                return;
            }
            return;
        }
        e0Var.a(new b0(this.f2995f, b0.i));
        e0Var.a(new z(new g().b()));
        e0Var.a(new l0(g()));
        byte[] bArr = new byte[(this.u.length() * 2) + 1];
        bArr[0] = 1;
        d.p.j0.b(this.u, bArr, 1);
        e0Var.a(new d.p.m(bArr));
        byte[] bArr2 = new byte[16];
        d.p.d0.b(0, bArr2, 0);
        d.p.d0.b(0, bArr2, 2);
        d.p.d0.b(this.u.length(), bArr2, 8);
        d.p.d0.b(0, bArr2, 10);
        e0Var.a(new d.p.m(bArr2));
    }

    public void b(double d2) {
        if (this.l == d0.f2962a) {
            if (!this.f2994e) {
                i();
            }
            this.l = d0.f2964c;
        }
        this.j = d2;
    }

    @Override // d.p.q0.r
    public void b(d.t.o.e0 e0Var) {
        if (this.l == d0.f2962a) {
            e0Var.a(this.r);
        } else {
            e0Var.a(new a0(this.h, this.i, this.f2995f));
        }
    }
}
