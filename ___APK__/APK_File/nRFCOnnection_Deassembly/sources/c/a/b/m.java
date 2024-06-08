package c.a.b;

import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
public final class m extends j {

    /* renamed from: a, reason: collision with root package name */
    private final c.a.b.w.h<String, j> f2188a = new c.a.b.w.h<>();

    public void a(String str, j jVar) {
        c.a.b.w.h<String, j> hVar = this.f2188a;
        if (jVar == null) {
            jVar = l.f2187a;
        }
        hVar.put(str, jVar);
    }

    public boolean equals(Object obj) {
        return obj == this || ((obj instanceof m) && ((m) obj).f2188a.equals(this.f2188a));
    }

    public Set<Map.Entry<String, j>> h() {
        return this.f2188a.entrySet();
    }

    public int hashCode() {
        return this.f2188a.hashCode();
    }
}
