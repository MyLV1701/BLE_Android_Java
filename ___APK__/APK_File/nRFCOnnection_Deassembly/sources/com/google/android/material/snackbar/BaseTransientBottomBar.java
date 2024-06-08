package com.google.android.material.snackbar;

import a.f.l.e0;
import a.f.l.w;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityManager;
import android.widget.FrameLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.behavior.SwipeDismissBehavior;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.b;
import java.util.ArrayList;
import java.util.List;
import no.nordicsemi.android.mcp.ble.parser.AppearanceLibrary;

/* loaded from: classes.dex */
public abstract class BaseTransientBottomBar<B extends BaseTransientBottomBar<B>> {
    static final Handler s;
    private static final boolean t;
    private static final int[] u;
    private static final String v;

    /* renamed from: a, reason: collision with root package name */
    private final ViewGroup f2624a;

    /* renamed from: b, reason: collision with root package name */
    private final Context f2625b;

    /* renamed from: c, reason: collision with root package name */
    protected final v f2626c;

    /* renamed from: d, reason: collision with root package name */
    private final com.google.android.material.snackbar.a f2627d;

    /* renamed from: e, reason: collision with root package name */
    private int f2628e;

    /* renamed from: f, reason: collision with root package name */
    private boolean f2629f;
    private View g;
    private Rect i;
    private int j;
    private int k;
    private int l;
    private int m;
    private int n;
    private List<r<B>> o;
    private Behavior p;
    private final AccessibilityManager q;
    private final Runnable h = new j();
    b.InterfaceC0088b r = new m();

    /* loaded from: classes.dex */
    public static class Behavior extends SwipeDismissBehavior<View> {
        private final s k = new s(this);

        /* JADX INFO: Access modifiers changed from: private */
        public void a(BaseTransientBottomBar<?> baseTransientBottomBar) {
            this.k.a(baseTransientBottomBar);
        }

        @Override // com.google.android.material.behavior.SwipeDismissBehavior
        public boolean a(View view) {
            return this.k.a(view);
        }

