package d.t.o;

/* loaded from: classes.dex */
class x extends d.p.n0 {

    /* renamed from: c, reason: collision with root package name */
    private int f3413c;

    /* renamed from: d, reason: collision with root package name */
    private int f3414d;

    /* renamed from: e, reason: collision with root package name */
    private byte[] f3415e;

    public x(int i, int i2) {
        super(d.p.k0.h);
        this.f3413c = i;
        this.f3414d = i2;
        this.f3415e = new byte[14];
        d.p.d0.a(this.f3413c, this.f3415e, 4);
        d.p.d0.b(this.f3414d, this.f3415e, 10);
    }

    @Override // d.p.n0
    protected byte[] u() {
        return this.f3415e;
    }
}
