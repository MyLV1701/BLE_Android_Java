package d.t.o;

/* loaded from: classes.dex */
class l0 extends d.p.n0 {

    /* renamed from: c, reason: collision with root package name */
    private int f3328c;

    /* renamed from: d, reason: collision with root package name */
    private byte[] f3329d;

    public l0(int i) {
        super(d.p.k0.e0);
        this.f3328c = i;
        this.f3329d = new byte[2];
        d.p.d0.b(this.f3328c, this.f3329d, 0);
    }

    @Override // d.p.n0
    public byte[] u() {
        return this.f3329d;
    }
}
