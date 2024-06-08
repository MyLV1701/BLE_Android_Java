package d.p.q0;

/* loaded from: classes.dex */
final class w {

    /* renamed from: b, reason: collision with root package name */
    private static w[] f3039b = new w[0];

    /* renamed from: c, reason: collision with root package name */
    public static final w f3040c = new w(0);

    /* renamed from: d, reason: collision with root package name */
    public static final w f3041d = new w(61440);

    /* renamed from: e, reason: collision with root package name */
    public static final w f3042e = new w(61441);

    /* renamed from: f, reason: collision with root package name */
    public static final w f3043f = new w(61442);
    public static final w g = new w(61443);
    public static final w h = new w(61444);
    public static final w i = new w(61446);
    public static final w j = new w(61447);
    public static final w k = new w(61448);
    public static final w l = new w(61449);
    public static final w m = new w(61450);
    public static final w n = new w(61451);
    public static final w o = new w(61456);
    public static final w p = new w(61457);
    public static final w q = new w(61453);
    public static final w r = new w(61726);

    /* renamed from: a, reason: collision with root package name */
    private int f3044a;

    private w(int i2) {
        this.f3044a = i2;
        w[] wVarArr = f3039b;
        w[] wVarArr2 = new w[wVarArr.length + 1];
        System.arraycopy(wVarArr, 0, wVarArr2, 0, wVarArr.length);
        wVarArr2[f3039b.length] = this;
        f3039b = wVarArr2;
    }

    public int a() {
        return this.f3044a;
    }

    public static w a(int i2) {
        w wVar = f3040c;
        int i3 = 0;
        while (true) {
            w[] wVarArr = f3039b;
            if (i3 >= wVarArr.length) {
                return wVar;
            }
            if (i2 == wVarArr[i3].f3044a) {
                return wVarArr[i3];
            }
            i3++;
        }
    }
}
