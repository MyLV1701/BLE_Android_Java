package d.t.o;

/* loaded from: classes.dex */
class d1 extends d.p.n0 {

    /* renamed from: c, reason: collision with root package name */
    private boolean f3266c;

    /* renamed from: d, reason: collision with root package name */
    private byte[] f3267d;

    public d1(boolean z) {
        super(d.p.k0.f0);
        this.f3266c = z;
        this.f3267d = new byte[2];
        if (this.f3266c) {
            d.p.d0.b(1, this.f3267d, 0);
        }
    }

    @Override // d.p.n0
    public byte[] u() {
        return this.f3267d;
    }
}
