package d.p.q0;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class g0 extends s {

    /* renamed from: c, reason: collision with root package name */
    private byte[] f2980c;

    /* renamed from: d, reason: collision with root package name */
    private int f2981d;

    /* renamed from: e, reason: collision with root package name */
    private int f2982e;

    /* renamed from: f, reason: collision with root package name */
    private int f2983f;

    static {
        d.q.c.b(g0.class);
    }

    public g0(v vVar) {
        super(vVar);
        this.f2981d = d();
        byte[] a2 = a();
        this.f2982e = d.p.d0.a(a2[0], a2[1], a2[2], a2[3]);
        this.f2983f = d.p.d0.a(a2[4], a2[5], a2[6], a2[7]);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // d.p.q0.s, d.p.q0.u
    public byte[] b() {
        this.f2980c = new byte[8];
        d.p.d0.a(this.f2982e, this.f2980c, 0);
        d.p.d0.a(this.f2983f, this.f2980c, 4);
        return a(this.f2980c);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int i() {
        return this.f2982e;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int j() {
        return this.f2981d;
    }

    public g0(e0 e0Var, int i, int i2) {
        super(w.m);
        b(2);
        this.f2981d = e0Var.a();
        this.f2982e = i;
        this.f2983f = i2;
        a(this.f2981d);
    }
}
