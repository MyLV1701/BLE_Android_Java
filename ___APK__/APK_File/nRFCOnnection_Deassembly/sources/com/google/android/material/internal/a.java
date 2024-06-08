package com.google.android.material.internal;

import a.f.l.w;
import android.animation.TimeInterpolator;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Build;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.View;
import c.a.a.a.y.a;
import no.nordicsemi.android.ble.error.GattError;

/* loaded from: classes.dex */
public final class a {
    private static final boolean V;
    private static final Paint W;
    private boolean A;
    private Bitmap B;
    private Paint C;
    private float D;
    private float E;
    private float F;
    private float G;
    private int[] H;
    private boolean I;
    private TimeInterpolator L;
    private TimeInterpolator M;
    private float N;
    private float O;
    private float P;
    private ColorStateList Q;
    private float R;
    private float S;
    private float T;
    private ColorStateList U;

    /* renamed from: a, reason: collision with root package name */
    private final View f2571a;

    /* renamed from: b, reason: collision with root package name */
    private boolean f2572b;

    /* renamed from: c, reason: collision with root package name */
    private float f2573c;
    private ColorStateList k;
    private ColorStateList l;
    private float m;
    private float n;
    private float o;
    private float p;
    private float q;
    private float r;
    private Typeface s;
    private Typeface t;
    private Typeface u;
    private c.a.a.a.y.a v;
    private c.a.a.a.y.a w;
    private CharSequence x;
    private CharSequence y;
    private boolean z;
    private int g = 16;
    private int h = 16;
    private float i = 15.0f;
    private float j = 15.0f;
    private final TextPaint J = new TextPaint(GattError.GATT_INTERNAL_ERROR);
    private final TextPaint K = new TextPaint(this.J);

    /* renamed from: e, reason: collision with root package name */
    private final Rect f2575e = new Rect();

    /* renamed from: d, reason: collision with root package name */
    private final Rect f2574d = new Rect();

    /* renamed from: f, reason: collision with root package name */
    private final RectF f2576f = new RectF();

