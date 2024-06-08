package a.n;

import android.annotation.SuppressLint;
import android.os.Build;
import android.view.ViewGroup;

/* loaded from: classes.dex */
class x {

    /* renamed from: a, reason: collision with root package name */
    private static boolean f525a = true;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static w a(ViewGroup viewGroup) {
        if (Build.VERSION.SDK_INT >= 18) {
            return new v(viewGroup);
        }
        return u.a(viewGroup);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void b(ViewGroup viewGroup, boolean z) {
        int i = Build.VERSION.SDK_INT;
        if (i >= 29) {
            viewGroup.suppressLayout(z);
        } else if (i >= 18) {
            a(viewGroup, z);
        } else {
            y.a(viewGroup, z);
        }
    }

    @SuppressLint({"NewApi"})
    private static void a(ViewGroup viewGroup, boolean z) {
        if (f525a) {
            try {
                viewGroup.suppressLayout(z);
            } catch (NoSuchMethodError unused) {
                f525a = false;
            }
        }
    }
}
