package d.p.r0;

/* loaded from: classes.dex */
public class w {

    /* renamed from: a, reason: collision with root package name */
    private u0 f3119a;

    static {
        d.q.c.b(w.class);
    }

    public w(byte[] bArr, d.a aVar, t tVar, d.p.l0 l0Var, d.o oVar, r0 r0Var) {
        if (tVar.a() != null && !tVar.a().u()) {
            throw new v(v.f3115d);
        }
        d.q.a.a(l0Var != null);
        this.f3119a = new j1(bArr, aVar, tVar, l0Var, oVar, r0Var);
    }

    public byte[] a() {
        return this.f3119a.a();
    }

    public String b() {
        return this.f3119a.c();
    }

    public void c() {
        this.f3119a.b();
    }

    public w(String str, t tVar, d.p.l0 l0Var, d.o oVar, r0 r0Var) {
        this.f3119a = new b1(str, tVar, l0Var, oVar, r0Var);
    }
}
