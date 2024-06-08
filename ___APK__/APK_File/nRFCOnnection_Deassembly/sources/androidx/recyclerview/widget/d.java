package androidx.recyclerview.widget;

import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class d {

    /* renamed from: a, reason: collision with root package name */
    final b f1794a;

    /* renamed from: b, reason: collision with root package name */
    final a f1795b = new a();

    /* renamed from: c, reason: collision with root package name */
    final List<View> f1796c = new ArrayList();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface b {
        int a();

        View a(int i);

        void a(View view);

        void a(View view, int i);

        void a(View view, int i, ViewGroup.LayoutParams layoutParams);

        RecyclerView.d0 b(View view);

        void b();

        void b(int i);

        void c(int i);

        void c(View view);

        int d(View view);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public d(b bVar) {
        this.f1794a = bVar;
    }

    private int f(int i) {
        if (i < 0) {
            return -1;
        }
        int a2 = this.f1794a.a();
        int i2 = i;
        while (i2 < a2) {
            int b2 = i - (i2 - this.f1795b.b(i2));
            if (b2 == 0) {
                while (this.f1795b.c(i2)) {
                    i2++;
                }
                return i2;
            }
            i2 += b2;
        }
        return -1;
    }

    private void g(View view) {
        this.f1796c.add(view);
        this.f1794a.a(view);
    }

    private boolean h(View view) {
        if (!this.f1796c.remove(view)) {
            return false;
        }
        this.f1794a.c(view);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(View view, boolean z) {
        a(view, -1, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public View b(int i) {
        int size = this.f1796c.size();
        for (int i2 = 0; i2 < size; i2++) {
            View view = this.f1796c.get(i2);
            RecyclerView.d0 b2 = this.f1794a.b(view);
            if (b2.getLayoutPosition() == i && !b2.isInvalid() && !b2.isRemoved()) {
                return view;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public View c(int i) {
        return this.f1794a.a(f(i));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void d(View view) {
        int d2 = this.f1794a.d(view);
        if (d2 < 0) {
            return;
        }
        if (this.f1795b.d(d2)) {
            h(view);
        }
        this.f1794a.c(d2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void e(int i) {
        int f2 = f(i);
        View a2 = this.f1794a.a(f2);
        if (a2 == null) {
            return;
        }
        if (this.f1795b.d(f2)) {
            h(a2);
        }
        this.f1794a.c(f2);
    }

    public String toString() {
        return this.f1795b.toString() + ", hidden list:" + this.f1796c.size();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a, reason: collision with root package name */
        long f1797a = 0;

        /* renamed from: b, reason: collision with root package name */
        a f1798b;

        a() {
        }

        private void b() {
            if (this.f1798b == null) {
                this.f1798b = new a();
            }
        }

        void a(int i) {
            if (i >= 64) {
                a aVar = this.f1798b;
                if (aVar != null) {
                    aVar.a(i - 64);
                    return;
                }
                return;
            }
            this.f1797a &= (1 << i) ^ (-1);
        }

        boolean c(int i) {
            if (i < 64) {
                return (this.f1797a & (1 << i)) != 0;
            }
            b();
            return this.f1798b.c(i - 64);
        }

        boolean d(int i) {
            if (i >= 64) {
                b();
                return this.f1798b.d(i - 64);
            }
            long j = 1 << i;
            boolean z = (this.f1797a & j) != 0;
            this.f1797a &= j ^ (-1);
            long j2 = j - 1;
            long j3 = this.f1797a;
            this.f1797a = Long.rotateRight(j3 & (j2 ^ (-1)), 1) | (j3 & j2);
            a aVar = this.f1798b;
            if (aVar != null) {
                if (aVar.c(0)) {
                    e(63);
                }
                this.f1798b.d(0);
            }
            return z;
        }

        void e(int i) {
            if (i >= 64) {
                b();
                this.f1798b.e(i - 64);
            } else {
                this.f1797a |= 1 << i;
            }
        }

        public String toString() {
            if (this.f1798b == null) {
                return Long.toBinaryString(this.f1797a);
            }
            return this.f1798b.toString() + "xx" + Long.toBinaryString(this.f1797a);
        }

        int b(int i) {
            a aVar = this.f1798b;
            if (aVar == null) {
                if (i >= 64) {
                    return Long.bitCount(this.f1797a);
                }
                return Long.bitCount(this.f1797a & ((1 << i) - 1));
            }
            if (i < 64) {
                return Long.bitCount(this.f1797a & ((1 << i) - 1));
            }
            return aVar.b(i - 64) + Long.bitCount(this.f1797a);
        }

        void a() {
            this.f1797a = 0L;
            a aVar = this.f1798b;
            if (aVar != null) {
                aVar.a();
            }
        }

        void a(int i, boolean z) {
            if (i >= 64) {
                b();
                this.f1798b.a(i - 64, z);
                return;
            }
            boolean z2 = (this.f1797a & Long.MIN_VALUE) != 0;
            long j = (1 << i) - 1;
            long j2 = this.f1797a;
            this.f1797a = ((j2 & (j ^ (-1))) << 1) | (j2 & j);
            if (z) {
                e(i);
            } else {
                a(i);
            }
            if (z2 || this.f1798b != null) {
                b();
                this.f1798b.a(0, z2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(View view, int i, boolean z) {
        int f2;
        if (i < 0) {
            f2 = this.f1794a.a();
        } else {
            f2 = f(i);
        }
        this.f1795b.a(f2, z);
        if (z) {
            g(view);
        }
        this.f1794a.a(view, f2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void c() {
        this.f1795b.a();
        for (int size = this.f1796c.size() - 1; size >= 0; size--) {
            this.f1794a.c(this.f1796c.get(size));
            this.f1796c.remove(size);
        }
        this.f1794a.b();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void f(View view) {
        int d2 = this.f1794a.d(view);
        if (d2 >= 0) {
            if (this.f1795b.c(d2)) {
                this.f1795b.a(d2);
                h(view);
                return;
            } else {
                throw new RuntimeException("trying to unhide a view that was not hidden" + view);
            }
        }
        throw new IllegalArgumentException("view is not a child, cannot hide " + view);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public View d(int i) {
        return this.f1794a.a(i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean e(View view) {
        int d2 = this.f1794a.d(view);
        if (d2 == -1) {
            h(view);
            return true;
        }
        if (!this.f1795b.c(d2)) {
            return false;
        }
        this.f1795b.d(d2);
        h(view);
        this.f1794a.c(d2);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(View view, int i, ViewGroup.LayoutParams layoutParams, boolean z) {
        int f2;
        if (i < 0) {
            f2 = this.f1794a.a();
        } else {
            f2 = f(i);
        }
        this.f1795b.a(f2, z);
        if (z) {
            g(view);
        }
        this.f1794a.a(view, f2, layoutParams);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int b() {
        return this.f1794a.a();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int b(View view) {
        int d2 = this.f1794a.d(view);
        if (d2 == -1 || this.f1795b.c(d2)) {
            return -1;
        }
        return d2 - this.f1795b.b(d2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean c(View view) {
        return this.f1796c.contains(view);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int a() {
        return this.f1794a.a() - this.f1796c.size();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(int i) {
        int f2 = f(i);
        this.f1795b.d(f2);
        this.f1794a.b(f2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(View view) {
        int d2 = this.f1794a.d(view);
        if (d2 >= 0) {
            this.f1795b.e(d2);
            g(view);
        } else {
            throw new IllegalArgumentException("view is not a child, cannot hide " + view);
        }
    }
}
