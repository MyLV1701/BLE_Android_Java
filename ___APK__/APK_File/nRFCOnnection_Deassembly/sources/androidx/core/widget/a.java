package androidx.core.widget;

import a.f.l.w;
import android.content.res.Resources;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;

/* loaded from: classes.dex */
public abstract class a implements View.OnTouchListener {
    private static final int s = ViewConfiguration.getTapTimeout();

    /* renamed from: d, reason: collision with root package name */
    final View f1304d;

    /* renamed from: e, reason: collision with root package name */
    private Runnable f1305e;
    private int h;
    private int i;
    private boolean m;
    boolean n;
    boolean o;
    boolean p;
    private boolean q;
    private boolean r;

    /* renamed from: b, reason: collision with root package name */
    final C0050a f1302b = new C0050a();

    /* renamed from: c, reason: collision with root package name */
    private final Interpolator f1303c = new AccelerateInterpolator();

    /* renamed from: f, reason: collision with root package name */
    private float[] f1306f = {0.0f, 0.0f};
    private float[] g = {Float.MAX_VALUE, Float.MAX_VALUE};
    private float[] j = {0.0f, 0.0f};
    private float[] k = {0.0f, 0.0f};
    private float[] l = {Float.MAX_VALUE, Float.MAX_VALUE};

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: androidx.core.widget.a$a, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static class C0050a {

        /* renamed from: a, reason: collision with root package name */
        private int f1307a;

        /* renamed from: b, reason: collision with root package name */
        private int f1308b;

        /* renamed from: c, reason: collision with root package name */
        private float f1309c;

        /* renamed from: d, reason: collision with root package name */
        private float f1310d;
        private float j;
        private int k;

        /* renamed from: e, reason: collision with root package name */
        private long f1311e = Long.MIN_VALUE;
        private long i = -1;

        /* renamed from: f, reason: collision with root package name */
        private long f1312f = 0;
        private int g = 0;
        private int h = 0;

        C0050a() {
        }

        private float a(float f2) {
            return ((-4.0f) * f2 * f2) + (f2 * 4.0f);
        }

        public void a(int i) {
            this.f1308b = i;
        }

        public void b(int i) {
            this.f1307a = i;
        }

        public int c() {
            return this.h;
        }

        public int d() {
            float f2 = this.f1309c;
            return (int) (f2 / Math.abs(f2));
        }

        public int e() {
            float f2 = this.f1310d;
            return (int) (f2 / Math.abs(f2));
        }

        public boolean f() {
            return this.i > 0 && AnimationUtils.currentAnimationTimeMillis() > this.i + ((long) this.k);
        }

        public void g() {
            long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
            this.k = a.a((int) (currentAnimationTimeMillis - this.f1311e), 0, this.f1308b);
            this.j = a(currentAnimationTimeMillis);
            this.i = currentAnimationTimeMillis;
        }

        public void h() {
            this.f1311e = AnimationUtils.currentAnimationTimeMillis();
            this.i = -1L;
            this.f1312f = this.f1311e;
            this.j = 0.5f;
            this.g = 0;
            this.h = 0;
        }

        private float a(long j) {
            if (j < this.f1311e) {
                return 0.0f;
            }
            long j2 = this.i;
            if (j2 >= 0 && j >= j2) {
                long j3 = j - j2;
                float f2 = this.j;
                return (1.0f - f2) + (f2 * a.a(((float) j3) / this.k, 0.0f, 1.0f));
            }
            return a.a(((float) (j - this.f1311e)) / this.f1307a, 0.0f, 1.0f) * 0.5f;
        }

        public int b() {
            return this.g;
        }

        public void a() {
            if (this.f1312f != 0) {
                long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
                float a2 = a(a(currentAnimationTimeMillis));
                long j = currentAnimationTimeMillis - this.f1312f;
                this.f1312f = currentAnimationTimeMillis;
                float f2 = ((float) j) * a2;
                this.g = (int) (this.f1309c * f2);
                this.h = (int) (f2 * this.f1310d);
                return;
            }
            throw new RuntimeException("Cannot compute scroll delta before calling start()");
        }

        public void a(float f2, float f3) {
            this.f1309c = f2;
            this.f1310d = f3;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            a aVar = a.this;
            if (aVar.p) {
                if (aVar.n) {
                    aVar.n = false;
                    aVar.f1302b.h();
                }
                C0050a c0050a = a.this.f1302b;
                if (!c0050a.f() && a.this.b()) {
                    a aVar2 = a.this;
                    if (aVar2.o) {
                        aVar2.o = false;
                        aVar2.a();
                    }
                    c0050a.a();
                    a.this.a(c0050a.b(), c0050a.c());
                    w.a(a.this.f1304d, this);
                    return;
                }
                a.this.p = false;
            }
        }
    }

    public a(View view) {
        this.f1304d = view;
        float f2 = Resources.getSystem().getDisplayMetrics().density;
        float f3 = (int) ((1575.0f * f2) + 0.5f);
        b(f3, f3);
        float f4 = (int) ((f2 * 315.0f) + 0.5f);
        c(f4, f4);
        d(1);
        a(Float.MAX_VALUE, Float.MAX_VALUE);
        d(0.2f, 0.2f);
        e(1.0f, 1.0f);
        c(s);
        f(500);
        e(500);
    }

    static float a(float f2, float f3, float f4) {
        return f2 > f4 ? f4 : f2 < f3 ? f3 : f2;
    }

    static int a(int i, int i2, int i3) {
        return i > i3 ? i3 : i < i2 ? i2 : i;
    }

