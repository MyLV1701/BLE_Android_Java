package com.google.android.material.behavior;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

/* loaded from: classes.dex */
public class HideBottomViewOnScrollBehavior<V extends View> extends CoordinatorLayout.c<V> {

    /* renamed from: a, reason: collision with root package name */
    private int f2372a;

    /* renamed from: b, reason: collision with root package name */
    private int f2373b;

    /* renamed from: c, reason: collision with root package name */
    private int f2374c;

    /* renamed from: d, reason: collision with root package name */
    private ViewPropertyAnimator f2375d;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a extends AnimatorListenerAdapter {
        a() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            HideBottomViewOnScrollBehavior.this.f2375d = null;
        }
    }

    public HideBottomViewOnScrollBehavior() {
        this.f2372a = 0;
        this.f2373b = 2;
        this.f2374c = 0;
    }

    public void b(V v) {
        if (this.f2373b == 2) {
            return;
        }
        ViewPropertyAnimator viewPropertyAnimator = this.f2375d;
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.cancel();
            v.clearAnimation();
        }
        this.f2373b = 2;
        a((HideBottomViewOnScrollBehavior<V>) v, 0, 225L, c.a.a.a.m.a.f2096d);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean b(CoordinatorLayout coordinatorLayout, V v, View view, View view2, int i, int i2) {
        return i == 2;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean a(CoordinatorLayout coordinatorLayout, V v, int i) {
        this.f2372a = v.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) v.getLayoutParams()).bottomMargin;
        return super.a(coordinatorLayout, (CoordinatorLayout) v, i);
    }

    public HideBottomViewOnScrollBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2372a = 0;
        this.f2373b = 2;
        this.f2374c = 0;
    }

    public void a(V v, int i) {
        this.f2374c = i;
        if (this.f2373b == 1) {
            v.setTranslationY(this.f2372a + this.f2374c);
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public void a(CoordinatorLayout coordinatorLayout, V v, View view, int i, int i2, int i3, int i4, int i5, int[] iArr) {
        if (i2 > 0) {
            a((HideBottomViewOnScrollBehavior<V>) v);
        } else if (i2 < 0) {
            b(v);
        }
    }

    public void a(V v) {
        if (this.f2373b == 1) {
            return;
        }
        ViewPropertyAnimator viewPropertyAnimator = this.f2375d;
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.cancel();
            v.clearAnimation();
        }
        this.f2373b = 1;
        a((HideBottomViewOnScrollBehavior<V>) v, this.f2372a + this.f2374c, 175L, c.a.a.a.m.a.f2095c);
    }

    private void a(V v, int i, long j, TimeInterpolator timeInterpolator) {
        this.f2375d = v.animate().translationY(i).setInterpolator(timeInterpolator).setDuration(j).setListener(new a());
    }
}
