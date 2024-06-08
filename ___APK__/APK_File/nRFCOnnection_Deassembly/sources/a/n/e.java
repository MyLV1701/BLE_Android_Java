package a.n;

import a.n.m;
import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

@SuppressLint({"RestrictedApi"})
/* loaded from: classes.dex */
public class e extends androidx.fragment.app.w {

    /* loaded from: classes.dex */
    class a extends m.e {
        a(e eVar, Rect rect) {
        }
    }

    /* loaded from: classes.dex */
    class b implements m.f {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ View f452a;

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ ArrayList f453b;

        b(e eVar, View view, ArrayList arrayList) {
            this.f452a = view;
            this.f453b = arrayList;
        }

        @Override // a.n.m.f
        public void a(m mVar) {
        }

        @Override // a.n.m.f
        public void b(m mVar) {
        }

        @Override // a.n.m.f
        public void c(m mVar) {
        }

        @Override // a.n.m.f
        public void d(m mVar) {
        }

        @Override // a.n.m.f
        public void e(m mVar) {
            mVar.b(this);
            this.f452a.setVisibility(8);
            int size = this.f453b.size();
            for (int i = 0; i < size; i++) {
                ((View) this.f453b.get(i)).setVisibility(0);
            }
        }
    }

    /* loaded from: classes.dex */
    class c extends n {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ Object f454a;

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ ArrayList f455b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ Object f456c;

        /* renamed from: d, reason: collision with root package name */
        final /* synthetic */ ArrayList f457d;

        /* renamed from: e, reason: collision with root package name */
        final /* synthetic */ Object f458e;

        /* renamed from: f, reason: collision with root package name */
        final /* synthetic */ ArrayList f459f;

        c(Object obj, ArrayList arrayList, Object obj2, ArrayList arrayList2, Object obj3, ArrayList arrayList3) {
            this.f454a = obj;
            this.f455b = arrayList;
            this.f456c = obj2;
            this.f457d = arrayList2;
            this.f458e = obj3;
            this.f459f = arrayList3;
        }

        @Override // a.n.n, a.n.m.f
        public void a(m mVar) {
            Object obj = this.f454a;
            if (obj != null) {
                e.this.a(obj, this.f455b, (ArrayList<View>) null);
            }
            Object obj2 = this.f456c;
            if (obj2 != null) {
                e.this.a(obj2, this.f457d, (ArrayList<View>) null);
            }
            Object obj3 = this.f458e;
            if (obj3 != null) {
                e.this.a(obj3, this.f459f, (ArrayList<View>) null);
            }
        }

        @Override // a.n.m.f
        public void e(m mVar) {
            mVar.b(this);
        }
    }

    /* loaded from: classes.dex */
    class d extends m.e {
        d(e eVar, Rect rect) {
        }
    }

    @Override // androidx.fragment.app.w
    public boolean a(Object obj) {
        return obj instanceof m;
    }

    @Override // androidx.fragment.app.w
    public Object b(Object obj) {
        if (obj != null) {
            return ((m) obj).mo2clone();
        }
        return null;
    }

    @Override // androidx.fragment.app.w
    public Object c(Object obj) {
        if (obj == null) {
            return null;
        }
        q qVar = new q();
        qVar.a((m) obj);
        return qVar;
    }

    @Override // androidx.fragment.app.w
    public void a(Object obj, ArrayList<View> arrayList) {
        m mVar = (m) obj;
        if (mVar == null) {
            return;
        }
        int i = 0;
        if (mVar instanceof q) {
            q qVar = (q) mVar;
            int q = qVar.q();
            while (i < q) {
                a(qVar.a(i), arrayList);
                i++;
            }
            return;
        }
        if (a(mVar) || !androidx.fragment.app.w.a((List) mVar.m())) {
            return;
        }
        int size = arrayList.size();
        while (i < size) {
            mVar.a(arrayList.get(i));
            i++;
        }
    }

