package d.t.o;

/* loaded from: classes.dex */
class b extends d.p.n0 {

    /* renamed from: c, reason: collision with root package name */
    private boolean f3233c;

    /* renamed from: d, reason: collision with root package name */
    private byte[] f3234d;

    public b(boolean z) {
        super(d.p.k0.d0);
        this.f3233c = z;
        this.f3234d = new byte[2];
        if (this.f3233c) {
            d.p.d0.b(1, this.f3234d, 0);
        }
    }

    @Override // d.p.n0
    public byte[] u() {
        return this.f3234d;
    }
}
