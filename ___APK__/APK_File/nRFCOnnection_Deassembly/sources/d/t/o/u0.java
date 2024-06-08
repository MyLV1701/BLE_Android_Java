package d.t.o;

/* loaded from: classes.dex */
public abstract class u0 extends j {
    private String l;
    private c2 m;
    private int n;

    static {
        d.q.c.b(u0.class);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public u0(int i, int i2, String str) {
        super(d.p.k0.s, i, i2);
        this.l = str;
        if (this.l == null) {
            this.l = "";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // d.t.o.j
    public void a(d.p.a0 a0Var, c2 c2Var, v2 v2Var) {
        super.a(a0Var, c2Var, v2Var);
        this.m = c2Var;
        this.n = this.m.a(this.l);
        this.l = this.m.a(this.n);
    }

    @Override // d.a
    public String c() {
        return this.l;
    }

    @Override // d.a
    public d.d getType() {
        return d.d.f2809c;
    }

    @Override // d.t.o.j, d.p.n0
    public byte[] u() {
        byte[] u = super.u();
        byte[] bArr = new byte[u.length + 4];
        System.arraycopy(u, 0, bArr, 0, u.length);
        d.p.d0.a(this.n, bArr, u.length);
        return bArr;
    }
}
