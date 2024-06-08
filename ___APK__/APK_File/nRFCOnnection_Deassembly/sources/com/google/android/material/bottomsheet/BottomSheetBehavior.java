package com.google.android.material.bottomsheet;

import a.f.l.f0.c;
import a.f.l.f0.f;
import a.f.l.w;
import a.h.b.c;
import android.R;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import c.a.a.a.k;
import c.a.a.a.l;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import no.nordicsemi.android.mcp.ble.parser.AppearanceLibrary;

/* loaded from: classes.dex */
public class BottomSheetBehavior<V extends View> extends CoordinatorLayout.c<V> {
    private static final int K = k.Widget_Design_BottomSheet_Modal;
    int A;
    WeakReference<V> B;
    WeakReference<View> C;
    private final ArrayList<e> D;
    private VelocityTracker E;
    int F;
    private int G;
    boolean H;
    private Map<View, Integer> I;
    private final c.AbstractC0024c J;

    /* renamed from: a, reason: collision with root package name */
    private int f2411a;

    /* renamed from: b, reason: collision with root package name */
    private boolean f2412b;

    /* renamed from: c, reason: collision with root package name */
    private boolean f2413c;

    /* renamed from: d, reason: collision with root package name */
    private float f2414d;

    /* renamed from: e, reason: collision with root package name */
    private int f2415e;

