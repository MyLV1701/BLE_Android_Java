package c.a.b.w.n;

import c.a.b.q;
import c.a.b.t;
import c.a.b.u;

/* loaded from: classes.dex */
public final class d implements u {

    /* renamed from: b, reason: collision with root package name */
    private final c.a.b.w.c f2255b;

    public d(c.a.b.w.c cVar) {
        this.f2255b = cVar;
    }

    @Override // c.a.b.u
    public <T> t<T> a(c.a.b.e eVar, c.a.b.x.a<T> aVar) {
        c.a.b.v.b bVar = (c.a.b.v.b) aVar.a().getAnnotation(c.a.b.v.b.class);
        if (bVar == null) {
            return null;
        }
        return (t<T>) a(this.f2255b, eVar, aVar, bVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public t<?> a(c.a.b.w.c cVar, c.a.b.e eVar, c.a.b.x.a<?> aVar, c.a.b.v.b bVar) {
        t<?> lVar;
        Object a2 = cVar.a(c.a.b.x.a.a((Class) bVar.value())).a();
        if (a2 instanceof t) {
            lVar = (t) a2;
        } else if (a2 instanceof u) {
            lVar = ((u) a2).a(eVar, aVar);
        } else {
            boolean z = a2 instanceof q;
            if (!z && !(a2 instanceof c.a.b.i)) {
                throw new IllegalArgumentException("Invalid attempt to bind an instance of " + a2.getClass().getName() + " as a @JsonAdapter for " + aVar.toString() + ". @JsonAdapter value must be a TypeAdapter, TypeAdapterFactory, JsonSerializer or JsonDeserializer.");
            }
            lVar = new l<>(z ? (q) a2 : null, a2 instanceof c.a.b.i ? (c.a.b.i) a2 : null, eVar, aVar, null);
        }
        return (lVar == null || !bVar.nullSafe()) ? lVar : lVar.a();
    }
}
