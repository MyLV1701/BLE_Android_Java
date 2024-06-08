package androidx.recyclerview.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewPropertyAnimator;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class e extends u {
    private static TimeInterpolator s;
    private ArrayList<RecyclerView.d0> h = new ArrayList<>();
    private ArrayList<RecyclerView.d0> i = new ArrayList<>();
    private ArrayList<j> j = new ArrayList<>();
    private ArrayList<i> k = new ArrayList<>();
    ArrayList<ArrayList<RecyclerView.d0>> l = new ArrayList<>();
    ArrayList<ArrayList<j>> m = new ArrayList<>();
    ArrayList<ArrayList<i>> n = new ArrayList<>();
    ArrayList<RecyclerView.d0> o = new ArrayList<>();
    ArrayList<RecyclerView.d0> p = new ArrayList<>();
    ArrayList<RecyclerView.d0> q = new ArrayList<>();
    ArrayList<RecyclerView.d0> r = new ArrayList<>();

    /* loaded from: classes.dex */
    class a implements Runnable {

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ ArrayList f1799b;

        a(ArrayList arrayList) {
            this.f1799b = arrayList;
        }

        @Override // java.lang.Runnable
        public void run() {
            Iterator it = this.f1799b.iterator();
            while (it.hasNext()) {
                j jVar = (j) it.next();
                e.this.b(jVar.f1833a, jVar.f1834b, jVar.f1835c, jVar.f1836d, jVar.f1837e);
            }
            this.f1799b.clear();
            e.this.m.remove(this.f1799b);
        }
    }

    /* loaded from: classes.dex */
    class b implements Runnable {

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ ArrayList f1801b;

        b(ArrayList arrayList) {
            this.f1801b = arrayList;
        }

        @Override // java.lang.Runnable
        public void run() {
            Iterator it = this.f1801b.iterator();
            while (it.hasNext()) {
                e.this.a((i) it.next());
            }
            this.f1801b.clear();
            e.this.n.remove(this.f1801b);
        }
    }

    /* loaded from: classes.dex */
    class c implements Runnable {

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ ArrayList f1803b;

        c(ArrayList arrayList) {
            this.f1803b = arrayList;
        }

        @Override // java.lang.Runnable
        public void run() {
            Iterator it = this.f1803b.iterator();
            while (it.hasNext()) {
                e.this.t((RecyclerView.d0) it.next());
            }
            this.f1803b.clear();
            e.this.l.remove(this.f1803b);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class d extends AnimatorListenerAdapter {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ RecyclerView.d0 f1805a;

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ ViewPropertyAnimator f1806b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ View f1807c;

        d(RecyclerView.d0 d0Var, ViewPropertyAnimator viewPropertyAnimator, View view) {
            this.f1805a = d0Var;
            this.f1806b = viewPropertyAnimator;
            this.f1807c = view;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f1806b.setListener(null);
            this.f1807c.setAlpha(1.0f);
            e.this.l(this.f1805a);
            e.this.q.remove(this.f1805a);
            e.this.j();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            e.this.m(this.f1805a);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: androidx.recyclerview.widget.e$e, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public class C0060e extends AnimatorListenerAdapter {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ RecyclerView.d0 f1809a;

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ View f1810b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ ViewPropertyAnimator f1811c;

        C0060e(RecyclerView.d0 d0Var, View view, ViewPropertyAnimator viewPropertyAnimator) {
            this.f1809a = d0Var;
            this.f1810b = view;
            this.f1811c = viewPropertyAnimator;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f1810b.setAlpha(1.0f);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f1811c.setListener(null);
            e.this.h(this.f1809a);
            e.this.o.remove(this.f1809a);
            e.this.j();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            e.this.i(this.f1809a);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class f extends AnimatorListenerAdapter {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ RecyclerView.d0 f1813a;

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f1814b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ View f1815c;

        /* renamed from: d, reason: collision with root package name */
        final /* synthetic */ int f1816d;

        /* renamed from: e, reason: collision with root package name */
        final /* synthetic */ ViewPropertyAnimator f1817e;

        f(RecyclerView.d0 d0Var, int i, View view, int i2, ViewPropertyAnimator viewPropertyAnimator) {
            this.f1813a = d0Var;
            this.f1814b = i;
            this.f1815c = view;
            this.f1816d = i2;
            this.f1817e = viewPropertyAnimator;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            if (this.f1814b != 0) {
                this.f1815c.setTranslationX(0.0f);
            }
            if (this.f1816d != 0) {
                this.f1815c.setTranslationY(0.0f);
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f1817e.setListener(null);
            e.this.j(this.f1813a);
            e.this.p.remove(this.f1813a);
            e.this.j();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            e.this.k(this.f1813a);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class g extends AnimatorListenerAdapter {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ i f1819a;

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ ViewPropertyAnimator f1820b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ View f1821c;

        g(i iVar, ViewPropertyAnimator viewPropertyAnimator, View view) {
            this.f1819a = iVar;
            this.f1820b = viewPropertyAnimator;
            this.f1821c = view;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f1820b.setListener(null);
            this.f1821c.setAlpha(1.0f);
            this.f1821c.setTranslationX(0.0f);
            this.f1821c.setTranslationY(0.0f);
            e.this.a(this.f1819a.f1827a, true);
            e.this.r.remove(this.f1819a.f1827a);
            e.this.j();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            e.this.b(this.f1819a.f1827a, true);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class h extends AnimatorListenerAdapter {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ i f1823a;

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ ViewPropertyAnimator f1824b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ View f1825c;

        h(i iVar, ViewPropertyAnimator viewPropertyAnimator, View view) {
            this.f1823a = iVar;
            this.f1824b = viewPropertyAnimator;
            this.f1825c = view;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f1824b.setListener(null);
            this.f1825c.setAlpha(1.0f);
            this.f1825c.setTranslationX(0.0f);
            this.f1825c.setTranslationY(0.0f);
            e.this.a(this.f1823a.f1828b, false);
            e.this.r.remove(this.f1823a.f1828b);
            e.this.j();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            e.this.b(this.f1823a.f1828b, false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class j {

        /* renamed from: a, reason: collision with root package name */
        public RecyclerView.d0 f1833a;

        /* renamed from: b, reason: collision with root package name */
        public int f1834b;

        /* renamed from: c, reason: collision with root package name */
        public int f1835c;

        /* renamed from: d, reason: collision with root package name */
        public int f1836d;

        /* renamed from: e, reason: collision with root package name */
        public int f1837e;

        j(RecyclerView.d0 d0Var, int i, int i2, int i3, int i4) {
            this.f1833a = d0Var;
            this.f1834b = i;
            this.f1835c = i2;
            this.f1836d = i3;
            this.f1837e = i4;
        }
    }

    private void u(RecyclerView.d0 d0Var) {
        View view = d0Var.itemView;
        ViewPropertyAnimator animate = view.animate();
        this.q.add(d0Var);
        animate.setDuration(f()).alpha(0.0f).setListener(new d(d0Var, animate, view)).start();
    }

    private void v(RecyclerView.d0 d0Var) {
        if (s == null) {
            s = new ValueAnimator().getInterpolator();
        }
        d0Var.itemView.animate().setInterpolator(s);
        c(d0Var);
    }

    @Override // androidx.recyclerview.widget.u
    public boolean a(RecyclerView.d0 d0Var, int i2, int i3, int i4, int i5) {
        View view = d0Var.itemView;
        int translationX = i2 + ((int) view.getTranslationX());
        int translationY = i3 + ((int) d0Var.itemView.getTranslationY());
        v(d0Var);
        int i6 = i4 - translationX;
        int i7 = i5 - translationY;
        if (i6 == 0 && i7 == 0) {
            j(d0Var);
            return false;
        }
        if (i6 != 0) {
            view.setTranslationX(-i6);
        }
        if (i7 != 0) {
            view.setTranslationY(-i7);
        }
        this.j.add(new j(d0Var, translationX, translationY, i4, i5));
        return true;
    }

    void b(RecyclerView.d0 d0Var, int i2, int i3, int i4, int i5) {
        View view = d0Var.itemView;
        int i6 = i4 - i2;
        int i7 = i5 - i3;
        if (i6 != 0) {
            view.animate().translationX(0.0f);
        }
        if (i7 != 0) {
            view.animate().translationY(0.0f);
        }
        ViewPropertyAnimator animate = view.animate();
        this.p.add(d0Var);
        animate.setDuration(e()).setListener(new f(d0Var, i6, view, i7, animate)).start();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.l
    public void c(RecyclerView.d0 d0Var) {
        View view = d0Var.itemView;
        view.animate().cancel();
        int size = this.j.size();
        while (true) {
            size--;
            if (size < 0) {
                break;
            }
            if (this.j.get(size).f1833a == d0Var) {
                view.setTranslationY(0.0f);
                view.setTranslationX(0.0f);
                j(d0Var);
                this.j.remove(size);
            }
        }
        a(this.k, d0Var);
        if (this.h.remove(d0Var)) {
            view.setAlpha(1.0f);
            l(d0Var);
        }
        if (this.i.remove(d0Var)) {
            view.setAlpha(1.0f);
            h(d0Var);
        }
        for (int size2 = this.n.size() - 1; size2 >= 0; size2--) {
            ArrayList<i> arrayList = this.n.get(size2);
            a(arrayList, d0Var);
            if (arrayList.isEmpty()) {
                this.n.remove(size2);
            }
        }
        for (int size3 = this.m.size() - 1; size3 >= 0; size3--) {
            ArrayList<j> arrayList2 = this.m.get(size3);
            int size4 = arrayList2.size() - 1;
            while (true) {
                if (size4 < 0) {
                    break;
                }
                if (arrayList2.get(size4).f1833a == d0Var) {
                    view.setTranslationY(0.0f);
                    view.setTranslationX(0.0f);
                    j(d0Var);
                    arrayList2.remove(size4);
                    if (arrayList2.isEmpty()) {
                        this.m.remove(size3);
                    }
                } else {
                    size4--;
                }
            }
        }
        for (int size5 = this.l.size() - 1; size5 >= 0; size5--) {
            ArrayList<RecyclerView.d0> arrayList3 = this.l.get(size5);
            if (arrayList3.remove(d0Var)) {
                view.setAlpha(1.0f);
                h(d0Var);
                if (arrayList3.isEmpty()) {
                    this.l.remove(size5);
                }
            }
        }
        this.q.remove(d0Var);
        this.o.remove(d0Var);
        this.r.remove(d0Var);
        this.p.remove(d0Var);
        j();
    }

    @Override // androidx.recyclerview.widget.u
    public boolean f(RecyclerView.d0 d0Var) {
        v(d0Var);
        d0Var.itemView.setAlpha(0.0f);
        this.i.add(d0Var);
        return true;
    }

    @Override // androidx.recyclerview.widget.u
    public boolean g(RecyclerView.d0 d0Var) {
        v(d0Var);
        this.h.add(d0Var);
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.l
    public void i() {
        boolean z = !this.h.isEmpty();
        boolean z2 = !this.j.isEmpty();
        boolean z3 = !this.k.isEmpty();
        boolean z4 = !this.i.isEmpty();
        if (z || z2 || z4 || z3) {
            Iterator<RecyclerView.d0> it = this.h.iterator();
            while (it.hasNext()) {
                u(it.next());
            }
            this.h.clear();
            if (z2) {
                ArrayList<j> arrayList = new ArrayList<>();
                arrayList.addAll(this.j);
                this.m.add(arrayList);
                this.j.clear();
                a aVar = new a(arrayList);
                if (z) {
                    a.f.l.w.a(arrayList.get(0).f1833a.itemView, aVar, f());
                } else {
                    aVar.run();
                }
            }
            if (z3) {
                ArrayList<i> arrayList2 = new ArrayList<>();
                arrayList2.addAll(this.k);
                this.n.add(arrayList2);
                this.k.clear();
                b bVar = new b(arrayList2);
                if (z) {
                    a.f.l.w.a(arrayList2.get(0).f1827a.itemView, bVar, f());
                } else {
                    bVar.run();
                }
            }
            if (z4) {
                ArrayList<RecyclerView.d0> arrayList3 = new ArrayList<>();
                arrayList3.addAll(this.i);
                this.l.add(arrayList3);
                this.i.clear();
                c cVar = new c(arrayList3);
                if (!z && !z2 && !z3) {
                    cVar.run();
                } else {
                    a.f.l.w.a(arrayList3.get(0).itemView, cVar, (z ? f() : 0L) + Math.max(z2 ? e() : 0L, z3 ? d() : 0L));
                }
            }
        }
    }

    void j() {
        if (g()) {
            return;
        }
        a();
    }

    void t(RecyclerView.d0 d0Var) {
        View view = d0Var.itemView;
        ViewPropertyAnimator animate = view.animate();
        this.o.add(d0Var);
        animate.alpha(1.0f).setDuration(c()).setListener(new C0060e(d0Var, view, animate)).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class i {

        /* renamed from: a, reason: collision with root package name */
        public RecyclerView.d0 f1827a;

        /* renamed from: b, reason: collision with root package name */
        public RecyclerView.d0 f1828b;

        /* renamed from: c, reason: collision with root package name */
        public int f1829c;

        /* renamed from: d, reason: collision with root package name */
        public int f1830d;

        /* renamed from: e, reason: collision with root package name */
        public int f1831e;

        /* renamed from: f, reason: collision with root package name */
        public int f1832f;

        private i(RecyclerView.d0 d0Var, RecyclerView.d0 d0Var2) {
            this.f1827a = d0Var;
            this.f1828b = d0Var2;
        }

        public String toString() {
            return "ChangeInfo{oldHolder=" + this.f1827a + ", newHolder=" + this.f1828b + ", fromX=" + this.f1829c + ", fromY=" + this.f1830d + ", toX=" + this.f1831e + ", toY=" + this.f1832f + '}';
        }

        i(RecyclerView.d0 d0Var, RecyclerView.d0 d0Var2, int i, int i2, int i3, int i4) {
            this(d0Var, d0Var2);
            this.f1829c = i;
            this.f1830d = i2;
            this.f1831e = i3;
            this.f1832f = i4;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.l
    public boolean g() {
        return (this.i.isEmpty() && this.k.isEmpty() && this.j.isEmpty() && this.h.isEmpty() && this.p.isEmpty() && this.q.isEmpty() && this.o.isEmpty() && this.r.isEmpty() && this.m.isEmpty() && this.l.isEmpty() && this.n.isEmpty()) ? false : true;
    }

    private void b(i iVar) {
        RecyclerView.d0 d0Var = iVar.f1827a;
        if (d0Var != null) {
            a(iVar, d0Var);
        }
        RecyclerView.d0 d0Var2 = iVar.f1828b;
        if (d0Var2 != null) {
            a(iVar, d0Var2);
        }
    }

    @Override // androidx.recyclerview.widget.u
    public boolean a(RecyclerView.d0 d0Var, RecyclerView.d0 d0Var2, int i2, int i3, int i4, int i5) {
        if (d0Var == d0Var2) {
            return a(d0Var, i2, i3, i4, i5);
        }
        float translationX = d0Var.itemView.getTranslationX();
        float translationY = d0Var.itemView.getTranslationY();
        float alpha = d0Var.itemView.getAlpha();
        v(d0Var);
        int i6 = (int) ((i4 - i2) - translationX);
        int i7 = (int) ((i5 - i3) - translationY);
        d0Var.itemView.setTranslationX(translationX);
        d0Var.itemView.setTranslationY(translationY);
        d0Var.itemView.setAlpha(alpha);
        if (d0Var2 != null) {
            v(d0Var2);
            d0Var2.itemView.setTranslationX(-i6);
            d0Var2.itemView.setTranslationY(-i7);
            d0Var2.itemView.setAlpha(0.0f);
        }
        this.k.add(new i(d0Var, d0Var2, i2, i3, i4, i5));
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.l
    public void b() {
        int size = this.j.size();
        while (true) {
            size--;
            if (size < 0) {
                break;
            }
            j jVar = this.j.get(size);
            View view = jVar.f1833a.itemView;
            view.setTranslationY(0.0f);
            view.setTranslationX(0.0f);
            j(jVar.f1833a);
            this.j.remove(size);
        }
        for (int size2 = this.h.size() - 1; size2 >= 0; size2--) {
            l(this.h.get(size2));
            this.h.remove(size2);
        }
        int size3 = this.i.size();
        while (true) {
            size3--;
            if (size3 < 0) {
                break;
            }
            RecyclerView.d0 d0Var = this.i.get(size3);
            d0Var.itemView.setAlpha(1.0f);
            h(d0Var);
            this.i.remove(size3);
        }
        for (int size4 = this.k.size() - 1; size4 >= 0; size4--) {
            b(this.k.get(size4));
        }
        this.k.clear();
        if (g()) {
            for (int size5 = this.m.size() - 1; size5 >= 0; size5--) {
                ArrayList<j> arrayList = this.m.get(size5);
                for (int size6 = arrayList.size() - 1; size6 >= 0; size6--) {
                    j jVar2 = arrayList.get(size6);
                    View view2 = jVar2.f1833a.itemView;
                    view2.setTranslationY(0.0f);
                    view2.setTranslationX(0.0f);
                    j(jVar2.f1833a);
                    arrayList.remove(size6);
                    if (arrayList.isEmpty()) {
                        this.m.remove(arrayList);
                    }
                }
            }
            for (int size7 = this.l.size() - 1; size7 >= 0; size7--) {
                ArrayList<RecyclerView.d0> arrayList2 = this.l.get(size7);
                for (int size8 = arrayList2.size() - 1; size8 >= 0; size8--) {
                    RecyclerView.d0 d0Var2 = arrayList2.get(size8);
                    d0Var2.itemView.setAlpha(1.0f);
                    h(d0Var2);
                    arrayList2.remove(size8);
                    if (arrayList2.isEmpty()) {
                        this.l.remove(arrayList2);
                    }
                }
            }
            for (int size9 = this.n.size() - 1; size9 >= 0; size9--) {
                ArrayList<i> arrayList3 = this.n.get(size9);
                for (int size10 = arrayList3.size() - 1; size10 >= 0; size10--) {
                    b(arrayList3.get(size10));
                    if (arrayList3.isEmpty()) {
                        this.n.remove(arrayList3);
                    }
                }
            }
            a(this.q);
            a(this.p);
            a(this.o);
            a(this.r);
            a();
        }
    }

    void a(i iVar) {
        RecyclerView.d0 d0Var = iVar.f1827a;
        View view = d0Var == null ? null : d0Var.itemView;
        RecyclerView.d0 d0Var2 = iVar.f1828b;
        View view2 = d0Var2 != null ? d0Var2.itemView : null;
        if (view != null) {
            ViewPropertyAnimator duration = view.animate().setDuration(d());
            this.r.add(iVar.f1827a);
            duration.translationX(iVar.f1831e - iVar.f1829c);
            duration.translationY(iVar.f1832f - iVar.f1830d);
            duration.alpha(0.0f).setListener(new g(iVar, duration, view)).start();
        }
        if (view2 != null) {
            ViewPropertyAnimator animate = view2.animate();
            this.r.add(iVar.f1828b);
            animate.translationX(0.0f).translationY(0.0f).setDuration(d()).alpha(1.0f).setListener(new h(iVar, animate, view2)).start();
        }
    }

    private void a(List<i> list, RecyclerView.d0 d0Var) {
        for (int size = list.size() - 1; size >= 0; size--) {
            i iVar = list.get(size);
            if (a(iVar, d0Var) && iVar.f1827a == null && iVar.f1828b == null) {
                list.remove(iVar);
            }
        }
    }

    private boolean a(i iVar, RecyclerView.d0 d0Var) {
        boolean z = false;
        if (iVar.f1828b == d0Var) {
            iVar.f1828b = null;
        } else {
            if (iVar.f1827a != d0Var) {
                return false;
            }
            iVar.f1827a = null;
            z = true;
        }
        d0Var.itemView.setAlpha(1.0f);
        d0Var.itemView.setTranslationX(0.0f);
        d0Var.itemView.setTranslationY(0.0f);
        a(d0Var, z);
        return true;
    }

    void a(List<RecyclerView.d0> list) {
        for (int size = list.size() - 1; size >= 0; size--) {
            list.get(size).itemView.animate().cancel();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.l
    public boolean a(RecyclerView.d0 d0Var, List<Object> list) {
        return !list.isEmpty() || super.a(d0Var, list);
    }
}
