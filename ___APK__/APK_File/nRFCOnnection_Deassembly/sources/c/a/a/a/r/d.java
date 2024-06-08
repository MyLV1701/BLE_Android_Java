package c.a.a.a.r;

import android.animation.TypeEvaluator;
import android.graphics.drawable.Drawable;
import android.util.Property;
import c.a.a.a.r.c;

/* loaded from: classes.dex */
public interface d extends c.a {

    /* loaded from: classes.dex */
    public static class b implements TypeEvaluator<e> {

        /* renamed from: b, reason: collision with root package name */
        public static final TypeEvaluator<e> f2132b = new b();

        /* renamed from: a, reason: collision with root package name */
        private final e f2133a = new e();

        @Override // android.animation.TypeEvaluator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public e evaluate(float f2, e eVar, e eVar2) {
            this.f2133a.a(c.a.a.a.w.a.a(eVar.f2136a, eVar2.f2136a, f2), c.a.a.a.w.a.a(eVar.f2137b, eVar2.f2137b, f2), c.a.a.a.w.a.a(eVar.f2138c, eVar2.f2138c, f2));
            return this.f2133a;
        }
    }

    /* loaded from: classes.dex */
    public static class c extends Property<d, e> {

        /* renamed from: a, reason: collision with root package name */
        public static final Property<d, e> f2134a = new c("circularReveal");

        private c(String str) {
            super(e.class, str);
        }

        @Override // android.util.Property
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public e get(d dVar) {
            return dVar.getRevealInfo();
        }

        @Override // android.util.Property
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void set(d dVar, e eVar) {
            dVar.setRevealInfo(eVar);
        }
    }

    /* renamed from: c.a.a.a.r.d$d, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static class C0070d extends Property<d, Integer> {

        /* renamed from: a, reason: collision with root package name */
        public static final Property<d, Integer> f2135a = new C0070d("circularRevealScrimColor");

        private C0070d(String str) {
            super(Integer.class, str);
        }

        @Override // android.util.Property
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Integer get(d dVar) {
            return Integer.valueOf(dVar.getCircularRevealScrimColor());
        }

        @Override // android.util.Property
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void set(d dVar, Integer num) {
            dVar.setCircularRevealScrimColor(num.intValue());
        }
    }

    /* loaded from: classes.dex */
    public static class e {

        /* renamed from: a, reason: collision with root package name */
        public float f2136a;

        /* renamed from: b, reason: collision with root package name */
        public float f2137b;

        /* renamed from: c, reason: collision with root package name */
        public float f2138c;

        public void a(float f2, float f3, float f4) {
            this.f2136a = f2;
            this.f2137b = f3;
            this.f2138c = f4;
        }

        private e() {
        }

        public e(float f2, float f3, float f4) {
            this.f2136a = f2;
            this.f2137b = f3;
            this.f2138c = f4;
        }
    }

    void a();

    void b();

    int getCircularRevealScrimColor();

    e getRevealInfo();

    void setCircularRevealOverlayDrawable(Drawable drawable);

    void setCircularRevealScrimColor(int i);

    void setRevealInfo(e eVar);
}
