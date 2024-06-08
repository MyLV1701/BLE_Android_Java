package com.google.android.material.snackbar;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import android.widget.Button;
import android.widget.FrameLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import c.a.a.a.h;
import com.google.android.material.snackbar.BaseTransientBottomBar;

/* loaded from: classes.dex */
public class Snackbar extends BaseTransientBottomBar<Snackbar> {
    private static final int[] y;
    private final AccessibilityManager w;
    private boolean x;

    /* loaded from: classes.dex */
    public static final class SnackbarLayout extends BaseTransientBottomBar.v {
        public SnackbarLayout(Context context) {
            super(context);
        }

        @Override // android.widget.FrameLayout, android.view.View
        protected void onMeasure(int i, int i2) {
            super.onMeasure(i, i2);
            int childCount = getChildCount();
            int measuredWidth = (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
            for (int i3 = 0; i3 < childCount; i3++) {
                View childAt = getChildAt(i3);
                if (childAt.getLayoutParams().width == -1) {
                    childAt.measure(View.MeasureSpec.makeMeasureSpec(measuredWidth, 1073741824), View.MeasureSpec.makeMeasureSpec(childAt.getMeasuredHeight(), 1073741824));
                }
            }
        }

        @Override // com.google.android.material.snackbar.BaseTransientBottomBar.v, android.view.View
        public /* bridge */ /* synthetic */ void setBackground(Drawable drawable) {
            super.setBackground(drawable);
        }

        @Override // com.google.android.material.snackbar.BaseTransientBottomBar.v, android.view.View
        public /* bridge */ /* synthetic */ void setBackgroundDrawable(Drawable drawable) {
            super.setBackgroundDrawable(drawable);
        }

        @Override // com.google.android.material.snackbar.BaseTransientBottomBar.v, android.view.View
        public /* bridge */ /* synthetic */ void setBackgroundTintList(ColorStateList colorStateList) {
            super.setBackgroundTintList(colorStateList);
        }

        @Override // com.google.android.material.snackbar.BaseTransientBottomBar.v, android.view.View
        public /* bridge */ /* synthetic */ void setBackgroundTintMode(PorterDuff.Mode mode) {
            super.setBackgroundTintMode(mode);
        }

        @Override // com.google.android.material.snackbar.BaseTransientBottomBar.v, android.view.View
        public /* bridge */ /* synthetic */ void setOnClickListener(View.OnClickListener onClickListener) {
            super.setOnClickListener(onClickListener);
        }

        public SnackbarLayout(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a implements View.OnClickListener {

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ View.OnClickListener f2658b;

        a(View.OnClickListener onClickListener) {
            this.f2658b = onClickListener;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.f2658b.onClick(view);
            Snackbar.this.a(1);
        }
    }

    /* loaded from: classes.dex */
    public static class b extends BaseTransientBottomBar.r<Snackbar> {
        public static final int DISMISS_EVENT_ACTION = 1;
        public static final int DISMISS_EVENT_CONSECUTIVE = 4;
        public static final int DISMISS_EVENT_MANUAL = 3;
        public static final int DISMISS_EVENT_SWIPE = 0;
        public static final int DISMISS_EVENT_TIMEOUT = 2;

        /* renamed from: onDismissed, reason: avoid collision after fix types in other method */
        public void onDismissed2(Snackbar snackbar, int i) {
            throw null;
        }

        @Override // com.google.android.material.snackbar.BaseTransientBottomBar.r
        public /* bridge */ /* synthetic */ void onDismissed(Snackbar snackbar, int i) {
            throw null;
        }

        @Override // com.google.android.material.snackbar.BaseTransientBottomBar.r
        public void onShown(Snackbar snackbar) {
        }
    }

    static {
        int i = c.a.a.a.b.snackbarButtonStyle;
        new int[1][0] = i;
        y = new int[]{i, c.a.a.a.b.snackbarTextViewStyle};
    }

    private Snackbar(ViewGroup viewGroup, View view, com.google.android.material.snackbar.a aVar) {
        super(viewGroup, view, aVar);
        this.w = (AccessibilityManager) viewGroup.getContext().getSystemService("accessibility");
    }

    public static Snackbar a(View view, CharSequence charSequence, int i) {
        ViewGroup a2 = a(view);
        if (a2 != null) {
            SnackbarContentLayout snackbarContentLayout = (SnackbarContentLayout) LayoutInflater.from(a2.getContext()).inflate(a(a2.getContext()) ? h.mtrl_layout_snackbar_include : h.design_layout_snackbar_include, a2, false);
            Snackbar snackbar = new Snackbar(a2, snackbarContentLayout, snackbarContentLayout);
            snackbar.a(charSequence);
            snackbar.d(i);
            return snackbar;
        }
        throw new IllegalArgumentException("No suitable parent found from the given view. Please provide a valid view.");
    }

    @Override // com.google.android.material.snackbar.BaseTransientBottomBar
    public void b() {
        super.b();
    }

    @Override // com.google.android.material.snackbar.BaseTransientBottomBar
    public int d() {
        int d2 = super.d();
        if (d2 == -2) {
            return -2;
        }
        if (Build.VERSION.SDK_INT >= 29) {
            return this.w.getRecommendedTimeoutMillis(d2, (this.x ? 4 : 0) | 1 | 2);
        }
        if (this.x && this.w.isTouchExplorationEnabled()) {
            return -2;
        }
        return d2;
    }

    public Snackbar e(int i) {
        ((SnackbarContentLayout) this.f2626c.getChildAt(0)).getActionView().setTextColor(i);
        return this;
    }

    @Override // com.google.android.material.snackbar.BaseTransientBottomBar
    public void k() {
        super.k();
    }

    private static boolean a(Context context) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(y);
        int resourceId = obtainStyledAttributes.getResourceId(0, -1);
        int resourceId2 = obtainStyledAttributes.getResourceId(1, -1);
        obtainStyledAttributes.recycle();
        return (resourceId == -1 || resourceId2 == -1) ? false : true;
    }

    public static Snackbar a(View view, int i, int i2) {
        return a(view, view.getResources().getText(i), i2);
    }

    private static ViewGroup a(View view) {
        ViewGroup viewGroup = null;
        while (!(view instanceof CoordinatorLayout)) {
            if (view instanceof FrameLayout) {
                if (view.getId() == 16908290) {
                    return (ViewGroup) view;
                }
                viewGroup = (ViewGroup) view;
            }
            if (view != null) {
                Object parent = view.getParent();
                view = parent instanceof View ? (View) parent : null;
            }
            if (view == null) {
                return viewGroup;
            }
        }
        return (ViewGroup) view;
    }

    public Snackbar a(CharSequence charSequence) {
        ((SnackbarContentLayout) this.f2626c.getChildAt(0)).getMessageView().setText(charSequence);
        return this;
    }

    public Snackbar a(int i, View.OnClickListener onClickListener) {
        a(c().getText(i), onClickListener);
        return this;
    }

    public Snackbar a(CharSequence charSequence, View.OnClickListener onClickListener) {
        Button actionView = ((SnackbarContentLayout) this.f2626c.getChildAt(0)).getActionView();
        if (!TextUtils.isEmpty(charSequence) && onClickListener != null) {
            this.x = true;
            actionView.setVisibility(0);
            actionView.setText(charSequence);
            actionView.setOnClickListener(new a(onClickListener));
        } else {
            actionView.setVisibility(8);
            actionView.setOnClickListener(null);
            this.x = false;
        }
        return this;
    }
}
