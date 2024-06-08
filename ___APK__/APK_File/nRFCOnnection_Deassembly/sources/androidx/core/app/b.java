package androidx.core.app;

import android.app.Activity;
import android.app.Application;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

/* loaded from: classes.dex */
final class b {
    private static final Handler g = new Handler(Looper.getMainLooper());

    /* renamed from: a, reason: collision with root package name */
    protected static final Class<?> f1194a = a();

    /* renamed from: b, reason: collision with root package name */
    protected static final Field f1195b = b();

    /* renamed from: c, reason: collision with root package name */
    protected static final Field f1196c = c();

    /* renamed from: d, reason: collision with root package name */
    protected static final Method f1197d = b(f1194a);

    /* renamed from: e, reason: collision with root package name */
    protected static final Method f1198e = a(f1194a);

    /* renamed from: f, reason: collision with root package name */
    protected static final Method f1199f = c(f1194a);

    /* loaded from: classes.dex */
    static class a implements Runnable {

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ d f1200b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ Object f1201c;

        a(d dVar, Object obj) {
            this.f1200b = dVar;
            this.f1201c = obj;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f1200b.f1206a = this.f1201c;
        }
    }

    /* renamed from: androidx.core.app.b$b, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    static class RunnableC0048b implements Runnable {

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ Application f1202b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ d f1203c;

        RunnableC0048b(Application application, d dVar) {
            this.f1202b = application;
            this.f1203c = dVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f1202b.unregisterActivityLifecycleCallbacks(this.f1203c);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class c implements Runnable {

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ Object f1204b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ Object f1205c;

        c(Object obj, Object obj2) {
            this.f1204b = obj;
            this.f1205c = obj2;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                if (b.f1197d != null) {
                    b.f1197d.invoke(this.f1204b, this.f1205c, false, "AppCompat recreation");
                } else {
                    b.f1198e.invoke(this.f1204b, this.f1205c, false);
                }
            } catch (RuntimeException e2) {
                if (e2.getClass() == RuntimeException.class && e2.getMessage() != null && e2.getMessage().startsWith("Unable to stop")) {
                    throw e2;
                }
            } catch (Throwable th) {
                Log.e("ActivityRecreator", "Exception while invoking performStopActivity", th);
            }
        }
    }

    /* loaded from: classes.dex */
    private static final class d implements Application.ActivityLifecycleCallbacks {

        /* renamed from: a, reason: collision with root package name */
        Object f1206a;

        /* renamed from: b, reason: collision with root package name */
        private Activity f1207b;

        /* renamed from: c, reason: collision with root package name */
        private boolean f1208c = false;

        /* renamed from: d, reason: collision with root package name */
        private boolean f1209d = false;

        /* renamed from: e, reason: collision with root package name */
        private boolean f1210e = false;

        d(Activity activity) {
            this.f1207b = activity;
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
            if (this.f1207b == activity) {
                this.f1207b = null;
                this.f1209d = true;
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
            if (!this.f1209d || this.f1210e || this.f1208c || !b.a(this.f1206a, activity)) {
                return;
            }
            this.f1210e = true;
            this.f1206a = null;
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
            if (this.f1207b == activity) {
                this.f1208c = true;
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean a(Activity activity) {
        Object obj;
        if (Build.VERSION.SDK_INT >= 28) {
            activity.recreate();
            return true;
        }
        if (d() && f1199f == null) {
            return false;
        }
        if (f1198e == null && f1197d == null) {
            return false;
        }
        try {
            Object obj2 = f1196c.get(activity);
            if (obj2 == null || (obj = f1195b.get(activity)) == null) {
                return false;
            }
            Application application = activity.getApplication();
            d dVar = new d(activity);
            application.registerActivityLifecycleCallbacks(dVar);
            g.post(new a(dVar, obj2));
            try {
                if (d()) {
                    f1199f.invoke(obj, obj2, null, null, 0, false, null, null, false, false);
                } else {
                    activity.recreate();
                }
                return true;
            } finally {
                g.post(new RunnableC0048b(application, dVar));
            }
        } catch (Throwable unused) {
            return false;
        }
    }

    private static Method b(Class<?> cls) {
        if (cls == null) {
            return null;
        }
        try {
            Method declaredMethod = cls.getDeclaredMethod("performStopActivity", IBinder.class, Boolean.TYPE, String.class);
            declaredMethod.setAccessible(true);
            return declaredMethod;
        } catch (Throwable unused) {
            return null;
        }
    }

    private static Method c(Class<?> cls) {
        if (d() && cls != null) {
            try {
                Method declaredMethod = cls.getDeclaredMethod("requestRelaunchActivity", IBinder.class, List.class, List.class, Integer.TYPE, Boolean.TYPE, Configuration.class, Configuration.class, Boolean.TYPE, Boolean.TYPE);
                declaredMethod.setAccessible(true);
                return declaredMethod;
            } catch (Throwable unused) {
            }
        }
        return null;
    }

    private static boolean d() {
        int i = Build.VERSION.SDK_INT;
        return i == 26 || i == 27;
    }

    private static Field b() {
        try {
            Field declaredField = Activity.class.getDeclaredField("mMainThread");
            declaredField.setAccessible(true);
            return declaredField;
        } catch (Throwable unused) {
            return null;
        }
    }

    private static Field c() {
        try {
            Field declaredField = Activity.class.getDeclaredField("mToken");
            declaredField.setAccessible(true);
            return declaredField;
        } catch (Throwable unused) {
            return null;
        }
    }

    protected static boolean a(Object obj, Activity activity) {
        try {
            Object obj2 = f1196c.get(activity);
            if (obj2 != obj) {
                return false;
            }
            g.postAtFrontOfQueue(new c(f1195b.get(activity), obj2));
            return true;
        } catch (Throwable th) {
            Log.e("ActivityRecreator", "Exception while fetching field values", th);
            return false;
        }
    }

    private static Method a(Class<?> cls) {
        if (cls == null) {
            return null;
        }
        try {
            Method declaredMethod = cls.getDeclaredMethod("performStopActivity", IBinder.class, Boolean.TYPE);
            declaredMethod.setAccessible(true);
            return declaredMethod;
        } catch (Throwable unused) {
            return null;
        }
    }

    private static Class<?> a() {
        try {
            return Class.forName("android.app.ActivityThread");
        } catch (Throwable unused) {
            return null;
        }
    }
}
