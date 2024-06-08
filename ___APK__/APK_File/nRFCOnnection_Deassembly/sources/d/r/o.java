package d.r;

/* loaded from: classes.dex */
public final class o {

    /* renamed from: b, reason: collision with root package name */
    private static o[] f3186b = new o[0];

    /* renamed from: c, reason: collision with root package name */
    public static final o f3187c = new o(0, "none");

    /* renamed from: d, reason: collision with root package name */
    public static final o f3188d = new o(1, "single");

    /* renamed from: a, reason: collision with root package name */
    private int f3189a;

    static {
        new o(2, "double");
        new o(33, "single accounting");
        new o(34, "double accounting");
    }

    protected o(int i, String str) {
        this.f3189a = i;
        o[] oVarArr = f3186b;
        f3186b = new o[oVarArr.length + 1];
        System.arraycopy(oVarArr, 0, f3186b, 0, oVarArr.length);
        f3186b[oVarArr.length] = this;
    }

    public int a() {
        return this.f3189a;
    }

    public static o a(int i) {
        int i2 = 0;
        while (true) {
            o[] oVarArr = f3186b;
            if (i2 < oVarArr.length) {
                if (oVarArr[i2].a() == i) {
                    return f3186b[i2];
                }
                i2++;
            } else {
                return f3187c;
            }
        }
    }
}
