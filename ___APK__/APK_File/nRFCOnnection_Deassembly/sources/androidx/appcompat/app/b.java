package androidx.appcompat.app;

import android.R;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.app.c;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

/* loaded from: classes.dex */
public class b implements DrawerLayout.d {
    private final InterfaceC0039b mActivityImpl;
    private final int mCloseDrawerContentDescRes;
    boolean mDrawerIndicatorEnabled;
    private final DrawerLayout mDrawerLayout;
    private boolean mDrawerSlideAnimationEnabled;
    private boolean mHasCustomUpIndicator;
    private Drawable mHomeAsUpIndicator;
    private final int mOpenDrawerContentDescRes;
    private a.a.l.a.d mSlider;
    View.OnClickListener mToolbarNavigationClickListener;
    private boolean mWarnedForDisplayHomeAsUp;

    /* loaded from: classes.dex */
    class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            b bVar = b.this;
            if (bVar.mDrawerIndicatorEnabled) {
                bVar.toggle();
                return;
            }
            View.OnClickListener onClickListener = bVar.mToolbarNavigationClickListener;
            if (onClickListener != null) {
                onClickListener.onClick(view);
            }
        }
    }

    /* renamed from: androidx.appcompat.app.b$b, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public interface InterfaceC0039b {
        void a(int i);

        void a(Drawable drawable, int i);

        boolean a();

        Drawable b();

        Context c();
    }

    /* loaded from: classes.dex */
    public interface c {
        InterfaceC0039b getDrawerToggleDelegate();
    }

    public b(Activity activity, DrawerLayout drawerLayout, int i, int i2) {
        this(activity, null, drawerLayout, null, i, i2);
    }

    private void setPosition(float f2) {
        if (f2 == 1.0f) {
            this.mSlider.b(true);
        } else if (f2 == 0.0f) {
            this.mSlider.b(false);
        }
        this.mSlider.c(f2);
    }

    public a.a.l.a.d getDrawerArrowDrawable() {
        return this.mSlider;
    }

    Drawable getThemeUpIndicator() {
        return this.mActivityImpl.b();
    }

    public View.OnClickListener getToolbarNavigationClickListener() {
        return this.mToolbarNavigationClickListener;
    }

    public boolean isDrawerIndicatorEnabled() {
        return this.mDrawerIndicatorEnabled;
    }

    public boolean isDrawerSlideAnimationEnabled() {
        return this.mDrawerSlideAnimationEnabled;
    }

    public void onConfigurationChanged(Configuration configuration) {
        if (!this.mHasCustomUpIndicator) {
            this.mHomeAsUpIndicator = getThemeUpIndicator();
        }
        syncState();
    }

    @Override // androidx.drawerlayout.widget.DrawerLayout.d
    public void onDrawerClosed(View view) {
        setPosition(0.0f);
        if (this.mDrawerIndicatorEnabled) {
            setActionBarDescription(this.mOpenDrawerContentDescRes);
        }
    }

    @Override // androidx.drawerlayout.widget.DrawerLayout.d
    public void onDrawerOpened(View view) {
        setPosition(1.0f);
        if (this.mDrawerIndicatorEnabled) {
            setActionBarDescription(this.mCloseDrawerContentDescRes);
        }
    }

    @Override // androidx.drawerlayout.widget.DrawerLayout.d
    public void onDrawerSlide(View view, float f2) {
        if (this.mDrawerSlideAnimationEnabled) {
            setPosition(Math.min(1.0f, Math.max(0.0f, f2)));
        } else {
            setPosition(0.0f);
        }
    }

    @Override // androidx.drawerlayout.widget.DrawerLayout.d
    public void onDrawerStateChanged(int i) {
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem == null || menuItem.getItemId() != 16908332 || !this.mDrawerIndicatorEnabled) {
            return false;
        }
        toggle();
        return true;
    }

    void setActionBarDescription(int i) {
        this.mActivityImpl.a(i);
    }

    void setActionBarUpIndicator(Drawable drawable, int i) {
        if (!this.mWarnedForDisplayHomeAsUp && !this.mActivityImpl.a()) {
            Log.w("ActionBarDrawerToggle", "DrawerToggle may not show up because NavigationIcon is not visible. You may need to call actionbar.setDisplayHomeAsUpEnabled(true);");
            this.mWarnedForDisplayHomeAsUp = true;
        }
        this.mActivityImpl.a(drawable, i);
    }

    public void setDrawerArrowDrawable(a.a.l.a.d dVar) {
        this.mSlider = dVar;
        syncState();
    }

    public void setDrawerIndicatorEnabled(boolean z) {
        if (z != this.mDrawerIndicatorEnabled) {
            if (z) {
                setActionBarUpIndicator(this.mSlider, this.mDrawerLayout.e(8388611) ? this.mCloseDrawerContentDescRes : this.mOpenDrawerContentDescRes);
            } else {
                setActionBarUpIndicator(this.mHomeAsUpIndicator, 0);
            }
            this.mDrawerIndicatorEnabled = z;
        }
    }

    public void setDrawerSlideAnimationEnabled(boolean z) {
        this.mDrawerSlideAnimationEnabled = z;
        if (z) {
            return;
        }
        setPosition(0.0f);
    }

    public void setHomeAsUpIndicator(Drawable drawable) {
        if (drawable == null) {
            this.mHomeAsUpIndicator = getThemeUpIndicator();
            this.mHasCustomUpIndicator = false;
        } else {
            this.mHomeAsUpIndicator = drawable;
            this.mHasCustomUpIndicator = true;
        }
        if (this.mDrawerIndicatorEnabled) {
            return;
        }
        setActionBarUpIndicator(this.mHomeAsUpIndicator, 0);
    }

    public void setToolbarNavigationClickListener(View.OnClickListener onClickListener) {
        this.mToolbarNavigationClickListener = onClickListener;
    }

    public void syncState() {
        if (this.mDrawerLayout.e(8388611)) {
            setPosition(1.0f);
        } else {
            setPosition(0.0f);
        }
        if (this.mDrawerIndicatorEnabled) {
            setActionBarUpIndicator(this.mSlider, this.mDrawerLayout.e(8388611) ? this.mCloseDrawerContentDescRes : this.mOpenDrawerContentDescRes);
        }
    }

    void toggle() {
        int c2 = this.mDrawerLayout.c(8388611);
        if (this.mDrawerLayout.f(8388611) && c2 != 2) {
            this.mDrawerLayout.a(8388611);
        } else if (c2 != 1) {
            this.mDrawerLayout.g(8388611);
        }
    }

    /* loaded from: classes.dex */
    private static class d implements InterfaceC0039b {

        /* renamed from: a, reason: collision with root package name */
        private final Activity f653a;

        /* renamed from: b, reason: collision with root package name */
        private c.a f654b;

        d(Activity activity) {
            this.f653a = activity;
        }

        @Override // androidx.appcompat.app.b.InterfaceC0039b
        public boolean a() {
            ActionBar actionBar = this.f653a.getActionBar();
            return (actionBar == null || (actionBar.getDisplayOptions() & 4) == 0) ? false : true;
        }

        @Override // androidx.appcompat.app.b.InterfaceC0039b
        public Drawable b() {
            if (Build.VERSION.SDK_INT >= 18) {
                TypedArray obtainStyledAttributes = c().obtainStyledAttributes(null, new int[]{R.attr.homeAsUpIndicator}, R.attr.actionBarStyle, 0);
                Drawable drawable = obtainStyledAttributes.getDrawable(0);
                obtainStyledAttributes.recycle();
                return drawable;
            }
            return androidx.appcompat.app.c.a(this.f653a);
        }

        @Override // androidx.appcompat.app.b.InterfaceC0039b
        public Context c() {
            ActionBar actionBar = this.f653a.getActionBar();
            if (actionBar != null) {
                return actionBar.getThemedContext();
            }
            return this.f653a;
        }

        @Override // androidx.appcompat.app.b.InterfaceC0039b
        public void a(Drawable drawable, int i) {
            ActionBar actionBar = this.f653a.getActionBar();
            if (actionBar != null) {
                if (Build.VERSION.SDK_INT >= 18) {
                    actionBar.setHomeAsUpIndicator(drawable);
                    actionBar.setHomeActionContentDescription(i);
                } else {
                    actionBar.setDisplayShowHomeEnabled(true);
                    this.f654b = androidx.appcompat.app.c.a(this.f653a, drawable, i);
                    actionBar.setDisplayShowHomeEnabled(false);
                }
            }
        }

        @Override // androidx.appcompat.app.b.InterfaceC0039b
        public void a(int i) {
            if (Build.VERSION.SDK_INT >= 18) {
                ActionBar actionBar = this.f653a.getActionBar();
                if (actionBar != null) {
                    actionBar.setHomeActionContentDescription(i);
                    return;
                }
                return;
            }
            this.f654b = androidx.appcompat.app.c.a(this.f654b, this.f653a, i);
        }
    }

    /* loaded from: classes.dex */
    static class e implements InterfaceC0039b {

        /* renamed from: a, reason: collision with root package name */
        final Toolbar f655a;

        /* renamed from: b, reason: collision with root package name */
        final Drawable f656b;

        /* renamed from: c, reason: collision with root package name */
        final CharSequence f657c;

        e(Toolbar toolbar) {
            this.f655a = toolbar;
            this.f656b = toolbar.getNavigationIcon();
            this.f657c = toolbar.getNavigationContentDescription();
        }

        @Override // androidx.appcompat.app.b.InterfaceC0039b
        public void a(Drawable drawable, int i) {
            this.f655a.setNavigationIcon(drawable);
            a(i);
        }

        @Override // androidx.appcompat.app.b.InterfaceC0039b
        public boolean a() {
            return true;
        }

        @Override // androidx.appcompat.app.b.InterfaceC0039b
        public Drawable b() {
            return this.f656b;
        }

        @Override // androidx.appcompat.app.b.InterfaceC0039b
        public Context c() {
            return this.f655a.getContext();
        }

        @Override // androidx.appcompat.app.b.InterfaceC0039b
        public void a(int i) {
            if (i == 0) {
                this.f655a.setNavigationContentDescription(this.f657c);
            } else {
                this.f655a.setNavigationContentDescription(i);
            }
        }
    }

    public b(Activity activity, DrawerLayout drawerLayout, Toolbar toolbar, int i, int i2) {
        this(activity, toolbar, drawerLayout, null, i, i2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    b(Activity activity, Toolbar toolbar, DrawerLayout drawerLayout, a.a.l.a.d dVar, int i, int i2) {
        this.mDrawerSlideAnimationEnabled = true;
        this.mDrawerIndicatorEnabled = true;
        this.mWarnedForDisplayHomeAsUp = false;
        if (toolbar != null) {
            this.mActivityImpl = new e(toolbar);
            toolbar.setNavigationOnClickListener(new a());
        } else if (activity instanceof c) {
            this.mActivityImpl = ((c) activity).getDrawerToggleDelegate();
        } else {
            this.mActivityImpl = new d(activity);
        }
        this.mDrawerLayout = drawerLayout;
        this.mOpenDrawerContentDescRes = i;
        this.mCloseDrawerContentDescRes = i2;
        if (dVar == null) {
            this.mSlider = new a.a.l.a.d(this.mActivityImpl.c());
        } else {
            this.mSlider = dVar;
        }
        this.mHomeAsUpIndicator = getThemeUpIndicator();
    }

    public void setHomeAsUpIndicator(int i) {
        setHomeAsUpIndicator(i != 0 ? this.mDrawerLayout.getResources().getDrawable(i) : null);
    }
}
