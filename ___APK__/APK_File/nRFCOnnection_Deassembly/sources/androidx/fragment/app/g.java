package androidx.fragment.app;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

/* loaded from: classes.dex */
public class g {

    /* renamed from: a, reason: collision with root package name */
    private final i<?> f1391a;

    private g(i<?> iVar) {
        this.f1391a = iVar;
    }

    public static g a(i<?> iVar) {
        a.f.k.h.a(iVar, "callbacks == null");
        return new g(iVar);
    }

    public void b() {
        this.f1391a.f1396e.e();
    }

    public void c() {
        this.f1391a.f1396e.f();
    }

    public void d() {
        this.f1391a.f1396e.h();
    }

    public void e() {
        this.f1391a.f1396e.i();
    }

    public void f() {
        this.f1391a.f1396e.k();
    }

    public void g() {
        this.f1391a.f1396e.l();
    }

    public void h() {
        this.f1391a.f1396e.m();
    }

    public boolean i() {
        return this.f1391a.f1396e.c(true);
    }

    public l j() {
        return this.f1391a.f1396e;
    }

    public void k() {
        this.f1391a.f1396e.x();
    }

    public Parcelable l() {
        return this.f1391a.f1396e.A();
    }

    public Fragment a(String str) {
        return this.f1391a.f1396e.c(str);
    }

    public void b(boolean z) {
        this.f1391a.f1396e.b(z);
    }

    public void a(Fragment fragment) {
        i<?> iVar = this.f1391a;
        iVar.f1396e.a(iVar, iVar, fragment);
    }

    public boolean b(Menu menu) {
        return this.f1391a.f1396e.b(menu);
    }

    public View a(View view, String str, Context context, AttributeSet attributeSet) {
        return this.f1391a.f1396e.q().onCreateView(view, str, context, attributeSet);
    }

    public boolean b(MenuItem menuItem) {
        return this.f1391a.f1396e.b(menuItem);
    }

    public void a(Parcelable parcelable) {
        i<?> iVar = this.f1391a;
        if (iVar instanceof androidx.lifecycle.x) {
            iVar.f1396e.a(parcelable);
            return;
        }
        throw new IllegalStateException("Your FragmentHostCallback must implement ViewModelStoreOwner to call restoreSaveState(). Call restoreAllState()  if you're still using retainNestedNonConfig().");
    }

    public void a() {
        this.f1391a.f1396e.d();
    }

    public void a(boolean z) {
        this.f1391a.f1396e.a(z);
    }

    public void a(Configuration configuration) {
        this.f1391a.f1396e.a(configuration);
    }

    public boolean a(Menu menu, MenuInflater menuInflater) {
        return this.f1391a.f1396e.a(menu, menuInflater);
    }

    public boolean a(MenuItem menuItem) {
        return this.f1391a.f1396e.a(menuItem);
    }

    public void a(Menu menu) {
        this.f1391a.f1396e.a(menu);
    }
}
