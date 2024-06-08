package a.a.o;

import a.a.o.b;
import android.content.Context;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.view.menu.g;
import androidx.appcompat.widget.ActionBarContextView;
import java.lang.ref.WeakReference;

/* loaded from: classes.dex */
public class e extends b implements g.a {

    /* renamed from: d, reason: collision with root package name */
    private Context f41d;

    /* renamed from: e, reason: collision with root package name */
    private ActionBarContextView f42e;

    /* renamed from: f, reason: collision with root package name */
    private b.a f43f;
    private WeakReference<View> g;
    private boolean h;
    private androidx.appcompat.view.menu.g i;

    public e(Context context, ActionBarContextView actionBarContextView, b.a aVar, boolean z) {
        this.f41d = context;
        this.f42e = actionBarContextView;
        this.f43f = aVar;
        androidx.appcompat.view.menu.g gVar = new androidx.appcompat.view.menu.g(actionBarContextView.getContext());
        gVar.c(1);
        this.i = gVar;
        this.i.a(this);
    }

    @Override // a.a.o.b
    public void a(CharSequence charSequence) {
        this.f42e.setSubtitle(charSequence);
    }

    @Override // a.a.o.b
    public void b(CharSequence charSequence) {
        this.f42e.setTitle(charSequence);
    }

    @Override // a.a.o.b
    public Menu c() {
        return this.i;
    }

    @Override // a.a.o.b
    public MenuInflater d() {
        return new g(this.f42e.getContext());
    }

    @Override // a.a.o.b
    public CharSequence e() {
        return this.f42e.getSubtitle();
    }

    @Override // a.a.o.b
    public CharSequence g() {
        return this.f42e.getTitle();
    }

    @Override // a.a.o.b
    public void i() {
        this.f43f.onPrepareActionMode(this, this.i);
    }

    @Override // a.a.o.b
    public boolean j() {
        return this.f42e.b();
    }

    @Override // a.a.o.b
    public void a(int i) {
        a((CharSequence) this.f41d.getString(i));
    }

    @Override // a.a.o.b
    public void b(int i) {
        b(this.f41d.getString(i));
    }

    @Override // a.a.o.b
    public void a(boolean z) {
        super.a(z);
        this.f42e.setTitleOptional(z);
    }

    @Override // a.a.o.b
    public View b() {
        WeakReference<View> weakReference = this.g;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    @Override // a.a.o.b
    public void a(View view) {
        this.f42e.setCustomView(view);
        this.g = view != null ? new WeakReference<>(view) : null;
    }

    @Override // a.a.o.b
    public void a() {
        if (this.h) {
            return;
        }
        this.h = true;
        this.f42e.sendAccessibilityEvent(32);
        this.f43f.onDestroyActionMode(this);
    }

    @Override // androidx.appcompat.view.menu.g.a
    public boolean a(androidx.appcompat.view.menu.g gVar, MenuItem menuItem) {
        return this.f43f.onActionItemClicked(this, menuItem);
    }

    @Override // androidx.appcompat.view.menu.g.a
    public void a(androidx.appcompat.view.menu.g gVar) {
        i();
        this.f42e.d();
    }
}
