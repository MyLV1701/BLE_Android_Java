package d.t;

import d.r.o;
import d.t.o.s2;

/* loaded from: classes.dex */
public class j extends s2 {
    public static final b o = new b("Arial");
    public static final a p;

    /* loaded from: classes.dex */
    static class a {

        /* renamed from: a, reason: collision with root package name */
        public int f3217a;

        a(int i) {
            this.f3217a = i;
        }
    }

    /* loaded from: classes.dex */
    public static class b {

        /* renamed from: a, reason: collision with root package name */
        String f3218a;

        b(String str) {
            this.f3218a = str;
        }
    }

    static {
        new b("Times New Roman");
        new b("Courier New");
        new b("Tahoma");
        p = new a(400);
        new a(700);
    }

    public j(b bVar) {
        this(bVar, 10, p, false, o.f3187c, d.r.e.f3168d, d.r.n.f3184c);
    }

    @Override // d.p.x, d.r.f
    public boolean k() {
        return super.k();
    }

    public j(d.r.f fVar) {
        super(fVar);
    }

    public j(b bVar, int i, a aVar, boolean z, o oVar, d.r.e eVar) {
        this(bVar, i, aVar, z, oVar, eVar, d.r.n.f3184c);
    }

    public j(b bVar, int i, a aVar, boolean z, o oVar, d.r.e eVar, d.r.n nVar) {
        super(bVar.f3218a, i, aVar.f3217a, z, oVar.a(), eVar.a(), nVar.a());
    }
}
