package com.google.android.material.tabs;

import a.f.l.t;
import a.f.l.w;
import android.R;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.text.Layout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.v0;
import androidx.preference.Preference;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.internal.l;
import com.google.android.material.internal.m;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

@ViewPager.e
/* loaded from: classes.dex */
public class TabLayout extends HorizontalScrollView {
    private static final String ACCESSIBILITY_CLASS_NAME = "androidx.appcompat.app.ActionBar.Tab";
    private static final int ANIMATION_DURATION = 300;
    static final int DEFAULT_GAP_TEXT_ICON = 8;
    private static final int DEFAULT_HEIGHT = 48;
    private static final int DEFAULT_HEIGHT_WITH_TEXT_ICON = 72;
    static final int FIXED_WRAP_GUTTER_MIN = 16;
    public static final int GRAVITY_CENTER = 1;
    public static final int GRAVITY_FILL = 0;
    public static final int INDICATOR_GRAVITY_BOTTOM = 0;
    public static final int INDICATOR_GRAVITY_CENTER = 1;
    public static final int INDICATOR_GRAVITY_STRETCH = 3;
    public static final int INDICATOR_GRAVITY_TOP = 2;
    private static final int INVALID_WIDTH = -1;
    private static final int MIN_INDICATOR_WIDTH = 24;
    public static final int MODE_AUTO = 2;
    public static final int MODE_FIXED = 1;
    public static final int MODE_SCROLLABLE = 0;
    public static final int TAB_LABEL_VISIBILITY_LABELED = 1;
    public static final int TAB_LABEL_VISIBILITY_UNLABELED = 0;
    private static final int TAB_MIN_WIDTH_MARGIN = 56;
    private static final a.f.k.e<h> tabPool = new a.f.k.g(16);
    private c adapterChangeListener;
    private int contentInsetStart;
    private e currentVpSelectedListener;
    boolean inlineLabel;
    int mode;
    private i pageChangeListener;
    private androidx.viewpager.widget.a pagerAdapter;
    private DataSetObserver pagerAdapterObserver;
    private final int requestedTabMaxWidth;
    private final int requestedTabMinWidth;
    private ValueAnimator scrollAnimator;
    private final int scrollableTabMinWidth;
    private final HashMap<d<? extends h>, e> selectedListenerMap;
    private final ArrayList<e> selectedListeners;
    private h selectedTab;
    private boolean setupViewPagerImplicitly;
    private final g slidingTabIndicator;
    final int tabBackgroundResId;
    int tabGravity;
    ColorStateList tabIconTint;
    PorterDuff.Mode tabIconTintMode;
    int tabIndicatorAnimationDuration;
    boolean tabIndicatorFullWidth;
    int tabIndicatorGravity;
    int tabMaxWidth;
    int tabPaddingBottom;
    int tabPaddingEnd;
    int tabPaddingStart;
    int tabPaddingTop;
    ColorStateList tabRippleColorStateList;
    Drawable tabSelectedIndicator;
    int tabTextAppearance;
    ColorStateList tabTextColors;
    float tabTextMultiLineSize;
    float tabTextSize;
    private final RectF tabViewContentBounds;
    private final a.f.k.e<j> tabViewPool;
    private final ArrayList<h> tabs;
    boolean unboundedRipple;
    ViewPager viewPager;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a implements e {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ d f2678a;

        a(TabLayout tabLayout, d dVar) {
            this.f2678a = dVar;
        }

        @Override // com.google.android.material.tabs.TabLayout.e
        public void onTabReselected(h hVar) {
            this.f2678a.onTabReselected(hVar);
        }

        @Override // com.google.android.material.tabs.TabLayout.e
        public void onTabSelected(h hVar) {
            this.f2678a.onTabSelected(hVar);
        }

        @Override // com.google.android.material.tabs.TabLayout.e
        public void onTabUnselected(h hVar) {
            this.f2678a.onTabUnselected(hVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class b implements ValueAnimator.AnimatorUpdateListener {
        b() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            TabLayout.this.scrollTo(((Integer) valueAnimator.getAnimatedValue()).intValue(), 0);
        }
    }

    @Deprecated
    /* loaded from: classes.dex */
    public interface d<T extends h> {
        void onTabReselected(T t);

        void onTabSelected(T t);

        void onTabUnselected(T t);
    }

    /* loaded from: classes.dex */
    public interface e {
        void onTabReselected(h hVar);

        void onTabSelected(h hVar);

        void onTabUnselected(h hVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class f extends DataSetObserver {
        f() {
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            TabLayout.this.populateFromPagerAdapter();
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            TabLayout.this.populateFromPagerAdapter();
        }
    }

    /* loaded from: classes.dex */
    public static class h {

        /* renamed from: a, reason: collision with root package name */
        private Object f2695a;

        /* renamed from: b, reason: collision with root package name */
        private Drawable f2696b;

        /* renamed from: c, reason: collision with root package name */
        private CharSequence f2697c;

        /* renamed from: d, reason: collision with root package name */
        private CharSequence f2698d;

        /* renamed from: f, reason: collision with root package name */
        private View f2700f;
        public TabLayout h;
        public j i;

        /* renamed from: e, reason: collision with root package name */
        private int f2699e = -1;
        private int g = 1;

        public int d() {
            return this.g;
        }

        public Object e() {
            return this.f2695a;
        }

        public CharSequence f() {
            return this.f2697c;
        }

        public boolean g() {
            TabLayout tabLayout = this.h;
            if (tabLayout != null) {
                return tabLayout.getSelectedTabPosition() == this.f2699e;
            }
            throw new IllegalArgumentException("Tab not attached to a TabLayout");
        }

        void h() {
            this.h = null;
            this.i = null;
            this.f2695a = null;
            this.f2696b = null;
            this.f2697c = null;
            this.f2698d = null;
            this.f2699e = -1;
            this.f2700f = null;
        }

        public void i() {
            TabLayout tabLayout = this.h;
            if (tabLayout != null) {
                tabLayout.selectTab(this);
                return;
            }
            throw new IllegalArgumentException("Tab not attached to a TabLayout");
        }

        void j() {
            j jVar = this.i;
            if (jVar != null) {
                jVar.b();
            }
        }

        public h a(Object obj) {
            this.f2695a = obj;
            return this;
        }

        public Drawable b() {
            return this.f2696b;
        }

        public int c() {
            return this.f2699e;
        }

        public View a() {
            return this.f2700f;
        }

        void b(int i) {
            this.f2699e = i;
        }

        public h c(int i) {
            TabLayout tabLayout = this.h;
            if (tabLayout != null) {
                b(tabLayout.getResources().getText(i));
                return this;
            }
            throw new IllegalArgumentException("Tab not attached to a TabLayout");
        }

        public h a(View view) {
            this.f2700f = view;
            j();
            return this;
        }

        public h b(CharSequence charSequence) {
            if (TextUtils.isEmpty(this.f2698d) && !TextUtils.isEmpty(charSequence)) {
                this.i.setContentDescription(charSequence);
            }
            this.f2697c = charSequence;
            j();
            return this;
        }

        public h a(int i) {
            a(LayoutInflater.from(this.i.getContext()).inflate(i, (ViewGroup) this.i, false));
            return this;
        }

        public h a(Drawable drawable) {
            this.f2696b = drawable;
            TabLayout tabLayout = this.h;
            if (tabLayout.tabGravity == 1 || tabLayout.mode == 2) {
                this.h.updateTabViews(true);
            }
            j();
            if (c.a.a.a.n.b.f2126a && this.i.e() && this.i.f2708f.isVisible()) {
                this.i.invalidate();
            }
            return this;
        }

        public h a(CharSequence charSequence) {
            this.f2698d = charSequence;
            j();
            return this;
        }
    }

    /* loaded from: classes.dex */
    public static class i implements ViewPager.j {

        /* renamed from: b, reason: collision with root package name */
        private final WeakReference<TabLayout> f2701b;

        /* renamed from: c, reason: collision with root package name */
        private int f2702c;

        /* renamed from: d, reason: collision with root package name */
        private int f2703d;

        public i(TabLayout tabLayout) {
            this.f2701b = new WeakReference<>(tabLayout);
        }

        void a() {
            this.f2703d = 0;
            this.f2702c = 0;
        }

        @Override // androidx.viewpager.widget.ViewPager.j
        public void onPageScrollStateChanged(int i) {
            this.f2702c = this.f2703d;
            this.f2703d = i;
        }

        @Override // androidx.viewpager.widget.ViewPager.j
        public void onPageScrolled(int i, float f2, int i2) {
            TabLayout tabLayout = this.f2701b.get();
            if (tabLayout != null) {
                tabLayout.setScrollPosition(i, f2, this.f2703d != 2 || this.f2702c == 1, (this.f2703d == 2 && this.f2702c == 0) ? false : true);
            }
        }

        @Override // androidx.viewpager.widget.ViewPager.j
        public void onPageSelected(int i) {
            TabLayout tabLayout = this.f2701b.get();
            if (tabLayout == null || tabLayout.getSelectedTabPosition() == i || i >= tabLayout.getTabCount()) {
                return;
            }
            int i2 = this.f2703d;
            tabLayout.selectTab(tabLayout.getTabAt(i), i2 == 0 || (i2 == 2 && this.f2702c == 0));
        }
    }

    /* loaded from: classes.dex */
    public final class j extends LinearLayout {

        /* renamed from: b, reason: collision with root package name */
        private h f2704b;

        /* renamed from: c, reason: collision with root package name */
        private TextView f2705c;

        /* renamed from: d, reason: collision with root package name */
        private ImageView f2706d;

        /* renamed from: e, reason: collision with root package name */
        private View f2707e;

        /* renamed from: f, reason: collision with root package name */
        private c.a.a.a.n.a f2708f;
        private View g;
        private TextView h;
        private ImageView i;
        private Drawable j;
        private int k;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes.dex */
        public class a implements View.OnLayoutChangeListener {

            /* renamed from: a, reason: collision with root package name */
            final /* synthetic */ View f2709a;

            a(View view) {
                this.f2709a = view;
            }

            @Override // android.view.View.OnLayoutChangeListener
            public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                if (this.f2709a.getVisibility() == 0) {
                    j.this.d(this.f2709a);
                }
            }
        }

        public j(Context context) {
            super(context);
            this.k = 2;
            a(context);
            w.b(this, TabLayout.this.tabPaddingStart, TabLayout.this.tabPaddingTop, TabLayout.this.tabPaddingEnd, TabLayout.this.tabPaddingBottom);
            setGravity(17);
            setOrientation(!TabLayout.this.inlineLabel ? 1 : 0);
            setClickable(true);
            w.a(this, t.a(getContext(), 1002));
            w.a(this, (a.f.l.a) null);
        }

        private FrameLayout d() {
            FrameLayout frameLayout = new FrameLayout(getContext());
            frameLayout.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
            return frameLayout;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean e() {
            return this.f2708f != null;
        }

        private void f() {
            FrameLayout frameLayout;
            if (c.a.a.a.n.b.f2126a) {
                frameLayout = d();
                addView(frameLayout, 0);
            } else {
                frameLayout = this;
            }
            this.f2706d = (ImageView) LayoutInflater.from(getContext()).inflate(c.a.a.a.h.design_layout_tab_icon, (ViewGroup) frameLayout, false);
            frameLayout.addView(this.f2706d, 0);
        }

        private void g() {
            FrameLayout frameLayout;
            if (c.a.a.a.n.b.f2126a) {
                frameLayout = d();
                addView(frameLayout);
            } else {
                frameLayout = this;
            }
            this.f2705c = (TextView) LayoutInflater.from(getContext()).inflate(c.a.a.a.h.design_layout_tab_text, (ViewGroup) frameLayout, false);
            frameLayout.addView(this.f2705c);
        }

        private c.a.a.a.n.a getBadge() {
            return this.f2708f;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int getContentWidth() {
            int i = 0;
            int i2 = 0;
            boolean z = false;
            for (View view : new View[]{this.f2705c, this.f2706d, this.g}) {
                if (view != null && view.getVisibility() == 0) {
                    i2 = z ? Math.min(i2, view.getLeft()) : view.getLeft();
                    i = z ? Math.max(i, view.getRight()) : view.getRight();
                    z = true;
                }
            }
            return i - i2;
        }

        private c.a.a.a.n.a getOrCreateBadge() {
            if (this.f2708f == null) {
                this.f2708f = c.a.a.a.n.a.a(getContext());
            }
            i();
            c.a.a.a.n.a aVar = this.f2708f;
            if (aVar != null) {
                return aVar;
            }
            throw new IllegalStateException("Unable to create badge");
        }

        private void h() {
            if (e() && this.f2707e != null) {
                setClipChildren(true);
                setClipToPadding(true);
                c.a.a.a.n.a aVar = this.f2708f;
                View view = this.f2707e;
                c.a.a.a.n.b.b(aVar, view, b(view));
                this.f2707e = null;
            }
        }

        private void i() {
            h hVar;
            h hVar2;
            if (e()) {
                if (this.g != null) {
                    h();
                    return;
                }
                if (this.f2706d != null && (hVar2 = this.f2704b) != null && hVar2.b() != null) {
                    View view = this.f2707e;
                    ImageView imageView = this.f2706d;
                    if (view != imageView) {
                        h();
                        c(this.f2706d);
                        return;
                    } else {
                        d(imageView);
                        return;
                    }
                }
                if (this.f2705c != null && (hVar = this.f2704b) != null && hVar.d() == 1) {
                    View view2 = this.f2707e;
                    TextView textView = this.f2705c;
                    if (view2 != textView) {
                        h();
                        c(this.f2705c);
                        return;
                    } else {
                        d(textView);
                        return;
                    }
                }
                h();
            }
        }

        @Override // android.view.ViewGroup, android.view.View
        protected void drawableStateChanged() {
            super.drawableStateChanged();
            int[] drawableState = getDrawableState();
            Drawable drawable = this.j;
            boolean z = false;
            if (drawable != null && drawable.isStateful()) {
                z = false | this.j.setState(drawableState);
            }
            if (z) {
                invalidate();
                TabLayout.this.invalidate();
            }
        }

        public h getTab() {
            return this.f2704b;
        }

        @Override // android.view.View
        public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
            super.onInitializeAccessibilityEvent(accessibilityEvent);
            accessibilityEvent.setClassName(TabLayout.ACCESSIBILITY_CLASS_NAME);
        }

        @Override // android.view.View
        @TargetApi(14)
        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
            super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
            accessibilityNodeInfo.setClassName(TabLayout.ACCESSIBILITY_CLASS_NAME);
            c.a.a.a.n.a aVar = this.f2708f;
            if (aVar == null || !aVar.isVisible()) {
                return;
            }
            accessibilityNodeInfo.setContentDescription(((Object) getContentDescription()) + ", " + ((Object) this.f2708f.b()));
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onMeasure(int i, int i2) {
            Layout layout;
            int size = View.MeasureSpec.getSize(i);
            int mode = View.MeasureSpec.getMode(i);
            int tabMaxWidth = TabLayout.this.getTabMaxWidth();
            if (tabMaxWidth > 0 && (mode == 0 || size > tabMaxWidth)) {
                i = View.MeasureSpec.makeMeasureSpec(TabLayout.this.tabMaxWidth, RecyclerView.UNDEFINED_DURATION);
            }
            super.onMeasure(i, i2);
            if (this.f2705c != null) {
                float f2 = TabLayout.this.tabTextSize;
                int i3 = this.k;
                ImageView imageView = this.f2706d;
                boolean z = true;
                if (imageView == null || imageView.getVisibility() != 0) {
                    TextView textView = this.f2705c;
                    if (textView != null && textView.getLineCount() > 1) {
                        f2 = TabLayout.this.tabTextMultiLineSize;
                    }
                } else {
                    i3 = 1;
                }
                float textSize = this.f2705c.getTextSize();
                int lineCount = this.f2705c.getLineCount();
                int d2 = androidx.core.widget.i.d(this.f2705c);
                if (f2 != textSize || (d2 >= 0 && i3 != d2)) {
                    if (TabLayout.this.mode == 1 && f2 > textSize && lineCount == 1 && ((layout = this.f2705c.getLayout()) == null || a(layout, 0, f2) > (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight())) {
                        z = false;
                    }
                    if (z) {
                        this.f2705c.setTextSize(0, f2);
                        this.f2705c.setMaxLines(i3);
                        super.onMeasure(i, i2);
                    }
                }
            }
        }

        @Override // android.view.View
        public boolean performClick() {
            boolean performClick = super.performClick();
            if (this.f2704b == null) {
                return performClick;
            }
            if (!performClick) {
                playSoundEffect(0);
            }
            this.f2704b.i();
            return true;
        }

        @Override // android.view.View
        public void setSelected(boolean z) {
            boolean z2 = isSelected() != z;
            super.setSelected(z);
            if (z2 && z && Build.VERSION.SDK_INT < 16) {
                sendAccessibilityEvent(4);
            }
            TextView textView = this.f2705c;
            if (textView != null) {
                textView.setSelected(z);
            }
            ImageView imageView = this.f2706d;
            if (imageView != null) {
                imageView.setSelected(z);
            }
            View view = this.g;
            if (view != null) {
                view.setSelected(z);
            }
        }

        void setTab(h hVar) {
            if (hVar != this.f2704b) {
                this.f2704b = hVar;
                b();
            }
        }

        private void c(View view) {
            if (e() && view != null) {
                setClipChildren(false);
                setClipToPadding(false);
                c.a.a.a.n.b.a(this.f2708f, view, b(view));
                this.f2707e = view;
            }
        }

        final void b() {
            h hVar = this.f2704b;
            Drawable drawable = null;
            View a2 = hVar != null ? hVar.a() : null;
            if (a2 != null) {
                ViewParent parent = a2.getParent();
                if (parent != this) {
                    if (parent != null) {
                        ((ViewGroup) parent).removeView(a2);
                    }
                    addView(a2);
                }
                this.g = a2;
                TextView textView = this.f2705c;
                if (textView != null) {
                    textView.setVisibility(8);
                }
                ImageView imageView = this.f2706d;
                if (imageView != null) {
                    imageView.setVisibility(8);
                    this.f2706d.setImageDrawable(null);
                }
                this.h = (TextView) a2.findViewById(R.id.text1);
                TextView textView2 = this.h;
                if (textView2 != null) {
                    this.k = androidx.core.widget.i.d(textView2);
                }
                this.i = (ImageView) a2.findViewById(R.id.icon);
            } else {
                View view = this.g;
                if (view != null) {
                    removeView(view);
                    this.g = null;
                }
                this.h = null;
                this.i = null;
            }
            if (this.g == null) {
                if (this.f2706d == null) {
                    f();
                }
                if (hVar != null && hVar.b() != null) {
                    drawable = androidx.core.graphics.drawable.a.i(hVar.b()).mutate();
                }
                if (drawable != null) {
                    androidx.core.graphics.drawable.a.a(drawable, TabLayout.this.tabIconTint);
                    PorterDuff.Mode mode = TabLayout.this.tabIconTintMode;
                    if (mode != null) {
                        androidx.core.graphics.drawable.a.a(drawable, mode);
                    }
                }
                if (this.f2705c == null) {
                    g();
                    this.k = androidx.core.widget.i.d(this.f2705c);
                }
                androidx.core.widget.i.d(this.f2705c, TabLayout.this.tabTextAppearance);
                ColorStateList colorStateList = TabLayout.this.tabTextColors;
                if (colorStateList != null) {
                    this.f2705c.setTextColor(colorStateList);
                }
                a(this.f2705c, this.f2706d);
                i();
                a(this.f2706d);
                a(this.f2705c);
            } else if (this.h != null || this.i != null) {
                a(this.h, this.i);
            }
            if (hVar != null && !TextUtils.isEmpty(hVar.f2698d)) {
                setContentDescription(hVar.f2698d);
            }
            setSelected(hVar != null && hVar.g());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void d(View view) {
            if (e() && view == this.f2707e) {
                c.a.a.a.n.b.c(this.f2708f, view, b(view));
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v3, types: [android.graphics.drawable.RippleDrawable] */
        /* JADX WARN: Type inference failed for: r2v3, types: [android.graphics.drawable.LayerDrawable] */
        public void a(Context context) {
            int i = TabLayout.this.tabBackgroundResId;
            if (i != 0) {
                this.j = a.a.k.a.a.c(context, i);
                Drawable drawable = this.j;
                if (drawable != null && drawable.isStateful()) {
                    this.j.setState(getDrawableState());
                }
            } else {
                this.j = null;
            }
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setColor(0);
            if (TabLayout.this.tabRippleColorStateList != null) {
                GradientDrawable gradientDrawable2 = new GradientDrawable();
                gradientDrawable2.setCornerRadius(1.0E-5f);
                gradientDrawable2.setColor(-1);
                ColorStateList a2 = c.a.a.a.z.b.a(TabLayout.this.tabRippleColorStateList);
                if (Build.VERSION.SDK_INT >= 21) {
                    if (TabLayout.this.unboundedRipple) {
                        gradientDrawable = null;
                    }
                    gradientDrawable = new RippleDrawable(a2, gradientDrawable, TabLayout.this.unboundedRipple ? null : gradientDrawable2);
                } else {
                    Drawable i2 = androidx.core.graphics.drawable.a.i(gradientDrawable2);
                    androidx.core.graphics.drawable.a.a(i2, a2);
                    gradientDrawable = new LayerDrawable(new Drawable[]{gradientDrawable, i2});
                }
            }
            w.a(this, gradientDrawable);
            TabLayout.this.invalidate();
        }

        final void c() {
            setOrientation(!TabLayout.this.inlineLabel ? 1 : 0);
            if (this.h == null && this.i == null) {
                a(this.f2705c, this.f2706d);
            } else {
                a(this.h, this.i);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(Canvas canvas) {
            Drawable drawable = this.j;
            if (drawable != null) {
                drawable.setBounds(getLeft(), getTop(), getRight(), getBottom());
                this.j.draw(canvas);
            }
        }

        void a() {
            setTab(null);
            setSelected(false);
        }

        private void a(View view) {
            if (view == null) {
                return;
            }
            view.addOnLayoutChangeListener(new a(view));
        }

        private void a(TextView textView, ImageView imageView) {
            h hVar = this.f2704b;
            Drawable mutate = (hVar == null || hVar.b() == null) ? null : androidx.core.graphics.drawable.a.i(this.f2704b.b()).mutate();
            h hVar2 = this.f2704b;
            CharSequence f2 = hVar2 != null ? hVar2.f() : null;
            if (imageView != null) {
                if (mutate != null) {
                    imageView.setImageDrawable(mutate);
                    imageView.setVisibility(0);
                    setVisibility(0);
                } else {
                    imageView.setVisibility(8);
                    imageView.setImageDrawable(null);
                }
            }
            boolean z = !TextUtils.isEmpty(f2);
            if (textView != null) {
                if (z) {
                    textView.setText(f2);
                    if (this.f2704b.g == 1) {
                        textView.setVisibility(0);
                    } else {
                        textView.setVisibility(8);
                    }
                    setVisibility(0);
                } else {
                    textView.setVisibility(8);
                    textView.setText((CharSequence) null);
                }
            }
            if (imageView != null) {
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) imageView.getLayoutParams();
                int a2 = (z && imageView.getVisibility() == 0) ? (int) m.a(getContext(), 8) : 0;
                if (TabLayout.this.inlineLabel) {
                    if (a2 != a.f.l.g.a(marginLayoutParams)) {
                        a.f.l.g.a(marginLayoutParams, a2);
                        marginLayoutParams.bottomMargin = 0;
                        imageView.setLayoutParams(marginLayoutParams);
                        imageView.requestLayout();
                    }
                } else if (a2 != marginLayoutParams.bottomMargin) {
                    marginLayoutParams.bottomMargin = a2;
                    a.f.l.g.a(marginLayoutParams, 0);
                    imageView.setLayoutParams(marginLayoutParams);
                    imageView.requestLayout();
                }
            }
            h hVar3 = this.f2704b;
            CharSequence charSequence = hVar3 != null ? hVar3.f2698d : null;
            if (z) {
                charSequence = null;
            }
            v0.a(this, charSequence);
        }

        private FrameLayout b(View view) {
            if ((view == this.f2706d || view == this.f2705c) && c.a.a.a.n.b.f2126a) {
                return (FrameLayout) view.getParent();
            }
            return null;
        }

        private float a(Layout layout, int i, float f2) {
            return layout.getLineWidth(i) * (f2 / layout.getPaint().getTextSize());
        }
    }

    /* loaded from: classes.dex */
    public static class k implements e {

        /* renamed from: a, reason: collision with root package name */
        private final ViewPager f2711a;

        public k(ViewPager viewPager) {
            this.f2711a = viewPager;
        }

        @Override // com.google.android.material.tabs.TabLayout.e
        public void onTabReselected(h hVar) {
        }

        @Override // com.google.android.material.tabs.TabLayout.e
        public void onTabSelected(h hVar) {
            this.f2711a.setCurrentItem(hVar.c());
        }

        @Override // com.google.android.material.tabs.TabLayout.e
        public void onTabUnselected(h hVar) {
        }
    }

    public TabLayout(Context context) {
        this(context, null);
    }

    private void addTabFromItemView(com.google.android.material.tabs.a aVar) {
        h newTab = newTab();
        CharSequence charSequence = aVar.f2712b;
        if (charSequence != null) {
            newTab.b(charSequence);
        }
        Drawable drawable = aVar.f2713c;
        if (drawable != null) {
            newTab.a(drawable);
        }
        int i2 = aVar.f2714d;
        if (i2 != 0) {
            newTab.a(i2);
        }
        if (!TextUtils.isEmpty(aVar.getContentDescription())) {
            newTab.a(aVar.getContentDescription());
        }
        addTab(newTab);
    }

    private void addTabView(h hVar) {
        j jVar = hVar.i;
        jVar.setSelected(false);
        jVar.setActivated(false);
        this.slidingTabIndicator.addView(jVar, hVar.c(), createLayoutParamsForTabs());
    }

    private void addViewInternal(View view) {
        if (view instanceof com.google.android.material.tabs.a) {
            addTabFromItemView((com.google.android.material.tabs.a) view);
            return;
        }
        throw new IllegalArgumentException("Only TabItem instances can be added to TabLayout");
    }

    private void animateToTab(int i2) {
        if (i2 == -1) {
            return;
        }
        if (getWindowToken() != null && w.F(this) && !this.slidingTabIndicator.a()) {
            int scrollX = getScrollX();
            int calculateScrollXForTab = calculateScrollXForTab(i2, 0.0f);
            if (scrollX != calculateScrollXForTab) {
                ensureScrollAnimator();
                this.scrollAnimator.setIntValues(scrollX, calculateScrollXForTab);
                this.scrollAnimator.start();
            }
            this.slidingTabIndicator.a(i2, this.tabIndicatorAnimationDuration);
            return;
        }
        setScrollPosition(i2, 0.0f, true);
    }

    private void applyModeAndGravity() {
        int i2 = this.mode;
        w.b(this.slidingTabIndicator, (i2 == 0 || i2 == 2) ? Math.max(0, this.contentInsetStart - this.tabPaddingStart) : 0, 0, 0, 0);
        int i3 = this.mode;
        if (i3 == 0) {
            this.slidingTabIndicator.setGravity(8388611);
        } else if (i3 == 1 || i3 == 2) {
            this.slidingTabIndicator.setGravity(1);
        }
        updateTabViews(true);
    }

    private int calculateScrollXForTab(int i2, float f2) {
        int i3 = this.mode;
        if (i3 != 0 && i3 != 2) {
            return 0;
        }
        View childAt = this.slidingTabIndicator.getChildAt(i2);
        int i4 = i2 + 1;
        View childAt2 = i4 < this.slidingTabIndicator.getChildCount() ? this.slidingTabIndicator.getChildAt(i4) : null;
        int width = childAt != null ? childAt.getWidth() : 0;
        int width2 = childAt2 != null ? childAt2.getWidth() : 0;
        int left = (childAt.getLeft() + (width / 2)) - (getWidth() / 2);
        int i5 = (int) ((width + width2) * 0.5f * f2);
        return w.q(this) == 0 ? left + i5 : left - i5;
    }

    private void configureTab(h hVar, int i2) {
        hVar.b(i2);
        this.tabs.add(i2, hVar);
        int size = this.tabs.size();
        while (true) {
            i2++;
            if (i2 >= size) {
                return;
            } else {
                this.tabs.get(i2).b(i2);
            }
        }
    }

    private static ColorStateList createColorStateList(int i2, int i3) {
        return new ColorStateList(new int[][]{HorizontalScrollView.SELECTED_STATE_SET, HorizontalScrollView.EMPTY_STATE_SET}, new int[]{i3, i2});
    }

    private LinearLayout.LayoutParams createLayoutParamsForTabs() {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -1);
        updateTabViewLayoutParams(layoutParams);
        return layoutParams;
    }

    private j createTabView(h hVar) {
        a.f.k.e<j> eVar = this.tabViewPool;
        j a2 = eVar != null ? eVar.a() : null;
        if (a2 == null) {
            a2 = new j(getContext());
        }
        a2.setTab(hVar);
        a2.setFocusable(true);
        a2.setMinimumWidth(getTabMinWidth());
        if (TextUtils.isEmpty(hVar.f2698d)) {
            a2.setContentDescription(hVar.f2697c);
        } else {
            a2.setContentDescription(hVar.f2698d);
        }
        return a2;
    }

    private void dispatchTabReselected(h hVar) {
        for (int size = this.selectedListeners.size() - 1; size >= 0; size--) {
            this.selectedListeners.get(size).onTabReselected(hVar);
        }
    }

    private void dispatchTabSelected(h hVar) {
        for (int size = this.selectedListeners.size() - 1; size >= 0; size--) {
            this.selectedListeners.get(size).onTabSelected(hVar);
        }
    }

    private void dispatchTabUnselected(h hVar) {
        for (int size = this.selectedListeners.size() - 1; size >= 0; size--) {
            this.selectedListeners.get(size).onTabUnselected(hVar);
        }
    }

    private void ensureScrollAnimator() {
        if (this.scrollAnimator == null) {
            this.scrollAnimator = new ValueAnimator();
            this.scrollAnimator.setInterpolator(c.a.a.a.m.a.f2094b);
            this.scrollAnimator.setDuration(this.tabIndicatorAnimationDuration);
            this.scrollAnimator.addUpdateListener(new b());
        }
    }

    private int getDefaultHeight() {
        int size = this.tabs.size();
        boolean z = false;
        int i2 = 0;
        while (true) {
            if (i2 < size) {
                h hVar = this.tabs.get(i2);
                if (hVar != null && hVar.b() != null && !TextUtils.isEmpty(hVar.f())) {
                    z = true;
                    break;
                }
                i2++;
            } else {
                break;
            }
        }
        return (!z || this.inlineLabel) ? 48 : 72;
    }

    private int getTabMinWidth() {
        int i2 = this.requestedTabMinWidth;
        if (i2 != -1) {
            return i2;
        }
        int i3 = this.mode;
        if (i3 == 0 || i3 == 2) {
            return this.scrollableTabMinWidth;
        }
        return 0;
    }

    private int getTabScrollRange() {
        return Math.max(0, ((this.slidingTabIndicator.getWidth() - getWidth()) - getPaddingLeft()) - getPaddingRight());
    }

    private void removeTabViewAt(int i2) {
        j jVar = (j) this.slidingTabIndicator.getChildAt(i2);
        this.slidingTabIndicator.removeViewAt(i2);
        if (jVar != null) {
            jVar.a();
            this.tabViewPool.a(jVar);
        }
        requestLayout();
    }

    private void setSelectedTabView(int i2) {
        int childCount = this.slidingTabIndicator.getChildCount();
        if (i2 < childCount) {
            int i3 = 0;
            while (i3 < childCount) {
                View childAt = this.slidingTabIndicator.getChildAt(i3);
                boolean z = true;
                childAt.setSelected(i3 == i2);
                if (i3 != i2) {
                    z = false;
                }
                childAt.setActivated(z);
                i3++;
            }
        }
    }

    private void updateAllTabs() {
        int size = this.tabs.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.tabs.get(i2).j();
        }
    }

    private void updateTabViewLayoutParams(LinearLayout.LayoutParams layoutParams) {
        if (this.mode == 1 && this.tabGravity == 0) {
            layoutParams.width = 0;
            layoutParams.weight = 1.0f;
        } else {
            layoutParams.width = -2;
            layoutParams.weight = 0.0f;
        }
    }

    public void addOnTabSelectedListener(e eVar) {
        if (this.selectedListeners.contains(eVar)) {
            return;
        }
        this.selectedListeners.add(eVar);
    }

    public void addTab(h hVar) {
        addTab(hVar, this.tabs.isEmpty());
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup
    public void addView(View view) {
        addViewInternal(view);
    }

    public void clearOnTabSelectedListeners() {
        this.selectedListeners.clear();
        this.selectedListenerMap.clear();
    }

    protected h createTabFromPool() {
        h a2 = tabPool.a();
        return a2 == null ? new h() : a2;
    }

    public int getSelectedTabPosition() {
        h hVar = this.selectedTab;
        if (hVar != null) {
            return hVar.c();
        }
        return -1;
    }

    public h getTabAt(int i2) {
        if (i2 < 0 || i2 >= getTabCount()) {
            return null;
        }
        return this.tabs.get(i2);
    }

    public int getTabCount() {
        return this.tabs.size();
    }

    public int getTabGravity() {
        return this.tabGravity;
    }

    public ColorStateList getTabIconTint() {
        return this.tabIconTint;
    }

    public int getTabIndicatorGravity() {
        return this.tabIndicatorGravity;
    }

    int getTabMaxWidth() {
        return this.tabMaxWidth;
    }

    public int getTabMode() {
        return this.mode;
    }

    public ColorStateList getTabRippleColor() {
        return this.tabRippleColorStateList;
    }

    public Drawable getTabSelectedIndicator() {
        return this.tabSelectedIndicator;
    }

    public ColorStateList getTabTextColors() {
        return this.tabTextColors;
    }

    public boolean hasUnboundedRipple() {
        return this.unboundedRipple;
    }

    public boolean isInlineLabel() {
        return this.inlineLabel;
    }

    public boolean isTabIndicatorFullWidth() {
        return this.tabIndicatorFullWidth;
    }

    public h newTab() {
        h createTabFromPool = createTabFromPool();
        createTabFromPool.h = this;
        createTabFromPool.i = createTabView(createTabFromPool);
        return createTabFromPool;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        c.a.a.a.b0.h.a(this);
        if (this.viewPager == null) {
            ViewParent parent = getParent();
            if (parent instanceof ViewPager) {
                setupWithViewPager((ViewPager) parent, true, true);
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.setupViewPagerImplicitly) {
            setupWithViewPager(null);
            this.setupViewPagerImplicitly = false;
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        for (int i2 = 0; i2 < this.slidingTabIndicator.getChildCount(); i2++) {
            View childAt = this.slidingTabIndicator.getChildAt(i2);
            if (childAt instanceof j) {
                ((j) childAt).a(canvas);
            }
        }
        super.onDraw(canvas);
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0070, code lost:
    
        if (r0 != 2) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x007b, code lost:
    
        if (r7.getMeasuredWidth() != getMeasuredWidth()) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x007d, code lost:
    
        r4 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0087, code lost:
    
        if (r7.getMeasuredWidth() < getMeasuredWidth()) goto L29;
     */
    @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void onMeasure(int r7, int r8) {
        /*
            r6 = this;
            android.content.Context r0 = r6.getContext()
            int r1 = r6.getDefaultHeight()
            float r0 = com.google.android.material.internal.m.a(r0, r1)
            int r0 = (int) r0
            int r1 = android.view.View.MeasureSpec.getMode(r8)
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = 1073741824(0x40000000, float:2.0)
            r4 = 0
            r5 = 1
            if (r1 == r2) goto L2b
            if (r1 == 0) goto L1c
            goto L3e
        L1c:
            int r8 = r6.getPaddingTop()
            int r0 = r0 + r8
            int r8 = r6.getPaddingBottom()
            int r0 = r0 + r8
            int r8 = android.view.View.MeasureSpec.makeMeasureSpec(r0, r3)
            goto L3e
        L2b:
            int r1 = r6.getChildCount()
            if (r1 != r5) goto L3e
            int r1 = android.view.View.MeasureSpec.getSize(r8)
            if (r1 < r0) goto L3e
            android.view.View r1 = r6.getChildAt(r4)
            r1.setMinimumHeight(r0)
        L3e:
            int r0 = android.view.View.MeasureSpec.getSize(r7)
            int r1 = android.view.View.MeasureSpec.getMode(r7)
            if (r1 == 0) goto L5c
            int r1 = r6.requestedTabMaxWidth
            if (r1 <= 0) goto L4d
            goto L5a
        L4d:
            float r0 = (float) r0
            android.content.Context r1 = r6.getContext()
            r2 = 56
            float r1 = com.google.android.material.internal.m.a(r1, r2)
            float r0 = r0 - r1
            int r1 = (int) r0
        L5a:
            r6.tabMaxWidth = r1
        L5c:
            super.onMeasure(r7, r8)
            int r7 = r6.getChildCount()
            if (r7 != r5) goto Laa
            android.view.View r7 = r6.getChildAt(r4)
            int r0 = r6.mode
            if (r0 == 0) goto L7f
            if (r0 == r5) goto L73
            r1 = 2
            if (r0 == r1) goto L7f
            goto L8a
        L73:
            int r0 = r7.getMeasuredWidth()
            int r1 = r6.getMeasuredWidth()
            if (r0 == r1) goto L8a
        L7d:
            r4 = 1
            goto L8a
        L7f:
            int r0 = r7.getMeasuredWidth()
            int r1 = r6.getMeasuredWidth()
            if (r0 >= r1) goto L8a
            goto L7d
        L8a:
            if (r4 == 0) goto Laa
            int r0 = r6.getPaddingTop()
            int r1 = r6.getPaddingBottom()
            int r0 = r0 + r1
            android.view.ViewGroup$LayoutParams r1 = r7.getLayoutParams()
            int r1 = r1.height
            int r8 = android.widget.HorizontalScrollView.getChildMeasureSpec(r8, r0, r1)
            int r0 = r6.getMeasuredWidth()
            int r0 = android.view.View.MeasureSpec.makeMeasureSpec(r0, r3)
            r7.measure(r0, r8)
        Laa:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.tabs.TabLayout.onMeasure(int, int):void");
    }

    void populateFromPagerAdapter() {
        int currentItem;
        removeAllTabs();
        androidx.viewpager.widget.a aVar = this.pagerAdapter;
        if (aVar != null) {
            int count = aVar.getCount();
            for (int i2 = 0; i2 < count; i2++) {
                h newTab = newTab();
                newTab.b(this.pagerAdapter.getPageTitle(i2));
                addTab(newTab, false);
            }
            ViewPager viewPager = this.viewPager;
            if (viewPager == null || count <= 0 || (currentItem = viewPager.getCurrentItem()) == getSelectedTabPosition() || currentItem >= getTabCount()) {
                return;
            }
            selectTab(getTabAt(currentItem));
        }
    }

    protected boolean releaseFromTabPool(h hVar) {
        return tabPool.a(hVar);
    }

    public void removeAllTabs() {
        for (int childCount = this.slidingTabIndicator.getChildCount() - 1; childCount >= 0; childCount--) {
            removeTabViewAt(childCount);
        }
        Iterator<h> it = this.tabs.iterator();
        while (it.hasNext()) {
            h next = it.next();
            it.remove();
            next.h();
            releaseFromTabPool(next);
        }
        this.selectedTab = null;
    }

    public void removeOnTabSelectedListener(e eVar) {
        this.selectedListeners.remove(eVar);
    }

    public void removeTab(h hVar) {
        if (hVar.h == this) {
            removeTabAt(hVar.c());
            return;
        }
        throw new IllegalArgumentException("Tab does not belong to this TabLayout.");
    }

    public void removeTabAt(int i2) {
        h hVar = this.selectedTab;
        int c2 = hVar != null ? hVar.c() : 0;
        removeTabViewAt(i2);
        h remove = this.tabs.remove(i2);
        if (remove != null) {
            remove.h();
            releaseFromTabPool(remove);
        }
        int size = this.tabs.size();
        for (int i3 = i2; i3 < size; i3++) {
            this.tabs.get(i3).b(i3);
        }
        if (c2 == i2) {
            selectTab(this.tabs.isEmpty() ? null : this.tabs.get(Math.max(0, i2 - 1)));
        }
    }

    public void selectTab(h hVar) {
        selectTab(hVar, true);
    }

    @Override // android.view.View
    public void setElevation(float f2) {
        super.setElevation(f2);
        c.a.a.a.b0.h.a(this, f2);
    }

    public void setInlineLabel(boolean z) {
        if (this.inlineLabel != z) {
            this.inlineLabel = z;
            for (int i2 = 0; i2 < this.slidingTabIndicator.getChildCount(); i2++) {
                View childAt = this.slidingTabIndicator.getChildAt(i2);
                if (childAt instanceof j) {
                    ((j) childAt).c();
                }
            }
            applyModeAndGravity();
        }
    }

    public void setInlineLabelResource(int i2) {
        setInlineLabel(getResources().getBoolean(i2));
    }

    @Deprecated
    public void setOnTabSelectedListener(d dVar) {
        clearOnTabSelectedListeners();
        addOnTabSelectedListener(wrapOnTabSelectedListener(dVar));
    }

    void setPagerAdapter(androidx.viewpager.widget.a aVar, boolean z) {
        DataSetObserver dataSetObserver;
        androidx.viewpager.widget.a aVar2 = this.pagerAdapter;
        if (aVar2 != null && (dataSetObserver = this.pagerAdapterObserver) != null) {
            aVar2.unregisterDataSetObserver(dataSetObserver);
        }
        this.pagerAdapter = aVar;
        if (z && aVar != null) {
            if (this.pagerAdapterObserver == null) {
                this.pagerAdapterObserver = new f();
            }
            aVar.registerDataSetObserver(this.pagerAdapterObserver);
        }
        populateFromPagerAdapter();
    }

    void setScrollAnimatorListener(Animator.AnimatorListener animatorListener) {
        ensureScrollAnimator();
        this.scrollAnimator.addListener(animatorListener);
    }

    public void setScrollPosition(int i2, float f2, boolean z) {
        setScrollPosition(i2, f2, z, true);
    }

    public void setSelectedTabIndicator(Drawable drawable) {
        if (this.tabSelectedIndicator != drawable) {
            this.tabSelectedIndicator = drawable;
            w.K(this.slidingTabIndicator);
        }
    }

    public void setSelectedTabIndicatorColor(int i2) {
        this.slidingTabIndicator.a(i2);
    }

    public void setSelectedTabIndicatorGravity(int i2) {
        if (this.tabIndicatorGravity != i2) {
            this.tabIndicatorGravity = i2;
            w.K(this.slidingTabIndicator);
        }
    }

    @Deprecated
    public void setSelectedTabIndicatorHeight(int i2) {
        this.slidingTabIndicator.b(i2);
    }

    public void setTabGravity(int i2) {
        if (this.tabGravity != i2) {
            this.tabGravity = i2;
            applyModeAndGravity();
        }
    }

    public void setTabIconTint(ColorStateList colorStateList) {
        if (this.tabIconTint != colorStateList) {
            this.tabIconTint = colorStateList;
            updateAllTabs();
        }
    }

    public void setTabIconTintResource(int i2) {
        setTabIconTint(a.a.k.a.a.b(getContext(), i2));
    }

    public void setTabIndicatorFullWidth(boolean z) {
        this.tabIndicatorFullWidth = z;
        w.K(this.slidingTabIndicator);
    }

    public void setTabMode(int i2) {
        if (i2 != this.mode) {
            this.mode = i2;
            applyModeAndGravity();
        }
    }

    public void setTabRippleColor(ColorStateList colorStateList) {
        if (this.tabRippleColorStateList != colorStateList) {
            this.tabRippleColorStateList = colorStateList;
            for (int i2 = 0; i2 < this.slidingTabIndicator.getChildCount(); i2++) {
                View childAt = this.slidingTabIndicator.getChildAt(i2);
                if (childAt instanceof j) {
                    ((j) childAt).a(getContext());
                }
            }
        }
    }

    public void setTabRippleColorResource(int i2) {
        setTabRippleColor(a.a.k.a.a.b(getContext(), i2));
    }

    public void setTabTextColors(ColorStateList colorStateList) {
        if (this.tabTextColors != colorStateList) {
            this.tabTextColors = colorStateList;
            updateAllTabs();
        }
    }

    @Deprecated
    public void setTabsFromPagerAdapter(androidx.viewpager.widget.a aVar) {
        setPagerAdapter(aVar, false);
    }

    public void setUnboundedRipple(boolean z) {
        if (this.unboundedRipple != z) {
            this.unboundedRipple = z;
            for (int i2 = 0; i2 < this.slidingTabIndicator.getChildCount(); i2++) {
                View childAt = this.slidingTabIndicator.getChildAt(i2);
                if (childAt instanceof j) {
                    ((j) childAt).a(getContext());
                }
            }
        }
    }

    public void setUnboundedRippleResource(int i2) {
        setUnboundedRipple(getResources().getBoolean(i2));
    }

    public void setupWithViewPager(ViewPager viewPager) {
        setupWithViewPager(viewPager, true);
    }

    @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return getTabScrollRange() > 0;
    }

    void updateTabViews(boolean z) {
        for (int i2 = 0; i2 < this.slidingTabIndicator.getChildCount(); i2++) {
            View childAt = this.slidingTabIndicator.getChildAt(i2);
            childAt.setMinimumWidth(getTabMinWidth());
            updateTabViewLayoutParams((LinearLayout.LayoutParams) childAt.getLayoutParams());
            if (z) {
                childAt.requestLayout();
            }
        }
    }

    protected e wrapOnTabSelectedListener(d dVar) {
        if (dVar == null) {
            return null;
        }
        if (this.selectedListenerMap.containsKey(dVar)) {
            return this.selectedListenerMap.get(dVar);
        }
        a aVar = new a(this, dVar);
        this.selectedListenerMap.put(dVar, aVar);
        return aVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class c implements ViewPager.i {

        /* renamed from: a, reason: collision with root package name */
        private boolean f2680a;

        c() {
        }

        @Override // androidx.viewpager.widget.ViewPager.i
        public void a(ViewPager viewPager, androidx.viewpager.widget.a aVar, androidx.viewpager.widget.a aVar2) {
            TabLayout tabLayout = TabLayout.this;
            if (tabLayout.viewPager == viewPager) {
                tabLayout.setPagerAdapter(aVar2, this.f2680a);
            }
        }

        void a(boolean z) {
            this.f2680a = z;
        }
    }

    public TabLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, c.a.a.a.b.tabStyle);
    }

    public void addTab(h hVar, int i2) {
        addTab(hVar, i2, this.tabs.isEmpty());
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup
    public void addView(View view, int i2) {
        addViewInternal(view);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public FrameLayout.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return generateDefaultLayoutParams();
    }

    @Deprecated
    public void removeOnTabSelectedListener(d dVar) {
        removeOnTabSelectedListener(wrapOnTabSelectedListener(dVar));
    }

    public void selectTab(h hVar, boolean z) {
        h hVar2 = this.selectedTab;
        if (hVar2 == hVar) {
            if (hVar2 != null) {
                dispatchTabReselected(hVar);
                animateToTab(hVar.c());
                return;
            }
            return;
        }
        int c2 = hVar != null ? hVar.c() : -1;
        if (z) {
            if ((hVar2 == null || hVar2.c() == -1) && c2 != -1) {
                setScrollPosition(c2, 0.0f, true);
            } else {
                animateToTab(c2);
            }
            if (c2 != -1) {
                setSelectedTabView(c2);
            }
        }
        this.selectedTab = hVar;
        if (hVar2 != null) {
            dispatchTabUnselected(hVar2);
        }
        if (hVar != null) {
            dispatchTabSelected(hVar);
        }
    }

    public void setScrollPosition(int i2, float f2, boolean z, boolean z2) {
        int round = Math.round(i2 + f2);
        if (round < 0 || round >= this.slidingTabIndicator.getChildCount()) {
            return;
        }
        if (z2) {
            this.slidingTabIndicator.a(i2, f2);
        }
        ValueAnimator valueAnimator = this.scrollAnimator;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.scrollAnimator.cancel();
        }
        scrollTo(calculateScrollXForTab(i2, f2), 0);
        if (z) {
            setSelectedTabView(round);
        }
    }

    public void setupWithViewPager(ViewPager viewPager, boolean z) {
        setupWithViewPager(viewPager, z, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class g extends LinearLayout {

        /* renamed from: b, reason: collision with root package name */
        private int f2683b;

        /* renamed from: c, reason: collision with root package name */
        private final Paint f2684c;

        /* renamed from: d, reason: collision with root package name */
        private final GradientDrawable f2685d;

        /* renamed from: e, reason: collision with root package name */
        int f2686e;

        /* renamed from: f, reason: collision with root package name */
        float f2687f;
        private int g;
        private int h;
        private int i;
        private ValueAnimator j;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes.dex */
        public class a implements ValueAnimator.AnimatorUpdateListener {

            /* renamed from: a, reason: collision with root package name */
            final /* synthetic */ int f2688a;

            /* renamed from: b, reason: collision with root package name */
            final /* synthetic */ int f2689b;

            /* renamed from: c, reason: collision with root package name */
            final /* synthetic */ int f2690c;

            /* renamed from: d, reason: collision with root package name */
            final /* synthetic */ int f2691d;

            a(int i, int i2, int i3, int i4) {
                this.f2688a = i;
                this.f2689b = i2;
                this.f2690c = i3;
                this.f2691d = i4;
            }

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float animatedFraction = valueAnimator.getAnimatedFraction();
                g.this.b(c.a.a.a.m.a.a(this.f2688a, this.f2689b, animatedFraction), c.a.a.a.m.a.a(this.f2690c, this.f2691d, animatedFraction));
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes.dex */
        public class b extends AnimatorListenerAdapter {

            /* renamed from: a, reason: collision with root package name */
            final /* synthetic */ int f2693a;

            b(int i) {
                this.f2693a = i;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                g gVar = g.this;
                gVar.f2686e = this.f2693a;
                gVar.f2687f = 0.0f;
            }
        }

        g(Context context) {
            super(context);
            this.f2686e = -1;
            this.g = -1;
            this.h = -1;
            this.i = -1;
            setWillNotDraw(false);
            this.f2684c = new Paint();
            this.f2685d = new GradientDrawable();
        }

        void a(int i) {
            if (this.f2684c.getColor() != i) {
                this.f2684c.setColor(i);
                w.K(this);
            }
        }

        void b(int i) {
            if (this.f2683b != i) {
                this.f2683b = i;
                w.K(this);
            }
        }

        @Override // android.view.View
        public void draw(Canvas canvas) {
            Drawable drawable = TabLayout.this.tabSelectedIndicator;
            int i = 0;
            int intrinsicHeight = drawable != null ? drawable.getIntrinsicHeight() : 0;
            int i2 = this.f2683b;
            if (i2 >= 0) {
                intrinsicHeight = i2;
            }
            int i3 = TabLayout.this.tabIndicatorGravity;
            if (i3 == 0) {
                i = getHeight() - intrinsicHeight;
                intrinsicHeight = getHeight();
            } else if (i3 == 1) {
                i = (getHeight() - intrinsicHeight) / 2;
                intrinsicHeight = (getHeight() + intrinsicHeight) / 2;
            } else if (i3 != 2) {
                intrinsicHeight = i3 != 3 ? 0 : getHeight();
            }
            int i4 = this.h;
            if (i4 >= 0 && this.i > i4) {
                Drawable drawable2 = TabLayout.this.tabSelectedIndicator;
                if (drawable2 == null) {
                    drawable2 = this.f2685d;
                }
                Drawable i5 = androidx.core.graphics.drawable.a.i(drawable2);
                i5.setBounds(this.h, i, this.i, intrinsicHeight);
                Paint paint = this.f2684c;
                if (paint != null) {
                    if (Build.VERSION.SDK_INT == 21) {
                        i5.setColorFilter(paint.getColor(), PorterDuff.Mode.SRC_IN);
                    } else {
                        androidx.core.graphics.drawable.a.b(i5, paint.getColor());
                    }
                }
                i5.draw(canvas);
            }
            super.draw(canvas);
        }

        @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
        protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
            super.onLayout(z, i, i2, i3, i4);
            ValueAnimator valueAnimator = this.j;
            if (valueAnimator != null && valueAnimator.isRunning()) {
                this.j.cancel();
                a(this.f2686e, Math.round((1.0f - this.j.getAnimatedFraction()) * ((float) this.j.getDuration())));
                return;
            }
            b();
        }

        @Override // android.widget.LinearLayout, android.view.View
        protected void onMeasure(int i, int i2) {
            boolean z;
            super.onMeasure(i, i2);
            if (View.MeasureSpec.getMode(i) != 1073741824) {
                return;
            }
            TabLayout tabLayout = TabLayout.this;
            if (tabLayout.tabGravity == 1 || tabLayout.mode == 2) {
                int childCount = getChildCount();
                int i3 = 0;
                for (int i4 = 0; i4 < childCount; i4++) {
                    View childAt = getChildAt(i4);
                    if (childAt.getVisibility() == 0) {
                        i3 = Math.max(i3, childAt.getMeasuredWidth());
                    }
                }
                if (i3 <= 0) {
                    return;
                }
                if (i3 * childCount <= getMeasuredWidth() - (((int) m.a(getContext(), 16)) * 2)) {
                    z = false;
                    for (int i5 = 0; i5 < childCount; i5++) {
                        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) getChildAt(i5).getLayoutParams();
                        if (layoutParams.width != i3 || layoutParams.weight != 0.0f) {
                            layoutParams.width = i3;
                            layoutParams.weight = 0.0f;
                            z = true;
                        }
                    }
                } else {
                    TabLayout tabLayout2 = TabLayout.this;
                    tabLayout2.tabGravity = 0;
                    tabLayout2.updateTabViews(false);
                    z = true;
                }
                if (z) {
                    super.onMeasure(i, i2);
                }
            }
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onRtlPropertiesChanged(int i) {
            super.onRtlPropertiesChanged(i);
            if (Build.VERSION.SDK_INT >= 23 || this.g == i) {
                return;
            }
            requestLayout();
            this.g = i;
        }

        private void b() {
            int i;
            int i2;
            View childAt = getChildAt(this.f2686e);
            if (childAt == null || childAt.getWidth() <= 0) {
                i = -1;
                i2 = -1;
            } else {
                i = childAt.getLeft();
                i2 = childAt.getRight();
                TabLayout tabLayout = TabLayout.this;
                if (!tabLayout.tabIndicatorFullWidth && (childAt instanceof j)) {
                    a((j) childAt, tabLayout.tabViewContentBounds);
                    i = (int) TabLayout.this.tabViewContentBounds.left;
                    i2 = (int) TabLayout.this.tabViewContentBounds.right;
                }
                if (this.f2687f > 0.0f && this.f2686e < getChildCount() - 1) {
                    View childAt2 = getChildAt(this.f2686e + 1);
                    int left = childAt2.getLeft();
                    int right = childAt2.getRight();
                    TabLayout tabLayout2 = TabLayout.this;
                    if (!tabLayout2.tabIndicatorFullWidth && (childAt2 instanceof j)) {
                        a((j) childAt2, tabLayout2.tabViewContentBounds);
                        left = (int) TabLayout.this.tabViewContentBounds.left;
                        right = (int) TabLayout.this.tabViewContentBounds.right;
                    }
                    float f2 = this.f2687f;
                    i = (int) ((left * f2) + ((1.0f - f2) * i));
                    i2 = (int) ((right * f2) + ((1.0f - f2) * i2));
                }
            }
            b(i, i2);
        }

        boolean a() {
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                if (getChildAt(i).getWidth() <= 0) {
                    return true;
                }
            }
            return false;
        }

        void a(int i, float f2) {
            ValueAnimator valueAnimator = this.j;
            if (valueAnimator != null && valueAnimator.isRunning()) {
                this.j.cancel();
            }
            this.f2686e = i;
            this.f2687f = f2;
            b();
        }

        void a(int i, int i2) {
            ValueAnimator valueAnimator = this.j;
            if (valueAnimator != null && valueAnimator.isRunning()) {
                this.j.cancel();
            }
            View childAt = getChildAt(i);
            if (childAt == null) {
                b();
                return;
            }
            int left = childAt.getLeft();
            int right = childAt.getRight();
            TabLayout tabLayout = TabLayout.this;
            if (!tabLayout.tabIndicatorFullWidth && (childAt instanceof j)) {
                a((j) childAt, tabLayout.tabViewContentBounds);
                left = (int) TabLayout.this.tabViewContentBounds.left;
                right = (int) TabLayout.this.tabViewContentBounds.right;
            }
            int i3 = left;
            int i4 = right;
            int i5 = this.h;
            int i6 = this.i;
            if (i5 == i3 && i6 == i4) {
                return;
            }
            ValueAnimator valueAnimator2 = new ValueAnimator();
            this.j = valueAnimator2;
            valueAnimator2.setInterpolator(c.a.a.a.m.a.f2094b);
            valueAnimator2.setDuration(i2);
            valueAnimator2.setFloatValues(0.0f, 1.0f);
            valueAnimator2.addUpdateListener(new a(i5, i3, i6, i4));
            valueAnimator2.addListener(new b(i));
            valueAnimator2.start();
        }

        void b(int i, int i2) {
            if (i == this.h && i2 == this.i) {
                return;
            }
            this.h = i;
            this.i = i2;
            w.K(this);
        }

        private void a(j jVar, RectF rectF) {
            int contentWidth = jVar.getContentWidth();
            int a2 = (int) m.a(getContext(), 24);
            if (contentWidth < a2) {
                contentWidth = a2;
            }
            int left = (jVar.getLeft() + jVar.getRight()) / 2;
            int i = contentWidth / 2;
            rectF.set(left - i, 0.0f, left + i, 0.0f);
        }
    }

    public TabLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.tabs = new ArrayList<>();
        this.tabViewContentBounds = new RectF();
        this.tabMaxWidth = Preference.DEFAULT_ORDER;
        this.selectedListeners = new ArrayList<>();
        this.selectedListenerMap = new HashMap<>();
        this.tabViewPool = new a.f.k.f(12);
        setHorizontalScrollBarEnabled(false);
        this.slidingTabIndicator = new g(context);
        super.addView(this.slidingTabIndicator, 0, new FrameLayout.LayoutParams(-2, -1));
        TypedArray c2 = l.c(context, attributeSet, c.a.a.a.l.TabLayout, i2, c.a.a.a.k.Widget_Design_TabLayout, c.a.a.a.l.TabLayout_tabTextAppearance);
        if (getBackground() instanceof ColorDrawable) {
            ColorDrawable colorDrawable = (ColorDrawable) getBackground();
            c.a.a.a.b0.g gVar = new c.a.a.a.b0.g();
            gVar.a(ColorStateList.valueOf(colorDrawable.getColor()));
            gVar.a(context);
            gVar.b(w.l(this));
            w.a(this, gVar);
        }
        this.slidingTabIndicator.b(c2.getDimensionPixelSize(c.a.a.a.l.TabLayout_tabIndicatorHeight, -1));
        this.slidingTabIndicator.a(c2.getColor(c.a.a.a.l.TabLayout_tabIndicatorColor, 0));
        setSelectedTabIndicator(c.a.a.a.y.c.b(context, c2, c.a.a.a.l.TabLayout_tabIndicator));
        setSelectedTabIndicatorGravity(c2.getInt(c.a.a.a.l.TabLayout_tabIndicatorGravity, 0));
        setTabIndicatorFullWidth(c2.getBoolean(c.a.a.a.l.TabLayout_tabIndicatorFullWidth, true));
        int dimensionPixelSize = c2.getDimensionPixelSize(c.a.a.a.l.TabLayout_tabPadding, 0);
        this.tabPaddingBottom = dimensionPixelSize;
        this.tabPaddingEnd = dimensionPixelSize;
        this.tabPaddingTop = dimensionPixelSize;
        this.tabPaddingStart = dimensionPixelSize;
        this.tabPaddingStart = c2.getDimensionPixelSize(c.a.a.a.l.TabLayout_tabPaddingStart, this.tabPaddingStart);
        this.tabPaddingTop = c2.getDimensionPixelSize(c.a.a.a.l.TabLayout_tabPaddingTop, this.tabPaddingTop);
        this.tabPaddingEnd = c2.getDimensionPixelSize(c.a.a.a.l.TabLayout_tabPaddingEnd, this.tabPaddingEnd);
        this.tabPaddingBottom = c2.getDimensionPixelSize(c.a.a.a.l.TabLayout_tabPaddingBottom, this.tabPaddingBottom);
        this.tabTextAppearance = c2.getResourceId(c.a.a.a.l.TabLayout_tabTextAppearance, c.a.a.a.k.TextAppearance_Design_Tab);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(this.tabTextAppearance, a.a.j.TextAppearance);
        try {
            this.tabTextSize = obtainStyledAttributes.getDimensionPixelSize(a.a.j.TextAppearance_android_textSize, 0);
            this.tabTextColors = c.a.a.a.y.c.a(context, obtainStyledAttributes, a.a.j.TextAppearance_android_textColor);
            obtainStyledAttributes.recycle();
            if (c2.hasValue(c.a.a.a.l.TabLayout_tabTextColor)) {
                this.tabTextColors = c.a.a.a.y.c.a(context, c2, c.a.a.a.l.TabLayout_tabTextColor);
            }
            if (c2.hasValue(c.a.a.a.l.TabLayout_tabSelectedTextColor)) {
                this.tabTextColors = createColorStateList(this.tabTextColors.getDefaultColor(), c2.getColor(c.a.a.a.l.TabLayout_tabSelectedTextColor, 0));
            }
            this.tabIconTint = c.a.a.a.y.c.a(context, c2, c.a.a.a.l.TabLayout_tabIconTint);
            this.tabIconTintMode = m.a(c2.getInt(c.a.a.a.l.TabLayout_tabIconTintMode, -1), (PorterDuff.Mode) null);
            this.tabRippleColorStateList = c.a.a.a.y.c.a(context, c2, c.a.a.a.l.TabLayout_tabRippleColor);
            this.tabIndicatorAnimationDuration = c2.getInt(c.a.a.a.l.TabLayout_tabIndicatorAnimationDuration, ANIMATION_DURATION);
            this.requestedTabMinWidth = c2.getDimensionPixelSize(c.a.a.a.l.TabLayout_tabMinWidth, -1);
            this.requestedTabMaxWidth = c2.getDimensionPixelSize(c.a.a.a.l.TabLayout_tabMaxWidth, -1);
            this.tabBackgroundResId = c2.getResourceId(c.a.a.a.l.TabLayout_tabBackground, 0);
            this.contentInsetStart = c2.getDimensionPixelSize(c.a.a.a.l.TabLayout_tabContentStart, 0);
            this.mode = c2.getInt(c.a.a.a.l.TabLayout_tabMode, 1);
            this.tabGravity = c2.getInt(c.a.a.a.l.TabLayout_tabGravity, 0);
            this.inlineLabel = c2.getBoolean(c.a.a.a.l.TabLayout_tabInlineLabel, false);
            this.unboundedRipple = c2.getBoolean(c.a.a.a.l.TabLayout_tabUnboundedRipple, false);
            c2.recycle();
            Resources resources = getResources();
            this.tabTextMultiLineSize = resources.getDimensionPixelSize(c.a.a.a.d.design_tab_text_size_2line);
            this.scrollableTabMinWidth = resources.getDimensionPixelSize(c.a.a.a.d.design_tab_scrollable_min_width);
            applyModeAndGravity();
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }

    private void setupWithViewPager(ViewPager viewPager, boolean z, boolean z2) {
        ViewPager viewPager2 = this.viewPager;
        if (viewPager2 != null) {
            i iVar = this.pageChangeListener;
            if (iVar != null) {
                viewPager2.removeOnPageChangeListener(iVar);
            }
            c cVar = this.adapterChangeListener;
            if (cVar != null) {
                this.viewPager.removeOnAdapterChangeListener(cVar);
            }
        }
        e eVar = this.currentVpSelectedListener;
        if (eVar != null) {
            removeOnTabSelectedListener(eVar);
            this.currentVpSelectedListener = null;
        }
        if (viewPager != null) {
            this.viewPager = viewPager;
            if (this.pageChangeListener == null) {
                this.pageChangeListener = new i(this);
            }
            this.pageChangeListener.a();
            viewPager.addOnPageChangeListener(this.pageChangeListener);
            this.currentVpSelectedListener = new k(viewPager);
            addOnTabSelectedListener(this.currentVpSelectedListener);
            androidx.viewpager.widget.a adapter = viewPager.getAdapter();
            if (adapter != null) {
                setPagerAdapter(adapter, z);
            }
            if (this.adapterChangeListener == null) {
                this.adapterChangeListener = new c();
            }
            this.adapterChangeListener.a(z);
            viewPager.addOnAdapterChangeListener(this.adapterChangeListener);
            setScrollPosition(viewPager.getCurrentItem(), 0.0f, true);
        } else {
            this.viewPager = null;
            setPagerAdapter(null, false);
        }
        this.setupViewPagerImplicitly = z2;
    }

    @Deprecated
    public void addOnTabSelectedListener(d dVar) {
        addOnTabSelectedListener(wrapOnTabSelectedListener(dVar));
    }

    public void addTab(h hVar, boolean z) {
        addTab(hVar, this.tabs.size(), z);
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup, android.view.ViewManager
    public void addView(View view, ViewGroup.LayoutParams layoutParams) {
        addViewInternal(view);
    }

    public void addTab(h hVar, int i2, boolean z) {
        if (hVar.h == this) {
            configureTab(hVar, i2);
            addTabView(hVar);
            if (z) {
                hVar.i();
                return;
            }
            return;
        }
        throw new IllegalArgumentException("Tab belongs to a different TabLayout.");
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup
    public void addView(View view, int i2, ViewGroup.LayoutParams layoutParams) {
        addViewInternal(view);
    }

    public void setSelectedTabIndicator(int i2) {
        if (i2 != 0) {
            setSelectedTabIndicator(a.a.k.a.a.c(getContext(), i2));
        } else {
            setSelectedTabIndicator((Drawable) null);
        }
    }

    public void setTabTextColors(int i2, int i3) {
        setTabTextColors(createColorStateList(i2, i3));
    }
}
