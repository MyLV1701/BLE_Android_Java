package d.t.o;

/* loaded from: classes.dex */
class g0 extends d.p.n0 {

    /* renamed from: c, reason: collision with root package name */
    private byte[] f3288c;

    /* renamed from: d, reason: collision with root package name */
    private String f3289d;

    public g0(String str) {
        super(d.p.k0.P);
        this.f3289d = str;
    }

    @Override // d.p.n0
    public byte[] u() {
        String str = this.f3289d;
        if (str != null && str.length() != 0) {
            this.f3288c = new byte[(this.f3289d.length() * 2) + 3];
            d.p.d0.b(this.f3289d.length(), this.f3288c, 0);
            byte[] bArr = this.f3288c;
            bArr[2] = 1;
            d.p.j0.b(this.f3289d, bArr, 3);
            return this.f3288c;
        }
        this.f3288c = new byte[0];
        return this.f3288c;
    }
}
