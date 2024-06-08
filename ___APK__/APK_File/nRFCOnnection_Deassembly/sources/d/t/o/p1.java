package d.t.o;

/* loaded from: classes.dex */
class p1 extends d.p.n0 {

    /* renamed from: c, reason: collision with root package name */
    private boolean f3366c;

    /* renamed from: d, reason: collision with root package name */
    private byte[] f3367d;

    public p1(boolean z) {
        super(d.p.k0.K);
        this.f3366c = z;
        this.f3367d = new byte[2];
        if (this.f3366c) {
            d.p.d0.b(1, this.f3367d, 0);
        }
    }

    @Override // d.p.n0
    public byte[] u() {
        return this.f3367d;
    }
}
