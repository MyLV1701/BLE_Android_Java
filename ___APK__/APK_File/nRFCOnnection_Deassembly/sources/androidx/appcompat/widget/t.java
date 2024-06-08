package androidx.appcompat.widget;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.SeekBar;

/* loaded from: classes.dex */
class t extends q {

    /* renamed from: d, reason: collision with root package name */
    private final SeekBar f1088d;

    /* renamed from: e, reason: collision with root package name */
    private Drawable f1089e;

    /* renamed from: f, reason: collision with root package name */
    private ColorStateList f1090f;
    private PorterDuff.Mode g;
    private boolean h;
    private boolean i;

    /* JADX INFO: Access modifiers changed from: package-private */
    public t(SeekBar seekBar) {
        super(seekBar);
        this.f1090f = null;
        this.g = null;
        this.h = false;
        this.i = false;
        this.f1088d = seekBar;
    }

    private void d() {
        if (this.f1089e != null) {
            if (this.h || this.i) {
                this.f1089e = androidx.core.graphics.drawable.a.i(this.f1089e.mutate());
                if (this.h) {
                    androidx.core.graphics.drawable.a.a(this.f1089e, this.f1090f);
                }
                if (this.i) {
                    androidx.core.graphics.drawable.a.a(this.f1089e, this.g);
                }
                if (this.f1089e.isStateful()) {
                    this.f1089e.setState(this.f1088d.getDrawableState());
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // androidx.appcompat.widget.q
    public void a(AttributeSet attributeSet, int i) {
        super.a(attributeSet, i);
        t0 a2 = t0.a(this.f1088d.getContext(), attributeSet, a.a.j.AppCompatSeekBar, i, 0);
        Drawable c2 = a2.c(a.a.j.AppCompatSeekBar_android_thumb);
        if (c2 != null) {
            this.f1088d.setThumb(c2);
        }
        a(a2.b(a.a.j.AppCompatSeekBar_tickMark));
        if (a2.g(a.a.j.AppCompatSeekBar_tickMarkTintMode)) {
            this.g = a0.a(a2.d(a.a.j.AppCompatSeekBar_tickMarkTintMode, -1), this.g);
            this.i = true;
        }
        if (a2.g(a.a.j.AppCompatSeekBar_tickMarkTint)) {
            this.f1090f = a2.a(a.a.j.AppCompatSeekBar_tickMarkTint);
            this.h = true;
        }
        a2.a();
        d();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b() {
        Drawable drawable = this.f1089e;
        if (drawable != null && drawable.isStateful() && drawable.setState(this.f1088d.getDrawableState())) {
            this.f1088d.invalidateDrawable(drawable);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void c() {
        Drawable drawable = this.f1089e;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
    }

    void a(Drawable drawable) {
        Drawable drawable2 = this.f1089e;
        if (drawable2 != null) {
            drawable2.setCallback(null);
        }
        this.f1089e = drawable;
        if (drawable != null) {
            drawable.setCallback(this.f1088d);
            androidx.core.graphics.drawable.a.a(drawable, a.f.l.w.q(this.f1088d));
            if (drawable.isStateful()) {
                drawable.setState(this.f1088d.getDrawableState());
            }
            d();
        }
        this.f1088d.invalidate();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(Canvas canvas) {
        if (this.f1089e != null) {
            int max = this.f1088d.getMax();
            if (max > 1) {
                int intrinsicWidth = this.f1089e.getIntrinsicWidth();
                int intrinsicHeight = this.f1089e.getIntrinsicHeight();
                int i = intrinsicWidth >= 0 ? intrinsicWidth / 2 : 1;
                int i2 = intrinsicHeight >= 0 ? intrinsicHeight / 2 : 1;
                this.f1089e.setBounds(-i, -i2, i, i2);
                float width = ((this.f1088d.getWidth() - this.f1088d.getPaddingLeft()) - this.f1088d.getPaddingRight()) / max;
                int save = canvas.save();
                canvas.translate(this.f1088d.getPaddingLeft(), this.f1088d.getHeight() / 2);
                for (int i3 = 0; i3 <= max; i3++) {
                    this.f1089e.draw(canvas);
                    canvas.translate(width, 0.0f);
                }
                canvas.restoreToCount(save);
            }
        }
    }
}
