package a.d;

/* loaded from: classes.dex */
public class d<E> implements Cloneable {

    /* renamed from: f, reason: collision with root package name */
    private static final Object f106f = new Object();

    /* renamed from: b, reason: collision with root package name */
    private boolean f107b;

    /* renamed from: c, reason: collision with root package name */
    private long[] f108c;

    /* renamed from: d, reason: collision with root package name */
    private Object[] f109d;

    /* renamed from: e, reason: collision with root package name */
    private int f110e;

    public d() {
        this(10);
    }

    private void c() {
        int i = this.f110e;
        long[] jArr = this.f108c;
        Object[] objArr = this.f109d;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            Object obj = objArr[i3];
            if (obj != f106f) {
                if (i3 != i2) {
                    jArr[i2] = jArr[i3];
                    objArr[i2] = obj;
                    objArr[i3] = null;
                }
                i2++;
            }
        }
        this.f107b = false;
        this.f110e = i2;
    }

    @Deprecated
    public void a(long j) {
        d(j);
    }

    public E b(long j) {
        return b(j, null);
    }

    public void d(long j) {
        int a2 = c.a(this.f108c, this.f110e, j);
        if (a2 >= 0) {
            Object[] objArr = this.f109d;
            Object obj = objArr[a2];
            Object obj2 = f106f;
            if (obj != obj2) {
                objArr[a2] = obj2;
                this.f107b = true;
            }
        }
    }

    public String toString() {
        if (b() <= 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.f110e * 28);
        sb.append('{');
        for (int i = 0; i < this.f110e; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(a(i));
            sb.append('=');
            E c2 = c(i);
            if (c2 != this) {
                sb.append(c2);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        return sb.toString();
    }

    public d(int i) {
        this.f107b = false;
        if (i == 0) {
            this.f108c = c.f104b;
            this.f109d = c.f105c;
        } else {
            int c2 = c.c(i);
            this.f108c = new long[c2];
            this.f109d = new Object[c2];
        }
    }

    public long a(int i) {
        if (this.f107b) {
            c();
        }
        return this.f108c[i];
    }

    public E b(long j, E e2) {
        int a2 = c.a(this.f108c, this.f110e, j);
        if (a2 >= 0) {
            Object[] objArr = this.f109d;
            if (objArr[a2] != f106f) {
                return (E) objArr[a2];
            }
        }
        return e2;
    }

    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public d<E> m0clone() {
        try {
            d<E> dVar = (d) super.clone();
            dVar.f108c = (long[]) this.f108c.clone();
            dVar.f109d = (Object[]) this.f109d.clone();
            return dVar;
        } catch (CloneNotSupportedException e2) {
            throw new AssertionError(e2);
        }
    }

    public void a() {
        int i = this.f110e;
        Object[] objArr = this.f109d;
        for (int i2 = 0; i2 < i; i2++) {
            objArr[i2] = null;
        }
        this.f110e = 0;
        this.f107b = false;
    }

    public void b(int i) {
        Object[] objArr = this.f109d;
        Object obj = objArr[i];
        Object obj2 = f106f;
        if (obj != obj2) {
            objArr[i] = obj2;
            this.f107b = true;
        }
    }

    public int b() {
        if (this.f107b) {
            c();
        }
        return this.f110e;
    }

    public void a(long j, E e2) {
        int i = this.f110e;
        if (i != 0 && j <= this.f108c[i - 1]) {
            c(j, e2);
            return;
        }
        if (this.f107b && this.f110e >= this.f108c.length) {
            c();
        }
        int i2 = this.f110e;
        if (i2 >= this.f108c.length) {
            int c2 = c.c(i2 + 1);
            long[] jArr = new long[c2];
            Object[] objArr = new Object[c2];
            long[] jArr2 = this.f108c;
            System.arraycopy(jArr2, 0, jArr, 0, jArr2.length);
            Object[] objArr2 = this.f109d;
            System.arraycopy(objArr2, 0, objArr, 0, objArr2.length);
            this.f108c = jArr;
            this.f109d = objArr;
        }
        this.f108c[i2] = j;
        this.f109d[i2] = e2;
        this.f110e = i2 + 1;
    }

    public void c(long j, E e2) {
        int a2 = c.a(this.f108c, this.f110e, j);
        if (a2 >= 0) {
            this.f109d[a2] = e2;
            return;
        }
        int i = a2 ^ (-1);
        if (i < this.f110e) {
            Object[] objArr = this.f109d;
            if (objArr[i] == f106f) {
                this.f108c[i] = j;
                objArr[i] = e2;
                return;
            }
        }
        if (this.f107b && this.f110e >= this.f108c.length) {
            c();
            i = c.a(this.f108c, this.f110e, j) ^ (-1);
        }
        int i2 = this.f110e;
        if (i2 >= this.f108c.length) {
            int c2 = c.c(i2 + 1);
            long[] jArr = new long[c2];
            Object[] objArr2 = new Object[c2];
            long[] jArr2 = this.f108c;
            System.arraycopy(jArr2, 0, jArr, 0, jArr2.length);
            Object[] objArr3 = this.f109d;
            System.arraycopy(objArr3, 0, objArr2, 0, objArr3.length);
            this.f108c = jArr;
            this.f109d = objArr2;
        }
        int i3 = this.f110e;
        if (i3 - i != 0) {
            long[] jArr3 = this.f108c;
            int i4 = i + 1;
            System.arraycopy(jArr3, i, jArr3, i4, i3 - i);
            Object[] objArr4 = this.f109d;
            System.arraycopy(objArr4, i, objArr4, i4, this.f110e - i);
        }
        this.f108c[i] = j;
        this.f109d[i] = e2;
        this.f110e++;
    }

    public E c(int i) {
        if (this.f107b) {
            c();
        }
        return (E) this.f109d[i];
    }

    public int c(long j) {
        if (this.f107b) {
            c();
        }
        return c.a(this.f108c, this.f110e, j);
    }
}
