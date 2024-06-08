package d.p.q0;

/* loaded from: classes.dex */
final class e0 {

    /* renamed from: b, reason: collision with root package name */
    private static e0[] f2969b = new e0[0];

    /* renamed from: c, reason: collision with root package name */
    public static final e0 f2970c = new e0(0);

    /* renamed from: d, reason: collision with root package name */
    public static final e0 f2971d = new e0(75);

    /* renamed from: e, reason: collision with root package name */
    public static final e0 f2972e = new e0(201);

    /* renamed from: f, reason: collision with root package name */
    public static final e0 f2973f = new e0(202);
    public static final e0 g = new e0(-1);

    /* renamed from: a, reason: collision with root package name */
    private int f2974a;

    e0(int i) {
        this.f2974a = i;
        e0[] e0VarArr = f2969b;
        f2969b = new e0[e0VarArr.length + 1];
        System.arraycopy(e0VarArr, 0, f2969b, 0, e0VarArr.length);
        f2969b[e0VarArr.length] = this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static e0 a(int i) {
        int i2 = 0;
        e0 e0Var = g;
        boolean z = false;
        while (true) {
            e0[] e0VarArr = f2969b;
            if (i2 >= e0VarArr.length || z) {
                break;
            }
            if (e0VarArr[i2].f2974a == i) {
                e0Var = e0VarArr[i2];
                z = true;
            }
            i2++;
        }
        return e0Var;
    }

    public int a() {
        return this.f2974a;
    }
}
