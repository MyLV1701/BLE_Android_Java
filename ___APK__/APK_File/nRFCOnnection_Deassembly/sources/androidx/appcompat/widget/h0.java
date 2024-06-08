package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.transition.Transition;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import androidx.appcompat.view.menu.ListMenuItemView;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
public class h0 extends f0 implements g0 {
    private static Method K;
    private g0 J;

    /* loaded from: classes.dex */
    public static class a extends b0 {
        final int p;
        final int q;
        private g0 r;
        private MenuItem s;

        public a(Context context, boolean z) {
            super(context, z);
            Configuration configuration = context.getResources().getConfiguration();
            if (Build.VERSION.SDK_INT >= 17 && 1 == configuration.getLayoutDirection()) {
                this.p = 21;
                this.q = 22;
            } else {
                this.p = 22;
                this.q = 21;
            }
        }

        @Override // androidx.appcompat.widget.b0, android.view.View
        public boolean onHoverEvent(MotionEvent motionEvent) {
            int i;
            androidx.appcompat.view.menu.f fVar;
            int pointToPosition;
            int i2;
            if (this.r != null) {
                ListAdapter adapter = getAdapter();
                if (adapter instanceof HeaderViewListAdapter) {
                    HeaderViewListAdapter headerViewListAdapter = (HeaderViewListAdapter) adapter;
                    i = headerViewListAdapter.getHeadersCount();
                    fVar = (androidx.appcompat.view.menu.f) headerViewListAdapter.getWrappedAdapter();
                } else {
                    i = 0;
                    fVar = (androidx.appcompat.view.menu.f) adapter;
                }
                androidx.appcompat.view.menu.j jVar = null;
                if (motionEvent.getAction() != 10 && (pointToPosition = pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY())) != -1 && (i2 = pointToPosition - i) >= 0 && i2 < fVar.getCount()) {
                    jVar = fVar.getItem(i2);
                }
                MenuItem menuItem = this.s;
                if (menuItem != jVar) {
                    androidx.appcompat.view.menu.g b2 = fVar.b();
                    if (menuItem != null) {
                        this.r.b(b2, menuItem);
                    }
                    this.s = jVar;
                    if (jVar != null) {
                        this.r.a(b2, jVar);
                    }
                }
            }
            return super.onHoverEvent(motionEvent);
        }

        @Override // android.widget.ListView, android.widget.AbsListView, android.view.View, android.view.KeyEvent.Callback
        public boolean onKeyDown(int i, KeyEvent keyEvent) {
            ListMenuItemView listMenuItemView = (ListMenuItemView) getSelectedView();
            if (listMenuItemView != null && i == this.p) {
                if (listMenuItemView.isEnabled() && listMenuItemView.getItemData().hasSubMenu()) {
                    performItemClick(listMenuItemView, getSelectedItemPosition(), getSelectedItemId());
                }
                return true;
            }
            if (listMenuItemView != null && i == this.q) {
                setSelection(-1);
                ((androidx.appcompat.view.menu.f) getAdapter()).b().a(false);
                return true;
            }
            return super.onKeyDown(i, keyEvent);
        }

        public void setHoverListener(g0 g0Var) {
            this.r = g0Var;
        }

        @Override // androidx.appcompat.widget.b0, android.widget.AbsListView
        public /* bridge */ /* synthetic */ void setSelector(Drawable drawable) {
            super.setSelector(drawable);
        }
    }

    static {
        try {
            if (Build.VERSION.SDK_INT <= 28) {
                K = PopupWindow.class.getDeclaredMethod("setTouchModal", Boolean.TYPE);
            }
        } catch (NoSuchMethodException unused) {
            Log.i("MenuPopupWindow", "Could not find method setTouchModal() on PopupWindow. Oh well.");
        }
    }

    public h0(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    @Override // androidx.appcompat.widget.f0
    b0 a(Context context, boolean z) {
        a aVar = new a(context, z);
        aVar.setHoverListener(this);
        return aVar;
    }

    public void b(Object obj) {
        if (Build.VERSION.SDK_INT >= 23) {
            this.F.setExitTransition((Transition) obj);
        }
    }

    public void c(boolean z) {
        if (Build.VERSION.SDK_INT <= 28) {
            Method method = K;
            if (method != null) {
                try {
                    method.invoke(this.F, Boolean.valueOf(z));
                    return;
                } catch (Exception unused) {
                    Log.i("MenuPopupWindow", "Could not invoke setTouchModal() on PopupWindow. Oh well.");
                    return;
                }
            }
            return;
        }
        this.F.setTouchModal(z);
    }

    public void a(Object obj) {
        if (Build.VERSION.SDK_INT >= 23) {
            this.F.setEnterTransition((Transition) obj);
        }
    }

    @Override // androidx.appcompat.widget.g0
    public void b(androidx.appcompat.view.menu.g gVar, MenuItem menuItem) {
        g0 g0Var = this.J;
        if (g0Var != null) {
            g0Var.b(gVar, menuItem);
        }
    }

    public void a(g0 g0Var) {
        this.J = g0Var;
    }

    @Override // androidx.appcompat.widget.g0
    public void a(androidx.appcompat.view.menu.g gVar, MenuItem menuItem) {
        g0 g0Var = this.J;
        if (g0Var != null) {
            g0Var.a(gVar, menuItem);
        }
    }
}
