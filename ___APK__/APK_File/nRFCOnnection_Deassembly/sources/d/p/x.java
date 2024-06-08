package d.p;

/* loaded from: classes.dex */
public class x extends n0 implements d.r.f {

    /* renamed from: c, reason: collision with root package name */
    private int f3141c;

    /* renamed from: d, reason: collision with root package name */
    private int f3142d;

    /* renamed from: e, reason: collision with root package name */
    private int f3143e;

    /* renamed from: f, reason: collision with root package name */
    private int f3144f;
    private int g;
    private byte h;
    private byte i;
    private boolean j;
    private boolean k;
    private String l;
    private boolean m;
    private int n;

    /* loaded from: classes.dex */
    private static class b {
        private b() {
        }
    }

    static {
        d.q.c.b(x.class);
        new b();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public x(String str, int i, int i2, boolean z, int i3, int i4, int i5) {
        super(k0.i0);
        this.f3143e = i2;
        this.g = i3;
        this.l = str;
        this.f3141c = i;
        this.j = z;
        this.f3144f = i5;
        this.f3142d = i4;
        this.m = false;
        this.k = false;
    }

    public final void a(int i) {
        this.n = i;
        this.m = true;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof x)) {
            return false;
        }
        x xVar = (x) obj;
        return this.f3141c == xVar.f3141c && this.f3142d == xVar.f3142d && this.f3143e == xVar.f3143e && this.f3144f == xVar.f3144f && this.g == xVar.g && this.j == xVar.j && this.k == xVar.k && this.h == xVar.h && this.i == xVar.i && this.l.equals(xVar.l);
    }

    @Override // d.r.f
    public String getName() {
        return this.l;
    }

    public int hashCode() {
        return this.l.hashCode();
    }

    public final boolean i() {
        return this.m;
    }

    @Override // d.r.f
    public boolean k() {
        return this.k;
    }

    @Override // d.r.f
    public int m() {
        return this.f3141c;
    }

    @Override // d.r.f
    public d.r.n n() {
        return d.r.n.a(this.f3144f);
    }

    @Override // d.r.f
    public d.r.o o() {
        return d.r.o.a(this.g);
    }

    @Override // d.r.f
    public d.r.e p() {
        return d.r.e.a(this.f3142d);
    }

    @Override // d.r.f
    public int q() {
        return this.f3143e;
    }

    @Override // d.r.f
    public boolean r() {
        return this.j;
    }

    @Override // d.p.n0
    public byte[] u() {
        byte[] bArr = new byte[(this.l.length() * 2) + 16];
        d0.b(this.f3141c * 20, bArr, 0);
        if (this.j) {
            bArr[2] = (byte) (bArr[2] | 2);
        }
        if (this.k) {
            bArr[2] = (byte) (bArr[2] | 8);
        }
        d0.b(this.f3142d, bArr, 4);
        d0.b(this.f3143e, bArr, 6);
        d0.b(this.f3144f, bArr, 8);
        bArr[10] = (byte) this.g;
        bArr[11] = this.h;
        bArr[12] = this.i;
        bArr[13] = 0;
        bArr[14] = (byte) this.l.length();
        bArr[15] = 1;
        j0.b(this.l, bArr, 16);
        return bArr;
    }

    public final int v() {
        return this.n;
    }

    public final void w() {
        this.m = false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public x(d.r.f fVar) {
        super(k0.i0);
        d.q.a.a(fVar != null);
        this.f3141c = fVar.m();
        this.f3142d = fVar.p().a();
        this.f3143e = fVar.q();
        this.f3144f = fVar.n().a();
        this.g = fVar.o().a();
        this.j = fVar.r();
        this.l = fVar.getName();
        this.k = fVar.k();
        this.m = false;
    }
}
