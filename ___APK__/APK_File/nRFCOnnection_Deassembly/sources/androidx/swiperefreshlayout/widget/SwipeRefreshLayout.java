package androidx.swiperefreshlayout.widget;

import a.f.l.l;
import a.f.l.m;
import a.f.l.p;
import a.f.l.q;
import a.f.l.w;
import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Transformation;
import android.widget.AbsListView;
import android.widget.ListView;

/* loaded from: classes.dex */
public class SwipeRefreshLayout extends ViewGroup implements p, l {
    private static final String P = SwipeRefreshLayout.class.getSimpleName();
    private static final int[] Q = {R.attr.enabled};
    int A;
    int B;
    androidx.swiperefreshlayout.widget.b C;
    private Animation D;
    private Animation E;
    private Animation F;
    private Animation G;
    private Animation H;
    boolean I;
    private int J;
    boolean K;
    private i L;
    private Animation.AnimationListener M;
    private final Animation N;
    private final Animation O;

    /* renamed from: b, reason: collision with root package name */
    private View f1952b;

    /* renamed from: c, reason: collision with root package name */
    j f1953c;

    /* renamed from: d, reason: collision with root package name */
    boolean f1954d;

    /* renamed from: e, reason: collision with root package name */
    private int f1955e;

    /* renamed from: f, reason: collision with root package name */
    private float f1956f;
    private float g;
    private final q h;
    private final m i;
    private final int[] j;
    private final int[] k;
    private boolean l;
    private int m;
    int n;
    private float o;
    private float p;
    private boolean q;
    private int r;
    boolean s;
    private boolean t;
    private final DecelerateInterpolator u;
    androidx.swiperefreshlayout.widget.a v;
    private int w;
    protected int x;
    float y;
    protected int z;

