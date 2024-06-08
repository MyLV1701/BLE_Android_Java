package d.t.o;

/* loaded from: classes.dex */
class m0 extends d.p.n0 {

    /* renamed from: c, reason: collision with root package name */
    private byte[] f3338c;

    /* renamed from: d, reason: collision with root package name */
    private boolean f3339d;

    public m0(boolean z) {
        super(d.p.k0.Q);
        this.f3339d = z;
        this.f3338c = new byte[2];
        if (this.f3339d) {
            this.f3338c[0] = 1;
        }
    }

    @Override // d.p.n0
    public byte[] u() {
        return this.f3338c;
    }
}
