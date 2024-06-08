package com.google.android.material.floatingactionbutton;

import a.f.k.h;
import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.StateListAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.util.Property;
import android.view.View;
import c.a.a.a.b0.g;
import c.a.a.a.b0.k;
import java.util.ArrayList;

/* loaded from: classes.dex */
class c extends b {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class a extends g {
        a(k kVar) {
            super(kVar);
        }

        @Override // c.a.a.a.b0.g, android.graphics.drawable.Drawable
        public boolean isStateful() {
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public c(FloatingActionButton floatingActionButton, c.a.a.a.a0.b bVar) {
        super(floatingActionButton, bVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.material.floatingactionbutton.b
    public void a(ColorStateList colorStateList, PorterDuff.Mode mode, ColorStateList colorStateList2, int i) {
        Drawable drawable;
        this.f2543b = a();
        this.f2543b.setTintList(colorStateList);
        if (mode != null) {
            this.f2543b.setTintMode(mode);
        }
        this.f2543b.a(this.y.getContext());
        if (i > 0) {
            this.f2545d = a(i, colorStateList);
            com.google.android.material.floatingactionbutton.a aVar = this.f2545d;
            h.a(aVar);
            g gVar = this.f2543b;
            h.a(gVar);
            drawable = new LayerDrawable(new Drawable[]{aVar, gVar});
        } else {
            this.f2545d = null;
            drawable = this.f2543b;
        }
        this.f2544c = new RippleDrawable(c.a.a.a.z.b.b(colorStateList2), drawable, null);
        this.f2546e = this.f2544c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.material.floatingactionbutton.b
    public void b(ColorStateList colorStateList) {
        Drawable drawable = this.f2544c;
        if (drawable instanceof RippleDrawable) {
            ((RippleDrawable) drawable).setColor(c.a.a.a.z.b.b(colorStateList));
        } else {
            super.b(colorStateList);
        }
    }

    @Override // com.google.android.material.floatingactionbutton.b
    public float c() {
        return this.y.getElevation();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.material.floatingactionbutton.b
    public void l() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.material.floatingactionbutton.b
    public void n() {
        x();
    }

    @Override // com.google.android.material.floatingactionbutton.b
    boolean s() {
        return false;
    }

    @Override // com.google.android.material.floatingactionbutton.b
    boolean t() {
        return this.z.a() || !u();
    }

    @Override // com.google.android.material.floatingactionbutton.b
    void v() {
    }

    @Override // com.google.android.material.floatingactionbutton.b
    void a(float f2, float f3, float f4) {
        if (Build.VERSION.SDK_INT == 21) {
            this.y.refreshDrawableState();
        } else {
            StateListAnimator stateListAnimator = new StateListAnimator();
            stateListAnimator.addState(b.G, a(f2, f4));
            stateListAnimator.addState(b.H, a(f2, f3));
            stateListAnimator.addState(b.I, a(f2, f3));
            stateListAnimator.addState(b.J, a(f2, f3));
            AnimatorSet animatorSet = new AnimatorSet();
            ArrayList arrayList = new ArrayList();
            arrayList.add(ObjectAnimator.ofFloat(this.y, "elevation", f2).setDuration(0L));
            int i = Build.VERSION.SDK_INT;
            if (i >= 22 && i <= 24) {
                FloatingActionButton floatingActionButton = this.y;
                arrayList.add(ObjectAnimator.ofFloat(floatingActionButton, (Property<FloatingActionButton, Float>) View.TRANSLATION_Z, floatingActionButton.getTranslationZ()).setDuration(100L));
            }
            arrayList.add(ObjectAnimator.ofFloat(this.y, (Property<FloatingActionButton, Float>) View.TRANSLATION_Z, 0.0f).setDuration(100L));
            animatorSet.playSequentially((Animator[]) arrayList.toArray(new Animator[0]));
            animatorSet.setInterpolator(b.F);
            stateListAnimator.addState(b.K, animatorSet);
            stateListAnimator.addState(b.L, a(0.0f, 0.0f));
            this.y.setStateListAnimator(stateListAnimator);
        }
        if (t()) {
            x();
        }
    }

    private Animator a(float f2, float f3) {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(ObjectAnimator.ofFloat(this.y, "elevation", f2).setDuration(0L)).with(ObjectAnimator.ofFloat(this.y, (Property<FloatingActionButton, Float>) View.TRANSLATION_Z, f3).setDuration(100L));
        animatorSet.setInterpolator(b.F);
        return animatorSet;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.material.floatingactionbutton.b
    public void a(int[] iArr) {
        if (Build.VERSION.SDK_INT == 21) {
            if (this.y.isEnabled()) {
                this.y.setElevation(this.h);
                if (this.y.isPressed()) {
                    this.y.setTranslationZ(this.j);
                    return;
                } else if (!this.y.isFocused() && !this.y.isHovered()) {
                    this.y.setTranslationZ(0.0f);
                    return;
                } else {
                    this.y.setTranslationZ(this.i);
                    return;
                }
            }
            this.y.setElevation(0.0f);
            this.y.setTranslationZ(0.0f);
        }
    }

    com.google.android.material.floatingactionbutton.a a(int i, ColorStateList colorStateList) {
        Context context = this.y.getContext();
        k kVar = this.f2542a;
        h.a(kVar);
        com.google.android.material.floatingactionbutton.a aVar = new com.google.android.material.floatingactionbutton.a(kVar);
        aVar.a(a.f.d.b.a(context, c.a.a.a.c.design_fab_stroke_top_outer_color), a.f.d.b.a(context, c.a.a.a.c.design_fab_stroke_top_inner_color), a.f.d.b.a(context, c.a.a.a.c.design_fab_stroke_end_inner_color), a.f.d.b.a(context, c.a.a.a.c.design_fab_stroke_end_outer_color));
        aVar.a(i);
        aVar.a(colorStateList);
        return aVar;
    }

    @Override // com.google.android.material.floatingactionbutton.b
    g a() {
        k kVar = this.f2542a;
        h.a(kVar);
        return new a(kVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.material.floatingactionbutton.b
    public void a(Rect rect) {
        if (this.z.a()) {
            super.a(rect);
        } else if (!u()) {
            int sizeDimension = (this.k - this.y.getSizeDimension()) / 2;
            rect.set(sizeDimension, sizeDimension, sizeDimension, sizeDimension);
        } else {
            rect.set(0, 0, 0, 0);
        }
    }
}
