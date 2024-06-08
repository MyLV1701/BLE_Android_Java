package d.p.q0;

import java.util.ArrayList;
import java.util.Iterator;
import no.nordicsemi.android.dfu.DfuBaseService;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class c0 extends s {

    /* renamed from: c, reason: collision with root package name */
    private byte[] f2948c;

    /* renamed from: d, reason: collision with root package name */
    private int f2949d;

    /* renamed from: e, reason: collision with root package name */
    private ArrayList f2950e;

    static {
        d.q.c.b(c0.class);
    }

    public c0(v vVar) {
        super(vVar);
        this.f2949d = d();
        i();
    }

    private void i() {
        this.f2950e = new ArrayList();
        byte[] a2 = a();
        int i = 0;
        for (int i2 = 0; i2 < this.f2949d; i2++) {
            int a3 = d.p.d0.a(a2[i], a2[i + 1]);
            int i3 = a3 & 16383;
            int a4 = d.p.d0.a(a2[i + 2], a2[i + 3], a2[i + 4], a2[i + 5]);
            boolean z = true;
            boolean z2 = (a3 & DfuBaseService.ERROR_CONNECTION_MASK) != 0;
            if ((a3 & DfuBaseService.ERROR_CONNECTION_STATE_MASK) == 0) {
                z = false;
            }
            i += 6;
            this.f2950e.add(new a(i3, z2, z, a4));
        }
        Iterator it = this.f2950e.iterator();
        while (it.hasNext()) {
            a aVar = (a) it.next();
            if (aVar.f2953c) {
                aVar.f2955e = d.p.j0.a(a2, aVar.f2954d / 2, i);
                i += aVar.f2954d;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(int i, boolean z, boolean z2, int i2) {
        this.f2950e.add(new a(i, z, z2, i2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // d.p.q0.s, d.p.q0.u
    public byte[] b() {
        String str;
        this.f2949d = this.f2950e.size();
        a(this.f2949d);
        this.f2948c = new byte[this.f2949d * 6];
        Iterator it = this.f2950e.iterator();
        int i = 0;
        while (it.hasNext()) {
            a aVar = (a) it.next();
            int i2 = aVar.f2951a & 16383;
            if (aVar.f2952b) {
                i2 |= DfuBaseService.ERROR_CONNECTION_MASK;
            }
            if (aVar.f2953c) {
                i2 |= DfuBaseService.ERROR_CONNECTION_STATE_MASK;
            }
            d.p.d0.b(i2, this.f2948c, i);
            d.p.d0.a(aVar.f2954d, this.f2948c, i + 2);
            i += 6;
        }
        Iterator it2 = this.f2950e.iterator();
        while (it2.hasNext()) {
            a aVar2 = (a) it2.next();
            if (aVar2.f2953c && (str = aVar2.f2955e) != null) {
                byte[] bArr = new byte[this.f2948c.length + (str.length() * 2)];
                byte[] bArr2 = this.f2948c;
                System.arraycopy(bArr2, 0, bArr, 0, bArr2.length);
                d.p.j0.b(aVar2.f2955e, bArr, this.f2948c.length);
                this.f2948c = bArr;
            }
        }
        return a(this.f2948c);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public a c(int i) {
        Iterator it = this.f2950e.iterator();
        boolean z = false;
        a aVar = null;
        while (it.hasNext() && !z) {
            aVar = (a) it.next();
            if (aVar.f2951a == i) {
                z = true;
            }
        }
        if (z) {
            return aVar;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(int i, boolean z, boolean z2, int i2, String str) {
        this.f2950e.add(new a(i, z, z2, i2, str));
    }

    public c0() {
        super(w.n);
        this.f2950e = new ArrayList();
        b(3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class a {

        /* renamed from: a, reason: collision with root package name */
        int f2951a;

        /* renamed from: b, reason: collision with root package name */
        boolean f2952b;

        /* renamed from: c, reason: collision with root package name */
        boolean f2953c;

        /* renamed from: d, reason: collision with root package name */
        int f2954d;

        /* renamed from: e, reason: collision with root package name */
        String f2955e;

        public a(int i, boolean z, boolean z2, int i2) {
            this.f2951a = i;
            this.f2952b = z;
            this.f2953c = z2;
            this.f2954d = i2;
        }

        public a(int i, boolean z, boolean z2, int i2, String str) {
            this.f2951a = i;
            this.f2952b = z;
            this.f2953c = z2;
            this.f2954d = i2;
            this.f2955e = str;
        }
    }
}
