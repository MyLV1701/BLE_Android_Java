package a.a.l.a;

import a.a.i;
import a.a.j;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

/* loaded from: classes.dex */
public class d extends Drawable {
    private static final float m = (float) Math.toRadians(45.0d);

    /* renamed from: b, reason: collision with root package name */
    private float f27b;

    /* renamed from: c, reason: collision with root package name */
    private float f28c;

    /* renamed from: d, reason: collision with root package name */
    private float f29d;

    /* renamed from: e, reason: collision with root package name */
    private float f30e;

    /* renamed from: f, reason: collision with root package name */
    private boolean f31f;
    private final int h;
    private float j;
    private float k;

    /* renamed from: a, reason: collision with root package name */
    private final Paint f26a = new Paint();
    private final Path g = new Path();
    private boolean i = false;
    private int l = 2;

    public d(Context context) {
        this.f26a.setStyle(Paint.Style.STROKE);
        this.f26a.setStrokeJoin(Paint.Join.MITER);
        this.f26a.setStrokeCap(Paint.Cap.BUTT);
        this.f26a.setAntiAlias(true);
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(null, j.DrawerArrowToggle, a.a.a.drawerArrowStyle, i.Base_Widget_AppCompat_DrawerArrowToggle);
        a(obtainStyledAttributes.getColor(j.DrawerArrowToggle_color, 0));
        a(obtainStyledAttributes.getDimension(j.DrawerArrowToggle_thickness, 0.0f));
        a(obtainStyledAttributes.getBoolean(j.DrawerArrowToggle_spinBars, true));
        b(Math.round(obtainStyledAttributes.getDimension(j.DrawerArrowToggle_gapBetweenBars, 0.0f)));
        this.h = obtainStyledAttributes.getDimensionPixelSize(j.DrawerArrowToggle_drawableSize, 0);
        this.f28c = Math.round(obtainStyledAttributes.getDimension(j.DrawerArrowToggle_barLength, 0.0f));
        this.f27b = Math.round(obtainStyledAttributes.getDimension(j.DrawerArrowToggle_arrowHeadLength, 0.0f));
        this.f29d = obtainStyledAttributes.getDimension(j.DrawerArrowToggle_arrowShaftLength, 0.0f);
        obtainStyledAttributes.recycle();
    }

    private static float a(float f2, float f3, float f4) {
        return f2 + ((f3 - f2) * f4);
    }

    public void a(int i) {
        if (i != this.f26a.getColor()) {
            this.f26a.setColor(i);
            invalidateSelf();
        }
    }

    public void b(float f2) {
        if (f2 != this.f30e) {
            this.f30e = f2;
            invalidateSelf();
        }
    }

    public void c(float f2) {
        if (this.j != f2) {
            this.j = f2;
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Rect bounds = getBounds();
        int i = this.l;
        boolean z = false;
        if (i != 0 && (i == 1 || (i == 3 ? androidx.core.graphics.drawable.a.e(this) == 0 : androidx.core.graphics.drawable.a.e(this) == 1))) {
            z = true;
        }
        float f2 = this.f27b;
        float a2 = a(this.f28c, (float) Math.sqrt(f2 * f2 * 2.0f), this.j);
        float a3 = a(this.f28c, this.f29d, this.j);
        float round = Math.round(a(0.0f, this.k, this.j));
        float a4 = a(0.0f, m, this.j);
        float a5 = a(z ? 0.0f : -180.0f, z ? 180.0f : 0.0f, this.j);
        double d2 = a2;
        double d3 = a4;
        double cos = Math.cos(d3);
        Double.isNaN(d2);
        boolean z2 = z;
        float round2 = (float) Math.round(cos * d2);
        double sin = Math.sin(d3);
        Double.isNaN(d2);
        float round3 = (float) Math.round(d2 * sin);
        this.g.rewind();
        float a6 = a(this.f30e + this.f26a.getStrokeWidth(), -this.k, this.j);
        float f3 = (-a3) / 2.0f;
        this.g.moveTo(f3 + round, 0.0f);
        this.g.rLineTo(a3 - (round * 2.0f), 0.0f);
        this.g.moveTo(f3, a6);
        this.g.rLineTo(round2, round3);
        this.g.moveTo(f3, -a6);
        this.g.rLineTo(round2, -round3);
        this.g.close();
        canvas.save();
        float strokeWidth = this.f26a.getStrokeWidth();
        float height = bounds.height() - (3.0f * strokeWidth);
        canvas.translate(bounds.centerX(), ((((int) (height - (2.0f * r5))) / 4) * 2) + (strokeWidth * 1.5f) + this.f30e);
        if (this.f31f) {
            canvas.rotate(a5 * (this.i ^ z2 ? -1 : 1));
        } else if (z2) {
            canvas.rotate(180.0f);
        }
        canvas.drawPath(this.g, this.f26a);
        canvas.restore();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.h;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.h;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        if (i != this.f26a.getAlpha()) {
            this.f26a.setAlpha(i);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.f26a.setColorFilter(colorFilter);
        invalidateSelf();
    }

    public void a(float f2) {
        if (this.f26a.getStrokeWidth() != f2) {
            this.f26a.setStrokeWidth(f2);
            double d2 = f2 / 2.0f;
            double cos = Math.cos(m);
            Double.isNaN(d2);
            this.k = (float) (d2 * cos);
            invalidateSelf();
        }
    }

    public void b(boolean z) {
        if (this.i != z) {
            this.i = z;
            invalidateSelf();
        }
    }

    public void a(boolean z) {
        if (this.f31f != z) {
            this.f31f = z;
            invalidateSelf();
        }
    }
}
