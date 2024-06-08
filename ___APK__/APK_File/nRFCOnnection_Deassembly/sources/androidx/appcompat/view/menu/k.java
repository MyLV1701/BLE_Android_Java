package androidx.appcompat.view.menu;

import a.f.l.b;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.view.ActionProvider;
import android.view.CollapsibleActionView;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.FrameLayout;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
public class k extends androidx.appcompat.view.menu.c implements MenuItem {

    /* renamed from: d, reason: collision with root package name */
    private final a.f.f.a.b f811d;

    /* renamed from: e, reason: collision with root package name */
    private Method f812e;

    /* loaded from: classes.dex */
    private class a extends a.f.l.b {

        /* renamed from: b, reason: collision with root package name */
        final ActionProvider f813b;

        a(Context context, ActionProvider actionProvider) {
            super(context);
            this.f813b = actionProvider;
        }

        @Override // a.f.l.b
        public boolean a() {
            return this.f813b.hasSubMenu();
        }

        @Override // a.f.l.b
        public View c() {
            return this.f813b.onCreateActionView();
        }

        @Override // a.f.l.b
        public boolean d() {
            return this.f813b.onPerformDefaultAction();
        }

        @Override // a.f.l.b
        public void a(SubMenu subMenu) {
            this.f813b.onPrepareSubMenu(k.this.a(subMenu));
        }
    }

    /* loaded from: classes.dex */
    private class b extends a implements ActionProvider.VisibilityListener {

        /* renamed from: d, reason: collision with root package name */
        private b.InterfaceC0016b f815d;

        b(k kVar, Context context, ActionProvider actionProvider) {
            super(context, actionProvider);
        }

        @Override // a.f.l.b
        public View a(MenuItem menuItem) {
            return this.f813b.onCreateActionView(menuItem);
        }

        @Override // a.f.l.b
        public boolean b() {
            return this.f813b.isVisible();
        }

        @Override // a.f.l.b
        public boolean e() {
            return this.f813b.overridesItemVisibility();
        }

        @Override // android.view.ActionProvider.VisibilityListener
        public void onActionProviderVisibilityChanged(boolean z) {
            b.InterfaceC0016b interfaceC0016b = this.f815d;
            if (interfaceC0016b != null) {
                interfaceC0016b.onActionProviderVisibilityChanged(z);
            }
        }

        @Override // a.f.l.b
        public void a(b.InterfaceC0016b interfaceC0016b) {
            this.f815d = interfaceC0016b;
            this.f813b.setVisibilityListener(interfaceC0016b != null ? this : null);
        }
    }

    /* loaded from: classes.dex */
    static class c extends FrameLayout implements a.a.o.c {

        /* renamed from: b, reason: collision with root package name */
        final CollapsibleActionView f816b;

        /* JADX WARN: Multi-variable type inference failed */
        c(View view) {
            super(view.getContext());
            this.f816b = (CollapsibleActionView) view;
            addView(view);
        }

        View a() {
            return (View) this.f816b;
        }

        @Override // a.a.o.c
        public void b() {
            this.f816b.onActionViewExpanded();
        }

        @Override // a.a.o.c
        public void c() {
            this.f816b.onActionViewCollapsed();
        }
    }

    /* loaded from: classes.dex */
    private class d implements MenuItem.OnActionExpandListener {

        /* renamed from: a, reason: collision with root package name */
        private final MenuItem.OnActionExpandListener f817a;

        d(MenuItem.OnActionExpandListener onActionExpandListener) {
            this.f817a = onActionExpandListener;
        }

        @Override // android.view.MenuItem.OnActionExpandListener
        public boolean onMenuItemActionCollapse(MenuItem menuItem) {
            return this.f817a.onMenuItemActionCollapse(k.this.a(menuItem));
        }

        @Override // android.view.MenuItem.OnActionExpandListener
        public boolean onMenuItemActionExpand(MenuItem menuItem) {
            return this.f817a.onMenuItemActionExpand(k.this.a(menuItem));
        }
    }

