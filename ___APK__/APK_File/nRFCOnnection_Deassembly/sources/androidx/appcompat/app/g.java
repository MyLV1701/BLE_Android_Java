package androidx.appcompat.app;

import a.a.o.b;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.b;
import androidx.appcompat.widget.Toolbar;
import java.lang.ref.WeakReference;
import java.util.Iterator;

/* loaded from: classes.dex */
public abstract class g {

    /* renamed from: b, reason: collision with root package name */
    private static int f665b = -100;

    /* renamed from: c, reason: collision with root package name */
    private static final a.d.b<WeakReference<g>> f666c = new a.d.b<>();

    /* renamed from: d, reason: collision with root package name */
    private static final Object f667d = new Object();

    public static g a(Activity activity, f fVar) {
        return new h(activity, fVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void b(g gVar) {
        synchronized (f667d) {
            c(gVar);
        }
    }

    private static void c(g gVar) {
        synchronized (f667d) {
            Iterator<WeakReference<g>> it = f666c.iterator();
            while (it.hasNext()) {
                g gVar2 = it.next().get();
                if (gVar2 == gVar || gVar2 == null) {
                    it.remove();
                }
            }
        }
    }

    public static void e(int i) {
        if (i != -1 && i != 0 && i != 1 && i != 2 && i != 3) {
            Log.d("AppCompatDelegate", "setDefaultNightMode() called with an unknown mode");
        } else if (f665b != i) {
            f665b = i;
            l();
        }
    }

    private static void l() {
        synchronized (f667d) {
            Iterator<WeakReference<g>> it = f666c.iterator();
            while (it.hasNext()) {
                g gVar = it.next().get();
                if (gVar != null) {
                    gVar.a();
                }
            }
        }
    }

    public static int m() {
        return f665b;
    }

    public abstract a.a.o.b a(b.a aVar);

    public abstract <T extends View> T a(int i);

    public void a(Context context) {
    }

    public abstract void a(Configuration configuration);

    public abstract void a(Bundle bundle);

    public abstract void a(View view);

    public abstract void a(View view, ViewGroup.LayoutParams layoutParams);

    public abstract void a(Toolbar toolbar);

    public abstract void a(CharSequence charSequence);

    public abstract boolean a();

    public abstract b.InterfaceC0039b b();

    public abstract void b(Bundle bundle);

    public abstract void b(View view, ViewGroup.LayoutParams layoutParams);

    public abstract boolean b(int i);

    public int c() {
        return -100;
    }

    public abstract void c(int i);

    public abstract void c(Bundle bundle);

    public abstract MenuInflater d();

    public void d(int i) {
    }

    public abstract a e();

    public abstract void f();

    public abstract void g();

    public abstract void h();

    public abstract void i();

    public abstract void j();

    public abstract void k();

    public static g a(Dialog dialog, f fVar) {
        return new h(dialog, fVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(g gVar) {
        synchronized (f667d) {
            c(gVar);
            f666c.add(new WeakReference<>(gVar));
        }
    }
}
