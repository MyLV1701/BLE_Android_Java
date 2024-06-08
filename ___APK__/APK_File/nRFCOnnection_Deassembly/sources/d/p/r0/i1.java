package d.p.r0;

import java.util.HashMap;

/* loaded from: classes.dex */
class i1 {
    public static final i1 I;
    public static final i1 J;
    public static final i1 K;
    public static final i1 L;
    public static final i1 M;
    public static final i1 N;

    /* renamed from: a, reason: collision with root package name */
    public final int[] f3080a;

    /* renamed from: b, reason: collision with root package name */
    private static HashMap f3075b = new HashMap(20);

    /* renamed from: c, reason: collision with root package name */
    public static final i1 f3076c = new i1(68, 36, 100);

    /* renamed from: d, reason: collision with root package name */
    public static final i1 f3077d = new i1(90, 58, 122);

    /* renamed from: e, reason: collision with root package name */
    public static final i1 f3078e = new i1(22);

    /* renamed from: f, reason: collision with root package name */
    public static final i1 f3079f = new i1(23);
    public static final i1 g = new i1(28);
    public static final i1 h = new i1(29);
    public static final i1 i = new i1(30);
    public static final i1 j = new i1(31);
    public static final i1 k = new i1(42, 74, 106);
    public static final i1 l = new i1(44, 76, 108);
    public static final i1 m = new i1(45, 77, 109);
    public static final i1 n = new i1(38, 70, 102);
    public static final i1 o = new i1(37, 101, 69);
    public static final i1 p = new i1(35, 67, 99);
    public static final i1 q = new i1(57, 89);
    public static final i1 r = new i1(59, 91);
    public static final i1 s = new i1(18);
    public static final i1 t = new i1(19);
    public static final i1 u = new i1(20);
    public static final i1 v = new i1(21);
    public static final i1 w = new i1(3);
    public static final i1 x = new i1(4);
    public static final i1 y = new i1(5);
    public static final i1 z = new i1(6);
    public static final i1 A = new i1(7);
    public static final i1 B = new i1(8);
    public static final i1 C = new i1(9);
    public static final i1 D = new i1(10);
    public static final i1 E = new i1(11);
    public static final i1 F = new i1(12);
    public static final i1 G = new i1(13);
    public static final i1 H = new i1(14);

    static {
        new i1(16);
        I = new i1(17);
        J = new i1(65, 33, 97);
        K = new i1(66, 34, 98);
        L = new i1(25);
        M = new i1(41, 73, 105);
        N = new i1(65535);
    }

    private i1(int i2) {
        this.f3080a = new int[]{i2};
        f3075b.put(new Integer(i2), this);
    }

    public byte a() {
        return (byte) this.f3080a[0];
    }

    public byte b() {
        int[] iArr = this.f3080a;
        return (byte) (iArr.length > 0 ? iArr[1] : iArr[0]);
    }

    public byte c() {
        return (byte) this.f3080a[0];
    }

    public byte d() {
        int[] iArr = this.f3080a;
        return (byte) (iArr.length > 0 ? iArr[1] : iArr[0]);
    }

    public static i1 a(int i2) {
        i1 i1Var = (i1) f3075b.get(new Integer(i2));
        return i1Var != null ? i1Var : N;
    }

    private i1(int i2, int i3) {
        this.f3080a = new int[]{i2, i3};
        f3075b.put(new Integer(i2), this);
        f3075b.put(new Integer(i3), this);
    }

    private i1(int i2, int i3, int i4) {
        this.f3080a = new int[]{i2, i3, i4};
        f3075b.put(new Integer(i2), this);
        f3075b.put(new Integer(i3), this);
        f3075b.put(new Integer(i4), this);
    }
}
