package d.t.o;

/* loaded from: classes.dex */
class r2 extends d.p.n0 {

    /* renamed from: c, reason: collision with root package name */
    private boolean f3378c;

    /* renamed from: d, reason: collision with root package name */
    private byte[] f3379d;

    public r2(boolean z) {
        super(d.p.k0.W);
        this.f3378c = z;
        this.f3379d = new byte[2];
        if (this.f3378c) {
            d.p.d0.b(1, this.f3379d, 0);
        }
    }

    @Override // d.p.n0
    public byte[] u() {
        return this.f3379d;
    }
}
