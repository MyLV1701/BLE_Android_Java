package com.google.android.material.textfield;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import c.a.a.a.b0.k;

/* loaded from: classes.dex */
class c extends c.a.a.a.b0.g {
    private int A;
    private final Paint y;
    private final RectF z;

    c() {
        this(null);
    }

    private void b(Canvas canvas) {
        Drawable.Callback callback = getCallback();
        if (a(callback)) {
            View view = (View) callback;
            if (view.getLayerType() != 2) {
                view.setLayerType(2, null);
                return;
            }
            return;
        }
        c(canvas);
    }

    private void c(Canvas canvas) {
        if (Build.VERSION.SDK_INT >= 21) {
            this.A = canvas.saveLayer(0.0f, 0.0f, canvas.getWidth(), canvas.getHeight(), null);
        } else {
            this.A = canvas.saveLayer(0.0f, 0.0f, canvas.getWidth(), canvas.getHeight(), null, 31);
        }
    }

    private void u() {
        this.y.setStyle(Paint.Style.FILL_AND_STROKE);
        this.y.setColor(-1);
        this.y.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
    }

    void a(float f2, float f3, float f4, float f5) {
        RectF rectF = this.z;
        if (f2 == rectF.left && f3 == rectF.top && f4 == rectF.right && f5 == rectF.bottom) {
            return;
        }
        this.z.set(f2, f3, f4, f5);
        invalidateSelf();
    }

    @Override // c.a.a.a.b0.g, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        b(canvas);
        super.draw(canvas);
        canvas.drawRect(this.z, this.y);
        a(canvas);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean s() {
        return !this.z.isEmpty();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void t() {
        a(0.0f, 0.0f, 0.0f, 0.0f);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public c(k kVar) {
        super(kVar == null ? new k() : kVar);
        this.y = new Paint(1);
        u();
        this.z = new RectF();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(RectF rectF) {
        a(rectF.left, rectF.top, rectF.right, rectF.bottom);
    }

    private void a(Canvas canvas) {
        if (a(getCallback())) {
            return;
        }
        canvas.restoreToCount(this.A);
    }

    private boolean a(Drawable.Callback callback) {
        return callback instanceof View;
    }
}
