package androidx.appcompat.widget;

import android.R;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.DecelerateInterpolator;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import androidx.appcompat.app.a;
import androidx.appcompat.widget.e0;

/* loaded from: classes.dex */
public class m0 extends HorizontalScrollView implements AdapterView.OnItemSelectedListener {

    /* renamed from: b, reason: collision with root package name */
    Runnable f1038b;

    /* renamed from: c, reason: collision with root package name */
    private c f1039c;

    /* renamed from: d, reason: collision with root package name */
    e0 f1040d;

    /* renamed from: e, reason: collision with root package name */
    private Spinner f1041e;

    /* renamed from: f, reason: collision with root package name */
    private boolean f1042f;
    int g;
    int h;
    private int i;
    private int j;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a implements Runnable {

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ View f1043b;

        a(View view) {
            this.f1043b = view;
        }

        @Override // java.lang.Runnable
        public void run() {
            m0.this.smoothScrollTo(this.f1043b.getLeft() - ((m0.this.getWidth() - this.f1043b.getWidth()) / 2), 0);
            m0.this.f1038b = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class b extends BaseAdapter {
        b() {
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return m0.this.f1040d.getChildCount();
        }

        @Override // android.widget.Adapter
        public Object getItem(int i) {
            return ((d) m0.this.f1040d.getChildAt(i)).a();
        }

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                return m0.this.a((a.c) getItem(i), true);
            }
            ((d) view).a((a.c) getItem(i));
            return view;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class c implements View.OnClickListener {
        c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ((d) view).a().e();
            int childCount = m0.this.f1040d.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = m0.this.f1040d.getChildAt(i);
                childAt.setSelected(childAt == view);
            }
        }
    }

    static {
        new DecelerateInterpolator();
    }

    private Spinner a() {
        AppCompatSpinner appCompatSpinner = new AppCompatSpinner(getContext(), null, a.a.a.actionDropDownStyle);
        appCompatSpinner.setLayoutParams(new e0.a(-2, -1));
        appCompatSpinner.setOnItemSelectedListener(this);
        return appCompatSpinner;
    }

    private boolean b() {
        Spinner spinner = this.f1041e;
        return spinner != null && spinner.getParent() == this;
    }

    private void c() {
        if (b()) {
            return;
        }
        if (this.f1041e == null) {
            this.f1041e = a();
        }
        removeView(this.f1040d);
        addView(this.f1041e, new ViewGroup.LayoutParams(-2, -1));
        if (this.f1041e.getAdapter() == null) {
            this.f1041e.setAdapter((SpinnerAdapter) new b());
        }
        Runnable runnable = this.f1038b;
        if (runnable != null) {
            removeCallbacks(runnable);
            this.f1038b = null;
        }
        this.f1041e.setSelection(this.j);
    }

    private boolean d() {
        if (!b()) {
            return false;
        }
        removeView(this.f1041e);
        addView(this.f1040d, new ViewGroup.LayoutParams(-2, -1));
        setTabSelected(this.f1041e.getSelectedItemPosition());
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Runnable runnable = this.f1038b;
        if (runnable != null) {
            post(runnable);
        }
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        a.a.o.a a2 = a.a.o.a.a(getContext());
        setContentHeight(a2.e());
        this.h = a2.d();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Runnable runnable = this.f1038b;
        if (runnable != null) {
            removeCallbacks(runnable);
        }
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        ((d) view).a().e();
    }

    @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.View
    public void onMeasure(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i);
        boolean z = mode == 1073741824;
        setFillViewport(z);
        int childCount = this.f1040d.getChildCount();
        if (childCount > 1 && (mode == 1073741824 || mode == Integer.MIN_VALUE)) {
            if (childCount > 2) {
                this.g = (int) (View.MeasureSpec.getSize(i) * 0.4f);
            } else {
                this.g = View.MeasureSpec.getSize(i) / 2;
            }
            this.g = Math.min(this.g, this.h);
        } else {
            this.g = -1;
        }
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(this.i, 1073741824);
        if (!z && this.f1042f) {
            this.f1040d.measure(0, makeMeasureSpec);
            if (this.f1040d.getMeasuredWidth() > View.MeasureSpec.getSize(i)) {
                c();
            } else {
                d();
            }
        } else {
            d();
        }
        int measuredWidth = getMeasuredWidth();
        super.onMeasure(i, makeMeasureSpec);
        int measuredWidth2 = getMeasuredWidth();
        if (!z || measuredWidth == measuredWidth2) {
            return;
        }
        setTabSelected(this.j);
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    public void setAllowCollapse(boolean z) {
        this.f1042f = z;
    }

    public void setContentHeight(int i) {
        this.i = i;
        requestLayout();
    }

