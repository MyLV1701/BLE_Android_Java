package d.t.o;

/* loaded from: classes.dex */
class g1 extends d.p.n0 {

    /* renamed from: c, reason: collision with root package name */
    private boolean f3290c;

    /* renamed from: d, reason: collision with root package name */
    private byte[] f3291d;

    public g1(boolean z) {
        super(d.p.k0.M);
        this.f3290c = z;
        this.f3291d = new byte[2];
        if (this.f3290c) {
            d.p.d0.b(1, this.f3291d, 0);
        }
    }

    @Override // d.p.n0
    public byte[] u() {
        return this.f3291d;
    }
}
