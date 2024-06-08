package d.p;

/* loaded from: classes.dex */
public class n {

    /* renamed from: c, reason: collision with root package name */
    private static d.q.c f2895c = d.q.c.b(n.class);

    /* renamed from: d, reason: collision with root package name */
    private static n[] f2896d = new n[0];

    /* renamed from: e, reason: collision with root package name */
    public static final n f2897e = new n(1, "US", "USA");

    /* renamed from: f, reason: collision with root package name */
    public static final n f2898f;
    public static final n g;

    /* renamed from: a, reason: collision with root package name */
    private int f2899a;

    /* renamed from: b, reason: collision with root package name */
    private String f2900b;

    static {
        new n(2, "CA", "Canada");
        new n(30, "GR", "Greece");
        new n(31, "NE", "Netherlands");
        new n(32, "BE", "Belgium");
        new n(33, "FR", "France");
        new n(34, "ES", "Spain");
        new n(39, "IT", "Italy");
        new n(41, "CH", "Switzerland");
        f2898f = new n(44, "UK", "United Kingdowm");
        new n(45, "DK", "Denmark");
        new n(46, "SE", "Sweden");
        new n(47, "NO", "Norway");
        new n(49, "DE", "Germany");
        new n(63, "PH", "Philippines");
        new n(86, "CN", "China");
        new n(91, "IN", "India");
        g = new n(65535, "??", "Unknown");
    }

    private n(int i, String str, String str2) {
        this.f2899a = i;
        this.f2900b = str;
        n[] nVarArr = f2896d;
        n[] nVarArr2 = new n[nVarArr.length + 1];
        System.arraycopy(nVarArr, 0, nVarArr2, 0, nVarArr.length);
        nVarArr2[f2896d.length] = this;
        f2896d = nVarArr2;
    }

    public String a() {
        return this.f2900b;
    }

    public int b() {
        return this.f2899a;
    }

    public static n a(String str) {
        if (str != null && str.length() == 2) {
            n nVar = g;
            int i = 0;
            while (true) {
                n[] nVarArr = f2896d;
                if (i >= nVarArr.length || nVar != g) {
                    break;
                }
                if (nVarArr[i].f2900b.equals(str)) {
                    nVar = f2896d[i];
                }
                i++;
            }
            return nVar;
        }
        f2895c.b("Please specify two character ISO 3166 country code");
        return f2897e;
    }
}
