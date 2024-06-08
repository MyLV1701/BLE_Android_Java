package a.d;

import java.util.ConcurrentModificationException;
import java.util.Map;

/* loaded from: classes.dex */
public class g<K, V> {

    /* renamed from: e, reason: collision with root package name */
    static Object[] f132e;

    /* renamed from: f, reason: collision with root package name */
    static int f133f;
    static Object[] g;
    static int h;

    /* renamed from: b, reason: collision with root package name */
    int[] f134b;

    /* renamed from: c, reason: collision with root package name */
    Object[] f135c;

    /* renamed from: d, reason: collision with root package name */
    int f136d;

    public g() {
        this.f134b = c.f103a;
        this.f135c = c.f105c;
        this.f136d = 0;
    }

    private static int a(int[] iArr, int i, int i2) {
        try {
            return c.a(iArr, i, i2);
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw new ConcurrentModificationException();
        }
    }

    private void e(int i) {
        if (i == 8) {
            synchronized (g.class) {
                if (g != null) {
                    Object[] objArr = g;
                    this.f135c = objArr;
                    g = (Object[]) objArr[0];
                    this.f134b = (int[]) objArr[1];
                    objArr[1] = null;
                    objArr[0] = null;
                    h--;
                    return;
                }
            }
        } else if (i == 4) {
            synchronized (g.class) {
                if (f132e != null) {
                    Object[] objArr2 = f132e;
                    this.f135c = objArr2;
                    f132e = (Object[]) objArr2[0];
                    this.f134b = (int[]) objArr2[1];
                    objArr2[1] = null;
                    objArr2[0] = null;
                    f133f--;
                    return;
                }
            }
        }
        this.f134b = new int[i];
        this.f135c = new Object[i << 1];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int b(Object obj) {
        int i = this.f136d * 2;
        Object[] objArr = this.f135c;
        if (obj == null) {
            for (int i2 = 1; i2 < i; i2 += 2) {
                if (objArr[i2] == null) {
                    return i2 >> 1;
                }
            }
            return -1;
        }
        for (int i3 = 1; i3 < i; i3 += 2) {
            if (obj.equals(objArr[i3])) {
                return i3 >> 1;
            }
        }
        return -1;
    }

    public V c(int i) {
        int i2;
        Object[] objArr = this.f135c;
        int i3 = i << 1;
        V v = (V) objArr[i3 + 1];
        int i4 = this.f136d;
        if (i4 <= 1) {
            a(this.f134b, objArr, i4);
            this.f134b = c.f103a;
            this.f135c = c.f105c;
            i2 = 0;
        } else {
            i2 = i4 - 1;
            int[] iArr = this.f134b;
            if (iArr.length > 8 && i4 < iArr.length / 3) {
                int i5 = i4 > 8 ? i4 + (i4 >> 1) : 8;
                int[] iArr2 = this.f134b;
                Object[] objArr2 = this.f135c;
                e(i5);
                if (i4 != this.f136d) {
                    throw new ConcurrentModificationException();
                }
                if (i > 0) {
                    System.arraycopy(iArr2, 0, this.f134b, 0, i);
                    System.arraycopy(objArr2, 0, this.f135c, 0, i3);
                }
                if (i < i2) {
                    int i6 = i + 1;
                    int i7 = i2 - i;
                    System.arraycopy(iArr2, i6, this.f134b, i, i7);
                    System.arraycopy(objArr2, i6 << 1, this.f135c, i3, i7 << 1);
                }
            } else {
                if (i < i2) {
                    int[] iArr3 = this.f134b;
                    int i8 = i + 1;
                    int i9 = i2 - i;
                    System.arraycopy(iArr3, i8, iArr3, i, i9);
                    Object[] objArr3 = this.f135c;
                    System.arraycopy(objArr3, i8 << 1, objArr3, i3, i9 << 1);
                }
                Object[] objArr4 = this.f135c;
                int i10 = i2 << 1;
                objArr4[i10] = null;
                objArr4[i10 + 1] = null;
            }
        }
        if (i4 == this.f136d) {
            this.f136d = i2;
            return v;
        }
        throw new ConcurrentModificationException();
    }

    public void clear() {
        int i = this.f136d;
        if (i > 0) {
            int[] iArr = this.f134b;
            Object[] objArr = this.f135c;
            this.f134b = c.f103a;
            this.f135c = c.f105c;
            this.f136d = 0;
            a(iArr, objArr, i);
        }
        if (this.f136d > 0) {
            throw new ConcurrentModificationException();
        }
    }

    public boolean containsKey(Object obj) {
        return a(obj) >= 0;
    }

    public boolean containsValue(Object obj) {
        return b(obj) >= 0;
    }

    public V d(int i) {
        return (V) this.f135c[(i << 1) + 1];
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof g) {
            g gVar = (g) obj;
            if (size() != gVar.size()) {
                return false;
            }
            for (int i = 0; i < this.f136d; i++) {
                try {
                    K b2 = b(i);
                    V d2 = d(i);
                    Object obj2 = gVar.get(b2);
                    if (d2 == null) {
                        if (obj2 != null || !gVar.containsKey(b2)) {
                            return false;
                        }
                    } else if (!d2.equals(obj2)) {
                        return false;
                    }
                } catch (ClassCastException | NullPointerException unused) {
                    return false;
                }
            }
            return true;
        }
        if (obj instanceof Map) {
            Map map = (Map) obj;
            if (size() != map.size()) {
                return false;
            }
            for (int i2 = 0; i2 < this.f136d; i2++) {
                try {
                    K b3 = b(i2);
                    V d3 = d(i2);
                    Object obj3 = map.get(b3);
                    if (d3 == null) {
                        if (obj3 != null || !map.containsKey(b3)) {
                            return false;
                        }
                    } else if (!d3.equals(obj3)) {
                        return false;
                    }
                } catch (ClassCastException | NullPointerException unused2) {
                }
            }
            return true;
        }
        return false;
    }

