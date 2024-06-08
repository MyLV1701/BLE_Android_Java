package a.f.k;

/* loaded from: classes.dex */
public class g<T> extends f<T> {

    /* renamed from: c, reason: collision with root package name */
    private final Object f272c;

    public g(int i) {
        super(i);
        this.f272c = new Object();
    }

    @Override // a.f.k.f, a.f.k.e
    public T a() {
        T t;
        synchronized (this.f272c) {
            t = (T) super.a();
        }
        return t;
    }

    @Override // a.f.k.f, a.f.k.e
    public boolean a(T t) {
        boolean a2;
        synchronized (this.f272c) {
            a2 = super.a(t);
        }
        return a2;
    }
}
