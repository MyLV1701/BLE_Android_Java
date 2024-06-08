package d.t.o;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class c1 extends d.p.n0 {

    /* renamed from: c, reason: collision with root package name */
    private byte[] f3248c;

    /* renamed from: d, reason: collision with root package name */
    private String f3249d;

    /* renamed from: e, reason: collision with root package name */
    private d.p.e f3250e;

    /* renamed from: f, reason: collision with root package name */
    private int f3251f;
    private int g;
    private boolean h;
    private a[] i;

    /* loaded from: classes.dex */
    static class a {

        /* renamed from: a, reason: collision with root package name */
        private int f3252a;

        /* renamed from: b, reason: collision with root package name */
        private int f3253b;

        /* renamed from: c, reason: collision with root package name */
        private int f3254c;

        /* renamed from: d, reason: collision with root package name */
        private int f3255d;

        /* renamed from: e, reason: collision with root package name */
        private int f3256e;

        a(int i, int i2, int i3, int i4, int i5) {
            this.f3252a = i4;
            this.f3253b = i2;
            this.f3254c = i5;
            this.f3255d = i3;
            this.f3256e = i;
        }

        byte[] a() {
            byte[] bArr = new byte[10];
            d.p.d0.b(this.f3256e, bArr, 0);
            d.p.d0.b(this.f3253b, bArr, 2);
            d.p.d0.b(this.f3255d, bArr, 4);
            d.p.d0.b(this.f3252a & 255, bArr, 6);
            d.p.d0.b(this.f3254c & 255, bArr, 8);
            return bArr;
        }
    }

    static {
        d.q.c.b(c1.class);
        new a(0, 0, 0, 0, 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public c1(d.p.e eVar, int i, int i2, int i3, int i4, int i5, int i6, boolean z) {
        super(d.p.k0.u);
        this.g = 0;
        this.f3250e = eVar;
        this.f3251f = i;
        this.g = z ? 0 : this.f3251f + 1;
        this.i = new a[1];
        this.i[0] = new a(i2, i3, i4, i5, i6);
    }

    public String getName() {
        return this.f3249d;
    }

    @Override // d.p.n0
    public byte[] u() {
        byte[] bArr = this.f3248c;
        if (bArr != null && !this.h) {
            return bArr;
        }
        a[] aVarArr = this.i;
        int length = aVarArr.length > 1 ? (aVarArr.length * 11) + 4 : 11;
        this.f3248c = new byte[length + 15 + (this.f3250e != null ? 1 : this.f3249d.length())];
        d.p.d0.b(this.f3250e != null ? 32 : 0, this.f3248c, 0);
        byte[] bArr2 = this.f3248c;
        bArr2[2] = 0;
        if (this.f3250e != null) {
            bArr2[3] = 1;
        } else {
            bArr2[3] = (byte) this.f3249d.length();
        }
        d.p.d0.b(length, this.f3248c, 4);
        d.p.d0.b(this.g, this.f3248c, 6);
        d.p.d0.b(this.g, this.f3248c, 8);
        d.p.e eVar = this.f3250e;
        if (eVar != null) {
            this.f3248c[15] = (byte) eVar.a();
        } else {
            d.p.j0.a(this.f3249d, this.f3248c, 15);
        }
        int length2 = this.f3250e != null ? 16 : this.f3249d.length() + 15;
        a[] aVarArr2 = this.i;
        if (aVarArr2.length > 1) {
            byte[] bArr3 = this.f3248c;
            int i = length2 + 1;
            bArr3[length2] = 41;
            d.p.d0.b(length - 3, bArr3, i);
            int i2 = i + 2;
            int i3 = 0;
            while (true) {
                a[] aVarArr3 = this.i;
                if (i3 >= aVarArr3.length) {
                    break;
                }
                int i4 = i2 + 1;
                this.f3248c[i2] = 59;
                byte[] a2 = aVarArr3[i3].a();
                System.arraycopy(a2, 0, this.f3248c, i4, a2.length);
                i2 = i4 + a2.length;
                i3++;
            }
            this.f3248c[i2] = 16;
        } else {
            this.f3248c[length2] = 59;
            byte[] a3 = aVarArr2[0].a();
            System.arraycopy(a3, 0, this.f3248c, length2 + 1, a3.length);
        }
        return this.f3248c;
    }

    public int v() {
        return this.f3251f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public c1(d.p.e eVar, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, boolean z) {
        super(d.p.k0.u);
        this.g = 0;
        this.f3250e = eVar;
        this.f3251f = i;
        this.g = z ? 0 : this.f3251f + 1;
        this.i = new a[2];
        this.i[0] = new a(i2, i3, i4, i5, i6);
        this.i[1] = new a(i2, i7, i8, i9, i10);
    }
}
