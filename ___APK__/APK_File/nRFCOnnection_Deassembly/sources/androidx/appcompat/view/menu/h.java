package androidx.appcompat.view.menu;

import android.content.DialogInterface;
import android.os.IBinder;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import androidx.appcompat.app.d;
import androidx.appcompat.view.menu.n;

/* loaded from: classes.dex */
class h implements DialogInterface.OnKeyListener, DialogInterface.OnClickListener, DialogInterface.OnDismissListener, n.a {

    /* renamed from: b, reason: collision with root package name */
    private g f800b;

    /* renamed from: c, reason: collision with root package name */
    private androidx.appcompat.app.d f801c;

    /* renamed from: d, reason: collision with root package name */
    e f802d;

    /* renamed from: e, reason: collision with root package name */
    private n.a f803e;

    public h(g gVar) {
        this.f800b = gVar;
    }

    public void a(IBinder iBinder) {
        g gVar = this.f800b;
        d.a aVar = new d.a(gVar.e());
        this.f802d = new e(aVar.b(), a.a.g.abc_list_menu_item_layout);
        this.f802d.a(this);
        this.f800b.a(this.f802d);
        aVar.a(this.f802d.d(), this);
        View i = gVar.i();
        if (i != null) {
            aVar.a(i);
        } else {
            aVar.a(gVar.g());
            aVar.b(gVar.h());
        }
        aVar.a(this);
        this.f801c = aVar.a();
        this.f801c.setOnDismissListener(this);
        WindowManager.LayoutParams attributes = this.f801c.getWindow().getAttributes();
        attributes.type = 1003;
        if (iBinder != null) {
            attributes.token = iBinder;
        }
        attributes.flags |= 131072;
        this.f801c.show();
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        this.f800b.a((j) this.f802d.d().getItem(i), 0);
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        this.f802d.a(this.f800b, true);
    }

    @Override // android.content.DialogInterface.OnKeyListener
    public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
        Window window;
        View decorView;
        KeyEvent.DispatcherState keyDispatcherState;
        View decorView2;
        KeyEvent.DispatcherState keyDispatcherState2;
        if (i == 82 || i == 4) {
            if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
                Window window2 = this.f801c.getWindow();
                if (window2 != null && (decorView2 = window2.getDecorView()) != null && (keyDispatcherState2 = decorView2.getKeyDispatcherState()) != null) {
                    keyDispatcherState2.startTracking(keyEvent, this);
                    return true;
                }
            } else if (keyEvent.getAction() == 1 && !keyEvent.isCanceled() && (window = this.f801c.getWindow()) != null && (decorView = window.getDecorView()) != null && (keyDispatcherState = decorView.getKeyDispatcherState()) != null && keyDispatcherState.isTracking(keyEvent)) {
                this.f800b.a(true);
                dialogInterface.dismiss();
                return true;
            }
        }
        return this.f800b.performShortcut(i, keyEvent, 0);
    }

    public void a() {
        androidx.appcompat.app.d dVar = this.f801c;
        if (dVar != null) {
            dVar.dismiss();
        }
    }

    @Override // androidx.appcompat.view.menu.n.a
    public void a(g gVar, boolean z) {
        if (z || gVar == this.f800b) {
            a();
        }
        n.a aVar = this.f803e;
        if (aVar != null) {
            aVar.a(gVar, z);
        }
    }

    @Override // androidx.appcompat.view.menu.n.a
    public boolean a(g gVar) {
        n.a aVar = this.f803e;
        if (aVar != null) {
            return aVar.a(gVar);
        }
        return false;
    }
}
