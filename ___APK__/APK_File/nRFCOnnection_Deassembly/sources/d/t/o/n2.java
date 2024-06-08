package d.t.o;

/* loaded from: classes.dex */
class n2 extends d.p.n0 {

    /* renamed from: c, reason: collision with root package name */
    private int[] f3353c;

    public n2(int[] iArr) {
        super(d.p.k0.u0);
        this.f3353c = iArr;
    }

    @Override // d.p.n0
    public byte[] u() {
        int[] iArr = this.f3353c;
        int i = 2;
        byte[] bArr = new byte[(iArr.length * 6) + 2];
        int i2 = 0;
        d.p.d0.b(iArr.length, bArr, 0);
        while (true) {
            int[] iArr2 = this.f3353c;
            if (i2 >= iArr2.length) {
                return bArr;
            }
            d.p.d0.b(iArr2[i2], bArr, i);
            d.p.d0.b(255, bArr, i + 4);
            i += 6;
            i2++;
        }
    }
}
