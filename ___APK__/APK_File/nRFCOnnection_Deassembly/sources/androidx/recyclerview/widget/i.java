package androidx.recyclerview.widget;

import android.annotation.SuppressLint;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class i implements Runnable {

    /* renamed from: f, reason: collision with root package name */
    static final ThreadLocal<i> f1872f = new ThreadLocal<>();
    static Comparator<c> g = new a();

    /* renamed from: c, reason: collision with root package name */
    long f1874c;

    /* renamed from: d, reason: collision with root package name */
    long f1875d;

    /* renamed from: b, reason: collision with root package name */
    ArrayList<RecyclerView> f1873b = new ArrayList<>();

    /* renamed from: e, reason: collision with root package name */
    private ArrayList<c> f1876e = new ArrayList<>();

    /* loaded from: classes.dex */
    static class a implements Comparator<c> {
        a() {
        }

        @Override // java.util.Comparator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(c cVar, c cVar2) {
            if ((cVar.f1884d == null) != (cVar2.f1884d == null)) {
                return cVar.f1884d == null ? 1 : -1;
            }
            boolean z = cVar.f1881a;
            if (z != cVar2.f1881a) {
                return z ? -1 : 1;
            }
            int i = cVar2.f1882b - cVar.f1882b;
            if (i != 0) {
                return i;
            }
            int i2 = cVar.f1883c - cVar2.f1883c;
            if (i2 != 0) {
                return i2;
            }
            return 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class c {

        /* renamed from: a, reason: collision with root package name */
        public boolean f1881a;

        /* renamed from: b, reason: collision with root package name */
        public int f1882b;

        /* renamed from: c, reason: collision with root package name */
        public int f1883c;

        /* renamed from: d, reason: collision with root package name */
        public RecyclerView f1884d;

        /* renamed from: e, reason: collision with root package name */
        public int f1885e;

        c() {
        }

        public void a() {
            this.f1881a = false;
            this.f1882b = 0;
            this.f1883c = 0;
            this.f1884d = null;
            this.f1885e = 0;
        }
    }

    public void a(RecyclerView recyclerView) {
        this.f1873b.add(recyclerView);
    }

    public void b(RecyclerView recyclerView) {
        this.f1873b.remove(recyclerView);
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            a.f.h.c.a("RV Prefetch");
            if (!this.f1873b.isEmpty()) {
                int size = this.f1873b.size();
                long j = 0;
                for (int i = 0; i < size; i++) {
                    RecyclerView recyclerView = this.f1873b.get(i);
                    if (recyclerView.getWindowVisibility() == 0) {
                        j = Math.max(recyclerView.getDrawingTime(), j);
                    }
                }
                if (j != 0) {
                    a(TimeUnit.MILLISECONDS.toNanos(j) + this.f1875d);
                }
            }
        } finally {
            this.f1874c = 0L;
            a.f.h.c.a();
        }
    }

    private void b(long j) {
        for (int i = 0; i < this.f1876e.size(); i++) {
            c cVar = this.f1876e.get(i);
            if (cVar.f1884d == null) {
                return;
            }
            a(cVar, j);
            cVar.a();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(RecyclerView recyclerView, int i, int i2) {
        if (recyclerView.isAttachedToWindow() && this.f1874c == 0) {
            this.f1874c = recyclerView.getNanoTime();
            recyclerView.post(this);
        }
        recyclerView.mPrefetchRegistry.b(i, i2);
    }

    private void a() {
        c cVar;
        int size = this.f1873b.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            RecyclerView recyclerView = this.f1873b.get(i2);
            if (recyclerView.getWindowVisibility() == 0) {
                recyclerView.mPrefetchRegistry.a(recyclerView, false);
                i += recyclerView.mPrefetchRegistry.f1880d;
            }
        }
        this.f1876e.ensureCapacity(i);
        int i3 = 0;
        for (int i4 = 0; i4 < size; i4++) {
            RecyclerView recyclerView2 = this.f1873b.get(i4);
            if (recyclerView2.getWindowVisibility() == 0) {
                b bVar = recyclerView2.mPrefetchRegistry;
                int abs = Math.abs(bVar.f1877a) + Math.abs(bVar.f1878b);
                int i5 = i3;
                for (int i6 = 0; i6 < bVar.f1880d * 2; i6 += 2) {
                    if (i5 >= this.f1876e.size()) {
                        cVar = new c();
                        this.f1876e.add(cVar);
                    } else {
                        cVar = this.f1876e.get(i5);
                    }
                    int i7 = bVar.f1879c[i6 + 1];
                    cVar.f1881a = i7 <= abs;
                    cVar.f1882b = abs;
                    cVar.f1883c = i7;
                    cVar.f1884d = recyclerView2;
                    cVar.f1885e = bVar.f1879c[i6];
                    i5++;
                }
                i3 = i5;
            }
        }
        Collections.sort(this.f1876e, g);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @SuppressLint({"VisibleForTests"})
    /* loaded from: classes.dex */
    public static class b implements RecyclerView.o.c {

        /* renamed from: a, reason: collision with root package name */
        int f1877a;

        /* renamed from: b, reason: collision with root package name */
        int f1878b;

        /* renamed from: c, reason: collision with root package name */
        int[] f1879c;

        /* renamed from: d, reason: collision with root package name */
        int f1880d;

        void a(RecyclerView recyclerView, boolean z) {
            this.f1880d = 0;
            int[] iArr = this.f1879c;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
            RecyclerView.o oVar = recyclerView.mLayout;
            if (recyclerView.mAdapter == null || oVar == null || !oVar.w()) {
                return;
            }
            if (z) {
                if (!recyclerView.mAdapterHelper.c()) {
                    oVar.a(recyclerView.mAdapter.getItemCount(), this);
                }
            } else if (!recyclerView.hasPendingAdapterUpdates()) {
                oVar.a(this.f1877a, this.f1878b, recyclerView.mState, this);
            }
            int i = this.f1880d;
            if (i > oVar.m) {
                oVar.m = i;
                oVar.n = z;
                recyclerView.mRecycler.j();
            }
        }

        void b(int i, int i2) {
            this.f1877a = i;
            this.f1878b = i2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.o.c
        public void a(int i, int i2) {
            if (i < 0) {
                throw new IllegalArgumentException("Layout positions must be non-negative");
            }
            if (i2 >= 0) {
                int i3 = this.f1880d * 2;
                int[] iArr = this.f1879c;
                if (iArr == null) {
                    this.f1879c = new int[4];
                    Arrays.fill(this.f1879c, -1);
                } else if (i3 >= iArr.length) {
                    this.f1879c = new int[i3 * 2];
                    System.arraycopy(iArr, 0, this.f1879c, 0, iArr.length);
                }
                int[] iArr2 = this.f1879c;
                iArr2[i3] = i;
                iArr2[i3 + 1] = i2;
                this.f1880d++;
                return;
            }
            throw new IllegalArgumentException("Pixel distance must be non-negative");
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public boolean a(int i) {
            if (this.f1879c != null) {
                int i2 = this.f1880d * 2;
                for (int i3 = 0; i3 < i2; i3 += 2) {
                    if (this.f1879c[i3] == i) {
                        return true;
                    }
                }
            }
            return false;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void a() {
            int[] iArr = this.f1879c;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
            this.f1880d = 0;
        }
    }

    static boolean a(RecyclerView recyclerView, int i) {
        int b2 = recyclerView.mChildHelper.b();
        for (int i2 = 0; i2 < b2; i2++) {
            RecyclerView.d0 childViewHolderInt = RecyclerView.getChildViewHolderInt(recyclerView.mChildHelper.d(i2));
            if (childViewHolderInt.mPosition == i && !childViewHolderInt.isInvalid()) {
                return true;
            }
        }
        return false;
    }

    private RecyclerView.d0 a(RecyclerView recyclerView, int i, long j) {
        if (a(recyclerView, i)) {
            return null;
        }
        RecyclerView.v vVar = recyclerView.mRecycler;
        try {
            recyclerView.onEnterLayoutOrScroll();
            RecyclerView.d0 a2 = vVar.a(i, false, j);
            if (a2 != null) {
                if (a2.isBound() && !a2.isInvalid()) {
                    vVar.b(a2.itemView);
                } else {
                    vVar.a(a2, false);
                }
            }
            return a2;
        } finally {
            recyclerView.onExitLayoutOrScroll(false);
        }
    }

    private void a(RecyclerView recyclerView, long j) {
        if (recyclerView == null) {
            return;
        }
        if (recyclerView.mDataSetHasChangedAfterLayout && recyclerView.mChildHelper.b() != 0) {
            recyclerView.removeAndRecycleViews();
        }
        b bVar = recyclerView.mPrefetchRegistry;
        bVar.a(recyclerView, true);
        if (bVar.f1880d != 0) {
            try {
                a.f.h.c.a("RV Nested Prefetch");
                recyclerView.mState.a(recyclerView.mAdapter);
                for (int i = 0; i < bVar.f1880d * 2; i += 2) {
                    a(recyclerView, bVar.f1879c[i], j);
                }
            } finally {
                a.f.h.c.a();
            }
        }
    }

    private void a(c cVar, long j) {
        RecyclerView.d0 a2 = a(cVar.f1884d, cVar.f1885e, cVar.f1881a ? Long.MAX_VALUE : j);
        if (a2 == null || a2.mNestedRecyclerView == null || !a2.isBound() || a2.isInvalid()) {
            return;
        }
        a(a2.mNestedRecyclerView.get(), j);
    }

    void a(long j) {
        a();
        b(j);
    }
}
