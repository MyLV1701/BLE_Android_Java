package a.d;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
public final class b<E> implements Collection<E>, Set<E> {

    /* renamed from: f, reason: collision with root package name */
    private static final int[] f97f = new int[0];
    private static final Object[] g = new Object[0];
    private static Object[] h;
    private static int i;
    private static Object[] j;
    private static int k;

    /* renamed from: b, reason: collision with root package name */
    private int[] f98b;

    /* renamed from: c, reason: collision with root package name */
    Object[] f99c;

    /* renamed from: d, reason: collision with root package name */
    int f100d;

    /* renamed from: e, reason: collision with root package name */
    private f<E, E> f101e;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a extends f<E, E> {
        a() {
        }

        @Override // a.d.f
        protected Object a(int i, int i2) {
            return b.this.f99c[i];
        }

        @Override // a.d.f
        protected int b(Object obj) {
            return b.this.indexOf(obj);
        }

        @Override // a.d.f
        protected int c() {
            return b.this.f100d;
        }

        @Override // a.d.f
        protected int a(Object obj) {
            return b.this.indexOf(obj);
        }

        @Override // a.d.f
        protected Map<E, E> b() {
            throw new UnsupportedOperationException("not a map");
        }

        @Override // a.d.f
        protected void a(E e2, E e3) {
            b.this.add(e2);
        }

        @Override // a.d.f
        protected E a(int i, E e2) {
            throw new UnsupportedOperationException("not a map");
        }

        @Override // a.d.f
        protected void a(int i) {
            b.this.b(i);
        }

        @Override // a.d.f
        protected void a() {
            b.this.clear();
        }
    }

    public b() {
        this(0);
    }

    private int a(Object obj, int i2) {
        int i3 = this.f100d;
        if (i3 == 0) {
            return -1;
        }
        int a2 = c.a(this.f98b, i3, i2);
        if (a2 < 0 || obj.equals(this.f99c[a2])) {
            return a2;
        }
        int i4 = a2 + 1;
        while (i4 < i3 && this.f98b[i4] == i2) {
            if (obj.equals(this.f99c[i4])) {
                return i4;
            }
            i4++;
        }
        for (int i5 = a2 - 1; i5 >= 0 && this.f98b[i5] == i2; i5--) {
            if (obj.equals(this.f99c[i5])) {
                return i5;
            }
        }
        return i4 ^ (-1);
    }

    private int b() {
        int i2 = this.f100d;
        if (i2 == 0) {
            return -1;
        }
        int a2 = c.a(this.f98b, i2, 0);
        if (a2 < 0 || this.f99c[a2] == null) {
            return a2;
        }
        int i3 = a2 + 1;
        while (i3 < i2 && this.f98b[i3] == 0) {
            if (this.f99c[i3] == null) {
                return i3;
            }
            i3++;
        }
        for (int i4 = a2 - 1; i4 >= 0 && this.f98b[i4] == 0; i4--) {
            if (this.f99c[i4] == null) {
                return i4;
            }
        }
        return i3 ^ (-1);
    }

    private void d(int i2) {
        if (i2 == 8) {
            synchronized (b.class) {
                if (j != null) {
                    Object[] objArr = j;
                    this.f99c = objArr;
                    j = (Object[]) objArr[0];
                    this.f98b = (int[]) objArr[1];
                    objArr[1] = null;
                    objArr[0] = null;
                    k--;
                    return;
                }
            }
        } else if (i2 == 4) {
            synchronized (b.class) {
                if (h != null) {
                    Object[] objArr2 = h;
                    this.f99c = objArr2;
                    h = (Object[]) objArr2[0];
                    this.f98b = (int[]) objArr2[1];
                    objArr2[1] = null;
                    objArr2[0] = null;
                    i--;
                    return;
                }
            }
        }
        this.f98b = new int[i2];
        this.f99c = new Object[i2];
    }

