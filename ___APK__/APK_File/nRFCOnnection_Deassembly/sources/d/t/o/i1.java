package d.t.o;

/* loaded from: classes.dex */
class i1 extends d.p.n0 {

    /* renamed from: c, reason: collision with root package name */
    private int f3311c;

    /* renamed from: d, reason: collision with root package name */
    private int f3312d;

    public i1(int i, int i2) {
        super(d.p.k0.G0);
        this.f3311c = i2;
        this.f3312d = i;
    }

    @Override // d.p.n0
    public byte[] u() {
        byte[] bArr = new byte[10];
        int i = 0;
        d.p.d0.b(this.f3312d, bArr, 0);
        d.p.d0.b(this.f3311c, bArr, 2);
        int i2 = this.f3311c;
        if (i2 > 0) {
            d.p.d0.b(i2, bArr, 4);
        }
        int i3 = this.f3312d;
        if (i3 > 0) {
            d.p.d0.b(i3, bArr, 6);
        }
        if (this.f3311c > 0 && this.f3312d == 0) {
            i = 2;
        } else if (this.f3311c == 0 && this.f3312d > 0) {
            i = 1;
        } else if (this.f3311c <= 0 || this.f3312d <= 0) {
            i = 3;
        }
        d.p.d0.b(i, bArr, 8);
        return bArr;
    }
}
