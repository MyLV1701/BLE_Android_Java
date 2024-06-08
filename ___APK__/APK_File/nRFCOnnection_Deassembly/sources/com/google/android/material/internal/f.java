package com.google.android.material.internal;

import a.f.l.e0;
import a.f.l.f0.c;
import a.f.l.w;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.view.menu.n;
import androidx.appcompat.view.menu.o;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.s;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class f implements androidx.appcompat.view.menu.n {

    /* renamed from: b, reason: collision with root package name */
    private NavigationMenuView f2583b;

    /* renamed from: c, reason: collision with root package name */
    LinearLayout f2584c;

    /* renamed from: d, reason: collision with root package name */
    private n.a f2585d;

    /* renamed from: e, reason: collision with root package name */
    androidx.appcompat.view.menu.g f2586e;

    /* renamed from: f, reason: collision with root package name */
    private int f2587f;
    c g;
    LayoutInflater h;
    int i;
    boolean j;
    ColorStateList k;
    ColorStateList l;
    Drawable m;
    int n;
    int o;
    int p;
    boolean q;
    private int s;
    private int t;
    int u;
    boolean r = true;
    private int v = -1;
    final View.OnClickListener w = new a();

    /* loaded from: classes.dex */
    class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            boolean z = true;
            f.this.c(true);
            androidx.appcompat.view.menu.j itemData = ((NavigationMenuItemView) view).getItemData();
            f fVar = f.this;
            boolean a2 = fVar.f2586e.a(itemData, fVar, 0);
            if (itemData != null && itemData.isCheckable() && a2) {
                f.this.g.a(itemData);
            } else {
                z = false;
            }
            f.this.c(false);
            if (z) {
                f.this.a(false);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class b extends l {
        public b(View view) {
            super(view);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class c extends RecyclerView.g<l> {

        /* renamed from: a, reason: collision with root package name */
        private final ArrayList<e> f2589a = new ArrayList<>();

        /* renamed from: b, reason: collision with root package name */
        private androidx.appcompat.view.menu.j f2590b;

        /* renamed from: c, reason: collision with root package name */
        private boolean f2591c;

        c() {
            e();
        }

        private void e() {
            if (this.f2591c) {
                return;
            }
            this.f2591c = true;
            this.f2589a.clear();
            this.f2589a.add(new d());
            int size = f.this.f2586e.n().size();
            int i = -1;
            boolean z = false;
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                androidx.appcompat.view.menu.j jVar = f.this.f2586e.n().get(i3);
                if (jVar.isChecked()) {
                    a(jVar);
                }
                if (jVar.isCheckable()) {
                    jVar.c(false);
                }
                if (jVar.hasSubMenu()) {
                    SubMenu subMenu = jVar.getSubMenu();
                    if (subMenu.hasVisibleItems()) {
                        if (i3 != 0) {
                            this.f2589a.add(new C0087f(f.this.u, 0));
                        }
                        this.f2589a.add(new g(jVar));
                        int size2 = this.f2589a.size();
                        int size3 = subMenu.size();
                        boolean z2 = false;
                        for (int i4 = 0; i4 < size3; i4++) {
                            androidx.appcompat.view.menu.j jVar2 = (androidx.appcompat.view.menu.j) subMenu.getItem(i4);
                            if (jVar2.isVisible()) {
                                if (!z2 && jVar2.getIcon() != null) {
                                    z2 = true;
                                }
                                if (jVar2.isCheckable()) {
                                    jVar2.c(false);
                                }
                                if (jVar.isChecked()) {
                                    a(jVar);
                                }
                                this.f2589a.add(new g(jVar2));
                            }
                        }
                        if (z2) {
                            a(size2, this.f2589a.size());
                        }
                    }
                } else {
                    int groupId = jVar.getGroupId();
                    if (groupId != i) {
                        i2 = this.f2589a.size();
                        boolean z3 = jVar.getIcon() != null;
                        if (i3 != 0) {
                            i2++;
                            ArrayList<e> arrayList = this.f2589a;
                            int i5 = f.this.u;
                            arrayList.add(new C0087f(i5, i5));
                        }
                        z = z3;
                    } else if (!z && jVar.getIcon() != null) {
                        a(i2, this.f2589a.size());
                        z = true;
                    }
                    g gVar = new g(jVar);
                    gVar.f2596b = z;
                    this.f2589a.add(gVar);
                    i = groupId;
                }
            }
            this.f2591c = false;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onBindViewHolder(l lVar, int i) {
            int itemViewType = getItemViewType(i);
            if (itemViewType != 0) {
                if (itemViewType == 1) {
                    ((TextView) lVar.itemView).setText(((g) this.f2589a.get(i)).a().getTitle());
                    return;
                } else {
                    if (itemViewType != 2) {
                        return;
                    }
                    C0087f c0087f = (C0087f) this.f2589a.get(i);
                    lVar.itemView.setPadding(0, c0087f.b(), 0, c0087f.a());
                    return;
                }
            }
            NavigationMenuItemView navigationMenuItemView = (NavigationMenuItemView) lVar.itemView;
            navigationMenuItemView.setIconTintList(f.this.l);
            f fVar = f.this;
            if (fVar.j) {
                navigationMenuItemView.setTextAppearance(fVar.i);
            }
            ColorStateList colorStateList = f.this.k;
            if (colorStateList != null) {
                navigationMenuItemView.setTextColor(colorStateList);
            }
            Drawable drawable = f.this.m;
            w.a(navigationMenuItemView, drawable != null ? drawable.getConstantState().newDrawable() : null);
            g gVar = (g) this.f2589a.get(i);
            navigationMenuItemView.setNeedsEmptyIcon(gVar.f2596b);
            navigationMenuItemView.setHorizontalPadding(f.this.n);
            navigationMenuItemView.setIconPadding(f.this.o);
            f fVar2 = f.this;
            if (fVar2.q) {
                navigationMenuItemView.setIconSize(fVar2.p);
            }
            navigationMenuItemView.setMaxLines(f.this.s);
            navigationMenuItemView.a(gVar.a(), 0);
        }

        public androidx.appcompat.view.menu.j b() {
            return this.f2590b;
        }

        int c() {
            int i = f.this.f2584c.getChildCount() == 0 ? 0 : 1;
            for (int i2 = 0; i2 < f.this.g.getItemCount(); i2++) {
                if (f.this.g.getItemViewType(i2) == 0) {
                    i++;
                }
            }
            return i;
        }

        public void d() {
            e();
            notifyDataSetChanged();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public int getItemCount() {
            return this.f2589a.size();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public long getItemId(int i) {
            return i;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public int getItemViewType(int i) {
            e eVar = this.f2589a.get(i);
            if (eVar instanceof C0087f) {
                return 2;
            }
            if (eVar instanceof d) {
                return 3;
            }
            if (eVar instanceof g) {
                return ((g) eVar).a().hasSubMenu() ? 1 : 0;
            }
            throw new RuntimeException("Unknown item type.");
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public l onCreateViewHolder(ViewGroup viewGroup, int i) {
            if (i == 0) {
                f fVar = f.this;
                return new i(fVar.h, viewGroup, fVar.w);
            }
            if (i == 1) {
                return new k(f.this.h, viewGroup);
            }
            if (i == 2) {
                return new j(f.this.h, viewGroup);
            }
            if (i != 3) {
                return null;
            }
            return new b(f.this.f2584c);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onViewRecycled(l lVar) {
            if (lVar instanceof i) {
                ((NavigationMenuItemView) lVar.itemView).d();
            }
        }

        private void a(int i, int i2) {
            while (i < i2) {
                ((g) this.f2589a.get(i)).f2596b = true;
                i++;
            }
        }

        public void a(androidx.appcompat.view.menu.j jVar) {
            if (this.f2590b == jVar || !jVar.isCheckable()) {
                return;
            }
            androidx.appcompat.view.menu.j jVar2 = this.f2590b;
            if (jVar2 != null) {
                jVar2.setChecked(false);
            }
            this.f2590b = jVar;
            jVar.setChecked(true);
        }

        public Bundle a() {
            Bundle bundle = new Bundle();
            androidx.appcompat.view.menu.j jVar = this.f2590b;
            if (jVar != null) {
                bundle.putInt("android:menu:checked", jVar.getItemId());
            }
            SparseArray<? extends Parcelable> sparseArray = new SparseArray<>();
            int size = this.f2589a.size();
            for (int i = 0; i < size; i++) {
                e eVar = this.f2589a.get(i);
                if (eVar instanceof g) {
                    androidx.appcompat.view.menu.j a2 = ((g) eVar).a();
                    View actionView = a2 != null ? a2.getActionView() : null;
                    if (actionView != null) {
                        com.google.android.material.internal.h hVar = new com.google.android.material.internal.h();
                        actionView.saveHierarchyState(hVar);
                        sparseArray.put(a2.getItemId(), hVar);
                    }
                }
            }
            bundle.putSparseParcelableArray("android:menu:action_views", sparseArray);
            return bundle;
        }

        public void a(Bundle bundle) {
            androidx.appcompat.view.menu.j a2;
            View actionView;
            com.google.android.material.internal.h hVar;
            androidx.appcompat.view.menu.j a3;
            int i = bundle.getInt("android:menu:checked", 0);
            if (i != 0) {
                this.f2591c = true;
                int size = this.f2589a.size();
                int i2 = 0;
                while (true) {
                    if (i2 >= size) {
                        break;
                    }
                    e eVar = this.f2589a.get(i2);
                    if ((eVar instanceof g) && (a3 = ((g) eVar).a()) != null && a3.getItemId() == i) {
                        a(a3);
                        break;
                    }
                    i2++;
                }
                this.f2591c = false;
                e();
            }
            SparseArray sparseParcelableArray = bundle.getSparseParcelableArray("android:menu:action_views");
            if (sparseParcelableArray != null) {
                int size2 = this.f2589a.size();
                for (int i3 = 0; i3 < size2; i3++) {
                    e eVar2 = this.f2589a.get(i3);
                    if ((eVar2 instanceof g) && (a2 = ((g) eVar2).a()) != null && (actionView = a2.getActionView()) != null && (hVar = (com.google.android.material.internal.h) sparseParcelableArray.get(a2.getItemId())) != null) {
                        actionView.restoreHierarchyState(hVar);
                    }
                }
            }
        }

        public void a(boolean z) {
            this.f2591c = z;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class d implements e {
        d() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public interface e {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.google.android.material.internal.f$f, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static class C0087f implements e {

        /* renamed from: a, reason: collision with root package name */
        private final int f2593a;

        /* renamed from: b, reason: collision with root package name */
        private final int f2594b;

        public C0087f(int i, int i2) {
            this.f2593a = i;
            this.f2594b = i2;
        }

        public int a() {
            return this.f2594b;
        }

        public int b() {
            return this.f2593a;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class g implements e {

        /* renamed from: a, reason: collision with root package name */
        private final androidx.appcompat.view.menu.j f2595a;

        /* renamed from: b, reason: collision with root package name */
        boolean f2596b;

        g(androidx.appcompat.view.menu.j jVar) {
            this.f2595a = jVar;
        }

        public androidx.appcompat.view.menu.j a() {
            return this.f2595a;
        }
    }

    /* loaded from: classes.dex */
    private class h extends s {
        h(RecyclerView recyclerView) {
            super(recyclerView);
        }

        @Override // androidx.recyclerview.widget.s, a.f.l.a
        public void onInitializeAccessibilityNodeInfo(View view, a.f.l.f0.c cVar) {
            super.onInitializeAccessibilityNodeInfo(view, cVar);
            cVar.a(c.b.a(f.this.g.c(), 0, false));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class i extends l {
        public i(LayoutInflater layoutInflater, ViewGroup viewGroup, View.OnClickListener onClickListener) {
            super(layoutInflater.inflate(c.a.a.a.h.design_navigation_item, viewGroup, false));
            this.itemView.setOnClickListener(onClickListener);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class j extends l {
        public j(LayoutInflater layoutInflater, ViewGroup viewGroup) {
            super(layoutInflater.inflate(c.a.a.a.h.design_navigation_item_separator, viewGroup, false));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class k extends l {
        public k(LayoutInflater layoutInflater, ViewGroup viewGroup) {
            super(layoutInflater.inflate(c.a.a.a.h.design_navigation_item_subheader, viewGroup, false));
        }
    }

    /* loaded from: classes.dex */
    private static abstract class l extends RecyclerView.d0 {
        public l(View view) {
            super(view);
        }
    }

    private void l() {
        int i2 = (this.f2584c.getChildCount() == 0 && this.r) ? this.t : 0;
        NavigationMenuView navigationMenuView = this.f2583b;
        navigationMenuView.setPadding(0, i2, 0, navigationMenuView.getPaddingBottom());
    }

    @Override // androidx.appcompat.view.menu.n
    public boolean a(androidx.appcompat.view.menu.g gVar, androidx.appcompat.view.menu.j jVar) {
        return false;
    }

    @Override // androidx.appcompat.view.menu.n
    public boolean a(androidx.appcompat.view.menu.s sVar) {
        return false;
    }

    public void b(int i2) {
        this.f2587f = i2;
    }

    @Override // androidx.appcompat.view.menu.n
    public boolean b() {
        return false;
    }

    @Override // androidx.appcompat.view.menu.n
    public boolean b(androidx.appcompat.view.menu.g gVar, androidx.appcompat.view.menu.j jVar) {
        return false;
    }

    @Override // androidx.appcompat.view.menu.n
    public Parcelable c() {
        Bundle bundle = new Bundle();
        if (this.f2583b != null) {
            SparseArray<Parcelable> sparseArray = new SparseArray<>();
            this.f2583b.saveHierarchyState(sparseArray);
            bundle.putSparseParcelableArray("android:menu:list", sparseArray);
        }
        c cVar = this.g;
        if (cVar != null) {
            bundle.putBundle("android:menu:adapter", cVar.a());
        }
        if (this.f2584c != null) {
            SparseArray<? extends Parcelable> sparseArray2 = new SparseArray<>();
            this.f2584c.saveHierarchyState(sparseArray2);
            bundle.putSparseParcelableArray("android:menu:header", sparseArray2);
        }
        return bundle;
    }

    public androidx.appcompat.view.menu.j d() {
        return this.g.b();
    }

    public int e() {
        return this.f2584c.getChildCount();
    }

    public Drawable f() {
        return this.m;
    }

    public void g(int i2) {
        this.i = i2;
        this.j = true;
        a(false);
    }

    public int h() {
        return this.o;
    }

    public int i() {
        return this.s;
    }

    public ColorStateList j() {
        return this.k;
    }

    public ColorStateList k() {
        return this.l;
    }

    @Override // androidx.appcompat.view.menu.n
    public void a(Context context, androidx.appcompat.view.menu.g gVar) {
        this.h = LayoutInflater.from(context);
        this.f2586e = gVar;
        this.u = context.getResources().getDimensionPixelOffset(c.a.a.a.d.design_navigation_separator_vertical_padding);
    }

    public void b(ColorStateList colorStateList) {
        this.k = colorStateList;
        a(false);
    }

    public void d(int i2) {
        this.o = i2;
        a(false);
    }

    public void e(int i2) {
        if (this.p != i2) {
            this.p = i2;
            this.q = true;
            a(false);
        }
    }

    public void f(int i2) {
        this.s = i2;
        a(false);
    }

    public void h(int i2) {
        this.v = i2;
        NavigationMenuView navigationMenuView = this.f2583b;
        if (navigationMenuView != null) {
            navigationMenuView.setOverScrollMode(i2);
        }
    }

    public void b(boolean z) {
        if (this.r != z) {
            this.r = z;
            l();
        }
    }

    public int g() {
        return this.n;
    }

    public o a(ViewGroup viewGroup) {
        if (this.f2583b == null) {
            this.f2583b = (NavigationMenuView) this.h.inflate(c.a.a.a.h.design_navigation_menu, viewGroup, false);
            NavigationMenuView navigationMenuView = this.f2583b;
            navigationMenuView.setAccessibilityDelegateCompat(new h(navigationMenuView));
            if (this.g == null) {
                this.g = new c();
            }
            int i2 = this.v;
            if (i2 != -1) {
                this.f2583b.setOverScrollMode(i2);
            }
            this.f2584c = (LinearLayout) this.h.inflate(c.a.a.a.h.design_navigation_item_header, (ViewGroup) this.f2583b, false);
            this.f2583b.setAdapter(this.g);
        }
        return this.f2583b;
    }

    public void c(int i2) {
        this.n = i2;
        a(false);
    }

    public void c(boolean z) {
        c cVar = this.g;
        if (cVar != null) {
            cVar.a(z);
        }
    }

    @Override // androidx.appcompat.view.menu.n
    public void a(boolean z) {
        c cVar = this.g;
        if (cVar != null) {
            cVar.d();
        }
    }

    @Override // androidx.appcompat.view.menu.n
    public void a(androidx.appcompat.view.menu.g gVar, boolean z) {
        n.a aVar = this.f2585d;
        if (aVar != null) {
            aVar.a(gVar, z);
        }
    }

    @Override // androidx.appcompat.view.menu.n
    public int a() {
        return this.f2587f;
    }

    @Override // androidx.appcompat.view.menu.n
    public void a(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            SparseArray<Parcelable> sparseParcelableArray = bundle.getSparseParcelableArray("android:menu:list");
            if (sparseParcelableArray != null) {
                this.f2583b.restoreHierarchyState(sparseParcelableArray);
            }
            Bundle bundle2 = bundle.getBundle("android:menu:adapter");
            if (bundle2 != null) {
                this.g.a(bundle2);
            }
            SparseArray sparseParcelableArray2 = bundle.getSparseParcelableArray("android:menu:header");
            if (sparseParcelableArray2 != null) {
                this.f2584c.restoreHierarchyState(sparseParcelableArray2);
            }
        }
    }

    public void a(androidx.appcompat.view.menu.j jVar) {
        this.g.a(jVar);
    }

    public View a(int i2) {
        View inflate = this.h.inflate(i2, (ViewGroup) this.f2584c, false);
        a(inflate);
        return inflate;
    }

    public void a(View view) {
        this.f2584c.addView(view);
        NavigationMenuView navigationMenuView = this.f2583b;
        navigationMenuView.setPadding(0, 0, 0, navigationMenuView.getPaddingBottom());
    }

    public void a(ColorStateList colorStateList) {
        this.l = colorStateList;
        a(false);
    }

    public void a(Drawable drawable) {
        this.m = drawable;
        a(false);
    }

    public void a(e0 e0Var) {
        int e2 = e0Var.e();
        if (this.t != e2) {
            this.t = e2;
            l();
        }
        NavigationMenuView navigationMenuView = this.f2583b;
        navigationMenuView.setPadding(0, navigationMenuView.getPaddingTop(), 0, e0Var.b());
        w.a(this.f2584c, e0Var);
    }
}
