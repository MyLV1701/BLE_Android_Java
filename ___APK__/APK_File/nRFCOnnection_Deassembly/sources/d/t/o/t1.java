package d.t.o;

import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class t1 extends d.p.n0 {
    private static final d.q.c n = d.q.c.b(t1.class);
    private static int o = 255;
    private static int p = 256;

    /* renamed from: c, reason: collision with root package name */
    private j[] f3387c;

    /* renamed from: d, reason: collision with root package name */
    private int f3388d;

    /* renamed from: e, reason: collision with root package name */
    private boolean f3389e;

    /* renamed from: f, reason: collision with root package name */
    private int f3390f;
    private int g;
    private int h;
    private boolean i;
    private boolean j;
    private int k;
    private boolean l;
    private d.t.l m;

    public t1(int i, d.t.l lVar) {
        super(d.p.k0.j);
        this.f3390f = i;
        this.f3387c = new j[0];
        this.g = 0;
        this.f3388d = o;
        this.f3389e = false;
        this.j = true;
        this.m = lVar;
    }

    public void a(j jVar) {
        d.t.h j;
        int f2 = jVar.f();
        if (f2 >= p) {
            n.b("Could not add cell at " + d.p.i.a(jVar.e(), jVar.f()) + " because it exceeds the maximum column limit");
            return;
        }
        j[] jVarArr = this.f3387c;
        if (f2 >= jVarArr.length) {
            this.f3387c = new j[Math.max(jVarArr.length + 10, f2 + 1)];
            System.arraycopy(jVarArr, 0, this.f3387c, 0, jVarArr.length);
        }
        j[] jVarArr2 = this.f3387c;
        if (jVarArr2[f2] != null && (j = jVarArr2[f2].j()) != null) {
            j.g();
            if (j.d() != null && !j.d().b()) {
                j.h();
            }
        }
        this.f3387c[f2] = jVar;
        this.g = Math.max(f2 + 1, this.g);
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0059  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void b(d.t.o.e0 r10) {
        /*
            r9 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1 = 0
            r2 = 0
        L7:
            int r3 = r9.g
            if (r2 >= r3) goto L86
            d.t.o.j[] r3 = r9.f3387c
            r4 = r3[r2]
            if (r4 == 0) goto L80
            r3 = r3[r2]
            d.d r3 = r3.getType()
            d.d r4 = d.d.f2810d
            if (r3 != r4) goto L4e
            d.t.o.j[] r3 = r9.f3387c
            r3 = r3[r2]
            d.t.e r3 = (d.t.e) r3
            double r4 = r3.z()
            double r6 = r3.z()
            int r6 = (int) r6
            double r6 = (double) r6
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 != 0) goto L4e
            double r4 = r3.z()
            r6 = 4737786807976984576(0x41bfffffff000000, double:5.36870911E8)
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 >= 0) goto L4e
            double r4 = r3.z()
            r6 = -4485585228861014016(0xc1c0000000000000, double:-5.36870912E8)
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 <= 0) goto L4e
            d.b r3 = r3.b()
            if (r3 != 0) goto L4e
            r3 = 1
            goto L4f
        L4e:
            r3 = 0
        L4f:
            if (r3 == 0) goto L59
            d.t.o.j[] r3 = r9.f3387c
            r3 = r3[r2]
            r0.add(r3)
            goto L83
        L59:
            r9.a(r0, r10)
            d.t.o.j[] r3 = r9.f3387c
            r3 = r3[r2]
            r10.a(r3)
            d.t.o.j[] r3 = r9.f3387c
            r3 = r3[r2]
            d.d r3 = r3.getType()
            d.d r4 = d.d.f2811e
            if (r3 != r4) goto L83
            d.t.o.e2 r3 = new d.t.o.e2
            d.t.o.j[] r4 = r9.f3387c
            r4 = r4[r2]
            java.lang.String r4 = r4.c()
            r3.<init>(r4)
            r10.a(r3)
            goto L83
        L80:
            r9.a(r0, r10)
        L83:
            int r2 = r2 + 1
            goto L7
        L86:
            r9.a(r0, r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: d.t.o.t1.b(d.t.o.e0):void");
    }

    @Override // d.p.n0
    public byte[] u() {
        byte[] bArr = new byte[16];
        int i = this.f3388d;
        if (this.m.a().f() != 255 && i == o) {
            i = this.m.a().f();
        }
        d.p.d0.b(this.f3390f, bArr, 0);
        d.p.d0.b(this.g, bArr, 4);
        d.p.d0.b(i, bArr, 6);
        int i2 = this.k + 256;
        if (this.l) {
            i2 |= 16;
        }
        if (this.f3389e) {
            i2 |= 32;
        }
        if (!this.j) {
            i2 |= 64;
        }
        if (this.i) {
            i2 = i2 | 128 | (this.h << 16);
        }
        d.p.d0.a(i2, bArr, 12);
        return bArr;
    }

    public int v() {
        return this.g;
    }

    public void a(e0 e0Var) {
        e0Var.a(this);
    }

    private void a(ArrayList arrayList, e0 e0Var) {
        if (arrayList.size() == 0) {
            return;
        }
        if (arrayList.size() >= 3) {
            e0Var.a(new b1(arrayList));
        } else {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                e0Var.a((j) it.next());
            }
        }
        arrayList.clear();
    }

    public j b(int i) {
        if (i < 0 || i >= this.g) {
            return null;
        }
        return this.f3387c[i];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(d.p.c0 c0Var) {
        if (this.i) {
            this.h = c0Var.a(this.h);
        }
    }
}
