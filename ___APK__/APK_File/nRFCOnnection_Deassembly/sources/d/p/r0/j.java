package d.p.r0;

import no.nordicsemi.android.dfu.DfuBaseService;

/* loaded from: classes.dex */
class j extends o0 implements t0 {

    /* renamed from: e, reason: collision with root package name */
    private boolean f3081e;

    /* renamed from: f, reason: collision with root package name */
    private boolean f3082f;
    private int g;
    private int h;

    static {
        d.q.c.b(j.class);
    }

    public j(d.a aVar) {
    }

    public int a(byte[] bArr, int i) {
        this.h = d.p.d0.a(bArr[i], bArr[i + 1]);
        int a2 = d.p.d0.a(bArr[i + 2], bArr[i + 3]);
        this.g = a2 & 255;
        this.f3081e = (a2 & DfuBaseService.ERROR_CONNECTION_MASK) != 0;
        this.f3082f = (a2 & DfuBaseService.ERROR_CONNECTION_STATE_MASK) != 0;
        return 4;
    }

    public j() {
    }

    public j(String str) {
        this.g = d.p.i.a(str);
        this.h = d.p.i.c(str);
        this.f3081e = d.p.i.d(str);
        this.f3082f = d.p.i.e(str);
    }

    @Override // d.p.r0.s0
    public void a(StringBuffer stringBuffer) {
        d.p.i.a(this.g, !this.f3081e, this.h, !this.f3082f, stringBuffer);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // d.p.r0.s0
    public byte[] a() {
        byte[] bArr = new byte[5];
        bArr[0] = !f() ? i1.f3076c.a() : i1.f3076c.b();
        d.p.d0.b(this.h, bArr, 1);
        int i = this.g;
        if (this.f3082f) {
            i |= DfuBaseService.ERROR_CONNECTION_STATE_MASK;
        }
        if (this.f3081e) {
            i |= DfuBaseService.ERROR_CONNECTION_MASK;
        }
        d.p.d0.b(i, bArr, 3);
        return bArr;
    }
}
