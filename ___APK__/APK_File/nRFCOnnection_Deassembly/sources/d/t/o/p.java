package d.t.o;

/* loaded from: classes.dex */
class p extends d.p.n0 {

    /* renamed from: c, reason: collision with root package name */
    private int f3360c;

    /* renamed from: d, reason: collision with root package name */
    private int f3361d;

    public p(d.p.n nVar, d.p.n nVar2) {
        super(d.p.k0.J);
        this.f3360c = nVar.b();
        this.f3361d = nVar2.b();
    }

    @Override // d.p.n0
    public byte[] u() {
        byte[] bArr = new byte[4];
        d.p.d0.b(this.f3360c, bArr, 0);
        d.p.d0.b(this.f3361d, bArr, 2);
        return bArr;
    }
}
