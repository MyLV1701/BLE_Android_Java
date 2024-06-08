package d.p.q0;

import java.util.ArrayList;

/* loaded from: classes.dex */
public class o implements x {

    /* renamed from: d, reason: collision with root package name */
    private static d.q.c f3018d = d.q.c.b(o.class);

    /* renamed from: a, reason: collision with root package name */
    private byte[] f3019a = null;

    /* renamed from: b, reason: collision with root package name */
    private boolean f3020b = false;

    /* renamed from: c, reason: collision with root package name */
    private u[] f3021c;

    private void a() {
        v vVar = new v(this, 0);
        d.q.a.a(vVar.h());
        t tVar = new t(vVar);
        tVar.i();
        u[] i = tVar.i();
        t tVar2 = null;
        for (int i2 = 0; i2 < i.length && tVar2 == null; i2++) {
            u uVar = i[i2];
            if (uVar.h() == w.g) {
                tVar2 = (t) uVar;
            }
        }
        d.q.a.a(tVar2 != null);
        u[] i3 = tVar2.i();
        boolean z = false;
        for (int i4 = 0; i4 < i3.length && !z; i4++) {
            if (i3[i4].h() == w.g) {
                z = true;
            }
        }
        if (!z) {
            this.f3021c = i3;
        } else {
            ArrayList arrayList = new ArrayList();
            a(tVar2, arrayList);
            this.f3021c = new u[arrayList.size()];
            this.f3021c = (u[]) arrayList.toArray(this.f3021c);
        }
        this.f3020b = true;
    }

    @Override // d.p.q0.x
    public byte[] b() {
        return this.f3019a;
    }

    private void a(t tVar, ArrayList arrayList) {
        u[] i = tVar.i();
        for (int i2 = 0; i2 < i.length; i2++) {
            if (i[i2].h() == w.h) {
                arrayList.add(i[i2]);
            } else if (i[i2].h() == w.g) {
                a((t) i[i2], arrayList);
            } else {
                f3018d.b("Spgr Containers contains a record other than Sp/Spgr containers");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public t a(int i) {
        if (!this.f3020b) {
            a();
        }
        int i2 = i + 1;
        u[] uVarArr = this.f3021c;
        if (i2 < uVarArr.length) {
            t tVar = (t) uVarArr[i2];
            d.q.a.a(tVar != null);
            return tVar;
        }
        throw new p();
    }
}
