package c.a.a.a.b0;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;

/* loaded from: classes.dex */
public class k {
    public static final c.a.a.a.b0.c m = new i(0.5f);

    /* renamed from: a, reason: collision with root package name */
    d f2048a;

    /* renamed from: b, reason: collision with root package name */
    d f2049b;

    /* renamed from: c, reason: collision with root package name */
    d f2050c;

    /* renamed from: d, reason: collision with root package name */
    d f2051d;

    /* renamed from: e, reason: collision with root package name */
    c.a.a.a.b0.c f2052e;

    /* renamed from: f, reason: collision with root package name */
    c.a.a.a.b0.c f2053f;
    c.a.a.a.b0.c g;
    c.a.a.a.b0.c h;
    f i;
    f j;
    f k;
    f l;

    /* loaded from: classes.dex */
    public static final class b {

        /* renamed from: a, reason: collision with root package name */
        private d f2054a;

        /* renamed from: b, reason: collision with root package name */
        private d f2055b;

        /* renamed from: c, reason: collision with root package name */
        private d f2056c;

        /* renamed from: d, reason: collision with root package name */
        private d f2057d;

        /* renamed from: e, reason: collision with root package name */
        private c.a.a.a.b0.c f2058e;

        /* renamed from: f, reason: collision with root package name */
        private c.a.a.a.b0.c f2059f;
        private c.a.a.a.b0.c g;
        private c.a.a.a.b0.c h;
        private f i;
        private f j;
        private f k;
        private f l;

        public b() {
            this.f2054a = h.a();
            this.f2055b = h.a();
            this.f2056c = h.a();
            this.f2057d = h.a();
            this.f2058e = new c.a.a.a.b0.a(0.0f);
            this.f2059f = new c.a.a.a.b0.a(0.0f);
            this.g = new c.a.a.a.b0.a(0.0f);
            this.h = new c.a.a.a.b0.a(0.0f);
            this.i = h.b();
            this.j = h.b();
            this.k = h.b();
            this.l = h.b();
        }

        public b a(float f2) {
            d(f2);
            e(f2);
            c(f2);
            b(f2);
            return this;
        }

        public b b(c.a.a.a.b0.c cVar) {
            this.g = cVar;
            return this;
        }

        public b c(c.a.a.a.b0.c cVar) {
            this.f2058e = cVar;
            return this;
        }

        public b d(float f2) {
            this.f2058e = new c.a.a.a.b0.a(f2);
            return this;
        }

        public b e(float f2) {
            this.f2059f = new c.a.a.a.b0.a(f2);
            return this;
        }

        private static float e(d dVar) {
            if (dVar instanceof j) {
                return ((j) dVar).f2047a;
            }
            if (dVar instanceof e) {
                return ((e) dVar).f2032a;
            }
            return -1.0f;
        }

        public b b(float f2) {
            this.h = new c.a.a.a.b0.a(f2);
            return this;
        }

        public b c(float f2) {
            this.g = new c.a.a.a.b0.a(f2);
            return this;
        }

        public b d(c.a.a.a.b0.c cVar) {
            this.f2059f = cVar;
            return this;
        }

        public b b(int i, c.a.a.a.b0.c cVar) {
            b(h.a(i));
            b(cVar);
            return this;
        }

        public b c(int i, c.a.a.a.b0.c cVar) {
            c(h.a(i));
            c(cVar);
            return this;
        }

        public b d(int i, c.a.a.a.b0.c cVar) {
            d(h.a(i));
            d(cVar);
            return this;
        }

        public b a(c.a.a.a.b0.c cVar) {
            this.h = cVar;
            return this;
        }

        public b b(d dVar) {
            this.f2056c = dVar;
            float e2 = e(dVar);
            if (e2 != -1.0f) {
                c(e2);
            }
            return this;
        }

        public b c(d dVar) {
            this.f2054a = dVar;
            float e2 = e(dVar);
            if (e2 != -1.0f) {
                d(e2);
            }
            return this;
        }

        public b d(d dVar) {
            this.f2055b = dVar;
            float e2 = e(dVar);
            if (e2 != -1.0f) {
                e(e2);
            }
            return this;
        }

