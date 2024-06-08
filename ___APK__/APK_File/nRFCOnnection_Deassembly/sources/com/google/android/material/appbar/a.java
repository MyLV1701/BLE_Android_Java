package com.google.android.material.appbar;

import a.f.l.w;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.OverScroller;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.preference.Preference;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class a<V extends View> extends c<V> {

    /* renamed from: d, reason: collision with root package name */
    private Runnable f2353d;

    /* renamed from: e, reason: collision with root package name */
    OverScroller f2354e;

    /* renamed from: f, reason: collision with root package name */
    private boolean f2355f;
    private int g;
    private int h;
    private int i;
    private VelocityTracker j;

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.google.android.material.appbar.a$a, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public class RunnableC0080a implements Runnable {

        /* renamed from: b, reason: collision with root package name */
        private final CoordinatorLayout f2356b;

        /* renamed from: c, reason: collision with root package name */
        private final V f2357c;

        RunnableC0080a(CoordinatorLayout coordinatorLayout, V v) {
            this.f2356b = coordinatorLayout;
            this.f2357c = v;
        }

        @Override // java.lang.Runnable
        public void run() {
            OverScroller overScroller;
            if (this.f2357c == null || (overScroller = a.this.f2354e) == null) {
                return;
            }
            if (overScroller.computeScrollOffset()) {
                a aVar = a.this;
                aVar.c(this.f2356b, (CoordinatorLayout) this.f2357c, aVar.f2354e.getCurrY());
                w.a(this.f2357c, this);
                return;
            }
            a.this.e(this.f2356b, this.f2357c);
        }
    }

    public a() {
        this.g = -1;
        this.i = -1;
    }

    private void d() {
        if (this.j == null) {
            this.j = VelocityTracker.obtain();
        }
    }

    abstract boolean a(V v);

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean a(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        int findPointerIndex;
        if (this.i < 0) {
            this.i = ViewConfiguration.get(coordinatorLayout.getContext()).getScaledTouchSlop();
        }
        if (motionEvent.getActionMasked() == 2 && this.f2355f) {
            int i = this.g;
            if (i == -1 || (findPointerIndex = motionEvent.findPointerIndex(i)) == -1) {
                return false;
            }
            int y = (int) motionEvent.getY(findPointerIndex);
            if (Math.abs(y - this.h) > this.i) {
                this.h = y;
                return true;
            }
        }
        if (motionEvent.getActionMasked() == 0) {
            this.g = -1;
            int x = (int) motionEvent.getX();
            int y2 = (int) motionEvent.getY();
            this.f2355f = a((a<V>) v) && coordinatorLayout.a(v, x, y2);
            if (this.f2355f) {
                this.h = y2;
                this.g = motionEvent.getPointerId(0);
                d();
                OverScroller overScroller = this.f2354e;
                if (overScroller != null && !overScroller.isFinished()) {
                    this.f2354e.abortAnimation();
                    return true;
                }
            }
        }
        VelocityTracker velocityTracker = this.j;
        if (velocityTracker != null) {
            velocityTracker.addMovement(motionEvent);
        }
        return false;
    }

    abstract int b(V v);

    abstract int b(CoordinatorLayout coordinatorLayout, V v, int i, int i2, int i3);

    /* JADX WARN: Removed duplicated region for block: B:17:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x008c A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:24:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x007b  */
    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean b(androidx.coordinatorlayout.widget.CoordinatorLayout r12, V r13, android.view.MotionEvent r14) {
        /*
            r11 = this;
            int r0 = r14.getActionMasked()
            r1 = -1
            r2 = 1
            r3 = 0
            if (r0 == r2) goto L4e
            r4 = 2
            if (r0 == r4) goto L2d
            r12 = 3
            if (r0 == r12) goto L72
            r12 = 6
            if (r0 == r12) goto L13
            goto L4c
        L13:
            int r12 = r14.getActionIndex()
            if (r12 != 0) goto L1b
            r12 = 1
            goto L1c
        L1b:
            r12 = 0
        L1c:
            int r13 = r14.getPointerId(r12)
            r11.g = r13
            float r12 = r14.getY(r12)
            r13 = 1056964608(0x3f000000, float:0.5)
            float r12 = r12 + r13
            int r12 = (int) r12
            r11.h = r12
            goto L4c
        L2d:
            int r0 = r11.g
            int r0 = r14.findPointerIndex(r0)
            if (r0 != r1) goto L36
            return r3
        L36:
            float r0 = r14.getY(r0)
            int r0 = (int) r0
            int r1 = r11.h
            int r7 = r1 - r0
            r11.h = r0
            int r8 = r11.b(r13)
            r9 = 0
            r4 = r11
            r5 = r12
            r6 = r13
            r4.a(r5, r6, r7, r8, r9)
        L4c:
            r12 = 0
            goto L81
        L4e:
            android.view.VelocityTracker r0 = r11.j
            if (r0 == 0) goto L72
            r0.addMovement(r14)
            android.view.VelocityTracker r0 = r11.j
            r4 = 1000(0x3e8, float:1.401E-42)
            r0.computeCurrentVelocity(r4)
            android.view.VelocityTracker r0 = r11.j
            int r4 = r11.g
            float r10 = r0.getYVelocity(r4)
            int r0 = r11.c(r13)
            int r8 = -r0
            r9 = 0
            r5 = r11
            r6 = r12
            r7 = r13
            r5.a(r6, r7, r8, r9, r10)
            r12 = 1
            goto L73
        L72:
            r12 = 0
        L73:
            r11.f2355f = r3
            r11.g = r1
            android.view.VelocityTracker r13 = r11.j
            if (r13 == 0) goto L81
            r13.recycle()
            r13 = 0
            r11.j = r13
        L81:
            android.view.VelocityTracker r13 = r11.j
            if (r13 == 0) goto L88
            r13.addMovement(r14)
        L88:
            boolean r13 = r11.f2355f
            if (r13 != 0) goto L90
            if (r12 == 0) goto L8f
            goto L90
        L8f:
            r2 = 0
        L90:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.appbar.a.b(androidx.coordinatorlayout.widget.CoordinatorLayout, android.view.View, android.view.MotionEvent):boolean");
    }

    abstract int c();

    abstract int c(V v);

    /* JADX INFO: Access modifiers changed from: package-private */
    public int c(CoordinatorLayout coordinatorLayout, V v, int i) {
        return b(coordinatorLayout, (CoordinatorLayout) v, i, RecyclerView.UNDEFINED_DURATION, Preference.DEFAULT_ORDER);
    }

    abstract void e(CoordinatorLayout coordinatorLayout, V v);

    public a(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.g = -1;
        this.i = -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int a(CoordinatorLayout coordinatorLayout, V v, int i, int i2, int i3) {
        return b(coordinatorLayout, (CoordinatorLayout) v, c() - i, i2, i3);
    }

    final boolean a(CoordinatorLayout coordinatorLayout, V v, int i, int i2, float f2) {
        Runnable runnable = this.f2353d;
        if (runnable != null) {
            v.removeCallbacks(runnable);
            this.f2353d = null;
        }
        if (this.f2354e == null) {
            this.f2354e = new OverScroller(v.getContext());
        }
        this.f2354e.fling(0, b(), 0, Math.round(f2), 0, 0, i, i2);
        if (this.f2354e.computeScrollOffset()) {
            this.f2353d = new RunnableC0080a(coordinatorLayout, v);
            w.a(v, this.f2353d);
            return true;
        }
        e(coordinatorLayout, v);
        return false;
    }
}
