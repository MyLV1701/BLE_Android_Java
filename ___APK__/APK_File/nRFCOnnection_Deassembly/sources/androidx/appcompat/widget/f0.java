package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import androidx.preference.Preference;
import androidx.recyclerview.widget.RecyclerView;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
public class f0 implements androidx.appcompat.view.menu.q {
    private static Method G;
    private static Method H;
    private static Method I;
    private final c A;
    final Handler B;
    private final Rect C;
    private Rect D;
    private boolean E;
    PopupWindow F;

    /* renamed from: b, reason: collision with root package name */
    private Context f981b;

    /* renamed from: c, reason: collision with root package name */
    private ListAdapter f982c;

    /* renamed from: d, reason: collision with root package name */
    b0 f983d;

    /* renamed from: e, reason: collision with root package name */
    private int f984e;

    /* renamed from: f, reason: collision with root package name */
    private int f985f;
    private int g;
    private int h;
    private int i;
    private boolean j;
    private boolean k;
    private boolean l;
    private int m;
    private boolean n;
    private boolean o;
    int p;
    private View q;
    private int r;
    private DataSetObserver s;
    private View t;
    private Drawable u;
    private AdapterView.OnItemClickListener v;
    private AdapterView.OnItemSelectedListener w;
    final g x;
    private final f y;
    private final e z;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            View i = f0.this.i();
            if (i == null || i.getWindowToken() == null) {
                return;
            }
            f0.this.d();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class b implements AdapterView.OnItemSelectedListener {
        b() {
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
            b0 b0Var;
            if (i == -1 || (b0Var = f0.this.f983d) == null) {
                return;
            }
            b0Var.setListSelectionHidden(false);
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class c implements Runnable {
        c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            f0.this.h();
        }
    }

    /* loaded from: classes.dex */
    private class d extends DataSetObserver {
        d() {
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            if (f0.this.e()) {
                f0.this.d();
            }
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            f0.this.dismiss();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class e implements AbsListView.OnScrollListener {
        e() {
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScrollStateChanged(AbsListView absListView, int i) {
            if (i != 1 || f0.this.o() || f0.this.F.getContentView() == null) {
                return;
            }
            f0 f0Var = f0.this;
            f0Var.B.removeCallbacks(f0Var.x);
            f0.this.x.run();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class f implements View.OnTouchListener {
        f() {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            PopupWindow popupWindow;
            int action = motionEvent.getAction();
            int x = (int) motionEvent.getX();
            int y = (int) motionEvent.getY();
            if (action == 0 && (popupWindow = f0.this.F) != null && popupWindow.isShowing() && x >= 0 && x < f0.this.F.getWidth() && y >= 0 && y < f0.this.F.getHeight()) {
                f0 f0Var = f0.this;
                f0Var.B.postDelayed(f0Var.x, 250L);
                return false;
            }
            if (action != 1) {
                return false;
            }
            f0 f0Var2 = f0.this;
            f0Var2.B.removeCallbacks(f0Var2.x);
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class g implements Runnable {
        g() {
        }

        @Override // java.lang.Runnable
        public void run() {
            b0 b0Var = f0.this.f983d;
            if (b0Var == null || !a.f.l.w.E(b0Var) || f0.this.f983d.getCount() <= f0.this.f983d.getChildCount()) {
                return;
            }
            int childCount = f0.this.f983d.getChildCount();
            f0 f0Var = f0.this;
            if (childCount <= f0Var.p) {
                f0Var.F.setInputMethodMode(2);
                f0.this.d();
            }
        }
    }

    static {
        if (Build.VERSION.SDK_INT <= 28) {
            try {
                G = PopupWindow.class.getDeclaredMethod("setClipToScreenEnabled", Boolean.TYPE);
            } catch (NoSuchMethodException unused) {
                Log.i("ListPopupWindow", "Could not find method setClipToScreenEnabled() on PopupWindow. Oh well.");
            }
            try {
                I = PopupWindow.class.getDeclaredMethod("setEpicenterBounds", Rect.class);
            } catch (NoSuchMethodException unused2) {
                Log.i("ListPopupWindow", "Could not find method setEpicenterBounds(Rect) on PopupWindow. Oh well.");
            }
        }
        if (Build.VERSION.SDK_INT <= 23) {
            try {
                H = PopupWindow.class.getDeclaredMethod("getMaxAvailableHeight", View.class, Integer.TYPE, Boolean.TYPE);
            } catch (NoSuchMethodException unused3) {
                Log.i("ListPopupWindow", "Could not find method getMaxAvailableHeight(View, int, boolean) on PopupWindow. Oh well.");
            }
        }
    }

    public f0(Context context) {
        this(context, null, a.a.a.listPopupWindowStyle);
    }

    private int q() {
        int i;
        int i2;
        int makeMeasureSpec;
        int i3;
        if (this.f983d == null) {
            Context context = this.f981b;
            new a();
            this.f983d = a(context, !this.E);
            Drawable drawable = this.u;
            if (drawable != null) {
                this.f983d.setSelector(drawable);
            }
            this.f983d.setAdapter(this.f982c);
            this.f983d.setOnItemClickListener(this.v);
            this.f983d.setFocusable(true);
            this.f983d.setFocusableInTouchMode(true);
            this.f983d.setOnItemSelectedListener(new b());
            this.f983d.setOnScrollListener(this.z);
            AdapterView.OnItemSelectedListener onItemSelectedListener = this.w;
            if (onItemSelectedListener != null) {
                this.f983d.setOnItemSelectedListener(onItemSelectedListener);
            }
            View view = this.f983d;
            View view2 = this.q;
            if (view2 != null) {
                LinearLayout linearLayout = new LinearLayout(context);
                linearLayout.setOrientation(1);
                ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, 0, 1.0f);
                int i4 = this.r;
                if (i4 == 0) {
                    linearLayout.addView(view2);
                    linearLayout.addView(view, layoutParams);
                } else if (i4 != 1) {
                    Log.e("ListPopupWindow", "Invalid hint position " + this.r);
                } else {
                    linearLayout.addView(view, layoutParams);
                    linearLayout.addView(view2);
                }
                int i5 = this.f985f;
                if (i5 >= 0) {
                    i3 = RecyclerView.UNDEFINED_DURATION;
                } else {
                    i5 = 0;
                    i3 = 0;
                }
                view2.measure(View.MeasureSpec.makeMeasureSpec(i5, i3), 0);
                LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) view2.getLayoutParams();
                i = view2.getMeasuredHeight() + layoutParams2.topMargin + layoutParams2.bottomMargin;
                view = linearLayout;
            } else {
                i = 0;
            }
            this.F.setContentView(view);
        } else {
            View view3 = this.q;
            if (view3 != null) {
                LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) view3.getLayoutParams();
                i = view3.getMeasuredHeight() + layoutParams3.topMargin + layoutParams3.bottomMargin;
            } else {
                i = 0;
            }
        }
        Drawable background = this.F.getBackground();
        if (background != null) {
            background.getPadding(this.C);
            Rect rect = this.C;
            int i6 = rect.top;
            i2 = rect.bottom + i6;
            if (!this.j) {
                this.h = -i6;
            }
        } else {
            this.C.setEmpty();
            i2 = 0;
        }
        int a2 = a(i(), this.h, this.F.getInputMethodMode() == 2);
        if (this.n || this.f984e == -1) {
            return a2 + i2;
        }
        int i7 = this.f985f;
        if (i7 == -2) {
            int i8 = this.f981b.getResources().getDisplayMetrics().widthPixels;
            Rect rect2 = this.C;
            makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i8 - (rect2.left + rect2.right), RecyclerView.UNDEFINED_DURATION);
        } else if (i7 != -1) {
            makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i7, 1073741824);
        } else {
            int i9 = this.f981b.getResources().getDisplayMetrics().widthPixels;
            Rect rect3 = this.C;
            makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i9 - (rect3.left + rect3.right), 1073741824);
        }
        int a3 = this.f983d.a(makeMeasureSpec, 0, -1, a2 - i, -1);
        if (a3 > 0) {
            i += i2 + this.f983d.getPaddingTop() + this.f983d.getPaddingBottom();
        }
        return a3 + i;
    }

    private void r() {
        View view = this.q;
        if (view != null) {
            ViewParent parent = view.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(this.q);
            }
        }
    }

    public void a(ListAdapter listAdapter) {
        DataSetObserver dataSetObserver = this.s;
        if (dataSetObserver == null) {
            this.s = new d();
        } else {
            ListAdapter listAdapter2 = this.f982c;
            if (listAdapter2 != null) {
                listAdapter2.unregisterDataSetObserver(dataSetObserver);
            }
        }
        this.f982c = listAdapter;
        if (listAdapter != null) {
            listAdapter.registerDataSetObserver(this.s);
        }
        b0 b0Var = this.f983d;
        if (b0Var != null) {
            b0Var.setAdapter(this.f982c);
        }
    }

    public Drawable b() {
        return this.F.getBackground();
    }

    public void c(int i) {
        this.g = i;
    }

    public void d(int i) {
        this.F.setAnimationStyle(i);
    }

    @Override // androidx.appcompat.view.menu.q
    public void dismiss() {
        this.F.dismiss();
        r();
        this.F.setContentView(null);
        this.f983d = null;
        this.B.removeCallbacks(this.x);
    }

    public void e(int i) {
        Drawable background = this.F.getBackground();
        if (background != null) {
            background.getPadding(this.C);
            Rect rect = this.C;
            this.f985f = rect.left + rect.right + i;
            return;
        }
        j(i);
    }

    public void f(int i) {
        this.m = i;
    }

    public void g(int i) {
        this.F.setInputMethodMode(i);
    }

    public void h(int i) {
        this.r = i;
    }

    public View i() {
        return this.t;
    }

    public void j(int i) {
        this.f985f = i;
    }

    public long k() {
        if (e()) {
            return this.f983d.getSelectedItemId();
        }
        return Long.MIN_VALUE;
    }

    public int l() {
        if (e()) {
            return this.f983d.getSelectedItemPosition();
        }
        return -1;
    }

    public View m() {
        if (e()) {
            return this.f983d.getSelectedView();
        }
        return null;
    }

    public int n() {
        return this.f985f;
    }

    public boolean o() {
        return this.F.getInputMethodMode() == 2;
    }

    public boolean p() {
        return this.E;
    }

    public f0(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public void b(boolean z) {
        this.l = true;
        this.k = z;
    }

    public int c() {
        if (this.j) {
            return this.h;
        }
        return 0;
    }

    @Override // androidx.appcompat.view.menu.q
    public void d() {
        int q = q();
        boolean o = o();
        androidx.core.widget.h.a(this.F, this.i);
        if (this.F.isShowing()) {
            if (a.f.l.w.E(i())) {
                int i = this.f985f;
                if (i == -1) {
                    i = -1;
                } else if (i == -2) {
                    i = i().getWidth();
                }
                int i2 = this.f984e;
                if (i2 == -1) {
                    if (!o) {
                        q = -1;
                    }
                    if (o) {
                        this.F.setWidth(this.f985f == -1 ? -1 : 0);
                        this.F.setHeight(0);
                    } else {
                        this.F.setWidth(this.f985f == -1 ? -1 : 0);
                        this.F.setHeight(-1);
                    }
                } else if (i2 != -2) {
                    q = i2;
                }
                this.F.setOutsideTouchable((this.o || this.n) ? false : true);
                this.F.update(i(), this.g, this.h, i < 0 ? -1 : i, q < 0 ? -1 : q);
                return;
            }
            return;
        }
        int i3 = this.f985f;
        if (i3 == -1) {
            i3 = -1;
        } else if (i3 == -2) {
            i3 = i().getWidth();
        }
        int i4 = this.f984e;
        if (i4 == -1) {
            q = -1;
        } else if (i4 != -2) {
            q = i4;
        }
        this.F.setWidth(i3);
        this.F.setHeight(q);
        c(true);
        this.F.setOutsideTouchable((this.o || this.n) ? false : true);
        this.F.setTouchInterceptor(this.y);
        if (this.l) {
            androidx.core.widget.h.a(this.F, this.k);
        }
        if (Build.VERSION.SDK_INT <= 28) {
            Method method = I;
            if (method != null) {
                try {
                    method.invoke(this.F, this.D);
                } catch (Exception e2) {
                    Log.e("ListPopupWindow", "Could not invoke setEpicenterBounds on PopupWindow", e2);
                }
            }
        } else {
            this.F.setEpicenterBounds(this.D);
        }
        androidx.core.widget.h.a(this.F, i(), this.g, this.h, this.m);
        this.f983d.setSelection(-1);
        if (!this.E || this.f983d.isInTouchMode()) {
            h();
        }
        if (this.E) {
            return;
        }
        this.B.post(this.A);
    }

    @Override // androidx.appcompat.view.menu.q
    public ListView f() {
        return this.f983d;
    }

    public void h() {
        b0 b0Var = this.f983d;
        if (b0Var != null) {
            b0Var.setListSelectionHidden(true);
            b0Var.requestLayout();
        }
    }

    public void i(int i) {
        b0 b0Var = this.f983d;
        if (!e() || b0Var == null) {
            return;
        }
        b0Var.setListSelectionHidden(false);
        b0Var.setSelection(i);
        if (b0Var.getChoiceMode() != 0) {
            b0Var.setItemChecked(i, true);
        }
    }

    public Object j() {
        if (e()) {
            return this.f983d.getSelectedItem();
        }
        return null;
    }

    public f0(Context context, AttributeSet attributeSet, int i, int i2) {
        this.f984e = -2;
        this.f985f = -2;
        this.i = 1002;
        this.m = 0;
        this.n = false;
        this.o = false;
        this.p = Preference.DEFAULT_ORDER;
        this.r = 0;
        this.x = new g();
        this.y = new f();
        this.z = new e();
        this.A = new c();
        this.C = new Rect();
        this.f981b = context;
        this.B = new Handler(context.getMainLooper());
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, a.a.j.ListPopupWindow, i, i2);
        this.g = obtainStyledAttributes.getDimensionPixelOffset(a.a.j.ListPopupWindow_android_dropDownHorizontalOffset, 0);
        this.h = obtainStyledAttributes.getDimensionPixelOffset(a.a.j.ListPopupWindow_android_dropDownVerticalOffset, 0);
        if (this.h != 0) {
            this.j = true;
        }
        obtainStyledAttributes.recycle();
        this.F = new p(context, attributeSet, i, i2);
        this.F.setInputMethodMode(1);
    }

    private void c(boolean z) {
        if (Build.VERSION.SDK_INT <= 28) {
            Method method = G;
            if (method != null) {
                try {
                    method.invoke(this.F, Boolean.valueOf(z));
                    return;
                } catch (Exception unused) {
                    Log.i("ListPopupWindow", "Could not call setClipToScreenEnabled() on PopupWindow. Oh well.");
                    return;
                }
            }
            return;
        }
        this.F.setIsClippedToScreen(z);
    }

    @Override // androidx.appcompat.view.menu.q
    public boolean e() {
        return this.F.isShowing();
    }

    public void a(boolean z) {
        this.E = z;
        this.F.setFocusable(z);
    }

    public void a(Drawable drawable) {
        this.F.setBackgroundDrawable(drawable);
    }

    public void a(View view) {
        this.t = view;
    }

    public int a() {
        return this.g;
    }

    public void a(int i) {
        this.h = i;
        this.j = true;
    }

    public void a(Rect rect) {
        this.D = rect != null ? new Rect(rect) : null;
    }

    public void a(AdapterView.OnItemClickListener onItemClickListener) {
        this.v = onItemClickListener;
    }

    public void a(PopupWindow.OnDismissListener onDismissListener) {
        this.F.setOnDismissListener(onDismissListener);
    }

    b0 a(Context context, boolean z) {
        return new b0(context, z);
    }

    private int a(View view, int i, boolean z) {
        if (Build.VERSION.SDK_INT <= 23) {
            Method method = H;
            if (method != null) {
                try {
                    return ((Integer) method.invoke(this.F, view, Integer.valueOf(i), Boolean.valueOf(z))).intValue();
                } catch (Exception unused) {
                    Log.i("ListPopupWindow", "Could not call getMaxAvailableHeightMethod(View, int, boolean) on PopupWindow. Using the public version.");
                }
            }
            return this.F.getMaxAvailableHeight(view, i);
        }
        return this.F.getMaxAvailableHeight(view, i, z);
    }
}
