package c.a.a.a.u;

import android.content.Context;
import android.graphics.Color;
import c.a.a.a.y.b;

/* loaded from: classes.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    private final boolean f2139a;

    /* renamed from: b, reason: collision with root package name */
    private final int f2140b;

    /* renamed from: c, reason: collision with root package name */
    private final int f2141c;

    /* renamed from: d, reason: collision with root package name */
    private final float f2142d;

    public a(Context context) {
        this.f2139a = b.a(context, c.a.a.a.b.elevationOverlayEnabled, false);
        this.f2140b = c.a.a.a.s.a.a(context, c.a.a.a.b.elevationOverlayColor, 0);
        this.f2141c = c.a.a.a.s.a.a(context, c.a.a.a.b.colorSurface, 0);
        this.f2142d = context.getResources().getDisplayMetrics().density;
    }

    public int a(int i, float f2) {
        float a2 = a(f2);
        return a.f.e.a.c(c.a.a.a.s.a.a(a.f.e.a.c(i, 255), this.f2140b, a2), Color.alpha(i));
    }

    public int b(int i, float f2) {
        return (this.f2139a && a(i)) ? a(i, f2) : i;
    }

    public float a(float f2) {
        if (this.f2142d <= 0.0f || f2 <= 0.0f) {
            return 0.0f;
        }
        return Math.min(((((float) Math.log1p(f2 / r0)) * 4.5f) + 2.0f) / 100.0f, 1.0f);
    }

    public boolean a() {
        return this.f2139a;
    }

    private boolean a(int i) {
        return a.f.e.a.c(i, 255) == this.f2141c;
    }
}
