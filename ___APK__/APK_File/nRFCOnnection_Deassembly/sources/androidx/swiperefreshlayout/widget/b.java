package androidx.swiperefreshlayout.widget;

import a.f.k.h;
import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

/* loaded from: classes.dex */
public class b extends Drawable implements Animatable {
    private static final Interpolator h = new LinearInterpolator();
    private static final Interpolator i = new a.j.a.a.b();
    private static final int[] j = {-16777216};

    /* renamed from: b, reason: collision with root package name */
    private final c f1972b;

    /* renamed from: c, reason: collision with root package name */
    private float f1973c;

    /* renamed from: d, reason: collision with root package name */
    private Resources f1974d;

    /* renamed from: e, reason: collision with root package name */
    private Animator f1975e;

    /* renamed from: f, reason: collision with root package name */
    float f1976f;
    boolean g;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a implements ValueAnimator.AnimatorUpdateListener {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ c f1977a;

        a(c cVar) {
            this.f1977a = cVar;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            b.this.a(floatValue, this.f1977a);
            b.this.a(floatValue, this.f1977a, false);
            b.this.invalidateSelf();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: androidx.swiperefreshlayout.widget.b$b, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public class C0063b implements Animator.AnimatorListener {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ c f1979a;

        C0063b(c cVar) {
            this.f1979a = cVar;
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
            b.this.a(1.0f, this.f1979a, true);
            this.f1979a.l();
            this.f1979a.j();
            b bVar = b.this;
            if (bVar.g) {
                bVar.g = false;
                animator.cancel();
                animator.setDuration(1332L);
                animator.start();
                this.f1979a.a(false);
                return;
            }
            bVar.f1976f += 1.0f;
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            b.this.f1976f = 0.0f;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class c {
        int[] i;
        int j;
        float k;
        float l;
        float m;
        boolean n;
        Path o;
        float q;
        int r;
        int s;
        int u;

        /* renamed from: a, reason: collision with root package name */
        final RectF f1981a = new RectF();

        /* renamed from: b, reason: collision with root package name */
        final Paint f1982b = new Paint();

        /* renamed from: c, reason: collision with root package name */
        final Paint f1983c = new Paint();

        /* renamed from: d, reason: collision with root package name */
        final Paint f1984d = new Paint();

        /* renamed from: e, reason: collision with root package name */
        float f1985e = 0.0f;

        /* renamed from: f, reason: collision with root package name */
        float f1986f = 0.0f;
        float g = 0.0f;
        float h = 5.0f;
        float p = 1.0f;
        int t = 255;

        c() {
            this.f1982b.setStrokeCap(Paint.Cap.SQUARE);
            this.f1982b.setAntiAlias(true);
            this.f1982b.setStyle(Paint.Style.STROKE);
            this.f1983c.setStyle(Paint.Style.FILL);
            this.f1983c.setAntiAlias(true);
            this.f1984d.setColor(0);
        }

        void a(float f2, float f3) {
            this.r = (int) f2;
            this.s = (int) f3;
        }

        void b(int i) {
            this.u = i;
        }

        void c(int i) {
            this.j = i;
            this.u = this.i[this.j];
        }

        int d() {
            return (this.j + 1) % this.i.length;
        }

        void e(float f2) {
            this.f1985e = f2;
        }

        void f(float f2) {
            this.h = f2;
            this.f1982b.setStrokeWidth(f2);
        }

        float g() {
            return this.l;
        }

        float h() {
            return this.m;
        }

        float i() {
            return this.k;
        }

        void j() {
            c(d());
        }

        void k() {
            this.k = 0.0f;
            this.l = 0.0f;
            this.m = 0.0f;
            e(0.0f);
            c(0.0f);
            d(0.0f);
        }

        void l() {
            this.k = this.f1985e;
            this.l = this.f1986f;
            this.m = this.g;
        }

        float b() {
            return this.f1986f;
        }

        void d(float f2) {
            this.g = f2;
        }

        float e() {
            return this.f1985e;
        }

        void a(Canvas canvas, Rect rect) {
            RectF rectF = this.f1981a;
            float f2 = this.q;
            float f3 = (this.h / 2.0f) + f2;
            if (f2 <= 0.0f) {
                f3 = (Math.min(rect.width(), rect.height()) / 2.0f) - Math.max((this.r * this.p) / 2.0f, this.h / 2.0f);
            }
            rectF.set(rect.centerX() - f3, rect.centerY() - f3, rect.centerX() + f3, rect.centerY() + f3);
            float f4 = this.f1985e;
            float f5 = this.g;
            float f6 = (f4 + f5) * 360.0f;
            float f7 = ((this.f1986f + f5) * 360.0f) - f6;
            this.f1982b.setColor(this.u);
            this.f1982b.setAlpha(this.t);
            float f8 = this.h / 2.0f;
            rectF.inset(f8, f8);
            canvas.drawCircle(rectF.centerX(), rectF.centerY(), rectF.width() / 2.0f, this.f1984d);
            float f9 = -f8;
            rectF.inset(f9, f9);
            canvas.drawArc(rectF, f6, f7, false, this.f1982b);
            a(canvas, f6, f7, rectF);
        }

        void b(float f2) {
            this.q = f2;
        }

        int c() {
            return this.i[d()];
        }

        int f() {
            return this.i[this.j];
        }

        void c(float f2) {
            this.f1986f = f2;
        }

        void a(Canvas canvas, float f2, float f3, RectF rectF) {
            if (this.n) {
                Path path = this.o;
                if (path == null) {
                    this.o = new Path();
                    this.o.setFillType(Path.FillType.EVEN_ODD);
                } else {
                    path.reset();
                }
                float min = Math.min(rectF.width(), rectF.height()) / 2.0f;
                float f4 = (this.r * this.p) / 2.0f;
                this.o.moveTo(0.0f, 0.0f);
                this.o.lineTo(this.r * this.p, 0.0f);
                Path path2 = this.o;
                float f5 = this.r;
                float f6 = this.p;
                path2.lineTo((f5 * f6) / 2.0f, this.s * f6);
                this.o.offset((min + rectF.centerX()) - f4, rectF.centerY() + (this.h / 2.0f));
                this.o.close();
                this.f1983c.setColor(this.u);
                this.f1983c.setAlpha(this.t);
                canvas.save();
                canvas.rotate(f2 + f3, rectF.centerX(), rectF.centerY());
                canvas.drawPath(this.o, this.f1983c);
                canvas.restore();
            }
        }

        void a(int[] iArr) {
            this.i = iArr;
            c(0);
        }

        void a(ColorFilter colorFilter) {
            this.f1982b.setColorFilter(colorFilter);
        }

        void a(int i) {
            this.t = i;
        }

        int a() {
            return this.t;
        }

        void a(boolean z) {
            if (this.n != z) {
                this.n = z;
            }
        }

        void a(float f2) {
            if (f2 != this.p) {
                this.p = f2;
            }
        }
    }

    public b(Context context) {
        h.a(context);
        this.f1974d = context.getResources();
        this.f1972b = new c();
        this.f1972b.a(j);
        c(2.5f);
        a();
    }

    private int a(float f2, int i2, int i3) {
        return ((((i2 >> 24) & 255) + ((int) ((((i3 >> 24) & 255) - r0) * f2))) << 24) | ((((i2 >> 16) & 255) + ((int) ((((i3 >> 16) & 255) - r1) * f2))) << 16) | ((((i2 >> 8) & 255) + ((int) ((((i3 >> 8) & 255) - r2) * f2))) << 8) | ((i2 & 255) + ((int) (f2 * ((i3 & 255) - r8))));
    }

    private void a(float f2, float f3, float f4, float f5) {
        c cVar = this.f1972b;
        float f6 = this.f1974d.getDisplayMetrics().density;
        cVar.f(f3 * f6);
        cVar.b(f2 * f6);
        cVar.c(0);
        cVar.a(f4 * f6, f5 * f6);
    }

    private void d(float f2) {
        this.f1973c = f2;
    }

    public void b(float f2) {
        this.f1972b.d(f2);
        invalidateSelf();
    }

    public void c(float f2) {
        this.f1972b.f(f2);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Rect bounds = getBounds();
        canvas.save();
        canvas.rotate(this.f1973c, bounds.exactCenterX(), bounds.exactCenterY());
        this.f1972b.a(canvas, bounds);
        canvas.restore();
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.f1972b.a();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Animatable
    public boolean isRunning() {
        return this.f1975e.isRunning();
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        this.f1972b.a(i2);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.f1972b.a(colorFilter);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Animatable
    public void start() {
        this.f1975e.cancel();
        this.f1972b.l();
        if (this.f1972b.b() != this.f1972b.e()) {
            this.g = true;
            this.f1975e.setDuration(666L);
            this.f1975e.start();
        } else {
            this.f1972b.c(0);
            this.f1972b.k();
            this.f1975e.setDuration(1332L);
            this.f1975e.start();
        }
    }

    @Override // android.graphics.drawable.Animatable
    public void stop() {
        this.f1975e.cancel();
        d(0.0f);
        this.f1972b.a(false);
        this.f1972b.c(0);
        this.f1972b.k();
        invalidateSelf();
    }

    private void b(float f2, c cVar) {
        a(f2, cVar);
        float floor = (float) (Math.floor(cVar.h() / 0.8f) + 1.0d);
        cVar.e(cVar.i() + (((cVar.g() - 0.01f) - cVar.i()) * f2));
        cVar.c(cVar.g());
        cVar.d(cVar.h() + ((floor - cVar.h()) * f2));
    }

    public void a(int i2) {
        if (i2 == 0) {
            a(11.0f, 3.0f, 12.0f, 6.0f);
        } else {
            a(7.5f, 2.5f, 10.0f, 5.0f);
        }
        invalidateSelf();
    }

    public void a(boolean z) {
        this.f1972b.a(z);
        invalidateSelf();
    }

    public void a(float f2) {
        this.f1972b.a(f2);
        invalidateSelf();
    }

    public void a(float f2, float f3) {
        this.f1972b.e(f2);
        this.f1972b.c(f3);
        invalidateSelf();
    }

    public void a(int... iArr) {
        this.f1972b.a(iArr);
        this.f1972b.c(0);
        invalidateSelf();
    }

    void a(float f2, c cVar) {
        if (f2 > 0.75f) {
            cVar.b(a((f2 - 0.75f) / 0.25f, cVar.f(), cVar.c()));
        } else {
            cVar.b(cVar.f());
        }
    }

    void a(float f2, c cVar, boolean z) {
        float i2;
        float interpolation;
        if (this.g) {
            b(f2, cVar);
            return;
        }
        if (f2 != 1.0f || z) {
            float h2 = cVar.h();
            if (f2 < 0.5f) {
                float i3 = cVar.i();
                i2 = (i.getInterpolation(f2 / 0.5f) * 0.79f) + 0.01f + i3;
                interpolation = i3;
            } else {
                i2 = cVar.i() + 0.79f;
                interpolation = i2 - (((1.0f - i.getInterpolation((f2 - 0.5f) / 0.5f)) * 0.79f) + 0.01f);
            }
            float f3 = h2 + (0.20999998f * f2);
            float f4 = (f2 + this.f1976f) * 216.0f;
            cVar.e(interpolation);
            cVar.c(i2);
            cVar.d(f3);
            d(f4);
        }
    }

    private void a() {
        c cVar = this.f1972b;
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        ofFloat.addUpdateListener(new a(cVar));
        ofFloat.setRepeatCount(-1);
        ofFloat.setRepeatMode(1);
        ofFloat.setInterpolator(h);
        ofFloat.addListener(new C0063b(cVar));
        this.f1975e = ofFloat;
    }
}
