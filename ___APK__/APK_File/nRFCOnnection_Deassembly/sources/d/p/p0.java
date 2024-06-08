package d.p;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import no.nordicsemi.android.dfu.DfuBaseService;

/* loaded from: classes.dex */
public class p0 extends n0 implements d.r.d {
    private static d.q.c K = d.q.c.b(p0.class);
    public static final b L;
    protected static final c M;
    protected static final c N;
    private int A;
    private int B;
    private x C;
    private t D;
    private boolean E;
    private boolean F;
    private boolean G;
    private boolean H;
    private a0 I;
    private b J;

    /* renamed from: c, reason: collision with root package name */
    public int f2919c;

    /* renamed from: d, reason: collision with root package name */
    private int f2920d;

    /* renamed from: e, reason: collision with root package name */
    private c f2921e;

    /* renamed from: f, reason: collision with root package name */
    private NumberFormat f2922f;
    private byte g;
    private int h;
    private boolean i;
    private boolean j;
    private d.r.a k;
    private d.r.p l;
    private d.r.h m;
    private boolean n;
    private int o;
    private boolean p;
    private d.r.c q;
    private d.r.c r;
    private d.r.c s;
    private d.r.c t;
    private d.r.e u;
    private d.r.e v;
    private d.r.e w;
    private d.r.e x;
    private d.r.e y;
    private d.r.l z;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class b {
        private b() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class c {
        private c() {
        }
    }

    static {
        DateFormat[] dateFormatArr = {SimpleDateFormat.getDateInstance(3), SimpleDateFormat.getDateInstance(2), new SimpleDateFormat("d-MMM"), new SimpleDateFormat("MMM-yy"), new SimpleDateFormat("h:mm a"), new SimpleDateFormat("h:mm:ss a"), new SimpleDateFormat("H:mm"), new SimpleDateFormat("H:mm:ss"), new SimpleDateFormat("M/d/yy H:mm"), new SimpleDateFormat("mm:ss"), new SimpleDateFormat("H:mm:ss"), new SimpleDateFormat("mm:ss.S")};
        NumberFormat[] numberFormatArr = {new DecimalFormat("0"), new DecimalFormat("0.00"), new DecimalFormat("#,##0"), new DecimalFormat("#,##0.00"), new DecimalFormat("$#,##0;($#,##0)"), new DecimalFormat("$#,##0;($#,##0)"), new DecimalFormat("$#,##0.00;($#,##0.00)"), new DecimalFormat("$#,##0.00;($#,##0.00)"), new DecimalFormat("0%"), new DecimalFormat("0.00%"), new DecimalFormat("0.00E00"), new DecimalFormat("#,##0;(#,##0)"), new DecimalFormat("#,##0;(#,##0)"), new DecimalFormat("#,##0.00;(#,##0.00)"), new DecimalFormat("#,##0.00;(#,##0.00)"), new DecimalFormat("#,##0;(#,##0)"), new DecimalFormat("$#,##0;($#,##0)"), new DecimalFormat("#,##0.00;(#,##0.00)"), new DecimalFormat("$#,##0.00;($#,##0.00)"), new DecimalFormat("##0.0E0")};
        L = new b();
        new b();
        M = new c();
        N = new c();
    }

    public p0(x xVar, t tVar) {
        super(k0.y);
        this.E = false;
        this.i = true;
        this.j = false;
        this.k = d.r.a.f3154c;
        this.l = d.r.p.f3191c;
        this.m = d.r.h.f3173c;
        this.n = false;
        d.r.c cVar = d.r.c.f3163d;
        this.q = cVar;
        this.r = cVar;
        this.s = cVar;
        this.t = cVar;
        d.r.e eVar = d.r.e.l;
        this.u = eVar;
        this.v = eVar;
        this.w = eVar;
        this.x = eVar;
        this.z = d.r.l.f3181c;
        this.y = d.r.e.f3170f;
        this.o = 0;
        this.p = false;
        this.g = (byte) 124;
        this.f2920d = 0;
        this.f2921e = null;
        this.C = xVar;
        this.D = tVar;
        this.J = L;
        this.F = false;
        this.H = false;
        this.G = true;
        d.q.a.a(this.C != null);
        d.q.a.a(this.D != null);
    }

    private void E() {
        int i = this.f2919c;
        d[] dVarArr = d.f2852b;
        if (i < dVarArr.length && dVarArr[i] != null) {
            d dVar = dVarArr[i];
        } else {
            this.I.a(this.f2919c);
        }
        this.C = this.I.a().a(this.h);
        byte[] a2 = t().a();
        int a3 = d0.a(a2[4], a2[5]);
        this.f2920d = (65520 & a3) >> 4;
        this.f2921e = (a3 & 4) == 0 ? M : N;
        this.i = (a3 & 1) != 0;
        this.j = (a3 & 2) != 0;
        if (this.f2921e == M && (this.f2920d & 4095) == 4095) {
            this.f2920d = 0;
            K.b("Invalid parent format found - ignoring");
        }
        int a4 = d0.a(a2[6], a2[7]);
        if ((a4 & 8) != 0) {
            this.n = true;
        }
        this.k = d.r.a.a(a4 & 7);
        this.l = d.r.p.a((a4 >> 4) & 7);
        this.m = d.r.h.a((a4 >> 8) & 255);
        int a5 = d0.a(a2[8], a2[9]);
        this.o = a5 & 15;
        this.p = (a5 & 16) != 0;
        if (this.J == L) {
            this.g = a2[9];
        }
        int a6 = d0.a(a2[10], a2[11]);
        this.q = d.r.c.a(a6 & 7);
        this.r = d.r.c.a((a6 >> 4) & 7);
        this.s = d.r.c.a((a6 >> 8) & 7);
        this.t = d.r.c.a((a6 >> 12) & 7);
        int a7 = d0.a(a2[12], a2[13]);
        this.u = d.r.e.a(a7 & 127);
        this.v = d.r.e.a((a7 & 16256) >> 7);
        int a8 = d0.a(a2[14], a2[15]);
        this.w = d.r.e.a(a8 & 127);
        this.x = d.r.e.a((a8 & 16256) >> 7);
        if (this.J == L) {
            this.z = d.r.l.a((d0.a(a2[16], a2[17]) & 64512) >> 10);
            this.y = d.r.e.a(d0.a(a2[18], a2[19]) & 63);
            d.r.e eVar = this.y;
            if (eVar == d.r.e.f3167c || eVar == d.r.e.f3169e) {
                this.y = d.r.e.f3170f;
            }
        } else {
            this.z = d.r.l.f3181c;
            this.y = d.r.e.f3170f;
        }
        this.G = true;
    }

    public final int A() {
        return this.B;
    }

    public final boolean B() {
        if (!this.G) {
            E();
        }
        d.r.c cVar = this.q;
        d.r.c cVar2 = d.r.c.f3163d;
        return (cVar == cVar2 && this.r == cVar2 && this.s == cVar2 && this.t == cVar2) ? false : true;
    }

    public final boolean C() {
        return this.F;
    }

    public final void D() {
        if (this.E) {
            K.b("A default format has been initialized");
        }
        this.E = false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void a(boolean z) {
        this.i = z;
        this.g = (byte) (this.g | 128);
    }

    public d.r.c b(d.r.b bVar) {
        if (bVar != d.r.b.f3156a && bVar != d.r.b.f3157b) {
            if (!this.G) {
                E();
            }
            if (bVar == d.r.b.f3160e) {
                return this.q;
            }
            if (bVar == d.r.b.f3161f) {
                return this.r;
            }
            if (bVar == d.r.b.f3158c) {
                return this.s;
            }
            if (bVar == d.r.b.f3159d) {
                return this.t;
            }
            return d.r.c.f3163d;
        }
        return d.r.c.f3163d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void c(int i) {
        this.f2919c = i;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void d(int i) {
        this.A = i | this.A;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof p0)) {
            return false;
        }
        p0 p0Var = (p0) obj;
        if (!this.G) {
            E();
        }
        if (!p0Var.G) {
            p0Var.E();
        }
        if (this.f2921e == p0Var.f2921e && this.f2920d == p0Var.f2920d && this.i == p0Var.i && this.j == p0Var.j && this.g == p0Var.g && this.k == p0Var.k && this.l == p0Var.l && this.m == p0Var.m && this.n == p0Var.n && this.p == p0Var.p && this.o == p0Var.o && this.q == p0Var.q && this.r == p0Var.r && this.s == p0Var.s && this.t == p0Var.t && this.u == p0Var.u && this.v == p0Var.v && this.w == p0Var.w && this.x == p0Var.x && this.y == p0Var.y && this.z == p0Var.z) {
            if (this.E && p0Var.E) {
                if (this.h != p0Var.h || this.f2919c != p0Var.f2919c) {
                    return false;
                }
            } else if (!this.C.equals(p0Var.C) || !this.D.equals(p0Var.D)) {
            }
            return true;
        }
        return false;
    }

    public int hashCode() {
        if (!this.G) {
            E();
        }
        int i = ((((((629 + (this.j ? 1 : 0)) * 37) + (this.i ? 1 : 0)) * 37) + (this.n ? 1 : 0)) * 37) + (this.p ? 1 : 0);
        c cVar = this.f2921e;
        if (cVar == M) {
            i = (i * 37) + 1;
        } else if (cVar == N) {
            i = (i * 37) + 2;
        }
        return (37 * ((((((((((((((((((((((((((((((i * 37) + (this.k.a() + 1)) * 37) + (this.l.a() + 1)) * 37) + this.m.a()) ^ this.q.a().hashCode()) ^ this.r.a().hashCode()) ^ this.s.a().hashCode()) ^ this.t.a().hashCode()) * 37) + this.u.a()) * 37) + this.v.a()) * 37) + this.w.a()) * 37) + this.x.a()) * 37) + this.y.a()) * 37) + this.z.a() + 1) * 37) + this.g) * 37) + this.f2920d) * 37) + this.h) * 37) + this.f2919c)) + this.o;
    }

    public final boolean i() {
        return this.E;
    }

    @Override // d.r.d
    public d.r.f l() {
        if (!this.G) {
            E();
        }
        return this.C;
    }

    @Override // d.p.n0
    public byte[] u() {
        if (!this.G) {
            E();
        }
        byte[] bArr = new byte[20];
        d0.b(this.h, bArr, 0);
        d0.b(this.f2919c, bArr, 2);
        int i = y() ? 1 : 0;
        if (x()) {
            i |= 2;
        }
        if (this.f2921e == N) {
            i |= 4;
            this.f2920d = 65535;
        }
        d0.b((this.f2920d << 4) | i, bArr, 4);
        int a2 = this.k.a();
        if (this.n) {
            a2 |= 8;
        }
        d0.b(a2 | (this.l.a() << 4) | (this.m.a() << 8), bArr, 6);
        bArr[9] = 16;
        int b2 = (this.r.b() << 4) | this.q.b() | (this.s.b() << 8) | (this.t.b() << 12);
        d0.b(b2, bArr, 10);
        if (b2 != 0) {
            int a3 = (((byte) this.u.a()) & Byte.MAX_VALUE) | ((((byte) this.v.a()) & Byte.MAX_VALUE) << 7);
            int a4 = (((byte) this.w.a()) & Byte.MAX_VALUE) | ((((byte) this.x.a()) & Byte.MAX_VALUE) << 7);
            d0.b(a3, bArr, 12);
            d0.b(a4, bArr, 14);
        }
        d0.b(this.z.a() << 10, bArr, 16);
        d0.b(this.y.a() | DfuBaseService.ERROR_REMOTE_MASK, bArr, 18);
        this.A |= this.o & 15;
        if (this.p) {
            this.A = 16 | this.A;
        } else {
            this.A &= 239;
        }
        bArr[8] = (byte) this.A;
        if (this.J == L) {
            bArr[9] = this.g;
        }
        return bArr;
    }

    public int v() {
        return this.h;
    }

    public int w() {
        return this.f2919c;
    }

    protected final boolean x() {
        return this.j;
    }

    protected final boolean y() {
        return this.i;
    }

    public NumberFormat z() {
        return this.f2922f;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(d.r.b bVar, d.r.c cVar, d.r.e eVar) {
        d.q.a.a(!this.E);
        if (eVar == d.r.e.f3168d || eVar == d.r.e.f3167c) {
            eVar = d.r.e.g;
        }
        if (bVar == d.r.b.f3160e) {
            this.q = cVar;
            this.u = eVar;
        } else if (bVar == d.r.b.f3161f) {
            this.r = cVar;
            this.v = eVar;
        } else if (bVar == d.r.b.f3158c) {
            this.s = cVar;
            this.w = eVar;
        } else if (bVar == d.r.b.f3159d) {
            this.t = cVar;
            this.x = eVar;
        }
        this.g = (byte) (this.g | 32);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(int i) {
        this.h = i;
    }

    public d.r.e a(d.r.b bVar) {
        if (bVar != d.r.b.f3156a && bVar != d.r.b.f3157b) {
            if (!this.G) {
                E();
            }
            if (bVar == d.r.b.f3160e) {
                return this.u;
            }
            if (bVar == d.r.b.f3161f) {
                return this.v;
            }
            if (bVar == d.r.b.f3158c) {
                return this.w;
            }
            if (bVar == d.r.b.f3159d) {
                return this.x;
            }
            return d.r.e.f3168d;
        }
        return d.r.e.g;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public p0(p0 p0Var) {
        super(k0.y);
        this.E = false;
        this.i = p0Var.i;
        this.j = p0Var.j;
        this.k = p0Var.k;
        this.l = p0Var.l;
        this.m = p0Var.m;
        this.n = p0Var.n;
        this.q = p0Var.q;
        this.r = p0Var.r;
        this.s = p0Var.s;
        this.t = p0Var.t;
        this.u = p0Var.u;
        this.v = p0Var.v;
        this.w = p0Var.w;
        this.x = p0Var.x;
        this.z = p0Var.z;
        this.f2921e = p0Var.f2921e;
        this.o = p0Var.o;
        this.p = p0Var.p;
        this.f2920d = p0Var.f2920d;
        this.y = p0Var.y;
        this.C = p0Var.C;
        this.D = p0Var.D;
        this.h = p0Var.h;
        this.f2919c = p0Var.f2919c;
        this.G = p0Var.G;
        this.J = L;
        this.F = false;
        this.H = true;
    }

    public final void a(int i, a0 a0Var, y yVar) {
        this.B = i;
        this.I = a0Var;
        if (!this.F && !this.H) {
            if (!this.C.i()) {
                yVar.a(this.C);
            }
            if (!this.D.i()) {
                a0Var.a(this.D);
            }
            this.h = this.C.v();
            this.f2919c = this.D.h();
            this.E = true;
            return;
        }
        this.E = true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(c cVar, int i) {
        this.f2921e = cVar;
        this.f2920d = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(c0 c0Var) {
        this.B = c0Var.a(this.B);
        if (this.f2921e == M) {
            this.f2920d = c0Var.a(this.f2920d);
        }
    }

    public void a(x xVar) {
        this.C = xVar;
    }
}
