package androidx.fragment.app;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.g;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class r {

    /* renamed from: a, reason: collision with root package name */
    private final k f1436a;

    /* renamed from: b, reason: collision with root package name */
    private final Fragment f1437b;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static /* synthetic */ class a {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f1438a = new int[g.b.values().length];

        static {
            try {
                f1438a[g.b.RESUMED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1438a[g.b.STARTED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1438a[g.b.CREATED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public r(k kVar, Fragment fragment) {
        this.f1436a = kVar;
        this.f1437b = fragment;
    }

    private Bundle n() {
        Bundle bundle = new Bundle();
        this.f1437b.performSaveInstanceState(bundle);
        this.f1436a.d(this.f1437b, bundle, false);
        if (bundle.isEmpty()) {
            bundle = null;
        }
        if (this.f1437b.mView != null) {
            k();
        }
        if (this.f1437b.mSavedViewState != null) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putSparseParcelableArray("android:view_state", this.f1437b.mSavedViewState);
        }
        if (!this.f1437b.mUserVisibleHint) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putBoolean("android:user_visible_hint", this.f1437b.mUserVisibleHint);
        }
        return bundle;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(ClassLoader classLoader) {
        Bundle bundle = this.f1437b.mSavedFragmentState;
        if (bundle == null) {
            return;
        }
        bundle.setClassLoader(classLoader);
        Fragment fragment = this.f1437b;
        fragment.mSavedViewState = fragment.mSavedFragmentState.getSparseParcelableArray("android:view_state");
        Fragment fragment2 = this.f1437b;
        fragment2.mTargetWho = fragment2.mSavedFragmentState.getString("android:target_state");
        Fragment fragment3 = this.f1437b;
        if (fragment3.mTargetWho != null) {
            fragment3.mTargetRequestCode = fragment3.mSavedFragmentState.getInt("android:target_req_state", 0);
        }
        Fragment fragment4 = this.f1437b;
        Boolean bool = fragment4.mSavedUserVisibleHint;
        if (bool != null) {
            fragment4.mUserVisibleHint = bool.booleanValue();
            this.f1437b.mSavedUserVisibleHint = null;
        } else {
            fragment4.mUserVisibleHint = fragment4.mSavedFragmentState.getBoolean("android:user_visible_hint", true);
        }
        Fragment fragment5 = this.f1437b;
        if (fragment5.mUserVisibleHint) {
            return;
        }
        fragment5.mDeferStart = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int b() {
        Fragment fragment = this.f1437b;
        int i = 4;
        if (fragment.mFromLayout && !fragment.mInLayout) {
            i = Math.min(4, fragment.mState);
        }
        if (!this.f1437b.mAdded) {
            i = Math.min(i, 1);
        }
        Fragment fragment2 = this.f1437b;
        if (fragment2.mRemoving) {
            if (fragment2.isInBackStack()) {
                i = Math.min(i, 1);
            } else {
                i = Math.min(i, -1);
            }
        }
        Fragment fragment3 = this.f1437b;
        if (fragment3.mDeferStart && fragment3.mState < 3) {
            i = Math.min(i, 2);
        }
        int i2 = a.f1438a[this.f1437b.mMaxState.ordinal()];
        if (i2 == 1) {
            return i;
        }
        if (i2 == 2) {
            return Math.min(i, 3);
        }
        if (i2 != 3) {
            return Math.min(i, -1);
        }
        return Math.min(i, 1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void c() {
        if (l.d(3)) {
            Log.d("FragmentManager", "moveto CREATED: " + this.f1437b);
        }
        Fragment fragment = this.f1437b;
        if (!fragment.mIsCreated) {
            this.f1436a.c(fragment, fragment.mSavedFragmentState, false);
            Fragment fragment2 = this.f1437b;
            fragment2.performCreate(fragment2.mSavedFragmentState);
            k kVar = this.f1436a;
            Fragment fragment3 = this.f1437b;
            kVar.b(fragment3, fragment3.mSavedFragmentState, false);
            return;
        }
        fragment.restoreChildFragmentState(fragment.mSavedFragmentState);
        this.f1437b.mState = 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void d() {
        Fragment fragment = this.f1437b;
        if (!fragment.mFromLayout || fragment.mPerformedCreateView) {
            return;
        }
        if (l.d(3)) {
            Log.d("FragmentManager", "moveto CREATE_VIEW: " + this.f1437b);
        }
        Fragment fragment2 = this.f1437b;
        fragment2.performCreateView(fragment2.performGetLayoutInflater(fragment2.mSavedFragmentState), null, this.f1437b.mSavedFragmentState);
        View view = this.f1437b.mView;
        if (view != null) {
            view.setSaveFromParentEnabled(false);
            Fragment fragment3 = this.f1437b;
            if (fragment3.mHidden) {
                fragment3.mView.setVisibility(8);
            }
            Fragment fragment4 = this.f1437b;
            fragment4.onViewCreated(fragment4.mView, fragment4.mSavedFragmentState);
            k kVar = this.f1436a;
            Fragment fragment5 = this.f1437b;
            kVar.a(fragment5, fragment5.mView, fragment5.mSavedFragmentState, false);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Fragment e() {
        return this.f1437b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void f() {
        if (l.d(3)) {
            Log.d("FragmentManager", "movefrom RESUMED: " + this.f1437b);
        }
        this.f1437b.performPause();
        this.f1436a.c(this.f1437b, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void g() {
        if (l.d(3)) {
            Log.d("FragmentManager", "moveto RESTORE_VIEW_STATE: " + this.f1437b);
        }
        Fragment fragment = this.f1437b;
        if (fragment.mView != null) {
            fragment.restoreViewState(fragment.mSavedFragmentState);
        }
        this.f1437b.mSavedFragmentState = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void h() {
        if (l.d(3)) {
            Log.d("FragmentManager", "moveto RESUMED: " + this.f1437b);
        }
        this.f1437b.performResume();
        this.f1436a.d(this.f1437b, false);
        Fragment fragment = this.f1437b;
        fragment.mSavedFragmentState = null;
        fragment.mSavedViewState = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Fragment.g i() {
        Bundle n;
        if (this.f1437b.mState <= -1 || (n = n()) == null) {
            return null;
        }
        return new Fragment.g(n);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public q j() {
        q qVar = new q(this.f1437b);
        if (this.f1437b.mState > -1 && qVar.n == null) {
            qVar.n = n();
            if (this.f1437b.mTargetWho != null) {
                if (qVar.n == null) {
                    qVar.n = new Bundle();
                }
                qVar.n.putString("android:target_state", this.f1437b.mTargetWho);
                int i = this.f1437b.mTargetRequestCode;
                if (i != 0) {
                    qVar.n.putInt("android:target_req_state", i);
                }
            }
        } else {
            qVar.n = this.f1437b.mSavedFragmentState;
        }
        return qVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void k() {
        if (this.f1437b.mView == null) {
            return;
        }
        SparseArray<Parcelable> sparseArray = new SparseArray<>();
        this.f1437b.mView.saveHierarchyState(sparseArray);
        if (sparseArray.size() > 0) {
            this.f1437b.mSavedViewState = sparseArray;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void l() {
        if (l.d(3)) {
            Log.d("FragmentManager", "moveto STARTED: " + this.f1437b);
        }
        this.f1437b.performStart();
        this.f1436a.e(this.f1437b, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void m() {
        if (l.d(3)) {
            Log.d("FragmentManager", "movefrom STARTED: " + this.f1437b);
        }
        this.f1437b.performStop();
        this.f1436a.f(this.f1437b, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public r(k kVar, ClassLoader classLoader, h hVar, q qVar) {
        this.f1436a = kVar;
        this.f1437b = hVar.a(classLoader, qVar.f1431b);
        Bundle bundle = qVar.k;
        if (bundle != null) {
            bundle.setClassLoader(classLoader);
        }
        this.f1437b.setArguments(qVar.k);
        Fragment fragment = this.f1437b;
        fragment.mWho = qVar.f1432c;
        fragment.mFromLayout = qVar.f1433d;
        fragment.mRestored = true;
        fragment.mFragmentId = qVar.f1434e;
        fragment.mContainerId = qVar.f1435f;
        fragment.mTag = qVar.g;
        fragment.mRetainInstance = qVar.h;
        fragment.mRemoving = qVar.i;
        fragment.mDetached = qVar.j;
        fragment.mHidden = qVar.l;
        fragment.mMaxState = g.b.values()[qVar.m];
        Bundle bundle2 = qVar.n;
        if (bundle2 != null) {
            this.f1437b.mSavedFragmentState = bundle2;
        } else {
            this.f1437b.mSavedFragmentState = new Bundle();
        }
        if (l.d(2)) {
            Log.v("FragmentManager", "Instantiated fragment " + this.f1437b);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(i<?> iVar, l lVar, Fragment fragment) {
        Fragment fragment2 = this.f1437b;
        fragment2.mHost = iVar;
        fragment2.mParentFragment = fragment;
        fragment2.mFragmentManager = lVar;
        this.f1436a.b(fragment2, iVar.c(), false);
        this.f1437b.performAttach();
        Fragment fragment3 = this.f1437b;
        Fragment fragment4 = fragment3.mParentFragment;
        if (fragment4 == null) {
            iVar.a(fragment3);
        } else {
            fragment4.onAttachFragment(fragment3);
        }
        this.f1436a.a(this.f1437b, iVar.c(), false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public r(k kVar, Fragment fragment, q qVar) {
        this.f1436a = kVar;
        this.f1437b = fragment;
        Fragment fragment2 = this.f1437b;
        fragment2.mSavedViewState = null;
        fragment2.mBackStackNesting = 0;
        fragment2.mInLayout = false;
        fragment2.mAdded = false;
        Fragment fragment3 = fragment2.mTarget;
        fragment2.mTargetWho = fragment3 != null ? fragment3.mWho : null;
        Fragment fragment4 = this.f1437b;
        fragment4.mTarget = null;
        Bundle bundle = qVar.n;
        if (bundle != null) {
            fragment4.mSavedFragmentState = bundle;
        } else {
            fragment4.mSavedFragmentState = new Bundle();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(f fVar) {
        String str;
        if (this.f1437b.mFromLayout) {
            return;
        }
        if (l.d(3)) {
            Log.d("FragmentManager", "moveto CREATE_VIEW: " + this.f1437b);
        }
        ViewGroup viewGroup = null;
        Fragment fragment = this.f1437b;
        ViewGroup viewGroup2 = fragment.mContainer;
        if (viewGroup2 != null) {
            viewGroup = viewGroup2;
        } else {
            int i = fragment.mContainerId;
            if (i != 0) {
                if (i != -1) {
                    viewGroup = (ViewGroup) fVar.a(i);
                    if (viewGroup == null) {
                        Fragment fragment2 = this.f1437b;
                        if (!fragment2.mRestored) {
                            try {
                                str = fragment2.getResources().getResourceName(this.f1437b.mContainerId);
                            } catch (Resources.NotFoundException unused) {
                                str = "unknown";
                            }
                            throw new IllegalArgumentException("No view found for id 0x" + Integer.toHexString(this.f1437b.mContainerId) + " (" + str + ") for fragment " + this.f1437b);
                        }
                    }
                } else {
                    throw new IllegalArgumentException("Cannot create fragment " + this.f1437b + " for a container view with no id");
                }
            }
        }
        Fragment fragment3 = this.f1437b;
        fragment3.mContainer = viewGroup;
        fragment3.performCreateView(fragment3.performGetLayoutInflater(fragment3.mSavedFragmentState), viewGroup, this.f1437b.mSavedFragmentState);
        View view = this.f1437b.mView;
        if (view != null) {
            boolean z = false;
            view.setSaveFromParentEnabled(false);
            Fragment fragment4 = this.f1437b;
            fragment4.mView.setTag(a.i.b.fragment_container_view_tag, fragment4);
            if (viewGroup != null) {
                viewGroup.addView(this.f1437b.mView);
            }
            Fragment fragment5 = this.f1437b;
            if (fragment5.mHidden) {
                fragment5.mView.setVisibility(8);
            }
            a.f.l.w.L(this.f1437b.mView);
            Fragment fragment6 = this.f1437b;
            fragment6.onViewCreated(fragment6.mView, fragment6.mSavedFragmentState);
            k kVar = this.f1436a;
            Fragment fragment7 = this.f1437b;
            kVar.a(fragment7, fragment7.mView, fragment7.mSavedFragmentState, false);
            Fragment fragment8 = this.f1437b;
            if (fragment8.mView.getVisibility() == 0 && this.f1437b.mContainer != null) {
                z = true;
            }
            fragment8.mIsNewlyAdded = z;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a() {
        if (l.d(3)) {
            Log.d("FragmentManager", "moveto ACTIVITY_CREATED: " + this.f1437b);
        }
        Fragment fragment = this.f1437b;
        fragment.performActivityCreated(fragment.mSavedFragmentState);
        k kVar = this.f1436a;
        Fragment fragment2 = this.f1437b;
        kVar.a(fragment2, fragment2.mSavedFragmentState, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(i<?> iVar, o oVar) {
        if (l.d(3)) {
            Log.d("FragmentManager", "movefrom CREATED: " + this.f1437b);
        }
        Fragment fragment = this.f1437b;
        boolean z = true;
        boolean z2 = fragment.mRemoving && !fragment.isInBackStack();
        if (z2 || oVar.f(this.f1437b)) {
            if (iVar instanceof androidx.lifecycle.x) {
                z = oVar.d();
            } else if (iVar.c() instanceof Activity) {
                z = true ^ ((Activity) iVar.c()).isChangingConfigurations();
            }
            if (z2 || z) {
                oVar.b(this.f1437b);
            }
            this.f1437b.performDestroy();
            this.f1436a.a(this.f1437b, false);
            return;
        }
        this.f1437b.mState = 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(o oVar) {
        if (l.d(3)) {
            Log.d("FragmentManager", "movefrom ATTACHED: " + this.f1437b);
        }
        this.f1437b.performDetach();
        boolean z = false;
        this.f1436a.b(this.f1437b, false);
        Fragment fragment = this.f1437b;
        fragment.mState = -1;
        fragment.mHost = null;
        fragment.mParentFragment = null;
        fragment.mFragmentManager = null;
        if (fragment.mRemoving && !fragment.isInBackStack()) {
            z = true;
        }
        if (z || oVar.f(this.f1437b)) {
            if (l.d(3)) {
                Log.d("FragmentManager", "initState called for fragment: " + this.f1437b);
            }
            this.f1437b.initState();
        }
    }
}
