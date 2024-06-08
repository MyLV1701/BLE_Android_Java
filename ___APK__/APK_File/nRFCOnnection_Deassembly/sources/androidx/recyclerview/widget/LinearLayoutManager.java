package androidx.recyclerview.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PointF;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import androidx.preference.Preference;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.j;
import java.util.List;
import no.nordicsemi.android.dfu.DfuBaseService;

/* loaded from: classes.dex */
public class LinearLayoutManager extends RecyclerView.o implements j.i, RecyclerView.z.b {
    int A;
    int B;
    private boolean C;
    d D;
    final a E;
    private final b F;
    private int G;
    private int[] H;
    int s;
    private c t;
    r u;
    private boolean v;
    private boolean w;
    boolean x;
    private boolean y;
    private boolean z;

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes.dex */
    public static class b {

        /* renamed from: a, reason: collision with root package name */
        public int f1672a;

        /* renamed from: b, reason: collision with root package name */
        public boolean f1673b;

        /* renamed from: c, reason: collision with root package name */
        public boolean f1674c;

        /* renamed from: d, reason: collision with root package name */
        public boolean f1675d;

        protected b() {
        }

        void a() {
            this.f1672a = 0;
            this.f1673b = false;
            this.f1674c = false;
            this.f1675d = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class c {

        /* renamed from: b, reason: collision with root package name */
        int f1677b;

        /* renamed from: c, reason: collision with root package name */
        int f1678c;

        /* renamed from: d, reason: collision with root package name */
        int f1679d;

        /* renamed from: e, reason: collision with root package name */
        int f1680e;

        /* renamed from: f, reason: collision with root package name */
        int f1681f;
        int g;
        boolean j;
        int k;
        boolean m;

        /* renamed from: a, reason: collision with root package name */
        boolean f1676a = true;
        int h = 0;
        int i = 0;
        List<RecyclerView.d0> l = null;

        c() {
        }

        private View b() {
            int size = this.l.size();
            for (int i = 0; i < size; i++) {
                View view = this.l.get(i).itemView;
                RecyclerView.p pVar = (RecyclerView.p) view.getLayoutParams();
                if (!pVar.c() && this.f1679d == pVar.a()) {
                    a(view);
                    return view;
                }
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public boolean a(RecyclerView.a0 a0Var) {
            int i = this.f1679d;
            return i >= 0 && i < a0Var.a();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public View a(RecyclerView.v vVar) {
            if (this.l != null) {
                return b();
            }
            View d2 = vVar.d(this.f1679d);
            this.f1679d += this.f1680e;
            return d2;
        }

        public void a() {
            a((View) null);
        }

        public void a(View view) {
            View b2 = b(view);
            if (b2 == null) {
                this.f1679d = -1;
            } else {
                this.f1679d = ((RecyclerView.p) b2.getLayoutParams()).a();
            }
        }

        public View b(View view) {
            int a2;
            int size = this.l.size();
            View view2 = null;
            int i = Preference.DEFAULT_ORDER;
            for (int i2 = 0; i2 < size; i2++) {
                View view3 = this.l.get(i2).itemView;
                RecyclerView.p pVar = (RecyclerView.p) view3.getLayoutParams();
                if (view3 != view && !pVar.c() && (a2 = (pVar.a() - this.f1679d) * this.f1680e) >= 0 && a2 < i) {
                    view2 = view3;
                    if (a2 == 0) {
                        break;
                    }
                    i = a2;
                }
            }
            return view2;
        }
    }

    @SuppressLint({"BanParcelableUsage"})
    /* loaded from: classes.dex */
    public static class d implements Parcelable {
        public static final Parcelable.Creator<d> CREATOR = new a();

        /* renamed from: b, reason: collision with root package name */
        int f1682b;

        /* renamed from: c, reason: collision with root package name */
        int f1683c;

        /* renamed from: d, reason: collision with root package name */
        boolean f1684d;

        /* loaded from: classes.dex */
        static class a implements Parcelable.Creator<d> {
            a() {
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public d createFromParcel(Parcel parcel) {
                return new d(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public d[] newArray(int i) {
                return new d[i];
            }
        }

        public d() {
        }

        boolean d() {
            return this.f1682b >= 0;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        void e() {
            this.f1682b = -1;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.f1682b);
            parcel.writeInt(this.f1683c);
            parcel.writeInt(this.f1684d ? 1 : 0);
        }

        d(Parcel parcel) {
            this.f1682b = parcel.readInt();
            this.f1683c = parcel.readInt();
            this.f1684d = parcel.readInt() == 1;
        }

        public d(d dVar) {
            this.f1682b = dVar.f1682b;
            this.f1683c = dVar.f1683c;
            this.f1684d = dVar.f1684d;
        }
    }

    public LinearLayoutManager(Context context) {
        this(context, 1, false);
    }

    private View O() {
        return e(0, e());
    }

    private View P() {
        return e(e() - 1, -1);
    }

    private View Q() {
        return this.x ? O() : P();
    }

    private View R() {
        return this.x ? P() : O();
    }

    private View S() {
        return d(this.x ? 0 : e() - 1);
    }

    private View T() {
        return d(this.x ? e() - 1 : 0);
    }

    private void U() {
        if (this.s != 1 && L()) {
            this.x = !this.w;
        } else {
            this.x = this.w;
        }
    }

    private int j(RecyclerView.a0 a0Var) {
        if (e() == 0) {
            return 0;
        }
        F();
        return t.a(a0Var, this.u, b(!this.z, true), a(!this.z, true), this, this.z, this.x);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    boolean B() {
        return (i() == 1073741824 || s() == 1073741824 || !t()) ? false : true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean D() {
        return this.D == null && this.v == this.y;
    }

    c E() {
        return new c();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void F() {
        if (this.t == null) {
            this.t = E();
        }
    }

    public int G() {
        View a2 = a(0, e(), true, false);
        if (a2 == null) {
            return -1;
        }
        return l(a2);
    }

    public int H() {
        View a2 = a(0, e(), false, true);
        if (a2 == null) {
            return -1;
        }
        return l(a2);
    }

    public int I() {
        View a2 = a(e() - 1, -1, true, false);
        if (a2 == null) {
            return -1;
        }
        return l(a2);
    }

    public int J() {
        View a2 = a(e() - 1, -1, false, true);
        if (a2 == null) {
            return -1;
        }
        return l(a2);
    }

    public int K() {
        return this.s;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean L() {
        return k() == 1;
    }

    public boolean M() {
        return this.z;
    }

    boolean N() {
        return this.u.d() == 0 && this.u.a() == 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void a(AccessibilityEvent accessibilityEvent) {
        super.a(accessibilityEvent);
        if (e() > 0) {
            accessibilityEvent.setFromIndex(H());
            accessibilityEvent.setToIndex(J());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(RecyclerView.v vVar, RecyclerView.a0 a0Var, a aVar, int i) {
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void b(RecyclerView recyclerView, RecyclerView.v vVar) {
        super.b(recyclerView, vVar);
        if (this.C) {
            b(vVar);
            vVar.a();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public RecyclerView.p c() {
        return new RecyclerView.p(-2, -2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int d(RecyclerView.a0 a0Var) {
        return i(a0Var);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void e(RecyclerView.v vVar, RecyclerView.a0 a0Var) {
        int i;
        int i2;
        int i3;
        int i4;
        int a2;
        int i5;
        View c2;
        int d2;
        int i6;
        int i7 = -1;
        if ((this.D != null || this.A != -1) && a0Var.a() == 0) {
            b(vVar);
            return;
        }
        d dVar = this.D;
        if (dVar != null && dVar.d()) {
            this.A = this.D.f1682b;
        }
        F();
        this.t.f1676a = false;
        U();
        View g = g();
        if (this.E.f1671e && this.A == -1 && this.D == null) {
            if (g != null && (this.u.d(g) >= this.u.b() || this.u.a(g) <= this.u.f())) {
                this.E.b(g, l(g));
            }
        } else {
            this.E.b();
            a aVar = this.E;
            aVar.f1670d = this.x ^ this.y;
            b(vVar, a0Var, aVar);
            this.E.f1671e = true;
        }
        c cVar = this.t;
        cVar.f1681f = cVar.k >= 0 ? 1 : -1;
        int[] iArr = this.H;
        iArr[0] = 0;
        iArr[1] = 0;
        a(a0Var, iArr);
        int max = Math.max(0, this.H[0]) + this.u.f();
        int max2 = Math.max(0, this.H[1]) + this.u.c();
        if (a0Var.d() && (i5 = this.A) != -1 && this.B != Integer.MIN_VALUE && (c2 = c(i5)) != null) {
            if (this.x) {
                i6 = this.u.b() - this.u.a(c2);
                d2 = this.B;
            } else {
                d2 = this.u.d(c2) - this.u.f();
                i6 = this.B;
            }
            int i8 = i6 - d2;
            if (i8 > 0) {
                max += i8;
            } else {
                max2 -= i8;
            }
        }
        if (!this.E.f1670d ? !this.x : this.x) {
            i7 = 1;
        }
        a(vVar, a0Var, this.E, i7);
        a(vVar);
        this.t.m = N();
        this.t.j = a0Var.d();
        this.t.i = 0;
        a aVar2 = this.E;
        if (aVar2.f1670d) {
            b(aVar2);
            c cVar2 = this.t;
            cVar2.h = max;
            a(vVar, cVar2, a0Var, false);
            c cVar3 = this.t;
            i2 = cVar3.f1677b;
            int i9 = cVar3.f1679d;
            int i10 = cVar3.f1678c;
            if (i10 > 0) {
                max2 += i10;
            }
            a(this.E);
            c cVar4 = this.t;
            cVar4.h = max2;
            cVar4.f1679d += cVar4.f1680e;
            a(vVar, cVar4, a0Var, false);
            c cVar5 = this.t;
            i = cVar5.f1677b;
            int i11 = cVar5.f1678c;
            if (i11 > 0) {
                h(i9, i2);
                c cVar6 = this.t;
                cVar6.h = i11;
                a(vVar, cVar6, a0Var, false);
                i2 = this.t.f1677b;
            }
        } else {
            a(aVar2);
            c cVar7 = this.t;
            cVar7.h = max2;
            a(vVar, cVar7, a0Var, false);
            c cVar8 = this.t;
            i = cVar8.f1677b;
            int i12 = cVar8.f1679d;
            int i13 = cVar8.f1678c;
            if (i13 > 0) {
                max += i13;
            }
            b(this.E);
            c cVar9 = this.t;
            cVar9.h = max;
            cVar9.f1679d += cVar9.f1680e;
            a(vVar, cVar9, a0Var, false);
            c cVar10 = this.t;
            i2 = cVar10.f1677b;
            int i14 = cVar10.f1678c;
            if (i14 > 0) {
                g(i12, i);
                c cVar11 = this.t;
                cVar11.h = i14;
                a(vVar, cVar11, a0Var, false);
                i = this.t.f1677b;
            }
        }
        if (e() > 0) {
            if (this.x ^ this.y) {
                int a3 = a(i, vVar, a0Var, true);
                i3 = i2 + a3;
                i4 = i + a3;
                a2 = b(i3, vVar, a0Var, false);
            } else {
                int b2 = b(i2, vVar, a0Var, true);
                i3 = i2 + b2;
                i4 = i + b2;
                a2 = a(i4, vVar, a0Var, false);
            }
            i2 = i3 + a2;
            i = i4 + a2;
        }
        b(vVar, a0Var, i2, i);
        if (!a0Var.d()) {
            this.u.i();
        } else {
            this.E.b();
        }
        this.v = this.y;
    }

    public void f(int i, int i2) {
        this.A = i;
        this.B = i2;
        d dVar = this.D;
        if (dVar != null) {
            dVar.e();
        }
        z();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void g(RecyclerView.a0 a0Var) {
        super.g(a0Var);
        this.D = null;
        this.A = -1;
        this.B = RecyclerView.UNDEFINED_DURATION;
        this.E.b();
    }

    @Deprecated
    protected int h(RecyclerView.a0 a0Var) {
        if (a0Var.c()) {
            return this.u.g();
        }
        return 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void i(int i) {
        this.A = i;
        this.B = RecyclerView.UNDEFINED_DURATION;
        d dVar = this.D;
        if (dVar != null) {
            dVar.e();
        }
        z();
    }

    public void k(int i) {
        if (i != 0 && i != 1) {
            throw new IllegalArgumentException("invalid orientation:" + i);
        }
        a((String) null);
        if (i != this.s || this.u == null) {
            this.u = r.a(this, i);
            this.E.f1667a = this.u;
            this.s = i;
            z();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean v() {
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public Parcelable y() {
        d dVar = this.D;
        if (dVar != null) {
            return new d(dVar);
        }
        d dVar2 = new d();
        if (e() > 0) {
            F();
            boolean z = this.v ^ this.x;
            dVar2.f1684d = z;
            if (z) {
                View S = S();
                dVar2.f1683c = this.u.b() - this.u.a(S);
                dVar2.f1682b = l(S);
            } else {
                View T = T();
                dVar2.f1682b = l(T);
                dVar2.f1683c = this.u.d(T) - this.u.f();
            }
        } else {
            dVar2.e();
        }
        return dVar2;
    }

    public LinearLayoutManager(Context context, int i, boolean z) {
        this.s = 1;
        this.w = false;
        this.x = false;
        this.y = false;
        this.z = true;
        this.A = -1;
        this.B = RecyclerView.UNDEFINED_DURATION;
        this.D = null;
        this.E = new a();
        this.F = new b();
        this.G = 2;
        this.H = new int[2];
        k(i);
        a(z);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public View c(int i) {
        int e2 = e();
        if (e2 == 0) {
            return null;
        }
        int l = i - l(d(0));
        if (l >= 0 && l < e2) {
            View d2 = d(l);
            if (l(d2) == i) {
                return d2;
            }
        }
        return super.c(i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a, reason: collision with root package name */
        r f1667a;

        /* renamed from: b, reason: collision with root package name */
        int f1668b;

        /* renamed from: c, reason: collision with root package name */
        int f1669c;

        /* renamed from: d, reason: collision with root package name */
        boolean f1670d;

        /* renamed from: e, reason: collision with root package name */
        boolean f1671e;

        a() {
            b();
        }

        void a() {
            int f2;
            if (this.f1670d) {
                f2 = this.f1667a.b();
            } else {
                f2 = this.f1667a.f();
            }
            this.f1669c = f2;
        }

        void b() {
            this.f1668b = -1;
            this.f1669c = RecyclerView.UNDEFINED_DURATION;
            this.f1670d = false;
            this.f1671e = false;
        }

        public String toString() {
            return "AnchorInfo{mPosition=" + this.f1668b + ", mCoordinate=" + this.f1669c + ", mLayoutFromEnd=" + this.f1670d + ", mValid=" + this.f1671e + '}';
        }

        boolean a(View view, RecyclerView.a0 a0Var) {
            RecyclerView.p pVar = (RecyclerView.p) view.getLayoutParams();
            return !pVar.c() && pVar.a() >= 0 && pVar.a() < a0Var.a();
        }

        public void b(View view, int i) {
            int h = this.f1667a.h();
            if (h >= 0) {
                a(view, i);
                return;
            }
            this.f1668b = i;
            if (this.f1670d) {
                int b2 = (this.f1667a.b() - h) - this.f1667a.a(view);
                this.f1669c = this.f1667a.b() - b2;
                if (b2 > 0) {
                    int b3 = this.f1669c - this.f1667a.b(view);
                    int f2 = this.f1667a.f();
                    int min = b3 - (f2 + Math.min(this.f1667a.d(view) - f2, 0));
                    if (min < 0) {
                        this.f1669c += Math.min(b2, -min);
                        return;
                    }
                    return;
                }
                return;
            }
            int d2 = this.f1667a.d(view);
            int f3 = d2 - this.f1667a.f();
            this.f1669c = d2;
            if (f3 > 0) {
                int b4 = (this.f1667a.b() - Math.min(0, (this.f1667a.b() - h) - this.f1667a.a(view))) - (d2 + this.f1667a.b(view));
                if (b4 < 0) {
                    this.f1669c -= Math.min(f3, -b4);
                }
            }
        }

        public void a(View view, int i) {
            if (this.f1670d) {
                this.f1669c = this.f1667a.a(view) + this.f1667a.h();
            } else {
                this.f1669c = this.f1667a.d(view);
            }
            this.f1668b = i;
        }
    }

    private void h(int i, int i2) {
        this.t.f1678c = i2 - this.u.f();
        c cVar = this.t;
        cVar.f1679d = i;
        cVar.f1680e = this.x ? 1 : -1;
        c cVar2 = this.t;
        cVar2.f1681f = -1;
        cVar2.f1677b = i2;
        cVar2.g = RecyclerView.UNDEFINED_DURATION;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void a(Parcelable parcelable) {
        if (parcelable instanceof d) {
            this.D = (d) parcelable;
            z();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean b() {
        return this.s == 1;
    }

    private void g(int i, int i2) {
        this.t.f1678c = this.u.b() - i2;
        this.t.f1680e = this.x ? -1 : 1;
        c cVar = this.t;
        cVar.f1679d = i;
        cVar.f1681f = 1;
        cVar.f1677b = i2;
        cVar.g = RecyclerView.UNDEFINED_DURATION;
    }

    private int i(RecyclerView.a0 a0Var) {
        if (e() == 0) {
            return 0;
        }
        F();
        return t.a(a0Var, this.u, b(!this.z, true), a(!this.z, true), this, this.z);
    }

    public void b(boolean z) {
        a((String) null);
        if (this.y == z) {
            return;
        }
        this.y = z;
        z();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int f(RecyclerView.a0 a0Var) {
        return k(a0Var);
    }

    private View f(RecyclerView.v vVar, RecyclerView.a0 a0Var) {
        return a(vVar, a0Var, 0, e(), a0Var.a());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int c(RecyclerView.a0 a0Var) {
        return k(a0Var);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int j(int i) {
        if (i == 1) {
            return (this.s != 1 && L()) ? 1 : -1;
        }
        if (i == 2) {
            return (this.s != 1 && L()) ? -1 : 1;
        }
        if (i == 17) {
            if (this.s == 0) {
                return -1;
            }
            return RecyclerView.UNDEFINED_DURATION;
        }
        if (i == 33) {
            if (this.s == 1) {
                return -1;
            }
            return RecyclerView.UNDEFINED_DURATION;
        }
        if (i != 66) {
            if (i == 130 && this.s == 1) {
                return 1;
            }
            return RecyclerView.UNDEFINED_DURATION;
        }
        if (this.s == 0) {
            return 1;
        }
        return RecyclerView.UNDEFINED_DURATION;
    }

    private int k(RecyclerView.a0 a0Var) {
        if (e() == 0) {
            return 0;
        }
        F();
        return t.b(a0Var, this.u, b(!this.z, true), a(!this.z, true), this, this.z);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean a() {
        return this.s == 0;
    }

    int c(int i, RecyclerView.v vVar, RecyclerView.a0 a0Var) {
        if (e() == 0 || i == 0) {
            return 0;
        }
        F();
        this.t.f1676a = true;
        int i2 = i > 0 ? 1 : -1;
        int abs = Math.abs(i);
        a(i2, abs, true, a0Var);
        c cVar = this.t;
        int a2 = cVar.g + a(vVar, cVar, a0Var, false);
        if (a2 < 0) {
            return 0;
        }
        if (abs > a2) {
            i = i2 * a2;
        }
        this.u.a(-i);
        this.t.k = i;
        return i;
    }

    private View h(RecyclerView.v vVar, RecyclerView.a0 a0Var) {
        return this.x ? f(vVar, a0Var) : g(vVar, a0Var);
    }

    public void a(boolean z) {
        a((String) null);
        if (z == this.w) {
            return;
        }
        this.w = z;
        z();
    }

    private void b(RecyclerView.v vVar, RecyclerView.a0 a0Var, int i, int i2) {
        if (!a0Var.e() || e() == 0 || a0Var.d() || !D()) {
            return;
        }
        List<RecyclerView.d0> f2 = vVar.f();
        int size = f2.size();
        int l = l(d(0));
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < size; i5++) {
            RecyclerView.d0 d0Var = f2.get(i5);
            if (!d0Var.isRemoved()) {
                if (((d0Var.getLayoutPosition() < l) != this.x ? (char) 65535 : (char) 1) == 65535) {
                    i3 += this.u.b(d0Var.itemView);
                } else {
                    i4 += this.u.b(d0Var.itemView);
                }
            }
        }
        this.t.l = f2;
        if (i3 > 0) {
            h(l(T()), i);
            c cVar = this.t;
            cVar.h = i3;
            cVar.f1678c = 0;
            cVar.a();
            a(vVar, this.t, a0Var, false);
        }
        if (i4 > 0) {
            g(l(S()), i2);
            c cVar2 = this.t;
            cVar2.h = i4;
            cVar2.f1678c = 0;
            cVar2.a();
            a(vVar, this.t, a0Var, false);
        }
        this.t.l = null;
    }

    private View g(RecyclerView.v vVar, RecyclerView.a0 a0Var) {
        return a(vVar, a0Var, e() - 1, -1, a0Var.a());
    }

    private View i(RecyclerView.v vVar, RecyclerView.a0 a0Var) {
        return this.x ? g(vVar, a0Var) : f(vVar, a0Var);
    }

    protected void a(RecyclerView.a0 a0Var, int[] iArr) {
        int i;
        int h = h(a0Var);
        if (this.t.f1681f == -1) {
            i = 0;
        } else {
            i = h;
            h = 0;
        }
        iArr[0] = h;
        iArr[1] = i;
    }

    public LinearLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        this.s = 1;
        this.w = false;
        this.x = false;
        this.y = false;
        this.z = true;
        this.A = -1;
        this.B = RecyclerView.UNDEFINED_DURATION;
        this.D = null;
        this.E = new a();
        this.F = new b();
        this.G = 2;
        this.H = new int[2];
        RecyclerView.o.d a2 = RecyclerView.o.a(context, attributeSet, i, i2);
        k(a2.f1718a);
        a(a2.f1720c);
        b(a2.f1721d);
    }

    private void c(RecyclerView.v vVar, int i, int i2) {
        if (i < 0) {
            return;
        }
        int i3 = i - i2;
        int e2 = e();
        if (!this.x) {
            for (int i4 = 0; i4 < e2; i4++) {
                View d2 = d(i4);
                if (this.u.a(d2) > i3 || this.u.e(d2) > i3) {
                    a(vVar, 0, i4);
                    return;
                }
            }
            return;
        }
        int i5 = e2 - 1;
        for (int i6 = i5; i6 >= 0; i6--) {
            View d3 = d(i6);
            if (this.u.a(d3) > i3 || this.u.e(d3) > i3) {
                a(vVar, i5, i6);
                return;
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void a(RecyclerView recyclerView, RecyclerView.a0 a0Var, int i) {
        n nVar = new n(recyclerView.getContext());
        nVar.c(i);
        b(nVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.z.b
    public PointF a(int i) {
        if (e() == 0) {
            return null;
        }
        int i2 = (i < l(d(0))) != this.x ? -1 : 1;
        if (this.s == 0) {
            return new PointF(i2, 0.0f);
        }
        return new PointF(0.0f, i2);
    }

    private boolean a(RecyclerView.v vVar, RecyclerView.a0 a0Var, a aVar) {
        View i;
        int f2;
        if (e() == 0) {
            return false;
        }
        View g = g();
        if (g != null && aVar.a(g, a0Var)) {
            aVar.b(g, l(g));
            return true;
        }
        if (this.v != this.y) {
            return false;
        }
        if (aVar.f1670d) {
            i = h(vVar, a0Var);
        } else {
            i = i(vVar, a0Var);
        }
        if (i == null) {
            return false;
        }
        aVar.a(i, l(i));
        if (!a0Var.d() && D()) {
            if (this.u.d(i) >= this.u.b() || this.u.a(i) < this.u.f()) {
                if (aVar.f1670d) {
                    f2 = this.u.b();
                } else {
                    f2 = this.u.f();
                }
                aVar.f1669c = f2;
            }
        }
        return true;
    }

    private void b(RecyclerView.v vVar, RecyclerView.a0 a0Var, a aVar) {
        if (a(a0Var, aVar) || a(vVar, a0Var, aVar)) {
            return;
        }
        aVar.a();
        aVar.f1668b = this.y ? a0Var.a() - 1 : 0;
    }

    private int b(int i, RecyclerView.v vVar, RecyclerView.a0 a0Var, boolean z) {
        int f2;
        int f3 = i - this.u.f();
        if (f3 <= 0) {
            return 0;
        }
        int i2 = -c(f3, vVar, a0Var);
        int i3 = i + i2;
        if (!z || (f2 = i3 - this.u.f()) <= 0) {
            return i2;
        }
        this.u.a(-f2);
        return i2 - f2;
    }

    private void b(a aVar) {
        h(aVar.f1668b, aVar.f1669c);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int b(int i, RecyclerView.v vVar, RecyclerView.a0 a0Var) {
        if (this.s == 0) {
            return 0;
        }
        return c(i, vVar, a0Var);
    }

    private boolean a(RecyclerView.a0 a0Var, a aVar) {
        int i;
        int d2;
        if (!a0Var.d() && (i = this.A) != -1) {
            if (i >= 0 && i < a0Var.a()) {
                aVar.f1668b = this.A;
                d dVar = this.D;
                if (dVar != null && dVar.d()) {
                    aVar.f1670d = this.D.f1684d;
                    if (aVar.f1670d) {
                        aVar.f1669c = this.u.b() - this.D.f1683c;
                    } else {
                        aVar.f1669c = this.u.f() + this.D.f1683c;
                    }
                    return true;
                }
                if (this.B == Integer.MIN_VALUE) {
                    View c2 = c(this.A);
                    if (c2 != null) {
                        if (this.u.b(c2) > this.u.g()) {
                            aVar.a();
                            return true;
                        }
                        if (this.u.d(c2) - this.u.f() < 0) {
                            aVar.f1669c = this.u.f();
                            aVar.f1670d = false;
                            return true;
                        }
                        if (this.u.b() - this.u.a(c2) < 0) {
                            aVar.f1669c = this.u.b();
                            aVar.f1670d = true;
                            return true;
                        }
                        if (aVar.f1670d) {
                            d2 = this.u.a(c2) + this.u.h();
                        } else {
                            d2 = this.u.d(c2);
                        }
                        aVar.f1669c = d2;
                    } else {
                        if (e() > 0) {
                            aVar.f1670d = (this.A < l(d(0))) == this.x;
                        }
                        aVar.a();
                    }
                    return true;
                }
                boolean z = this.x;
                aVar.f1670d = z;
                if (z) {
                    aVar.f1669c = this.u.b() - this.B;
                } else {
                    aVar.f1669c = this.u.f() + this.B;
                }
                return true;
            }
            this.A = -1;
            this.B = RecyclerView.UNDEFINED_DURATION;
        }
        return false;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int b(RecyclerView.a0 a0Var) {
        return j(a0Var);
    }

    private void b(RecyclerView.v vVar, int i, int i2) {
        int e2 = e();
        if (i < 0) {
            return;
        }
        int a2 = (this.u.a() - i) + i2;
        if (this.x) {
            for (int i3 = 0; i3 < e2; i3++) {
                View d2 = d(i3);
                if (this.u.d(d2) < a2 || this.u.f(d2) < a2) {
                    a(vVar, 0, i3);
                    return;
                }
            }
            return;
        }
        int i4 = e2 - 1;
        for (int i5 = i4; i5 >= 0; i5--) {
            View d3 = d(i5);
            if (this.u.d(d3) < a2 || this.u.f(d3) < a2) {
                a(vVar, i4, i5);
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public View b(boolean z, boolean z2) {
        if (this.x) {
            return a(e() - 1, -1, z, z2);
        }
        return a(0, e(), z, z2);
    }

    private int a(int i, RecyclerView.v vVar, RecyclerView.a0 a0Var, boolean z) {
        int b2;
        int b3 = this.u.b() - i;
        if (b3 <= 0) {
            return 0;
        }
        int i2 = -c(-b3, vVar, a0Var);
        int i3 = i + i2;
        if (!z || (b2 = this.u.b() - i3) <= 0) {
            return i2;
        }
        this.u.a(b2);
        return b2 + i2;
    }

    private void a(a aVar) {
        g(aVar.f1668b, aVar.f1669c);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int a(int i, RecyclerView.v vVar, RecyclerView.a0 a0Var) {
        if (this.s == 1) {
            return 0;
        }
        return c(i, vVar, a0Var);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int a(RecyclerView.a0 a0Var) {
        return i(a0Var);
    }

    private void a(int i, int i2, boolean z, RecyclerView.a0 a0Var) {
        int f2;
        this.t.m = N();
        this.t.f1681f = i;
        int[] iArr = this.H;
        iArr[0] = 0;
        iArr[1] = 0;
        a(a0Var, iArr);
        int max = Math.max(0, this.H[0]);
        int max2 = Math.max(0, this.H[1]);
        boolean z2 = i == 1;
        this.t.h = z2 ? max2 : max;
        c cVar = this.t;
        if (!z2) {
            max = max2;
        }
        cVar.i = max;
        if (z2) {
            this.t.h += this.u.c();
            View S = S();
            this.t.f1680e = this.x ? -1 : 1;
            c cVar2 = this.t;
            int l = l(S);
            c cVar3 = this.t;
            cVar2.f1679d = l + cVar3.f1680e;
            cVar3.f1677b = this.u.a(S);
            f2 = this.u.a(S) - this.u.b();
        } else {
            View T = T();
            this.t.h += this.u.f();
            this.t.f1680e = this.x ? 1 : -1;
            c cVar4 = this.t;
            int l2 = l(T);
            c cVar5 = this.t;
            cVar4.f1679d = l2 + cVar5.f1680e;
            cVar5.f1677b = this.u.d(T);
            f2 = (-this.u.d(T)) + this.u.f();
        }
        c cVar6 = this.t;
        cVar6.f1678c = i2;
        if (z) {
            cVar6.f1678c -= f2;
        }
        this.t.g = f2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int e(RecyclerView.a0 a0Var) {
        return j(a0Var);
    }

    View e(int i, int i2) {
        int i3;
        int i4;
        F();
        if ((i2 > i ? (char) 1 : i2 < i ? (char) 65535 : (char) 0) == 0) {
            return d(i);
        }
        if (this.u.d(d(i)) < this.u.f()) {
            i3 = 16644;
            i4 = 16388;
        } else {
            i3 = 4161;
            i4 = DfuBaseService.ERROR_FILE_NOT_FOUND;
        }
        if (this.s == 0) {
            return this.f1714e.a(i, i2, i3, i4);
        }
        return this.f1715f.a(i, i2, i3, i4);
    }

    void a(RecyclerView.a0 a0Var, c cVar, RecyclerView.o.c cVar2) {
        int i = cVar.f1679d;
        if (i < 0 || i >= a0Var.a()) {
            return;
        }
        cVar2.a(i, Math.max(0, cVar.g));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void a(int i, RecyclerView.o.c cVar) {
        boolean z;
        int i2;
        d dVar = this.D;
        if (dVar != null && dVar.d()) {
            d dVar2 = this.D;
            z = dVar2.f1684d;
            i2 = dVar2.f1682b;
        } else {
            U();
            z = this.x;
            i2 = this.A;
            if (i2 == -1) {
                i2 = z ? i - 1 : 0;
            }
        }
        int i3 = z ? -1 : 1;
        int i4 = i2;
        for (int i5 = 0; i5 < this.G && i4 >= 0 && i4 < i; i5++) {
            cVar.a(i4, 0);
            i4 += i3;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void a(int i, int i2, RecyclerView.a0 a0Var, RecyclerView.o.c cVar) {
        if (this.s != 0) {
            i = i2;
        }
        if (e() == 0 || i == 0) {
            return;
        }
        F();
        a(i > 0 ? 1 : -1, Math.abs(i), true, a0Var);
        a(a0Var, this.t, cVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void a(String str) {
        if (this.D == null) {
            super.a(str);
        }
    }

    private void a(RecyclerView.v vVar, int i, int i2) {
        if (i == i2) {
            return;
        }
        if (i2 <= i) {
            while (i > i2) {
                a(i, vVar);
                i--;
            }
        } else {
            for (int i3 = i2 - 1; i3 >= i; i3--) {
                a(i3, vVar);
            }
        }
    }

    private void a(RecyclerView.v vVar, c cVar) {
        if (!cVar.f1676a || cVar.m) {
            return;
        }
        int i = cVar.g;
        int i2 = cVar.i;
        if (cVar.f1681f == -1) {
            b(vVar, i, i2);
        } else {
            c(vVar, i, i2);
        }
    }

    int a(RecyclerView.v vVar, c cVar, RecyclerView.a0 a0Var, boolean z) {
        int i = cVar.f1678c;
        int i2 = cVar.g;
        if (i2 != Integer.MIN_VALUE) {
            if (i < 0) {
                cVar.g = i2 + i;
            }
            a(vVar, cVar);
        }
        int i3 = cVar.f1678c + cVar.h;
        b bVar = this.F;
        while (true) {
            if ((!cVar.m && i3 <= 0) || !cVar.a(a0Var)) {
                break;
            }
            bVar.a();
            a(vVar, a0Var, cVar, bVar);
            if (!bVar.f1673b) {
                cVar.f1677b += bVar.f1672a * cVar.f1681f;
                if (!bVar.f1674c || cVar.l != null || !a0Var.d()) {
                    int i4 = cVar.f1678c;
                    int i5 = bVar.f1672a;
                    cVar.f1678c = i4 - i5;
                    i3 -= i5;
                }
                int i6 = cVar.g;
                if (i6 != Integer.MIN_VALUE) {
                    cVar.g = i6 + bVar.f1672a;
                    int i7 = cVar.f1678c;
                    if (i7 < 0) {
                        cVar.g += i7;
                    }
                    a(vVar, cVar);
                }
                if (z && bVar.f1675d) {
                    break;
                }
            } else {
                break;
            }
        }
        return i - cVar.f1678c;
    }

    void a(RecyclerView.v vVar, RecyclerView.a0 a0Var, c cVar, b bVar) {
        int i;
        int i2;
        int i3;
        int i4;
        int c2;
        View a2 = cVar.a(vVar);
        if (a2 == null) {
            bVar.f1673b = true;
            return;
        }
        RecyclerView.p pVar = (RecyclerView.p) a2.getLayoutParams();
        if (cVar.l == null) {
            if (this.x == (cVar.f1681f == -1)) {
                b(a2);
            } else {
                b(a2, 0);
            }
        } else {
            if (this.x == (cVar.f1681f == -1)) {
                a(a2);
            } else {
                a(a2, 0);
            }
        }
        a(a2, 0, 0);
        bVar.f1672a = this.u.b(a2);
        if (this.s == 1) {
            if (L()) {
                c2 = r() - p();
                i4 = c2 - this.u.c(a2);
            } else {
                i4 = o();
                c2 = this.u.c(a2) + i4;
            }
            if (cVar.f1681f == -1) {
                int i5 = cVar.f1677b;
                i3 = i5;
                i2 = c2;
                i = i5 - bVar.f1672a;
            } else {
                int i6 = cVar.f1677b;
                i = i6;
                i2 = c2;
                i3 = bVar.f1672a + i6;
            }
        } else {
            int q = q();
            int c3 = this.u.c(a2) + q;
            if (cVar.f1681f == -1) {
                int i7 = cVar.f1677b;
                i2 = i7;
                i = q;
                i3 = c3;
                i4 = i7 - bVar.f1672a;
            } else {
                int i8 = cVar.f1677b;
                i = q;
                i2 = bVar.f1672a + i8;
                i3 = c3;
                i4 = i8;
            }
        }
        a(a2, i4, i, i2, i3);
        if (pVar.c() || pVar.b()) {
            bVar.f1674c = true;
        }
        bVar.f1675d = a2.hasFocusable();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public View a(boolean z, boolean z2) {
        if (this.x) {
            return a(0, e(), z, z2);
        }
        return a(e() - 1, -1, z, z2);
    }

    View a(RecyclerView.v vVar, RecyclerView.a0 a0Var, int i, int i2, int i3) {
        F();
        int f2 = this.u.f();
        int b2 = this.u.b();
        int i4 = i2 > i ? 1 : -1;
        View view = null;
        View view2 = null;
        while (i != i2) {
            View d2 = d(i);
            int l = l(d2);
            if (l >= 0 && l < i3) {
                if (((RecyclerView.p) d2.getLayoutParams()).c()) {
                    if (view2 == null) {
                        view2 = d2;
                    }
                } else {
                    if (this.u.d(d2) < b2 && this.u.a(d2) >= f2) {
                        return d2;
                    }
                    if (view == null) {
                        view = d2;
                    }
                }
            }
            i += i4;
        }
        return view != null ? view : view2;
    }

    View a(int i, int i2, boolean z, boolean z2) {
        F();
        int i3 = z ? 24579 : 320;
        int i4 = z2 ? 320 : 0;
        if (this.s == 0) {
            return this.f1714e.a(i, i2, i3, i4);
        }
        return this.f1715f.a(i, i2, i3, i4);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public View a(View view, int i, RecyclerView.v vVar, RecyclerView.a0 a0Var) {
        int j;
        View Q;
        View S;
        U();
        if (e() == 0 || (j = j(i)) == Integer.MIN_VALUE) {
            return null;
        }
        F();
        a(j, (int) (this.u.g() * 0.33333334f), false, a0Var);
        c cVar = this.t;
        cVar.g = RecyclerView.UNDEFINED_DURATION;
        cVar.f1676a = false;
        a(vVar, cVar, a0Var, true);
        if (j == -1) {
            Q = R();
        } else {
            Q = Q();
        }
        if (j == -1) {
            S = T();
        } else {
            S = S();
        }
        if (!S.hasFocusable()) {
            return Q;
        }
        if (Q == null) {
            return null;
        }
        return S;
    }

    @Override // androidx.recyclerview.widget.j.i
    public void a(View view, View view2, int i, int i2) {
        a("Cannot drop a view during a scroll or layout calculation");
        F();
        U();
        int l = l(view);
        int l2 = l(view2);
        char c2 = l < l2 ? (char) 1 : (char) 65535;
        if (this.x) {
            if (c2 == 1) {
                f(l2, this.u.b() - (this.u.d(view2) + this.u.b(view)));
                return;
            } else {
                f(l2, this.u.b() - this.u.a(view2));
                return;
            }
        }
        if (c2 == 65535) {
            f(l2, this.u.d(view2));
        } else {
            f(l2, this.u.a(view2) - this.u.b(view));
        }
    }
}
