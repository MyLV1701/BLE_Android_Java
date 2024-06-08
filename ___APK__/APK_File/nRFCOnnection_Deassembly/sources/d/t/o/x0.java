package d.t.o;

/* loaded from: classes.dex */
abstract class x0 extends d.p.n0 {

    /* renamed from: c, reason: collision with root package name */
    private double f3416c;

    public x0(d.p.k0 k0Var, double d2) {
        super(k0Var);
        this.f3416c = d2;
    }

    @Override // d.p.n0
    public byte[] u() {
        byte[] bArr = new byte[8];
        d.p.u.a(this.f3416c, bArr, 0);
        return bArr;
    }
}
