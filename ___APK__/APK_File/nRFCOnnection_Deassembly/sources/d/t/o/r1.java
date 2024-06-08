package d.t.o;

/* loaded from: classes.dex */
class r1 extends d.p.n0 {

    /* renamed from: c, reason: collision with root package name */
    private boolean f3376c;

    /* renamed from: d, reason: collision with root package name */
    private byte[] f3377d;

    public r1(boolean z) {
        super(d.p.k0.a0);
        this.f3376c = z;
        this.f3377d = new byte[2];
        if (this.f3376c) {
            d.p.d0.b(1, this.f3377d, 0);
        }
    }

    @Override // d.p.n0
    public byte[] u() {
        return this.f3377d;
    }
}
