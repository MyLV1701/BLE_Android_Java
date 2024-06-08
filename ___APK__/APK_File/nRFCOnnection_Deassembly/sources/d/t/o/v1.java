package d.t.o;

/* loaded from: classes.dex */
class v1 extends d.p.n0 {

    /* renamed from: c, reason: collision with root package name */
    private int f3396c;

    public v1(int i) {
        super(d.p.k0.F0);
        this.f3396c = i;
    }

    @Override // d.p.n0
    public byte[] u() {
        byte[] bArr = new byte[4];
        d.p.d0.b(this.f3396c, bArr, 0);
        d.p.d0.b(100, bArr, 2);
        return bArr;
    }
}
