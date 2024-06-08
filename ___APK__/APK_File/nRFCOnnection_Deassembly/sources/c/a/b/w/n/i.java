package c.a.b.w.n;

import c.a.b.r;
import c.a.b.t;
import c.a.b.u;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public final class i implements u {

    /* renamed from: b, reason: collision with root package name */
    private final c.a.b.w.c f2265b;

    /* renamed from: c, reason: collision with root package name */
    private final c.a.b.d f2266c;

    /* renamed from: d, reason: collision with root package name */
    private final c.a.b.w.d f2267d;

    /* renamed from: e, reason: collision with root package name */
    private final d f2268e;

    /* renamed from: f, reason: collision with root package name */
    private final c.a.b.w.o.b f2269f = c.a.b.w.o.b.a();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static abstract class c {

        /* renamed from: a, reason: collision with root package name */
        final String f2275a;

        /* renamed from: b, reason: collision with root package name */
        final boolean f2276b;

        /* renamed from: c, reason: collision with root package name */
        final boolean f2277c;

        protected c(String str, boolean z, boolean z2) {
            this.f2275a = str;
            this.f2276b = z;
            this.f2277c = z2;
        }

        abstract void a(c.a.b.y.a aVar, Object obj);

        abstract void a(c.a.b.y.c cVar, Object obj);

        abstract boolean a(Object obj);
    }

    public i(c.a.b.w.c cVar, c.a.b.d dVar, c.a.b.w.d dVar2, d dVar3) {
        this.f2265b = cVar;
        this.f2266c = dVar;
        this.f2267d = dVar2;
        this.f2268e = dVar3;
    }

    public boolean a(Field field, boolean z) {
        return a(field, z, this.f2267d);
    }

    static boolean a(Field field, boolean z, c.a.b.w.d dVar) {
        return (dVar.a(field.getType(), z) || dVar.a(field, z)) ? false : true;
    }

    private List<String> a(Field field) {
        c.a.b.v.c cVar = (c.a.b.v.c) field.getAnnotation(c.a.b.v.c.class);
        if (cVar == null) {
            return Collections.singletonList(this.f2266c.a(field));
        }
        String value = cVar.value();
        String[] alternate = cVar.alternate();
        if (alternate.length == 0) {
            return Collections.singletonList(value);
        }
        ArrayList arrayList = new ArrayList(alternate.length + 1);
        arrayList.add(value);
        for (String str : alternate) {
            arrayList.add(str);
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a extends c {

        /* renamed from: d, reason: collision with root package name */
        final /* synthetic */ Field f2270d;

        /* renamed from: e, reason: collision with root package name */
        final /* synthetic */ boolean f2271e;

        /* renamed from: f, reason: collision with root package name */
        final /* synthetic */ t f2272f;
        final /* synthetic */ c.a.b.e g;
        final /* synthetic */ c.a.b.x.a h;
        final /* synthetic */ boolean i;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(i iVar, String str, boolean z, boolean z2, Field field, boolean z3, t tVar, c.a.b.e eVar, c.a.b.x.a aVar, boolean z4) {
            super(str, z, z2);
            this.f2270d = field;
            this.f2271e = z3;
            this.f2272f = tVar;
            this.g = eVar;
            this.h = aVar;
            this.i = z4;
        }

        @Override // c.a.b.w.n.i.c
        void a(c.a.b.y.c cVar, Object obj) {
            (this.f2271e ? this.f2272f : new m(this.g, this.f2272f, this.h.b())).a(cVar, this.f2270d.get(obj));
        }

        @Override // c.a.b.w.n.i.c
        void a(c.a.b.y.a aVar, Object obj) {
            Object a2 = this.f2272f.a2(aVar);
            if (a2 == null && this.i) {
                return;
            }
            this.f2270d.set(obj, a2);
        }

        @Override // c.a.b.w.n.i.c
        public boolean a(Object obj) {
            return this.f2276b && this.f2270d.get(obj) != obj;
        }
    }

    /* loaded from: classes.dex */
    public static final class b<T> extends t<T> {

        /* renamed from: a, reason: collision with root package name */
        private final c.a.b.w.i<T> f2273a;

        /* renamed from: b, reason: collision with root package name */
        private final Map<String, c> f2274b;

        b(c.a.b.w.i<T> iVar, Map<String, c> map) {
            this.f2273a = iVar;
            this.f2274b = map;
        }

        @Override // c.a.b.t
        /* renamed from: a */
        public T a2(c.a.b.y.a aVar) {
            if (aVar.q() == c.a.b.y.b.NULL) {
                aVar.o();
                return null;
            }
            T a2 = this.f2273a.a();
            try {
                aVar.b();
                while (aVar.g()) {
                    c cVar = this.f2274b.get(aVar.n());
                    if (cVar != null && cVar.f2277c) {
                        cVar.a(aVar, a2);
                    }
                    aVar.r();
                }
                aVar.e();
                return a2;
            } catch (IllegalAccessException e2) {
                throw new AssertionError(e2);
            } catch (IllegalStateException e3) {
                throw new r(e3);
            }
        }

        @Override // c.a.b.t
        public void a(c.a.b.y.c cVar, T t) {
            if (t == null) {
                cVar.g();
                return;
            }
            cVar.b();
            try {
                for (c cVar2 : this.f2274b.values()) {
                    if (cVar2.a(t)) {
                        cVar.a(cVar2.f2275a);
                        cVar2.a(cVar, t);
                    }
                }
                cVar.d();
            } catch (IllegalAccessException e2) {
                throw new AssertionError(e2);
            }
        }
    }

    @Override // c.a.b.u
    public <T> t<T> a(c.a.b.e eVar, c.a.b.x.a<T> aVar) {
        Class<? super T> a2 = aVar.a();
        if (Object.class.isAssignableFrom(a2)) {
            return new b(this.f2265b.a(aVar), a(eVar, (c.a.b.x.a<?>) aVar, (Class<?>) a2));
        }
        return null;
    }

    private c a(c.a.b.e eVar, Field field, String str, c.a.b.x.a<?> aVar, boolean z, boolean z2) {
        boolean a2 = c.a.b.w.k.a((Type) aVar.a());
        c.a.b.v.b bVar = (c.a.b.v.b) field.getAnnotation(c.a.b.v.b.class);
        t<?> a3 = bVar != null ? this.f2268e.a(this.f2265b, eVar, aVar, bVar) : null;
        boolean z3 = a3 != null;
        if (a3 == null) {
            a3 = eVar.a(aVar);
        }
        return new a(this, str, z, z2, field, z3, a3, eVar, aVar, a2);
    }

    private Map<String, c> a(c.a.b.e eVar, c.a.b.x.a<?> aVar, Class<?> cls) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (cls.isInterface()) {
            return linkedHashMap;
        }
        Type b2 = aVar.b();
        c.a.b.x.a<?> aVar2 = aVar;
        Class<?> cls2 = cls;
        while (cls2 != Object.class) {
            Field[] declaredFields = cls2.getDeclaredFields();
            int length = declaredFields.length;
            boolean z = false;
            int i = 0;
            while (i < length) {
                Field field = declaredFields[i];
                boolean a2 = a(field, true);
                boolean a3 = a(field, z);
                if (a2 || a3) {
                    this.f2269f.a(field);
                    Type a4 = c.a.b.w.b.a(aVar2.b(), cls2, field.getGenericType());
                    List<String> a5 = a(field);
                    int size = a5.size();
                    c cVar = null;
                    int i2 = 0;
                    while (i2 < size) {
                        String str = a5.get(i2);
                        boolean z2 = i2 != 0 ? false : a2;
                        c cVar2 = cVar;
                        int i3 = i2;
                        int i4 = size;
                        List<String> list = a5;
                        Field field2 = field;
                        cVar = cVar2 == null ? (c) linkedHashMap.put(str, a(eVar, field, str, c.a.b.x.a.a(a4), z2, a3)) : cVar2;
                        i2 = i3 + 1;
                        a2 = z2;
                        a5 = list;
                        size = i4;
                        field = field2;
                    }
                    c cVar3 = cVar;
                    if (cVar3 != null) {
                        throw new IllegalArgumentException(b2 + " declares multiple JSON fields named " + cVar3.f2275a);
                    }
                }
                i++;
                z = false;
            }
            aVar2 = c.a.b.x.a.a(c.a.b.w.b.a(aVar2.b(), cls2, cls2.getGenericSuperclass()));
            cls2 = aVar2.a();
        }
        return linkedHashMap;
    }
}
