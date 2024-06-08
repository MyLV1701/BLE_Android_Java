package d.r;

/* loaded from: classes.dex */
public class l {

    /* renamed from: b, reason: collision with root package name */
    private static l[] f3180b = new l[0];

    /* renamed from: c, reason: collision with root package name */
    public static final l f3181c = new l(0, "None");

    /* renamed from: a, reason: collision with root package name */
    private int f3182a;

    static {
        new l(1, "Solid");
        new l(2, "Gray 50%");
        new l(3, "Gray 75%");
        new l(4, "Gray 25%");
        new l(5, "Pattern 1");
        new l(6, "Pattern 2");
        new l(7, "Pattern 3");
        new l(8, "Pattern 4");
        new l(9, "Pattern 5");
        new l(10, "Pattern 6");
        new l(11, "Pattern 7");
        new l(12, "Pattern 8");
        new l(13, "Pattern 9");
        new l(14, "Pattern 10");
        new l(15, "Pattern 11");
        new l(16, "Pattern 12");
        new l(17, "Pattern 13");
        new l(18, "Pattern 14");
    }

    protected l(int i, String str) {
        this.f3182a = i;
        l[] lVarArr = f3180b;
        f3180b = new l[lVarArr.length + 1];
        System.arraycopy(lVarArr, 0, f3180b, 0, lVarArr.length);
        f3180b[lVarArr.length] = this;
    }

    public int a() {
        return this.f3182a;
    }

    public static l a(int i) {
        int i2 = 0;
        while (true) {
            l[] lVarArr = f3180b;
            if (i2 < lVarArr.length) {
                if (lVarArr[i2].a() == i) {
                    return f3180b[i2];
                }
                i2++;
            } else {
                return f3181c;
            }
        }
    }
}
