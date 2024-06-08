package d.t.o;

/* loaded from: classes.dex */
class o1 extends d.p.n0 {

    /* renamed from: c, reason: collision with root package name */
    private boolean f3358c;

    /* renamed from: d, reason: collision with root package name */
    private byte[] f3359d;

    public o1(boolean z) {
        super(d.p.k0.X);
        this.f3358c = z;
        this.f3359d = new byte[2];
        if (this.f3358c) {
            d.p.d0.b(1, this.f3359d, 0);
        }
    }

    @Override // d.p.n0
    public byte[] u() {
        return this.f3359d;
    }
}
