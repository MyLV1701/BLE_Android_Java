package d.p;

import d.p.r0.r0;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import no.nordicsemi.android.dfu.DfuBaseService;
import no.nordicsemi.android.log.LogContract;

/* loaded from: classes.dex */
public class o {
    public static final a A;
    public static final a B;
    public static final a C;
    public static final a D;
    public static final a E;
    private static d.q.c w = d.q.c.b(o.class);
    public static final a x;
    public static final a y;
    public static final a z;

    /* renamed from: a, reason: collision with root package name */
    private b f2901a;

    /* renamed from: b, reason: collision with root package name */
    private c f2902b;

    /* renamed from: c, reason: collision with root package name */
    private a f2903c;

    /* renamed from: d, reason: collision with root package name */
    private boolean f2904d;

    /* renamed from: e, reason: collision with root package name */
    private boolean f2905e;

    /* renamed from: f, reason: collision with root package name */
    private boolean f2906f;
    private boolean g;
    private boolean h;
    private String i;
    private String j;
    private String k;
    private String l;
    private d.p.r0.w m;
    private String n;
    private d.p.r0.w o;
    private String p;
    private int q;
    private int r;
    private int s;
    private int t;
    private boolean u;
    private boolean v;

    static {
        new b(0, "any");
        new b(1, "int");
        new b(2, "dec");
        new b(3, "list");
        new b(4, "date");
        new b(5, LogContract.LogColumns.TIME);
        new b(6, "strlen");
        new b(7, "form");
        new c(0);
        new c(1);
        new c(2);
        x = new a(0, "{0} <= x <= {1}");
        y = new a(1, "!({0} <= x <= {1}");
        z = new a(2, "x == {0}");
        A = new a(3, "x != {0}");
        B = new a(4, "x > {0}");
        C = new a(5, "x < {0}");
        D = new a(6, "x >= {0}");
        E = new a(7, "x <= {0}");
        new DecimalFormat("#.#");
    }

    /* JADX WARN: Removed duplicated region for block: B:51:0x017f A[Catch: v -> 0x01b9, TryCatch #0 {v -> 0x01b9, blocks: (B:49:0x0174, B:51:0x017f, B:53:0x019d), top: B:48:0x0174 }] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x019d A[Catch: v -> 0x01b9, TRY_LEAVE, TryCatch #0 {v -> 0x01b9, blocks: (B:49:0x0174, B:51:0x017f, B:53:0x019d), top: B:48:0x0174 }] */
    /* JADX WARN: Removed duplicated region for block: B:57:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0109  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0116  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00eb  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public o(byte[] r16, d.p.r0.t r17, d.p.l0 r18, d.o r19) {
        /*
            Method dump skipped, instructions count: 496
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: d.p.o.<init>(byte[], d.p.r0.t, d.p.l0, d.o):void");
    }

    public void a(int i, int i2, d.p.r0.t tVar, l0 l0Var, d.o oVar) {
        if (this.u) {
            return;
        }
        this.r = i2;
        this.t = i2;
        this.q = i;
        this.s = i;
        this.m = new d.p.r0.w(this.n, tVar, l0Var, oVar, r0.f3102b);
        this.m.c();
        String str = this.p;
        if (str != null) {
            this.o = new d.p.r0.w(str, tVar, l0Var, oVar, r0.f3102b);
            this.o.c();
        }
    }

    public boolean b() {
        return this.u;
    }

    public byte[] c() {
        d.p.r0.w wVar = this.m;
        byte[] a2 = wVar != null ? wVar.a() : new byte[0];
        d.p.r0.w wVar2 = this.o;
        byte[] a3 = wVar2 != null ? wVar2.a() : new byte[0];
        byte[] bArr = new byte[(this.i.length() * 2) + 4 + 3 + (this.j.length() * 2) + 3 + (this.k.length() * 2) + 3 + (this.l.length() * 2) + 3 + a2.length + 2 + a3.length + 2 + 4 + 10];
        int a4 = this.f2901a.a() | 0 | (this.f2902b.a() << 4) | (this.f2903c.a() << 20);
        if (this.f2904d) {
            a4 |= 128;
        }
        if (this.f2905e) {
            a4 |= 256;
        }
        if (this.f2906f) {
            a4 |= DfuBaseService.ERROR_REMOTE_TYPE_SECURE;
        }
        if (this.g) {
            a4 |= 262144;
        }
        if (this.h) {
            a4 |= 524288;
        }
        d0.a(a4, bArr, 0);
        d0.b(this.i.length(), bArr, 4);
        bArr[6] = 1;
        j0.b(this.i, bArr, 7);
        int length = 7 + (this.i.length() * 2);
        d0.b(this.j.length(), bArr, length);
        int i = length + 2;
        bArr[i] = 1;
        int i2 = i + 1;
        j0.b(this.j, bArr, i2);
        int length2 = i2 + (this.j.length() * 2);
        d0.b(this.k.length(), bArr, length2);
        int i3 = length2 + 2;
        bArr[i3] = 1;
        int i4 = i3 + 1;
        j0.b(this.k, bArr, i4);
        int length3 = i4 + (this.k.length() * 2);
        d0.b(this.l.length(), bArr, length3);
        int i5 = length3 + 2;
        bArr[i5] = 1;
        int i6 = i5 + 1;
        j0.b(this.l, bArr, i6);
        int length4 = i6 + (this.l.length() * 2);
        d0.b(a2.length, bArr, length4);
        int i7 = length4 + 4;
        System.arraycopy(a2, 0, bArr, i7, a2.length);
        int length5 = i7 + a2.length;
        d0.b(a3.length, bArr, length5);
        int i8 = length5 + 4;
        System.arraycopy(a3, 0, bArr, i8, a3.length);
        int length6 = i8 + a3.length;
        d0.b(1, bArr, length6);
        int i9 = length6 + 2;
        d0.b(this.r, bArr, i9);
        int i10 = i9 + 2;
        d0.b(this.t, bArr, i10);
        int i11 = i10 + 2;
        d0.b(this.q, bArr, i11);
        d0.b(this.s, bArr, i11 + 2);
        return bArr;
    }

    public int d() {
        return this.q;
    }

    public int e() {
        return this.r;
    }

    public int f() {
        return this.s;
    }

    public int g() {
        return this.t;
    }

    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: b, reason: collision with root package name */
        private static a[] f2907b = new a[0];

