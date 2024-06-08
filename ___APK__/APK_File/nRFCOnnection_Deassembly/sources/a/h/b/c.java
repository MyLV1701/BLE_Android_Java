package a.h.b;

import a.f.l.w;
import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.OverScroller;
import java.util.Arrays;

/* loaded from: classes.dex */
public class c {
    private static final Interpolator w = new a();

    /* renamed from: a, reason: collision with root package name */
    private int f368a;

    /* renamed from: b, reason: collision with root package name */
    private int f369b;

    /* renamed from: d, reason: collision with root package name */
    private float[] f371d;

    /* renamed from: e, reason: collision with root package name */
    private float[] f372e;

    /* renamed from: f, reason: collision with root package name */
    private float[] f373f;
    private float[] g;
    private int[] h;
    private int[] i;
    private int[] j;
    private int k;
    private VelocityTracker l;
    private float m;
    private float n;
    private int o;
    private int p;
    private OverScroller q;
    private final AbstractC0024c r;
    private View s;
    private boolean t;
    private final ViewGroup u;

    /* renamed from: c, reason: collision with root package name */
    private int f370c = -1;
    private final Runnable v = new b();

    /* loaded from: classes.dex */
    static class a implements Interpolator {
        a() {
        }

        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f2) {
            float f3 = f2 - 1.0f;
            return (f3 * f3 * f3 * f3 * f3) + 1.0f;
        }
    }

    /* loaded from: classes.dex */
    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            c.this.c(0);
        }
    }

    /* renamed from: a.h.b.c$c, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static abstract class AbstractC0024c {
        public abstract int clampViewPositionHorizontal(View view, int i, int i2);

        public abstract int clampViewPositionVertical(View view, int i, int i2);

        public int getOrderedChildIndex(int i) {
            return i;
        }

        public int getViewHorizontalDragRange(View view) {
            return 0;
        }

        public int getViewVerticalDragRange(View view) {
            return 0;
        }

        public void onEdgeDragStarted(int i, int i2) {
        }

        public boolean onEdgeLock(int i) {
            return false;
        }

        public void onEdgeTouched(int i, int i2) {
        }

        public void onViewCaptured(View view, int i) {
        }

        public abstract void onViewDragStateChanged(int i);

        public abstract void onViewPositionChanged(View view, int i, int i2, int i3, int i4);

        public abstract void onViewReleased(View view, float f2, float f3);

        public abstract boolean tryCaptureView(View view, int i);
    }

    private c(Context context, ViewGroup viewGroup, AbstractC0024c abstractC0024c) {
        if (viewGroup == null) {
            throw new IllegalArgumentException("Parent view may not be null");
        }
        if (abstractC0024c != null) {
            this.u = viewGroup;
            this.r = abstractC0024c;
            ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
            this.o = (int) ((context.getResources().getDisplayMetrics().density * 20.0f) + 0.5f);
            this.f369b = viewConfiguration.getScaledTouchSlop();
            this.m = viewConfiguration.getScaledMaximumFlingVelocity();
            this.n = viewConfiguration.getScaledMinimumFlingVelocity();
            this.q = new OverScroller(context, w);
            return;
        }
        throw new IllegalArgumentException("Callback may not be null");
    }

    public static c a(ViewGroup viewGroup, AbstractC0024c abstractC0024c) {
        return new c(viewGroup.getContext(), viewGroup, abstractC0024c);
    }

    private void g() {
        float[] fArr = this.f371d;
        if (fArr == null) {
            return;
        }
        Arrays.fill(fArr, 0.0f);
        Arrays.fill(this.f372e, 0.0f);
        Arrays.fill(this.f373f, 0.0f);
        Arrays.fill(this.g, 0.0f);
        Arrays.fill(this.h, 0);
        Arrays.fill(this.i, 0);
        Arrays.fill(this.j, 0);
        this.k = 0;
    }

    private void h() {
        this.l.computeCurrentVelocity(1000, this.m);
        a(a(this.l.getXVelocity(this.f370c), this.n, this.m), a(this.l.getYVelocity(this.f370c), this.n, this.m));
    }

    public void b() {
        this.f370c = -1;
        g();
        VelocityTracker velocityTracker = this.l;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.l = null;
        }
    }

    public View c() {
        return this.s;
    }

    public void d(int i) {
        this.p = i;
    }

    public int e() {
        return this.f369b;
    }

    public int f() {
        return this.f368a;
    }

    public static c a(ViewGroup viewGroup, float f2, AbstractC0024c abstractC0024c) {
        c a2 = a(viewGroup, abstractC0024c);
        a2.f369b = (int) (a2.f369b * (1.0f / f2));
        return a2;
    }

    private void c(MotionEvent motionEvent) {
        int pointerCount = motionEvent.getPointerCount();
        for (int i = 0; i < pointerCount; i++) {
            int pointerId = motionEvent.getPointerId(i);
            if (g(pointerId)) {
                float x = motionEvent.getX(i);
                float y = motionEvent.getY(i);
                this.f373f[pointerId] = x;
                this.g[pointerId] = y;
            }
        }
    }

    private void e(int i) {
        if (this.f371d == null || !b(i)) {
            return;
        }
        this.f371d[i] = 0.0f;
        this.f372e[i] = 0.0f;
        this.f373f[i] = 0.0f;
        this.g[i] = 0.0f;
        this.h[i] = 0;
        this.i[i] = 0;
        this.j[i] = 0;
        this.k = ((1 << i) ^ (-1)) & this.k;
    }

    private void f(int i) {
        float[] fArr = this.f371d;
        if (fArr == null || fArr.length <= i) {
            int i2 = i + 1;
            float[] fArr2 = new float[i2];
            float[] fArr3 = new float[i2];
            float[] fArr4 = new float[i2];
            float[] fArr5 = new float[i2];
            int[] iArr = new int[i2];
            int[] iArr2 = new int[i2];
            int[] iArr3 = new int[i2];
            float[] fArr6 = this.f371d;
            if (fArr6 != null) {
                System.arraycopy(fArr6, 0, fArr2, 0, fArr6.length);
                float[] fArr7 = this.f372e;
                System.arraycopy(fArr7, 0, fArr3, 0, fArr7.length);
                float[] fArr8 = this.f373f;
                System.arraycopy(fArr8, 0, fArr4, 0, fArr8.length);
                float[] fArr9 = this.g;
                System.arraycopy(fArr9, 0, fArr5, 0, fArr9.length);
                int[] iArr4 = this.h;
                System.arraycopy(iArr4, 0, iArr, 0, iArr4.length);
                int[] iArr5 = this.i;
                System.arraycopy(iArr5, 0, iArr2, 0, iArr5.length);
                int[] iArr6 = this.j;
                System.arraycopy(iArr6, 0, iArr3, 0, iArr6.length);
            }
            this.f371d = fArr2;
            this.f372e = fArr3;
            this.f373f = fArr4;
            this.g = fArr5;
            this.h = iArr;
            this.i = iArr2;
            this.j = iArr3;
        }
    }

    public int d() {
        return this.o;
    }

    public boolean d(int i, int i2) {
        if (this.t) {
            return b(i, i2, (int) this.l.getXVelocity(this.f370c), (int) this.l.getYVelocity(this.f370c));
        }
        throw new IllegalStateException("Cannot settleCapturedViewAt outside of a call to Callback#onViewReleased");
    }

    public void a(float f2) {
        this.n = f2;
    }

    public void a(View view, int i) {
        if (view.getParent() == this.u) {
            this.s = view;
            this.f370c = i;
            this.r.onViewCaptured(view, i);
            c(1);
            return;
        }
        throw new IllegalArgumentException("captureChildView: parameter must be a descendant of the ViewDragHelper's tracked parent view (" + this.u + ")");
    }

    public boolean b(View view, int i, int i2) {
        this.s = view;
        this.f370c = -1;
        boolean b2 = b(i, i2, 0, 0);
        if (!b2 && this.f368a == 0 && this.s != null) {
            this.s = null;
        }
        return b2;
    }

    void c(int i) {
        this.u.removeCallbacks(this.v);
        if (this.f368a != i) {
            this.f368a = i;
            this.r.onViewDragStateChanged(i);
            if (this.f368a == 0) {
                this.s = null;
            }
        }
    }

    private boolean g(int i) {
        if (b(i)) {
            return true;
        }
        Log.e("ViewDragHelper", "Ignoring pointerId=" + i + " because ACTION_DOWN was not received for this pointer before ACTION_MOVE. It likely happened because  ViewDragHelper did not receive all the events in the event stream.");
        return false;
    }

    private boolean b(int i, int i2, int i3, int i4) {
        int left = this.s.getLeft();
        int top = this.s.getTop();
        int i5 = i - left;
        int i6 = i2 - top;
        if (i5 == 0 && i6 == 0) {
            this.q.abortAnimation();
            c(0);
            return false;
        }
        this.q.startScroll(left, top, i5, i6, a(this.s, i5, i6, i3, i4));
        c(2);
        return true;
    }

    private int e(int i, int i2) {
        int i3 = i < this.u.getLeft() + this.o ? 1 : 0;
        if (i2 < this.u.getTop() + this.o) {
            i3 |= 4;
        }
        if (i > this.u.getRight() - this.o) {
            i3 |= 2;
        }
        return i2 > this.u.getBottom() - this.o ? i3 | 8 : i3;
    }

    public void a() {
        b();
        if (this.f368a == 2) {
            int currX = this.q.getCurrX();
            int currY = this.q.getCurrY();
            this.q.abortAnimation();
            int currX2 = this.q.getCurrX();
            int currY2 = this.q.getCurrY();
            this.r.onViewPositionChanged(this.s, currX2, currY2, currX2 - currX, currY2 - currY);
        }
        c(0);
    }

    public boolean c(int i, int i2) {
        return a(this.s, i, i2);
    }

    private int b(int i, int i2, int i3) {
        int abs;
        if (i == 0) {
            return 0;
        }
        int width = this.u.getWidth();
        float f2 = width / 2;
        float b2 = f2 + (b(Math.min(1.0f, Math.abs(i) / width)) * f2);
        int abs2 = Math.abs(i2);
        if (abs2 > 0) {
            abs = Math.round(Math.abs(b2 / abs2) * 1000.0f) * 4;
        } else {
            abs = (int) (((Math.abs(i) / i3) + 1.0f) * 256.0f);
        }
        return Math.min(abs, 600);
    }

    private int a(View view, int i, int i2, int i3, int i4) {
        float f2;
        float f3;
        float f4;
        float f5;
        int a2 = a(i3, (int) this.n, (int) this.m);
        int a3 = a(i4, (int) this.n, (int) this.m);
        int abs = Math.abs(i);
        int abs2 = Math.abs(i2);
        int abs3 = Math.abs(a2);
        int abs4 = Math.abs(a3);
        int i5 = abs3 + abs4;
        int i6 = abs + abs2;
        if (a2 != 0) {
            f2 = abs3;
            f3 = i5;
        } else {
            f2 = abs;
            f3 = i6;
        }
        float f6 = f2 / f3;
        if (a3 != 0) {
            f4 = abs4;
            f5 = i5;
        } else {
            f4 = abs2;
            f5 = i6;
        }
        return (int) ((b(i, a2, this.r.getViewHorizontalDragRange(view)) * f6) + (b(i2, a3, this.r.getViewVerticalDragRange(view)) * (f4 / f5)));
    }

    private float b(float f2) {
        return (float) Math.sin((f2 - 0.5f) * 0.47123894f);
    }

    private void b(float f2, float f3, int i) {
        f(i);
        float[] fArr = this.f371d;
        this.f373f[i] = f2;
        fArr[i] = f2;
        float[] fArr2 = this.f372e;
        this.g[i] = f3;
        fArr2[i] = f3;
        this.h[i] = e((int) f2, (int) f3);
        this.k |= 1 << i;
    }

    private int a(int i, int i2, int i3) {
        int abs = Math.abs(i);
        if (abs < i2) {
            return 0;
        }
        return abs > i3 ? i > 0 ? i3 : -i3 : i;
    }

    private float a(float f2, float f3, float f4) {
        float abs = Math.abs(f2);
        if (abs < f3) {
            return 0.0f;
        }
        return abs > f4 ? f2 > 0.0f ? f4 : -f4 : f2;
    }

    public boolean a(boolean z) {
        if (this.f368a == 2) {
            boolean computeScrollOffset = this.q.computeScrollOffset();
            int currX = this.q.getCurrX();
            int currY = this.q.getCurrY();
            int left = currX - this.s.getLeft();
            int top = currY - this.s.getTop();
            if (left != 0) {
                w.e(this.s, left);
            }
            if (top != 0) {
                w.f(this.s, top);
            }
            if (left != 0 || top != 0) {
                this.r.onViewPositionChanged(this.s, currX, currY, left, top);
            }
            if (computeScrollOffset && currX == this.q.getFinalX() && currY == this.q.getFinalY()) {
                this.q.abortAnimation();
                computeScrollOffset = false;
            }
            if (!computeScrollOffset) {
                if (z) {
                    this.u.post(this.v);
                } else {
                    c(0);
                }
            }
        }
        return this.f368a == 2;
    }

    public boolean b(int i) {
        return ((1 << i) & this.k) != 0;
    }

    boolean b(View view, int i) {
        if (view == this.s && this.f370c == i) {
            return true;
        }
        if (view == null || !this.r.tryCaptureView(view, i)) {
            return false;
        }
        this.f370c = i;
        a(view, i);
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:50:0x00dd, code lost:
    
        if (r12 != r11) goto L54;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean b(android.view.MotionEvent r17) {
        /*
            Method dump skipped, instructions count: 315
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: a.h.b.c.b(android.view.MotionEvent):boolean");
    }

    private void a(float f2, float f3) {
        this.t = true;
        this.r.onViewReleased(this.s, f2, f3);
        this.t = false;
        if (this.f368a == 1) {
            c(0);
        }
    }

    public void a(MotionEvent motionEvent) {
        int i;
        int actionMasked = motionEvent.getActionMasked();
        int actionIndex = motionEvent.getActionIndex();
        if (actionMasked == 0) {
            b();
        }
        if (this.l == null) {
            this.l = VelocityTracker.obtain();
        }
        this.l.addMovement(motionEvent);
        int i2 = 0;
        if (actionMasked == 0) {
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            int pointerId = motionEvent.getPointerId(0);
            View b2 = b((int) x, (int) y);
            b(x, y, pointerId);
            b(b2, pointerId);
            int i3 = this.h[pointerId];
            int i4 = this.p;
            if ((i3 & i4) != 0) {
                this.r.onEdgeTouched(i3 & i4, pointerId);
                return;
            }
            return;
        }
        if (actionMasked == 1) {
            if (this.f368a == 1) {
                h();
            }
            b();
            return;
        }
        if (actionMasked == 2) {
            if (this.f368a == 1) {
                if (g(this.f370c)) {
                    int findPointerIndex = motionEvent.findPointerIndex(this.f370c);
                    float x2 = motionEvent.getX(findPointerIndex);
                    float y2 = motionEvent.getY(findPointerIndex);
                    float[] fArr = this.f373f;
                    int i5 = this.f370c;
                    int i6 = (int) (x2 - fArr[i5]);
                    int i7 = (int) (y2 - this.g[i5]);
                    a(this.s.getLeft() + i6, this.s.getTop() + i7, i6, i7);
                    c(motionEvent);
                    return;
                }
                return;
            }
            int pointerCount = motionEvent.getPointerCount();
            while (i2 < pointerCount) {
                int pointerId2 = motionEvent.getPointerId(i2);
                if (g(pointerId2)) {
                    float x3 = motionEvent.getX(i2);
                    float y3 = motionEvent.getY(i2);
                    float f2 = x3 - this.f371d[pointerId2];
                    float f3 = y3 - this.f372e[pointerId2];
                    a(f2, f3, pointerId2);
                    if (this.f368a != 1) {
                        View b3 = b((int) x3, (int) y3);
                        if (a(b3, f2, f3) && b(b3, pointerId2)) {
                            break;
                        }
                    } else {
                        break;
                    }
                }
                i2++;
            }
            c(motionEvent);
            return;
        }
        if (actionMasked == 3) {
            if (this.f368a == 1) {
                a(0.0f, 0.0f);
            }
            b();
            return;
        }
        if (actionMasked == 5) {
            int pointerId3 = motionEvent.getPointerId(actionIndex);
            float x4 = motionEvent.getX(actionIndex);
            float y4 = motionEvent.getY(actionIndex);
            b(x4, y4, pointerId3);
            if (this.f368a == 0) {
                b(b((int) x4, (int) y4), pointerId3);
                int i8 = this.h[pointerId3];
                int i9 = this.p;
                if ((i8 & i9) != 0) {
                    this.r.onEdgeTouched(i8 & i9, pointerId3);
                    return;
                }
                return;
            }
            if (c((int) x4, (int) y4)) {
                b(this.s, pointerId3);
                return;
            }
            return;
        }
        if (actionMasked != 6) {
            return;
        }
        int pointerId4 = motionEvent.getPointerId(actionIndex);
        if (this.f368a == 1 && pointerId4 == this.f370c) {
            int pointerCount2 = motionEvent.getPointerCount();
            while (true) {
                if (i2 >= pointerCount2) {
                    i = -1;
                    break;
                }
                int pointerId5 = motionEvent.getPointerId(i2);
                if (pointerId5 != this.f370c) {
                    View b4 = b((int) motionEvent.getX(i2), (int) motionEvent.getY(i2));
                    View view = this.s;
                    if (b4 == view && b(view, pointerId5)) {
                        i = this.f370c;
                        break;
                    }
                }
                i2++;
            }
            if (i == -1) {
                h();
            }
        }
        e(pointerId4);
    }

    public View b(int i, int i2) {
        for (int childCount = this.u.getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = this.u.getChildAt(this.r.getOrderedChildIndex(childCount));
            if (i >= childAt.getLeft() && i < childAt.getRight() && i2 >= childAt.getTop() && i2 < childAt.getBottom()) {
                return childAt;
            }
        }
        return null;
    }

    private void a(float f2, float f3, int i) {
        int i2 = a(f2, f3, i, 1) ? 1 : 0;
        if (a(f3, f2, i, 4)) {
            i2 |= 4;
        }
        if (a(f2, f3, i, 2)) {
            i2 |= 2;
        }
        if (a(f3, f2, i, 8)) {
            i2 |= 8;
        }
        if (i2 != 0) {
            int[] iArr = this.i;
            iArr[i] = iArr[i] | i2;
            this.r.onEdgeDragStarted(i2, i);
        }
    }

    private boolean a(float f2, float f3, int i, int i2) {
        float abs = Math.abs(f2);
        float abs2 = Math.abs(f3);
        if ((this.h[i] & i2) != i2 || (this.p & i2) == 0 || (this.j[i] & i2) == i2 || (this.i[i] & i2) == i2) {
            return false;
        }
        int i3 = this.f369b;
        if (abs <= i3 && abs2 <= i3) {
            return false;
        }
        if (abs >= abs2 * 0.5f || !this.r.onEdgeLock(i2)) {
            return (this.i[i] & i2) == 0 && abs > ((float) this.f369b);
        }
        int[] iArr = this.j;
        iArr[i] = iArr[i] | i2;
        return false;
    }

    private boolean a(View view, float f2, float f3) {
        if (view == null) {
            return false;
        }
        boolean z = this.r.getViewHorizontalDragRange(view) > 0;
        boolean z2 = this.r.getViewVerticalDragRange(view) > 0;
        if (!z || !z2) {
            return z ? Math.abs(f2) > ((float) this.f369b) : z2 && Math.abs(f3) > ((float) this.f369b);
        }
        float f4 = (f2 * f2) + (f3 * f3);
        int i = this.f369b;
        return f4 > ((float) (i * i));
    }

    public boolean a(int i) {
        int length = this.f371d.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (a(i, i2)) {
                return true;
            }
        }
        return false;
    }

    public boolean a(int i, int i2) {
        if (!b(i2)) {
            return false;
        }
        boolean z = (i & 1) == 1;
        boolean z2 = (i & 2) == 2;
        float f2 = this.f373f[i2] - this.f371d[i2];
        float f3 = this.g[i2] - this.f372e[i2];
        if (!z || !z2) {
            return z ? Math.abs(f2) > ((float) this.f369b) : z2 && Math.abs(f3) > ((float) this.f369b);
        }
        float f4 = (f2 * f2) + (f3 * f3);
        int i3 = this.f369b;
        return f4 > ((float) (i3 * i3));
    }

    private void a(int i, int i2, int i3, int i4) {
        int left = this.s.getLeft();
        int top = this.s.getTop();
        if (i3 != 0) {
            i = this.r.clampViewPositionHorizontal(this.s, i, i3);
            w.e(this.s, i - left);
        }
        int i5 = i;
        if (i4 != 0) {
            i2 = this.r.clampViewPositionVertical(this.s, i2, i4);
            w.f(this.s, i2 - top);
        }
        int i6 = i2;
        if (i3 == 0 && i4 == 0) {
            return;
        }
        this.r.onViewPositionChanged(this.s, i5, i6, i5 - left, i6 - top);
    }

    public boolean a(View view, int i, int i2) {
        return view != null && i >= view.getLeft() && i < view.getRight() && i2 >= view.getTop() && i2 < view.getBottom();
    }
}
