package d.r;

/* loaded from: classes.dex */
public final class n {

    /* renamed from: b, reason: collision with root package name */
    private static n[] f3183b = new n[0];

    /* renamed from: c, reason: collision with root package name */
    public static final n f3184c = new n(0, "normal");

    /* renamed from: a, reason: collision with root package name */
    private int f3185a;

    static {
        new n(1, "super");
        new n(2, "sub");
    }

    protected n(int i, String str) {
        this.f3185a = i;
        n[] nVarArr = f3183b;
        f3183b = new n[nVarArr.length + 1];
        System.arraycopy(nVarArr, 0, f3183b, 0, nVarArr.length);
        f3183b[nVarArr.length] = this;
    }

    public int a() {
        return this.f3185a;
    }

    public static n a(int i) {
        int i2 = 0;
        while (true) {
            n[] nVarArr = f3183b;
            if (i2 < nVarArr.length) {
                if (nVarArr[i2].a() == i) {
                    return f3183b[i2];
                }
                i2++;
            } else {
                return f3184c;
            }
        }
    }
}
