package androidx.preference;

import a.f.l.w;
import android.R;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.preference.Preference;
import androidx.preference.PreferenceGroup;
import androidx.preference.j;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.f;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class h extends RecyclerView.g<l> implements Preference.c, PreferenceGroup.c {

    /* renamed from: b, reason: collision with root package name */
    private PreferenceGroup f1630b;

    /* renamed from: c, reason: collision with root package name */
    private List<Preference> f1631c;

    /* renamed from: d, reason: collision with root package name */
    private List<Preference> f1632d;

    /* renamed from: e, reason: collision with root package name */
    private List<d> f1633e;
    private Runnable g = new a();

    /* renamed from: f, reason: collision with root package name */
    private Handler f1634f = new Handler();

    /* loaded from: classes.dex */
    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            h.this.a();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class b extends f.b {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ List f1636a;

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ List f1637b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ j.d f1638c;

        b(h hVar, List list, List list2, j.d dVar) {
            this.f1636a = list;
            this.f1637b = list2;
            this.f1638c = dVar;
        }

        @Override // androidx.recyclerview.widget.f.b
        public int a() {
            return this.f1637b.size();
        }

        @Override // androidx.recyclerview.widget.f.b
        public int b() {
            return this.f1636a.size();
        }

        @Override // androidx.recyclerview.widget.f.b
        public boolean a(int i, int i2) {
            return this.f1638c.a((Preference) this.f1636a.get(i), (Preference) this.f1637b.get(i2));
        }

        @Override // androidx.recyclerview.widget.f.b
        public boolean b(int i, int i2) {
            return this.f1638c.b((Preference) this.f1636a.get(i), (Preference) this.f1637b.get(i2));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class c implements Preference.e {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ PreferenceGroup f1639a;

        c(PreferenceGroup preferenceGroup) {
            this.f1639a = preferenceGroup;
        }

        @Override // androidx.preference.Preference.e
        public boolean a(Preference preference) {
            this.f1639a.b(Preference.DEFAULT_ORDER);
            h.this.a(preference);
            PreferenceGroup.b b2 = this.f1639a.b();
            if (b2 == null) {
                return true;
            }
            b2.a();
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class d {

        /* renamed from: a, reason: collision with root package name */
        int f1641a;

        /* renamed from: b, reason: collision with root package name */
        int f1642b;

        /* renamed from: c, reason: collision with root package name */
        String f1643c;

        d(Preference preference) {
            this.f1643c = preference.getClass().getName();
            this.f1641a = preference.getLayoutResource();
            this.f1642b = preference.getWidgetLayoutResource();
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof d)) {
                return false;
            }
            d dVar = (d) obj;
            return this.f1641a == dVar.f1641a && this.f1642b == dVar.f1642b && TextUtils.equals(this.f1643c, dVar.f1643c);
        }

        public int hashCode() {
            return ((((527 + this.f1641a) * 31) + this.f1642b) * 31) + this.f1643c.hashCode();
        }
    }

    public h(PreferenceGroup preferenceGroup) {
        this.f1630b = preferenceGroup;
        this.f1630b.setOnPreferenceChangeInternalListener(this);
        this.f1631c = new ArrayList();
        this.f1632d = new ArrayList();
        this.f1633e = new ArrayList();
        PreferenceGroup preferenceGroup2 = this.f1630b;
        if (preferenceGroup2 instanceof PreferenceScreen) {
            setHasStableIds(((PreferenceScreen) preferenceGroup2).f());
        } else {
            setHasStableIds(true);
        }
        a();
    }

    private boolean b(PreferenceGroup preferenceGroup) {
        return preferenceGroup.a() != Integer.MAX_VALUE;
    }

    void a() {
        Iterator<Preference> it = this.f1631c.iterator();
        while (it.hasNext()) {
            it.next().setOnPreferenceChangeInternalListener(null);
        }
        this.f1631c = new ArrayList(this.f1631c.size());
        a(this.f1631c, this.f1630b);
        List<Preference> list = this.f1632d;
        List<Preference> a2 = a(this.f1630b);
        this.f1632d = a2;
        j preferenceManager = this.f1630b.getPreferenceManager();
        if (preferenceManager != null && preferenceManager.e() != null) {
            androidx.recyclerview.widget.f.a(new b(this, list, a2, preferenceManager.e())).a(this);
        } else {
            notifyDataSetChanged();
        }
        Iterator<Preference> it2 = this.f1631c.iterator();
        while (it2.hasNext()) {
            it2.next().clearWasDetached();
        }
    }

    @Override // androidx.preference.Preference.c
    public void c(Preference preference) {
        a(preference);
    }

    @Override // androidx.preference.PreferenceGroup.c
    public int d(Preference preference) {
        int size = this.f1632d.size();
        for (int i = 0; i < size; i++) {
            Preference preference2 = this.f1632d.get(i);
            if (preference2 != null && preference2.equals(preference)) {
                return i;
            }
        }
        return -1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public int getItemCount() {
        return this.f1632d.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public long getItemId(int i) {
        if (hasStableIds()) {
            return a(i).getId();
        }
        return -1L;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public int getItemViewType(int i) {
        d dVar = new d(a(i));
        int indexOf = this.f1633e.indexOf(dVar);
        if (indexOf != -1) {
            return indexOf;
        }
        int size = this.f1633e.size();
        this.f1633e.add(dVar);
        return size;
    }

    @Override // androidx.preference.Preference.c
    public void b(Preference preference) {
        int indexOf = this.f1632d.indexOf(preference);
        if (indexOf != -1) {
            notifyItemChanged(indexOf, preference);
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // androidx.recyclerview.widget.RecyclerView.g
    public l onCreateViewHolder(ViewGroup viewGroup, int i) {
        d dVar = this.f1633e.get(i);
        LayoutInflater from = LayoutInflater.from(viewGroup.getContext());
        TypedArray obtainStyledAttributes = viewGroup.getContext().obtainStyledAttributes((AttributeSet) null, t.BackgroundStyle);
        Drawable drawable = obtainStyledAttributes.getDrawable(t.BackgroundStyle_android_selectableItemBackground);
        if (drawable == null) {
            drawable = a.a.k.a.a.c(viewGroup.getContext(), R.drawable.list_selector_background);
        }
        obtainStyledAttributes.recycle();
        View inflate = from.inflate(dVar.f1641a, viewGroup, false);
        if (inflate.getBackground() == null) {
            w.a(inflate, drawable);
        }
        ViewGroup viewGroup2 = (ViewGroup) inflate.findViewById(R.id.widget_frame);
        if (viewGroup2 != null) {
            int i2 = dVar.f1642b;
            if (i2 != 0) {
                from.inflate(i2, viewGroup2);
            } else {
                viewGroup2.setVisibility(8);
            }
        }
        return new l(inflate);
    }

    private void a(List<Preference> list, PreferenceGroup preferenceGroup) {
        preferenceGroup.e();
        int c2 = preferenceGroup.c();
        for (int i = 0; i < c2; i++) {
            Preference a2 = preferenceGroup.a(i);
            list.add(a2);
            d dVar = new d(a2);
            if (!this.f1633e.contains(dVar)) {
                this.f1633e.add(dVar);
            }
            if (a2 instanceof PreferenceGroup) {
                PreferenceGroup preferenceGroup2 = (PreferenceGroup) a2;
                if (preferenceGroup2.d()) {
                    a(list, preferenceGroup2);
                }
            }
            a2.setOnPreferenceChangeInternalListener(this);
        }
    }

    private List<Preference> a(PreferenceGroup preferenceGroup) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        int c2 = preferenceGroup.c();
        int i = 0;
        for (int i2 = 0; i2 < c2; i2++) {
            Preference a2 = preferenceGroup.a(i2);
            if (a2.isVisible()) {
                if (b(preferenceGroup) && i >= preferenceGroup.a()) {
                    arrayList2.add(a2);
                } else {
                    arrayList.add(a2);
                }
                if (a2 instanceof PreferenceGroup) {
                    PreferenceGroup preferenceGroup2 = (PreferenceGroup) a2;
                    if (!preferenceGroup2.d()) {
                        continue;
                    } else {
                        if (b(preferenceGroup) && b(preferenceGroup2)) {
                            throw new IllegalStateException("Nesting an expandable group inside of another expandable group is not supported!");
                        }
                        for (Preference preference : a(preferenceGroup2)) {
                            if (b(preferenceGroup) && i >= preferenceGroup.a()) {
                                arrayList2.add(preference);
                            } else {
                                arrayList.add(preference);
                            }
                            i++;
                        }
                    }
                } else {
                    i++;
                }
            }
        }
        if (b(preferenceGroup) && i > preferenceGroup.a()) {
            arrayList.add(a(preferenceGroup, arrayList2));
        }
        return arrayList;
    }

    private androidx.preference.b a(PreferenceGroup preferenceGroup, List<Preference> list) {
        androidx.preference.b bVar = new androidx.preference.b(preferenceGroup.getContext(), list, preferenceGroup.getId());
        bVar.setOnPreferenceClickListener(new c(preferenceGroup));
        return bVar;
    }

    public Preference a(int i) {
        if (i < 0 || i >= getItemCount()) {
            return null;
        }
        return this.f1632d.get(i);
    }

    @Override // androidx.preference.Preference.c
    public void a(Preference preference) {
        this.f1634f.removeCallbacks(this.g);
        this.f1634f.post(this.g);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(l lVar, int i) {
        a(i).onBindViewHolder(lVar);
    }

    @Override // androidx.preference.PreferenceGroup.c
    public int a(String str) {
        int size = this.f1632d.size();
        for (int i = 0; i < size; i++) {
            if (TextUtils.equals(str, this.f1632d.get(i).getKey())) {
                return i;
            }
        }
        return -1;
    }
}
