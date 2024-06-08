package d.p.q0;

import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class t extends u {

    /* renamed from: b, reason: collision with root package name */
    private boolean f3030b;

    /* renamed from: c, reason: collision with root package name */
    private ArrayList f3031c;

    static {
        d.q.c.b(t.class);
    }

    public t(v vVar) {
        super(vVar);
        this.f3030b = false;
        this.f3031c = new ArrayList();
    }

    private void j() {
        u sVar;
        int f2 = f() + 8;
        int min = Math.min(f() + e(), g());
        while (f2 < min) {
            v vVar = new v(c(), f2);
            w g = vVar.g();
            if (g == w.i) {
                sVar = new l(vVar);
            } else if (g == w.k) {
                sVar = new j(vVar);
            } else if (g == w.f3042e) {
                sVar = new a(vVar);
            } else if (g == w.g) {
                sVar = new j0(vVar);
            } else if (g == w.h) {
                sVar = new h0(vVar);
            } else if (g == w.l) {
                sVar = new i0(vVar);
            } else if (g == w.m) {
                sVar = new g0(vVar);
            } else if (g == w.o) {
                sVar = new e(vVar);
            } else if (g == w.p) {
                sVar = new f(vVar);
            } else if (g == w.j) {
                sVar = new b(vVar);
            } else if (g == w.n) {
                sVar = new c0(vVar);
            } else if (g == w.r) {
                sVar = new k0(vVar);
            } else if (g == w.q) {
                sVar = new g(vVar);
            } else {
                sVar = new s(vVar);
            }
            this.f3031c.add(sVar);
            f2 += sVar.e();
        }
        this.f3030b = true;
    }

    public void a(u uVar) {
        this.f3031c.add(uVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // d.p.q0.u
    public byte[] b() {
        if (!this.f3030b) {
            j();
        }
        byte[] bArr = new byte[0];
        Iterator it = this.f3031c.iterator();
        while (it.hasNext()) {
            byte[] b2 = ((u) it.next()).b();
            if (b2 != null) {
                byte[] bArr2 = new byte[bArr.length + b2.length];
                System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
                System.arraycopy(b2, 0, bArr2, bArr.length, b2.length);
                bArr = bArr2;
            }
        }
        return a(bArr);
    }

    public u[] i() {
        if (!this.f3030b) {
            j();
        }
        ArrayList arrayList = this.f3031c;
        return (u[]) arrayList.toArray(new u[arrayList.size()]);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public t(w wVar) {
        super(wVar);
        a(true);
        this.f3031c = new ArrayList();
    }
}
