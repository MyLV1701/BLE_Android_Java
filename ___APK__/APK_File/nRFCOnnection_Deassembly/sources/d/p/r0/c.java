package d.p.r0;

import no.nordicsemi.android.dfu.DfuBaseService;

/* loaded from: classes.dex */
class c extends o0 implements t0 {

    /* renamed from: e, reason: collision with root package name */
    private int f3062e;

    /* renamed from: f, reason: collision with root package name */
    private int f3063f;
    private int g;
    private int h;
    private boolean i;
    private boolean j;
    private boolean k;
    private boolean l;

    static {
        d.q.c.b(c.class);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public c() {
    }

    public int a(byte[] bArr, int i) {
        this.f3063f = d.p.d0.a(bArr[i], bArr[i + 1]);
        this.h = d.p.d0.a(bArr[i + 2], bArr[i + 3]);
        int a2 = d.p.d0.a(bArr[i + 4], bArr[i + 5]);
        this.f3062e = a2 & 255;
        this.i = (a2 & DfuBaseService.ERROR_CONNECTION_MASK) != 0;
        this.j = (a2 & DfuBaseService.ERROR_CONNECTION_STATE_MASK) != 0;
        int a3 = d.p.d0.a(bArr[i + 6], bArr[i + 7]);
        this.g = a3 & 255;
        this.k = (a3 & DfuBaseService.ERROR_CONNECTION_MASK) != 0;
        this.l = (a3 & DfuBaseService.ERROR_CONNECTION_STATE_MASK) != 0;
        return 8;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int g() {
        return this.f3062e;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int h() {
        return this.g;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public c(String str) {
        int indexOf = str.indexOf(":");
        d.q.a.a(indexOf != -1);
        String substring = str.substring(0, indexOf);
        String substring2 = str.substring(indexOf + 1);
        this.f3062e = d.p.i.a(substring);
        this.f3063f = d.p.i.c(substring);
        this.g = d.p.i.a(substring2);
        this.h = d.p.i.c(substring2);
        this.i = d.p.i.d(substring);
        this.j = d.p.i.e(substring);
        this.k = d.p.i.d(substring2);
        this.l = d.p.i.e(substring2);
    }

    @Override // d.p.r0.s0
    public void a(StringBuffer stringBuffer) {
        d.p.i.a(this.f3062e, this.f3063f, stringBuffer);
        stringBuffer.append(':');
        d.p.i.a(this.g, this.h, stringBuffer);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // d.p.r0.s0
    public byte[] a() {
        byte[] bArr = new byte[9];
        bArr[0] = !f() ? i1.o.a() : i1.o.b();
        d.p.d0.b(this.f3063f, bArr, 1);
        d.p.d0.b(this.h, bArr, 3);
        int i = this.f3062e;
        if (this.j) {
            i |= DfuBaseService.ERROR_CONNECTION_STATE_MASK;
        }
        if (this.i) {
            i |= DfuBaseService.ERROR_CONNECTION_MASK;
        }
        d.p.d0.b(i, bArr, 5);
        int i2 = this.g;
        if (this.l) {
            i2 |= DfuBaseService.ERROR_CONNECTION_STATE_MASK;
        }
        if (this.k) {
            i2 |= DfuBaseService.ERROR_CONNECTION_MASK;
        }
        d.p.d0.b(i2, bArr, 7);
        return bArr;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(int i, int i2, int i3, int i4, boolean z, boolean z2, boolean z3, boolean z4) {
        this.f3062e = i;
        this.g = i2;
        this.f3063f = i3;
        this.h = i4;
        this.i = z;
        this.k = z2;
        this.j = z3;
        this.l = z4;
    }
}
