package c.a.b.w;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

/* loaded from: classes.dex */
public final class h<K, V> extends AbstractMap<K, V> implements Serializable {
    private static final Comparator<Comparable> i = new a();

    /* renamed from: b, reason: collision with root package name */
    Comparator<? super K> f2226b;

    /* renamed from: c, reason: collision with root package name */
    e<K, V> f2227c;

    /* renamed from: d, reason: collision with root package name */
    int f2228d;

    /* renamed from: e, reason: collision with root package name */
    int f2229e;

    /* renamed from: f, reason: collision with root package name */
    final e<K, V> f2230f;
    private h<K, V>.b g;
    private h<K, V>.c h;

    /* loaded from: classes.dex */
    class a implements Comparator<Comparable> {
        a() {
        }

        @Override // java.util.Comparator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(Comparable comparable, Comparable comparable2) {
            return comparable.compareTo(comparable2);
        }
    }

    /* loaded from: classes.dex */
    class b extends AbstractSet<Map.Entry<K, V>> {

        /* loaded from: classes.dex */
        class a extends h<K, V>.d<Map.Entry<K, V>> {
            a(b bVar) {
                super();
            }

            @Override // java.util.Iterator
            public Map.Entry<K, V> next() {
                return a();
            }
        }

        b() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            h.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return (obj instanceof Map.Entry) && h.this.a((Map.Entry<?, ?>) obj) != null;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Map.Entry<K, V>> iterator() {
            return new a(this);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            e<K, V> a2;
            if (!(obj instanceof Map.Entry) || (a2 = h.this.a((Map.Entry<?, ?>) obj)) == null) {
                return false;
            }
            h.this.a((e) a2, true);
            return true;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return h.this.f2228d;
        }
    }

    /* loaded from: classes.dex */
    final class c extends AbstractSet<K> {

        /* loaded from: classes.dex */
        class a extends h<K, V>.d<K> {
            a(c cVar) {
                super();
            }

            @Override // java.util.Iterator
            public K next() {
                return a().g;
            }
        }

        c() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            h.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return h.this.containsKey(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<K> iterator() {
            return new a(this);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            return h.this.b(obj) != null;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return h.this.f2228d;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public abstract class d<T> implements Iterator<T> {

        /* renamed from: b, reason: collision with root package name */
        e<K, V> f2233b;

        /* renamed from: c, reason: collision with root package name */
        e<K, V> f2234c;

        /* renamed from: d, reason: collision with root package name */
        int f2235d;

        d() {
            h hVar = h.this;
            this.f2233b = hVar.f2230f.f2240e;
            this.f2234c = null;
            this.f2235d = hVar.f2229e;
        }

        final e<K, V> a() {
            e<K, V> eVar = this.f2233b;
            h hVar = h.this;
            if (eVar != hVar.f2230f) {
                if (hVar.f2229e == this.f2235d) {
                    this.f2233b = eVar.f2240e;
                    this.f2234c = eVar;
                    return eVar;
                }
                throw new ConcurrentModificationException();
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            return this.f2233b != h.this.f2230f;
        }

        @Override // java.util.Iterator
        public final void remove() {
            e<K, V> eVar = this.f2234c;
            if (eVar != null) {
                h.this.a((e) eVar, true);
                this.f2234c = null;
                this.f2235d = h.this.f2229e;
                return;
            }
            throw new IllegalStateException();
        }
    }

    public h() {
        this(i);
    }

    e<K, V> a(K k, boolean z) {
        int i2;
        e<K, V> eVar;
        Comparator<? super K> comparator = this.f2226b;
        e<K, V> eVar2 = this.f2227c;
        if (eVar2 != null) {
            Comparable comparable = comparator == i ? (Comparable) k : null;
            while (true) {
                if (comparable != null) {
                    i2 = comparable.compareTo(eVar2.g);
                } else {
                    i2 = comparator.compare(k, eVar2.g);
                }
                if (i2 == 0) {
                    return eVar2;
                }
                e<K, V> eVar3 = i2 < 0 ? eVar2.f2238c : eVar2.f2239d;
                if (eVar3 == null) {
                    break;
                }
                eVar2 = eVar3;
            }
        } else {
            i2 = 0;
        }
        if (!z) {
            return null;
        }
        e<K, V> eVar4 = this.f2230f;
        if (eVar2 == null) {
            if (comparator == i && !(k instanceof Comparable)) {
                throw new ClassCastException(k.getClass().getName() + " is not Comparable");
            }
            eVar = new e<>(eVar2, k, eVar4, eVar4.f2241f);
            this.f2227c = eVar;
        } else {
            eVar = new e<>(eVar2, k, eVar4, eVar4.f2241f);
            if (i2 < 0) {
                eVar2.f2238c = eVar;
            } else {
                eVar2.f2239d = eVar;
            }
            b(eVar2, true);
        }
        this.f2228d++;
        this.f2229e++;
        return eVar;
    }

    e<K, V> b(Object obj) {
        e<K, V> a2 = a(obj);
        if (a2 != null) {
            a((e) a2, true);
        }
        return a2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        this.f2227c = null;
        this.f2228d = 0;
        this.f2229e++;
        e<K, V> eVar = this.f2230f;
        eVar.f2241f = eVar;
        eVar.f2240e = eVar;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(Object obj) {
        return a(obj) != null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        h<K, V>.b bVar = this.g;
        if (bVar != null) {
            return bVar;
        }
        h<K, V>.b bVar2 = new b();
        this.g = bVar2;
        return bVar2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V get(Object obj) {
        e<K, V> a2 = a(obj);
        if (a2 != null) {
            return a2.h;
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<K> keySet() {
        h<K, V>.c cVar = this.h;
        if (cVar != null) {
            return cVar;
        }
        h<K, V>.c cVar2 = new c();
        this.h = cVar2;
        return cVar2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V put(K k, V v) {
        if (k != null) {
            e<K, V> a2 = a((h<K, V>) k, true);
            V v2 = a2.h;
            a2.h = v;
            return v2;
        }
        throw new NullPointerException("key == null");
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V remove(Object obj) {
        e<K, V> b2 = b(obj);
        if (b2 != null) {
            return b2.h;
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int size() {
        return this.f2228d;
    }

    public h(Comparator<? super K> comparator) {
        this.f2228d = 0;
        this.f2229e = 0;
        this.f2230f = new e<>();
        this.f2226b = comparator == null ? i : comparator;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class e<K, V> implements Map.Entry<K, V> {

        /* renamed from: b, reason: collision with root package name */
        e<K, V> f2237b;

        /* renamed from: c, reason: collision with root package name */
        e<K, V> f2238c;

        /* renamed from: d, reason: collision with root package name */
        e<K, V> f2239d;

        /* renamed from: e, reason: collision with root package name */
        e<K, V> f2240e;

        /* renamed from: f, reason: collision with root package name */
        e<K, V> f2241f;
        final K g;
        V h;
        int i;

        e() {
            this.g = null;
            this.f2241f = this;
            this.f2240e = this;
        }

        public e<K, V> a() {
            e<K, V> eVar = this;
            for (e<K, V> eVar2 = this.f2238c; eVar2 != null; eVar2 = eVar2.f2238c) {
                eVar = eVar2;
            }
            return eVar;
        }

        public e<K, V> b() {
            e<K, V> eVar = this;
            for (e<K, V> eVar2 = this.f2239d; eVar2 != null; eVar2 = eVar2.f2239d) {
                eVar = eVar2;
            }
            return eVar;
        }

        @Override // java.util.Map.Entry
        public boolean equals(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            K k = this.g;
            if (k == null) {
                if (entry.getKey() != null) {
                    return false;
                }
            } else if (!k.equals(entry.getKey())) {
                return false;
            }
            V v = this.h;
            if (v == null) {
                if (entry.getValue() != null) {
                    return false;
                }
            } else if (!v.equals(entry.getValue())) {
                return false;
            }
            return true;
        }

        @Override // java.util.Map.Entry
        public K getKey() {
            return this.g;
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            return this.h;
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            K k = this.g;
            int hashCode = k == null ? 0 : k.hashCode();
            V v = this.h;
            return hashCode ^ (v != null ? v.hashCode() : 0);
        }

        @Override // java.util.Map.Entry
        public V setValue(V v) {
            V v2 = this.h;
            this.h = v;
            return v2;
        }

        public String toString() {
            return this.g + "=" + this.h;
        }

        e(e<K, V> eVar, K k, e<K, V> eVar2, e<K, V> eVar3) {
            this.f2237b = eVar;
            this.g = k;
            this.i = 1;
            this.f2240e = eVar2;
            this.f2241f = eVar3;
            eVar3.f2240e = this;
            eVar2.f2241f = this;
        }
    }

    private void b(e<K, V> eVar, boolean z) {
        while (eVar != null) {
            e<K, V> eVar2 = eVar.f2238c;
            e<K, V> eVar3 = eVar.f2239d;
            int i2 = eVar2 != null ? eVar2.i : 0;
            int i3 = eVar3 != null ? eVar3.i : 0;
            int i4 = i2 - i3;
            if (i4 == -2) {
                e<K, V> eVar4 = eVar3.f2238c;
                e<K, V> eVar5 = eVar3.f2239d;
                int i5 = (eVar4 != null ? eVar4.i : 0) - (eVar5 != null ? eVar5.i : 0);
                if (i5 != -1 && (i5 != 0 || z)) {
                    b((e) eVar3);
                    a((e) eVar);
                } else {
                    a((e) eVar);
                }
                if (z) {
                    return;
                }
            } else if (i4 == 2) {
                e<K, V> eVar6 = eVar2.f2238c;
                e<K, V> eVar7 = eVar2.f2239d;
                int i6 = (eVar6 != null ? eVar6.i : 0) - (eVar7 != null ? eVar7.i : 0);
                if (i6 != 1 && (i6 != 0 || z)) {
                    a((e) eVar2);
                    b((e) eVar);
                } else {
                    b((e) eVar);
                }
                if (z) {
                    return;
                }
            } else if (i4 == 0) {
                eVar.i = i2 + 1;
                if (z) {
                    return;
                }
            } else {
                eVar.i = Math.max(i2, i3) + 1;
                if (!z) {
                    return;
                }
            }
            eVar = eVar.f2237b;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    e<K, V> a(Object obj) {
        if (obj == 0) {
            return null;
        }
        try {
            return a((h<K, V>) obj, false);
        } catch (ClassCastException unused) {
            return null;
        }
    }

    e<K, V> a(Map.Entry<?, ?> entry) {
        e<K, V> a2 = a(entry.getKey());
        if (a2 != null && a(a2.h, entry.getValue())) {
            return a2;
        }
        return null;
    }

    private boolean a(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    void a(e<K, V> eVar, boolean z) {
        int i2;
        if (z) {
            e<K, V> eVar2 = eVar.f2241f;
            eVar2.f2240e = eVar.f2240e;
            eVar.f2240e.f2241f = eVar2;
        }
        e<K, V> eVar3 = eVar.f2238c;
        e<K, V> eVar4 = eVar.f2239d;
        e<K, V> eVar5 = eVar.f2237b;
        int i3 = 0;
        if (eVar3 != null && eVar4 != null) {
            e<K, V> b2 = eVar3.i > eVar4.i ? eVar3.b() : eVar4.a();
            a((e) b2, false);
            e<K, V> eVar6 = eVar.f2238c;
            if (eVar6 != null) {
                i2 = eVar6.i;
                b2.f2238c = eVar6;
                eVar6.f2237b = b2;
                eVar.f2238c = null;
            } else {
                i2 = 0;
            }
            e<K, V> eVar7 = eVar.f2239d;
            if (eVar7 != null) {
                i3 = eVar7.i;
                b2.f2239d = eVar7;
                eVar7.f2237b = b2;
                eVar.f2239d = null;
            }
            b2.i = Math.max(i2, i3) + 1;
            a((e) eVar, (e) b2);
            return;
        }
        if (eVar3 != null) {
            a((e) eVar, (e) eVar3);
            eVar.f2238c = null;
        } else if (eVar4 != null) {
            a((e) eVar, (e) eVar4);
            eVar.f2239d = null;
        } else {
            a((e) eVar, (e) null);
        }
        b(eVar5, false);
        this.f2228d--;
        this.f2229e++;
    }

    private void b(e<K, V> eVar) {
        e<K, V> eVar2 = eVar.f2238c;
        e<K, V> eVar3 = eVar.f2239d;
        e<K, V> eVar4 = eVar2.f2238c;
        e<K, V> eVar5 = eVar2.f2239d;
        eVar.f2238c = eVar5;
        if (eVar5 != null) {
            eVar5.f2237b = eVar;
        }
        a((e) eVar, (e) eVar2);
        eVar2.f2239d = eVar;
        eVar.f2237b = eVar2;
        eVar.i = Math.max(eVar3 != null ? eVar3.i : 0, eVar5 != null ? eVar5.i : 0) + 1;
        eVar2.i = Math.max(eVar.i, eVar4 != null ? eVar4.i : 0) + 1;
    }

    private void a(e<K, V> eVar, e<K, V> eVar2) {
        e<K, V> eVar3 = eVar.f2237b;
        eVar.f2237b = null;
        if (eVar2 != null) {
            eVar2.f2237b = eVar3;
        }
        if (eVar3 != null) {
            if (eVar3.f2238c == eVar) {
                eVar3.f2238c = eVar2;
                return;
            } else {
                eVar3.f2239d = eVar2;
                return;
            }
        }
        this.f2227c = eVar2;
    }

    private void a(e<K, V> eVar) {
        e<K, V> eVar2 = eVar.f2238c;
        e<K, V> eVar3 = eVar.f2239d;
        e<K, V> eVar4 = eVar3.f2238c;
        e<K, V> eVar5 = eVar3.f2239d;
        eVar.f2239d = eVar4;
        if (eVar4 != null) {
            eVar4.f2237b = eVar;
        }
        a((e) eVar, (e) eVar3);
        eVar3.f2238c = eVar;
        eVar.f2237b = eVar3;
        eVar.i = Math.max(eVar2 != null ? eVar2.i : 0, eVar4 != null ? eVar4.i : 0) + 1;
        eVar3.i = Math.max(eVar.i, eVar5 != null ? eVar5.i : 0) + 1;
    }
}
