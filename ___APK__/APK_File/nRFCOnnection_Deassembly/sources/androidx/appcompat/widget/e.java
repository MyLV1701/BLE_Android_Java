package androidx.appcompat.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

/* loaded from: classes.dex */
class e {

    /* renamed from: a, reason: collision with root package name */
    private final View f966a;

    /* renamed from: d, reason: collision with root package name */
    private r0 f969d;

    /* renamed from: e, reason: collision with root package name */
    private r0 f970e;

    /* renamed from: f, reason: collision with root package name */
    private r0 f971f;

    /* renamed from: c, reason: collision with root package name */
    private int f968c = -1;

    /* renamed from: b, reason: collision with root package name */
    private final j f967b = j.b();

    /* JADX INFO: Access modifiers changed from: package-private */
    public e(View view) {
        this.f966a = view;
    }

    private boolean d() {
        int i = Build.VERSION.SDK_INT;
        return i > 21 ? this.f969d != null : i == 21;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(AttributeSet attributeSet, int i) {
        t0 a2 = t0.a(this.f966a.getContext(), attributeSet, a.a.j.ViewBackgroundHelper, i, 0);
        try {
            if (a2.g(a.a.j.ViewBackgroundHelper_android_background)) {
                this.f968c = a2.g(a.a.j.ViewBackgroundHelper_android_background, -1);
                ColorStateList b2 = this.f967b.b(this.f966a.getContext(), this.f968c);
                if (b2 != null) {
                    a(b2);
                }
            }
            if (a2.g(a.a.j.ViewBackgroundHelper_backgroundTint)) {
                a.f.l.w.a(this.f966a, a2.a(a.a.j.ViewBackgroundHelper_backgroundTint));
            }
            if (a2.g(a.a.j.ViewBackgroundHelper_backgroundTintMode)) {
                a.f.l.w.a(this.f966a, a0.a(a2.d(a.a.j.ViewBackgroundHelper_backgroundTintMode, -1), null));
            }
        } finally {
            a2.a();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(ColorStateList colorStateList) {
        if (this.f970e == null) {
            this.f970e = new r0();
        }
        r0 r0Var = this.f970e;
        r0Var.f1082a = colorStateList;
        r0Var.f1085d = true;
        a();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PorterDuff.Mode c() {
        r0 r0Var = this.f970e;
        if (r0Var != null) {
            return r0Var.f1083b;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ColorStateList b() {
        r0 r0Var = this.f970e;
        if (r0Var != null) {
            return r0Var.f1082a;
        }
        return null;
    }

    private boolean b(Drawable drawable) {
        if (this.f971f == null) {
            this.f971f = new r0();
        }
        r0 r0Var = this.f971f;
        r0Var.a();
        ColorStateList h = a.f.l.w.h(this.f966a);
        if (h != null) {
            r0Var.f1085d = true;
            r0Var.f1082a = h;
        }
        PorterDuff.Mode i = a.f.l.w.i(this.f966a);
        if (i != null) {
            r0Var.f1084c = true;
            r0Var.f1083b = i;
        }
        if (!r0Var.f1085d && !r0Var.f1084c) {
            return false;
        }
        j.a(drawable, r0Var, this.f966a.getDrawableState());
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(int i) {
        this.f968c = i;
        j jVar = this.f967b;
        a(jVar != null ? jVar.b(this.f966a.getContext(), i) : null);
        a();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(Drawable drawable) {
        this.f968c = -1;
        a((ColorStateList) null);
        a();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(PorterDuff.Mode mode) {
        if (this.f970e == null) {
            this.f970e = new r0();
        }
        r0 r0Var = this.f970e;
        r0Var.f1083b = mode;
        r0Var.f1084c = true;
        a();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a() {
        Drawable background = this.f966a.getBackground();
        if (background != null) {
            if (d() && b(background)) {
                return;
            }
            r0 r0Var = this.f970e;
            if (r0Var != null) {
                j.a(background, r0Var, this.f966a.getDrawableState());
                return;
            }
            r0 r0Var2 = this.f969d;
            if (r0Var2 != null) {
                j.a(background, r0Var2, this.f966a.getDrawableState());
            }
        }
    }

    void a(ColorStateList colorStateList) {
        if (colorStateList != null) {
            if (this.f969d == null) {
                this.f969d = new r0();
            }
            r0 r0Var = this.f969d;
            r0Var.f1082a = colorStateList;
            r0Var.f1085d = true;
        } else {
            this.f969d = null;
        }
        a();
    }
}
