package d.p;

/* loaded from: classes.dex */
class f extends n0 {

    /* renamed from: c, reason: collision with root package name */
    private int f2858c;

    /* renamed from: d, reason: collision with root package name */
    private int f2859d;

    public f(int i, int i2) {
        super(k0.s0);
        this.f2858c = i;
        this.f2859d = i2;
    }

    @Override // d.p.n0
    public byte[] u() {
        d0.b(this.f2858c, r0, 0);
        byte[] bArr = {0, (byte) (bArr[1] | 128), (byte) this.f2859d, -1};
        return bArr;
    }
}