    public V get(Object obj) {
        return getOrDefault(obj, null);
    }

    public V getOrDefault(Object obj, V v) {
        int a2 = a(obj);
        return a2 >= 0 ? (V) this.f135c[(a2 << 1) + 1] : v;
    }

    public int hashCode() {
        int[] iArr = this.f134b;
        Object[] objArr = this.f135c;
        int i = this.f136d;
        int i2 = 0;
        int i3 = 0;
        int i4 = 1;
        while (i2 < i) {
            Object obj = objArr[i4];
            i3 += (obj == null ? 0 : obj.hashCode()) ^ iArr[i2];
            i2++;
            i4 += 2;
        }
        return i3;
    }

    public boolean isEmpty() {
        return this.f136d <= 0;
    }

    public V put(K k, V v) {
        int i;
        int a2;
        int i2 = this.f136d;
        if (k == null) {
            a2 = a();
            i = 0;
        } else {
            int hashCode = k.hashCode();
            i = hashCode;
            a2 = a(k, hashCode);
        }
        if (a2 >= 0) {
            int i3 = (a2 << 1) + 1;
            Object[] objArr = this.f135c;
            V v2 = (V) objArr[i3];
            objArr[i3] = v;
            return v2;
        }
        int i4 = a2 ^ (-1);
        if (i2 >= this.f134b.length) {
            int i5 = 4;
            if (i2 >= 8) {
                i5 = (i2 >> 1) + i2;
            } else if (i2 >= 4) {
                i5 = 8;
            }
            int[] iArr = this.f134b;
            Object[] objArr2 = this.f135c;
            e(i5);
            if (i2 == this.f136d) {
                int[] iArr2 = this.f134b;
                if (iArr2.length > 0) {
                    System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
                    System.arraycopy(objArr2, 0, this.f135c, 0, objArr2.length);
                }
                a(iArr, objArr2, i2);
            } else {
                throw new ConcurrentModificationException();
            }
        }
        if (i4 < i2) {
            int[] iArr3 = this.f134b;
            int i6 = i4 + 1;
            System.arraycopy(iArr3, i4, iArr3, i6, i2 - i4);
            Object[] objArr3 = this.f135c;
            System.arraycopy(objArr3, i4 << 1, objArr3, i6 << 1, (this.f136d - i4) << 1);
        }
        int i7 = this.f136d;
        if (i2 == i7) {
            int[] iArr4 = this.f134b;
            if (i4 < iArr4.length) {
                iArr4[i4] = i;
                Object[] objArr4 = this.f135c;
                int i8 = i4 << 1;
                objArr4[i8] = k;
                objArr4[i8 + 1] = v;
                this.f136d = i7 + 1;
                return null;
            }
        }
        throw new ConcurrentModificationException();
    }

    public V putIfAbsent(K k, V v) {
        V v2 = get(k);
        return v2 == null ? put(k, v) : v2;
    }

    public V remove(Object obj) {
        int a2 = a(obj);
        if (a2 >= 0) {
            return c(a2);
        }
        return null;
    }

    public V replace(K k, V v) {
        int a2 = a(k);
        if (a2 >= 0) {
            return a(a2, (int) v);
        }
        return null;
    }

    public int size() {
        return this.f136d;
    }

