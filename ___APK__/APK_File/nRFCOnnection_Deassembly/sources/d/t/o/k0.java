package d.t.o;

/* loaded from: classes.dex */
class k0 extends d.p.n0 {

    /* renamed from: c, reason: collision with root package name */
    private byte[] f3323c;

    /* renamed from: d, reason: collision with root package name */
    private String f3324d;

    public k0(String str) {
        super(d.p.k0.O);
        this.f3324d = str;
    }

    @Override // d.p.n0
    public byte[] u() {
        String str = this.f3324d;
        if (str != null && str.length() != 0) {
            this.f3323c = new byte[(this.f3324d.length() * 2) + 3];
            d.p.d0.b(this.f3324d.length(), this.f3323c, 0);
            byte[] bArr = this.f3323c;
            bArr[2] = 1;
            d.p.j0.b(this.f3324d, bArr, 3);
            return this.f3323c;
        }
        this.f3323c = new byte[0];
        return this.f3323c;
    }
}
