package c.a.b.w;

import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/* loaded from: classes.dex */
public abstract class m {

    /* loaded from: classes.dex */
    class a extends m {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ Method f2242a;

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ Object f2243b;

        a(Method method, Object obj) {
            this.f2242a = method;
            this.f2243b = obj;
        }

        @Override // c.a.b.w.m
        public <T> T a(Class<T> cls) {
            m.b(cls);
            return (T) this.f2242a.invoke(this.f2243b, cls);
        }
    }

    /* loaded from: classes.dex */
    class b extends m {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ Method f2244a;

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f2245b;

        b(Method method, int i) {
            this.f2244a = method;
            this.f2245b = i;
        }

        @Override // c.a.b.w.m
        public <T> T a(Class<T> cls) {
            m.b(cls);
            return (T) this.f2244a.invoke(null, cls, Integer.valueOf(this.f2245b));
        }
    }

    /* loaded from: classes.dex */
    class c extends m {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ Method f2246a;

        c(Method method) {
            this.f2246a = method;
        }

        @Override // c.a.b.w.m
        public <T> T a(Class<T> cls) {
            m.b(cls);
            return (T) this.f2246a.invoke(null, cls, Object.class);
        }
    }

    /* loaded from: classes.dex */
    class d extends m {
        d() {
        }

        @Override // c.a.b.w.m
        public <T> T a(Class<T> cls) {
            throw new UnsupportedOperationException("Cannot allocate " + cls);
        }
    }

    public static m a() {
        try {
            Class<?> cls = Class.forName("sun.misc.Unsafe");
            Field declaredField = cls.getDeclaredField("theUnsafe");
            declaredField.setAccessible(true);
            return new a(cls.getMethod("allocateInstance", Class.class), declaredField.get(null));
        } catch (Exception unused) {
            try {
                try {
                    Method declaredMethod = ObjectStreamClass.class.getDeclaredMethod("getConstructorId", Class.class);
                    declaredMethod.setAccessible(true);
                    int intValue = ((Integer) declaredMethod.invoke(null, Object.class)).intValue();
                    Method declaredMethod2 = ObjectStreamClass.class.getDeclaredMethod("newInstance", Class.class, Integer.TYPE);
                    declaredMethod2.setAccessible(true);
                    return new b(declaredMethod2, intValue);
                } catch (Exception unused2) {
                    return new d();
                }
            } catch (Exception unused3) {
                Method declaredMethod3 = ObjectInputStream.class.getDeclaredMethod("newInstance", Class.class, Class.class);
                declaredMethod3.setAccessible(true);
                return new c(declaredMethod3);
            }
        }
    }

    static void b(Class<?> cls) {
        int modifiers = cls.getModifiers();
        if (!Modifier.isInterface(modifiers)) {
            if (Modifier.isAbstract(modifiers)) {
                throw new UnsupportedOperationException("Abstract class can't be instantiated! Class name: " + cls.getName());
            }
            return;
        }
        throw new UnsupportedOperationException("Interface can't be instantiated! Interface name: " + cls.getName());
    }

    public abstract <T> T a(Class<T> cls);
}
