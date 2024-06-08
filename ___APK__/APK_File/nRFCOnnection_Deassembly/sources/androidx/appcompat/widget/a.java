package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class a extends ViewGroup {

    /* renamed from: b, reason: collision with root package name */
    protected final C0042a f930b;

    /* renamed from: c, reason: collision with root package name */
    protected final Context f931c;

    /* renamed from: d, reason: collision with root package name */
    protected ActionMenuView f932d;

    /* renamed from: e, reason: collision with root package name */
    protected c f933e;

    /* renamed from: f, reason: collision with root package name */
    protected int f934f;
    protected a.f.l.a0 g;
    private boolean h;
    private boolean i;

    a(Context context) {
        this(context, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static int a(int i, int i2, boolean z) {
        return z ? i - i2 : i + i2;
    }

    public int getAnimatedVisibility() {
        if (this.g != null) {
            return this.f930b.f936b;
        }
        return getVisibility();
    }

    public int getContentHeight() {
        return this.f934f;
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(null, a.a.j.ActionBar, a.a.a.actionBarStyle, 0);
        setContentHeight(obtainStyledAttributes.getLayoutDimension(a.a.j.ActionBar_height, 0));
        obtainStyledAttributes.recycle();
        c cVar = this.f933e;
        if (cVar != null) {
            cVar.a(configuration);
        }
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9) {
            this.i = false;
        }
        if (!this.i) {
            boolean onHoverEvent = super.onHoverEvent(motionEvent);
            if (actionMasked == 9 && !onHoverEvent) {
                this.i = true;
            }
        }
        if (actionMasked == 10 || actionMasked == 3) {
            this.i = false;
        }
        return true;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.h = false;
        }
        if (!this.h) {
            boolean onTouchEvent = super.onTouchEvent(motionEvent);
            if (actionMasked == 0 && !onTouchEvent) {
                this.h = true;
            }
        }
        if (actionMasked == 1 || actionMasked == 3) {
            this.h = false;
        }
        return true;
    }

    public abstract void setContentHeight(int i);

    @Override // android.view.View
    public void setVisibility(int i) {
        if (i != getVisibility()) {
            a.f.l.a0 a0Var = this.g;
            if (a0Var != null) {
                a0Var.a();
            }
            super.setVisibility(i);
        }
    }

    /* renamed from: androidx.appcompat.widget.a$a, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    protected class C0042a implements a.f.l.b0 {

        /* renamed from: a, reason: collision with root package name */
        private boolean f935a = false;

        /* renamed from: b, reason: collision with root package name */
        int f936b;

        protected C0042a() {
        }

        public C0042a a(a.f.l.a0 a0Var, int i) {
            a.this.g = a0Var;
            this.f936b = i;
            return this;
        }

        @Override // a.f.l.b0
        public void b(View view) {
            if (this.f935a) {
                return;
            }
            a aVar = a.this;
            aVar.g = null;
            a.super.setVisibility(this.f936b);
        }

        @Override // a.f.l.b0
        public void c(View view) {
            a.super.setVisibility(0);
            this.f935a = false;
        }

        @Override // a.f.l.b0
        public void a(View view) {
            this.f935a = true;
        }
    }

    a(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public a.f.l.a0 a(int i, long j) {
        a.f.l.a0 a0Var = this.g;
        if (a0Var != null) {
            a0Var.a();
        }
        if (i == 0) {
            if (getVisibility() != 0) {
                setAlpha(0.0f);
            }
            a.f.l.a0 a2 = a.f.l.w.a(this);
            a2.a(1.0f);
            a2.a(j);
            C0042a c0042a = this.f930b;
            c0042a.a(a2, i);
            a2.a(c0042a);
            return a2;
        }
        a.f.l.a0 a3 = a.f.l.w.a(this);
        a3.a(0.0f);
        a3.a(j);
        C0042a c0042a2 = this.f930b;
        c0042a2.a(a3, i);
        a3.a(c0042a2);
        return a3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public a(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        int i2;
        this.f930b = new C0042a();
        TypedValue typedValue = new TypedValue();
        if (context.getTheme().resolveAttribute(a.a.a.actionBarPopupTheme, typedValue, true) && (i2 = typedValue.resourceId) != 0) {
            this.f931c = new ContextThemeWrapper(context, i2);
        } else {
            this.f931c = context;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int a(View view, int i, int i2, int i3) {
        view.measure(View.MeasureSpec.makeMeasureSpec(i, RecyclerView.UNDEFINED_DURATION), i2);
        return Math.max(0, (i - view.getMeasuredWidth()) - i3);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int a(View view, int i, int i2, int i3, boolean z) {
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        int i4 = i2 + ((i3 - measuredHeight) / 2);
        if (z) {
            view.layout(i - measuredWidth, i4, i, measuredHeight + i4);
        } else {
            view.layout(i, i4, i + measuredWidth, measuredHeight + i4);
        }
        return z ? -measuredWidth : measuredWidth;
    }
}
