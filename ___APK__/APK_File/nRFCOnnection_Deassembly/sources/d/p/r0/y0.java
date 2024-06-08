package d.p.r0;

/* loaded from: classes.dex */
class y0 extends f implements t0 {
    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // d.p.r0.f, d.p.r0.s0
    public byte[] a() {
        e();
        i();
        byte[] a2 = super.a();
        byte[] bArr = new byte[a2.length + 3];
        System.arraycopy(a2, 0, bArr, 3, a2.length);
        bArr[0] = i1.M.a();
        d.p.d0.b(a2.length, bArr, 1);
        return bArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // d.p.r0.p0
    public int h() {
        return 1;
    }

    @Override // d.p.r0.f
    public String j() {
        return ":";
    }

    @Override // d.p.r0.f
    i1 k() {
        return i1.I;
    }
}
