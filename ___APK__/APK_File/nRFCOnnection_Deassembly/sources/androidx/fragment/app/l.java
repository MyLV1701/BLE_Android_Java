package androidx.fragment.app;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import androidx.activity.OnBackPressedDispatcher;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.e;
import androidx.fragment.app.u;
import androidx.lifecycle.g;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import no.nordicsemi.android.dfu.DfuBaseService;

/* loaded from: classes.dex */
public abstract class l {
    private static boolean G = false;
    private ArrayList<androidx.fragment.app.a> A;
    private ArrayList<Boolean> B;
    private ArrayList<Fragment> C;
    private ArrayList<k> D;
    private o E;

    /* renamed from: b, reason: collision with root package name */
    private boolean f1403b;

    /* renamed from: e, reason: collision with root package name */
    ArrayList<androidx.fragment.app.a> f1406e;

    /* renamed from: f, reason: collision with root package name */
    private ArrayList<Fragment> f1407f;
    private OnBackPressedDispatcher h;
    private ArrayList<h> k;
    androidx.fragment.app.i<?> p;
    androidx.fragment.app.f q;
    private Fragment r;
    Fragment s;
    private boolean v;
    private boolean w;
    private boolean x;
    private boolean y;
    private boolean z;

    /* renamed from: a, reason: collision with root package name */
    private final ArrayList<i> f1402a = new ArrayList<>();

    /* renamed from: c, reason: collision with root package name */
    final ArrayList<Fragment> f1404c = new ArrayList<>();

    /* renamed from: d, reason: collision with root package name */
    final HashMap<String, r> f1405d = new HashMap<>();
    private final androidx.fragment.app.j g = new androidx.fragment.app.j(this);
    private final androidx.activity.b i = new a(false);
    private final AtomicInteger j = new AtomicInteger();
    private ConcurrentHashMap<Fragment, HashSet<a.f.h.a>> l = new ConcurrentHashMap<>();
    private final u.g m = new b();
    private final androidx.fragment.app.k n = new androidx.fragment.app.k(this);
    int o = -1;
    private androidx.fragment.app.h t = null;
    private androidx.fragment.app.h u = new c();
    private Runnable F = new d();

    /* loaded from: classes.dex */
    class a extends androidx.activity.b {
        a(boolean z) {
            super(z);
        }

        @Override // androidx.activity.b
        public void a() {
            l.this.u();
        }
    }

    /* loaded from: classes.dex */
    class b implements u.g {
        b() {
        }

        @Override // androidx.fragment.app.u.g
        public void a(Fragment fragment, a.f.h.a aVar) {
            if (aVar.c()) {
                return;
            }
            l.this.b(fragment, aVar);
        }

        @Override // androidx.fragment.app.u.g
        public void b(Fragment fragment, a.f.h.a aVar) {
            l.this.a(fragment, aVar);
        }
    }

    /* loaded from: classes.dex */
    class c extends androidx.fragment.app.h {
        c() {
        }

        @Override // androidx.fragment.app.h
        public Fragment a(ClassLoader classLoader, String str) {
            androidx.fragment.app.i<?> iVar = l.this.p;
            return iVar.a(iVar.c(), str, (Bundle) null);
        }
    }

