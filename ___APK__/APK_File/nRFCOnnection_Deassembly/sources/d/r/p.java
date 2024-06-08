package d.r;

/* loaded from: classes.dex */
public class p {

    /* renamed from: b, reason: collision with root package name */
    private static p[] f3190b = new p[0];

    /* renamed from: c, reason: collision with root package name */
    public static p f3191c;

    /* renamed from: a, reason: collision with root package name */
    private int f3192a;

    static {
        new p(0, "top");
        new p(1, "centre");
        f3191c = new p(2, "bottom");
        new p(3, "Justify");
    }

    protected p(int i, String str) {
        this.f3192a = i;
        p[] pVarArr = f3190b;
        f3190b = new p[pVarArr.length + 1];
        System.arraycopy(pVarArr, 0, f3190b, 0, pVarArr.length);
        f3190b[pVarArr.length] = this;
    }

    public int a() {
        return this.f3192a;
    }

    public static p a(int i) {
        int i2 = 0;
        while (true) {
            p[] pVarArr = f3190b;
            if (i2 < pVarArr.length) {
                if (pVarArr[i2].a() == i) {
                    return f3190b[i2];
                }
                i2++;
            } else {
                return f3191c;
            }
        }
    }
}
