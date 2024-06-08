package c.a.b.w.n;

import c.a.b.r;
import c.a.b.t;
import c.a.b.u;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* loaded from: classes.dex */
public final class c extends t<Date> {

    /* renamed from: b, reason: collision with root package name */
    public static final u f2253b = new a();

    /* renamed from: a, reason: collision with root package name */
    private final List<DateFormat> f2254a = new ArrayList();

    /* loaded from: classes.dex */
    class a implements u {
        a() {
        }

        @Override // c.a.b.u
        public <T> t<T> a(c.a.b.e eVar, c.a.b.x.a<T> aVar) {
            if (aVar.a() == Date.class) {
                return new c();
            }
            return null;
        }
    }

    public c() {
        this.f2254a.add(DateFormat.getDateTimeInstance(2, 2, Locale.US));
        if (!Locale.getDefault().equals(Locale.US)) {
            this.f2254a.add(DateFormat.getDateTimeInstance(2, 2));
        }
        if (c.a.b.w.e.c()) {
            this.f2254a.add(c.a.b.w.j.a(2, 2));
        }
    }

    @Override // c.a.b.t
    /* renamed from: a, reason: avoid collision after fix types in other method */
    public Date a2(c.a.b.y.a aVar) {
        if (aVar.q() == c.a.b.y.b.NULL) {
            aVar.o();
            return null;
        }
        return a(aVar.p());
    }

    private synchronized Date a(String str) {
        Iterator<DateFormat> it = this.f2254a.iterator();
        while (it.hasNext()) {
            try {
                return it.next().parse(str);
            } catch (ParseException unused) {
            }
        }
        try {
            return c.a.b.w.n.o.a.a(str, new ParsePosition(0));
        } catch (ParseException e2) {
            throw new r(str, e2);
        }
    }

    @Override // c.a.b.t
    public synchronized void a(c.a.b.y.c cVar, Date date) {
        if (date == null) {
            cVar.g();
        } else {
            cVar.b(this.f2254a.get(0).format(date));
        }
    }
}
