package d.p;

import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
public class y {

    /* renamed from: a, reason: collision with root package name */
    private ArrayList f3145a = new ArrayList();

    public void a(x xVar) {
        if (xVar.i()) {
            return;
        }
        int size = this.f3145a.size();
        if (size >= 4) {
            size++;
        }
        xVar.a(size);
        this.f3145a.add(xVar);
    }

    public x a(int i) {
        if (i > 4) {
            i--;
        }
        return (x) this.f3145a.get(i);
    }

    public void a(d.t.o.e0 e0Var) {
        Iterator it = this.f3145a.iterator();
        while (it.hasNext()) {
            e0Var.a((x) it.next());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public c0 a() {
        c0 c0Var = new c0(this.f3145a.size() + 1);
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 4; i++) {
            x xVar = (x) this.f3145a.get(i);
            arrayList.add(xVar);
            c0Var.a(xVar.v(), xVar.v());
        }
        int i2 = 4;
        int i3 = 0;
        while (i2 < this.f3145a.size()) {
            x xVar2 = (x) this.f3145a.get(i2);
            Iterator it = arrayList.iterator();
            int i4 = i3;
            boolean z = false;
            while (it.hasNext() && !z) {
                x xVar3 = (x) it.next();
                if (xVar2.equals(xVar3)) {
                    c0Var.a(xVar2.v(), c0Var.a(xVar3.v()));
                    i4++;
                    z = true;
                }
            }
            if (!z) {
                arrayList.add(xVar2);
                int v = xVar2.v() - i4;
                d.q.a.a(v > 4);
                c0Var.a(xVar2.v(), v);
            }
            i2++;
            i3 = i4;
        }
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            x xVar4 = (x) it2.next();
            xVar4.a(c0Var.a(xVar4.v()));
        }
        this.f3145a = arrayList;
        return c0Var;
    }
}
