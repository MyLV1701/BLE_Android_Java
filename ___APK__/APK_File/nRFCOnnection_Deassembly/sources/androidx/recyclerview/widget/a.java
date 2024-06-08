package androidx.recyclerview.widget;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.q;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class a implements q.a {

    /* renamed from: a, reason: collision with root package name */
    private a.f.k.e<b> f1778a;

    /* renamed from: b, reason: collision with root package name */
    final ArrayList<b> f1779b;

    /* renamed from: c, reason: collision with root package name */
    final ArrayList<b> f1780c;

    /* renamed from: d, reason: collision with root package name */
    final InterfaceC0059a f1781d;

    /* renamed from: e, reason: collision with root package name */
    Runnable f1782e;

    /* renamed from: f, reason: collision with root package name */
    final boolean f1783f;
    final q g;
    private int h;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: androidx.recyclerview.widget.a$a, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public interface InterfaceC0059a {
        RecyclerView.d0 a(int i);

        void a(int i, int i2);

        void a(int i, int i2, Object obj);

        void a(b bVar);

        void b(int i, int i2);

        void b(b bVar);

        void c(int i, int i2);

        void d(int i, int i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class b {

        /* renamed from: a, reason: collision with root package name */
        int f1784a;

        /* renamed from: b, reason: collision with root package name */
        int f1785b;

        /* renamed from: c, reason: collision with root package name */
        Object f1786c;

        /* renamed from: d, reason: collision with root package name */
        int f1787d;

        b(int i, int i2, int i3, Object obj) {
            this.f1784a = i;
            this.f1785b = i2;
            this.f1787d = i3;
            this.f1786c = obj;
        }

        String a() {
            int i = this.f1784a;
            return i != 1 ? i != 2 ? i != 4 ? i != 8 ? "??" : "mv" : "up" : "rm" : "add";
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || b.class != obj.getClass()) {
                return false;
            }
            b bVar = (b) obj;
            int i = this.f1784a;
            if (i != bVar.f1784a) {
                return false;
            }
            if (i == 8 && Math.abs(this.f1787d - this.f1785b) == 1 && this.f1787d == bVar.f1785b && this.f1785b == bVar.f1787d) {
                return true;
            }
            if (this.f1787d != bVar.f1787d || this.f1785b != bVar.f1785b) {
                return false;
            }
            Object obj2 = this.f1786c;
            if (obj2 != null) {
                if (!obj2.equals(bVar.f1786c)) {
                    return false;
                }
            } else if (bVar.f1786c != null) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return (((this.f1784a * 31) + this.f1785b) * 31) + this.f1787d;
        }

        public String toString() {
            return Integer.toHexString(System.identityHashCode(this)) + "[" + a() + ",s:" + this.f1785b + "c:" + this.f1787d + ",p:" + this.f1786c + "]";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public a(InterfaceC0059a interfaceC0059a) {
        this(interfaceC0059a, false);
    }

    private void b(b bVar) {
        g(bVar);
    }

    private void c(b bVar) {
        g(bVar);
    }

    private void d(b bVar) {
        boolean z;
        char c2;
        int i = bVar.f1785b;
        int i2 = bVar.f1787d + i;
        int i3 = 0;
        char c3 = 65535;
        int i4 = i;
        while (i4 < i2) {
            if (this.f1781d.a(i4) != null || d(i4)) {
                if (c3 == 0) {
                    f(a(2, i, i3, null));
                    z = true;
                } else {
                    z = false;
                }
                c2 = 1;
            } else {
                if (c3 == 1) {
                    g(a(2, i, i3, null));
                    z = true;
                } else {
                    z = false;
                }
                c2 = 0;
            }
            if (z) {
                i4 -= i3;
                i2 -= i3;
                i3 = 1;
            } else {
                i3++;
            }
            i4++;
            c3 = c2;
        }
        if (i3 != bVar.f1787d) {
            a(bVar);
            bVar = a(2, i, i3, null);
        }
        if (c3 == 0) {
            f(bVar);
        } else {
            g(bVar);
        }
    }

    private void g(b bVar) {
        this.f1780c.add(bVar);
        int i = bVar.f1784a;
        if (i == 1) {
            this.f1781d.c(bVar.f1785b, bVar.f1787d);
            return;
        }
        if (i == 2) {
            this.f1781d.b(bVar.f1785b, bVar.f1787d);
            return;
        }
        if (i == 4) {
            this.f1781d.a(bVar.f1785b, bVar.f1787d, bVar.f1786c);
        } else {
            if (i == 8) {
                this.f1781d.a(bVar.f1785b, bVar.f1787d);
                return;
            }
            throw new IllegalArgumentException("Unknown update op type for " + bVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a() {
        int size = this.f1780c.size();
        for (int i = 0; i < size; i++) {
            this.f1781d.a(this.f1780c.get(i));
        }
        a(this.f1780c);
        this.h = 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void e() {
        this.g.a(this.f1779b);
        int size = this.f1779b.size();
        for (int i = 0; i < size; i++) {
            b bVar = this.f1779b.get(i);
            int i2 = bVar.f1784a;
            if (i2 == 1) {
                b(bVar);
            } else if (i2 == 2) {
                d(bVar);
            } else if (i2 == 4) {
                e(bVar);
            } else if (i2 == 8) {
                c(bVar);
            }
            Runnable runnable = this.f1782e;
            if (runnable != null) {
                runnable.run();
            }
        }
        this.f1779b.clear();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void f() {
        a(this.f1779b);
        a(this.f1780c);
        this.h = 0;
    }

    a(InterfaceC0059a interfaceC0059a, boolean z) {
        this.f1778a = new a.f.k.f(30);
        this.f1779b = new ArrayList<>();
        this.f1780c = new ArrayList<>();
        this.h = 0;
        this.f1781d = interfaceC0059a;
        this.f1783f = z;
        this.g = new q(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int b(int i) {
        return a(i, 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean c() {
        return this.f1779b.size() > 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean b(int i, int i2) {
        if (i2 < 1) {
            return false;
        }
        this.f1779b.add(a(1, i, i2, null));
        this.h |= 1;
        return this.f1779b.size() == 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean c(int i) {
        return (i & this.h) != 0;
    }

    private void f(b bVar) {
        int i;
        int i2 = bVar.f1784a;
        if (i2 != 1 && i2 != 8) {
            int d2 = d(bVar.f1785b, i2);
            int i3 = bVar.f1785b;
            int i4 = bVar.f1784a;
            if (i4 == 2) {
                i = 0;
            } else {
                if (i4 != 4) {
                    throw new IllegalArgumentException("op should be remove or update." + bVar);
                }
                i = 1;
            }
            int i5 = d2;
            int i6 = i3;
            int i7 = 1;
            for (int i8 = 1; i8 < bVar.f1787d; i8++) {
                int d3 = d(bVar.f1785b + (i * i8), bVar.f1784a);
                int i9 = bVar.f1784a;
                if (i9 == 2 ? d3 == i5 : i9 == 4 && d3 == i5 + 1) {
                    i7++;
                } else {
                    b a2 = a(bVar.f1784a, i5, i7, bVar.f1786c);
                    a(a2, i6);
                    a(a2);
                    if (bVar.f1784a == 4) {
                        i6 += i7;
                    }
                    i5 = d3;
                    i7 = 1;
                }
            }
            Object obj = bVar.f1786c;
            a(bVar);
            if (i7 > 0) {
                b a3 = a(bVar.f1784a, i5, i7, obj);
                a(a3, i6);
                a(a3);
                return;
            }
            return;
        }
        throw new IllegalArgumentException("should not dispatch add or move for pre layout");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean c(int i, int i2) {
        if (i2 < 1) {
            return false;
        }
        this.f1779b.add(a(2, i, i2, null));
        this.h |= 2;
        return this.f1779b.size() == 1;
    }

    void a(b bVar, int i) {
        this.f1781d.b(bVar);
        int i2 = bVar.f1784a;
        if (i2 == 2) {
            this.f1781d.d(i, bVar.f1787d);
        } else {
            if (i2 == 4) {
                this.f1781d.a(i, bVar.f1787d, bVar.f1786c);
                return;
            }
            throw new IllegalArgumentException("only remove and update ops can be dispatched in first pass");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b() {
        a();
        int size = this.f1779b.size();
        for (int i = 0; i < size; i++) {
            b bVar = this.f1779b.get(i);
            int i2 = bVar.f1784a;
            if (i2 == 1) {
                this.f1781d.a(bVar);
                this.f1781d.c(bVar.f1785b, bVar.f1787d);
            } else if (i2 == 2) {
                this.f1781d.a(bVar);
                this.f1781d.d(bVar.f1785b, bVar.f1787d);
            } else if (i2 == 4) {
                this.f1781d.a(bVar);
                this.f1781d.a(bVar.f1785b, bVar.f1787d, bVar.f1786c);
            } else if (i2 == 8) {
                this.f1781d.a(bVar);
                this.f1781d.a(bVar.f1785b, bVar.f1787d);
            }
            Runnable runnable = this.f1782e;
            if (runnable != null) {
                runnable.run();
            }
        }
        a(this.f1779b);
        this.h = 0;
    }

    int a(int i, int i2) {
        int size = this.f1780c.size();
        while (i2 < size) {
            b bVar = this.f1780c.get(i2);
            int i3 = bVar.f1784a;
            if (i3 == 8) {
                int i4 = bVar.f1785b;
                if (i4 == i) {
                    i = bVar.f1787d;
                } else {
                    if (i4 < i) {
                        i--;
                    }
                    if (bVar.f1787d <= i) {
                        i++;
                    }
                }
            } else {
                int i5 = bVar.f1785b;
                if (i5 > i) {
                    continue;
                } else if (i3 == 2) {
                    int i6 = bVar.f1787d;
                    if (i < i5 + i6) {
                        return -1;
                    }
                    i -= i6;
                } else if (i3 == 1) {
                    i += bVar.f1787d;
                }
            }
            i2++;
        }
        return i;
    }

    private void e(b bVar) {
        int i = bVar.f1785b;
        int i2 = bVar.f1787d + i;
        int i3 = i;
        int i4 = 0;
        char c2 = 65535;
        while (i < i2) {
            if (this.f1781d.a(i) != null || d(i)) {
                if (c2 == 0) {
                    f(a(4, i3, i4, bVar.f1786c));
                    i3 = i;
                    i4 = 0;
                }
                c2 = 1;
            } else {
                if (c2 == 1) {
                    g(a(4, i3, i4, bVar.f1786c));
                    i3 = i;
                    i4 = 0;
                }
                c2 = 0;
            }
            i4++;
            i++;
        }
        if (i4 != bVar.f1787d) {
            Object obj = bVar.f1786c;
            a(bVar);
            bVar = a(4, i3, i4, obj);
        }
        if (c2 == 0) {
            f(bVar);
        } else {
            g(bVar);
        }
    }

    private int d(int i, int i2) {
        for (int size = this.f1780c.size() - 1; size >= 0; size--) {
            b bVar = this.f1780c.get(size);
            int i3 = bVar.f1784a;
            if (i3 == 8) {
                int i4 = bVar.f1785b;
                int i5 = bVar.f1787d;
                if (i4 >= i5) {
                    i5 = i4;
                    i4 = i5;
                }
                if (i >= i4 && i <= i5) {
                    int i6 = bVar.f1785b;
                    if (i4 == i6) {
                        if (i2 == 1) {
                            bVar.f1787d++;
                        } else if (i2 == 2) {
                            bVar.f1787d--;
                        }
                        i++;
                    } else {
                        if (i2 == 1) {
                            bVar.f1785b = i6 + 1;
                        } else if (i2 == 2) {
                            bVar.f1785b = i6 - 1;
                        }
                        i--;
                    }
                } else {
                    int i7 = bVar.f1785b;
                    if (i < i7) {
                        if (i2 == 1) {
                            bVar.f1785b = i7 + 1;
                            bVar.f1787d++;
                        } else if (i2 == 2) {
                            bVar.f1785b = i7 - 1;
                            bVar.f1787d--;
                        }
                    }
                }
            } else {
                int i8 = bVar.f1785b;
                if (i8 <= i) {
                    if (i3 == 1) {
                        i -= bVar.f1787d;
                    } else if (i3 == 2) {
                        i += bVar.f1787d;
                    }
                } else if (i2 == 1) {
                    bVar.f1785b = i8 + 1;
                } else if (i2 == 2) {
                    bVar.f1785b = i8 - 1;
                }
            }
        }
        for (int size2 = this.f1780c.size() - 1; size2 >= 0; size2--) {
            b bVar2 = this.f1780c.get(size2);
            if (bVar2.f1784a == 8) {
                int i9 = bVar2.f1787d;
                if (i9 == bVar2.f1785b || i9 < 0) {
                    this.f1780c.remove(size2);
                    a(bVar2);
                }
            } else if (bVar2.f1787d <= 0) {
                this.f1780c.remove(size2);
                a(bVar2);
            }
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean a(int i, int i2, Object obj) {
        if (i2 < 1) {
            return false;
        }
        this.f1779b.add(a(4, i, i2, obj));
        this.h |= 4;
        return this.f1779b.size() == 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean a(int i, int i2, int i3) {
        if (i == i2) {
            return false;
        }
        if (i3 == 1) {
            this.f1779b.add(a(8, i, i2, null));
            this.h |= 8;
            return this.f1779b.size() == 1;
        }
        throw new IllegalArgumentException("Moving more than 1 item is not supported yet");
    }

    public int a(int i) {
        int size = this.f1779b.size();
        for (int i2 = 0; i2 < size; i2++) {
            b bVar = this.f1779b.get(i2);
            int i3 = bVar.f1784a;
            if (i3 != 1) {
                if (i3 == 2) {
                    int i4 = bVar.f1785b;
                    if (i4 <= i) {
                        int i5 = bVar.f1787d;
                        if (i4 + i5 > i) {
                            return -1;
                        }
                        i -= i5;
                    } else {
                        continue;
                    }
                } else if (i3 == 8) {
                    int i6 = bVar.f1785b;
                    if (i6 == i) {
                        i = bVar.f1787d;
                    } else {
                        if (i6 < i) {
                            i--;
                        }
                        if (bVar.f1787d <= i) {
                            i++;
                        }
                    }
                }
            } else if (bVar.f1785b <= i) {
                i += bVar.f1787d;
            }
        }
        return i;
    }

    @Override // androidx.recyclerview.widget.q.a
    public b a(int i, int i2, int i3, Object obj) {
        b a2 = this.f1778a.a();
        if (a2 == null) {
            return new b(i, i2, i3, obj);
        }
        a2.f1784a = i;
        a2.f1785b = i2;
        a2.f1787d = i3;
        a2.f1786c = obj;
        return a2;
    }

    private boolean d(int i) {
        int size = this.f1780c.size();
        for (int i2 = 0; i2 < size; i2++) {
            b bVar = this.f1780c.get(i2);
            int i3 = bVar.f1784a;
            if (i3 == 8) {
                if (a(bVar.f1787d, i2 + 1) == i) {
                    return true;
                }
            } else if (i3 == 1) {
                int i4 = bVar.f1785b;
                int i5 = bVar.f1787d + i4;
                while (i4 < i5) {
                    if (a(i4, i2 + 1) == i) {
                        return true;
                    }
                    i4++;
                }
            } else {
                continue;
            }
        }
        return false;
    }

    @Override // androidx.recyclerview.widget.q.a
    public void a(b bVar) {
        if (this.f1783f) {
            return;
        }
        bVar.f1786c = null;
        this.f1778a.a(bVar);
    }

    void a(List<b> list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            a(list.get(i));
        }
        list.clear();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean d() {
        return (this.f1780c.isEmpty() || this.f1779b.isEmpty()) ? false : true;
    }
}