    /* loaded from: classes.dex */
    class d implements Runnable {
        d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            l.this.c(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class e extends AnimatorListenerAdapter {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ ViewGroup f1412a;

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ View f1413b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ Fragment f1414c;

        e(l lVar, ViewGroup viewGroup, View view, Fragment fragment) {
            this.f1412a = viewGroup;
            this.f1413b = view;
            this.f1414c = fragment;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f1412a.endViewTransition(this.f1413b);
            animator.removeListener(this);
            Fragment fragment = this.f1414c;
            View view = fragment.mView;
            if (view == null || !fragment.mHidden) {
                return;
            }
            view.setVisibility(8);
        }
    }

    /* loaded from: classes.dex */
    public interface f {
    }

    /* loaded from: classes.dex */
    public static abstract class g {
        public abstract void a(l lVar, Fragment fragment);

        public abstract void a(l lVar, Fragment fragment, Context context);

        public abstract void a(l lVar, Fragment fragment, Bundle bundle);

        public abstract void a(l lVar, Fragment fragment, View view, Bundle bundle);

        public abstract void b(l lVar, Fragment fragment);

        public abstract void b(l lVar, Fragment fragment, Context context);

        public abstract void b(l lVar, Fragment fragment, Bundle bundle);

        public abstract void c(l lVar, Fragment fragment);

        public abstract void c(l lVar, Fragment fragment, Bundle bundle);

        public abstract void d(l lVar, Fragment fragment);

        public abstract void d(l lVar, Fragment fragment, Bundle bundle);

        public abstract void e(l lVar, Fragment fragment);

        public abstract void f(l lVar, Fragment fragment);

        public abstract void g(l lVar, Fragment fragment);
    }

    /* loaded from: classes.dex */
    public interface h {
        void onBackStackChanged();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface i {
        boolean a(ArrayList<androidx.fragment.app.a> arrayList, ArrayList<Boolean> arrayList2);
    }

    /* loaded from: classes.dex */
    private class j implements i {

        /* renamed from: a, reason: collision with root package name */
        final String f1415a;

        /* renamed from: b, reason: collision with root package name */
        final int f1416b;

        /* renamed from: c, reason: collision with root package name */
        final int f1417c;

        j(String str, int i, int i2) {
            this.f1415a = str;
            this.f1416b = i;
            this.f1417c = i2;
        }

        @Override // androidx.fragment.app.l.i
        public boolean a(ArrayList<androidx.fragment.app.a> arrayList, ArrayList<Boolean> arrayList2) {
            Fragment fragment = l.this.s;
            if (fragment == null || this.f1416b >= 0 || this.f1415a != null || !fragment.getChildFragmentManager().z()) {
                return l.this.a(arrayList, arrayList2, this.f1415a, this.f1416b, this.f1417c);
            }
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class k implements Fragment.f {

        /* renamed from: a, reason: collision with root package name */
        final boolean f1419a;

        /* renamed from: b, reason: collision with root package name */
        final androidx.fragment.app.a f1420b;

        /* renamed from: c, reason: collision with root package name */
        private int f1421c;

        k(androidx.fragment.app.a aVar, boolean z) {
            this.f1419a = z;
            this.f1420b = aVar;
        }

        @Override // androidx.fragment.app.Fragment.f
        public void a() {
            this.f1421c--;
            if (this.f1421c != 0) {
                return;
            }
            this.f1420b.r.B();
        }

        @Override // androidx.fragment.app.Fragment.f
        public void b() {
            this.f1421c++;
        }

        void c() {
            androidx.fragment.app.a aVar = this.f1420b;
            aVar.r.a(aVar, this.f1419a, false, false);
        }

        void d() {
            boolean z = this.f1421c > 0;
            l lVar = this.f1420b.r;
            int size = lVar.f1404c.size();
            for (int i = 0; i < size; i++) {
                Fragment fragment = lVar.f1404c.get(i);
                fragment.setOnStartEnterTransitionListener(null);
                if (z && fragment.isPostponed()) {
                    fragment.startPostponedEnterTransition();
                }
            }
            androidx.fragment.app.a aVar = this.f1420b;
            aVar.r.a(aVar, this.f1419a, !z, true);
        }

        public boolean e() {
            return this.f1421c == 0;
        }
    }

    private void C() {
        this.f1405d.values().removeAll(Collections.singleton(null));
    }

    private void D() {
        if (w()) {
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
        }
    }

    private void E() {
        this.f1403b = false;
        this.B.clear();
        this.A.clear();
    }

    private void F() {
        if (this.z) {
            this.z = false;
            J();
        }
    }

    private void G() {
        if (this.l.isEmpty()) {
            return;
        }
        for (Fragment fragment : this.l.keySet()) {
            q(fragment);
            a(fragment, fragment.getStateAfterAnimating());
        }
    }

    private void H() {
        if (this.D != null) {
            while (!this.D.isEmpty()) {
                this.D.remove(0).d();
            }
        }
    }

    private void I() {
        if (this.k != null) {
            for (int i2 = 0; i2 < this.k.size(); i2++) {
                this.k.get(i2).onBackStackChanged();
            }
        }
    }

    private void J() {
        for (r rVar : this.f1405d.values()) {
            if (rVar != null) {
                k(rVar.e());
            }
        }
    }

    private void K() {
        synchronized (this.f1402a) {
            if (!this.f1402a.isEmpty()) {
                this.i.a(true);
            } else {
                this.i.a(n() > 0 && g(this.r));
            }
        }
    }

    private void a(RuntimeException runtimeException) {
        Log.e("FragmentManager", runtimeException.getMessage());
        Log.e("FragmentManager", "Activity state:");
        PrintWriter printWriter = new PrintWriter(new a.f.k.b("FragmentManager"));
        androidx.fragment.app.i<?> iVar = this.p;
        if (iVar != null) {
            try {
                iVar.a("  ", (FileDescriptor) null, printWriter, new String[0]);
                throw runtimeException;
            } catch (Exception e2) {
                Log.e("FragmentManager", "Failed dumping state", e2);
                throw runtimeException;
            }
        }
        try {
            a("  ", (FileDescriptor) null, printWriter, new String[0]);
            throw runtimeException;
        } catch (Exception e3) {
            Log.e("FragmentManager", "Failed dumping state", e3);
            throw runtimeException;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean d(int i2) {
        return G || Log.isLoggable("FragmentManager", i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int e(int i2) {
        if (i2 == 4097) {
            return 8194;
        }
        if (i2 == 4099) {
            return DfuBaseService.ERROR_FILE_INVALID;
        }
        if (i2 != 8194) {
            return 0;
        }
        return DfuBaseService.ERROR_FILE_NOT_FOUND;
    }

    private void q(Fragment fragment) {
        HashSet<a.f.h.a> hashSet = this.l.get(fragment);
        if (hashSet != null) {
            Iterator<a.f.h.a> it = hashSet.iterator();
            while (it.hasNext()) {
                it.next().a();
            }
            hashSet.clear();
            s(fragment);
            this.l.remove(fragment);
        }
    }

    private void r(Fragment fragment) {
        Animator animator;
        if (fragment.mView != null) {
            e.d a2 = androidx.fragment.app.e.a(this.p.c(), this.q, fragment, !fragment.mHidden);
            if (a2 != null && (animator = a2.f1385b) != null) {
                animator.setTarget(fragment.mView);
                if (fragment.mHidden) {
                    if (fragment.isHideReplaced()) {
                        fragment.setHideReplaced(false);
                    } else {
                        ViewGroup viewGroup = fragment.mContainer;
                        View view = fragment.mView;
                        viewGroup.startViewTransition(view);
                        a2.f1385b.addListener(new e(this, viewGroup, view, fragment));
                    }
                } else {
                    fragment.mView.setVisibility(0);
                }
                a2.f1385b.start();
            } else {
                if (a2 != null) {
                    fragment.mView.startAnimation(a2.f1384a);
                    a2.f1384a.start();
                }
                fragment.mView.setVisibility((!fragment.mHidden || fragment.isHideReplaced()) ? 0 : 8);
                if (fragment.isHideReplaced()) {
                    fragment.setHideReplaced(false);
                }
            }
        }
        if (fragment.mAdded && x(fragment)) {
            this.v = true;
        }
        fragment.mHiddenChanged = false;
        fragment.onHiddenChanged(fragment.mHidden);
    }

    private void s(Fragment fragment) {
        fragment.performDestroyView();
        this.n.g(fragment, false);
        fragment.mContainer = null;
        fragment.mView = null;
        fragment.mViewLifecycleOwner = null;
        fragment.mViewLifecycleOwnerLiveData.b((androidx.lifecycle.o<androidx.lifecycle.j>) null);
        fragment.mInLayout = false;
    }

    private void t(Fragment fragment) {
        if (fragment == null || !fragment.equals(a(fragment.mWho))) {
            return;
        }
        fragment.performPrimaryNavigationFragmentChanged();
    }

    private o v(Fragment fragment) {
        return this.E.c(fragment);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Parcelable A() {
        ArrayList<String> arrayList;
        int size;
        H();
        G();
        c(true);
        this.w = true;
        androidx.fragment.app.b[] bVarArr = null;
        if (this.f1405d.isEmpty()) {
            return null;
        }
        ArrayList<q> arrayList2 = new ArrayList<>(this.f1405d.size());
        boolean z = false;
        for (r rVar : this.f1405d.values()) {
            if (rVar != null) {
                Fragment e2 = rVar.e();
                if (e2.mFragmentManager == this) {
                    q j2 = rVar.j();
                    arrayList2.add(j2);
                    if (d(2)) {
                        Log.v("FragmentManager", "Saved state of " + e2 + ": " + j2.n);
                    }
                    z = true;
                } else {
                    a(new IllegalStateException("Failure saving state: active " + e2 + " was removed from the FragmentManager"));
                    throw null;
                }
            }
        }
        if (!z) {
            if (d(2)) {
                Log.v("FragmentManager", "saveAllState: no fragments!");
            }
            return null;
        }
        int size2 = this.f1404c.size();
        if (size2 > 0) {
            arrayList = new ArrayList<>(size2);
            Iterator<Fragment> it = this.f1404c.iterator();
            while (it.hasNext()) {
                Fragment next = it.next();
                arrayList.add(next.mWho);
                if (next.mFragmentManager == this) {
                    if (d(2)) {
                        Log.v("FragmentManager", "saveAllState: adding fragment (" + next.mWho + "): " + next);
                    }
                } else {
                    a(new IllegalStateException("Failure saving state: active " + next + " was removed from the FragmentManager"));
                    throw null;
                }
            }
        } else {
            arrayList = null;
        }
        ArrayList<androidx.fragment.app.a> arrayList3 = this.f1406e;
        if (arrayList3 != null && (size = arrayList3.size()) > 0) {
            bVarArr = new androidx.fragment.app.b[size];
            for (int i2 = 0; i2 < size; i2++) {
                bVarArr[i2] = new androidx.fragment.app.b(this.f1406e.get(i2));
                if (d(2)) {
                    Log.v("FragmentManager", "saveAllState: adding back stack #" + i2 + ": " + this.f1406e.get(i2));
                }
            }
        }
        n nVar = new n();
        nVar.f1422b = arrayList2;
        nVar.f1423c = arrayList;
        nVar.f1424d = bVarArr;
        nVar.f1425e = this.j.get();
        Fragment fragment = this.s;
        if (fragment != null) {
            nVar.f1426f = fragment.mWho;
        }
        return nVar;
    }

    void B() {
        synchronized (this.f1402a) {
            boolean z = (this.D == null || this.D.isEmpty()) ? false : true;
            boolean z2 = this.f1402a.size() == 1;
            if (z || z2) {
                this.p.d().removeCallbacks(this.F);
                this.p.d().post(this.F);
                K();
            }
        }
    }

    public t b() {
        return new androidx.fragment.app.a(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void c(Fragment fragment) {
        if (d(2)) {
            Log.v("FragmentManager", "attach: " + fragment);
        }
        if (fragment.mDetached) {
            fragment.mDetached = false;
            if (fragment.mAdded) {
                return;
            }
            if (!this.f1404c.contains(fragment)) {
                if (d(2)) {
                    Log.v("FragmentManager", "add from attach: " + fragment);
                }
                synchronized (this.f1404c) {
                    this.f1404c.add(fragment);
                }
                fragment.mAdded = true;
                if (x(fragment)) {
                    this.v = true;
                    return;
                }
                return;
            }
            throw new IllegalStateException("Fragment already added: " + fragment);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public androidx.lifecycle.w e(Fragment fragment) {
        return this.E.d(fragment);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void f(Fragment fragment) {
        if (d(2)) {
            Log.v("FragmentManager", "hide: " + fragment);
        }
        if (fragment.mHidden) {
            return;
        }
        fragment.mHidden = true;
        fragment.mHiddenChanged = true ^ fragment.mHiddenChanged;
        z(fragment);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean g(Fragment fragment) {
        if (fragment == null) {
            return true;
        }
        l lVar = fragment.mFragmentManager;
        return fragment.equals(lVar.t()) && g(lVar.r);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void h(Fragment fragment) {
        if (a(fragment.mWho) != null) {
            return;
        }
        r rVar = new r(this.n, fragment);
        rVar.a(this.p.c().getClassLoader());
        this.f1405d.put(fragment.mWho, rVar);
        if (fragment.mRetainInstanceChangedWhileDetached) {
            if (fragment.mRetainInstance) {
                b(fragment);
            } else {
                m(fragment);
            }
            fragment.mRetainInstanceChangedWhileDetached = false;
        }
        if (d(2)) {
            Log.v("FragmentManager", "Added fragment to active set " + fragment);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void i(Fragment fragment) {
        if (!this.f1405d.containsKey(fragment.mWho)) {
            if (d(3)) {
                Log.d("FragmentManager", "Ignoring moving " + fragment + " to state " + this.o + "since it is not added to " + this);
                return;
            }
            return;
        }
        j(fragment);
        if (fragment.mView != null) {
            Fragment u = u(fragment);
            if (u != null) {
                View view = u.mView;
                ViewGroup viewGroup = fragment.mContainer;
                int indexOfChild = viewGroup.indexOfChild(view);
                int indexOfChild2 = viewGroup.indexOfChild(fragment.mView);
                if (indexOfChild2 < indexOfChild) {
                    viewGroup.removeViewAt(indexOfChild2);
                    viewGroup.addView(fragment.mView, indexOfChild);
                }
            }
            if (fragment.mIsNewlyAdded && fragment.mContainer != null) {
                float f2 = fragment.mPostponedAlpha;
                if (f2 > 0.0f) {
                    fragment.mView.setAlpha(f2);
                }
                fragment.mPostponedAlpha = 0.0f;
                fragment.mIsNewlyAdded = false;
                e.d a2 = androidx.fragment.app.e.a(this.p.c(), this.q, fragment, true);
                if (a2 != null) {
                    Animation animation = a2.f1384a;
                    if (animation != null) {
                        fragment.mView.startAnimation(animation);
                    } else {
                        a2.f1385b.setTarget(fragment.mView);
                        a2.f1385b.start();
                    }
                }
            }
        }
        if (fragment.mHiddenChanged) {
            r(fragment);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void j(Fragment fragment) {
        a(fragment, this.o);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void k(Fragment fragment) {
        if (fragment.mDeferStart) {
            if (this.f1403b) {
                this.z = true;
            } else {
                fragment.mDeferStart = false;
                a(fragment, this.o);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void l(Fragment fragment) {
        if (d(2)) {
            Log.v("FragmentManager", "remove: " + fragment + " nesting=" + fragment.mBackStackNesting);
        }
        boolean z = !fragment.isInBackStack();
        if (!fragment.mDetached || z) {
            synchronized (this.f1404c) {
                this.f1404c.remove(fragment);
            }
            if (x(fragment)) {
                this.v = true;
            }
            fragment.mAdded = false;
            fragment.mRemoving = true;
            z(fragment);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void m(Fragment fragment) {
        if (w()) {
            if (d(2)) {
                Log.v("FragmentManager", "Ignoring removeRetainedFragment as the state is already saved");
            }
        } else if (this.E.e(fragment) && d(2)) {
            Log.v("FragmentManager", "Updating retained Fragments: Removed " + fragment);
        }
    }

    public int n() {
        ArrayList<androidx.fragment.app.a> arrayList = this.f1406e;
        if (arrayList != null) {
            return arrayList.size();
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void o(Fragment fragment) {
        if (fragment != null && (!fragment.equals(a(fragment.mWho)) || (fragment.mHost != null && fragment.mFragmentManager != this))) {
            throw new IllegalArgumentException("Fragment " + fragment + " is not an active fragment of FragmentManager " + this);
        }
        Fragment fragment2 = this.s;
        this.s = fragment;
        t(fragment2);
        t(this.s);
    }

    public List<Fragment> p() {
        List<Fragment> list;
        if (this.f1404c.isEmpty()) {
            return Collections.emptyList();
        }
        synchronized (this.f1404c) {
            list = (List) this.f1404c.clone();
        }
        return list;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("FragmentManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        Fragment fragment = this.r;
        if (fragment != null) {
            sb.append(fragment.getClass().getSimpleName());
            sb.append("{");
            sb.append(Integer.toHexString(System.identityHashCode(this.r)));
            sb.append("}");
        } else {
            sb.append(this.p.getClass().getSimpleName());
            sb.append("{");
            sb.append(Integer.toHexString(System.identityHashCode(this.p)));
            sb.append("}");
        }
        sb.append("}}");
        return sb.toString();
    }

    void u() {
        c(true);
        if (this.i.b()) {
            z();
        } else {
            this.h.a();
        }
    }

    public boolean w() {
        return this.w || this.x;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void x() {
        this.w = false;
        this.x = false;
        int size = this.f1404c.size();
        for (int i2 = 0; i2 < size; i2++) {
            Fragment fragment = this.f1404c.get(i2);
            if (fragment != null) {
                fragment.noteStateNotSaved();
            }
        }
    }

    public void y() {
        a((i) new j(null, -1, 0), false);
    }

    public boolean z() {
        return a((String) null, -1, 0);
    }

    private ViewGroup w(Fragment fragment) {
        if (fragment.mContainerId > 0 && this.q.a()) {
            View a2 = this.q.a(fragment.mContainerId);
            if (a2 instanceof ViewGroup) {
                return (ViewGroup) a2;
            }
        }
        return null;
    }

    private void y(Fragment fragment) {
        if (a(fragment.mWho) == null) {
            return;
        }
        if (d(2)) {
            Log.v("FragmentManager", "Removed fragment from active set " + fragment);
        }
        for (r rVar : this.f1405d.values()) {
            if (rVar != null) {
                Fragment e2 = rVar.e();
                if (fragment.mWho.equals(e2.mTargetWho)) {
                    e2.mTarget = fragment;
                    e2.mTargetWho = null;
                }
            }
        }
        this.f1405d.put(fragment.mWho, null);
        m(fragment);
        String str = fragment.mTargetWho;
        if (str != null) {
            fragment.mTarget = a(str);
        }
    }

    private void z(Fragment fragment) {
        ViewGroup w = w(fragment);
        if (w != null) {
            if (w.getTag(a.i.b.visible_removing_fragment_view_tag) == null) {
                w.setTag(a.i.b.visible_removing_fragment_view_tag, fragment);
            }
            ((Fragment) w.getTag(a.i.b.visible_removing_fragment_view_tag)).setNextAnim(fragment.getNextAnim());
        }
    }

    public void b(h hVar) {
        ArrayList<h> arrayList = this.k;
        if (arrayList != null) {
            arrayList.remove(hVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void d(Fragment fragment) {
        if (d(2)) {
            Log.v("FragmentManager", "detach: " + fragment);
        }
        if (fragment.mDetached) {
            return;
        }
        fragment.mDetached = true;
        if (fragment.mAdded) {
            if (d(2)) {
                Log.v("FragmentManager", "remove from detach: " + fragment);
            }
            synchronized (this.f1404c) {
                this.f1404c.remove(fragment);
            }
            if (x(fragment)) {
                this.v = true;
            }
            fragment.mAdded = false;
            z(fragment);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void e() {
        this.w = false;
        this.x = false;
        c(1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void j() {
        K();
        t(this.s);
    }

    public Fragment.g n(Fragment fragment) {
        r rVar = this.f1405d.get(fragment.mWho);
        if (rVar != null && rVar.e().equals(fragment)) {
            return rVar.i();
        }
        a(new IllegalStateException("Fragment " + fragment + " is not currently in the FragmentManager"));
        throw null;
    }

    public boolean v() {
        return this.y;
    }

    public Fragment t() {
        return this.s;
    }

    void b(Fragment fragment, a.f.h.a aVar) {
        HashSet<a.f.h.a> hashSet = this.l.get(fragment);
        if (hashSet != null && hashSet.remove(aVar) && hashSet.isEmpty()) {
            this.l.remove(fragment);
            if (fragment.mState < 3) {
                s(fragment);
                a(fragment, fragment.getStateAfterAnimating());
            }
        }
    }

    private Fragment u(Fragment fragment) {
        ViewGroup viewGroup = fragment.mContainer;
        View view = fragment.mView;
        if (viewGroup != null && view != null) {
            for (int indexOf = this.f1404c.indexOf(fragment) - 1; indexOf >= 0; indexOf--) {
                Fragment fragment2 = this.f1404c.get(indexOf);
                if (fragment2.mContainer == viewGroup && fragment2.mView != null) {
                    return fragment2;
                }
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void g() {
        c(1);
    }

    private boolean x(Fragment fragment) {
        return (fragment.mHasMenu && fragment.mMenuVisible) || fragment.mChildFragmentManager.c();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void f() {
        this.y = true;
        c(true);
        G();
        c(-1);
        this.p = null;
        this.q = null;
        this.r = null;
        if (this.h != null) {
            this.i.c();
            this.h = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void k() {
        this.w = false;
        this.x = false;
        c(4);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void p(Fragment fragment) {
        if (d(2)) {
            Log.v("FragmentManager", "show: " + fragment);
        }
        if (fragment.mHidden) {
            fragment.mHidden = false;
            fragment.mHiddenChanged = !fragment.mHiddenChanged;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void m() {
        this.x = true;
        c(2);
    }

    public androidx.fragment.app.h o() {
        androidx.fragment.app.h hVar = this.t;
        if (hVar != null) {
            return hVar;
        }
        Fragment fragment = this.r;
        if (fragment != null) {
            return fragment.mFragmentManager.o();
        }
        return this.u;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public LayoutInflater.Factory2 q() {
        return this.g;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Fragment s() {
        return this.r;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(Fragment fragment) {
        if (w()) {
            if (d(2)) {
                Log.v("FragmentManager", "Ignoring addRetainedFragment as the state is already saved");
            }
        } else if (this.E.a(fragment) && d(2)) {
            Log.v("FragmentManager", "Updating retained Fragments: Added " + fragment);
        }
    }

    public void a(int i2, int i3) {
        if (i2 >= 0) {
            a((i) new j(null, i2, i3), false);
            return;
        }
        throw new IllegalArgumentException("Bad id: " + i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void h() {
        for (int i2 = 0; i2 < this.f1404c.size(); i2++) {
            Fragment fragment = this.f1404c.get(i2);
            if (fragment != null) {
                fragment.performLowMemory();
            }
        }
    }

    private boolean a(String str, int i2, int i3) {
        c(false);
        d(true);
        Fragment fragment = this.s;
        if (fragment != null && i2 < 0 && str == null && fragment.getChildFragmentManager().z()) {
            return true;
        }
        boolean a2 = a(this.A, this.B, str, i2, i3);
        if (a2) {
            this.f1403b = true;
            try {
                c(this.A, this.B);
            } finally {
                E();
            }
        }
        K();
        F();
        C();
        return a2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void l() {
        this.w = false;
        this.x = false;
        c(3);
    }

    private void d(boolean z) {
        if (!this.f1403b) {
            if (this.p == null) {
                if (this.y) {
                    throw new IllegalStateException("FragmentManager has been destroyed");
                }
                throw new IllegalStateException("FragmentManager has not been attached to a host.");
            }
            if (Looper.myLooper() == this.p.d().getLooper()) {
                if (!z) {
                    D();
                }
                if (this.A == null) {
                    this.A = new ArrayList<>();
                    this.B = new ArrayList<>();
                }
                this.f1403b = true;
                try {
                    a((ArrayList<androidx.fragment.app.a>) null, (ArrayList<Boolean>) null);
                    return;
                } finally {
                    this.f1403b = false;
                }
            }
            throw new IllegalStateException("Must be called from main thread of fragment host");
        }
        throw new IllegalStateException("FragmentManager is already executing transactions");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Fragment c(String str) {
        Fragment findFragmentByWho;
        for (r rVar : this.f1405d.values()) {
            if (rVar != null && (findFragmentByWho = rVar.e().findFragmentByWho(str)) != null) {
                return findFragmentByWho;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean b(int i2) {
        return this.o >= i2;
    }

    public Fragment b(String str) {
        if (str != null) {
            for (int size = this.f1404c.size() - 1; size >= 0; size--) {
                Fragment fragment = this.f1404c.get(size);
                if (fragment != null && str.equals(fragment.mTag)) {
                    return fragment;
                }
            }
        }
        if (str == null) {
            return null;
        }
        for (r rVar : this.f1405d.values()) {
            if (rVar != null) {
                Fragment e2 = rVar.e();
                if (str.equals(e2.mTag)) {
                    return e2;
                }
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean c(boolean z) {
        d(z);
        boolean z2 = false;
        while (b(this.A, this.B)) {
            this.f1403b = true;
            try {
                c(this.A, this.B);
                E();
                z2 = true;
            } catch (Throwable th) {
                E();
                throw th;
            }
        }
        K();
        F();
        C();
        return z2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(i iVar, boolean z) {
        if (z && (this.p == null || this.y)) {
            return;
        }
        d(z);
        if (iVar.a(this.A, this.B)) {
            this.f1403b = true;
            try {
                c(this.A, this.B);
            } finally {
                E();
            }
        }
        K();
        F();
        C();
    }

    public void a(h hVar) {
        if (this.k == null) {
            this.k = new ArrayList<>();
        }
        this.k.add(hVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public androidx.fragment.app.k r() {
        return this.n;
    }

    private void c(ArrayList<androidx.fragment.app.a> arrayList, ArrayList<Boolean> arrayList2) {
        if (arrayList.isEmpty()) {
            return;
        }
        if (arrayList.size() == arrayList2.size()) {
            a(arrayList, arrayList2);
            int size = arrayList.size();
            int i2 = 0;
            int i3 = 0;
            while (i2 < size) {
                if (!arrayList.get(i2).p) {
                    if (i3 != i2) {
                        b(arrayList, arrayList2, i3, i2);
                    }
                    i3 = i2 + 1;
                    if (arrayList2.get(i2).booleanValue()) {
                        while (i3 < size && arrayList2.get(i3).booleanValue() && !arrayList.get(i3).p) {
                            i3++;
                        }
                    }
                    b(arrayList, arrayList2, i2, i3);
                    i2 = i3 - 1;
                }
                i2++;
            }
            if (i3 != size) {
                b(arrayList, arrayList2, i3, size);
                return;
            }
            return;
        }
        throw new IllegalStateException("Internal error with the back stack records");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void i() {
        c(3);
    }

    void a(Fragment fragment, a.f.h.a aVar) {
        if (this.l.get(fragment) == null) {
            this.l.put(fragment, new HashSet<>());
        }
        this.l.get(fragment).add(aVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void d() {
        this.w = false;
        this.x = false;
        c(2);
    }

    public void a(Bundle bundle, String str, Fragment fragment) {
        if (fragment.mFragmentManager == this) {
            bundle.putString(str, fragment.mWho);
            return;
        }
        a(new IllegalStateException("Fragment " + fragment + " is not currently in the FragmentManager"));
        throw null;
    }

    private void b(ArrayList<androidx.fragment.app.a> arrayList, ArrayList<Boolean> arrayList2, int i2, int i3) {
        int i4;
        int i5 = i2;
        boolean z = arrayList.get(i5).p;
        ArrayList<Fragment> arrayList3 = this.C;
        if (arrayList3 == null) {
            this.C = new ArrayList<>();
        } else {
            arrayList3.clear();
        }
        this.C.addAll(this.f1404c);
        Fragment t = t();
        boolean z2 = false;
        for (int i6 = i5; i6 < i3; i6++) {
            androidx.fragment.app.a aVar = arrayList.get(i6);
            if (!arrayList2.get(i6).booleanValue()) {
                t = aVar.a(this.C, t);
            } else {
                t = aVar.b(this.C, t);
            }
            z2 = z2 || aVar.g;
        }
        this.C.clear();
        if (!z) {
            u.a(this, arrayList, arrayList2, i2, i3, false, this.m);
        }
        a(arrayList, arrayList2, i2, i3);
        if (z) {
            a.d.b<Fragment> bVar = new a.d.b<>();
            a(bVar);
            int a2 = a(arrayList, arrayList2, i2, i3, bVar);
            b(bVar);
            i4 = a2;
        } else {
            i4 = i3;
        }
        if (i4 != i5 && z) {
            u.a(this, arrayList, arrayList2, i2, i4, true, this.m);
            a(this.o, true);
        }
        while (i5 < i3) {
            androidx.fragment.app.a aVar2 = arrayList.get(i5);
            if (arrayList2.get(i5).booleanValue() && aVar2.t >= 0) {
                aVar2.t = -1;
            }
            aVar2.i();
            i5++;
        }
        if (z2) {
            I();
        }
    }

    public Fragment a(Bundle bundle, String str) {
        String string = bundle.getString(str);
        if (string == null) {
            return null;
        }
        Fragment a2 = a(string);
        if (a2 != null) {
            return a2;
        }
        a(new IllegalStateException("Fragment no longer exists for key " + str + ": unique id " + string));
        throw null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Fragment a(View view) {
        Object tag = view.getTag(a.i.b.fragment_container_view_tag);
        if (tag instanceof Fragment) {
            return (Fragment) tag;
        }
        return null;
    }

    private void c(int i2) {
        try {
            this.f1403b = true;
            a(i2, false);
            this.f1403b = false;
            c(true);
        } catch (Throwable th) {
            this.f1403b = false;
            throw th;
        }
    }

    public void a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        int size;
        int size2;
        String str2 = str + "    ";
        if (!this.f1405d.isEmpty()) {
            printWriter.print(str);
            printWriter.print("Active Fragments in ");
            printWriter.print(Integer.toHexString(System.identityHashCode(this)));
            printWriter.println(":");
            for (r rVar : this.f1405d.values()) {
                printWriter.print(str);
                if (rVar != null) {
                    Fragment e2 = rVar.e();
                    printWriter.println(e2);
                    e2.dump(str2, fileDescriptor, printWriter, strArr);
                } else {
                    printWriter.println("null");
                }
            }
        }
        int size3 = this.f1404c.size();
        if (size3 > 0) {
            printWriter.print(str);
            printWriter.println("Added Fragments:");
            for (int i2 = 0; i2 < size3; i2++) {
                Fragment fragment = this.f1404c.get(i2);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i2);
                printWriter.print(": ");
                printWriter.println(fragment.toString());
            }
        }
        ArrayList<Fragment> arrayList = this.f1407f;
        if (arrayList != null && (size2 = arrayList.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Fragments Created Menus:");
            for (int i3 = 0; i3 < size2; i3++) {
                Fragment fragment2 = this.f1407f.get(i3);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i3);
                printWriter.print(": ");
                printWriter.println(fragment2.toString());
            }
        }
        ArrayList<androidx.fragment.app.a> arrayList2 = this.f1406e;
        if (arrayList2 != null && (size = arrayList2.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Back Stack:");
            for (int i4 = 0; i4 < size; i4++) {
                androidx.fragment.app.a aVar = this.f1406e.get(i4);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i4);
                printWriter.print(": ");
                printWriter.println(aVar.toString());
                aVar.a(str2, printWriter);
            }
        }
        printWriter.print(str);
        printWriter.println("Back Stack Index: " + this.j.get());
        synchronized (this.f1402a) {
            int size4 = this.f1402a.size();
            if (size4 > 0) {
                printWriter.print(str);
                printWriter.println("Pending Actions:");
                for (int i5 = 0; i5 < size4; i5++) {
                    Object obj = (i) this.f1402a.get(i5);
                    printWriter.print(str);
                    printWriter.print("  #");
                    printWriter.print(i5);
                    printWriter.print(": ");
                    printWriter.println(obj);
                }
            }
        }
        printWriter.print(str);
        printWriter.println("FragmentManager misc state:");
        printWriter.print(str);
        printWriter.print("  mHost=");
        printWriter.println(this.p);
        printWriter.print(str);
        printWriter.print("  mContainer=");
        printWriter.println(this.q);
        if (this.r != null) {
            printWriter.print(str);
            printWriter.print("  mParent=");
            printWriter.println(this.r);
        }
        printWriter.print(str);
        printWriter.print("  mCurState=");
        printWriter.print(this.o);
        printWriter.print(" mStateSaved=");
        printWriter.print(this.w);
        printWriter.print(" mStopped=");
        printWriter.print(this.x);
        printWriter.print(" mDestroyed=");
        printWriter.println(this.y);
        if (this.v) {
            printWriter.print(str);
            printWriter.print("  mNeedMenuInvalidate=");
            printWriter.println(this.v);
        }
    }

    boolean c() {
        boolean z = false;
        for (r rVar : this.f1405d.values()) {
            if (rVar != null) {
                z = x(rVar.e());
            }
            if (z) {
                return true;
            }
        }
        return false;
    }

    private void b(a.d.b<Fragment> bVar) {
        int size = bVar.size();
        for (int i2 = 0; i2 < size; i2++) {
            Fragment c2 = bVar.c(i2);
            if (!c2.mAdded) {
                View requireView = c2.requireView();
                c2.mPostponedAlpha = requireView.getAlpha();
                requireView.setAlpha(0.0f);
            }
        }
    }

    private boolean b(ArrayList<androidx.fragment.app.a> arrayList, ArrayList<Boolean> arrayList2) {
        synchronized (this.f1402a) {
            if (this.f1402a.isEmpty()) {
                return false;
            }
            int size = this.f1402a.size();
            boolean z = false;
            for (int i2 = 0; i2 < size; i2++) {
                z |= this.f1402a.get(i2).a(arrayList, arrayList2);
            }
            this.f1402a.clear();
            this.p.d().removeCallbacks(this.F);
            return z;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(boolean z) {
        for (int size = this.f1404c.size() - 1; size >= 0; size--) {
            Fragment fragment = this.f1404c.get(size);
            if (fragment != null) {
                fragment.performPictureInPictureModeChanged(z);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean b(Menu menu) {
        if (this.o < 1) {
            return false;
        }
        boolean z = false;
        for (int i2 = 0; i2 < this.f1404c.size(); i2++) {
            Fragment fragment = this.f1404c.get(i2);
            if (fragment != null && fragment.performPrepareOptionsMenu(menu)) {
                z = true;
            }
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean b(MenuItem menuItem) {
        if (this.o < 1) {
            return false;
        }
        for (int i2 = 0; i2 < this.f1404c.size(); i2++) {
            Fragment fragment = this.f1404c.get(i2);
            if (fragment != null && fragment.performOptionsItemSelected(menuItem)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x003d, code lost:
    
        if (r1 != 3) goto L123;
     */
    /* JADX WARN: Removed duplicated region for block: B:17:0x00fc  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00f7  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00e5  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00ea  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(androidx.fragment.app.Fragment r13, int r14) {
        /*
            Method dump skipped, instructions count: 542
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.l.a(androidx.fragment.app.Fragment, int):void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(Fragment fragment, boolean z) {
        ViewGroup w = w(fragment);
        if (w == null || !(w instanceof FragmentContainerView)) {
            return;
        }
        ((FragmentContainerView) w).setDrawDisappearingViewsLast(!z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(int i2, boolean z) {
        androidx.fragment.app.i<?> iVar;
        if (this.p == null && i2 != -1) {
            throw new IllegalStateException("No activity");
        }
        if (z || i2 != this.o) {
            this.o = i2;
            int size = this.f1404c.size();
            for (int i3 = 0; i3 < size; i3++) {
                i(this.f1404c.get(i3));
            }
            for (r rVar : this.f1405d.values()) {
                if (rVar != null) {
                    Fragment e2 = rVar.e();
                    if (!e2.mIsNewlyAdded) {
                        i(e2);
                    }
                }
            }
            J();
            if (this.v && (iVar = this.p) != null && this.o == 4) {
                iVar.g();
                this.v = false;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(Fragment fragment) {
        if (d(2)) {
            Log.v("FragmentManager", "add: " + fragment);
        }
        h(fragment);
        if (fragment.mDetached) {
            return;
        }
        if (!this.f1404c.contains(fragment)) {
            synchronized (this.f1404c) {
                this.f1404c.add(fragment);
            }
            fragment.mAdded = true;
            fragment.mRemoving = false;
            if (fragment.mView == null) {
                fragment.mHiddenChanged = false;
            }
            if (x(fragment)) {
                this.v = true;
                return;
            }
            return;
        }
        throw new IllegalStateException("Fragment already added: " + fragment);
    }

    public Fragment a(int i2) {
        for (int size = this.f1404c.size() - 1; size >= 0; size--) {
            Fragment fragment = this.f1404c.get(size);
            if (fragment != null && fragment.mFragmentId == i2) {
                return fragment;
            }
        }
        for (r rVar : this.f1405d.values()) {
            if (rVar != null) {
                Fragment e2 = rVar.e();
                if (e2.mFragmentId == i2) {
                    return e2;
                }
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Fragment a(String str) {
        r rVar = this.f1405d.get(str);
        if (rVar != null) {
            return rVar.e();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(i iVar, boolean z) {
        if (!z) {
            if (this.p == null) {
                if (this.y) {
                    throw new IllegalStateException("FragmentManager has been destroyed");
                }
                throw new IllegalStateException("FragmentManager has not been attached to a host.");
            }
            D();
        }
        synchronized (this.f1402a) {
            if (this.p == null) {
                if (!z) {
                    throw new IllegalStateException("Activity has been destroyed");
                }
            } else {
                this.f1402a.add(iVar);
                B();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int a() {
        return this.j.getAndIncrement();
    }

    private void a(ArrayList<androidx.fragment.app.a> arrayList, ArrayList<Boolean> arrayList2) {
        int indexOf;
        int indexOf2;
        ArrayList<k> arrayList3 = this.D;
        int size = arrayList3 == null ? 0 : arrayList3.size();
        int i2 = 0;
        while (i2 < size) {
            k kVar = this.D.get(i2);
            if (arrayList != null && !kVar.f1419a && (indexOf2 = arrayList.indexOf(kVar.f1420b)) != -1 && arrayList2 != null && arrayList2.get(indexOf2).booleanValue()) {
                this.D.remove(i2);
                i2--;
                size--;
                kVar.c();
            } else if (kVar.e() || (arrayList != null && kVar.f1420b.a(arrayList, 0, arrayList.size()))) {
                this.D.remove(i2);
                i2--;
                size--;
                if (arrayList != null && !kVar.f1419a && (indexOf = arrayList.indexOf(kVar.f1420b)) != -1 && arrayList2 != null && arrayList2.get(indexOf).booleanValue()) {
                    kVar.c();
                } else {
                    kVar.d();
                }
            }
            i2++;
        }
    }

    private int a(ArrayList<androidx.fragment.app.a> arrayList, ArrayList<Boolean> arrayList2, int i2, int i3, a.d.b<Fragment> bVar) {
        int i4 = i3;
        for (int i5 = i3 - 1; i5 >= i2; i5--) {
            androidx.fragment.app.a aVar = arrayList.get(i5);
            boolean booleanValue = arrayList2.get(i5).booleanValue();
            if (aVar.h() && !aVar.a(arrayList, i5 + 1, i3)) {
                if (this.D == null) {
                    this.D = new ArrayList<>();
                }
                k kVar = new k(aVar, booleanValue);
                this.D.add(kVar);
                aVar.a(kVar);
                if (booleanValue) {
                    aVar.f();
                } else {
                    aVar.c(false);
                }
                i4--;
                if (i5 != i4) {
                    arrayList.remove(i5);
                    arrayList.add(i4, aVar);
                }
                a(bVar);
            }
        }
        return i4;
    }

    void a(androidx.fragment.app.a aVar, boolean z, boolean z2, boolean z3) {
        if (z) {
            aVar.c(z3);
        } else {
            aVar.f();
        }
        ArrayList arrayList = new ArrayList(1);
        ArrayList arrayList2 = new ArrayList(1);
        arrayList.add(aVar);
        arrayList2.add(Boolean.valueOf(z));
        if (z2) {
            u.a(this, arrayList, arrayList2, 0, 1, true, this.m);
        }
        if (z3) {
            a(this.o, true);
        }
        for (r rVar : this.f1405d.values()) {
            if (rVar != null) {
                Fragment e2 = rVar.e();
                if (e2.mView != null && e2.mIsNewlyAdded && aVar.b(e2.mContainerId)) {
                    float f2 = e2.mPostponedAlpha;
                    if (f2 > 0.0f) {
                        e2.mView.setAlpha(f2);
                    }
                    if (z3) {
                        e2.mPostponedAlpha = 0.0f;
                    } else {
                        e2.mPostponedAlpha = -1.0f;
                        e2.mIsNewlyAdded = false;
                    }
                }
            }
        }
    }

    private static void a(ArrayList<androidx.fragment.app.a> arrayList, ArrayList<Boolean> arrayList2, int i2, int i3) {
        while (i2 < i3) {
            androidx.fragment.app.a aVar = arrayList.get(i2);
            if (arrayList2.get(i2).booleanValue()) {
                aVar.a(-1);
                aVar.c(i2 == i3 + (-1));
            } else {
                aVar.a(1);
                aVar.f();
            }
            i2++;
        }
    }

    private void a(a.d.b<Fragment> bVar) {
        int i2 = this.o;
        if (i2 < 1) {
            return;
        }
        int min = Math.min(i2, 3);
        int size = this.f1404c.size();
        for (int i3 = 0; i3 < size; i3++) {
            Fragment fragment = this.f1404c.get(i3);
            if (fragment.mState < min) {
                a(fragment, min);
                if (fragment.mView != null && !fragment.mHidden && fragment.mIsNewlyAdded) {
                    bVar.add(fragment);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(androidx.fragment.app.a aVar) {
        if (this.f1406e == null) {
            this.f1406e = new ArrayList<>();
        }
        this.f1406e.add(aVar);
    }

    boolean a(ArrayList<androidx.fragment.app.a> arrayList, ArrayList<Boolean> arrayList2, String str, int i2, int i3) {
        int size;
        ArrayList<androidx.fragment.app.a> arrayList3 = this.f1406e;
        if (arrayList3 == null) {
            return false;
        }
        if (str == null && i2 < 0 && (i3 & 1) == 0) {
            int size2 = arrayList3.size() - 1;
            if (size2 < 0) {
                return false;
            }
            arrayList.add(this.f1406e.remove(size2));
            arrayList2.add(true);
        } else {
            if (str != null || i2 >= 0) {
                size = this.f1406e.size() - 1;
                while (size >= 0) {
                    androidx.fragment.app.a aVar = this.f1406e.get(size);
                    if ((str != null && str.equals(aVar.g())) || (i2 >= 0 && i2 == aVar.t)) {
                        break;
                    }
                    size--;
                }
                if (size < 0) {
                    return false;
                }
                if ((i3 & 1) != 0) {
                    while (true) {
                        size--;
                        if (size < 0) {
                            break;
                        }
                        androidx.fragment.app.a aVar2 = this.f1406e.get(size);
                        if (str == null || !str.equals(aVar2.g())) {
                            if (i2 < 0 || i2 != aVar2.t) {
                                break;
                            }
                        }
                    }
                }
            } else {
                size = -1;
            }
            if (size == this.f1406e.size() - 1) {
                return false;
            }
            for (int size3 = this.f1406e.size() - 1; size3 > size; size3--) {
                arrayList.add(this.f1406e.remove(size3));
                arrayList2.add(true);
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(Parcelable parcelable) {
        r rVar;
        if (parcelable == null) {
            return;
        }
        n nVar = (n) parcelable;
        if (nVar.f1422b == null) {
            return;
        }
        this.f1405d.clear();
        Iterator<q> it = nVar.f1422b.iterator();
        while (it.hasNext()) {
            q next = it.next();
            if (next != null) {
                Fragment b2 = this.E.b(next.f1432c);
                if (b2 != null) {
                    if (d(2)) {
                        Log.v("FragmentManager", "restoreSaveState: re-attaching retained " + b2);
                    }
                    rVar = new r(this.n, b2, next);
                } else {
                    rVar = new r(this.n, this.p.c().getClassLoader(), o(), next);
                }
                Fragment e2 = rVar.e();
                e2.mFragmentManager = this;
                if (d(2)) {
                    Log.v("FragmentManager", "restoreSaveState: active (" + e2.mWho + "): " + e2);
                }
                rVar.a(this.p.c().getClassLoader());
                this.f1405d.put(e2.mWho, rVar);
            }
        }
        for (Fragment fragment : this.E.c()) {
            if (!this.f1405d.containsKey(fragment.mWho)) {
                if (d(2)) {
                    Log.v("FragmentManager", "Discarding retained Fragment " + fragment + " that was not found in the set of active Fragments " + nVar.f1422b);
                }
                a(fragment, 1);
                fragment.mRemoving = true;
                a(fragment, -1);
            }
        }
        this.f1404c.clear();
        ArrayList<String> arrayList = nVar.f1423c;
        if (arrayList != null) {
            Iterator<String> it2 = arrayList.iterator();
            while (it2.hasNext()) {
                String next2 = it2.next();
                Fragment a2 = a(next2);
                if (a2 != null) {
                    a2.mAdded = true;
                    if (d(2)) {
                        Log.v("FragmentManager", "restoreSaveState: added (" + next2 + "): " + a2);
                    }
                    if (!this.f1404c.contains(a2)) {
                        synchronized (this.f1404c) {
                            this.f1404c.add(a2);
                        }
                    } else {
                        throw new IllegalStateException("Already added " + a2);
                    }
                } else {
                    a(new IllegalStateException("No instantiated fragment for (" + next2 + ")"));
                    throw null;
                }
            }
        }
        androidx.fragment.app.b[] bVarArr = nVar.f1424d;
        if (bVarArr != null) {
            this.f1406e = new ArrayList<>(bVarArr.length);
            int i2 = 0;
            while (true) {
                androidx.fragment.app.b[] bVarArr2 = nVar.f1424d;
                if (i2 >= bVarArr2.length) {
                    break;
                }
                androidx.fragment.app.a a3 = bVarArr2[i2].a(this);
                if (d(2)) {
                    Log.v("FragmentManager", "restoreAllState: back stack #" + i2 + " (index " + a3.t + "): " + a3);
                    PrintWriter printWriter = new PrintWriter(new a.f.k.b("FragmentManager"));
                    a3.a("  ", printWriter, false);
                    printWriter.close();
                }
                this.f1406e.add(a3);
                i2++;
            }
        } else {
            this.f1406e = null;
        }
        this.j.set(nVar.f1425e);
        String str = nVar.f1426f;
        if (str != null) {
            this.s = a(str);
            t(this.s);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    public void a(androidx.fragment.app.i<?> iVar, androidx.fragment.app.f fVar, Fragment fragment) {
        if (this.p == null) {
            this.p = iVar;
            this.q = fVar;
            this.r = fragment;
            if (this.r != null) {
                K();
            }
            if (iVar instanceof androidx.activity.c) {
                androidx.activity.c cVar = (androidx.activity.c) iVar;
                this.h = cVar.getOnBackPressedDispatcher();
                Fragment fragment2 = cVar;
                if (fragment != null) {
                    fragment2 = fragment;
                }
                this.h.a(fragment2, this.i);
            }
            if (fragment != null) {
                this.E = fragment.mFragmentManager.v(fragment);
                return;
            } else if (iVar instanceof androidx.lifecycle.x) {
                this.E = o.a(((androidx.lifecycle.x) iVar).getViewModelStore());
                return;
            } else {
                this.E = new o(false);
                return;
            }
        }
        throw new IllegalStateException("Already attached");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(boolean z) {
        for (int size = this.f1404c.size() - 1; size >= 0; size--) {
            Fragment fragment = this.f1404c.get(size);
            if (fragment != null) {
                fragment.performMultiWindowModeChanged(z);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(Configuration configuration) {
        for (int i2 = 0; i2 < this.f1404c.size(); i2++) {
            Fragment fragment = this.f1404c.get(i2);
            if (fragment != null) {
                fragment.performConfigurationChanged(configuration);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean a(Menu menu, MenuInflater menuInflater) {
        if (this.o < 1) {
            return false;
        }
        ArrayList<Fragment> arrayList = null;
        boolean z = false;
        for (int i2 = 0; i2 < this.f1404c.size(); i2++) {
            Fragment fragment = this.f1404c.get(i2);
            if (fragment != null && fragment.performCreateOptionsMenu(menu, menuInflater)) {
                if (arrayList == null) {
                    arrayList = new ArrayList<>();
                }
                arrayList.add(fragment);
                z = true;
            }
        }
        if (this.f1407f != null) {
            for (int i3 = 0; i3 < this.f1407f.size(); i3++) {
                Fragment fragment2 = this.f1407f.get(i3);
                if (arrayList == null || !arrayList.contains(fragment2)) {
                    fragment2.onDestroyOptionsMenu();
                }
            }
        }
        this.f1407f = arrayList;
        return z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean a(MenuItem menuItem) {
        if (this.o < 1) {
            return false;
        }
        for (int i2 = 0; i2 < this.f1404c.size(); i2++) {
            Fragment fragment = this.f1404c.get(i2);
            if (fragment != null && fragment.performContextItemSelected(menuItem)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(Menu menu) {
        if (this.o < 1) {
            return;
        }
        for (int i2 = 0; i2 < this.f1404c.size(); i2++) {
            Fragment fragment = this.f1404c.get(i2);
            if (fragment != null) {
                fragment.performOptionsMenuClosed(menu);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(Fragment fragment, g.b bVar) {
        if (fragment.equals(a(fragment.mWho)) && (fragment.mHost == null || fragment.mFragmentManager == this)) {
            fragment.mMaxState = bVar;
            return;
        }
        throw new IllegalArgumentException("Fragment " + fragment + " is not an active fragment of FragmentManager " + this);
    }
}
