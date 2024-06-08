package d.p;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* loaded from: classes.dex */
public class a0 {
    private static d.q.c g = d.q.c.b(a0.class);

    /* renamed from: e, reason: collision with root package name */
    private y f2830e;

    /* renamed from: f, reason: collision with root package name */
    private g0 f2831f;

    /* renamed from: c, reason: collision with root package name */
    private ArrayList f2828c = new ArrayList(10);

    /* renamed from: a, reason: collision with root package name */
    private HashMap f2826a = new HashMap(10);

    /* renamed from: b, reason: collision with root package name */
    private ArrayList f2827b = new ArrayList(10);

    /* renamed from: d, reason: collision with root package name */
    private int f2829d = 164;

    public a0(y yVar) {
        this.f2830e = yVar;
    }

    public final void a(p0 p0Var) {
        if (!p0Var.i()) {
            p0Var.a(this.f2828c.size(), this, this.f2830e);
            this.f2828c.add(p0Var);
        } else if (p0Var.A() >= this.f2828c.size()) {
            this.f2828c.add(p0Var);
        }
    }

    public g0 b() {
        return this.f2831f;
    }

    public c0 c() {
        ArrayList arrayList = new ArrayList();
        c0 c0Var = new c0(this.f2829d);
        Iterator it = this.f2827b.iterator();
        int i = 0;
        while (it.hasNext()) {
            t tVar = (t) it.next();
            d.q.a.a(!tVar.g());
            Iterator it2 = arrayList.iterator();
            int i2 = i;
            boolean z = false;
            while (it2.hasNext() && !z) {
                t tVar2 = (t) it2.next();
                if (tVar2.equals(tVar)) {
                    c0Var.a(tVar.h(), c0Var.a(tVar2.h()));
                    i2++;
                    z = true;
                }
            }
            if (!z) {
                arrayList.add(tVar);
                if (tVar.h() - i2 > 441) {
                    g.b("Too many number formats - using default format.");
                }
                c0Var.a(tVar.h(), tVar.h() - i2);
            }
            i = i2;
        }
        this.f2827b = arrayList;
        Iterator it3 = this.f2827b.iterator();
        while (it3.hasNext()) {
            t tVar3 = (t) it3.next();
            tVar3.a(c0Var.a(tVar3.h()));
        }
        return c0Var;
    }

    public c0 d() {
        return this.f2830e.a();
    }

    public final void a(t tVar) {
        if (tVar.i() && tVar.h() >= 441) {
            g.b("Format index exceeds Excel maximum - assigning custom number");
            tVar.a(this.f2829d);
            this.f2829d++;
        }
        if (!tVar.i()) {
            tVar.a(this.f2829d);
            this.f2829d++;
        }
        if (this.f2829d <= 441) {
            if (tVar.h() >= this.f2829d) {
                this.f2829d = tVar.h() + 1;
            }
            if (tVar.g()) {
                return;
            }
            this.f2827b.add(tVar);
            this.f2826a.put(new Integer(tVar.h()), tVar);
            return;
        }
        this.f2829d = 441;
        throw new f0();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public z a(int i) {
        return (z) this.f2826a.get(new Integer(i));
    }

    public void a(d.t.o.e0 e0Var) {
        Iterator it = this.f2827b.iterator();
        while (it.hasNext()) {
            e0Var.a((z) it.next());
        }
        Iterator it2 = this.f2828c.iterator();
        while (it2.hasNext()) {
            e0Var.a((p0) it2.next());
        }
        e0Var.a(new f(16, 3));
        e0Var.a(new f(17, 6));
        e0Var.a(new f(18, 4));
        e0Var.a(new f(19, 7));
        e0Var.a(new f(0, 0));
        e0Var.a(new f(20, 5));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final y a() {
        return this.f2830e;
    }

    public c0 a(c0 c0Var, c0 c0Var2) {
        Iterator it = this.f2828c.iterator();
        while (it.hasNext()) {
            p0 p0Var = (p0) it.next();
            if (p0Var.w() >= 164) {
                p0Var.c(c0Var2.a(p0Var.w()));
            }
            p0Var.b(c0Var.a(p0Var.v()));
        }
        int i = 21;
        ArrayList arrayList = new ArrayList(21);
        c0 c0Var3 = new c0(this.f2828c.size());
        int min = Math.min(21, this.f2828c.size());
        for (int i2 = 0; i2 < min; i2++) {
            arrayList.add(this.f2828c.get(i2));
            c0Var3.a(i2, i2);
        }
        if (min < 21) {
            g.b("There are less than the expected minimum number of XF records");
            return c0Var3;
        }
        int i3 = 0;
        while (i < this.f2828c.size()) {
            p0 p0Var2 = (p0) this.f2828c.get(i);
            Iterator it2 = arrayList.iterator();
            int i4 = i3;
            boolean z = false;
            while (it2.hasNext() && !z) {
                p0 p0Var3 = (p0) it2.next();
                if (p0Var3.equals(p0Var2)) {
                    c0Var3.a(i, c0Var3.a(p0Var3.A()));
                    i4++;
                    z = true;
                }
            }
            if (!z) {
                arrayList.add(p0Var2);
                c0Var3.a(i, i - i4);
            }
            i++;
            i3 = i4;
        }
        Iterator it3 = this.f2828c.iterator();
        while (it3.hasNext()) {
            ((p0) it3.next()).a(c0Var3);
        }
        this.f2828c = arrayList;
        return c0Var3;
    }
}
