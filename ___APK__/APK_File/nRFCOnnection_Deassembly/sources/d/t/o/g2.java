package d.t.o;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class g2 {

    /* renamed from: a, reason: collision with root package name */
    private d.t.j f3292a = null;

    /* renamed from: b, reason: collision with root package name */
    private d.t.j f3293b = null;

    /* renamed from: c, reason: collision with root package name */
    private d.t.i f3294c = null;

    /* renamed from: d, reason: collision with root package name */
    private d.t.i f3295d = null;

    /* renamed from: e, reason: collision with root package name */
    private d.t.i f3296e = null;

    /* renamed from: f, reason: collision with root package name */
    private d.t.i f3297f;

    static {
        d.q.c.b(g2.class);
    }

    private synchronized void g() {
        this.f3292a = new d.t.j(d.t.m.f3219a);
    }

    private synchronized void h() {
        this.f3297f = new d.t.i(d.t.c.f3208b);
    }

    private synchronized void i() {
        this.f3296e = new d.t.i(a(), new d.t.b(";;;"));
    }

    private synchronized void j() {
        this.f3293b = new d.t.j(d.t.m.f3220b);
    }

    private synchronized void k() {
        this.f3295d = new d.t.i(d(), d.t.f.f3210a);
    }

    private synchronized void l() {
        this.f3294c = new d.t.i(a(), d.t.f.f3210a);
        this.f3294c.a(a());
    }

    public d.t.j a() {
        if (this.f3292a == null) {
            g();
        }
        return this.f3292a;
    }

    public d.t.i b() {
        if (this.f3297f == null) {
            h();
        }
        return this.f3297f;
    }

    public d.t.i c() {
        if (this.f3296e == null) {
            i();
        }
        return this.f3296e;
    }

    public d.t.j d() {
        if (this.f3293b == null) {
            j();
        }
        return this.f3293b;
    }

    public d.t.i e() {
        if (this.f3295d == null) {
            k();
        }
        return this.f3295d;
    }

    public d.t.i f() {
        if (this.f3294c == null) {
            l();
        }
        return this.f3294c;
    }

    public d.p.p0 a(d.p.p0 p0Var) {
        if (p0Var == d.t.m.f3221c) {
            p0Var = f();
        } else if (p0Var == d.t.m.f3222d) {
            p0Var = e();
        } else if (p0Var == d.t.m.f3223e) {
            p0Var = c();
        } else if (p0Var == t.n) {
            p0Var = b();
        }
        if (p0Var.l() == d.t.m.f3219a) {
            p0Var.a(a());
        } else if (p0Var.l() == d.t.m.f3220b) {
            p0Var.a(d());
        }
        return p0Var;
    }
}
