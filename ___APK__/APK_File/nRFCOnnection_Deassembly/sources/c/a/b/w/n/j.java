package c.a.b.w.n;

import c.a.b.r;
import c.a.b.t;
import c.a.b.u;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/* loaded from: classes.dex */
public final class j extends t<Date> {

    /* renamed from: b, reason: collision with root package name */
    public static final u f2278b = new a();

    /* renamed from: a, reason: collision with root package name */
    private final DateFormat f2279a = new SimpleDateFormat("MMM d, yyyy");

    /* loaded from: classes.dex */
    class a implements u {
        a() {
        }

        @Override // c.a.b.u
        public <T> t<T> a(c.a.b.e eVar, c.a.b.x.a<T> aVar) {
            if (aVar.a() == Date.class) {
                return new j();
            }
            return null;
        }
    }

    @Override // c.a.b.t
    /* renamed from: a, reason: avoid collision after fix types in other method */
    public synchronized Date a2(c.a.b.y.a aVar) {
        if (aVar.q() == c.a.b.y.b.NULL) {
            aVar.o();
            return null;
        }
        try {
            return new Date(this.f2279a.parse(aVar.p()).getTime());
        } catch (ParseException e2) {
            throw new r(e2);
        }
    }

    @Override // c.a.b.t
    public synchronized void a(c.a.b.y.c cVar, Date date) {
        cVar.b(date == null ? null : this.f2279a.format((java.util.Date) date));
    }
}
