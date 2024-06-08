package d.t.o;

/* loaded from: classes.dex */
class m1 extends d.p.n0 {

    /* renamed from: c, reason: collision with root package name */
    private byte[] f3340c;

    /* renamed from: d, reason: collision with root package name */
    private boolean f3341d;

    public m1(boolean z) {
        super(d.p.k0.N);
        this.f3341d = z;
        this.f3340c = new byte[2];
        if (this.f3341d) {
            this.f3340c[0] = 1;
        }
    }

    @Override // d.p.n0
    public byte[] u() {
        return this.f3340c;
    }
}
