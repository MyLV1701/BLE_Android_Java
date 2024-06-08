package a.a.o;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

/* loaded from: classes.dex */
public abstract class b {

    /* renamed from: b, reason: collision with root package name */
    private Object f34b;

    /* renamed from: c, reason: collision with root package name */
    private boolean f35c;

    /* loaded from: classes.dex */
    public interface a {
        boolean onActionItemClicked(b bVar, MenuItem menuItem);

        boolean onCreateActionMode(b bVar, Menu menu);

        void onDestroyActionMode(b bVar);

        boolean onPrepareActionMode(b bVar, Menu menu);
    }

    public abstract void a();

    public abstract void a(int i);

    public abstract void a(View view);

    public abstract void a(CharSequence charSequence);

    public void a(Object obj) {
        this.f34b = obj;
    }

    public abstract View b();

    public abstract void b(int i);

    public abstract void b(CharSequence charSequence);

    public abstract Menu c();

    public abstract MenuInflater d();

    public abstract CharSequence e();

    public Object f() {
        return this.f34b;
    }

    public abstract CharSequence g();

    public boolean h() {
        return this.f35c;
    }

    public abstract void i();

    public abstract boolean j();

    public void a(boolean z) {
        this.f35c = z;
    }
}
