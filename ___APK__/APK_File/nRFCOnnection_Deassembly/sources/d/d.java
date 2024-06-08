package d;

/* loaded from: classes.dex */
public final class d {

    /* renamed from: b, reason: collision with root package name */
    public static final d f2808b = new d("Empty");

    /* renamed from: c, reason: collision with root package name */
    public static final d f2809c = new d("Label");

    /* renamed from: d, reason: collision with root package name */
    public static final d f2810d = new d("Number");

    /* renamed from: e, reason: collision with root package name */
    public static final d f2811e;

    /* renamed from: f, reason: collision with root package name */
    public static final d f2812f;

    /* renamed from: a, reason: collision with root package name */
    private String f2813a;

    static {
        new d("Boolean");
        new d("Error");
        new d("Numerical Formula");
        new d("Date Formula");
        f2811e = new d("String Formula");
        new d("Boolean Formula");
        new d("Formula Error");
        f2812f = new d("Date");
    }

    private d(String str) {
        this.f2813a = str;
    }

    public String toString() {
        return this.f2813a;
    }
}
