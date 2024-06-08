package d.t.o;

/* loaded from: classes.dex */
class b0 extends d.p.n0 {

    /* renamed from: c, reason: collision with root package name */
    private int f3235c;

    /* renamed from: d, reason: collision with root package name */
    private int[] f3236d;

    /* renamed from: e, reason: collision with root package name */
    private int[] f3237e;

    /* renamed from: f, reason: collision with root package name */
    private int f3238f;

    public b0(int i) {
        super(d.p.k0.q);
        this.f3238f = 0;
        this.f3235c = i;
        int v = v();
        this.f3236d = new int[v];
        this.f3237e = new int[v];
        this.f3238f = 0;
    }

    public void a(int i, int i2) {
        int[] iArr = this.f3236d;
        int i3 = this.f3238f;
        iArr[i3] = i + i2;
        this.f3237e[i3] = i2;
        this.f3238f = i3 + 1;
    }

    @Override // d.p.n0
    public byte[] u() {
        int v = v();
        byte[] bArr = new byte[(v * 8) + 2];
        d.p.d0.b(w(), bArr, 0);
        for (int i = 0; i < v; i++) {
            int i2 = i * 8;
            d.p.d0.a(this.f3236d[i], bArr, i2 + 2);
            d.p.d0.b(this.f3237e[i], bArr, i2 + 6);
        }
        return bArr;
    }

    public int v() {
        int w = w();
        if (w != 0) {
            return ((this.f3235c + w) - 1) / w;
        }
        return 0;
    }

    public int w() {
        return ((this.f3235c + 128) - 1) / 128;
    }
}
