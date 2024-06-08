package a.n;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.os.Build;
import android.util.Property;
import android.view.View;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class c0 {

    /* renamed from: a, reason: collision with root package name */
    private static final i0 f446a;

    /* renamed from: b, reason: collision with root package name */
    static final Property<View, Float> f447b;

    /* loaded from: classes.dex */
    static class a extends Property<View, Float> {
        a(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Float get(View view) {
            return Float.valueOf(c0.c(view));
        }

        @Override // android.util.Property
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void set(View view, Float f2) {
            c0.a(view, f2.floatValue());
        }
    }

    /* loaded from: classes.dex */
    static class b extends Property<View, Rect> {
        b(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Rect get(View view) {
            return a.f.l.w.j(view);
        }

        @Override // android.util.Property
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void set(View view, Rect rect) {
            a.f.l.w.a(view, rect);
        }
    }

    static {
        int i = Build.VERSION.SDK_INT;
        if (i >= 29) {
            f446a = new h0();
        } else if (i >= 23) {
            f446a = new g0();
        } else if (i >= 22) {
            f446a = new f0();
        } else if (i >= 21) {
            f446a = new e0();
        } else if (i >= 19) {
            f446a = new d0();
        } else {
            f446a = new i0();
        }
        f447b = new a(Float.class, "translationAlpha");
        new b(Rect.class, "clipBounds");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(View view, float f2) {
        f446a.a(view, f2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static b0 b(View view) {
        if (Build.VERSION.SDK_INT >= 18) {
            return new a0(view);
        }
        return z.c(view);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static float c(View view) {
        return f446a.b(view);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static m0 d(View view) {
        if (Build.VERSION.SDK_INT >= 18) {
            return new l0(view);
        }
        return new k0(view.getWindowToken());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void e(View view) {
        f446a.c(view);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(View view) {
        f446a.a(view);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(View view, int i) {
        f446a.a(view, i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(View view, Matrix matrix) {
        f446a.a(view, matrix);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void b(View view, Matrix matrix) {
        f446a.b(view, matrix);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(View view, int i, int i2, int i3, int i4) {
        f446a.a(view, i, i2, i3, i4);
    }
}
