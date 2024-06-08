package androidx.fragment.app;

import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.g;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

/* loaded from: classes.dex */
public abstract class t {

    /* renamed from: b, reason: collision with root package name */
    int f1440b;

    /* renamed from: c, reason: collision with root package name */
    int f1441c;

    /* renamed from: d, reason: collision with root package name */
    int f1442d;

    /* renamed from: e, reason: collision with root package name */
    int f1443e;

    /* renamed from: f, reason: collision with root package name */
    int f1444f;
    boolean g;
    String i;
    int j;
    CharSequence k;
    int l;
    CharSequence m;
    ArrayList<String> n;
    ArrayList<String> o;
    ArrayList<Runnable> q;

    /* renamed from: a, reason: collision with root package name */
    ArrayList<a> f1439a = new ArrayList<>();
    boolean h = true;
    boolean p = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class a {

        /* renamed from: a, reason: collision with root package name */
        int f1445a;

        /* renamed from: b, reason: collision with root package name */
        Fragment f1446b;

        /* renamed from: c, reason: collision with root package name */
        int f1447c;

        /* renamed from: d, reason: collision with root package name */
        int f1448d;

        /* renamed from: e, reason: collision with root package name */
        int f1449e;

        /* renamed from: f, reason: collision with root package name */
        int f1450f;
        g.b g;
        g.b h;

        /* JADX INFO: Access modifiers changed from: package-private */
        public a() {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public a(int i, Fragment fragment) {
            this.f1445a = i;
            this.f1446b = fragment;
            g.b bVar = g.b.RESUMED;
            this.g = bVar;
            this.h = bVar;
        }

        a(int i, Fragment fragment, g.b bVar) {
            this.f1445a = i;
            this.f1446b = fragment;
            this.g = fragment.mMaxState;
            this.h = bVar;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public t(h hVar, ClassLoader classLoader) {
    }

    public abstract int a();

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(a aVar) {
        this.f1439a.add(aVar);
        aVar.f1447c = this.f1440b;
        aVar.f1448d = this.f1441c;
        aVar.f1449e = this.f1442d;
        aVar.f1450f = this.f1443e;
    }

    public abstract int b();

    public t b(int i, Fragment fragment) {
        b(i, fragment, null);
        return this;
    }

    public t c(Fragment fragment) {
        a(new a(3, fragment));
        return this;
    }

    public abstract void c();

    public abstract void d();

    public t e() {
        if (!this.g) {
            this.h = false;
            return this;
        }
        throw new IllegalStateException("This transaction is already being added to the back stack");
    }

    public t b(int i, Fragment fragment, String str) {
        if (i != 0) {
            a(i, fragment, str, 2);
            return this;
        }
        throw new IllegalArgumentException("Must use non-zero containerViewId");
    }

    public t b(Fragment fragment) {
        a(new a(6, fragment));
        return this;
    }

    public t a(Fragment fragment, String str) {
        a(0, fragment, str, 1);
        return this;
    }

    public t a(int i, Fragment fragment) {
        a(i, fragment, null, 1);
        return this;
    }

    public t a(int i, Fragment fragment, String str) {
        a(i, fragment, str, 1);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public t a(ViewGroup viewGroup, Fragment fragment, String str) {
        fragment.mContainer = viewGroup;
        a(viewGroup.getId(), fragment, str);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(int i, Fragment fragment, String str, int i2) {
        Class<?> cls = fragment.getClass();
        int modifiers = cls.getModifiers();
        if (!cls.isAnonymousClass() && Modifier.isPublic(modifiers) && (!cls.isMemberClass() || Modifier.isStatic(modifiers))) {
            if (str != null) {
                String str2 = fragment.mTag;
                if (str2 != null && !str.equals(str2)) {
                    throw new IllegalStateException("Can't change tag of fragment " + fragment + ": was " + fragment.mTag + " now " + str);
                }
                fragment.mTag = str;
            }
            if (i != 0) {
                if (i != -1) {
                    int i3 = fragment.mFragmentId;
                    if (i3 != 0 && i3 != i) {
                        throw new IllegalStateException("Can't change container ID of fragment " + fragment + ": was " + fragment.mFragmentId + " now " + i);
                    }
                    fragment.mFragmentId = i;
                    fragment.mContainerId = i;
                } else {
                    throw new IllegalArgumentException("Can't add fragment " + fragment + " with tag " + str + " to container view with no id");
                }
            }
            a(new a(i2, fragment));
            return;
        }
        throw new IllegalStateException("Fragment " + cls.getCanonicalName() + " must be a public static class to be  properly recreated from instance state.");
    }

    public t a(Fragment fragment) {
        a(new a(7, fragment));
        return this;
    }

    public t a(Fragment fragment, g.b bVar) {
        a(new a(10, fragment, bVar));
        return this;
    }

    public t a(View view, String str) {
        if (u.b()) {
            String x = a.f.l.w.x(view);
            if (x != null) {
                if (this.n == null) {
                    this.n = new ArrayList<>();
                    this.o = new ArrayList<>();
                } else if (!this.o.contains(str)) {
                    if (this.n.contains(x)) {
                        throw new IllegalArgumentException("A shared element with the source name '" + x + "' has already been added to the transaction.");
                    }
                } else {
                    throw new IllegalArgumentException("A shared element with the target name '" + str + "' has already been added to the transaction.");
                }
                this.n.add(x);
                this.o.add(str);
            } else {
                throw new IllegalArgumentException("Unique transitionNames are required for all sharedElements");
            }
        }
        return this;
    }

    public t a(String str) {
        if (this.h) {
            this.g = true;
            this.i = str;
            return this;
        }
        throw new IllegalStateException("This FragmentTransaction is not allowed to be added to the back stack.");
    }

    public t a(boolean z) {
        this.p = z;
        return this;
    }
}
