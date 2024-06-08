package d.p.r0;

import no.nordicsemi.android.dfu.DfuBaseService;

/* loaded from: classes.dex */
class z0 extends o0 implements t0 {

    /* renamed from: e, reason: collision with root package name */
    private int f3128e;

    /* renamed from: f, reason: collision with root package name */
    private int f3129f;
    private int g;
    private int h;
    private boolean i;
    private boolean j;
    private boolean k;
    private boolean l;
    private d.a m;

    public z0(d.a aVar) {
        this.m = aVar;
    }

    public int a(byte[] bArr, int i) {
        this.f3129f = d.p.d0.b(bArr[i], bArr[i + 1]);
        this.h = d.p.d0.b(bArr[i + 2], bArr[i + 3]);
        int a2 = d.p.d0.a(bArr[i + 4], bArr[i + 5]);
        this.f3128e = a2 & 255;
        this.i = (a2 & DfuBaseService.ERROR_CONNECTION_MASK) != 0;
        this.j = (a2 & DfuBaseService.ERROR_CONNECTION_STATE_MASK) != 0;
        if (this.i) {
            this.f3128e = this.m.f() + this.f3128e;
        }
        if (this.j) {
            this.f3129f = this.m.e() + this.f3129f;
        }
        int a3 = d.p.d0.a(bArr[i + 6], bArr[i + 7]);
        this.g = a3 & 255;
        this.k = (a3 & DfuBaseService.ERROR_CONNECTION_MASK) != 0;
        this.l = (a3 & DfuBaseService.ERROR_CONNECTION_STATE_MASK) != 0;
        if (this.k) {
            this.g = this.m.f() + this.g;
        }
        if (!this.l) {
            return 8;
        }
        this.h = this.m.e() + this.h;
        return 8;
    }

    @Override // d.p.r0.s0
    public void a(StringBuffer stringBuffer) {
        d.p.i.a(this.f3128e, this.f3129f, stringBuffer);
        stringBuffer.append(':');
        d.p.i.a(this.g, this.h, stringBuffer);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // d.p.r0.s0
    public byte[] a() {
        byte[] bArr = new byte[9];
        bArr[0] = i1.o.a();
        d.p.d0.b(this.f3129f, bArr, 1);
        d.p.d0.b(this.h, bArr, 3);
        d.p.d0.b(this.f3128e, bArr, 5);
        d.p.d0.b(this.g, bArr, 7);
        return bArr;
    }
}
