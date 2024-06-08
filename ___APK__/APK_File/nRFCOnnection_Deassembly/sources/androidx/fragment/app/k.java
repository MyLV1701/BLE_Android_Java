package androidx.fragment.app;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import androidx.fragment.app.l;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class k {

    /* renamed from: a, reason: collision with root package name */
    private final CopyOnWriteArrayList<a> f1398a = new CopyOnWriteArrayList<>();

    /* renamed from: b, reason: collision with root package name */
    private final l f1399b;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class a {

        /* renamed from: a, reason: collision with root package name */
        final l.g f1400a;

        /* renamed from: b, reason: collision with root package name */
        final boolean f1401b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public k(l lVar) {
        this.f1399b = lVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(Fragment fragment, Context context, boolean z) {
        Fragment s = this.f1399b.s();
        if (s != null) {
            s.getParentFragmentManager().r().a(fragment, context, true);
        }
        Iterator<a> it = this.f1398a.iterator();
        while (it.hasNext()) {
            a next = it.next();
            if (!z || next.f1401b) {
                next.f1400a.a(this.f1399b, fragment, context);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(Fragment fragment, Context context, boolean z) {
        Fragment s = this.f1399b.s();
        if (s != null) {
            s.getParentFragmentManager().r().b(fragment, context, true);
        }
        Iterator<a> it = this.f1398a.iterator();
        while (it.hasNext()) {
            a next = it.next();
            if (!z || next.f1401b) {
                next.f1400a.b(this.f1399b, fragment, context);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void c(Fragment fragment, Bundle bundle, boolean z) {
        Fragment s = this.f1399b.s();
        if (s != null) {
            s.getParentFragmentManager().r().c(fragment, bundle, true);
        }
        Iterator<a> it = this.f1398a.iterator();
        while (it.hasNext()) {
            a next = it.next();
            if (!z || next.f1401b) {
                next.f1400a.c(this.f1399b, fragment, bundle);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void d(Fragment fragment, boolean z) {
        Fragment s = this.f1399b.s();
        if (s != null) {
            s.getParentFragmentManager().r().d(fragment, true);
        }
        Iterator<a> it = this.f1398a.iterator();
        while (it.hasNext()) {
            a next = it.next();
            if (!z || next.f1401b) {
                next.f1400a.d(this.f1399b, fragment);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void e(Fragment fragment, boolean z) {
        Fragment s = this.f1399b.s();
        if (s != null) {
            s.getParentFragmentManager().r().e(fragment, true);
        }
        Iterator<a> it = this.f1398a.iterator();
        while (it.hasNext()) {
            a next = it.next();
            if (!z || next.f1401b) {
                next.f1400a.e(this.f1399b, fragment);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void f(Fragment fragment, boolean z) {
        Fragment s = this.f1399b.s();
        if (s != null) {
            s.getParentFragmentManager().r().f(fragment, true);
        }
        Iterator<a> it = this.f1398a.iterator();
        while (it.hasNext()) {
            a next = it.next();
            if (!z || next.f1401b) {
                next.f1400a.f(this.f1399b, fragment);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void g(Fragment fragment, boolean z) {
        Fragment s = this.f1399b.s();
        if (s != null) {
            s.getParentFragmentManager().r().g(fragment, true);
        }
        Iterator<a> it = this.f1398a.iterator();
        while (it.hasNext()) {
            a next = it.next();
            if (!z || next.f1401b) {
                next.f1400a.g(this.f1399b, fragment);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(Fragment fragment, Bundle bundle, boolean z) {
        Fragment s = this.f1399b.s();
        if (s != null) {
            s.getParentFragmentManager().r().a(fragment, bundle, true);
        }
        Iterator<a> it = this.f1398a.iterator();
        while (it.hasNext()) {
            a next = it.next();
            if (!z || next.f1401b) {
                next.f1400a.a(this.f1399b, fragment, bundle);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(Fragment fragment, Bundle bundle, boolean z) {
        Fragment s = this.f1399b.s();
        if (s != null) {
            s.getParentFragmentManager().r().b(fragment, bundle, true);
        }
        Iterator<a> it = this.f1398a.iterator();
        while (it.hasNext()) {
            a next = it.next();
            if (!z || next.f1401b) {
                next.f1400a.b(this.f1399b, fragment, bundle);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void c(Fragment fragment, boolean z) {
        Fragment s = this.f1399b.s();
        if (s != null) {
            s.getParentFragmentManager().r().c(fragment, true);
        }
        Iterator<a> it = this.f1398a.iterator();
        while (it.hasNext()) {
            a next = it.next();
            if (!z || next.f1401b) {
                next.f1400a.c(this.f1399b, fragment);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void d(Fragment fragment, Bundle bundle, boolean z) {
        Fragment s = this.f1399b.s();
        if (s != null) {
            s.getParentFragmentManager().r().d(fragment, bundle, true);
        }
        Iterator<a> it = this.f1398a.iterator();
        while (it.hasNext()) {
            a next = it.next();
            if (!z || next.f1401b) {
                next.f1400a.d(this.f1399b, fragment, bundle);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(Fragment fragment, View view, Bundle bundle, boolean z) {
        Fragment s = this.f1399b.s();
        if (s != null) {
            s.getParentFragmentManager().r().a(fragment, view, bundle, true);
        }
        Iterator<a> it = this.f1398a.iterator();
        while (it.hasNext()) {
            a next = it.next();
            if (!z || next.f1401b) {
                next.f1400a.a(this.f1399b, fragment, view, bundle);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(Fragment fragment, boolean z) {
        Fragment s = this.f1399b.s();
        if (s != null) {
            s.getParentFragmentManager().r().b(fragment, true);
        }
        Iterator<a> it = this.f1398a.iterator();
        while (it.hasNext()) {
            a next = it.next();
            if (!z || next.f1401b) {
                next.f1400a.b(this.f1399b, fragment);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(Fragment fragment, boolean z) {
        Fragment s = this.f1399b.s();
        if (s != null) {
            s.getParentFragmentManager().r().a(fragment, true);
        }
        Iterator<a> it = this.f1398a.iterator();
        while (it.hasNext()) {
            a next = it.next();
            if (!z || next.f1401b) {
                next.f1400a.a(this.f1399b, fragment);
            }
        }
    }
}
