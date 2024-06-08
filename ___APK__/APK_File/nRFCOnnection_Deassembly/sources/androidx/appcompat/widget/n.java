package androidx.appcompat.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;

/* loaded from: classes.dex */
public class n {

    /* renamed from: a, reason: collision with root package name */
    private final ImageView f1052a;

    /* renamed from: b, reason: collision with root package name */
    private r0 f1053b;

    /* renamed from: c, reason: collision with root package name */
    private r0 f1054c;

    /* renamed from: d, reason: collision with root package name */
    private r0 f1055d;

    public n(ImageView imageView) {
        this.f1052a = imageView;
    }

    private boolean e() {
        int i = Build.VERSION.SDK_INT;
        return i > 21 ? this.f1053b != null : i == 21;
    }

    public void a(AttributeSet attributeSet, int i) {
        int g;
        t0 a2 = t0.a(this.f1052a.getContext(), attributeSet, a.a.j.AppCompatImageView, i, 0);
        try {
            Drawable drawable = this.f1052a.getDrawable();
            if (drawable == null && (g = a2.g(a.a.j.AppCompatImageView_srcCompat, -1)) != -1 && (drawable = a.a.k.a.a.c(this.f1052a.getContext(), g)) != null) {
                this.f1052a.setImageDrawable(drawable);
            }
            if (drawable != null) {
                a0.b(drawable);
            }
            if (a2.g(a.a.j.AppCompatImageView_tint)) {
                androidx.core.widget.e.a(this.f1052a, a2.a(a.a.j.AppCompatImageView_tint));
            }
            if (a2.g(a.a.j.AppCompatImageView_tintMode)) {
                androidx.core.widget.e.a(this.f1052a, a0.a(a2.d(a.a.j.AppCompatImageView_tintMode, -1), null));
            }
        } finally {
            a2.a();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ColorStateList b() {
        r0 r0Var = this.f1054c;
        if (r0Var != null) {
            return r0Var.f1082a;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PorterDuff.Mode c() {
        r0 r0Var = this.f1054c;
        if (r0Var != null) {
            return r0Var.f1083b;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean d() {
        return Build.VERSION.SDK_INT < 21 || !(this.f1052a.getBackground() instanceof RippleDrawable);
    }

    public void a(int i) {
        if (i != 0) {
            Drawable c2 = a.a.k.a.a.c(this.f1052a.getContext(), i);
            if (c2 != null) {
                a0.b(c2);
            }
            this.f1052a.setImageDrawable(c2);
        } else {
            this.f1052a.setImageDrawable(null);
        }
        a();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(ColorStateList colorStateList) {
        if (this.f1054c == null) {
            this.f1054c = new r0();
        }
        r0 r0Var = this.f1054c;
        r0Var.f1082a = colorStateList;
        r0Var.f1085d = true;
        a();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(PorterDuff.Mode mode) {
        if (this.f1054c == null) {
            this.f1054c = new r0();
        }
        r0 r0Var = this.f1054c;
        r0Var.f1083b = mode;
        r0Var.f1084c = true;
        a();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a() {
        Drawable drawable = this.f1052a.getDrawable();
        if (drawable != null) {
            a0.b(drawable);
        }
        if (drawable != null) {
            if (e() && a(drawable)) {
                return;
            }
            r0 r0Var = this.f1054c;
            if (r0Var != null) {
                j.a(drawable, r0Var, this.f1052a.getDrawableState());
                return;
            }
            r0 r0Var2 = this.f1053b;
            if (r0Var2 != null) {
                j.a(drawable, r0Var2, this.f1052a.getDrawableState());
            }
        }
    }

    private boolean a(Drawable drawable) {
        if (this.f1055d == null) {
            this.f1055d = new r0();
        }
        r0 r0Var = this.f1055d;
        r0Var.a();
        ColorStateList a2 = androidx.core.widget.e.a(this.f1052a);
        if (a2 != null) {
            r0Var.f1085d = true;
            r0Var.f1082a = a2;
        }
        PorterDuff.Mode b2 = androidx.core.widget.e.b(this.f1052a);
        if (b2 != null) {
            r0Var.f1084c = true;
            r0Var.f1083b = b2;
        }
        if (!r0Var.f1085d && !r0Var.f1084c) {
            return false;
        }
        j.a(drawable, r0Var, this.f1052a.getDrawableState());
        return true;
    }
}
