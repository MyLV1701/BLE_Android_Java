package c.a.a.a.r.e;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import c.a.a.a.r.c;
import c.a.a.a.r.d;

/* loaded from: classes.dex */
public class a extends c.a.a.a.p.a implements d {
    private final c s;

    @Override // c.a.a.a.r.d
    public void a() {
        this.s.b();
        throw null;
    }

    @Override // c.a.a.a.r.d
    public void b() {
        this.s.a();
        throw null;
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        c cVar = this.s;
        if (cVar == null) {
            super.draw(canvas);
        } else {
            cVar.a(canvas);
            throw null;
        }
    }

    public Drawable getCircularRevealOverlayDrawable() {
        this.s.c();
        throw null;
    }

    @Override // c.a.a.a.r.d
    public int getCircularRevealScrimColor() {
        this.s.d();
        throw null;
    }

    @Override // c.a.a.a.r.d
    public d.e getRevealInfo() {
        this.s.e();
        throw null;
    }

    @Override // android.view.View
    public boolean isOpaque() {
        c cVar = this.s;
        if (cVar == null) {
            return super.isOpaque();
        }
        cVar.f();
        throw null;
    }

    @Override // c.a.a.a.r.d
    public void setCircularRevealOverlayDrawable(Drawable drawable) {
        this.s.a(drawable);
        throw null;
    }

    @Override // c.a.a.a.r.d
    public void setCircularRevealScrimColor(int i) {
        this.s.a(i);
        throw null;
    }

    @Override // c.a.a.a.r.d
    public void setRevealInfo(d.e eVar) {
        this.s.a(eVar);
        throw null;
    }
}
