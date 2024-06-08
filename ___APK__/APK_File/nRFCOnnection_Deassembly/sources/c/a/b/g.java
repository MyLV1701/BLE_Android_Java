package c.a.b;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public final class g extends j implements Iterable<j> {

    /* renamed from: b, reason: collision with root package name */
    private final List<j> f2186b = new ArrayList();

    public void a(j jVar) {
        if (jVar == null) {
            jVar = l.f2187a;
        }
        this.f2186b.add(jVar);
    }

    public boolean equals(Object obj) {
        return obj == this || ((obj instanceof g) && ((g) obj).f2186b.equals(this.f2186b));
    }

    public int hashCode() {
        return this.f2186b.hashCode();
    }

    @Override // java.lang.Iterable
    public Iterator<j> iterator() {
        return this.f2186b.iterator();
    }
}
