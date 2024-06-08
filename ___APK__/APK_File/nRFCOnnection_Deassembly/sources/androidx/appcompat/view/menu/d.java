package androidx.appcompat.view.menu;

import a.f.l.w;
import android.R;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build;
import android.os.Handler;
import android.os.Parcelable;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.appcompat.view.menu.n;
import androidx.appcompat.widget.g0;
import androidx.appcompat.widget.h0;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
final class d extends l implements n, View.OnKeyListener, PopupWindow.OnDismissListener {
    private static final int C = a.a.g.abc_cascading_menu_item_layout;
    private PopupWindow.OnDismissListener A;
    boolean B;

    /* renamed from: c, reason: collision with root package name */
    private final Context f768c;

    /* renamed from: d, reason: collision with root package name */
    private final int f769d;

    /* renamed from: e, reason: collision with root package name */
    private final int f770e;

    /* renamed from: f, reason: collision with root package name */
    private final int f771f;
    private final boolean g;
    final Handler h;
    private View p;
    View q;
    private boolean s;
    private boolean t;
    private int u;
    private int v;
    private boolean x;
    private n.a y;
    ViewTreeObserver z;
    private final List<g> i = new ArrayList();
    final List<C0041d> j = new ArrayList();
    final ViewTreeObserver.OnGlobalLayoutListener k = new a();
    private final View.OnAttachStateChangeListener l = new b();
    private final g0 m = new c();
    private int n = 0;
    private int o = 0;
    private boolean w = false;
    private int r = j();

