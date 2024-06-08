package com.google.android.material.floatingactionbutton;

import android.annotation.TargetApi;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import c.a.a.a.b0.k;
import c.a.a.a.b0.l;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class a extends Drawable {
    float h;
    private int i;
    private int j;
    private int k;
    private int l;
    private int m;
    private k o;
    private ColorStateList p;

    /* renamed from: a, reason: collision with root package name */
    private final l f2535a = new l();

    /* renamed from: c, reason: collision with root package name */
    private final Path f2537c = new Path();

    /* renamed from: d, reason: collision with root package name */
    private final Rect f2538d = new Rect();

    /* renamed from: e, reason: collision with root package name */
    private final RectF f2539e = new RectF();

    /* renamed from: f, reason: collision with root package name */
    private final RectF f2540f = new RectF();
    private final b g = new b();
    private boolean n = true;

    /* renamed from: b, reason: collision with root package name */
    private final Paint f2536b = new Paint(1);

    /* loaded from: classes.dex */
    private class b extends Drawable.ConstantState {
        private b() {
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return 0;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            return a.this;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public a(k kVar) {
        this.o = kVar;
        this.f2536b.setStyle(Paint.Style.STROKE);
    }

    private Shader b() {
        copyBounds(this.f2538d);
        float height = this.h / r0.height();
        return new LinearGradient(0.0f, r0.top, 0.0f, r0.bottom, new int[]{a.f.e.a.b(this.i, this.m), a.f.e.a.b(this.j, this.m), a.f.e.a.b(a.f.e.a.c(this.j, 0), this.m), a.f.e.a.b(a.f.e.a.c(this.l, 0), this.m), a.f.e.a.b(this.l, this.m), a.f.e.a.b(this.k, this.m)}, new float[]{0.0f, height, 0.5f, 0.5f, 1.0f - height, 1.0f}, Shader.TileMode.CLAMP);
    }

    public void a(float f2) {
        if (this.h != f2) {
            this.h = f2;
            this.f2536b.setStrokeWidth(f2 * 1.3333f);
            this.n = true;
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.n) {
            this.f2536b.setShader(b());
            this.n = false;
        }
        float strokeWidth = this.f2536b.getStrokeWidth() / 2.0f;
        copyBounds(this.f2538d);
        this.f2539e.set(this.f2538d);
        float min = Math.min(this.o.j().a(a()), this.f2539e.width() / 2.0f);
        if (this.o.a(a())) {
            this.f2539e.inset(strokeWidth, strokeWidth);
            canvas.drawRoundRect(this.f2539e, min, min, this.f2536b);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        return this.g;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return this.h > 0.0f ? -3 : -2;
    }

    @Override // android.graphics.drawable.Drawable
    @TargetApi(21)
    public void getOutline(Outline outline) {
        if (this.o.a(a())) {
            outline.setRoundRect(getBounds(), this.o.j().a(a()));
            return;
        }
        copyBounds(this.f2538d);
        this.f2539e.set(this.f2538d);
        this.f2535a.a(this.o, 1.0f, this.f2539e, this.f2537c);
        if (this.f2537c.isConvex()) {
            outline.setConvexPath(this.f2537c);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean getPadding(Rect rect) {
        if (!this.o.a(a())) {
            return true;
        }
        int round = Math.round(this.h);
        rect.set(round, round, round, round);
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        ColorStateList colorStateList = this.p;
        return (colorStateList != null && colorStateList.isStateful()) || super.isStateful();
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        this.n = true;
    }

    @Override // android.graphics.drawable.Drawable
    protected boolean onStateChange(int[] iArr) {
        int colorForState;
        ColorStateList colorStateList = this.p;
        if (colorStateList != null && (colorForState = colorStateList.getColorForState(iArr, this.m)) != this.m) {
            this.n = true;
            this.m = colorForState;
        }
        if (this.n) {
            invalidateSelf();
        }
        return this.n;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        this.f2536b.setAlpha(i);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.f2536b.setColorFilter(colorFilter);
        invalidateSelf();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(ColorStateList colorStateList) {
        if (colorStateList != null) {
            this.m = colorStateList.getColorForState(getState(), this.m);
        }
        this.p = colorStateList;
        this.n = true;
        invalidateSelf();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(int i, int i2, int i3, int i4) {
        this.i = i;
        this.j = i2;
        this.k = i3;
        this.l = i4;
    }

    protected RectF a() {
        this.f2540f.set(getBounds());
        return this.f2540f;
    }

    public void a(k kVar) {
        this.o = kVar;
        invalidateSelf();
    }
}
