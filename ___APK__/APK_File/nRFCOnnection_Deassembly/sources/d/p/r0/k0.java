package d.p.r0;

/* loaded from: classes.dex */
class k0 extends o0 implements t0 {

    /* renamed from: e, reason: collision with root package name */
    private d.p.l0 f3089e;

    /* renamed from: f, reason: collision with root package name */
    private String f3090f;
    private int g;

    static {
        d.q.c.b(k0.class);
    }

    public k0(d.p.l0 l0Var) {
        this.f3089e = l0Var;
        d.q.a.a(this.f3089e != null);
    }

    public int a(byte[] bArr, int i) {
        try {
            this.g = d.p.d0.a(bArr[i], bArr[i + 1]);
            this.f3090f = this.f3089e.a(this.g - 1);
            return 4;
        } catch (d.p.e0 unused) {
            throw new v(v.h, "");
        }
    }

    public k0(String str, d.p.l0 l0Var) {
        this.f3090f = str;
        this.f3089e = l0Var;
        this.g = this.f3089e.a(this.f3090f);
        int i = this.g;
        if (i >= 0) {
            this.g = i + 1;
            return;
        }
        throw new v(v.h, this.f3090f);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // d.p.r0.s0
    public byte[] a() {
        byte[] bArr = new byte[5];
        bArr[0] = i1.p.d();
        if (b() == r0.f3102b) {
            bArr[0] = i1.p.c();
        }
        d.p.d0.b(this.g, bArr, 1);
        return bArr;
    }

    @Override // d.p.r0.s0
    public void a(StringBuffer stringBuffer) {
        stringBuffer.append(this.f3090f);
    }
}
