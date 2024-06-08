package d.t.o;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class d2 {
    private static d.q.c x = d.q.c.b(d2.class);

    /* renamed from: a, reason: collision with root package name */
    private e0 f3268a;

    /* renamed from: b, reason: collision with root package name */
    private t1[] f3269b;

    /* renamed from: c, reason: collision with root package name */
    private int f3270c;

    /* renamed from: d, reason: collision with root package name */
    private int f3271d;

    /* renamed from: e, reason: collision with root package name */
    private d.m f3272e;

    /* renamed from: f, reason: collision with root package name */
    private d.o f3273f;
    private ArrayList g;
    private ArrayList h;
    private ArrayList i;
    private ArrayList j;
    private d.p.a k;
    private ArrayList l;
    private d.p.q m;
    private z0 n;
    private h1 o;
    private g p;
    private TreeSet r;
    private d.p.q0.f0 s;
    private int u;
    private int v;
    private v2 w;
    private d.p.m0 q = new d.p.m0();
    private boolean t = false;

    public d2(e0 e0Var, v2 v2Var, d.o oVar) {
        this.f3268a = e0Var;
        this.w = v2Var;
        this.f3273f = oVar;
        this.s = new d.p.q0.f0(oVar);
    }

    private void d() {
        if (this.m != null && this.l.size() == 0) {
            this.m.a(this.f3268a);
            return;
        }
        if (this.m == null && this.l.size() > 0) {
            this.m = new d.p.q(this.w.f() != null ? this.w.f().g() : -1, this.w.g(), this.w.g(), this.f3273f);
        }
        Iterator it = this.l.iterator();
        while (it.hasNext()) {
            j jVar = (j) it.next();
            d.b b2 = jVar.b();
            if (!b2.d().a()) {
                if (!b2.d().b()) {
                    this.m.a(new d.p.s(b2.d()));
                } else if (jVar.f() == b2.d().d() && jVar.e() == b2.d().e()) {
                    this.m.a(new d.p.s(b2.d()));
                }
            }
        }
        this.m.a(this.f3268a);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(t1[] t1VarArr, ArrayList arrayList, ArrayList arrayList2, ArrayList arrayList3, z0 z0Var, TreeSet treeSet, int i, int i2) {
        this.f3269b = t1VarArr;
        this.g = arrayList;
        this.h = arrayList2;
        this.i = arrayList3;
        this.n = z0Var;
        this.r = treeSet;
        this.u = i;
        this.v = i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public d.p.q0.d[] b() {
        return this.s.a();
    }

    public void c() {
        int i;
        d.q.a.a(this.f3269b != null);
        if (this.t) {
            this.s.a(this.f3268a);
            return;
        }
        this.f3268a.a(new a(a.f3225e));
        int i2 = this.f3270c;
        int i3 = i2 / 32;
        if (i2 - (i3 * 32) != 0) {
            i3++;
        }
        int a2 = this.f3268a.a();
        p0 p0Var = new p0(0, this.f3270c, i3);
        this.f3268a.a(p0Var);
        if (this.f3272e.a()) {
            this.f3268a.a(new i(i.f3306e));
        } else {
            this.f3268a.a(new i(i.f3305d));
        }
        this.f3268a.a(new h(100));
        this.f3268a.a(new q1());
        this.f3268a.a(new s0(false));
        this.f3268a.a(new w(0.001d));
        this.f3268a.a(new y1(this.f3272e.G()));
        this.f3268a.a(new m1(this.f3272e.D()));
        this.f3268a.a(new l1(this.f3272e.C()));
        this.f3268a.a(new i0(true));
        j0 j0Var = new j0();
        j0Var.b(this.v + 1);
        j0Var.c(this.u + 1);
        this.f3268a.a(j0Var);
        this.f3268a.a(new v(this.f3272e.f(), this.f3272e.f() != 255));
        if (this.u > 0) {
            this.q.c(true);
        }
        if (this.v > 0) {
            this.q.a(true);
        }
        this.q.b(this.f3272e.j());
        this.f3268a.a(this.q);
        if (this.g.size() > 0) {
            int[] iArr = new int[this.g.size()];
            for (int i4 = 0; i4 < iArr.length; i4++) {
                iArr[i4] = ((Integer) this.g.get(i4)).intValue();
            }
            this.f3268a.a(new n0(iArr));
        }
        if (this.h.size() > 0) {
            int[] iArr2 = new int[this.h.size()];
            for (int i5 = 0; i5 < iArr2.length; i5++) {
                iArr2[i5] = ((Integer) this.h.get(i5)).intValue();
            }
            this.f3268a.a(new n2(iArr2));
        }
        this.f3268a.a(new k0(this.f3272e.n().toString()));
        this.f3268a.a(new g0(this.f3272e.l().toString()));
        this.f3268a.a(new m0(this.f3272e.P()));
        this.f3268a.a(new m2(this.f3272e.S()));
        if (this.f3272e.r() != this.f3272e.g()) {
            this.f3268a.a(new v0(this.f3272e.r()));
        }
        if (this.f3272e.H() != this.f3272e.g()) {
            this.f3268a.a(new s1(this.f3272e.H()));
        }
        if (this.f3272e.K() != this.f3272e.e()) {
            this.f3268a.a(new k2(this.f3272e.K()));
        }
        if (this.f3272e.b() != this.f3272e.e()) {
            this.f3268a.a(new e(this.f3272e.b()));
        }
        h1 h1Var = this.o;
        if (h1Var != null) {
            this.f3268a.a(h1Var);
        }
        this.f3268a.a(new b2(this.f3272e));
        if (this.f3272e.Q()) {
            this.f3268a.a(new p1(this.f3272e.Q()));
            this.f3268a.a(new z1(this.f3272e.Q()));
            this.f3268a.a(new g1(this.f3272e.Q()));
            if (this.f3272e.z() != null) {
                this.f3268a.a(new j1(this.f3272e.z()));
            } else if (this.f3272e.A() != 0) {
                this.f3268a.a(new j1(this.f3272e.A()));
            }
        }
        p0Var.c(this.f3268a.a());
        this.f3268a.a(new u(this.f3272e.d()));
        d.t.i f2 = this.w.g().g().f();
        d.t.i b2 = this.w.g().g().b();
        Iterator it = this.r.iterator();
        while (it.hasNext()) {
            m mVar = (m) it.next();
            if (mVar.f() < 256) {
                this.f3268a.a(mVar);
            }
            d.p.p0 d2 = mVar.d();
            if (d2 != f2 && mVar.f() < 256) {
                d.a[] a3 = a(mVar.f());
                for (int i6 = 0; i6 < a3.length; i6++) {
                    if (a3[i6] != null && (a3[i6].d() == f2 || a3[i6].d() == b2)) {
                        ((d.t.g) a3[i6]).a(d2);
                    }
                }
            }
        }
        d.p.a aVar = this.k;
        if (aVar == null) {
            this.f3268a.a(new x(this.f3270c, this.f3271d));
            for (int i7 = 0; i7 < i3; i7++) {
                q qVar = new q(this.f3268a.a());
                int i8 = i7 * 32;
                int min = Math.min(32, this.f3270c - i8);
                int i9 = i8;
                boolean z = true;
                while (true) {
                    i = i8 + min;
                    if (i9 >= i) {
                        break;
                    }
                    t1[] t1VarArr = this.f3269b;
                    if (t1VarArr[i9] != null) {
                        t1VarArr[i9].a(this.f3268a);
                        if (z) {
                            qVar.c(this.f3268a.a());
                            z = false;
                        }
                    }
                    i9++;
                }
                while (i8 < i) {
                    if (this.f3269b[i8] != null) {
                        qVar.b(this.f3268a.a());
                        this.f3269b[i8].b(this.f3268a);
                    }
                    i8++;
                }
                p0Var.b(this.f3268a.a());
                qVar.d(this.f3268a.a());
                this.f3268a.a(qVar);
            }
            if (!this.f3273f.b()) {
                this.s.a(this.f3268a);
            }
            this.f3268a.a(new q2(this.f3272e));
            if (this.f3272e.p() == 0 && this.f3272e.L() == 0) {
                this.f3268a.a(new a2(a2.i, 0, 0));
            } else {
                this.f3268a.a(new i1(this.f3272e.p(), this.f3272e.L()));
                this.f3268a.a(new a2(a2.i, 0, 0));
                if (this.f3272e.p() != 0) {
                    this.f3268a.a(new a2(a2.g, this.f3272e.p(), 0));
                }
                if (this.f3272e.L() != 0) {
                    this.f3268a.a(new a2(a2.h, 0, this.f3272e.L()));
                }
                if (this.f3272e.p() != 0 && this.f3272e.L() != 0) {
                    this.f3268a.a(new a2(a2.f3228f, this.f3272e.p(), this.f3272e.L()));
                }
                this.f3268a.a(new o2());
            }
            if (this.f3272e.N() != 100) {
                this.f3268a.a(new v1(this.f3272e.N()));
            }
            this.n.a(this.f3268a);
            Iterator it2 = this.i.iterator();
            while (it2.hasNext()) {
                this.f3268a.a((d.t.k) it2.next());
            }
            g gVar = this.p;
            if (gVar != null) {
                this.f3268a.a(gVar);
            }
            if (this.m != null || this.l.size() > 0) {
                d();
            }
            ArrayList arrayList = this.j;
            if (arrayList != null && arrayList.size() > 0) {
                Iterator it3 = this.j.iterator();
                while (it3.hasNext()) {
                    ((d.p.j) it3.next()).a(this.f3268a);
                }
            }
            this.f3268a.a(new y());
            this.f3268a.a(p0Var.u(), a2 + 4);
            return;
        }
        aVar.a(this.f3268a);
        throw null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(int i, int i2) {
        this.f3270c = i;
        this.f3271d = i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(d.m mVar) {
        this.f3272e = mVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(ArrayList arrayList, boolean z) {
        this.s.a(arrayList, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a() {
        d.k[] a2 = this.n.a();
        ArrayList arrayList = new ArrayList();
        for (d.k kVar : a2) {
            d.a b2 = kVar.b();
            d.p.p0 p0Var = (d.p.p0) b2.d();
            if (p0Var != null && p0Var.B() && !p0Var.C()) {
                try {
                    k kVar2 = new k(p0Var);
                    d.a a3 = kVar.a();
                    kVar2.b(d.r.b.f3157b, d.r.c.f3163d, d.r.e.f3168d);
                    kVar2.b(d.r.b.f3160e, p0Var.b(d.r.b.f3160e), p0Var.a(d.r.b.f3160e));
                    kVar2.b(d.r.b.f3158c, p0Var.b(d.r.b.f3158c), p0Var.a(d.r.b.f3158c));
                    if (b2.e() == a3.e()) {
                        kVar2.b(d.r.b.f3159d, p0Var.b(d.r.b.f3159d), p0Var.a(d.r.b.f3159d));
                    }
                    if (b2.f() == a3.f()) {
                        kVar2.b(d.r.b.f3161f, p0Var.b(d.r.b.f3161f), p0Var.a(d.r.b.f3161f));
                    }
                    int indexOf = arrayList.indexOf(kVar2);
                    if (indexOf != -1) {
                        kVar2 = (k) arrayList.get(indexOf);
                    } else {
                        arrayList.add(kVar2);
                    }
                    ((d.t.g) b2).a(kVar2);
                    if (a3.e() > b2.e()) {
                        if (a3.f() != b2.f()) {
                            k kVar3 = new k(p0Var);
                            kVar3.b(d.r.b.f3157b, d.r.c.f3163d, d.r.e.f3168d);
                            kVar3.b(d.r.b.f3160e, p0Var.b(d.r.b.f3160e), p0Var.a(d.r.b.f3160e));
                            kVar3.b(d.r.b.f3159d, p0Var.b(d.r.b.f3159d), p0Var.a(d.r.b.f3159d));
                            int indexOf2 = arrayList.indexOf(kVar3);
                            if (indexOf2 != -1) {
                                kVar3 = (k) arrayList.get(indexOf2);
                            } else {
                                arrayList.add(kVar3);
                            }
                            this.w.a((d.t.g) new d.t.a(b2.f(), a3.e(), kVar3));
                        }
                        for (int e2 = b2.e() + 1; e2 < a3.e(); e2++) {
                            k kVar4 = new k(p0Var);
                            kVar4.b(d.r.b.f3157b, d.r.c.f3163d, d.r.e.f3168d);
                            kVar4.b(d.r.b.f3160e, p0Var.b(d.r.b.f3160e), p0Var.a(d.r.b.f3160e));
                            if (b2.f() == a3.f()) {
                                kVar4.b(d.r.b.f3161f, p0Var.b(d.r.b.f3161f), p0Var.a(d.r.b.f3161f));
                            }
                            int indexOf3 = arrayList.indexOf(kVar4);
                            if (indexOf3 != -1) {
                                kVar4 = (k) arrayList.get(indexOf3);
                            } else {
                                arrayList.add(kVar4);
                            }
                            this.w.a((d.t.g) new d.t.a(b2.f(), e2, kVar4));
                        }
                    }
                    if (a3.f() > b2.f()) {
                        if (a3.e() != b2.e()) {
                            k kVar5 = new k(p0Var);
                            kVar5.b(d.r.b.f3157b, d.r.c.f3163d, d.r.e.f3168d);
                            kVar5.b(d.r.b.f3161f, p0Var.b(d.r.b.f3161f), p0Var.a(d.r.b.f3161f));
                            kVar5.b(d.r.b.f3158c, p0Var.b(d.r.b.f3158c), p0Var.a(d.r.b.f3158c));
                            int indexOf4 = arrayList.indexOf(kVar5);
                            if (indexOf4 != -1) {
                                kVar5 = (k) arrayList.get(indexOf4);
                            } else {
                                arrayList.add(kVar5);
                            }
                            this.w.a((d.t.g) new d.t.a(a3.f(), b2.e(), kVar5));
                        }
                        for (int e3 = b2.e() + 1; e3 < a3.e(); e3++) {
                            k kVar6 = new k(p0Var);
                            kVar6.b(d.r.b.f3157b, d.r.c.f3163d, d.r.e.f3168d);
                            kVar6.b(d.r.b.f3161f, p0Var.b(d.r.b.f3161f), p0Var.a(d.r.b.f3161f));
                            int indexOf5 = arrayList.indexOf(kVar6);
                            if (indexOf5 != -1) {
                                kVar6 = (k) arrayList.get(indexOf5);
                            } else {
                                arrayList.add(kVar6);
                            }
                            this.w.a((d.t.g) new d.t.a(a3.f(), e3, kVar6));
                        }
                        for (int f2 = b2.f() + 1; f2 < a3.f(); f2++) {
                            k kVar7 = new k(p0Var);
                            kVar7.b(d.r.b.f3157b, d.r.c.f3163d, d.r.e.f3168d);
                            kVar7.b(d.r.b.f3158c, p0Var.b(d.r.b.f3158c), p0Var.a(d.r.b.f3158c));
                            if (b2.e() == a3.e()) {
                                kVar7.b(d.r.b.f3159d, p0Var.b(d.r.b.f3159d), p0Var.a(d.r.b.f3159d));
                            }
                            int indexOf6 = arrayList.indexOf(kVar7);
                            if (indexOf6 != -1) {
                                kVar7 = (k) arrayList.get(indexOf6);
                            } else {
                                arrayList.add(kVar7);
                            }
                            this.w.a((d.t.g) new d.t.a(f2, b2.e(), kVar7));
                        }
                    }
                    if (a3.f() > b2.f() || a3.e() > b2.e()) {
                        k kVar8 = new k(p0Var);
                        kVar8.b(d.r.b.f3157b, d.r.c.f3163d, d.r.e.f3168d);
                        kVar8.b(d.r.b.f3161f, p0Var.b(d.r.b.f3161f), p0Var.a(d.r.b.f3161f));
                        kVar8.b(d.r.b.f3159d, p0Var.b(d.r.b.f3159d), p0Var.a(d.r.b.f3159d));
                        if (a3.e() == b2.e()) {
                            kVar8.b(d.r.b.f3158c, p0Var.b(d.r.b.f3158c), p0Var.a(d.r.b.f3158c));
                        }
                        if (a3.f() == b2.f()) {
                            kVar8.b(d.r.b.f3160e, p0Var.b(d.r.b.f3160e), p0Var.a(d.r.b.f3160e));
                        }
                        int indexOf7 = arrayList.indexOf(kVar8);
                        if (indexOf7 != -1) {
                            kVar8 = (k) arrayList.get(indexOf7);
                        } else {
                            arrayList.add(kVar8);
                        }
                        this.w.a((d.t.g) new d.t.a(a3.f(), a3.e(), kVar8));
                        for (int f3 = b2.f() + 1; f3 < a3.f(); f3++) {
                            k kVar9 = new k(p0Var);
                            kVar9.b(d.r.b.f3157b, d.r.c.f3163d, d.r.e.f3168d);
                            kVar9.b(d.r.b.f3159d, p0Var.b(d.r.b.f3159d), p0Var.a(d.r.b.f3159d));
                            if (b2.e() == a3.e()) {
                                kVar9.b(d.r.b.f3158c, p0Var.b(d.r.b.f3158c), p0Var.a(d.r.b.f3158c));
                            }
                            int indexOf8 = arrayList.indexOf(kVar9);
                            if (indexOf8 != -1) {
                                kVar9 = (k) arrayList.get(indexOf8);
                            } else {
                                arrayList.add(kVar9);
                            }
                            this.w.a((d.t.g) new d.t.a(f3, a3.e(), kVar9));
                        }
                    }
                } catch (d.t.n e4) {
                    x.b(e4.toString());
                }
            }
        }
    }

    private d.a[] a(int i) {
        int i2 = this.f3270c - 1;
        boolean z = false;
        while (i2 >= 0 && !z) {
            t1[] t1VarArr = this.f3269b;
            if (t1VarArr[i2] == null || t1VarArr[i2].b(i) == null) {
                i2--;
            } else {
                z = true;
            }
        }
        d.a[] aVarArr = new d.a[i2 + 1];
        for (int i3 = 0; i3 <= i2; i3++) {
            t1[] t1VarArr2 = this.f3269b;
            aVarArr[i3] = t1VarArr2[i3] != null ? t1VarArr2[i3].b(i) : null;
        }
        return aVarArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(h1 h1Var) {
        this.o = h1Var;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(g gVar) {
        this.p = gVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(d.p.q qVar, ArrayList arrayList) {
        this.m = qVar;
        this.l = arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(ArrayList arrayList) {
        this.j = arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(d.p.a aVar) {
        this.k = aVar;
    }
}
