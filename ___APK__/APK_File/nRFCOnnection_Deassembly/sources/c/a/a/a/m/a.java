package c.a.a.a.m;

import android.animation.TimeInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;

/* loaded from: classes.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    public static final TimeInterpolator f2093a = new LinearInterpolator();

    /* renamed from: b, reason: collision with root package name */
    public static final TimeInterpolator f2094b = new a.j.a.a.b();

    /* renamed from: c, reason: collision with root package name */
    public static final TimeInterpolator f2095c = new a.j.a.a.a();

    /* renamed from: d, reason: collision with root package name */
    public static final TimeInterpolator f2096d = new a.j.a.a.c();

    /* renamed from: e, reason: collision with root package name */
    public static final TimeInterpolator f2097e = new DecelerateInterpolator();

    public static float a(float f2, float f3, float f4) {
        return f2 + (f4 * (f3 - f2));
    }

    public static int a(int i, int i2, float f2) {
        return i + Math.round(f2 * (i2 - i));
    }
}
