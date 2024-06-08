package d.t.o;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* loaded from: classes.dex */
public class w2 extends d.t.m implements d.p.r0.t, d.p.l0 {
    private static d.q.c y = d.q.c.b(w2.class);
    private static Object z = new Object();

    /* renamed from: f, reason: collision with root package name */
    private d.p.a0 f3412f;
    private e0 g;
    private d.p.y i;
    private d0 j;
    private ArrayList k;
    private ArrayList l;
    private boolean o;
    private d.o q;
    private d.p.q0.q r;
    private g2 s;
    private g u;
    private p v;
    private String[] w;
    private d.p.o0[] x;
    private ArrayList h = new ArrayList();
    private c2 n = new c2();
    private HashMap m = new HashMap();
    private boolean p = false;
    private boolean t = false;

    public w2(OutputStream outputStream, boolean z2, d.o oVar) {
        this.g = new e0(outputStream, oVar, null);
        this.o = z2;
        this.q = oVar;
        new ArrayList();
        this.s = new g2();
        synchronized (z) {
            d.t.m.f3219a.w();
            d.t.m.f3220b.w();
            d.t.m.f3221c.D();
            d.t.m.f3222d.D();
            d.t.m.f3223e.D();
            t.n.D();
        }
        this.i = new t2(this);
        this.f3412f = new u2(this.i, this.s);
    }

    private d.t.l a(String str, int i, boolean z2) {
        d0 d0Var;
        v2 v2Var = new v2(str, this.g, this.f3412f, this.n, this.q, this);
        if (i <= 0) {
            this.h.add(0, v2Var);
            i = 0;
        } else if (i > this.h.size()) {
            i = this.h.size();
            this.h.add(v2Var);
        } else {
            this.h.add(i, v2Var);
        }
        if (z2 && (d0Var = this.j) != null) {
            d0Var.d(i);
        }
        ArrayList arrayList = this.k;
        if (arrayList != null && arrayList.size() > 0) {
            h2 h2Var = (h2) this.k.get(0);
            if (h2Var.getType() == h2.j) {
                h2Var.b(this.h.size());
            }
        }
        return v2Var;
    }

    private void h() {
        d.p.c0 d2 = this.f3412f.d();
        d.p.c0 c2 = this.f3412f.c();
        d.p.c0 a2 = this.f3412f.a(d2, c2);
        for (int i = 0; i < this.h.size(); i++) {
            ((v2) this.h.get(i)).a(a2, d2, c2);
        }
    }

    @Override // d.p.r0.t
    public d.s.a.a a() {
        return null;
    }

    @Override // d.t.m
    public void b() {
        this.g.a(this.o);
    }

