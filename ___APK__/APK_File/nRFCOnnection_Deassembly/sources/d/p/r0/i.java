package d.p.r0;

import no.nordicsemi.android.dfu.DfuBaseService;

/* loaded from: classes.dex */
class i extends o0 implements t0 {

    /* renamed from: e, reason: collision with root package name */
    private boolean f3073e;

    /* renamed from: f, reason: collision with root package name */
    private boolean f3074f;
    private int g;
    private int h;
    private int i;
    private t j;

    static {
        d.q.c.b(i.class);
    }

    public i(d.a aVar, t tVar) {
        this.j = tVar;
    }

    public int a(byte[] bArr, int i) {
        this.i = d.p.d0.a(bArr[i], bArr[i + 1]);
        this.h = d.p.d0.a(bArr[i + 2], bArr[i + 3]);
        int a2 = d.p.d0.a(bArr[i + 4], bArr[i + 5]);
        this.g = a2 & 255;
        this.f3073e = (a2 & DfuBaseService.ERROR_CONNECTION_MASK) != 0;
        this.f3074f = (a2 & DfuBaseService.ERROR_CONNECTION_STATE_MASK) != 0;
        return 6;
    }

    public i(String str, t tVar) {
        this.j = tVar;
        this.f3073e = true;
        this.f3074f = true;
        int indexOf = str.indexOf(33);
        String substring = str.substring(indexOf + 1);
        this.g = d.p.i.a(substring);
        this.h = d.p.i.c(substring);
        String substring2 = str.substring(0, indexOf);
        if (substring2.charAt(0) == '\'' && substring2.charAt(substring2.length() - 1) == '\'') {
            substring2 = substring2.substring(1, substring2.length() - 1);
        }
        this.i = tVar.b(substring2);
        if (this.i < 0) {
            throw new v(v.g, substring2);
        }
    }

    @Override // d.p.r0.s0
    public void a(StringBuffer stringBuffer) {
        d.p.i.a(this.i, this.g, !this.f3073e, this.h, !this.f3074f, this.j, stringBuffer);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // d.p.r0.s0
    public byte[] a() {
        byte[] bArr = new byte[7];
        bArr[0] = i1.f3077d.a();
        d.p.d0.b(this.i, bArr, 1);
        d.p.d0.b(this.h, bArr, 3);
        int i = this.g;
        if (this.f3074f) {
            i |= DfuBaseService.ERROR_CONNECTION_STATE_MASK;
        }
        if (this.f3073e) {
            i |= DfuBaseService.ERROR_CONNECTION_MASK;
        }
        d.p.d0.b(i, bArr, 5);
        return bArr;
    }
}
