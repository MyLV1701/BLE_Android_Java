package d.p.r0;

/* loaded from: classes.dex */
public class u {

    /* renamed from: c, reason: collision with root package name */
    private static u[] f3108c = new u[0];

    /* renamed from: d, reason: collision with root package name */
    public static final u f3109d = new u(255, "?");

    /* renamed from: e, reason: collision with root package name */
    public static final u f3110e;

    /* renamed from: a, reason: collision with root package name */
    private int f3111a;

    /* renamed from: b, reason: collision with root package name */
    private String f3112b;

    static {
        new u(0, "#NULL!");
        new u(7, "#DIV/0!");
        new u(15, "#VALUE!");
        f3110e = new u(23, "#REF!");
        new u(29, "#NAME?");
        new u(36, "#NUM!");
        new u(42, "#N/A!");
    }

    u(int i, String str) {
        this.f3111a = i;
        this.f3112b = str;
        u[] uVarArr = f3108c;
        u[] uVarArr2 = new u[uVarArr.length + 1];
        System.arraycopy(uVarArr, 0, uVarArr2, 0, uVarArr.length);
        uVarArr2[f3108c.length] = this;
        f3108c = uVarArr2;
    }

    public int a() {
        return this.f3111a;
    }

    public String b() {
        return this.f3112b;
    }

    public static u a(int i) {
        int i2 = 0;
        u uVar = f3109d;
        boolean z = false;
        while (true) {
            u[] uVarArr = f3108c;
            if (i2 >= uVarArr.length || z) {
                break;
            }
            if (uVarArr[i2].f3111a == i) {
                uVar = uVarArr[i2];
                z = true;
            }
            i2++;
        }
        return uVar;
    }

    public static u a(String str) {
        u uVar = f3109d;
        if (str == null || str.length() == 0) {
            return uVar;
        }
        int i = 0;
        u uVar2 = uVar;
        boolean z = false;
        while (true) {
            u[] uVarArr = f3108c;
            if (i >= uVarArr.length || z) {
                break;
            }
            if (uVarArr[i].f3112b.equals(str)) {
                uVar2 = f3108c[i];
                z = true;
            }
            i++;
        }
        return uVar2;
    }
}
