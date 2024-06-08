package androidx.coordinatorlayout.widget;

import a.d.g;
import a.f.k.e;
import a.f.k.f;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/* loaded from: classes.dex */
public final class a<T> {

    /* renamed from: a, reason: collision with root package name */
    private final e<ArrayList<T>> f1177a = new f(10);

    /* renamed from: b, reason: collision with root package name */
    private final g<T, ArrayList<T>> f1178b = new g<>();

    /* renamed from: c, reason: collision with root package name */
    private final ArrayList<T> f1179c = new ArrayList<>();

    /* renamed from: d, reason: collision with root package name */
    private final HashSet<T> f1180d = new HashSet<>();

    public void a(T t) {
        if (this.f1178b.containsKey(t)) {
            return;
        }
        this.f1178b.put(t, null);
    }

    public boolean b(T t) {
        return this.f1178b.containsKey(t);
    }

    public List c(T t) {
        return this.f1178b.get(t);
    }

    public List<T> d(T t) {
        int size = this.f1178b.size();
        ArrayList arrayList = null;
        for (int i = 0; i < size; i++) {
            ArrayList<T> d2 = this.f1178b.d(i);
            if (d2 != null && d2.contains(t)) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(this.f1178b.b(i));
            }
        }
        return arrayList;
    }

    public boolean e(T t) {
        int size = this.f1178b.size();
        for (int i = 0; i < size; i++) {
            ArrayList<T> d2 = this.f1178b.d(i);
            if (d2 != null && d2.contains(t)) {
                return true;
            }
        }
        return false;
    }

    private ArrayList<T> c() {
        ArrayList<T> a2 = this.f1177a.a();
        return a2 == null ? new ArrayList<>() : a2;
    }

    public ArrayList<T> b() {
        this.f1179c.clear();
        this.f1180d.clear();
        int size = this.f1178b.size();
        for (int i = 0; i < size; i++) {
            a(this.f1178b.b(i), this.f1179c, this.f1180d);
        }
        return this.f1179c;
    }

    public void a(T t, T t2) {
        if (this.f1178b.containsKey(t) && this.f1178b.containsKey(t2)) {
            ArrayList<T> arrayList = this.f1178b.get(t);
            if (arrayList == null) {
                arrayList = c();
                this.f1178b.put(t, arrayList);
            }
            arrayList.add(t2);
            return;
        }
        throw new IllegalArgumentException("All nodes must be present in the graph before being added as an edge");
    }

    public void a() {
        int size = this.f1178b.size();
        for (int i = 0; i < size; i++) {
            ArrayList<T> d2 = this.f1178b.d(i);
            if (d2 != null) {
                a((ArrayList) d2);
            }
        }
        this.f1178b.clear();
    }

    private void a(T t, ArrayList<T> arrayList, HashSet<T> hashSet) {
        if (arrayList.contains(t)) {
            return;
        }
        if (!hashSet.contains(t)) {
            hashSet.add(t);
            ArrayList<T> arrayList2 = this.f1178b.get(t);
            if (arrayList2 != null) {
                int size = arrayList2.size();
                for (int i = 0; i < size; i++) {
                    a(arrayList2.get(i), arrayList, hashSet);
                }
            }
            hashSet.remove(t);
            arrayList.add(t);
            return;
        }
        throw new RuntimeException("This graph contains cyclic dependencies");
    }

    private void a(ArrayList<T> arrayList) {
        arrayList.clear();
        this.f1177a.a(arrayList);
    }
}
