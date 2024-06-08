package a.d;

/* loaded from: classes.dex */
public class h<E> implements Cloneable {

    /* renamed from: f, reason: collision with root package name */
    private static final Object f137f = new Object();

    /* renamed from: b, reason: collision with root package name */
    private boolean f138b;

    /* renamed from: c, reason: collision with root package name */
    private int[] f139c;

    /* renamed from: d, reason: collision with root package name */
    private Object[] f140d;

    /* renamed from: e, reason: collision with root package name */
    private int f141e;

    public h() {
        this(10);
    }

    private void c() {
        int i = this.f141e;
        int[] iArr = this.f139c;
        Object[] objArr = this.f140d;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            Object obj = objArr[i3];
            if (obj != f137f) {
                if (i3 != i2) {
                    iArr[i2] = iArr[i3];
                    objArr[i2] = obj;
                    objArr[i3] = null;
                }
                i2++;
            }
        }
        this.f138b = false;
        this.f141e = i2;
    }

    public E a(int i) {
        return b(i, null);
    }

    public E b(int i, E e2) {
        int a2 = c.a(this.f139c, this.f141e, i);
        if (a2 >= 0) {
            Object[] objArr = this.f140d;
            if (objArr[a2] != f137f) {
                return (E) objArr[a2];
            }
        }
        return e2;
    }

    public void d(int i) {
        int a2 = c.a(this.f139c, this.f141e, i);
        if (a2 >= 0) {
            Object[] objArr = this.f140d;
            Object obj = objArr[a2];
            Object obj2 = f137f;
            if (obj != obj2) {
                objArr[a2] = obj2;
                this.f138b = true;
            }
        }
    }

    public E e(int i) {
        if (this.f138b) {
            c();
        }
        return (E) this.f140d[i];
    }

    public String toString() {
        if (b() <= 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.f141e * 28);
        sb.append('{');
        for (int i = 0; i < this.f141e; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(c(i));
            sb.append('=');
            E e2 = e(i);
            if (e2 != this) {
                sb.append(e2);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        return sb.toString();
    }

    public h(int i) {
        this.f138b = false;
        if (i == 0) {
            this.f139c = c.f103a;
            this.f140d = c.f105c;
        } else {
            int b2 = c.b(i);
            this.f139c = new int[b2];
            this.f140d = new Object[b2];
        }
    }

    public int a(E e2) {
        if (this.f138b) {
            c();
        }
        for (int i = 0; i < this.f141e; i++) {
            if (this.f140d[i] == e2) {
                return i;
            }
        }
        return -1;
    }

    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public h<E> m1clone() {
        try {
            h<E> hVar = (h) super.clone();
            hVar.f139c = (int[]) this.f139c.clone();
            hVar.f140d = (Object[]) this.f140d.clone();
            return hVar;
        } catch (CloneNotSupportedException e2) {
            throw new AssertionError(e2);
        }
    }

    public int b() {
        if (this.f138b) {
            c();
        }
        return this.f141e;
    }

    public void a() {
        int i = this.f141e;
        Object[] objArr = this.f140d;
        for (int i2 = 0; i2 < i; i2++) {
            objArr[i2] = null;
        }
        this.f141e = 0;
        this.f138b = false;
    }

    public int b(int i) {
        if (this.f138b) {
            c();
        }
        return c.a(this.f139c, this.f141e, i);
    }

    public void a(int i, E e2) {
        int i2 = this.f141e;
        if (i2 != 0 && i <= this.f139c[i2 - 1]) {
            c(i, e2);
            return;
        }
        if (this.f138b && this.f141e >= this.f139c.length) {
            c();
        }
        int i3 = this.f141e;
        if (i3 >= this.f139c.length) {
            int b2 = c.b(i3 + 1);
            int[] iArr = new int[b2];
            Object[] objArr = new Object[b2];
            int[] iArr2 = this.f139c;
            System.arraycopy(iArr2, 0, iArr, 0, iArr2.length);
            Object[] objArr2 = this.f140d;
            System.arraycopy(objArr2, 0, objArr, 0, objArr2.length);
            this.f139c = iArr;
            this.f140d = objArr;
        }
        this.f139c[i3] = i;
        this.f140d[i3] = e2;
        this.f141e = i3 + 1;
    }

    public void c(int i, E e2) {
        int a2 = c.a(this.f139c, this.f141e, i);
        if (a2 >= 0) {
            this.f140d[a2] = e2;
            return;
        }
        int i2 = a2 ^ (-1);
        if (i2 < this.f141e) {
            Object[] objArr = this.f140d;
            if (objArr[i2] == f137f) {
                this.f139c[i2] = i;
                objArr[i2] = e2;
                return;
            }
        }
        if (this.f138b && this.f141e >= this.f139c.length) {
            c();
            i2 = c.a(this.f139c, this.f141e, i) ^ (-1);
        }
        int i3 = this.f141e;
        if (i3 >= this.f139c.length) {
            int b2 = c.b(i3 + 1);
            int[] iArr = new int[b2];
            Object[] objArr2 = new Object[b2];
            int[] iArr2 = this.f139c;
            System.arraycopy(iArr2, 0, iArr, 0, iArr2.length);
            Object[] objArr3 = this.f140d;
            System.arraycopy(objArr3, 0, objArr2, 0, objArr3.length);
            this.f139c = iArr;
            this.f140d = objArr2;
        }
        int i4 = this.f141e;
        if (i4 - i2 != 0) {
            int[] iArr3 = this.f139c;
            int i5 = i2 + 1;
            System.arraycopy(iArr3, i2, iArr3, i5, i4 - i2);
            Object[] objArr4 = this.f140d;
            System.arraycopy(objArr4, i2, objArr4, i5, this.f141e - i2);
        }
        this.f139c[i2] = i;
        this.f140d[i2] = e2;
        this.f141e++;
    }

    public int c(int i) {
        if (this.f138b) {
            c();
        }
        return this.f139c[i];
    }
}