    public d.t.l c(int i) {
        return (d.t.l) this.h.get(i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public d.p.q0.q d() {
        return this.r;
    }

    public int e() {
        return this.h.size();
    }

    public String[] f() {
        String[] strArr = new String[e()];
        for (int i = 0; i < strArr.length; i++) {
            strArr[i] = c(i).getName();
        }
        return strArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public g2 g() {
        return this.s;
    }

    @Override // d.p.r0.t
    public String b(int i) {
        h2 h2Var = (h2) this.k.get(this.j.c(i));
        int b2 = this.j.b(i);
        if (h2Var.getType() == h2.j) {
            return c(b2).getName();
        }
        if (h2Var.getType() == h2.k) {
            return h2Var.v() + h2Var.c(b2);
        }
        y.b("Unknown Supbook 1");
        return "[UNKNOWN]";
    }

    @Override // d.t.m
    public void c() {
        for (int i = 0; i < e(); i++) {
            v2 v2Var = (v2) c(i);
            v2Var.d();
            d.k B = v2Var.a().B();
            if (B != null) {
                a(d.p.e.f2855c, v2Var, B.b().f(), B.b().e(), B.a().f(), B.a().e(), false);
            }
            d.k F = v2Var.a().F();
            d.k E = v2Var.a().E();
            if (F != null && E != null) {
                a(d.p.e.f2856d, v2Var, F.b().f(), F.b().e(), F.a().f(), F.a().e(), E.b().f(), E.b().e(), E.a().f(), E.a().e(), false);
            } else if (F != null) {
                a(d.p.e.f2856d, v2Var, F.b().f(), F.b().e(), F.a().f(), F.a().e(), false);
            } else if (E != null) {
                a(d.p.e.f2856d, v2Var, E.b().f(), E.b().e(), E.a().f(), E.a().e(), false);
            }
        }
        if (!this.q.l()) {
            h();
        }
        this.g.a(new a(a.f3224d));
        if (this.q.n()) {
            this.g.a(new j2());
        }
        this.g.a(new r0());
        this.g.a(new w0(0, 0));
        this.g.a(new q0());
        this.g.a(new x2(this.q.r()));
        this.g.a(new l());
        this.g.a(new r());
        if (this.q.d()) {
            this.g.a(new z());
        }
        this.g.a(new i2(e()));
        if (this.t) {
            this.g.a(new f1());
        }
        g gVar = this.u;
        if (gVar != null) {
            this.g.a(gVar);
        }
        this.g.a(new h0());
        this.g.a(new r2(this.q.q()));
        this.g.a(new p1(this.p));
        this.g.a(new j1((String) null));
        this.g.a(new o1(false));
        this.g.a(new n1());
        boolean z2 = false;
        int i2 = 0;
        for (int i3 = 0; i3 < e() && !z2; i3++) {
            if (((v2) c(i3)).a().R()) {
                i2 = i3;
                z2 = true;
            }
        }
        if (!z2) {
            ((v2) c(0)).a().a(true);
            i2 = 0;
        }
        this.g.a(new p2(i2));
        this.g.a(new b(false));
        this.g.a(new l0(this.q.i()));
        this.g.a(new d1(false));
        this.g.a(new k1(false));
        this.g.a(new r1(this.q.m()));
        this.g.a(new d(true));
        this.i.a(this.g);
        this.f3412f.a(this.g);
        if (this.f3412f.b() != null) {
            this.g.a(this.f3412f.b());
        }
        this.g.a(new l2());
        int[] iArr = new int[e()];
        for (int i4 = 0; i4 < e(); i4++) {
            iArr[i4] = this.g.a();
            d.t.l c2 = c(i4);
            f fVar = new f(c2.getName());
            if (c2.a().O()) {
                fVar.w();
            }
            if (((v2) this.h.get(i4)).i()) {
                fVar.v();
            }
            this.g.a(fVar);
        }
        if (this.v == null) {
            d.p.n a2 = d.p.n.a(this.q.e());
            if (a2 == d.p.n.g) {
                y.b("Unknown country code " + this.q.e() + " using " + d.p.n.f2897e.a());
                a2 = d.p.n.f2897e;
            }
            d.p.n a3 = d.p.n.a(this.q.f());
            this.v = new p(a2, a3);
            if (a3 == d.p.n.g) {
                y.b("Unknown country code " + this.q.e() + " using " + d.p.n.f2898f.a());
                d.p.n nVar = d.p.n.f2898f;
            }
        }
        this.g.a(this.v);
        String[] strArr = this.w;
        if (strArr != null && strArr.length > 0) {
            int i5 = 0;
            while (true) {
                String[] strArr2 = this.w;
                if (i5 >= strArr2.length) {
                    break;
                }
                this.g.a(new c0(strArr2[i5]));
                i5++;
            }
        }
        if (this.x != null) {
            int i6 = 0;
            while (true) {
                d.p.o0[] o0VarArr = this.x;
                if (i6 >= o0VarArr.length) {
                    break;
                }
                this.g.a(o0VarArr[i6]);
                i6++;
            }
        }
        if (this.j != null) {
            for (int i7 = 0; i7 < this.k.size(); i7++) {
                this.g.a((h2) this.k.get(i7));
            }
            this.g.a(this.j);
        }
        if (this.l != null) {
            for (int i8 = 0; i8 < this.l.size(); i8++) {
                this.g.a((c1) this.l.get(i8));
            }
        }
        d.p.q0.q qVar = this.r;
        if (qVar != null) {
            qVar.a(this.g);
        }
        this.n.a(this.g);
        this.g.a(new y());
        for (int i9 = 0; i9 < e(); i9++) {
            e0 e0Var = this.g;
            e0Var.a(d.p.d0.a(e0Var.a()), iArr[i9] + 4);
            ((v2) c(i9)).j();
        }
    }

    @Override // d.p.r0.t
    public int b(String str) {
        if (this.j == null) {
            this.j = new d0();
            this.k = new ArrayList();
            this.k.add(new h2(e(), this.q));
        }
        Iterator it = this.h.iterator();
        boolean z2 = false;
        int i = 0;
        while (it.hasNext() && !z2) {
            if (((v2) it.next()).getName().equals(str)) {
                z2 = true;
            } else {
                i++;
            }
        }
        if (z2) {
            h2 h2Var = (h2) this.k.get(0);
            if (h2Var.getType() != h2.j || h2Var.w() != e()) {
                y.b("Cannot find sheet " + str + " in supbook record");
            }
            return this.j.a(0, i);
        }
        int lastIndexOf = str.lastIndexOf(93);
        int lastIndexOf2 = str.lastIndexOf(91);
        int i2 = -1;
        if (lastIndexOf != -1 && lastIndexOf2 != -1) {
            String substring = str.substring(lastIndexOf + 1);
            String substring2 = str.substring(lastIndexOf2 + 1, lastIndexOf);
            String str2 = str.substring(0, lastIndexOf2) + substring2;
            h2 h2Var2 = null;
            boolean z3 = false;
            for (int i3 = 0; i3 < this.k.size() && !z3; i3++) {
                h2Var2 = (h2) this.k.get(i3);
                if (h2Var2.getType() == h2.k && h2Var2.v().equals(str2)) {
                    i2 = i3;
                    z3 = true;
                }
            }
            if (!z3) {
                h2Var2 = new h2(str2, this.q);
                i2 = this.k.size();
                this.k.add(h2Var2);
            }
            return this.j.a(i2, h2Var2.a(substring));
        }
        y.b("Square brackets");
        return -1;
    }

    @Override // d.t.m
    public d.t.l a(String str, int i) {
        return a(str, i, true);
    }

    @Override // d.p.l0
    public String a(int i) {
        d.q.a.a(i >= 0 && i < this.l.size());
        return ((c1) this.l.get(i)).getName();
    }

    @Override // d.p.l0
    public int a(String str) {
        c1 c1Var = (c1) this.m.get(str);
        if (c1Var != null) {
            return c1Var.v();
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(d.p.q0.r rVar) {
        if (this.r == null) {
            this.r = new d.p.q0.q(d.p.q0.d0.f2963b);
        }
        this.r.a(rVar);
    }

    void a(d.p.e eVar, d.t.l lVar, int i, int i2, int i3, int i4, boolean z2) {
        if (this.l == null) {
            this.l = new ArrayList();
        }
        c1 c1Var = new c1(eVar, c(lVar.getName()), b(lVar.getName()), i2, i4, i, i3, z2);
        this.l.add(c1Var);
        this.m.put(eVar, c1Var);
    }

    void a(d.p.e eVar, d.t.l lVar, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, boolean z2) {
        if (this.l == null) {
            this.l = new ArrayList();
        }
        c1 c1Var = new c1(eVar, c(lVar.getName()), b(lVar.getName()), i6, i8, i5, i7, i2, i4, i, i3, z2);
        this.l.add(c1Var);
        this.m.put(eVar, c1Var);
    }

    private int c(String str) {
        String[] f2 = f();
        for (int i = 0; i < f2.length; i++) {
            if (str.equals(f2[i])) {
                return i;
            }
        }
        return -1;
    }
}
