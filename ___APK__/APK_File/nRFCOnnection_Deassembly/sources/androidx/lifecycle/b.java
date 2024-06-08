package androidx.lifecycle;

import androidx.lifecycle.g;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
class b {

    /* renamed from: c, reason: collision with root package name */
    static b f1529c = new b();

    /* renamed from: a, reason: collision with root package name */
    private final Map<Class<?>, a> f1530a = new HashMap();

    /* renamed from: b, reason: collision with root package name */
    private final Map<Class<?>, Boolean> f1531b = new HashMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: androidx.lifecycle.b$b, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static class C0053b {

        /* renamed from: a, reason: collision with root package name */
        final int f1534a;

        /* renamed from: b, reason: collision with root package name */
        final Method f1535b;

        C0053b(int i, Method method) {
            this.f1534a = i;
            this.f1535b = method;
            this.f1535b.setAccessible(true);
        }

        void a(j jVar, g.a aVar, Object obj) {
            try {
                int i = this.f1534a;
                if (i == 0) {
                    this.f1535b.invoke(obj, new Object[0]);
                } else if (i == 1) {
                    this.f1535b.invoke(obj, jVar);
                } else {
                    if (i != 2) {
                        return;
                    }
                    this.f1535b.invoke(obj, jVar, aVar);
                }
            } catch (IllegalAccessException e2) {
                throw new RuntimeException(e2);
            } catch (InvocationTargetException e3) {
                throw new RuntimeException("Failed to call observer method", e3.getCause());
            }
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || C0053b.class != obj.getClass()) {
                return false;
            }
            C0053b c0053b = (C0053b) obj;
            return this.f1534a == c0053b.f1534a && this.f1535b.getName().equals(c0053b.f1535b.getName());
        }

        public int hashCode() {
            return (this.f1534a * 31) + this.f1535b.getName().hashCode();
        }
    }

    b() {
    }

    private Method[] c(Class<?> cls) {
        try {
            return cls.getDeclaredMethods();
        } catch (NoClassDefFoundError e2) {
            throw new IllegalArgumentException("The observer class has some methods that use newer APIs which are not available in the current OS version. Lifecycles cannot access even other methods so you should make sure that your observer classes only access framework classes that are available in your min API level OR use lifecycle:compiler annotation processor.", e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public a a(Class<?> cls) {
        a aVar = this.f1530a.get(cls);
        return aVar != null ? aVar : a(cls, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean b(Class<?> cls) {
        Boolean bool = this.f1531b.get(cls);
        if (bool != null) {
            return bool.booleanValue();
        }
        Method[] c2 = c(cls);
        for (Method method : c2) {
            if (((q) method.getAnnotation(q.class)) != null) {
                a(cls, c2);
                return true;
            }
        }
        this.f1531b.put(cls, false);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a, reason: collision with root package name */
        final Map<g.a, List<C0053b>> f1532a = new HashMap();

        /* renamed from: b, reason: collision with root package name */
        final Map<C0053b, g.a> f1533b;

        a(Map<C0053b, g.a> map) {
            this.f1533b = map;
            for (Map.Entry<C0053b, g.a> entry : map.entrySet()) {
                g.a value = entry.getValue();
                List<C0053b> list = this.f1532a.get(value);
                if (list == null) {
                    list = new ArrayList<>();
                    this.f1532a.put(value, list);
                }
                list.add(entry.getKey());
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void a(j jVar, g.a aVar, Object obj) {
            a(this.f1532a.get(aVar), jVar, aVar, obj);
            a(this.f1532a.get(g.a.ON_ANY), jVar, aVar, obj);
        }

        private static void a(List<C0053b> list, j jVar, g.a aVar, Object obj) {
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    list.get(size).a(jVar, aVar, obj);
                }
            }
        }
    }

    private void a(Map<C0053b, g.a> map, C0053b c0053b, g.a aVar, Class<?> cls) {
        g.a aVar2 = map.get(c0053b);
        if (aVar2 == null || aVar == aVar2) {
            if (aVar2 == null) {
                map.put(c0053b, aVar);
                return;
            }
            return;
        }
        throw new IllegalArgumentException("Method " + c0053b.f1535b.getName() + " in " + cls.getName() + " already declared with different @OnLifecycleEvent value: previous value " + aVar2 + ", new value " + aVar);
    }

    private a a(Class<?> cls, Method[] methodArr) {
        int i;
        a a2;
        Class<? super Object> superclass = cls.getSuperclass();
        HashMap hashMap = new HashMap();
        if (superclass != null && (a2 = a(superclass)) != null) {
            hashMap.putAll(a2.f1533b);
        }
        for (Class<?> cls2 : cls.getInterfaces()) {
            for (Map.Entry<C0053b, g.a> entry : a(cls2).f1533b.entrySet()) {
                a(hashMap, entry.getKey(), entry.getValue(), cls);
            }
        }
        if (methodArr == null) {
            methodArr = c(cls);
        }
        boolean z = false;
        for (Method method : methodArr) {
            q qVar = (q) method.getAnnotation(q.class);
            if (qVar != null) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length <= 0) {
                    i = 0;
                } else {
                    if (!parameterTypes[0].isAssignableFrom(j.class)) {
                        throw new IllegalArgumentException("invalid parameter type. Must be one and instanceof LifecycleOwner");
                    }
                    i = 1;
                }
                g.a value = qVar.value();
                if (parameterTypes.length > 1) {
                    if (parameterTypes[1].isAssignableFrom(g.a.class)) {
                        if (value != g.a.ON_ANY) {
                            throw new IllegalArgumentException("Second arg is supported only for ON_ANY value");
                        }
                        i = 2;
                    } else {
                        throw new IllegalArgumentException("invalid parameter type. second arg must be an event");
                    }
                }
                if (parameterTypes.length <= 2) {
                    a(hashMap, new C0053b(i, method), value, cls);
                    z = true;
                } else {
                    throw new IllegalArgumentException("cannot have more than 2 params");
                }
            }
        }
        a aVar = new a(hashMap);
        this.f1530a.put(cls, aVar);
        this.f1531b.put(cls, Boolean.valueOf(z));
        return aVar;
    }
}
