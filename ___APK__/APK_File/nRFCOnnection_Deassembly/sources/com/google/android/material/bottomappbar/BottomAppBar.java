package com.google.android.material.bottomappbar;

import a.f.l.w;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import c.a.a.a.b0.h;
import c.a.a.a.m.k;
import com.google.android.material.behavior.HideBottomViewOnScrollBehavior;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class BottomAppBar extends Toolbar implements CoordinatorLayout.b {
    private final int Q;
    private final c.a.a.a.b0.g R;
    private Animator S;
    private Animator T;
    private int U;
    private int V;
    private boolean W;
    private int a0;
    private ArrayList<f> b0;
    private boolean c0;
    private Behavior d0;
    private int e0;
    AnimatorListenerAdapter f0;
    k<FloatingActionButton> g0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a extends AnimatorListenerAdapter {
        a() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            BottomAppBar.this.m();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            BottomAppBar.this.n();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class b extends FloatingActionButton.b {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f2394a;

        /* loaded from: classes.dex */
        class a extends FloatingActionButton.b {
            a() {
            }

            @Override // com.google.android.material.floatingactionbutton.FloatingActionButton.b
            public void b(FloatingActionButton floatingActionButton) {
                BottomAppBar.this.m();
            }
        }

        b(int i) {
            this.f2394a = i;
        }

        @Override // com.google.android.material.floatingactionbutton.FloatingActionButton.b
        public void a(FloatingActionButton floatingActionButton) {
            floatingActionButton.setTranslationX(BottomAppBar.this.c(this.f2394a));
            floatingActionButton.b(new a());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class c extends AnimatorListenerAdapter {
        c() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            BottomAppBar.this.m();
            BottomAppBar.this.T = null;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            BottomAppBar.this.n();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class d extends AnimatorListenerAdapter {

        /* renamed from: a, reason: collision with root package name */
        public boolean f2398a;

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ ActionMenuView f2399b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ int f2400c;

        /* renamed from: d, reason: collision with root package name */
        final /* synthetic */ boolean f2401d;

        d(ActionMenuView actionMenuView, int i, boolean z) {
            this.f2399b = actionMenuView;
            this.f2400c = i;
            this.f2401d = z;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f2398a = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            if (this.f2398a) {
                return;
            }
            BottomAppBar.this.b(this.f2399b, this.f2400c, this.f2401d);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class e extends AnimatorListenerAdapter {
        e() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            BottomAppBar.this.f0.onAnimationStart(animator);
            FloatingActionButton o = BottomAppBar.this.o();
            if (o != null) {
                o.setTranslationX(BottomAppBar.this.getFabTranslationX());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface f {
        void a(BottomAppBar bottomAppBar);

        void b(BottomAppBar bottomAppBar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class g extends a.h.a.a {
        public static final Parcelable.Creator<g> CREATOR = new a();

        /* renamed from: d, reason: collision with root package name */
        int f2404d;

        /* renamed from: e, reason: collision with root package name */
        boolean f2405e;

        /* loaded from: classes.dex */
        static class a implements Parcelable.ClassLoaderCreator<g> {
            a() {
            }

            @Override // android.os.Parcelable.Creator
            public g[] newArray(int i) {
                return new g[i];
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.ClassLoaderCreator
            public g createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new g(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            public g createFromParcel(Parcel parcel) {
                return new g(parcel, null);
            }
        }

        public g(Parcelable parcelable) {
            super(parcelable);
        }

        @Override // a.h.a.a, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.f2404d);
            parcel.writeInt(this.f2405e ? 1 : 0);
        }

        public g(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f2404d = parcel.readInt();
            this.f2405e = parcel.readInt() != 0;
        }
    }

    private ActionMenuView getActionMenuView() {
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (childAt instanceof ActionMenuView) {
                return (ActionMenuView) childAt;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getBottomInset() {
        return this.e0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float getFabTranslationX() {
        return c(this.U);
    }

    private float getFabTranslationY() {
        return -getTopEdgeTreatment().a();
    }

    private com.google.android.material.bottomappbar.a getTopEdgeTreatment() {
        return (com.google.android.material.bottomappbar.a) this.R.k().h();
    }

    private void l() {
        Animator animator = this.T;
        if (animator != null) {
            animator.cancel();
        }
        Animator animator2 = this.S;
        if (animator2 != null) {
            animator2.cancel();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        ArrayList<f> arrayList;
        int i = this.a0 - 1;
        this.a0 = i;
        if (i != 0 || (arrayList = this.b0) == null) {
            return;
        }
        Iterator<f> it = arrayList.iterator();
        while (it.hasNext()) {
            it.next().a(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        ArrayList<f> arrayList;
        int i = this.a0;
        this.a0 = i + 1;
        if (i != 0 || (arrayList = this.b0) == null) {
            return;
        }
        Iterator<f> it = arrayList.iterator();
        while (it.hasNext()) {
            it.next().b(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public FloatingActionButton o() {
        View p = p();
        if (p instanceof FloatingActionButton) {
            return (FloatingActionButton) p;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public View p() {
        if (!(getParent() instanceof CoordinatorLayout)) {
            return null;
        }
        for (View view : ((CoordinatorLayout) getParent()).c(this)) {
            if ((view instanceof FloatingActionButton) || (view instanceof ExtendedFloatingActionButton)) {
                return view;
            }
        }
        return null;
    }

    private boolean q() {
        FloatingActionButton o = o();
        return o != null && o.d();
    }

    private void r() {
        ActionMenuView actionMenuView = getActionMenuView();
        if (actionMenuView != null) {
            actionMenuView.setAlpha(1.0f);
            if (!q()) {
                b(actionMenuView, 0, false);
            } else {
                b(actionMenuView, this.U, this.c0);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s() {
        getTopEdgeTreatment().e(getFabTranslationX());
        View p = p();
        this.R.c((this.c0 && q()) ? 1.0f : 0.0f);
        if (p != null) {
            p.setTranslationY(getFabTranslationY());
            p.setTranslationX(getFabTranslationX());
        }
    }

    public ColorStateList getBackgroundTint() {
        return this.R.l();
    }

    public float getCradleVerticalOffset() {
        return getTopEdgeTreatment().a();
    }

    public int getFabAlignmentMode() {
        return this.U;
    }

    public int getFabAnimationMode() {
        return this.V;
    }

    public float getFabCradleMargin() {
        return getTopEdgeTreatment().b();
    }

    public float getFabCradleRoundedCornerRadius() {
        return getTopEdgeTreatment().c();
    }

    public boolean getHideOnScroll() {
        return this.W;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        h.a(this, this.R);
        if (getParent() instanceof ViewGroup) {
            ((ViewGroup) getParent()).setClipChildren(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.widget.Toolbar, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (z) {
            l();
            s();
        }
        r();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.widget.Toolbar, android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof g)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        g gVar = (g) parcelable;
        super.onRestoreInstanceState(gVar.d());
        this.U = gVar.f2404d;
        this.c0 = gVar.f2405e;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.widget.Toolbar, android.view.View
    public Parcelable onSaveInstanceState() {
        g gVar = new g(super.onSaveInstanceState());
        gVar.f2404d = this.U;
        gVar.f2405e = this.c0;
        return gVar;
    }

    public void setBackgroundTint(ColorStateList colorStateList) {
        androidx.core.graphics.drawable.a.a(this.R, colorStateList);
    }

    public void setCradleVerticalOffset(float f2) {
        if (f2 != getCradleVerticalOffset()) {
            getTopEdgeTreatment().a(f2);
            this.R.invalidateSelf();
            s();
        }
    }

    @Override // android.view.View
    public void setElevation(float f2) {
        this.R.b(f2);
        getBehavior().a((Behavior) this, this.R.j() - this.R.i());
    }

    public void setFabAlignmentMode(int i) {
        d(i);
        a(i, this.c0);
        this.U = i;
    }

    public void setFabAnimationMode(int i) {
        this.V = i;
    }

    public void setFabCradleMargin(float f2) {
        if (f2 != getFabCradleMargin()) {
            getTopEdgeTreatment().b(f2);
            this.R.invalidateSelf();
        }
    }

    public void setFabCradleRoundedCornerRadius(float f2) {
        if (f2 != getFabCradleRoundedCornerRadius()) {
            getTopEdgeTreatment().c(f2);
            this.R.invalidateSelf();
        }
    }

    public void setHideOnScroll(boolean z) {
        this.W = z;
    }

    @Override // androidx.appcompat.widget.Toolbar
    public void setSubtitle(CharSequence charSequence) {
    }

    @Override // androidx.appcompat.widget.Toolbar
    public void setTitle(CharSequence charSequence) {
    }

    /* loaded from: classes.dex */
    public static class Behavior extends HideBottomViewOnScrollBehavior<BottomAppBar> {

        /* renamed from: e, reason: collision with root package name */
        private final Rect f2390e;

        /* renamed from: f, reason: collision with root package name */
        private WeakReference<BottomAppBar> f2391f;
        private int g;
        private final View.OnLayoutChangeListener h;

        /* loaded from: classes.dex */
        class a implements View.OnLayoutChangeListener {
            a() {
            }

            @Override // android.view.View.OnLayoutChangeListener
            public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                BottomAppBar bottomAppBar = (BottomAppBar) Behavior.this.f2391f.get();
                if (bottomAppBar != null && (view instanceof FloatingActionButton)) {
                    FloatingActionButton floatingActionButton = (FloatingActionButton) view;
                    floatingActionButton.b(Behavior.this.f2390e);
                    int height = Behavior.this.f2390e.height();
                    bottomAppBar.b(height);
                    CoordinatorLayout.f fVar = (CoordinatorLayout.f) view.getLayoutParams();
                    if (Behavior.this.g == 0) {
                        ((ViewGroup.MarginLayoutParams) fVar).bottomMargin = bottomAppBar.getBottomInset() + (bottomAppBar.getResources().getDimensionPixelOffset(c.a.a.a.d.mtrl_bottomappbar_fab_bottom_margin) - ((floatingActionButton.getMeasuredHeight() - height) / 2));
                        return;
                    }
                    return;
                }
                view.removeOnLayoutChangeListener(this);
            }
        }

        public Behavior() {
            this.h = new a();
            this.f2390e = new Rect();
        }

        @Override // com.google.android.material.behavior.HideBottomViewOnScrollBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.c
        public boolean a(CoordinatorLayout coordinatorLayout, BottomAppBar bottomAppBar, int i) {
            this.f2391f = new WeakReference<>(bottomAppBar);
            View p = bottomAppBar.p();
            if (p != null && !w.F(p)) {
                CoordinatorLayout.f fVar = (CoordinatorLayout.f) p.getLayoutParams();
                fVar.f1172d = 49;
                this.g = ((ViewGroup.MarginLayoutParams) fVar).bottomMargin;
                if (p instanceof FloatingActionButton) {
                    FloatingActionButton floatingActionButton = (FloatingActionButton) p;
                    floatingActionButton.addOnLayoutChangeListener(this.h);
                    bottomAppBar.a(floatingActionButton);
                }
                bottomAppBar.s();
            }
            coordinatorLayout.c(bottomAppBar, i);
            return super.a(coordinatorLayout, (CoordinatorLayout) bottomAppBar, i);
        }

        public Behavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.h = new a();
            this.f2390e = new Rect();
        }

        @Override // com.google.android.material.behavior.HideBottomViewOnScrollBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.c
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public boolean b(CoordinatorLayout coordinatorLayout, BottomAppBar bottomAppBar, View view, View view2, int i, int i2) {
            return bottomAppBar.getHideOnScroll() && super.b(coordinatorLayout, bottomAppBar, view, view2, i, i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float c(int i) {
        boolean z = w.q(this) == 1;
        if (i == 1) {
            return ((getMeasuredWidth() / 2) - this.Q) * (z ? -1 : 1);
        }
        return 0.0f;
    }

    private void d(int i) {
        if (this.U == i || !w.F(this)) {
            return;
        }
        Animator animator = this.S;
        if (animator != null) {
            animator.cancel();
        }
        ArrayList arrayList = new ArrayList();
        if (this.V == 1) {
            b(i, arrayList);
        } else {
            a(i, arrayList);
        }
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(arrayList);
        this.S = animatorSet;
        this.S.addListener(new a());
        this.S.start();
    }

    boolean b(int i) {
        float f2 = i;
        if (f2 == getTopEdgeTreatment().d()) {
            return false;
        }
        getTopEdgeTreatment().d(f2);
        this.R.invalidateSelf();
        return true;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.b
    public Behavior getBehavior() {
        if (this.d0 == null) {
            this.d0 = new Behavior();
        }
        return this.d0;
    }

    private void b(int i, List<Animator> list) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(o(), "translationX", c(i));
        ofFloat.setDuration(300L);
        list.add(ofFloat);
    }

    protected void a(int i, List<Animator> list) {
        FloatingActionButton o = o();
        if (o == null || o.c()) {
            return;
        }
        n();
        o.a(new b(i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(ActionMenuView actionMenuView, int i, boolean z) {
        actionMenuView.setTranslationX(a(actionMenuView, i, z));
    }

    private void a(int i, boolean z) {
        if (w.F(this)) {
            Animator animator = this.T;
            if (animator != null) {
                animator.cancel();
            }
            ArrayList arrayList = new ArrayList();
            if (!q()) {
                i = 0;
                z = false;
            }
            a(i, z, arrayList);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(arrayList);
            this.T = animatorSet;
            this.T.addListener(new c());
            this.T.start();
        }
    }

    private void a(int i, boolean z, List<Animator> list) {
        ActionMenuView actionMenuView = getActionMenuView();
        if (actionMenuView == null) {
            return;
        }
        Animator ofFloat = ObjectAnimator.ofFloat(actionMenuView, "alpha", 1.0f);
        if (Math.abs(actionMenuView.getTranslationX() - a(actionMenuView, i, z)) <= 1.0f) {
            if (actionMenuView.getAlpha() < 1.0f) {
                list.add(ofFloat);
            }
        } else {
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(actionMenuView, "alpha", 0.0f);
            ofFloat2.addListener(new d(actionMenuView, i, z));
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.setDuration(150L);
            animatorSet.playSequentially(ofFloat2, ofFloat);
            list.add(animatorSet);
        }
    }

    protected int a(ActionMenuView actionMenuView, int i, boolean z) {
        boolean z2 = w.q(this) == 1;
        int measuredWidth = z2 ? getMeasuredWidth() : 0;
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            View childAt = getChildAt(i2);
            if ((childAt.getLayoutParams() instanceof Toolbar.e) && (((Toolbar.e) childAt.getLayoutParams()).f651a & 8388615) == 8388611) {
                if (z2) {
                    measuredWidth = Math.min(measuredWidth, childAt.getLeft());
                } else {
                    measuredWidth = Math.max(measuredWidth, childAt.getRight());
                }
            }
        }
        int right = measuredWidth - (z2 ? actionMenuView.getRight() : actionMenuView.getLeft());
        if (i == 1 && z) {
            return right;
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(FloatingActionButton floatingActionButton) {
        floatingActionButton.a(this.f0);
        floatingActionButton.b(new e());
        floatingActionButton.a(this.g0);
    }
}
