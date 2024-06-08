package a.b.a.b;

import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

/* loaded from: classes.dex */
public class b<K, V> implements Iterable<Map.Entry<K, V>> {

    /* renamed from: b, reason: collision with root package name */
    c<K, V> f83b;

    /* renamed from: c, reason: collision with root package name */
    private c<K, V> f84c;

    /* renamed from: d, reason: collision with root package name */
    private WeakHashMap<f<K, V>, Boolean> f85d = new WeakHashMap<>();

    /* renamed from: e, reason: collision with root package name */
    private int f86e = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class a<K, V> extends e<K, V> {
        a(c<K, V> cVar, c<K, V> cVar2) {
            super(cVar, cVar2);
        }

        @Override // a.b.a.b.b.e
        c<K, V> b(c<K, V> cVar) {
            return cVar.f90e;
        }

        @Override // a.b.a.b.b.e
        c<K, V> c(c<K, V> cVar) {
            return cVar.f89d;
        }
    }

    /* renamed from: a.b.a.b.b$b, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    private static class C0004b<K, V> extends e<K, V> {
        C0004b(c<K, V> cVar, c<K, V> cVar2) {
            super(cVar, cVar2);
        }

        @Override // a.b.a.b.b.e
        c<K, V> b(c<K, V> cVar) {
            return cVar.f89d;
        }

        @Override // a.b.a.b.b.e
        c<K, V> c(c<K, V> cVar) {
            return cVar.f90e;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class c<K, V> implements Map.Entry<K, V> {

        /* renamed from: b, reason: collision with root package name */
        final K f87b;

        /* renamed from: c, reason: collision with root package name */
        final V f88c;

        /* renamed from: d, reason: collision with root package name */
        c<K, V> f89d;

        /* renamed from: e, reason: collision with root package name */
        c<K, V> f90e;

        c(K k, V v) {
            this.f87b = k;
            this.f88c = v;
        }