        public b a(int i, c.a.a.a.b0.c cVar) {
            a(h.a(i));
            a(cVar);
            return this;
        }

        public b a(d dVar) {
            this.f2057d = dVar;
            float e2 = e(dVar);
            if (e2 != -1.0f) {
                b(e2);
            }
            return this;
        }

        public k a() {
            return new k(this);
        }

        public b(k kVar) {
            this.f2054a = h.a();
            this.f2055b = h.a();
            this.f2056c = h.a();
            this.f2057d = h.a();
            this.f2058e = new c.a.a.a.b0.a(0.0f);
            this.f2059f = new c.a.a.a.b0.a(0.0f);
            this.g = new c.a.a.a.b0.a(0.0f);
            this.h = new c.a.a.a.b0.a(0.0f);
            this.i = h.b();
            this.j = h.b();
            this.k = h.b();
            this.l = h.b();
            this.f2054a = kVar.f2048a;
            this.f2055b = kVar.f2049b;
            this.f2056c = kVar.f2050c;
            this.f2057d = kVar.f2051d;
            this.f2058e = kVar.f2052e;
            this.f2059f = kVar.f2053f;
            this.g = kVar.g;
            this.h = kVar.h;
            this.i = kVar.i;
            this.j = kVar.j;
            this.k = kVar.k;
            this.l = kVar.l;
        }
    }

    /* loaded from: classes.dex */
    public interface c {
        c.a.a.a.b0.c a(c.a.a.a.b0.c cVar);
    }

    public static b a(Context context, AttributeSet attributeSet, int i, int i2) {
        return a(context, attributeSet, i, i2, 0);
    }

    public static b n() {
        return new b();
    }

    public d b() {
        return this.f2051d;
    }

    public c.a.a.a.b0.c c() {
        return this.h;
    }

    public d d() {
        return this.f2050c;
    }

    public c.a.a.a.b0.c e() {
        return this.g;
    }

    public f f() {
        return this.l;
    }

    public f g() {
        return this.j;
    }

    public f h() {
        return this.i;
    }

    public d i() {
        return this.f2048a;
    }

    public c.a.a.a.b0.c j() {
        return this.f2052e;
    }

    public d k() {
        return this.f2049b;
    }

    public c.a.a.a.b0.c l() {
        return this.f2053f;
    }

    public b m() {
        return new b(this);
    }

    private k(b bVar) {
        this.f2048a = bVar.f2054a;
        this.f2049b = bVar.f2055b;
        this.f2050c = bVar.f2056c;
        this.f2051d = bVar.f2057d;
        this.f2052e = bVar.f2058e;
        this.f2053f = bVar.f2059f;
        this.g = bVar.g;
        this.h = bVar.h;
        this.i = bVar.i;
        this.j = bVar.j;
        this.k = bVar.k;
        this.l = bVar.l;
    }

    public static b a(Context context, AttributeSet attributeSet, int i, int i2, int i3) {
        return a(context, attributeSet, i, i2, new c.a.a.a.b0.a(i3));
    }

