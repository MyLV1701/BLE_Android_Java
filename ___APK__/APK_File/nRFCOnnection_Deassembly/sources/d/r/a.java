package d.r;

/* loaded from: classes.dex */
public class a {

    /* renamed from: b, reason: collision with root package name */
    private static a[] f3153b = new a[0];

    /* renamed from: c, reason: collision with root package name */
    public static a f3154c = new a(0, "general");

    /* renamed from: a, reason: collision with root package name */
    private int f3155a;

    static {
        new a(1, "left");
        new a(2, "centre");
        new a(3, "right");
        new a(4, "fill");
        new a(5, "justify");
    }

    protected a(int i, String str) {
        this.f3155a = i;
        a[] aVarArr = f3153b;
        f3153b = new a[aVarArr.length + 1];
        System.arraycopy(aVarArr, 0, f3153b, 0, aVarArr.length);
        f3153b[aVarArr.length] = this;
    }

    public int a() {
        return this.f3155a;
    }

    public static a a(int i) {
        int i2 = 0;
        while (true) {
            a[] aVarArr = f3153b;
            if (i2 < aVarArr.length) {
                if (aVarArr[i2].a() == i) {
                    return f3153b[i2];
                }
                i2++;
            } else {
                return f3154c;
            }
        }
    }
}