    public void setTabSelected(int i) {
        this.j = i;
        int childCount = this.f1040d.getChildCount();
        int i2 = 0;
        while (i2 < childCount) {
            View childAt = this.f1040d.getChildAt(i2);
            boolean z = i2 == i;
            childAt.setSelected(z);
            if (z) {
                a(i);
            }
            i2++;
        }
        Spinner spinner = this.f1041e;
        if (spinner == null || i < 0) {
            return;
        }
        spinner.setSelection(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class d extends LinearLayout {

        /* renamed from: b, reason: collision with root package name */
        private final int[] f1047b;

        /* renamed from: c, reason: collision with root package name */
        private a.c f1048c;

        /* renamed from: d, reason: collision with root package name */
        private TextView f1049d;

        /* renamed from: e, reason: collision with root package name */
        private ImageView f1050e;

        /* renamed from: f, reason: collision with root package name */
        private View f1051f;

        public d(Context context, a.c cVar, boolean z) {
            super(context, null, a.a.a.actionBarTabStyle);
            this.f1047b = new int[]{R.attr.background};
            this.f1048c = cVar;
            t0 a2 = t0.a(context, null, this.f1047b, a.a.a.actionBarTabStyle, 0);
            if (a2.g(0)) {
                setBackgroundDrawable(a2.b(0));
            }
            a2.a();
            if (z) {
                setGravity(8388627);
            }
            b();
        }

        public void a(a.c cVar) {
            this.f1048c = cVar;
            b();
        }

        public void b() {
            a.c cVar = this.f1048c;
            View b2 = cVar.b();
            if (b2 != null) {
                ViewParent parent = b2.getParent();
                if (parent != this) {
                    if (parent != null) {
                        ((ViewGroup) parent).removeView(b2);
                    }
                    addView(b2);
                }
                this.f1051f = b2;
                TextView textView = this.f1049d;
                if (textView != null) {
                    textView.setVisibility(8);
                }
                ImageView imageView = this.f1050e;
                if (imageView != null) {
                    imageView.setVisibility(8);
                    this.f1050e.setImageDrawable(null);
                    return;
                }
                return;
            }
            View view = this.f1051f;
            if (view != null) {
                removeView(view);
                this.f1051f = null;
            }
            Drawable c2 = cVar.c();
            CharSequence d2 = cVar.d();
            if (c2 != null) {
                if (this.f1050e == null) {
                    AppCompatImageView appCompatImageView = new AppCompatImageView(getContext());
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
                    layoutParams.gravity = 16;
                    appCompatImageView.setLayoutParams(layoutParams);
                    addView(appCompatImageView, 0);
                    this.f1050e = appCompatImageView;
                }
                this.f1050e.setImageDrawable(c2);
                this.f1050e.setVisibility(0);
            } else {
                ImageView imageView2 = this.f1050e;
                if (imageView2 != null) {
                    imageView2.setVisibility(8);
                    this.f1050e.setImageDrawable(null);
                }
            }
            boolean z = !TextUtils.isEmpty(d2);
            if (z) {
                if (this.f1049d == null) {
                    AppCompatTextView appCompatTextView = new AppCompatTextView(getContext(), null, a.a.a.actionBarTabTextStyle);
                    appCompatTextView.setEllipsize(TextUtils.TruncateAt.END);
                    LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
                    layoutParams2.gravity = 16;
                    appCompatTextView.setLayoutParams(layoutParams2);
                    addView(appCompatTextView);
                    this.f1049d = appCompatTextView;
                }
                this.f1049d.setText(d2);
                this.f1049d.setVisibility(0);
            } else {
                TextView textView2 = this.f1049d;
                if (textView2 != null) {
                    textView2.setVisibility(8);
                    this.f1049d.setText((CharSequence) null);
                }
            }
            ImageView imageView3 = this.f1050e;
            if (imageView3 != null) {
                imageView3.setContentDescription(cVar.a());
            }
            v0.a(this, z ? null : cVar.a());
        }

        @Override // android.view.View
        public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
            super.onInitializeAccessibilityEvent(accessibilityEvent);
            accessibilityEvent.setClassName("androidx.appcompat.app.ActionBar$Tab");
        }

        @Override // android.view.View
        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
            super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
            accessibilityNodeInfo.setClassName("androidx.appcompat.app.ActionBar$Tab");
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onMeasure(int i, int i2) {
            super.onMeasure(i, i2);
            if (m0.this.g > 0) {
                int measuredWidth = getMeasuredWidth();
                int i3 = m0.this.g;
                if (measuredWidth > i3) {
                    super.onMeasure(View.MeasureSpec.makeMeasureSpec(i3, 1073741824), i2);
                }
            }
        }

        @Override // android.view.View
        public void setSelected(boolean z) {
            boolean z2 = isSelected() != z;
            super.setSelected(z);
            if (z2 && z) {
                sendAccessibilityEvent(4);
            }
        }

        public a.c a() {
            return this.f1048c;
        }
    }

    public void a(int i) {
        View childAt = this.f1040d.getChildAt(i);
        Runnable runnable = this.f1038b;
        if (runnable != null) {
            removeCallbacks(runnable);
        }
        this.f1038b = new a(childAt);
        post(this.f1038b);
    }

    d a(a.c cVar, boolean z) {
        d dVar = new d(getContext(), cVar, z);
        if (z) {
            dVar.setBackgroundDrawable(null);
            dVar.setLayoutParams(new AbsListView.LayoutParams(-1, this.i));
        } else {
            dVar.setFocusable(true);
            if (this.f1039c == null) {
                this.f1039c = new c();
            }
            dVar.setOnClickListener(this.f1039c);
        }
        return dVar;
    }
}
