package org.simpleframework.xml.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.WeakHashMap;

/* loaded from: classes.dex */
public class WeakCache<T> implements Cache<T> {
    private WeakCache<T>.SegmentList list;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class Segment extends WeakHashMap<Object, T> {
        private Segment() {
        }

        public synchronized void cache(Object obj, T t) {
            put(obj, t);
        }

        public synchronized boolean contains(Object obj) {
            return containsKey(obj);
        }

        public synchronized T fetch(Object obj) {
            return get(obj);
        }

        public synchronized T take(Object obj) {
            return remove(obj);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class SegmentList implements Iterable<WeakCache<T>.Segment> {
        private List<WeakCache<T>.Segment> list = new ArrayList();
        private int size;

        public SegmentList(int i) {
            this.size = i;
            create(i);
        }

        private void create(int i) {
            while (true) {
                int i2 = i - 1;
                if (i <= 0) {
                    return;
                }
                this.list.add(new Segment());
                i = i2;
            }
        }

        private int segment(Object obj) {
            return Math.abs(obj.hashCode() % this.size);
        }

        public WeakCache<T>.Segment get(Object obj) {
            int segment = segment(obj);
            if (segment < this.size) {
                return this.list.get(segment);
            }
            return null;
        }

        @Override // java.lang.Iterable
        public Iterator<WeakCache<T>.Segment> iterator() {
            return this.list.iterator();
        }
    }

    public WeakCache() {
        this(10);
    }

    private WeakCache<T>.Segment map(Object obj) {
        return this.list.get(obj);
    }

    @Override // org.simpleframework.xml.util.Cache
    public void cache(Object obj, T t) {
        map(obj).cache(obj, t);
    }

    @Override // org.simpleframework.xml.util.Cache
    public boolean contains(Object obj) {
        return map(obj).contains(obj);
    }

    @Override // org.simpleframework.xml.util.Cache
    public T fetch(Object obj) {
        return map(obj).fetch(obj);
    }

    @Override // org.simpleframework.xml.util.Cache
    public boolean isEmpty() {
        Iterator<WeakCache<T>.Segment> it = this.list.iterator();
        while (it.hasNext()) {
            if (!it.next().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override // org.simpleframework.xml.util.Cache
    public T take(Object obj) {
        return map(obj).take(obj);
    }

    public WeakCache(int i) {
        this.list = new SegmentList(i);
    }
}
