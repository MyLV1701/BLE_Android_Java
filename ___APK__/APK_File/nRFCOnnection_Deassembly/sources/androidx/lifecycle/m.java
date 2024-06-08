package androidx.lifecycle;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class m {

    /* renamed from: a, reason: collision with root package name */
    private static Map<Class<?>, Integer> f1551a = new HashMap();

    /* renamed from: b, reason: collision with root package name */
    private static Map<Class<?>, List<Constructor<? extends d>>> f1552b = new HashMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static h a(Object obj) {
        boolean z = obj instanceof h;
        boolean z2 = obj instanceof c;
        if (z && z2) {
            return new FullLifecycleObserverAdapter((c) obj, (h) obj);
        }
        if (z2) {
            return new FullLifecycleObserverAdapter((c) obj, null);
        }
        if (z) {
            return (h) obj;
        }
        Class<?> cls = obj.getClass();
        if (b(cls) == 2) {
            List<Constructor<? extends d>> list = f1552b.get(cls);
            if (list.size() == 1) {
                return new SingleGeneratedAdapterObserver(a(list.get(0), obj));
            }
            d[] dVarArr = new d[list.size()];
            for (int i = 0; i < list.size(); i++) {
                dVarArr[i] = a(list.get(i), obj);
            }
            return new CompositeGeneratedAdaptersObserver(dVarArr);
        }
        return new ReflectiveGenericLifecycleObserver(obj);
    }

    private static int b(Class<?> cls) {
        Integer num = f1551a.get(cls);
        if (num != null) {
            return num.intValue();
        }
        int d2 = d(cls);
        f1551a.put(cls, Integer.valueOf(d2));
        return d2;
    }

    private static boolean c(Class<?> cls) {
        return cls != null && i.class.isAssignableFrom(cls);
    }

    private static int d(Class<?> cls) {
        if (cls.getCanonicalName() == null) {
            return 1;
        }
        Constructor<? extends d> a2 = a(cls);
        if (a2 != null) {
            f1552b.put(cls, Collections.singletonList(a2));
            return 2;
        }
        if (b.f1529c.b(cls)) {
            return 1;
        }
        Class<? super Object> superclass = cls.getSuperclass();
        ArrayList arrayList = null;
        if (c(superclass)) {
            if (b(superclass) == 1) {
                return 1;
            }
            arrayList = new ArrayList(f1552b.get(superclass));
        }
        for (Class<?> cls2 : cls.getInterfaces()) {
            if (c(cls2)) {
                if (b(cls2) == 1) {
                    return 1;
                }
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.addAll(f1552b.get(cls2));
            }
        }
        if (arrayList == null) {
            return 1;
        }
        f1552b.put(cls, arrayList);
        return 2;
    }

    private static d a(Constructor<? extends d> constructor, Object obj) {
        try {
            return constructor.newInstance(obj);
        } catch (IllegalAccessException e2) {
            throw new RuntimeException(e2);
        } catch (InstantiationException e3) {
            throw new RuntimeException(e3);
        } catch (InvocationTargetException e4) {
            throw new RuntimeException(e4);
        }
    }

    private static Constructor<? extends d> a(Class<?> cls) {
        try {
            Package r0 = cls.getPackage();
            String canonicalName = cls.getCanonicalName();
            String name = r0 != null ? r0.getName() : "";
            if (!name.isEmpty()) {
                canonicalName = canonicalName.substring(name.length() + 1);
            }
            String a2 = a(canonicalName);
            if (!name.isEmpty()) {
                a2 = name + "." + a2;
            }
            Constructor declaredConstructor = Class.forName(a2).getDeclaredConstructor(cls);
            if (!declaredConstructor.isAccessible()) {
                declaredConstructor.setAccessible(true);
            }
            return declaredConstructor;
        } catch (ClassNotFoundException unused) {
            return null;
        } catch (NoSuchMethodException e2) {
            throw new RuntimeException(e2);
        }
    }

    public static String a(String str) {
        return str.replace(".", "_") + "_LifecycleAdapter";
    }
}
