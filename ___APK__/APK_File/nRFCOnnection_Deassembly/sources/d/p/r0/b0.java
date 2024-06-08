package d.p.r0;

/* loaded from: classes.dex */
class b0 extends m0 implements t0 {
    private static d.q.c g = d.q.c.b(b0.class);

    /* renamed from: e, reason: collision with root package name */
    private double f3054e;

    /* renamed from: f, reason: collision with root package name */
    private boolean f3055f;

    public b0() {
        this.f3055f = false;
    }

    public int a(byte[] bArr, int i) {
        this.f3054e = d.p.d0.a(bArr[i], bArr[i + 1]);
        return 2;
    }

    @Override // d.p.r0.m0
    public double g() {
        return this.f3054e;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean h() {
        return this.f3055f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // d.p.r0.s0
    public byte[] a() {
        byte[] bArr = {i1.i.a()};
        d.p.d0.b((int) this.f3054e, bArr, 1);
        return bArr;
    }

    public b0(String str) {
        try {
            this.f3054e = Integer.parseInt(str);
        } catch (NumberFormatException e2) {
            g.a(e2, e2);
            this.f3054e = 0.0d;
        }
        double d2 = this.f3054e;
        this.f3055f = d2 != ((double) ((short) ((int) d2)));
    }
}
