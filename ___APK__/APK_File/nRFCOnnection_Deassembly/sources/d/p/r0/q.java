package d.p.r0;

/* loaded from: classes.dex */
class q extends m0 implements t0 {

    /* renamed from: f, reason: collision with root package name */
    private static d.q.c f3099f = d.q.c.b(q.class);

    /* renamed from: e, reason: collision with root package name */
    private double f3100e;

    public q() {
    }

    public int a(byte[] bArr, int i) {
        this.f3100e = d.p.u.a(bArr, i);
        return 8;
    }

    @Override // d.p.r0.m0
    public double g() {
        return this.f3100e;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public q(double d2) {
        this.f3100e = d2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // d.p.r0.s0
    public byte[] a() {
        byte[] bArr = new byte[9];
        bArr[0] = i1.j.a();
        d.p.u.a(this.f3100e, bArr, 1);
        return bArr;
    }

    public q(String str) {
        try {
            this.f3100e = Double.parseDouble(str);
        } catch (NumberFormatException e2) {
            f3099f.a(e2, e2);
            this.f3100e = 0.0d;
        }
    }
}
