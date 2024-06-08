package a.b.a.b;

import a.b.a.b.b;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class a<K, V> extends b<K, V> {

    /* renamed from: f, reason: collision with root package name */
    private HashMap<K, b.c<K, V>> f82f = new HashMap<>();

    public Map.Entry<K, V> a(K k) {
        if (contains(k)) {
            return this.f82f.get(k).f90e;
        }
        return null;
    }

    @Override // a.b.a.b.b
    public V b(K k, V v) {
        b.c<K, V> cVar = get(k);
        if (cVar != null) {
            return cVar.f88c;
        }
        this.f82f.put(k, a(k, v));
        return null;
    }

    public boolean contains(K k) {
        return this.f82f.containsKey(k);
    }

    @Override // a.b.a.b.b
    protected b.c<K, V> get(K k) {
        return this.f82f.get(k);
    }

    @Override // a.b.a.b.b
    public V remove(K k) {
        V v = (V) super.remove(k);
        this.f82f.remove(k);
        return v;
    }
}
