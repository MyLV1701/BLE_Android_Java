package androidx.cardview.widget;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;

/* loaded from: classes.dex */
class g extends Drawable {
    private static final double q = Math.cos(Math.toRadians(45.0d));
    static a r;

    /* renamed from: a, reason: collision with root package name */
    private final int f1156a;

    /* renamed from: c, reason: collision with root package name */
    private Paint f1158c;

    /* renamed from: d, reason: collision with root package name */
    private Paint f1159d;

    /* renamed from: e, reason: collision with root package name */
    private final RectF f1160e;

    /* renamed from: f, reason: collision with root package name */
    private float f1161f;
    private Path g;
    private float h;
    private float i;
    private float j;
    private ColorStateList k;
    private final int m;
    private final int n;
    private boolean l = true;
    private boolean o = true;
    private boolean p = false;

    /* renamed from: b, reason: collision with root package name */
    private Paint f1157b = new Paint(5);

    /* loaded from: classes.dex */
    interface a {
        void a(Canvas canvas, RectF rectF, float f2, Paint paint);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public g(Resources resources, ColorStateList colorStateList, float f2, float f3, float f4) {
        this.m = resources.getColor(a.c.b.cardview_shadow_start_color);
        this.n = resources.getColor(a.c.b.cardview_shadow_end_color);
        this.f1156a = resources.getDimensionPixelSize(a.c.c.cardview_compat_inset_shadow);
        b(colorStateList);
        this.f1158c = new Paint(5);
        this.f1158c.setStyle(Paint.Style.FILL);
        this.f1161f = (int) (f2 + 0.5f);
        this.f1160e = new RectF();
        this.f1159d = new Paint(this.f1158c);
        this.f1159d.setAntiAlias(false);
        a(f3, f4);
    }

    private void b(ColorStateList colorStateList) {
        if (colorStateList == null) {
            colorStateList = ColorStateList.valueOf(0);
        }
        this.k = colorStateList;
        this.f1157b.setColor(this.k.getColorForState(getState(), this.k.getDefaultColor()));
    }

    private int d(float f2) {
        int i = (int) (f2 + 0.5f);
        return i % 2 == 1 ? i - 1 : i;
    }

