package d.t.o;

/* loaded from: classes.dex */
class s0 extends d.p.n0 {

    /* renamed from: c, reason: collision with root package name */
    private boolean f3380c;

    /* renamed from: d, reason: collision with root package name */
    private byte[] f3381d;

    public s0(boolean z) {
        super(d.p.k0.r0);
        this.f3380c = z;
        this.f3381d = new byte[2];
        if (this.f3380c) {
            this.f3381d[0] = 1;
        }
    }

    @Override // d.p.n0
    public byte[] u() {
        return this.f3381d;
    }
}