        @Override // com.google.android.material.behavior.SwipeDismissBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.c
        public boolean a(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
            this.k.a(coordinatorLayout, view, motionEvent);
            return super.a(coordinatorLayout, (CoordinatorLayout) view, motionEvent);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a extends AnimatorListenerAdapter {
        a() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            BaseTransientBottomBar.this.i();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class b extends AnimatorListenerAdapter {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f2631a;

        b(int i) {
            this.f2631a = i;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            BaseTransientBottomBar.this.c(this.f2631a);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class c implements ValueAnimator.AnimatorUpdateListener {
        c() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            BaseTransientBottomBar.this.f2626c.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class d implements ValueAnimator.AnimatorUpdateListener {
        d() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            BaseTransientBottomBar.this.f2626c.setScaleX(floatValue);
            BaseTransientBottomBar.this.f2626c.setScaleY(floatValue);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class e extends AnimatorListenerAdapter {
        e() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            BaseTransientBottomBar.this.i();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            BaseTransientBottomBar.this.f2627d.a(70, 180);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class f implements ValueAnimator.AnimatorUpdateListener {

        /* renamed from: a, reason: collision with root package name */
        private int f2636a;

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f2637b;

        f(int i) {
            this.f2637b = i;
            this.f2636a = this.f2637b;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
            if (BaseTransientBottomBar.t) {
                w.f(BaseTransientBottomBar.this.f2626c, intValue - this.f2636a);
            } else {
                BaseTransientBottomBar.this.f2626c.setTranslationY(intValue);
            }
            this.f2636a = intValue;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class g extends AnimatorListenerAdapter {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f2639a;

        g(int i) {
            this.f2639a = i;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            BaseTransientBottomBar.this.c(this.f2639a);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            BaseTransientBottomBar.this.f2627d.b(0, 180);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class h implements ValueAnimator.AnimatorUpdateListener {

        /* renamed from: a, reason: collision with root package name */
        private int f2641a = 0;

        h() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
            if (BaseTransientBottomBar.t) {
                w.f(BaseTransientBottomBar.this.f2626c, intValue - this.f2641a);
            } else {
                BaseTransientBottomBar.this.f2626c.setTranslationY(intValue);
            }
            this.f2641a = intValue;
        }
    }

    /* loaded from: classes.dex */
    static class i implements Handler.Callback {
        i() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            int i = message.what;
            if (i == 0) {
                ((BaseTransientBottomBar) message.obj).l();
                return true;
            }
            if (i != 1) {
                return false;
            }
            ((BaseTransientBottomBar) message.obj).b(message.arg1);
            return true;
        }
    }

    /* loaded from: classes.dex */
    class j implements Runnable {
        j() {
        }

        @Override // java.lang.Runnable
        public void run() {
            int p = BaseTransientBottomBar.this.p() - BaseTransientBottomBar.this.r();
            if (p >= BaseTransientBottomBar.this.m) {
                return;
            }
            ViewGroup.LayoutParams layoutParams = BaseTransientBottomBar.this.f2626c.getLayoutParams();
            if (!(layoutParams instanceof ViewGroup.MarginLayoutParams)) {
                Log.w(BaseTransientBottomBar.v, "Unable to apply gesture inset because layout params are not MarginLayoutParams");
                return;
            }
            ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin += BaseTransientBottomBar.this.m - p;
            BaseTransientBottomBar.this.f2626c.requestLayout();
        }
    }

    /* loaded from: classes.dex */
    class k implements a.f.l.r {
        k() {
        }

        @Override // a.f.l.r
        public e0 a(View view, e0 e0Var) {
            BaseTransientBottomBar.this.j = e0Var.b();
            BaseTransientBottomBar.this.k = e0Var.c();
            BaseTransientBottomBar.this.l = e0Var.d();
            BaseTransientBottomBar.this.x();
            return e0Var;
        }
    }

    /* loaded from: classes.dex */
    class l extends a.f.l.a {
        l() {
        }

        @Override // a.f.l.a
        public void onInitializeAccessibilityNodeInfo(View view, a.f.l.f0.c cVar) {
            super.onInitializeAccessibilityNodeInfo(view, cVar);
            cVar.a(AppearanceLibrary.MASK_BEACON);
            cVar.f(true);
        }

        @Override // a.f.l.a
        public boolean performAccessibilityAction(View view, int i, Bundle bundle) {
            if (i == 1048576) {
                BaseTransientBottomBar.this.b();
                return true;
            }
            return super.performAccessibilityAction(view, i, bundle);
        }
    }

    /* loaded from: classes.dex */
    class m implements b.InterfaceC0088b {
        m() {
        }

        @Override // com.google.android.material.snackbar.b.InterfaceC0088b
        public void a(int i) {
            Handler handler = BaseTransientBottomBar.s;
            handler.sendMessage(handler.obtainMessage(1, i, 0, BaseTransientBottomBar.this));
        }

        @Override // com.google.android.material.snackbar.b.InterfaceC0088b
        public void d() {
            Handler handler = BaseTransientBottomBar.s;
            handler.sendMessage(handler.obtainMessage(0, BaseTransientBottomBar.this));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class n implements t {

        /* loaded from: classes.dex */
        class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                BaseTransientBottomBar.this.c(3);
            }
        }

        n() {
        }

        @Override // com.google.android.material.snackbar.BaseTransientBottomBar.t
        public void onViewAttachedToWindow(View view) {
            WindowInsets rootWindowInsets;
            if (Build.VERSION.SDK_INT < 29 || (rootWindowInsets = BaseTransientBottomBar.this.f2626c.getRootWindowInsets()) == null) {
                return;
            }
            BaseTransientBottomBar.this.m = rootWindowInsets.getMandatorySystemGestureInsets().bottom;
            BaseTransientBottomBar.this.x();
        }

        @Override // com.google.android.material.snackbar.BaseTransientBottomBar.t
        public void onViewDetachedFromWindow(View view) {
            if (BaseTransientBottomBar.this.h()) {
                BaseTransientBottomBar.s.post(new a());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class o implements u {
        o() {
        }

        @Override // com.google.android.material.snackbar.BaseTransientBottomBar.u
        public void a(View view, int i, int i2, int i3, int i4) {
            BaseTransientBottomBar.this.f2626c.setOnLayoutChangeListener(null);
            BaseTransientBottomBar.this.u();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class q implements Runnable {
        q() {
        }

        @Override // java.lang.Runnable
        public void run() {
            BaseTransientBottomBar.this.f2626c.setVisibility(0);
            if (BaseTransientBottomBar.this.f2626c.getAnimationMode() == 1) {
                BaseTransientBottomBar.this.v();
            } else {
                BaseTransientBottomBar.this.w();
            }
        }
    }

    /* loaded from: classes.dex */
    public static abstract class r<B> {
        public static final int DISMISS_EVENT_ACTION = 1;
        public static final int DISMISS_EVENT_CONSECUTIVE = 4;
        public static final int DISMISS_EVENT_MANUAL = 3;
        public static final int DISMISS_EVENT_SWIPE = 0;
        public static final int DISMISS_EVENT_TIMEOUT = 2;

        public void onDismissed(B b2, int i) {
        }

        public void onShown(B b2) {
        }
    }

    /* loaded from: classes.dex */
    public static class s {

        /* renamed from: a, reason: collision with root package name */
        private b.InterfaceC0088b f2652a;

        public s(SwipeDismissBehavior<?> swipeDismissBehavior) {
            swipeDismissBehavior.b(0.1f);
            swipeDismissBehavior.a(0.6f);
            swipeDismissBehavior.a(0);
        }

        public void a(BaseTransientBottomBar<?> baseTransientBottomBar) {
            this.f2652a = baseTransientBottomBar.r;
        }

        public boolean a(View view) {
            return view instanceof v;
        }

        public void a(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked == 0) {
                if (coordinatorLayout.a(view, (int) motionEvent.getX(), (int) motionEvent.getY())) {
                    com.google.android.material.snackbar.b.a().d(this.f2652a);
                }
            } else if (actionMasked == 1 || actionMasked == 3) {
                com.google.android.material.snackbar.b.a().e(this.f2652a);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes.dex */
    public interface t {
        void onViewAttachedToWindow(View view);

        void onViewDetachedFromWindow(View view);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes.dex */
    public interface u {
        void a(View view, int i, int i2, int i3, int i4);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes.dex */
    public static class v extends FrameLayout {
        private static final View.OnTouchListener i = new a();

        /* renamed from: b, reason: collision with root package name */
        private u f2653b;

        /* renamed from: c, reason: collision with root package name */
        private t f2654c;

        /* renamed from: d, reason: collision with root package name */
        private int f2655d;

        /* renamed from: e, reason: collision with root package name */
        private final float f2656e;

        /* renamed from: f, reason: collision with root package name */
        private final float f2657f;
        private ColorStateList g;
        private PorterDuff.Mode h;

        /* loaded from: classes.dex */
        static class a implements View.OnTouchListener {
            a() {
            }

            @Override // android.view.View.OnTouchListener
            @SuppressLint({"ClickableViewAccessibility"})
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public v(Context context) {
            this(context, null);
        }

        private Drawable a() {
            float dimension = getResources().getDimension(c.a.a.a.d.mtrl_snackbar_background_corner_radius);
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setShape(0);
            gradientDrawable.setCornerRadius(dimension);
            gradientDrawable.setColor(c.a.a.a.s.a.a(this, c.a.a.a.b.colorSurface, c.a.a.a.b.colorOnSurface, getBackgroundOverlayColorAlpha()));
            if (this.g != null) {
                Drawable i2 = androidx.core.graphics.drawable.a.i(gradientDrawable);
                androidx.core.graphics.drawable.a.a(i2, this.g);
                return i2;
            }
            return androidx.core.graphics.drawable.a.i(gradientDrawable);
        }

        float getActionTextColorAlpha() {
            return this.f2657f;
        }

        int getAnimationMode() {
            return this.f2655d;
        }

        float getBackgroundOverlayColorAlpha() {
            return this.f2656e;
        }

        @Override // android.view.ViewGroup, android.view.View
        protected void onAttachedToWindow() {
            super.onAttachedToWindow();
            t tVar = this.f2654c;
            if (tVar != null) {
                tVar.onViewAttachedToWindow(this);
            }
            w.L(this);
        }

        @Override // android.view.ViewGroup, android.view.View
        protected void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            t tVar = this.f2654c;
            if (tVar != null) {
                tVar.onViewDetachedFromWindow(this);
            }
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
            super.onLayout(z, i2, i3, i4, i5);
            u uVar = this.f2653b;
            if (uVar != null) {
                uVar.a(this, i2, i3, i4, i5);
            }
        }

        void setAnimationMode(int i2) {
            this.f2655d = i2;
        }

        @Override // android.view.View
        public void setBackground(Drawable drawable) {
            setBackgroundDrawable(drawable);
        }

        @Override // android.view.View
        public void setBackgroundDrawable(Drawable drawable) {
            if (drawable != null && this.g != null) {
                drawable = androidx.core.graphics.drawable.a.i(drawable.mutate());
                androidx.core.graphics.drawable.a.a(drawable, this.g);
                androidx.core.graphics.drawable.a.a(drawable, this.h);
            }
            super.setBackgroundDrawable(drawable);
        }

        @Override // android.view.View
        public void setBackgroundTintList(ColorStateList colorStateList) {
            this.g = colorStateList;
            if (getBackground() != null) {
                Drawable i2 = androidx.core.graphics.drawable.a.i(getBackground().mutate());
                androidx.core.graphics.drawable.a.a(i2, colorStateList);
                androidx.core.graphics.drawable.a.a(i2, this.h);
                if (i2 != getBackground()) {
                    super.setBackgroundDrawable(i2);
                }
            }
        }

        @Override // android.view.View
        public void setBackgroundTintMode(PorterDuff.Mode mode) {
            this.h = mode;
            if (getBackground() != null) {
                Drawable i2 = androidx.core.graphics.drawable.a.i(getBackground().mutate());
                androidx.core.graphics.drawable.a.a(i2, mode);
                if (i2 != getBackground()) {
                    super.setBackgroundDrawable(i2);
                }
            }
        }

        void setOnAttachStateChangeListener(t tVar) {
            this.f2654c = tVar;
        }

        @Override // android.view.View
        public void setOnClickListener(View.OnClickListener onClickListener) {
            setOnTouchListener(onClickListener != null ? null : i);
            super.setOnClickListener(onClickListener);
        }

        void setOnLayoutChangeListener(u uVar) {
            this.f2653b = uVar;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public v(Context context, AttributeSet attributeSet) {
            super(com.google.android.material.theme.a.a.b(context, attributeSet, 0, 0), attributeSet);
            Context context2 = getContext();
            TypedArray obtainStyledAttributes = context2.obtainStyledAttributes(attributeSet, c.a.a.a.l.SnackbarLayout);
            if (obtainStyledAttributes.hasValue(c.a.a.a.l.SnackbarLayout_elevation)) {
                w.a(this, obtainStyledAttributes.getDimensionPixelSize(c.a.a.a.l.SnackbarLayout_elevation, 0));
            }
            this.f2655d = obtainStyledAttributes.getInt(c.a.a.a.l.SnackbarLayout_animationMode, 0);
            this.f2656e = obtainStyledAttributes.getFloat(c.a.a.a.l.SnackbarLayout_backgroundOverlayColorAlpha, 1.0f);
            setBackgroundTintList(c.a.a.a.y.c.a(context2, obtainStyledAttributes, c.a.a.a.l.SnackbarLayout_backgroundTint));
            setBackgroundTintMode(com.google.android.material.internal.m.a(obtainStyledAttributes.getInt(c.a.a.a.l.SnackbarLayout_backgroundTintMode, -1), PorterDuff.Mode.SRC_IN));
            this.f2657f = obtainStyledAttributes.getFloat(c.a.a.a.l.SnackbarLayout_actionTextColorAlpha, 1.0f);
            obtainStyledAttributes.recycle();
            setOnTouchListener(i);
            setFocusable(true);
            if (getBackground() == null) {
                w.a(this, a());
            }
        }
    }

    static {
        int i2 = Build.VERSION.SDK_INT;
        t = i2 >= 16 && i2 <= 19;
        u = new int[]{c.a.a.a.b.snackbarStyle};
        v = BaseTransientBottomBar.class.getSimpleName();
        s = new Handler(Looper.getMainLooper(), new i());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public BaseTransientBottomBar(ViewGroup viewGroup, View view, com.google.android.material.snackbar.a aVar) {
        if (viewGroup == null) {
            throw new IllegalArgumentException("Transient bottom bar must have non-null parent");
        }
        if (view == null) {
            throw new IllegalArgumentException("Transient bottom bar must have non-null content");
        }
        if (aVar != null) {
            this.f2624a = viewGroup;
            this.f2627d = aVar;
            this.f2625b = viewGroup.getContext();
            com.google.android.material.internal.l.a(this.f2625b);
            this.f2626c = (v) LayoutInflater.from(this.f2625b).inflate(f(), this.f2624a, false);
            if (view instanceof SnackbarContentLayout) {
                ((SnackbarContentLayout) view).a(this.f2626c.getActionTextColorAlpha());
            }
            this.f2626c.addView(view);
            ViewGroup.LayoutParams layoutParams = this.f2626c.getLayoutParams();
            if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
                this.i = new Rect(marginLayoutParams.leftMargin, marginLayoutParams.topMargin, marginLayoutParams.rightMargin, marginLayoutParams.bottomMargin);
            }
            w.h(this.f2626c, 1);
            w.i(this.f2626c, 1);
            w.b((View) this.f2626c, true);
            w.a(this.f2626c, new k());
            w.a(this.f2626c, new l());
            this.q = (AccessibilityManager) this.f2625b.getSystemService("accessibility");
            return;
        }
        throw new IllegalArgumentException("Transient bottom bar must have non-null callback");
    }

    private int o() {
        View view = this.g;
        if (view == null) {
            return 0;
        }
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        int i2 = iArr[1];
        int[] iArr2 = new int[2];
        this.f2624a.getLocationOnScreen(iArr2);
        return (iArr2[1] + this.f2624a.getHeight()) - i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int p() {
        WindowManager windowManager = (WindowManager) this.f2625b.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getRealMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    private int q() {
        int height = this.f2626c.getHeight();
        ViewGroup.LayoutParams layoutParams = this.f2626c.getLayoutParams();
        return layoutParams instanceof ViewGroup.MarginLayoutParams ? height + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin : height;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int r() {
        int[] iArr = new int[2];
        this.f2626c.getLocationOnScreen(iArr);
        return iArr[1] + this.f2626c.getHeight();
    }

    private boolean s() {
        ViewGroup.LayoutParams layoutParams = this.f2626c.getLayoutParams();
        return (layoutParams instanceof CoordinatorLayout.f) && (((CoordinatorLayout.f) layoutParams).d() instanceof SwipeDismissBehavior);
    }

    private boolean t() {
        return this.m > 0 && !this.f2629f && s();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void u() {
        if (j()) {
            a();
        } else {
            this.f2626c.setVisibility(0);
            i();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void v() {
        ValueAnimator a2 = a(0.0f, 1.0f);
        ValueAnimator b2 = b(0.8f, 1.0f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(a2, b2);
        animatorSet.setDuration(150L);
        animatorSet.addListener(new a());
        animatorSet.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void w() {
        int q2 = q();
        if (t) {
            w.f(this.f2626c, q2);
        } else {
            this.f2626c.setTranslationY(q2);
        }
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setIntValues(q2, 0);
        valueAnimator.setInterpolator(c.a.a.a.m.a.f2094b);
        valueAnimator.setDuration(250L);
        valueAnimator.addListener(new e());
        valueAnimator.addUpdateListener(new f(q2));
        valueAnimator.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void x() {
        ViewGroup.LayoutParams layoutParams = this.f2626c.getLayoutParams();
        if ((layoutParams instanceof ViewGroup.MarginLayoutParams) && this.i != null) {
            int i2 = this.g != null ? this.n : this.j;
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            Rect rect = this.i;
            marginLayoutParams.bottomMargin = rect.bottom + i2;
            marginLayoutParams.leftMargin = rect.left + this.k;
            marginLayoutParams.rightMargin = rect.right + this.l;
            this.f2626c.requestLayout();
            if (Build.VERSION.SDK_INT < 29 || !t()) {
                return;
            }
            this.f2626c.removeCallbacks(this.h);
            this.f2626c.post(this.h);
            return;
        }
        Log.w(v, "Unable to update margins because layout params are not MarginLayoutParams");
    }

    void i() {
        com.google.android.material.snackbar.b.a().c(this.r);
        List<r<B>> list = this.o;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.o.get(size).onShown(this);
            }
        }
    }

    boolean j() {
        List<AccessibilityServiceInfo> enabledAccessibilityServiceList = this.q.getEnabledAccessibilityServiceList(1);
        return enabledAccessibilityServiceList != null && enabledAccessibilityServiceList.isEmpty();
    }

    public void k() {
        com.google.android.material.snackbar.b.a().a(d(), this.r);
    }

    final void l() {
        this.f2626c.setOnAttachStateChangeListener(new n());
        if (this.f2626c.getParent() == null) {
            ViewGroup.LayoutParams layoutParams = this.f2626c.getLayoutParams();
            if (layoutParams instanceof CoordinatorLayout.f) {
                a((CoordinatorLayout.f) layoutParams);
            }
            this.n = o();
            x();
            this.f2626c.setVisibility(4);
            this.f2624a.addView(this.f2626c);
        }
        if (w.F(this.f2626c)) {
            u();
        } else {
            this.f2626c.setOnLayoutChangeListener(new o());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class p implements SwipeDismissBehavior.c {
        p() {
        }

        @Override // com.google.android.material.behavior.SwipeDismissBehavior.c
        public void a(View view) {
            view.setVisibility(8);
            BaseTransientBottomBar.this.a(0);
        }

        @Override // com.google.android.material.behavior.SwipeDismissBehavior.c
        public void a(int i) {
            if (i == 0) {
                com.google.android.material.snackbar.b.a().e(BaseTransientBottomBar.this.r);
            } else if (i == 1 || i == 2) {
                com.google.android.material.snackbar.b.a().d(BaseTransientBottomBar.this.r);
            }
        }
    }

    protected SwipeDismissBehavior<? extends View> e() {
        return new Behavior();
    }

    protected int f() {
        return g() ? c.a.a.a.h.mtrl_layout_snackbar : c.a.a.a.h.design_layout_snackbar;
    }

    protected boolean g() {
        TypedArray obtainStyledAttributes = this.f2625b.obtainStyledAttributes(u);
        int resourceId = obtainStyledAttributes.getResourceId(0, -1);
        obtainStyledAttributes.recycle();
        return resourceId != -1;
    }

    public boolean h() {
        return com.google.android.material.snackbar.b.a().a(this.r);
    }

    private void e(int i2) {
        if (this.f2626c.getAnimationMode() == 1) {
            f(i2);
        } else {
            g(i2);
        }
    }

    private void f(int i2) {
        ValueAnimator a2 = a(1.0f, 0.0f);
        a2.setDuration(75L);
        a2.addListener(new b(i2));
        a2.start();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(int i2) {
        com.google.android.material.snackbar.b.a().a(this.r, i2);
    }

    public void b() {
        a(3);
    }

    public Context c() {
        return this.f2625b;
    }

    public B d(int i2) {
        this.f2628e = i2;
        return this;
    }

    private ValueAnimator b(float... fArr) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(fArr);
        ofFloat.setInterpolator(c.a.a.a.m.a.f2096d);
        ofFloat.addUpdateListener(new d());
        return ofFloat;
    }

    public B a(r<B> rVar) {
        if (rVar == null) {
            return this;
        }
        if (this.o == null) {
            this.o = new ArrayList();
        }
        this.o.add(rVar);
        return this;
    }

    void c(int i2) {
        com.google.android.material.snackbar.b.a().b(this.r);
        List<r<B>> list = this.o;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.o.get(size).onDismissed(this, i2);
            }
        }
        ViewParent parent = this.f2626c.getParent();
        if (parent instanceof ViewGroup) {
            ((ViewGroup) parent).removeView(this.f2626c);
        }
    }

    public int d() {
        return this.f2628e;
    }

    private void g(int i2) {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setIntValues(0, q());
        valueAnimator.setInterpolator(c.a.a.a.m.a.f2094b);
        valueAnimator.setDuration(250L);
        valueAnimator.addListener(new g(i2));
        valueAnimator.addUpdateListener(new h());
        valueAnimator.start();
    }

    private void a(CoordinatorLayout.f fVar) {
        SwipeDismissBehavior<? extends View> swipeDismissBehavior = this.p;
        if (swipeDismissBehavior == null) {
            swipeDismissBehavior = e();
        }
        if (swipeDismissBehavior instanceof Behavior) {
            ((Behavior) swipeDismissBehavior).a((BaseTransientBottomBar<?>) this);
        }
        swipeDismissBehavior.a(new p());
        fVar.a(swipeDismissBehavior);
        if (this.g == null) {
            fVar.g = 80;
        }
    }

    final void b(int i2) {
        if (j() && this.f2626c.getVisibility() == 0) {
            e(i2);
        } else {
            c(i2);
        }
    }

    void a() {
        this.f2626c.post(new q());
    }

    private ValueAnimator a(float... fArr) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(fArr);
        ofFloat.setInterpolator(c.a.a.a.m.a.f2093a);
        ofFloat.addUpdateListener(new c());
        return ofFloat;
    }
}
