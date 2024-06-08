package d.p.r0;

/* loaded from: classes.dex */
class e0 extends g1 {
    @Override // d.p.r0.s0
    public void a(StringBuffer stringBuffer) {
        s0[] h = h();
        if (h.length == 1) {
            h[0].a(stringBuffer);
        } else if (h.length == 2) {
            h[1].a(stringBuffer);
            stringBuffer.append(':');
            h[0].a(stringBuffer);
        }
    }

    @Override // d.p.r0.g1
    public int a(byte[] bArr, int i) {
        a(d.p.d0.a(bArr[i + 4], bArr[i + 5]));
        return 6;
    }
}
