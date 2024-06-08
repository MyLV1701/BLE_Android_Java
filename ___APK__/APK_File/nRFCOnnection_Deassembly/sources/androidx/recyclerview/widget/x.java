package androidx.recyclerview.widget;

import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class x {

    /* renamed from: a, reason: collision with root package name */
    final a.d.g<RecyclerView.d0, a> f1936a = new a.d.g<>();

    /* renamed from: b, reason: collision with root package name */
    final a.d.d<RecyclerView.d0> f1937b = new a.d.d<>();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface b {
        void a(RecyclerView.d0 d0Var);

        void a(RecyclerView.d0 d0Var, RecyclerView.l.c cVar, RecyclerView.l.c cVar2);

        void b(RecyclerView.d0 d0Var, RecyclerView.l.c cVar, RecyclerView.l.c cVar2);

        void c(RecyclerView.d0 d0Var, RecyclerView.l.c cVar, RecyclerView.l.c cVar2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a() {
        this.f1936a.clear();
        this.f1937b.a();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean b(RecyclerView.d0 d0Var) {
        a aVar = this.f1936a.get(d0Var);
        return (aVar == null || (aVar.f1939a & 1) == 0) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void c(RecyclerView.d0 d0Var, RecyclerView.l.c cVar) {
        a aVar = this.f1936a.get(d0Var);
        if (aVar == null) {
            aVar = a.b();
            this.f1936a.put(d0Var, aVar);
        }
        aVar.f1940b = cVar;
        aVar.f1939a |= 4;
    }

    public void d(RecyclerView.d0 d0Var) {
        g(d0Var);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public RecyclerView.l.c e(RecyclerView.d0 d0Var) {
        return a(d0Var, 8);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public RecyclerView.l.c f(RecyclerView.d0 d0Var) {
        return a(d0Var, 4);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void g(RecyclerView.d0 d0Var) {
        a aVar = this.f1936a.get(d0Var);
        if (aVar == null) {
            return;
        }
        aVar.f1939a &= -2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void h(RecyclerView.d0 d0Var) {
        int b2 = this.f1937b.b() - 1;
        while (true) {
            if (b2 < 0) {
                break;
            }
            if (d0Var == this.f1937b.c(b2)) {
                this.f1937b.b(b2);
                break;
            }
            b2--;
        }
        a remove = this.f1936a.remove(d0Var);
        if (remove != null) {
            a.a(remove);
        }
    }

    private RecyclerView.l.c a(RecyclerView.d0 d0Var, int i) {
        a d2;
        RecyclerView.l.c cVar;
        int a2 = this.f1936a.a(d0Var);
        if (a2 >= 0 && (d2 = this.f1936a.d(a2)) != null) {
            int i2 = d2.f1939a;
            if ((i2 & i) != 0) {
                d2.f1939a = (i ^ (-1)) & i2;
                if (i == 4) {
                    cVar = d2.f1940b;
                } else if (i == 8) {
                    cVar = d2.f1941c;
                } else {
                    throw new IllegalArgumentException("Must provide flag PRE or POST");
                }
                if ((d2.f1939a & 12) == 0) {
                    this.f1936a.c(a2);
                    a.a(d2);
                }
                return cVar;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(RecyclerView.d0 d0Var, RecyclerView.l.c cVar) {
        a aVar = this.f1936a.get(d0Var);
        if (aVar == null) {
            aVar = a.b();
            this.f1936a.put(d0Var, aVar);
        }
        aVar.f1941c = cVar;
        aVar.f1939a |= 8;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: d, reason: collision with root package name */
        static a.f.k.e<a> f1938d = new a.f.k.f(20);

        /* renamed from: a, reason: collision with root package name */
        int f1939a;

        /* renamed from: b, reason: collision with root package name */
        RecyclerView.l.c f1940b;

        /* renamed from: c, reason: collision with root package name */
        RecyclerView.l.c f1941c;

        private a() {
        }

        static void a(a aVar) {
            aVar.f1939a = 0;
            aVar.f1940b = null;
            aVar.f1941c = null;
            f1938d.a(aVar);
        }

        static a b() {
            a a2 = f1938d.a();
            return a2 == null ? new a() : a2;
        }

        static void a() {
            do {
            } while (f1938d.a() != null);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean c(RecyclerView.d0 d0Var) {
        a aVar = this.f1936a.get(d0Var);
        return (aVar == null || (aVar.f1939a & 4) == 0) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b() {
        a.a();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(long j, RecyclerView.d0 d0Var) {
        this.f1937b.c(j, d0Var);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(RecyclerView.d0 d0Var, RecyclerView.l.c cVar) {
        a aVar = this.f1936a.get(d0Var);
        if (aVar == null) {
            aVar = a.b();
            this.f1936a.put(d0Var, aVar);
        }
        aVar.f1939a |= 2;
        aVar.f1940b = cVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public RecyclerView.d0 a(long j) {
        return this.f1937b.b(j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(RecyclerView.d0 d0Var) {
        a aVar = this.f1936a.get(d0Var);
        if (aVar == null) {
            aVar = a.b();
            this.f1936a.put(d0Var, aVar);
        }
        aVar.f1939a |= 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(b bVar) {
        for (int size = this.f1936a.size() - 1; size >= 0; size--) {
            RecyclerView.d0 b2 = this.f1936a.b(size);
            a c2 = this.f1936a.c(size);
            int i = c2.f1939a;
            if ((i & 3) == 3) {
                bVar.a(b2);
            } else if ((i & 1) != 0) {
                RecyclerView.l.c cVar = c2.f1940b;
                if (cVar == null) {
                    bVar.a(b2);
                } else {
                    bVar.b(b2, cVar, c2.f1941c);
                }
            } else if ((i & 14) == 14) {
                bVar.a(b2, c2.f1940b, c2.f1941c);
            } else if ((i & 12) == 12) {
                bVar.c(b2, c2.f1940b, c2.f1941c);
            } else if ((i & 4) != 0) {
                bVar.b(b2, c2.f1940b, null);
            } else if ((i & 8) != 0) {
                bVar.a(b2, c2.f1940b, c2.f1941c);
            }
            a.a(c2);
        }
    }
}
