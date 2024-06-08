package d.p;

/* loaded from: classes.dex */
public class p {

    /* renamed from: f, reason: collision with root package name */
    private static int f2913f;
    private static int g;
    private static int h;

    /* renamed from: a, reason: collision with root package name */
    private boolean f2914a;

    /* renamed from: b, reason: collision with root package name */
    private boolean f2915b;

    /* renamed from: c, reason: collision with root package name */
    private boolean f2916c;

    /* renamed from: d, reason: collision with root package name */
    private int f2917d;

    /* renamed from: e, reason: collision with root package name */
    private int f2918e;

    static {
        d.q.c.b(p.class);
        f2913f = 1;
        g = 2;
        h = 4;
    }

    public p(byte[] bArr) {
        int a2 = d0.a(bArr[0], bArr[1]);
        this.f2914a = (f2913f & a2) != 0;
        this.f2915b = (g & a2) != 0;
        this.f2916c = (a2 & h) != 0;
        this.f2918e = d0.a(bArr[10], bArr[11], bArr[12], bArr[13]);
        this.f2917d = d0.a(bArr[14], bArr[15], bArr[16], bArr[17]);
    }

    public void a() {
        this.f2917d++;
    }

    public void b() {
        this.f2917d--;
    }

    public byte[] c() {
        byte[] bArr = new byte[18];
        int i = this.f2914a ? f2913f | 0 : 0;
        if (this.f2915b) {
            i |= g;
        }
        if (this.f2916c) {
            i |= h;
        }
        d0.b(i, bArr, 0);
        d0.a(this.f2918e, bArr, 10);
        d0.a(this.f2917d, bArr, 14);
        return bArr;
    }

    public int d() {
        return this.f2917d;
    }

    public p(int i, int i2) {
        this.f2918e = i;
        this.f2917d = i2;
        this.f2916c = true;
    }
}
