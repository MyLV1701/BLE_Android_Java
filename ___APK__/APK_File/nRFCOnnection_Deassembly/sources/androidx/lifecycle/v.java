package androidx.lifecycle;

import android.app.Application;
import java.lang.reflect.InvocationTargetException;

/* loaded from: classes.dex */
public class v {

    /* renamed from: a, reason: collision with root package name */
    private final b f1565a;

    /* renamed from: b, reason: collision with root package name */
    private final w f1566b;

    /* loaded from: classes.dex */
    public interface b {
        <T extends u> T a(Class<T> cls);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static abstract class c extends e implements b {
        public <T extends u> T a(Class<T> cls) {
            throw new UnsupportedOperationException("create(String, Class<?>) must be called on implementaions of KeyedFactory");
        }

        public abstract <T extends u> T a(String str, Class<T> cls);
    }

    /* loaded from: classes.dex */
    public static class d implements b {
        @Override // androidx.lifecycle.v.b
        public <T extends u> T a(Class<T> cls) {
            try {
                return cls.newInstance();
            } catch (IllegalAccessException e2) {
                throw new RuntimeException("Cannot create an instance of " + cls, e2);
            } catch (InstantiationException e3) {
                throw new RuntimeException("Cannot create an instance of " + cls, e3);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class e {
        e() {
        }

        void a(u uVar) {
        }
    }

    public v(w wVar, b bVar) {
        this.f1565a = bVar;
        this.f1566b = wVar;
    }

    public <T extends u> T a(Class<T> cls) {
        String canonicalName = cls.getCanonicalName();
        if (canonicalName != null) {
            return (T) a("androidx.lifecycle.ViewModelProvider.DefaultKey:" + canonicalName, cls);
        }
        throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
    }

    /* loaded from: classes.dex */
    public static class a extends d {

        /* renamed from: b, reason: collision with root package name */
        private static a f1567b;

        /* renamed from: a, reason: collision with root package name */
        private Application f1568a;

        public a(Application application) {
            this.f1568a = application;
        }

        public static a a(Application application) {
            if (f1567b == null) {
                f1567b = new a(application);
            }
            return f1567b;
        }

        @Override // androidx.lifecycle.v.d, androidx.lifecycle.v.b
        public <T extends u> T a(Class<T> cls) {
            if (androidx.lifecycle.a.class.isAssignableFrom(cls)) {
                try {
                    return cls.getConstructor(Application.class).newInstance(this.f1568a);
                } catch (IllegalAccessException e2) {
                    throw new RuntimeException("Cannot create an instance of " + cls, e2);
                } catch (InstantiationException e3) {
                    throw new RuntimeException("Cannot create an instance of " + cls, e3);
                } catch (NoSuchMethodException e4) {
                    throw new RuntimeException("Cannot create an instance of " + cls, e4);
                } catch (InvocationTargetException e5) {
                    throw new RuntimeException("Cannot create an instance of " + cls, e5);
                }
            }
            return (T) super.a(cls);
        }
    }

    public <T extends u> T a(String str, Class<T> cls) {
        T t;
        T t2 = (T) this.f1566b.a(str);
        if (cls.isInstance(t2)) {
            Object obj = this.f1565a;
            if (obj instanceof e) {
                ((e) obj).a(t2);
            }
            return t2;
        }
        b bVar = this.f1565a;
        if (bVar instanceof c) {
            t = (T) ((c) bVar).a(str, cls);
        } else {
            t = (T) bVar.a(cls);
        }
        this.f1566b.a(str, t);
        return t;
    }
}
