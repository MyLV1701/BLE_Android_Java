package androidx.recyclerview.widget;

import a.f.l.f0.c;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import androidx.preference.Preference;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;

/* loaded from: classes.dex */
public class StaggeredGridLayoutManager extends RecyclerView.o implements RecyclerView.z.b {
    private BitSet B;
    private boolean G;
    private boolean H;
    private e I;
    private int J;
    private int[] O;
    f[] t;
    r u;
    r v;
    private int w;
    private int x;
    private final m y;
    private int s = -1;
    boolean z = false;
    boolean A = false;
    int C = -1;
    int D = RecyclerView.UNDEFINED_DURATION;
    d E = new d();
    private int F = 2;
    private final Rect K = new Rect();
    private final b L = new b();
    private boolean M = false;
    private boolean N = true;
    private final Runnable P = new a();

    /* loaded from: classes.dex */
    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            StaggeredGridLayoutManager.this.G();
        }
    }

    /* loaded from: classes.dex */
    public static class c extends RecyclerView.p {

        /* renamed from: e, reason: collision with root package name */
        f f1759e;

        /* renamed from: f, reason: collision with root package name */
        boolean f1760f;

        public c(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public final int e() {
            f fVar = this.f1759e;
            if (fVar == null) {
                return -1;
            }
            return fVar.f1776e;
        }

        public boolean f() {
            return this.f1760f;
        }

        public c(int i, int i2) {
            super(i, i2);
        }

        public c(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
        }

        public c(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }
    }

    @SuppressLint({"BanParcelableUsage"})
    /* loaded from: classes.dex */
    public static class e implements Parcelable {
        public static final Parcelable.Creator<e> CREATOR = new a();

        /* renamed from: b, reason: collision with root package name */
        int f1767b;

        /* renamed from: c, reason: collision with root package name */
        int f1768c;

        /* renamed from: d, reason: collision with root package name */
        int f1769d;

        /* renamed from: e, reason: collision with root package name */
        int[] f1770e;

        /* renamed from: f, reason: collision with root package name */
        int f1771f;
        int[] g;
        List<d.a> h;
        boolean i;
        boolean j;
        boolean k;

        /* loaded from: classes.dex */
        static class a implements Parcelable.Creator<e> {
            a() {
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public e createFromParcel(Parcel parcel) {
                return new e(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public e[] newArray(int i) {
                return new e[i];
            }
        }

        public e() {
        }

        void d() {
            this.f1770e = null;
            this.f1769d = 0;
            this.f1767b = -1;
            this.f1768c = -1;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        void e() {
            this.f1770e = null;
            this.f1769d = 0;
            this.f1771f = 0;
            this.g = null;
            this.h = null;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.f1767b);
            parcel.writeInt(this.f1768c);
            parcel.writeInt(this.f1769d);
            if (this.f1769d > 0) {
                parcel.writeIntArray(this.f1770e);
            }
            parcel.writeInt(this.f1771f);
            if (this.f1771f > 0) {
                parcel.writeIntArray(this.g);
            }
            parcel.writeInt(this.i ? 1 : 0);
            parcel.writeInt(this.j ? 1 : 0);
            parcel.writeInt(this.k ? 1 : 0);
            parcel.writeList(this.h);
        }

        e(Parcel parcel) {
            this.f1767b = parcel.readInt();
            this.f1768c = parcel.readInt();
            this.f1769d = parcel.readInt();
            int i = this.f1769d;
            if (i > 0) {
                this.f1770e = new int[i];
                parcel.readIntArray(this.f1770e);
            }
            this.f1771f = parcel.readInt();
            int i2 = this.f1771f;
            if (i2 > 0) {
                this.g = new int[i2];
                parcel.readIntArray(this.g);
            }
            this.i = parcel.readInt() == 1;
            this.j = parcel.readInt() == 1;
            this.k = parcel.readInt() == 1;
            this.h = parcel.readArrayList(d.a.class.getClassLoader());
        }

        public e(e eVar) {
            this.f1769d = eVar.f1769d;
            this.f1767b = eVar.f1767b;
            this.f1768c = eVar.f1768c;
            this.f1770e = eVar.f1770e;
            this.f1771f = eVar.f1771f;
            this.g = eVar.g;
            this.i = eVar.i;
            this.j = eVar.j;
            this.k = eVar.k;
            this.h = eVar.h;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class f {

        /* renamed from: a, reason: collision with root package name */
        ArrayList<View> f1772a = new ArrayList<>();

        /* renamed from: b, reason: collision with root package name */
        int f1773b = RecyclerView.UNDEFINED_DURATION;

        /* renamed from: c, reason: collision with root package name */
        int f1774c = RecyclerView.UNDEFINED_DURATION;

        /* renamed from: d, reason: collision with root package name */
        int f1775d = 0;

        /* renamed from: e, reason: collision with root package name */
        final int f1776e;

        f(int i) {
            this.f1776e = i;
        }

        int a(int i) {
            int i2 = this.f1774c;
            if (i2 != Integer.MIN_VALUE) {
                return i2;
            }
            if (this.f1772a.size() == 0) {
                return i;
            }
            a();
            return this.f1774c;
        }

        int b(int i) {
            int i2 = this.f1773b;
            if (i2 != Integer.MIN_VALUE) {
                return i2;
            }
            if (this.f1772a.size() == 0) {
                return i;
            }
            b();
            return this.f1773b;
        }

        void c(View view) {
            c b2 = b(view);
            b2.f1759e = this;
            this.f1772a.add(0, view);
            this.f1773b = RecyclerView.UNDEFINED_DURATION;
            if (this.f1772a.size() == 1) {
                this.f1774c = RecyclerView.UNDEFINED_DURATION;
            }
            if (b2.c() || b2.b()) {
                this.f1775d += StaggeredGridLayoutManager.this.u.b(view);
            }
        }

        void d(int i) {
            this.f1773b = i;
            this.f1774c = i;
        }

        public int e() {
            if (StaggeredGridLayoutManager.this.z) {
                return a(0, this.f1772a.size(), true);
            }
            return a(this.f1772a.size() - 1, -1, true);
        }

        public int f() {
            return this.f1775d;
        }

        int g() {
            int i = this.f1774c;
            if (i != Integer.MIN_VALUE) {
                return i;
            }
            a();
            return this.f1774c;
        }

        int h() {
            int i = this.f1773b;
            if (i != Integer.MIN_VALUE) {
                return i;
            }
            b();
            return this.f1773b;
        }

        void i() {
            this.f1773b = RecyclerView.UNDEFINED_DURATION;
            this.f1774c = RecyclerView.UNDEFINED_DURATION;
        }

        void j() {
            int size = this.f1772a.size();
            View remove = this.f1772a.remove(size - 1);
            c b2 = b(remove);
            b2.f1759e = null;
            if (b2.c() || b2.b()) {
                this.f1775d -= StaggeredGridLayoutManager.this.u.b(remove);
            }
            if (size == 1) {
                this.f1773b = RecyclerView.UNDEFINED_DURATION;
            }
            this.f1774c = RecyclerView.UNDEFINED_DURATION;
        }

        void k() {
            View remove = this.f1772a.remove(0);
            c b2 = b(remove);
            b2.f1759e = null;
            if (this.f1772a.size() == 0) {
                this.f1774c = RecyclerView.UNDEFINED_DURATION;
            }
            if (b2.c() || b2.b()) {
                this.f1775d -= StaggeredGridLayoutManager.this.u.b(remove);
            }
            this.f1773b = RecyclerView.UNDEFINED_DURATION;
        }

        public int d() {
            if (StaggeredGridLayoutManager.this.z) {
                return a(this.f1772a.size() - 1, -1, true);
            }
            return a(0, this.f1772a.size(), true);
        }

        void a() {
            d.a c2;
            ArrayList<View> arrayList = this.f1772a;
            View view = arrayList.get(arrayList.size() - 1);
            c b2 = b(view);
            this.f1774c = StaggeredGridLayoutManager.this.u.a(view);
            if (b2.f1760f && (c2 = StaggeredGridLayoutManager.this.E.c(b2.a())) != null && c2.f1764c == 1) {
                this.f1774c += c2.a(this.f1776e);
            }
        }

        void b() {
            d.a c2;
            View view = this.f1772a.get(0);
            c b2 = b(view);
            this.f1773b = StaggeredGridLayoutManager.this.u.d(view);
            if (b2.f1760f && (c2 = StaggeredGridLayoutManager.this.E.c(b2.a())) != null && c2.f1764c == -1) {
                this.f1773b -= c2.a(this.f1776e);
            }
        }

        void c() {
            this.f1772a.clear();
            i();
            this.f1775d = 0;
        }

        void c(int i) {
            int i2 = this.f1773b;
            if (i2 != Integer.MIN_VALUE) {
                this.f1773b = i2 + i;
            }
            int i3 = this.f1774c;
            if (i3 != Integer.MIN_VALUE) {
                this.f1774c = i3 + i;
            }
        }

        void a(View view) {
            c b2 = b(view);
            b2.f1759e = this;
            this.f1772a.add(view);
            this.f1774c = RecyclerView.UNDEFINED_DURATION;
            if (this.f1772a.size() == 1) {
                this.f1773b = RecyclerView.UNDEFINED_DURATION;
            }
            if (b2.c() || b2.b()) {
                this.f1775d += StaggeredGridLayoutManager.this.u.b(view);
            }
        }

        c b(View view) {
            return (c) view.getLayoutParams();
        }

        void a(boolean z, int i) {
            int b2;
            if (z) {
                b2 = a(RecyclerView.UNDEFINED_DURATION);
            } else {
                b2 = b(RecyclerView.UNDEFINED_DURATION);
            }
            c();
            if (b2 == Integer.MIN_VALUE) {
                return;
            }
            if (!z || b2 >= StaggeredGridLayoutManager.this.u.b()) {
                if (z || b2 <= StaggeredGridLayoutManager.this.u.f()) {
                    if (i != Integer.MIN_VALUE) {
                        b2 += i;
                    }
                    this.f1774c = b2;
                    this.f1773b = b2;
                }
            }
        }

        int a(int i, int i2, boolean z, boolean z2, boolean z3) {
            int f2 = StaggeredGridLayoutManager.this.u.f();
            int b2 = StaggeredGridLayoutManager.this.u.b();
            int i3 = i2 > i ? 1 : -1;
            while (i != i2) {
                View view = this.f1772a.get(i);
                int d2 = StaggeredGridLayoutManager.this.u.d(view);
                int a2 = StaggeredGridLayoutManager.this.u.a(view);
                boolean z4 = false;
                boolean z5 = !z3 ? d2 >= b2 : d2 > b2;
                if (!z3 ? a2 > f2 : a2 >= f2) {
                    z4 = true;
                }
                if (z5 && z4) {
                    if (z && z2) {
                        if (d2 >= f2 && a2 <= b2) {
                            return StaggeredGridLayoutManager.this.l(view);
                        }
                    } else {
                        if (z2) {
                            return StaggeredGridLayoutManager.this.l(view);
                        }
                        if (d2 < f2 || a2 > b2) {
                            return StaggeredGridLayoutManager.this.l(view);
                        }
                    }
                }
                i += i3;
            }
            return -1;
        }

        int a(int i, int i2, boolean z) {
            return a(i, i2, false, false, z);
        }

        public View a(int i, int i2) {
            View view = null;
            if (i2 == -1) {
                int size = this.f1772a.size();
                int i3 = 0;
                while (i3 < size) {
                    View view2 = this.f1772a.get(i3);
                    StaggeredGridLayoutManager staggeredGridLayoutManager = StaggeredGridLayoutManager.this;
                    if (staggeredGridLayoutManager.z && staggeredGridLayoutManager.l(view2) <= i) {
                        break;
                    }
                    StaggeredGridLayoutManager staggeredGridLayoutManager2 = StaggeredGridLayoutManager.this;
                    if ((!staggeredGridLayoutManager2.z && staggeredGridLayoutManager2.l(view2) >= i) || !view2.hasFocusable()) {
                        break;
                    }
                    i3++;
                    view = view2;
                }
            } else {
                int size2 = this.f1772a.size() - 1;
                while (size2 >= 0) {
                    View view3 = this.f1772a.get(size2);
                    StaggeredGridLayoutManager staggeredGridLayoutManager3 = StaggeredGridLayoutManager.this;
                    if (staggeredGridLayoutManager3.z && staggeredGridLayoutManager3.l(view3) >= i) {
                        break;
                    }
                    StaggeredGridLayoutManager staggeredGridLayoutManager4 = StaggeredGridLayoutManager.this;
                    if ((!staggeredGridLayoutManager4.z && staggeredGridLayoutManager4.l(view3) <= i) || !view3.hasFocusable()) {
                        break;
                    }
                    size2--;
                    view = view3;
                }
            }
            return view;
        }
    }

    public StaggeredGridLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        RecyclerView.o.d a2 = RecyclerView.o.a(context, attributeSet, i, i2);
        j(a2.f1718a);
        k(a2.f1719b);
        c(a2.f1720c);
        this.y = new m();
        N();
    }

    private void N() {
        this.u = r.a(this, this.w);
        this.v = r.a(this, 1 - this.w);
    }

    private void O() {
        if (this.v.d() == 1073741824) {
            return;
        }
        int e2 = e();
        float f2 = 0.0f;
        for (int i = 0; i < e2; i++) {
            View d2 = d(i);
            float b2 = this.v.b(d2);
            if (b2 >= f2) {
                if (((c) d2.getLayoutParams()).f()) {
                    b2 = (b2 * 1.0f) / this.s;
                }
                f2 = Math.max(f2, b2);
            }
        }
        int i2 = this.x;
        int round = Math.round(f2 * this.s);
        if (this.v.d() == Integer.MIN_VALUE) {
            round = Math.min(round, this.v.g());
        }
        l(round);
        if (this.x == i2) {
            return;
        }
        for (int i3 = 0; i3 < e2; i3++) {
            View d3 = d(i3);
            c cVar = (c) d3.getLayoutParams();
            if (!cVar.f1760f) {
                if (M() && this.w == 1) {
                    int i4 = this.s;
                    int i5 = cVar.f1759e.f1776e;
                    d3.offsetLeftAndRight(((-((i4 - 1) - i5)) * this.x) - ((-((i4 - 1) - i5)) * i2));
                } else {
                    int i6 = cVar.f1759e.f1776e;
                    int i7 = this.x * i6;
                    int i8 = i6 * i2;
                    if (this.w == 1) {
                        d3.offsetLeftAndRight(i7 - i8);
                    } else {
                        d3.offsetTopAndBottom(i7 - i8);
                    }
                }
            }
        }
    }

    private void P() {
        if (this.w != 1 && M()) {
            this.A = !this.z;
        } else {
            this.A = this.z;
        }
    }

    private boolean a(f fVar) {
        if (this.A) {
            if (fVar.g() < this.u.b()) {
                ArrayList<View> arrayList = fVar.f1772a;
                return !fVar.b(arrayList.get(arrayList.size() - 1)).f1760f;
            }
        } else if (fVar.h() > this.u.f()) {
            return !fVar.b(fVar.f1772a.get(0)).f1760f;
        }
        return false;
    }

    private int h(RecyclerView.a0 a0Var) {
        if (e() == 0) {
            return 0;
        }
        return t.a(a0Var, this.u, b(!this.N), a(!this.N), this, this.N);
    }

    private int i(RecyclerView.a0 a0Var) {
        if (e() == 0) {
            return 0;
        }
        return t.a(a0Var, this.u, b(!this.N), a(!this.N), this, this.N, this.A);
    }

    private int m(int i) {
        if (e() == 0) {
            return this.A ? 1 : -1;
        }
        return (i < I()) != this.A ? -1 : 1;
    }

    private int n(int i) {
        if (i == 1) {
            return (this.w != 1 && M()) ? 1 : -1;
        }
        if (i == 2) {
            return (this.w != 1 && M()) ? -1 : 1;
        }
        if (i == 17) {
            if (this.w == 0) {
                return -1;
            }
            return RecyclerView.UNDEFINED_DURATION;
        }
        if (i == 33) {
            if (this.w == 1) {
                return -1;
            }
            return RecyclerView.UNDEFINED_DURATION;
        }
        if (i != 66) {
            if (i == 130 && this.w == 1) {
                return 1;
            }
            return RecyclerView.UNDEFINED_DURATION;
        }
        if (this.w == 0) {
            return 1;
        }
        return RecyclerView.UNDEFINED_DURATION;
    }

    private d.a o(int i) {
        d.a aVar = new d.a();
        aVar.f1765d = new int[this.s];
        for (int i2 = 0; i2 < this.s; i2++) {
            aVar.f1765d[i2] = i - this.t[i2].a(i);
        }
        return aVar;
    }

    private d.a p(int i) {
        d.a aVar = new d.a();
        aVar.f1765d = new int[this.s];
        for (int i2 = 0; i2 < this.s; i2++) {
            aVar.f1765d[i2] = this.t[i2].b(i) - i;
        }
        return aVar;
    }

    private void q(View view) {
        for (int i = this.s - 1; i >= 0; i--) {
            this.t[i].c(view);
        }
    }

    private int r(int i) {
        for (int e2 = e() - 1; e2 >= 0; e2--) {
            int l = l(d(e2));
            if (l >= 0 && l < i) {
                return l;
            }
        }
        return 0;
    }

    private int s(int i) {
        int a2 = this.t[0].a(i);
        for (int i2 = 1; i2 < this.s; i2++) {
            int a3 = this.t[i2].a(i);
            if (a3 > a2) {
                a2 = a3;
            }
        }
        return a2;
    }

    private int t(int i) {
        int b2 = this.t[0].b(i);
        for (int i2 = 1; i2 < this.s; i2++) {
            int b3 = this.t[i2].b(i);
            if (b3 > b2) {
                b2 = b3;
            }
        }
        return b2;
    }

    private int u(int i) {
        int a2 = this.t[0].a(i);
        for (int i2 = 1; i2 < this.s; i2++) {
            int a3 = this.t[i2].a(i);
            if (a3 < a2) {
                a2 = a3;
            }
        }
        return a2;
    }

    private boolean w(int i) {
        if (this.w == 0) {
            return (i == -1) != this.A;
        }
        return ((i == -1) == this.A) == M();
    }

    private void x(int i) {
        m mVar = this.y;
        mVar.f1912e = i;
        mVar.f1911d = this.A != (i == -1) ? -1 : 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean D() {
        return this.I == null;
    }

    boolean E() {
        int a2 = this.t[0].a(RecyclerView.UNDEFINED_DURATION);
        for (int i = 1; i < this.s; i++) {
            if (this.t[i].a(RecyclerView.UNDEFINED_DURATION) != a2) {
                return false;
            }
        }
        return true;
    }

    boolean F() {
        int b2 = this.t[0].b(RecyclerView.UNDEFINED_DURATION);
        for (int i = 1; i < this.s; i++) {
            if (this.t[i].b(RecyclerView.UNDEFINED_DURATION) != b2) {
                return false;
            }
        }
        return true;
    }

    boolean G() {
        int I;
        int J;
        if (e() == 0 || this.F == 0 || !u()) {
            return false;
        }
        if (this.A) {
            I = J();
            J = I();
        } else {
            I = I();
            J = J();
        }
        if (I == 0 && K() != null) {
            this.E.a();
            A();
            z();
            return true;
        }
        if (!this.M) {
            return false;
        }
        int i = this.A ? -1 : 1;
        int i2 = J + 1;
        d.a a2 = this.E.a(I, i2, i, true);
        if (a2 == null) {
            this.M = false;
            this.E.b(i2);
            return false;
        }
        d.a a3 = this.E.a(I, a2.f1763b, i * (-1), true);
        if (a3 == null) {
            this.E.b(a2.f1763b);
        } else {
            this.E.b(a3.f1763b + 1);
        }
        A();
        z();
        return true;
    }

    int H() {
        View a2 = this.A ? a(true) : b(true);
        if (a2 == null) {
            return -1;
        }
        return l(a2);
    }

    int I() {
        if (e() == 0) {
            return 0;
        }
        return l(d(0));
    }

    int J() {
        int e2 = e();
        if (e2 == 0) {
            return 0;
        }
        return l(d(e2 - 1));
    }

    /* JADX WARN: Code restructure failed: missing block: B:29:0x0074, code lost:
    
        if (r10 == r11) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x008a, code lost:
    
        r10 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0088, code lost:
    
        r10 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x0086, code lost:
    
        if (r10 == r11) goto L37;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    android.view.View K() {
        /*
            r12 = this;
            int r0 = r12.e()
            r1 = 1
            int r0 = r0 - r1
            java.util.BitSet r2 = new java.util.BitSet
            int r3 = r12.s
            r2.<init>(r3)
            int r3 = r12.s
            r4 = 0
            r2.set(r4, r3, r1)
            int r3 = r12.w
            r5 = -1
            if (r3 != r1) goto L20
            boolean r3 = r12.M()
            if (r3 == 0) goto L20
            r3 = 1
            goto L21
        L20:
            r3 = -1
        L21:
            boolean r6 = r12.A
            if (r6 == 0) goto L27
            r6 = -1
            goto L2b
        L27:
            int r0 = r0 + 1
            r6 = r0
            r0 = 0
        L2b:
            if (r0 >= r6) goto L2e
            r5 = 1
        L2e:
            if (r0 == r6) goto Lab
            android.view.View r7 = r12.d(r0)
            android.view.ViewGroup$LayoutParams r8 = r7.getLayoutParams()
            androidx.recyclerview.widget.StaggeredGridLayoutManager$c r8 = (androidx.recyclerview.widget.StaggeredGridLayoutManager.c) r8
            androidx.recyclerview.widget.StaggeredGridLayoutManager$f r9 = r8.f1759e
            int r9 = r9.f1776e
            boolean r9 = r2.get(r9)
            if (r9 == 0) goto L54
            androidx.recyclerview.widget.StaggeredGridLayoutManager$f r9 = r8.f1759e
            boolean r9 = r12.a(r9)
            if (r9 == 0) goto L4d
            return r7
        L4d:
            androidx.recyclerview.widget.StaggeredGridLayoutManager$f r9 = r8.f1759e
            int r9 = r9.f1776e
            r2.clear(r9)
        L54:
            boolean r9 = r8.f1760f
            if (r9 == 0) goto L59
            goto La9
        L59:
            int r9 = r0 + r5
            if (r9 == r6) goto La9
            android.view.View r9 = r12.d(r9)
            boolean r10 = r12.A
            if (r10 == 0) goto L77
            androidx.recyclerview.widget.r r10 = r12.u
            int r10 = r10.a(r7)
            androidx.recyclerview.widget.r r11 = r12.u
            int r11 = r11.a(r9)
            if (r10 >= r11) goto L74
            return r7
        L74:
            if (r10 != r11) goto L8a
            goto L88
        L77:
            androidx.recyclerview.widget.r r10 = r12.u
            int r10 = r10.d(r7)
            androidx.recyclerview.widget.r r11 = r12.u
            int r11 = r11.d(r9)
            if (r10 <= r11) goto L86
            return r7
        L86:
            if (r10 != r11) goto L8a
        L88:
            r10 = 1
            goto L8b
        L8a:
            r10 = 0
        L8b:
            if (r10 == 0) goto La9
            android.view.ViewGroup$LayoutParams r9 = r9.getLayoutParams()
            androidx.recyclerview.widget.StaggeredGridLayoutManager$c r9 = (androidx.recyclerview.widget.StaggeredGridLayoutManager.c) r9
            androidx.recyclerview.widget.StaggeredGridLayoutManager$f r8 = r8.f1759e
            int r8 = r8.f1776e
            androidx.recyclerview.widget.StaggeredGridLayoutManager$f r9 = r9.f1759e
            int r9 = r9.f1776e
            int r8 = r8 - r9
            if (r8 >= 0) goto La0
            r8 = 1
            goto La1
        La0:
            r8 = 0
        La1:
            if (r3 >= 0) goto La5
            r9 = 1
            goto La6
        La5:
            r9 = 0
        La6:
            if (r8 == r9) goto La9
            return r7
        La9:
            int r0 = r0 + r5
            goto L2e
        Lab:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.K():android.view.View");
    }

    public void L() {
        this.E.a();
        z();
    }

    boolean M() {
        return k() == 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void b(RecyclerView recyclerView, RecyclerView.v vVar) {
        super.b(recyclerView, vVar);
        a(this.P);
        for (int i = 0; i < this.s; i++) {
            this.t[i].c();
        }
        recyclerView.requestLayout();
    }

    public void c(boolean z) {
        a((String) null);
        e eVar = this.I;
        if (eVar != null && eVar.i != z) {
            eVar.i = z;
        }
        this.z = z;
        z();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int d(RecyclerView.a0 a0Var) {
        return h(a0Var);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void e(RecyclerView.v vVar, RecyclerView.a0 a0Var) {
        c(vVar, a0Var, true);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int f(RecyclerView.a0 a0Var) {
        return j(a0Var);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void g(int i) {
        if (i == 0) {
            G();
        }
    }

    public void j(int i) {
        if (i != 0 && i != 1) {
            throw new IllegalArgumentException("invalid orientation.");
        }
        a((String) null);
        if (i == this.w) {
            return;
        }
        this.w = i;
        r rVar = this.u;
        this.u = this.v;
        this.v = rVar;
        z();
    }

    public void k(int i) {
        a((String) null);
        if (i != this.s) {
            L();
            this.s = i;
            this.B = new BitSet(this.s);
            this.t = new f[this.s];
            for (int i2 = 0; i2 < this.s; i2++) {
                this.t[i2] = new f(i2);
            }
            z();
        }
    }

    void l(int i) {
        this.x = i / this.s;
        this.J = View.MeasureSpec.makeMeasureSpec(i, this.v.d());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean v() {
        return this.F != 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public Parcelable y() {
        int b2;
        int f2;
        int[] iArr;
        e eVar = this.I;
        if (eVar != null) {
            return new e(eVar);
        }
        e eVar2 = new e();
        eVar2.i = this.z;
        eVar2.j = this.G;
        eVar2.k = this.H;
        d dVar = this.E;
        if (dVar != null && (iArr = dVar.f1761a) != null) {
            eVar2.g = iArr;
            eVar2.f1771f = eVar2.g.length;
            eVar2.h = dVar.f1762b;
        } else {
            eVar2.f1771f = 0;
        }
        if (e() > 0) {
            eVar2.f1767b = this.G ? J() : I();
            eVar2.f1768c = H();
            int i = this.s;
            eVar2.f1769d = i;
            eVar2.f1770e = new int[i];
            for (int i2 = 0; i2 < this.s; i2++) {
                if (this.G) {
                    b2 = this.t[i2].a(RecyclerView.UNDEFINED_DURATION);
                    if (b2 != Integer.MIN_VALUE) {
                        f2 = this.u.b();
                        b2 -= f2;
                        eVar2.f1770e[i2] = b2;
                    } else {
                        eVar2.f1770e[i2] = b2;
                    }
                } else {
                    b2 = this.t[i2].b(RecyclerView.UNDEFINED_DURATION);
                    if (b2 != Integer.MIN_VALUE) {
                        f2 = this.u.f();
                        b2 -= f2;
                        eVar2.f1770e[i2] = b2;
                    } else {
                        eVar2.f1770e[i2] = b2;
                    }
                }
            }
        } else {
            eVar2.f1767b = -1;
            eVar2.f1768c = -1;
            eVar2.f1769d = 0;
        }
        return eVar2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class d {

        /* renamed from: a, reason: collision with root package name */
        int[] f1761a;

        /* renamed from: b, reason: collision with root package name */
        List<a> f1762b;

        d() {
        }

        private void c(int i, int i2) {
            List<a> list = this.f1762b;
            if (list == null) {
                return;
            }
            for (int size = list.size() - 1; size >= 0; size--) {
                a aVar = this.f1762b.get(size);
                int i3 = aVar.f1763b;
                if (i3 >= i) {
                    aVar.f1763b = i3 + i2;
                }
            }
        }

        private int g(int i) {
            if (this.f1762b == null) {
                return -1;
            }
            a c2 = c(i);
            if (c2 != null) {
                this.f1762b.remove(c2);
            }
            int size = this.f1762b.size();
            int i2 = 0;
            while (true) {
                if (i2 >= size) {
                    i2 = -1;
                    break;
                }
                if (this.f1762b.get(i2).f1763b >= i) {
                    break;
                }
                i2++;
            }
            if (i2 == -1) {
                return -1;
            }
            a aVar = this.f1762b.get(i2);
            this.f1762b.remove(i2);
            return aVar.f1763b;
        }

        void a(int i, f fVar) {
            a(i);
            this.f1761a[i] = fVar.f1776e;
        }

        int b(int i) {
            List<a> list = this.f1762b;
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    if (this.f1762b.get(size).f1763b >= i) {
                        this.f1762b.remove(size);
                    }
                }
            }
            return e(i);
        }

        int d(int i) {
            int[] iArr = this.f1761a;
            if (iArr == null || i >= iArr.length) {
                return -1;
            }
            return iArr[i];
        }

        int e(int i) {
            int[] iArr = this.f1761a;
            if (iArr == null || i >= iArr.length) {
                return -1;
            }
            int g = g(i);
            if (g == -1) {
                int[] iArr2 = this.f1761a;
                Arrays.fill(iArr2, i, iArr2.length, -1);
                return this.f1761a.length;
            }
            int i2 = g + 1;
            Arrays.fill(this.f1761a, i, i2, -1);
            return i2;
        }

        int f(int i) {
            int length = this.f1761a.length;
            while (length <= i) {
                length *= 2;
            }
            return length;
        }

        private void d(int i, int i2) {
            List<a> list = this.f1762b;
            if (list == null) {
                return;
            }
            int i3 = i + i2;
            for (int size = list.size() - 1; size >= 0; size--) {
                a aVar = this.f1762b.get(size);
                int i4 = aVar.f1763b;
                if (i4 >= i) {
                    if (i4 < i3) {
                        this.f1762b.remove(size);
                    } else {
                        aVar.f1763b = i4 - i2;
                    }
                }
            }
        }

        void a(int i) {
            int[] iArr = this.f1761a;
            if (iArr == null) {
                this.f1761a = new int[Math.max(i, 10) + 1];
                Arrays.fill(this.f1761a, -1);
            } else if (i >= iArr.length) {
                this.f1761a = new int[f(i)];
                System.arraycopy(iArr, 0, this.f1761a, 0, iArr.length);
                int[] iArr2 = this.f1761a;
                Arrays.fill(iArr2, iArr.length, iArr2.length, -1);
            }
        }

        public a c(int i) {
            List<a> list = this.f1762b;
            if (list == null) {
                return null;
            }
            for (int size = list.size() - 1; size >= 0; size--) {
                a aVar = this.f1762b.get(size);
                if (aVar.f1763b == i) {
                    return aVar;
                }
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @SuppressLint({"BanParcelableUsage"})
        /* loaded from: classes.dex */
        public static class a implements Parcelable {
            public static final Parcelable.Creator<a> CREATOR = new C0058a();

            /* renamed from: b, reason: collision with root package name */
            int f1763b;

            /* renamed from: c, reason: collision with root package name */
            int f1764c;

            /* renamed from: d, reason: collision with root package name */
            int[] f1765d;

            /* renamed from: e, reason: collision with root package name */
            boolean f1766e;

            /* renamed from: androidx.recyclerview.widget.StaggeredGridLayoutManager$d$a$a, reason: collision with other inner class name */
            /* loaded from: classes.dex */
            static class C0058a implements Parcelable.Creator<a> {
                C0058a() {
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public a createFromParcel(Parcel parcel) {
                    return new a(parcel);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public a[] newArray(int i) {
                    return new a[i];
                }
            }

            a(Parcel parcel) {
                this.f1763b = parcel.readInt();
                this.f1764c = parcel.readInt();
                this.f1766e = parcel.readInt() == 1;
                int readInt = parcel.readInt();
                if (readInt > 0) {
                    this.f1765d = new int[readInt];
                    parcel.readIntArray(this.f1765d);
                }
            }

            int a(int i) {
                int[] iArr = this.f1765d;
                if (iArr == null) {
                    return 0;
                }
                return iArr[i];
            }

            @Override // android.os.Parcelable
            public int describeContents() {
                return 0;
            }

            public String toString() {
                return "FullSpanItem{mPosition=" + this.f1763b + ", mGapDir=" + this.f1764c + ", mHasUnwantedGapAfter=" + this.f1766e + ", mGapPerSpan=" + Arrays.toString(this.f1765d) + '}';
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel parcel, int i) {
                parcel.writeInt(this.f1763b);
                parcel.writeInt(this.f1764c);
                parcel.writeInt(this.f1766e ? 1 : 0);
                int[] iArr = this.f1765d;
                if (iArr != null && iArr.length > 0) {
                    parcel.writeInt(iArr.length);
                    parcel.writeIntArray(this.f1765d);
                } else {
                    parcel.writeInt(0);
                }
            }

            a() {
            }
        }

        void b(int i, int i2) {
            int[] iArr = this.f1761a;
            if (iArr == null || i >= iArr.length) {
                return;
            }
            int i3 = i + i2;
            a(i3);
            int[] iArr2 = this.f1761a;
            System.arraycopy(iArr2, i3, iArr2, i, (iArr2.length - i) - i2);
            int[] iArr3 = this.f1761a;
            Arrays.fill(iArr3, iArr3.length - i2, iArr3.length, -1);
            d(i, i2);
        }

        void a() {
            int[] iArr = this.f1761a;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
            this.f1762b = null;
        }

        void a(int i, int i2) {
            int[] iArr = this.f1761a;
            if (iArr == null || i >= iArr.length) {
                return;
            }
            int i3 = i + i2;
            a(i3);
            int[] iArr2 = this.f1761a;
            System.arraycopy(iArr2, i, iArr2, i3, (iArr2.length - i) - i2);
            Arrays.fill(this.f1761a, i, i3, -1);
            c(i, i2);
        }

        public void a(a aVar) {
            if (this.f1762b == null) {
                this.f1762b = new ArrayList();
            }
            int size = this.f1762b.size();
            for (int i = 0; i < size; i++) {
                a aVar2 = this.f1762b.get(i);
                if (aVar2.f1763b == aVar.f1763b) {
                    this.f1762b.remove(i);
                }
                if (aVar2.f1763b >= aVar.f1763b) {
                    this.f1762b.add(i, aVar);
                    return;
                }
            }
            this.f1762b.add(aVar);
        }

        public a a(int i, int i2, int i3, boolean z) {
            List<a> list = this.f1762b;
            if (list == null) {
                return null;
            }
            int size = list.size();
            for (int i4 = 0; i4 < size; i4++) {
                a aVar = this.f1762b.get(i4);
                int i5 = aVar.f1763b;
                if (i5 >= i2) {
                    return null;
                }
                if (i5 >= i && (i3 == 0 || aVar.f1764c == i3 || (z && aVar.f1766e))) {
                    return aVar;
                }
            }
            return null;
        }
    }

    private int v(int i) {
        int b2 = this.t[0].b(i);
        for (int i2 = 1; i2 < this.s; i2++) {
            int b3 = this.t[i2].b(i);
            if (b3 < b2) {
                b2 = b3;
            }
        }
        return b2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void d(RecyclerView recyclerView) {
        this.E.a();
        z();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int e(RecyclerView.a0 a0Var) {
        return i(a0Var);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void f(int i) {
        super.f(i);
        for (int i2 = 0; i2 < this.s; i2++) {
            this.t[i2].c(i);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void g(RecyclerView.a0 a0Var) {
        super.g(a0Var);
        this.C = -1;
        this.D = RecyclerView.UNDEFINED_DURATION;
        this.I = null;
        this.L.b();
    }

    private int q(int i) {
        int e2 = e();
        for (int i2 = 0; i2 < e2; i2++) {
            int l = l(d(i2));
            if (l >= 0 && l < i) {
                return l;
            }
        }
        return 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void e(int i) {
        super.e(i);
        for (int i2 = 0; i2 < this.s; i2++) {
            this.t[i2].c(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class b {

        /* renamed from: a, reason: collision with root package name */
        int f1753a;

        /* renamed from: b, reason: collision with root package name */
        int f1754b;

        /* renamed from: c, reason: collision with root package name */
        boolean f1755c;

        /* renamed from: d, reason: collision with root package name */
        boolean f1756d;

        /* renamed from: e, reason: collision with root package name */
        boolean f1757e;

        /* renamed from: f, reason: collision with root package name */
        int[] f1758f;

        b() {
            b();
        }

        void a(f[] fVarArr) {
            int length = fVarArr.length;
            int[] iArr = this.f1758f;
            if (iArr == null || iArr.length < length) {
                this.f1758f = new int[StaggeredGridLayoutManager.this.t.length];
            }
            for (int i = 0; i < length; i++) {
                this.f1758f[i] = fVarArr[i].b(RecyclerView.UNDEFINED_DURATION);
            }
        }

        void b() {
            this.f1753a = -1;
            this.f1754b = RecyclerView.UNDEFINED_DURATION;
            this.f1755c = false;
            this.f1756d = false;
            this.f1757e = false;
            int[] iArr = this.f1758f;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
        }

        void a() {
            this.f1754b = this.f1755c ? StaggeredGridLayoutManager.this.u.b() : StaggeredGridLayoutManager.this.u.f();
        }

        void a(int i) {
            if (this.f1755c) {
                this.f1754b = StaggeredGridLayoutManager.this.u.b() - i;
            } else {
                this.f1754b = StaggeredGridLayoutManager.this.u.f() + i;
            }
        }
    }

    private void p(View view) {
        for (int i = this.s - 1; i >= 0; i--) {
            this.t[i].a(view);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:82:0x0157, code lost:
    
        if (G() != false) goto L90;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void c(androidx.recyclerview.widget.RecyclerView.v r9, androidx.recyclerview.widget.RecyclerView.a0 r10, boolean r11) {
        /*
            Method dump skipped, instructions count: 379
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.c(androidx.recyclerview.widget.RecyclerView$v, androidx.recyclerview.widget.RecyclerView$a0, boolean):void");
    }

    private void e(int i, int i2) {
        for (int i3 = 0; i3 < this.s; i3++) {
            if (!this.t[i3].f1772a.isEmpty()) {
                a(this.t[i3], i, i2);
            }
        }
    }

    void b(RecyclerView.a0 a0Var, b bVar) {
        if (a(a0Var, bVar) || c(a0Var, bVar)) {
            return;
        }
        bVar.a();
        bVar.f1753a = 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void i(int i) {
        e eVar = this.I;
        if (eVar != null && eVar.f1767b != i) {
            eVar.d();
        }
        this.C = i;
        this.D = RecyclerView.UNDEFINED_DURATION;
        z();
    }

    private int j(RecyclerView.a0 a0Var) {
        if (e() == 0) {
            return 0;
        }
        return t.b(a0Var, this.u, b(!this.N), a(!this.N), this, this.N);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void a(String str) {
        if (this.I == null) {
            super.a(str);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int b(RecyclerView.a0 a0Var) {
        return i(a0Var);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int b(RecyclerView.v vVar, RecyclerView.a0 a0Var) {
        if (this.w == 0) {
            return this.s;
        }
        return super.b(vVar, a0Var);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void a(Rect rect, int i, int i2) {
        int a2;
        int a3;
        int o = o() + p();
        int q = q() + n();
        if (this.w == 1) {
            a3 = RecyclerView.o.a(i2, rect.height() + q, l());
            a2 = RecyclerView.o.a(i, (this.x * this.s) + o, m());
        } else {
            a2 = RecyclerView.o.a(i, rect.width() + o, m());
            a3 = RecyclerView.o.a(i2, (this.x * this.s) + q, l());
        }
        c(a2, a3);
    }

    View b(boolean z) {
        int f2 = this.u.f();
        int b2 = this.u.b();
        int e2 = e();
        View view = null;
        for (int i = 0; i < e2; i++) {
            View d2 = d(i);
            int d3 = this.u.d(d2);
            if (this.u.a(d2) > f2 && d3 < b2) {
                if (d3 >= f2 || !z) {
                    return d2;
                }
                if (view == null) {
                    view = d2;
                }
            }
        }
        return view;
    }

    private void b(RecyclerView.v vVar, RecyclerView.a0 a0Var, boolean z) {
        int f2;
        int v = v(Preference.DEFAULT_ORDER);
        if (v != Integer.MAX_VALUE && (f2 = v - this.u.f()) > 0) {
            int c2 = f2 - c(f2, vVar, a0Var);
            if (!z || c2 <= 0) {
                return;
            }
            this.u.a(-c2);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x004d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void b(int r5, androidx.recyclerview.widget.RecyclerView.a0 r6) {
        /*
            r4 = this;
            androidx.recyclerview.widget.m r0 = r4.y
            r1 = 0
            r0.f1909b = r1
            r0.f1910c = r5
            boolean r0 = r4.x()
            r2 = 1
            if (r0 == 0) goto L2e
            int r6 = r6.b()
            r0 = -1
            if (r6 == r0) goto L2e
            boolean r0 = r4.A
            if (r6 >= r5) goto L1b
            r5 = 1
            goto L1c
        L1b:
            r5 = 0
        L1c:
            if (r0 != r5) goto L25
            androidx.recyclerview.widget.r r5 = r4.u
            int r5 = r5.g()
            goto L2f
        L25:
            androidx.recyclerview.widget.r r5 = r4.u
            int r5 = r5.g()
            r6 = r5
            r5 = 0
            goto L30
        L2e:
            r5 = 0
        L2f:
            r6 = 0
        L30:
            boolean r0 = r4.f()
            if (r0 == 0) goto L4d
            androidx.recyclerview.widget.m r0 = r4.y
            androidx.recyclerview.widget.r r3 = r4.u
            int r3 = r3.f()
            int r3 = r3 - r6
            r0.f1913f = r3
            androidx.recyclerview.widget.m r6 = r4.y
            androidx.recyclerview.widget.r r0 = r4.u
            int r0 = r0.b()
            int r0 = r0 + r5
            r6.g = r0
            goto L5d
        L4d:
            androidx.recyclerview.widget.m r0 = r4.y
            androidx.recyclerview.widget.r r3 = r4.u
            int r3 = r3.a()
            int r3 = r3 + r5
            r0.g = r3
            androidx.recyclerview.widget.m r5 = r4.y
            int r6 = -r6
            r5.f1913f = r6
        L5d:
            androidx.recyclerview.widget.m r5 = r4.y
            r5.h = r1
            r5.f1908a = r2
            androidx.recyclerview.widget.r r6 = r4.u
            int r6 = r6.d()
            if (r6 != 0) goto L74
            androidx.recyclerview.widget.r r6 = r4.u
            int r6 = r6.a()
            if (r6 != 0) goto L74
            r1 = 1
        L74:
            r5.i = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.b(int, androidx.recyclerview.widget.RecyclerView$a0):void");
    }

    private void a(b bVar) {
        int f2;
        e eVar = this.I;
        int i = eVar.f1769d;
        if (i > 0) {
            if (i == this.s) {
                for (int i2 = 0; i2 < this.s; i2++) {
                    this.t[i2].c();
                    e eVar2 = this.I;
                    int i3 = eVar2.f1770e[i2];
                    if (i3 != Integer.MIN_VALUE) {
                        if (eVar2.j) {
                            f2 = this.u.b();
                        } else {
                            f2 = this.u.f();
                        }
                        i3 += f2;
                    }
                    this.t[i2].d(i3);
                }
            } else {
                eVar.e();
                e eVar3 = this.I;
                eVar3.f1767b = eVar3.f1768c;
            }
        }
        e eVar4 = this.I;
        this.H = eVar4.k;
        c(eVar4.i);
        P();
        e eVar5 = this.I;
        int i4 = eVar5.f1767b;
        if (i4 != -1) {
            this.C = i4;
            bVar.f1755c = eVar5.j;
        } else {
            bVar.f1755c = this.A;
        }
        e eVar6 = this.I;
        if (eVar6.f1771f > 1) {
            d dVar = this.E;
            dVar.f1761a = eVar6.g;
            dVar.f1762b = eVar6.h;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void b(RecyclerView recyclerView, int i, int i2) {
        b(i, i2, 2);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0027  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0045 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x003e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void b(int r7, int r8, int r9) {
        /*
            r6 = this;
            boolean r0 = r6.A
            if (r0 == 0) goto L9
            int r0 = r6.J()
            goto Ld
        L9:
            int r0 = r6.I()
        Ld:
            r1 = 8
            if (r9 != r1) goto L1b
            if (r7 >= r8) goto L16
            int r2 = r8 + 1
            goto L1d
        L16:
            int r2 = r7 + 1
            r3 = r2
            r2 = r8
            goto L1f
        L1b:
            int r2 = r7 + r8
        L1d:
            r3 = r2
            r2 = r7
        L1f:
            androidx.recyclerview.widget.StaggeredGridLayoutManager$d r4 = r6.E
            r4.e(r2)
            r4 = 1
            if (r9 == r4) goto L3e
            r5 = 2
            if (r9 == r5) goto L38
            if (r9 == r1) goto L2d
            goto L43
        L2d:
            androidx.recyclerview.widget.StaggeredGridLayoutManager$d r9 = r6.E
            r9.b(r7, r4)
            androidx.recyclerview.widget.StaggeredGridLayoutManager$d r7 = r6.E
            r7.a(r8, r4)
            goto L43
        L38:
            androidx.recyclerview.widget.StaggeredGridLayoutManager$d r9 = r6.E
            r9.b(r7, r8)
            goto L43
        L3e:
            androidx.recyclerview.widget.StaggeredGridLayoutManager$d r9 = r6.E
            r9.a(r7, r8)
        L43:
            if (r3 > r0) goto L46
            return
        L46:
            boolean r7 = r6.A
            if (r7 == 0) goto L4f
            int r7 = r6.I()
            goto L53
        L4f:
            int r7 = r6.J()
        L53:
            if (r2 > r7) goto L58
            r6.z()
        L58:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.b(int, int, int):void");
    }

    boolean a(RecyclerView.a0 a0Var, b bVar) {
        int i;
        int f2;
        if (!a0Var.d() && (i = this.C) != -1) {
            if (i >= 0 && i < a0Var.a()) {
                e eVar = this.I;
                if (eVar != null && eVar.f1767b != -1 && eVar.f1769d >= 1) {
                    bVar.f1754b = RecyclerView.UNDEFINED_DURATION;
                    bVar.f1753a = this.C;
                } else {
                    View c2 = c(this.C);
                    if (c2 != null) {
                        bVar.f1753a = this.A ? J() : I();
                        if (this.D != Integer.MIN_VALUE) {
                            if (bVar.f1755c) {
                                bVar.f1754b = (this.u.b() - this.D) - this.u.a(c2);
                            } else {
                                bVar.f1754b = (this.u.f() + this.D) - this.u.d(c2);
                            }
                            return true;
                        }
                        if (this.u.b(c2) > this.u.g()) {
                            if (bVar.f1755c) {
                                f2 = this.u.b();
                            } else {
                                f2 = this.u.f();
                            }
                            bVar.f1754b = f2;
                            return true;
                        }
                        int d2 = this.u.d(c2) - this.u.f();
                        if (d2 < 0) {
                            bVar.f1754b = -d2;
                            return true;
                        }
                        int b2 = this.u.b() - this.u.a(c2);
                        if (b2 < 0) {
                            bVar.f1754b = b2;
                            return true;
                        }
                        bVar.f1754b = RecyclerView.UNDEFINED_DURATION;
                    } else {
                        bVar.f1753a = this.C;
                        int i2 = this.D;
                        if (i2 == Integer.MIN_VALUE) {
                            bVar.f1755c = m(bVar.f1753a) == 1;
                            bVar.a();
                        } else {
                            bVar.a(i2);
                        }
                        bVar.f1756d = true;
                    }
                }
                return true;
            }
            this.C = -1;
            this.D = RecyclerView.UNDEFINED_DURATION;
        }
        return false;
    }

    private void b(RecyclerView.v vVar, int i) {
        while (e() > 0) {
            View d2 = d(0);
            if (this.u.a(d2) > i || this.u.e(d2) > i) {
                return;
            }
            c cVar = (c) d2.getLayoutParams();
            if (cVar.f1760f) {
                for (int i2 = 0; i2 < this.s; i2++) {
                    if (this.t[i2].f1772a.size() == 1) {
                        return;
                    }
                }
                for (int i3 = 0; i3 < this.s; i3++) {
                    this.t[i3].k();
                }
            } else if (cVar.f1759e.f1772a.size() == 1) {
                return;
            } else {
                cVar.f1759e.k();
            }
            a(d2, vVar);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean b() {
        return this.w == 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int b(int i, RecyclerView.v vVar, RecyclerView.a0 a0Var) {
        return c(i, vVar, a0Var);
    }

    private boolean c(RecyclerView.a0 a0Var, b bVar) {
        int q;
        if (this.G) {
            q = r(a0Var.a());
        } else {
            q = q(a0Var.a());
        }
        bVar.f1753a = q;
        bVar.f1754b = RecyclerView.UNDEFINED_DURATION;
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int c(RecyclerView.a0 a0Var) {
        return j(a0Var);
    }

    private int c(int i, int i2, int i3) {
        if (i2 == 0 && i3 == 0) {
            return i;
        }
        int mode = View.MeasureSpec.getMode(i);
        return (mode == Integer.MIN_VALUE || mode == 1073741824) ? View.MeasureSpec.makeMeasureSpec(Math.max(0, (View.MeasureSpec.getSize(i) - i2) - i3), mode) : i;
    }

    int c(int i, RecyclerView.v vVar, RecyclerView.a0 a0Var) {
        if (e() == 0 || i == 0) {
            return 0;
        }
        a(i, a0Var);
        int a2 = a(vVar, this.y, a0Var);
        if (this.y.f1909b >= a2) {
            i = i < 0 ? -a2 : a2;
        }
        this.u.a(-i);
        this.G = this.A;
        m mVar = this.y;
        mVar.f1909b = 0;
        a(vVar, mVar);
        return i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int a(RecyclerView.a0 a0Var) {
        return h(a0Var);
    }

    private void a(View view, c cVar, boolean z) {
        if (cVar.f1760f) {
            if (this.w == 1) {
                a(view, this.J, RecyclerView.o.a(h(), i(), q() + n(), ((ViewGroup.MarginLayoutParams) cVar).height, true), z);
                return;
            } else {
                a(view, RecyclerView.o.a(r(), s(), o() + p(), ((ViewGroup.MarginLayoutParams) cVar).width, true), this.J, z);
                return;
            }
        }
        if (this.w == 1) {
            a(view, RecyclerView.o.a(this.x, s(), 0, ((ViewGroup.MarginLayoutParams) cVar).width, false), RecyclerView.o.a(h(), i(), q() + n(), ((ViewGroup.MarginLayoutParams) cVar).height, true), z);
        } else {
            a(view, RecyclerView.o.a(r(), s(), o() + p(), ((ViewGroup.MarginLayoutParams) cVar).width, true), RecyclerView.o.a(this.x, i(), 0, ((ViewGroup.MarginLayoutParams) cVar).height, false), z);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public RecyclerView.p c() {
        if (this.w == 0) {
            return new c(-2, -1);
        }
        return new c(-1, -2);
    }

    private void a(View view, int i, int i2, boolean z) {
        boolean a2;
        a(view, this.K);
        c cVar = (c) view.getLayoutParams();
        int i3 = ((ViewGroup.MarginLayoutParams) cVar).leftMargin;
        Rect rect = this.K;
        int c2 = c(i, i3 + rect.left, ((ViewGroup.MarginLayoutParams) cVar).rightMargin + rect.right);
        int i4 = ((ViewGroup.MarginLayoutParams) cVar).topMargin;
        Rect rect2 = this.K;
        int c3 = c(i2, i4 + rect2.top, ((ViewGroup.MarginLayoutParams) cVar).bottomMargin + rect2.bottom);
        if (z) {
            a2 = b(view, c2, c3, cVar);
        } else {
            a2 = a(view, c2, c3, cVar);
        }
        if (a2) {
            view.measure(c2, c3);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void a(Parcelable parcelable) {
        if (parcelable instanceof e) {
            this.I = (e) parcelable;
            z();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void a(RecyclerView.v vVar, RecyclerView.a0 a0Var, View view, a.f.l.f0.c cVar) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof c)) {
            super.a(view, cVar);
            return;
        }
        c cVar2 = (c) layoutParams;
        if (this.w == 0) {
            cVar.b(c.C0018c.a(cVar2.e(), cVar2.f1760f ? this.s : 1, -1, -1, false, false));
        } else {
            cVar.b(c.C0018c.a(-1, -1, cVar2.e(), cVar2.f1760f ? this.s : 1, false, false));
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void a(AccessibilityEvent accessibilityEvent) {
        super.a(accessibilityEvent);
        if (e() > 0) {
            View b2 = b(false);
            View a2 = a(false);
            if (b2 == null || a2 == null) {
                return;
            }
            int l = l(b2);
            int l2 = l(a2);
            if (l < l2) {
                accessibilityEvent.setFromIndex(l);
                accessibilityEvent.setToIndex(l2);
            } else {
                accessibilityEvent.setFromIndex(l2);
                accessibilityEvent.setToIndex(l);
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int a(RecyclerView.v vVar, RecyclerView.a0 a0Var) {
        if (this.w == 1) {
            return this.s;
        }
        return super.a(vVar, a0Var);
    }

    View a(boolean z) {
        int f2 = this.u.f();
        int b2 = this.u.b();
        View view = null;
        for (int e2 = e() - 1; e2 >= 0; e2--) {
            View d2 = d(e2);
            int d3 = this.u.d(d2);
            int a2 = this.u.a(d2);
            if (a2 > f2 && d3 < b2) {
                if (a2 <= b2 || !z) {
                    return d2;
                }
                if (view == null) {
                    view = d2;
                }
            }
        }
        return view;
    }

    private void a(RecyclerView.v vVar, RecyclerView.a0 a0Var, boolean z) {
        int b2;
        int s = s(RecyclerView.UNDEFINED_DURATION);
        if (s != Integer.MIN_VALUE && (b2 = this.u.b() - s) > 0) {
            int i = b2 - (-c(-b2, vVar, a0Var));
            if (!z || i <= 0) {
                return;
            }
            this.u.a(i);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void a(RecyclerView recyclerView, int i, int i2) {
        b(i, i2, 1);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void a(RecyclerView recyclerView, int i, int i2, int i3) {
        b(i, i2, 8);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void a(RecyclerView recyclerView, int i, int i2, Object obj) {
        b(i, i2, 4);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r9v0 */
    /* JADX WARN: Type inference failed for: r9v1, types: [int, boolean] */
    /* JADX WARN: Type inference failed for: r9v6 */
    private int a(RecyclerView.v vVar, m mVar, RecyclerView.a0 a0Var) {
        int i;
        int i2;
        int f2;
        int s;
        f fVar;
        int b2;
        int i3;
        int i4;
        int b3;
        boolean F;
        ?? r9 = 0;
        this.B.set(0, this.s, true);
        if (this.y.i) {
            i2 = mVar.f1912e == 1 ? Preference.DEFAULT_ORDER : RecyclerView.UNDEFINED_DURATION;
        } else {
            if (mVar.f1912e == 1) {
                i = mVar.g + mVar.f1909b;
            } else {
                i = mVar.f1913f - mVar.f1909b;
            }
            i2 = i;
        }
        e(mVar.f1912e, i2);
        if (this.A) {
            f2 = this.u.b();
        } else {
            f2 = this.u.f();
        }
        int i5 = f2;
        boolean z = false;
        while (mVar.a(a0Var) && (this.y.i || !this.B.isEmpty())) {
            View a2 = mVar.a(vVar);
            c cVar = (c) a2.getLayoutParams();
            int a3 = cVar.a();
            int d2 = this.E.d(a3);
            boolean z2 = d2 == -1;
            if (z2) {
                fVar = cVar.f1760f ? this.t[r9] : a(mVar);
                this.E.a(a3, fVar);
            } else {
                fVar = this.t[d2];
            }
            f fVar2 = fVar;
            cVar.f1759e = fVar2;
            if (mVar.f1912e == 1) {
                b(a2);
            } else {
                b(a2, (int) r9);
            }
            a(a2, cVar, (boolean) r9);
            if (mVar.f1912e == 1) {
                int s2 = cVar.f1760f ? s(i5) : fVar2.a(i5);
                int b4 = this.u.b(a2) + s2;
                if (z2 && cVar.f1760f) {
                    d.a o = o(s2);
                    o.f1764c = -1;
                    o.f1763b = a3;
                    this.E.a(o);
                }
                i3 = b4;
                b2 = s2;
            } else {
                int v = cVar.f1760f ? v(i5) : fVar2.b(i5);
                b2 = v - this.u.b(a2);
                if (z2 && cVar.f1760f) {
                    d.a p = p(v);
                    p.f1764c = 1;
                    p.f1763b = a3;
                    this.E.a(p);
                }
                i3 = v;
            }
            if (cVar.f1760f && mVar.f1911d == -1) {
                if (z2) {
                    this.M = true;
                } else {
                    if (mVar.f1912e == 1) {
                        F = E();
                    } else {
                        F = F();
                    }
                    if (!F) {
                        d.a c2 = this.E.c(a3);
                        if (c2 != null) {
                            c2.f1766e = true;
                        }
                        this.M = true;
                    }
                }
            }
            a(a2, cVar, mVar);
            if (M() && this.w == 1) {
                int b5 = cVar.f1760f ? this.v.b() : this.v.b() - (((this.s - 1) - fVar2.f1776e) * this.x);
                b3 = b5;
                i4 = b5 - this.v.b(a2);
            } else {
                int f3 = cVar.f1760f ? this.v.f() : (fVar2.f1776e * this.x) + this.v.f();
                i4 = f3;
                b3 = this.v.b(a2) + f3;
            }
            if (this.w == 1) {
                a(a2, i4, b2, b3, i3);
            } else {
                a(a2, b2, i4, i3, b3);
            }
            if (cVar.f1760f) {
                e(this.y.f1912e, i2);
            } else {
                a(fVar2, this.y.f1912e, i2);
            }
            a(vVar, this.y);
            if (this.y.h && a2.hasFocusable()) {
                if (cVar.f1760f) {
                    this.B.clear();
                } else {
                    this.B.set(fVar2.f1776e, false);
                    z = true;
                    r9 = 0;
                }
            }
            z = true;
            r9 = 0;
        }
        if (!z) {
            a(vVar, this.y);
        }
        if (this.y.f1912e == -1) {
            s = this.u.f() - v(this.u.f());
        } else {
            s = s(this.u.b()) - this.u.b();
        }
        if (s > 0) {
            return Math.min(mVar.f1909b, s);
        }
        return 0;
    }

    private void a(View view, c cVar, m mVar) {
        if (mVar.f1912e == 1) {
            if (cVar.f1760f) {
                p(view);
                return;
            } else {
                cVar.f1759e.a(view);
                return;
            }
        }
        if (cVar.f1760f) {
            q(view);
        } else {
            cVar.f1759e.c(view);
        }
    }

    private void a(RecyclerView.v vVar, m mVar) {
        int min;
        int min2;
        if (!mVar.f1908a || mVar.i) {
            return;
        }
        if (mVar.f1909b == 0) {
            if (mVar.f1912e == -1) {
                a(vVar, mVar.g);
                return;
            } else {
                b(vVar, mVar.f1913f);
                return;
            }
        }
        if (mVar.f1912e == -1) {
            int i = mVar.f1913f;
            int t = i - t(i);
            if (t < 0) {
                min2 = mVar.g;
            } else {
                min2 = mVar.g - Math.min(t, mVar.f1909b);
            }
            a(vVar, min2);
            return;
        }
        int u = u(mVar.g) - mVar.g;
        if (u < 0) {
            min = mVar.f1913f;
        } else {
            min = Math.min(u, mVar.f1909b) + mVar.f1913f;
        }
        b(vVar, min);
    }

    private void a(f fVar, int i, int i2) {
        int f2 = fVar.f();
        if (i == -1) {
            if (fVar.h() + f2 <= i2) {
                this.B.set(fVar.f1776e, false);
            }
        } else if (fVar.g() - f2 >= i2) {
            this.B.set(fVar.f1776e, false);
        }
    }

    private void a(RecyclerView.v vVar, int i) {
        for (int e2 = e() - 1; e2 >= 0; e2--) {
            View d2 = d(e2);
            if (this.u.d(d2) < i || this.u.f(d2) < i) {
                return;
            }
            c cVar = (c) d2.getLayoutParams();
            if (cVar.f1760f) {
                for (int i2 = 0; i2 < this.s; i2++) {
                    if (this.t[i2].f1772a.size() == 1) {
                        return;
                    }
                }
                for (int i3 = 0; i3 < this.s; i3++) {
                    this.t[i3].j();
                }
            } else if (cVar.f1759e.f1772a.size() == 1) {
                return;
            } else {
                cVar.f1759e.j();
            }
            a(d2, vVar);
        }
    }

    private f a(m mVar) {
        int i;
        int i2;
        int i3 = -1;
        if (w(mVar.f1912e)) {
            i = this.s - 1;
            i2 = -1;
        } else {
            i = 0;
            i3 = this.s;
            i2 = 1;
        }
        f fVar = null;
        if (mVar.f1912e == 1) {
            int i4 = Preference.DEFAULT_ORDER;
            int f2 = this.u.f();
            while (i != i3) {
                f fVar2 = this.t[i];
                int a2 = fVar2.a(f2);
                if (a2 < i4) {
                    fVar = fVar2;
                    i4 = a2;
                }
                i += i2;
            }
            return fVar;
        }
        int i5 = RecyclerView.UNDEFINED_DURATION;
        int b2 = this.u.b();
        while (i != i3) {
            f fVar3 = this.t[i];
            int b3 = fVar3.b(b2);
            if (b3 > i5) {
                fVar = fVar3;
                i5 = b3;
            }
            i += i2;
        }
        return fVar;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean a() {
        return this.w == 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int a(int i, RecyclerView.v vVar, RecyclerView.a0 a0Var) {
        return c(i, vVar, a0Var);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.z.b
    public PointF a(int i) {
        int m = m(i);
        PointF pointF = new PointF();
        if (m == 0) {
            return null;
        }
        if (this.w == 0) {
            pointF.x = m;
            pointF.y = 0.0f;
        } else {
            pointF.x = 0.0f;
            pointF.y = m;
        }
        return pointF;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void a(RecyclerView recyclerView, RecyclerView.a0 a0Var, int i) {
        n nVar = new n(recyclerView.getContext());
        nVar.c(i);
        b(nVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void a(int i, int i2, RecyclerView.a0 a0Var, RecyclerView.o.c cVar) {
        int a2;
        int i3;
        if (this.w != 0) {
            i = i2;
        }
        if (e() == 0 || i == 0) {
            return;
        }
        a(i, a0Var);
        int[] iArr = this.O;
        if (iArr == null || iArr.length < this.s) {
            this.O = new int[this.s];
        }
        int i4 = 0;
        for (int i5 = 0; i5 < this.s; i5++) {
            m mVar = this.y;
            if (mVar.f1911d == -1) {
                a2 = mVar.f1913f;
                i3 = this.t[i5].b(a2);
            } else {
                a2 = this.t[i5].a(mVar.g);
                i3 = this.y.g;
            }
            int i6 = a2 - i3;
            if (i6 >= 0) {
                this.O[i4] = i6;
                i4++;
            }
        }
        Arrays.sort(this.O, 0, i4);
        for (int i7 = 0; i7 < i4 && this.y.a(a0Var); i7++) {
            cVar.a(this.y.f1910c, this.O[i7]);
            m mVar2 = this.y;
            mVar2.f1910c += mVar2.f1911d;
        }
    }

    void a(int i, RecyclerView.a0 a0Var) {
        int I;
        int i2;
        if (i > 0) {
            I = J();
            i2 = 1;
        } else {
            I = I();
            i2 = -1;
        }
        this.y.f1908a = true;
        b(I, a0Var);
        x(i2);
        m mVar = this.y;
        mVar.f1910c = I + mVar.f1911d;
        mVar.f1909b = Math.abs(i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public RecyclerView.p a(Context context, AttributeSet attributeSet) {
        return new c(context, attributeSet);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public RecyclerView.p a(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            return new c((ViewGroup.MarginLayoutParams) layoutParams);
        }
        return new c(layoutParams);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean a(RecyclerView.p pVar) {
        return pVar instanceof c;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public View a(View view, int i, RecyclerView.v vVar, RecyclerView.a0 a0Var) {
        View c2;
        int I;
        int e2;
        int e3;
        int e4;
        View a2;
        if (e() == 0 || (c2 = c(view)) == null) {
            return null;
        }
        P();
        int n = n(i);
        if (n == Integer.MIN_VALUE) {
            return null;
        }
        c cVar = (c) c2.getLayoutParams();
        boolean z = cVar.f1760f;
        f fVar = cVar.f1759e;
        if (n == 1) {
            I = J();
        } else {
            I = I();
        }
        b(I, a0Var);
        x(n);
        m mVar = this.y;
        mVar.f1910c = mVar.f1911d + I;
        mVar.f1909b = (int) (this.u.g() * 0.33333334f);
        m mVar2 = this.y;
        mVar2.h = true;
        mVar2.f1908a = false;
        a(vVar, mVar2, a0Var);
        this.G = this.A;
        if (!z && (a2 = fVar.a(I, n)) != null && a2 != c2) {
            return a2;
        }
        if (w(n)) {
            for (int i2 = this.s - 1; i2 >= 0; i2--) {
                View a3 = this.t[i2].a(I, n);
                if (a3 != null && a3 != c2) {
                    return a3;
                }
            }
        } else {
            for (int i3 = 0; i3 < this.s; i3++) {
                View a4 = this.t[i3].a(I, n);
                if (a4 != null && a4 != c2) {
                    return a4;
                }
            }
        }
        boolean z2 = (this.z ^ true) == (n == -1);
        if (!z) {
            if (z2) {
                e4 = fVar.d();
            } else {
                e4 = fVar.e();
            }
            View c3 = c(e4);
            if (c3 != null && c3 != c2) {
                return c3;
            }
        }
        if (w(n)) {
            for (int i4 = this.s - 1; i4 >= 0; i4--) {
                if (i4 != fVar.f1776e) {
                    if (z2) {
                        e3 = this.t[i4].d();
                    } else {
                        e3 = this.t[i4].e();
                    }
                    View c4 = c(e3);
                    if (c4 != null && c4 != c2) {
                        return c4;
                    }
                }
            }
        } else {
            for (int i5 = 0; i5 < this.s; i5++) {
                if (z2) {
                    e2 = this.t[i5].d();
                } else {
                    e2 = this.t[i5].e();
                }
                View c5 = c(e2);
                if (c5 != null && c5 != c2) {
                    return c5;
                }
            }
        }
        return null;
    }
}
