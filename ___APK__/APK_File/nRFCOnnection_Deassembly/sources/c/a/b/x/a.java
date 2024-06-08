package c.a.b.x;

import c.a.b.w.b;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/* loaded from: classes.dex */
public class a<T> {

    /* renamed from: a, reason: collision with root package name */
    final Class<? super T> f2318a;

    /* renamed from: b, reason: collision with root package name */
    final Type f2319b;

    /* renamed from: c, reason: collision with root package name */
    final int f2320c;

    protected a() {
        this.f2319b = b(a.class);
        this.f2318a = (Class<? super T>) b.e(this.f2319b);
        this.f2320c = this.f2319b.hashCode();
    }

    static Type b(Class<?> cls) {
        Type genericSuperclass = cls.getGenericSuperclass();
        if (!(genericSuperclass instanceof Class)) {
            return b.b(((ParameterizedType) genericSuperclass).getActualTypeArguments()[0]);
        }
        throw new RuntimeException("Missing type parameter.");
    }

    public final Class<? super T> a() {
        return this.f2318a;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof a) && b.a(this.f2319b, ((a) obj).f2319b);
    }

    public final int hashCode() {
        return this.f2320c;
    }

    public final String toString() {
        return b.h(this.f2319b);
    }

    public static a<?> a(Type type) {
        return new a<>(type);
    }

    public static <T> a<T> a(Class<T> cls) {
        return new a<>(cls);
    }

    a(Type type) {
        c.a.b.w.a.a(type);
        this.f2319b = b.b(type);
        this.f2318a = (Class<? super T>) b.e(this.f2319b);
        this.f2320c = this.f2319b.hashCode();
    }

    public final Type b() {
        return this.f2319b;
    }
}
