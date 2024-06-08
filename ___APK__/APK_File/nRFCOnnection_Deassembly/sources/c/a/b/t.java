package c.a.b;

import java.io.IOException;

/* loaded from: classes.dex */
public abstract class t<T> {
    public final t<T> a() {
        return new a();
    }

    public abstract T a(c.a.b.y.a aVar);

    public abstract void a(c.a.b.y.c cVar, T t);

    /* loaded from: classes.dex */
    class a extends t<T> {
        a() {
        }

        @Override // c.a.b.t
        public void a(c.a.b.y.c cVar, T t) {
            if (t == null) {
                cVar.g();
            } else {
                t.this.a(cVar, t);
            }
        }

        @Override // c.a.b.t
        public T a(c.a.b.y.a aVar) {
            if (aVar.q() == c.a.b.y.b.NULL) {
                aVar.o();
                return null;
            }
            return (T) t.this.a(aVar);
        }
    }

    public final j a(T t) {
        try {
            c.a.b.w.n.f fVar = new c.a.b.w.n.f();
            a(fVar, t);
            return fVar.h();
        } catch (IOException e2) {
            throw new k(e2);
        }
    }
}
