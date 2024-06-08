package androidx.appcompat.widget;

import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewParent;

/* loaded from: classes.dex */
public abstract class d0 implements View.OnTouchListener, View.OnAttachStateChangeListener {

    /* renamed from: b, reason: collision with root package name */
    private final float f959b;

    /* renamed from: c, reason: collision with root package name */
    private final int f960c;

    /* renamed from: d, reason: collision with root package name */
    private final int f961d;

    /* renamed from: e, reason: collision with root package name */
    final View f962e;

    /* renamed from: f, reason: collision with root package name */
    private Runnable f963f;
    private Runnable g;
    private boolean h;
    private int i;
    private final int[] j = new int[2];

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ViewParent parent = d0.this.f962e.getParent();
            if (parent != null) {
                parent.requestDisallowInterceptTouchEvent(true);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            d0.this.d();
        }
    }

    public d0(View view) {
        this.f962e = view;
        view.setLongClickable(true);
        view.addOnAttachStateChangeListener(this);
        this.f959b = ViewConfiguration.get(view.getContext()).getScaledTouchSlop();
        this.f960c = ViewConfiguration.getTapTimeout();
        this.f961d = (this.f960c + ViewConfiguration.getLongPressTimeout()) / 2;
    }

    private boolean a(MotionEvent motionEvent) {
        b0 b0Var;
        View view = this.f962e;
        androidx.appcompat.view.menu.q a2 = a();
        if (a2 == null || !a2.e() || (b0Var = (b0) a2.f()) == null || !b0Var.isShown()) {
            return false;
        }
        MotionEvent obtainNoHistory = MotionEvent.obtainNoHistory(motionEvent);
        a(view, obtainNoHistory);
        b(b0Var, obtainNoHistory);
        boolean a3 = b0Var.a(obtainNoHistory, this.i);
        obtainNoHistory.recycle();
        int actionMasked = motionEvent.getActionMasked();
        return a3 && (actionMasked != 1 && actionMasked != 3);
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0017, code lost:
    
        if (r1 != 3) goto L28;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean b(android.view.MotionEvent r6) {
        /*
            r5 = this;
            android.view.View r0 = r5.f962e
            boolean r1 = r0.isEnabled()
            r2 = 0
            if (r1 != 0) goto La
            return r2
        La:
            int r1 = r6.getActionMasked()
            if (r1 == 0) goto L41
            r3 = 1
            if (r1 == r3) goto L3d
            r4 = 2
            if (r1 == r4) goto L1a
            r6 = 3
            if (r1 == r6) goto L3d
            goto L6d
        L1a:
            int r1 = r5.i
            int r1 = r6.findPointerIndex(r1)
            if (r1 < 0) goto L6d
            float r4 = r6.getX(r1)
            float r6 = r6.getY(r1)
            float r1 = r5.f959b
            boolean r6 = a(r0, r4, r6, r1)
            if (r6 != 0) goto L6d
            r5.e()
            android.view.ViewParent r6 = r0.getParent()
            r6.requestDisallowInterceptTouchEvent(r3)
            return r3
        L3d:
            r5.e()
            goto L6d
        L41:
            int r6 = r6.getPointerId(r2)
            r5.i = r6
            java.lang.Runnable r6 = r5.f963f
            if (r6 != 0) goto L52
            androidx.appcompat.widget.d0$a r6 = new androidx.appcompat.widget.d0$a
            r6.<init>()
            r5.f963f = r6
        L52:
            java.lang.Runnable r6 = r5.f963f
            int r1 = r5.f960c
            long r3 = (long) r1
            r0.postDelayed(r6, r3)
            java.lang.Runnable r6 = r5.g
            if (r6 != 0) goto L65
            androidx.appcompat.widget.d0$b r6 = new androidx.appcompat.widget.d0$b
            r6.<init>()
            r5.g = r6
        L65:
            java.lang.Runnable r6 = r5.g
            int r1 = r5.f961d
            long r3 = (long) r1
            r0.postDelayed(r6, r3)
        L6d:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.d0.b(android.view.MotionEvent):boolean");
    }

    private void e() {
        Runnable runnable = this.g;
        if (runnable != null) {
            this.f962e.removeCallbacks(runnable);
        }
        Runnable runnable2 = this.f963f;
        if (runnable2 != null) {
            this.f962e.removeCallbacks(runnable2);
        }
    }

    public abstract androidx.appcompat.view.menu.q a();

    protected abstract boolean b();

    protected boolean c() {
        androidx.appcompat.view.menu.q a2 = a();
        if (a2 == null || !a2.e()) {
            return true;
        }
        a2.dismiss();
        return true;
    }

    void d() {
        e();
        View view = this.f962e;
        if (view.isEnabled() && !view.isLongClickable() && b()) {
            view.getParent().requestDisallowInterceptTouchEvent(true);
            long uptimeMillis = SystemClock.uptimeMillis();
            MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
            view.onTouchEvent(obtain);
            obtain.recycle();
            this.h = true;
        }
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        boolean z;
        boolean z2 = this.h;
        if (z2) {
            z = a(motionEvent) || !c();
        } else {
            z = b(motionEvent) && b();
            if (z) {
                long uptimeMillis = SystemClock.uptimeMillis();
                MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
                this.f962e.onTouchEvent(obtain);
                obtain.recycle();
            }
        }
        this.h = z;
        return z || z2;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewAttachedToWindow(View view) {
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewDetachedFromWindow(View view) {
        this.h = false;
        this.i = -1;
        Runnable runnable = this.f963f;
        if (runnable != null) {
            this.f962e.removeCallbacks(runnable);
        }
    }

    private static boolean a(View view, float f2, float f3, float f4) {
        float f5 = -f4;
        return f2 >= f5 && f3 >= f5 && f2 < ((float) (view.getRight() - view.getLeft())) + f4 && f3 < ((float) (view.getBottom() - view.getTop())) + f4;
    }

    private boolean a(View view, MotionEvent motionEvent) {
        view.getLocationOnScreen(this.j);
        motionEvent.offsetLocation(r0[0], r0[1]);
        return true;
    }

    private boolean b(View view, MotionEvent motionEvent) {
        view.getLocationOnScreen(this.j);
        motionEvent.offsetLocation(-r0[0], -r0[1]);
        return true;
    }
}
