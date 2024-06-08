package d.p.q0;

import d.p.n0;

/* loaded from: classes.dex */
public class b0 extends n0 {

    /* renamed from: f, reason: collision with root package name */
    public static final a f2937f;
    public static final a g;
    public static final a h;
    public static final a i;

    /* renamed from: c, reason: collision with root package name */
    private a f2938c;

    /* renamed from: d, reason: collision with root package name */
    private boolean f2939d;

    /* renamed from: e, reason: collision with root package name */
    private int f2940e;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class a {

        /* renamed from: c, reason: collision with root package name */
        private static a[] f2941c = new a[0];

        /* renamed from: a, reason: collision with root package name */
        public int f2942a;

        /* renamed from: b, reason: collision with root package name */
        public String f2943b;

        a(int i, String str) {
            this.f2942a = i;
            this.f2943b = str;
            a[] aVarArr = f2941c;
            f2941c = new a[aVarArr.length + 1];
            System.arraycopy(aVarArr, 0, f2941c, 0, aVarArr.length);
            f2941c[aVarArr.length] = this;
        }

        public String toString() {
            return this.f2943b;
        }
    }

    static {
        d.q.c.b(b0.class);
        new a(0, "Group");
        new a(1, "Line");
        new a(2, "Rectangle");
        new a(3, "Oval");
        new a(4, "Arc");
        f2937f = new a(5, "Chart");
        new a(6, "Text");
        new a(7, "Button");
        g = new a(8, "Picture");
        new a(9, "Polygon");
        new a(11, "Checkbox");
        new a(12, "Option");
        new a(13, "Edit Box");
        new a(14, "Label");
        new a(15, "Dialogue Box");
        new a(16, "Spin Box");
        new a(17, "Scrollbar");
        new a(18, "List Box");
        new a(19, "Group Box");
        h = new a(20, "Combo Box");
        new a(30, "MS Office Drawing");
        new a(20, "Form Combo Box");
        i = new a(25, "Excel Note");
        new a(255, "Unknown");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public b0(int i2, a aVar) {
        super(d.p.k0.x0);
        this.f2940e = i2;
        this.f2938c = aVar;
    }

    private byte[] w() {
        byte[] bArr = new byte[70];
        d.p.d0.b(21, bArr, 0);
        d.p.d0.b(18, bArr, 2);
        d.p.d0.b(this.f2938c.f2942a, bArr, 4);
        d.p.d0.b(this.f2940e, bArr, 6);
        d.p.d0.b(0, bArr, 8);
        d.p.d0.b(12, bArr, 22);
        d.p.d0.b(20, bArr, 24);
        bArr[36] = 1;
        bArr[38] = 4;
        bArr[42] = 16;
        bArr[46] = 19;
        bArr[48] = -18;
        bArr[49] = 31;
        bArr[52] = 4;
        bArr[56] = 1;
        bArr[57] = 6;
        bArr[60] = 2;
        bArr[62] = 8;
        bArr[64] = 64;
        d.p.d0.b(0, bArr, 66);
        d.p.d0.b(0, bArr, 68);
        return bArr;
    }

    private byte[] x() {
        byte[] bArr = new byte[52];
        d.p.d0.b(21, bArr, 0);
        d.p.d0.b(18, bArr, 2);
        d.p.d0.b(this.f2938c.f2942a, bArr, 4);
        d.p.d0.b(this.f2940e, bArr, 6);
        d.p.d0.b(16401, bArr, 8);
        d.p.d0.b(13, bArr, 22);
        d.p.d0.b(22, bArr, 24);
        d.p.d0.b(0, bArr, 48);
        d.p.d0.b(0, bArr, 50);
        return bArr;
    }

    private byte[] y() {
        byte[] bArr = new byte[38];
        d.p.d0.b(21, bArr, 0);
        d.p.d0.b(18, bArr, 2);
        d.p.d0.b(this.f2938c.f2942a, bArr, 4);
        d.p.d0.b(this.f2940e, bArr, 6);
        d.p.d0.b(24593, bArr, 8);
        d.p.d0.b(7, bArr, 22);
        d.p.d0.b(2, bArr, 24);
        d.p.d0.b(65535, bArr, 26);
        d.p.d0.b(8, bArr, 28);
        d.p.d0.b(2, bArr, 30);
        d.p.d0.b(1, bArr, 32);
        d.p.d0.b(0, bArr, 34);
        d.p.d0.b(0, bArr, 36);
        return bArr;
    }

    @Override // d.p.h0
    public d.s.a.e t() {
        return super.t();
    }

    @Override // d.p.n0
    public byte[] u() {
        if (this.f2939d) {
            return t().a();
        }
        a aVar = this.f2938c;
        if (aVar != g && aVar != f2937f) {
            if (aVar == i) {
                return x();
            }
            if (aVar == h) {
                return w();
            }
            d.q.a.a(false);
            return null;
        }
        return y();
    }

    public int v() {
        return this.f2940e;
    }
}
