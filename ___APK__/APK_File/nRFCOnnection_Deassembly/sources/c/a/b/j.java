package c.a.b;

import java.io.IOException;
import java.io.StringWriter;

/* loaded from: classes.dex */
public abstract class j {
    public g a() {
        if (d()) {
            return (g) this;
        }
        throw new IllegalStateException("Not a JSON Array: " + this);
    }

    public m b() {
        if (f()) {
            return (m) this;
        }
        throw new IllegalStateException("Not a JSON Object: " + this);
    }

    public o c() {
        if (g()) {
            return (o) this;
        }
        throw new IllegalStateException("Not a JSON Primitive: " + this);
    }

    public boolean d() {
        return this instanceof g;
    }

    public boolean e() {
        return this instanceof l;
    }

    public boolean f() {
        return this instanceof m;
    }

    public boolean g() {
        return this instanceof o;
    }

    public String toString() {
        try {
            StringWriter stringWriter = new StringWriter();
            c.a.b.y.c cVar = new c.a.b.y.c(stringWriter);
            cVar.a(true);
            c.a.b.w.l.a(this, cVar);
            return stringWriter.toString();
        } catch (IOException e2) {
            throw new AssertionError(e2);
        }
    }
}