    /* loaded from: classes.dex */
    class a implements Animation.AnimationListener {
        a() {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            j jVar;
            SwipeRefreshLayout swipeRefreshLayout = SwipeRefreshLayout.this;
            if (swipeRefreshLayout.f1954d) {
                swipeRefreshLayout.C.setAlpha(255);
                SwipeRefreshLayout.this.C.start();
                SwipeRefreshLayout swipeRefreshLayout2 = SwipeRefreshLayout.this;
                if (swipeRefreshLayout2.I && (jVar = swipeRefreshLayout2.f1953c) != null) {
                    jVar.onRefresh();
                }
                SwipeRefreshLayout swipeRefreshLayout3 = SwipeRefreshLayout.this;
                swipeRefreshLayout3.n = swipeRefreshLayout3.v.getTop();
                return;
            }
            swipeRefreshLayout.b();
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class b extends Animation {
        b() {
        }

        @Override // android.view.animation.Animation
        public void applyTransformation(float f2, Transformation transformation) {
            SwipeRefreshLayout.this.setAnimationProgress(f2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class c extends Animation {
        c() {
        }

        @Override // android.view.animation.Animation
        public void applyTransformation(float f2, Transformation transformation) {
            SwipeRefreshLayout.this.setAnimationProgress(1.0f - f2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class d extends Animation {

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f1960b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ int f1961c;

        d(int i, int i2) {
            this.f1960b = i;
            this.f1961c = i2;
        }

        @Override // android.view.animation.Animation
        public void applyTransformation(float f2, Transformation transformation) {
            SwipeRefreshLayout.this.C.setAlpha((int) (this.f1960b + ((this.f1961c - r0) * f2)));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class e implements Animation.AnimationListener {
        e() {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            SwipeRefreshLayout swipeRefreshLayout = SwipeRefreshLayout.this;
            if (swipeRefreshLayout.s) {
                return;
            }
            swipeRefreshLayout.a((Animation.AnimationListener) null);
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
        }
    }

    /* loaded from: classes.dex */
    class f extends Animation {
        f() {
        }

        @Override // android.view.animation.Animation
        public void applyTransformation(float f2, Transformation transformation) {
            int i;
            SwipeRefreshLayout swipeRefreshLayout = SwipeRefreshLayout.this;
            if (!swipeRefreshLayout.K) {
                i = swipeRefreshLayout.A - Math.abs(swipeRefreshLayout.z);
            } else {
                i = swipeRefreshLayout.A;
            }
            SwipeRefreshLayout swipeRefreshLayout2 = SwipeRefreshLayout.this;
            SwipeRefreshLayout.this.setTargetOffsetTopAndBottom((swipeRefreshLayout2.x + ((int) ((i - r1) * f2))) - swipeRefreshLayout2.v.getTop());
            SwipeRefreshLayout.this.C.a(1.0f - f2);
        }
    }

    /* loaded from: classes.dex */
    class g extends Animation {
        g() {
        }

        @Override // android.view.animation.Animation
        public void applyTransformation(float f2, Transformation transformation) {
            SwipeRefreshLayout.this.a(f2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class h extends Animation {
        h() {
        }

        @Override // android.view.animation.Animation
        public void applyTransformation(float f2, Transformation transformation) {
            SwipeRefreshLayout swipeRefreshLayout = SwipeRefreshLayout.this;
            float f3 = swipeRefreshLayout.y;
            swipeRefreshLayout.setAnimationProgress(f3 + ((-f3) * f2));
            SwipeRefreshLayout.this.a(f2);
        }
    }

    /* loaded from: classes.dex */
    public interface i {
        boolean a(SwipeRefreshLayout swipeRefreshLayout, View view);
    }

    /* loaded from: classes.dex */
    public interface j {
        void onRefresh();
    }

    public SwipeRefreshLayout(Context context) {
        this(context, null);
    }

    private void a(boolean z, boolean z2) {
        if (this.f1954d != z) {
            this.I = z2;
            d();
            this.f1954d = z;
            if (this.f1954d) {
                a(this.n, this.M);
            } else {
                a(this.M);
            }
        }
    }

    private void c() {
        this.v = new androidx.swiperefreshlayout.widget.a(getContext(), -328966);
        this.C = new androidx.swiperefreshlayout.widget.b(getContext());
        this.C.a(1);
        this.v.setImageDrawable(this.C);
        this.v.setVisibility(8);
        addView(this.v);
    }

    private void d() {
        if (this.f1952b == null) {
            for (int i2 = 0; i2 < getChildCount(); i2++) {
                View childAt = getChildAt(i2);
                if (!childAt.equals(this.v)) {
                    this.f1952b = childAt;
                    return;
                }
            }
        }
    }

    private void e() {
        this.G = a(this.C.getAlpha(), 255);
    }

    private void f() {
        this.F = a(this.C.getAlpha(), 76);
    }

    private void setColorViewAlpha(int i2) {
        this.v.getBackground().setAlpha(i2);
        this.C.setAlpha(i2);
    }

    void b() {
        this.v.clearAnimation();
        this.C.stop();
        this.v.setVisibility(8);
        setColorViewAlpha(255);
        if (this.s) {
            setAnimationProgress(0.0f);
        } else {
            setTargetOffsetTopAndBottom(this.z - this.n);
        }
        this.n = this.v.getTop();
    }

    @Override // android.view.View
    public boolean dispatchNestedFling(float f2, float f3, boolean z) {
        return this.i.a(f2, f3, z);
    }

    @Override // android.view.View
    public boolean dispatchNestedPreFling(float f2, float f3) {
        return this.i.a(f2, f3);
    }

    @Override // android.view.View
    public boolean dispatchNestedPreScroll(int i2, int i3, int[] iArr, int[] iArr2) {
        return this.i.a(i2, i3, iArr, iArr2);
    }

    @Override // android.view.View
    public boolean dispatchNestedScroll(int i2, int i3, int i4, int i5, int[] iArr) {
        return this.i.a(i2, i3, i4, i5, iArr);
    }

    @Override // android.view.ViewGroup
    protected int getChildDrawingOrder(int i2, int i3) {
        int i4 = this.w;
        return i4 < 0 ? i3 : i3 == i2 + (-1) ? i4 : i3 >= i4 ? i3 + 1 : i3;
    }

    @Override // android.view.ViewGroup
    public int getNestedScrollAxes() {
        return this.h.a();
    }

    public int getProgressCircleDiameter() {
        return this.J;
    }

    public int getProgressViewEndOffset() {
        return this.A;
    }

    public int getProgressViewStartOffset() {
        return this.z;
    }

    @Override // android.view.View
    public boolean hasNestedScrollingParent() {
        return this.i.a();
    }

    @Override // android.view.View, a.f.l.l
    public boolean isNestedScrollingEnabled() {
        return this.i.b();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        b();
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        d();
        int actionMasked = motionEvent.getActionMasked();
        if (this.t && actionMasked == 0) {
            this.t = false;
        }
        if (!isEnabled() || this.t || a() || this.f1954d || this.l) {
            return false;
        }
        if (actionMasked != 0) {
            if (actionMasked != 1) {
                if (actionMasked == 2) {
                    int i2 = this.r;
                    if (i2 == -1) {
                        Log.e(P, "Got ACTION_MOVE event but don't have an active pointer id.");
                        return false;
                    }
                    int findPointerIndex = motionEvent.findPointerIndex(i2);
                    if (findPointerIndex < 0) {
                        return false;
                    }
                    d(motionEvent.getY(findPointerIndex));
                } else if (actionMasked != 3) {
                    if (actionMasked == 6) {
                        a(motionEvent);
                    }
                }
            }
            this.q = false;
            this.r = -1;
        } else {
            setTargetOffsetTopAndBottom(this.z - this.v.getTop());
            this.r = motionEvent.getPointerId(0);
            this.q = false;
            int findPointerIndex2 = motionEvent.findPointerIndex(this.r);
            if (findPointerIndex2 < 0) {
                return false;
            }
            this.p = motionEvent.getY(findPointerIndex2);
        }
        return this.q;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        if (getChildCount() == 0) {
            return;
        }
        if (this.f1952b == null) {
            d();
        }
        View view = this.f1952b;
        if (view == null) {
            return;
        }
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        view.layout(paddingLeft, paddingTop, ((measuredWidth - getPaddingLeft()) - getPaddingRight()) + paddingLeft, ((measuredHeight - getPaddingTop()) - getPaddingBottom()) + paddingTop);
        int measuredWidth2 = this.v.getMeasuredWidth();
        int measuredHeight2 = this.v.getMeasuredHeight();
        int i6 = measuredWidth / 2;
        int i7 = measuredWidth2 / 2;
        int i8 = this.n;
        this.v.layout(i6 - i7, i8, i6 + i7, measuredHeight2 + i8);
    }

    @Override // android.view.View
    public void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        if (this.f1952b == null) {
            d();
        }
        View view = this.f1952b;
        if (view == null) {
            return;
        }
        view.measure(View.MeasureSpec.makeMeasureSpec((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), 1073741824), View.MeasureSpec.makeMeasureSpec((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), 1073741824));
        this.v.measure(View.MeasureSpec.makeMeasureSpec(this.J, 1073741824), View.MeasureSpec.makeMeasureSpec(this.J, 1073741824));
        this.w = -1;
        for (int i4 = 0; i4 < getChildCount(); i4++) {
            if (getChildAt(i4) == this.v) {
                this.w = i4;
                return;
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, a.f.l.p
    public boolean onNestedFling(View view, float f2, float f3, boolean z) {
        return dispatchNestedFling(f2, f3, z);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, a.f.l.p
    public boolean onNestedPreFling(View view, float f2, float f3) {
        return dispatchNestedPreFling(f2, f3);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, a.f.l.p
    public void onNestedPreScroll(View view, int i2, int i3, int[] iArr) {
        if (i3 > 0) {
            float f2 = this.g;
            if (f2 > 0.0f) {
                float f3 = i3;
                if (f3 > f2) {
                    iArr[1] = i3 - ((int) f2);
                    this.g = 0.0f;
                } else {
                    this.g = f2 - f3;
                    iArr[1] = i3;
                }
                c(this.g);
            }
        }
        if (this.K && i3 > 0 && this.g == 0.0f && Math.abs(i3 - iArr[1]) > 0) {
            this.v.setVisibility(8);
        }
        int[] iArr2 = this.j;
        if (dispatchNestedPreScroll(i2 - iArr[0], i3 - iArr[1], iArr2, null)) {
            iArr[0] = iArr[0] + iArr2[0];
            iArr[1] = iArr[1] + iArr2[1];
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, a.f.l.p
    public void onNestedScroll(View view, int i2, int i3, int i4, int i5) {
        dispatchNestedScroll(i2, i3, i4, i5, this.k);
        if (i5 + this.k[1] >= 0 || a()) {
            return;
        }
        this.g += Math.abs(r11);
        c(this.g);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, a.f.l.p
    public void onNestedScrollAccepted(View view, View view2, int i2) {
        this.h.a(view, view2, i2);
        startNestedScroll(i2 & 2);
        this.g = 0.0f;
        this.l = true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, a.f.l.p
    public boolean onStartNestedScroll(View view, View view2, int i2) {
        return (!isEnabled() || this.t || this.f1954d || (i2 & 2) == 0) ? false : true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, a.f.l.p
    public void onStopNestedScroll(View view) {
        this.h.a(view);
        this.l = false;
        float f2 = this.g;
        if (f2 > 0.0f) {
            b(f2);
            this.g = 0.0f;
        }
        stopNestedScroll();
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (this.t && actionMasked == 0) {
            this.t = false;
        }
        if (!isEnabled() || this.t || a() || this.f1954d || this.l) {
            return false;
        }
        if (actionMasked == 0) {
            this.r = motionEvent.getPointerId(0);
            this.q = false;
        } else {
            if (actionMasked == 1) {
                int findPointerIndex = motionEvent.findPointerIndex(this.r);
                if (findPointerIndex < 0) {
                    Log.e(P, "Got ACTION_UP event but don't have an active pointer id.");
                    return false;
                }
                if (this.q) {
                    float y = (motionEvent.getY(findPointerIndex) - this.o) * 0.5f;
                    this.q = false;
                    b(y);
                }
                this.r = -1;
                return false;
            }
            if (actionMasked == 2) {
                int findPointerIndex2 = motionEvent.findPointerIndex(this.r);
                if (findPointerIndex2 < 0) {
                    Log.e(P, "Got ACTION_MOVE event but have an invalid active pointer id.");
                    return false;
                }
                float y2 = motionEvent.getY(findPointerIndex2);
                d(y2);
                if (this.q) {
                    float f2 = (y2 - this.o) * 0.5f;
                    if (f2 <= 0.0f) {
                        return false;
                    }
                    c(f2);
                }
            } else {
                if (actionMasked == 3) {
                    return false;
                }
                if (actionMasked == 5) {
                    int actionIndex = motionEvent.getActionIndex();
                    if (actionIndex < 0) {
                        Log.e(P, "Got ACTION_POINTER_DOWN event but have an invalid action index.");
                        return false;
                    }
                    this.r = motionEvent.getPointerId(actionIndex);
                } else if (actionMasked == 6) {
                    a(motionEvent);
                }
            }
        }
        return true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean z) {
        if (Build.VERSION.SDK_INT >= 21 || !(this.f1952b instanceof AbsListView)) {
            View view = this.f1952b;
            if (view == null || w.G(view)) {
                super.requestDisallowInterceptTouchEvent(z);
            }
        }
    }

    void setAnimationProgress(float f2) {
        this.v.setScaleX(f2);
        this.v.setScaleY(f2);
    }

    @Deprecated
    public void setColorScheme(int... iArr) {
        setColorSchemeResources(iArr);
    }

    public void setColorSchemeColors(int... iArr) {
        d();
        this.C.a(iArr);
    }

    public void setColorSchemeResources(int... iArr) {
        Context context = getContext();
        int[] iArr2 = new int[iArr.length];
        for (int i2 = 0; i2 < iArr.length; i2++) {
            iArr2[i2] = a.f.d.b.a(context, iArr[i2]);
        }
        setColorSchemeColors(iArr2);
    }

    public void setDistanceToTriggerSync(int i2) {
        this.f1956f = i2;
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        if (z) {
            return;
        }
        b();
    }

    @Override // android.view.View
    public void setNestedScrollingEnabled(boolean z) {
        this.i.a(z);
    }

    public void setOnChildScrollUpCallback(i iVar) {
        this.L = iVar;
    }

    public void setOnRefreshListener(j jVar) {
        this.f1953c = jVar;
    }

    @Deprecated
    public void setProgressBackgroundColor(int i2) {
        setProgressBackgroundColorSchemeResource(i2);
    }

    public void setProgressBackgroundColorSchemeColor(int i2) {
        this.v.setBackgroundColor(i2);
    }

    public void setProgressBackgroundColorSchemeResource(int i2) {
        setProgressBackgroundColorSchemeColor(a.f.d.b.a(getContext(), i2));
    }

    public void setRefreshing(boolean z) {
        int i2;
        if (z && this.f1954d != z) {
            this.f1954d = z;
            if (!this.K) {
                i2 = this.A + this.z;
            } else {
                i2 = this.A;
            }
            setTargetOffsetTopAndBottom(i2 - this.n);
            this.I = false;
            b(this.M);
            return;
        }
        a(z, false);
    }

    public void setSize(int i2) {
        if (i2 == 0 || i2 == 1) {
            DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            if (i2 == 0) {
                this.J = (int) (displayMetrics.density * 56.0f);
            } else {
                this.J = (int) (displayMetrics.density * 40.0f);
            }
            this.v.setImageDrawable(null);
            this.C.a(i2);
            this.v.setImageDrawable(this.C);
        }
    }

    public void setSlingshotDistance(int i2) {
        this.B = i2;
    }

    void setTargetOffsetTopAndBottom(int i2) {
        this.v.bringToFront();
        w.f(this.v, i2);
        this.n = this.v.getTop();
    }

    @Override // android.view.View
    public boolean startNestedScroll(int i2) {
        return this.i.b(i2);
    }

    @Override // android.view.View, a.f.l.l
    public void stopNestedScroll() {
        this.i.c();
    }

    public SwipeRefreshLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1954d = false;
        this.f1956f = -1.0f;
        this.j = new int[2];
        this.k = new int[2];
        this.r = -1;
        this.w = -1;
        this.M = new a();
        this.N = new f();
        this.O = new g();
        this.f1955e = ViewConfiguration.get(context).getScaledTouchSlop();
        this.m = getResources().getInteger(R.integer.config_mediumAnimTime);
        setWillNotDraw(false);
        this.u = new DecelerateInterpolator(2.0f);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        this.J = (int) (displayMetrics.density * 40.0f);
        c();
        setChildrenDrawingOrderEnabled(true);
        this.A = (int) (displayMetrics.density * 64.0f);
        this.f1956f = this.A;
        this.h = new q(this);
        this.i = new m(this);
        setNestedScrollingEnabled(true);
        int i2 = -this.J;
        this.n = i2;
        this.z = i2;
        a(1.0f);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, Q);
        setEnabled(obtainStyledAttributes.getBoolean(0, true));
        obtainStyledAttributes.recycle();
    }

    private void d(float f2) {
        float f3 = this.p;
        float f4 = f2 - f3;
        int i2 = this.f1955e;
        if (f4 <= i2 || this.q) {
            return;
        }
        this.o = f3 + i2;
        this.q = true;
        this.C.setAlpha(76);
    }

    private void c(float f2) {
        this.C.a(true);
        float min = Math.min(1.0f, Math.abs(f2 / this.f1956f));
        double d2 = min;
        Double.isNaN(d2);
        float max = (((float) Math.max(d2 - 0.4d, 0.0d)) * 5.0f) / 3.0f;
        float abs = Math.abs(f2) - this.f1956f;
        int i2 = this.B;
        if (i2 <= 0) {
            i2 = this.K ? this.A - this.z : this.A;
        }
        float f3 = i2;
        double max2 = Math.max(0.0f, Math.min(abs, f3 * 2.0f) / f3) / 4.0f;
        double pow = Math.pow(max2, 2.0d);
        Double.isNaN(max2);
        float f4 = ((float) (max2 - pow)) * 2.0f;
        int i3 = this.z + ((int) ((f3 * min) + (f3 * f4 * 2.0f)));
        if (this.v.getVisibility() != 0) {
            this.v.setVisibility(0);
        }
        if (!this.s) {
            this.v.setScaleX(1.0f);
            this.v.setScaleY(1.0f);
        }
        if (this.s) {
            setAnimationProgress(Math.min(1.0f, f2 / this.f1956f));
        }
        if (f2 < this.f1956f) {
            if (this.C.getAlpha() > 76 && !a(this.F)) {
                f();
            }
        } else if (this.C.getAlpha() < 255 && !a(this.G)) {
            e();
        }
        this.C.a(0.0f, Math.min(0.8f, max * 0.8f));
        this.C.a(Math.min(1.0f, max));
        this.C.b((((max * 0.4f) - 0.25f) + (f4 * 2.0f)) * 0.5f);
        setTargetOffsetTopAndBottom(i3 - this.n);
    }

    void a(Animation.AnimationListener animationListener) {
        this.E = new c();
        this.E.setDuration(150L);
        this.v.a(animationListener);
        this.v.clearAnimation();
        this.v.startAnimation(this.E);
    }

    private void b(Animation.AnimationListener animationListener) {
        this.v.setVisibility(0);
        this.C.setAlpha(255);
        this.D = new b();
        this.D.setDuration(this.m);
        if (animationListener != null) {
            this.v.a(animationListener);
        }
        this.v.clearAnimation();
        this.v.startAnimation(this.D);
    }

    private Animation a(int i2, int i3) {
        d dVar = new d(i2, i3);
        dVar.setDuration(300L);
        this.v.a(null);
        this.v.clearAnimation();
        this.v.startAnimation(dVar);
        return dVar;
    }

    private void b(float f2) {
        if (f2 > this.f1956f) {
            a(true, true);
            return;
        }
        this.f1954d = false;
        this.C.a(0.0f, 0.0f);
        b(this.n, this.s ? null : new e());
        this.C.a(false);
    }

    public boolean a() {
        i iVar = this.L;
        if (iVar != null) {
            return iVar.a(this, this.f1952b);
        }
        View view = this.f1952b;
        if (view instanceof ListView) {
            return androidx.core.widget.g.a((ListView) view, -1);
        }
        return view.canScrollVertically(-1);
    }

    private boolean a(Animation animation) {
        return (animation == null || !animation.hasStarted() || animation.hasEnded()) ? false : true;
    }

    private void a(int i2, Animation.AnimationListener animationListener) {
        this.x = i2;
        this.N.reset();
        this.N.setDuration(200L);
        this.N.setInterpolator(this.u);
        if (animationListener != null) {
            this.v.a(animationListener);
        }
        this.v.clearAnimation();
        this.v.startAnimation(this.N);
    }

    private void b(int i2, Animation.AnimationListener animationListener) {
        if (this.s) {
            c(i2, animationListener);
            return;
        }
        this.x = i2;
        this.O.reset();
        this.O.setDuration(200L);
        this.O.setInterpolator(this.u);
        if (animationListener != null) {
            this.v.a(animationListener);
        }
        this.v.clearAnimation();
        this.v.startAnimation(this.O);
    }

    void a(float f2) {
        setTargetOffsetTopAndBottom((this.x + ((int) ((this.z - r0) * f2))) - this.v.getTop());
    }

    private void c(int i2, Animation.AnimationListener animationListener) {
        this.x = i2;
        this.y = this.v.getScaleX();
        this.H = new h();
        this.H.setDuration(150L);
        if (animationListener != null) {
            this.v.a(animationListener);
        }
        this.v.clearAnimation();
        this.v.startAnimation(this.H);
    }

    private void a(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.r) {
            this.r = motionEvent.getPointerId(actionIndex == 0 ? 1 : 0);
        }
    }
}
