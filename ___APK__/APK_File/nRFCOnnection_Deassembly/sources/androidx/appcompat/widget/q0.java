package androidx.appcompat.widget;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Build;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class q0 extends ContextWrapper {

    /* renamed from: c, reason: collision with root package name */
    private static final Object f1077c = new Object();

    /* renamed from: d, reason: collision with root package name */
    private static ArrayList<WeakReference<q0>> f1078d;

    /* renamed from: a, reason: collision with root package name */
    private final Resources f1079a;

    /* renamed from: b, reason: collision with root package name */
    private final Resources.Theme f1080b;

    private q0(Context context) {
        super(context);
        if (y0.b()) {
            this.f1079a = new y0(this, context.getResources());
            this.f1080b = this.f1079a.newTheme();
            this.f1080b.setTo(context.getTheme());
        } else {
            this.f1079a = new s0(this, context.getResources());
            this.f1080b = null;
        }
    }

    private static boolean a(Context context) {
        if ((context instanceof q0) || (context.getResources() instanceof s0) || (context.getResources() instanceof y0)) {
            return false;
        }
        return Build.VERSION.SDK_INT < 21 || y0.b();
    }

    public static Context b(Context context) {
        if (!a(context)) {
            return context;
        }
        synchronized (f1077c) {
            if (f1078d == null) {
                f1078d = new ArrayList<>();
            } else {
                for (int size = f1078d.size() - 1; size >= 0; size--) {
                    WeakReference<q0> weakReference = f1078d.get(size);
                    if (weakReference == null || weakReference.get() == null) {
                        f1078d.remove(size);
                    }
                }
                for (int size2 = f1078d.size() - 1; size2 >= 0; size2--) {
                    WeakReference<q0> weakReference2 = f1078d.get(size2);
                    q0 q0Var = weakReference2 != null ? weakReference2.get() : null;
                    if (q0Var != null && q0Var.getBaseContext() == context) {
                        return q0Var;
                    }
                }
            }
            q0 q0Var2 = new q0(context);
            f1078d.add(new WeakReference<>(q0Var2));
            return q0Var2;
        }
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public AssetManager getAssets() {
        return this.f1079a.getAssets();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        return this.f1079a;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources.Theme getTheme() {
        Resources.Theme theme = this.f1080b;
        return theme == null ? super.getTheme() : theme;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void setTheme(int i) {
        Resources.Theme theme = this.f1080b;
        if (theme == null) {
            super.setTheme(i);
        } else {
            theme.applyStyle(i, true);
        }
    }
}
