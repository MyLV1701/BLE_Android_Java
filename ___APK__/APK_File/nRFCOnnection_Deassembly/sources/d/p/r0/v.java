package d.p.r0;

/* loaded from: classes.dex */
public class v extends d.h {

    /* renamed from: b, reason: collision with root package name */
    static final a f3113b = new a("Unrecognized token");

    /* renamed from: c, reason: collision with root package name */
    static final a f3114c = new a("Unrecognized function");

    /* renamed from: d, reason: collision with root package name */
    public static final a f3115d = new a("Only biff8 formulas are supported");

    /* renamed from: e, reason: collision with root package name */
    static final a f3116e = new a("Lexical error:  ");

    /* renamed from: f, reason: collision with root package name */
    static final a f3117f = new a("Incorrect arguments supplied to function");
    static final a g = new a("Could not find sheet");
    static final a h = new a("Could not find named cell");

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a, reason: collision with root package name */
        private String f3118a;

        a(String str) {
            this.f3118a = str;
        }
    }

    public v(a aVar) {
        super(aVar.f3118a);
    }

    public v(a aVar, int i) {
        super(aVar.f3118a + " " + i);
    }

    public v(a aVar, String str) {
        super(aVar.f3118a + " " + str);
    }
}
