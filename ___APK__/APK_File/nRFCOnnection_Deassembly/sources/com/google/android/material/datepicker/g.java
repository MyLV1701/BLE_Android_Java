package com.google.android.material.datepicker;

import a.f.l.w;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.button.MaterialButton;
import java.util.Calendar;
import java.util.Iterator;
import no.nordicsemi.android.dfu.DfuBaseService;

/* loaded from: classes.dex */
public final class g<S> extends m<S> {
    static final Object m = "MONTHS_VIEW_GROUP_TAG";
    static final Object n = "NAVIGATION_PREV_TAG";
    static final Object o = "NAVIGATION_NEXT_TAG";
    static final Object p = "SELECTOR_TOGGLE_TAG";

    /* renamed from: c, reason: collision with root package name */
    private int f2470c;

    /* renamed from: d, reason: collision with root package name */
    private com.google.android.material.datepicker.d<S> f2471d;

    /* renamed from: e, reason: collision with root package name */
    private com.google.android.material.datepicker.a f2472e;

    /* renamed from: f, reason: collision with root package name */
    private com.google.android.material.datepicker.i f2473f;
    private k g;
    private com.google.android.material.datepicker.c h;
    private RecyclerView i;
    private RecyclerView j;
    private View k;
    private View l;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a implements Runnable {

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f2474b;

        a(int i) {
            this.f2474b = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            g.this.j.smoothScrollToPosition(this.f2474b);
        }
    }

    /* loaded from: classes.dex */
    class b extends a.f.l.a {
        b(g gVar) {
        }

        @Override // a.f.l.a
        public void onInitializeAccessibilityNodeInfo(View view, a.f.l.f0.c cVar) {
            super.onInitializeAccessibilityNodeInfo(view, cVar);
            cVar.a((Object) null);
        }
    }

    /* loaded from: classes.dex */
    class c extends n {
        final /* synthetic */ int I;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        c(Context context, int i, boolean z, int i2) {
            super(context, i, z);
            this.I = i2;
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager
        protected void a(RecyclerView.a0 a0Var, int[] iArr) {
            if (this.I == 0) {
                iArr[0] = g.this.j.getWidth();
                iArr[1] = g.this.j.getWidth();
            } else {
                iArr[0] = g.this.j.getHeight();
                iArr[1] = g.this.j.getHeight();
            }
        }
    }

