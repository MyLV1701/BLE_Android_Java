package d.t.o;

/* loaded from: classes.dex */
class v extends d.p.n0 {

    /* renamed from: c, reason: collision with root package name */
    private byte[] f3393c;

    /* renamed from: d, reason: collision with root package name */
    private int f3394d;

    /* renamed from: e, reason: collision with root package name */
    private boolean f3395e;

    public v(int i, boolean z) {
        super(d.p.k0.D);
        this.f3393c = new byte[4];
        this.f3394d = i;
        this.f3395e = z;
    }

    @Override // d.p.n0
    public byte[] u() {
        if (this.f3395e) {
            byte[] bArr = this.f3393c;
            bArr[0] = (byte) (bArr[0] | 1);
        }
        d.p.d0.b(this.f3394d, this.f3393c, 2);
        return this.f3393c;
    }
}
