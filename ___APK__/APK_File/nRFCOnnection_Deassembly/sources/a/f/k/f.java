package a.f.k;

/* loaded from: classes.dex */
public class f<T> implements e<T> {

    /* renamed from: a, reason: collision with root package name */
    private final Object[] f270a;

    /* renamed from: b, reason: collision with root package name */
    private int f271b;

    public f(int i) {
        if (i > 0) {
            this.f270a = new Object[i];
            return;
        }
        throw new IllegalArgumentException("The max pool size must be > 0");
    }

    private boolean b(T t) {
        for (int i = 0; i < this.f271b; i++) {
            if (this.f270a[i] == t) {
                return true;
            }
        }
        return false;
    }

    @Override // a.f.k.e
    public T a() {
        int i = this.f271b;
        if (i <= 0) {
            return null;
        }
        int i2 = i - 1;
        Object[] objArr = this.f270a;
        T t = (T) objArr[i2];
        objArr[i2] = null;
        this.f271b = i - 1;
        return t;
    }

    @Override // a.f.k.e
    public boolean a(T t) {
        if (!b(t)) {
            int i = this.f271b;
            Object[] objArr = this.f270a;
            if (i >= objArr.length) {
                return false;
            }
            objArr[i] = t;
            this.f271b = i + 1;
            return true;
        }
        throw new IllegalStateException("Already in the pool!");
    }
}
