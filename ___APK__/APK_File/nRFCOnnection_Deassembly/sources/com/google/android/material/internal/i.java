package com.google.android.material.internal;

import a.f.l.e0;
import a.f.l.r;
import a.f.l.w;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

/* loaded from: classes.dex */
public class i extends FrameLayout {

    /* renamed from: b, reason: collision with root package name */
    Drawable f2598b;

    /* renamed from: c, reason: collision with root package name */
    Rect f2599c;

    /* renamed from: d, reason: collision with root package name */
    private Rect f2600d;

    /* renamed from: e, reason: collision with root package name */
    private boolean f2601e;

    /* renamed from: f, reason: collision with root package name */
    private boolean f2602f;

    /* loaded from: classes.dex */
    class a implements r {
        a() {
        }

        @Override // a.f.l.r
        public e0 a(View view, e0 e0Var) {
            i iVar = i.this;
            if (iVar.f2599c == null) {
                iVar.f2599c = new Rect();
            }
            i.this.f2599c.set(e0Var.c(), e0Var.e(), e0Var.d(), e0Var.b());
            i.this.a(e0Var);
            i.this.setWillNotDraw(!e0Var.f() || i.this.f2598b == null);
            w.K(i.this);
            return e0Var.a();
        }
    }

    public i(Context context) {
        this(context, null);
    }

    protected void a(e0 e0Var) {
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        super.draw(canvas);
        int width = getWidth();
        int height = getHeight();
        if (this.f2599c == null || this.f2598b == null) {
            return;
        }
        int save = canvas.save();
        canvas.translate(getScrollX(), getScrollY());
        if (this.f2601e) {
            this.f2600d.set(0, 0, width, this.f2599c.top);
            this.f2598b.setBounds(this.f2600d);
            this.f2598b.draw(canvas);
        }
        if (this.f2602f) {
            this.f2600d.set(0, height - this.f2599c.bottom, width, height);
            this.f2598b.setBounds(this.f2600d);
            this.f2598b.draw(canvas);
        }
        Rect rect = this.f2600d;
        Rect rect2 = this.f2599c;
        rect.set(0, rect2.top, rect2.left, height - rect2.bottom);
        this.f2598b.setBounds(this.f2600d);
        this.f2598b.draw(canvas);
        Rect rect3 = this.f2600d;
        Rect rect4 = this.f2599c;
        rect3.set(width - rect4.right, rect4.top, width, height - rect4.bottom);
        this.f2598b.setBounds(this.f2600d);
        this.f2598b.draw(canvas);
        canvas.restoreToCount(save);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Drawable drawable = this.f2598b;
        if (drawable != null) {
            drawable.setCallback(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Drawable drawable = this.f2598b;
        if (drawable != null) {
            drawable.setCallback(null);
        }
    }

    public void setDrawBottomInsetForeground(boolean z) {
        this.f2602f = z;
    }

    public void setDrawTopInsetForeground(boolean z) {
        this.f2601e = z;
    }

    public void setScrimInsetForeground(Drawable drawable) {
        this.f2598b = drawable;
    }

    public i(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public i(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f2600d = new Rect();
        this.f2601e = true;
        this.f2602f = true;
        TypedArray c2 = l.c(context, attributeSet, c.a.a.a.l.ScrimInsetsFrameLayout, i, c.a.a.a.k.Widget_Design_ScrimInsetsFrameLayout, new int[0]);
        this.f2598b = c2.getDrawable(c.a.a.a.l.ScrimInsetsFrameLayout_insetForeground);
        c2.recycle();
        setWillNotDraw(true);
        w.a(this, new a());
    }
}
