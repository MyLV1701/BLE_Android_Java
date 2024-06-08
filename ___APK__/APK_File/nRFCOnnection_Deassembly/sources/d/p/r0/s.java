package d.p.r0;

/* loaded from: classes.dex */
class s extends o0 implements t0 {

    /* renamed from: e, reason: collision with root package name */
    private u f3103e;

    public s() {
    }

    public int a(byte[] bArr, int i) {
        this.f3103e = u.a(bArr[i]);
        return 1;
    }

    public s(String str) {
        this.f3103e = u.a(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // d.p.r0.s0
    public byte[] a() {
        return new byte[]{i1.g.a(), (byte) this.f3103e.a()};
    }

    @Override // d.p.r0.s0
    public void a(StringBuffer stringBuffer) {
        stringBuffer.append(this.f3103e.b());
    }
}
