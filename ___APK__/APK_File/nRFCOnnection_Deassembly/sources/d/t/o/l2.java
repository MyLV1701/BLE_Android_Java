package d.t.o;

/* loaded from: classes.dex */
class l2 extends d.p.n0 {

    /* renamed from: c, reason: collision with root package name */
    private byte[] f3332c;

    /* renamed from: d, reason: collision with root package name */
    private boolean f3333d;

    public l2() {
        super(d.p.k0.t0);
        this.f3333d = true;
        this.f3332c = new byte[2];
        if (this.f3333d) {
            this.f3332c[0] = 1;
        }
    }

    @Override // d.p.n0
    public byte[] u() {
        return this.f3332c;
    }
}
