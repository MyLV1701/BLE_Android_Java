package d.p.q0;

import java.io.File;
import java.io.FileInputStream;

/* loaded from: classes.dex */
public class n implements r, d.g {
    private static d.q.c u = d.q.c.b(n.class);
    public static a v = new a(1);

    /* renamed from: a, reason: collision with root package name */
    private t f3010a;

    /* renamed from: b, reason: collision with root package name */
    private z f3011b;

    /* renamed from: c, reason: collision with root package name */
    private b0 f3012c;

    /* renamed from: d, reason: collision with root package name */
    private boolean f3013d;

    /* renamed from: e, reason: collision with root package name */
    private File f3014e;

    /* renamed from: f, reason: collision with root package name */
    private byte[] f3015f;
    private int g;
    private int h;
    private double i;
    private double j;
    private double k;
    private double l;
    private int m;
    private d0 n;
    private q o;
    private o p;
    private e0 q;
    private int r;
    private int s;
    private a t;

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: b, reason: collision with root package name */
        private static a[] f3016b = new a[0];

        /* renamed from: a, reason: collision with root package name */
        private int f3017a;

        a(int i) {
            this.f3017a = i;
            a[] aVarArr = f3016b;
            f3016b = new a[aVarArr.length + 1];
            System.arraycopy(aVarArr, 0, f3016b, 0, aVarArr.length);
            f3016b[aVarArr.length] = this;
        }

        int a() {
            return this.f3017a;
        }

        static a a(int i) {
            a aVar = n.v;
            int i2 = 0;
            while (true) {
                a[] aVarArr = f3016b;
                if (i2 >= aVarArr.length) {
                    return aVar;
                }
                if (aVarArr[i2].a() == i) {
                    return f3016b[i2];
                }
                i2++;
            }
        }
    }

    static {
        new a(2);
        new a(3);
    }

    private t m() {
        if (!this.f3013d) {
            n();
        }
        return this.f3010a;
    }

    private void n() {
        this.f3010a = this.p.a(this.s);
        d.q.a.a(this.f3010a != null);
        u[] i = this.f3010a.i();
        g0 g0Var = (g0) this.f3010a.i()[0];
        this.r = g0Var.i();
        this.g = this.f3012c.v();
        this.q = e0.a(g0Var.j());
        if (this.q == e0.g) {
            u.b("Unknown shape type");
        }
        c0 c0Var = (c0) this.f3010a.i()[1];
        if (c0Var.c(260) != null) {
            this.h = c0Var.c(260).f2954d;
        }
        if (c0Var.c(261) != null) {
            this.f3014e = new File(c0Var.c(261).f2955e);
        } else if (this.q == e0.f2971d) {
            u.b("no filename property for drawing");
            this.f3014e = new File(Integer.toString(this.h));
        }
        e eVar = null;
        for (int i2 = 0; i2 < i.length && eVar == null; i2++) {
            if (i[i2].h() == w.o) {
                eVar = (e) i[i2];
            }
        }
        if (eVar == null) {
            u.b("client anchor not found");
        } else {
            this.i = eVar.j();
            this.j = eVar.l();
            this.k = eVar.k() - this.i;
            this.l = eVar.m() - this.j;
            this.t = a.a(eVar.i());
        }
        if (this.h == 0) {
            u.b("linked drawings are not supported");
        }
        this.f3013d = true;
    }

    @Override // d.p.q0.r
    public final void a(int i, int i2, int i3) {
        this.g = i;
        this.h = i2;
        this.r = i3;
        if (this.n == d0.f2962a) {
            this.n = d0.f2964c;
        }
    }

    @Override // d.p.q0.r
    public t b() {
        if (!this.f3013d) {
            n();
        }
        if (this.n == d0.f2962a) {
            return m();
        }
        h0 h0Var = new h0();
        h0Var.a(new g0(this.q, this.r, 2560));
        c0 c0Var = new c0();
        c0Var.a(260, true, false, this.h);
        if (this.q == e0.f2971d) {
            File file = this.f3014e;
            String path = file != null ? file.getPath() : "";
            c0Var.a(261, true, true, path.length() * 2, path);
            c0Var.a(447, false, false, 65536);
            c0Var.a(959, false, false, 524288);
            h0Var.a(c0Var);
        }
        double d2 = this.i;
        double d3 = this.j;
        h0Var.a(new e(d2, d3, d2 + this.k, d3 + this.l, this.t.a()));
        h0Var.a(new f());
        return h0Var;
    }

    @Override // d.p.q0.r
    public void b(d.t.o.e0 e0Var) {
    }

    @Override // d.p.q0.r
    public boolean c() {
        return this.f3011b.v();
    }

    @Override // d.p.q0.r
    public boolean d() {
        return false;
    }

    @Override // d.p.q0.r
    public d0 e() {
        return this.n;
    }

    @Override // d.p.q0.r
    public String f() {
        File file = this.f3014e;
        if (file == null) {
            int i = this.h;
            return i != 0 ? Integer.toString(i) : "__new__image__";
        }
        return file.getPath();
    }

    public final int g() {
        if (!this.f3013d) {
            n();
        }
        return this.h;
    }

    public byte[] h() {
        d0 d0Var = this.n;
        if (d0Var != d0.f2962a && d0Var != d0.f2964c) {
            d.q.a.a(d0Var == d0.f2963b);
            File file = this.f3014e;
            if (file == null) {
                d.q.a.a(this.f3015f != null);
                return this.f3015f;
            }
            byte[] bArr = new byte[(int) file.length()];
            FileInputStream fileInputStream = new FileInputStream(this.f3014e);
            fileInputStream.read(bArr, 0, bArr.length);
            fileInputStream.close();
            return bArr;
        }
        return i();
    }

    public byte[] i() {
        d0 d0Var = this.n;
        d.q.a.a(d0Var == d0.f2962a || d0Var == d0.f2964c);
        if (!this.f3013d) {
            n();
        }
        return this.o.a(this.h);
    }

    public final int j() {
        if (!this.f3013d) {
            n();
        }
        return this.g;
    }

    public int k() {
        return this.m;
    }

    public int l() {
        if (!this.f3013d) {
            n();
        }
        return this.r;
    }

    @Override // d.p.q0.r
    public z a() {
        return this.f3011b;
    }

    @Override // d.p.q0.r
    public void a(q qVar) {
        this.o = qVar;
    }

    public void a(int i) {
        this.m = i;
    }

    @Override // d.p.q0.r
    public void a(d.t.o.e0 e0Var) {
        if (this.n == d0.f2962a) {
            e0Var.a(this.f3012c);
        } else {
            e0Var.a(new b0(this.g, b0.g));
        }
    }
}
