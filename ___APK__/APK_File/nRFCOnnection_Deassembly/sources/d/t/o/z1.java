package d.t.o;

/* loaded from: classes.dex */
class z1 extends d.p.n0 {

    /* renamed from: c, reason: collision with root package name */
    private boolean f3430c;

    /* renamed from: d, reason: collision with root package name */
    private byte[] f3431d;

    public z1(boolean z) {
        super(d.p.k0.L);
        this.f3430c = z;
        this.f3431d = new byte[2];
        if (this.f3430c) {
            d.p.d0.b(1, this.f3431d, 0);
        }
    }

    @Override // d.p.n0
    public byte[] u() {
        return this.f3431d;
    }
}
