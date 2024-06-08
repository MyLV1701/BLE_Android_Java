package d.t.o;

/* loaded from: classes.dex */
class m2 extends d.p.n0 {

    /* renamed from: c, reason: collision with root package name */
    private byte[] f3342c;

    /* renamed from: d, reason: collision with root package name */
    private boolean f3343d;

    public m2(boolean z) {
        super(d.p.k0.R);
        this.f3343d = z;
        this.f3342c = new byte[2];
        if (this.f3343d) {
            this.f3342c[0] = 1;
        }
    }

    @Override // d.p.n0
    public byte[] u() {
        return this.f3342c;
    }
}
