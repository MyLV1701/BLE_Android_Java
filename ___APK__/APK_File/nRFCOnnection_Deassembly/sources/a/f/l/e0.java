package a.f.l;

import android.os.Build;
import android.view.WindowInsets;

/* loaded from: classes.dex */
public class e0 {

    /* renamed from: a, reason: collision with root package name */
    private final Object f300a;

    e0(Object obj) {
        this.f300a = obj;
    }

    public e0 a() {
        if (Build.VERSION.SDK_INT >= 20) {
            return new e0(((WindowInsets) this.f300a).consumeSystemWindowInsets());
        }
        return null;
    }

    public int b() {
        if (Build.VERSION.SDK_INT >= 20) {
            return ((WindowInsets) this.f300a).getSystemWindowInsetBottom();
        }
        return 0;
    }

    public int c() {
        if (Build.VERSION.SDK_INT >= 20) {
            return ((WindowInsets) this.f300a).getSystemWindowInsetLeft();
        }
        return 0;
    }

    public int d() {
        if (Build.VERSION.SDK_INT >= 20) {
            return ((WindowInsets) this.f300a).getSystemWindowInsetRight();
        }
        return 0;
    }

    public int e() {
        if (Build.VERSION.SDK_INT >= 20) {
            return ((WindowInsets) this.f300a).getSystemWindowInsetTop();
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof e0) {
            return a.f.k.c.a(this.f300a, ((e0) obj).f300a);
        }
        return false;
    }

    public boolean f() {
        if (Build.VERSION.SDK_INT >= 20) {
            return ((WindowInsets) this.f300a).hasSystemWindowInsets();
        }
        return false;
    }

    public boolean g() {
        if (Build.VERSION.SDK_INT >= 21) {
            return ((WindowInsets) this.f300a).isConsumed();
        }
        return false;
    }

    public WindowInsets h() {
        return (WindowInsets) this.f300a;
    }

    public int hashCode() {
        Object obj = this.f300a;
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    public e0 a(int i, int i2, int i3, int i4) {
        if (Build.VERSION.SDK_INT >= 20) {
            return new e0(((WindowInsets) this.f300a).replaceSystemWindowInsets(i, i2, i3, i4));
        }
        return null;
    }

    public static e0 a(WindowInsets windowInsets) {
        windowInsets.getClass();
        return new e0(windowInsets);
    }
}
