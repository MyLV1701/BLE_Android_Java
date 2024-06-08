package androidx.appcompat.app;

import a.a.o.b;
import a.f.l.a0;
import a.f.l.b0;
import a.f.l.c0;
import a.f.l.d0;
import a.f.l.w;
import android.R;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import androidx.appcompat.app.a;
import androidx.appcompat.view.menu.g;
import androidx.appcompat.widget.ActionBarContainer;
import androidx.appcompat.widget.ActionBarContextView;
import androidx.appcompat.widget.ActionBarOverlayLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.m0;
import androidx.appcompat.widget.z;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class n extends androidx.appcompat.app.a implements ActionBarOverlayLayout.d {
    private static final Interpolator B = new AccelerateInterpolator();
    private static final Interpolator C = new DecelerateInterpolator();
    final d0 A;

    /* renamed from: a, reason: collision with root package name */
    Context f733a;

    /* renamed from: b, reason: collision with root package name */
    private Context f734b;

    /* renamed from: c, reason: collision with root package name */
    ActionBarOverlayLayout f735c;

    /* renamed from: d, reason: collision with root package name */
    ActionBarContainer f736d;

    /* renamed from: e, reason: collision with root package name */
    z f737e;

    /* renamed from: f, reason: collision with root package name */
    ActionBarContextView f738f;
    View g;
    m0 h;
    private boolean i;
    d j;
    a.a.o.b k;
    b.a l;
    private boolean m;
    private ArrayList<a.b> n;
    private boolean o;
    private int p;
    boolean q;
    boolean r;
    boolean s;
    private boolean t;
    private boolean u;
    a.a.o.h v;
    private boolean w;
    boolean x;
    final b0 y;
    final b0 z;

    /* loaded from: classes.dex */
    class a extends c0 {
        a() {
        }

        @Override // a.f.l.b0
        public void b(View view) {
            View view2;
            n nVar = n.this;
            if (nVar.q && (view2 = nVar.g) != null) {
                view2.setTranslationY(0.0f);
                n.this.f736d.setTranslationY(0.0f);
            }
            n.this.f736d.setVisibility(8);
            n.this.f736d.setTransitioning(false);
            n nVar2 = n.this;
            nVar2.v = null;
            nVar2.l();
            ActionBarOverlayLayout actionBarOverlayLayout = n.this.f735c;
            if (actionBarOverlayLayout != null) {
                w.L(actionBarOverlayLayout);
            }
        }
    }

    /* loaded from: classes.dex */
    class b extends c0 {
        b() {
        }

        @Override // a.f.l.b0
        public void b(View view) {
            n nVar = n.this;
            nVar.v = null;
            nVar.f736d.requestLayout();
        }
    }

    /* loaded from: classes.dex */
    class c implements d0 {
        c() {
        }

        @Override // a.f.l.d0
        public void a(View view) {
            ((View) n.this.f736d.getParent()).invalidate();
        }
    }

    /* loaded from: classes.dex */
    public class d extends a.a.o.b implements g.a {

        /* renamed from: d, reason: collision with root package name */
        private final Context f742d;

        /* renamed from: e, reason: collision with root package name */
        private final androidx.appcompat.view.menu.g f743e;

        /* renamed from: f, reason: collision with root package name */
        private b.a f744f;
        private WeakReference<View> g;

        public d(Context context, b.a aVar) {
            this.f742d = context;
            this.f744f = aVar;
            androidx.appcompat.view.menu.g gVar = new androidx.appcompat.view.menu.g(context);
            gVar.c(1);
            this.f743e = gVar;
            this.f743e.a(this);
        }

        @Override // a.a.o.b
        public void a() {
            n nVar = n.this;
            if (nVar.j != this) {
                return;
            }
            if (!n.a(nVar.r, nVar.s, false)) {
                n nVar2 = n.this;
                nVar2.k = this;
                nVar2.l = this.f744f;
            } else {
                this.f744f.onDestroyActionMode(this);
            }
            this.f744f = null;
            n.this.g(false);
            n.this.f738f.a();
            n.this.f737e.h().sendAccessibilityEvent(32);
            n nVar3 = n.this;
            nVar3.f735c.setHideOnContentScrollEnabled(nVar3.x);
            n.this.j = null;
        }

        @Override // a.a.o.b
        public void b(CharSequence charSequence) {
            n.this.f738f.setTitle(charSequence);
        }

        @Override // a.a.o.b
        public Menu c() {
            return this.f743e;
        }

        @Override // a.a.o.b
        public MenuInflater d() {
            return new a.a.o.g(this.f742d);
        }

        @Override // a.a.o.b
        public CharSequence e() {
            return n.this.f738f.getSubtitle();
        }

        @Override // a.a.o.b
        public CharSequence g() {
            return n.this.f738f.getTitle();
        }

        @Override // a.a.o.b
        public void i() {
            if (n.this.j != this) {
                return;
            }
            this.f743e.s();
            try {
                this.f744f.onPrepareActionMode(this, this.f743e);
            } finally {
                this.f743e.r();
            }
        }

        @Override // a.a.o.b
        public boolean j() {
            return n.this.f738f.b();
        }

        public boolean k() {
            this.f743e.s();
            try {
                return this.f744f.onCreateActionMode(this, this.f743e);
            } finally {
                this.f743e.r();
            }
        }

        @Override // a.a.o.b
        public void b(int i) {
            b(n.this.f733a.getResources().getString(i));
        }

        @Override // a.a.o.b
        public View b() {
            WeakReference<View> weakReference = this.g;
            if (weakReference != null) {
                return weakReference.get();
            }
            return null;
        }

        @Override // a.a.o.b
        public void a(View view) {
            n.this.f738f.setCustomView(view);
            this.g = new WeakReference<>(view);
        }

        @Override // a.a.o.b
        public void a(CharSequence charSequence) {
            n.this.f738f.setSubtitle(charSequence);
        }

        @Override // a.a.o.b
        public void a(int i) {
            a((CharSequence) n.this.f733a.getResources().getString(i));
        }

        @Override // a.a.o.b
        public void a(boolean z) {
            super.a(z);
            n.this.f738f.setTitleOptional(z);
        }

        @Override // androidx.appcompat.view.menu.g.a
        public boolean a(androidx.appcompat.view.menu.g gVar, MenuItem menuItem) {
            b.a aVar = this.f744f;
            if (aVar != null) {
                return aVar.onActionItemClicked(this, menuItem);
            }
            return false;
        }

        @Override // androidx.appcompat.view.menu.g.a
        public void a(androidx.appcompat.view.menu.g gVar) {
            if (this.f744f == null) {
                return;
            }
            i();
            n.this.f738f.d();
        }
    }

    public n(Activity activity, boolean z) {
        new ArrayList();
        this.n = new ArrayList<>();
        this.p = 0;
        this.q = true;
        this.u = true;
        this.y = new a();
        this.z = new b();
        this.A = new c();
        View decorView = activity.getWindow().getDecorView();
        b(decorView);
        if (z) {
            return;
        }
        this.g = decorView.findViewById(R.id.content);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private z a(View view) {
        if (view instanceof z) {
            return (z) view;
        }
        if (view instanceof Toolbar) {
            return ((Toolbar) view).getWrapper();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Can't make a decor toolbar out of ");
        sb.append(view != 0 ? view.getClass().getSimpleName() : "null");
        throw new IllegalStateException(sb.toString());
    }

    static boolean a(boolean z, boolean z2, boolean z3) {
        if (z3) {
            return true;
        }
        return (z || z2) ? false : true;
    }

    private void b(View view) {
        this.f735c = (ActionBarOverlayLayout) view.findViewById(a.a.f.decor_content_parent);
        ActionBarOverlayLayout actionBarOverlayLayout = this.f735c;
        if (actionBarOverlayLayout != null) {
            actionBarOverlayLayout.setActionBarVisibilityCallback(this);
        }
        this.f737e = a(view.findViewById(a.a.f.action_bar));
        this.f738f = (ActionBarContextView) view.findViewById(a.a.f.action_context_bar);
        this.f736d = (ActionBarContainer) view.findViewById(a.a.f.action_bar_container);
        z zVar = this.f737e;
        if (zVar != null && this.f738f != null && this.f736d != null) {
            this.f733a = zVar.getContext();
            boolean z = (this.f737e.j() & 4) != 0;
            if (z) {
                this.i = true;
            }
            a.a.o.a a2 = a.a.o.a.a(this.f733a);
            k(a2.a() || z);
            l(a2.f());
            TypedArray obtainStyledAttributes = this.f733a.obtainStyledAttributes(null, a.a.j.ActionBar, a.a.a.actionBarStyle, 0);
            if (obtainStyledAttributes.getBoolean(a.a.j.ActionBar_hideOnContentScroll, false)) {
                j(true);
            }
            int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(a.a.j.ActionBar_elevation, 0);
            if (dimensionPixelSize != 0) {
                a(dimensionPixelSize);
            }
            obtainStyledAttributes.recycle();
            return;
        }
        throw new IllegalStateException(n.class.getSimpleName() + " can only be used with a compatible window decor layout");
    }

    private void l(boolean z) {
        this.o = z;
        if (!this.o) {
            this.f737e.a((m0) null);
            this.f736d.setTabContainer(this.h);
        } else {
            this.f736d.setTabContainer(null);
            this.f737e.a(this.h);
        }
        boolean z2 = m() == 2;
        m0 m0Var = this.h;
        if (m0Var != null) {
            if (z2) {
                m0Var.setVisibility(0);
                ActionBarOverlayLayout actionBarOverlayLayout = this.f735c;
                if (actionBarOverlayLayout != null) {
                    w.L(actionBarOverlayLayout);
                }
            } else {
                m0Var.setVisibility(8);
            }
        }
        this.f737e.b(!this.o && z2);
        this.f735c.setHasNonEmbeddedTabs(!this.o && z2);
    }

    private void n() {
        if (this.t) {
            this.t = false;
            ActionBarOverlayLayout actionBarOverlayLayout = this.f735c;
            if (actionBarOverlayLayout != null) {
                actionBarOverlayLayout.setShowingForActionMode(false);
            }
            m(false);
        }
    }

    private boolean o() {
        return w.F(this.f736d);
    }

    private void p() {
        if (this.t) {
            return;
        }
        this.t = true;
        ActionBarOverlayLayout actionBarOverlayLayout = this.f735c;
        if (actionBarOverlayLayout != null) {
            actionBarOverlayLayout.setShowingForActionMode(true);
        }
        m(false);
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.d
    public void c() {
    }

    @Override // androidx.appcompat.app.a
    public void c(int i) {
        this.f737e.e(i);
    }

    @Override // androidx.appcompat.app.a
    public void d(boolean z) {
        a(z ? 4 : 0, 4);
    }

    @Override // androidx.appcompat.app.a
    public void e(boolean z) {
        a(z ? 8 : 0, 8);
    }

    @Override // androidx.appcompat.app.a
    public void f(boolean z) {
        a.a.o.h hVar;
        this.w = z;
        if (z || (hVar = this.v) == null) {
            return;
        }
        hVar.a();
    }

    @Override // androidx.appcompat.app.a
    public int g() {
        return this.f737e.j();
    }

    public void h(boolean z) {
        View view;
        a.a.o.h hVar = this.v;
        if (hVar != null) {
            hVar.a();
        }
        if (this.p == 0 && (this.w || z)) {
            this.f736d.setAlpha(1.0f);
            this.f736d.setTransitioning(true);
            a.a.o.h hVar2 = new a.a.o.h();
            float f2 = -this.f736d.getHeight();
            if (z) {
                this.f736d.getLocationInWindow(new int[]{0, 0});
                f2 -= r5[1];
            }
            a0 a2 = w.a(this.f736d);
            a2.b(f2);
            a2.a(this.A);
            hVar2.a(a2);
            if (this.q && (view = this.g) != null) {
                a0 a3 = w.a(view);
                a3.b(f2);
                hVar2.a(a3);
            }
            hVar2.a(B);
            hVar2.a(250L);
            hVar2.a(this.y);
            this.v = hVar2;
            hVar2.c();
            return;
        }
        this.y.b(null);
    }

    public void i(boolean z) {
        View view;
        View view2;
        a.a.o.h hVar = this.v;
        if (hVar != null) {
            hVar.a();
        }
        this.f736d.setVisibility(0);
        if (this.p == 0 && (this.w || z)) {
            this.f736d.setTranslationY(0.0f);
            float f2 = -this.f736d.getHeight();
            if (z) {
                this.f736d.getLocationInWindow(new int[]{0, 0});
                f2 -= r5[1];
            }
            this.f736d.setTranslationY(f2);
            a.a.o.h hVar2 = new a.a.o.h();
            a0 a2 = w.a(this.f736d);
            a2.b(0.0f);
            a2.a(this.A);
            hVar2.a(a2);
            if (this.q && (view2 = this.g) != null) {
                view2.setTranslationY(f2);
                a0 a3 = w.a(this.g);
                a3.b(0.0f);
                hVar2.a(a3);
            }
            hVar2.a(C);
            hVar2.a(250L);
            hVar2.a(this.z);
            this.v = hVar2;
            hVar2.c();
        } else {
            this.f736d.setAlpha(1.0f);
            this.f736d.setTranslationY(0.0f);
            if (this.q && (view = this.g) != null) {
                view.setTranslationY(0.0f);
            }
            this.z.b(null);
        }
        ActionBarOverlayLayout actionBarOverlayLayout = this.f735c;
        if (actionBarOverlayLayout != null) {
            w.L(actionBarOverlayLayout);
        }
    }

    public void j(boolean z) {
        if (z && !this.f735c.i()) {
            throw new IllegalStateException("Action bar must be in overlay mode (Window.FEATURE_OVERLAY_ACTION_BAR) to enable hide on content scroll");
        }
        this.x = z;
        this.f735c.setHideOnContentScrollEnabled(z);
    }

    public void k(boolean z) {
        this.f737e.a(z);
    }

    public int m() {
        return this.f737e.l();
    }

    private void m(boolean z) {
        if (a(this.r, this.s, this.t)) {
            if (this.u) {
                return;
            }
            this.u = true;
            i(z);
            return;
        }
        if (this.u) {
            this.u = false;
            h(z);
        }
    }

    @Override // androidx.appcompat.app.a
    public void c(boolean z) {
        if (this.i) {
            return;
        }
        d(z);
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.d
    public void d() {
        if (this.s) {
            return;
        }
        this.s = true;
        m(true);
    }

    public void g(boolean z) {
        a0 a2;
        a0 a3;
        if (z) {
            p();
        } else {
            n();
        }
        if (!o()) {
            if (z) {
                this.f737e.a(4);
                this.f738f.setVisibility(0);
                return;
            } else {
                this.f737e.a(0);
                this.f738f.setVisibility(8);
                return;
            }
        }
        if (z) {
            a3 = this.f737e.a(4, 100L);
            a2 = this.f738f.a(0, 200L);
        } else {
            a2 = this.f737e.a(0, 200L);
            a3 = this.f738f.a(8, 100L);
        }
        a.a.o.h hVar = new a.a.o.h();
        hVar.a(a3, a2);
        hVar.c();
    }

    @Override // androidx.appcompat.app.a
    public boolean f() {
        z zVar = this.f737e;
        if (zVar == null || !zVar.i()) {
            return false;
        }
        this.f737e.collapseActionView();
        return true;
    }

    public void a(float f2) {
        w.a(this.f736d, f2);
    }

    @Override // androidx.appcompat.app.a
    public void a(Configuration configuration) {
        l(a.a.o.a.a(this.f733a).f());
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.d
    public void a(int i) {
        this.p = i;
    }

    @Override // androidx.appcompat.app.a
    public void a(CharSequence charSequence) {
        this.f737e.setWindowTitle(charSequence);
    }

    public void a(int i, int i2) {
        int j = this.f737e.j();
        if ((i2 & 4) != 0) {
            this.i = true;
        }
        this.f737e.b((i & i2) | ((i2 ^ (-1)) & j));
    }

    public n(Dialog dialog) {
        new ArrayList();
        this.n = new ArrayList<>();
        this.p = 0;
        this.q = true;
        this.u = true;
        this.y = new a();
        this.z = new b();
        this.A = new c();
        b(dialog.getWindow().getDecorView());
    }

    @Override // androidx.appcompat.app.a
    public a.a.o.b a(b.a aVar) {
        d dVar = this.j;
        if (dVar != null) {
            dVar.a();
        }
        this.f735c.setHideOnContentScrollEnabled(false);
        this.f738f.c();
        d dVar2 = new d(this.f738f.getContext(), aVar);
        if (!dVar2.k()) {
            return null;
        }
        this.j = dVar2;
        dVar2.i();
        this.f738f.a(dVar2);
        g(true);
        this.f738f.sendAccessibilityEvent(32);
        return dVar2;
    }

    void l() {
        b.a aVar = this.l;
        if (aVar != null) {
            aVar.onDestroyActionMode(this.k);
            this.k = null;
            this.l = null;
        }
    }

    @Override // androidx.appcompat.app.a
    public void b(boolean z) {
        if (z == this.m) {
            return;
        }
        this.m = z;
        int size = this.n.size();
        for (int i = 0; i < size; i++) {
            this.n.get(i).a(z);
        }
    }

    @Override // androidx.appcompat.app.a
    public Context h() {
        if (this.f734b == null) {
            TypedValue typedValue = new TypedValue();
            this.f733a.getTheme().resolveAttribute(a.a.a.actionBarWidgetTheme, typedValue, true);
            int i = typedValue.resourceId;
            if (i != 0) {
                this.f734b = new ContextThemeWrapper(this.f733a, i);
            } else {
                this.f734b = this.f733a;
            }
        }
        return this.f734b;
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.d
    public void a(boolean z) {
        this.q = z;
    }

    @Override // androidx.appcompat.app.a
    public void b(int i) {
        this.f737e.d(i);
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.d
    public void a() {
        if (this.s) {
            this.s = false;
            m(true);
        }
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.d
    public void b() {
        a.a.o.h hVar = this.v;
        if (hVar != null) {
            hVar.a();
            this.v = null;
        }
    }

    @Override // androidx.appcompat.app.a
    public void a(Drawable drawable) {
        this.f737e.a(drawable);
    }

    @Override // androidx.appcompat.app.a
    public boolean a(int i, KeyEvent keyEvent) {
        Menu c2;
        d dVar = this.j;
        if (dVar == null || (c2 = dVar.c()) == null) {
            return false;
        }
        c2.setQwertyMode(KeyCharacterMap.load(keyEvent != null ? keyEvent.getDeviceId() : -1).getKeyboardType() != 1);
        return c2.performShortcut(i, keyEvent, 0);
    }
}
