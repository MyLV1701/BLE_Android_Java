package c.a.b.w.n;

import c.a.b.r;
import c.a.b.t;
import c.a.b.u;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/* loaded from: classes.dex */
public final class k extends t<Time> {

    /* renamed from: b, reason: collision with root package name */
    public static final u f2280b = new a();

    /* renamed from: a, reason: collision with root package name */
    private final DateFormat f2281a = new SimpleDateFormat("hh:mm:ss a");

    /* loaded from: classes.dex */
    class a implements u {
        a() {
        }

        @Override // c.a.b.u
        public <T> t<T> a(c.a.b.e eVar, c.a.b.x.a<T> aVar) {
            if (aVar.a() == Time.class) {
                return new k();
            }
            return null;
        }
    }

    @Override // c.a.b.t
    /* renamed from: a, reason: avoid collision after fix types in other method */
    public synchronized Time a2(c.a.b.y.a aVar) {
        if (aVar.q() == c.a.b.y.b.NULL) {
            aVar.o();
            return null;
        }
        try {
            return new Time(this.f2281a.parse(aVar.p()).getTime());
        } catch (ParseException e2) {
            throw new r(e2);
        }
    }

    @Override // c.a.b.t
    public synchronized void a(c.a.b.y.c cVar, Time time) {
        cVar.b(time == null ? null : this.f2281a.format((Date) time));
    }
}
