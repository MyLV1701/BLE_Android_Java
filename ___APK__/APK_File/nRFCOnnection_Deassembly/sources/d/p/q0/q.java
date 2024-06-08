package d.p.q0;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* loaded from: classes.dex */
public class q implements x {
    private static d.q.c n = d.q.c.b(q.class);

    /* renamed from: a, reason: collision with root package name */
    private byte[] f3023a;

    /* renamed from: b, reason: collision with root package name */
    private t f3024b;

    /* renamed from: c, reason: collision with root package name */
    private a f3025c;

    /* renamed from: d, reason: collision with root package name */
    private boolean f3026d;

    /* renamed from: e, reason: collision with root package name */
    private ArrayList f3027e;

    /* renamed from: f, reason: collision with root package name */
    private int f3028f;
    private int g;
    private int h;
    private boolean i;
    private d0 j;
    private HashMap k;
    private int l;
    private int m;

    public q(d0 d0Var) {
        this.j = d0Var;
        this.f3026d = d0Var == d0.f2963b;
        this.f3027e = new ArrayList();
        this.k = new HashMap();
        this.i = false;
        this.l = 1;
        this.m = 1024;
    }

    private a c() {
        if (this.f3025c == null) {
            if (!this.f3026d) {
                d();
            }
            u[] i = this.f3024b.i();
            if (i.length > 1 && i[1].h() == w.f3042e) {
                this.f3025c = (a) i[1];
            }
        }
        return this.f3025c;
    }

    private void d() {
        v vVar = new v(this, 0);
        d.q.a.a(vVar.h());
        this.f3024b = new t(vVar);
        d.q.a.a(this.f3024b.e() == this.f3023a.length);
        d.q.a.a(this.f3024b.h() == w.f3041d);
        this.f3026d = true;
    }

    public void a(r rVar) {
        if (this.j == d0.f2962a) {
            this.j = d0.f2964c;
            a c2 = c();
            this.h = (((l) this.f3024b.i()[0]).c(1).f3006a - this.f3028f) - 1;
            this.f3028f = c2 != null ? c2.j() : 0;
            if (c2 != null) {
                d.q.a.a(this.f3028f == c2.j());
            }
        }
        if (!(rVar instanceof n)) {
            this.l++;
            this.m++;
            rVar.a(this);
            rVar.a(this.l, this.f3028f + 1, this.m);
            if (this.f3027e.size() > this.l) {
                n.b("drawings length " + this.f3027e.size() + " exceeds the max object id " + this.l);
                return;
            }
            return;
        }
        n nVar = (n) rVar;
        n nVar2 = (n) this.k.get(rVar.f());
        if (nVar2 == null) {
            this.l++;
            this.m++;
            this.f3027e.add(nVar);
            nVar.a(this);
            nVar.a(this.l, this.f3028f + 1, this.m);
            this.f3028f++;
            this.k.put(nVar.f(), nVar);
            return;
        }
        nVar2.a(nVar2.k() + 1);
        nVar.a(this);
        nVar.a(nVar2.j(), nVar2.g(), nVar2.l());
    }

    @Override // d.p.q0.x
    public byte[] b() {
        return this.f3023a;
    }

    public void a(d.t.o.e0 e0Var) {
        d0 d0Var = this.j;
        int i = 0;
        if (d0Var == d0.f2963b) {
            m mVar = new m();
            int i2 = this.f3028f;
            l lVar = new l(this.g + i2 + 1, i2);
            lVar.a(1, 0);
            lVar.a(this.f3028f + 1, 0);
            mVar.a(lVar);
            a aVar = new a();
            Iterator it = this.f3027e.iterator();
            while (it.hasNext()) {
                Object next = it.next();
                if (next instanceof n) {
                    aVar.a(new b((n) next));
                    i++;
                }
            }
            if (i > 0) {
                aVar.c(i);
                mVar.a(aVar);
            }
            mVar.a(new c0());
            mVar.a(new k0());
            this.f3023a = mVar.b();
        } else if (d0Var == d0.f2964c) {
            m mVar2 = new m();
            int i3 = this.f3028f;
            l lVar2 = new l(this.g + i3 + 1, i3);
            lVar2.a(1, 0);
            lVar2.a(this.h + this.f3028f + 1, 0);
            mVar2.a(lVar2);
            a aVar2 = new a();
            aVar2.c(this.f3028f);
            a c2 = c();
            if (c2 != null) {
                for (u uVar : c2.i()) {
                    aVar2.a((b) uVar);
                }
            }
            Iterator it2 = this.f3027e.iterator();
            while (it2.hasNext()) {
                r rVar = (r) it2.next();
                if (rVar instanceof n) {
                    n nVar = (n) rVar;
                    if (nVar.e() == d0.f2963b) {
                        aVar2.a(new b(nVar));
                    }
                }
            }
            mVar2.a(aVar2);
            c0 c0Var = new c0();
            c0Var.a(191, false, false, 524296);
            c0Var.a(385, false, false, 134217737);
            c0Var.a(448, false, false, 134217792);
            mVar2.a(c0Var);
            mVar2.a(new k0());
            this.f3023a = mVar2.b();
        }
        e0Var.a(new y(this.f3023a));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte[] a(int i) {
        this.f3028f = c().j();
        d.q.a.a(i <= this.f3028f);
        d0 d0Var = this.j;
        d.q.a.a(d0Var == d0.f2962a || d0Var == d0.f2964c);
        return ((b) c().i()[i - 1]).i();
    }

    public boolean a() {
        return this.i;
    }
}
