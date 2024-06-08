package d.t;

import d.p.t;

/* loaded from: classes.dex */
public final class c {

    /* renamed from: a, reason: collision with root package name */
    public static final t f3207a = new a(14, "M/d/yy");

    /* renamed from: b, reason: collision with root package name */
    public static final t f3208b = f3207a;

    /* loaded from: classes.dex */
    private static class a implements t {

        /* renamed from: a, reason: collision with root package name */
        private int f3209a;

        public a(int i, String str) {
            this.f3209a = i;
        }

        @Override // d.p.t
        public void a(int i) {
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            return (obj instanceof a) && this.f3209a == ((a) obj).f3209a;
        }

        @Override // d.p.t
        public boolean g() {
            return true;
        }

        @Override // d.p.t
        public int h() {
            return this.f3209a;
        }

        public int hashCode() {
            return this.f3209a;
        }

        @Override // d.p.t
        public boolean i() {
            return true;
        }
    }

    static {
        new a(15, "d-MMM-yy");
        new a(16, "d-MMM");
        new a(17, "MMM-yy");
        new a(18, "h:mm a");
        new a(19, "h:mm:ss a");
        new a(20, "H:mm");
        new a(21, "H:mm:ss");
        new a(22, "M/d/yy H:mm");
        new a(45, "mm:ss");
        new a(46, "H:mm:ss");
        new a(47, "H:mm:ss");
    }
}