    @Override // androidx.fragment.app.w
    public void b(Object obj, View view, ArrayList<View> arrayList) {
        q qVar = (q) obj;
        List<View> m = qVar.m();
        m.clear();
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            androidx.fragment.app.w.a(m, arrayList.get(i));
        }
        m.add(view);
        arrayList.add(view);
        a(qVar, arrayList);
    }

    @Override // androidx.fragment.app.w
    public void c(Object obj, View view) {
        if (view != null) {
            Rect rect = new Rect();
            a(view, rect);
            ((m) obj).a(new a(this, rect));
        }
    }

    @Override // androidx.fragment.app.w
    public Object b(Object obj, Object obj2, Object obj3) {
        q qVar = new q();
        if (obj != null) {
            qVar.a((m) obj);
        }
        if (obj2 != null) {
            qVar.a((m) obj2);
        }
        if (obj3 != null) {
            qVar.a((m) obj3);
        }
        return qVar;
    }

    private static boolean a(m mVar) {
        return (androidx.fragment.app.w.a((List) mVar.j()) && androidx.fragment.app.w.a((List) mVar.k()) && androidx.fragment.app.w.a((List) mVar.l())) ? false : true;
    }

    @Override // androidx.fragment.app.w
    public void b(Object obj, ArrayList<View> arrayList, ArrayList<View> arrayList2) {
        q qVar = (q) obj;
        if (qVar != null) {
            qVar.m().clear();
            qVar.m().addAll(arrayList2);
            a((Object) qVar, arrayList, arrayList2);
        }
    }

    @Override // androidx.fragment.app.w
    public void a(Object obj, View view, ArrayList<View> arrayList) {
        ((m) obj).a(new b(this, view, arrayList));
    }

    @Override // androidx.fragment.app.w
    public Object a(Object obj, Object obj2, Object obj3) {
        m mVar = (m) obj;
        m mVar2 = (m) obj2;
        m mVar3 = (m) obj3;
        if (mVar != null && mVar2 != null) {
            q qVar = new q();
            qVar.a(mVar);
            qVar.a(mVar2);
            qVar.b(1);
            mVar = qVar;
        } else if (mVar == null) {
            mVar = mVar2 != null ? mVar2 : null;
        }
        if (mVar3 == null) {
            return mVar;
        }
        q qVar2 = new q();
        if (mVar != null) {
            qVar2.a(mVar);
        }
        qVar2.a(mVar3);
        return qVar2;
    }

    @Override // androidx.fragment.app.w
    public void b(Object obj, View view) {
        if (obj != null) {
            ((m) obj).d(view);
        }
    }

    @Override // androidx.fragment.app.w
    public void a(ViewGroup viewGroup, Object obj) {
        o.a(viewGroup, (m) obj);
    }

    @Override // androidx.fragment.app.w
    public void a(Object obj, Object obj2, ArrayList<View> arrayList, Object obj3, ArrayList<View> arrayList2, Object obj4, ArrayList<View> arrayList3) {
        ((m) obj).a(new c(obj2, arrayList, obj3, arrayList2, obj4, arrayList3));
    }

    @Override // androidx.fragment.app.w
    public void a(Object obj, ArrayList<View> arrayList, ArrayList<View> arrayList2) {
        m mVar = (m) obj;
        int i = 0;
        if (mVar instanceof q) {
            q qVar = (q) mVar;
            int q = qVar.q();
            while (i < q) {
                a((Object) qVar.a(i), arrayList, arrayList2);
                i++;
            }
            return;
        }
        if (a(mVar)) {
            return;
        }
        List<View> m = mVar.m();
        if (m.size() == arrayList.size() && m.containsAll(arrayList)) {
            int size = arrayList2 == null ? 0 : arrayList2.size();
            while (i < size) {
                mVar.a(arrayList2.get(i));
                i++;
            }
            for (int size2 = arrayList.size() - 1; size2 >= 0; size2--) {
                mVar.d(arrayList.get(size2));
            }
        }
    }

    @Override // androidx.fragment.app.w
    public void a(Object obj, View view) {
        if (obj != null) {
            ((m) obj).a(view);
        }
    }

    @Override // androidx.fragment.app.w
    public void a(Object obj, Rect rect) {
        if (obj != null) {
            ((m) obj).a(new d(this, rect));
        }
    }
}
