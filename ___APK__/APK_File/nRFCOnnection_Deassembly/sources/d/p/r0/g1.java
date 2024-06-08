package d.p.r0;

/* loaded from: classes.dex */
abstract class g1 extends o0 implements t0 {

    /* renamed from: e, reason: collision with root package name */
    private int f3070e;

    /* renamed from: f, reason: collision with root package name */
    private s0[] f3071f;

    public int a(byte[] bArr, int i) {
        this.f3070e = d.p.d0.a(bArr[i], bArr[i + 1]);
        return 2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // d.p.r0.s0
    public byte[] a() {
        return null;
    }

    public int g() {
        return this.f3070e;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public s0[] h() {
        return this.f3071f;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void a(int i) {
        this.f3070e = i;
    }

    public void a(s0[] s0VarArr) {
        this.f3071f = s0VarArr;
    }
}