    /* loaded from: classes.dex */
    class a implements ViewTreeObserver.OnGlobalLayoutListener {
        a() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            if (!d.this.e() || d.this.j.size() <= 0 || d.this.j.get(0).f779a.p()) {
                return;
            }
            View view = d.this.q;
            if (view != null && view.isShown()) {
                Iterator<C0041d> it = d.this.j.iterator();
                while (it.hasNext()) {
                    it.next().f779a.d();
                }
                return;
            }
            d.this.dismiss();
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
            ViewTreeObserver viewTreeObserver = d.this.z;
            if (viewTreeObserver != null) {
                if (!viewTreeObserver.isAlive()) {
                    d.this.z = view.getViewTreeObserver();
                }
                d dVar = d.this;
                dVar.z.removeGlobalOnLayoutListener(dVar.k);
            }
            view.removeOnAttachStateChangeListener(this);
        }
    }

    /* loaded from: classes.dex */
    class c implements g0 {

        /* loaded from: classes.dex */
        class a implements Runnable {

            /* renamed from: b, reason: collision with root package name */
            final /* synthetic */ C0041d f775b;

            /* renamed from: c, reason: collision with root package name */
            final /* synthetic */ MenuItem f776c;

            /* renamed from: d, reason: collision with root package name */
            final /* synthetic */ g f777d;

            a(C0041d c0041d, MenuItem menuItem, g gVar) {
                this.f775b = c0041d;
                this.f776c = menuItem;
                this.f777d = gVar;
            }

            @Override // java.lang.Runnable
            public void run() {
                C0041d c0041d = this.f775b;
                if (c0041d != null) {
                    d.this.B = true;
                    c0041d.f780b.a(false);
                    d.this.B = false;
                }
                if (this.f776c.isEnabled() && this.f776c.hasSubMenu()) {
                    this.f777d.a(this.f776c, 4);
                }
            }
        }

        c() {
        }

        @Override // androidx.appcompat.widget.g0
        public void a(g gVar, MenuItem menuItem) {
            d.this.h.removeCallbacksAndMessages(null);
            int size = d.this.j.size();
            int i = 0;
            while (true) {
                if (i >= size) {
                    i = -1;
                    break;
                } else if (gVar == d.this.j.get(i).f780b) {
                    break;
                } else {
                    i++;
                }
            }
            if (i == -1) {
                return;
            }
            int i2 = i + 1;
            d.this.h.postAtTime(new a(i2 < d.this.j.size() ? d.this.j.get(i2) : null, menuItem, gVar), gVar, SystemClock.uptimeMillis() + 200);
        }

        @Override // androidx.appcompat.widget.g0
        public void b(g gVar, MenuItem menuItem) {
            d.this.h.removeCallbacksAndMessages(gVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: androidx.appcompat.view.menu.d$d, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static class C0041d {

        /* renamed from: a, reason: collision with root package name */
        public final h0 f779a;

        /* renamed from: b, reason: collision with root package name */
        public final g f780b;

        /* renamed from: c, reason: collision with root package name */
        public final int f781c;

        public C0041d(h0 h0Var, g gVar, int i) {
            this.f779a = h0Var;
            this.f780b = gVar;
            this.f781c = i;
        }

        public ListView a() {
            return this.f779a.f();
        }
    }

    public d(Context context, View view, int i, int i2, boolean z) {
        this.f768c = context;
        this.p = view;
        this.f770e = i;
        this.f771f = i2;
        this.g = z;
        Resources resources = context.getResources();
        this.f769d = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(a.a.d.abc_config_prefDialogWidth));
        this.h = new Handler();
    }

    private int c(g gVar) {
        int size = this.j.size();
        for (int i = 0; i < size; i++) {
            if (gVar == this.j.get(i).f780b) {
                return i;
            }
        }
        return -1;
    }

    private h0 i() {
        h0 h0Var = new h0(this.f768c, null, this.f770e, this.f771f);
        h0Var.a(this.m);
        h0Var.a((AdapterView.OnItemClickListener) this);
        h0Var.a((PopupWindow.OnDismissListener) this);
        h0Var.a(this.p);
        h0Var.f(this.o);
        h0Var.a(true);
        h0Var.g(2);
        return h0Var;
    }

    private int j() {
        return w.q(this.p) == 1 ? 0 : 1;
    }

    @Override // androidx.appcompat.view.menu.n
    public void a(Parcelable parcelable) {
    }

    @Override // androidx.appcompat.view.menu.l
    public void a(g gVar) {
        gVar.a(this, this.f768c);
        if (e()) {
            d(gVar);
        } else {
            this.i.add(gVar);
        }
    }

    @Override // androidx.appcompat.view.menu.l
    public void b(boolean z) {
        this.w = z;
    }

    @Override // androidx.appcompat.view.menu.n
    public boolean b() {
        return false;
    }

    @Override // androidx.appcompat.view.menu.n
    public Parcelable c() {
        return null;
    }

    @Override // androidx.appcompat.view.menu.q
    public void d() {
        if (e()) {
            return;
        }
        Iterator<g> it = this.i.iterator();
        while (it.hasNext()) {
            d(it.next());
        }
        this.i.clear();
        this.q = this.p;
        if (this.q != null) {
            boolean z = this.z == null;
            this.z = this.q.getViewTreeObserver();
            if (z) {
                this.z.addOnGlobalLayoutListener(this.k);
            }
            this.q.addOnAttachStateChangeListener(this.l);
        }
    }

    @Override // androidx.appcompat.view.menu.q
    public void dismiss() {
        int size = this.j.size();
        if (size > 0) {
            C0041d[] c0041dArr = (C0041d[]) this.j.toArray(new C0041d[size]);
            for (int i = size - 1; i >= 0; i--) {
                C0041d c0041d = c0041dArr[i];
                if (c0041d.f779a.e()) {
                    c0041d.f779a.dismiss();
                }
            }
        }
    }

    @Override // androidx.appcompat.view.menu.q
    public boolean e() {
        return this.j.size() > 0 && this.j.get(0).f779a.e();
    }

    @Override // androidx.appcompat.view.menu.q
    public ListView f() {
        if (this.j.isEmpty()) {
            return null;
        }
        return this.j.get(r0.size() - 1).a();
    }

    @Override // androidx.appcompat.view.menu.l
    protected boolean g() {
        return false;
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public void onDismiss() {
        C0041d c0041d;
        int size = this.j.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                c0041d = null;
                break;
            }
            c0041d = this.j.get(i);
            if (!c0041d.f779a.e()) {
                break;
            } else {
                i++;
            }
        }
        if (c0041d != null) {
            c0041d.f780b.a(false);
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

    @Override // androidx.appcompat.view.menu.l
    public void b(int i) {
        this.s = true;
        this.u = i;
    }

    @Override // androidx.appcompat.view.menu.l
    public void c(int i) {
        this.t = true;
        this.v = i;
    }

    private MenuItem a(g gVar, g gVar2) {
        int size = gVar.size();
        for (int i = 0; i < size; i++) {
            MenuItem item = gVar.getItem(i);
            if (item.hasSubMenu() && gVar2 == item.getSubMenu()) {
                return item;
            }
        }
        return null;
    }

    @Override // androidx.appcompat.view.menu.l
    public void c(boolean z) {
        this.x = z;
    }

    private View a(C0041d c0041d, g gVar) {
        f fVar;
        int i;
        int firstVisiblePosition;
        MenuItem a2 = a(c0041d.f780b, gVar);
        if (a2 == null) {
            return null;
        }
        ListView a3 = c0041d.a();
        ListAdapter adapter = a3.getAdapter();
        int i2 = 0;
        if (adapter instanceof HeaderViewListAdapter) {
            HeaderViewListAdapter headerViewListAdapter = (HeaderViewListAdapter) adapter;
            i = headerViewListAdapter.getHeadersCount();
            fVar = (f) headerViewListAdapter.getWrappedAdapter();
        } else {
            fVar = (f) adapter;
            i = 0;
        }
        int count = fVar.getCount();
        while (true) {
            if (i2 >= count) {
                i2 = -1;
                break;
            }
            if (a2 == fVar.getItem(i2)) {
                break;
            }
            i2++;
        }
        if (i2 != -1 && (firstVisiblePosition = (i2 + i) - a3.getFirstVisiblePosition()) >= 0 && firstVisiblePosition < a3.getChildCount()) {
            return a3.getChildAt(firstVisiblePosition);
        }
        return null;
    }

    private int d(int i) {
        List<C0041d> list = this.j;
        ListView a2 = list.get(list.size() - 1).a();
        int[] iArr = new int[2];
        a2.getLocationOnScreen(iArr);
        Rect rect = new Rect();
        this.q.getWindowVisibleDisplayFrame(rect);
        return this.r == 1 ? (iArr[0] + a2.getWidth()) + i > rect.right ? 0 : 1 : iArr[0] - i < 0 ? 1 : 0;
    }

    private void d(g gVar) {
        C0041d c0041d;
        View view;
        int i;
        int i2;
        int i3;
        LayoutInflater from = LayoutInflater.from(this.f768c);
        f fVar = new f(gVar, from, this.g, C);
        if (!e() && this.w) {
            fVar.a(true);
        } else if (e()) {
            fVar.a(l.b(gVar));
        }
        int a2 = l.a(fVar, null, this.f768c, this.f769d);
        h0 i4 = i();
        i4.a((ListAdapter) fVar);
        i4.e(a2);
        i4.f(this.o);
        if (this.j.size() > 0) {
            List<C0041d> list = this.j;
            c0041d = list.get(list.size() - 1);
            view = a(c0041d, gVar);
        } else {
            c0041d = null;
            view = null;
        }
        if (view != null) {
            i4.c(false);
            i4.a((Object) null);
            int d2 = d(a2);
            boolean z = d2 == 1;
            this.r = d2;
            if (Build.VERSION.SDK_INT >= 26) {
                i4.a(view);
                i2 = 0;
                i = 0;
            } else {
                int[] iArr = new int[2];
                this.p.getLocationOnScreen(iArr);
                int[] iArr2 = new int[2];
                view.getLocationOnScreen(iArr2);
                if ((this.o & 7) == 5) {
                    iArr[0] = iArr[0] + this.p.getWidth();
                    iArr2[0] = iArr2[0] + view.getWidth();
                }
                i = iArr2[0] - iArr[0];
                i2 = iArr2[1] - iArr[1];
            }
            if ((this.o & 5) == 5) {
                if (!z) {
                    a2 = view.getWidth();
                    i3 = i - a2;
                }
                i3 = i + a2;
            } else {
                if (z) {
                    a2 = view.getWidth();
                    i3 = i + a2;
                }
                i3 = i - a2;
            }
            i4.c(i3);
            i4.b(true);
            i4.a(i2);
        } else {
            if (this.s) {
                i4.c(this.u);
            }
            if (this.t) {
                i4.a(this.v);
            }
            i4.a(h());
        }
        this.j.add(new C0041d(i4, gVar, this.r));
        i4.d();
        ListView f2 = i4.f();
        f2.setOnKeyListener(this);
        if (c0041d == null && this.x && gVar.h() != null) {
            FrameLayout frameLayout = (FrameLayout) from.inflate(a.a.g.abc_popup_menu_header_item_layout, (ViewGroup) f2, false);
            TextView textView = (TextView) frameLayout.findViewById(R.id.title);
            frameLayout.setEnabled(false);
            textView.setText(gVar.h());
            f2.addHeaderView(frameLayout, null, false);
            i4.d();
        }
    }

    @Override // androidx.appcompat.view.menu.n
    public void a(boolean z) {
        Iterator<C0041d> it = this.j.iterator();
        while (it.hasNext()) {
            l.a(it.next().a().getAdapter()).notifyDataSetChanged();
        }
    }

    @Override // androidx.appcompat.view.menu.n
    public void a(n.a aVar) {
        this.y = aVar;
    }

    @Override // androidx.appcompat.view.menu.n
    public boolean a(s sVar) {
        for (C0041d c0041d : this.j) {
            if (sVar == c0041d.f780b) {
                c0041d.a().requestFocus();
                return true;
            }
        }
        if (!sVar.hasVisibleItems()) {
            return false;
        }
        a((g) sVar);
        n.a aVar = this.y;
        if (aVar != null) {
            aVar.a(sVar);
        }
        return true;
    }

    @Override // androidx.appcompat.view.menu.n
    public void a(g gVar, boolean z) {
        int c2 = c(gVar);
        if (c2 < 0) {
            return;
        }
        int i = c2 + 1;
        if (i < this.j.size()) {
            this.j.get(i).f780b.a(false);
        }
        C0041d remove = this.j.remove(c2);
        remove.f780b.b(this);
        if (this.B) {
            remove.f779a.b((Object) null);
            remove.f779a.d(0);
        }
        remove.f779a.dismiss();
        int size = this.j.size();
        if (size > 0) {
            this.r = this.j.get(size - 1).f781c;
        } else {
            this.r = j();
        }
        if (size != 0) {
            if (z) {
                this.j.get(0).f780b.a(false);
                return;
            }
            return;
        }
        dismiss();
        n.a aVar = this.y;
        if (aVar != null) {
            aVar.a(gVar, true);
        }
        ViewTreeObserver viewTreeObserver = this.z;
        if (viewTreeObserver != null) {
            if (viewTreeObserver.isAlive()) {
                this.z.removeGlobalOnLayoutListener(this.k);
            }
            this.z = null;
        }
        this.q.removeOnAttachStateChangeListener(this.l);
        this.A.onDismiss();
    }

    @Override // androidx.appcompat.view.menu.l
    public void a(int i) {
        if (this.n != i) {
            this.n = i;
            this.o = a.f.l.d.a(i, w.q(this.p));
        }
    }

    @Override // androidx.appcompat.view.menu.l
    public void a(View view) {
        if (this.p != view) {
            this.p = view;
            this.o = a.f.l.d.a(this.n, w.q(this.p));
        }
    }

    @Override // androidx.appcompat.view.menu.l
    public void a(PopupWindow.OnDismissListener onDismissListener) {
        this.A = onDismissListener;
    }
}
