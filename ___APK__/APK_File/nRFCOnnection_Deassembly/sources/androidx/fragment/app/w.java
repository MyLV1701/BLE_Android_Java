package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SuppressLint({"UnknownNullness"})
/* loaded from: classes.dex */
public abstract class w {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a implements Runnable {

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f1493b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ ArrayList f1494c;

        /* renamed from: d, reason: collision with root package name */
        final /* synthetic */ ArrayList f1495d;

        /* renamed from: e, reason: collision with root package name */
        final /* synthetic */ ArrayList f1496e;

        /* renamed from: f, reason: collision with root package name */
        final /* synthetic */ ArrayList f1497f;

        a(w wVar, int i, ArrayList arrayList, ArrayList arrayList2, ArrayList arrayList3, ArrayList arrayList4) {
            this.f1493b = i;
            this.f1494c = arrayList;
            this.f1495d = arrayList2;
            this.f1496e = arrayList3;
            this.f1497f = arrayList4;
        }

        @Override // java.lang.Runnable
        public void run() {
            for (int i = 0; i < this.f1493b; i++) {
                a.f.l.w.a((View) this.f1494c.get(i), (String) this.f1495d.get(i));
                a.f.l.w.a((View) this.f1496e.get(i), (String) this.f1497f.get(i));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class b implements Runnable {

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ ArrayList f1498b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ Map f1499c;

        b(w wVar, ArrayList arrayList, Map map) {
            this.f1498b = arrayList;
            this.f1499c = map;
        }

        @Override // java.lang.Runnable
        public void run() {
            int size = this.f1498b.size();
            for (int i = 0; i < size; i++) {
                View view = (View) this.f1498b.get(i);
                String x = a.f.l.w.x(view);
                if (x != null) {
                    a.f.l.w.a(view, w.a((Map<String, String>) this.f1499c, x));
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class c implements Runnable {

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ ArrayList f1500b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ Map f1501c;

        c(w wVar, ArrayList arrayList, Map map) {
            this.f1500b = arrayList;
            this.f1501c = map;
        }

        @Override // java.lang.Runnable
        public void run() {
            int size = this.f1500b.size();
            for (int i = 0; i < size; i++) {
                View view = (View) this.f1500b.get(i);
                a.f.l.w.a(view, (String) this.f1501c.get(a.f.l.w.x(view)));
            }
        }
    }

    public abstract Object a(Object obj, Object obj2, Object obj3);

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(View view, Rect rect) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        rect.set(iArr[0], iArr[1], iArr[0] + view.getWidth(), iArr[1] + view.getHeight());
    }

    public abstract void a(ViewGroup viewGroup, Object obj);

    public abstract void a(Object obj, Rect rect);

    public abstract void a(Object obj, View view);

    public abstract void a(Object obj, View view, ArrayList<View> arrayList);

    public abstract void a(Object obj, Object obj2, ArrayList<View> arrayList, Object obj3, ArrayList<View> arrayList2, Object obj4, ArrayList<View> arrayList3);

    public abstract void a(Object obj, ArrayList<View> arrayList);

    public abstract void a(Object obj, ArrayList<View> arrayList, ArrayList<View> arrayList2);

    public abstract boolean a(Object obj);

    public abstract Object b(Object obj);

    public abstract Object b(Object obj, Object obj2, Object obj3);

    public abstract void b(Object obj, View view);

    public abstract void b(Object obj, View view, ArrayList<View> arrayList);

    public abstract void b(Object obj, ArrayList<View> arrayList, ArrayList<View> arrayList2);

    public abstract Object c(Object obj);

    public abstract void c(Object obj, View view);

    /* JADX INFO: Access modifiers changed from: package-private */
    public ArrayList<String> a(ArrayList<View> arrayList) {
        ArrayList<String> arrayList2 = new ArrayList<>();
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            View view = arrayList.get(i);
            arrayList2.add(a.f.l.w.x(view));
            a.f.l.w.a(view, (String) null);
        }
        return arrayList2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(View view, ArrayList<View> arrayList, ArrayList<View> arrayList2, ArrayList<String> arrayList3, Map<String, String> map) {
        int size = arrayList2.size();
        ArrayList arrayList4 = new ArrayList();
        for (int i = 0; i < size; i++) {
            View view2 = arrayList.get(i);
            String x = a.f.l.w.x(view2);
            arrayList4.add(x);
            if (x != null) {
                a.f.l.w.a(view2, (String) null);
                String str = map.get(x);
                int i2 = 0;
                while (true) {
                    if (i2 >= size) {
                        break;
                    }
                    if (str.equals(arrayList3.get(i2))) {
                        a.f.l.w.a(arrayList2.get(i2), x);
                        break;
                    }
                    i2++;
                }
            }
        }
        a.f.l.s.a(view, new a(this, size, arrayList2, arrayList3, arrayList, arrayList4));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(ArrayList<View> arrayList, View view) {
        if (view.getVisibility() == 0) {
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                if (a.f.l.y.a(viewGroup)) {
                    arrayList.add(viewGroup);
                    return;
                }
                int childCount = viewGroup.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    a(arrayList, viewGroup.getChildAt(i));
                }
                return;
            }
            arrayList.add(view);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(Map<String, View> map, View view) {
        if (view.getVisibility() == 0) {
            String x = a.f.l.w.x(view);
            if (x != null) {
                map.put(x, view);
            }
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                int childCount = viewGroup.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    a(map, viewGroup.getChildAt(i));
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(View view, ArrayList<View> arrayList, Map<String, String> map) {
        a.f.l.s.a(view, new b(this, arrayList, map));
    }

    public void a(Fragment fragment, Object obj, a.f.h.a aVar, Runnable runnable) {
        runnable.run();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(ViewGroup viewGroup, ArrayList<View> arrayList, Map<String, String> map) {
        a.f.l.s.a(viewGroup, new c(this, arrayList, map));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void a(List<View> list, View view) {
        int size = list.size();
        if (a(list, view, size)) {
            return;
        }
        list.add(view);
        for (int i = size; i < list.size(); i++) {
            View view2 = list.get(i);
            if (view2 instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view2;
                int childCount = viewGroup.getChildCount();
                for (int i2 = 0; i2 < childCount; i2++) {
                    View childAt = viewGroup.getChildAt(i2);
                    if (!a(list, childAt, size)) {
                        list.add(childAt);
                    }
                }
            }
        }
    }

    private static boolean a(List<View> list, View view, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            if (list.get(i2) == view) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static boolean a(List list) {
        return list == null || list.isEmpty();
    }

    static String a(Map<String, String> map, String str) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (str.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }
}
