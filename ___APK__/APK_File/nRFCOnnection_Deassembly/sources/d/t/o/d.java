package d.t.o;

/* loaded from: classes.dex */
class d extends d.p.n0 {

    /* renamed from: c, reason: collision with root package name */
    private boolean f3260c;

    /* renamed from: d, reason: collision with root package name */
    private byte[] f3261d;

    public d(boolean z) {
        super(d.p.k0.h0);
        this.f3260c = z;
        this.f3261d = new byte[2];
        if (this.f3260c) {
            return;
        }
        d.p.d0.b(1, this.f3261d, 0);
    }

    @Override // d.p.n0
    public byte[] u() {
        return this.f3261d;
    }
}