    public a a(boolean z) {
        if (this.q && !z) {
            c();
        }
        this.q = z;
        return this;
    }

    public abstract void a(int i, int i2);

    public abstract boolean a(int i);

    public a b(float f2, float f3) {
        float[] fArr = this.l;
        fArr[0] = f2 / 1000.0f;
        fArr[1] = f3 / 1000.0f;
        return this;
    }

    public abstract boolean b(int i);

    public a c(float f2, float f3) {
        float[] fArr = this.k;
        fArr[0] = f2 / 1000.0f;
        fArr[1] = f3 / 1000.0f;
        return this;
    }

    public a d(int i) {
        this.h = i;
        return this;
    }

    public a e(float f2, float f3) {
        float[] fArr = this.j;
        fArr[0] = f2 / 1000.0f;
        fArr[1] = f3 / 1000.0f;
        return this;
    }

    public a f(int i) {
        this.f1302b.b(i);
        return this;
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0013, code lost:
    
        if (r0 != 3) goto L20;
     */
    @Override // android.view.View.OnTouchListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onTouch(android.view.View r6, android.view.MotionEvent r7) {
        /*
            r5 = this;
            boolean r0 = r5.q
            r1 = 0
            if (r0 != 0) goto L6
            return r1
        L6:
            int r0 = r7.getActionMasked()
            r2 = 1
            if (r0 == 0) goto L1a
            if (r0 == r2) goto L16
            r3 = 2
            if (r0 == r3) goto L1e
            r6 = 3
            if (r0 == r6) goto L16
            goto L58
        L16:
            r5.c()
            goto L58
        L1a:
            r5.o = r2
            r5.m = r1
        L1e:
            float r0 = r7.getX()
            int r3 = r6.getWidth()
            float r3 = (float) r3
            android.view.View r4 = r5.f1304d
            int r4 = r4.getWidth()
            float r4 = (float) r4
            float r0 = r5.a(r1, r0, r3, r4)
            float r7 = r7.getY()
            int r6 = r6.getHeight()
            float r6 = (float) r6
            android.view.View r3 = r5.f1304d
            int r3 = r3.getHeight()
            float r3 = (float) r3
            float r6 = r5.a(r2, r7, r6, r3)
            androidx.core.widget.a$a r7 = r5.f1302b
            r7.a(r0, r6)
            boolean r6 = r5.p
            if (r6 != 0) goto L58
            boolean r6 = r5.b()
            if (r6 == 0) goto L58
            r5.d()
        L58:
            boolean r6 = r5.r
            if (r6 == 0) goto L61
            boolean r6 = r5.p
            if (r6 == 0) goto L61
            r1 = 1
        L61:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.widget.a.onTouch(android.view.View, android.view.MotionEvent):boolean");
    }

    private float f(float f2, float f3) {
        if (f3 == 0.0f) {
            return 0.0f;
        }
        int i = this.h;
        if (i == 0 || i == 1) {
            if (f2 < f3) {
                if (f2 >= 0.0f) {
                    return 1.0f - (f2 / f3);
                }
                if (this.p && this.h == 1) {
                    return 1.0f;
                }
            }
        } else if (i == 2 && f2 < 0.0f) {
            return f2 / (-f3);
        }
        return 0.0f;
    }

    public a d(float f2, float f3) {
        float[] fArr = this.f1306f;
        fArr[0] = f2;
        fArr[1] = f3;
        return this;
    }

    boolean b() {
        C0050a c0050a = this.f1302b;
        int e2 = c0050a.e();
        int d2 = c0050a.d();
        return (e2 != 0 && b(e2)) || (d2 != 0 && a(d2));
    }

    public a c(int i) {
        this.i = i;
        return this;
    }

    public a e(int i) {
        this.f1302b.a(i);
        return this;
    }

    private void c() {
        if (this.n) {
            this.p = false;
        } else {
            this.f1302b.g();
        }
    }

    private void d() {
        int i;
        if (this.f1305e == null) {
            this.f1305e = new b();
        }
        this.p = true;
        this.n = true;
        if (!this.m && (i = this.i) > 0) {
            w.a(this.f1304d, this.f1305e, i);
        } else {
            this.f1305e.run();
        }
        this.m = true;
    }

    public a a(float f2, float f3) {
        float[] fArr = this.g;
        fArr[0] = f2;
        fArr[1] = f3;
        return this;
    }

    private float a(int i, float f2, float f3, float f4) {
        float a2 = a(this.f1306f[i], f3, this.g[i], f2);
        if (a2 == 0.0f) {
            return 0.0f;
        }
        float f5 = this.j[i];
        float f6 = this.k[i];
        float f7 = this.l[i];
        float f8 = f5 * f4;
        if (a2 > 0.0f) {
            return a(a2 * f8, f6, f7);
        }
        return -a((-a2) * f8, f6, f7);
    }

    private float a(float f2, float f3, float f4, float f5) {
        float interpolation;
        float a2 = a(f2 * f3, 0.0f, f4);
        float f6 = f(f3 - f5, a2) - f(f5, a2);
        if (f6 < 0.0f) {
            interpolation = -this.f1303c.getInterpolation(-f6);
        } else {
            if (f6 <= 0.0f) {
                return 0.0f;
            }
            interpolation = this.f1303c.getInterpolation(f6);
        }
        return a(interpolation, -1.0f, 1.0f);
    }

    void a() {
        long uptimeMillis = SystemClock.uptimeMillis();
        MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
        this.f1304d.onTouchEvent(obtain);
        obtain.recycle();
    }
}
