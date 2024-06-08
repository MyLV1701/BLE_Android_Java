package com.google.android.material.floatingactionbutton;

import a.f.l.v;
import a.f.l.w;
import android.animation.Animator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.appcompat.widget.j;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.widget.l;
import c.a.a.a.k;
import c.a.a.a.m.h;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.floatingactionbutton.b;
import com.google.android.material.internal.m;
import com.google.android.material.internal.n;
import java.util.List;

/* loaded from: classes.dex */
public class FloatingActionButton extends n implements v, l, c.a.a.a.v.a, c.a.a.a.b0.n, CoordinatorLayout.b {
    private static final int s = k.Widget_Design_FloatingActionButton;

    /* renamed from: c, reason: collision with root package name */
    private ColorStateList f2523c;

    /* renamed from: d, reason: collision with root package name */
    private PorterDuff.Mode f2524d;

    /* renamed from: e, reason: collision with root package name */
    private ColorStateList f2525e;

    /* renamed from: f, reason: collision with root package name */
    private PorterDuff.Mode f2526f;
    private ColorStateList g;
    private int h;
    private int i;
    private int j;
    private int k;
    private int l;
    boolean m;
    final Rect n;
    private final Rect o;
    private final androidx.appcompat.widget.n p;
    private final c.a.a.a.v.c q;
    private com.google.android.material.floatingactionbutton.b r;

    /* loaded from: classes.dex */
    protected static class BaseBehavior<T extends FloatingActionButton> extends CoordinatorLayout.c<T> {

        /* renamed from: a, reason: collision with root package name */
        private Rect f2527a;

        /* renamed from: b, reason: collision with root package name */
        private b f2528b;

        /* renamed from: c, reason: collision with root package name */
        private boolean f2529c;

        public BaseBehavior() {
            this.f2529c = true;
        }

        private boolean b(View view, FloatingActionButton floatingActionButton) {
            if (!a(view, floatingActionButton)) {
                return false;
            }
            if (view.getTop() < (floatingActionButton.getHeight() / 2) + ((ViewGroup.MarginLayoutParams) ((CoordinatorLayout.f) floatingActionButton.getLayoutParams())).topMargin) {
                floatingActionButton.a(this.f2528b, false);
                return true;
            }
            floatingActionButton.b(this.f2528b, false);
            return true;
        }

        public BaseBehavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, c.a.a.a.l.FloatingActionButton_Behavior_Layout);
            this.f2529c = obtainStyledAttributes.getBoolean(c.a.a.a.l.FloatingActionButton_Behavior_Layout_behavior_autoHide, true);
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
        public boolean b(CoordinatorLayout coordinatorLayout, FloatingActionButton floatingActionButton, View view) {
            if (view instanceof AppBarLayout) {
                a(coordinatorLayout, (AppBarLayout) view, floatingActionButton);
                return false;
            }
            if (!a(view)) {
                return false;
            }
            b(view, floatingActionButton);
            return false;
        }