    /* loaded from: classes.dex */
    class d implements l {
        d() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.android.material.datepicker.g.l
        public void a(long j) {
            if (g.this.f2472e.d().a(j)) {
                g.this.f2471d.b(j);
                Iterator<com.google.android.material.datepicker.l<S>> it = g.this.f2510b.iterator();
                while (it.hasNext()) {
                    it.next().a(g.this.f2471d.c());
                }
                g.this.j.getAdapter().notifyDataSetChanged();
                if (g.this.i != null) {
                    g.this.i.getAdapter().notifyDataSetChanged();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class e extends RecyclerView.n {

        /* renamed from: a, reason: collision with root package name */
        private final Calendar f2477a = o.b();

        /* renamed from: b, reason: collision with root package name */
        private final Calendar f2478b = o.b();

        e() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.n
        public void a(Canvas canvas, RecyclerView recyclerView, RecyclerView.a0 a0Var) {
            int width;
            if ((recyclerView.getAdapter() instanceof p) && (recyclerView.getLayoutManager() instanceof GridLayoutManager)) {
                p pVar = (p) recyclerView.getAdapter();
                GridLayoutManager gridLayoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
                for (a.f.k.d<Long, Long> dVar : g.this.f2471d.a()) {
                    Long l = dVar.f268a;
                    if (l != null && dVar.f269b != null) {
                        this.f2477a.setTimeInMillis(l.longValue());
                        this.f2478b.setTimeInMillis(dVar.f269b.longValue());
                        int a2 = pVar.a(this.f2477a.get(1));
                        int a3 = pVar.a(this.f2478b.get(1));
                        View c2 = gridLayoutManager.c(a2);
                        View c3 = gridLayoutManager.c(a3);
                        int O = a2 / gridLayoutManager.O();
                        int O2 = a3 / gridLayoutManager.O();
                        int i = O;
                        while (i <= O2) {
                            View c4 = gridLayoutManager.c(gridLayoutManager.O() * i);
                            if (c4 != null) {
                                int top = c4.getTop() + g.this.h.f2463d.b();
                                int bottom = c4.getBottom() - g.this.h.f2463d.a();
                                int left = i == O ? c2.getLeft() + (c2.getWidth() / 2) : 0;
                                if (i == O2) {
                                    width = c3.getLeft() + (c3.getWidth() / 2);
                                } else {
                                    width = recyclerView.getWidth();
                                }
                                canvas.drawRect(left, top, width, bottom, g.this.h.h);
                            }
                            i++;
                        }
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class f extends a.f.l.a {
        f() {
        }

        @Override // a.f.l.a
        public void onInitializeAccessibilityNodeInfo(View view, a.f.l.f0.c cVar) {
            String string;
            super.onInitializeAccessibilityNodeInfo(view, cVar);
            if (g.this.l.getVisibility() == 0) {
                string = g.this.getString(c.a.a.a.j.mtrl_picker_toggle_to_year_selection);
            } else {
                string = g.this.getString(c.a.a.a.j.mtrl_picker_toggle_to_day_selection);
            }
            cVar.d(string);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.google.android.material.datepicker.g$g, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public class C0083g extends RecyclerView.t {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ com.google.android.material.datepicker.k f2481a;

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ MaterialButton f2482b;

        C0083g(com.google.android.material.datepicker.k kVar, MaterialButton materialButton) {
            this.f2481a = kVar;
            this.f2482b = materialButton;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.t
        public void onScrollStateChanged(RecyclerView recyclerView, int i) {
            if (i == 0) {
                CharSequence text = this.f2482b.getText();
                if (Build.VERSION.SDK_INT >= 16) {
                    recyclerView.announceForAccessibility(text);
                } else {
                    recyclerView.sendAccessibilityEvent(DfuBaseService.ERROR_REMOTE_TYPE_SECURE_BUTTONLESS);
                }
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.t
        public void onScrolled(RecyclerView recyclerView, int i, int i2) {
            int J;
            if (i < 0) {
                J = g.this.e().H();
            } else {
                J = g.this.e().J();
            }
            g.this.f2473f = this.f2481a.a(J);
            this.f2482b.setText(this.f2481a.b(J));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class h implements View.OnClickListener {
        h() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            g.this.f();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class i implements View.OnClickListener {

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ com.google.android.material.datepicker.k f2485b;

        i(com.google.android.material.datepicker.k kVar) {
            this.f2485b = kVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            int H = g.this.e().H() + 1;
            if (H < g.this.j.getAdapter().getItemCount()) {
                g.this.a(this.f2485b.a(H));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class j implements View.OnClickListener {

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ com.google.android.material.datepicker.k f2487b;

        j(com.google.android.material.datepicker.k kVar) {
            this.f2487b = kVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            int J = g.this.e().J() - 1;
            if (J >= 0) {
                g.this.a(this.f2487b.a(J));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public enum k {
        DAY,
        YEAR
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface l {
        void a(long j);
    }

    private RecyclerView.n g() {
        return new e();
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            bundle = getArguments();
        }
        this.f2470c = bundle.getInt("THEME_RES_ID_KEY");
        this.f2471d = (com.google.android.material.datepicker.d) bundle.getParcelable("GRID_SELECTOR_KEY");
        this.f2472e = (com.google.android.material.datepicker.a) bundle.getParcelable("CALENDAR_CONSTRAINTS_KEY");
        this.f2473f = (com.google.android.material.datepicker.i) bundle.getParcelable("CURRENT_MONTH_KEY");
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        int i2;
        int i3;
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(getContext(), this.f2470c);
        this.h = new com.google.android.material.datepicker.c(contextThemeWrapper);
        LayoutInflater cloneInContext = layoutInflater.cloneInContext(contextThemeWrapper);
        com.google.android.material.datepicker.i h2 = this.f2472e.h();
        if (com.google.android.material.datepicker.h.a(contextThemeWrapper)) {
            i2 = c.a.a.a.h.mtrl_calendar_vertical;
            i3 = 1;
        } else {
            i2 = c.a.a.a.h.mtrl_calendar_horizontal;
            i3 = 0;
        }
        View inflate = cloneInContext.inflate(i2, viewGroup, false);
        GridView gridView = (GridView) inflate.findViewById(c.a.a.a.f.mtrl_calendar_days_of_week);
        w.a(gridView, new b(this));
        gridView.setAdapter((ListAdapter) new com.google.android.material.datepicker.f());
        gridView.setNumColumns(h2.f2496f);
        gridView.setEnabled(false);
        this.j = (RecyclerView) inflate.findViewById(c.a.a.a.f.mtrl_calendar_months);
        this.j.setLayoutManager(new c(getContext(), i3, false, i3));
        this.j.setTag(m);
        com.google.android.material.datepicker.k kVar = new com.google.android.material.datepicker.k(contextThemeWrapper, this.f2471d, this.f2472e, new d());
        this.j.setAdapter(kVar);
        int integer = contextThemeWrapper.getResources().getInteger(c.a.a.a.g.mtrl_calendar_year_selector_span);
        this.i = (RecyclerView) inflate.findViewById(c.a.a.a.f.mtrl_calendar_year_selector_frame);
        RecyclerView recyclerView = this.i;
        if (recyclerView != null) {
            recyclerView.setHasFixedSize(true);
            this.i.setLayoutManager(new GridLayoutManager((Context) contextThemeWrapper, integer, 1, false));
            this.i.setAdapter(new p(this));
            this.i.addItemDecoration(g());
        }
        if (inflate.findViewById(c.a.a.a.f.month_navigation_fragment_toggle) != null) {
            a(inflate, kVar);
        }
        if (!com.google.android.material.datepicker.h.a(contextThemeWrapper)) {
            new androidx.recyclerview.widget.o().a(this.j);
        }
        this.j.scrollToPosition(kVar.a(this.f2473f));
        return inflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("THEME_RES_ID_KEY", this.f2470c);
        bundle.putParcelable("GRID_SELECTOR_KEY", this.f2471d);
        bundle.putParcelable("CALENDAR_CONSTRAINTS_KEY", this.f2472e);
        bundle.putParcelable("CURRENT_MONTH_KEY", this.f2473f);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public com.google.android.material.datepicker.c b() {
        return this.h;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public com.google.android.material.datepicker.i c() {
        return this.f2473f;
    }

    public com.google.android.material.datepicker.d<S> d() {
        return this.f2471d;
    }

    LinearLayoutManager e() {
        return (LinearLayoutManager) this.j.getLayoutManager();
    }

    void f() {
        k kVar = this.g;
        if (kVar == k.YEAR) {
            a(k.DAY);
        } else if (kVar == k.DAY) {
            a(k.YEAR);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public com.google.android.material.datepicker.a a() {
        return this.f2472e;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(com.google.android.material.datepicker.i iVar) {
        com.google.android.material.datepicker.k kVar = (com.google.android.material.datepicker.k) this.j.getAdapter();
        int a2 = kVar.a(iVar);
        int a3 = a2 - kVar.a(this.f2473f);
        boolean z = Math.abs(a3) > 3;
        boolean z2 = a3 > 0;
        this.f2473f = iVar;
        if (z && z2) {
            this.j.scrollToPosition(a2 - 3);
            a(a2);
        } else if (z) {
            this.j.scrollToPosition(a2 + 3);
            a(a2);
        } else {
            a(a2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int a(Context context) {
        return context.getResources().getDimensionPixelSize(c.a.a.a.d.mtrl_calendar_day_height);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(k kVar) {
        this.g = kVar;
        if (kVar == k.YEAR) {
            this.i.getLayoutManager().i(((p) this.i.getAdapter()).a(this.f2473f.f2495e));
            this.k.setVisibility(0);
            this.l.setVisibility(8);
        } else if (kVar == k.DAY) {
            this.k.setVisibility(8);
            this.l.setVisibility(0);
            a(this.f2473f);
        }
    }

    private void a(View view, com.google.android.material.datepicker.k kVar) {
        MaterialButton materialButton = (MaterialButton) view.findViewById(c.a.a.a.f.month_navigation_fragment_toggle);
        materialButton.setTag(p);
        w.a(materialButton, new f());
        MaterialButton materialButton2 = (MaterialButton) view.findViewById(c.a.a.a.f.month_navigation_previous);
        materialButton2.setTag(n);
        MaterialButton materialButton3 = (MaterialButton) view.findViewById(c.a.a.a.f.month_navigation_next);
        materialButton3.setTag(o);
        this.k = view.findViewById(c.a.a.a.f.mtrl_calendar_year_selector_frame);
        this.l = view.findViewById(c.a.a.a.f.mtrl_calendar_day_selector_frame);
        a(k.DAY);
        materialButton.setText(this.f2473f.e());
        this.j.addOnScrollListener(new C0083g(kVar, materialButton));
        materialButton.setOnClickListener(new h());
        materialButton3.setOnClickListener(new i(kVar));
        materialButton2.setOnClickListener(new j(kVar));
    }

    private void a(int i2) {
        this.j.post(new a(i2));
    }
}
