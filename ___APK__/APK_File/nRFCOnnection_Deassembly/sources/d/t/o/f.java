package d.t.o;

/* loaded from: classes.dex */
class f extends d.p.n0 {

    /* renamed from: c, reason: collision with root package name */
    private boolean f3281c;

    /* renamed from: d, reason: collision with root package name */
    private boolean f3282d;

    /* renamed from: e, reason: collision with root package name */
    private String f3283e;

    /* renamed from: f, reason: collision with root package name */
    private byte[] f3284f;

    public f(String str) {
        super(d.p.k0.f2886e);
        this.f3283e = str;
        this.f3281c = false;
        this.f3282d = false;
    }

    @Override // d.p.n0
    public byte[] u() {
        this.f3284f = new byte[(this.f3283e.length() * 2) + 8];
        if (this.f3282d) {
            this.f3284f[5] = 2;
        } else {
            this.f3284f[5] = 0;
        }
        if (this.f3281c) {
            byte[] bArr = this.f3284f;
            bArr[4] = 1;
            bArr[5] = 0;
        }
        this.f3284f[6] = (byte) this.f3283e.length();
        byte[] bArr2 = this.f3284f;
        bArr2[7] = 1;
        d.p.j0.b(this.f3283e, bArr2, 8);
        return this.f3284f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void v() {
        this.f3282d = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void w() {
        this.f3281c = true;
    }
}
