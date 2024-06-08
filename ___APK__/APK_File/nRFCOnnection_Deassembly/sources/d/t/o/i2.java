package d.t.o;

/* loaded from: classes.dex */
class i2 extends d.p.n0 {

    /* renamed from: c, reason: collision with root package name */
    private byte[] f3313c;

    public i2(int i) {
        super(d.p.k0.v);
        this.f3313c = new byte[i * 2];
        int i2 = 0;
        while (i2 < i) {
            int i3 = i2 + 1;
            d.p.d0.b(i3, this.f3313c, i2 * 2);
            i2 = i3;
        }
    }

    @Override // d.p.n0
    public byte[] u() {
        return this.f3313c;
    }
}
