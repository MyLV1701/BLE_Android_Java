package d.t.o;

/* loaded from: classes.dex */
class l1 extends d.p.n0 {

    /* renamed from: c, reason: collision with root package name */
    private byte[] f3330c;

    /* renamed from: d, reason: collision with root package name */
    private boolean f3331d;

    public l1(boolean z) {
        super(d.p.k0.T);
        this.f3331d = z;
        this.f3330c = new byte[2];
        if (this.f3331d) {
            this.f3330c[0] = 1;
        }
    }

    @Override // d.p.n0
    public byte[] u() {
        return this.f3330c;
    }
}