        @Override // java.util.Map.Entry
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof c)) {
                return false;
            }
            c cVar = (c) obj;
            return this.f87b.equals(cVar.f87b) && this.f88c.equals(cVar.f88c);
        }

        @Override // java.util.Map.Entry
        public K getKey() {
            return this.f87b;
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            return this.f88c;
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            return this.f87b.hashCode() ^ this.f88c.hashCode();
        }

        @Override // java.util.Map.Entry
        public V setValue(V v) {
            throw new UnsupportedOperationException("An entry modification is not supported");
        }

        public String toString() {
            return this.f87b + "=" + this.f88c;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class d implements Iterator<Map.Entry<K, V>>, f<K, V> {

        /* renamed from: b, reason: collision with root package name */
        private c<K, V> f91b;

        /* renamed from: c, reason: collision with root package name */
        private boolean f92c = true;

        d() {
        }

        @Override // a.b.a.b.b.f
        public void a(c<K, V> cVar) {
            c<K, V> cVar2 = this.f91b;
            if (cVar == cVar2) {
                this.f91b = cVar2.f90e;
                this.f92c = this.f91b == null;
            }
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.f92c) {
                return b.this.f83b != null;
            }
            c<K, V> cVar = this.f91b;
            return (cVar == null || cVar.f89d == null) ? false : true;
        }

        @Override // java.util.Iterator
        public Map.Entry<K, V> next() {
            if (this.f92c) {
                this.f92c = false;
                this.f91b = b.this.f83b;
            } else {
                c<K, V> cVar = this.f91b;
                this.f91b = cVar != null ? cVar.f89d : null;
            }
            return this.f91b;
        }
    }

    /* loaded from: classes.dex */
    private static abstract class e<K, V> implements Iterator<Map.Entry<K, V>>, f<K, V> {

        /* renamed from: b, reason: collision with root package name */
        c<K, V> f94b;

        /* renamed from: c, reason: collision with root package name */
        c<K, V> f95c;

        e(c<K, V> cVar, c<K, V> cVar2) {
            this.f94b = cVar2;
            this.f95c = cVar;
        }

        @Override // a.b.a.b.b.f
        public void a(c<K, V> cVar) {
            if (this.f94b == cVar && cVar == this.f95c) {
                this.f95c = null;
                this.f94b = null;
            }
            c<K, V> cVar2 = this.f94b;
            if (cVar2 == cVar) {
                this.f94b = b(cVar2);
            }
            if (this.f95c == cVar) {
                this.f95c = a();
            }
        }

        abstract c<K, V> b(c<K, V> cVar);

        abstract c<K, V> c(c<K, V> cVar);

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.f95c != null;
        }

        @Override // java.util.Iterator
        public Map.Entry<K, V> next() {
            c<K, V> cVar = this.f95c;
            this.f95c = a();
            return cVar;
        }

        private c<K, V> a() {
            c<K, V> cVar = this.f95c;
            c<K, V> cVar2 = this.f94b;
            if (cVar == cVar2 || cVar2 == null) {
                return null;
            }
            return c(cVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface f<K, V> {
        void a(c<K, V> cVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public c<K, V> a(K k, V v) {
        c<K, V> cVar = new c<>(k, v);
        this.f86e++;
        c<K, V> cVar2 = this.f84c;
        if (cVar2 == null) {
            this.f83b = cVar;
            this.f84c = this.f83b;
            return cVar;
        }
        cVar2.f89d = cVar;
        cVar.f90e = cVar2;
        this.f84c = cVar;
        return cVar;
    }

    public V b(K k, V v) {
        c<K, V> cVar = get(k);
        if (cVar != null) {
            return cVar.f88c;
        }
        a(k, v);
        return null;
    }

    public Map.Entry<K, V> c() {
        return this.f84c;
    }

    public Iterator<Map.Entry<K, V>> descendingIterator() {
        C0004b c0004b = new C0004b(this.f84c, this.f83b);
        this.f85d.put(c0004b, false);
        return c0004b;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        if (size() != bVar.size()) {
            return false;
        }
        Iterator<Map.Entry<K, V>> it = iterator();
        Iterator<Map.Entry<K, V>> it2 = bVar.iterator();
        while (it.hasNext() && it2.hasNext()) {
            Map.Entry<K, V> next = it.next();
            Map.Entry<K, V> next2 = it2.next();
            if ((next == null && next2 != null) || (next != null && !next.equals(next2))) {
                return false;
            }
        }
        return (it.hasNext() || it2.hasNext()) ? false : true;
    }

    protected c<K, V> get(K k) {
        c<K, V> cVar = this.f83b;
        while (cVar != null && !cVar.f87b.equals(k)) {
            cVar = cVar.f89d;
        }
        return cVar;
    }

    public int hashCode() {
        Iterator<Map.Entry<K, V>> it = iterator();
        int i = 0;
        while (it.hasNext()) {
            i += it.next().hashCode();
        }
        return i;
    }

    @Override // java.lang.Iterable
    public Iterator<Map.Entry<K, V>> iterator() {
        a aVar = new a(this.f83b, this.f84c);
        this.f85d.put(aVar, false);
        return aVar;
    }

    public V remove(K k) {
        c<K, V> cVar = get(k);
        if (cVar == null) {
            return null;
        }
        this.f86e--;
        if (!this.f85d.isEmpty()) {
            Iterator<f<K, V>> it = this.f85d.keySet().iterator();
            while (it.hasNext()) {
                it.next().a(cVar);
            }
        }
        c<K, V> cVar2 = cVar.f90e;
        if (cVar2 != null) {
            cVar2.f89d = cVar.f89d;
        } else {
            this.f83b = cVar.f89d;
        }
        c<K, V> cVar3 = cVar.f89d;
        if (cVar3 != null) {
            cVar3.f90e = cVar.f90e;
        } else {
            this.f84c = cVar.f90e;
        }
        cVar.f89d = null;
        cVar.f90e = null;
        return cVar.f88c;
    }

    public int size() {
        return this.f86e;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Iterator<Map.Entry<K, V>> it = iterator();
        while (it.hasNext()) {
            sb.append(it.next().toString());
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public b<K, V>.d b() {
        b<K, V>.d dVar = new d();
        this.f85d.put(dVar, false);
        return dVar;
    }

    public Map.Entry<K, V> a() {
        return this.f83b;
    }
}
