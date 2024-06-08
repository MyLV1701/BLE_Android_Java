package d.t.o;

/* loaded from: classes.dex */
class y1 extends d.p.n0 {

    /* renamed from: c, reason: collision with root package name */
    private byte[] f3425c;

    /* renamed from: d, reason: collision with root package name */
    private boolean f3426d;

    public y1(boolean z) {
        super(d.p.k0.A);
        this.f3426d = z;
        this.f3425c = new byte[2];
        if (this.f3426d) {
            this.f3425c[0] = 1;
        }
    }

    @Override // d.p.n0
    public byte[] u() {
        return this.f3425c;
    }
}
