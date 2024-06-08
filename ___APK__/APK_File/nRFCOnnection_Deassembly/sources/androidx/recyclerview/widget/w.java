package androidx.recyclerview.widget;

import android.view.View;

/* loaded from: classes.dex */
class w {

    /* renamed from: a, reason: collision with root package name */
    final b f1929a;

    /* renamed from: b, reason: collision with root package name */
    a f1930b = new a();

    /* loaded from: classes.dex */
    interface b {
        int a();

        int a(View view);

        View a(int i);

        int b();

        int b(View view);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public w(b bVar) {
        this.f1929a = bVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public View a(int i, int i2, int i3, int i4) {
        int b2 = this.f1929a.b();
        int a2 = this.f1929a.a();
        int i5 = i2 > i ? 1 : -1;
        View view = null;
        while (i != i2) {
            View a3 = this.f1929a.a(i);
            this.f1930b.a(b2, a2, this.f1929a.a(a3), this.f1929a.b(a3));
            if (i3 != 0) {
                this.f1930b.b();
                this.f1930b.a(i3);
                if (this.f1930b.a()) {
                    return a3;
                }
            }
            if (i4 != 0) {
                this.f1930b.b();
                this.f1930b.a(i4);
                if (this.f1930b.a()) {
                    view = a3;
                }
            }
            i += i5;
        }
        return view;
    }

    /* loaded from: classes.dex */
    static class a {

        /* renamed from: a, reason: collision with root package name */
        int f1931a = 0;

        /* renamed from: b, reason: collision with root package name */
        int f1932b;

        /* renamed from: c, reason: collision with root package name */
        int f1933c;

        /* renamed from: d, reason: collision with root package name */
        int f1934d;

        /* renamed from: e, reason: collision with root package name */
        int f1935e;

        a() {
        }

        int a(int i, int i2) {
            if (i > i2) {
                return 1;
            }
            return i == i2 ? 2 : 4;
        }

        void a(int i, int i2, int i3, int i4) {
            this.f1932b = i;
            this.f1933c = i2;
            this.f1934d = i3;
            this.f1935e = i4;
        }

        void b() {
            this.f1931a = 0;
        }

        void a(int i) {
            this.f1931a = i | this.f1931a;
        }

        boolean a() {
            int i = this.f1931a;
            if ((i & 7) != 0 && (i & (a(this.f1934d, this.f1932b) << 0)) == 0) {
                return false;
            }
            int i2 = this.f1931a;
            if ((i2 & 112) != 0 && (i2 & (a(this.f1934d, this.f1933c) << 4)) == 0) {
                return false;
            }
            int i3 = this.f1931a;
            if ((i3 & 1792) != 0 && (i3 & (a(this.f1935e, this.f1932b) << 8)) == 0) {
                return false;
            }
            int i4 = this.f1931a;
            return (i4 & 28672) == 0 || (i4 & (a(this.f1935e, this.f1933c) << 12)) != 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean a(View view, int i) {
        this.f1930b.a(this.f1929a.b(), this.f1929a.a(), this.f1929a.a(view), this.f1929a.b(view));
        if (i == 0) {
            return false;
        }
        this.f1930b.b();
        this.f1930b.a(i);
        return this.f1930b.a();
    }
}
