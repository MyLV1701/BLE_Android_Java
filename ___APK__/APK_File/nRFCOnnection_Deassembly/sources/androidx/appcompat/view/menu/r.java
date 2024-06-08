package androidx.appcompat.view.menu;

import a.f.l.w;
import android.R;
import android.content.Context;
import android.content.res.Resources;
import android.os.Parcelable;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.appcompat.view.menu.n;
import androidx.appcompat.widget.h0;

/* loaded from: classes.dex */
final class r extends l implements PopupWindow.OnDismissListener, AdapterView.OnItemClickListener, n, View.OnKeyListener {
    private static final int w = a.a.g.abc_popup_menu_item_layout;

    /* renamed from: c, reason: collision with root package name */
    private final Context f830c;

    /* renamed from: d, reason: collision with root package name */
    private final g f831d;

    /* renamed from: e, reason: collision with root package name */
    private final f f832e;

    /* renamed from: f, reason: collision with root package name */
    private final boolean f833f;
    private final int g;
    private final int h;
    private final int i;
    final h0 j;
    private PopupWindow.OnDismissListener m;
    private View n;
    View o;
    private n.a p;
    ViewTreeObserver q;
    private boolean r;
    private boolean s;
    private int t;
    private boolean v;
    final ViewTreeObserver.OnGlobalLayoutListener k = new a();
    private final View.OnAttachStateChangeListener l = new b();
    private int u = 0;

