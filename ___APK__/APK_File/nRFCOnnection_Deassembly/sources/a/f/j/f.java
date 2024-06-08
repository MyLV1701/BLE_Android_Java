package a.f.j;

import android.os.Build;
import android.text.TextUtils;
import java.util.Locale;

/* loaded from: classes.dex */
public final class f {

    /* renamed from: a, reason: collision with root package name */
    private static final Locale f265a = new Locale("", "");

    private static int a(Locale locale) {
        byte directionality = Character.getDirectionality(locale.getDisplayName(locale).charAt(0));
        return (directionality == 1 || directionality == 2) ? 1 : 0;
    }

    public static int b(Locale locale) {
        if (Build.VERSION.SDK_INT >= 17) {
            return TextUtils.getLayoutDirectionFromLocale(locale);
        }
        if (locale == null || locale.equals(f265a)) {
            return 0;
        }
        String b2 = b.b(locale);
        if (b2 == null) {
            return a(locale);
        }
        return (b2.equalsIgnoreCase("Arab") || b2.equalsIgnoreCase("Hebr")) ? 1 : 0;
    }
}
