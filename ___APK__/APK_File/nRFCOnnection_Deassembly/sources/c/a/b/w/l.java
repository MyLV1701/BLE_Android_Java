package c.a.b.w;

import c.a.b.r;
import c.a.b.w.n.n;
import java.io.EOFException;
import java.io.IOException;

/* loaded from: classes.dex */
public final class l {
    public static c.a.b.j a(c.a.b.y.a aVar) {
        boolean z;
        try {
            try {
                aVar.q();
                z = false;
            } catch (EOFException e2) {
                e = e2;
                z = true;
            }
            try {
                return n.X.a2(aVar);
            } catch (EOFException e3) {
                e = e3;
                if (z) {
                    return c.a.b.l.f2187a;
                }
                throw new r(e);
            }
        } catch (c.a.b.y.d e4) {
            throw new r(e4);
        } catch (IOException e5) {
            throw new c.a.b.k(e5);
        } catch (NumberFormatException e6) {
            throw new r(e6);
        }
    }

    public static void a(c.a.b.j jVar, c.a.b.y.c cVar) {
        n.X.a(cVar, jVar);
    }
}
