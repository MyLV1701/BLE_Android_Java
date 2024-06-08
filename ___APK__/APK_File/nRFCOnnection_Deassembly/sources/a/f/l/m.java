package a.f.l;

import android.view.View;
import android.view.ViewParent;

/* loaded from: classes.dex */
public class m {

    /* renamed from: a, reason: collision with root package name */
    private ViewParent f320a;

    /* renamed from: b, reason: collision with root package name */
    private ViewParent f321b;

    /* renamed from: c, reason: collision with root package name */
    private final View f322c;

    /* renamed from: d, reason: collision with root package name */
    private boolean f323d;

    /* renamed from: e, reason: collision with root package name */
    private int[] f324e;

    public m(View view) {
        this.f322c = view;
    }

    private ViewParent d(int i) {
        if (i == 0) {
            return this.f320a;
        }
        if (i != 1) {
            return null;
        }
        return this.f321b;
    }

    public void a(boolean z) {
        if (this.f323d) {
            w.M(this.f322c);
        }
        this.f323d = z;
    }

    public boolean b() {
        return this.f323d;
    }

    public void c() {
        c(0);
    }

    public boolean b(int i) {
        return a(i, 0);
    }

    public void c(int i) {
        ViewParent d2 = d(i);
        if (d2 != null) {
            z.a(d2, this.f322c, i);
            a(i, (ViewParent) null);
        }
    }

    private boolean b(int i, int i2, int i3, int i4, int[] iArr, int i5, int[] iArr2) {
        ViewParent d2;
        int i6;
        int i7;
        int[] iArr3;
        if (!b() || (d2 = d(i5)) == null) {
            return false;
        }
        if (i == 0 && i2 == 0 && i3 == 0 && i4 == 0) {
            if (iArr != null) {
                iArr[0] = 0;
                iArr[1] = 0;
            }
            return false;
        }
        if (iArr != null) {
            this.f322c.getLocationInWindow(iArr);
            i6 = iArr[0];
            i7 = iArr[1];
        } else {
            i6 = 0;
            i7 = 0;
        }
        if (iArr2 == null) {
            int[] d3 = d();
            d3[0] = 0;
            d3[1] = 0;
            iArr3 = d3;
        } else {
            iArr3 = iArr2;
        }
        z.a(d2, this.f322c, i, i2, i3, i4, i5, iArr3);
        if (iArr != null) {
            this.f322c.getLocationInWindow(iArr);
            iArr[0] = iArr[0] - i6;
            iArr[1] = iArr[1] - i7;
        }
        return true;
    }

    private int[] d() {
        if (this.f324e == null) {
            this.f324e = new int[2];
        }
        return this.f324e;
    }

    public boolean a() {
        return a(0);
    }

    public boolean a(int i) {
        return d(i) != null;
    }

    public boolean a(int i, int i2) {
        if (a(i2)) {
            return true;
        }
        if (!b()) {
            return false;
        }
        View view = this.f322c;
        for (ViewParent parent = this.f322c.getParent(); parent != null; parent = parent.getParent()) {
            if (z.b(parent, view, this.f322c, i, i2)) {
                a(i2, parent);
                z.a(parent, view, this.f322c, i, i2);
                return true;
            }
            if (parent instanceof View) {
                view = (View) parent;
            }
        }
        return false;
    }

    public boolean a(int i, int i2, int i3, int i4, int[] iArr) {
        return b(i, i2, i3, i4, iArr, 0, null);
    }

    public boolean a(int i, int i2, int i3, int i4, int[] iArr, int i5) {
        return b(i, i2, i3, i4, iArr, i5, null);
    }

    public void a(int i, int i2, int i3, int i4, int[] iArr, int i5, int[] iArr2) {
        b(i, i2, i3, i4, iArr, i5, iArr2);
    }

    public boolean a(int i, int i2, int[] iArr, int[] iArr2) {
        return a(i, i2, iArr, iArr2, 0);
    }

    public boolean a(int i, int i2, int[] iArr, int[] iArr2, int i3) {
        ViewParent d2;
        int i4;
        int i5;
        if (!b() || (d2 = d(i3)) == null) {
            return false;
        }
        if (i == 0 && i2 == 0) {
            if (iArr2 != null) {
                iArr2[0] = 0;
                iArr2[1] = 0;
            }
            return false;
        }
        if (iArr2 != null) {
            this.f322c.getLocationInWindow(iArr2);
            i4 = iArr2[0];
            i5 = iArr2[1];
        } else {
            i4 = 0;
            i5 = 0;
        }
        if (iArr == null) {
            iArr = d();
        }
        iArr[0] = 0;
        iArr[1] = 0;
        z.a(d2, this.f322c, i, i2, iArr, i3);
        if (iArr2 != null) {
            this.f322c.getLocationInWindow(iArr2);
            iArr2[0] = iArr2[0] - i4;
            iArr2[1] = iArr2[1] - i5;
        }
        return (iArr[0] == 0 && iArr[1] == 0) ? false : true;
    }

    public boolean a(float f2, float f3, boolean z) {
        ViewParent d2;
        if (!b() || (d2 = d(0)) == null) {
            return false;
        }
        return z.a(d2, this.f322c, f2, f3, z);
    }

    public boolean a(float f2, float f3) {
        ViewParent d2;
        if (!b() || (d2 = d(0)) == null) {
            return false;
        }
        return z.a(d2, this.f322c, f2, f3);
    }

    private void a(int i, ViewParent viewParent) {
        if (i == 0) {
            this.f320a = viewParent;
        } else {
            if (i != 1) {
                return;
            }
            this.f321b = viewParent;
        }
    }
}
