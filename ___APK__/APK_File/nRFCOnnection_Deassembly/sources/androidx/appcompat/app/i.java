package androidx.appcompat.app;

import a.a.o.b;
import a.f.l.e;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;

/* loaded from: classes.dex */
public class i extends Dialog implements f {

    /* renamed from: b, reason: collision with root package name */
    private g f698b;

    /* renamed from: c, reason: collision with root package name */
    private final e.a f699c;

    /* loaded from: classes.dex */
    class a implements e.a {
        a() {
        }

        @Override // a.f.l.e.a
        public boolean superDispatchKeyEvent(KeyEvent keyEvent) {
            return i.this.a(keyEvent);
        }
    }

    public i(Context context, int i) {
        super(context, a(context, i));
        this.f699c = new a();
        g a2 = a();
        a2.d(a(context, i));
        a2.a((Bundle) null);
    }

    public boolean a(int i) {
        return a().b(i);
    }

    @Override // android.app.Dialog
    public void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        a().a(view, layoutParams);
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return a.f.l.e.a(this.f699c, getWindow().getDecorView(), this, keyEvent);
    }

    @Override // android.app.Dialog
    public <T extends View> T findViewById(int i) {
        return (T) a().a(i);
    }

    @Override // android.app.Dialog
    public void invalidateOptionsMenu() {
        a().g();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.app.Dialog
    public void onCreate(Bundle bundle) {
        a().f();
        super.onCreate(bundle);
        a().a(bundle);
    }

    @Override // android.app.Dialog
    protected void onStop() {
        super.onStop();
        a().k();
    }

    @Override // androidx.appcompat.app.f
    public void onSupportActionModeFinished(a.a.o.b bVar) {
    }

    @Override // androidx.appcompat.app.f
    public void onSupportActionModeStarted(a.a.o.b bVar) {
    }

    @Override // androidx.appcompat.app.f
    public a.a.o.b onWindowStartingSupportActionMode(b.a aVar) {
        return null;
    }

    @Override // android.app.Dialog
    public void setContentView(int i) {
        a().c(i);
    }

    @Override // android.app.Dialog
    public void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        a().a(charSequence);
    }

    public g a() {
        if (this.f698b == null) {
            this.f698b = g.a(this, this);
        }
        return this.f698b;
    }

    @Override // android.app.Dialog
    public void setContentView(View view) {
        a().a(view);
    }

    @Override // android.app.Dialog
    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        a().b(view, layoutParams);
    }

    @Override // android.app.Dialog
    public void setTitle(int i) {
        super.setTitle(i);
        a().a(getContext().getString(i));
    }

    private static int a(Context context, int i) {
        if (i != 0) {
            return i;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(a.a.a.dialogTheme, typedValue, true);
        return typedValue.resourceId;
    }

    boolean a(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent);
    }
}
