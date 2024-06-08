package d.p.q0;

/* loaded from: classes.dex */
final class v {

    /* renamed from: a, reason: collision with root package name */
    private int f3033a;

    /* renamed from: b, reason: collision with root package name */
    private int f3034b;

    /* renamed from: c, reason: collision with root package name */
    private int f3035c;

    /* renamed from: d, reason: collision with root package name */
    private int f3036d;

    /* renamed from: e, reason: collision with root package name */
    private int f3037e;

    /* renamed from: f, reason: collision with root package name */
    private int f3038f;
    private boolean g;
    private w h;
    private x i;

    static {
        d.q.c.b(v.class);
    }

    public v(x xVar, int i) {
        this.i = xVar;
        this.f3033a = i;
        byte[] b2 = this.i.b();
        this.f3038f = b2.length;
        int i2 = this.f3033a;
        int a2 = d.p.d0.a(b2[i2], b2[i2 + 1]);
        this.f3034b = (65520 & a2) >> 4;
        this.f3035c = a2 & 15;
        int i3 = this.f3033a;
        this.f3036d = d.p.d0.a(b2[i3 + 2], b2[i3 + 3]);
        int i4 = this.f3033a;
        this.f3037e = d.p.d0.a(b2[i4 + 4], b2[i4 + 5], b2[i4 + 6], b2[i4 + 7]);
        if (this.f3035c == 15) {
            this.g = true;
        } else {
            this.g = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(boolean z) {
        this.g = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(int i) {
        this.f3035c = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int c() {
        return this.f3034b;
    }

    public int d() {
        return this.f3037e;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int e() {
        return this.f3033a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int f() {
        return this.f3038f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public w g() {
        if (this.h == null) {
            this.h = w.a(this.f3036d);
        }
        return this.h;
    }

    public boolean h() {
        return this.g;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(int i) {
        this.f3034b = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public x b() {
        return this.i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte[] a(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length + 8];
        System.arraycopy(bArr, 0, bArr2, 8, bArr.length);
        if (this.g) {
            this.f3035c = 15;
        }
        d.p.d0.b((this.f3034b << 4) | this.f3035c, bArr2, 0);
        d.p.d0.b(this.f3036d, bArr2, 2);
        d.p.d0.a(bArr.length, bArr2, 4);
        return bArr2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte[] a() {
        byte[] bArr = new byte[this.f3037e];
        System.arraycopy(this.i.b(), this.f3033a + 8, bArr, 0, this.f3037e);
        return bArr;
    }

    public v(w wVar) {
        this.h = wVar;
        this.f3036d = this.h.a();
    }
}
