package com.google.android.material.button;

import a.f.l.w;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import c.a.a.a.b;
import c.a.a.a.b0.g;
import c.a.a.a.b0.k;
import c.a.a.a.b0.n;
import c.a.a.a.l;
import c.a.a.a.y.c;
import com.google.android.material.internal.m;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class a {
    private static final boolean s;

    /* renamed from: a, reason: collision with root package name */
    private final MaterialButton f2433a;

    /* renamed from: b, reason: collision with root package name */
    private k f2434b;

    /* renamed from: c, reason: collision with root package name */
    private int f2435c;

    /* renamed from: d, reason: collision with root package name */
    private int f2436d;

    /* renamed from: e, reason: collision with root package name */
    private int f2437e;

    /* renamed from: f, reason: collision with root package name */
    private int f2438f;
    private int g;
    private int h;
    private PorterDuff.Mode i;
    private ColorStateList j;
    private ColorStateList k;
    private ColorStateList l;
    private Drawable m;
    private boolean n = false;
    private boolean o = false;
    private boolean p = false;
    private boolean q;
    private LayerDrawable r;

    static {
        s = Build.VERSION.SDK_INT >= 21;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public a(MaterialButton materialButton, k kVar) {
        this.f2433a = materialButton;
        this.f2434b = kVar;
    }

    private Drawable m() {
        g gVar = new g(this.f2434b);
        gVar.a(this.f2433a.getContext());
        androidx.core.graphics.drawable.a.a(gVar, this.j);
        PorterDuff.Mode mode = this.i;
        if (mode != null) {
            androidx.core.graphics.drawable.a.a(gVar, mode);
        }
        gVar.a(this.h, this.k);
        g gVar2 = new g(this.f2434b);
        gVar2.setTint(0);
        gVar2.a(this.h, this.n ? c.a.a.a.s.a.a(this.f2433a, b.colorSurface) : 0);
        if (s) {
            this.m = new g(this.f2434b);
            androidx.core.graphics.drawable.a.b(this.m, -1);
            this.r = new RippleDrawable(c.a.a.a.z.b.b(this.l), a(new LayerDrawable(new Drawable[]{gVar2, gVar})), this.m);
            return this.r;
        }
        this.m = new c.a.a.a.z.a(this.f2434b);
        androidx.core.graphics.drawable.a.a(this.m, c.a.a.a.z.b.b(this.l));
        this.r = new LayerDrawable(new Drawable[]{gVar2, gVar, this.m});
        return a(this.r);
    }

    private g n() {
        return c(true);
    }

    private void o() {
        g c2 = c();
        g n = n();
        if (c2 != null) {
            c2.a(this.h, this.k);
            if (n != null) {
                n.a(this.h, this.n ? c.a.a.a.s.a.a(this.f2433a, b.colorSurface) : 0);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(TypedArray typedArray) {
        this.f2435c = typedArray.getDimensionPixelOffset(l.MaterialButton_android_insetLeft, 0);
        this.f2436d = typedArray.getDimensionPixelOffset(l.MaterialButton_android_insetRight, 0);
        this.f2437e = typedArray.getDimensionPixelOffset(l.MaterialButton_android_insetTop, 0);
        this.f2438f = typedArray.getDimensionPixelOffset(l.MaterialButton_android_insetBottom, 0);
        if (typedArray.hasValue(l.MaterialButton_cornerRadius)) {
            this.g = typedArray.getDimensionPixelSize(l.MaterialButton_cornerRadius, -1);
            a(this.f2434b.a(this.g));
            this.p = true;
        }
        this.h = typedArray.getDimensionPixelSize(l.MaterialButton_strokeWidth, 0);
        this.i = m.a(typedArray.getInt(l.MaterialButton_backgroundTintMode, -1), PorterDuff.Mode.SRC_IN);
        this.j = c.a(this.f2433a.getContext(), typedArray, l.MaterialButton_backgroundTint);
        this.k = c.a(this.f2433a.getContext(), typedArray, l.MaterialButton_strokeColor);
        this.l = c.a(this.f2433a.getContext(), typedArray, l.MaterialButton_rippleColor);
        this.q = typedArray.getBoolean(l.MaterialButton_android_checkable, false);
        int dimensionPixelSize = typedArray.getDimensionPixelSize(l.MaterialButton_elevation, 0);
        int v = w.v(this.f2433a);
        int paddingTop = this.f2433a.getPaddingTop();
        int u = w.u(this.f2433a);
        int paddingBottom = this.f2433a.getPaddingBottom();
        this.f2433a.setInternalBackground(m());
        g c2 = c();
        if (c2 != null) {
            c2.b(dimensionPixelSize);
        }
        w.b(this.f2433a, v + this.f2435c, paddingTop + this.f2437e, u + this.f2436d, paddingBottom + this.f2438f);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(boolean z) {
        this.n = z;
        o();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void c(ColorStateList colorStateList) {
        if (this.j != colorStateList) {
            this.j = colorStateList;
            if (c() != null) {
                androidx.core.graphics.drawable.a.a(c(), this.j);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ColorStateList d() {
        return this.l;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public k e() {
        return this.f2434b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ColorStateList f() {
        return this.k;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int g() {
        return this.h;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ColorStateList h() {
        return this.j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PorterDuff.Mode i() {
        return this.i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean j() {
        return this.o;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean k() {
        return this.q;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void l() {
        this.o = true;
        this.f2433a.setSupportBackgroundTintList(this.j);
        this.f2433a.setSupportBackgroundTintMode(this.i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(ColorStateList colorStateList) {
        if (this.k != colorStateList) {
            this.k = colorStateList;
            o();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void c(int i) {
        if (this.h != i) {
            this.h = i;
            o();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(int i) {
        if (this.p && this.g == i) {
            return;
        }
        this.g = i;
        this.p = true;
        a(this.f2434b.a(i));
    }

    private g c(boolean z) {
        LayerDrawable layerDrawable = this.r;
        if (layerDrawable == null || layerDrawable.getNumberOfLayers() <= 0) {
            return null;
        }
        if (s) {
            return (g) ((LayerDrawable) ((InsetDrawable) this.r.getDrawable(0)).getDrawable()).getDrawable(!z ? 1 : 0);
        }
        return (g) this.r.getDrawable(!z ? 1 : 0);
    }

    private void b(k kVar) {
        if (c() != null) {
            c().setShapeAppearanceModel(kVar);
        }
        if (n() != null) {
            n().setShapeAppearanceModel(kVar);
        }
        if (b() != null) {
            b().setShapeAppearanceModel(kVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public g c() {
        return c(false);
    }

    public n b() {
        LayerDrawable layerDrawable = this.r;
        if (layerDrawable == null || layerDrawable.getNumberOfLayers() <= 1) {
            return null;
        }
        if (this.r.getNumberOfLayers() > 2) {
            return (n) this.r.getDrawable(2);
        }
        return (n) this.r.getDrawable(1);
    }

    private InsetDrawable a(Drawable drawable) {
        return new InsetDrawable(drawable, this.f2435c, this.f2437e, this.f2436d, this.f2438f);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(PorterDuff.Mode mode) {
        if (this.i != mode) {
            this.i = mode;
            if (c() == null || this.i == null) {
                return;
            }
            androidx.core.graphics.drawable.a.a(c(), this.i);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(int i, int i2) {
        Drawable drawable = this.m;
        if (drawable != null) {
            drawable.setBounds(this.f2435c, this.f2437e, i2 - this.f2436d, i - this.f2438f);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(int i) {
        if (c() != null) {
            c().setTint(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(ColorStateList colorStateList) {
        if (this.l != colorStateList) {
            this.l = colorStateList;
            if (s && (this.f2433a.getBackground() instanceof RippleDrawable)) {
                ((RippleDrawable) this.f2433a.getBackground()).setColor(c.a.a.a.z.b.b(colorStateList));
            } else {
                if (s || !(this.f2433a.getBackground() instanceof c.a.a.a.z.a)) {
                    return;
                }
                ((c.a.a.a.z.a) this.f2433a.getBackground()).setTintList(c.a.a.a.z.b.b(colorStateList));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int a() {
        return this.g;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(boolean z) {
        this.q = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(k kVar) {
        this.f2434b = kVar;
        b(kVar);
    }
}
