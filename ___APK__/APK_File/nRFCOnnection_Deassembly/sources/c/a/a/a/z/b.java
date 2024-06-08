package c.a.a.a.z;

import android.R;
import android.annotation.TargetApi;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.util.StateSet;

/* loaded from: classes.dex */
public class b {

    /* renamed from: a, reason: collision with root package name */
    public static final boolean f2166a;

    /* renamed from: b, reason: collision with root package name */
    private static final int[] f2167b;

    /* renamed from: c, reason: collision with root package name */
    private static final int[] f2168c;

    /* renamed from: d, reason: collision with root package name */
    private static final int[] f2169d;

    /* renamed from: e, reason: collision with root package name */
    private static final int[] f2170e;

    /* renamed from: f, reason: collision with root package name */
    private static final int[] f2171f;
    private static final int[] g;
    private static final int[] h;
    private static final int[] i;
    private static final int[] j;
    private static final int[] k;
    static final String l;

    static {
        f2166a = Build.VERSION.SDK_INT >= 21;
        f2167b = new int[]{R.attr.state_pressed};
        f2168c = new int[]{R.attr.state_hovered, R.attr.state_focused};
        f2169d = new int[]{R.attr.state_focused};
        f2170e = new int[]{R.attr.state_hovered};
        f2171f = new int[]{R.attr.state_selected, R.attr.state_pressed};
        g = new int[]{R.attr.state_selected, R.attr.state_hovered, R.attr.state_focused};
        h = new int[]{R.attr.state_selected, R.attr.state_focused};
        i = new int[]{R.attr.state_selected, R.attr.state_hovered};
        j = new int[]{R.attr.state_selected};
        k = new int[]{R.attr.state_enabled, R.attr.state_pressed};
        l = b.class.getSimpleName();
    }

    private b() {
    }

    public static ColorStateList a(ColorStateList colorStateList) {
        if (f2166a) {
            return new ColorStateList(new int[][]{j, StateSet.NOTHING}, new int[]{a(colorStateList, f2171f), a(colorStateList, f2167b)});
        }
        int[] iArr = f2171f;
        int[] iArr2 = g;
        int[] iArr3 = h;
        int[] iArr4 = i;
        int[] iArr5 = f2167b;
        int[] iArr6 = f2168c;
        int[] iArr7 = f2169d;
        int[] iArr8 = f2170e;
        return new ColorStateList(new int[][]{iArr, iArr2, iArr3, iArr4, j, iArr5, iArr6, iArr7, iArr8, StateSet.NOTHING}, new int[]{a(colorStateList, iArr), a(colorStateList, iArr2), a(colorStateList, iArr3), a(colorStateList, iArr4), 0, a(colorStateList, iArr5), a(colorStateList, iArr6), a(colorStateList, iArr7), a(colorStateList, iArr8), 0});
    }

    public static ColorStateList b(ColorStateList colorStateList) {
        if (colorStateList != null) {
            int i2 = Build.VERSION.SDK_INT;
            if (i2 >= 22 && i2 <= 27 && Color.alpha(colorStateList.getDefaultColor()) == 0 && Color.alpha(colorStateList.getColorForState(k, 0)) != 0) {
                Log.w(l, "Use a non-transparent color for the default color as it will be used to finish ripple animations.");
            }
            return colorStateList;
        }
        return ColorStateList.valueOf(0);
    }

    public static boolean a(int[] iArr) {
        boolean z = false;
        boolean z2 = false;
        for (int i2 : iArr) {
            if (i2 == 16842910) {
                z = true;
            } else if (i2 == 16842908 || i2 == 16842919 || i2 == 16843623) {
                z2 = true;
            }
        }
        return z && z2;
    }

    private static int a(ColorStateList colorStateList, int[] iArr) {
        int colorForState = colorStateList != null ? colorStateList.getColorForState(iArr, colorStateList.getDefaultColor()) : 0;
        return f2166a ? a(colorForState) : colorForState;
    }

    @TargetApi(21)
    private static int a(int i2) {
        return a.f.e.a.c(i2, Math.min(Color.alpha(i2) * 2, 255));
    }
}
