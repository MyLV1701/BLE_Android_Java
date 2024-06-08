package d.t.o;

/* loaded from: classes.dex */
public class t0 extends d.t.n {

    /* renamed from: b, reason: collision with root package name */
    static a f3382b = new a("Attempt to modify a referenced format");

    /* renamed from: c, reason: collision with root package name */
    static a f3383c = new a("Cell has already been added to a worksheet");

    /* renamed from: d, reason: collision with root package name */
    static a f3384d = new a("The maximum number of rows permitted on a worksheet been exceeded");

    /* renamed from: e, reason: collision with root package name */
    static a f3385e;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a, reason: collision with root package name */
        public String f3386a;

        a(String str) {
            this.f3386a = str;
        }
    }

    static {
        new a("The maximum number of columns permitted on a worksheet has been exceeded");
        f3385e = new a("Error encounted when copying additional property sets");
    }

    public t0(a aVar) {
        super(aVar.f3386a);
    }
}