        private static boolean a(View view) {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams instanceof CoordinatorLayout.f) {
                return ((CoordinatorLayout.f) layoutParams).d() instanceof BottomSheetBehavior;
            }
            return false;
        }

        private boolean a(View view, FloatingActionButton floatingActionButton) {
            return this.f2529c && ((CoordinatorLayout.f) floatingActionButton.getLayoutParams()).c() == view.getId() && floatingActionButton.getUserSetVisibility() == 0;
        }

        private boolean a(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, FloatingActionButton floatingActionButton) {
            if (!a(appBarLayout, floatingActionButton)) {
                return false;
            }
            if (this.f2527a == null) {
                this.f2527a = new Rect();
            }
            Rect rect = this.f2527a;
            com.google.android.material.internal.b.a(coordinatorLayout, appBarLayout, rect);
            if (rect.bottom <= appBarLayout.getMinimumHeightForVisibleOverlappingContent()) {
                floatingActionButton.a(this.f2528b, false);
                return true;
            }
            floatingActionButton.b(this.f2528b, false);
            return true;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
        public boolean a(CoordinatorLayout coordinatorLayout, FloatingActionButton floatingActionButton, int i) {
            List<View> b2 = coordinatorLayout.b(floatingActionButton);
            int size = b2.size();
            for (int i2 = 0; i2 < size; i2++) {
                View view = b2.get(i2);
                if (view instanceof AppBarLayout) {
                    if (a(coordinatorLayout, (AppBarLayout) view, floatingActionButton)) {
                        break;
                    }
                } else {
                    if (a(view) && b(view, floatingActionButton)) {
                        break;
                    }
                }
            }
            coordinatorLayout.c(floatingActionButton, i);
            a(coordinatorLayout, floatingActionButton);
            return true;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
        public boolean a(CoordinatorLayout coordinatorLayout, FloatingActionButton floatingActionButton, Rect rect) {
            Rect rect2 = floatingActionButton.n;
            rect.set(floatingActionButton.getLeft() + rect2.left, floatingActionButton.getTop() + rect2.top, floatingActionButton.getRight() - rect2.right, floatingActionButton.getBottom() - rect2.bottom);
            return true;
        }

        private void a(CoordinatorLayout coordinatorLayout, FloatingActionButton floatingActionButton) {
            int i;
            Rect rect = floatingActionButton.n;
            if (rect == null || rect.centerX() <= 0 || rect.centerY() <= 0) {
                return;
            }
            CoordinatorLayout.f fVar = (CoordinatorLayout.f) floatingActionButton.getLayoutParams();
            int i2 = 0;
            if (floatingActionButton.getRight() >= coordinatorLayout.getWidth() - ((ViewGroup.MarginLayoutParams) fVar).rightMargin) {
                i = rect.right;
            } else {
                i = floatingActionButton.getLeft() <= ((ViewGroup.MarginLayoutParams) fVar).leftMargin ? -rect.left : 0;
            }
            if (floatingActionButton.getBottom() >= coordinatorLayout.getHeight() - ((ViewGroup.MarginLayoutParams) fVar).bottomMargin) {
                i2 = rect.bottom;
            } else if (floatingActionButton.getTop() <= ((ViewGroup.MarginLayoutParams) fVar).topMargin) {
                i2 = -rect.top;
            }
            if (i2 != 0) {
                w.f(floatingActionButton, i2);
            }
            if (i != 0) {
                w.e(floatingActionButton, i);
            }
        }
    }

    /* loaded from: classes.dex */
    public static class Behavior extends BaseBehavior<FloatingActionButton> {
        public Behavior() {
        }

        public Behavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a implements b.i {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ b f2530a;

        a(b bVar) {
            this.f2530a = bVar;
        }

        @Override // com.google.android.material.floatingactionbutton.b.i
        public void a() {
            this.f2530a.b(FloatingActionButton.this);
        }

        @Override // com.google.android.material.floatingactionbutton.b.i
        public void b() {
            this.f2530a.a(FloatingActionButton.this);
        }
    }

    /* loaded from: classes.dex */
    public static abstract class b {
        public void a(FloatingActionButton floatingActionButton) {
        }

        public void b(FloatingActionButton floatingActionButton) {
        }
    }

    /* loaded from: classes.dex */
    class d<T extends FloatingActionButton> implements b.h {

        /* renamed from: a, reason: collision with root package name */
        private final c.a.a.a.m.k<T> f2533a;

        d(c.a.a.a.m.k<T> kVar) {
            this.f2533a = kVar;
        }

        @Override // com.google.android.material.floatingactionbutton.b.h
        public void a() {
            this.f2533a.a(FloatingActionButton.this);
        }

        @Override // com.google.android.material.floatingactionbutton.b.h
        public void b() {
            this.f2533a.b(FloatingActionButton.this);
        }

        public boolean equals(Object obj) {
            return (obj instanceof d) && ((d) obj).f2533a.equals(this.f2533a);
        }

        public int hashCode() {
            return this.f2533a.hashCode();
        }
    }

    public FloatingActionButton(Context context) {
        this(context, null);
    }

    private b.i c(b bVar) {
        if (bVar == null) {
            return null;
        }
        return new a(bVar);
    }

    private com.google.android.material.floatingactionbutton.b f() {
        if (Build.VERSION.SDK_INT >= 21) {
            return new com.google.android.material.floatingactionbutton.c(this, new c());
        }
        return new com.google.android.material.floatingactionbutton.b(this, new c());
    }

    private void g() {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return;
        }
        ColorStateList colorStateList = this.f2525e;
        if (colorStateList == null) {
            androidx.core.graphics.drawable.a.b(drawable);
            return;
        }
        int colorForState = colorStateList.getColorForState(getDrawableState(), 0);
        PorterDuff.Mode mode = this.f2526f;
        if (mode == null) {
            mode = PorterDuff.Mode.SRC_IN;
        }
        drawable.mutate().setColorFilter(j.a(colorForState, mode));
    }

    private com.google.android.material.floatingactionbutton.b getImpl() {
        if (this.r == null) {
            this.r = f();
        }
        return this.r;
    }

    public void b(b bVar) {
        b(bVar, true);
    }

    public boolean d() {
        return getImpl().k();
    }

    @Override // android.widget.ImageView, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        getImpl().a(getDrawableState());
    }

    public void e() {
        b((b) null);
    }

    @Override // android.view.View
    public ColorStateList getBackgroundTintList() {
        return this.f2523c;
    }

    @Override // android.view.View
    public PorterDuff.Mode getBackgroundTintMode() {
        return this.f2524d;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.b
    public CoordinatorLayout.c<FloatingActionButton> getBehavior() {
        return new Behavior();
    }

    public float getCompatElevation() {
        return getImpl().c();
    }

    public float getCompatHoveredFocusedTranslationZ() {
        return getImpl().f();
    }

    public float getCompatPressedTranslationZ() {
        return getImpl().g();
    }

    public Drawable getContentBackground() {
        return getImpl().b();
    }

    public int getCustomSize() {
        return this.j;
    }

    public int getExpandedComponentIdHint() {
        return this.q.a();
    }

    public h getHideMotionSpec() {
        return getImpl().e();
    }

    @Deprecated
    public int getRippleColor() {
        ColorStateList colorStateList = this.g;
        if (colorStateList != null) {
            return colorStateList.getDefaultColor();
        }
        return 0;
    }

    public ColorStateList getRippleColorStateList() {
        return this.g;
    }

    public c.a.a.a.b0.k getShapeAppearanceModel() {
        c.a.a.a.b0.k h = getImpl().h();
        a.f.k.h.a(h);
        return h;
    }

    public h getShowMotionSpec() {
        return getImpl().i();
    }

    public int getSize() {
        return this.i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getSizeDimension() {
        return a(this.i);
    }

    @Override // a.f.l.v
    public ColorStateList getSupportBackgroundTintList() {
        return getBackgroundTintList();
    }

    @Override // a.f.l.v
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        return getBackgroundTintMode();
    }

    @Override // androidx.core.widget.l
    public ColorStateList getSupportImageTintList() {
        return this.f2525e;
    }

    @Override // androidx.core.widget.l
    public PorterDuff.Mode getSupportImageTintMode() {
        return this.f2526f;
    }

    public boolean getUseCompatPadding() {
        return this.m;
    }

    @Override // android.widget.ImageView, android.view.View
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        getImpl().l();
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        getImpl().m();
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getImpl().o();
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onMeasure(int i, int i2) {
        int sizeDimension = getSizeDimension();
        this.k = (sizeDimension - this.l) / 2;
        getImpl().x();
        int min = Math.min(a(sizeDimension, i), a(sizeDimension, i2));
        Rect rect = this.n;
        setMeasuredDimension(rect.left + min + rect.right, min + rect.top + rect.bottom);
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof c.a.a.a.c0.a)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        c.a.a.a.c0.a aVar = (c.a.a.a.c0.a) parcelable;
        super.onRestoreInstanceState(aVar.d());
        c.a.a.a.v.c cVar = this.q;
        Bundle bundle = aVar.f2092d.get("expandableWidgetHelper");
        a.f.k.h.a(bundle);
        cVar.a(bundle);
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        Parcelable onSaveInstanceState = super.onSaveInstanceState();
        if (onSaveInstanceState == null) {
            onSaveInstanceState = new Bundle();
        }
        c.a.a.a.c0.a aVar = new c.a.a.a.c0.a(onSaveInstanceState);
        aVar.f2092d.put("expandableWidgetHelper", this.q.c());
        return aVar;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0 && a(this.o) && !this.o.contains((int) motionEvent.getX(), (int) motionEvent.getY())) {
            return false;
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override // android.view.View
    public void setBackgroundColor(int i) {
        Log.i("FloatingActionButton", "Setting a custom background is not supported.");
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        Log.i("FloatingActionButton", "Setting a custom background is not supported.");
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        Log.i("FloatingActionButton", "Setting a custom background is not supported.");
    }

    @Override // android.view.View
    public void setBackgroundTintList(ColorStateList colorStateList) {
        if (this.f2523c != colorStateList) {
            this.f2523c = colorStateList;
            getImpl().a(colorStateList);
        }
    }

    @Override // android.view.View
    public void setBackgroundTintMode(PorterDuff.Mode mode) {
        if (this.f2524d != mode) {
            this.f2524d = mode;
            getImpl().a(mode);
        }
    }

    public void setCompatElevation(float f2) {
        getImpl().a(f2);
    }

    public void setCompatElevationResource(int i) {
        setCompatElevation(getResources().getDimension(i));
    }

    public void setCompatHoveredFocusedTranslationZ(float f2) {
        getImpl().b(f2);
    }

    public void setCompatHoveredFocusedTranslationZResource(int i) {
        setCompatHoveredFocusedTranslationZ(getResources().getDimension(i));
    }

    public void setCompatPressedTranslationZ(float f2) {
        getImpl().d(f2);
    }

    public void setCompatPressedTranslationZResource(int i) {
        setCompatPressedTranslationZ(getResources().getDimension(i));
    }

    public void setCustomSize(int i) {
        if (i >= 0) {
            if (i != this.j) {
                this.j = i;
                requestLayout();
                return;
            }
            return;
        }
        throw new IllegalArgumentException("Custom size must be non-negative");
    }

    @Override // android.view.View
    public void setElevation(float f2) {
        super.setElevation(f2);
        getImpl().e(f2);
    }

    public void setEnsureMinTouchTargetSize(boolean z) {
        if (z != getImpl().d()) {
            getImpl().a(z);
            requestLayout();
        }
    }

    public void setExpandedComponentIdHint(int i) {
        this.q.a(i);
    }

    public void setHideMotionSpec(h hVar) {
        getImpl().a(hVar);
    }

    public void setHideMotionSpecResource(int i) {
        setHideMotionSpec(h.a(getContext(), i));
    }

    @Override // android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        if (getDrawable() != drawable) {
            super.setImageDrawable(drawable);
            getImpl().w();
            if (this.f2525e != null) {
                g();
            }
        }
    }

    @Override // android.widget.ImageView
    public void setImageResource(int i) {
        this.p.a(i);
        g();
    }

    public void setRippleColor(int i) {
        setRippleColor(ColorStateList.valueOf(i));
    }

    @Override // android.view.View
    public void setScaleX(float f2) {
        super.setScaleX(f2);
        getImpl().q();
    }

    @Override // android.view.View
    public void setScaleY(float f2) {
        super.setScaleY(f2);
        getImpl().q();
    }

    public void setShadowPaddingEnabled(boolean z) {
        getImpl().b(z);
    }

    @Override // c.a.a.a.b0.n
    public void setShapeAppearanceModel(c.a.a.a.b0.k kVar) {
        getImpl().a(kVar);
    }

    public void setShowMotionSpec(h hVar) {
        getImpl().b(hVar);
    }

    public void setShowMotionSpecResource(int i) {
        setShowMotionSpec(h.a(getContext(), i));
    }

    public void setSize(int i) {
        this.j = 0;
        if (i != this.i) {
            this.i = i;
            requestLayout();
        }
    }

    @Override // a.f.l.v
    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        setBackgroundTintList(colorStateList);
    }

    @Override // a.f.l.v
    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        setBackgroundTintMode(mode);
    }

    @Override // androidx.core.widget.l
    public void setSupportImageTintList(ColorStateList colorStateList) {
        if (this.f2525e != colorStateList) {
            this.f2525e = colorStateList;
            g();
        }
    }

    @Override // androidx.core.widget.l
    public void setSupportImageTintMode(PorterDuff.Mode mode) {
        if (this.f2526f != mode) {
            this.f2526f = mode;
            g();
        }
    }

    @Override // android.view.View
    public void setTranslationX(float f2) {
        super.setTranslationX(f2);
        getImpl().r();
    }

    @Override // android.view.View
    public void setTranslationY(float f2) {
        super.setTranslationY(f2);
        getImpl().r();
    }

    @Override // android.view.View
    public void setTranslationZ(float f2) {
        super.setTranslationZ(f2);
        getImpl().r();
    }

    public void setUseCompatPadding(boolean z) {
        if (this.m != z) {
            this.m = z;
            getImpl().n();
        }
    }

    @Override // com.google.android.material.internal.n, android.widget.ImageView, android.view.View
    public void setVisibility(int i) {
        super.setVisibility(i);
    }

    public FloatingActionButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, c.a.a.a.b.floatingActionButtonStyle);
    }

    void b(b bVar, boolean z) {
        getImpl().b(c(bVar), z);
    }

    public boolean c() {
        return getImpl().j();
    }

    public void setRippleColor(ColorStateList colorStateList) {
        if (this.g != colorStateList) {
            this.g = colorStateList;
            getImpl().b(this.g);
        }
    }

    public FloatingActionButton(Context context, AttributeSet attributeSet, int i) {
        super(com.google.android.material.theme.a.a.b(context, attributeSet, i, s), attributeSet, i);
        this.n = new Rect();
        this.o = new Rect();
        Context context2 = getContext();
        TypedArray c2 = com.google.android.material.internal.l.c(context2, attributeSet, c.a.a.a.l.FloatingActionButton, i, s, new int[0]);
        this.f2523c = c.a.a.a.y.c.a(context2, c2, c.a.a.a.l.FloatingActionButton_backgroundTint);
        this.f2524d = m.a(c2.getInt(c.a.a.a.l.FloatingActionButton_backgroundTintMode, -1), (PorterDuff.Mode) null);
        this.g = c.a.a.a.y.c.a(context2, c2, c.a.a.a.l.FloatingActionButton_rippleColor);
        this.i = c2.getInt(c.a.a.a.l.FloatingActionButton_fabSize, -1);
        this.j = c2.getDimensionPixelSize(c.a.a.a.l.FloatingActionButton_fabCustomSize, 0);
        this.h = c2.getDimensionPixelSize(c.a.a.a.l.FloatingActionButton_borderWidth, 0);
        float dimension = c2.getDimension(c.a.a.a.l.FloatingActionButton_elevation, 0.0f);
        float dimension2 = c2.getDimension(c.a.a.a.l.FloatingActionButton_hoveredFocusedTranslationZ, 0.0f);
        float dimension3 = c2.getDimension(c.a.a.a.l.FloatingActionButton_pressedTranslationZ, 0.0f);
        this.m = c2.getBoolean(c.a.a.a.l.FloatingActionButton_useCompatPadding, false);
        int dimensionPixelSize = getResources().getDimensionPixelSize(c.a.a.a.d.mtrl_fab_min_touch_target);
        this.l = c2.getDimensionPixelSize(c.a.a.a.l.FloatingActionButton_maxImageSize, 0);
        h a2 = h.a(context2, c2, c.a.a.a.l.FloatingActionButton_showMotionSpec);
        h a3 = h.a(context2, c2, c.a.a.a.l.FloatingActionButton_hideMotionSpec);
        c.a.a.a.b0.k a4 = c.a.a.a.b0.k.a(context2, attributeSet, i, s, c.a.a.a.b0.k.m).a();
        boolean z = c2.getBoolean(c.a.a.a.l.FloatingActionButton_ensureMinTouchTargetSize, false);
        c2.recycle();
        this.p = new androidx.appcompat.widget.n(this);
        this.p.a(attributeSet, i);
        this.q = new c.a.a.a.v.c(this);
        getImpl().a(a4);
        getImpl().a(this.f2523c, this.f2524d, this.g, this.h);
        getImpl().b(dimensionPixelSize);
        getImpl().a(dimension);
        getImpl().b(dimension2);
        getImpl().d(dimension3);
        getImpl().a(this.l);
        getImpl().b(a2);
        getImpl().a(a3);
        getImpl().a(z);
        setScaleType(ImageView.ScaleType.MATRIX);
    }

    private void c(Rect rect) {
        int i = rect.left;
        Rect rect2 = this.n;
        rect.left = i + rect2.left;
        rect.top += rect2.top;
        rect.right -= rect2.right;
        rect.bottom -= rect2.bottom;
    }

    public void a(b bVar) {
        a(bVar, true);
    }

    public void b(Animator.AnimatorListener animatorListener) {
        getImpl().b(animatorListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class c implements c.a.a.a.a0.b {
        c() {
        }

        @Override // c.a.a.a.a0.b
        public void a(int i, int i2, int i3, int i4) {
            FloatingActionButton.this.n.set(i, i2, i3, i4);
            FloatingActionButton floatingActionButton = FloatingActionButton.this;
            floatingActionButton.setPadding(i + floatingActionButton.k, i2 + FloatingActionButton.this.k, i3 + FloatingActionButton.this.k, i4 + FloatingActionButton.this.k);
        }

        @Override // c.a.a.a.a0.b
        public void a(Drawable drawable) {
            if (drawable != null) {
                FloatingActionButton.super.setBackgroundDrawable(drawable);
            }
        }

        @Override // c.a.a.a.a0.b
        public boolean a() {
            return FloatingActionButton.this.m;
        }
    }

    void a(b bVar, boolean z) {
        getImpl().a(c(bVar), z);
    }

    public void b() {
        a((b) null);
    }

    public void a(Animator.AnimatorListener animatorListener) {
        getImpl().a(animatorListener);
    }

    public void b(Rect rect) {
        rect.set(0, 0, getMeasuredWidth(), getMeasuredHeight());
        c(rect);
    }

    @Override // c.a.a.a.v.b
    public boolean a() {
        return this.q.b();
    }

    private int a(int i) {
        int i2 = this.j;
        if (i2 != 0) {
            return i2;
        }
        Resources resources = getResources();
        if (i != -1) {
            if (i != 1) {
                return resources.getDimensionPixelSize(c.a.a.a.d.design_fab_size_normal);
            }
            return resources.getDimensionPixelSize(c.a.a.a.d.design_fab_size_mini);
        }
        if (Math.max(resources.getConfiguration().screenWidthDp, resources.getConfiguration().screenHeightDp) < 470) {
            return a(1);
        }
        return a(0);
    }

    @Deprecated
    public boolean a(Rect rect) {
        if (!w.F(this)) {
            return false;
        }
        rect.set(0, 0, getWidth(), getHeight());
        c(rect);
        return true;
    }

    private static int a(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        if (mode == Integer.MIN_VALUE) {
            return Math.min(i, size);
        }
        if (mode == 0) {
            return i;
        }
        if (mode == 1073741824) {
            return size;
        }
        throw new IllegalArgumentException();
    }

    public void a(c.a.a.a.m.k<? extends FloatingActionButton> kVar) {
        getImpl().a(new d(kVar));
    }
}
