package d.t.o;

import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class z0 {

    /* renamed from: c, reason: collision with root package name */
    private static d.q.c f3427c = d.q.c.b(z0.class);

    /* renamed from: a, reason: collision with root package name */
    private ArrayList f3428a = new ArrayList();

    /* renamed from: b, reason: collision with root package name */
    private d.t.l f3429b;

    public z0(d.t.l lVar) {
        this.f3429b = lVar;
    }

    private void b() {
        ArrayList arrayList = new ArrayList(this.f3428a.size());
        Iterator it = this.f3428a.iterator();
        while (it.hasNext()) {
            d.p.i0 i0Var = (d.p.i0) it.next();
            Iterator it2 = arrayList.iterator();
            boolean z = false;
            while (it2.hasNext() && !z) {
                if (((d.p.i0) it2.next()).a(i0Var)) {
                    f3427c.b("Could not merge cells " + i0Var + " as they clash with an existing set of merged cells.");
                    z = true;
                }
            }
            if (!z) {
                arrayList.add(i0Var);
            }
        }
        this.f3428a = arrayList;
    }

    private void c() {
        for (int i = 0; i < this.f3428a.size(); i++) {
            try {
                d.p.i0 i0Var = (d.p.i0) this.f3428a.get(i);
                d.a b2 = i0Var.b();
                d.a a2 = i0Var.a();
                boolean z = false;
                for (int f2 = b2.f(); f2 <= a2.f(); f2++) {
                    for (int e2 = b2.e(); e2 <= a2.e(); e2++) {
                        if (this.f3429b.a(f2, e2).getType() != d.d.f2808b) {
                            if (z) {
                                f3427c.b("Range " + i0Var + " contains more than one data cell.  Setting the other cells to blank.");
                                this.f3429b.a(new d.t.a(f2, e2));
                            } else {
                                z = true;
                            }
                        }
                    }
                }
            } catch (d.t.n unused) {
                d.q.a.a(false);
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public d.k[] a() {
        d.k[] kVarArr = new d.k[this.f3428a.size()];
        for (int i = 0; i < kVarArr.length; i++) {
            kVarArr[i] = (d.k) this.f3428a.get(i);
        }
        return kVarArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(e0 e0Var) {
        if (this.f3428a.size() == 0) {
            return;
        }
        if (!((v2) this.f3429b).h().k()) {
            b();
            c();
        }
        if (this.f3428a.size() < 1020) {
            e0Var.a(new a1(this.f3428a));
            return;
        }
        int size = (this.f3428a.size() / 1020) + 1;
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            int min = Math.min(1020, this.f3428a.size() - i);
            ArrayList arrayList = new ArrayList(min);
            for (int i3 = 0; i3 < min; i3++) {
                arrayList.add(this.f3428a.get(i + i3));
            }
            e0Var.a(new a1(arrayList));
            i += min;
        }
    }
}
