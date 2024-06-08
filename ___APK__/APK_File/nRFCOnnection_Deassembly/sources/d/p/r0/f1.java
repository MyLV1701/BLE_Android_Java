package d.p.r0;

import no.nordicsemi.android.mcp.ble.parser.gap.FlagsParser;

/* loaded from: classes.dex */
class f1 extends o0 implements t0 {

    /* renamed from: e, reason: collision with root package name */
    private String f3067e;

    /* renamed from: f, reason: collision with root package name */
    private d.o f3068f;

    static {
        d.q.c.b(f1.class);
    }

    public f1(d.o oVar) {
        this.f3068f = oVar;
    }

    public int a(byte[] bArr, int i) {
        int i2 = bArr[i] & FlagsParser.UNKNOWN_FLAGS;
        if ((bArr[i + 1] & 1) == 0) {
            this.f3067e = d.p.j0.a(bArr, i2, i + 2, this.f3068f);
        } else {
            this.f3067e = d.p.j0.a(bArr, i2, i + 2);
            i2 *= 2;
        }
        return i2 + 2;
    }

    public f1(String str) {
        this.f3067e = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // d.p.r0.s0
    public byte[] a() {
        byte[] bArr = new byte[(this.f3067e.length() * 2) + 3];
        bArr[0] = i1.f3079f.a();
        bArr[1] = (byte) this.f3067e.length();
        bArr[2] = 1;
        d.p.j0.b(this.f3067e, bArr, 3);
        return bArr;
    }

    @Override // d.p.r0.s0
    public void a(StringBuffer stringBuffer) {
        stringBuffer.append("\"");
        stringBuffer.append(this.f3067e);
        stringBuffer.append("\"");
    }
}