    /* renamed from: f, reason: collision with root package name */
    private boolean f2416f;
    private int g;
    private boolean h;
    private c.a.a.a.b0.g i;
    private c.a.a.a.b0.k j;
    private boolean k;
    private ValueAnimator l;
    int m;
    int n;
    int o;
    float p;
    int q;
    float r;
    boolean s;
    private boolean t;
    int u;
    a.h.b.c v;
    private boolean w;
    private int x;
    private boolean y;
    int z;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a implements Runnable {

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ View f2417b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ int f2418c;

        a(View view, int i) {
            this.f2417b = view;
            this.f2418c = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            BottomSheetBehavior.this.a(this.f2417b, this.f2418c);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class b implements ValueAnimator.AnimatorUpdateListener {
        b() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            if (BottomSheetBehavior.this.i != null) {
                BottomSheetBehavior.this.i.c(floatValue);
            }
        }
    }

    /* loaded from: classes.dex */
    class c extends c.AbstractC0024c {
        c() {
        }

        private boolean a(View view) {
            int top = view.getTop();
            BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
            return top > (bottomSheetBehavior.A + bottomSheetBehavior.h()) / 2;
        }

        @Override // a.h.b.c.AbstractC0024c
        public int clampViewPositionHorizontal(View view, int i, int i2) {
            return view.getLeft();
        }

        @Override // a.h.b.c.AbstractC0024c
        public int clampViewPositionVertical(View view, int i, int i2) {
            int h = BottomSheetBehavior.this.h();
            BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
            return a.f.g.a.a(i, h, bottomSheetBehavior.s ? bottomSheetBehavior.A : bottomSheetBehavior.q);
        }

        @Override // a.h.b.c.AbstractC0024c
        public int getViewVerticalDragRange(View view) {
            BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
            if (bottomSheetBehavior.s) {
                return bottomSheetBehavior.A;
            }
            return bottomSheetBehavior.q;
        }

        @Override // a.h.b.c.AbstractC0024c
        public void onViewDragStateChanged(int i) {
            if (i == 1) {
                BottomSheetBehavior.this.f(1);
            }
        }

        @Override // a.h.b.c.AbstractC0024c
        public void onViewPositionChanged(View view, int i, int i2, int i3, int i4) {
            BottomSheetBehavior.this.a(i2);
        }

        @Override // a.h.b.c.AbstractC0024c
        public void onViewReleased(View view, float f2, float f3) {
            int i;
            int i2 = 4;
            if (f3 < 0.0f) {
                if (BottomSheetBehavior.this.f2412b) {
                    i = BottomSheetBehavior.this.n;
                } else {
                    int top = view.getTop();
                    BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
                    int i3 = bottomSheetBehavior.o;
                    if (top > i3) {
                        i = i3;
                        i2 = 6;
                    } else {
                        i = bottomSheetBehavior.m;
                    }
                }
                i2 = 3;
            } else {
                BottomSheetBehavior bottomSheetBehavior2 = BottomSheetBehavior.this;
                if (bottomSheetBehavior2.s && bottomSheetBehavior2.a(view, f3)) {
                    if ((Math.abs(f2) >= Math.abs(f3) || f3 <= 500.0f) && !a(view)) {
                        if (BottomSheetBehavior.this.f2412b) {
                            i = BottomSheetBehavior.this.n;
                        } else if (Math.abs(view.getTop() - BottomSheetBehavior.this.m) < Math.abs(view.getTop() - BottomSheetBehavior.this.o)) {
                            i = BottomSheetBehavior.this.m;
                        } else {
                            i = BottomSheetBehavior.this.o;
                            i2 = 6;
                        }
                        i2 = 3;
                    } else {
                        i = BottomSheetBehavior.this.A;
                        i2 = 5;
                    }
                } else if (f3 != 0.0f && Math.abs(f2) <= Math.abs(f3)) {
                    if (BottomSheetBehavior.this.f2412b) {
                        i = BottomSheetBehavior.this.q;
                    } else {
                        int top2 = view.getTop();
                        if (Math.abs(top2 - BottomSheetBehavior.this.o) < Math.abs(top2 - BottomSheetBehavior.this.q)) {
                            i = BottomSheetBehavior.this.o;
                            i2 = 6;
                        } else {
                            i = BottomSheetBehavior.this.q;
                        }
                    }
                } else {
                    int top3 = view.getTop();
                    if (BottomSheetBehavior.this.f2412b) {
                        if (Math.abs(top3 - BottomSheetBehavior.this.n) < Math.abs(top3 - BottomSheetBehavior.this.q)) {
                            i = BottomSheetBehavior.this.n;
                            i2 = 3;
                        } else {
                            i = BottomSheetBehavior.this.q;
                        }
                    } else {
                        BottomSheetBehavior bottomSheetBehavior3 = BottomSheetBehavior.this;
                        int i4 = bottomSheetBehavior3.o;
                        if (top3 < i4) {
                            if (top3 < Math.abs(top3 - bottomSheetBehavior3.q)) {
                                i = BottomSheetBehavior.this.m;
                                i2 = 3;
                            } else {
                                i = BottomSheetBehavior.this.o;
                            }
                        } else if (Math.abs(top3 - i4) < Math.abs(top3 - BottomSheetBehavior.this.q)) {
                            i = BottomSheetBehavior.this.o;
                        } else {
                            i = BottomSheetBehavior.this.q;
                        }
                        i2 = 6;
                    }
                }
            }
            BottomSheetBehavior.this.a(view, i2, i, true);
        }

        @Override // a.h.b.c.AbstractC0024c
        public boolean tryCaptureView(View view, int i) {
            BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
            int i2 = bottomSheetBehavior.u;
            if (i2 == 1 || bottomSheetBehavior.H) {
                return false;
            }
            if (i2 == 3 && bottomSheetBehavior.F == i) {
                WeakReference<View> weakReference = bottomSheetBehavior.C;
                View view2 = weakReference != null ? weakReference.get() : null;
                if (view2 != null && view2.canScrollVertically(-1)) {
                    return false;
                }
            }
            WeakReference<V> weakReference2 = BottomSheetBehavior.this.B;
            return weakReference2 != null && weakReference2.get() == view;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class d implements a.f.l.f0.f {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f2422a;

        d(int i) {
            this.f2422a = i;
        }

        @Override // a.f.l.f0.f
        public boolean a(View view, f.a aVar) {
            BottomSheetBehavior.this.e(this.f2422a);
            return true;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class e {
        public abstract void onSlide(View view, float f2);

        public abstract void onStateChanged(View view, int i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class g implements Runnable {

        /* renamed from: b, reason: collision with root package name */
        private final View f2427b;

        /* renamed from: c, reason: collision with root package name */
        private final int f2428c;

        g(View view, int i) {
            this.f2427b = view;
            this.f2428c = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            a.h.b.c cVar = BottomSheetBehavior.this.v;
            if (cVar != null && cVar.a(true)) {
                w.a(this.f2427b, this);
                return;
            }
            BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
            if (bottomSheetBehavior.u == 2) {
                bottomSheetBehavior.f(this.f2428c);
            }
        }
    }

    public BottomSheetBehavior() {
        this.f2411a = 0;
        this.f2412b = true;
        this.f2413c = false;
        this.p = 0.5f;
        this.r = -1.0f;
        this.u = 4;
        this.D = new ArrayList<>();
        this.J = new c();
    }

    private void g(int i) {
        V v = this.B.get();
        if (v == null) {
            return;
        }
        ViewParent parent = v.getParent();
        if (parent != null && parent.isLayoutRequested() && w.E(v)) {
            v.post(new a(v, i));
        } else {
            a((View) v, i);
        }
    }

    private void h(int i) {
        ValueAnimator valueAnimator;
        if (i == 2) {
            return;
        }
        boolean z = i == 3;
        if (this.k != z) {
            this.k = z;
            if (this.i == null || (valueAnimator = this.l) == null) {
                return;
            }
            if (valueAnimator.isRunning()) {
                this.l.reverse();
                return;
            }
            float f2 = z ? 0.0f : 1.0f;
            this.l.setFloatValues(1.0f - f2, f2);
            this.l.start();
        }
    }

    private float i() {
        VelocityTracker velocityTracker = this.E;
        if (velocityTracker == null) {
            return 0.0f;
        }
        velocityTracker.computeCurrentVelocity(1000, this.f2414d);
        return this.E.getYVelocity(this.F);
    }

    private void j() {
        this.F = -1;
        VelocityTracker velocityTracker = this.E;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.E = null;
        }
    }

    private void k() {
        V v;
        WeakReference<V> weakReference = this.B;
        if (weakReference == null || (v = weakReference.get()) == null) {
            return;
        }
        w.g(v, 524288);
        w.g(v, 262144);
        w.g(v, AppearanceLibrary.MASK_BEACON);
        if (this.s && this.u != 5) {
            a((BottomSheetBehavior<V>) v, c.a.l, 5);
        }
        int i = this.u;
        if (i == 3) {
            a((BottomSheetBehavior<V>) v, c.a.k, this.f2412b ? 4 : 6);
            return;
        }
        if (i == 4) {
            a((BottomSheetBehavior<V>) v, c.a.j, this.f2412b ? 3 : 6);
        } else {
            if (i != 6) {
                return;
            }
            a((BottomSheetBehavior<V>) v, c.a.k, 4);
            a((BottomSheetBehavior<V>) v, c.a.j, 3);
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public void a(CoordinatorLayout coordinatorLayout, V v, View view, int i, int i2, int i3, int i4, int i5, int[] iArr) {
    }

    void f(int i) {
        V v;
        if (this.u == i) {
            return;
        }
        this.u = i;
        WeakReference<V> weakReference = this.B;
        if (weakReference == null || (v = weakReference.get()) == null) {
            return;
        }
        if (i == 3) {
            d(true);
        } else if (i == 6 || i == 5 || i == 4) {
            d(false);
        }
        h(i);
        for (int i2 = 0; i2 < this.D.size(); i2++) {
            this.D.get(i2).onStateChanged(v, i);
        }
        k();
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public void a(CoordinatorLayout coordinatorLayout, V v, Parcelable parcelable) {
        f fVar = (f) parcelable;
        super.a(coordinatorLayout, (CoordinatorLayout) v, fVar.d());
        a(fVar);
        int i = fVar.f2424d;
        if (i != 1 && i != 2) {
            this.u = i;
        } else {
            this.u = 4;
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean b(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        if (!v.isShown()) {
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (this.u == 1 && actionMasked == 0) {
            return true;
        }
        a.h.b.c cVar = this.v;
        if (cVar != null) {
            cVar.a(motionEvent);
        }
        if (actionMasked == 0) {
            j();
        }
        if (this.E == null) {
            this.E = VelocityTracker.obtain();
        }
        this.E.addMovement(motionEvent);
        if (actionMasked == 2 && !this.w && Math.abs(this.G - motionEvent.getY()) > this.v.e()) {
            this.v.a(v, motionEvent.getPointerId(motionEvent.getActionIndex()));
        }
        return !this.w;
    }

    public void c(int i) {
        a(i, false);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public Parcelable d(CoordinatorLayout coordinatorLayout, V v) {
        return new f(super.d(coordinatorLayout, v), (BottomSheetBehavior<?>) this);
    }

    public void e(int i) {
        if (i == this.u) {
            return;
        }
        if (this.B == null) {
            if (i == 4 || i == 3 || i == 6 || (this.s && i == 5)) {
                this.u = i;
                return;
            }
            return;
        }
        g(i);
    }

    public boolean c() {
        return this.s;
    }

    public void d(int i) {
        this.f2411a = i;
    }

    private void d() {
        int f2 = f();
        if (this.f2412b) {
            this.q = Math.max(this.A - f2, this.n);
        } else {
            this.q = this.A - f2;
        }
    }

    public void c(boolean z) {
        this.t = z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes.dex */
    public static class f extends a.h.a.a {
        public static final Parcelable.Creator<f> CREATOR = new a();

        /* renamed from: d, reason: collision with root package name */
        final int f2424d;

        /* renamed from: e, reason: collision with root package name */
        int f2425e;

        /* renamed from: f, reason: collision with root package name */
        boolean f2426f;
        boolean g;
        boolean h;

        /* loaded from: classes.dex */
        static class a implements Parcelable.ClassLoaderCreator<f> {
            a() {
            }

            @Override // android.os.Parcelable.Creator
            public f[] newArray(int i) {
                return new f[i];
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.ClassLoaderCreator
            public f createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new f(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            public f createFromParcel(Parcel parcel) {
                return new f(parcel, (ClassLoader) null);
            }
        }

        public f(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f2424d = parcel.readInt();
            this.f2425e = parcel.readInt();
            this.f2426f = parcel.readInt() == 1;
            this.g = parcel.readInt() == 1;
            this.h = parcel.readInt() == 1;
        }

        @Override // a.h.a.a, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.f2424d);
            parcel.writeInt(this.f2425e);
            parcel.writeInt(this.f2426f ? 1 : 0);
            parcel.writeInt(this.g ? 1 : 0);
            parcel.writeInt(this.h ? 1 : 0);
        }

        public f(Parcelable parcelable, BottomSheetBehavior<?> bottomSheetBehavior) {
            super(parcelable);
            this.f2424d = bottomSheetBehavior.u;
            this.f2425e = ((BottomSheetBehavior) bottomSheetBehavior).f2415e;
            this.f2426f = ((BottomSheetBehavior) bottomSheetBehavior).f2412b;
            this.g = bottomSheetBehavior.s;
            this.h = ((BottomSheetBehavior) bottomSheetBehavior).t;
        }
    }

    private void g() {
        this.l = ValueAnimator.ofFloat(0.0f, 1.0f);
        this.l.setDuration(500L);
        this.l.addUpdateListener(new b());
    }

    private void e() {
        this.o = (int) (this.A * (1.0f - this.p));
    }

    private void d(boolean z) {
        Map<View, Integer> map;
        WeakReference<V> weakReference = this.B;
        if (weakReference == null) {
            return;
        }
        ViewParent parent = weakReference.get().getParent();
        if (parent instanceof CoordinatorLayout) {
            CoordinatorLayout coordinatorLayout = (CoordinatorLayout) parent;
            int childCount = coordinatorLayout.getChildCount();
            if (Build.VERSION.SDK_INT >= 16 && z) {
                if (this.I != null) {
                    return;
                } else {
                    this.I = new HashMap(childCount);
                }
            }
            for (int i = 0; i < childCount; i++) {
                View childAt = coordinatorLayout.getChildAt(i);
                if (childAt != this.B.get()) {
                    if (z) {
                        if (Build.VERSION.SDK_INT >= 16) {
                            this.I.put(childAt, Integer.valueOf(childAt.getImportantForAccessibility()));
                        }
                        if (this.f2413c) {
                            w.i(childAt, 4);
                        }
                    } else if (this.f2413c && (map = this.I) != null && map.containsKey(childAt)) {
                        w.i(childAt, this.I.get(childAt).intValue());
                    }
                }
            }
            if (z) {
                return;
            }
            this.I = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int h() {
        return this.f2412b ? this.n : this.m;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public void a(CoordinatorLayout.f fVar) {
        super.a(fVar);
        this.B = null;
        this.v = null;
    }

    public BottomSheetBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        int i;
        this.f2411a = 0;
        this.f2412b = true;
        this.f2413c = false;
        this.p = 0.5f;
        this.r = -1.0f;
        this.u = 4;
        this.D = new ArrayList<>();
        this.J = new c();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, l.BottomSheetBehavior_Layout);
        this.h = obtainStyledAttributes.hasValue(l.BottomSheetBehavior_Layout_shapeAppearance);
        boolean hasValue = obtainStyledAttributes.hasValue(l.BottomSheetBehavior_Layout_backgroundTint);
        if (hasValue) {
            a(context, attributeSet, hasValue, c.a.a.a.y.c.a(context, obtainStyledAttributes, l.BottomSheetBehavior_Layout_backgroundTint));
        } else {
            a(context, attributeSet, hasValue);
        }
        g();
        if (Build.VERSION.SDK_INT >= 21) {
            this.r = obtainStyledAttributes.getDimension(l.BottomSheetBehavior_Layout_android_elevation, -1.0f);
        }
        TypedValue peekValue = obtainStyledAttributes.peekValue(l.BottomSheetBehavior_Layout_behavior_peekHeight);
        if (peekValue != null && (i = peekValue.data) == -1) {
            c(i);
        } else {
            c(obtainStyledAttributes.getDimensionPixelSize(l.BottomSheetBehavior_Layout_behavior_peekHeight, -1));
        }
        b(obtainStyledAttributes.getBoolean(l.BottomSheetBehavior_Layout_behavior_hideable, false));
        a(obtainStyledAttributes.getBoolean(l.BottomSheetBehavior_Layout_behavior_fitToContents, true));
        c(obtainStyledAttributes.getBoolean(l.BottomSheetBehavior_Layout_behavior_skipCollapsed, false));
        d(obtainStyledAttributes.getInt(l.BottomSheetBehavior_Layout_behavior_saveFlags, 0));
        a(obtainStyledAttributes.getFloat(l.BottomSheetBehavior_Layout_behavior_halfExpandedRatio, 0.5f));
        b(obtainStyledAttributes.getInt(l.BottomSheetBehavior_Layout_behavior_expandedOffset, 0));
        obtainStyledAttributes.recycle();
        this.f2414d = ViewConfiguration.get(context).getScaledMaximumFlingVelocity();
    }

    private int f() {
        if (this.f2416f) {
            return Math.max(this.g, this.A - ((this.z * 9) / 16));
        }
        return this.f2415e;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public void a() {
        super.a();
        this.B = null;
        this.v = null;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean a(CoordinatorLayout coordinatorLayout, V v, int i) {
        c.a.a.a.b0.g gVar;
        if (w.m(coordinatorLayout) && !w.m(v)) {
            v.setFitsSystemWindows(true);
        }
        if (this.B == null) {
            this.g = coordinatorLayout.getResources().getDimensionPixelSize(c.a.a.a.d.design_bottom_sheet_peek_height_min);
            this.B = new WeakReference<>(v);
            if (this.h && (gVar = this.i) != null) {
                w.a(v, gVar);
            }
            c.a.a.a.b0.g gVar2 = this.i;
            if (gVar2 != null) {
                float f2 = this.r;
                if (f2 == -1.0f) {
                    f2 = w.l(v);
                }
                gVar2.b(f2);
                this.k = this.u == 3;
                this.i.c(this.k ? 0.0f : 1.0f);
            }
            k();
            if (w.n(v) == 0) {
                w.i(v, 1);
            }
        }
        if (this.v == null) {
            this.v = a.h.b.c.a(coordinatorLayout, this.J);
        }
        int top = v.getTop();
        coordinatorLayout.c(v, i);
        this.z = coordinatorLayout.getWidth();
        this.A = coordinatorLayout.getHeight();
        this.n = Math.max(0, this.A - v.getHeight());
        e();
        d();
        int i2 = this.u;
        if (i2 == 3) {
            w.f(v, h());
        } else if (i2 == 6) {
            w.f(v, this.o);
        } else if (this.s && i2 == 5) {
            w.f(v, this.A);
        } else {
            int i3 = this.u;
            if (i3 == 4) {
                w.f(v, this.q);
            } else if (i3 == 1 || i3 == 2) {
                w.f(v, top - v.getTop());
            }
        }
        this.C = new WeakReference<>(a(v));
        return true;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean b(CoordinatorLayout coordinatorLayout, V v, View view, View view2, int i, int i2) {
        this.x = 0;
        this.y = false;
        return (i & 2) != 0;
    }

    public void b(int i) {
        if (i >= 0) {
            this.m = i;
            return;
        }
        throw new IllegalArgumentException("offset must be greater than or equal to 0");
    }

    public void b(boolean z) {
        if (this.s != z) {
            this.s = z;
            if (!z && this.u == 5) {
                e(4);
            }
            k();
        }
    }

    public int b() {
        return this.u;
    }

    public static <V extends View> BottomSheetBehavior<V> b(V v) {
        ViewGroup.LayoutParams layoutParams = v.getLayoutParams();
        if (layoutParams instanceof CoordinatorLayout.f) {
            CoordinatorLayout.c d2 = ((CoordinatorLayout.f) layoutParams).d();
            if (d2 instanceof BottomSheetBehavior) {
                return (BottomSheetBehavior) d2;
            }
            throw new IllegalArgumentException("The view is not associated with BottomSheetBehavior");
        }
        throw new IllegalArgumentException("The view is not a child of CoordinatorLayout");
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean a(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        a.h.b.c cVar;
        if (!v.isShown()) {
            this.w = true;
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            j();
        }
        if (this.E == null) {
            this.E = VelocityTracker.obtain();
        }
        this.E.addMovement(motionEvent);
        if (actionMasked == 0) {
            int x = (int) motionEvent.getX();
            this.G = (int) motionEvent.getY();
            if (this.u != 2) {
                WeakReference<View> weakReference = this.C;
                View view = weakReference != null ? weakReference.get() : null;
                if (view != null && coordinatorLayout.a(view, x, this.G)) {
                    this.F = motionEvent.getPointerId(motionEvent.getActionIndex());
                    this.H = true;
                }
            }
            this.w = this.F == -1 && !coordinatorLayout.a(v, x, this.G);
        } else if (actionMasked == 1 || actionMasked == 3) {
            this.H = false;
            this.F = -1;
            if (this.w) {
                this.w = false;
                return false;
            }
        }
        if (!this.w && (cVar = this.v) != null && cVar.b(motionEvent)) {
            return true;
        }
        WeakReference<View> weakReference2 = this.C;
        View view2 = weakReference2 != null ? weakReference2.get() : null;
        return (actionMasked != 2 || view2 == null || this.w || this.u == 1 || coordinatorLayout.a(view2, (int) motionEvent.getX(), (int) motionEvent.getY()) || this.v == null || Math.abs(((float) this.G) - motionEvent.getY()) <= ((float) this.v.e())) ? false : true;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public void a(CoordinatorLayout coordinatorLayout, V v, View view, int i, int i2, int[] iArr, int i3) {
        if (i3 == 1) {
            return;
        }
        WeakReference<View> weakReference = this.C;
        if (view != (weakReference != null ? weakReference.get() : null)) {
            return;
        }
        int top = v.getTop();
        int i4 = top - i2;
        if (i2 > 0) {
            if (i4 < h()) {
                iArr[1] = top - h();
                w.f(v, -iArr[1]);
                f(3);
            } else {
                iArr[1] = i2;
                w.f(v, -i2);
                f(1);
            }
        } else if (i2 < 0 && !view.canScrollVertically(-1)) {
            int i5 = this.q;
            if (i4 > i5 && !this.s) {
                iArr[1] = top - i5;
                w.f(v, -iArr[1]);
                f(4);
            } else {
                iArr[1] = i2;
                w.f(v, -i2);
                f(1);
            }
        }
        a(v.getTop());
        this.x = i2;
        this.y = true;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public void a(CoordinatorLayout coordinatorLayout, V v, View view, int i) {
        int i2;
        int i3 = 3;
        if (v.getTop() == h()) {
            f(3);
            return;
        }
        WeakReference<View> weakReference = this.C;
        if (weakReference != null && view == weakReference.get() && this.y) {
            if (this.x > 0) {
                i2 = h();
            } else if (this.s && a(v, i())) {
                i2 = this.A;
                i3 = 5;
            } else if (this.x == 0) {
                int top = v.getTop();
                if (this.f2412b) {
                    if (Math.abs(top - this.n) < Math.abs(top - this.q)) {
                        i2 = this.n;
                    } else {
                        i2 = this.q;
                        i3 = 4;
                    }
                } else {
                    int i4 = this.o;
                    if (top < i4) {
                        if (top < Math.abs(top - this.q)) {
                            i2 = this.m;
                        } else {
                            i2 = this.o;
                        }
                    } else if (Math.abs(top - i4) < Math.abs(top - this.q)) {
                        i2 = this.o;
                    } else {
                        i2 = this.q;
                        i3 = 4;
                    }
                    i3 = 6;
                }
            } else {
                if (this.f2412b) {
                    i2 = this.q;
                } else {
                    int top2 = v.getTop();
                    if (Math.abs(top2 - this.o) < Math.abs(top2 - this.q)) {
                        i2 = this.o;
                        i3 = 6;
                    } else {
                        i2 = this.q;
                    }
                }
                i3 = 4;
            }
            a((View) v, i3, i2, false);
            this.y = false;
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean a(CoordinatorLayout coordinatorLayout, V v, View view, float f2, float f3) {
        WeakReference<View> weakReference = this.C;
        if (weakReference == null || view != weakReference.get()) {
            return false;
        }
        return this.u != 3 || super.a(coordinatorLayout, (CoordinatorLayout) v, view, f2, f3);
    }

    public void a(boolean z) {
        if (this.f2412b == z) {
            return;
        }
        this.f2412b = z;
        if (this.B != null) {
            d();
        }
        f((this.f2412b && this.u == 6) ? 3 : this.u);
        k();
    }

    public final void a(int i, boolean z) {
        V v;
        boolean z2 = true;
        if (i == -1) {
            if (!this.f2416f) {
                this.f2416f = true;
            }
            z2 = false;
        } else {
            if (this.f2416f || this.f2415e != i) {
                this.f2416f = false;
                this.f2415e = Math.max(0, i);
            }
            z2 = false;
        }
        if (!z2 || this.B == null) {
            return;
        }
        d();
        if (this.u != 4 || (v = this.B.get()) == null) {
            return;
        }
        if (z) {
            g(this.u);
        } else {
            v.requestLayout();
        }
    }

    public void a(float f2) {
        if (f2 > 0.0f && f2 < 1.0f) {
            this.p = f2;
            if (this.B != null) {
                e();
                return;
            }
            return;
        }
        throw new IllegalArgumentException("ratio must be a float value between 0 and 1");
    }

    public void a(e eVar) {
        if (this.D.contains(eVar)) {
            return;
        }
        this.D.add(eVar);
    }

    private void a(f fVar) {
        int i = this.f2411a;
        if (i == 0) {
            return;
        }
        if (i == -1 || (i & 1) == 1) {
            this.f2415e = fVar.f2425e;
        }
        int i2 = this.f2411a;
        if (i2 == -1 || (i2 & 2) == 2) {
            this.f2412b = fVar.f2426f;
        }
        int i3 = this.f2411a;
        if (i3 == -1 || (i3 & 4) == 4) {
            this.s = fVar.g;
        }
        int i4 = this.f2411a;
        if (i4 == -1 || (i4 & 8) == 8) {
            this.t = fVar.h;
        }
    }

    boolean a(View view, float f2) {
        if (this.t) {
            return true;
        }
        if (view.getTop() < this.q) {
            return false;
        }
        return Math.abs((((float) view.getTop()) + (f2 * 0.1f)) - ((float) this.q)) / ((float) f()) > 0.5f;
    }

    View a(View view) {
        if (w.G(view)) {
            return view;
        }
        if (!(view instanceof ViewGroup)) {
            return null;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View a2 = a(viewGroup.getChildAt(i));
            if (a2 != null) {
                return a2;
            }
        }
        return null;
    }

    private void a(Context context, AttributeSet attributeSet, boolean z) {
        a(context, attributeSet, z, (ColorStateList) null);
    }

    private void a(Context context, AttributeSet attributeSet, boolean z, ColorStateList colorStateList) {
        if (this.h) {
            this.j = c.a.a.a.b0.k.a(context, attributeSet, c.a.a.a.b.bottomSheetStyle, K).a();
            this.i = new c.a.a.a.b0.g(this.j);
            this.i.a(context);
            if (z && colorStateList != null) {
                this.i.a(colorStateList);
                return;
            }
            TypedValue typedValue = new TypedValue();
            context.getTheme().resolveAttribute(R.attr.colorBackground, typedValue, true);
            this.i.setTint(typedValue.data);
        }
    }

    void a(View view, int i) {
        int i2;
        int i3;
        if (i == 4) {
            i2 = this.q;
        } else if (i == 6) {
            int i4 = this.o;
            if (!this.f2412b || i4 > (i3 = this.n)) {
                i2 = i4;
            } else {
                i2 = i3;
                i = 3;
            }
        } else if (i == 3) {
            i2 = h();
        } else if (this.s && i == 5) {
            i2 = this.A;
        } else {
            throw new IllegalArgumentException("Illegal state argument: " + i);
        }
        a(view, i, i2, false);
    }

    void a(View view, int i, int i2, boolean z) {
        boolean b2;
        if (z) {
            b2 = this.v.d(view.getLeft(), i2);
        } else {
            b2 = this.v.b(view, view.getLeft(), i2);
        }
        if (b2) {
            f(2);
            h(i);
            w.a(view, new g(view, i));
            return;
        }
        f(i);
    }

    void a(int i) {
        float f2;
        float f3;
        V v = this.B.get();
        if (v == null || this.D.isEmpty()) {
            return;
        }
        int i2 = this.q;
        if (i <= i2 && i2 != h()) {
            int i3 = this.q;
            f2 = i3 - i;
            f3 = i3 - h();
        } else {
            int i4 = this.q;
            f2 = i4 - i;
            f3 = this.A - i4;
        }
        float f4 = f2 / f3;
        for (int i5 = 0; i5 < this.D.size(); i5++) {
            this.D.get(i5).onSlide(v, f4);
        }
    }

    private void a(V v, c.a aVar, int i) {
        w.a(v, aVar, null, new d(i));
    }
}
