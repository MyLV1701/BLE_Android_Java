package androidx.cardview.widget;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

/* loaded from: classes.dex */
class f extends Drawable {

    /* renamed from: a, reason: collision with root package name */
    private float f1150a;

    /* renamed from: c, reason: collision with root package name */
    private final RectF f1152c;

    /* renamed from: d, reason: collision with root package name */
    private final Rect f1153d;

    /* renamed from: e, reason: collision with root package name */
    private float f1154e;
    private ColorStateList h;
    private PorterDuffColorFilter i;
    private ColorStateList j;

    /* renamed from: f, reason: collision with root package name */
    private boolean f1155f = false;
    private boolean g = true;
    private PorterDuff.Mode k = PorterDuff.Mode.SRC_IN;

    /* renamed from: b, reason: collision with root package name */
    private final Paint f1151b = new Paint(5);

    /* JADX INFO: Access modifiers changed from: package-private */
    public f(ColorStateList colorStateList, float f2) {
        this.f1150a = f2;
        b(colorStateList);
        this.f1152c = new RectF();
        this.f1153d = new Rect();
    }

    private void b(ColorStateList colorStateList) {
        if (colorStateList == null) {
            colorStateList = ColorStateList.valueOf(0);
        }
        this.h = colorStateList;
        this.f1151b.setColor(this.h.getColorForState(getState(), this.h.getDefaultColor()));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(float f2, boolean z, boolean z2) {
        if (f2 == this.f1154e && this.f1155f == z && this.g == z2) {
            return;
        }
        this.f1154e = f2;
        this.f1155f = z;
        this.g = z2;
        a((Rect) null);
        invalidateSelf();
    }

    public float c() {
        return this.f1150a;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        boolean z;
        Paint paint = this.f1151b;
        if (this.i == null || paint.getColorFilter() != null) {
            z = false;
        } else {
            paint.setColorFilter(this.i);
            z = true;
        }
        RectF rectF = this.f1152c;
        float f2 = this.f1150a;
        canvas.drawRoundRect(rectF, f2, f2, paint);
        if (z) {
            paint.setColorFilter(null);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public void getOutline(Outline outline) {
        outline.setRoundRect(this.f1153d, this.f1150a);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        ColorStateList colorStateList;
        ColorStateList colorStateList2 = this.j;
        return (colorStateList2 != null && colorStateList2.isStateful()) || ((colorStateList = this.h) != null && colorStateList.isStateful()) || super.isStateful();
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        a(rect);
    }

    @Override // android.graphics.drawable.Drawable
    protected boolean onStateChange(int[] iArr) {
        PorterDuff.Mode mode;
        ColorStateList colorStateList = this.h;
        int colorForState = colorStateList.getColorForState(iArr, colorStateList.getDefaultColor());
        boolean z = colorForState != this.f1151b.getColor();
        if (z) {
            this.f1151b.setColor(colorForState);
        }
        ColorStateList colorStateList2 = this.j;
        if (colorStateList2 == null || (mode = this.k) == null) {
            return z;
        }
        this.i = a(colorStateList2, mode);
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        this.f1151b.setAlpha(i);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.f1151b.setColorFilter(colorFilter);
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintList(ColorStateList colorStateList) {
        this.j = colorStateList;
        this.i = a(this.j, this.k);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintMode(PorterDuff.Mode mode) {
        this.k = mode;
        this.i = a(this.j, this.k);
        invalidateSelf();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float b() {
        return this.f1154e;
    }

    private void a(Rect rect) {
        if (rect == null) {
            rect = getBounds();
        }
        this.f1152c.set(rect.left, rect.top, rect.right, rect.bottom);
        this.f1153d.set(rect);
        if (this.f1155f) {
            this.f1153d.inset((int) Math.ceil(g.a(this.f1154e, this.f1150a, this.g)), (int) Math.ceil(g.b(this.f1154e, this.f1150a, this.g)));
            this.f1152c.set(this.f1153d);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(float f2) {
        if (f2 == this.f1150a) {
            return;
        }
        this.f1150a = f2;
        a((Rect) null);
        invalidateSelf();
    }

    public void a(ColorStateList colorStateList) {
        b(colorStateList);
        invalidateSelf();
    }

    public ColorStateList a() {
        return this.h;
    }

    private PorterDuffColorFilter a(ColorStateList colorStateList, PorterDuff.Mode mode) {
        if (colorStateList == null || mode == null) {
            return null;
        }
        return new PorterDuffColorFilter(colorStateList.getColorForState(getState(), 0), mode);
    }
}
