package d.p;

import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
public class q {

    /* renamed from: e, reason: collision with root package name */
    private static d.q.c f2923e = d.q.c.b(q.class);

    /* renamed from: a, reason: collision with root package name */
    private r f2924a;

    /* renamed from: c, reason: collision with root package name */
    private int f2926c;

    /* renamed from: b, reason: collision with root package name */
    private ArrayList f2925b = new ArrayList();

    /* renamed from: d, reason: collision with root package name */
    private boolean f2927d = false;

    public q(int i, d.p.r0.t tVar, l0 l0Var, d.o oVar) {
        this.f2926c = i;
    }

    public void a(s sVar) {
        this.f2925b.add(sVar);
        sVar.a(this);
        if (this.f2927d) {
            d.q.a.a(this.f2924a != null);
            this.f2924a.v();
        }
    }

    public void a(d.t.o.e0 e0Var) {
        if (this.f2925b.size() > 65533) {
            f2923e.b("Maximum number of data validations exceeded - truncating...");
            this.f2925b = new ArrayList(this.f2925b.subList(0, 65532));
            d.q.a.a(this.f2925b.size() <= 65533);
        }
        if (this.f2924a == null) {
            this.f2924a = new r(new p(this.f2926c, this.f2925b.size()));
        }
        if (this.f2924a.x()) {
            e0Var.a(this.f2924a);
            Iterator it = this.f2925b.iterator();
            while (it.hasNext()) {
                e0Var.a((s) it.next());
            }
        }
    }

    public void a(int i, int i2) {
        Iterator it = this.f2925b.iterator();
        while (it.hasNext()) {
            s sVar = (s) it.next();
            if (sVar.w() == i && sVar.y() == i && sVar.x() == i2 && sVar.z() == i2) {
                it.remove();
                this.f2924a.w();
                return;
            }
        }
    }
}
