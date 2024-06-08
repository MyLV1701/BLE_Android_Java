package d.p.q0;

/* loaded from: classes.dex */
final class c {

    /* renamed from: b, reason: collision with root package name */
    private static c[] f2944b = new c[0];

    /* renamed from: c, reason: collision with root package name */
    public static final c f2945c;

    /* renamed from: d, reason: collision with root package name */
    public static final c f2946d;

    /* renamed from: a, reason: collision with root package name */
    private int f2947a;

    static {
        new c(0, "Error");
        f2945c = new c(1, "Unknown");
        new c(2, "EMF");
        new c(3, "WMF");
        new c(4, "PICT");
        new c(5, "JPEG");
        f2946d = new c(6, "PNG");
        new c(7, "DIB");
        new c(32, "FIRST");
        new c(255, "LAST");
    }

    private c(int i, String str) {
        this.f2947a = i;
        c[] cVarArr = f2944b;
        c[] cVarArr2 = new c[cVarArr.length + 1];
        System.arraycopy(cVarArr, 0, cVarArr2, 0, cVarArr.length);
        cVarArr2[f2944b.length] = this;
        f2944b = cVarArr2;
    }

    public int a() {
        return this.f2947a;
    }

    public static c a(int i) {
        c cVar = f2945c;
        int i2 = 0;
        while (true) {
            c[] cVarArr = f2944b;
            if (i2 >= cVarArr.length) {
                return cVar;
            }
            if (cVarArr[i2].f2947a == i) {
                return cVarArr[i2];
            }
            i2++;
        }
    }
}