    /* loaded from: classes.dex */
    private class e implements MenuItem.OnMenuItemClickListener {

        /* renamed from: b, reason: collision with root package name */
        private final MenuItem.OnMenuItemClickListener f819b;

        e(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
            this.f819b = onMenuItemClickListener;
        }

        @Override // android.view.MenuItem.OnMenuItemClickListener
        public boolean onMenuItemClick(MenuItem menuItem) {
            return this.f819b.onMenuItemClick(k.this.a(menuItem));
        }
    }

    public k(Context context, a.f.f.a.b bVar) {
        super(context);
        if (bVar != null) {
            this.f811d = bVar;
            return;
        }
        throw new IllegalArgumentException("Wrapped Object can not be null.");
    }

    public void a(boolean z) {
        try {
            if (this.f812e == null) {
                this.f812e = this.f811d.getClass().getDeclaredMethod("setExclusiveCheckable", Boolean.TYPE);
            }
            this.f812e.invoke(this.f811d, Boolean.valueOf(z));
        } catch (Exception e2) {
            Log.w("MenuItemWrapper", "Error while calling setExclusiveCheckable", e2);
        }
    }

    @Override // android.view.MenuItem
    public boolean collapseActionView() {
        return this.f811d.collapseActionView();
    }

    @Override // android.view.MenuItem
    public boolean expandActionView() {
        return this.f811d.expandActionView();
    }

    @Override // android.view.MenuItem
    public ActionProvider getActionProvider() {
        a.f.l.b a2 = this.f811d.a();
        if (a2 instanceof a) {
            return ((a) a2).f813b;
        }
        return null;
    }

    @Override // android.view.MenuItem
    public View getActionView() {
        View actionView = this.f811d.getActionView();
        return actionView instanceof c ? ((c) actionView).a() : actionView;
    }

    @Override // android.view.MenuItem
    public int getAlphabeticModifiers() {
        return this.f811d.getAlphabeticModifiers();
    }

    @Override // android.view.MenuItem
    public char getAlphabeticShortcut() {
        return this.f811d.getAlphabeticShortcut();
    }

    @Override // android.view.MenuItem
    public CharSequence getContentDescription() {
        return this.f811d.getContentDescription();
    }

    @Override // android.view.MenuItem
    public int getGroupId() {
        return this.f811d.getGroupId();
    }

    @Override // android.view.MenuItem
    public Drawable getIcon() {
        return this.f811d.getIcon();
    }

    @Override // android.view.MenuItem
    public ColorStateList getIconTintList() {
        return this.f811d.getIconTintList();
    }

    @Override // android.view.MenuItem
    public PorterDuff.Mode getIconTintMode() {
        return this.f811d.getIconTintMode();
    }

    @Override // android.view.MenuItem
    public Intent getIntent() {
        return this.f811d.getIntent();
    }

    @Override // android.view.MenuItem
    public int getItemId() {
        return this.f811d.getItemId();
    }

    @Override // android.view.MenuItem
    public ContextMenu.ContextMenuInfo getMenuInfo() {
        return this.f811d.getMenuInfo();
    }

    @Override // android.view.MenuItem
    public int getNumericModifiers() {
        return this.f811d.getNumericModifiers();
    }

    @Override // android.view.MenuItem
    public char getNumericShortcut() {
        return this.f811d.getNumericShortcut();
    }

    @Override // android.view.MenuItem
    public int getOrder() {
        return this.f811d.getOrder();
    }

    @Override // android.view.MenuItem
    public SubMenu getSubMenu() {
        return a(this.f811d.getSubMenu());
    }

    @Override // android.view.MenuItem
    public CharSequence getTitle() {
        return this.f811d.getTitle();
    }

    @Override // android.view.MenuItem
    public CharSequence getTitleCondensed() {
        return this.f811d.getTitleCondensed();
    }

    @Override // android.view.MenuItem
    public CharSequence getTooltipText() {
        return this.f811d.getTooltipText();
    }

