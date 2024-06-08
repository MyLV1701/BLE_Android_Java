package d.t.o;

/* loaded from: classes.dex */
class u extends d.p.n0 {

    /* renamed from: c, reason: collision with root package name */
    private int f3391c;

    /* renamed from: d, reason: collision with root package name */
    private byte[] f3392d;

    public u(int i) {
        super(d.p.k0.C);
        this.f3391c = i;
        this.f3392d = new byte[2];
        d.p.d0.b(this.f3391c, this.f3392d, 0);
    }

    @Override // d.p.n0
    protected byte[] u() {
        return this.f3392d;
    }
}
