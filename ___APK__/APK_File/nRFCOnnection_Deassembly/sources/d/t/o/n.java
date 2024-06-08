package d.t.o;

import d.p.c;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import no.nordicsemi.android.dfu.DfuBaseService;

/* loaded from: classes.dex */
final class n extends d.p.c {
    private static d.q.c z = d.q.c.b(n.class);

    /* renamed from: c, reason: collision with root package name */
    private OutputStream f3344c;

    /* renamed from: d, reason: collision with root package name */
    private a0 f3345d;

    /* renamed from: e, reason: collision with root package name */
    private int f3346e;

    /* renamed from: f, reason: collision with root package name */
    private int f3347f;
    private int g;
    private int h;
    private int i;
    private int j;
    private int k;
    private int l;
    private int m;
    private int n;
    private int o;
    private int p;
    private int q;
    private int r;
    private int s;
    private int t;
    private int u;
    private ArrayList v;
    private HashMap w;
    private int x;
    private byte[] y;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class a {

        /* renamed from: a, reason: collision with root package name */
        c.a f3348a;

        /* renamed from: b, reason: collision with root package name */
        byte[] f3349b;

        /* renamed from: c, reason: collision with root package name */
        int f3350c;

        a(c.a aVar, byte[] bArr, int i) {
            this.f3348a = aVar;
            this.f3349b = bArr;
            this.f3350c = i;
        }
    }

    public n(a0 a0Var, int i, OutputStream outputStream, d.s.a.c cVar) {
        this.f3346e = i;
        this.f3345d = a0Var;
        a(cVar);
        this.u = 1;
        ArrayList arrayList = this.v;
        this.t = (arrayList != null ? arrayList.size() : 0) + 4;
        if (this.v != null) {
            this.h = a(this.s * 4);
            this.i = a(this.s * 64);
            this.u += a(this.v.size() * 128);
        }
        int a2 = a(i);
        if (i < 4096) {
            this.f3347f = 4096;
        } else {
            this.f3347f = a2 * DfuBaseService.ERROR_REMOTE_TYPE_SECURE;
        }
        this.f3344c = outputStream;
        this.l = this.f3347f / DfuBaseService.ERROR_REMOTE_TYPE_SECURE;
        this.g = 1;
        int i2 = this.l + 8 + 8 + this.r + this.i + this.h + this.u;
        double d2 = this.g + i2;
        Double.isNaN(d2);
        this.g = (int) Math.ceil(d2 / 128.0d);
        double d3 = this.g + i2;
        Double.isNaN(d3);
        this.g = (int) Math.ceil(d3 / 128.0d);
        int i3 = this.g;
        int i4 = i2 + i3;
        if (i3 > 108) {
            this.k = 0;
            double d4 = (i3 - 109) + 1;
            Double.isNaN(d4);
            this.j = (int) Math.ceil(d4 / 127.0d);
            double d5 = this.j + i2 + this.g;
            Double.isNaN(d5);
            this.g = (int) Math.ceil(d5 / 128.0d);
            i4 = i2 + this.j + this.g;
        } else {
            this.k = -2;
            this.j = 0;
        }
        this.n = this.j;
        this.q = -2;
        if (this.v != null && this.i != 0) {
            this.q = this.n + this.l + this.r + 16;
        }
        this.p = -2;
        int i5 = this.q;
        if (i5 != -2) {
            this.p = i5 + this.i;
        }
        int i6 = this.p;
        if (i6 != -2) {
            this.o = i6 + this.h;
        } else {
            this.o = this.n + this.l + this.r + 16;
        }
        this.m = this.o + this.g;
        if (i4 != this.m + this.u) {
            z.b("Root start block and total blocks are inconsistent  generated file may be corrupt");
            z.b("RootStartBlock " + this.m + " totalBlocks " + i4);
        }
    }

    private void a(d.s.a.c cVar) {
        boolean z2;
        if (cVar == null) {
            return;
        }
        this.v = new ArrayList();
        this.w = new HashMap();
        int a2 = cVar.a();
        int i = 0;
        for (int i2 = 0; i2 < a2; i2++) {
            c.a a3 = cVar.a(i2);
            if (a3.f2845a.equalsIgnoreCase("Root Entry")) {
                this.w.put("Root Entry", new a(a3, null, i2));
                z2 = true;
            } else {
                z2 = false;
            }
            boolean z3 = z2;
            int i3 = 0;
            while (true) {
                String[] strArr = d.p.c.f2844b;
                if (i3 >= strArr.length || z3) {
                    break;
                }
                if (a3.f2845a.equalsIgnoreCase(strArr[i3])) {
                    c.a a4 = cVar.a(a3.f2845a);
                    d.q.a.a(a4 != null);
                    if (a4 == a3) {
                        this.w.put(d.p.c.f2844b[i3], new a(a3, null, i2));
                        z3 = true;
                    }
                }
                i3++;
            }
            if (!z3) {
                try {
                    byte[] b2 = a3.f2849e > 0 ? cVar.b(i2) : new byte[0];
                    this.v.add(new a(a3, b2, i2));
                    if (b2.length > 4096) {
                        i += a(b2.length);
                    } else {
                        this.s += b(b2.length);
                    }
                } catch (d.s.a.b e2) {
                    z.a(e2);
                    throw new o();
                }
            }
        }
        this.r = i;
    }

