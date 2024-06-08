package androidx.recyclerview.widget;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Scroller;
import androidx.preference.Preference;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes.dex */
public abstract class v extends RecyclerView.r {

    /* renamed from: a, reason: collision with root package name */
    RecyclerView f1924a;

    /* renamed from: b, reason: collision with root package name */
    private Scroller f1925b;

    /* renamed from: c, reason: collision with root package name */
    private final RecyclerView.t f1926c = new a();

    /* loaded from: classes.dex */
    class a extends RecyclerView.t {

        /* renamed from: a, reason: collision with root package name */
        boolean f1927a = false;

        a() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.t
        public void onScrollStateChanged(RecyclerView recyclerView, int i) {
            super.onScrollStateChanged(recyclerView, i);
            if (i == 0 && this.f1927a) {
                this.f1927a = false;
                v.this.a();
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.t
        public void onScrolled(RecyclerView recyclerView, int i, int i2) {
            if (i == 0 && i2 == 0) {
                return;
            }
            this.f1927a = true;
        }
    }

    private void b() {
        this.f1924a.removeOnScrollListener(this.f1926c);
        this.f1924a.setOnFlingListener(null);
    }

    private void c() {
        if (this.f1924a.getOnFlingListener() == null) {
            this.f1924a.addOnScrollListener(this.f1926c);
            this.f1924a.setOnFlingListener(this);
            return;
        }
        throw new IllegalStateException("An instance of OnFlingListener already set.");
    }

    public abstract int a(RecyclerView.o oVar, int i, int i2);

    @Override // androidx.recyclerview.widget.RecyclerView.r
    public boolean a(int i, int i2) {
        RecyclerView.o layoutManager = this.f1924a.getLayoutManager();
        if (layoutManager == null || this.f1924a.getAdapter() == null) {
            return false;
        }
        int minFlingVelocity = this.f1924a.getMinFlingVelocity();
        return (Math.abs(i2) > minFlingVelocity || Math.abs(i) > minFlingVelocity) && b(layoutManager, i, i2);
    }

    public abstract int[] a(RecyclerView.o oVar, View view);

    public abstract View c(RecyclerView.o oVar);

    public int[] b(int i, int i2) {
        this.f1925b.fling(0, 0, i, i2, RecyclerView.UNDEFINED_DURATION, Preference.DEFAULT_ORDER, RecyclerView.UNDEFINED_DURATION, Preference.DEFAULT_ORDER);
        return new int[]{this.f1925b.getFinalX(), this.f1925b.getFinalY()};
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class b extends n {
        b(Context context) {
            super(context);
        }

        @Override // androidx.recyclerview.widget.n, androidx.recyclerview.widget.RecyclerView.z
        protected void a(View view, RecyclerView.a0 a0Var, RecyclerView.z.a aVar) {
            v vVar = v.this;
            RecyclerView recyclerView = vVar.f1924a;
            if (recyclerView == null) {
                return;
            }
            int[] a2 = vVar.a(recyclerView.getLayoutManager(), view);
            int i = a2[0];
            int i2 = a2[1];
            int d2 = d(Math.max(Math.abs(i), Math.abs(i2)));
            if (d2 > 0) {
                aVar.a(i, i2, d2, this.j);
            }
        }

        @Override // androidx.recyclerview.widget.n
        protected float a(DisplayMetrics displayMetrics) {
            return 100.0f / displayMetrics.densityDpi;
        }
    }

    private boolean b(RecyclerView.o oVar, int i, int i2) {
        RecyclerView.z a2;
        int a3;
        if (!(oVar instanceof RecyclerView.z.b) || (a2 = a(oVar)) == null || (a3 = a(oVar, i, i2)) == -1) {
            return false;
        }
        a2.c(a3);
        oVar.b(a2);
        return true;
    }

    public void a(RecyclerView recyclerView) {
        RecyclerView recyclerView2 = this.f1924a;
        if (recyclerView2 == recyclerView) {
            return;
        }
        if (recyclerView2 != null) {
            b();
        }
        this.f1924a = recyclerView;
        if (this.f1924a != null) {
            c();
            this.f1925b = new Scroller(this.f1924a.getContext(), new DecelerateInterpolator());
            a();
        }
    }

    @Deprecated
    protected n b(RecyclerView.o oVar) {
        if (oVar instanceof RecyclerView.z.b) {
            return new b(this.f1924a.getContext());
        }
        return null;
    }

    void a() {
        RecyclerView.o layoutManager;
        View c2;
        RecyclerView recyclerView = this.f1924a;
        if (recyclerView == null || (layoutManager = recyclerView.getLayoutManager()) == null || (c2 = c(layoutManager)) == null) {
            return;
        }
        int[] a2 = a(layoutManager, c2);
        if (a2[0] == 0 && a2[1] == 0) {
            return;
        }
        this.f1924a.smoothScrollBy(a2[0], a2[1]);
    }

    protected RecyclerView.z a(RecyclerView.o oVar) {
        return b(oVar);
    }
}
