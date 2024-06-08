package d.r;

/* loaded from: classes.dex */
public class c {

    /* renamed from: c, reason: collision with root package name */
    private static c[] f3162c = new c[0];

    /* renamed from: d, reason: collision with root package name */
    public static final c f3163d = new c(0, "none");

    /* renamed from: a, reason: collision with root package name */
    private int f3164a;

    /* renamed from: b, reason: collision with root package name */
    private String f3165b;

    static {
        new c(1, "thin");
        new c(2, "medium");
        new c(3, "dashed");
        new c(4, "dotted");
        new c(5, "thick");
        new c(6, "double");
        new c(7, "hair");
        new c(8, "medium dashed");
        new c(9, "dash dot");
        new c(10, "medium dash dot");
        new c(11, "Dash dot dot");
        new c(12, "Medium dash dot dot");
        new c(13, "Slanted dash dot");
    }

    protected c(int i, String str) {
        this.f3164a = i;
        this.f3165b = str;
        c[] cVarArr = f3162c;
        f3162c = new c[cVarArr.length + 1];
        System.arraycopy(cVarArr, 0, f3162c, 0, cVarArr.length);
        f3162c[cVarArr.length] = this;
    }

    public String a() {
        return this.f3165b;
    }

    public int b() {
        return this.f3164a;
    }

    public static c a(int i) {
        int i2 = 0;
        while (true) {
            c[] cVarArr = f3162c;
            if (i2 < cVarArr.length) {
                if (cVarArr[i2].b() == i) {
                    return f3162c[i2];
                }
                i2++;
            } else {
                return f3163d;
            }
        }
    }
}
