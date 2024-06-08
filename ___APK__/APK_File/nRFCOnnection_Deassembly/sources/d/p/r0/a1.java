package d.p.r0;

import no.nordicsemi.android.dfu.DfuBaseService;

/* loaded from: classes.dex */
class a1 extends o0 implements t0 {

    /* renamed from: e, reason: collision with root package name */
    private boolean f3050e;

    /* renamed from: f, reason: collision with root package name */
    private boolean f3051f;
    private int g;
    private int h;
    private d.a i;

    static {
        d.q.c.b(a1.class);
    }

    public a1(d.a aVar) {
        this.i = aVar;
    }

    public int a(byte[] bArr, int i) {
        d.a aVar;
        d.a aVar2;
        this.h = d.p.d0.b(bArr[i], bArr[i + 1]);
        int a2 = d.p.d0.a(bArr[i + 2], bArr[i + 3]);
        this.g = (byte) (a2 & 255);
        this.f3050e = (a2 & DfuBaseService.ERROR_CONNECTION_MASK) != 0;
        this.f3051f = (a2 & DfuBaseService.ERROR_CONNECTION_STATE_MASK) != 0;
        if (this.f3050e && (aVar2 = this.i) != null) {
            this.g = aVar2.f() + this.g;
        }
        if (!this.f3051f || (aVar = this.i) == null) {
            return 4;
        }
        this.h = aVar.e() + this.h;
        return 4;
    }

    @Override // d.p.r0.s0
    public void a(StringBuffer stringBuffer) {
        d.p.i.a(this.g, this.h, stringBuffer);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // d.p.r0.s0
    public byte[] a() {
        byte[] bArr = new byte[5];
        bArr[0] = i1.f3076c.a();
        d.p.d0.b(this.h, bArr, 1);
        int i = this.g;
        if (this.f3050e) {
            i |= DfuBaseService.ERROR_CONNECTION_MASK;
        }
        if (this.f3051f) {
            i |= DfuBaseService.ERROR_CONNECTION_STATE_MASK;
        }
        d.p.d0.b(i, bArr, 3);
        return bArr;
    }
}
