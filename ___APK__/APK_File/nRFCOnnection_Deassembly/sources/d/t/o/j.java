package d.t.o;

/* loaded from: classes.dex */
public abstract class j extends d.p.n0 implements d.t.g {
    private static d.q.c k = d.q.c.b(j.class);

    /* renamed from: c, reason: collision with root package name */
    private int f3314c;

    /* renamed from: d, reason: collision with root package name */
    private int f3315d;

    /* renamed from: e, reason: collision with root package name */
    private d.p.p0 f3316e;

    /* renamed from: f, reason: collision with root package name */
    private d.p.a0 f3317f;
    private boolean g;
    private v2 h;
    private d.t.h i;
    private boolean j;

    /* JADX INFO: Access modifiers changed from: protected */
    public j(d.p.k0 k0Var, int i, int i2) {
        this(k0Var, i, i2, d.t.m.f3221c);
        this.j = false;
    }

    private void z() {
        g2 g = this.h.g().g();
        this.f3316e = g.a(this.f3316e);
        try {
            if (this.f3316e.i()) {
                return;
            }
            this.f3317f.a(this.f3316e);
        } catch (d.p.f0 unused) {
            k.b("Maximum number of format records exceeded.  Using default format.");
            this.f3316e = g.f();
        }
    }

    @Override // d.t.g
    public void a(d.r.d dVar) {
        this.f3316e = (d.p.p0) dVar;
        if (this.g) {
            d.q.a.a(this.f3317f != null);
            z();
        }
    }

    @Override // d.a
    public d.b b() {
        return this.i;
    }

    @Override // d.a
    public d.r.d d() {
        return this.f3316e;
    }

    @Override // d.a
    public int e() {
        return this.f3314c;
    }

    @Override // d.a
    public int f() {
        return this.f3315d;
    }

    @Override // d.t.g
    public d.t.h j() {
        return this.i;
    }

    @Override // d.p.n0
    public byte[] u() {
        byte[] bArr = new byte[6];
        d.p.d0.b(this.f3314c, bArr, 0);
        d.p.d0.b(this.f3315d, bArr, 2);
        d.p.d0.b(this.f3316e.A(), bArr, 4);
        return bArr;
    }

    public final void v() {
        d.t.h hVar = this.i;
        if (hVar == null) {
            return;
        }
        if (this.j) {
            this.j = false;
            return;
        }
        if (hVar.a() != null) {
            d.p.q0.i iVar = new d.p.q0.i(this.i.a(), this.f3315d, this.f3314c);
            iVar.b(this.i.c());
            iVar.a(this.i.b());
            this.h.a(iVar);
            this.h.g().a(iVar);
            this.i.a(iVar);
        }
        if (this.i.e()) {
            try {
                this.i.d().a(this.f3315d, this.f3314c, this.h.g(), this.h.g(), this.h.h());
            } catch (d.p.r0.v unused) {
                d.q.a.a(false);
            }
            this.h.a(this);
            if (this.i.f()) {
                if (this.h.f() == null) {
                    d.p.q0.h hVar2 = new d.p.q0.h();
                    this.h.a((d.p.q0.r) hVar2);
                    this.h.g().a(hVar2);
                    this.h.a(hVar2);
                }
                this.i.a(this.h.f());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int w() {
        return this.f3316e.A();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean x() {
        return this.g;
    }

    public final void y() {
        this.h.b(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public j(d.p.k0 k0Var, int i, int i2, d.r.d dVar) {
        super(k0Var);
        this.f3314c = i2;
        this.f3315d = i;
        this.f3316e = (d.p.p0) dVar;
        this.g = false;
        this.j = false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(d.p.a0 a0Var, c2 c2Var, v2 v2Var) {
        this.g = true;
        this.h = v2Var;
        this.f3317f = a0Var;
        z();
        v();
    }

    @Override // d.t.g
    public void a(d.t.h hVar) {
        if (this.i != null) {
            k.b("current cell features for " + d.c.a(this) + " not null - overwriting");
            if (this.i.e() && this.i.d() != null && this.i.d().b()) {
                d.p.o d2 = this.i.d();
                k.b("Cannot add cell features to " + d.c.a(this) + " because it is part of the shared cell validation group " + d.c.a(d2.d(), d2.e()) + "-" + d.c.a(d2.f(), d2.g()));
                return;
            }
        }
        this.i = hVar;
        hVar.a(this);
        if (this.g) {
            v();
        }
    }

    public final void a(d.p.q0.i iVar) {
        this.h.b(iVar);
    }
}
