package com.google.android.material.chip;

import android.R;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.text.TextUtils;
import android.util.AttributeSet;
import androidx.core.graphics.drawable.b;
import androidx.preference.Preference;
import c.a.a.a.b0.g;
import c.a.a.a.m.h;
import c.a.a.a.y.c;
import c.a.a.a.y.d;
import com.google.android.material.internal.k;
import com.google.android.material.internal.l;
import java.lang.ref.WeakReference;
import java.util.Arrays;

/* loaded from: classes.dex */
public class a extends g implements b, Drawable.Callback, k.b {
    private static final int[] G0 = {R.attr.state_enabled};
    private static final ShapeDrawable H0 = new ShapeDrawable(new OvalShape());
    private float A;
    private ColorStateList A0;
    private float B;
    private WeakReference<InterfaceC0081a> B0;
    private ColorStateList C;
    private TextUtils.TruncateAt C0;
    private float D;
    private boolean D0;
    private ColorStateList E;
    private int E0;
    private CharSequence F;
    private boolean F0;
    private boolean G;
    private Drawable H;
    private ColorStateList I;
    private float J;
    private boolean K;
    private Drawable L;
    private Drawable M;
    private ColorStateList N;
    private float O;
    private CharSequence P;
    private boolean Q;
    private boolean R;
    private Drawable S;
    private h T;
    private h U;
    private float V;
    private float W;
    private float X;
    private float Y;
    private float Z;
    private float a0;
    private float b0;
    private float c0;
    private final Context d0;
    private final Paint e0;
    private final Paint f0;
    private final Paint.FontMetrics g0;
    private final RectF h0;
    private final PointF i0;
    private final Path j0;
    private final k k0;
    private int l0;
    private int m0;
    private int n0;
    private int o0;
    private int p0;
    private int q0;
    private boolean r0;
    private int s0;
    private int t0;
    private ColorFilter u0;
    private PorterDuffColorFilter v0;
    private ColorStateList w0;
    private PorterDuff.Mode x0;
    private ColorStateList y;
    private int[] y0;
    private ColorStateList z;
    private boolean z0;

