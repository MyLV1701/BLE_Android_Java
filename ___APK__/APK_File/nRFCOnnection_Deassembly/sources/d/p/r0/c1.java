package d.p.r0;

/* loaded from: classes.dex */
class c1 extends e1 {

    /* renamed from: e, reason: collision with root package name */
    private x f3064e;

    /* renamed from: f, reason: collision with root package name */
    private String f3065f;

    static {
        d.q.c.b(c1.class);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public c1(String str) {
        this.f3065f = str.substring(0, str.length() - 1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public x a(d.o oVar) {
        if (this.f3064e == null) {
            this.f3064e = x.a(this.f3065f, oVar);
        }
        return this.f3064e;
    }
}