    public static b a(Context context, AttributeSet attributeSet, int i, int i2, c.a.a.a.b0.c cVar) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, c.a.a.a.l.MaterialShape, i, i2);
        int resourceId = obtainStyledAttributes.getResourceId(c.a.a.a.l.MaterialShape_shapeAppearance, 0);
        int resourceId2 = obtainStyledAttributes.getResourceId(c.a.a.a.l.MaterialShape_shapeAppearanceOverlay, 0);
        obtainStyledAttributes.recycle();
        return a(context, resourceId, resourceId2, cVar);
    }

    public static b a(Context context, int i, int i2) {
        return a(context, i, i2, 0);
    }

    private static b a(Context context, int i, int i2, int i3) {
        return a(context, i, i2, new c.a.a.a.b0.a(i3));
    }

    private static b a(Context context, int i, int i2, c.a.a.a.b0.c cVar) {
        if (i2 != 0) {
            ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, i);
            i = i2;
            context = contextThemeWrapper;
        }
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(i, c.a.a.a.l.ShapeAppearance);
        try {
            int i3 = obtainStyledAttributes.getInt(c.a.a.a.l.ShapeAppearance_cornerFamily, 0);
            int i4 = obtainStyledAttributes.getInt(c.a.a.a.l.ShapeAppearance_cornerFamilyTopLeft, i3);
            int i5 = obtainStyledAttributes.getInt(c.a.a.a.l.ShapeAppearance_cornerFamilyTopRight, i3);
            int i6 = obtainStyledAttributes.getInt(c.a.a.a.l.ShapeAppearance_cornerFamilyBottomRight, i3);
            int i7 = obtainStyledAttributes.getInt(c.a.a.a.l.ShapeAppearance_cornerFamilyBottomLeft, i3);
            c.a.a.a.b0.c a2 = a(obtainStyledAttributes, c.a.a.a.l.ShapeAppearance_cornerSize, cVar);
            c.a.a.a.b0.c a3 = a(obtainStyledAttributes, c.a.a.a.l.ShapeAppearance_cornerSizeTopLeft, a2);
            c.a.a.a.b0.c a4 = a(obtainStyledAttributes, c.a.a.a.l.ShapeAppearance_cornerSizeTopRight, a2);
            c.a.a.a.b0.c a5 = a(obtainStyledAttributes, c.a.a.a.l.ShapeAppearance_cornerSizeBottomRight, a2);
            c.a.a.a.b0.c a6 = a(obtainStyledAttributes, c.a.a.a.l.ShapeAppearance_cornerSizeBottomLeft, a2);
            b bVar = new b();
            bVar.c(i4, a3);
            bVar.d(i5, a4);
            bVar.b(i6, a5);
            bVar.a(i7, a6);
            return bVar;
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    public k() {
        this.f2048a = h.a();
        this.f2049b = h.a();
        this.f2050c = h.a();
        this.f2051d = h.a();
        this.f2052e = new c.a.a.a.b0.a(0.0f);
        this.f2053f = new c.a.a.a.b0.a(0.0f);
        this.g = new c.a.a.a.b0.a(0.0f);
        this.h = new c.a.a.a.b0.a(0.0f);
        this.i = h.b();
        this.j = h.b();
        this.k = h.b();
        this.l = h.b();
    }

    private static c.a.a.a.b0.c a(TypedArray typedArray, int i, c.a.a.a.b0.c cVar) {
        TypedValue peekValue = typedArray.peekValue(i);
        if (peekValue == null) {
            return cVar;
        }
        int i2 = peekValue.type;
        if (i2 == 5) {
            return new c.a.a.a.b0.a(TypedValue.complexToDimensionPixelSize(peekValue.data, typedArray.getResources().getDisplayMetrics()));
        }
        return i2 == 6 ? new i(peekValue.getFraction(1.0f, 1.0f)) : cVar;
    }

    public f a() {
        return this.k;
    }

    public k a(float f2) {
        b m2 = m();
        m2.a(f2);
        return m2.a();
    }

    public k a(c cVar) {
        b m2 = m();
        m2.c(cVar.a(j()));
        m2.d(cVar.a(l()));
        m2.a(cVar.a(c()));
        m2.b(cVar.a(e()));
        return m2.a();
    }

    public boolean a(RectF rectF) {
        boolean z = this.l.getClass().equals(f.class) && this.j.getClass().equals(f.class) && this.i.getClass().equals(f.class) && this.k.getClass().equals(f.class);
        float a2 = this.f2052e.a(rectF);
        return z && ((this.f2053f.a(rectF) > a2 ? 1 : (this.f2053f.a(rectF) == a2 ? 0 : -1)) == 0 && (this.h.a(rectF) > a2 ? 1 : (this.h.a(rectF) == a2 ? 0 : -1)) == 0 && (this.g.a(rectF) > a2 ? 1 : (this.g.a(rectF) == a2 ? 0 : -1)) == 0) && ((this.f2049b instanceof j) && (this.f2048a instanceof j) && (this.f2050c instanceof j) && (this.f2051d instanceof j));
    }
}