    @Override // java.util.Collection, java.util.Set
    public boolean add(E e2) {
        int i2;
        int a2;
        if (e2 == null) {
            a2 = b();
            i2 = 0;
        } else {
            int hashCode = e2.hashCode();
            i2 = hashCode;
            a2 = a(e2, hashCode);
        }
        if (a2 >= 0) {
            return false;
        }
        int i3 = a2 ^ (-1);
        int i4 = this.f100d;
        if (i4 >= this.f98b.length) {
            int i5 = 4;
            if (i4 >= 8) {
                i5 = (i4 >> 1) + i4;
            } else if (i4 >= 4) {
                i5 = 8;
            }
            int[] iArr = this.f98b;
            Object[] objArr = this.f99c;
            d(i5);
            int[] iArr2 = this.f98b;
            if (iArr2.length > 0) {
                System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
                System.arraycopy(objArr, 0, this.f99c, 0, objArr.length);
            }
            a(iArr, objArr, this.f100d);
        }
        int i6 = this.f100d;
        if (i3 < i6) {
            int[] iArr3 = this.f98b;
            int i7 = i3 + 1;
            System.arraycopy(iArr3, i3, iArr3, i7, i6 - i3);
            Object[] objArr2 = this.f99c;
            System.arraycopy(objArr2, i3, objArr2, i7, this.f100d - i3);
        }
        this.f98b[i3] = i2;
        this.f99c[i3] = e2;
        this.f100d++;
        return true;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean addAll(Collection<? extends E> collection) {
        a(this.f100d + collection.size());
        Iterator<? extends E> it = collection.iterator();
        boolean z = false;
        while (it.hasNext()) {
            z |= add(it.next());
        }
        return z;
    }

    public E c(int i2) {
        return (E) this.f99c[i2];
    }

    @Override // java.util.Collection, java.util.Set
    public void clear() {
        int i2 = this.f100d;
        if (i2 != 0) {
            a(this.f98b, this.f99c, i2);
            this.f98b = f97f;
            this.f99c = g;
            this.f100d = 0;
        }
    }

    @Override // java.util.Collection, java.util.Set
    public boolean contains(Object obj) {
        return indexOf(obj) >= 0;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean containsAll(Collection<?> collection) {
        Iterator<?> it = collection.iterator();
        while (it.hasNext()) {
            if (!contains(it.next())) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Set) {
            Set set = (Set) obj;
            if (size() != set.size()) {
                return false;
            }
            for (int i2 = 0; i2 < this.f100d; i2++) {
                try {
                    if (!set.contains(c(i2))) {
                        return false;
                    }
                } catch (ClassCastException | NullPointerException unused) {
                }
            }
            return true;
        }
        return false;
    }

    @Override // java.util.Collection, java.util.Set
    public int hashCode() {
        int[] iArr = this.f98b;
        int i2 = this.f100d;
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            i3 += iArr[i4];
        }
        return i3;
    }

    public int indexOf(Object obj) {
        return obj == null ? b() : a(obj, obj.hashCode());
    }

    @Override // java.util.Collection, java.util.Set
    public boolean isEmpty() {
        return this.f100d <= 0;
    }

    @Override // java.util.Collection, java.lang.Iterable, java.util.Set
    public Iterator<E> iterator() {
        return a().e().iterator();
    }

    @Override // java.util.Collection, java.util.Set
    public boolean remove(Object obj) {
        int indexOf = indexOf(obj);
        if (indexOf < 0) {
            return false;
        }
        b(indexOf);
        return true;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean removeAll(Collection<?> collection) {
        Iterator<?> it = collection.iterator();
        boolean z = false;
        while (it.hasNext()) {
            z |= remove(it.next());
        }
        return z;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean retainAll(Collection<?> collection) {
        boolean z = false;
        for (int i2 = this.f100d - 1; i2 >= 0; i2--) {
            if (!collection.contains(this.f99c[i2])) {
                b(i2);
                z = true;
            }
        }
        return z;
    }

    @Override // java.util.Collection, java.util.Set
    public int size() {
        return this.f100d;
    }

    @Override // java.util.Collection, java.util.Set
    public Object[] toArray() {
        int i2 = this.f100d;
        Object[] objArr = new Object[i2];
        System.arraycopy(this.f99c, 0, objArr, 0, i2);
        return objArr;
    }

    public String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.f100d * 14);
        sb.append('{');
        for (int i2 = 0; i2 < this.f100d; i2++) {
            if (i2 > 0) {
                sb.append(", ");
            }
            E c2 = c(i2);
            if (c2 != this) {
                sb.append(c2);
            } else {
                sb.append("(this Set)");
            }
        }
        sb.append('}');
        return sb.toString();
    }

    public b(int i2) {
        if (i2 == 0) {
            this.f98b = f97f;
            this.f99c = g;
        } else {
            d(i2);
        }
        this.f100d = 0;
    }

    @Override // java.util.Collection, java.util.Set
    public <T> T[] toArray(T[] tArr) {
        if (tArr.length < this.f100d) {
            tArr = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), this.f100d));
        }
        System.arraycopy(this.f99c, 0, tArr, 0, this.f100d);
        int length = tArr.length;
        int i2 = this.f100d;
        if (length > i2) {
            tArr[i2] = null;
        }
        return tArr;
    }

    private static void a(int[] iArr, Object[] objArr, int i2) {
        if (iArr.length == 8) {
            synchronized (b.class) {
                if (k < 10) {
                    objArr[0] = j;
                    objArr[1] = iArr;
                    for (int i3 = i2 - 1; i3 >= 2; i3--) {
                        objArr[i3] = null;
                    }
                    j = objArr;
                    k++;
                }
            }
            return;
        }
        if (iArr.length == 4) {
            synchronized (b.class) {
                if (i < 10) {
                    objArr[0] = h;
                    objArr[1] = iArr;
                    for (int i4 = i2 - 1; i4 >= 2; i4--) {
                        objArr[i4] = null;
                    }
                    h = objArr;
                    i++;
                }
            }
        }
    }

    public E b(int i2) {
        Object[] objArr = this.f99c;
        E e2 = (E) objArr[i2];
        int i3 = this.f100d;
        if (i3 <= 1) {
            a(this.f98b, objArr, i3);
            this.f98b = f97f;
            this.f99c = g;
            this.f100d = 0;
        } else {
            int[] iArr = this.f98b;
            if (iArr.length > 8 && i3 < iArr.length / 3) {
                int i4 = i3 > 8 ? i3 + (i3 >> 1) : 8;
                int[] iArr2 = this.f98b;
                Object[] objArr2 = this.f99c;
                d(i4);
                this.f100d--;
                if (i2 > 0) {
                    System.arraycopy(iArr2, 0, this.f98b, 0, i2);
                    System.arraycopy(objArr2, 0, this.f99c, 0, i2);
                }
                int i5 = this.f100d;
                if (i2 < i5) {
                    int i6 = i2 + 1;
                    System.arraycopy(iArr2, i6, this.f98b, i2, i5 - i2);
                    System.arraycopy(objArr2, i6, this.f99c, i2, this.f100d - i2);
                }
            } else {
                this.f100d--;
                int i7 = this.f100d;
                if (i2 < i7) {
                    int[] iArr3 = this.f98b;
                    int i8 = i2 + 1;
                    System.arraycopy(iArr3, i8, iArr3, i2, i7 - i2);
                    Object[] objArr3 = this.f99c;
                    System.arraycopy(objArr3, i8, objArr3, i2, this.f100d - i2);
                }
                this.f99c[this.f100d] = null;
            }
        }
        return e2;
    }

    public void a(int i2) {
        int[] iArr = this.f98b;
        if (iArr.length < i2) {
            Object[] objArr = this.f99c;
            d(i2);
            int i3 = this.f100d;
            if (i3 > 0) {
                System.arraycopy(iArr, 0, this.f98b, 0, i3);
                System.arraycopy(objArr, 0, this.f99c, 0, this.f100d);
            }
            a(iArr, objArr, this.f100d);
        }
    }

    private f<E, E> a() {
        if (this.f101e == null) {
            this.f101e = new a();
        }
        return this.f101e;
    }
}
