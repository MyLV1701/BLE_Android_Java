package d.t.o;

/* loaded from: classes.dex */
class p0 extends d.p.n0 {

    /* renamed from: c, reason: collision with root package name */
    private byte[] f3362c;

    /* renamed from: d, reason: collision with root package name */
    private int f3363d;

    /* renamed from: e, reason: collision with root package name */
    private int f3364e;

    /* renamed from: f, reason: collision with root package name */
    private int f3365f;
    private int g;

    public p0(int i, int i2, int i3) {
        super(d.p.k0.n);
        this.f3364e = i;
        this.f3363d = i2;
        this.f3365f = i3;
        this.f3362c = new byte[(this.f3365f * 4) + 16];
        this.g = 16;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(int i) {
        d.p.d0.a(i - this.f3364e, this.f3362c, this.g);
        this.g += 4;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void c(int i) {
        d.p.d0.a(i - this.f3364e, this.f3362c, 12);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // d.p.n0
    public byte[] u() {
        d.p.d0.a(this.f3363d, this.f3362c, 8);
        return this.f3362c;
    }
}
