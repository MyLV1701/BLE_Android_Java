package d.t.o;

/* loaded from: classes.dex */
class e2 extends d.p.n0 {

    /* renamed from: c, reason: collision with root package name */
    private String f3280c;

    public e2(String str) {
        super(d.p.k0.w);
        this.f3280c = str;
    }

    @Override // d.p.n0
    public byte[] u() {
        byte[] bArr = new byte[(this.f3280c.length() * 2) + 3];
        d.p.d0.b(this.f3280c.length(), bArr, 0);
        bArr[2] = 1;
        d.p.j0.b(this.f3280c, bArr, 3);
        return bArr;
    }
}