    private void b() {
        if (this.x >= 512) {
            this.f3344c.write(this.y);
            this.y = new byte[DfuBaseService.ERROR_REMOTE_TYPE_SECURE];
            this.x = 0;
        }
    }

    private void c() {
        ArrayList arrayList = this.v;
        if (arrayList == null) {
            return;
        }
        int i = this.n + this.l + 16;
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            byte[] bArr = ((a) it.next()).f3349b;
            if (bArr.length > 4096) {
                int a2 = a(bArr.length);
                a(i, a2);
                i += a2;
            }
        }
    }

    private void d() {
        ArrayList arrayList = this.v;
        if (arrayList == null) {
            return;
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            byte[] bArr = ((a) it.next()).f3349b;
            if (bArr.length > 4096) {
                int a2 = a(bArr.length) * DfuBaseService.ERROR_REMOTE_TYPE_SECURE;
                this.f3344c.write(bArr, 0, bArr.length);
                byte[] bArr2 = new byte[a2 - bArr.length];
                this.f3344c.write(bArr2, 0, bArr2.length);
            }
        }
    }

    private void e() {
        this.y = new byte[DfuBaseService.ERROR_REMOTE_TYPE_SECURE];
        this.x = 0;
        for (int i = 0; i < this.j; i++) {
            d.p.d0.a(-3, this.y, this.x);
            this.x += 4;
            b();
        }
        a(this.n, this.l);
        int i2 = this.n + this.l + this.r;
        int i3 = i2;
        while (i3 < i2 + 7) {
            i3++;
            d.p.d0.a(i3, this.y, this.x);
            this.x += 4;
            b();
        }
        d.p.d0.a(-2, this.y, this.x);
        this.x += 4;
        b();
        int i4 = i2 + 8;
        while (i4 < i2 + 15) {
            i4++;
            d.p.d0.a(i4, this.y, this.x);
            this.x += 4;
            b();
        }
        d.p.d0.a(-2, this.y, this.x);
        this.x += 4;
        b();
        c();
        int i5 = this.q;
        if (i5 != -2) {
            a(i5, this.i);
            a(this.p, this.h);
        }
        for (int i6 = 0; i6 < this.g; i6++) {
            d.p.d0.a(-3, this.y, this.x);
            this.x += 4;
            b();
        }
        a(this.m, this.u);
        int i7 = this.x;
        if (i7 != 0) {
            while (i7 < 512) {
                this.y[i7] = -1;
                i7++;
            }
            this.f3344c.write(this.y);
        }
    }

    private void f() {
        this.f3344c.write(new byte[4096]);
    }

    private void g() {
        this.f3345d.a(this.f3344c);
        this.f3344c.write(new byte[this.f3347f - this.f3346e]);
    }

    private void h() {
        int i;
        byte[] bArr = new byte[DfuBaseService.ERROR_REMOTE_TYPE_SECURE];
        byte[] bArr2 = new byte[this.j * DfuBaseService.ERROR_REMOTE_TYPE_SECURE];
        byte[] bArr3 = d.p.c.f2843a;
        System.arraycopy(bArr3, 0, bArr, 0, bArr3.length);
        bArr[24] = 62;
        bArr[26] = 3;
        bArr[28] = -2;
        bArr[29] = -1;
        bArr[30] = 9;
        bArr[32] = 6;
        bArr[57] = 16;
        d.p.d0.a(this.g, bArr, 44);
        d.p.d0.a(this.p, bArr, 60);
        d.p.d0.a(this.h, bArr, 64);
        d.p.d0.a(this.k, bArr, 68);
        d.p.d0.a(this.j, bArr, 72);
        d.p.d0.a(this.m, bArr, 48);
        int min = Math.min(this.g, 109);
        int i2 = 76;
        int i3 = 0;
        for (int i4 = 0; i4 < min; i4++) {
            d.p.d0.a(this.o + i4, bArr, i2);
            i2 += 4;
            i3++;
        }
        while (i2 < 512) {
            bArr[i2] = -1;
            i2++;
        }
        this.f3344c.write(bArr);
        int i5 = 0;
        int i6 = 0;
        while (true) {
            i = this.j;
            if (i5 >= i) {
                break;
            }
            int min2 = Math.min(this.g - i3, 127);
            int i7 = i6;
            for (int i8 = 0; i8 < min2; i8++) {
                d.p.d0.a(this.o + i3 + i8, bArr2, i7);
                i7 += 4;
            }
            i3 += min2;
            d.p.d0.a(i3 == this.g ? -2 : i5 + 1, bArr2, i7);
            i6 = i7 + 4;
            i5++;
        }
        if (i > 0) {
            while (i6 < bArr2.length) {
                bArr2[i6] = -1;
                i6++;
            }
            this.f3344c.write(bArr2);
        }
    }

    private void i() {
        int[] iArr;
        int i;
        int i2;
        int i3;
        a aVar;
        int b2;
        String[] strArr;
        byte[] bArr = new byte[this.u * DfuBaseService.ERROR_REMOTE_TYPE_SECURE];
        int i4 = 1;
        if (this.v != null) {
            iArr = new int[this.t];
            int i5 = 0;
            while (true) {
                strArr = d.p.c.f2844b;
                if (i5 >= strArr.length) {
                    break;
                }
                a aVar2 = (a) this.w.get(strArr[i5]);
                if (aVar2 != null) {
                    iArr[aVar2.f3350c] = i5;
                } else {
                    z.b("Standard property set " + d.p.c.f2844b[i5] + " not present in source file");
                }
                i5++;
            }
            int length = strArr.length;
            Iterator it = this.v.iterator();
            while (it.hasNext()) {
                iArr[((a) it.next()).f3350c] = length;
                length++;
            }
        } else {
            iArr = null;
        }
        if (this.v != null) {
            i = (a(this.f3347f) * DfuBaseService.ERROR_REMOTE_TYPE_SECURE) + 0 + (a(4096) * DfuBaseService.ERROR_REMOTE_TYPE_SECURE) + (a(4096) * DfuBaseService.ERROR_REMOTE_TYPE_SECURE);
            Iterator it2 = this.v.iterator();
            while (it2.hasNext()) {
                c.a aVar3 = ((a) it2.next()).f3348a;
                if (aVar3.f2846b != 1) {
                    int i6 = aVar3.f2849e;
                    if (i6 >= 4096) {
                        b2 = a(i6) * DfuBaseService.ERROR_REMOTE_TYPE_SECURE;
                    } else {
                        b2 = b(i6) * 64;
                    }
                    i += b2;
                }
            }
        } else {
            i = 0;
        }
        c.a aVar4 = new c.a(this, "Root Entry");
        aVar4.g(5);
        aVar4.f(this.q);
        aVar4.e(i);
        aVar4.d(-1);
        aVar4.c(-1);
        aVar4.b(0);
        aVar4.a(this.v != null ? iArr[((a) this.w.get("Root Entry")).f3348a.h] : 1);
        System.arraycopy(aVar4.i, 0, bArr, 0, 128);
        c.a aVar5 = new c.a(this, "Workbook");
        aVar5.g(2);
        aVar5.f(this.n);
        aVar5.e(this.f3347f);
        int i7 = 3;
        if (this.v != null) {
            a aVar6 = (a) this.w.get("Workbook");
            int i8 = aVar6.f3348a.f2850f;
            i3 = i8 != -1 ? iArr[i8] : -1;
            int i9 = aVar6.f3348a.g;
            i2 = i9 != -1 ? iArr[i9] : -1;
        } else {
            i2 = -1;
            i3 = 3;
        }
        aVar5.d(i3);
        aVar5.c(i2);
        aVar5.a(-1);
        System.arraycopy(aVar5.i, 0, bArr, 128, 128);
        c.a aVar7 = new c.a(this, "\u0005SummaryInformation");
        aVar7.g(2);
        aVar7.f(this.n + this.l);
        aVar7.e(4096);
        if (this.v != null && (aVar = (a) this.w.get("\u0005SummaryInformation")) != null) {
            int i10 = aVar.f3348a.f2850f;
            i4 = i10 != -1 ? iArr[i10] : -1;
            int i11 = aVar.f3348a.g;
            i7 = i11 != -1 ? iArr[i11] : -1;
        }
        aVar7.d(i4);
        aVar7.c(i7);
        aVar7.a(-1);
        System.arraycopy(aVar7.i, 0, bArr, 256, 128);
        c.a aVar8 = new c.a(this, "\u0005DocumentSummaryInformation");
        aVar8.g(2);
        aVar8.f(this.n + this.l + 8);
        aVar8.e(4096);
        aVar8.d(-1);
        aVar8.c(-1);
        aVar8.a(-1);
        System.arraycopy(aVar8.i, 0, bArr, 384, 128);
        ArrayList arrayList = this.v;
        if (arrayList == null) {
            this.f3344c.write(bArr);
            return;
        }
        int i12 = this.n + this.l + 16;
        Iterator it3 = arrayList.iterator();
        int i13 = 0;
        int i14 = DfuBaseService.ERROR_REMOTE_TYPE_SECURE;
        while (it3.hasNext()) {
            a aVar9 = (a) it3.next();
            int i15 = aVar9.f3349b.length > 4096 ? i12 : i13;
            c.a aVar10 = new c.a(this, aVar9.f3348a.f2845a);
            aVar10.g(aVar9.f3348a.f2846b);
            aVar10.f(i15);
            aVar10.e(aVar9.f3348a.f2849e);
            int i16 = aVar9.f3348a.f2850f;
            int i17 = i16 != -1 ? iArr[i16] : -1;
            int i18 = aVar9.f3348a.g;
            int i19 = i18 != -1 ? iArr[i18] : -1;
            int i20 = aVar9.f3348a.h;
            int i21 = i20 != -1 ? iArr[i20] : -1;
            aVar10.d(i17);
            aVar10.c(i19);
            aVar10.a(i21);
            System.arraycopy(aVar10.i, 0, bArr, i14, 128);
            i14 += 128;
            byte[] bArr2 = aVar9.f3349b;
            if (bArr2.length > 4096) {
                i12 += a(bArr2.length);
            } else {
                i13 += b(bArr2.length);
            }
        }
        this.f3344c.write(bArr);
    }

    private void j() {
        ArrayList arrayList = this.v;
        if (arrayList == null) {
            return;
        }
        byte[] bArr = new byte[this.i * DfuBaseService.ERROR_REMOTE_TYPE_SECURE];
        Iterator it = arrayList.iterator();
        int i = 0;
        while (it.hasNext()) {
            a aVar = (a) it.next();
            byte[] bArr2 = aVar.f3349b;
            if (bArr2.length <= 4096) {
                int b2 = b(bArr2.length) * 64;
                byte[] bArr3 = aVar.f3349b;
                System.arraycopy(bArr3, 0, bArr, i, bArr3.length);
                i += b2;
            }
        }
        this.f3344c.write(bArr);
    }

    private void k() {
        if (this.p == -2) {
            return;
        }
        byte[] bArr = new byte[this.h * DfuBaseService.ERROR_REMOTE_TYPE_SECURE];
        Iterator it = this.v.iterator();
        int i = 0;
        int i2 = 1;
        while (it.hasNext()) {
            byte[] bArr2 = ((a) it.next()).f3349b;
            if (bArr2.length <= 4096 && bArr2.length != 0) {
                int b2 = b(bArr2.length);
                int i3 = i2;
                int i4 = i;
                for (int i5 = 0; i5 < b2 - 1; i5++) {
                    d.p.d0.a(i3, bArr, i4);
                    i4 += 4;
                    i3++;
                }
                d.p.d0.a(-2, bArr, i4);
                i = i4 + 4;
                i2 = i3 + 1;
            }
        }
        this.f3344c.write(bArr);
    }

    private void l() {
        this.f3344c.write(new byte[4096]);
    }

    private int b(int i) {
        int i2 = i / 64;
        return i % 64 > 0 ? i2 + 1 : i2;
    }

    public void a() {
        h();
        g();
        f();
        l();
        d();
        j();
        k();
        e();
        i();
    }

    private void a(int i, int i2) {
        int i3 = i2 - 1;
        int i4 = i + 1;
        while (i3 > 0) {
            int min = Math.min(i3, (512 - this.x) / 4);
            for (int i5 = 0; i5 < min; i5++) {
                d.p.d0.a(i4, this.y, this.x);
                this.x += 4;
                i4++;
            }
            i3 -= min;
            b();
        }
        d.p.d0.a(-2, this.y, this.x);
        this.x += 4;
        b();
    }

    private int a(int i) {
        int i2 = i / DfuBaseService.ERROR_REMOTE_TYPE_SECURE;
        return i % DfuBaseService.ERROR_REMOTE_TYPE_SECURE > 0 ? i2 + 1 : i2;
    }
}
