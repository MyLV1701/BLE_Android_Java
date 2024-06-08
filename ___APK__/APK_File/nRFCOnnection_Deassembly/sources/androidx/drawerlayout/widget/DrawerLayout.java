package androidx.drawerlayout.widget;

import a.f.l.f0.c;
import a.f.l.w;
import a.h.b.c;
import android.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.view.accessibility.AccessibilityEvent;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class DrawerLayout extends ViewGroup {
    private static final int[] L = {R.attr.colorPrimaryDark};
    static final int[] M = {R.attr.layout_gravity};
    static final boolean N;
    private static final boolean O;
    private CharSequence A;
    private CharSequence B;
    private Object C;
    private boolean D;
    private Drawable E;
    private Drawable F;
    private Drawable G;
    private Drawable H;
    private final ArrayList<View> I;
    private Rect J;
    private Matrix K;

    /* renamed from: b, reason: collision with root package name */
    private final c f1331b;

    /* renamed from: c, reason: collision with root package name */
    private float f1332c;

    /* renamed from: d, reason: collision with root package name */
    private int f1333d;

    /* renamed from: e, reason: collision with root package name */
    private int f1334e;

    /* renamed from: f, reason: collision with root package name */
    private float f1335f;
    private Paint g;
    private final a.h.b.c h;
    private final a.h.b.c i;
    private final h j;
    private final h k;
    private int l;
    private boolean m;
    private boolean n;
    private int o;
    private int p;
    private int q;
    private int r;
    private boolean s;
    private d t;
    private List<d> u;
    private float v;
    private float w;
    private Drawable x;
    private Drawable y;
    private Drawable z;

    /* loaded from: classes.dex */
    class a implements View.OnApplyWindowInsetsListener {
        a(DrawerLayout drawerLayout) {
        }

        @Override // android.view.View.OnApplyWindowInsetsListener
        public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
            ((DrawerLayout) view).a(windowInsets, windowInsets.getSystemWindowInsetTop() > 0);
            return windowInsets.consumeSystemWindowInsets();
        }
    }

    /* loaded from: classes.dex */
    class b extends a.f.l.a {

        /* renamed from: a, reason: collision with root package name */
        private final Rect f1336a = new Rect();

        b() {
        }

        private void a(a.f.l.f0.c cVar, ViewGroup viewGroup) {
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewGroup.getChildAt(i);
                if (DrawerLayout.m(childAt)) {
                    cVar.a(childAt);
                }
            }
        }

        private void copyNodeInfoNoChildren(a.f.l.f0.c cVar, a.f.l.f0.c cVar2) {
            Rect rect = this.f1336a;
            cVar2.a(rect);
            cVar.c(rect);
            cVar2.b(rect);
            cVar.d(rect);
            cVar.p(cVar2.x());
            cVar.e(cVar2.i());
            cVar.a(cVar2.d());
            cVar.b(cVar2.f());
            cVar.g(cVar2.p());
            cVar.d(cVar2.o());
            cVar.h(cVar2.q());
            cVar.i(cVar2.r());
            cVar.a(cVar2.l());
            cVar.n(cVar2.v());
            cVar.k(cVar2.s());
            cVar.a(cVar2.b());
        }

        @Override // a.f.l.a
        public boolean dispatchPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            if (accessibilityEvent.getEventType() == 32) {
                List<CharSequence> text = accessibilityEvent.getText();
                View d2 = DrawerLayout.this.d();
                if (d2 == null) {
                    return true;
                }
                CharSequence d3 = DrawerLayout.this.d(DrawerLayout.this.e(d2));
                if (d3 == null) {
                    return true;
                }
                text.add(d3);
                return true;
            }
            return super.dispatchPopulateAccessibilityEvent(view, accessibilityEvent);
        }

        @Override // a.f.l.a
        public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            super.onInitializeAccessibilityEvent(view, accessibilityEvent);
            accessibilityEvent.setClassName(DrawerLayout.class.getName());
        }

        @Override // a.f.l.a
        public void onInitializeAccessibilityNodeInfo(View view, a.f.l.f0.c cVar) {
            if (DrawerLayout.N) {
                super.onInitializeAccessibilityNodeInfo(view, cVar);
            } else {
                a.f.l.f0.c a2 = a.f.l.f0.c.a(cVar);
                super.onInitializeAccessibilityNodeInfo(view, a2);
                cVar.c(view);
                Object w = w.w(view);
                if (w instanceof View) {
                    cVar.b((View) w);
                }
                copyNodeInfoNoChildren(cVar, a2);
                a2.y();
                a(cVar, (ViewGroup) view);
            }
            cVar.a((CharSequence) DrawerLayout.class.getName());
            cVar.h(false);
            cVar.i(false);
            cVar.b(c.a.f310e);
            cVar.b(c.a.f311f);
        }

        @Override // a.f.l.a
        public boolean onRequestSendAccessibilityEvent(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
            if (DrawerLayout.N || DrawerLayout.m(view)) {
                return super.onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
            }
            return false;
        }
    }

    /* loaded from: classes.dex */
    static final class c extends a.f.l.a {
        c() {
        }

        @Override // a.f.l.a
        public void onInitializeAccessibilityNodeInfo(View view, a.f.l.f0.c cVar) {
            super.onInitializeAccessibilityNodeInfo(view, cVar);
            if (DrawerLayout.m(view)) {
                return;
            }
            cVar.b((View) null);
        }
    }

    /* loaded from: classes.dex */
    public interface d {
        void onDrawerClosed(View view);

        void onDrawerOpened(View view);

        void onDrawerSlide(View view, float f2);

        void onDrawerStateChanged(int i);
    }

    /* loaded from: classes.dex */
    public static abstract class g implements d {
        @Override // androidx.drawerlayout.widget.DrawerLayout.d
        public abstract void onDrawerClosed(View view);

        @Override // androidx.drawerlayout.widget.DrawerLayout.d
        public void onDrawerOpened(View view) {
        }

        @Override // androidx.drawerlayout.widget.DrawerLayout.d
        public void onDrawerSlide(View view, float f2) {
        }

        @Override // androidx.drawerlayout.widget.DrawerLayout.d
        public void onDrawerStateChanged(int i) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class h extends c.AbstractC0024c {

        /* renamed from: a, reason: collision with root package name */
        private final int f1345a;

        /* renamed from: b, reason: collision with root package name */
        private a.h.b.c f1346b;

        /* renamed from: c, reason: collision with root package name */
        private final Runnable f1347c = new a();

        /* loaded from: classes.dex */
        class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                h.this.a();
            }
        }

        h(int i) {
            this.f1345a = i;
        }

        private void c() {
            View b2 = DrawerLayout.this.b(this.f1345a == 3 ? 5 : 3);
            if (b2 != null) {
                DrawerLayout.this.a(b2);
            }
        }

        public void a(a.h.b.c cVar) {
            this.f1346b = cVar;
        }

        public void b() {
            DrawerLayout.this.removeCallbacks(this.f1347c);
        }

        @Override // a.h.b.c.AbstractC0024c
        public int clampViewPositionHorizontal(View view, int i, int i2) {
            if (DrawerLayout.this.a(view, 3)) {
                return Math.max(-view.getWidth(), Math.min(i, 0));
            }
            int width = DrawerLayout.this.getWidth();
            return Math.max(width - view.getWidth(), Math.min(i, width));
        }

        @Override // a.h.b.c.AbstractC0024c
        public int clampViewPositionVertical(View view, int i, int i2) {
            return view.getTop();
        }

        @Override // a.h.b.c.AbstractC0024c
        public int getViewHorizontalDragRange(View view) {
            if (DrawerLayout.this.i(view)) {
                return view.getWidth();
            }
            return 0;
        }

        @Override // a.h.b.c.AbstractC0024c
        public void onEdgeDragStarted(int i, int i2) {
            View b2;
            if ((i & 1) == 1) {
                b2 = DrawerLayout.this.b(3);
            } else {
                b2 = DrawerLayout.this.b(5);
            }
            if (b2 == null || DrawerLayout.this.d(b2) != 0) {
                return;
            }
            this.f1346b.a(b2, i2);
        }

        @Override // a.h.b.c.AbstractC0024c
        public boolean onEdgeLock(int i) {
            return false;
        }

        @Override // a.h.b.c.AbstractC0024c
        public void onEdgeTouched(int i, int i2) {
            DrawerLayout.this.postDelayed(this.f1347c, 160L);
        }

        @Override // a.h.b.c.AbstractC0024c
        public void onViewCaptured(View view, int i) {
            ((e) view.getLayoutParams()).f1340c = false;
            c();
        }

        @Override // a.h.b.c.AbstractC0024c
        public void onViewDragStateChanged(int i) {
            DrawerLayout.this.a(this.f1345a, i, this.f1346b.c());
        }

        @Override // a.h.b.c.AbstractC0024c
        public void onViewPositionChanged(View view, int i, int i2, int i3, int i4) {
            float width = (DrawerLayout.this.a(view, 3) ? i + r3 : DrawerLayout.this.getWidth() - i) / view.getWidth();
            DrawerLayout.this.c(view, width);
            view.setVisibility(width == 0.0f ? 4 : 0);
            DrawerLayout.this.invalidate();
        }

        @Override // a.h.b.c.AbstractC0024c
        public void onViewReleased(View view, float f2, float f3) {
            int i;
            float f4 = DrawerLayout.this.f(view);
            int width = view.getWidth();
            if (DrawerLayout.this.a(view, 3)) {
                i = (f2 > 0.0f || (f2 == 0.0f && f4 > 0.5f)) ? 0 : -width;
            } else {
                int width2 = DrawerLayout.this.getWidth();
                if (f2 < 0.0f || (f2 == 0.0f && f4 > 0.5f)) {
                    width2 -= width;
                }
                i = width2;
            }
            this.f1346b.d(i, view.getTop());
            DrawerLayout.this.invalidate();
        }

        @Override // a.h.b.c.AbstractC0024c
        public boolean tryCaptureView(View view, int i) {
            return DrawerLayout.this.i(view) && DrawerLayout.this.a(view, this.f1345a) && DrawerLayout.this.d(view) == 0;
        }

        void a() {
            View b2;
            int width;
            int d2 = this.f1346b.d();
            boolean z = this.f1345a == 3;
            if (z) {
                b2 = DrawerLayout.this.b(3);
                width = (b2 != null ? -b2.getWidth() : 0) + d2;
            } else {
                b2 = DrawerLayout.this.b(5);
                width = DrawerLayout.this.getWidth() - d2;
            }
            if (b2 != null) {
                if (((!z || b2.getLeft() >= width) && (z || b2.getLeft() <= width)) || DrawerLayout.this.d(b2) != 0) {
                    return;
                }
                e eVar = (e) b2.getLayoutParams();
                this.f1346b.b(b2, width, b2.getTop());
                eVar.f1340c = true;
                DrawerLayout.this.invalidate();
                c();
                DrawerLayout.this.a();
            }
        }
    }

    static {
        N = Build.VERSION.SDK_INT >= 19;
        O = Build.VERSION.SDK_INT >= 21;
    }

    public DrawerLayout(Context context) {
        this(context, null);
    }

    private Drawable g() {
        int q = w.q(this);
        if (q == 0) {
            Drawable drawable = this.E;
            if (drawable != null) {
                b(drawable, q);
                return this.E;
            }
        } else {
            Drawable drawable2 = this.F;
            if (drawable2 != null) {
                b(drawable2, q);
                return this.F;
            }
        }
        return this.G;
    }

    static String h(int i) {
        return (i & 3) == 3 ? "LEFT" : (i & 5) == 5 ? "RIGHT" : Integer.toHexString(i);
    }

    private void i() {
        if (O) {
            return;
        }
        this.y = g();
        this.z = h();
    }

    private static boolean l(View view) {
        Drawable background = view.getBackground();
        return background != null && background.getOpacity() == -1;
    }

    static boolean m(View view) {
        return (w.n(view) == 4 || w.n(view) == 2) ? false : true;
    }

    public void a(Object obj, boolean z) {
        this.C = obj;
        this.D = z;
        setWillNotDraw(!z && getBackground() == null);
        requestLayout();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void addFocusables(ArrayList<View> arrayList, int i, int i2) {
        if (getDescendantFocusability() == 393216) {
            return;
        }
        int childCount = getChildCount();
        boolean z = false;
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            if (i(childAt)) {
                if (h(childAt)) {
                    childAt.addFocusables(arrayList, i, i2);
                    z = true;
                }
            } else {
                this.I.add(childAt);
            }
        }
        if (!z) {
            int size = this.I.size();
            for (int i4 = 0; i4 < size; i4++) {
                View view = this.I.get(i4);
                if (view.getVisibility() == 0) {
                    view.addFocusables(arrayList, i, i2);
                }
            }
        }
        this.I.clear();
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        super.addView(view, i, layoutParams);
        if (c() == null && !i(view)) {
            w.i(view, 1);
        } else {
            w.i(view, 4);
        }
        if (N) {
            return;
        }
        w.a(view, this.f1331b);
    }

    public void b(int i, int i2) {
        a(a.f.d.b.c(getContext(), i), i2);
    }

    public int c(int i) {
        int q = w.q(this);
        if (i == 3) {
            int i2 = this.o;
            if (i2 != 3) {
                return i2;
            }
            int i3 = q == 0 ? this.q : this.r;
            if (i3 != 3) {
                return i3;
            }
            return 0;
        }
        if (i == 5) {
            int i4 = this.p;
            if (i4 != 3) {
                return i4;
            }
            int i5 = q == 0 ? this.r : this.q;
            if (i5 != 3) {
                return i5;
            }
            return 0;
        }
        if (i == 8388611) {
            int i6 = this.q;
            if (i6 != 3) {
                return i6;
            }
            int i7 = q == 0 ? this.o : this.p;
            if (i7 != 3) {
                return i7;
            }
            return 0;
        }
        if (i != 8388613) {
            return 0;
        }
        int i8 = this.r;
        if (i8 != 3) {
            return i8;
        }
        int i9 = q == 0 ? this.p : this.o;
        if (i9 != 3) {
            return i9;
        }
        return 0;
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof e) && super.checkLayoutParams(layoutParams);
    }

    @Override // android.view.View
    public void computeScroll() {
        int childCount = getChildCount();
        float f2 = 0.0f;
        for (int i = 0; i < childCount; i++) {
            f2 = Math.max(f2, ((e) getChildAt(i).getLayoutParams()).f1339b);
        }
        this.f1335f = f2;
        boolean a2 = this.h.a(true);
        boolean a3 = this.i.a(true);
        if (a2 || a3) {
            w.K(this);
        }
    }

    public int d(View view) {
        if (i(view)) {
            return c(((e) view.getLayoutParams()).f1338a);
        }
        throw new IllegalArgumentException("View " + view + " is not a drawer");
    }

    @Override // android.view.View
    public boolean dispatchGenericMotionEvent(MotionEvent motionEvent) {
        if ((motionEvent.getSource() & 2) != 0 && motionEvent.getAction() != 10 && this.f1335f > 0.0f) {
            int childCount = getChildCount();
            if (childCount == 0) {
                return false;
            }
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            for (int i = childCount - 1; i >= 0; i--) {
                View childAt = getChildAt(i);
                if (a(x, y, childAt) && !g(childAt) && a(motionEvent, childAt)) {
                    return true;
                }
            }
            return false;
        }
        return super.dispatchGenericMotionEvent(motionEvent);
    }

    @Override // android.view.ViewGroup
    protected boolean drawChild(Canvas canvas, View view, long j) {
        int i;
        int height = getHeight();
        boolean g2 = g(view);
        int width = getWidth();
        int save = canvas.save();
        int i2 = 0;
        if (g2) {
            int childCount = getChildCount();
            i = width;
            int i3 = 0;
            for (int i4 = 0; i4 < childCount; i4++) {
                View childAt = getChildAt(i4);
                if (childAt != view && childAt.getVisibility() == 0 && l(childAt) && i(childAt) && childAt.getHeight() >= height) {
                    if (a(childAt, 3)) {
                        int right = childAt.getRight();
                        if (right > i3) {
                            i3 = right;
                        }
                    } else {
                        int left = childAt.getLeft();
                        if (left < i) {
                            i = left;
                        }
                    }
                }
            }
            canvas.clipRect(i3, 0, i, getHeight());
            i2 = i3;
        } else {
            i = width;
        }
        boolean drawChild = super.drawChild(canvas, view, j);
        canvas.restoreToCount(save);
        float f2 = this.f1335f;
        if (f2 > 0.0f && g2) {
            this.g.setColor((this.f1334e & 16777215) | (((int) ((((-16777216) & r2) >>> 24) * f2)) << 24));
            canvas.drawRect(i2, 0.0f, i, getHeight(), this.g);
        } else if (this.y != null && a(view, 3)) {
            int intrinsicWidth = this.y.getIntrinsicWidth();
            int right2 = view.getRight();
            float max = Math.max(0.0f, Math.min(right2 / this.h.d(), 1.0f));
            this.y.setBounds(right2, view.getTop(), intrinsicWidth + right2, view.getBottom());
            this.y.setAlpha((int) (max * 255.0f));
            this.y.draw(canvas);
        } else if (this.z != null && a(view, 5)) {
            int intrinsicWidth2 = this.z.getIntrinsicWidth();
            int left2 = view.getLeft();
            float max2 = Math.max(0.0f, Math.min((getWidth() - left2) / this.i.d(), 1.0f));
            this.z.setBounds(left2 - intrinsicWidth2, view.getTop(), left2, view.getBottom());
            this.z.setAlpha((int) (max2 * 255.0f));
            this.z.draw(canvas);
        }
        return drawChild;
    }

    int e(View view) {
        return a.f.l.d.a(((e) view.getLayoutParams()).f1338a, w.q(this));
    }

    float f(View view) {
        return ((e) view.getLayoutParams()).f1339b;
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new e(-1, -1);
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof e ? new e((e) layoutParams) : layoutParams instanceof ViewGroup.MarginLayoutParams ? new e((ViewGroup.MarginLayoutParams) layoutParams) : new e(layoutParams);
    }

    public float getDrawerElevation() {
        if (O) {
            return this.f1332c;
        }
        return 0.0f;
    }

    public Drawable getStatusBarBackgroundDrawable() {
        return this.x;
    }

    public boolean j(View view) {
        if (i(view)) {
            return ((e) view.getLayoutParams()).f1339b > 0.0f;
        }
        throw new IllegalArgumentException("View " + view + " is not a drawer");
    }

    public void k(View view) {
        b(view, true);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.n = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.n = true;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        Object obj;
        super.onDraw(canvas);
        if (!this.D || this.x == null) {
            return;
        }
        int systemWindowInsetTop = (Build.VERSION.SDK_INT < 21 || (obj = this.C) == null) ? 0 : ((WindowInsets) obj).getSystemWindowInsetTop();
        if (systemWindowInsetTop > 0) {
            this.x.setBounds(0, 0, getWidth(), systemWindowInsetTop);
            this.x.draw(canvas);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:6:0x001b, code lost:
    
        if (r0 != 3) goto L13;
     */
    @Override // android.view.ViewGroup
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onInterceptTouchEvent(android.view.MotionEvent r7) {
        /*
            r6 = this;
            int r0 = r7.getActionMasked()
            a.h.b.c r1 = r6.h
            boolean r1 = r1.b(r7)
            a.h.b.c r2 = r6.i
            boolean r2 = r2.b(r7)
            r1 = r1 | r2
            r2 = 1
            r3 = 0
            if (r0 == 0) goto L38
            if (r0 == r2) goto L31
            r7 = 2
            r4 = 3
            if (r0 == r7) goto L1e
            if (r0 == r4) goto L31
            goto L36
        L1e:
            a.h.b.c r7 = r6.h
            boolean r7 = r7.a(r4)
            if (r7 == 0) goto L36
            androidx.drawerlayout.widget.DrawerLayout$h r7 = r6.j
            r7.b()
            androidx.drawerlayout.widget.DrawerLayout$h r7 = r6.k
            r7.b()
            goto L36
        L31:
            r6.a(r2)
            r6.s = r3
        L36:
            r7 = 0
            goto L60
        L38:
            float r0 = r7.getX()
            float r7 = r7.getY()
            r6.v = r0
            r6.w = r7
            float r4 = r6.f1335f
            r5 = 0
            int r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r4 <= 0) goto L5d
            a.h.b.c r4 = r6.h
            int r0 = (int) r0
            int r7 = (int) r7
            android.view.View r7 = r4.b(r0, r7)
            if (r7 == 0) goto L5d
            boolean r7 = r6.g(r7)
            if (r7 == 0) goto L5d
            r7 = 1
            goto L5e
        L5d:
            r7 = 0
        L5e:
            r6.s = r3
        L60:
            if (r1 != 0) goto L70
            if (r7 != 0) goto L70
            boolean r7 = r6.e()
            if (r7 != 0) goto L70
            boolean r7 = r6.s
            if (r7 == 0) goto L6f
            goto L70
        L6f:
            r2 = 0
        L70:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.drawerlayout.widget.DrawerLayout.onInterceptTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4 && f()) {
            keyEvent.startTracking();
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (i == 4) {
            View d2 = d();
            if (d2 != null && d(d2) == 0) {
                b();
            }
            return d2 != null;
        }
        return super.onKeyUp(i, keyEvent);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        float f2;
        int i5;
        this.m = true;
        int i6 = i3 - i;
        int childCount = getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = getChildAt(i7);
            if (childAt.getVisibility() != 8) {
                e eVar = (e) childAt.getLayoutParams();
                if (g(childAt)) {
                    int i8 = ((ViewGroup.MarginLayoutParams) eVar).leftMargin;
                    childAt.layout(i8, ((ViewGroup.MarginLayoutParams) eVar).topMargin, childAt.getMeasuredWidth() + i8, ((ViewGroup.MarginLayoutParams) eVar).topMargin + childAt.getMeasuredHeight());
                } else {
                    int measuredWidth = childAt.getMeasuredWidth();
                    int measuredHeight = childAt.getMeasuredHeight();
                    if (a(childAt, 3)) {
                        float f3 = measuredWidth;
                        i5 = (-measuredWidth) + ((int) (eVar.f1339b * f3));
                        f2 = (measuredWidth + i5) / f3;
                    } else {
                        float f4 = measuredWidth;
                        f2 = (i6 - r11) / f4;
                        i5 = i6 - ((int) (eVar.f1339b * f4));
                    }
                    boolean z2 = f2 != eVar.f1339b;
                    int i9 = eVar.f1338a & 112;
                    if (i9 == 16) {
                        int i10 = i4 - i2;
                        int i11 = (i10 - measuredHeight) / 2;
                        int i12 = ((ViewGroup.MarginLayoutParams) eVar).topMargin;
                        if (i11 < i12) {
                            i11 = i12;
                        } else {
                            int i13 = i11 + measuredHeight;
                            int i14 = ((ViewGroup.MarginLayoutParams) eVar).bottomMargin;
                            if (i13 > i10 - i14) {
                                i11 = (i10 - i14) - measuredHeight;
                            }
                        }
                        childAt.layout(i5, i11, measuredWidth + i5, measuredHeight + i11);
                    } else if (i9 != 80) {
                        int i15 = ((ViewGroup.MarginLayoutParams) eVar).topMargin;
                        childAt.layout(i5, i15, measuredWidth + i5, measuredHeight + i15);
                    } else {
                        int i16 = i4 - i2;
                        childAt.layout(i5, (i16 - ((ViewGroup.MarginLayoutParams) eVar).bottomMargin) - childAt.getMeasuredHeight(), measuredWidth + i5, i16 - ((ViewGroup.MarginLayoutParams) eVar).bottomMargin);
                    }
                    if (z2) {
                        c(childAt, f2);
                    }
                    int i17 = eVar.f1339b > 0.0f ? 0 : 4;
                    if (childAt.getVisibility() != i17) {
                        childAt.setVisibility(i17);
                    }
                }
            }
        }
        this.m = false;
        this.n = false;
    }

    @Override // android.view.View
    @SuppressLint({"WrongConstant"})
    protected void onMeasure(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        if (mode != 1073741824 || mode2 != 1073741824) {
            if (!isInEditMode()) {
                throw new IllegalArgumentException("DrawerLayout must be measured with MeasureSpec.EXACTLY.");
            }
            if (mode != Integer.MIN_VALUE && mode == 0) {
                size = 300;
            }
            if (mode2 != Integer.MIN_VALUE && mode2 == 0) {
                size2 = 300;
            }
        }
        setMeasuredDimension(size, size2);
        int i3 = 0;
        boolean z = this.C != null && w.m(this);
        int q = w.q(this);
        int childCount = getChildCount();
        int i4 = 0;
        boolean z2 = false;
        boolean z3 = false;
        while (i4 < childCount) {
            View childAt = getChildAt(i4);
            if (childAt.getVisibility() != 8) {
                e eVar = (e) childAt.getLayoutParams();
                if (z) {
                    int a2 = a.f.l.d.a(eVar.f1338a, q);
                    if (w.m(childAt)) {
                        if (Build.VERSION.SDK_INT >= 21) {
                            WindowInsets windowInsets = (WindowInsets) this.C;
                            if (a2 == 3) {
                                windowInsets = windowInsets.replaceSystemWindowInsets(windowInsets.getSystemWindowInsetLeft(), windowInsets.getSystemWindowInsetTop(), i3, windowInsets.getSystemWindowInsetBottom());
                            } else if (a2 == 5) {
                                windowInsets = windowInsets.replaceSystemWindowInsets(i3, windowInsets.getSystemWindowInsetTop(), windowInsets.getSystemWindowInsetRight(), windowInsets.getSystemWindowInsetBottom());
                            }
                            childAt.dispatchApplyWindowInsets(windowInsets);
                        }
                    } else if (Build.VERSION.SDK_INT >= 21) {
                        WindowInsets windowInsets2 = (WindowInsets) this.C;
                        if (a2 == 3) {
                            windowInsets2 = windowInsets2.replaceSystemWindowInsets(windowInsets2.getSystemWindowInsetLeft(), windowInsets2.getSystemWindowInsetTop(), i3, windowInsets2.getSystemWindowInsetBottom());
                        } else if (a2 == 5) {
                            windowInsets2 = windowInsets2.replaceSystemWindowInsets(i3, windowInsets2.getSystemWindowInsetTop(), windowInsets2.getSystemWindowInsetRight(), windowInsets2.getSystemWindowInsetBottom());
                        }
                        ((ViewGroup.MarginLayoutParams) eVar).leftMargin = windowInsets2.getSystemWindowInsetLeft();
                        ((ViewGroup.MarginLayoutParams) eVar).topMargin = windowInsets2.getSystemWindowInsetTop();
                        ((ViewGroup.MarginLayoutParams) eVar).rightMargin = windowInsets2.getSystemWindowInsetRight();
                        ((ViewGroup.MarginLayoutParams) eVar).bottomMargin = windowInsets2.getSystemWindowInsetBottom();
                    }
                }
                if (g(childAt)) {
                    childAt.measure(View.MeasureSpec.makeMeasureSpec((size - ((ViewGroup.MarginLayoutParams) eVar).leftMargin) - ((ViewGroup.MarginLayoutParams) eVar).rightMargin, 1073741824), View.MeasureSpec.makeMeasureSpec((size2 - ((ViewGroup.MarginLayoutParams) eVar).topMargin) - ((ViewGroup.MarginLayoutParams) eVar).bottomMargin, 1073741824));
                } else if (i(childAt)) {
                    if (O) {
                        float l = w.l(childAt);
                        float f2 = this.f1332c;
                        if (l != f2) {
                            w.a(childAt, f2);
                        }
                    }
                    int e2 = e(childAt) & 7;
                    boolean z4 = e2 == 3;
                    if ((z4 && z2) || (!z4 && z3)) {
                        throw new IllegalStateException("Child drawer has absolute gravity " + h(e2) + " but this DrawerLayout already has a drawer view along that edge");
                    }
                    if (z4) {
                        z2 = true;
                    } else {
                        z3 = true;
                    }
                    childAt.measure(ViewGroup.getChildMeasureSpec(i, this.f1333d + ((ViewGroup.MarginLayoutParams) eVar).leftMargin + ((ViewGroup.MarginLayoutParams) eVar).rightMargin, ((ViewGroup.MarginLayoutParams) eVar).width), ViewGroup.getChildMeasureSpec(i2, ((ViewGroup.MarginLayoutParams) eVar).topMargin + ((ViewGroup.MarginLayoutParams) eVar).bottomMargin, ((ViewGroup.MarginLayoutParams) eVar).height));
                    i4++;
                    i3 = 0;
                } else {
                    throw new IllegalStateException("Child " + childAt + " at index " + i4 + " does not have a valid layout_gravity - must be Gravity.LEFT, Gravity.RIGHT or Gravity.NO_GRAVITY");
                }
            }
            i4++;
            i3 = 0;
        }
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        View b2;
        if (!(parcelable instanceof f)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        f fVar = (f) parcelable;
        super.onRestoreInstanceState(fVar.d());
        int i = fVar.f1342d;
        if (i != 0 && (b2 = b(i)) != null) {
            k(b2);
        }
        int i2 = fVar.f1343e;
        if (i2 != 3) {
            a(i2, 3);
        }
        int i3 = fVar.f1344f;
        if (i3 != 3) {
            a(i3, 5);
        }
        int i4 = fVar.g;
        if (i4 != 3) {
            a(i4, 8388611);
        }
        int i5 = fVar.h;
        if (i5 != 3) {
            a(i5, 8388613);
        }
    }

    @Override // android.view.View
    public void onRtlPropertiesChanged(int i) {
        i();
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        f fVar = new f(super.onSaveInstanceState());
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            e eVar = (e) getChildAt(i).getLayoutParams();
            boolean z = eVar.f1341d == 1;
            boolean z2 = eVar.f1341d == 2;
            if (z || z2) {
                fVar.f1342d = eVar.f1338a;
                break;
            }
        }
        fVar.f1343e = this.o;
        fVar.f1344f = this.p;
        fVar.g = this.q;
        fVar.h = this.r;
        return fVar;
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x005b, code lost:
    
        if (d(r7) != 2) goto L20;
     */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onTouchEvent(android.view.MotionEvent r7) {
        /*
            r6 = this;
            a.h.b.c r0 = r6.h
            r0.a(r7)
            a.h.b.c r0 = r6.i
            r0.a(r7)
            int r0 = r7.getAction()
            r0 = r0 & 255(0xff, float:3.57E-43)
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L62
            if (r0 == r2) goto L20
            r7 = 3
            if (r0 == r7) goto L1a
            goto L70
        L1a:
            r6.a(r2)
            r6.s = r1
            goto L70
        L20:
            float r0 = r7.getX()
            float r7 = r7.getY()
            a.h.b.c r3 = r6.h
            int r4 = (int) r0
            int r5 = (int) r7
            android.view.View r3 = r3.b(r4, r5)
            if (r3 == 0) goto L5d
            boolean r3 = r6.g(r3)
            if (r3 == 0) goto L5d
            float r3 = r6.v
            float r0 = r0 - r3
            float r3 = r6.w
            float r7 = r7 - r3
            a.h.b.c r3 = r6.h
            int r3 = r3.e()
            float r0 = r0 * r0
            float r7 = r7 * r7
            float r0 = r0 + r7
            int r3 = r3 * r3
            float r7 = (float) r3
            int r7 = (r0 > r7 ? 1 : (r0 == r7 ? 0 : -1))
            if (r7 >= 0) goto L5d
            android.view.View r7 = r6.c()
            if (r7 == 0) goto L5d
            int r7 = r6.d(r7)
            r0 = 2
            if (r7 != r0) goto L5e
        L5d:
            r1 = 1
        L5e:
            r6.a(r1)
            goto L70
        L62:
            float r0 = r7.getX()
            float r7 = r7.getY()
            r6.v = r0
            r6.w = r7
            r6.s = r1
        L70:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.drawerlayout.widget.DrawerLayout.onTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean z) {
        super.requestDisallowInterceptTouchEvent(z);
        if (z) {
            a(true);
        }
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        if (this.m) {
            return;
        }
        super.requestLayout();
    }

    public void setDrawerElevation(float f2) {
        this.f1332c = f2;
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (i(childAt)) {
                w.a(childAt, this.f1332c);
            }
        }
    }

    @Deprecated
    public void setDrawerListener(d dVar) {
        d dVar2 = this.t;
        if (dVar2 != null) {
            b(dVar2);
        }
        if (dVar != null) {
            a(dVar);
        }
        this.t = dVar;
    }

    public void setDrawerLockMode(int i) {
        a(i, 3);
        a(i, 5);
    }

    public void setScrimColor(int i) {
        this.f1334e = i;
        invalidate();
    }

    public void setStatusBarBackground(Drawable drawable) {
        this.x = drawable;
        invalidate();
    }

    public void setStatusBarBackgroundColor(int i) {
        this.x = new ColorDrawable(i);
        invalidate();
    }

    public DrawerLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private Drawable h() {
        int q = w.q(this);
        if (q == 0) {
            Drawable drawable = this.F;
            if (drawable != null) {
                b(drawable, q);
                return this.F;
            }
        } else {
            Drawable drawable2 = this.E;
            if (drawable2 != null) {
                b(drawable2, q);
                return this.E;
            }
        }
        return this.H;
    }

    public void b(d dVar) {
        List<d> list;
        if (dVar == null || (list = this.u) == null) {
            return;
        }
        list.remove(dVar);
    }

    public boolean f(int i) {
        View b2 = b(i);
        if (b2 != null) {
            return j(b2);
        }
        return false;
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new e(getContext(), attributeSet);
    }

    public DrawerLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f1331b = new c();
        this.f1334e = -1728053248;
        this.g = new Paint();
        this.n = true;
        this.o = 3;
        this.p = 3;
        this.q = 3;
        this.r = 3;
        this.E = null;
        this.F = null;
        this.G = null;
        this.H = null;
        setDescendantFocusability(262144);
        float f2 = getResources().getDisplayMetrics().density;
        this.f1333d = (int) ((64.0f * f2) + 0.5f);
        float f3 = 400.0f * f2;
        this.j = new h(3);
        this.k = new h(5);
        this.h = a.h.b.c.a(this, 1.0f, this.j);
        this.h.d(1);
        this.h.a(f3);
        this.j.a(this.h);
        this.i = a.h.b.c.a(this, 1.0f, this.k);
        this.i.d(2);
        this.i.a(f3);
        this.k.a(this.i);
        setFocusableInTouchMode(true);
        w.i(this, 1);
        w.a(this, new b());
        setMotionEventSplittingEnabled(false);
        if (w.m(this)) {
            if (Build.VERSION.SDK_INT >= 21) {
                setOnApplyWindowInsetsListener(new a(this));
                setSystemUiVisibility(1280);
                TypedArray obtainStyledAttributes = context.obtainStyledAttributes(L);
                try {
                    this.x = obtainStyledAttributes.getDrawable(0);
                } finally {
                    obtainStyledAttributes.recycle();
                }
            } else {
                this.x = null;
            }
        }
        this.f1332c = f2 * 10.0f;
        this.I = new ArrayList<>();
    }

    public boolean e(int i) {
        View b2 = b(i);
        if (b2 != null) {
            return h(b2);
        }
        return false;
    }

    public void setStatusBarBackground(int i) {
        this.x = i != 0 ? a.f.d.b.c(getContext(), i) : null;
        invalidate();
    }

    private MotionEvent b(MotionEvent motionEvent, View view) {
        float scrollX = getScrollX() - view.getLeft();
        float scrollY = getScrollY() - view.getTop();
        MotionEvent obtain = MotionEvent.obtain(motionEvent);
        obtain.offsetLocation(scrollX, scrollY);
        Matrix matrix = view.getMatrix();
        if (!matrix.isIdentity()) {
            if (this.K == null) {
                this.K = new Matrix();
            }
            matrix.invert(this.K);
            obtain.transform(this.K);
        }
        return obtain;
    }

    private boolean f() {
        return d() != null;
    }

    boolean i(View view) {
        int a2 = a.f.l.d.a(((e) view.getLayoutParams()).f1338a, w.q(view));
        return ((a2 & 3) == 0 && (a2 & 5) == 0) ? false : true;
    }

    /* loaded from: classes.dex */
    public static class e extends ViewGroup.MarginLayoutParams {

        /* renamed from: a, reason: collision with root package name */
        public int f1338a;

        /* renamed from: b, reason: collision with root package name */
        float f1339b;

        /* renamed from: c, reason: collision with root package name */
        boolean f1340c;

        /* renamed from: d, reason: collision with root package name */
        int f1341d;

        public e(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f1338a = 0;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, DrawerLayout.M);
            this.f1338a = obtainStyledAttributes.getInt(0, 0);
            obtainStyledAttributes.recycle();
        }

        public e(int i, int i2) {
            super(i, i2);
            this.f1338a = 0;
        }

        public e(e eVar) {
            super((ViewGroup.MarginLayoutParams) eVar);
            this.f1338a = 0;
            this.f1338a = eVar.f1338a;
        }

        public e(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f1338a = 0;
        }

        public e(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.f1338a = 0;
        }
    }

    private boolean e() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (((e) getChildAt(i).getLayoutParams()).f1340c) {
                return true;
            }
        }
        return false;
    }

    public void a(Drawable drawable, int i) {
        if (O) {
            return;
        }
        if ((i & 8388611) == 8388611) {
            this.E = drawable;
        } else if ((i & 8388613) == 8388613) {
            this.F = drawable;
        } else if ((i & 3) == 3) {
            this.G = drawable;
        } else if ((i & 5) != 5) {
            return;
        } else {
            this.H = drawable;
        }
        i();
        invalidate();
    }

    public CharSequence d(int i) {
        int a2 = a.f.l.d.a(i, w.q(this));
        if (a2 == 3) {
            return this.A;
        }
        if (a2 == 5) {
            return this.B;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes.dex */
    public static class f extends a.h.a.a {
        public static final Parcelable.Creator<f> CREATOR = new a();

        /* renamed from: d, reason: collision with root package name */
        int f1342d;

        /* renamed from: e, reason: collision with root package name */
        int f1343e;

        /* renamed from: f, reason: collision with root package name */
        int f1344f;
        int g;
        int h;

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
                return new f(parcel, null);
            }
        }

        public f(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f1342d = 0;
            this.f1342d = parcel.readInt();
            this.f1343e = parcel.readInt();
            this.f1344f = parcel.readInt();
            this.g = parcel.readInt();
            this.h = parcel.readInt();
        }

        @Override // a.h.a.a, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.f1342d);
            parcel.writeInt(this.f1343e);
            parcel.writeInt(this.f1344f);
            parcel.writeInt(this.g);
            parcel.writeInt(this.h);
        }

        public f(Parcelable parcelable) {
            super(parcelable);
            this.f1342d = 0;
        }
    }

    View d() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (i(childAt) && j(childAt)) {
                return childAt;
            }
        }
        return null;
    }

    boolean g(View view) {
        return ((e) view.getLayoutParams()).f1338a == 0;
    }

    void c(View view) {
        e eVar = (e) view.getLayoutParams();
        if ((eVar.f1341d & 1) == 0) {
            eVar.f1341d = 1;
            List<d> list = this.u;
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    this.u.get(size).onDrawerOpened(view);
                }
            }
            c(view, true);
            if (hasWindowFocus()) {
                sendAccessibilityEvent(32);
            }
        }
    }

    public void g(int i) {
        b(i, true);
    }

    public boolean h(View view) {
        if (i(view)) {
            return (((e) view.getLayoutParams()).f1341d & 1) == 1;
        }
        throw new IllegalArgumentException("View " + view + " is not a drawer");
    }

    public void a(d dVar) {
        if (dVar == null) {
            return;
        }
        if (this.u == null) {
            this.u = new ArrayList();
        }
        this.u.add(dVar);
    }

    void b(View view) {
        View rootView;
        e eVar = (e) view.getLayoutParams();
        if ((eVar.f1341d & 1) == 1) {
            eVar.f1341d = 0;
            List<d> list = this.u;
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    this.u.get(size).onDrawerClosed(view);
                }
            }
            c(view, false);
            if (!hasWindowFocus() || (rootView = getRootView()) == null) {
                return;
            }
            rootView.sendAccessibilityEvent(32);
        }
    }

    public void a(int i, int i2) {
        View b2;
        int a2 = a.f.l.d.a(i2, w.q(this));
        if (i2 == 3) {
            this.o = i;
        } else if (i2 == 5) {
            this.p = i;
        } else if (i2 == 8388611) {
            this.q = i;
        } else if (i2 == 8388613) {
            this.r = i;
        }
        if (i != 0) {
            (a2 == 3 ? this.h : this.i).b();
        }
        if (i != 1) {
            if (i == 2 && (b2 = b(a2)) != null) {
                k(b2);
                return;
            }
            return;
        }
        View b3 = b(a2);
        if (b3 != null) {
            a(b3);
        }
    }

    private void c(View view, boolean z) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if ((!z && !i(childAt)) || (z && childAt == view)) {
                w.i(childAt, 1);
            } else {
                w.i(childAt, 4);
            }
        }
    }

    void b(View view, float f2) {
        float f3 = f(view);
        float width = view.getWidth();
        int i = ((int) (width * f2)) - ((int) (f3 * width));
        if (!a(view, 3)) {
            i = -i;
        }
        view.offsetLeftAndRight(i);
        c(view, f2);
    }

    void c(View view, float f2) {
        e eVar = (e) view.getLayoutParams();
        if (f2 == eVar.f1339b) {
            return;
        }
        eVar.f1339b = f2;
        a(view, f2);
    }

    private boolean a(float f2, float f3, View view) {
        if (this.J == null) {
            this.J = new Rect();
        }
        view.getHitRect(this.J);
        return this.J.contains((int) f2, (int) f3);
    }

    View c() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if ((((e) childAt.getLayoutParams()).f1341d & 1) == 1) {
                return childAt;
            }
        }
        return null;
    }

    View b(int i) {
        int a2 = a.f.l.d.a(i, w.q(this)) & 7;
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if ((e(childAt) & 7) == a2) {
                return childAt;
            }
        }
        return null;
    }

    private boolean a(MotionEvent motionEvent, View view) {
        if (!view.getMatrix().isIdentity()) {
            MotionEvent b2 = b(motionEvent, view);
            boolean dispatchGenericMotionEvent = view.dispatchGenericMotionEvent(b2);
            b2.recycle();
            return dispatchGenericMotionEvent;
        }
        float scrollX = getScrollX() - view.getLeft();
        float scrollY = getScrollY() - view.getTop();
        motionEvent.offsetLocation(scrollX, scrollY);
        boolean dispatchGenericMotionEvent2 = view.dispatchGenericMotionEvent(motionEvent);
        motionEvent.offsetLocation(-scrollX, -scrollY);
        return dispatchGenericMotionEvent2;
    }

    private boolean b(Drawable drawable, int i) {
        if (drawable == null || !androidx.core.graphics.drawable.a.f(drawable)) {
            return false;
        }
        androidx.core.graphics.drawable.a.a(drawable, i);
        return true;
    }

    public void b() {
        a(false);
    }

    public void b(View view, boolean z) {
        if (i(view)) {
            e eVar = (e) view.getLayoutParams();
            if (this.n) {
                eVar.f1339b = 1.0f;
                eVar.f1341d = 1;
                c(view, true);
            } else if (z) {
                eVar.f1341d |= 2;
                if (a(view, 3)) {
                    this.h.b(view, 0, view.getTop());
                } else {
                    this.i.b(view, getWidth() - view.getWidth(), view.getTop());
                }
            } else {
                b(view, 1.0f);
                a(eVar.f1338a, 0, view);
                view.setVisibility(0);
            }
            invalidate();
            return;
        }
        throw new IllegalArgumentException("View " + view + " is not a sliding drawer");
    }

    void a(int i, int i2, View view) {
        int f2 = this.h.f();
        int f3 = this.i.f();
        int i3 = 2;
        if (f2 == 1 || f3 == 1) {
            i3 = 1;
        } else if (f2 != 2 && f3 != 2) {
            i3 = 0;
        }
        if (view != null && i2 == 0) {
            float f4 = ((e) view.getLayoutParams()).f1339b;
            if (f4 == 0.0f) {
                b(view);
            } else if (f4 == 1.0f) {
                c(view);
            }
        }
        if (i3 != this.l) {
            this.l = i3;
            List<d> list = this.u;
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    this.u.get(size).onDrawerStateChanged(i3);
                }
            }
        }
    }

    void a(View view, float f2) {
        List<d> list = this.u;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.u.get(size).onDrawerSlide(view, f2);
            }
        }
    }

    public void b(int i, boolean z) {
        View b2 = b(i);
        if (b2 != null) {
            b(b2, z);
            return;
        }
        throw new IllegalArgumentException("No drawer view found with gravity " + h(i));
    }

    boolean a(View view, int i) {
        return (e(view) & i) == i;
    }

    void a(boolean z) {
        boolean b2;
        int childCount = getChildCount();
        boolean z2 = false;
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            e eVar = (e) childAt.getLayoutParams();
            if (i(childAt) && (!z || eVar.f1340c)) {
                int width = childAt.getWidth();
                if (a(childAt, 3)) {
                    b2 = this.h.b(childAt, -width, childAt.getTop());
                } else {
                    b2 = this.i.b(childAt, getWidth(), childAt.getTop());
                }
                z2 |= b2;
                eVar.f1340c = false;
            }
        }
        this.j.b();
        this.k.b();
        if (z2) {
            invalidate();
        }
    }

    public void a(View view) {
        a(view, true);
    }

    public void a(View view, boolean z) {
        if (i(view)) {
            e eVar = (e) view.getLayoutParams();
            if (this.n) {
                eVar.f1339b = 0.0f;
                eVar.f1341d = 0;
            } else if (z) {
                eVar.f1341d |= 4;
                if (a(view, 3)) {
                    this.h.b(view, -view.getWidth(), view.getTop());
                } else {
                    this.i.b(view, getWidth(), view.getTop());
                }
            } else {
                b(view, 0.0f);
                a(eVar.f1338a, 0, view);
                view.setVisibility(4);
            }
            invalidate();
            return;
        }
        throw new IllegalArgumentException("View " + view + " is not a sliding drawer");
    }

    public void a(int i) {
        a(i, true);
    }

    public void a(int i, boolean z) {
        View b2 = b(i);
        if (b2 != null) {
            a(b2, z);
            return;
        }
        throw new IllegalArgumentException("No drawer view found with gravity " + h(i));
    }

    void a() {
        if (this.s) {
            return;
        }
        long uptimeMillis = SystemClock.uptimeMillis();
        MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            getChildAt(i).dispatchTouchEvent(obtain);
        }
        obtain.recycle();
        this.s = true;
    }
}
