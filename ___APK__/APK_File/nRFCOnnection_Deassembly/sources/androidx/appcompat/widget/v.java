package androidx.appcompat.widget;

import a.f.d.d.f;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.widget.TextView;
import java.lang.ref.WeakReference;

/* loaded from: classes.dex */
class v {

    /* renamed from: a, reason: collision with root package name */
    private final TextView f1107a;

    /* renamed from: b, reason: collision with root package name */
    private r0 f1108b;

    /* renamed from: c, reason: collision with root package name */
    private r0 f1109c;

    /* renamed from: d, reason: collision with root package name */
    private r0 f1110d;

    /* renamed from: e, reason: collision with root package name */
    private r0 f1111e;

    /* renamed from: f, reason: collision with root package name */
    private r0 f1112f;
    private r0 g;
    private r0 h;
    private final w i;
    private int j = 0;
    private int k = -1;
    private Typeface l;
    private boolean m;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class a extends f.a {

        /* renamed from: a, reason: collision with root package name */
        private final WeakReference<v> f1113a;

        /* renamed from: b, reason: collision with root package name */
        private final int f1114b;

        /* renamed from: c, reason: collision with root package name */
        private final int f1115c;

        /* renamed from: androidx.appcompat.widget.v$a$a, reason: collision with other inner class name */
        /* loaded from: classes.dex */
        private class RunnableC0044a implements Runnable {

            /* renamed from: b, reason: collision with root package name */
            private final WeakReference<v> f1116b;

            /* renamed from: c, reason: collision with root package name */
            private final Typeface f1117c;

            RunnableC0044a(a aVar, WeakReference<v> weakReference, Typeface typeface) {
                this.f1116b = weakReference;
                this.f1117c = typeface;
            }

            @Override // java.lang.Runnable
            public void run() {
                v vVar = this.f1116b.get();
                if (vVar == null) {
                    return;
                }
                vVar.a(this.f1117c);
            }
        }

        a(v vVar, int i, int i2) {
            this.f1113a = new WeakReference<>(vVar);
            this.f1114b = i;
            this.f1115c = i2;
        }

        @Override // a.f.d.d.f.a
        public void a(int i) {
        }

        @Override // a.f.d.d.f.a
        public void a(Typeface typeface) {
            int i;
            v vVar = this.f1113a.get();
            if (vVar == null) {
                return;
            }
            if (Build.VERSION.SDK_INT >= 28 && (i = this.f1114b) != -1) {
                typeface = Typeface.create(typeface, i, (this.f1115c & 2) != 0);
            }
            vVar.a(new RunnableC0044a(this, this.f1113a, typeface));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public v(TextView textView) {
        this.f1107a = textView;
        this.i = new w(this.f1107a);
    }

    private void l() {
        r0 r0Var = this.h;
        this.f1108b = r0Var;
        this.f1109c = r0Var;
        this.f1110d = r0Var;
        this.f1111e = r0Var;
        this.f1112f = r0Var;
        this.g = r0Var;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:157:0x0117  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0110  */
    @android.annotation.SuppressLint({"NewApi"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(android.util.AttributeSet r19, int r20) {
        /*
            Method dump skipped, instructions count: 825
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.v.a(android.util.AttributeSet, int):void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b() {
        this.i.a();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int c() {
        return this.i.b();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int d() {
        return this.i.c();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int e() {
        return this.i.d();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int[] f() {
        return this.i.e();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int g() {
        return this.i.f();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ColorStateList h() {
        r0 r0Var = this.h;
        if (r0Var != null) {
            return r0Var.f1082a;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PorterDuff.Mode i() {
        r0 r0Var = this.h;
        if (r0Var != null) {
            return r0Var.f1083b;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean j() {
        return this.i.g();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void k() {
        a();
    }

    private void b(int i, float f2) {
        this.i.a(i, f2);
    }

    public void a(Typeface typeface) {
        if (this.m) {
            this.f1107a.setTypeface(typeface);
            this.l = typeface;
        }
    }

    public void a(Runnable runnable) {
        this.f1107a.post(runnable);
    }

    private void a(Context context, t0 t0Var) {
        String d2;
        this.j = t0Var.d(a.a.j.TextAppearance_android_textStyle, this.j);
        if (Build.VERSION.SDK_INT >= 28) {
            this.k = t0Var.d(a.a.j.TextAppearance_android_textFontWeight, -1);
            if (this.k != -1) {
                this.j = (this.j & 2) | 0;
            }
        }
        if (!t0Var.g(a.a.j.TextAppearance_android_fontFamily) && !t0Var.g(a.a.j.TextAppearance_fontFamily)) {
            if (t0Var.g(a.a.j.TextAppearance_android_typeface)) {
                this.m = false;
                int d3 = t0Var.d(a.a.j.TextAppearance_android_typeface, 1);
                if (d3 == 1) {
                    this.l = Typeface.SANS_SERIF;
                    return;
                } else if (d3 == 2) {
                    this.l = Typeface.SERIF;
                    return;
                } else {
                    if (d3 != 3) {
                        return;
                    }
                    this.l = Typeface.MONOSPACE;
                    return;
                }
            }
            return;
        }
        this.l = null;
        int i = t0Var.g(a.a.j.TextAppearance_fontFamily) ? a.a.j.TextAppearance_fontFamily : a.a.j.TextAppearance_android_fontFamily;
        int i2 = this.k;
        int i3 = this.j;
        if (!context.isRestricted()) {
            try {
                Typeface a2 = t0Var.a(i, this.j, new a(this, i2, i3));
                if (a2 != null) {
                    if (Build.VERSION.SDK_INT >= 28 && this.k != -1) {
                        this.l = Typeface.create(Typeface.create(a2, 0), this.k, (this.j & 2) != 0);
                    } else {
                        this.l = a2;
                    }
                }
                this.m = this.l == null;
            } catch (Resources.NotFoundException | UnsupportedOperationException unused) {
            }
        }
        if (this.l != null || (d2 = t0Var.d(i)) == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 28 && this.k != -1) {
            this.l = Typeface.create(Typeface.create(d2, 0), this.k, (this.j & 2) != 0);
        } else {
            this.l = Typeface.create(d2, this.j);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(Context context, int i) {
        String d2;
        ColorStateList a2;
        t0 a3 = t0.a(context, i, a.a.j.TextAppearance);
        if (a3.g(a.a.j.TextAppearance_textAllCaps)) {
            a(a3.a(a.a.j.TextAppearance_textAllCaps, false));
        }
        if (Build.VERSION.SDK_INT < 23 && a3.g(a.a.j.TextAppearance_android_textColor) && (a2 = a3.a(a.a.j.TextAppearance_android_textColor)) != null) {
            this.f1107a.setTextColor(a2);
        }
        if (a3.g(a.a.j.TextAppearance_android_textSize) && a3.c(a.a.j.TextAppearance_android_textSize, -1) == 0) {
            this.f1107a.setTextSize(0, 0.0f);
        }
        a(context, a3);
        if (Build.VERSION.SDK_INT >= 26 && a3.g(a.a.j.TextAppearance_fontVariationSettings) && (d2 = a3.d(a.a.j.TextAppearance_fontVariationSettings)) != null) {
            this.f1107a.setFontVariationSettings(d2);
        }
        a3.a();
        Typeface typeface = this.l;
        if (typeface != null) {
            this.f1107a.setTypeface(typeface, this.j);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(boolean z) {
        this.f1107a.setAllCaps(z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a() {
        if (this.f1108b != null || this.f1109c != null || this.f1110d != null || this.f1111e != null) {
            Drawable[] compoundDrawables = this.f1107a.getCompoundDrawables();
            a(compoundDrawables[0], this.f1108b);
            a(compoundDrawables[1], this.f1109c);
            a(compoundDrawables[2], this.f1110d);
            a(compoundDrawables[3], this.f1111e);
        }
        if (Build.VERSION.SDK_INT >= 17) {
            if (this.f1112f == null && this.g == null) {
                return;
            }
            Drawable[] compoundDrawablesRelative = this.f1107a.getCompoundDrawablesRelative();
            a(compoundDrawablesRelative[0], this.f1112f);
            a(compoundDrawablesRelative[2], this.g);
        }
    }

    private void a(Drawable drawable, r0 r0Var) {
        if (drawable == null || r0Var == null) {
            return;
        }
        j.a(drawable, r0Var, this.f1107a.getDrawableState());
    }

    private static r0 a(Context context, j jVar, int i) {
        ColorStateList b2 = jVar.b(context, i);
        if (b2 == null) {
            return null;
        }
        r0 r0Var = new r0();
        r0Var.f1085d = true;
        r0Var.f1082a = b2;
        return r0Var;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(boolean z, int i, int i2, int i3, int i4) {
        if (androidx.core.widget.b.f1314a) {
            return;
        }
        b();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(int i, float f2) {
        if (androidx.core.widget.b.f1314a || j()) {
            return;
        }
        b(i, f2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(int i) {
        this.i.b(i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(int i, int i2, int i3, int i4) {
        this.i.a(i, i2, i3, i4);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(int[] iArr, int i) {
        this.i.a(iArr, i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(ColorStateList colorStateList) {
        if (this.h == null) {
            this.h = new r0();
        }
        r0 r0Var = this.h;
        r0Var.f1082a = colorStateList;
        r0Var.f1085d = colorStateList != null;
        l();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(PorterDuff.Mode mode) {
        if (this.h == null) {
            this.h = new r0();
        }
        r0 r0Var = this.h;
        r0Var.f1083b = mode;
        r0Var.f1084c = mode != null;
        l();
    }

    private void a(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4, Drawable drawable5, Drawable drawable6) {
        if (Build.VERSION.SDK_INT >= 17 && (drawable5 != null || drawable6 != null)) {
            Drawable[] compoundDrawablesRelative = this.f1107a.getCompoundDrawablesRelative();
            TextView textView = this.f1107a;
            if (drawable5 == null) {
                drawable5 = compoundDrawablesRelative[0];
            }
            if (drawable2 == null) {
                drawable2 = compoundDrawablesRelative[1];
            }
            if (drawable6 == null) {
                drawable6 = compoundDrawablesRelative[2];
            }
            if (drawable4 == null) {
                drawable4 = compoundDrawablesRelative[3];
            }
            textView.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable5, drawable2, drawable6, drawable4);
            return;
        }
        if (drawable == null && drawable2 == null && drawable3 == null && drawable4 == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 17) {
            Drawable[] compoundDrawablesRelative2 = this.f1107a.getCompoundDrawablesRelative();
            if (compoundDrawablesRelative2[0] != null || compoundDrawablesRelative2[2] != null) {
                TextView textView2 = this.f1107a;
                Drawable drawable7 = compoundDrawablesRelative2[0];
                if (drawable2 == null) {
                    drawable2 = compoundDrawablesRelative2[1];
                }
                Drawable drawable8 = compoundDrawablesRelative2[2];
                if (drawable4 == null) {
                    drawable4 = compoundDrawablesRelative2[3];
                }
                textView2.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable7, drawable2, drawable8, drawable4);
                return;
            }
        }
        Drawable[] compoundDrawables = this.f1107a.getCompoundDrawables();
        TextView textView3 = this.f1107a;
        if (drawable == null) {
            drawable = compoundDrawables[0];
        }
        if (drawable2 == null) {
            drawable2 = compoundDrawables[1];
        }
        if (drawable3 == null) {
            drawable3 = compoundDrawables[2];
        }
        if (drawable4 == null) {
            drawable4 = compoundDrawables[3];
        }
        textView3.setCompoundDrawablesWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
    }
}
