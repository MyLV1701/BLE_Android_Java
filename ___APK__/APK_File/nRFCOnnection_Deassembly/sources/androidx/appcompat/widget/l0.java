package androidx.appcompat.widget;

import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes.dex */
class l0 {

    /* renamed from: a, reason: collision with root package name */
    private int f1030a = 0;

    /* renamed from: b, reason: collision with root package name */
    private int f1031b = 0;

    /* renamed from: c, reason: collision with root package name */
    private int f1032c = RecyclerView.UNDEFINED_DURATION;

    /* renamed from: d, reason: collision with root package name */
    private int f1033d = RecyclerView.UNDEFINED_DURATION;

    /* renamed from: e, reason: collision with root package name */
    private int f1034e = 0;

    /* renamed from: f, reason: collision with root package name */
    private int f1035f = 0;
    private boolean g = false;
    private boolean h = false;

    public int a() {
        return this.g ? this.f1030a : this.f1031b;
    }

    public int b() {
        return this.f1030a;
    }

    public int c() {
        return this.f1031b;
    }

    public int d() {
        return this.g ? this.f1031b : this.f1030a;
    }

    public void a(int i, int i2) {
        this.h = false;
        if (i != Integer.MIN_VALUE) {
            this.f1034e = i;
            this.f1030a = i;
        }
        if (i2 != Integer.MIN_VALUE) {
            this.f1035f = i2;
            this.f1031b = i2;
        }
    }

    public void b(int i, int i2) {
        this.f1032c = i;
        this.f1033d = i2;
        this.h = true;
        if (this.g) {
            if (i2 != Integer.MIN_VALUE) {
                this.f1030a = i2;
            }
            if (i != Integer.MIN_VALUE) {
                this.f1031b = i;
                return;
            }
            return;
        }
        if (i != Integer.MIN_VALUE) {
            this.f1030a = i;
        }
        if (i2 != Integer.MIN_VALUE) {
            this.f1031b = i2;
        }
    }

    public void a(boolean z) {
        if (z == this.g) {
            return;
        }
        this.g = z;
        if (!this.h) {
            this.f1030a = this.f1034e;
            this.f1031b = this.f1035f;
            return;
        }
        if (z) {
            int i = this.f1033d;
            if (i == Integer.MIN_VALUE) {
                i = this.f1034e;
            }
            this.f1030a = i;
            int i2 = this.f1032c;
            if (i2 == Integer.MIN_VALUE) {
                i2 = this.f1035f;
            }
            this.f1031b = i2;
            return;
        }
        int i3 = this.f1032c;
        if (i3 == Integer.MIN_VALUE) {
            i3 = this.f1034e;
        }
        this.f1030a = i3;
        int i4 = this.f1033d;
        if (i4 == Integer.MIN_VALUE) {
            i4 = this.f1035f;
        }
        this.f1031b = i4;
    }
}
