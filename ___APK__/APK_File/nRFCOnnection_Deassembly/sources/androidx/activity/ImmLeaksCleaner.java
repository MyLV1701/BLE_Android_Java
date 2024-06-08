package androidx.activity;

import android.app.Activity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import androidx.lifecycle.g;
import androidx.lifecycle.h;
import androidx.lifecycle.j;
import java.lang.reflect.Field;

/* loaded from: classes.dex */
final class ImmLeaksCleaner implements h {

    /* renamed from: b, reason: collision with root package name */
    private static int f594b;

    /* renamed from: c, reason: collision with root package name */
    private static Field f595c;

    /* renamed from: d, reason: collision with root package name */
    private static Field f596d;

    /* renamed from: e, reason: collision with root package name */
    private static Field f597e;

    /* renamed from: a, reason: collision with root package name */
    private Activity f598a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ImmLeaksCleaner(Activity activity) {
        this.f598a = activity;
    }

    @Override // androidx.lifecycle.h
    public void a(j jVar, g.a aVar) {
        if (aVar != g.a.ON_DESTROY) {
            return;
        }
        if (f594b == 0) {
            a();
        }
        if (f594b == 1) {
            InputMethodManager inputMethodManager = (InputMethodManager) this.f598a.getSystemService("input_method");
            try {
                Object obj = f595c.get(inputMethodManager);
                if (obj == null) {
                    return;
                }
                synchronized (obj) {
                    try {
                        try {
                            View view = (View) f596d.get(inputMethodManager);
                            if (view == null) {
                                return;
                            }
                            if (view.isAttachedToWindow()) {
                                return;
                            }
                            try {
                                f597e.set(inputMethodManager, null);
                                inputMethodManager.isActive();
                            } catch (IllegalAccessException unused) {
                            }
                        } catch (Throwable th) {
                            throw th;
                        }
                    } catch (ClassCastException unused2) {
                    } catch (IllegalAccessException unused3) {
                    }
                }
            } catch (IllegalAccessException unused4) {
            }
        }
    }

    private static void a() {
        try {
            f594b = 2;
            f596d = InputMethodManager.class.getDeclaredField("mServedView");
            f596d.setAccessible(true);
            f597e = InputMethodManager.class.getDeclaredField("mNextServedView");
            f597e.setAccessible(true);
            f595c = InputMethodManager.class.getDeclaredField("mH");
            f595c.setAccessible(true);
            f594b = 1;
        } catch (NoSuchFieldException unused) {
        }
    }
}
