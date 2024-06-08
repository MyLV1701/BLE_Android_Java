package com.google.android.material.floatingactionbutton;

import a.f.l.w;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import c.a.a.a.l;
import c.a.a.a.m.h;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.button.MaterialButton;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class ExtendedFloatingActionButton extends MaterialButton implements CoordinatorLayout.b {
    private final com.google.android.material.floatingactionbutton.d s;
    private final com.google.android.material.floatingactionbutton.d t;
    private final com.google.android.material.floatingactionbutton.d u;
    private final com.google.android.material.floatingactionbutton.d v;
    private final CoordinatorLayout.c<ExtendedFloatingActionButton> w;
    private boolean x;

    /* loaded from: classes.dex */
    protected static class ExtendedFloatingActionButtonBehavior<T extends ExtendedFloatingActionButton> extends CoordinatorLayout.c<T> {

        /* renamed from: a, reason: collision with root package name */
        private Rect f2515a;

        /* renamed from: b, reason: collision with root package name */
        private d f2516b;

        /* renamed from: c, reason: collision with root package name */
        private d f2517c;

        /* renamed from: d, reason: collision with root package name */
        private boolean f2518d;

        /* renamed from: e, reason: collision with root package name */
        private boolean f2519e;

        public ExtendedFloatingActionButtonBehavior() {
            this.f2518d = false;
            this.f2519e = true;
        }

        private boolean b(View view, ExtendedFloatingActionButton extendedFloatingActionButton) {
            if (!a(view, extendedFloatingActionButton)) {
                return false;
            }
            if (view.getTop() < (extendedFloatingActionButton.getHeight() / 2) + ((ViewGroup.MarginLayoutParams) ((CoordinatorLayout.f) extendedFloatingActionButton.getLayoutParams())).topMargin) {
                b(extendedFloatingActionButton);
                return true;
            }
            a(extendedFloatingActionButton);
            return true;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
        public boolean a(CoordinatorLayout coordinatorLayout, ExtendedFloatingActionButton extendedFloatingActionButton, Rect rect) {
            return super.a(coordinatorLayout, (CoordinatorLayout) extendedFloatingActionButton, rect);
        }

        public ExtendedFloatingActionButtonBehavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, l.ExtendedFloatingActionButton_Behavior_Layout);
            this.f2518d = obtainStyledAttributes.getBoolean(l.ExtendedFloatingActionButton_Behavior_Layout_behavior_autoHide, false);
            this.f2519e = obtainStyledAttributes.getBoolean(l.ExtendedFloatingActionButton_Behavior_Layout_behavior_autoShrink, true);
            obtainStyledAttributes.recycle();
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
        public void a(CoordinatorLayout.f fVar) {
            if (fVar.h == 0) {
                fVar.h = 80;
            }
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public boolean b(CoordinatorLayout coordinatorLayout, ExtendedFloatingActionButton extendedFloatingActionButton, View view) {
            if (view instanceof AppBarLayout) {
                a(coordinatorLayout, (AppBarLayout) view, extendedFloatingActionButton);
                return false;
            }
            if (!a(view)) {
                return false;
            }
            b(view, extendedFloatingActionButton);
            return false;
        }

        protected void b(ExtendedFloatingActionButton extendedFloatingActionButton) {
            extendedFloatingActionButton.a(this.f2519e ? extendedFloatingActionButton.s : extendedFloatingActionButton.v, this.f2519e ? this.f2517c : this.f2516b);
        }

        private static boolean a(View view) {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams instanceof CoordinatorLayout.f) {
                return ((CoordinatorLayout.f) layoutParams).d() instanceof BottomSheetBehavior;
            }
            return false;
        }

        private boolean a(View view, ExtendedFloatingActionButton extendedFloatingActionButton) {
            return (this.f2518d || this.f2519e) && ((CoordinatorLayout.f) extendedFloatingActionButton.getLayoutParams()).c() == view.getId();
        }

        private boolean a(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, ExtendedFloatingActionButton extendedFloatingActionButton) {
            if (!a(appBarLayout, extendedFloatingActionButton)) {
                return false;
            }
            if (this.f2515a == null) {
                this.f2515a = new Rect();
            }
            Rect rect = this.f2515a;
            com.google.android.material.internal.b.a(coordinatorLayout, appBarLayout, rect);
            if (rect.bottom <= appBarLayout.getMinimumHeightForVisibleOverlappingContent()) {
                b(extendedFloatingActionButton);
                return true;
            }
            a(extendedFloatingActionButton);
            return true;
        }

        protected void a(ExtendedFloatingActionButton extendedFloatingActionButton) {
            extendedFloatingActionButton.a(this.f2519e ? extendedFloatingActionButton.t : extendedFloatingActionButton.u, this.f2519e ? this.f2517c : this.f2516b);
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
        public boolean a(CoordinatorLayout coordinatorLayout, ExtendedFloatingActionButton extendedFloatingActionButton, int i) {
            List<View> b2 = coordinatorLayout.b(extendedFloatingActionButton);
            int size = b2.size();
            for (int i2 = 0; i2 < size; i2++) {
                View view = b2.get(i2);
                if (view instanceof AppBarLayout) {
                    if (a(coordinatorLayout, (AppBarLayout) view, extendedFloatingActionButton)) {
                        break;
                    }
                } else {
                    if (a(view) && b(view, extendedFloatingActionButton)) {
                        break;
                    }
                }
            }
            coordinatorLayout.c(extendedFloatingActionButton, i);
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a extends AnimatorListenerAdapter {

        /* renamed from: a, reason: collision with root package name */
        private boolean f2520a;

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ com.google.android.material.floatingactionbutton.d f2521b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ d f2522c;

        a(ExtendedFloatingActionButton extendedFloatingActionButton, com.google.android.material.floatingactionbutton.d dVar, d dVar2) {
            this.f2521b = dVar;
            this.f2522c = dVar2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f2520a = true;
            this.f2521b.b();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f2521b.a();
            if (this.f2520a) {
                return;
            }
            this.f2521b.a(this.f2522c);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            this.f2521b.onAnimationStart(animator);
            this.f2520a = false;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class d {
    }

    static {
        new b(Float.class, "width");
        new c(Float.class, "height");
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.b
    public CoordinatorLayout.c<ExtendedFloatingActionButton> getBehavior() {
        return this.w;
    }

    int getCollapsedSize() {
        return (Math.min(w.v(this), w.u(this)) * 2) + getIconSize();
    }

    public h getExtendMotionSpec() {
        return this.t.d();
    }

    public h getHideMotionSpec() {
        return this.v.d();
    }

    public h getShowMotionSpec() {
        return this.u.d();
    }

    public h getShrinkMotionSpec() {
        return this.s.d();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.material.button.MaterialButton, android.widget.TextView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.x && TextUtils.isEmpty(getText()) && getIcon() != null) {
            this.x = false;
            this.s.c();
        }
    }

    public void setExtendMotionSpec(h hVar) {
        this.t.a(hVar);
    }

    public void setExtendMotionSpecResource(int i) {
        setExtendMotionSpec(h.a(getContext(), i));
    }

    public void setExtended(boolean z) {
        if (this.x == z) {
            return;
        }
        com.google.android.material.floatingactionbutton.d dVar = z ? this.t : this.s;
        if (dVar.g()) {
            return;
        }
        dVar.c();
    }

    public void setHideMotionSpec(h hVar) {
        this.v.a(hVar);
    }

    public void setHideMotionSpecResource(int i) {
        setHideMotionSpec(h.a(getContext(), i));
    }

    public void setShowMotionSpec(h hVar) {
        this.u.a(hVar);
    }

    public void setShowMotionSpecResource(int i) {
        setShowMotionSpec(h.a(getContext(), i));
    }

    public void setShrinkMotionSpec(h hVar) {
        this.s.a(hVar);
    }

    public void setShrinkMotionSpecResource(int i) {
        setShrinkMotionSpec(h.a(getContext(), i));
    }

    /* loaded from: classes.dex */
    static class b extends Property<View, Float> {
        b(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void set(View view, Float f2) {
            view.getLayoutParams().width = f2.intValue();
            view.requestLayout();
        }

        @Override // android.util.Property
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Float get(View view) {
            return Float.valueOf(view.getLayoutParams().width);
        }
    }

    /* loaded from: classes.dex */
    static class c extends Property<View, Float> {
        c(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void set(View view, Float f2) {
            view.getLayoutParams().height = f2.intValue();
            view.requestLayout();
        }

        @Override // android.util.Property
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Float get(View view) {
            return Float.valueOf(view.getLayoutParams().height);
        }
    }

    private boolean b() {
        return w.F(this) && !isInEditMode();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(com.google.android.material.floatingactionbutton.d dVar, d dVar2) {
        if (dVar.g()) {
            return;
        }
        if (!b()) {
            dVar.c();
            dVar.a(dVar2);
            return;
        }
        measure(0, 0);
        AnimatorSet e2 = dVar.e();
        e2.addListener(new a(this, dVar, dVar2));
        Iterator<Animator.AnimatorListener> it = dVar.f().iterator();
        while (it.hasNext()) {
            e2.addListener(it.next());
        }
        e2.start();
    }
}
