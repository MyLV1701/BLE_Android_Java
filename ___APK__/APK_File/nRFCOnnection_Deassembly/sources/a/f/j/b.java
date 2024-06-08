package a.f.j;

import android.icu.util.ULocale;
import android.os.Build;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;

/* loaded from: classes.dex */
public final class b {

    /* renamed from: a, reason: collision with root package name */
    private static Method f242a;

    /* renamed from: b, reason: collision with root package name */
    private static Method f243b;

    static {
        int i = Build.VERSION.SDK_INT;
        if (i >= 21) {
            if (i < 24) {
                try {
                    f243b = Class.forName("libcore.icu.ICU").getMethod("addLikelySubtags", Locale.class);
                    return;
                } catch (Exception e2) {
                    throw new IllegalStateException(e2);
                }
            }
            return;
        }
        try {
            Class<?> cls = Class.forName("libcore.icu.ICU");
            if (cls != null) {
                f242a = cls.getMethod("getScript", String.class);
                f243b = cls.getMethod("addLikelySubtags", String.class);
            }
        } catch (Exception e3) {
            f242a = null;
            f243b = null;
            Log.w("ICUCompat", e3);
        }
    }

    private static String a(String str) {
        try {
            if (f242a != null) {
                return (String) f242a.invoke(null, str);
            }
        } catch (IllegalAccessException e2) {
            Log.w("ICUCompat", e2);
        } catch (InvocationTargetException e3) {
            Log.w("ICUCompat", e3);
        }
        return null;
    }

    public static String b(Locale locale) {
        int i = Build.VERSION.SDK_INT;
        if (i >= 24) {
            return ULocale.addLikelySubtags(ULocale.forLocale(locale)).getScript();
        }
        if (i >= 21) {
            try {
                return ((Locale) f243b.invoke(null, locale)).getScript();
            } catch (IllegalAccessException e2) {
                Log.w("ICUCompat", e2);
                return locale.getScript();
            } catch (InvocationTargetException e3) {
                Log.w("ICUCompat", e3);
                return locale.getScript();
            }
        }
        String a2 = a(locale);
        if (a2 != null) {
            return a(a2);
        }
        return null;
    }

    private static String a(Locale locale) {
        String locale2 = locale.toString();
        try {
            if (f243b != null) {
                return (String) f243b.invoke(null, locale2);
            }
        } catch (IllegalAccessException e2) {
            Log.w("ICUCompat", e2);
        } catch (InvocationTargetException e3) {
            Log.w("ICUCompat", e3);
        }
        return locale2;
    }
}
