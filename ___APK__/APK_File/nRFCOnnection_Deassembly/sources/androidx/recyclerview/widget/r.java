package androidx.recyclerview.widget;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes.dex */
public abstract class r {

    /* renamed from: a, reason: collision with root package name */
    protected final RecyclerView.o f1917a;

    /* renamed from: b, reason: collision with root package name */
    private int f1918b;

    /* renamed from: c, reason: collision with root package name */
    final Rect f1919c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class a extends r {
        a(RecyclerView.o oVar) {
            super(oVar, null);
        }

        @Override // androidx.recyclerview.widget.r
        public int a() {
            return this.f1917a.r();
        }

        @Override // androidx.recyclerview.widget.r
        public int b() {
            return this.f1917a.r() - this.f1917a.p();
        }

        @Override // androidx.recyclerview.widget.r
        public int c(View view) {
            RecyclerView.p pVar = (RecyclerView.p) view.getLayoutParams();
            return this.f1917a.g(view) + ((ViewGroup.MarginLayoutParams) pVar).topMargin + ((ViewGroup.MarginLayoutParams) pVar).bottomMargin;
        }

        @Override // androidx.recyclerview.widget.r
        public int d(View view) {
            return this.f1917a.f(view) - ((ViewGroup.MarginLayoutParams) ((RecyclerView.p) view.getLayoutParams())).leftMargin;
        }

        @Override // androidx.recyclerview.widget.r
        public int e(View view) {
            this.f1917a.a(view, true, this.f1919c);
            return this.f1919c.right;
        }

        @Override // androidx.recyclerview.widget.r
        public int f() {
            return this.f1917a.o();
        }

        @Override // androidx.recyclerview.widget.r
        public int g() {
            return (this.f1917a.r() - this.f1917a.o()) - this.f1917a.p();
        }

        @Override // androidx.recyclerview.widget.r
        public void a(int i) {
            this.f1917a.e(i);
        }

        @Override // androidx.recyclerview.widget.r
        public int b(View view) {
            RecyclerView.p pVar = (RecyclerView.p) view.getLayoutParams();
            return this.f1917a.h(view) + ((ViewGroup.MarginLayoutParams) pVar).leftMargin + ((ViewGroup.MarginLayoutParams) pVar).rightMargin;
        }

        @Override // androidx.recyclerview.widget.r
        public int f(View view) {
            this.f1917a.a(view, true, this.f1919c);
            return this.f1919c.left;
        }

        @Override // androidx.recyclerview.widget.r
        public int a(View view) {
            return this.f1917a.i(view) + ((ViewGroup.MarginLayoutParams) ((RecyclerView.p) view.getLayoutParams())).rightMargin;
        }

        @Override // androidx.recyclerview.widget.r
        public int c() {
            return this.f1917a.p();
        }

        @Override // androidx.recyclerview.widget.r
        public int d() {
            return this.f1917a.s();
        }

        @Override // androidx.recyclerview.widget.r
        public int e() {
            return this.f1917a.i();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class b extends r {
        b(RecyclerView.o oVar) {
            super(oVar, null);
        }

        @Override // androidx.recyclerview.widget.r
        public int a() {
            return this.f1917a.h();
        }

        @Override // androidx.recyclerview.widget.r
        public int b() {
            return this.f1917a.h() - this.f1917a.n();
        }

        @Override // androidx.recyclerview.widget.r
        public int c(View view) {
            RecyclerView.p pVar = (RecyclerView.p) view.getLayoutParams();
            return this.f1917a.h(view) + ((ViewGroup.MarginLayoutParams) pVar).leftMargin + ((ViewGroup.MarginLayoutParams) pVar).rightMargin;
        }

        @Override // androidx.recyclerview.widget.r
        public int d(View view) {
            return this.f1917a.j(view) - ((ViewGroup.MarginLayoutParams) ((RecyclerView.p) view.getLayoutParams())).topMargin;
        }

        @Override // androidx.recyclerview.widget.r
        public int e(View view) {
            this.f1917a.a(view, true, this.f1919c);
            return this.f1919c.bottom;
        }

        @Override // androidx.recyclerview.widget.r
        public int f() {
            return this.f1917a.q();
        }

        @Override // androidx.recyclerview.widget.r
        public int g() {
            return (this.f1917a.h() - this.f1917a.q()) - this.f1917a.n();
        }

        @Override // androidx.recyclerview.widget.r
        public void a(int i) {
            this.f1917a.f(i);
        }

        @Override // androidx.recyclerview.widget.r
        public int b(View view) {
            RecyclerView.p pVar = (RecyclerView.p) view.getLayoutParams();
            return this.f1917a.g(view) + ((ViewGroup.MarginLayoutParams) pVar).topMargin + ((ViewGroup.MarginLayoutParams) pVar).bottomMargin;
        }

        @Override // androidx.recyclerview.widget.r
        public int f(View view) {
            this.f1917a.a(view, true, this.f1919c);
            return this.f1919c.top;
        }

        @Override // androidx.recyclerview.widget.r
        public int a(View view) {
            return this.f1917a.e(view) + ((ViewGroup.MarginLayoutParams) ((RecyclerView.p) view.getLayoutParams())).bottomMargin;
        }

        @Override // androidx.recyclerview.widget.r
        public int c() {
            return this.f1917a.n();
        }

        @Override // androidx.recyclerview.widget.r
        public int d() {
            return this.f1917a.i();
        }

        @Override // androidx.recyclerview.widget.r
        public int e() {
            return this.f1917a.s();
        }
    }

    /* synthetic */ r(RecyclerView.o oVar, a aVar) {
        this(oVar);
    }

    public static r a(RecyclerView.o oVar, int i) {
        if (i == 0) {
            return a(oVar);
        }
        if (i == 1) {
            return b(oVar);
        }
        throw new IllegalArgumentException("invalid orientation");
    }

    public static r b(RecyclerView.o oVar) {
        return new b(oVar);
    }

    public abstract int a();

    public abstract int a(View view);

    public abstract void a(int i);

    public abstract int b();

    public abstract int b(View view);

    public abstract int c();

    public abstract int c(View view);

    public abstract int d();

    public abstract int d(View view);

    public abstract int e();

    public abstract int e(View view);

    public abstract int f();

    public abstract int f(View view);

    public abstract int g();

    public int h() {
        if (Integer.MIN_VALUE == this.f1918b) {
            return 0;
        }
        return g() - this.f1918b;
    }

    public void i() {
        this.f1918b = g();
    }

    private r(RecyclerView.o oVar) {
        this.f1918b = RecyclerView.UNDEFINED_DURATION;
        this.f1919c = new Rect();
        this.f1917a = oVar;
    }

    public static r a(RecyclerView.o oVar) {
        return new a(oVar);
    }
}
