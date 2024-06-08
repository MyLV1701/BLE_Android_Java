package d.t.o;

/* loaded from: classes.dex */
class w extends d.p.n0 {

    /* renamed from: c, reason: collision with root package name */
    private byte[] f3403c;

    /* renamed from: d, reason: collision with root package name */
    private double f3404d;

    public w(double d2) {
        super(d.p.k0.p0);
        this.f3404d = d2;
        this.f3403c = new byte[8];
    }

    @Override // d.p.n0
    public byte[] u() {
        d.p.u.a(this.f3404d, this.f3403c, 0);
        return this.f3403c;
    }
}
