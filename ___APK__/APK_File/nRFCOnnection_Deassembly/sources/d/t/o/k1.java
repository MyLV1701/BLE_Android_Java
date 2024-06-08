package d.t.o;

/* loaded from: classes.dex */
class k1 extends d.p.n0 {

    /* renamed from: c, reason: collision with root package name */
    private boolean f3325c;

    /* renamed from: d, reason: collision with root package name */
    private byte[] f3326d;

    public k1(boolean z) {
        super(d.p.k0.g0);
        this.f3325c = z;
        this.f3326d = new byte[2];
        if (this.f3325c) {
            return;
        }
        d.p.d0.b(1, this.f3326d, 0);
    }

    @Override // d.p.n0
    public byte[] u() {
        return this.f3326d;
    }
}
