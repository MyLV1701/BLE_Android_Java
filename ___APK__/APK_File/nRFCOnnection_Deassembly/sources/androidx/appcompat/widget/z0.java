package androidx.appcompat.widget;

import android.graphics.Rect;
import android.os.Build;
import android.util.Log;
import android.view.View;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
public class z0 {

    /* renamed from: a, reason: collision with root package name */
    private static Method f1140a;

    static {
        if (Build.VERSION.SDK_INT >= 18) {
            try {
                f1140a = View.class.getDeclaredMethod("computeFitSystemWindows", Rect.class, Rect.class);
                if (f1140a.isAccessible()) {
                    return;
                }
                f1140a.setAccessible(true);
            } catch (NoSuchMethodException unused) {
                Log.d("ViewUtils", "Could not find method computeFitSystemWindows. Oh well.");
            }
        }
    }

    public static boolean a(View view) {
        return a.f.l.w.q(view) == 1;
    }

    public static void b(View view) {
        if (Build.VERSION.SDK_INT >= 16) {
            try {
                Method method = view.getClass().getMethod("makeOptionalFitsSystemWindows", new Class[0]);
                if (!method.isAccessible()) {
                    method.setAccessible(true);
                }
                method.invoke(view, new Object[0]);
            } catch (IllegalAccessException e2) {
                Log.d("ViewUtils", "Could not invoke makeOptionalFitsSystemWindows", e2);
            } catch (NoSuchMethodException unused) {
                Log.d("ViewUtils", "Could not find method makeOptionalFitsSystemWindows. Oh well...");
            } catch (InvocationTargetException e3) {
                Log.d("ViewUtils", "Could not invoke makeOptionalFitsSystemWindows", e3);
            }
        }
    }

    public static void a(View view, Rect rect, Rect rect2) {
        Method method = f1140a;
        if (method != null) {
            try {
                method.invoke(view, rect, rect2);
            } catch (Exception e2) {
                Log.d("ViewUtils", "Could not invoke computeFitSystemWindows", e2);
            }
        }
    }
}