    /* renamed from: com.google.android.material.internal.a$a, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    class C0086a implements a.InterfaceC0071a {
        C0086a() {
        }

        @Override // c.a.a.a.y.a.InterfaceC0071a
        public void a(Typeface typeface) {
            a.this.a(typeface);
        }
    }

    static {
        V = Build.VERSION.SDK_INT < 18;
        W = null;
        Paint paint = W;
        if (paint != null) {
            paint.setAntiAlias(true);
            W.setColor(-65281);
        }
    }

    public a(View view) {
        this.f2571a = view;
    }

    private boolean d(Typeface typeface) {
        c.a.a.a.y.a aVar = this.v;
        if (aVar != null) {
            aVar.a();
        }
        if (this.t == typeface) {
            return false;
        }
        this.t = typeface;
        return true;
    }

    private void j() {
        float f2 = this.G;
        d(this.j);
        CharSequence charSequence = this.y;
        float measureText = charSequence != null ? this.J.measureText(charSequence, 0, charSequence.length()) : 0.0f;
        int a2 = a.f.l.d.a(this.h, this.z ? 1 : 0);
        int i = a2 & 112;
        if (i == 48) {
            this.n = this.f2575e.top - this.J.ascent();
        } else if (i != 80) {
            this.n = this.f2575e.centerY() + (((this.J.descent() - this.J.ascent()) / 2.0f) - this.J.descent());
        } else {
            this.n = this.f2575e.bottom;
        }
        int i2 = a2 & 8388615;
        if (i2 == 1) {
            this.p = this.f2575e.centerX() - (measureText / 2.0f);
        } else if (i2 != 5) {
            this.p = this.f2575e.left;
        } else {
            this.p = this.f2575e.right - measureText;
        }
        d(this.i);
        CharSequence charSequence2 = this.y;
        float measureText2 = charSequence2 != null ? this.J.measureText(charSequence2, 0, charSequence2.length()) : 0.0f;
        int a3 = a.f.l.d.a(this.g, this.z ? 1 : 0);
        int i3 = a3 & 112;
        if (i3 == 48) {
            this.m = this.f2574d.top - this.J.ascent();
        } else if (i3 != 80) {
            this.m = this.f2574d.centerY() + (((this.J.descent() - this.J.ascent()) / 2.0f) - this.J.descent());
        } else {
            this.m = this.f2574d.bottom;
        }
        int i4 = a3 & 8388615;
        if (i4 == 1) {
            this.o = this.f2574d.centerX() - (measureText2 / 2.0f);
        } else if (i4 != 5) {
            this.o = this.f2574d.left;
        } else {
            this.o = this.f2574d.right - measureText2;
        }
        l();
        f(f2);
    }

    private void k() {
        c(this.f2573c);
    }

    private void l() {
        Bitmap bitmap = this.B;
        if (bitmap != null) {
            bitmap.recycle();
            this.B = null;
        }
    }

    private void m() {
        if (this.B != null || this.f2574d.isEmpty() || TextUtils.isEmpty(this.y)) {
            return;
        }
        c(0.0f);
        this.D = this.J.ascent();
        this.E = this.J.descent();
        TextPaint textPaint = this.J;
        CharSequence charSequence = this.y;
        int round = Math.round(textPaint.measureText(charSequence, 0, charSequence.length()));
        int round2 = Math.round(this.E - this.D);
        if (round <= 0 || round2 <= 0) {
            return;
        }
        this.B = Bitmap.createBitmap(round, round2, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(this.B);
        CharSequence charSequence2 = this.y;
        canvas.drawText(charSequence2, 0, charSequence2.length(), 0.0f, round2 - this.J.descent(), this.J);
        if (this.C == null) {
            this.C = new Paint(3);
        }
    }

    private int n() {
        return c(this.k);
    }

    public void a(TimeInterpolator timeInterpolator) {
        this.L = timeInterpolator;
        i();
    }

    public void b(TimeInterpolator timeInterpolator) {
        this.M = timeInterpolator;
        i();
    }

    public float c() {
        a(this.K);
        return -this.K.ascent();
    }

    public float e() {
        b(this.K);
        return -this.K.ascent();
    }

    public float f() {
        return this.f2573c;
    }

    public final boolean g() {
        ColorStateList colorStateList;
        ColorStateList colorStateList2 = this.l;
        return (colorStateList2 != null && colorStateList2.isStateful()) || ((colorStateList = this.k) != null && colorStateList.isStateful());
    }

    void h() {
        this.f2572b = this.f2575e.width() > 0 && this.f2575e.height() > 0 && this.f2574d.width() > 0 && this.f2574d.height() > 0;
    }

    public void i() {
        if (this.f2571a.getHeight() <= 0 || this.f2571a.getWidth() <= 0) {
            return;
        }
        j();
        k();
    }

    private void f(float f2) {
        d(f2);
        this.A = V && this.F != 1.0f;
        if (this.A) {
            m();
        }
        w.K(this.f2571a);
    }

    private void e(float f2) {
        this.f2576f.left = a(this.f2574d.left, this.f2575e.left, f2, this.L);
        this.f2576f.top = a(this.m, this.n, f2, this.L);
        this.f2576f.right = a(this.f2574d.right, this.f2575e.right, f2, this.L);
        this.f2576f.bottom = a(this.f2574d.bottom, this.f2575e.bottom, f2, this.L);
    }

    public void a(float f2) {
        if (this.i != f2) {
            this.i = f2;
            i();
        }
    }

    public void b(ColorStateList colorStateList) {
        if (this.k != colorStateList) {
            this.k = colorStateList;
            i();
        }
    }

    public void c(int i) {
        if (this.g != i) {
            this.g = i;
            i();
        }
    }

    public int d() {
        return c(this.l);
    }

    private boolean c(Typeface typeface) {
        c.a.a.a.y.a aVar = this.w;
        if (aVar != null) {
            aVar.a();
        }
        if (this.s == typeface) {
            return false;
        }
        this.s = typeface;
        return true;
    }

    private void d(float f2) {
        float f3;
        boolean z;
        boolean z2;
        if (this.x == null) {
            return;
        }
        float width = this.f2575e.width();
        float width2 = this.f2574d.width();
        if (a(f2, this.j)) {
            float f4 = this.j;
            this.F = 1.0f;
            Typeface typeface = this.u;
            Typeface typeface2 = this.s;
            if (typeface != typeface2) {
                this.u = typeface2;
                z2 = true;
            } else {
                z2 = false;
            }
            f3 = f4;
            z = z2;
        } else {
            f3 = this.i;
            Typeface typeface3 = this.u;
            Typeface typeface4 = this.t;
            if (typeface3 != typeface4) {
                this.u = typeface4;
                z = true;
            } else {
                z = false;
            }
            if (a(f2, this.i)) {
                this.F = 1.0f;
            } else {
                this.F = f2 / this.i;
            }
            float f5 = this.j / this.i;
            width = width2 * f5 > width ? Math.min(width / f5, width2) : width2;
        }
        if (width > 0.0f) {
            z = this.G != f3 || this.I || z;
            this.G = f3;
            this.I = false;
        }
        if (this.y == null || z) {
            this.J.setTextSize(this.G);
            this.J.setTypeface(this.u);
            this.J.setLinearText(this.F != 1.0f);
            CharSequence ellipsize = TextUtils.ellipsize(this.x, this.J, width, TextUtils.TruncateAt.END);
            if (TextUtils.equals(ellipsize, this.y)) {
                return;
            }
            this.y = ellipsize;
            this.z = b(this.y);
        }
    }

    public void a(ColorStateList colorStateList) {
        if (this.l != colorStateList) {
            this.l = colorStateList;
            i();
        }
    }

    public void b(int i, int i2, int i3, int i4) {
        if (a(this.f2574d, i, i2, i3, i4)) {
            return;
        }
        this.f2574d.set(i, i2, i3, i4);
        this.I = true;
        h();
    }

    public void a(int i, int i2, int i3, int i4) {
        if (a(this.f2575e, i, i2, i3, i4)) {
            return;
        }
        this.f2575e.set(i, i2, i3, i4);
        this.I = true;
        h();
    }

    private void c(float f2) {
        e(f2);
        this.q = a(this.o, this.p, f2, this.L);
        this.r = a(this.m, this.n, f2, this.L);
        f(a(this.i, this.j, f2, this.M));
        if (this.l != this.k) {
            this.J.setColor(a(n(), d(), f2));
        } else {
            this.J.setColor(d());
        }
        this.J.setShadowLayer(a(this.R, this.N, f2, (TimeInterpolator) null), a(this.S, this.O, f2, (TimeInterpolator) null), a(this.T, this.P, f2, (TimeInterpolator) null), a(c(this.U), c(this.Q), f2));
        w.K(this.f2571a);
    }

    public void b(Rect rect) {
        b(rect.left, rect.top, rect.right, rect.bottom);
    }

    private float b(RectF rectF, int i, int i2) {
        if ((i2 & 8388613) == 8388613 || (i2 & 5) == 5) {
            return this.z ? rectF.left + a() : this.f2575e.right;
        }
        if (i2 == 17) {
            return (i / 2.0f) + (a() / 2.0f);
        }
        return this.z ? this.f2575e.right : rectF.left + a();
    }

    public void a(Rect rect) {
        a(rect.left, rect.top, rect.right, rect.bottom);
    }

    private void b(TextPaint textPaint) {
        textPaint.setTextSize(this.i);
        textPaint.setTypeface(this.t);
    }

    public void a(RectF rectF, int i, int i2) {
        this.z = b(this.x);
        rectF.left = a(i, i2);
        rectF.top = this.f2575e.top;
        rectF.right = b(rectF, i, i2);
        rectF.bottom = this.f2575e.top + c();
    }

    public void b(int i) {
        if (this.h != i) {
            this.h = i;
            i();
        }
    }

    private float a(int i, int i2) {
        if ((i2 & 8388613) == 8388613 || (i2 & 5) == 5) {
            return this.z ? this.f2575e.left : this.f2575e.right - a();
        }
        if (i2 == 17) {
            return (i / 2.0f) - (a() / 2.0f);
        }
        return this.z ? this.f2575e.right - a() : this.f2575e.left;
    }

    public void b(Typeface typeface) {
        boolean c2 = c(typeface);
        boolean d2 = d(typeface);
        if (c2 || d2) {
            i();
        }
    }

    public float a() {
        if (this.x == null) {
            return 0.0f;
        }
        a(this.K);
        TextPaint textPaint = this.K;
        CharSequence charSequence = this.x;
        return textPaint.measureText(charSequence, 0, charSequence.length());
    }

    public void b(float f2) {
        float a2 = a.f.g.a.a(f2, 0.0f, 1.0f);
        if (a2 != this.f2573c) {
            this.f2573c = a2;
            k();
        }
    }

    private void a(TextPaint textPaint) {
        textPaint.setTextSize(this.j);
        textPaint.setTypeface(this.s);
    }

    private boolean b(CharSequence charSequence) {
        return (w.q(this.f2571a) == 1 ? a.f.j.e.f258d : a.f.j.e.f257c).a(charSequence, 0, charSequence.length());
    }

    public void a(int i) {
        c.a.a.a.y.d dVar = new c.a.a.a.y.d(this.f2571a.getContext(), i);
        ColorStateList colorStateList = dVar.f2152b;
        if (colorStateList != null) {
            this.l = colorStateList;
        }
        float f2 = dVar.f2151a;
        if (f2 != 0.0f) {
            this.j = f2;
        }
        ColorStateList colorStateList2 = dVar.f2156f;
        if (colorStateList2 != null) {
            this.Q = colorStateList2;
        }
        this.O = dVar.g;
        this.P = dVar.h;
        this.N = dVar.i;
        c.a.a.a.y.a aVar = this.w;
        if (aVar != null) {
            aVar.a();
        }
        this.w = new c.a.a.a.y.a(new C0086a(), dVar.a());
        dVar.a(this.f2571a.getContext(), this.w);
        i();
    }

    private int c(ColorStateList colorStateList) {
        if (colorStateList == null) {
            return 0;
        }
        int[] iArr = this.H;
        if (iArr != null) {
            return colorStateList.getColorForState(iArr, 0);
        }
        return colorStateList.getDefaultColor();
    }

    public ColorStateList b() {
        return this.l;
    }

    public void a(Typeface typeface) {
        if (c(typeface)) {
            i();
        }
    }

    public final boolean a(int[] iArr) {
        this.H = iArr;
        if (!g()) {
            return false;
        }
        i();
        return true;
    }

    public void a(Canvas canvas) {
        float ascent;
        int save = canvas.save();
        if (this.y != null && this.f2572b) {
            float f2 = this.q;
            float f3 = this.r;
            boolean z = this.A && this.B != null;
            if (z) {
                ascent = this.D * this.F;
            } else {
                ascent = this.J.ascent() * this.F;
                this.J.descent();
            }
            if (z) {
                f3 += ascent;
            }
            float f4 = f3;
            float f5 = this.F;
            if (f5 != 1.0f) {
                canvas.scale(f5, f5, f2, f4);
            }
            if (z) {
                canvas.drawBitmap(this.B, f2, f4, this.C);
            } else {
                CharSequence charSequence = this.y;
                canvas.drawText(charSequence, 0, charSequence.length(), f2, f4, this.J);
            }
        }
        canvas.restoreToCount(save);
    }

    public void a(CharSequence charSequence) {
        if (charSequence == null || !TextUtils.equals(this.x, charSequence)) {
            this.x = charSequence;
            this.y = null;
            l();
            i();
        }
    }

    private static boolean a(float f2, float f3) {
        return Math.abs(f2 - f3) < 0.001f;
    }

    private static int a(int i, int i2, float f2) {
        float f3 = 1.0f - f2;
        return Color.argb((int) ((Color.alpha(i) * f3) + (Color.alpha(i2) * f2)), (int) ((Color.red(i) * f3) + (Color.red(i2) * f2)), (int) ((Color.green(i) * f3) + (Color.green(i2) * f2)), (int) ((Color.blue(i) * f3) + (Color.blue(i2) * f2)));
    }

    private static float a(float f2, float f3, float f4, TimeInterpolator timeInterpolator) {
        if (timeInterpolator != null) {
            f4 = timeInterpolator.getInterpolation(f4);
        }
        return c.a.a.a.m.a.a(f2, f3, f4);
    }

    private static boolean a(Rect rect, int i, int i2, int i3, int i4) {
        return rect.left == i && rect.top == i2 && rect.right == i3 && rect.bottom == i4;
    }
}
