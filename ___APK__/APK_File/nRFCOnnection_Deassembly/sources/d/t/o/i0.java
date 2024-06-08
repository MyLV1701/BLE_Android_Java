package d.t.o;

/* loaded from: classes.dex */
class i0 extends d.p.n0 {

    /* renamed from: c, reason: collision with root package name */
    private byte[] f3309c;

    /* renamed from: d, reason: collision with root package name */
    private boolean f3310d;

    public i0(boolean z) {
        super(d.p.k0.U);
        this.f3310d = z;
        this.f3309c = new byte[2];
        if (this.f3310d) {
            this.f3309c[0] = 1;
        }
    }

    @Override // d.p.n0
    public byte[] u() {
        return this.f3309c;
    }
}
