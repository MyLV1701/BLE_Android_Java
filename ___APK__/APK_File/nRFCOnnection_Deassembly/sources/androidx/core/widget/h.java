package androidx.core.widget;

import a.f.l.w;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.PopupWindow;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
public final class h {

    /* renamed from: a, reason: collision with root package name */
    private static Method f1317a;

    /* renamed from: b, reason: collision with root package name */
    private static boolean f1318b;

    /* renamed from: c, reason: collision with root package name */
    private static Field f1319c;

    /* renamed from: d, reason: collision with root package name */
    private static boolean f1320d;

    public static void a(PopupWindow popupWindow, View view, int i, int i2, int i3) {
        if (Build.VERSION.SDK_INT >= 19) {
            popupWindow.showAsDropDown(view, i, i2, i3);
            return;
        }
        if ((a.f.l.d.a(i3, w.q(view)) & 7) == 5) {
            i -= popupWindow.getWidth() - view.getWidth();
        }
        popupWindow.showAsDropDown(view, i, i2);
    }

    public static void a(PopupWindow popupWindow, boolean z) {
        int i = Build.VERSION.SDK_INT;
        if (i >= 23) {
            popupWindow.setOverlapAnchor(z);
            return;
        }
        if (i >= 21) {
            if (!f1320d) {
                try {
                    f1319c = PopupWindow.class.getDeclaredField("mOverlapAnchor");
                    f1319c.setAccessible(true);
                } catch (NoSuchFieldException e2) {
                    Log.i("PopupWindowCompatApi21", "Could not fetch mOverlapAnchor field from PopupWindow", e2);
                }
                f1320d = true;
            }
            Field field = f1319c;
            if (field != null) {
                try {
                    field.set(popupWindow, Boolean.valueOf(z));
                } catch (IllegalAccessException e3) {
                    Log.i("PopupWindowCompatApi21", "Could not set overlap anchor field in PopupWindow", e3);
                }
            }
        }
    }

    public static void a(PopupWindow popupWindow, int i) {
        if (Build.VERSION.SDK_INT >= 23) {
            popupWindow.setWindowLayoutType(i);
            return;
        }
        if (!f1318b) {
            try {
                f1317a = PopupWindow.class.getDeclaredMethod("setWindowLayoutType", Integer.TYPE);
                f1317a.setAccessible(true);
            } catch (Exception unused) {
            }
            f1318b = true;
        }
        Method method = f1317a;
        if (method != null) {
            try {
                method.invoke(popupWindow, Integer.valueOf(i));
            } catch (Exception unused2) {
            }
        }
    }
}
