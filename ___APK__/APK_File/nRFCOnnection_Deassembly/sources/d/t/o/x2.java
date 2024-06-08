package d.t.o;

/* loaded from: classes.dex */
class x2 extends d.p.n0 {

    /* renamed from: c, reason: collision with root package name */
    private byte[] f3421c;

    public x2(String str) {
        super(d.p.k0.E);
        this.f3421c = new byte[112];
        if (str == null) {
            str = "Java Excel API v" + d.n.a();
        }
        d.p.j0.a(str, this.f3421c, 0);
        int length = str.length();
        while (true) {
            byte[] bArr = this.f3421c;
            if (length >= bArr.length) {
                return;
            }
            bArr[length] = 32;
            length++;
        }
    }

    @Override // d.p.n0
    public byte[] u() {
        return this.f3421c;
    }
}
