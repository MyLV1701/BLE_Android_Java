package androidx.lifecycle;

import android.annotation.SuppressLint;
import android.app.Application;
import android.os.Bundle;
import androidx.lifecycle.v;
import androidx.savedstate.SavedStateRegistry;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

/* loaded from: classes.dex */
public final class t extends v.c {

    /* renamed from: f, reason: collision with root package name */
    private static final Class<?>[] f1557f = {Application.class, s.class};
    private static final Class<?>[] g = {s.class};

    /* renamed from: a, reason: collision with root package name */
    private final Application f1558a;

    /* renamed from: b, reason: collision with root package name */
    private final v.a f1559b;

    /* renamed from: c, reason: collision with root package name */
    private final Bundle f1560c;

    /* renamed from: d, reason: collision with root package name */
    private final g f1561d;

    /* renamed from: e, reason: collision with root package name */
    private final SavedStateRegistry f1562e;

    @SuppressLint({"LambdaLast"})
    public t(Application application, androidx.savedstate.b bVar, Bundle bundle) {
        this.f1562e = bVar.getSavedStateRegistry();
        this.f1561d = bVar.getLifecycle();
        this.f1560c = bundle;
        this.f1558a = application;
        this.f1559b = v.a.a(application);
    }

    @Override // androidx.lifecycle.v.c
    public <T extends u> T a(String str, Class<T> cls) {
        Constructor a2;
        boolean isAssignableFrom = a.class.isAssignableFrom(cls);
        if (isAssignableFrom) {
            a2 = a(cls, f1557f);
        } else {
            a2 = a(cls, g);
        }
        if (a2 == null) {
            return (T) this.f1559b.a(cls);
        }
        SavedStateHandleController a3 = SavedStateHandleController.a(this.f1562e, this.f1561d, str, this.f1560c);
        try {
            T t = isAssignableFrom ? (T) a2.newInstance(this.f1558a, a3.a()) : (T) a2.newInstance(a3.a());
            t.a("androidx.lifecycle.savedstate.vm.tag", a3);
            return t;
        } catch (IllegalAccessException e2) {
            throw new RuntimeException("Failed to access " + cls, e2);
        } catch (InstantiationException e3) {
            throw new RuntimeException("A " + cls + " cannot be instantiated.", e3);
        } catch (InvocationTargetException e4) {
            throw new RuntimeException("An exception happened in constructor of " + cls, e4.getCause());
        }
    }

    @Override // androidx.lifecycle.v.c, androidx.lifecycle.v.b
    public <T extends u> T a(Class<T> cls) {
        String canonicalName = cls.getCanonicalName();
        if (canonicalName != null) {
            return (T) a(canonicalName, cls);
        }
        throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
    }

    private static <T> Constructor<T> a(Class<T> cls, Class<?>[] clsArr) {
        for (Object obj : cls.getConstructors()) {
            Constructor<T> constructor = (Constructor<T>) obj;
            if (Arrays.equals(clsArr, constructor.getParameterTypes())) {
                return constructor;
            }
        }
        return null;
    }

    @Override // androidx.lifecycle.v.e
    void a(u uVar) {
        SavedStateHandleController.a(uVar, this.f1562e, this.f1561d);
    }
}
