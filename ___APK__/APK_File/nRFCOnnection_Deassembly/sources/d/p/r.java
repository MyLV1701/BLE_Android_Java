package d.p;

/* loaded from: classes.dex */
public class r extends n0 {

    /* renamed from: c, reason: collision with root package name */
    private p f3048c;

    /* renamed from: d, reason: collision with root package name */
    private byte[] f3049d;

    static {
        d.q.c.b(r.class);
    }

    public r(p pVar) {
        super(k0.J0);
        this.f3048c = pVar;
    }

    @Override // d.p.n0
    public byte[] u() {
        p pVar = this.f3048c;
        if (pVar == null) {
            return this.f3049d;
        }
        return pVar.c();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void v() {
        if (this.f3048c == null) {
            this.f3048c = new p(this.f3049d);
        }
        this.f3048c.a();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void w() {
        if (this.f3048c == null) {
            this.f3048c = new p(this.f3049d);
        }
        this.f3048c.b();
    }

    public boolean x() {
        p pVar = this.f3048c;
        return pVar == null || pVar.d() > 0;
    }
}
