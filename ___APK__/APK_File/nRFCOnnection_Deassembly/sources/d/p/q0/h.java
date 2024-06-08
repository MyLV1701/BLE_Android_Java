package d.p.q0;

import android.R;

/* loaded from: classes.dex */
public class h implements r {
    private static d.q.c m = d.q.c.b(h.class);

    /* renamed from: a, reason: collision with root package name */
    private t f2984a;

    /* renamed from: b, reason: collision with root package name */
    private z f2985b;

    /* renamed from: c, reason: collision with root package name */
    private b0 f2986c;

    /* renamed from: d, reason: collision with root package name */
    private boolean f2987d;

    /* renamed from: e, reason: collision with root package name */
    private int f2988e;

    /* renamed from: f, reason: collision with root package name */
    private int f2989f;
    private int g;
    private int h;
    private o j;
    private int l;
    private d0 i = d0.f2963b;
    private e0 k = e0.f2972e;

    public h() {
        this.f2987d = false;
        this.f2987d = true;
    }

    private t h() {
        if (!this.f2987d) {
            i();
        }
        return this.f2984a;
    }

    private void i() {
        this.f2984a = this.j.a(this.l);
        d.q.a.a(this.f2984a != null);
        u[] i = this.f2984a.i();
        g0 g0Var = (g0) this.f2984a.i()[0];
        this.f2988e = this.f2986c.v();
        this.f2989f = g0Var.i();
        this.k = e0.a(g0Var.j());
        if (this.k == e0.g) {
            m.b("Unknown shape type");
        }
        e eVar = null;
        for (int i2 = 0; i2 < i.length && eVar == null; i2++) {
            if (i[i2].h() == w.o) {
                eVar = (e) i[i2];
            }
        }
        if (eVar == null) {
            m.b("Client anchor not found");
        } else {
            this.g = (int) eVar.j();
            this.h = (int) eVar.l();
        }
        this.f2987d = true;
    }

    @Override // d.p.q0.r
    public final void a(int i, int i2, int i3) {
        this.f2988e = i;
        this.f2989f = i3;
        if (this.i == d0.f2962a) {
            this.i = d0.f2964c;
        }
    }

    @Override // d.p.q0.r
    public void a(q qVar) {
    }

    @Override // d.p.q0.r
    public t b() {
        if (!this.f2987d) {
            i();
        }
        if (this.i == d0.f2962a) {
            return h();
        }
        h0 h0Var = new h0();
        h0Var.a(new g0(this.k, this.f2989f, 2560));
        c0 c0Var = new c0();
        c0Var.a(127, false, false, R.string.aerr_wait);
        c0Var.a(191, false, false, 524296);
        c0Var.a(511, false, false, 524288);
        c0Var.a(959, false, false, 131072);
        h0Var.a(c0Var);
        h0Var.a(new e(this.g, this.h, r2 + 1, r3 + 1, 1));
        h0Var.a(new f());
        return h0Var;
    }

    @Override // d.p.q0.r
    public void b(d.t.o.e0 e0Var) {
    }

    @Override // d.p.q0.r
    public boolean c() {
        return this.f2985b.v();
    }

    @Override // d.p.q0.r
    public boolean d() {
        return false;
    }

    @Override // d.p.q0.r
    public d0 e() {
        return this.i;
    }

    @Override // d.p.q0.r
    public String f() {
        d.q.a.a(false);
        return null;
    }

    public final int g() {
        if (!this.f2987d) {
            i();
        }
        return this.f2988e;
    }

    public int hashCode() {
        return h.class.getName().hashCode();
    }

    @Override // d.p.q0.r
    public z a() {
        return this.f2985b;
    }

    @Override // d.p.q0.r
    public void a(d.t.o.e0 e0Var) {
        if (this.i == d0.f2962a) {
            e0Var.a(this.f2986c);
        } else {
            e0Var.a(new b0(this.f2988e, b0.h));
        }
    }
}
