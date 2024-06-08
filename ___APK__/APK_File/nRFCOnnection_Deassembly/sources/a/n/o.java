package a.n;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
public class o {

    /* renamed from: a, reason: collision with root package name */
    private static m f505a = new b();

    /* renamed from: b, reason: collision with root package name */
    private static ThreadLocal<WeakReference<a.d.a<ViewGroup, ArrayList<m>>>> f506b = new ThreadLocal<>();

    /* renamed from: c, reason: collision with root package name */
    static ArrayList<ViewGroup> f507c = new ArrayList<>();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class a implements ViewTreeObserver.OnPreDrawListener, View.OnAttachStateChangeListener {

        /* renamed from: b, reason: collision with root package name */
        m f508b;

        /* renamed from: c, reason: collision with root package name */
        ViewGroup f509c;

        /* renamed from: a.n.o$a$a, reason: collision with other inner class name */
        /* loaded from: classes.dex */
        class C0033a extends n {

            /* renamed from: a, reason: collision with root package name */
            final /* synthetic */ a.d.a f510a;

            C0033a(a.d.a aVar) {
                this.f510a = aVar;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // a.n.m.f
            public void e(m mVar) {
                ((ArrayList) this.f510a.get(a.this.f509c)).remove(mVar);
                mVar.b(this);
            }
        }

        a(m mVar, ViewGroup viewGroup) {
            this.f508b = mVar;
            this.f509c = viewGroup;
        }

        private void a() {
            this.f509c.getViewTreeObserver().removeOnPreDrawListener(this);
            this.f509c.removeOnAttachStateChangeListener(this);
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            a();
            if (!o.f507c.remove(this.f509c)) {
                return true;
            }
            a.d.a<ViewGroup, ArrayList<m>> a2 = o.a();
            ArrayList<m> arrayList = a2.get(this.f509c);
            ArrayList arrayList2 = null;
            if (arrayList == null) {
                arrayList = new ArrayList<>();
                a2.put(this.f509c, arrayList);
            } else if (arrayList.size() > 0) {
                arrayList2 = new ArrayList(arrayList);
            }
            arrayList.add(this.f508b);
            this.f508b.a(new C0033a(a2));
            this.f508b.a(this.f509c, false);
            if (arrayList2 != null) {
                Iterator it = arrayList2.iterator();
                while (it.hasNext()) {
                    ((m) it.next()).e(this.f509c);
                }
            }
            this.f508b.a(this.f509c);
            return true;
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
            a();
            o.f507c.remove(this.f509c);
            ArrayList<m> arrayList = o.a().get(this.f509c);
            if (arrayList != null && arrayList.size() > 0) {
                Iterator<m> it = arrayList.iterator();
                while (it.hasNext()) {
                    it.next().e(this.f509c);
                }
            }
            this.f508b.a(true);
        }
    }

    static a.d.a<ViewGroup, ArrayList<m>> a() {
        a.d.a<ViewGroup, ArrayList<m>> aVar;
        WeakReference<a.d.a<ViewGroup, ArrayList<m>>> weakReference = f506b.get();
        if (weakReference != null && (aVar = weakReference.get()) != null) {
            return aVar;
        }
        a.d.a<ViewGroup, ArrayList<m>> aVar2 = new a.d.a<>();
        f506b.set(new WeakReference<>(aVar2));
        return aVar2;
    }

    private static void b(ViewGroup viewGroup, m mVar) {
        if (mVar == null || viewGroup == null) {
            return;
        }
        a aVar = new a(mVar, viewGroup);
        viewGroup.addOnAttachStateChangeListener(aVar);
        viewGroup.getViewTreeObserver().addOnPreDrawListener(aVar);
    }

    private static void c(ViewGroup viewGroup, m mVar) {
        ArrayList<m> arrayList = a().get(viewGroup);
        if (arrayList != null && arrayList.size() > 0) {
            Iterator<m> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().c(viewGroup);
            }
        }
        if (mVar != null) {
            mVar.a(viewGroup, true);
        }
        l a2 = l.a(viewGroup);
        if (a2 != null) {
            a2.a();
        }
    }

    public static void a(ViewGroup viewGroup, m mVar) {
        if (f507c.contains(viewGroup) || !a.f.l.w.F(viewGroup)) {
            return;
        }
        f507c.add(viewGroup);
        if (mVar == null) {
            mVar = f505a;
        }
        m mo2clone = mVar.mo2clone();
        c(viewGroup, mo2clone);
        l.a(viewGroup, null);
        b(viewGroup, mo2clone);
    }
}
