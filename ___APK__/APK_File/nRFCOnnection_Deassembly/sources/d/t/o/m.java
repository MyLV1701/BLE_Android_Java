package d.t.o;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class m extends d.p.n0 {

    /* renamed from: c, reason: collision with root package name */
    private byte[] f3334c;

    /* renamed from: d, reason: collision with root package name */
    private int f3335d;

    /* renamed from: e, reason: collision with root package name */
    private d.p.p0 f3336e;

    /* renamed from: f, reason: collision with root package name */
    private int f3337f;
    private int g;
    private boolean h;
    private int i;
    private boolean j;

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(d.p.c0 c0Var) {
        this.f3337f = c0Var.a(this.f3337f);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(int i) {
        this.g = i;
    }

    public d.p.p0 d() {
        return this.f3336e;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof m)) {
            return false;
        }
        m mVar = (m) obj;
        if (this.f3335d != mVar.f3335d || this.f3337f != mVar.f3337f || this.g != mVar.g || this.h != mVar.h || this.i != mVar.i || this.j != mVar.j) {
            return false;
        }
        if ((this.f3336e != null || mVar.f3336e == null) && (this.f3336e == null || mVar.f3336e != null)) {
            return this.f3336e.equals(mVar.f3336e);
        }
        return false;
    }

    public int f() {
        return this.f3335d;
    }

    public int hashCode() {
        int i = ((((((10823 + this.f3335d) * 79) + this.f3337f) * 79) + this.g) * 79) + (this.h ? 1 : 0);
        d.p.p0 p0Var = this.f3336e;
        return p0Var != null ? i ^ p0Var.hashCode() : i;
    }

    @Override // d.p.n0
    public byte[] u() {
        this.f3334c = new byte[12];
        d.p.d0.b(this.f3335d, this.f3334c, 0);
        d.p.d0.b(this.f3335d, this.f3334c, 2);
        d.p.d0.b(this.g, this.f3334c, 4);
        d.p.d0.b(this.f3337f, this.f3334c, 6);
        int i = (this.i << 8) | 6;
        if (this.h) {
            i |= 1;
        }
        this.i = (i & 1792) / 256;
        if (this.j) {
            i |= 4096;
        }
        d.p.d0.b(i, this.f3334c, 8);
        return this.f3334c;
    }
}
