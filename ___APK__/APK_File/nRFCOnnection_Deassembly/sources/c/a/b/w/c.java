package c.a.b.w;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

/* loaded from: classes.dex */
public final class c {

    /* renamed from: a, reason: collision with root package name */
    private final Map<Type, c.a.b.f<?>> f2201a;

    /* renamed from: b, reason: collision with root package name */
    private final c.a.b.w.o.b f2202b = c.a.b.w.o.b.a();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX INFO: Add missing generic type declarations: [T] */
    /* loaded from: classes.dex */
    public class a<T> implements c.a.b.w.i<T> {
        a(c cVar) {
        }

        @Override // c.a.b.w.i
        public T a() {
            return (T) new ConcurrentHashMap();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX INFO: Add missing generic type declarations: [T] */
    /* loaded from: classes.dex */
    public class b<T> implements c.a.b.w.i<T> {
        b(c cVar) {
        }

        @Override // c.a.b.w.i
        public T a() {
            return (T) new TreeMap();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX INFO: Add missing generic type declarations: [T] */
    /* renamed from: c.a.b.w.c$c, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public class C0076c<T> implements c.a.b.w.i<T> {
        C0076c(c cVar) {
        }

        @Override // c.a.b.w.i
        public T a() {
            return (T) new LinkedHashMap();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX INFO: Add missing generic type declarations: [T] */
    /* loaded from: classes.dex */
    public class d<T> implements c.a.b.w.i<T> {
        d(c cVar) {
        }

        @Override // c.a.b.w.i
        public T a() {
            return (T) new c.a.b.w.h();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX INFO: Add missing generic type declarations: [T] */
    /* loaded from: classes.dex */
    public class e<T> implements c.a.b.w.i<T> {

        /* renamed from: a, reason: collision with root package name */
        private final c.a.b.w.m f2203a = c.a.b.w.m.a();

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ Class f2204b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ Type f2205c;

        e(c cVar, Class cls, Type type) {
            this.f2204b = cls;
            this.f2205c = type;
        }

        @Override // c.a.b.w.i
        public T a() {
            try {
                return (T) this.f2203a.a(this.f2204b);
            } catch (Exception e2) {
                throw new RuntimeException("Unable to invoke no-args constructor for " + this.f2205c + ". Registering an InstanceCreator with Gson for this type may fix this problem.", e2);
            }
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* loaded from: classes.dex */
    class f<T> implements c.a.b.w.i<T> {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ c.a.b.f f2206a;

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ Type f2207b;

        f(c cVar, c.a.b.f fVar, Type type) {
            this.f2206a = fVar;
            this.f2207b = type;
        }

        @Override // c.a.b.w.i
        public T a() {
            return (T) this.f2206a.a(this.f2207b);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* loaded from: classes.dex */
    class g<T> implements c.a.b.w.i<T> {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ c.a.b.f f2208a;

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ Type f2209b;

        g(c cVar, c.a.b.f fVar, Type type) {
            this.f2208a = fVar;
            this.f2209b = type;
        }

        @Override // c.a.b.w.i
        public T a() {
            return (T) this.f2208a.a(this.f2209b);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX INFO: Add missing generic type declarations: [T] */
    /* loaded from: classes.dex */
    public class h<T> implements c.a.b.w.i<T> {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ Constructor f2210a;

        h(c cVar, Constructor constructor) {
            this.f2210a = constructor;
        }

        @Override // c.a.b.w.i
        public T a() {
            try {
                return (T) this.f2210a.newInstance(null);
            } catch (IllegalAccessException e2) {
                throw new AssertionError(e2);
            } catch (InstantiationException e3) {
                throw new RuntimeException("Failed to invoke " + this.f2210a + " with no args", e3);
            } catch (InvocationTargetException e4) {
                throw new RuntimeException("Failed to invoke " + this.f2210a + " with no args", e4.getTargetException());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX INFO: Add missing generic type declarations: [T] */
    /* loaded from: classes.dex */
    public class i<T> implements c.a.b.w.i<T> {
        i(c cVar) {
        }

        @Override // c.a.b.w.i
        public T a() {
            return (T) new TreeSet();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX INFO: Add missing generic type declarations: [T] */
    /* loaded from: classes.dex */
    public class j<T> implements c.a.b.w.i<T> {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ Type f2211a;

        j(c cVar, Type type) {
            this.f2211a = type;
        }

        @Override // c.a.b.w.i
        public T a() {
            Type type = this.f2211a;
            if (type instanceof ParameterizedType) {
                Type type2 = ((ParameterizedType) type).getActualTypeArguments()[0];
                if (type2 instanceof Class) {
                    return (T) EnumSet.noneOf((Class) type2);
                }
                throw new c.a.b.k("Invalid EnumSet type: " + this.f2211a.toString());
            }
            throw new c.a.b.k("Invalid EnumSet type: " + this.f2211a.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX INFO: Add missing generic type declarations: [T] */
    /* loaded from: classes.dex */
    public class k<T> implements c.a.b.w.i<T> {
        k(c cVar) {
        }

        @Override // c.a.b.w.i
        public T a() {
            return (T) new LinkedHashSet();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX INFO: Add missing generic type declarations: [T] */
    /* loaded from: classes.dex */
    public class l<T> implements c.a.b.w.i<T> {
        l(c cVar) {
        }

        @Override // c.a.b.w.i
        public T a() {
            return (T) new ArrayDeque();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX INFO: Add missing generic type declarations: [T] */
    /* loaded from: classes.dex */
    public class m<T> implements c.a.b.w.i<T> {
        m(c cVar) {
        }

        @Override // c.a.b.w.i
        public T a() {
            return (T) new ArrayList();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX INFO: Add missing generic type declarations: [T] */
    /* loaded from: classes.dex */
    public class n<T> implements c.a.b.w.i<T> {
        n(c cVar) {
        }

        @Override // c.a.b.w.i
        public T a() {
            return (T) new ConcurrentSkipListMap();
        }
    }

    public c(Map<Type, c.a.b.f<?>> map) {
        this.f2201a = map;
    }

    private <T> c.a.b.w.i<T> b(Type type, Class<? super T> cls) {
        return new e(this, cls, type);
    }

    public <T> c.a.b.w.i<T> a(c.a.b.x.a<T> aVar) {
        Type b2 = aVar.b();
        Class<? super T> a2 = aVar.a();
        c.a.b.f<?> fVar = this.f2201a.get(b2);
        if (fVar != null) {
            return new f(this, fVar, b2);
        }
        c.a.b.f<?> fVar2 = this.f2201a.get(a2);
        if (fVar2 != null) {
            return new g(this, fVar2, b2);
        }
        c.a.b.w.i<T> a3 = a(a2);
        if (a3 != null) {
            return a3;
        }
        c.a.b.w.i<T> a4 = a(b2, a2);
        return a4 != null ? a4 : b(b2, a2);
    }

    public String toString() {
        return this.f2201a.toString();
    }

    private <T> c.a.b.w.i<T> a(Class<? super T> cls) {
        try {
            Constructor<? super T> declaredConstructor = cls.getDeclaredConstructor(new Class[0]);
            if (!declaredConstructor.isAccessible()) {
                this.f2202b.a(declaredConstructor);
            }
            return new h(this, declaredConstructor);
        } catch (NoSuchMethodException unused) {
            return null;
        }
    }

    private <T> c.a.b.w.i<T> a(Type type, Class<? super T> cls) {
        if (Collection.class.isAssignableFrom(cls)) {
            if (SortedSet.class.isAssignableFrom(cls)) {
                return new i(this);
            }
            if (EnumSet.class.isAssignableFrom(cls)) {
                return new j(this, type);
            }
            if (Set.class.isAssignableFrom(cls)) {
                return new k(this);
            }
            if (Queue.class.isAssignableFrom(cls)) {
                return new l(this);
            }
            return new m(this);
        }
        if (!Map.class.isAssignableFrom(cls)) {
            return null;
        }
        if (ConcurrentNavigableMap.class.isAssignableFrom(cls)) {
            return new n(this);
        }
        if (ConcurrentMap.class.isAssignableFrom(cls)) {
            return new a(this);
        }
        if (SortedMap.class.isAssignableFrom(cls)) {
            return new b(this);
        }
        if ((type instanceof ParameterizedType) && !String.class.isAssignableFrom(c.a.b.x.a.a(((ParameterizedType) type).getActualTypeArguments()[0]).a())) {
            return new C0076c(this);
        }
        return new d(this);
    }
}
