package c.a.a.a.r;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.widget.FrameLayout;
import c.a.a.a.r.d;

/* loaded from: classes.dex */
public class b extends FrameLayout implements d {

    /* renamed from: b, reason: collision with root package name */
    private final c f2130b;

    @Override // c.a.a.a.r.d
    public void a() {
        this.f2130b.b();
        throw null;
    }

    @Override // c.a.a.a.r.d
    public void b() {
        this.f2130b.a();
        throw null;
    }

    @Override // android.view.View
    @SuppressLint({"MissingSuperCall"})
    public void draw(Canvas canvas) {
        c cVar = this.f2130b;
        if (cVar == null) {
            super.draw(canvas);
        } else {
            cVar.a(canvas);
            throw null;
        }
    }

    public Drawable getCircularRevealOverlayDrawable() {
        this.f2130b.c();
        throw null;
    }

    @Override // c.a.a.a.r.d
    public int getCircularRevealScrimColor() {
        this.f2130b.d();
        throw null;
    }

    @Override // c.a.a.a.r.d
    public d.e getRevealInfo() {
        this.f2130b.e();
        throw null;
    }

    @Override // android.view.View
    public boolean isOpaque() {
        c cVar = this.f2130b;
        if (cVar == null) {
            return super.isOpaque();
        }
        cVar.f();
        throw null;
    }

    @Override // c.a.a.a.r.d
    public void setCircularRevealOverlayDrawable(Drawable drawable) {
        this.f2130b.a(drawable);
        throw null;
    }

    @Override // c.a.a.a.r.d
    public void setCircularRevealScrimColor(int i) {
        this.f2130b.a(i);
        throw null;
    }

    @Override // c.a.a.a.r.d
    public void setRevealInfo(d.e eVar) {
        this.f2130b.a(eVar);
        throw null;
    }
}
