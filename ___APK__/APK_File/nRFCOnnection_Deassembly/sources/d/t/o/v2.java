package d.t.o;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class v2 implements d.t.l {
    private static d.q.c D = d.q.c.b(v2.class);
    private static final char[] E = {'*', ':', '?', '\\'};
    private d2 A;
    private d.o B;
    private w2 C;

    /* renamed from: a, reason: collision with root package name */
    private String f3397a;

    /* renamed from: b, reason: collision with root package name */
    private e0 f3398b;

    /* renamed from: d, reason: collision with root package name */
    private d.p.a0 f3400d;

    /* renamed from: e, reason: collision with root package name */
    private c2 f3401e;
    private h1 l;
    private g m;
    private d.p.q o;
    private ArrayList s;
    private d.p.a t;
    private ArrayList u;
    private d.p.q0.h v;
    private int x;
    private int y;
    private d.m z;

    /* renamed from: c, reason: collision with root package name */
    private t1[] f3399c = new t1[0];
    private int j = 0;
    private int k = 0;
    private boolean n = false;
    private boolean w = false;

    /* renamed from: f, reason: collision with root package name */
    private TreeSet f3402f = new TreeSet(new b());
    private TreeSet g = new TreeSet();
    private ArrayList h = new ArrayList();
    private z0 i = new z0(this);
    private ArrayList p = new ArrayList();
    private ArrayList q = new ArrayList();
    private ArrayList r = new ArrayList();

    /* loaded from: classes.dex */
    private static class b implements Comparator {
        private b() {
        }

        @Override // java.util.Comparator
        public int compare(Object obj, Object obj2) {
            if (obj == obj2) {
                return 0;
            }
            d.q.a.a(obj instanceof m);
            d.q.a.a(obj2 instanceof m);
            return ((m) obj).f() - ((m) obj2).f();
        }

        @Override // java.util.Comparator
        public boolean equals(Object obj) {
            return obj == this;
        }
    }

    static {
        new String[1][0] = "png";
    }

    public v2(String str, e0 e0Var, d.p.a0 a0Var, c2 c2Var, d.o oVar, w2 w2Var) {
        this.f3397a = a(str);
        this.f3398b = e0Var;
        this.C = w2Var;
        this.f3400d = a0Var;
        this.f3401e = c2Var;
        this.B = oVar;
        new ArrayList();
        this.s = new ArrayList();
        this.u = new ArrayList();
        this.z = new d.m(this);
        this.A = new d2(this.f3398b, this, this.B);
    }

    private void k() {
        Iterator it = this.g.iterator();
        while (it.hasNext()) {
            c(((Integer) it.next()).intValue());
        }
    }

    @Override // d.l
    public d.a a(int i, int i2) {
        return b(i, i2);
    }

    public d.t.g b(int i, int i2) {
        t1[] t1VarArr = this.f3399c;
        j b2 = (i2 >= t1VarArr.length || t1VarArr[i2] == null) ? null : t1VarArr[i2].b(i);
        return b2 == null ? new d.p.v(i, i2) : b2;
    }

    @Override // d.l
    public int c() {
        return this.j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void d() {
        this.A.a(this.f3399c, this.p, this.q, this.h, this.i, this.f3402f, this.x, this.y);
        this.A.a(c(), b());
        this.A.a();
    }

    d.p.q0.d[] e() {
        return this.A.b();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public d.p.q0.h f() {
        return this.v;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public w2 g() {
        return this.C;
    }

    @Override // d.l
    public String getName() {
        return this.f3397a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public d.o h() {
        return this.B;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean i() {
        return this.n;
    }

    public void j() {
        boolean z = this.w;
        if (this.C.d() != null) {
            z |= this.C.d().a();
        }
        if (this.g.size() > 0) {
            k();
        }
        this.A.a(this.f3399c, this.p, this.q, this.h, this.i, this.f3402f, this.x, this.y);
        this.A.a(c(), b());
        this.A.a(this.z);
        this.A.a(this.l);
        this.A.a(this.r, z);
        this.A.a(this.m);
        this.A.a(this.o, this.u);
        this.A.a(this.s);
        this.A.a(this.t);
        this.A.c();
    }

    private void c(int i) {
        m a2 = a(i);
        d.r.f l = a2.d().l();
        d.r.f l2 = d.t.m.f3221c.l();
        int i2 = 0;
        for (int i3 = 0; i3 < this.j; i3++) {
            t1[] t1VarArr = this.f3399c;
            j b2 = t1VarArr[i3] != null ? t1VarArr[i3].b(i) : null;
            if (b2 != null) {
                String c2 = b2.c();
                d.r.f l3 = b2.d().l();
                if (l3.equals(l2)) {
                    l3 = l;
                }
                int m = l3.m();
                int length = c2.length();
                if (l3.r() || l3.q() > 400) {
                    length += 2;
                }
                i2 = Math.max(i2, length * m * 256);
            }
        }
        a2.b(i2 / l2.m());
    }

    @Override // d.t.l
    public void a(d.t.g gVar) {
        if (gVar.getType() == d.d.f2808b && gVar != null && gVar.d() == null) {
            return;
        }
        j jVar = (j) gVar;
        if (!jVar.x()) {
            int e2 = gVar.e();
            t1 b2 = b(e2);
            j b3 = b2.b(jVar.f());
            boolean z = (b3 == null || b3.b() == null || b3.b().d() == null || !b3.b().d().b()) ? false : true;
            if (gVar.b() != null && gVar.b().e() && z) {
                d.p.o d2 = b3.b().d();
                D.b("Cannot add cell at " + d.c.a(jVar) + " because it is part of the shared cell validation group " + d.c.a(d2.d(), d2.e()) + "-" + d.c.a(d2.f(), d2.g()));
                return;
            }
            if (z) {
                d.t.h j = gVar.j();
                if (j == null) {
                    j = new d.t.h();
                    gVar.a(j);
                }
                j.a(b3.b());
            }
            b2.a(jVar);
            this.j = Math.max(e2 + 1, this.j);
            this.k = Math.max(this.k, b2.v());
            jVar.a(this.f3400d, this.f3401e, this);
            return;
        }
        throw new t0(t0.f3383c);
    }

    @Override // d.l
    public int b() {
        return this.k;
    }

    t1 b(int i) {
        if (i < 65536) {
            t1[] t1VarArr = this.f3399c;
            if (i >= t1VarArr.length) {
                this.f3399c = new t1[Math.max(t1VarArr.length + 10, i + 1)];
                System.arraycopy(t1VarArr, 0, this.f3399c, 0, t1VarArr.length);
            }
            t1 t1Var = this.f3399c[i];
            if (t1Var != null) {
                return t1Var;
            }
            t1 t1Var2 = new t1(i, this);
            this.f3399c[i] = t1Var2;
            return t1Var2;
        }
        throw new u1();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(d.p.q0.r rVar) {
        int size = this.r.size();
        this.r.remove(rVar);
        int size2 = this.r.size();
        this.w = true;
        d.q.a.a(size2 == size - 1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(j jVar) {
        d.p.q qVar = this.o;
        if (qVar != null) {
            qVar.a(jVar.f(), jVar.e());
        }
        ArrayList arrayList = this.u;
        if (arrayList == null || arrayList.remove(jVar)) {
            return;
        }
        D.b("Could not remove validated cell " + d.c.a(jVar));
    }

    m a(int i) {
        Iterator it = this.f3402f.iterator();
        boolean z = false;
        m mVar = null;
        while (it.hasNext() && !z) {
            mVar = (m) it.next();
            if (mVar.f() >= i) {
                z = true;
            }
        }
        if (z && mVar.f() == i) {
            return mVar;
        }
        return null;
    }

    @Override // d.l
    public d.m a() {
        return this.z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(d.p.c0 c0Var, d.p.c0 c0Var2, d.p.c0 c0Var3) {
        Iterator it = this.f3402f.iterator();
        while (it.hasNext()) {
            ((m) it.next()).a(c0Var);
        }
        int i = 0;
        while (true) {
            t1[] t1VarArr = this.f3399c;
            if (i >= t1VarArr.length) {
                break;
            }
            if (t1VarArr[i] != null) {
                t1VarArr[i].a(c0Var);
            }
            i++;
        }
        for (d.p.q0.d dVar : e()) {
            dVar.a(c0Var, c0Var2, c0Var3);
        }
    }

    private String a(String str) {
        int i = 0;
        if (str.length() > 31) {
            D.b("Sheet name " + str + " too long - truncating");
            str = str.substring(0, 31);
        }
        if (str.charAt(0) == '\'') {
            D.b("Sheet naming cannot start with ' - removing");
            str = str.substring(1);
        }
        while (true) {
            char[] cArr = E;
            if (i >= cArr.length) {
                return str;
            }
            String replace = str.replace(cArr[i], '@');
            if (str != replace) {
                D.b(E[i] + " is not a valid character within a sheet name - replacing");
            }
            i++;
            str = replace;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(d.p.q0.r rVar) {
        this.r.add(rVar);
        d.q.a.a(!(rVar instanceof d.p.q0.n));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(j jVar) {
        this.u.add(jVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(d.p.q0.h hVar) {
        this.v = hVar;
    }
}
