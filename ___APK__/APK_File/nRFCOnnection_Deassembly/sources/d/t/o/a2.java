package d.t.o;

/* loaded from: classes.dex */
class a2 extends d.p.n0 {

    /* renamed from: f, reason: collision with root package name */
    public static final a f3228f = new a(0);
    public static final a g = new a(1);
    public static final a h = new a(2);
    public static final a i = new a(3);

    /* renamed from: c, reason: collision with root package name */
    private a f3229c;

    /* renamed from: d, reason: collision with root package name */
    private int f3230d;

    /* renamed from: e, reason: collision with root package name */
    private int f3231e;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a, reason: collision with root package name */
        int f3232a;

        a(int i) {
            this.f3232a = i;
        }
    }

    public a2(a aVar, int i2, int i3) {
        super(d.p.k0.w0);
        this.f3230d = i2;
        this.f3231e = i3;
        this.f3229c = aVar;
    }

    @Override // d.p.n0
    public byte[] u() {
        byte[] bArr = new byte[15];
        bArr[0] = (byte) this.f3229c.f3232a;
        d.p.d0.b(this.f3231e, bArr, 1);
        d.p.d0.b(this.f3230d, bArr, 3);
        bArr[7] = 1;
        d.p.d0.b(this.f3231e, bArr, 9);
        d.p.d0.b(this.f3231e, bArr, 11);
        int i2 = this.f3230d;
        bArr[13] = (byte) i2;
        bArr[14] = (byte) i2;
        return bArr;
    }
}