        /* renamed from: a, reason: collision with root package name */
        private int f2908a;

        a(int i, String str) {
            this.f2908a = i;
            new MessageFormat(str);
            a[] aVarArr = f2907b;
            f2907b = new a[aVarArr.length + 1];
            System.arraycopy(aVarArr, 0, f2907b, 0, aVarArr.length);
            f2907b[aVarArr.length] = this;
        }

        static a a(int i) {
            a aVar = null;
            int i2 = 0;
            while (true) {
                a[] aVarArr = f2907b;
                if (i2 >= aVarArr.length || aVar != null) {
                    break;
                }
                if (aVarArr[i2].f2908a == i) {
                    aVar = aVarArr[i2];
                }
                i2++;
            }
            return aVar;
        }

        public int a() {
            return this.f2908a;
        }
    }

    /* loaded from: classes.dex */
    public static class b {

        /* renamed from: b, reason: collision with root package name */
        private static b[] f2909b = new b[0];

        /* renamed from: a, reason: collision with root package name */
        private int f2910a;

        b(int i, String str) {
            this.f2910a = i;
            b[] bVarArr = f2909b;
            f2909b = new b[bVarArr.length + 1];
            System.arraycopy(bVarArr, 0, f2909b, 0, bVarArr.length);
            f2909b[bVarArr.length] = this;
        }

        static b a(int i) {
            b bVar = null;
            int i2 = 0;
            while (true) {
                b[] bVarArr = f2909b;
                if (i2 >= bVarArr.length || bVar != null) {
                    break;
                }
                if (bVarArr[i2].f2910a == i) {
                    bVar = bVarArr[i2];
                }
                i2++;
            }
            return bVar;
        }

        public int a() {
            return this.f2910a;
        }
    }

    /* loaded from: classes.dex */
    public static class c {

        /* renamed from: b, reason: collision with root package name */
        private static c[] f2911b = new c[0];

        /* renamed from: a, reason: collision with root package name */
        private int f2912a;

        c(int i) {
            this.f2912a = i;
            c[] cVarArr = f2911b;
            f2911b = new c[cVarArr.length + 1];
            System.arraycopy(cVarArr, 0, f2911b, 0, cVarArr.length);
            f2911b[cVarArr.length] = this;
        }

        static c a(int i) {
            c cVar = null;
            int i2 = 0;
            while (true) {
                c[] cVarArr = f2911b;
                if (i2 >= cVarArr.length || cVar != null) {
                    break;
                }
                if (cVarArr[i2].f2912a == i) {
                    cVar = cVarArr[i2];
                }
                i2++;
            }
            return cVar;
        }

        public int a() {
            return this.f2912a;
        }
    }

    public boolean a() {
        return this.v;
    }

    public o(o oVar) {
        this.v = true;
        this.f2901a = oVar.f2901a;
        this.f2902b = oVar.f2902b;
        this.f2903c = oVar.f2903c;
        this.f2904d = oVar.f2904d;
        this.f2905e = oVar.f2905e;
        this.f2906f = oVar.f2906f;
        this.g = oVar.g;
        this.h = oVar.h;
        this.i = oVar.i;
        this.k = oVar.k;
        this.j = oVar.j;
        this.l = oVar.l;
        this.u = oVar.u;
        this.r = oVar.r;
        this.t = oVar.t;
        this.q = oVar.q;
        this.s = oVar.s;
        String str = oVar.n;
        if (str != null) {
            this.n = str;
            this.p = oVar.p;
            return;
        }
        try {
            this.n = oVar.m.b();
            this.p = oVar.o != null ? oVar.o.b() : null;
        } catch (d.p.r0.v e2) {
            w.b("Cannot parse validation formula:  " + e2.getMessage());
        }
    }
}
