package d.t.o;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class b2 extends d.p.n0 {

    /* renamed from: c, reason: collision with root package name */
    private byte[] f3243c;

    /* renamed from: d, reason: collision with root package name */
    private double f3244d;

    /* renamed from: e, reason: collision with root package name */
    private double f3245e;

    /* renamed from: f, reason: collision with root package name */
    private d.r.j f3246f;
    private d.r.i g;
    private int h;
    private int i;
    private int j;
    private int k;
    private int l;
    private int m;
    private int n;
    private int o;
    private boolean p;

    public b2(d.m mVar) {
        super(d.p.k0.S);
        d.q.c.b(b2.class);
        this.f3246f = mVar.t();
        this.g = mVar.w();
        this.f3244d = mVar.o();
        this.f3245e = mVar.m();
        this.h = mVar.y().a();
        this.m = mVar.q();
        this.n = mVar.M();
        this.k = mVar.k();
        this.l = mVar.i();
        this.j = mVar.x();
        this.i = mVar.I();
        this.o = mVar.c();
        this.p = true;
    }

    @Override // d.p.n0
    public byte[] u() {
        this.f3243c = new byte[34];
        d.p.d0.b(this.h, this.f3243c, 0);
        d.p.d0.b(this.i, this.f3243c, 2);
        d.p.d0.b(this.j, this.f3243c, 4);
        d.p.d0.b(this.k, this.f3243c, 6);
        d.p.d0.b(this.l, this.f3243c, 8);
        int i = this.g == d.r.i.f3175a ? 1 : 0;
        if (this.f3246f == d.r.j.f3176a) {
            i |= 2;
        }
        if (this.j != 0) {
            i |= 128;
        }
        if (!this.p) {
            i |= 4;
        }
        d.p.d0.b(i, this.f3243c, 10);
        d.p.d0.b(this.m, this.f3243c, 12);
        d.p.d0.b(this.n, this.f3243c, 14);
        d.p.u.a(this.f3244d, this.f3243c, 16);
        d.p.u.a(this.f3245e, this.f3243c, 24);
        d.p.d0.b(this.o, this.f3243c, 32);
        return this.f3243c;
    }
}
