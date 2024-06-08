package d.t.o;

/* loaded from: classes.dex */
class i extends d.p.n0 {

    /* renamed from: d, reason: collision with root package name */
    static a f3305d = new a(0);

    /* renamed from: e, reason: collision with root package name */
    static a f3306e = new a(1);

    /* renamed from: c, reason: collision with root package name */
    private a f3307c;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a, reason: collision with root package name */
        int f3308a;

        public a(int i) {
            this.f3308a = i;
        }
    }

    static {
        new a(-1);
    }

    public i(a aVar) {
        super(d.p.k0.k0);
        this.f3307c = aVar;
    }

    @Override // d.p.n0
    public byte[] u() {
        byte[] bArr = new byte[2];
        d.p.d0.b(this.f3307c.f3308a, bArr, 0);
        return bArr;
    }
}
