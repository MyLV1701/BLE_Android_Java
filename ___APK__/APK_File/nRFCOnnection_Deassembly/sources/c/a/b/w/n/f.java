package c.a.b.w.n;

import c.a.b.o;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public final class f extends c.a.b.y.c {
    private static final Writer p = new a();
    private static final o q = new o("closed");
    private final List<c.a.b.j> m;
    private String n;
    private c.a.b.j o;

    /* loaded from: classes.dex */
    class a extends Writer {
        a() {
        }

        @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            throw new AssertionError();
        }

        @Override // java.io.Writer, java.io.Flushable
        public void flush() {
            throw new AssertionError();
        }

        @Override // java.io.Writer
        public void write(char[] cArr, int i, int i2) {
            throw new AssertionError();
        }
    }

    public f() {
        super(p);
        this.m = new ArrayList();
        this.o = c.a.b.l.f2187a;
    }

    private void a(c.a.b.j jVar) {
        if (this.n != null) {
            if (!jVar.e() || e()) {
                ((c.a.b.m) i()).a(this.n, jVar);
            }
            this.n = null;
            return;
        }
        if (this.m.isEmpty()) {
            this.o = jVar;
            return;
        }
        c.a.b.j i = i();
        if (i instanceof c.a.b.g) {
            ((c.a.b.g) i).a(jVar);
            return;
        }
        throw new IllegalStateException();
    }

    private c.a.b.j i() {
        return this.m.get(r0.size() - 1);
    }

    @Override // c.a.b.y.c
    public c.a.b.y.c b() {
        c.a.b.m mVar = new c.a.b.m();
        a(mVar);
        this.m.add(mVar);
        return this;
    }

    @Override // c.a.b.y.c
    public c.a.b.y.c c() {
        if (!this.m.isEmpty() && this.n == null) {
            if (i() instanceof c.a.b.g) {
                this.m.remove(r0.size() - 1);
                return this;
            }
            throw new IllegalStateException();
        }
        throw new IllegalStateException();
    }

    @Override // c.a.b.y.c, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.m.isEmpty()) {
            this.m.add(q);
            return;
        }
        throw new IOException("Incomplete document");
    }

    @Override // c.a.b.y.c
    public c.a.b.y.c d() {
        if (!this.m.isEmpty() && this.n == null) {
            if (i() instanceof c.a.b.m) {
                this.m.remove(r0.size() - 1);
                return this;
            }
            throw new IllegalStateException();
        }
        throw new IllegalStateException();
    }

    @Override // c.a.b.y.c, java.io.Flushable
    public void flush() {
    }

    @Override // c.a.b.y.c
    public c.a.b.y.c g() {
        a(c.a.b.l.f2187a);
        return this;
    }

    public c.a.b.j h() {
        if (this.m.isEmpty()) {
            return this.o;
        }
        throw new IllegalStateException("Expected one JSON element but was " + this.m);
    }

    @Override // c.a.b.y.c
    public c.a.b.y.c b(String str) {
        if (str == null) {
            g();
            return this;
        }
        a(new o(str));
        return this;
    }

    @Override // c.a.b.y.c
    public c.a.b.y.c b(boolean z) {
        a(new o(Boolean.valueOf(z)));
        return this;
    }

    @Override // c.a.b.y.c
    public c.a.b.y.c a() {
        c.a.b.g gVar = new c.a.b.g();
        a(gVar);
        this.m.add(gVar);
        return this;
    }

    @Override // c.a.b.y.c
    public c.a.b.y.c a(String str) {
        if (!this.m.isEmpty() && this.n == null) {
            if (i() instanceof c.a.b.m) {
                this.n = str;
                return this;
            }
            throw new IllegalStateException();
        }
        throw new IllegalStateException();
    }

    @Override // c.a.b.y.c
    public c.a.b.y.c a(Boolean bool) {
        if (bool == null) {
            g();
            return this;
        }
        a(new o(bool));
        return this;
    }

    @Override // c.a.b.y.c
    public c.a.b.y.c a(long j) {
        a(new o(Long.valueOf(j)));
        return this;
    }

    @Override // c.a.b.y.c
    public c.a.b.y.c a(Number number) {
        if (number == null) {
            g();
            return this;
        }
        if (!f()) {
            double doubleValue = number.doubleValue();
            if (Double.isNaN(doubleValue) || Double.isInfinite(doubleValue)) {
                throw new IllegalArgumentException("JSON forbids NaN and infinities: " + number);
            }
        }
        a(new o(number));
        return this;
    }
}
