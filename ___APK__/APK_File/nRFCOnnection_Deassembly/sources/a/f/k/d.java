package a.f.k;

/* loaded from: classes.dex */
public class d<F, S> {

    /* renamed from: a, reason: collision with root package name */
    public final F f268a;

    /* renamed from: b, reason: collision with root package name */
    public final S f269b;

    public boolean equals(Object obj) {
        if (!(obj instanceof d)) {
            return false;
        }
        d dVar = (d) obj;
        return c.a(dVar.f268a, this.f268a) && c.a(dVar.f269b, this.f269b);
    }

    public int hashCode() {
        F f2 = this.f268a;
        int hashCode = f2 == null ? 0 : f2.hashCode();
        S s = this.f269b;
        return hashCode ^ (s != null ? s.hashCode() : 0);
    }

    public String toString() {
        return "Pair{" + String.valueOf(this.f268a) + " " + String.valueOf(this.f269b) + "}";
    }
}
