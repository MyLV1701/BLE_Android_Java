package d.p.r0;

/* loaded from: classes.dex */
class g extends o0 implements t0 {

    /* renamed from: e, reason: collision with root package name */
    private boolean f3069e;

    public g() {
    }

    public int a(byte[] bArr, int i) {
        this.f3069e = bArr[i] == 1;
        return 1;
    }

    public g(String str) {
        this.f3069e = Boolean.valueOf(str).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // d.p.r0.s0
    public byte[] a() {
        byte[] bArr = new byte[2];
        bArr[0] = i1.h.a();
        bArr[1] = (byte) (this.f3069e ? 1 : 0);
        return bArr;
    }

    @Override // d.p.r0.s0
    public void a(StringBuffer stringBuffer) {
        stringBuffer.append(new Boolean(this.f3069e).toString());
    }
}