    /* renamed from: com.google.android.material.chip.a$a, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public interface InterfaceC0081a {
        void a();
    }

    private a(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.e0 = new Paint(1);
        this.g0 = new Paint.FontMetrics();
        this.h0 = new RectF();
        this.i0 = new PointF();
        this.j0 = new Path();
        this.t0 = 255;
        this.x0 = PorterDuff.Mode.SRC_IN;
        this.B0 = new WeakReference<>(null);
        a(context);
        this.d0 = context;
        this.k0 = new k(this);
        this.F = "";
        this.k0.b().density = context.getResources().getDisplayMetrics().density;
        this.f0 = null;
        Paint paint = this.f0;
        if (paint != null) {
            paint.setStyle(Paint.Style.STROKE);
        }
        setState(G0);
        a(G0);
        this.D0 = true;
        if (c.a.a.a.z.b.f2166a) {
            H0.setTint(-1);
        }
    }

    public static a a(Context context, AttributeSet attributeSet, int i, int i2) {
        a aVar = new a(context, attributeSet, i, i2);
        aVar.a(attributeSet, i, i2);
        return aVar;
    }

    private void b(Canvas canvas, Rect rect) {
        if (this.F0) {
            return;
        }
        this.e0.setColor(this.m0);
        this.e0.setStyle(Paint.Style.FILL);
        this.e0.setColorFilter(e0());
        this.h0.set(rect);
        canvas.drawRoundRect(this.h0, w(), w(), this.e0);
    }

    private void c(Canvas canvas, Rect rect) {
        if (g0()) {
            a(rect, this.h0);
            RectF rectF = this.h0;
            float f2 = rectF.left;
            float f3 = rectF.top;
            canvas.translate(f2, f3);
            this.H.setBounds(0, 0, (int) this.h0.width(), (int) this.h0.height());
            this.H.draw(canvas);
            canvas.translate(-f2, -f3);
        }
    }

    private float c0() {
        this.k0.b().getFontMetrics(this.g0);
        Paint.FontMetrics fontMetrics = this.g0;
        return (fontMetrics.descent + fontMetrics.ascent) / 2.0f;
    }

    private void d(Canvas canvas, Rect rect) {
        if (this.D <= 0.0f || this.F0) {
            return;
        }
        this.e0.setColor(this.o0);
        this.e0.setStyle(Paint.Style.STROKE);
        if (!this.F0) {
            this.e0.setColorFilter(e0());
        }
        RectF rectF = this.h0;
        float f2 = rect.left;
        float f3 = this.D;
        rectF.set(f2 + (f3 / 2.0f), rect.top + (f3 / 2.0f), rect.right - (f3 / 2.0f), rect.bottom - (f3 / 2.0f));
        float f4 = this.B - (this.D / 2.0f);
        canvas.drawRoundRect(this.h0, f4, f4, this.e0);
    }

    private boolean d0() {
        return this.R && this.S != null && this.Q;
    }

    private void e(Canvas canvas, Rect rect) {
        if (this.F0) {
            return;
        }
        this.e0.setColor(this.l0);
        this.e0.setStyle(Paint.Style.FILL);
        this.h0.set(rect);
        canvas.drawRoundRect(this.h0, w(), w(), this.e0);
    }

    private ColorFilter e0() {
        ColorFilter colorFilter = this.u0;
        return colorFilter != null ? colorFilter : this.v0;
    }

    private boolean f0() {
        return this.R && this.S != null && this.r0;
    }

    private void g(Canvas canvas, Rect rect) {
        this.e0.setColor(this.p0);
        this.e0.setStyle(Paint.Style.FILL);
        this.h0.set(rect);
        if (!this.F0) {
            canvas.drawRoundRect(this.h0, w(), w(), this.e0);
        } else {
            a(new RectF(rect), this.j0);
            super.a(canvas, this.e0, this.j0, d());
        }
    }

    private boolean g0() {
        return this.G && this.H != null;
    }

    private void h(Canvas canvas, Rect rect) {
        Paint paint = this.f0;
        if (paint != null) {
            paint.setColor(a.f.e.a.c(-16777216, 127));
            canvas.drawRect(rect, this.f0);
            if (g0() || f0()) {
                a(rect, this.h0);
                canvas.drawRect(this.h0, this.f0);
            }
            if (this.F != null) {
                canvas.drawLine(rect.left, rect.exactCenterY(), rect.right, rect.exactCenterY(), this.f0);
            }
            if (h0()) {
                c(rect, this.h0);
                canvas.drawRect(this.h0, this.f0);
            }
            this.f0.setColor(a.f.e.a.c(-65536, 127));
            b(rect, this.h0);
            canvas.drawRect(this.h0, this.f0);
            this.f0.setColor(a.f.e.a.c(-16711936, 127));
            d(rect, this.h0);
            canvas.drawRect(this.h0, this.f0);
        }
    }

    private boolean h0() {
        return this.K && this.L != null;
    }

    private void i(Canvas canvas, Rect rect) {
        if (this.F != null) {
            Paint.Align a2 = a(rect, this.i0);
            e(rect, this.h0);
            if (this.k0.a() != null) {
                this.k0.b().drawableState = getState();
                this.k0.a(this.d0);
            }
            this.k0.b().setTextAlign(a2);
            int i = 0;
            boolean z = Math.round(this.k0.a(S().toString())) > Math.round(this.h0.width());
            if (z) {
                i = canvas.save();
                canvas.clipRect(this.h0);
            }
            CharSequence charSequence = this.F;
            if (z && this.C0 != null) {
                charSequence = TextUtils.ellipsize(charSequence, this.k0.b(), this.h0.width(), this.C0);
            }
            CharSequence charSequence2 = charSequence;
            int length = charSequence2.length();
            PointF pointF = this.i0;
            canvas.drawText(charSequence2, 0, length, pointF.x, pointF.y, this.k0.b());
            if (z) {
                canvas.restoreToCount(i);
            }
        }
    }

    private void i0() {
        this.A0 = this.z0 ? c.a.a.a.z.b.b(this.E) : null;
    }

    @TargetApi(21)
    private void j0() {
        this.M = new RippleDrawable(c.a.a.a.z.b.b(Q()), this.L, H0);
    }

    public ColorStateList A() {
        return this.I;
    }

    public float B() {
        return this.A;
    }

    public float C() {
        return this.V;
    }

    public ColorStateList D() {
        return this.C;
    }

    public float E() {
        return this.D;
    }

    public Drawable F() {
        Drawable drawable = this.L;
        if (drawable != null) {
            return androidx.core.graphics.drawable.a.h(drawable);
        }
        return null;
    }

    public CharSequence G() {
        return this.P;
    }

    public float H() {
        return this.b0;
    }

    public float I() {
        return this.O;
    }

    public float J() {
        return this.a0;
    }

    public int[] K() {
        return this.y0;
    }

    public ColorStateList L() {
        return this.N;
    }

    public TextUtils.TruncateAt M() {
        return this.C0;
    }

    public h N() {
        return this.U;
    }

    public float O() {
        return this.X;
    }

    public float P() {
        return this.W;
    }

    public ColorStateList Q() {
        return this.E;
    }

    public h R() {
        return this.T;
    }

    public CharSequence S() {
        return this.F;
    }

    public d T() {
        return this.k0.a();
    }

    public float U() {
        return this.Z;
    }

    public float V() {
        return this.Y;
    }

    public boolean W() {
        return this.z0;
    }

    public boolean X() {
        return this.Q;
    }

    public boolean Y() {
        return e(this.L);
    }

    public boolean Z() {
        return this.K;
    }

    protected void a0() {
        InterfaceC0081a interfaceC0081a = this.B0.get();
        if (interfaceC0081a != null) {
            interfaceC0081a.a();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean b0() {
        return this.D0;
    }

    @Override // c.a.a.a.b0.g, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Rect bounds = getBounds();
        if (bounds.isEmpty() || getAlpha() == 0) {
            return;
        }
        int i = this.t0;
        int a2 = i < 255 ? c.a.a.a.o.a.a(canvas, bounds.left, bounds.top, bounds.right, bounds.bottom, i) : 0;
        e(canvas, bounds);
        b(canvas, bounds);
        if (this.F0) {
            super.draw(canvas);
        }
        d(canvas, bounds);
        g(canvas, bounds);
        c(canvas, bounds);
        a(canvas, bounds);
        if (this.D0) {
            i(canvas, bounds);
        }
        f(canvas, bounds);
        h(canvas, bounds);
        if (this.t0 < 255) {
            canvas.restoreToCount(a2);
        }
    }

    public void f(boolean z) {
        if (this.z0 != z) {
            this.z0 = z;
            i0();
            onStateChange(getState());
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.t0;
    }

    @Override // android.graphics.drawable.Drawable
    public ColorFilter getColorFilter() {
        return this.u0;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return (int) this.A;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return Math.min(Math.round(this.V + s() + this.Y + this.k0.a(S().toString()) + this.Z + t() + this.c0), this.E0);
    }

    @Override // c.a.a.a.b0.g, android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // c.a.a.a.b0.g, android.graphics.drawable.Drawable
    @TargetApi(21)
    public void getOutline(Outline outline) {
        if (this.F0) {
            super.getOutline(outline);
            return;
        }
        Rect bounds = getBounds();
        if (!bounds.isEmpty()) {
            outline.setRoundRect(bounds, this.B);
        } else {
            outline.setRoundRect(0, 0, getIntrinsicWidth(), getIntrinsicHeight(), this.B);
        }
        outline.setAlpha(getAlpha() / 255.0f);
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(Drawable drawable) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.invalidateDrawable(this);
        }
    }

    @Override // c.a.a.a.b0.g, android.graphics.drawable.Drawable
    public boolean isStateful() {
        return h(this.y) || h(this.z) || h(this.C) || (this.z0 && h(this.A0)) || b(this.k0.a()) || d0() || e(this.H) || e(this.S) || h(this.w0);
    }

    public void j(int i) {
        h(this.d0.getResources().getDimension(i));
    }

    public void k(float f2) {
        if (this.D != f2) {
            this.D = f2;
            this.e0.setStrokeWidth(f2);
            if (this.F0) {
                super.e(f2);
            }
            invalidateSelf();
        }
    }

    public void l(int i) {
        c(this.d0.getResources().getBoolean(i));
    }

    public void m(int i) {
        i(this.d0.getResources().getDimension(i));
    }

    public void n(int i) {
        j(this.d0.getResources().getDimension(i));
    }

    public void o(int i) {
        e(a.a.k.a.a.b(this.d0, i));
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onLayoutDirectionChanged(int i) {
        boolean onLayoutDirectionChanged = super.onLayoutDirectionChanged(i);
        if (g0()) {
            onLayoutDirectionChanged |= androidx.core.graphics.drawable.a.a(this.H, i);
        }
        if (f0()) {
            onLayoutDirectionChanged |= androidx.core.graphics.drawable.a.a(this.S, i);
        }
        if (h0()) {
            onLayoutDirectionChanged |= androidx.core.graphics.drawable.a.a(this.L, i);
        }
        if (!onLayoutDirectionChanged) {
            return true;
        }
        invalidateSelf();
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    protected boolean onLevelChange(int i) {
        boolean onLevelChange = super.onLevelChange(i);
        if (g0()) {
            onLevelChange |= this.H.setLevel(i);
        }
        if (f0()) {
            onLevelChange |= this.S.setLevel(i);
        }
        if (h0()) {
            onLevelChange |= this.L.setLevel(i);
        }
        if (onLevelChange) {
            invalidateSelf();
        }
        return onLevelChange;
    }

    @Override // c.a.a.a.b0.g, android.graphics.drawable.Drawable
    public boolean onStateChange(int[] iArr) {
        if (this.F0) {
            super.onStateChange(iArr);
        }
        return a(iArr, K());
    }

    public void p(int i) {
        k(this.d0.getResources().getDimension(i));
    }

    public void q(float f2) {
        if (this.Z != f2) {
            this.Z = f2;
            invalidateSelf();
            a0();
        }
    }

    public void r(int i) {
        c(a.a.k.a.a.c(this.d0, i));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float s() {
        if (g0() || f0()) {
            return this.W + this.J + this.X;
        }
        return 0.0f;
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.scheduleDrawable(this, runnable, j);
        }
    }

    @Override // c.a.a.a.b0.g, android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        if (this.t0 != i) {
            this.t0 = i;
            invalidateSelf();
        }
    }

    @Override // c.a.a.a.b0.g, android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        if (this.u0 != colorFilter) {
            this.u0 = colorFilter;
            invalidateSelf();
        }
    }

    @Override // c.a.a.a.b0.g, android.graphics.drawable.Drawable, androidx.core.graphics.drawable.b
    public void setTintList(ColorStateList colorStateList) {
        if (this.w0 != colorStateList) {
            this.w0 = colorStateList;
            onStateChange(getState());
        }
    }

    @Override // c.a.a.a.b0.g, android.graphics.drawable.Drawable, androidx.core.graphics.drawable.b
    public void setTintMode(PorterDuff.Mode mode) {
        if (this.x0 != mode) {
            this.x0 = mode;
            this.v0 = c.a.a.a.t.a.a(this, this.w0, mode);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z2) {
        boolean visible = super.setVisible(z, z2);
        if (g0()) {
            visible |= this.H.setVisible(z, z2);
        }
        if (f0()) {
            visible |= this.S.setVisible(z, z2);
        }
        if (h0()) {
            visible |= this.L.setVisible(z, z2);
        }
        if (visible) {
            invalidateSelf();
        }
        return visible;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float t() {
        if (h0()) {
            return this.a0 + this.O + this.b0;
        }
        return 0.0f;
    }

    public void u(int i) {
        f(a.a.k.a.a.b(this.d0, i));
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.unscheduleDrawable(this, runnable);
        }
    }

    public ColorStateList v() {
        return this.z;
    }

    public float w() {
        return this.F0 ? m() : this.B;
    }

    public void x(int i) {
        p(this.d0.getResources().getDimension(i));
    }

    public Drawable y() {
        Drawable drawable = this.H;
        if (drawable != null) {
            return androidx.core.graphics.drawable.a.h(drawable);
        }
        return null;
    }

    public void z(int i) {
        g(a.a.k.a.a.b(this.d0, i));
    }

    public void A(int i) {
        b(h.a(this.d0, i));
    }

    public void B(int i) {
        a(new d(this.d0, i));
    }

    public void C(int i) {
        q(this.d0.getResources().getDimension(i));
    }

    public void D(int i) {
        r(this.d0.getResources().getDimension(i));
    }

    public void j(float f2) {
        if (this.V != f2) {
            this.V = f2;
            invalidateSelf();
            a0();
        }
    }

    public void l(float f2) {
        if (this.b0 != f2) {
            this.b0 = f2;
            invalidateSelf();
            if (h0()) {
                a0();
            }
        }
    }

    public void m(float f2) {
        if (this.O != f2) {
            this.O = f2;
            invalidateSelf();
            if (h0()) {
                a0();
            }
        }
    }

    public void n(float f2) {
        if (this.a0 != f2) {
            this.a0 = f2;
            invalidateSelf();
            if (h0()) {
                a0();
            }
        }
    }

    public void o(float f2) {
        if (this.X != f2) {
            float s = s();
            this.X = f2;
            float s2 = s();
            invalidateSelf();
            if (s != s2) {
                a0();
            }
        }
    }

    public void p(float f2) {
        if (this.W != f2) {
            float s = s();
            this.W = f2;
            float s2 = s();
            invalidateSelf();
            if (s != s2) {
                a0();
            }
        }
    }

    public void r(float f2) {
        if (this.Y != f2) {
            this.Y = f2;
            invalidateSelf();
            a0();
        }
    }

    public Drawable u() {
        return this.S;
    }

    public void v(int i) {
        a(h.a(this.d0, i));
    }

    public void w(int i) {
        o(this.d0.getResources().getDimension(i));
    }

    public float x() {
        return this.c0;
    }

    public void y(int i) {
        this.E0 = i;
    }

    public float z() {
        return this.J;
    }

    private void a(AttributeSet attributeSet, int i, int i2) {
        TypedArray c2 = l.c(this.d0, attributeSet, c.a.a.a.l.Chip, i, i2, new int[0]);
        this.F0 = c2.hasValue(c.a.a.a.l.Chip_shapeAppearance);
        i(c.a(this.d0, c2, c.a.a.a.l.Chip_chipSurfaceColor));
        c(c.a(this.d0, c2, c.a.a.a.l.Chip_chipBackgroundColor));
        i(c2.getDimension(c.a.a.a.l.Chip_chipMinHeight, 0.0f));
        if (c2.hasValue(c.a.a.a.l.Chip_chipCornerRadius)) {
            f(c2.getDimension(c.a.a.a.l.Chip_chipCornerRadius, 0.0f));
        }
        e(c.a(this.d0, c2, c.a.a.a.l.Chip_chipStrokeColor));
        k(c2.getDimension(c.a.a.a.l.Chip_chipStrokeWidth, 0.0f));
        g(c.a(this.d0, c2, c.a.a.a.l.Chip_rippleColor));
        b(c2.getText(c.a.a.a.l.Chip_android_text));
        a(c.c(this.d0, c2, c.a.a.a.l.Chip_android_textAppearance));
        int i3 = c2.getInt(c.a.a.a.l.Chip_android_ellipsize, 0);
        if (i3 == 1) {
            a(TextUtils.TruncateAt.START);
        } else if (i3 == 2) {
            a(TextUtils.TruncateAt.MIDDLE);
        } else if (i3 == 3) {
            a(TextUtils.TruncateAt.END);
        }
        c(c2.getBoolean(c.a.a.a.l.Chip_chipIconVisible, false));
        if (attributeSet != null && attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "chipIconEnabled") != null && attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "chipIconVisible") == null) {
            c(c2.getBoolean(c.a.a.a.l.Chip_chipIconEnabled, false));
        }
        b(c.b(this.d0, c2, c.a.a.a.l.Chip_chipIcon));
        d(c.a(this.d0, c2, c.a.a.a.l.Chip_chipIconTint));
        h(c2.getDimension(c.a.a.a.l.Chip_chipIconSize, 0.0f));
        d(c2.getBoolean(c.a.a.a.l.Chip_closeIconVisible, false));
        if (attributeSet != null && attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "closeIconEnabled") != null && attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "closeIconVisible") == null) {
            d(c2.getBoolean(c.a.a.a.l.Chip_closeIconEnabled, false));
        }
        c(c.b(this.d0, c2, c.a.a.a.l.Chip_closeIcon));
        f(c.a(this.d0, c2, c.a.a.a.l.Chip_closeIconTint));
        m(c2.getDimension(c.a.a.a.l.Chip_closeIconSize, 0.0f));
        a(c2.getBoolean(c.a.a.a.l.Chip_android_checkable, false));
        b(c2.getBoolean(c.a.a.a.l.Chip_checkedIconVisible, false));
        if (attributeSet != null && attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "checkedIconEnabled") != null && attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "checkedIconVisible") == null) {
            b(c2.getBoolean(c.a.a.a.l.Chip_checkedIconEnabled, false));
        }
        a(c.b(this.d0, c2, c.a.a.a.l.Chip_checkedIcon));
        b(h.a(this.d0, c2, c.a.a.a.l.Chip_showMotionSpec));
        a(h.a(this.d0, c2, c.a.a.a.l.Chip_hideMotionSpec));
        j(c2.getDimension(c.a.a.a.l.Chip_chipStartPadding, 0.0f));
        p(c2.getDimension(c.a.a.a.l.Chip_iconStartPadding, 0.0f));
        o(c2.getDimension(c.a.a.a.l.Chip_iconEndPadding, 0.0f));
        r(c2.getDimension(c.a.a.a.l.Chip_textStartPadding, 0.0f));
        q(c2.getDimension(c.a.a.a.l.Chip_textEndPadding, 0.0f));
        n(c2.getDimension(c.a.a.a.l.Chip_closeIconStartPadding, 0.0f));
        l(c2.getDimension(c.a.a.a.l.Chip_closeIconEndPadding, 0.0f));
        g(c2.getDimension(c.a.a.a.l.Chip_chipEndPadding, 0.0f));
        y(c2.getDimensionPixelSize(c.a.a.a.l.Chip_android_maxWidth, Preference.DEFAULT_ORDER));
        c2.recycle();
    }

    public void s(int i) {
        m(this.d0.getResources().getDimension(i));
    }

    public void t(int i) {
        n(this.d0.getResources().getDimension(i));
    }

    private void f(Canvas canvas, Rect rect) {
        if (h0()) {
            c(rect, this.h0);
            RectF rectF = this.h0;
            float f2 = rectF.left;
            float f3 = rectF.top;
            canvas.translate(f2, f3);
            this.L.setBounds(0, 0, (int) this.h0.width(), (int) this.h0.height());
            if (c.a.a.a.z.b.f2166a) {
                this.M.setBounds(this.L.getBounds());
                this.M.jumpToCurrentState();
                this.M.draw(canvas);
            } else {
                this.L.draw(canvas);
            }
            canvas.translate(-f2, -f3);
        }
    }

    public void q(int i) {
        l(this.d0.getResources().getDimension(i));
    }

    private void e(Rect rect, RectF rectF) {
        rectF.setEmpty();
        if (this.F != null) {
            float s = this.V + s() + this.Y;
            float t = this.c0 + t() + this.Z;
            if (androidx.core.graphics.drawable.a.e(this) == 0) {
                rectF.left = rect.left + s;
                rectF.right = rect.right - t;
            } else {
                rectF.left = rect.left + t;
                rectF.right = rect.right - s;
            }
            rectF.top = rect.top;
            rectF.bottom = rect.bottom;
        }
    }

    private void b(Rect rect, RectF rectF) {
        rectF.set(rect);
        if (h0()) {
            float f2 = this.c0 + this.b0 + this.O + this.a0 + this.Z;
            if (androidx.core.graphics.drawable.a.e(this) == 0) {
                rectF.right = rect.right - f2;
            } else {
                rectF.left = rect.left + f2;
            }
        }
    }

    public void k(int i) {
        d(a.a.k.a.a.b(this.d0, i));
    }

    @Deprecated
    public void g(int i) {
        f(this.d0.getResources().getDimension(i));
    }

    private void c(Rect rect, RectF rectF) {
        rectF.setEmpty();
        if (h0()) {
            float f2 = this.c0 + this.b0;
            if (androidx.core.graphics.drawable.a.e(this) == 0) {
                rectF.right = rect.right - f2;
                rectF.left = rectF.right - this.O;
            } else {
                rectF.left = rect.left + f2;
                rectF.right = rectF.left + this.O;
            }
            float exactCenterY = rect.exactCenterY();
            float f3 = this.O;
            rectF.top = exactCenterY - (f3 / 2.0f);
            rectF.bottom = rectF.top + f3;
        }
    }

    private void d(Rect rect, RectF rectF) {
        rectF.setEmpty();
        if (h0()) {
            float f2 = this.c0 + this.b0 + this.O + this.a0 + this.Z;
            if (androidx.core.graphics.drawable.a.e(this) == 0) {
                rectF.right = rect.right;
                rectF.left = rectF.right - f2;
            } else {
                int i = rect.left;
                rectF.left = i;
                rectF.right = i + f2;
            }
            rectF.top = rect.top;
            rectF.bottom = rect.bottom;
        }
    }

    public void g(ColorStateList colorStateList) {
        if (this.E != colorStateList) {
            this.E = colorStateList;
            i0();
            onStateChange(getState());
        }
    }

    private static boolean b(d dVar) {
        ColorStateList colorStateList;
        return (dVar == null || (colorStateList = dVar.f2152b) == null || !colorStateList.isStateful()) ? false : true;
    }

    public void g(float f2) {
        if (this.c0 != f2) {
            this.c0 = f2;
            invalidateSelf();
            a0();
        }
    }

    public void b(CharSequence charSequence) {
        if (charSequence == null) {
            charSequence = "";
        }
        if (TextUtils.equals(this.F, charSequence)) {
            return;
        }
        this.F = charSequence;
        this.k0.a(true);
        invalidateSelf();
        a0();
    }

    private static boolean e(Drawable drawable) {
        return drawable != null && drawable.isStateful();
    }

    private void f(Drawable drawable) {
        if (drawable != null) {
            drawable.setCallback(null);
        }
    }

    public void e(ColorStateList colorStateList) {
        if (this.C != colorStateList) {
            this.C = colorStateList;
            if (this.F0) {
                b(colorStateList);
            }
            onStateChange(getState());
        }
    }

    public void f(int i) {
        c(a.a.k.a.a.b(this.d0, i));
    }

    private void d(Drawable drawable) {
        if (drawable != null) {
            drawable.setCallback(this);
            androidx.core.graphics.drawable.a.a(drawable, androidx.core.graphics.drawable.a.e(this));
            drawable.setLevel(getLevel());
            drawable.setVisible(isVisible(), false);
            if (drawable == this.L) {
                if (drawable.isStateful()) {
                    drawable.setState(K());
                }
                androidx.core.graphics.drawable.a.a(drawable, this.N);
            } else {
                if (drawable.isStateful()) {
                    drawable.setState(getState());
                }
                Drawable drawable2 = this.H;
                if (drawable == drawable2) {
                    androidx.core.graphics.drawable.a.a(drawable2, this.I);
                }
            }
        }
    }

    public void c(ColorStateList colorStateList) {
        if (this.z != colorStateList) {
            this.z = colorStateList;
            onStateChange(getState());
        }
    }

    @Deprecated
    public void f(float f2) {
        if (this.B != f2) {
            this.B = f2;
            setShapeAppearanceModel(k().a(f2));
        }
    }

    private static boolean h(ColorStateList colorStateList) {
        return colorStateList != null && colorStateList.isStateful();
    }

    private void i(ColorStateList colorStateList) {
        if (this.y != colorStateList) {
            this.y = colorStateList;
            onStateChange(getState());
        }
    }

    public void b(Drawable drawable) {
        Drawable y = y();
        if (y != drawable) {
            float s = s();
            this.H = drawable != null ? androidx.core.graphics.drawable.a.i(drawable).mutate() : null;
            float s2 = s();
            f(y);
            if (g0()) {
                d(this.H);
            }
            invalidateSelf();
            if (s != s2) {
                a0();
            }
        }
    }

    public void h(float f2) {
        if (this.J != f2) {
            float s = s();
            this.J = f2;
            float s2 = s();
            invalidateSelf();
            if (s != s2) {
                a0();
            }
        }
    }

    public void c(boolean z) {
        if (this.G != z) {
            boolean g0 = g0();
            this.G = z;
            boolean g02 = g0();
            if (g0 != g02) {
                if (g02) {
                    d(this.H);
                } else {
                    f(this.H);
                }
                invalidateSelf();
                a0();
            }
        }
    }

    public void f(ColorStateList colorStateList) {
        if (this.N != colorStateList) {
            this.N = colorStateList;
            if (h0()) {
                androidx.core.graphics.drawable.a.a(this.L, colorStateList);
            }
            onStateChange(getState());
        }
    }

    public void e(int i) {
        b(this.d0.getResources().getBoolean(i));
    }

    public void i(float f2) {
        if (this.A != f2) {
            this.A = f2;
            invalidateSelf();
            a0();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void e(boolean z) {
        this.D0 = z;
    }

    public void h(int i) {
        g(this.d0.getResources().getDimension(i));
    }

    public void i(int i) {
        b(a.a.k.a.a.c(this.d0, i));
    }

    public void b(boolean z) {
        if (this.R != z) {
            boolean f0 = f0();
            this.R = z;
            boolean f02 = f0();
            if (f0 != f02) {
                if (f02) {
                    d(this.S);
                } else {
                    f(this.S);
                }
                invalidateSelf();
                a0();
            }
        }
    }

    public void c(Drawable drawable) {
        Drawable F = F();
        if (F != drawable) {
            float t = t();
            this.L = drawable != null ? androidx.core.graphics.drawable.a.i(drawable).mutate() : null;
            if (c.a.a.a.z.b.f2166a) {
                j0();
            }
            float t2 = t();
            f(F);
            if (h0()) {
                d(this.L);
            }
            invalidateSelf();
            if (t != t2) {
                a0();
            }
        }
    }

    public void d(ColorStateList colorStateList) {
        if (this.I != colorStateList) {
            this.I = colorStateList;
            if (g0()) {
                androidx.core.graphics.drawable.a.a(this.H, colorStateList);
            }
            onStateChange(getState());
        }
    }

    public void d(boolean z) {
        if (this.K != z) {
            boolean h0 = h0();
            this.K = z;
            boolean h02 = h0();
            if (h0 != h02) {
                if (h02) {
                    d(this.L);
                } else {
                    f(this.L);
                }
                invalidateSelf();
                a0();
            }
        }
    }

    public void b(h hVar) {
        this.T = hVar;
    }

    public void c(int i) {
        a(this.d0.getResources().getBoolean(i));
    }

    public void d(int i) {
        a(a.a.k.a.a.c(this.d0, i));
    }

    public void a(InterfaceC0081a interfaceC0081a) {
        this.B0 = new WeakReference<>(interfaceC0081a);
    }

    public void a(RectF rectF) {
        d(getBounds(), rectF);
    }

    private void a(Canvas canvas, Rect rect) {
        if (f0()) {
            a(rect, this.h0);
            RectF rectF = this.h0;
            float f2 = rectF.left;
            float f3 = rectF.top;
            canvas.translate(f2, f3);
            this.S.setBounds(0, 0, (int) this.h0.width(), (int) this.h0.height());
            this.S.draw(canvas);
            canvas.translate(-f2, -f3);
        }
    }

    private void a(Rect rect, RectF rectF) {
        rectF.setEmpty();
        if (g0() || f0()) {
            float f2 = this.V + this.W;
            if (androidx.core.graphics.drawable.a.e(this) == 0) {
                rectF.left = rect.left + f2;
                rectF.right = rectF.left + this.J;
            } else {
                rectF.right = rect.right - f2;
                rectF.left = rectF.right - this.J;
            }
            float exactCenterY = rect.exactCenterY();
            float f3 = this.J;
            rectF.top = exactCenterY - (f3 / 2.0f);
            rectF.bottom = rectF.top + f3;
        }
    }

    Paint.Align a(Rect rect, PointF pointF) {
        pointF.set(0.0f, 0.0f);
        Paint.Align align = Paint.Align.LEFT;
        if (this.F != null) {
            float s = this.V + s() + this.Y;
            if (androidx.core.graphics.drawable.a.e(this) == 0) {
                pointF.x = rect.left + s;
                align = Paint.Align.LEFT;
            } else {
                pointF.x = rect.right - s;
                align = Paint.Align.RIGHT;
            }
            pointF.y = rect.centerY() - c0();
        }
        return align;
    }

    public boolean a(int[] iArr) {
        if (Arrays.equals(this.y0, iArr)) {
            return false;
        }
        this.y0 = iArr;
        if (h0()) {
            return a(getState(), iArr);
        }
        return false;
    }

    @Override // com.google.android.material.internal.k.b
    public void a() {
        a0();
        invalidateSelf();
    }

    /* JADX WARN: Removed duplicated region for block: B:60:0x00dd  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00e9  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00fe  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x010d  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x011c  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0146  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x014b  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x00e4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean a(int[] r7, int[] r8) {
        /*
            Method dump skipped, instructions count: 335
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.chip.a.a(int[], int[]):boolean");
    }

    private static boolean a(int[] iArr, int i) {
        if (iArr == null) {
            return false;
        }
        for (int i2 : iArr) {
            if (i2 == i) {
                return true;
            }
        }
        return false;
    }

    public void a(d dVar) {
        this.k0.a(dVar, this.d0);
    }

    public void a(TextUtils.TruncateAt truncateAt) {
        this.C0 = truncateAt;
    }

    public void a(CharSequence charSequence) {
        if (this.P != charSequence) {
            this.P = a.f.j.a.b().a(charSequence);
            invalidateSelf();
        }
    }

    public void a(boolean z) {
        if (this.Q != z) {
            this.Q = z;
            float s = s();
            if (!z && this.r0) {
                this.r0 = false;
            }
            float s2 = s();
            invalidateSelf();
            if (s != s2) {
                a0();
            }
        }
    }

    public void a(Drawable drawable) {
        if (this.S != drawable) {
            float s = s();
            this.S = drawable;
            float s2 = s();
            f(this.S);
            d(this.S);
            invalidateSelf();
            if (s != s2) {
                a0();
            }
        }
    }

    public void a(h hVar) {
        this.U = hVar;
    }
}
