package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.ViewGroup;
import android.view.Window;
import androidx.appcompat.view.menu.g;
import androidx.appcompat.view.menu.n;

/* loaded from: classes.dex */
public interface z {
    a.f.l.a0 a(int i, long j);

    void a(int i);

    void a(Drawable drawable);

    void a(Menu menu, n.a aVar);

    void a(n.a aVar, g.a aVar2);

    void a(m0 m0Var);

    void a(boolean z);

    boolean a();

    void b();

    void b(int i);

    void b(boolean z);

    void c(int i);

    boolean c();

    void collapseActionView();

    void d(int i);

    boolean d();

    void e(int i);

    boolean e();

    boolean f();

    void g();

    Context getContext();

    CharSequence getTitle();

    ViewGroup h();

    boolean i();

    int j();

    Menu k();

    int l();

    void m();

    void n();

    void setIcon(int i);

    void setIcon(Drawable drawable);

    void setWindowCallback(Window.Callback callback);

    void setWindowTitle(CharSequence charSequence);
}