    private void g() {
        float f2 = this.f1161f;
        RectF rectF = new RectF(-f2, -f2, f2, f2);
        RectF rectF2 = new RectF(rectF);
        float f3 = this.i;
        rectF2.inset(-f3, -f3);
        Path path = this.g;
        if (path == null) {
            this.g = new Path();
        } else {
            path.reset();
        }
        this.g.setFillType(Path.FillType.EVEN_ODD);
        this.g.moveTo(-this.f1161f, 0.0f);
        this.g.rLineTo(-this.i, 0.0f);
        this.g.arcTo(rectF2, 180.0f, 90.0f, false);
        this.g.arcTo(rectF, 270.0f, -90.0f, false);
        this.g.close();
        float f4 = this.f1161f;
        float f5 = this.i;
        float f6 = f4 / (f4 + f5);
        Paint paint = this.f1158c;
        float f7 = f4 + f5;
        int i = this.m;
        paint.setShader(new RadialGradient(0.0f, 0.0f, f7, new int[]{i, i, this.n}, new float[]{0.0f, f6, 1.0f}, Shader.TileMode.CLAMP));
        Paint paint2 = this.f1159d;
        float f8 = this.f1161f;
        float f9 = this.i;
        int i2 = this.m;
        paint2.setShader(new LinearGradient(0.0f, (-f8) + f9, 0.0f, (-f8) - f9, new int[]{i2, i2, this.n}, new float[]{0.0f, 0.5f, 1.0f}, Shader.TileMode.CLAMP));
        this.f1159d.setAntiAlias(false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(boolean z) {
        this.o = z;
        invalidateSelf();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void c(float f2) {
        a(f2, this.h);
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.l) {
            b(getBounds());
            this.l = false;
        }
        canvas.translate(0.0f, this.j / 2.0f);
        a(canvas);
        canvas.translate(0.0f, (-this.j) / 2.0f);
        r.a(canvas, this.f1160e, this.f1161f, this.f1157b);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float e() {
        float f2 = this.h;
        return (Math.max(f2, this.f1161f + this.f1156a + (f2 / 2.0f)) * 2.0f) + ((this.h + this.f1156a) * 2.0f);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float f() {
        return this.j;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean getPadding(Rect rect) {
        int ceil = (int) Math.ceil(b(this.h, this.f1161f, this.o));
        int ceil2 = (int) Math.ceil(a(this.h, this.f1161f, this.o));
        rect.set(ceil2, ceil, ceil2, ceil);
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        ColorStateList colorStateList = this.k;
        return (colorStateList != null && colorStateList.isStateful()) || super.isStateful();
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        this.l = true;
    }

    @Override // android.graphics.drawable.Drawable
    protected boolean onStateChange(int[] iArr) {
        ColorStateList colorStateList = this.k;
        int colorForState = colorStateList.getColorForState(iArr, colorStateList.getDefaultColor());
        if (this.f1157b.getColor() == colorForState) {
            return false;
        }
        this.f1157b.setColor(colorForState);
        this.l = true;
        invalidateSelf();
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        this.f1157b.setAlpha(i);
        this.f1158c.setAlpha(i);
        this.f1159d.setAlpha(i);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.f1157b.setColorFilter(colorFilter);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float c() {
        return this.h;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float d() {
        float f2 = this.h;
        return (Math.max(f2, this.f1161f + this.f1156a + ((f2 * 1.5f) / 2.0f)) * 2.0f) + (((this.h * 1.5f) + this.f1156a) * 2.0f);
    }

    private void a(float f2, float f3) {
        if (f2 < 0.0f) {
            throw new IllegalArgumentException("Invalid shadow size " + f2 + ". Must be >= 0");
        }
        if (f3 >= 0.0f) {
            float d2 = d(f2);
            float d3 = d(f3);
            if (d2 > d3) {
                if (!this.p) {
                    this.p = true;
                }
                d2 = d3;
            }
            if (this.j == d2 && this.h == d3) {
                return;
            }
            this.j = d2;
            this.h = d3;
            this.i = (int) ((d2 * 1.5f) + this.f1156a + 0.5f);
            this.l = true;
            invalidateSelf();
            return;
        }
        throw new IllegalArgumentException("Invalid max shadow size " + f3 + ". Must be >= 0");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static float b(float f2, float f3, boolean z) {
        if (!z) {
            return f2 * 1.5f;
        }
        double d2 = f2 * 1.5f;
        double d3 = 1.0d - q;
        double d4 = f3;
        Double.isNaN(d4);
        Double.isNaN(d2);
        return (float) (d2 + (d3 * d4));
    }

    private void b(Rect rect) {
        float f2 = this.h;
        float f3 = 1.5f * f2;
        this.f1160e.set(rect.left + f2, rect.top + f3, rect.right - f2, rect.bottom - f3);
        g();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float b() {
        return this.f1161f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(float f2) {
        a(this.j, f2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static float a(float f2, float f3, boolean z) {
        if (!z) {
            return f2;
        }
        double d2 = f2;
        double d3 = 1.0d - q;
        double d4 = f3;
        Double.isNaN(d4);
        Double.isNaN(d2);
        return (float) (d2 + (d3 * d4));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(float f2) {
        if (f2 < 0.0f) {
            throw new IllegalArgumentException("Invalid radius " + f2 + ". Must be >= 0");
        }
        float f3 = (int) (f2 + 0.5f);
        if (this.f1161f == f3) {
            return;
        }
        this.f1161f = f3;
        this.l = true;
        invalidateSelf();
    }

    private void a(Canvas canvas) {
        float f2 = this.f1161f;
        float f3 = (-f2) - this.i;
        float f4 = f2 + this.f1156a + (this.j / 2.0f);
        float f5 = f4 * 2.0f;
        boolean z = this.f1160e.width() - f5 > 0.0f;
        boolean z2 = this.f1160e.height() - f5 > 0.0f;
        int save = canvas.save();
        RectF rectF = this.f1160e;
        canvas.translate(rectF.left + f4, rectF.top + f4);
        canvas.drawPath(this.g, this.f1158c);
        if (z) {
            canvas.drawRect(0.0f, f3, this.f1160e.width() - f5, -this.f1161f, this.f1159d);
        }
        canvas.restoreToCount(save);
        int save2 = canvas.save();
        RectF rectF2 = this.f1160e;
        canvas.translate(rectF2.right - f4, rectF2.bottom - f4);
        canvas.rotate(180.0f);
        canvas.drawPath(this.g, this.f1158c);
        if (z) {
            canvas.drawRect(0.0f, f3, this.f1160e.width() - f5, (-this.f1161f) + this.i, this.f1159d);
        }
        canvas.restoreToCount(save2);
        int save3 = canvas.save();
        RectF rectF3 = this.f1160e;
        canvas.translate(rectF3.left + f4, rectF3.bottom - f4);
        canvas.rotate(270.0f);
        canvas.drawPath(this.g, this.f1158c);
        if (z2) {
            canvas.drawRect(0.0f, f3, this.f1160e.height() - f5, -this.f1161f, this.f1159d);
        }
        canvas.restoreToCount(save3);
        int save4 = canvas.save();
        RectF rectF4 = this.f1160e;
        canvas.translate(rectF4.right - f4, rectF4.top + f4);
        canvas.rotate(90.0f);
        canvas.drawPath(this.g, this.f1158c);
        if (z2) {
            canvas.drawRect(0.0f, f3, this.f1160e.height() - f5, -this.f1161f, this.f1159d);
        }
        canvas.restoreToCount(save4);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(Rect rect) {
        getPadding(rect);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(ColorStateList colorStateList) {
        b(colorStateList);
        invalidateSelf();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ColorStateList a() {
        return this.k;
    }
}
