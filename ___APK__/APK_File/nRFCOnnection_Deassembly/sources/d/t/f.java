package d.t;

import d.p.t;

/* loaded from: classes.dex */
public final class f {

    /* renamed from: a, reason: collision with root package name */
    public static final t f3210a = new a(0, "#");

    /* renamed from: b, reason: collision with root package name */
    public static final t f3211b;

    /* renamed from: c, reason: collision with root package name */
    public static final t f3212c;

    /* renamed from: d, reason: collision with root package name */
    public static final t f3213d;

    /* renamed from: e, reason: collision with root package name */
    public static final t f3214e;

    /* renamed from: f, reason: collision with root package name */
    public static final t f3215f;
    public static final t g;

    /* loaded from: classes.dex */
    private static class a implements t, d.r.g {

        /* renamed from: a, reason: collision with root package name */
        private int f3216a;

        public a(int i, String str) {
            this.f3216a = i;
        }

        @Override // d.p.t
        public void a(int i) {
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            return (obj instanceof a) && this.f3216a == ((a) obj).f3216a;
        }

        @Override // d.p.t
        public boolean g() {
            return true;
        }

        @Override // d.p.t
        public int h() {
            return this.f3216a;
        }

        public int hashCode() {
            return this.f3216a;
        }

        @Override // d.p.t
        public boolean i() {
            return true;
        }
    }

    static {
        new a(1, "0");
        f3211b = new a(2, "0.00");
        new a(3, "#,##0");
        new a(4, "#,##0.00");
        new a(5, "$#,##0;($#,##0)");
        new a(6, "$#,##0;($#,##0)");
        new a(7, "$#,##0;($#,##0)");
        new a(8, "$#,##0;($#,##0)");
        f3212c = new a(9, "0%");
        new a(10, "0.00%");
        new a(11, "0.00E00");
        new a(12, "?/?");
        new a(13, "??/??");
        new a(37, "#,##0;(#,##0)");
        new a(38, "#,##0;(#,##0)");
        new a(39, "#,##0.00;(#,##0.00)");
        new a(40, "#,##0.00;(#,##0.00)");
        f3213d = new a(41, "#,##0;(#,##0)");
        f3214e = new a(42, "#,##0;(#,##0)");
        f3215f = new a(43, "#,##0.00;(#,##0.00)");
        g = new a(44, "#,##0.00;(#,##0.00)");
        new a(46, "#,##0.00;(#,##0.00)");
        new a(48, "##0.0E0");
        new a(49, "@");
    }
}
