package androidx.activity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import androidx.core.app.d;
import androidx.lifecycle.f;
import androidx.lifecycle.g;
import androidx.lifecycle.h;
import androidx.lifecycle.j;
import androidx.lifecycle.k;
import androidx.lifecycle.r;
import androidx.lifecycle.t;
import androidx.lifecycle.v;
import androidx.lifecycle.w;
import androidx.lifecycle.x;
import androidx.savedstate.SavedStateRegistry;

/* loaded from: classes.dex */
public class ComponentActivity extends d implements j, x, f, androidx.savedstate.b, c {
    private int mContentLayoutId;
    private v.b mDefaultFactory;
    private final k mLifecycleRegistry;
    private final OnBackPressedDispatcher mOnBackPressedDispatcher;
    private final androidx.savedstate.a mSavedStateRegistryController;
    private w mViewModelStore;

    /* loaded from: classes.dex */
    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ComponentActivity.super.onBackPressed();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class b {

        /* renamed from: a, reason: collision with root package name */
        Object f592a;

        /* renamed from: b, reason: collision with root package name */
        w f593b;

        b() {
        }
    }

    public ComponentActivity() {
        this.mLifecycleRegistry = new k(this);
        this.mSavedStateRegistryController = androidx.savedstate.a.a(this);
        this.mOnBackPressedDispatcher = new OnBackPressedDispatcher(new a());
        if (getLifecycle() != null) {
            if (Build.VERSION.SDK_INT >= 19) {
                getLifecycle().a(new h() { // from class: androidx.activity.ComponentActivity.2
                    @Override // androidx.lifecycle.h
                    public void a(j jVar, g.a aVar) {
                        if (aVar == g.a.ON_STOP) {
                            Window window = ComponentActivity.this.getWindow();
                            View peekDecorView = window != null ? window.peekDecorView() : null;
                            if (peekDecorView != null) {
                                peekDecorView.cancelPendingInputEvents();
                            }
                        }
                    }
                });
            }
            getLifecycle().a(new h() { // from class: androidx.activity.ComponentActivity.3
                @Override // androidx.lifecycle.h
                public void a(j jVar, g.a aVar) {
                    if (aVar != g.a.ON_DESTROY || ComponentActivity.this.isChangingConfigurations()) {
                        return;
                    }
                    ComponentActivity.this.getViewModelStore().a();
                }
            });
            int i = Build.VERSION.SDK_INT;
            if (19 > i || i > 23) {
                return;
            }
            getLifecycle().a(new ImmLeaksCleaner(this));
            return;
        }
        throw new IllegalStateException("getLifecycle() returned null in ComponentActivity's constructor. Please make sure you are lazily constructing your Lifecycle in the first call to getLifecycle() rather than relying on field initialization.");
    }

    public v.b getDefaultViewModelProviderFactory() {
        if (getApplication() != null) {
            if (this.mDefaultFactory == null) {
                this.mDefaultFactory = new t(getApplication(), this, getIntent() != null ? getIntent().getExtras() : null);
            }
            return this.mDefaultFactory;
        }
        throw new IllegalStateException("Your activity is not yet attached to the Application instance. You can't request ViewModel before onCreate call.");
    }

    @Deprecated
    public Object getLastCustomNonConfigurationInstance() {
        b bVar = (b) getLastNonConfigurationInstance();
        if (bVar != null) {
            return bVar.f592a;
        }
        return null;
    }

    @Override // androidx.core.app.d, androidx.lifecycle.j
    public g getLifecycle() {
        return this.mLifecycleRegistry;
    }

    @Override // androidx.activity.c
    public final OnBackPressedDispatcher getOnBackPressedDispatcher() {
        return this.mOnBackPressedDispatcher;
    }

    @Override // androidx.savedstate.b
    public final SavedStateRegistry getSavedStateRegistry() {
        return this.mSavedStateRegistryController.a();
    }

    @Override // androidx.lifecycle.x
    public w getViewModelStore() {
        if (getApplication() != null) {
            if (this.mViewModelStore == null) {
                b bVar = (b) getLastNonConfigurationInstance();
                if (bVar != null) {
                    this.mViewModelStore = bVar.f593b;
                }
                if (this.mViewModelStore == null) {
                    this.mViewModelStore = new w();
                }
            }
            return this.mViewModelStore;
        }
        throw new IllegalStateException("Your activity is not yet attached to the Application instance. You can't request ViewModel before onCreate call.");
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        this.mOnBackPressedDispatcher.a();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.core.app.d, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mSavedStateRegistryController.a(bundle);
        r.a(this);
        int i = this.mContentLayoutId;
        if (i != 0) {
            setContentView(i);
        }
    }

    @Deprecated
    public Object onRetainCustomNonConfigurationInstance() {
        return null;
    }

    @Override // android.app.Activity
    public final Object onRetainNonConfigurationInstance() {
        b bVar;
        Object onRetainCustomNonConfigurationInstance = onRetainCustomNonConfigurationInstance();
        w wVar = this.mViewModelStore;
        if (wVar == null && (bVar = (b) getLastNonConfigurationInstance()) != null) {
            wVar = bVar.f593b;
        }
        if (wVar == null && onRetainCustomNonConfigurationInstance == null) {
            return null;
        }
        b bVar2 = new b();
        bVar2.f592a = onRetainCustomNonConfigurationInstance;
        bVar2.f593b = wVar;
        return bVar2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.core.app.d, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        g lifecycle = getLifecycle();
        if (lifecycle instanceof k) {
            ((k) lifecycle).b(g.b.CREATED);
        }
        super.onSaveInstanceState(bundle);
        this.mSavedStateRegistryController.b(bundle);
    }

    public ComponentActivity(int i) {
        this();
        this.mContentLayoutId = i;
    }
}