    @Override // android.view.MenuItem
    public boolean hasSubMenu() {
        return this.f811d.hasSubMenu();
    }

    @Override // android.view.MenuItem
    public boolean isActionViewExpanded() {
        return this.f811d.isActionViewExpanded();
    }

    @Override // android.view.MenuItem
    public boolean isCheckable() {
        return this.f811d.isCheckable();
    }

    @Override // android.view.MenuItem
    public boolean isChecked() {
        return this.f811d.isChecked();
    }

    @Override // android.view.MenuItem
    public boolean isEnabled() {
        return this.f811d.isEnabled();
    }

    @Override // android.view.MenuItem
    public boolean isVisible() {
        return this.f811d.isVisible();
    }

    @Override // android.view.MenuItem
    public MenuItem setActionProvider(ActionProvider actionProvider) {
        a.f.l.b aVar;
        if (Build.VERSION.SDK_INT >= 16) {
            aVar = new b(this, this.f765a, actionProvider);
        } else {
            aVar = new a(this.f765a, actionProvider);
        }
        a.f.f.a.b bVar = this.f811d;
        if (actionProvider == null) {
            aVar = null;
        }
        bVar.a(aVar);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setActionView(View view) {
        if (view instanceof CollapsibleActionView) {
            view = new c(view);
        }
        this.f811d.setActionView(view);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setAlphabeticShortcut(char c2) {
        this.f811d.setAlphabeticShortcut(c2);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setCheckable(boolean z) {
        this.f811d.setCheckable(z);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setChecked(boolean z) {
        this.f811d.setChecked(z);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setContentDescription(CharSequence charSequence) {
        this.f811d.setContentDescription(charSequence);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setEnabled(boolean z) {
        this.f811d.setEnabled(z);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIcon(Drawable drawable) {
        this.f811d.setIcon(drawable);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIconTintList(ColorStateList colorStateList) {
        this.f811d.setIconTintList(colorStateList);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIconTintMode(PorterDuff.Mode mode) {
        this.f811d.setIconTintMode(mode);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIntent(Intent intent) {
        this.f811d.setIntent(intent);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setNumericShortcut(char c2) {
        this.f811d.setNumericShortcut(c2);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener onActionExpandListener) {
        this.f811d.setOnActionExpandListener(onActionExpandListener != null ? new d(onActionExpandListener) : null);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        this.f811d.setOnMenuItemClickListener(onMenuItemClickListener != null ? new e(onMenuItemClickListener) : null);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setShortcut(char c2, char c3) {
        this.f811d.setShortcut(c2, c3);
        return this;
    }

    @Override // android.view.MenuItem
    public void setShowAsAction(int i) {
        this.f811d.setShowAsAction(i);
    }

    @Override // android.view.MenuItem
    public MenuItem setShowAsActionFlags(int i) {
        this.f811d.setShowAsActionFlags(i);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setTitle(CharSequence charSequence) {
        this.f811d.setTitle(charSequence);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setTitleCondensed(CharSequence charSequence) {
        this.f811d.setTitleCondensed(charSequence);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setTooltipText(CharSequence charSequence) {
        this.f811d.setTooltipText(charSequence);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setVisible(boolean z) {
        return this.f811d.setVisible(z);
    }

    @Override // android.view.MenuItem
    public MenuItem setAlphabeticShortcut(char c2, int i) {
        this.f811d.setAlphabeticShortcut(c2, i);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIcon(int i) {
        this.f811d.setIcon(i);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setNumericShortcut(char c2, int i) {
        this.f811d.setNumericShortcut(c2, i);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setShortcut(char c2, char c3, int i, int i2) {
        this.f811d.setShortcut(c2, c3, i, i2);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setTitle(int i) {
        this.f811d.setTitle(i);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setActionView(int i) {
        this.f811d.setActionView(i);
        View actionView = this.f811d.getActionView();
        if (actionView instanceof CollapsibleActionView) {
            this.f811d.setActionView(new c(actionView));
        }
        return this;
    }
}