    public String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.f136d * 28);
        sb.append('{');
        for (int i = 0; i < this.f136d; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            K b2 = b(i);
            if (b2 != this) {
                sb.append(b2);
            } else {
                sb.append("(this Map)");
            }
            sb.append('=');
            V d2 = d(i);
            if (d2 != this) {
                sb.append(d2);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        return sb.toString();
    }

    int a(Object obj, int i) {
        int i2 = this.f136d;
        if (i2 == 0) {
            return -1;
        }
        int a2 = a(this.f134b, i2, i);
        if (a2 < 0 || obj.equals(this.f135c[a2 << 1])) {
            return a2;
        }
        int i3 = a2 + 1;
        while (i3 < i2 && this.f134b[i3] == i) {
            if (obj.equals(this.f135c[i3 << 1])) {
                return i3;
            }
            i3++;
        }
        for (int i4 = a2 - 1; i4 >= 0 && this.f134b[i4] == i; i4--) {
            if (obj.equals(this.f135c[i4 << 1])) {
                return i4;
            }
        }
        return i3 ^ (-1);
    }

    public boolean remove(Object obj, Object obj2) {
        int a2 = a(obj);
        if (a2 < 0) {
            return false;
        }
        V d2 = d(a2);
        if (obj2 != d2 && (obj2 == null || !obj2.equals(d2))) {
            return false;
        }
        c(a2);
        return true;
    }

    public boolean replace(K k, V v, V v2) {
        int a2 = a(k);
        if (a2 < 0) {
            return false;
        }
        V d2 = d(a2);
        if (d2 != v && (v == null || !v.equals(d2))) {
            return false;
        }
        a(a2, (int) v2);
        return true;
    }

    public g(int i) {
        if (i == 0) {
            this.f134b = c.f103a;
            this.f135c = c.f105c;
        } else {
            e(i);
        }
        this.f136d = 0;
    }

    public K b(int i) {
        return (K) this.f135c[i << 1];
    }

    /* JADX WARN: Multi-variable type inference failed */
    public g(g<K, V> gVar) {
        this();
        if (gVar != 0) {
            a((g) gVar);
        }
    }

    int a() {
        int i = this.f136d;
        if (i == 0) {
            return -1;
        }
        int a2 = a(this.f134b, i, 0);
        if (a2 < 0 || this.f135c[a2 << 1] == null) {
            return a2;
        }
        int i2 = a2 + 1;
        while (i2 < i && this.f134b[i2] == 0) {
            if (this.f135c[i2 << 1] == null) {
                return i2;
            }
            i2++;
        }
        for (int i3 = a2 - 1; i3 >= 0 && this.f134b[i3] == 0; i3--) {
            if (this.f135c[i3 << 1] == null) {
                return i3;
            }
        }
        return i2 ^ (-1);
    }

    private static void a(int[] iArr, Object[] objArr, int i) {
        if (iArr.length == 8) {
            synchronized (g.class) {
                if (h < 10) {
                    objArr[0] = g;
                    objArr[1] = iArr;
                    for (int i2 = (i << 1) - 1; i2 >= 2; i2--) {
                        objArr[i2] = null;
                    }
                    g = objArr;
                    h++;
                }
            }
            return;
        }
        if (iArr.length == 4) {
            synchronized (g.class) {
                if (f133f < 10) {
                    objArr[0] = f132e;
                    objArr[1] = iArr;
                    for (int i3 = (i << 1) - 1; i3 >= 2; i3--) {
                        objArr[i3] = null;
                    }
                    f132e = objArr;
                    f133f++;
                }
            }
        }
    }

    public void a(int i) {
        int i2 = this.f136d;
        int[] iArr = this.f134b;
        if (iArr.length < i) {
            Object[] objArr = this.f135c;
            e(i);
            if (this.f136d > 0) {
                System.arraycopy(iArr, 0, this.f134b, 0, i2);
                System.arraycopy(objArr, 0, this.f135c, 0, i2 << 1);
            }
            a(iArr, objArr, i2);
        }
        if (this.f136d != i2) {
            throw new ConcurrentModificationException();
        }
    }

    public int a(Object obj) {
        return obj == null ? a() : a(obj, obj.hashCode());
    }

    public V a(int i, V v) {
        int i2 = (i << 1) + 1;
        Object[] objArr = this.f135c;
        V v2 = (V) objArr[i2];
        objArr[i2] = v;
        return v2;
    }

    public void a(g<? extends K, ? extends V> gVar) {
        int i = gVar.f136d;
        a(this.f136d + i);
        if (this.f136d != 0) {
            for (int i2 = 0; i2 < i; i2++) {
                put(gVar.b(i2), gVar.d(i2));
            }
        } else if (i > 0) {
            System.arraycopy(gVar.f134b, 0, this.f134b, 0, i);
            System.arraycopy(gVar.f135c, 0, this.f135c, 0, i << 1);
            this.f136d = i;
        }
    }
}
