package androidx.recyclerview.widget;

import a.f.l.f0.c;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes.dex */
public class GridLayoutManager extends LinearLayoutManager {
    boolean I;
    int J;
    int[] K;
    View[] L;
    final SparseIntArray M;
    final SparseIntArray N;
    c O;
    final Rect P;
    private boolean Q;

    /* loaded from: classes.dex */
    public static final class a extends c {
        @Override // androidx.recyclerview.widget.GridLayoutManager.c
        public int getSpanIndex(int i, int i2) {
            return i % i2;
        }

        @Override // androidx.recyclerview.widget.GridLayoutManager.c
        public int getSpanSize(int i) {
            return 1;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class c {
        final SparseIntArray mSpanIndexCache = new SparseIntArray();
        final SparseIntArray mSpanGroupIndexCache = new SparseIntArray();
        private boolean mCacheSpanIndices = false;
        private boolean mCacheSpanGroupIndices = false;

        static int findFirstKeyLessThan(SparseIntArray sparseIntArray, int i) {
            int size = sparseIntArray.size() - 1;
            int i2 = 0;
            while (i2 <= size) {
                int i3 = (i2 + size) >>> 1;
                if (sparseIntArray.keyAt(i3) < i) {
                    i2 = i3 + 1;
                } else {
                    size = i3 - 1;
                }
            }
            int i4 = i2 - 1;
            if (i4 < 0 || i4 >= sparseIntArray.size()) {
                return -1;
            }
            return sparseIntArray.keyAt(i4);
        }

        int getCachedSpanGroupIndex(int i, int i2) {
            if (!this.mCacheSpanGroupIndices) {
                return getSpanGroupIndex(i, i2);
            }
            int i3 = this.mSpanGroupIndexCache.get(i, -1);
            if (i3 != -1) {
                return i3;
            }
            int spanGroupIndex = getSpanGroupIndex(i, i2);
            this.mSpanGroupIndexCache.put(i, spanGroupIndex);
            return spanGroupIndex;
        }

        int getCachedSpanIndex(int i, int i2) {
            if (!this.mCacheSpanIndices) {
                return getSpanIndex(i, i2);
            }
            int i3 = this.mSpanIndexCache.get(i, -1);
            if (i3 != -1) {
                return i3;
            }
            int spanIndex = getSpanIndex(i, i2);
            this.mSpanIndexCache.put(i, spanIndex);
            return spanIndex;
        }

        public int getSpanGroupIndex(int i, int i2) {
            int i3;
            int i4;
            int i5;
            int findFirstKeyLessThan;
            if (!this.mCacheSpanGroupIndices || (findFirstKeyLessThan = findFirstKeyLessThan(this.mSpanGroupIndexCache, i)) == -1) {
                i3 = 0;
                i4 = 0;
                i5 = 0;
            } else {
                i4 = this.mSpanGroupIndexCache.get(findFirstKeyLessThan);
                i5 = findFirstKeyLessThan + 1;
                i3 = getSpanSize(findFirstKeyLessThan) + getCachedSpanIndex(findFirstKeyLessThan, i2);
                if (i3 == i2) {
                    i4++;
                    i3 = 0;
                }
            }
            int spanSize = getSpanSize(i);
            while (i5 < i) {
                int spanSize2 = getSpanSize(i5);
                i3 += spanSize2;
                if (i3 == i2) {
                    i4++;
                    i3 = 0;
                } else if (i3 > i2) {
                    i4++;
                    i3 = spanSize2;
                }
                i5++;
            }
            return i3 + spanSize > i2 ? i4 + 1 : i4;
        }

        /* JADX WARN: Removed duplicated region for block: B:12:0x0024  */
        /* JADX WARN: Removed duplicated region for block: B:17:0x0033  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:14:0x002b -> B:10:0x0030). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:15:0x002d -> B:10:0x0030). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x002f -> B:10:0x0030). Please report as a decompilation issue!!! */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public int getSpanIndex(int r6, int r7) {
            /*
                r5 = this;
                int r0 = r5.getSpanSize(r6)
                r1 = 0
                if (r0 != r7) goto L8
                return r1
            L8:
                boolean r2 = r5.mCacheSpanIndices
                if (r2 == 0) goto L20
                android.util.SparseIntArray r2 = r5.mSpanIndexCache
                int r2 = findFirstKeyLessThan(r2, r6)
                if (r2 < 0) goto L20
                android.util.SparseIntArray r3 = r5.mSpanIndexCache
                int r3 = r3.get(r2)
                int r4 = r5.getSpanSize(r2)
                int r3 = r3 + r4
                goto L30
            L20:
                r2 = 0
                r3 = 0
            L22:
                if (r2 >= r6) goto L33
                int r4 = r5.getSpanSize(r2)
                int r3 = r3 + r4
                if (r3 != r7) goto L2d
                r3 = 0
                goto L30
            L2d:
                if (r3 <= r7) goto L30
                r3 = r4
            L30:
                int r2 = r2 + 1
                goto L22
            L33:
                int r0 = r0 + r3
                if (r0 > r7) goto L37
                return r3
            L37:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.GridLayoutManager.c.getSpanIndex(int, int):int");
        }

        public abstract int getSpanSize(int i);

        public void invalidateSpanGroupIndexCache() {
            this.mSpanGroupIndexCache.clear();
        }

        public void invalidateSpanIndexCache() {
            this.mSpanIndexCache.clear();
        }

        public boolean isSpanGroupIndexCacheEnabled() {
            return this.mCacheSpanGroupIndices;
        }

        public boolean isSpanIndexCacheEnabled() {
            return this.mCacheSpanIndices;
        }

        public void setSpanGroupIndexCacheEnabled(boolean z) {
            if (!z) {
                this.mSpanGroupIndexCache.clear();
            }
            this.mCacheSpanGroupIndices = z;
        }

        public void setSpanIndexCacheEnabled(boolean z) {
            if (!z) {
                this.mSpanGroupIndexCache.clear();
            }
            this.mCacheSpanIndices = z;
        }
    }

    public GridLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.I = false;
        this.J = -1;
        this.M = new SparseIntArray();
        this.N = new SparseIntArray();
        this.O = new a();
        this.P = new Rect();
        l(RecyclerView.o.a(context, attributeSet, i, i2).f1719b);
    }

    private void P() {
        int e2 = e();
        for (int i = 0; i < e2; i++) {
            b bVar = (b) d(i).getLayoutParams();
            int a2 = bVar.a();
            this.M.put(a2, bVar.f());
            this.N.put(a2, bVar.e());
        }
    }

    private void Q() {
        this.M.clear();
        this.N.clear();
    }

    private void R() {
        View[] viewArr = this.L;
        if (viewArr == null || viewArr.length != this.J) {
            this.L = new View[this.J];
        }
    }

    private void S() {
        int h;
        int q;
        if (K() == 1) {
            h = r() - p();
            q = o();
        } else {
            h = h() - n();
            q = q();
        }
        m(h - q);
    }

    private int i(RecyclerView.a0 a0Var) {
        int max;
        if (e() != 0 && a0Var.a() != 0) {
            F();
            boolean M = M();
            View b2 = b(!M, true);
            View a2 = a(!M, true);
            if (b2 != null && a2 != null) {
                int cachedSpanGroupIndex = this.O.getCachedSpanGroupIndex(l(b2), this.J);
                int cachedSpanGroupIndex2 = this.O.getCachedSpanGroupIndex(l(a2), this.J);
                int min = Math.min(cachedSpanGroupIndex, cachedSpanGroupIndex2);
                int max2 = Math.max(cachedSpanGroupIndex, cachedSpanGroupIndex2);
                int cachedSpanGroupIndex3 = this.O.getCachedSpanGroupIndex(a0Var.a() - 1, this.J) + 1;
                if (this.x) {
                    max = Math.max(0, (cachedSpanGroupIndex3 - max2) - 1);
                } else {
                    max = Math.max(0, min);
                }
                if (!M) {
                    return max;
                }
                return Math.round((max * (Math.abs(this.u.a(a2) - this.u.d(b2)) / ((this.O.getCachedSpanGroupIndex(l(a2), this.J) - this.O.getCachedSpanGroupIndex(l(b2), this.J)) + 1))) + (this.u.f() - this.u.d(b2)));
            }
        }
        return 0;
    }

    private int j(RecyclerView.a0 a0Var) {
        if (e() != 0 && a0Var.a() != 0) {
            F();
            View b2 = b(!M(), true);
            View a2 = a(!M(), true);
            if (b2 != null && a2 != null) {
                if (!M()) {
                    return this.O.getCachedSpanGroupIndex(a0Var.a() - 1, this.J) + 1;
                }
                int a3 = this.u.a(a2) - this.u.d(b2);
                int cachedSpanGroupIndex = this.O.getCachedSpanGroupIndex(l(b2), this.J);
                return (int) ((a3 / ((this.O.getCachedSpanGroupIndex(l(a2), this.J) - cachedSpanGroupIndex) + 1)) * (this.O.getCachedSpanGroupIndex(a0Var.a() - 1, this.J) + 1));
            }
        }
        return 0;
    }

    private void m(int i) {
        this.K = a(this.K, this.J, i);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.o
    public boolean D() {
        return this.D == null && !this.I;
    }

    public int O() {
        return this.J;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int a(RecyclerView.v vVar, RecyclerView.a0 a0Var) {
        if (this.s == 1) {
            return this.J;
        }
        if (a0Var.a() < 1) {
            return 0;
        }
        return a(vVar, a0Var, a0Var.a() - 1) + 1;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public void b(boolean z) {
        if (!z) {
            super.b(false);
            return;
        }
        throw new UnsupportedOperationException("GridLayoutManager does not support stack from end. Consider using reverse layout");
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.o
    public RecyclerView.p c() {
        if (this.s == 0) {
            return new b(-2, -1);
        }
        return new b(-1, -2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void d(RecyclerView recyclerView) {
        this.O.invalidateSpanIndexCache();
        this.O.invalidateSpanGroupIndexCache();
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.o
    public void e(RecyclerView.v vVar, RecyclerView.a0 a0Var) {
        if (a0Var.d()) {
            P();
        }
        super.e(vVar, a0Var);
        Q();
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.o
    public int f(RecyclerView.a0 a0Var) {
        if (this.Q) {
            return j(a0Var);
        }
        return super.f(a0Var);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.o
    public void g(RecyclerView.a0 a0Var) {
        super.g(a0Var);
        this.I = false;
    }

    public void l(int i) {
        if (i == this.J) {
            return;
        }
        this.I = true;
        if (i >= 1) {
            this.J = i;
            this.O.invalidateSpanIndexCache();
            z();
        } else {
            throw new IllegalArgumentException("Span count should be at least 1. Provided " + i);
        }
    }

    /* loaded from: classes.dex */
    public static class b extends RecyclerView.p {

        /* renamed from: e, reason: collision with root package name */
        int f1665e;

        /* renamed from: f, reason: collision with root package name */
        int f1666f;

        public b(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f1665e = -1;
            this.f1666f = 0;
        }

        public int e() {
            return this.f1665e;
        }

        public int f() {
            return this.f1666f;
        }

        public b(int i, int i2) {
            super(i, i2);
            this.f1665e = -1;
            this.f1666f = 0;
        }

        public b(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.f1665e = -1;
            this.f1666f = 0;
        }

        public b(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f1665e = -1;
            this.f1666f = 0;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int b(RecyclerView.v vVar, RecyclerView.a0 a0Var) {
        if (this.s == 0) {
            return this.J;
        }
        if (a0Var.a() < 1) {
            return 0;
        }
        return a(vVar, a0Var, a0Var.a() - 1) + 1;
    }

    int g(int i, int i2) {
        if (this.s == 1 && L()) {
            int[] iArr = this.K;
            int i3 = this.J;
            return iArr[i3 - i] - iArr[(i3 - i) - i2];
        }
        int[] iArr2 = this.K;
        return iArr2[i2 + i] - iArr2[i];
    }

    private int c(RecyclerView.v vVar, RecyclerView.a0 a0Var, int i) {
        if (!a0Var.d()) {
            return this.O.getSpanSize(i);
        }
        int i2 = this.M.get(i, -1);
        if (i2 != -1) {
            return i2;
        }
        int a2 = vVar.a(i);
        if (a2 == -1) {
            Log.w("GridLayoutManager", "Cannot find span size for pre layout position. It is not cached, not in the adapter. Pos:" + i);
            return 1;
        }
        return this.O.getSpanSize(a2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void a(RecyclerView.v vVar, RecyclerView.a0 a0Var, View view, a.f.l.f0.c cVar) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof b)) {
            super.a(view, cVar);
            return;
        }
        b bVar = (b) layoutParams;
        int a2 = a(vVar, a0Var, bVar.a());
        if (this.s == 0) {
            cVar.b(c.C0018c.a(bVar.e(), bVar.f(), a2, 1, false, false));
        } else {
            cVar.b(c.C0018c.a(a2, 1, bVar.e(), bVar.f(), false, false));
        }
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.o
    public int e(RecyclerView.a0 a0Var) {
        if (this.Q) {
            return i(a0Var);
        }
        return super.e(a0Var);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void b(RecyclerView recyclerView, int i, int i2) {
        this.O.invalidateSpanIndexCache();
        this.O.invalidateSpanGroupIndexCache();
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.o
    public int b(int i, RecyclerView.v vVar, RecyclerView.a0 a0Var) {
        S();
        R();
        return super.b(i, vVar, a0Var);
    }

    public GridLayoutManager(Context context, int i) {
        super(context);
        this.I = false;
        this.J = -1;
        this.M = new SparseIntArray();
        this.N = new SparseIntArray();
        this.O = new a();
        this.P = new Rect();
        l(i);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.o
    public int c(RecyclerView.a0 a0Var) {
        if (this.Q) {
            return j(a0Var);
        }
        return super.c(a0Var);
    }

    private void b(RecyclerView.v vVar, RecyclerView.a0 a0Var, LinearLayoutManager.a aVar, int i) {
        boolean z = i == 1;
        int b2 = b(vVar, a0Var, aVar.f1668b);
        if (z) {
            while (b2 > 0) {
                int i2 = aVar.f1668b;
                if (i2 <= 0) {
                    return;
                }
                aVar.f1668b = i2 - 1;
                b2 = b(vVar, a0Var, aVar.f1668b);
            }
            return;
        }
        int a2 = a0Var.a() - 1;
        int i3 = aVar.f1668b;
        while (i3 < a2) {
            int i4 = i3 + 1;
            int b3 = b(vVar, a0Var, i4);
            if (b3 <= b2) {
                break;
            }
            i3 = i4;
            b2 = b3;
        }
        aVar.f1668b = i3;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void a(RecyclerView recyclerView, int i, int i2) {
        this.O.invalidateSpanIndexCache();
        this.O.invalidateSpanGroupIndexCache();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void a(RecyclerView recyclerView, int i, int i2, Object obj) {
        this.O.invalidateSpanIndexCache();
        this.O.invalidateSpanGroupIndexCache();
    }

    public GridLayoutManager(Context context, int i, int i2, boolean z) {
        super(context, i2, z);
        this.I = false;
        this.J = -1;
        this.M = new SparseIntArray();
        this.N = new SparseIntArray();
        this.O = new a();
        this.P = new Rect();
        l(i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void a(RecyclerView recyclerView, int i, int i2, int i3) {
        this.O.invalidateSpanIndexCache();
        this.O.invalidateSpanGroupIndexCache();
    }

    private int b(RecyclerView.v vVar, RecyclerView.a0 a0Var, int i) {
        if (!a0Var.d()) {
            return this.O.getCachedSpanIndex(i, this.J);
        }
        int i2 = this.N.get(i, -1);
        if (i2 != -1) {
            return i2;
        }
        int a2 = vVar.a(i);
        if (a2 == -1) {
            Log.w("GridLayoutManager", "Cannot find span size for pre layout position. It is not cached, not in the adapter. Pos:" + i);
            return 0;
        }
        return this.O.getCachedSpanIndex(a2, this.J);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public RecyclerView.p a(Context context, AttributeSet attributeSet) {
        return new b(context, attributeSet);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public RecyclerView.p a(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            return new b((ViewGroup.MarginLayoutParams) layoutParams);
        }
        return new b(layoutParams);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean a(RecyclerView.p pVar) {
        return pVar instanceof b;
    }

    public void a(c cVar) {
        this.O = cVar;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.o
    public int b(RecyclerView.a0 a0Var) {
        if (this.Q) {
            return i(a0Var);
        }
        return super.b(a0Var);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void a(Rect rect, int i, int i2) {
        int a2;
        int a3;
        if (this.K == null) {
            super.a(rect, i, i2);
        }
        int o = o() + p();
        int q = q() + n();
        if (this.s == 1) {
            a3 = RecyclerView.o.a(i2, rect.height() + q, l());
            int[] iArr = this.K;
            a2 = RecyclerView.o.a(i, iArr[iArr.length - 1] + o, m());
        } else {
            a2 = RecyclerView.o.a(i, rect.width() + o, m());
            int[] iArr2 = this.K;
            a3 = RecyclerView.o.a(i2, iArr2[iArr2.length - 1] + q, l());
        }
        c(a2, a3);
    }

    static int[] a(int[] iArr, int i, int i2) {
        int i3;
        if (iArr == null || iArr.length != i + 1 || iArr[iArr.length - 1] != i2) {
            iArr = new int[i + 1];
        }
        int i4 = 0;
        iArr[0] = 0;
        int i5 = i2 / i;
        int i6 = i2 % i;
        int i7 = 0;
        for (int i8 = 1; i8 <= i; i8++) {
            i4 += i6;
            if (i4 <= 0 || i - i4 >= i6) {
                i3 = i5;
            } else {
                i3 = i5 + 1;
                i4 -= i;
            }
            i7 += i3;
            iArr[i8] = i7;
        }
        return iArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public void a(RecyclerView.v vVar, RecyclerView.a0 a0Var, LinearLayoutManager.a aVar, int i) {
        super.a(vVar, a0Var, aVar, i);
        S();
        if (a0Var.a() > 0 && !a0Var.d()) {
            b(vVar, a0Var, aVar, i);
        }
        R();
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.o
    public int a(int i, RecyclerView.v vVar, RecyclerView.a0 a0Var) {
        S();
        R();
        return super.a(i, vVar, a0Var);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
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
            if (l >= 0 && l < i3 && b(vVar, a0Var, l) == 0) {
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

    private int a(RecyclerView.v vVar, RecyclerView.a0 a0Var, int i) {
        if (!a0Var.d()) {
            return this.O.getCachedSpanGroupIndex(i, this.J);
        }
        int a2 = vVar.a(i);
        if (a2 == -1) {
            Log.w("GridLayoutManager", "Cannot find span size for pre layout position. " + i);
            return 0;
        }
        return this.O.getCachedSpanGroupIndex(a2, this.J);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    void a(RecyclerView.a0 a0Var, LinearLayoutManager.c cVar, RecyclerView.o.c cVar2) {
        int i = this.J;
        for (int i2 = 0; i2 < this.J && cVar.a(a0Var) && i > 0; i2++) {
            int i3 = cVar.f1679d;
            cVar2.a(i3, Math.max(0, cVar.g));
            i -= this.O.getSpanSize(i3);
            cVar.f1679d += cVar.f1680e;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x00a0, code lost:
    
        r22.f1673b = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00a2, code lost:
    
        return;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0 */
    /* JADX WARN: Type inference failed for: r5v1, types: [int, boolean] */
    /* JADX WARN: Type inference failed for: r5v28 */
    @Override // androidx.recyclerview.widget.LinearLayoutManager
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void a(androidx.recyclerview.widget.RecyclerView.v r19, androidx.recyclerview.widget.RecyclerView.a0 r20, androidx.recyclerview.widget.LinearLayoutManager.c r21, androidx.recyclerview.widget.LinearLayoutManager.b r22) {
        /*
            Method dump skipped, instructions count: 572
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.GridLayoutManager.a(androidx.recyclerview.widget.RecyclerView$v, androidx.recyclerview.widget.RecyclerView$a0, androidx.recyclerview.widget.LinearLayoutManager$c, androidx.recyclerview.widget.LinearLayoutManager$b):void");
    }

    private void a(View view, int i, boolean z) {
        int i2;
        int i3;
        b bVar = (b) view.getLayoutParams();
        Rect rect = bVar.f1723b;
        int i4 = rect.top + rect.bottom + ((ViewGroup.MarginLayoutParams) bVar).topMargin + ((ViewGroup.MarginLayoutParams) bVar).bottomMargin;
        int i5 = rect.left + rect.right + ((ViewGroup.MarginLayoutParams) bVar).leftMargin + ((ViewGroup.MarginLayoutParams) bVar).rightMargin;
        int g = g(bVar.f1665e, bVar.f1666f);
        if (this.s == 1) {
            i3 = RecyclerView.o.a(g, i, i5, ((ViewGroup.MarginLayoutParams) bVar).width, false);
            i2 = RecyclerView.o.a(this.u.g(), i(), i4, ((ViewGroup.MarginLayoutParams) bVar).height, true);
        } else {
            int a2 = RecyclerView.o.a(g, i, i4, ((ViewGroup.MarginLayoutParams) bVar).height, false);
            int a3 = RecyclerView.o.a(this.u.g(), s(), i5, ((ViewGroup.MarginLayoutParams) bVar).width, true);
            i2 = a2;
            i3 = a3;
        }
        a(view, i3, i2, z);
    }

    private void a(float f2, int i) {
        m(Math.max(Math.round(f2 * this.J), i));
    }

    private void a(View view, int i, int i2, boolean z) {
        boolean a2;
        RecyclerView.p pVar = (RecyclerView.p) view.getLayoutParams();
        if (z) {
            a2 = b(view, i, i2, pVar);
        } else {
            a2 = a(view, i, i2, pVar);
        }
        if (a2) {
            view.measure(i, i2);
        }
    }

    private void a(RecyclerView.v vVar, RecyclerView.a0 a0Var, int i, boolean z) {
        int i2;
        int i3;
        int i4 = 0;
        int i5 = -1;
        if (z) {
            i5 = i;
            i2 = 0;
            i3 = 1;
        } else {
            i2 = i - 1;
            i3 = -1;
        }
        while (i2 != i5) {
            View view = this.L[i2];
            b bVar = (b) view.getLayoutParams();
            bVar.f1666f = c(vVar, a0Var, l(view));
            bVar.f1665e = i4;
            i4 += bVar.f1666f;
            i2 += i3;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:69:0x00d7, code lost:
    
        if (r13 == (r2 > r8)) goto L54;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x00f7, code lost:
    
        if (r13 == (r2 > r10)) goto L50;
     */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0105  */
    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.o
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.view.View a(android.view.View r24, int r25, androidx.recyclerview.widget.RecyclerView.v r26, androidx.recyclerview.widget.RecyclerView.a0 r27) {
        /*
            Method dump skipped, instructions count: 335
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.GridLayoutManager.a(android.view.View, int, androidx.recyclerview.widget.RecyclerView$v, androidx.recyclerview.widget.RecyclerView$a0):android.view.View");
    }
}
