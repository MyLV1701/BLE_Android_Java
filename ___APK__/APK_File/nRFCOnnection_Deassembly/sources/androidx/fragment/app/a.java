package androidx.fragment.app;

import android.util.Log;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.l;
import androidx.fragment.app.t;
import androidx.lifecycle.g;
import java.io.PrintWriter;
import java.util.ArrayList;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class a extends t implements l.f, l.i {
    final l r;
    boolean s;
    int t;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public a(androidx.fragment.app.l r3) {
        /*
            r2 = this;
            androidx.fragment.app.h r0 = r3.o()
            androidx.fragment.app.i<?> r1 = r3.p
            if (r1 == 0) goto L11
            android.content.Context r1 = r1.c()
            java.lang.ClassLoader r1 = r1.getClassLoader()
            goto L12
        L11:
            r1 = 0
        L12:
            r2.<init>(r0, r1)
            r0 = -1
            r2.t = r0
            r2.r = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.a.<init>(androidx.fragment.app.l):void");
    }

    public void a(String str, PrintWriter printWriter) {
        a(str, printWriter, true);
    }

    @Override // androidx.fragment.app.t
    public t b(Fragment fragment) {
        l lVar = fragment.mFragmentManager;
        if (lVar != null && lVar != this.r) {
            throw new IllegalStateException("Cannot detach Fragment attached to a different FragmentManager. Fragment " + fragment.toString() + " is already attached to a FragmentManager.");
        }
        super.b(fragment);
        return this;
    }

    @Override // androidx.fragment.app.t
    public t c(Fragment fragment) {
        l lVar = fragment.mFragmentManager;
        if (lVar != null && lVar != this.r) {
            throw new IllegalStateException("Cannot remove Fragment attached to a different FragmentManager. Fragment " + fragment.toString() + " is already attached to a FragmentManager.");
        }
        super.c(fragment);
        return this;
    }

    @Override // androidx.fragment.app.t
    public void d() {
        e();
        this.r.b((l.i) this, true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void f() {
        int size = this.f1439a.size();
        for (int i = 0; i < size; i++) {
            t.a aVar = this.f1439a.get(i);
            Fragment fragment = aVar.f1446b;
            if (fragment != null) {
                fragment.setNextTransition(this.f1444f);
            }
            switch (aVar.f1445a) {
                case 1:
                    fragment.setNextAnim(aVar.f1447c);
                    this.r.a(fragment, false);
                    this.r.a(fragment);
                    break;
                case 2:
                default:
                    throw new IllegalArgumentException("Unknown cmd: " + aVar.f1445a);
                case 3:
                    fragment.setNextAnim(aVar.f1448d);
                    this.r.l(fragment);
                    break;
                case 4:
                    fragment.setNextAnim(aVar.f1448d);
                    this.r.f(fragment);
                    break;
                case 5:
                    fragment.setNextAnim(aVar.f1447c);
                    this.r.a(fragment, false);
                    this.r.p(fragment);
                    break;
                case 6:
                    fragment.setNextAnim(aVar.f1448d);
                    this.r.d(fragment);
                    break;
                case 7:
                    fragment.setNextAnim(aVar.f1447c);
                    this.r.a(fragment, false);
                    this.r.c(fragment);
                    break;
                case 8:
                    this.r.o(fragment);
                    break;
                case 9:
                    this.r.o(null);
                    break;
                case 10:
                    this.r.a(fragment, aVar.h);
                    break;
            }
            if (!this.p && aVar.f1445a != 1 && fragment != null) {
                this.r.i(fragment);
            }
        }
        if (this.p) {
            return;
        }
        l lVar = this.r;
        lVar.a(lVar.o, true);
    }

    public String g() {
        return this.i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean h() {
        for (int i = 0; i < this.f1439a.size(); i++) {
            if (b(this.f1439a.get(i))) {
                return true;
            }
        }
        return false;
    }

    public void i() {
        if (this.q != null) {
            for (int i = 0; i < this.q.size(); i++) {
                this.q.get(i).run();
            }
            this.q = null;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("BackStackEntry{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        if (this.t >= 0) {
            sb.append(" #");
            sb.append(this.t);
        }
        if (this.i != null) {
            sb.append(" ");
            sb.append(this.i);
        }
        sb.append("}");
        return sb.toString();
    }

    public void a(String str, PrintWriter printWriter, boolean z) {
        String str2;
        if (z) {
            printWriter.print(str);
            printWriter.print("mName=");
            printWriter.print(this.i);
            printWriter.print(" mIndex=");
            printWriter.print(this.t);
            printWriter.print(" mCommitted=");
            printWriter.println(this.s);
            if (this.f1444f != 0) {
                printWriter.print(str);
                printWriter.print("mTransition=#");
                printWriter.print(Integer.toHexString(this.f1444f));
            }
            if (this.f1440b != 0 || this.f1441c != 0) {
                printWriter.print(str);
                printWriter.print("mEnterAnim=#");
                printWriter.print(Integer.toHexString(this.f1440b));
                printWriter.print(" mExitAnim=#");
                printWriter.println(Integer.toHexString(this.f1441c));
            }
            if (this.f1442d != 0 || this.f1443e != 0) {
                printWriter.print(str);
                printWriter.print("mPopEnterAnim=#");
                printWriter.print(Integer.toHexString(this.f1442d));
                printWriter.print(" mPopExitAnim=#");
                printWriter.println(Integer.toHexString(this.f1443e));
            }
            if (this.j != 0 || this.k != null) {
                printWriter.print(str);
                printWriter.print("mBreadCrumbTitleRes=#");
                printWriter.print(Integer.toHexString(this.j));
                printWriter.print(" mBreadCrumbTitleText=");
                printWriter.println(this.k);
            }
            if (this.l != 0 || this.m != null) {
                printWriter.print(str);
                printWriter.print("mBreadCrumbShortTitleRes=#");
                printWriter.print(Integer.toHexString(this.l));
                printWriter.print(" mBreadCrumbShortTitleText=");
                printWriter.println(this.m);
            }
        }
        if (this.f1439a.isEmpty()) {
            return;
        }
        printWriter.print(str);
        printWriter.println("Operations:");
        int size = this.f1439a.size();
        for (int i = 0; i < size; i++) {
            t.a aVar = this.f1439a.get(i);
            switch (aVar.f1445a) {
                case 0:
                    str2 = "NULL";
                    break;
                case 1:
                    str2 = "ADD";
                    break;
                case 2:
                    str2 = "REPLACE";
                    break;
                case 3:
                    str2 = "REMOVE";
                    break;
                case 4:
                    str2 = "HIDE";
                    break;
                case 5:
                    str2 = "SHOW";
                    break;
                case 6:
                    str2 = "DETACH";
                    break;
                case 7:
                    str2 = "ATTACH";
                    break;
                case 8:
                    str2 = "SET_PRIMARY_NAV";
                    break;
                case 9:
                    str2 = "UNSET_PRIMARY_NAV";
                    break;
                case 10:
                    str2 = "OP_SET_MAX_LIFECYCLE";
                    break;
                default:
                    str2 = "cmd=" + aVar.f1445a;
                    break;
            }
            printWriter.print(str);
            printWriter.print("  Op #");
            printWriter.print(i);
            printWriter.print(": ");
            printWriter.print(str2);
            printWriter.print(" ");
            printWriter.println(aVar.f1446b);
            if (z) {
                if (aVar.f1447c != 0 || aVar.f1448d != 0) {
                    printWriter.print(str);
                    printWriter.print("enterAnim=#");
                    printWriter.print(Integer.toHexString(aVar.f1447c));
                    printWriter.print(" exitAnim=#");
                    printWriter.println(Integer.toHexString(aVar.f1448d));
                }
                if (aVar.f1449e != 0 || aVar.f1450f != 0) {
                    printWriter.print(str);
                    printWriter.print("popEnterAnim=#");
                    printWriter.print(Integer.toHexString(aVar.f1449e));
                    printWriter.print(" popExitAnim=#");
                    printWriter.println(Integer.toHexString(aVar.f1450f));
                }
            }
        }
    }

    @Override // androidx.fragment.app.t
    public int b() {
        return b(true);
    }

    @Override // androidx.fragment.app.t
    public void c() {
        e();
        this.r.b((l.i) this, false);
    }

    int b(boolean z) {
        if (!this.s) {
            if (l.d(2)) {
                Log.v("FragmentManager", "Commit: " + this);
                PrintWriter printWriter = new PrintWriter(new a.f.k.b("FragmentManager"));
                a("  ", printWriter);
                printWriter.close();
            }
            this.s = true;
            if (this.g) {
                this.t = this.r.a();
            } else {
                this.t = -1;
            }
            this.r.a(this, z);
            return this.t;
        }
        throw new IllegalStateException("commit already called");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void c(boolean z) {
        for (int size = this.f1439a.size() - 1; size >= 0; size--) {
            t.a aVar = this.f1439a.get(size);
            Fragment fragment = aVar.f1446b;
            if (fragment != null) {
                fragment.setNextTransition(l.e(this.f1444f));
            }
            switch (aVar.f1445a) {
                case 1:
                    fragment.setNextAnim(aVar.f1450f);
                    this.r.a(fragment, true);
                    this.r.l(fragment);
                    break;
                case 2:
                default:
                    throw new IllegalArgumentException("Unknown cmd: " + aVar.f1445a);
                case 3:
                    fragment.setNextAnim(aVar.f1449e);
                    this.r.a(fragment);
                    break;
                case 4:
                    fragment.setNextAnim(aVar.f1449e);
                    this.r.p(fragment);
                    break;
                case 5:
                    fragment.setNextAnim(aVar.f1450f);
                    this.r.a(fragment, true);
                    this.r.f(fragment);
                    break;
                case 6:
                    fragment.setNextAnim(aVar.f1449e);
                    this.r.c(fragment);
                    break;
                case 7:
                    fragment.setNextAnim(aVar.f1450f);
                    this.r.a(fragment, true);
                    this.r.d(fragment);
                    break;
                case 8:
                    this.r.o(null);
                    break;
                case 9:
                    this.r.o(fragment);
                    break;
                case 10:
                    this.r.a(fragment, aVar.g);
                    break;
            }
            if (!this.p && aVar.f1445a != 3 && fragment != null) {
                this.r.i(fragment);
            }
        }
        if (this.p || !z) {
            return;
        }
        l lVar = this.r;
        lVar.a(lVar.o, true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean b(int i) {
        int size = this.f1439a.size();
        for (int i2 = 0; i2 < size; i2++) {
            Fragment fragment = this.f1439a.get(i2).f1446b;
            int i3 = fragment != null ? fragment.mContainerId : 0;
            if (i3 != 0 && i3 == i) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Fragment b(ArrayList<Fragment> arrayList, Fragment fragment) {
        for (int size = this.f1439a.size() - 1; size >= 0; size--) {
            t.a aVar = this.f1439a.get(size);
            int i = aVar.f1445a;
            if (i != 1) {
                if (i != 3) {
                    switch (i) {
                        case 8:
                            fragment = null;
                            break;
                        case 9:
                            fragment = aVar.f1446b;
                            break;
                        case 10:
                            aVar.h = aVar.g;
                            break;
                    }
                }
                arrayList.add(aVar.f1446b);
            }
            arrayList.remove(aVar.f1446b);
        }
        return fragment;
    }

    private static boolean b(t.a aVar) {
        Fragment fragment = aVar.f1446b;
        return (fragment == null || !fragment.mAdded || fragment.mView == null || fragment.mDetached || fragment.mHidden || !fragment.isPostponed()) ? false : true;
    }

    @Override // androidx.fragment.app.t
    void a(int i, Fragment fragment, String str, int i2) {
        super.a(i, fragment, str, i2);
        fragment.mFragmentManager = this.r;
    }

    @Override // androidx.fragment.app.t
    public t a(Fragment fragment, g.b bVar) {
        if (fragment.mFragmentManager == this.r) {
            if (bVar.a(g.b.CREATED)) {
                super.a(fragment, bVar);
                return this;
            }
            throw new IllegalArgumentException("Cannot set maximum Lifecycle below " + g.b.CREATED);
        }
        throw new IllegalArgumentException("Cannot setMaxLifecycle for Fragment not attached to FragmentManager " + this.r);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(int i) {
        if (this.g) {
            if (l.d(2)) {
                Log.v("FragmentManager", "Bump nesting in " + this + " by " + i);
            }
            int size = this.f1439a.size();
            for (int i2 = 0; i2 < size; i2++) {
                t.a aVar = this.f1439a.get(i2);
                Fragment fragment = aVar.f1446b;
                if (fragment != null) {
                    fragment.mBackStackNesting += i;
                    if (l.d(2)) {
                        Log.v("FragmentManager", "Bump nesting of " + aVar.f1446b + " to " + aVar.f1446b.mBackStackNesting);
                    }
                }
            }
        }
    }

    @Override // androidx.fragment.app.t
    public int a() {
        return b(false);
    }

    @Override // androidx.fragment.app.l.i
    public boolean a(ArrayList<a> arrayList, ArrayList<Boolean> arrayList2) {
        if (l.d(2)) {
            Log.v("FragmentManager", "Run: " + this);
        }
        arrayList.add(this);
        arrayList2.add(false);
        if (!this.g) {
            return true;
        }
        this.r.a(this);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean a(ArrayList<a> arrayList, int i, int i2) {
        if (i2 == i) {
            return false;
        }
        int size = this.f1439a.size();
        int i3 = -1;
        for (int i4 = 0; i4 < size; i4++) {
            Fragment fragment = this.f1439a.get(i4).f1446b;
            int i5 = fragment != null ? fragment.mContainerId : 0;
            if (i5 != 0 && i5 != i3) {
                for (int i6 = i; i6 < i2; i6++) {
                    a aVar = arrayList.get(i6);
                    int size2 = aVar.f1439a.size();
                    for (int i7 = 0; i7 < size2; i7++) {
                        Fragment fragment2 = aVar.f1439a.get(i7).f1446b;
                        if ((fragment2 != null ? fragment2.mContainerId : 0) == i5) {
                            return true;
                        }
                    }
                }
                i3 = i5;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Fragment a(ArrayList<Fragment> arrayList, Fragment fragment) {
        Fragment fragment2 = fragment;
        int i = 0;
        while (i < this.f1439a.size()) {
            t.a aVar = this.f1439a.get(i);
            int i2 = aVar.f1445a;
            if (i2 != 1) {
                if (i2 == 2) {
                    Fragment fragment3 = aVar.f1446b;
                    int i3 = fragment3.mContainerId;
                    Fragment fragment4 = fragment2;
                    int i4 = i;
                    boolean z = false;
                    for (int size = arrayList.size() - 1; size >= 0; size--) {
                        Fragment fragment5 = arrayList.get(size);
                        if (fragment5.mContainerId == i3) {
                            if (fragment5 == fragment3) {
                                z = true;
                            } else {
                                if (fragment5 == fragment4) {
                                    this.f1439a.add(i4, new t.a(9, fragment5));
                                    i4++;
                                    fragment4 = null;
                                }
                                t.a aVar2 = new t.a(3, fragment5);
                                aVar2.f1447c = aVar.f1447c;
                                aVar2.f1449e = aVar.f1449e;
                                aVar2.f1448d = aVar.f1448d;
                                aVar2.f1450f = aVar.f1450f;
                                this.f1439a.add(i4, aVar2);
                                arrayList.remove(fragment5);
                                i4++;
                            }
                        }
                    }
                    if (z) {
                        this.f1439a.remove(i4);
                        i4--;
                    } else {
                        aVar.f1445a = 1;
                        arrayList.add(fragment3);
                    }
                    i = i4;
                    fragment2 = fragment4;
                } else if (i2 == 3 || i2 == 6) {
                    arrayList.remove(aVar.f1446b);
                    Fragment fragment6 = aVar.f1446b;
                    if (fragment6 == fragment2) {
                        this.f1439a.add(i, new t.a(9, fragment6));
                        i++;
                        fragment2 = null;
                    }
                } else if (i2 != 7) {
                    if (i2 == 8) {
                        this.f1439a.add(i, new t.a(9, fragment2));
                        i++;
                        fragment2 = aVar.f1446b;
                    }
                }
                i++;
            }
            arrayList.add(aVar.f1446b);
            i++;
        }
        return fragment2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(Fragment.f fVar) {
        for (int i = 0; i < this.f1439a.size(); i++) {
            t.a aVar = this.f1439a.get(i);
            if (b(aVar)) {
                aVar.f1446b.setOnStartEnterTransitionListener(fVar);
            }
        }
    }
}
