package a.a.o;

import a.f.l.a0;
import a.f.l.b0;
import a.f.l.c0;
import android.view.View;
import android.view.animation.Interpolator;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
public class h {

    /* renamed from: c, reason: collision with root package name */
    private Interpolator f67c;

    /* renamed from: d, reason: collision with root package name */
    b0 f68d;

    /* renamed from: e, reason: collision with root package name */
    private boolean f69e;

    /* renamed from: b, reason: collision with root package name */
    private long f66b = -1;

    /* renamed from: f, reason: collision with root package name */
    private final c0 f70f = new a();

    /* renamed from: a, reason: collision with root package name */
    final ArrayList<a0> f65a = new ArrayList<>();

    /* loaded from: classes.dex */
    class a extends c0 {

        /* renamed from: a, reason: collision with root package name */
        private boolean f71a = false;

        /* renamed from: b, reason: collision with root package name */
        private int f72b = 0;

        a() {
        }

        void a() {
            this.f72b = 0;
            this.f71a = false;
            h.this.b();
        }

        @Override // a.f.l.b0
        public void b(View view) {
            int i = this.f72b + 1;
            this.f72b = i;
            if (i == h.this.f65a.size()) {
                b0 b0Var = h.this.f68d;
                if (b0Var != null) {
                    b0Var.b(null);
                }
                a();
            }
        }

        @Override // a.f.l.c0, a.f.l.b0
        public void c(View view) {
            if (this.f71a) {
                return;
            }
            this.f71a = true;
            b0 b0Var = h.this.f68d;
            if (b0Var != null) {
                b0Var.c(null);
            }
        }
    }

    public h a(a0 a0Var) {
        if (!this.f69e) {
            this.f65a.add(a0Var);
        }
        return this;
    }

    void b() {
        this.f69e = false;
    }

    public void c() {
        if (this.f69e) {
            return;
        }
        Iterator<a0> it = this.f65a.iterator();
        while (it.hasNext()) {
            a0 next = it.next();
            long j = this.f66b;
            if (j >= 0) {
                next.a(j);
            }
            Interpolator interpolator = this.f67c;
            if (interpolator != null) {
                next.a(interpolator);
            }
            if (this.f68d != null) {
                next.a(this.f70f);
            }
            next.c();
        }
        this.f69e = true;
    }

    public h a(a0 a0Var, a0 a0Var2) {
        this.f65a.add(a0Var);
        a0Var2.b(a0Var.b());
        this.f65a.add(a0Var2);
        return this;
    }

    public void a() {
        if (this.f69e) {
            Iterator<a0> it = this.f65a.iterator();
            while (it.hasNext()) {
                it.next().a();
            }
            this.f69e = false;
        }
    }

    public h a(long j) {
        if (!this.f69e) {
            this.f66b = j;
        }
        return this;
    }

    public h a(Interpolator interpolator) {
        if (!this.f69e) {
            this.f67c = interpolator;
        }
        return this;
    }

    public h a(b0 b0Var) {
        if (!this.f69e) {
            this.f68d = b0Var;
        }
        return this;
    }
}
