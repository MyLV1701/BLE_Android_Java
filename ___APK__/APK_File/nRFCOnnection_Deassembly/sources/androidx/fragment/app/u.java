package androidx.fragment.app;

import android.graphics.Rect;
import android.os.Build;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class u {

    /* renamed from: a, reason: collision with root package name */
    private static final int[] f1451a = {0, 3, 0, 1, 5, 4, 7, 6, 9, 8, 10};

    /* renamed from: b, reason: collision with root package name */
    private static final w f1452b;

    /* renamed from: c, reason: collision with root package name */
    private static final w f1453c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class a implements Runnable {

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ g f1454b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ Fragment f1455c;

        /* renamed from: d, reason: collision with root package name */
        final /* synthetic */ a.f.h.a f1456d;

        a(g gVar, Fragment fragment, a.f.h.a aVar) {
            this.f1454b = gVar;
            this.f1455c = fragment;
            this.f1456d = aVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f1454b.a(this.f1455c, this.f1456d);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class b implements Runnable {

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ ArrayList f1457b;

        b(ArrayList arrayList) {
            this.f1457b = arrayList;
        }

        @Override // java.lang.Runnable
        public void run() {
            u.a((ArrayList<View>) this.f1457b, 4);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class c implements Runnable {

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ g f1458b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ Fragment f1459c;

        /* renamed from: d, reason: collision with root package name */
        final /* synthetic */ a.f.h.a f1460d;

        c(g gVar, Fragment fragment, a.f.h.a aVar) {
            this.f1458b = gVar;
            this.f1459c = fragment;
            this.f1460d = aVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f1458b.a(this.f1459c, this.f1460d);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class d implements Runnable {

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ Object f1461b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ w f1462c;

        /* renamed from: d, reason: collision with root package name */
        final /* synthetic */ View f1463d;

        /* renamed from: e, reason: collision with root package name */
        final /* synthetic */ Fragment f1464e;

        /* renamed from: f, reason: collision with root package name */
        final /* synthetic */ ArrayList f1465f;
        final /* synthetic */ ArrayList g;
        final /* synthetic */ ArrayList h;
        final /* synthetic */ Object i;

        d(Object obj, w wVar, View view, Fragment fragment, ArrayList arrayList, ArrayList arrayList2, ArrayList arrayList3, Object obj2) {
            this.f1461b = obj;
            this.f1462c = wVar;
            this.f1463d = view;
            this.f1464e = fragment;
            this.f1465f = arrayList;
            this.g = arrayList2;
            this.h = arrayList3;
            this.i = obj2;
        }

        @Override // java.lang.Runnable
        public void run() {
            Object obj = this.f1461b;
            if (obj != null) {
                this.f1462c.b(obj, this.f1463d);
                this.g.addAll(u.a(this.f1462c, this.f1461b, this.f1464e, (ArrayList<View>) this.f1465f, this.f1463d));
            }
            if (this.h != null) {
                if (this.i != null) {
                    ArrayList<View> arrayList = new ArrayList<>();
                    arrayList.add(this.f1463d);
                    this.f1462c.a(this.i, this.h, arrayList);
                }
                this.h.clear();
                this.h.add(this.f1463d);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class e implements Runnable {

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ Fragment f1466b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ Fragment f1467c;

        /* renamed from: d, reason: collision with root package name */
        final /* synthetic */ boolean f1468d;

        /* renamed from: e, reason: collision with root package name */
        final /* synthetic */ a.d.a f1469e;

        /* renamed from: f, reason: collision with root package name */
        final /* synthetic */ View f1470f;
        final /* synthetic */ w g;
        final /* synthetic */ Rect h;

        e(Fragment fragment, Fragment fragment2, boolean z, a.d.a aVar, View view, w wVar, Rect rect) {
            this.f1466b = fragment;
            this.f1467c = fragment2;
            this.f1468d = z;
            this.f1469e = aVar;
            this.f1470f = view;
            this.g = wVar;
            this.h = rect;
        }

        @Override // java.lang.Runnable
        public void run() {
            u.a(this.f1466b, this.f1467c, this.f1468d, (a.d.a<String, View>) this.f1469e, false);
            View view = this.f1470f;
            if (view != null) {
                this.g.a(view, this.h);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class f implements Runnable {

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ w f1471b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ a.d.a f1472c;

        /* renamed from: d, reason: collision with root package name */
        final /* synthetic */ Object f1473d;

        /* renamed from: e, reason: collision with root package name */
        final /* synthetic */ h f1474e;

        /* renamed from: f, reason: collision with root package name */
        final /* synthetic */ ArrayList f1475f;
        final /* synthetic */ View g;
        final /* synthetic */ Fragment h;
        final /* synthetic */ Fragment i;
        final /* synthetic */ boolean j;
        final /* synthetic */ ArrayList k;
        final /* synthetic */ Object l;
        final /* synthetic */ Rect m;

        f(w wVar, a.d.a aVar, Object obj, h hVar, ArrayList arrayList, View view, Fragment fragment, Fragment fragment2, boolean z, ArrayList arrayList2, Object obj2, Rect rect) {
            this.f1471b = wVar;
            this.f1472c = aVar;
            this.f1473d = obj;
            this.f1474e = hVar;
            this.f1475f = arrayList;
            this.g = view;
            this.h = fragment;
            this.i = fragment2;
            this.j = z;
            this.k = arrayList2;
            this.l = obj2;
            this.m = rect;
        }

        @Override // java.lang.Runnable
        public void run() {
            a.d.a<String, View> a2 = u.a(this.f1471b, (a.d.a<String, String>) this.f1472c, this.f1473d, this.f1474e);
            if (a2 != null) {
                this.f1475f.addAll(a2.values());
                this.f1475f.add(this.g);
            }
            u.a(this.h, this.i, this.j, a2, false);
            Object obj = this.f1473d;
            if (obj != null) {
                this.f1471b.b(obj, this.k, this.f1475f);
                View a3 = u.a(a2, this.f1474e, this.l, this.j);
                if (a3 != null) {
                    this.f1471b.a(a3, this.m);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface g {
        void a(Fragment fragment, a.f.h.a aVar);

        void b(Fragment fragment, a.f.h.a aVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class h {

        /* renamed from: a, reason: collision with root package name */
        public Fragment f1476a;

        /* renamed from: b, reason: collision with root package name */
        public boolean f1477b;

        /* renamed from: c, reason: collision with root package name */
        public androidx.fragment.app.a f1478c;

        /* renamed from: d, reason: collision with root package name */
        public Fragment f1479d;

        /* renamed from: e, reason: collision with root package name */
        public boolean f1480e;

        /* renamed from: f, reason: collision with root package name */
        public androidx.fragment.app.a f1481f;

        h() {
        }
    }

    static {
        f1452b = Build.VERSION.SDK_INT >= 21 ? new v() : null;
        f1453c = a();
    }

    private static w a() {
        try {
            return (w) Class.forName("a.n.e").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }

    private static void b(l lVar, int i, h hVar, View view, a.d.a<String, String> aVar, g gVar) {
        Fragment fragment;
        Fragment fragment2;
        w a2;
        Object obj;
        ViewGroup viewGroup = lVar.q.a() ? (ViewGroup) lVar.q.a(i) : null;
        if (viewGroup == null || (a2 = a((fragment2 = hVar.f1479d), (fragment = hVar.f1476a))) == null) {
            return;
        }
        boolean z = hVar.f1477b;
        boolean z2 = hVar.f1480e;
        ArrayList<View> arrayList = new ArrayList<>();
        ArrayList<View> arrayList2 = new ArrayList<>();
        Object a3 = a(a2, fragment, z);
        Object b2 = b(a2, fragment2, z2);
        Object b3 = b(a2, viewGroup, view, aVar, hVar, arrayList2, arrayList, a3, b2);
        if (a3 == null && b3 == null) {
            obj = b2;
            if (obj == null) {
                return;
            }
        } else {
            obj = b2;
        }
        ArrayList<View> a4 = a(a2, obj, fragment2, arrayList2, view);
        ArrayList<View> a5 = a(a2, a3, fragment, arrayList, view);
        a(a5, 4);
        Object a6 = a(a2, a3, obj, b3, fragment, z);
        if (fragment2 != null && a4 != null && (a4.size() > 0 || arrayList2.size() > 0)) {
            a.f.h.a aVar2 = new a.f.h.a();
            gVar.b(fragment2, aVar2);
            a2.a(fragment2, a6, aVar2, new a(gVar, fragment2, aVar2));
        }
        if (a6 != null) {
            a(a2, obj, fragment2, a4);
            ArrayList<String> a7 = a2.a(arrayList);
            a2.a(a6, a3, a5, obj, a4, b3, arrayList);
            a2.a(viewGroup, a6);
            a2.a(viewGroup, arrayList2, arrayList, a7, aVar);
            a(a5, 0);
            a2.b(b3, arrayList2, arrayList);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(l lVar, ArrayList<androidx.fragment.app.a> arrayList, ArrayList<Boolean> arrayList2, int i, int i2, boolean z, g gVar) {
        if (lVar.o < 1) {
            return;
        }
        SparseArray sparseArray = new SparseArray();
        for (int i3 = i; i3 < i2; i3++) {
            androidx.fragment.app.a aVar = arrayList.get(i3);
            if (arrayList2.get(i3).booleanValue()) {
                b(aVar, (SparseArray<h>) sparseArray, z);
            } else {
                a(aVar, (SparseArray<h>) sparseArray, z);
            }
        }
        if (sparseArray.size() != 0) {
            View view = new View(lVar.p.c());
            int size = sparseArray.size();
            for (int i4 = 0; i4 < size; i4++) {
                int keyAt = sparseArray.keyAt(i4);
                a.d.a<String, String> a2 = a(keyAt, arrayList, arrayList2, i, i2);
                h hVar = (h) sparseArray.valueAt(i4);
                if (z) {
                    b(lVar, keyAt, hVar, view, a2, gVar);
                } else {
                    a(lVar, keyAt, hVar, view, a2, gVar);
                }
            }
        }
    }

    private static a.d.a<String, String> a(int i, ArrayList<androidx.fragment.app.a> arrayList, ArrayList<Boolean> arrayList2, int i2, int i3) {
        ArrayList<String> arrayList3;
        ArrayList<String> arrayList4;
        a.d.a<String, String> aVar = new a.d.a<>();
        for (int i4 = i3 - 1; i4 >= i2; i4--) {
            androidx.fragment.app.a aVar2 = arrayList.get(i4);
            if (aVar2.b(i)) {
                boolean booleanValue = arrayList2.get(i4).booleanValue();
                ArrayList<String> arrayList5 = aVar2.n;
                if (arrayList5 != null) {
                    int size = arrayList5.size();
                    if (booleanValue) {
                        arrayList3 = aVar2.n;
                        arrayList4 = aVar2.o;
                    } else {
                        ArrayList<String> arrayList6 = aVar2.n;
                        arrayList3 = aVar2.o;
                        arrayList4 = arrayList6;
                    }
                    for (int i5 = 0; i5 < size; i5++) {
                        String str = arrayList4.get(i5);
                        String str2 = arrayList3.get(i5);
                        String remove = aVar.remove(str2);
                        if (remove != null) {
                            aVar.put(str, remove);
                        } else {
                            aVar.put(str, str2);
                        }
                    }
                }
            }
        }
        return aVar;
    }

    private static Object b(w wVar, Fragment fragment, boolean z) {
        Object exitTransition;
        if (fragment == null) {
            return null;
        }
        if (z) {
            exitTransition = fragment.getReturnTransition();
        } else {
            exitTransition = fragment.getExitTransition();
        }
        return wVar.b(exitTransition);
    }

    private static Object b(w wVar, ViewGroup viewGroup, View view, a.d.a<String, String> aVar, h hVar, ArrayList<View> arrayList, ArrayList<View> arrayList2, Object obj, Object obj2) {
        Object obj3;
        View view2;
        Rect rect;
        Fragment fragment = hVar.f1476a;
        Fragment fragment2 = hVar.f1479d;
        if (fragment != null) {
            fragment.requireView().setVisibility(0);
        }
        if (fragment == null || fragment2 == null) {
            return null;
        }
        boolean z = hVar.f1477b;
        Object a2 = aVar.isEmpty() ? null : a(wVar, fragment, fragment2, z);
        a.d.a<String, View> b2 = b(wVar, aVar, a2, hVar);
        a.d.a<String, View> a3 = a(wVar, aVar, a2, hVar);
        if (aVar.isEmpty()) {
            if (b2 != null) {
                b2.clear();
            }
            if (a3 != null) {
                a3.clear();
            }
            obj3 = null;
        } else {
            a(arrayList, b2, aVar.keySet());
            a(arrayList2, a3, aVar.values());
            obj3 = a2;
        }
        if (obj == null && obj2 == null && obj3 == null) {
            return null;
        }
        a(fragment, fragment2, z, b2, true);
        if (obj3 != null) {
            arrayList2.add(view);
            wVar.b(obj3, view, arrayList);
            a(wVar, obj3, obj2, b2, hVar.f1480e, hVar.f1481f);
            Rect rect2 = new Rect();
            View a4 = a(a3, hVar, obj, z);
            if (a4 != null) {
                wVar.a(obj, rect2);
            }
            rect = rect2;
            view2 = a4;
        } else {
            view2 = null;
            rect = null;
        }
        a.f.l.s.a(viewGroup, new e(fragment, fragment2, z, a3, view2, wVar, rect));
        return obj3;
    }

    private static void a(w wVar, Object obj, Fragment fragment, ArrayList<View> arrayList) {
        if (fragment != null && obj != null && fragment.mAdded && fragment.mHidden && fragment.mHiddenChanged) {
            fragment.setHideReplaced(true);
            wVar.a(obj, fragment.getView(), arrayList);
            a.f.l.s.a(fragment.mContainer, new b(arrayList));
        }
    }

    private static void a(l lVar, int i, h hVar, View view, a.d.a<String, String> aVar, g gVar) {
        Fragment fragment;
        Fragment fragment2;
        w a2;
        Object obj;
        ViewGroup viewGroup = lVar.q.a() ? (ViewGroup) lVar.q.a(i) : null;
        if (viewGroup == null || (a2 = a((fragment2 = hVar.f1479d), (fragment = hVar.f1476a))) == null) {
            return;
        }
        boolean z = hVar.f1477b;
        boolean z2 = hVar.f1480e;
        Object a3 = a(a2, fragment, z);
        Object b2 = b(a2, fragment2, z2);
        ArrayList arrayList = new ArrayList();
        ArrayList<View> arrayList2 = new ArrayList<>();
        Object a4 = a(a2, viewGroup, view, aVar, hVar, (ArrayList<View>) arrayList, arrayList2, a3, b2);
        if (a3 == null && a4 == null) {
            obj = b2;
            if (obj == null) {
                return;
            }
        } else {
            obj = b2;
        }
        ArrayList<View> a5 = a(a2, obj, fragment2, (ArrayList<View>) arrayList, view);
        Object obj2 = (a5 == null || a5.isEmpty()) ? null : obj;
        a2.a(a3, view);
        Object a6 = a(a2, a3, obj2, a4, fragment, hVar.f1477b);
        if (fragment2 != null && a5 != null && (a5.size() > 0 || arrayList.size() > 0)) {
            a.f.h.a aVar2 = new a.f.h.a();
            gVar.b(fragment2, aVar2);
            a2.a(fragment2, a6, aVar2, new c(gVar, fragment2, aVar2));
        }
        if (a6 != null) {
            ArrayList<View> arrayList3 = new ArrayList<>();
            a2.a(a6, a3, arrayList3, obj2, a5, a4, arrayList2);
            a(a2, viewGroup, fragment, view, arrayList2, a3, arrayList3, obj2, a5);
            a2.a((View) viewGroup, arrayList2, (Map<String, String>) aVar);
            a2.a(viewGroup, a6);
            a2.a(viewGroup, arrayList2, (Map<String, String>) aVar);
        }
    }

    private static a.d.a<String, View> b(w wVar, a.d.a<String, String> aVar, Object obj, h hVar) {
        androidx.core.app.l exitTransitionCallback;
        ArrayList<String> arrayList;
        if (!aVar.isEmpty() && obj != null) {
            Fragment fragment = hVar.f1479d;
            a.d.a<String, View> aVar2 = new a.d.a<>();
            wVar.a((Map<String, View>) aVar2, fragment.requireView());
            androidx.fragment.app.a aVar3 = hVar.f1481f;
            if (hVar.f1480e) {
                exitTransitionCallback = fragment.getEnterTransitionCallback();
                arrayList = aVar3.o;
            } else {
                exitTransitionCallback = fragment.getExitTransitionCallback();
                arrayList = aVar3.n;
            }
            if (arrayList != null) {
                aVar2.a((Collection<?>) arrayList);
            }
            if (exitTransitionCallback != null) {
                exitTransitionCallback.a(arrayList, aVar2);
                for (int size = arrayList.size() - 1; size >= 0; size--) {
                    String str = arrayList.get(size);
                    View view = aVar2.get(str);
                    if (view == null) {
                        aVar.remove(str);
                    } else if (!str.equals(a.f.l.w.x(view))) {
                        aVar.put(a.f.l.w.x(view), aVar.remove(str));
                    }
                }
            } else {
                aVar.a((Collection<?>) aVar2.keySet());
            }
            return aVar2;
        }
        aVar.clear();
        return null;
    }

    private static void a(w wVar, ViewGroup viewGroup, Fragment fragment, View view, ArrayList<View> arrayList, Object obj, ArrayList<View> arrayList2, Object obj2, ArrayList<View> arrayList3) {
        a.f.l.s.a(viewGroup, new d(obj, wVar, view, fragment, arrayList, arrayList2, arrayList3, obj2));
    }

    private static w a(Fragment fragment, Fragment fragment2) {
        ArrayList arrayList = new ArrayList();
        if (fragment != null) {
            Object exitTransition = fragment.getExitTransition();
            if (exitTransition != null) {
                arrayList.add(exitTransition);
            }
            Object returnTransition = fragment.getReturnTransition();
            if (returnTransition != null) {
                arrayList.add(returnTransition);
            }
            Object sharedElementReturnTransition = fragment.getSharedElementReturnTransition();
            if (sharedElementReturnTransition != null) {
                arrayList.add(sharedElementReturnTransition);
            }
        }
        if (fragment2 != null) {
            Object enterTransition = fragment2.getEnterTransition();
            if (enterTransition != null) {
                arrayList.add(enterTransition);
            }
            Object reenterTransition = fragment2.getReenterTransition();
            if (reenterTransition != null) {
                arrayList.add(reenterTransition);
            }
            Object sharedElementEnterTransition = fragment2.getSharedElementEnterTransition();
            if (sharedElementEnterTransition != null) {
                arrayList.add(sharedElementEnterTransition);
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        w wVar = f1452b;
        if (wVar != null && a(wVar, arrayList)) {
            return f1452b;
        }
        w wVar2 = f1453c;
        if (wVar2 != null && a(wVar2, arrayList)) {
            return f1453c;
        }
        if (f1452b == null && f1453c == null) {
            return null;
        }
        throw new IllegalArgumentException("Invalid Transition types");
    }

    public static void b(androidx.fragment.app.a aVar, SparseArray<h> sparseArray, boolean z) {
        if (aVar.r.q.a()) {
            for (int size = aVar.f1439a.size() - 1; size >= 0; size--) {
                a(aVar, aVar.f1439a.get(size), sparseArray, true, z);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean b() {
        return (f1452b == null && f1453c == null) ? false : true;
    }

    private static boolean a(w wVar, List<Object> list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (!wVar.a(list.get(i))) {
                return false;
            }
        }
        return true;
    }

    private static Object a(w wVar, Fragment fragment, Fragment fragment2, boolean z) {
        Object sharedElementEnterTransition;
        if (fragment == null || fragment2 == null) {
            return null;
        }
        if (z) {
            sharedElementEnterTransition = fragment2.getSharedElementReturnTransition();
        } else {
            sharedElementEnterTransition = fragment.getSharedElementEnterTransition();
        }
        return wVar.c(wVar.b(sharedElementEnterTransition));
    }

    private static Object a(w wVar, Fragment fragment, boolean z) {
        Object enterTransition;
        if (fragment == null) {
            return null;
        }
        if (z) {
            enterTransition = fragment.getReenterTransition();
        } else {
            enterTransition = fragment.getEnterTransition();
        }
        return wVar.b(enterTransition);
    }

    private static void a(ArrayList<View> arrayList, a.d.a<String, View> aVar, Collection<String> collection) {
        for (int size = aVar.size() - 1; size >= 0; size--) {
            View d2 = aVar.d(size);
            if (collection.contains(a.f.l.w.x(d2))) {
                arrayList.add(d2);
            }
        }
    }

    private static Object a(w wVar, ViewGroup viewGroup, View view, a.d.a<String, String> aVar, h hVar, ArrayList<View> arrayList, ArrayList<View> arrayList2, Object obj, Object obj2) {
        Object a2;
        a.d.a<String, String> aVar2;
        Object obj3;
        Rect rect;
        Fragment fragment = hVar.f1476a;
        Fragment fragment2 = hVar.f1479d;
        if (fragment == null || fragment2 == null) {
            return null;
        }
        boolean z = hVar.f1477b;
        if (aVar.isEmpty()) {
            aVar2 = aVar;
            a2 = null;
        } else {
            a2 = a(wVar, fragment, fragment2, z);
            aVar2 = aVar;
        }
        a.d.a<String, View> b2 = b(wVar, aVar2, a2, hVar);
        if (aVar.isEmpty()) {
            obj3 = null;
        } else {
            arrayList.addAll(b2.values());
            obj3 = a2;
        }
        if (obj == null && obj2 == null && obj3 == null) {
            return null;
        }
        a(fragment, fragment2, z, b2, true);
        if (obj3 != null) {
            rect = new Rect();
            wVar.b(obj3, view, arrayList);
            a(wVar, obj3, obj2, b2, hVar.f1480e, hVar.f1481f);
            if (obj != null) {
                wVar.a(obj, rect);
            }
        } else {
            rect = null;
        }
        a.f.l.s.a(viewGroup, new f(wVar, aVar, obj3, hVar, arrayList2, view, fragment, fragment2, z, arrayList, obj, rect));
        return obj3;
    }

    static a.d.a<String, View> a(w wVar, a.d.a<String, String> aVar, Object obj, h hVar) {
        androidx.core.app.l enterTransitionCallback;
        ArrayList<String> arrayList;
        String a2;
        Fragment fragment = hVar.f1476a;
        View view = fragment.getView();
        if (!aVar.isEmpty() && obj != null && view != null) {
            a.d.a<String, View> aVar2 = new a.d.a<>();
            wVar.a((Map<String, View>) aVar2, view);
            androidx.fragment.app.a aVar3 = hVar.f1478c;
            if (hVar.f1477b) {
                enterTransitionCallback = fragment.getExitTransitionCallback();
                arrayList = aVar3.n;
            } else {
                enterTransitionCallback = fragment.getEnterTransitionCallback();
                arrayList = aVar3.o;
            }
            if (arrayList != null) {
                aVar2.a((Collection<?>) arrayList);
                aVar2.a((Collection<?>) aVar.values());
            }
            if (enterTransitionCallback != null) {
                enterTransitionCallback.a(arrayList, aVar2);
                for (int size = arrayList.size() - 1; size >= 0; size--) {
                    String str = arrayList.get(size);
                    View view2 = aVar2.get(str);
                    if (view2 == null) {
                        String a3 = a(aVar, str);
                        if (a3 != null) {
                            aVar.remove(a3);
                        }
                    } else if (!str.equals(a.f.l.w.x(view2)) && (a2 = a(aVar, str)) != null) {
                        aVar.put(a2, a.f.l.w.x(view2));
                    }
                }
            } else {
                a(aVar, aVar2);
            }
            return aVar2;
        }
        aVar.clear();
        return null;
    }

    private static String a(a.d.a<String, String> aVar, String str) {
        int size = aVar.size();
        for (int i = 0; i < size; i++) {
            if (str.equals(aVar.d(i))) {
                return aVar.b(i);
            }
        }
        return null;
    }

    static View a(a.d.a<String, View> aVar, h hVar, Object obj, boolean z) {
        ArrayList<String> arrayList;
        String str;
        androidx.fragment.app.a aVar2 = hVar.f1478c;
        if (obj == null || aVar == null || (arrayList = aVar2.n) == null || arrayList.isEmpty()) {
            return null;
        }
        if (z) {
            str = aVar2.n.get(0);
        } else {
            str = aVar2.o.get(0);
        }
        return aVar.get(str);
    }

    private static void a(w wVar, Object obj, Object obj2, a.d.a<String, View> aVar, boolean z, androidx.fragment.app.a aVar2) {
        String str;
        ArrayList<String> arrayList = aVar2.n;
        if (arrayList == null || arrayList.isEmpty()) {
            return;
        }
        if (z) {
            str = aVar2.o.get(0);
        } else {
            str = aVar2.n.get(0);
        }
        View view = aVar.get(str);
        wVar.c(obj, view);
        if (obj2 != null) {
            wVar.c(obj2, view);
        }
    }

    private static void a(a.d.a<String, String> aVar, a.d.a<String, View> aVar2) {
        for (int size = aVar.size() - 1; size >= 0; size--) {
            if (!aVar2.containsKey(aVar.d(size))) {
                aVar.c(size);
            }
        }
    }

    static void a(Fragment fragment, Fragment fragment2, boolean z, a.d.a<String, View> aVar, boolean z2) {
        androidx.core.app.l enterTransitionCallback;
        if (z) {
            enterTransitionCallback = fragment2.getEnterTransitionCallback();
        } else {
            enterTransitionCallback = fragment.getEnterTransitionCallback();
        }
        if (enterTransitionCallback != null) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            int size = aVar == null ? 0 : aVar.size();
            for (int i = 0; i < size; i++) {
                arrayList2.add(aVar.b(i));
                arrayList.add(aVar.d(i));
            }
            if (z2) {
                enterTransitionCallback.b(arrayList2, arrayList, null);
            } else {
                enterTransitionCallback.a(arrayList2, arrayList, (List<View>) null);
            }
        }
    }

    static ArrayList<View> a(w wVar, Object obj, Fragment fragment, ArrayList<View> arrayList, View view) {
        if (obj == null) {
            return null;
        }
        ArrayList<View> arrayList2 = new ArrayList<>();
        View view2 = fragment.getView();
        if (view2 != null) {
            wVar.a(arrayList2, view2);
        }
        if (arrayList != null) {
            arrayList2.removeAll(arrayList);
        }
        if (arrayList2.isEmpty()) {
            return arrayList2;
        }
        arrayList2.add(view);
        wVar.a(obj, arrayList2);
        return arrayList2;
    }

    static void a(ArrayList<View> arrayList, int i) {
        if (arrayList == null) {
            return;
        }
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            arrayList.get(size).setVisibility(i);
        }
    }

    private static Object a(w wVar, Object obj, Object obj2, Object obj3, Fragment fragment, boolean z) {
        boolean z2;
        if (obj == null || obj2 == null || fragment == null) {
            z2 = true;
        } else if (z) {
            z2 = fragment.getAllowReturnTransitionOverlap();
        } else {
            z2 = fragment.getAllowEnterTransitionOverlap();
        }
        if (z2) {
            return wVar.b(obj2, obj, obj3);
        }
        return wVar.a(obj2, obj, obj3);
    }

    public static void a(androidx.fragment.app.a aVar, SparseArray<h> sparseArray, boolean z) {
        int size = aVar.f1439a.size();
        for (int i = 0; i < size; i++) {
            a(aVar, aVar.f1439a.get(i), sparseArray, false, z);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:100:0x008a, code lost:
    
        if (r0.mHidden == false) goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x0039, code lost:
    
        if (r0.mAdded != false) goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x008c, code lost:
    
        r9 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x006e, code lost:
    
        r9 = true;
     */
    /* JADX WARN: Removed duplicated region for block: B:26:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00a7 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00c7 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00d9 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:56:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static void a(androidx.fragment.app.a r8, androidx.fragment.app.t.a r9, android.util.SparseArray<androidx.fragment.app.u.h> r10, boolean r11, boolean r12) {
        /*
            Method dump skipped, instructions count: 228
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.u.a(androidx.fragment.app.a, androidx.fragment.app.t$a, android.util.SparseArray, boolean, boolean):void");
    }

    private static h a(h hVar, SparseArray<h> sparseArray, int i) {
        if (hVar != null) {
            return hVar;
        }
        h hVar2 = new h();
        sparseArray.put(i, hVar2);
        return hVar2;
    }
}
