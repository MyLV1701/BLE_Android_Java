package d.p.q0;

import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
public class f0 {

    /* renamed from: a, reason: collision with root package name */
    private ArrayList f2976a;

    /* renamed from: b, reason: collision with root package name */
    private boolean f2977b;

    /* renamed from: c, reason: collision with root package name */
    private d[] f2978c = new d[0];

    static {
        d.q.c.b(f0.class);
    }

    public f0(d.o oVar) {
    }

    private void b(d.t.o.e0 e0Var) {
        if (this.f2978c.length == 0 && this.f2976a.size() == 0) {
            return;
        }
        if (this.f2978c.length == 0 && this.f2976a.size() != 0) {
            Iterator it = this.f2976a.iterator();
            while (it.hasNext()) {
                r rVar = (r) it.next();
                e0Var.a(rVar.a());
                rVar.a(e0Var);
            }
            Iterator it2 = this.f2976a.iterator();
            while (it2.hasNext()) {
                ((r) it2.next()).b(e0Var);
            }
            return;
        }
        int i = 0;
        if (this.f2976a.size() != 0 || this.f2978c.length == 0) {
            int size = this.f2976a.size();
            d[] dVarArr = this.f2978c;
            t[] tVarArr = new t[dVarArr.length + size];
            boolean[] zArr = new boolean[dVarArr.length + size];
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                r rVar2 = (r) this.f2976a.get(i3);
                tVarArr[i3] = rVar2.b();
                if (i3 > 0) {
                    i2 += tVarArr[i3].e();
                }
                if (rVar2.d()) {
                    zArr[i3] = true;
                }
            }
            int i4 = 0;
            while (true) {
                d[] dVarArr2 = this.f2978c;
                if (i4 >= dVarArr2.length) {
                    break;
                }
                int i5 = i4 + size;
                tVarArr[i5] = dVarArr2[i4].e();
                i2 += tVarArr[i5].e();
                i4++;
            }
            k kVar = new k();
            kVar.a(new j(this.f2978c.length + size));
            j0 j0Var = new j0();
            h0 h0Var = new h0();
            h0Var.a(new i0());
            h0Var.a(new g0(e0.f2970c, 1024, 5));
            j0Var.a(h0Var);
            j0Var.a(tVarArr[0]);
            kVar.a(j0Var);
            byte[] b2 = kVar.b();
            d.p.d0.a(d.p.d0.a(b2[4], b2[5], b2[6], b2[7]) + i2, b2, 4);
            d.p.d0.a(d.p.d0.a(b2[28], b2[29], b2[30], b2[31]) + i2, b2, 28);
            if (zArr[0]) {
                byte[] bArr = new byte[b2.length - 8];
                System.arraycopy(b2, 0, bArr, 0, bArr.length);
                b2 = bArr;
            }
            e0Var.a(new z(b2));
            ((r) this.f2976a.get(0)).a(e0Var);
            for (int i6 = 1; i6 < tVarArr.length; i6++) {
                byte[] a2 = tVarArr[i6].a(tVarArr[i6].a());
                if (zArr[i6]) {
                    byte[] bArr2 = new byte[a2.length - 8];
                    System.arraycopy(a2, 0, bArr2, 0, bArr2.length);
                    a2 = bArr2;
                }
                e0Var.a(new z(a2));
                if (i6 < size) {
                    ((r) this.f2976a.get(i6)).a(e0Var);
                } else {
                    d dVar = this.f2978c[i6 - size];
                    e0Var.a(dVar.d());
                    e0Var.a(dVar);
                }
            }
            Iterator it3 = this.f2976a.iterator();
            while (it3.hasNext()) {
                ((r) it3.next()).b(e0Var);
            }
            return;
        }
        while (true) {
            d[] dVarArr3 = this.f2978c;
            if (i >= dVarArr3.length) {
                return;
            }
            d dVar2 = dVarArr3[i];
            if (dVar2.c() != null) {
                e0Var.a(dVar2.c());
            }
            if (dVar2.d() != null) {
                e0Var.a(dVar2.d());
            }
            e0Var.a(dVar2);
            i++;
        }
    }

    public void a(ArrayList arrayList, boolean z) {
        this.f2976a = arrayList;
        this.f2977b = z;
    }

    public void a(d.t.o.e0 e0Var) {
        int i;
        if (this.f2976a.size() == 0 && this.f2978c.length == 0) {
            return;
        }
        boolean z = this.f2977b;
        int size = this.f2976a.size();
        Iterator it = this.f2976a.iterator();
        while (true) {
            if (!it.hasNext() || z) {
                break;
            } else if (((r) it.next()).e() != d0.f2962a) {
                z = true;
            }
        }
        if (size > 0 && !z && !((r) this.f2976a.get(0)).c()) {
            z = true;
        }
        if (size == 0) {
            d[] dVarArr = this.f2978c;
            if (dVarArr.length == 1 && dVarArr[0].c() == null) {
                z = false;
            }
        }
        if (!z) {
            b(e0Var);
            return;
        }
        Object[] objArr = new Object[this.f2978c.length + size];
        t tVar = null;
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            t b2 = ((r) this.f2976a.get(i3)).b();
            if (b2 != null) {
                byte[] b3 = b2.b();
                objArr[i3] = b3;
                if (i3 == 0) {
                    tVar = b2;
                } else {
                    i2 += b3.length;
                }
            }
        }
        int i4 = 0;
        while (true) {
            d[] dVarArr2 = this.f2978c;
            if (i4 >= dVarArr2.length) {
                break;
            }
            t e2 = dVarArr2[i4].e();
            byte[] a2 = e2.a(e2.a());
            objArr[i4 + size] = a2;
            if (i4 == 0 && size == 0) {
                tVar = e2;
            } else {
                i2 += a2.length;
            }
            i4++;
        }
        k kVar = new k();
        kVar.a(new j(this.f2978c.length + size));
        j0 j0Var = new j0();
        h0 h0Var = new h0();
        h0Var.a(new i0());
        h0Var.a(new g0(e0.f2970c, 1024, 5));
        j0Var.a(h0Var);
        j0Var.a(tVar);
        kVar.a(j0Var);
        byte[] b4 = kVar.b();
        d.p.d0.a(d.p.d0.a(b4[4], b4[5], b4[6], b4[7]) + i2, b4, 4);
        d.p.d0.a(d.p.d0.a(b4[28], b4[29], b4[30], b4[31]) + i2, b4, 28);
        if (size > 0 && ((r) this.f2976a.get(0)).d()) {
            byte[] bArr = new byte[b4.length - 8];
            System.arraycopy(b4, 0, bArr, 0, bArr.length);
            b4 = bArr;
        }
        e0Var.a(new z(b4));
        if (size > 0) {
            ((r) this.f2976a.get(0)).a(e0Var);
        } else {
            d dVar = this.f2978c[0];
            e0Var.a(dVar.d());
            e0Var.a(dVar);
        }
        for (i = 1; i < objArr.length; i++) {
            byte[] bArr2 = (byte[]) objArr[i];
            if (i < size && ((r) this.f2976a.get(i)).d()) {
                byte[] bArr3 = new byte[bArr2.length - 8];
                System.arraycopy(bArr2, 0, bArr3, 0, bArr3.length);
                bArr2 = bArr3;
            }
            e0Var.a(new z(bArr2));
            if (i < size) {
                ((r) this.f2976a.get(i)).a(e0Var);
            } else {
                d dVar2 = this.f2978c[i - size];
                e0Var.a(dVar2.d());
                e0Var.a(dVar2);
            }
        }
        Iterator it2 = this.f2976a.iterator();
        while (it2.hasNext()) {
            ((r) it2.next()).b(e0Var);
        }
    }

    public d[] a() {
        return this.f2978c;
    }
}
