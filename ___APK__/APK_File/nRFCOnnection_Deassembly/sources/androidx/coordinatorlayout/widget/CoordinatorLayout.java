package androidx.coordinatorlayout.widget;

import a.f.l.e0;
import a.f.l.n;
import a.f.l.o;
import a.f.l.q;
import a.f.l.r;
import a.f.l.w;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class CoordinatorLayout extends ViewGroup implements n, o {
    static final String v;
    static final Class<?>[] w;
    static final ThreadLocal<Map<String, Constructor<c>>> x;
    static final Comparator<View> y;
    private static final a.f.k.e<Rect> z;

    /* renamed from: b, reason: collision with root package name */
    private final List<View> f1162b;

    /* renamed from: c, reason: collision with root package name */
    private final androidx.coordinatorlayout.widget.a<View> f1163c;

    /* renamed from: d, reason: collision with root package name */
    private final List<View> f1164d;

    /* renamed from: e, reason: collision with root package name */
    private final List<View> f1165e;

    /* renamed from: f, reason: collision with root package name */
    private Paint f1166f;
    private final int[] g;
    private final int[] h;
    private boolean i;
    private boolean j;
    private int[] k;
    private View l;
    private View m;
    private g n;
    private boolean o;
    private e0 p;
    private boolean q;
    private Drawable r;
    ViewGroup.OnHierarchyChangeListener s;
    private r t;
    private final q u;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a implements r {
        a() {
        }

        @Override // a.f.l.r
        public e0 a(View view, e0 e0Var) {
            return CoordinatorLayout.this.a(e0Var);
        }
    }

    /* loaded from: classes.dex */
    public interface b {
        c getBehavior();
    }

    /* loaded from: classes.dex */
    public static abstract class c<V extends View> {
        public c() {
        }

        public e0 a(CoordinatorLayout coordinatorLayout, V v, e0 e0Var) {
            return e0Var;
        }

        public void a() {
        }

        public void a(f fVar) {
        }

        public void a(CoordinatorLayout coordinatorLayout, V v, Parcelable parcelable) {
        }

        @Deprecated
        public void a(CoordinatorLayout coordinatorLayout, V v, View view, int i, int i2, int i3, int i4) {
        }

        @Deprecated
        public void a(CoordinatorLayout coordinatorLayout, V v, View view, int i, int i2, int[] iArr) {
        }

        @Deprecated
        public void a(CoordinatorLayout coordinatorLayout, V v, View view, View view2, int i) {
        }

        public boolean a(CoordinatorLayout coordinatorLayout, V v) {
            return c(coordinatorLayout, v) > 0.0f;
        }

        public boolean a(CoordinatorLayout coordinatorLayout, V v, int i) {
            return false;
        }

        public boolean a(CoordinatorLayout coordinatorLayout, V v, int i, int i2, int i3, int i4) {
            return false;
        }

        public boolean a(CoordinatorLayout coordinatorLayout, V v, Rect rect) {
            return false;
        }

        public boolean a(CoordinatorLayout coordinatorLayout, V v, Rect rect, boolean z) {
            return false;
        }

        public boolean a(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
            return false;
        }

        public boolean a(CoordinatorLayout coordinatorLayout, V v, View view) {
            return false;
        }

        public boolean a(CoordinatorLayout coordinatorLayout, V v, View view, float f2, float f3) {
            return false;
        }

        public boolean a(CoordinatorLayout coordinatorLayout, V v, View view, float f2, float f3, boolean z) {
            return false;
        }

        public int b(CoordinatorLayout coordinatorLayout, V v) {
            return -16777216;
        }

        public boolean b(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
            return false;
        }

        public boolean b(CoordinatorLayout coordinatorLayout, V v, View view) {
            return false;
        }

        @Deprecated
        public boolean b(CoordinatorLayout coordinatorLayout, V v, View view, View view2, int i) {
            return false;
        }

        public boolean b(CoordinatorLayout coordinatorLayout, V v, View view, View view2, int i, int i2) {
            if (i2 == 0) {
                return b(coordinatorLayout, v, view, view2, i);
            }
            return false;
        }

        public float c(CoordinatorLayout coordinatorLayout, V v) {
            return 0.0f;
        }

        public void c(CoordinatorLayout coordinatorLayout, V v, View view) {
        }

        public Parcelable d(CoordinatorLayout coordinatorLayout, V v) {
            return View.BaseSavedState.EMPTY_STATE;
        }

        @Deprecated
        public void d(CoordinatorLayout coordinatorLayout, V v, View view) {
        }

        public c(Context context, AttributeSet attributeSet) {
        }

        public void a(CoordinatorLayout coordinatorLayout, V v, View view, View view2, int i, int i2) {
            if (i2 == 0) {
                a(coordinatorLayout, (CoordinatorLayout) v, view, view2, i);
            }
        }

        public void a(CoordinatorLayout coordinatorLayout, V v, View view, int i) {
            if (i == 0) {
                d(coordinatorLayout, v, view);
            }
        }

        @Deprecated
        public void a(CoordinatorLayout coordinatorLayout, V v, View view, int i, int i2, int i3, int i4, int i5) {
            if (i5 == 0) {
                a(coordinatorLayout, (CoordinatorLayout) v, view, i, i2, i3, i4);
            }
        }

        public void a(CoordinatorLayout coordinatorLayout, V v, View view, int i, int i2, int i3, int i4, int i5, int[] iArr) {
            iArr[0] = iArr[0] + i3;
            iArr[1] = iArr[1] + i4;
            a(coordinatorLayout, v, view, i, i2, i3, i4, i5);
        }

        public void a(CoordinatorLayout coordinatorLayout, V v, View view, int i, int i2, int[] iArr, int i3) {
            if (i3 == 0) {
                a(coordinatorLayout, (CoordinatorLayout) v, view, i, i2, iArr);
            }
        }
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Deprecated
    /* loaded from: classes.dex */
    public @interface d {
        Class<? extends c> value();
    }

    /* loaded from: classes.dex */
    private class e implements ViewGroup.OnHierarchyChangeListener {
        e() {
        }

        @Override // android.view.ViewGroup.OnHierarchyChangeListener
        public void onChildViewAdded(View view, View view2) {
            ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener = CoordinatorLayout.this.s;
            if (onHierarchyChangeListener != null) {
                onHierarchyChangeListener.onChildViewAdded(view, view2);
            }
        }

        @Override // android.view.ViewGroup.OnHierarchyChangeListener
        public void onChildViewRemoved(View view, View view2) {
            CoordinatorLayout.this.a(2);
            ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener = CoordinatorLayout.this.s;
            if (onHierarchyChangeListener != null) {
                onHierarchyChangeListener.onChildViewRemoved(view, view2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class g implements ViewTreeObserver.OnPreDrawListener {
        g() {
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            CoordinatorLayout.this.a(0);
            return true;
        }
    }

    /* loaded from: classes.dex */
    static class i implements Comparator<View> {
        i() {
        }

        @Override // java.util.Comparator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(View view, View view2) {
            float z = w.z(view);
            float z2 = w.z(view2);
            if (z > z2) {
                return -1;
            }
            return z < z2 ? 1 : 0;
        }
    }

    static {
        Package r0 = CoordinatorLayout.class.getPackage();
        v = r0 != null ? r0.getName() : null;
        if (Build.VERSION.SDK_INT >= 21) {
            y = new i();
        } else {
            y = null;
        }
        w = new Class[]{Context.class, AttributeSet.class};
        x = new ThreadLocal<>();
        z = new a.f.k.g(12);
    }

    public CoordinatorLayout(Context context) {
        this(context, null);
    }

    private static int a(int i2, int i3, int i4) {
        return i2 < i3 ? i3 : i2 > i4 ? i4 : i2;
    }

    private static void a(Rect rect) {
        rect.setEmpty();
        z.a(rect);
    }

    private int b(int i2) {
        int[] iArr = this.k;
        if (iArr == null) {
            Log.e("CoordinatorLayout", "No keylines defined for " + this + " - attempted index lookup " + i2);
            return 0;
        }
        if (i2 >= 0 && i2 < iArr.length) {
            return iArr[i2];
        }
        Log.e("CoordinatorLayout", "Keyline index " + i2 + " out of range for " + this);
        return 0;
    }

    private static int c(int i2) {
        if (i2 == 0) {
            return 17;
        }
        return i2;
    }

    private static int d(int i2) {
        if ((i2 & 7) == 0) {
            i2 |= 8388611;
        }
        return (i2 & 112) == 0 ? i2 | 48 : i2;
    }

    private static Rect d() {
        Rect a2 = z.a();
        return a2 == null ? new Rect() : a2;
    }

    private static int e(int i2) {
        if (i2 == 0) {
            return 8388661;
        }
        return i2;
    }

    private void e() {
        this.f1162b.clear();
        this.f1163c.a();
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            f d2 = d(childAt);
            d2.a(this, childAt);
            this.f1163c.a((androidx.coordinatorlayout.widget.a<View>) childAt);
            for (int i3 = 0; i3 < childCount; i3++) {
                if (i3 != i2) {
                    View childAt2 = getChildAt(i3);
                    if (d2.a(this, childAt, childAt2)) {
                        if (!this.f1163c.b(childAt2)) {
                            this.f1163c.a((androidx.coordinatorlayout.widget.a<View>) childAt2);
                        }
                        this.f1163c.a(childAt2, childAt);
                    }
                }
            }
        }
        this.f1162b.addAll(this.f1163c.b());
        Collections.reverse(this.f1162b);
    }

    private void f(View view, int i2) {
        f fVar = (f) view.getLayoutParams();
        int i3 = fVar.j;
        if (i3 != i2) {
            w.f(view, i2 - i3);
            fVar.j = i2;
        }
    }

    public void c(View view, int i2) {
        f fVar = (f) view.getLayoutParams();
        if (!fVar.a()) {
            View view2 = fVar.k;
            if (view2 != null) {
                a(view, view2, i2);
                return;
            }
            int i3 = fVar.f1173e;
            if (i3 >= 0) {
                b(view, i3, i2);
                return;
            } else {
                d(view, i2);
                return;
            }
        }
        throw new IllegalStateException("An anchor may not be changed after CoordinatorLayout measurement begins before layout is complete.");
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof f) && super.checkLayoutParams(layoutParams);
    }

    @Override // android.view.ViewGroup
    protected boolean drawChild(Canvas canvas, View view, long j) {
        f fVar = (f) view.getLayoutParams();
        c cVar = fVar.f1169a;
        if (cVar != null) {
            float c2 = cVar.c(this, view);
            if (c2 > 0.0f) {
                if (this.f1166f == null) {
                    this.f1166f = new Paint();
                }
                this.f1166f.setColor(fVar.f1169a.b(this, view));
                this.f1166f.setAlpha(a(Math.round(c2 * 255.0f), 0, 255));
                int save = canvas.save();
                if (view.isOpaque()) {
                    canvas.clipRect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom(), Region.Op.DIFFERENCE);
                }
                canvas.drawRect(getPaddingLeft(), getPaddingTop(), getWidth() - getPaddingRight(), getHeight() - getPaddingBottom(), this.f1166f);
                canvas.restoreToCount(save);
            }
        }
        return super.drawChild(canvas, view, j);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        Drawable drawable = this.r;
        boolean z2 = false;
        if (drawable != null && drawable.isStateful()) {
            z2 = false | drawable.setState(drawableState);
        }
        if (z2) {
            invalidate();
        }
    }

    final List<View> getDependencySortedChildren() {
        e();
        return Collections.unmodifiableList(this.f1162b);
    }

    public final e0 getLastWindowInsets() {
        return this.p;
    }

    @Override // android.view.ViewGroup
    public int getNestedScrollAxes() {
        return this.u.a();
    }

    public Drawable getStatusBarBackground() {
        return this.r;
    }

    @Override // android.view.View
    protected int getSuggestedMinimumHeight() {
        return Math.max(super.getSuggestedMinimumHeight(), getPaddingTop() + getPaddingBottom());
    }

    @Override // android.view.View
    protected int getSuggestedMinimumWidth() {
        return Math.max(super.getSuggestedMinimumWidth(), getPaddingLeft() + getPaddingRight());
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        a(false);
        if (this.o) {
            if (this.n == null) {
                this.n = new g();
            }
            getViewTreeObserver().addOnPreDrawListener(this.n);
        }
        if (this.p == null && w.m(this)) {
            w.L(this);
        }
        this.j = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        a(false);
        if (this.o && this.n != null) {
            getViewTreeObserver().removeOnPreDrawListener(this.n);
        }
        View view = this.m;
        if (view != null) {
            onStopNestedScroll(view);
        }
        this.j = false;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!this.q || this.r == null) {
            return;
        }
        e0 e0Var = this.p;
        int e2 = e0Var != null ? e0Var.e() : 0;
        if (e2 > 0) {
            this.r.setBounds(0, 0, getWidth(), e2);
            this.r.draw(canvas);
        }
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            a(true);
        }
        boolean a2 = a(motionEvent, 0);
        if (actionMasked == 1 || actionMasked == 3) {
            a(true);
        }
        return a2;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        c d2;
        int q = w.q(this);
        int size = this.f1162b.size();
        for (int i6 = 0; i6 < size; i6++) {
            View view = this.f1162b.get(i6);
            if (view.getVisibility() != 8 && ((d2 = ((f) view.getLayoutParams()).d()) == null || !d2.a(this, (CoordinatorLayout) view, q))) {
                c(view, q);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x0117, code lost:
    
        if (r0.a(r30, (androidx.coordinatorlayout.widget.CoordinatorLayout) r20, r11, r21, r23, 0) == false) goto L46;
     */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00f9  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x011a  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void onMeasure(int r31, int r32) {
        /*
            Method dump skipped, instructions count: 388
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.coordinatorlayout.widget.CoordinatorLayout.onMeasure(int, int):void");
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, a.f.l.p
    public boolean onNestedFling(View view, float f2, float f3, boolean z2) {
        c d2;
        int childCount = getChildCount();
        boolean z3 = false;
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if (childAt.getVisibility() != 8) {
                f fVar = (f) childAt.getLayoutParams();
                if (fVar.a(0) && (d2 = fVar.d()) != null) {
                    z3 |= d2.a(this, (CoordinatorLayout) childAt, view, f2, f3, z2);
                }
            }
        }
        if (z3) {
            a(1);
        }
        return z3;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, a.f.l.p
    public boolean onNestedPreFling(View view, float f2, float f3) {
        c d2;
        int childCount = getChildCount();
        boolean z2 = false;
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if (childAt.getVisibility() != 8) {
                f fVar = (f) childAt.getLayoutParams();
                if (fVar.a(0) && (d2 = fVar.d()) != null) {
                    z2 |= d2.a(this, (CoordinatorLayout) childAt, view, f2, f3);
                }
            }
        }
        return z2;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, a.f.l.p
    public void onNestedPreScroll(View view, int i2, int i3, int[] iArr) {
        a(view, i2, i3, iArr, 0);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, a.f.l.p
    public void onNestedScroll(View view, int i2, int i3, int i4, int i5) {
        a(view, i2, i3, i4, i5, 0);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, a.f.l.p
    public void onNestedScrollAccepted(View view, View view2, int i2) {
        a(view, view2, i2, 0);
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        Parcelable parcelable2;
        if (!(parcelable instanceof h)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        h hVar = (h) parcelable;
        super.onRestoreInstanceState(hVar.d());
        SparseArray<Parcelable> sparseArray = hVar.f1176d;
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            int id = childAt.getId();
            c d2 = d(childAt).d();
            if (id != -1 && d2 != null && (parcelable2 = sparseArray.get(id)) != null) {
                d2.a(this, (CoordinatorLayout) childAt, parcelable2);
            }
        }
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        Parcelable d2;
        h hVar = new h(super.onSaveInstanceState());
        SparseArray<Parcelable> sparseArray = new SparseArray<>();
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            int id = childAt.getId();
            c d3 = ((f) childAt.getLayoutParams()).d();
            if (id != -1 && d3 != null && (d2 = d3.d(this, childAt)) != null) {
                sparseArray.append(id, d2);
            }
        }
        hVar.f1176d = sparseArray;
        return hVar;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, a.f.l.p
    public boolean onStartNestedScroll(View view, View view2, int i2) {
        return b(view, view2, i2, 0);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, a.f.l.p
    public void onStopNestedScroll(View view) {
        a(view, 0);
    }

    /* JADX WARN: Code restructure failed: missing block: B:4:0x0012, code lost:
    
        if (r3 != false) goto L8;
     */
    /* JADX WARN: Removed duplicated region for block: B:10:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0031  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onTouchEvent(android.view.MotionEvent r18) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            int r2 = r18.getActionMasked()
            android.view.View r3 = r0.l
            r4 = 1
            r5 = 0
            if (r3 != 0) goto L15
            boolean r3 = r0.a(r1, r4)
            if (r3 == 0) goto L2b
            goto L16
        L15:
            r3 = 0
        L16:
            android.view.View r6 = r0.l
            android.view.ViewGroup$LayoutParams r6 = r6.getLayoutParams()
            androidx.coordinatorlayout.widget.CoordinatorLayout$f r6 = (androidx.coordinatorlayout.widget.CoordinatorLayout.f) r6
            androidx.coordinatorlayout.widget.CoordinatorLayout$c r6 = r6.d()
            if (r6 == 0) goto L2b
            android.view.View r7 = r0.l
            boolean r6 = r6.b(r0, r7, r1)
            goto L2c
        L2b:
            r6 = 0
        L2c:
            android.view.View r7 = r0.l
            r8 = 0
            if (r7 != 0) goto L37
            boolean r1 = super.onTouchEvent(r18)
            r6 = r6 | r1
            goto L4a
        L37:
            if (r3 == 0) goto L4a
            long r11 = android.os.SystemClock.uptimeMillis()
            r13 = 3
            r14 = 0
            r15 = 0
            r16 = 0
            r9 = r11
            android.view.MotionEvent r8 = android.view.MotionEvent.obtain(r9, r11, r13, r14, r15, r16)
            super.onTouchEvent(r8)
        L4a:
            if (r8 == 0) goto L4f
            r8.recycle()
        L4f:
            if (r2 == r4) goto L54
            r1 = 3
            if (r2 != r1) goto L57
        L54:
            r0.a(r5)
        L57:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.coordinatorlayout.widget.CoordinatorLayout.onTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z2) {
        c d2 = ((f) view.getLayoutParams()).d();
        if (d2 == null || !d2.a(this, (CoordinatorLayout) view, rect, z2)) {
            return super.requestChildRectangleOnScreen(view, rect, z2);
        }
        return true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean z2) {
        super.requestDisallowInterceptTouchEvent(z2);
        if (!z2 || this.i) {
            return;
        }
        a(false);
        this.i = true;
    }

    @Override // android.view.View
    public void setFitsSystemWindows(boolean z2) {
        super.setFitsSystemWindows(z2);
        f();
    }

    @Override // android.view.ViewGroup
    public void setOnHierarchyChangeListener(ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener) {
        this.s = onHierarchyChangeListener;
    }

    public void setStatusBarBackground(Drawable drawable) {
        Drawable drawable2 = this.r;
        if (drawable2 != drawable) {
            if (drawable2 != null) {
                drawable2.setCallback(null);
            }
            this.r = drawable != null ? drawable.mutate() : null;
            Drawable drawable3 = this.r;
            if (drawable3 != null) {
                if (drawable3.isStateful()) {
                    this.r.setState(getDrawableState());
                }
                androidx.core.graphics.drawable.a.a(this.r, w.q(this));
                this.r.setVisible(getVisibility() == 0, false);
                this.r.setCallback(this);
            }
            w.K(this);
        }
    }

    public void setStatusBarBackgroundColor(int i2) {
        setStatusBarBackground(new ColorDrawable(i2));
    }

    public void setStatusBarBackgroundResource(int i2) {
        setStatusBarBackground(i2 != 0 ? a.f.d.b.c(getContext(), i2) : null);
    }

    @Override // android.view.View
    public void setVisibility(int i2) {
        super.setVisibility(i2);
        boolean z2 = i2 == 0;
        Drawable drawable = this.r;
        if (drawable == null || drawable.isVisible() == z2) {
            return;
        }
        this.r.setVisible(z2, false);
    }

    @Override // android.view.View
    protected boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.r;
    }

    public CoordinatorLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, a.e.a.coordinatorLayoutStyle);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    public f generateDefaultLayoutParams() {
        return new f(-2, -2);
    }

    /* loaded from: classes.dex */
    public static class f extends ViewGroup.MarginLayoutParams {

        /* renamed from: a, reason: collision with root package name */
        c f1169a;

        /* renamed from: b, reason: collision with root package name */
        boolean f1170b;

        /* renamed from: c, reason: collision with root package name */
        public int f1171c;

        /* renamed from: d, reason: collision with root package name */
        public int f1172d;

        /* renamed from: e, reason: collision with root package name */
        public int f1173e;

        /* renamed from: f, reason: collision with root package name */
        int f1174f;
        public int g;
        public int h;
        int i;
        int j;
        View k;
        View l;
        private boolean m;
        private boolean n;
        private boolean o;
        private boolean p;
        final Rect q;

        public f(int i, int i2) {
            super(i, i2);
            this.f1170b = false;
            this.f1171c = 0;
            this.f1172d = 0;
            this.f1173e = -1;
            this.f1174f = -1;
            this.g = 0;
            this.h = 0;
            this.q = new Rect();
        }

        public void a(c cVar) {
            c cVar2 = this.f1169a;
            if (cVar2 != cVar) {
                if (cVar2 != null) {
                    cVar2.a();
                }
                this.f1169a = cVar;
                this.f1170b = true;
                if (cVar != null) {
                    cVar.a(this);
                }
            }
        }

        boolean b() {
            if (this.f1169a == null) {
                this.m = false;
            }
            return this.m;
        }

        public int c() {
            return this.f1174f;
        }

        public c d() {
            return this.f1169a;
        }

        boolean e() {
            return this.p;
        }

        Rect f() {
            return this.q;
        }

        void g() {
            this.p = false;
        }

        void h() {
            this.m = false;
        }

        boolean b(CoordinatorLayout coordinatorLayout, View view) {
            boolean z = this.m;
            if (z) {
                return true;
            }
            c cVar = this.f1169a;
            boolean a2 = (cVar != null ? cVar.a(coordinatorLayout, view) : false) | z;
            this.m = a2;
            return a2;
        }

        void a(Rect rect) {
            this.q.set(rect);
        }

        boolean a() {
            return this.k == null && this.f1174f != -1;
        }

        void b(int i) {
            a(i, false);
        }

        private boolean b(View view, CoordinatorLayout coordinatorLayout) {
            if (this.k.getId() != this.f1174f) {
                return false;
            }
            View view2 = this.k;
            for (ViewParent parent = view2.getParent(); parent != coordinatorLayout; parent = parent.getParent()) {
                if (parent != null && parent != view) {
                    if (parent instanceof View) {
                        view2 = parent;
                    }
                } else {
                    this.l = null;
                    this.k = null;
                    return false;
                }
            }
            this.l = view2;
            return true;
        }

        void a(int i, boolean z) {
            if (i == 0) {
                this.n = z;
            } else {
                if (i != 1) {
                    return;
                }
                this.o = z;
            }
        }

        f(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f1170b = false;
            this.f1171c = 0;
            this.f1172d = 0;
            this.f1173e = -1;
            this.f1174f = -1;
            this.g = 0;
            this.h = 0;
            this.q = new Rect();
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, a.e.c.CoordinatorLayout_Layout);
            this.f1171c = obtainStyledAttributes.getInteger(a.e.c.CoordinatorLayout_Layout_android_layout_gravity, 0);
            this.f1174f = obtainStyledAttributes.getResourceId(a.e.c.CoordinatorLayout_Layout_layout_anchor, -1);
            this.f1172d = obtainStyledAttributes.getInteger(a.e.c.CoordinatorLayout_Layout_layout_anchorGravity, 0);
            this.f1173e = obtainStyledAttributes.getInteger(a.e.c.CoordinatorLayout_Layout_layout_keyline, -1);
            this.g = obtainStyledAttributes.getInt(a.e.c.CoordinatorLayout_Layout_layout_insetEdge, 0);
            this.h = obtainStyledAttributes.getInt(a.e.c.CoordinatorLayout_Layout_layout_dodgeInsetEdges, 0);
            this.f1170b = obtainStyledAttributes.hasValue(a.e.c.CoordinatorLayout_Layout_layout_behavior);
            if (this.f1170b) {
                this.f1169a = CoordinatorLayout.a(context, attributeSet, obtainStyledAttributes.getString(a.e.c.CoordinatorLayout_Layout_layout_behavior));
            }
            obtainStyledAttributes.recycle();
            c cVar = this.f1169a;
            if (cVar != null) {
                cVar.a(this);
            }
        }

        boolean a(int i) {
            if (i == 0) {
                return this.n;
            }
            if (i != 1) {
                return false;
            }
            return this.o;
        }

        void a(boolean z) {
            this.p = z;
        }

        boolean a(CoordinatorLayout coordinatorLayout, View view, View view2) {
            c cVar;
            return view2 == this.l || a(view2, w.q(coordinatorLayout)) || ((cVar = this.f1169a) != null && cVar.a(coordinatorLayout, (CoordinatorLayout) view, view2));
        }

        View a(CoordinatorLayout coordinatorLayout, View view) {
            if (this.f1174f == -1) {
                this.l = null;
                this.k = null;
                return null;
            }
            if (this.k == null || !b(view, coordinatorLayout)) {
                a(view, coordinatorLayout);
            }
            return this.k;
        }

        private void a(View view, CoordinatorLayout coordinatorLayout) {
            this.k = coordinatorLayout.findViewById(this.f1174f);
            View view2 = this.k;
            if (view2 == null) {
                if (coordinatorLayout.isInEditMode()) {
                    this.l = null;
                    this.k = null;
                    return;
                }
                throw new IllegalStateException("Could not find CoordinatorLayout descendant view with id " + coordinatorLayout.getResources().getResourceName(this.f1174f) + " to anchor view " + view);
            }
            if (view2 == coordinatorLayout) {
                if (coordinatorLayout.isInEditMode()) {
                    this.l = null;
                    this.k = null;
                    return;
                }
                throw new IllegalStateException("View can not be anchored to the the parent CoordinatorLayout");
            }
            for (ViewParent parent = view2.getParent(); parent != coordinatorLayout && parent != null; parent = parent.getParent()) {
                if (parent == view) {
                    if (coordinatorLayout.isInEditMode()) {
                        this.l = null;
                        this.k = null;
                        return;
                    }
                    throw new IllegalStateException("Anchor must not be a descendant of the anchored view");
                }
                if (parent instanceof View) {
                    view2 = parent;
                }
            }
            this.l = view2;
        }

        public f(f fVar) {
            super((ViewGroup.MarginLayoutParams) fVar);
            this.f1170b = false;
            this.f1171c = 0;
            this.f1172d = 0;
            this.f1173e = -1;
            this.f1174f = -1;
            this.g = 0;
            this.h = 0;
            this.q = new Rect();
        }

        private boolean a(View view, int i) {
            int a2 = a.f.l.d.a(((f) view.getLayoutParams()).g, i);
            return a2 != 0 && (a.f.l.d.a(this.h, i) & a2) == a2;
        }

        public f(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.f1170b = false;
            this.f1171c = 0;
            this.f1172d = 0;
            this.f1173e = -1;
            this.f1174f = -1;
            this.g = 0;
            this.h = 0;
            this.q = new Rect();
        }

        public f(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f1170b = false;
            this.f1171c = 0;
            this.f1172d = 0;
            this.f1173e = -1;
            this.f1174f = -1;
            this.g = 0;
            this.h = 0;
            this.q = new Rect();
        }
    }

    public CoordinatorLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        TypedArray obtainStyledAttributes;
        this.f1162b = new ArrayList();
        this.f1163c = new androidx.coordinatorlayout.widget.a<>();
        this.f1164d = new ArrayList();
        this.f1165e = new ArrayList();
        this.g = new int[2];
        this.h = new int[2];
        this.u = new q(this);
        if (i2 == 0) {
            obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, a.e.c.CoordinatorLayout, 0, a.e.b.Widget_Support_CoordinatorLayout);
        } else {
            obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, a.e.c.CoordinatorLayout, i2, 0);
        }
        if (Build.VERSION.SDK_INT >= 29) {
            if (i2 == 0) {
                saveAttributeDataForStyleable(context, a.e.c.CoordinatorLayout, attributeSet, obtainStyledAttributes, 0, a.e.b.Widget_Support_CoordinatorLayout);
            } else {
                saveAttributeDataForStyleable(context, a.e.c.CoordinatorLayout, attributeSet, obtainStyledAttributes, i2, 0);
            }
        }
        int resourceId = obtainStyledAttributes.getResourceId(a.e.c.CoordinatorLayout_keylines, 0);
        if (resourceId != 0) {
            Resources resources = context.getResources();
            this.k = resources.getIntArray(resourceId);
            float f2 = resources.getDisplayMetrics().density;
            int length = this.k.length;
            for (int i3 = 0; i3 < length; i3++) {
                this.k[i3] = (int) (r12[i3] * f2);
            }
        }
        this.r = obtainStyledAttributes.getDrawable(a.e.c.CoordinatorLayout_statusBarBackground);
        obtainStyledAttributes.recycle();
        f();
        super.setOnHierarchyChangeListener(new e());
        if (w.n(this) == 0) {
            w.i(this, 1);
        }
    }

    final e0 a(e0 e0Var) {
        if (a.f.k.c.a(this.p, e0Var)) {
            return e0Var;
        }
        this.p = e0Var;
        this.q = e0Var != null && e0Var.e() > 0;
        setWillNotDraw(!this.q && getBackground() == null);
        e0 b2 = b(e0Var);
        requestLayout();
        return b2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    f d(View view) {
        f fVar = (f) view.getLayoutParams();
        if (!fVar.f1170b) {
            if (view instanceof b) {
                c behavior = ((b) view).getBehavior();
                if (behavior == null) {
                    Log.e("CoordinatorLayout", "Attached behavior class is null");
                }
                fVar.a(behavior);
                fVar.f1170b = true;
            } else {
                d dVar = null;
                for (Class<?> cls = view.getClass(); cls != null; cls = cls.getSuperclass()) {
                    dVar = (d) cls.getAnnotation(d.class);
                    if (dVar != null) {
                        break;
                    }
                }
                if (dVar != null) {
                    try {
                        fVar.a(dVar.value().getDeclaredConstructor(new Class[0]).newInstance(new Object[0]));
                    } catch (Exception e2) {
                        Log.e("CoordinatorLayout", "Default behavior class " + dVar.value().getName() + " could not be instantiated. Did you forget a default constructor?", e2);
                    }
                }
                fVar.f1170b = true;
            }
        }
        return fVar;
    }

    @Override // android.view.ViewGroup
    public f generateLayoutParams(AttributeSet attributeSet) {
        return new f(getContext(), attributeSet);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    public f generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof f) {
            return new f((f) layoutParams);
        }
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            return new f((ViewGroup.MarginLayoutParams) layoutParams);
        }
        return new f(layoutParams);
    }

    private void f() {
        if (Build.VERSION.SDK_INT < 21) {
            return;
        }
        if (w.m(this)) {
            if (this.t == null) {
                this.t = new a();
            }
            w.a(this, this.t);
            setSystemUiVisibility(1280);
            return;
        }
        w.a(this, (r) null);
    }

    private e0 b(e0 e0Var) {
        c d2;
        if (e0Var.g()) {
            return e0Var;
        }
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if (w.m(childAt) && (d2 = ((f) childAt.getLayoutParams()).d()) != null) {
                e0Var = d2.a(this, (CoordinatorLayout) childAt, e0Var);
                if (e0Var.g()) {
                    break;
                }
            }
        }
        return e0Var;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes.dex */
    public static class h extends a.h.a.a {
        public static final Parcelable.Creator<h> CREATOR = new a();

        /* renamed from: d, reason: collision with root package name */
        SparseArray<Parcelable> f1176d;

        /* loaded from: classes.dex */
        static class a implements Parcelable.ClassLoaderCreator<h> {
            a() {
            }

            @Override // android.os.Parcelable.Creator
            public h[] newArray(int i) {
                return new h[i];
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.ClassLoaderCreator
            public h createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new h(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            public h createFromParcel(Parcel parcel) {
                return new h(parcel, null);
            }
        }

        public h(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            int readInt = parcel.readInt();
            int[] iArr = new int[readInt];
            parcel.readIntArray(iArr);
            Parcelable[] readParcelableArray = parcel.readParcelableArray(classLoader);
            this.f1176d = new SparseArray<>(readInt);
            for (int i = 0; i < readInt; i++) {
                this.f1176d.append(iArr[i], readParcelableArray[i]);
            }
        }

        @Override // a.h.a.a, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            SparseArray<Parcelable> sparseArray = this.f1176d;
            int size = sparseArray != null ? sparseArray.size() : 0;
            parcel.writeInt(size);
            int[] iArr = new int[size];
            Parcelable[] parcelableArr = new Parcelable[size];
            for (int i2 = 0; i2 < size; i2++) {
                iArr[i2] = this.f1176d.keyAt(i2);
                parcelableArr[i2] = this.f1176d.valueAt(i2);
            }
            parcel.writeIntArray(iArr);
            parcel.writeParcelableArray(parcelableArr, i);
        }

        public h(Parcelable parcelable) {
            super(parcelable);
        }
    }

    private void a(boolean z2) {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            c d2 = ((f) childAt.getLayoutParams()).d();
            if (d2 != null) {
                long uptimeMillis = SystemClock.uptimeMillis();
                MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
                if (z2) {
                    d2.a(this, (CoordinatorLayout) childAt, obtain);
                } else {
                    d2.b(this, (CoordinatorLayout) childAt, obtain);
                }
                obtain.recycle();
            }
        }
        for (int i3 = 0; i3 < childCount; i3++) {
            ((f) getChildAt(i3).getLayoutParams()).h();
        }
        this.l = null;
        this.i = false;
    }

    void c(View view, Rect rect) {
        ((f) view.getLayoutParams()).a(rect);
    }

    public List<View> c(View view) {
        List c2 = this.f1163c.c(view);
        this.f1165e.clear();
        if (c2 != null) {
            this.f1165e.addAll(c2);
        }
        return this.f1165e;
    }

    void b(View view, Rect rect) {
        rect.set(((f) view.getLayoutParams()).f());
    }

    private void e(View view, int i2) {
        f fVar = (f) view.getLayoutParams();
        int i3 = fVar.i;
        if (i3 != i2) {
            w.e(view, i2 - i3);
            fVar.i = i2;
        }
    }

    void c() {
        if (this.j && this.n != null) {
            getViewTreeObserver().removeOnPreDrawListener(this.n);
        }
        this.o = false;
    }

    private void b(View view, int i2, int i3) {
        f fVar = (f) view.getLayoutParams();
        int a2 = a.f.l.d.a(e(fVar.f1171c), i3);
        int i4 = a2 & 7;
        int i5 = a2 & 112;
        int width = getWidth();
        int height = getHeight();
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        if (i3 == 1) {
            i2 = width - i2;
        }
        int b2 = b(i2) - measuredWidth;
        int i6 = 0;
        if (i4 == 1) {
            b2 += measuredWidth / 2;
        } else if (i4 == 5) {
            b2 += measuredWidth;
        }
        if (i5 == 16) {
            i6 = 0 + (measuredHeight / 2);
        } else if (i5 == 80) {
            i6 = measuredHeight + 0;
        }
        int max = Math.max(getPaddingLeft() + ((ViewGroup.MarginLayoutParams) fVar).leftMargin, Math.min(b2, ((width - getPaddingRight()) - measuredWidth) - ((ViewGroup.MarginLayoutParams) fVar).rightMargin));
        int max2 = Math.max(getPaddingTop() + ((ViewGroup.MarginLayoutParams) fVar).topMargin, Math.min(i6, ((height - getPaddingBottom()) - measuredHeight) - ((ViewGroup.MarginLayoutParams) fVar).bottomMargin));
        view.layout(max, max2, measuredWidth + max, measuredHeight + max2);
    }

    private void d(View view, int i2) {
        f fVar = (f) view.getLayoutParams();
        Rect d2 = d();
        d2.set(getPaddingLeft() + ((ViewGroup.MarginLayoutParams) fVar).leftMargin, getPaddingTop() + ((ViewGroup.MarginLayoutParams) fVar).topMargin, (getWidth() - getPaddingRight()) - ((ViewGroup.MarginLayoutParams) fVar).rightMargin, (getHeight() - getPaddingBottom()) - ((ViewGroup.MarginLayoutParams) fVar).bottomMargin);
        if (this.p != null && w.m(this) && !w.m(view)) {
            d2.left += this.p.c();
            d2.top += this.p.e();
            d2.right -= this.p.d();
            d2.bottom -= this.p.b();
        }
        Rect d3 = d();
        a.f.l.d.a(d(fVar.f1171c), view.getMeasuredWidth(), view.getMeasuredHeight(), d2, d3, i2);
        view.layout(d3.left, d3.top, d3.right, d3.bottom);
        a(d2);
        a(d3);
    }

    private boolean e(View view) {
        return this.f1163c.e(view);
    }

    private void a(List<View> list) {
        list.clear();
        boolean isChildrenDrawingOrderEnabled = isChildrenDrawingOrderEnabled();
        int childCount = getChildCount();
        for (int i2 = childCount - 1; i2 >= 0; i2--) {
            list.add(getChildAt(isChildrenDrawingOrderEnabled ? getChildDrawingOrder(childCount, i2) : i2));
        }
        Comparator<View> comparator = y;
        if (comparator != null) {
            Collections.sort(list, comparator);
        }
    }

    private boolean a(MotionEvent motionEvent, int i2) {
        int actionMasked = motionEvent.getActionMasked();
        List<View> list = this.f1164d;
        a(list);
        int size = list.size();
        MotionEvent motionEvent2 = null;
        boolean z2 = false;
        boolean z3 = false;
        for (int i3 = 0; i3 < size; i3++) {
            View view = list.get(i3);
            f fVar = (f) view.getLayoutParams();
            c d2 = fVar.d();
            if (!(z2 || z3) || actionMasked == 0) {
                if (!z2 && d2 != null) {
                    if (i2 == 0) {
                        z2 = d2.a(this, (CoordinatorLayout) view, motionEvent);
                    } else if (i2 == 1) {
                        z2 = d2.b(this, (CoordinatorLayout) view, motionEvent);
                    }
                    if (z2) {
                        this.l = view;
                    }
                }
                boolean b2 = fVar.b();
                boolean b3 = fVar.b(this, view);
                boolean z4 = b3 && !b2;
                if (b3 && !z4) {
                    break;
                }
                z3 = z4;
            } else if (d2 != null) {
                if (motionEvent2 == null) {
                    long uptimeMillis = SystemClock.uptimeMillis();
                    motionEvent2 = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
                }
                if (i2 == 0) {
                    d2.a(this, (CoordinatorLayout) view, motionEvent2);
                } else if (i2 == 1) {
                    d2.b(this, (CoordinatorLayout) view, motionEvent2);
                }
            }
        }
        list.clear();
        return z2;
    }

    public List<View> b(View view) {
        List<View> d2 = this.f1163c.d(view);
        this.f1165e.clear();
        if (d2 != null) {
            this.f1165e.addAll(d2);
        }
        return this.f1165e;
    }

    void b() {
        int childCount = getChildCount();
        boolean z2 = false;
        int i2 = 0;
        while (true) {
            if (i2 >= childCount) {
                break;
            }
            if (e(getChildAt(i2))) {
                z2 = true;
                break;
            }
            i2++;
        }
        if (z2 != this.o) {
            if (z2) {
                a();
            } else {
                c();
            }
        }
    }

    void b(View view, int i2) {
        c d2;
        f fVar = (f) view.getLayoutParams();
        if (fVar.k != null) {
            Rect d3 = d();
            Rect d4 = d();
            Rect d5 = d();
            a(fVar.k, d3);
            a(view, false, d4);
            int measuredWidth = view.getMeasuredWidth();
            int measuredHeight = view.getMeasuredHeight();
            a(view, i2, d3, d5, fVar, measuredWidth, measuredHeight);
            boolean z2 = (d5.left == d4.left && d5.top == d4.top) ? false : true;
            a(fVar, d5, measuredWidth, measuredHeight);
            int i3 = d5.left - d4.left;
            int i4 = d5.top - d4.top;
            if (i3 != 0) {
                w.e(view, i3);
            }
            if (i4 != 0) {
                w.f(view, i4);
            }
            if (z2 && (d2 = fVar.d()) != null) {
                d2.b(this, (CoordinatorLayout) view, fVar.k);
            }
            a(d3);
            a(d4);
            a(d5);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    static c a(Context context, AttributeSet attributeSet, String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (str.startsWith(".")) {
            str = context.getPackageName() + str;
        } else if (str.indexOf(46) < 0 && !TextUtils.isEmpty(v)) {
            str = v + '.' + str;
        }
        try {
            Map<String, Constructor<c>> map = x.get();
            if (map == null) {
                map = new HashMap<>();
                x.set(map);
            }
            Constructor<c> constructor = map.get(str);
            if (constructor == null) {
                constructor = Class.forName(str, false, context.getClassLoader()).getConstructor(w);
                constructor.setAccessible(true);
                map.put(str, constructor);
            }
            return constructor.newInstance(context, attributeSet);
        } catch (Exception e2) {
            throw new RuntimeException("Could not inflate Behavior subclass " + str, e2);
        }
    }

    void a(View view, Rect rect) {
        androidx.coordinatorlayout.widget.b.a(this, view, rect);
    }

    public void a(View view, int i2, int i3, int i4, int i5) {
        measureChildWithMargins(view, i2, i3, i4, i5);
    }

    void a(View view, boolean z2, Rect rect) {
        if (view.isLayoutRequested() || view.getVisibility() == 8) {
            rect.setEmpty();
        } else if (z2) {
            a(view, rect);
        } else {
            rect.set(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        }
    }

    @Override // a.f.l.n
    public boolean b(View view, View view2, int i2, int i3) {
        int childCount = getChildCount();
        boolean z2 = false;
        for (int i4 = 0; i4 < childCount; i4++) {
            View childAt = getChildAt(i4);
            if (childAt.getVisibility() != 8) {
                f fVar = (f) childAt.getLayoutParams();
                c d2 = fVar.d();
                if (d2 != null) {
                    boolean b2 = d2.b(this, childAt, view, view2, i2, i3);
                    fVar.a(i3, b2);
                    z2 |= b2;
                } else {
                    fVar.a(i3, false);
                }
            }
        }
        return z2;
    }

    private void a(View view, int i2, Rect rect, Rect rect2, f fVar, int i3, int i4) {
        int width;
        int height;
        int a2 = a.f.l.d.a(c(fVar.f1171c), i2);
        int a3 = a.f.l.d.a(d(fVar.f1172d), i2);
        int i5 = a2 & 7;
        int i6 = a2 & 112;
        int i7 = a3 & 7;
        int i8 = a3 & 112;
        if (i7 == 1) {
            width = rect.left + (rect.width() / 2);
        } else if (i7 != 5) {
            width = rect.left;
        } else {
            width = rect.right;
        }
        if (i8 == 16) {
            height = rect.top + (rect.height() / 2);
        } else if (i8 != 80) {
            height = rect.top;
        } else {
            height = rect.bottom;
        }
        if (i5 == 1) {
            width -= i3 / 2;
        } else if (i5 != 5) {
            width -= i3;
        }
        if (i6 == 16) {
            height -= i4 / 2;
        } else if (i6 != 80) {
            height -= i4;
        }
        rect2.set(width, height, i3 + width, i4 + height);
    }

    private void a(f fVar, Rect rect, int i2, int i3) {
        int width = getWidth();
        int height = getHeight();
        int max = Math.max(getPaddingLeft() + ((ViewGroup.MarginLayoutParams) fVar).leftMargin, Math.min(rect.left, ((width - getPaddingRight()) - i2) - ((ViewGroup.MarginLayoutParams) fVar).rightMargin));
        int max2 = Math.max(getPaddingTop() + ((ViewGroup.MarginLayoutParams) fVar).topMargin, Math.min(rect.top, ((height - getPaddingBottom()) - i3) - ((ViewGroup.MarginLayoutParams) fVar).bottomMargin));
        rect.set(max, max2, i2 + max, i3 + max2);
    }

    void a(View view, int i2, Rect rect, Rect rect2) {
        f fVar = (f) view.getLayoutParams();
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        a(view, i2, rect, rect2, fVar, measuredWidth, measuredHeight);
        a(fVar, rect2, measuredWidth, measuredHeight);
    }

    private void a(View view, View view2, int i2) {
        Rect d2 = d();
        Rect d3 = d();
        try {
            a(view2, d2);
            a(view, i2, d2, d3);
            view.layout(d3.left, d3.top, d3.right, d3.bottom);
        } finally {
            a(d2);
            a(d3);
        }
    }

    final void a(int i2) {
        boolean z2;
        int q = w.q(this);
        int size = this.f1162b.size();
        Rect d2 = d();
        Rect d3 = d();
        Rect d4 = d();
        for (int i3 = 0; i3 < size; i3++) {
            View view = this.f1162b.get(i3);
            f fVar = (f) view.getLayoutParams();
            if (i2 != 0 || view.getVisibility() != 8) {
                for (int i4 = 0; i4 < i3; i4++) {
                    if (fVar.l == this.f1162b.get(i4)) {
                        b(view, q);
                    }
                }
                a(view, true, d3);
                if (fVar.g != 0 && !d3.isEmpty()) {
                    int a2 = a.f.l.d.a(fVar.g, q);
                    int i5 = a2 & 112;
                    if (i5 == 48) {
                        d2.top = Math.max(d2.top, d3.bottom);
                    } else if (i5 == 80) {
                        d2.bottom = Math.max(d2.bottom, getHeight() - d3.top);
                    }
                    int i6 = a2 & 7;
                    if (i6 == 3) {
                        d2.left = Math.max(d2.left, d3.right);
                    } else if (i6 == 5) {
                        d2.right = Math.max(d2.right, getWidth() - d3.left);
                    }
                }
                if (fVar.h != 0 && view.getVisibility() == 0) {
                    a(view, d2, q);
                }
                if (i2 != 2) {
                    b(view, d4);
                    if (!d4.equals(d3)) {
                        c(view, d3);
                    }
                }
                for (int i7 = i3 + 1; i7 < size; i7++) {
                    View view2 = this.f1162b.get(i7);
                    f fVar2 = (f) view2.getLayoutParams();
                    c d5 = fVar2.d();
                    if (d5 != null && d5.a(this, (CoordinatorLayout) view2, view)) {
                        if (i2 == 0 && fVar2.e()) {
                            fVar2.g();
                        } else {
                            if (i2 != 2) {
                                z2 = d5.b(this, (CoordinatorLayout) view2, view);
                            } else {
                                d5.c(this, view2, view);
                                z2 = true;
                            }
                            if (i2 == 1) {
                                fVar2.a(z2);
                            }
                        }
                    }
                }
            }
        }
        a(d2);
        a(d3);
        a(d4);
    }

    private void a(View view, Rect rect, int i2) {
        boolean z2;
        boolean z3;
        int width;
        int i3;
        int i4;
        int i5;
        int height;
        int i6;
        int i7;
        int i8;
        if (w.F(view) && view.getWidth() > 0 && view.getHeight() > 0) {
            f fVar = (f) view.getLayoutParams();
            c d2 = fVar.d();
            Rect d3 = d();
            Rect d4 = d();
            d4.set(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
            if (d2 != null && d2.a(this, (CoordinatorLayout) view, d3)) {
                if (!d4.contains(d3)) {
                    throw new IllegalArgumentException("Rect should be within the child's bounds. Rect:" + d3.toShortString() + " | Bounds:" + d4.toShortString());
                }
            } else {
                d3.set(d4);
            }
            a(d4);
            if (d3.isEmpty()) {
                a(d3);
                return;
            }
            int a2 = a.f.l.d.a(fVar.h, i2);
            if ((a2 & 48) != 48 || (i7 = (d3.top - ((ViewGroup.MarginLayoutParams) fVar).topMargin) - fVar.j) >= (i8 = rect.top)) {
                z2 = false;
            } else {
                f(view, i8 - i7);
                z2 = true;
            }
            if ((a2 & 80) == 80 && (height = ((getHeight() - d3.bottom) - ((ViewGroup.MarginLayoutParams) fVar).bottomMargin) + fVar.j) < (i6 = rect.bottom)) {
                f(view, height - i6);
                z2 = true;
            }
            if (!z2) {
                f(view, 0);
            }
            if ((a2 & 3) != 3 || (i4 = (d3.left - ((ViewGroup.MarginLayoutParams) fVar).leftMargin) - fVar.i) >= (i5 = rect.left)) {
                z3 = false;
            } else {
                e(view, i5 - i4);
                z3 = true;
            }
            if ((a2 & 5) == 5 && (width = ((getWidth() - d3.right) - ((ViewGroup.MarginLayoutParams) fVar).rightMargin) + fVar.i) < (i3 = rect.right)) {
                e(view, width - i3);
                z3 = true;
            }
            if (!z3) {
                e(view, 0);
            }
            a(d3);
        }
    }

    public void a(View view) {
        List c2 = this.f1163c.c(view);
        if (c2 == null || c2.isEmpty()) {
            return;
        }
        for (int i2 = 0; i2 < c2.size(); i2++) {
            View view2 = (View) c2.get(i2);
            c d2 = ((f) view2.getLayoutParams()).d();
            if (d2 != null) {
                d2.b(this, (CoordinatorLayout) view2, view);
            }
        }
    }

    void a() {
        if (this.j) {
            if (this.n == null) {
                this.n = new g();
            }
            getViewTreeObserver().addOnPreDrawListener(this.n);
        }
        this.o = true;
    }

    public boolean a(View view, int i2, int i3) {
        Rect d2 = d();
        a(view, d2);
        try {
            return d2.contains(i2, i3);
        } finally {
            a(d2);
        }
    }

    @Override // a.f.l.n
    public void a(View view, View view2, int i2, int i3) {
        c d2;
        this.u.a(view, view2, i2, i3);
        this.m = view2;
        int childCount = getChildCount();
        for (int i4 = 0; i4 < childCount; i4++) {
            View childAt = getChildAt(i4);
            f fVar = (f) childAt.getLayoutParams();
            if (fVar.a(i3) && (d2 = fVar.d()) != null) {
                d2.a(this, (CoordinatorLayout) childAt, view, view2, i2, i3);
            }
        }
    }

    @Override // a.f.l.n
    public void a(View view, int i2) {
        this.u.a(view, i2);
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            f fVar = (f) childAt.getLayoutParams();
            if (fVar.a(i2)) {
                c d2 = fVar.d();
                if (d2 != null) {
                    d2.a(this, (CoordinatorLayout) childAt, view, i2);
                }
                fVar.b(i2);
                fVar.g();
            }
        }
        this.m = null;
    }

    @Override // a.f.l.n
    public void a(View view, int i2, int i3, int i4, int i5, int i6) {
        a(view, i2, i3, i4, i5, 0, this.h);
    }

    @Override // a.f.l.o
    public void a(View view, int i2, int i3, int i4, int i5, int i6, int[] iArr) {
        c d2;
        int min;
        int childCount = getChildCount();
        boolean z2 = false;
        int i7 = 0;
        int i8 = 0;
        for (int i9 = 0; i9 < childCount; i9++) {
            View childAt = getChildAt(i9);
            if (childAt.getVisibility() != 8) {
                f fVar = (f) childAt.getLayoutParams();
                if (fVar.a(i6) && (d2 = fVar.d()) != null) {
                    int[] iArr2 = this.g;
                    iArr2[0] = 0;
                    iArr2[1] = 0;
                    d2.a(this, childAt, view, i2, i3, i4, i5, i6, iArr2);
                    int[] iArr3 = this.g;
                    int max = i4 > 0 ? Math.max(i7, iArr3[0]) : Math.min(i7, iArr3[0]);
                    if (i5 > 0) {
                        min = Math.max(i8, this.g[1]);
                    } else {
                        min = Math.min(i8, this.g[1]);
                    }
                    i7 = max;
                    i8 = min;
                    z2 = true;
                }
            }
        }
        iArr[0] = iArr[0] + i7;
        iArr[1] = iArr[1] + i8;
        if (z2) {
            a(1);
        }
    }

    @Override // a.f.l.n
    public void a(View view, int i2, int i3, int[] iArr, int i4) {
        c d2;
        int childCount = getChildCount();
        boolean z2 = false;
        int i5 = 0;
        int i6 = 0;
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = getChildAt(i7);
            if (childAt.getVisibility() != 8) {
                f fVar = (f) childAt.getLayoutParams();
                if (fVar.a(i4) && (d2 = fVar.d()) != null) {
                    int[] iArr2 = this.g;
                    iArr2[0] = 0;
                    iArr2[1] = 0;
                    d2.a(this, (CoordinatorLayout) childAt, view, i2, i3, iArr2, i4);
                    int[] iArr3 = this.g;
                    int max = i2 > 0 ? Math.max(i5, iArr3[0]) : Math.min(i5, iArr3[0]);
                    int[] iArr4 = this.g;
                    i5 = max;
                    i6 = i3 > 0 ? Math.max(i6, iArr4[1]) : Math.min(i6, iArr4[1]);
                    z2 = true;
                }
            }
        }
        iArr[0] = i5;
        iArr[1] = i6;
        if (z2) {
            a(1);
        }
    }
}
