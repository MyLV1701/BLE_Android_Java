package d.t.o;

/* loaded from: classes.dex */
class j0 extends d.p.n0 {

    /* renamed from: c, reason: collision with root package name */
    private byte[] f3318c;

    /* renamed from: d, reason: collision with root package name */
    private int f3319d;

    /* renamed from: e, reason: collision with root package name */
    private int f3320e;

    /* renamed from: f, reason: collision with root package name */
    private int f3321f;
    private int g;

    public j0() {
        super(d.p.k0.V);
    }

    public void b(int i) {
        this.g = i;
        this.f3320e = (i * 14) + 1;
    }

    public void c(int i) {
        this.f3321f = i;
        this.f3319d = (i * 14) + 1;
    }

    @Override // d.p.n0
    public byte[] u() {
        this.f3318c = new byte[8];
        d.p.d0.b(this.f3319d, this.f3318c, 0);
        d.p.d0.b(this.f3320e, this.f3318c, 2);
        d.p.d0.b(this.f3321f, this.f3318c, 4);
        d.p.d0.b(this.g, this.f3318c, 6);
        return this.f3318c;
    }
}
