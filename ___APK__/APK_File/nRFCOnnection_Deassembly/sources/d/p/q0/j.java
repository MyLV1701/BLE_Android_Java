package d.p.q0;

/* loaded from: classes.dex */
class j extends s {

    /* renamed from: c, reason: collision with root package name */
    private byte[] f2997c;

    /* renamed from: d, reason: collision with root package name */
    private int f2998d;

    /* renamed from: e, reason: collision with root package name */
    private int f2999e;

    /* renamed from: f, reason: collision with root package name */
    private int f3000f;

    public j(v vVar) {
        super(vVar);
        this.f2998d = d();
        byte[] a2 = a();
        this.f2999e = d.p.d0.a(a2[0], a2[1], a2[2], a2[3]);
        this.f3000f = d.p.d0.a(a2[4], a2[5], a2[6], a2[7]);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // d.p.q0.s, d.p.q0.u
    public byte[] b() {
        this.f2997c = new byte[8];
        d.p.d0.a(this.f2999e, this.f2997c, 0);
        d.p.d0.a(this.f3000f, this.f2997c, 4);
        return a(this.f2997c);
    }

    public j(int i) {
        super(w.k);
        this.f2998d = 1;
        this.f2999e = i + 1;
        this.f3000f = this.f2999e + 1024 + 1;
        a(this.f2998d);
    }
}
