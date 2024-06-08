package androidx.recyclerview.widget;

import android.R;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.view.MotionEvent;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class h extends RecyclerView.n implements RecyclerView.s {
    private static final int[] D = {R.attr.state_pressed};
    private static final int[] E = new int[0];

    /* renamed from: a, reason: collision with root package name */
    private final int f1861a;

    /* renamed from: b, reason: collision with root package name */
    private final int f1862b;

    /* renamed from: c, reason: collision with root package name */
    final StateListDrawable f1863c;

    /* renamed from: d, reason: collision with root package name */
    final Drawable f1864d;

    /* renamed from: e, reason: collision with root package name */
    private final int f1865e;

    /* renamed from: f, reason: collision with root package name */
    private final int f1866f;
    private final StateListDrawable g;
    private final Drawable h;
    private final int i;
    private final int j;
    int k;
    int l;
    float m;
    int n;
    int o;
    float p;
    private RecyclerView s;
    private int q = 0;
    private int r = 0;
    private boolean t = false;
    private boolean u = false;
    private int v = 0;
    private int w = 0;
    private final int[] x = new int[2];
    private final int[] y = new int[2];
    final ValueAnimator z = ValueAnimator.ofFloat(0.0f, 1.0f);
    int A = 0;
    private final Runnable B = new a();
    private final RecyclerView.t C = new b();

    /* loaded from: classes.dex */
    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            h.this.a(500);
        }
    }

    /* loaded from: classes.dex */
    class b extends RecyclerView.t {
        b() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.t
        public void onScrolled(RecyclerView recyclerView, int i, int i2) {
            h.this.a(recyclerView.computeHorizontalScrollOffset(), recyclerView.computeVerticalScrollOffset());
        }
    }

    /* loaded from: classes.dex */
    private class c extends AnimatorListenerAdapter {

        /* renamed from: a, reason: collision with root package name */
        private boolean f1869a = false;

        c() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f1869a = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            if (this.f1869a) {
                this.f1869a = false;
                return;
            }
            if (((Float) h.this.z.getAnimatedValue()).floatValue() == 0.0f) {
                h hVar = h.this;
                hVar.A = 0;
                hVar.b(0);
            } else {
                h hVar2 = h.this;
                hVar2.A = 2;
                hVar2.a();
            }
        }
    }

    /* loaded from: classes.dex */
    private class d implements ValueAnimator.AnimatorUpdateListener {
        d() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            int floatValue = (int) (((Float) valueAnimator.getAnimatedValue()).floatValue() * 255.0f);
            h.this.f1863c.setAlpha(floatValue);
            h.this.f1864d.setAlpha(floatValue);
            h.this.a();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public h(RecyclerView recyclerView, StateListDrawable stateListDrawable, Drawable drawable, StateListDrawable stateListDrawable2, Drawable drawable2, int i, int i2, int i3) {
        this.f1863c = stateListDrawable;
        this.f1864d = drawable;
        this.g = stateListDrawable2;
        this.h = drawable2;
        this.f1865e = Math.max(i, stateListDrawable.getIntrinsicWidth());
        this.f1866f = Math.max(i, drawable.getIntrinsicWidth());
        this.i = Math.max(i, stateListDrawable2.getIntrinsicWidth());
        this.j = Math.max(i, drawable2.getIntrinsicWidth());
        this.f1861a = i2;
        this.f1862b = i3;
        this.f1863c.setAlpha(255);
        this.f1864d.setAlpha(255);
        this.z.addListener(new c());
        this.z.addUpdateListener(new d());
        a(recyclerView);
    }

    private void c() {
        this.s.removeCallbacks(this.B);
    }

    private void d() {
        this.s.removeItemDecoration(this);
        this.s.removeOnItemTouchListener(this);
        this.s.removeOnScrollListener(this.C);
        c();
    }

    private int[] e() {
        int[] iArr = this.y;
        int i = this.f1862b;
        iArr[0] = i;
        iArr[1] = this.q - i;
        return iArr;
    }

    private int[] f() {
        int[] iArr = this.x;
        int i = this.f1862b;
        iArr[0] = i;
        iArr[1] = this.r - i;
        return iArr;
    }

    private boolean g() {
        return a.f.l.w.q(this.s) == 1;
    }

    private void h() {
        this.s.addItemDecoration(this);
        this.s.addOnItemTouchListener(this);
        this.s.addOnScrollListener(this.C);
    }

    public void a(RecyclerView recyclerView) {
        RecyclerView recyclerView2 = this.s;
        if (recyclerView2 == recyclerView) {
            return;
        }
        if (recyclerView2 != null) {
            d();
        }
        this.s = recyclerView;
        if (this.s != null) {
            h();
        }
    }

    void b(int i) {
        if (i == 2 && this.v != 2) {
            this.f1863c.setState(D);
            c();
        }
        if (i == 0) {
            a();
        } else {
            b();
        }
        if (this.v == 2 && i != 2) {
            this.f1863c.setState(E);
            c(1200);
        } else if (i == 1) {
            c(1500);
        }
        this.v = i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.s
    public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
        int i = this.v;
        if (i == 1) {
            boolean b2 = b(motionEvent.getX(), motionEvent.getY());
            boolean a2 = a(motionEvent.getX(), motionEvent.getY());
            if (motionEvent.getAction() != 0) {
                return false;
            }
            if (!b2 && !a2) {
                return false;
            }
            if (a2) {
                this.w = 1;
                this.p = (int) motionEvent.getX();
            } else if (b2) {
                this.w = 2;
                this.m = (int) motionEvent.getY();
            }
            b(2);
        } else if (i != 2) {
            return false;
        }
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.s
    public void onRequestDisallowInterceptTouchEvent(boolean z) {
    }

    @Override // androidx.recyclerview.widget.RecyclerView.s
    public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
        if (this.v == 0) {
            return;
        }
        if (motionEvent.getAction() == 0) {
            boolean b2 = b(motionEvent.getX(), motionEvent.getY());
            boolean a2 = a(motionEvent.getX(), motionEvent.getY());
            if (b2 || a2) {
                if (a2) {
                    this.w = 1;
                    this.p = (int) motionEvent.getX();
                } else if (b2) {
                    this.w = 2;
                    this.m = (int) motionEvent.getY();
                }
                b(2);
                return;
            }
            return;
        }
        if (motionEvent.getAction() == 1 && this.v == 2) {
            this.m = 0.0f;
            this.p = 0.0f;
            b(1);
            this.w = 0;
            return;
        }
        if (motionEvent.getAction() == 2 && this.v == 2) {
            b();
            if (this.w == 1) {
                a(motionEvent.getX());
            }
            if (this.w == 2) {
                b(motionEvent.getY());
            }
        }
    }

    private void c(int i) {
        c();
        this.s.postDelayed(this.B, i);
    }

    void a() {
        this.s.invalidate();
    }

    void a(int i) {
        int i2 = this.A;
        if (i2 == 1) {
            this.z.cancel();
        } else if (i2 != 2) {
            return;
        }
        this.A = 3;
        ValueAnimator valueAnimator = this.z;
        valueAnimator.setFloatValues(((Float) valueAnimator.getAnimatedValue()).floatValue(), 0.0f);
        this.z.setDuration(i);
        this.z.start();
    }

    public void b() {
        int i = this.A;
        if (i != 0) {
            if (i != 3) {
                return;
            } else {
                this.z.cancel();
            }
        }
        this.A = 1;
        ValueAnimator valueAnimator = this.z;
        valueAnimator.setFloatValues(((Float) valueAnimator.getAnimatedValue()).floatValue(), 1.0f);
        this.z.setDuration(500L);
        this.z.setStartDelay(0L);
        this.z.start();
    }

    private void a(Canvas canvas) {
        int i = this.r;
        int i2 = this.i;
        int i3 = this.o;
        int i4 = this.n;
        this.g.setBounds(0, 0, i4, i2);
        this.h.setBounds(0, 0, this.q, this.j);
        canvas.translate(0.0f, i - i2);
        this.h.draw(canvas);
        canvas.translate(i3 - (i4 / 2), 0.0f);
        this.g.draw(canvas);
        canvas.translate(-r2, -r0);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.n
    public void b(Canvas canvas, RecyclerView recyclerView, RecyclerView.a0 a0Var) {
        if (this.q == this.s.getWidth() && this.r == this.s.getHeight()) {
            if (this.A != 0) {
                if (this.t) {
                    b(canvas);
                }
                if (this.u) {
                    a(canvas);
                    return;
                }
                return;
            }
            return;
        }
        this.q = this.s.getWidth();
        this.r = this.s.getHeight();
        b(0);
    }

    void a(int i, int i2) {
        int computeVerticalScrollRange = this.s.computeVerticalScrollRange();
        int i3 = this.r;
        this.t = computeVerticalScrollRange - i3 > 0 && i3 >= this.f1861a;
        int computeHorizontalScrollRange = this.s.computeHorizontalScrollRange();
        int i4 = this.q;
        this.u = computeHorizontalScrollRange - i4 > 0 && i4 >= this.f1861a;
        if (!this.t && !this.u) {
            if (this.v != 0) {
                b(0);
                return;
            }
            return;
        }
        if (this.t) {
            float f2 = i3;
            this.l = (int) ((f2 * (i2 + (f2 / 2.0f))) / computeVerticalScrollRange);
            this.k = Math.min(i3, (i3 * i3) / computeVerticalScrollRange);
        }
        if (this.u) {
            float f3 = i4;
            this.o = (int) ((f3 * (i + (f3 / 2.0f))) / computeHorizontalScrollRange);
            this.n = Math.min(i4, (i4 * i4) / computeHorizontalScrollRange);
        }
        int i5 = this.v;
        if (i5 == 0 || i5 == 1) {
            b(1);
        }
    }

    private void b(Canvas canvas) {
        int i = this.q;
        int i2 = this.f1865e;
        int i3 = i - i2;
        int i4 = this.l;
        int i5 = this.k;
        int i6 = i4 - (i5 / 2);
        this.f1863c.setBounds(0, 0, i2, i5);
        this.f1864d.setBounds(0, 0, this.f1866f, this.r);
        if (g()) {
            this.f1864d.draw(canvas);
            canvas.translate(this.f1865e, i6);
            canvas.scale(-1.0f, 1.0f);
            this.f1863c.draw(canvas);
            canvas.scale(1.0f, 1.0f);
            canvas.translate(-this.f1865e, -i6);
            return;
        }
        canvas.translate(i3, 0.0f);
        this.f1864d.draw(canvas);
        canvas.translate(0.0f, i6);
        this.f1863c.draw(canvas);
        canvas.translate(-i3, -i6);
    }

    private void a(float f2) {
        int[] e2 = e();
        float max = Math.max(e2[0], Math.min(e2[1], f2));
        if (Math.abs(this.o - max) < 2.0f) {
            return;
        }
        int a2 = a(this.p, max, e2, this.s.computeHorizontalScrollRange(), this.s.computeHorizontalScrollOffset(), this.q);
        if (a2 != 0) {
            this.s.scrollBy(a2, 0);
        }
        this.p = max;
    }

    private void b(float f2) {
        int[] f3 = f();
        float max = Math.max(f3[0], Math.min(f3[1], f2));
        if (Math.abs(this.l - max) < 2.0f) {
            return;
        }
        int a2 = a(this.m, max, f3, this.s.computeVerticalScrollRange(), this.s.computeVerticalScrollOffset(), this.r);
        if (a2 != 0) {
            this.s.scrollBy(0, a2);
        }
        this.m = max;
    }

    private int a(float f2, float f3, int[] iArr, int i, int i2, int i3) {
        int i4 = iArr[1] - iArr[0];
        if (i4 == 0) {
            return 0;
        }
        int i5 = i - i3;
        int i6 = (int) (((f3 - f2) / i4) * i5);
        int i7 = i2 + i6;
        if (i7 >= i5 || i7 < 0) {
            return 0;
        }
        return i6;
    }

    boolean a(float f2, float f3) {
        if (f3 >= this.r - this.i) {
            int i = this.o;
            int i2 = this.n;
            if (f2 >= i - (i2 / 2) && f2 <= i + (i2 / 2)) {
                return true;
            }
        }
        return false;
    }

    boolean b(float f2, float f3) {
        if (!g() ? f2 >= this.q - this.f1865e : f2 <= this.f1865e / 2) {
            int i = this.l;
            int i2 = this.k;
            if (f3 >= i - (i2 / 2) && f3 <= i + (i2 / 2)) {
                return true;
            }
        }
        return false;
    }
}
