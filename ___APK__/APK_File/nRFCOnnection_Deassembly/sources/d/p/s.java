package d.p;

/* loaded from: classes.dex */
public class s extends n0 {

    /* renamed from: c, reason: collision with root package name */
    private byte[] f3130c;

    /* renamed from: d, reason: collision with root package name */
    private o f3131d;

    /* renamed from: e, reason: collision with root package name */
    private l0 f3132e;

    /* renamed from: f, reason: collision with root package name */
    private d.p.r0.t f3133f;
    private d.o g;

    static {
        d.q.c.b(s.class);
    }

    public s(o oVar) {
        super(k0.I0);
        this.f3131d = oVar;
    }

    private void A() {
        if (this.f3131d == null) {
            this.f3131d = new o(this.f3130c, this.f3133f, this.f3132e, this.g);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(q qVar) {
    }

    @Override // d.p.n0
    public byte[] u() {
        o oVar = this.f3131d;
        if (oVar == null) {
            return this.f3130c;
        }
        return oVar.c();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public o v() {
        return this.f3131d;
    }

    public int w() {
        if (this.f3131d == null) {
            A();
        }
        return this.f3131d.d();
    }

    public int x() {
        if (this.f3131d == null) {
            A();
        }
        return this.f3131d.e();
    }

    public int y() {
        if (this.f3131d == null) {
            A();
        }
        return this.f3131d.f();
    }

    public int z() {
        if (this.f3131d == null) {
            A();
        }
        return this.f3131d.g();
    }
}
