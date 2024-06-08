package d.p;

/* loaded from: classes.dex */
public class k extends n0 {

    /* renamed from: c, reason: collision with root package name */
    private a f2875c;

    /* renamed from: d, reason: collision with root package name */
    private a[] f2876d;

    /* renamed from: e, reason: collision with root package name */
    private int f2877e;

    /* renamed from: f, reason: collision with root package name */
    private boolean f2878f;
    private byte[] g;

    /* loaded from: classes.dex */
    private static class a {

        /* renamed from: a, reason: collision with root package name */
        public int f2879a;

        /* renamed from: b, reason: collision with root package name */
        public int f2880b;

        /* renamed from: c, reason: collision with root package name */
        public int f2881c;

        /* renamed from: d, reason: collision with root package name */
        public int f2882d;
    }

    static {
        d.q.c.b(k.class);
    }

    @Override // d.p.n0
    public byte[] u() {
        if (!this.f2878f) {
            return this.g;
        }
        int i = 14;
        byte[] bArr = new byte[(this.f2876d.length * 8) + 14];
        int i2 = 0;
        System.arraycopy(this.g, 0, bArr, 0, 4);
        d0.b(this.f2875c.f2879a, bArr, 4);
        d0.b(this.f2875c.f2881c, bArr, 6);
        d0.b(this.f2875c.f2880b, bArr, 8);
        d0.b(this.f2875c.f2882d, bArr, 10);
        d0.b(this.f2877e, bArr, 12);
        while (true) {
            a[] aVarArr = this.f2876d;
            if (i2 >= aVarArr.length) {
                return bArr;
            }
            d0.b(aVarArr[i2].f2879a, bArr, i);
            d0.b(this.f2876d[i2].f2881c, bArr, i + 2);
            d0.b(this.f2876d[i2].f2880b, bArr, i + 4);
            d0.b(this.f2876d[i2].f2882d, bArr, i + 6);
            i += 8;
            i2++;
        }
    }
}
