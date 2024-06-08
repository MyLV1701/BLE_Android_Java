package a.n;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import java.util.ArrayList;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class z implements b0 {

    /* renamed from: a, reason: collision with root package name */
    protected a f531a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public z(Context context, ViewGroup viewGroup, View view) {
        this.f531a = new a(context, viewGroup, view, this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static z c(View view) {
        ViewGroup d2 = d(view);
        if (d2 == null) {
            return null;
        }
        int childCount = d2.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = d2.getChildAt(i);
            if (childAt instanceof a) {
                return ((a) childAt).f535e;
            }
        }
        return new u(d2.getContext(), d2, view);
    }

    static ViewGroup d(View view) {
        while (view != null) {
            if (view.getId() == 16908290 && (view instanceof ViewGroup)) {
                return (ViewGroup) view;
            }
            if (view.getParent() instanceof ViewGroup) {
                view = (ViewGroup) view.getParent();
            }
        }
        return null;
    }

    @Override // a.n.b0
    public void a(Drawable drawable) {
        this.f531a.a(drawable);
    }

    @Override // a.n.b0
    public void b(Drawable drawable) {
        this.f531a.b(drawable);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class a extends ViewGroup {

        /* renamed from: b, reason: collision with root package name */
        ViewGroup f532b;

        /* renamed from: c, reason: collision with root package name */
        View f533c;

        /* renamed from: d, reason: collision with root package name */
        ArrayList<Drawable> f534d;

        /* renamed from: e, reason: collision with root package name */
        z f535e;

        /* renamed from: f, reason: collision with root package name */
        private boolean f536f;

        static {
            try {
                ViewGroup.class.getDeclaredMethod("invalidateChildInParentFast", Integer.TYPE, Integer.TYPE, Rect.class);
            } catch (NoSuchMethodException unused) {
            }
        }

        a(Context context, ViewGroup viewGroup, View view, z zVar) {
            super(context);
            this.f534d = null;
            this.f532b = viewGroup;
            this.f533c = view;
            setRight(viewGroup.getWidth());
            setBottom(viewGroup.getHeight());
            viewGroup.addView(this);
            this.f535e = zVar;
        }

        public void a(Drawable drawable) {
            a();
            if (this.f534d == null) {
                this.f534d = new ArrayList<>();
            }
            if (this.f534d.contains(drawable)) {
                return;
            }
            this.f534d.add(drawable);
            invalidate(drawable.getBounds());
            drawable.setCallback(this);
        }

        public void b(Drawable drawable) {
            ArrayList<Drawable> arrayList = this.f534d;
            if (arrayList != null) {
                arrayList.remove(drawable);
                invalidate(drawable.getBounds());
                drawable.setCallback(null);
                b();
            }
        }

        @Override // android.view.ViewGroup, android.view.View
        protected void dispatchDraw(Canvas canvas) {
            this.f532b.getLocationOnScreen(new int[2]);
            this.f533c.getLocationOnScreen(new int[2]);
            canvas.translate(r0[0] - r1[0], r0[1] - r1[1]);
            canvas.clipRect(new Rect(0, 0, this.f533c.getWidth(), this.f533c.getHeight()));
            super.dispatchDraw(canvas);
            ArrayList<Drawable> arrayList = this.f534d;
            int size = arrayList == null ? 0 : arrayList.size();
            for (int i = 0; i < size; i++) {
                this.f534d.get(i).draw(canvas);
            }
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent motionEvent) {
            return false;
        }

        @Override // android.view.ViewGroup, android.view.ViewParent
        public ViewParent invalidateChildInParent(int[] iArr, Rect rect) {
            if (this.f532b == null) {
                return null;
            }
            rect.offset(iArr[0], iArr[1]);
            if (this.f532b instanceof ViewGroup) {
                iArr[0] = 0;
                iArr[1] = 0;
                int[] iArr2 = new int[2];
                a(iArr2);
                rect.offset(iArr2[0], iArr2[1]);
                return super.invalidateChildInParent(iArr, rect);
            }
            invalidate(rect);
            return null;
        }

        @Override // android.view.View, android.graphics.drawable.Drawable.Callback
        public void invalidateDrawable(Drawable drawable) {
            invalidate(drawable.getBounds());
        }

        @Override // android.view.ViewGroup, android.view.View
        protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        }

        @Override // android.view.View
        protected boolean verifyDrawable(Drawable drawable) {
            ArrayList<Drawable> arrayList;
            return super.verifyDrawable(drawable) || ((arrayList = this.f534d) != null && arrayList.contains(drawable));
        }

        public void b(View view) {
            super.removeView(view);
            b();
        }

        private void b() {
            if (getChildCount() == 0) {
                ArrayList<Drawable> arrayList = this.f534d;
                if (arrayList == null || arrayList.size() == 0) {
                    this.f536f = true;
                    this.f532b.removeView(this);
                }
            }
        }

        public void a(View view) {
            a();
            if (view.getParent() instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view.getParent();
                if (viewGroup != this.f532b && viewGroup.getParent() != null && a.f.l.w.E(viewGroup)) {
                    int[] iArr = new int[2];
                    int[] iArr2 = new int[2];
                    viewGroup.getLocationOnScreen(iArr);
                    this.f532b.getLocationOnScreen(iArr2);
                    a.f.l.w.e(view, iArr[0] - iArr2[0]);
                    a.f.l.w.f(view, iArr[1] - iArr2[1]);
                }
                viewGroup.removeView(view);
                if (view.getParent() != null) {
                    viewGroup.removeView(view);
                }
            }
            super.addView(view);
        }

        private void a() {
            if (this.f536f) {
                throw new IllegalStateException("This overlay was disposed already. Please use a new one via ViewGroupUtils.getOverlay()");
            }
        }

        private void a(int[] iArr) {
            int[] iArr2 = new int[2];
            int[] iArr3 = new int[2];
            this.f532b.getLocationOnScreen(iArr2);
            this.f533c.getLocationOnScreen(iArr3);
            iArr[0] = iArr3[0] - iArr2[0];
            iArr[1] = iArr3[1] - iArr2[1];
        }
    }
}
