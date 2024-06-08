package androidx.appcompat.view.menu;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import androidx.appcompat.view.menu.n;
import androidx.appcompat.view.menu.o;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class e implements n, AdapterView.OnItemClickListener {

    /* renamed from: b, reason: collision with root package name */
    Context f782b;

    /* renamed from: c, reason: collision with root package name */
    LayoutInflater f783c;

    /* renamed from: d, reason: collision with root package name */
    g f784d;

    /* renamed from: e, reason: collision with root package name */
    ExpandedMenuView f785e;

    /* renamed from: f, reason: collision with root package name */
    int f786f;
    int g;
    int h;
    private n.a i;
    a j;
    private int k;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class a extends BaseAdapter {

        /* renamed from: b, reason: collision with root package name */
        private int f787b = -1;

        public a() {
            a();
        }

        void a() {
            j f2 = e.this.f784d.f();
            if (f2 != null) {
                ArrayList<j> j = e.this.f784d.j();
                int size = j.size();
                for (int i = 0; i < size; i++) {
                    if (j.get(i) == f2) {
                        this.f787b = i;
                        return;
                    }
                }
            }
            this.f787b = -1;
        }

        @Override // android.widget.Adapter
        public int getCount() {
            int size = e.this.f784d.j().size() - e.this.f786f;
            return this.f787b < 0 ? size : size - 1;
        }

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                e eVar = e.this;
                view = eVar.f783c.inflate(eVar.h, viewGroup, false);
            }
            ((o.a) view).a(getItem(i), 0);
            return view;
        }

        @Override // android.widget.BaseAdapter
        public void notifyDataSetChanged() {
            a();
            super.notifyDataSetChanged();
        }

        @Override // android.widget.Adapter
        public j getItem(int i) {
            ArrayList<j> j = e.this.f784d.j();
            int i2 = i + e.this.f786f;
            int i3 = this.f787b;
            if (i3 >= 0 && i2 >= i3) {
                i2++;
            }
            return j.get(i2);
        }
    }

    public e(Context context, int i) {
        this(i, 0);
        this.f782b = context;
        this.f783c = LayoutInflater.from(this.f782b);
    }

    @Override // androidx.appcompat.view.menu.n
    public void a(Context context, g gVar) {
        int i = this.g;
        if (i != 0) {
            this.f782b = new ContextThemeWrapper(context, i);
            this.f783c = LayoutInflater.from(this.f782b);
        } else if (this.f782b != null) {
            this.f782b = context;
            if (this.f783c == null) {
                this.f783c = LayoutInflater.from(this.f782b);
            }
        }
        this.f784d = gVar;
        a aVar = this.j;
        if (aVar != null) {
            aVar.notifyDataSetChanged();
        }
    }

    @Override // androidx.appcompat.view.menu.n
    public boolean a(g gVar, j jVar) {
        return false;
    }

    public void b(Bundle bundle) {
        SparseArray<Parcelable> sparseArray = new SparseArray<>();
        ExpandedMenuView expandedMenuView = this.f785e;
        if (expandedMenuView != null) {
            expandedMenuView.saveHierarchyState(sparseArray);
        }
        bundle.putSparseParcelableArray("android:menu:list", sparseArray);
    }

    @Override // androidx.appcompat.view.menu.n
    public boolean b() {
        return false;
    }

    @Override // androidx.appcompat.view.menu.n
    public boolean b(g gVar, j jVar) {
        return false;
    }

    @Override // androidx.appcompat.view.menu.n
    public Parcelable c() {
        if (this.f785e == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        b(bundle);
        return bundle;
    }

    public ListAdapter d() {
        if (this.j == null) {
            this.j = new a();
        }
        return this.j;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.f784d.a(this.j.getItem(i), this, 0);
    }

    public e(int i, int i2) {
        this.h = i;
        this.g = i2;
    }

    public o a(ViewGroup viewGroup) {
        if (this.f785e == null) {
            this.f785e = (ExpandedMenuView) this.f783c.inflate(a.a.g.abc_expanded_menu_layout, viewGroup, false);
            if (this.j == null) {
                this.j = new a();
            }
            this.f785e.setAdapter((ListAdapter) this.j);
            this.f785e.setOnItemClickListener(this);
        }
        return this.f785e;
    }

    @Override // androidx.appcompat.view.menu.n
    public void a(boolean z) {
        a aVar = this.j;
        if (aVar != null) {
            aVar.notifyDataSetChanged();
        }
    }

    @Override // androidx.appcompat.view.menu.n
    public void a(n.a aVar) {
        this.i = aVar;
    }

    @Override // androidx.appcompat.view.menu.n
    public boolean a(s sVar) {
        if (!sVar.hasVisibleItems()) {
            return false;
        }
        new h(sVar).a((IBinder) null);
        n.a aVar = this.i;
        if (aVar == null) {
            return true;
        }
        aVar.a(sVar);
        return true;
    }

    @Override // androidx.appcompat.view.menu.n
    public void a(g gVar, boolean z) {
        n.a aVar = this.i;
        if (aVar != null) {
            aVar.a(gVar, z);
        }
    }

    public void a(Bundle bundle) {
        SparseArray<Parcelable> sparseParcelableArray = bundle.getSparseParcelableArray("android:menu:list");
        if (sparseParcelableArray != null) {
            this.f785e.restoreHierarchyState(sparseParcelableArray);
        }
    }

    @Override // androidx.appcompat.view.menu.n
    public int a() {
        return this.k;
    }

    @Override // androidx.appcompat.view.menu.n
    public void a(Parcelable parcelable) {
        a((Bundle) parcelable);
    }
}
