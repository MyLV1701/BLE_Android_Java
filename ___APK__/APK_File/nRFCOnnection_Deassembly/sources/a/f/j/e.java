package a.f.j;

import java.util.Locale;

/* loaded from: classes.dex */
public final class e {

    /* renamed from: a, reason: collision with root package name */
    public static final a.f.j.d f255a = new C0014e(null, false);

    /* renamed from: b, reason: collision with root package name */
    public static final a.f.j.d f256b = new C0014e(null, true);

    /* renamed from: c, reason: collision with root package name */
    public static final a.f.j.d f257c = new C0014e(b.f261a, false);

    /* renamed from: d, reason: collision with root package name */
    public static final a.f.j.d f258d = new C0014e(b.f261a, true);

    /* loaded from: classes.dex */
    private static class a implements c {

        /* renamed from: b, reason: collision with root package name */
        static final a f259b = new a(true);

        /* renamed from: a, reason: collision with root package name */
        private final boolean f260a;

        private a(boolean z) {
            this.f260a = z;
        }

        @Override // a.f.j.e.c
        public int a(CharSequence charSequence, int i, int i2) {
            int i3 = i2 + i;
            boolean z = false;
            while (i < i3) {
                int a2 = e.a(Character.getDirectionality(charSequence.charAt(i)));
                if (a2 != 0) {
                    if (a2 != 1) {
                        continue;
                        i++;
                    } else if (!this.f260a) {
                        return 1;
                    }
                } else if (this.f260a) {
                    return 0;
                }
                z = true;
                i++;
            }
            if (z) {
                return this.f260a ? 1 : 0;
            }
            return 2;
        }
    }

    /* loaded from: classes.dex */
    private static class b implements c {

        /* renamed from: a, reason: collision with root package name */
        static final b f261a = new b();

        private b() {
        }

        @Override // a.f.j.e.c
        public int a(CharSequence charSequence, int i, int i2) {
            int i3 = i2 + i;
            int i4 = 2;
            while (i < i3 && i4 == 2) {
                i4 = e.b(Character.getDirectionality(charSequence.charAt(i)));
                i++;
            }
            return i4;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public interface c {
        int a(CharSequence charSequence, int i, int i2);
    }

    /* loaded from: classes.dex */
    private static abstract class d implements a.f.j.d {

        /* renamed from: a, reason: collision with root package name */
        private final c f262a;

        d(c cVar) {
            this.f262a = cVar;
        }

        private boolean b(CharSequence charSequence, int i, int i2) {
            int a2 = this.f262a.a(charSequence, i, i2);
            if (a2 == 0) {
                return true;
            }
            if (a2 != 1) {
                return a();
            }
            return false;
        }

        protected abstract boolean a();

        @Override // a.f.j.d
        public boolean a(CharSequence charSequence, int i, int i2) {
            if (charSequence != null && i >= 0 && i2 >= 0 && charSequence.length() - i2 >= i) {
                if (this.f262a == null) {
                    return a();
                }
                return b(charSequence, i, i2);
            }
            throw new IllegalArgumentException();
        }
    }

    /* renamed from: a.f.j.e$e, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    private static class C0014e extends d {

        /* renamed from: b, reason: collision with root package name */
        private final boolean f263b;

        C0014e(c cVar, boolean z) {
            super(cVar);
            this.f263b = z;
        }

        @Override // a.f.j.e.d
        protected boolean a() {
            return this.f263b;
        }
    }

    /* loaded from: classes.dex */
    private static class f extends d {

        /* renamed from: b, reason: collision with root package name */
        static final f f264b = new f();

        f() {
            super(null);
        }

        @Override // a.f.j.e.d
        protected boolean a() {
            return a.f.j.f.b(Locale.getDefault()) == 1;
        }
    }

    static {
        new C0014e(a.f259b, false);
        f fVar = f.f264b;
    }

    static int a(int i) {
        if (i != 0) {
            return (i == 1 || i == 2) ? 0 : 2;
        }
        return 1;
    }

    static int b(int i) {
        if (i != 0) {
            if (i == 1 || i == 2) {
                return 0;
            }
            switch (i) {
                case 14:
                case 15:
                    break;
                case 16:
                case 17:
                    return 0;
                default:
                    return 2;
            }
        }
        return 1;
    }
}
