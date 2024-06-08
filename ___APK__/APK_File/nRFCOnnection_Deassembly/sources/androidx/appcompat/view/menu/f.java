package androidx.appcompat.view.menu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import androidx.appcompat.view.menu.o;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class f extends BaseAdapter {

    /* renamed from: b, reason: collision with root package name */
    g f789b;

    /* renamed from: c, reason: collision with root package name */
    private int f790c = -1;

    /* renamed from: d, reason: collision with root package name */
    private boolean f791d;

    /* renamed from: e, reason: collision with root package name */
    private final boolean f792e;

    /* renamed from: f, reason: collision with root package name */
    private final LayoutInflater f793f;
    private final int g;

    public f(g gVar, LayoutInflater layoutInflater, boolean z, int i) {
        this.f792e = z;
        this.f793f = layoutInflater;
        this.f789b = gVar;
        this.g = i;
        a();
    }

    public void a(boolean z) {
        this.f791d = z;
    }

    public g b() {
        return this.f789b;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        ArrayList<j> j = this.f792e ? this.f789b.j() : this.f789b.n();
        if (this.f790c < 0) {
            return j.size();
        }
        return j.size() - 1;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = this.f793f.inflate(this.g, viewGroup, false);
        }
        int groupId = getItem(i).getGroupId();
        int i2 = i - 1;
        ListMenuItemView listMenuItemView = (ListMenuItemView) view;
        listMenuItemView.setGroupDividerEnabled(this.f789b.o() && groupId != (i2 >= 0 ? getItem(i2).getGroupId() : groupId));
        o.a aVar = (o.a) view;
        if (this.f791d) {
            listMenuItemView.setForceShowIcon(true);
        }
        aVar.a(getItem(i), 0);
        return view;
    }

    @Override // android.widget.BaseAdapter
    public void notifyDataSetChanged() {
        a();
        super.notifyDataSetChanged();
    }

    void a() {
        j f2 = this.f789b.f();
        if (f2 != null) {
            ArrayList<j> j = this.f789b.j();
            int size = j.size();
            for (int i = 0; i < size; i++) {
                if (j.get(i) == f2) {
                    this.f790c = i;
                    return;
                }
            }
        }
        this.f790c = -1;
    }

    @Override // android.widget.Adapter
    public j getItem(int i) {
        ArrayList<j> j = this.f792e ? this.f789b.j() : this.f789b.n();
        int i2 = this.f790c;
        if (i2 >= 0 && i >= i2) {
            i++;
        }
        return j.get(i);
    }
}
