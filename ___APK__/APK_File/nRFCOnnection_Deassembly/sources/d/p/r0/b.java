package d.p.r0;

import no.nordicsemi.android.dfu.DfuBaseService;

/* loaded from: classes.dex */
class b extends o0 implements t0 {

    /* renamed from: e, reason: collision with root package name */
    private int f3052e;

    /* renamed from: f, reason: collision with root package name */
    private int f3053f;
    private int g;
    private int h;
    private int i;
    private boolean j;
    private boolean k;
    private boolean l;
    private boolean m;
    private t n;

    static {
        d.q.c.b(b.class);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(t tVar) {
        this.n = tVar;
    }

    public int a(byte[] bArr, int i) {
        this.f3052e = d.p.d0.a(bArr[i], bArr[i + 1]);
        this.g = d.p.d0.a(bArr[i + 2], bArr[i + 3]);
        this.i = d.p.d0.a(bArr[i + 4], bArr[i + 5]);
        int a2 = d.p.d0.a(bArr[i + 6], bArr[i + 7]);
        this.f3053f = a2 & 255;
        this.j = (a2 & DfuBaseService.ERROR_CONNECTION_MASK) != 0;
        this.k = (a2 & DfuBaseService.ERROR_CONNECTION_STATE_MASK) != 0;
        int a3 = d.p.d0.a(bArr[i + 8], bArr[i + 9]);
        this.h = a3 & 255;
        this.l = (a3 & DfuBaseService.ERROR_CONNECTION_MASK) != 0;
        this.m = (a3 & DfuBaseService.ERROR_CONNECTION_STATE_MASK) != 0;
        return 10;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int g() {
        return this.f3053f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int h() {
        return this.h;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(String str, t tVar) {
        this.n = tVar;
        int lastIndexOf = str.lastIndexOf(":");
        d.q.a.a(lastIndexOf != -1);
        String substring = str.substring(lastIndexOf + 1);
        int indexOf = str.indexOf(33);
        String substring2 = str.substring(indexOf + 1, lastIndexOf);
        this.f3053f = d.p.i.a(substring2);
        this.g = d.p.i.c(substring2);
        String substring3 = str.substring(0, indexOf);
        if (substring3.charAt(0) == '\'' && substring3.charAt(substring3.length() - 1) == '\'') {
            substring3 = substring3.substring(1, substring3.length() - 1);
        }
        this.f3052e = tVar.b(substring3);
        if (this.f3052e >= 0) {
            this.h = d.p.i.a(substring);
            this.i = d.p.i.c(substring);
            this.j = true;
            this.k = true;
            this.l = true;
            this.m = true;
            return;
        }
        throw new v(v.g, substring3);
    }

    @Override // d.p.r0.s0
    public void a(StringBuffer stringBuffer) {
        d.p.i.a(this.f3052e, this.f3053f, this.g, this.n, stringBuffer);
        stringBuffer.append(':');
        d.p.i.a(this.h, this.i, stringBuffer);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // d.p.r0.s0
    public byte[] a() {
        byte[] bArr = new byte[11];
        bArr[0] = i1.r.a();
        d.p.d0.b(this.f3052e, bArr, 1);
        d.p.d0.b(this.g, bArr, 3);
        d.p.d0.b(this.i, bArr, 5);
        int i = this.f3053f;
        if (this.k) {
            i |= DfuBaseService.ERROR_CONNECTION_STATE_MASK;
        }
        if (this.j) {
            i |= DfuBaseService.ERROR_CONNECTION_MASK;
        }
        d.p.d0.b(i, bArr, 7);
        int i2 = this.h;
        if (this.m) {
            i2 |= DfuBaseService.ERROR_CONNECTION_STATE_MASK;
        }
        if (this.l) {
            i2 |= DfuBaseService.ERROR_CONNECTION_MASK;
        }
        d.p.d0.b(i2, bArr, 9);
        return bArr;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(int i, int i2, int i3, int i4, int i5, boolean z, boolean z2, boolean z3, boolean z4) {
        this.f3052e = i;
        this.f3053f = i2;
        this.h = i3;
        this.g = i4;
        this.i = i5;
        this.j = z;
        this.l = z2;
        this.k = z3;
        this.m = z4;
    }
}