    /* loaded from: classes.dex */
    class a implements ViewTreeObserver.OnGlobalLayoutListener {
        a() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            if (!r.this.e() || r.this.j.p()) {
                return;
            }
            View view = r.this.o;
            if (view != null && view.isShown()) {
                r.this.j.d();
            } else {
                r.this.dismiss();
            }
        }
    }

    /* loaded from: classes.dex */
    class b implements View.OnAttachStateChangeListener {
        b() {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
            ViewTreeObserver viewTreeObserver = r.this.q;
            if (viewTreeObserver != null) {
                if (!viewTreeObserver.isAlive()) {
                    r.this.q = view.getViewTreeObserver();
                }
                r rVar = r.this;
                rVar.q.removeGlobalOnLayoutListener(rVar.k);
            }
            view.removeOnAttachStateChangeListener(this);
        }
    }

    public r(Context context, g gVar, View view, int i, int i2, boolean z) {
        this.f830c = context;
        this.f831d = gVar;
        this.f833f = z;
        this.f832e = new f(gVar, LayoutInflater.from(context), this.f833f, w);
        this.h = i;
        this.i = i2;
        Resources resources = context.getResources();
        this.g = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(a.a.d.abc_config_prefDialogWidth));
        this.n = view;
        this.j = new h0(this.f830c, null, this.h, this.i);
        gVar.a(this, context);
    }

    private boolean i() {
        View view;
        if (e()) {
            return true;
        }
        if (this.r || (view = this.n) == null) {
            return false;
        }
        this.o = view;
        this.j.a((PopupWindow.OnDismissListener) this);
        this.j.a((AdapterView.OnItemClickListener) this);
        this.j.a(true);
        View view2 = this.o;
        boolean z = this.q == null;
        this.q = view2.getViewTreeObserver();
        if (z) {
            this.q.addOnGlobalLayoutListener(this.k);
        }
        view2.addOnAttachStateChangeListener(this.l);
        this.j.a(view2);
        this.j.f(this.u);
        if (!this.s) {
            this.t = l.a(this.f832e, null, this.f830c, this.g);
            this.s = true;
        }
        this.j.e(this.t);
        this.j.g(2);
        this.j.a(h());
        this.j.d();
        ListView f2 = this.j.f();
        f2.setOnKeyListener(this);
        if (this.v && this.f831d.h() != null) {
            FrameLayout frameLayout = (FrameLayout) LayoutInflater.from(this.f830c).inflate(a.a.g.abc_popup_menu_header_item_layout, (ViewGroup) f2, false);
            TextView textView = (TextView) frameLayout.findViewById(R.id.title);
            if (textView != null) {
                textView.setText(this.f831d.h());
            }
            frameLayout.setEnabled(false);
            f2.addHeaderView(frameLayout, null, false);
        }
        this.j.a((ListAdapter) this.f832e);
        this.j.d();
        return true;
    }

    @Override // androidx.appcompat.view.menu.l
    public void a(int i) {
        this.u = i;
    }

    @Override // androidx.appcompat.view.menu.n
    public void a(Parcelable parcelable) {
    }

    @Override // androidx.appcompat.view.menu.l
    public void a(g gVar) {
    }

    @Override // androidx.appcompat.view.menu.l
    public void b(boolean z) {
        this.f832e.a(z);
    }

    @Override // androidx.appcompat.view.menu.n
    public boolean b() {
        return false;
    }

    @Override // androidx.appcompat.view.menu.n
    public Parcelable c() {
        return null;
    }

    @Override // androidx.appcompat.view.menu.l
    public void c(int i) {
        this.j.a(i);
    }

    @Override // androidx.appcompat.view.menu.q
    public void d() {
        if (!i()) {
            throw new IllegalStateException("StandardMenuPopup cannot be used without an anchor");
        }
    }

    @Override // androidx.appcompat.view.menu.q
    public void dismiss() {
        if (e()) {
            this.j.dismiss();
        }
    }

    @Override // androidx.appcompat.view.menu.q
    public boolean e() {
        return !this.r && this.j.e();
    }

    @Override // androidx.appcompat.view.menu.q
    public ListView f() {
        return this.j.f();
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public void onDismiss() {
        this.r = true;
        this.f831d.close();
        ViewTreeObserver viewTreeObserver = this.q;
        if (viewTreeObserver != null) {
            if (!viewTreeObserver.isAlive()) {
                this.q = this.o.getViewTreeObserver();
            }
            this.q.removeGlobalOnLayoutListener(this.k);
            this.q = null;
        }
        this.o.removeOnAttachStateChangeListener(this.l);
        PopupWindow.OnDismissListener onDismissListener = this.m;
        if (onDismissListener != null) {
            onDismissListener.onDismiss();
        }
    }

    @Override // android.view.View.OnKeyListener
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (keyEvent.getAction() != 1 || i != 82) {
            return false;
        }
        dismiss();
        return true;
    }

    @Override // androidx.appcompat.view.menu.n
    public void a(boolean z) {
        this.s = false;
        f fVar = this.f832e;
        if (fVar != null) {
            fVar.notifyDataSetChanged();
        }
    }

    @Override // androidx.appcompat.view.menu.l
    public void b(int i) {
        this.j.c(i);
    }

    @Override // androidx.appcompat.view.menu.l
    public void c(boolean z) {
        this.v = z;
    }

    @Override // androidx.appcompat.view.menu.n
    public void a(n.a aVar) {
        this.p = aVar;
    }

    @Override // androidx.appcompat.view.menu.n
    public boolean a(s sVar) {
        if (sVar.hasVisibleItems()) {
            m mVar = new m(this.f830c, sVar, this.o, this.f833f, this.h, this.i);
            mVar.a(this.p);
            mVar.a(l.b(sVar));
            mVar.a(this.m);
            this.m = null;
            this.f831d.a(false);
            int a2 = this.j.a();
            int c2 = this.j.c();
            if ((Gravity.getAbsoluteGravity(this.u, w.q(this.n)) & 7) == 5) {
                a2 += this.n.getWidth();
            }
            if (mVar.a(a2, c2)) {
                n.a aVar = this.p;
                if (aVar == null) {
                    return true;
                }
                aVar.a(sVar);
                return true;
            }
        }
        return false;
    }

    @Override // androidx.appcompat.view.menu.n
    public void a(g gVar, boolean z) {
        if (gVar != this.f831d) {
            return;
        }
        dismiss();
        n.a aVar = this.p;
        if (aVar != null) {
            aVar.a(gVar, z);
        }
    }

    @Override // androidx.appcompat.view.menu.l
    public void a(View view) {
        this.n = view;
    }

    @Override // androidx.appcompat.view.menu.l
    public void a(PopupWindow.OnDismissListener onDismissListener) {
        this.m = onDismissListener;
    }
}
