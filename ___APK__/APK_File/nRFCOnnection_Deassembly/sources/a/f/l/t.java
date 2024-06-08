package a.f.l;

import android.content.Context;
import android.os.Build;
import android.view.PointerIcon;

/* loaded from: classes.dex */
public final class t {

    /* renamed from: a, reason: collision with root package name */
    private Object f330a;

    private t(Object obj) {
        this.f330a = obj;
    }

    public Object a() {
        return this.f330a;
    }

    public static t a(Context context, int i) {
        if (Build.VERSION.SDK_INT >= 24) {
            return new t(PointerIcon.getSystemIcon(context, i));
        }
        return new t(null);
    }
}
