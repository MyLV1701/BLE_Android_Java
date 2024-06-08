package androidx.core.app;

import android.app.Activity;
import android.app.SharedElementCallback;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.view.View;
import androidx.core.app.l;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class a extends a.f.d.b {

    /* renamed from: c, reason: collision with root package name */
    private static c f1189c;

    /* renamed from: androidx.core.app.a$a, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    static class RunnableC0046a implements Runnable {

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ String[] f1190b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ Activity f1191c;

        /* renamed from: d, reason: collision with root package name */
        final /* synthetic */ int f1192d;

        RunnableC0046a(String[] strArr, Activity activity, int i) {
            this.f1190b = strArr;
            this.f1191c = activity;
            this.f1192d = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            int[] iArr = new int[this.f1190b.length];
            PackageManager packageManager = this.f1191c.getPackageManager();
            String packageName = this.f1191c.getPackageName();
            int length = this.f1190b.length;
            for (int i = 0; i < length; i++) {
                iArr[i] = packageManager.checkPermission(this.f1190b[i], packageName);
            }
            ((b) this.f1191c).onRequestPermissionsResult(this.f1192d, this.f1190b, iArr);
        }
    }

    /* loaded from: classes.dex */
    public interface b {
        void onRequestPermissionsResult(int i, String[] strArr, int[] iArr);
    }

    /* loaded from: classes.dex */
    public interface c {
        boolean a(Activity activity, int i, int i2, Intent intent);

        boolean a(Activity activity, String[] strArr, int i);
    }

    /* loaded from: classes.dex */
    public interface d {
        void validateRequestPermissionsRequestCode(int i);
    }

    /* loaded from: classes.dex */
    private static class e extends SharedElementCallback {

        /* renamed from: a, reason: collision with root package name */
        private final l f1193a;

        /* renamed from: androidx.core.app.a$e$a, reason: collision with other inner class name */
        /* loaded from: classes.dex */
        class C0047a implements l.a {
            C0047a(e eVar, SharedElementCallback.OnSharedElementsReadyListener onSharedElementsReadyListener) {
            }
        }

        e(l lVar) {
            this.f1193a = lVar;
        }

        @Override // android.app.SharedElementCallback
        public Parcelable onCaptureSharedElementSnapshot(View view, Matrix matrix, RectF rectF) {
            return this.f1193a.a(view, matrix, rectF);
        }

        @Override // android.app.SharedElementCallback
        public View onCreateSnapshotView(Context context, Parcelable parcelable) {
            return this.f1193a.a(context, parcelable);
        }

        @Override // android.app.SharedElementCallback
        public void onMapSharedElements(List<String> list, Map<String, View> map) {
            this.f1193a.a(list, map);
        }

        @Override // android.app.SharedElementCallback
        public void onRejectSharedElements(List<View> list) {
            this.f1193a.a(list);
        }

        @Override // android.app.SharedElementCallback
        public void onSharedElementEnd(List<String> list, List<View> list2, List<View> list3) {
            this.f1193a.a(list, list2, list3);
        }

        @Override // android.app.SharedElementCallback
        public void onSharedElementStart(List<String> list, List<View> list2, List<View> list3) {
            this.f1193a.b(list, list2, list3);
        }

        @Override // android.app.SharedElementCallback
        public void onSharedElementsArrived(List<String> list, List<View> list2, SharedElementCallback.OnSharedElementsReadyListener onSharedElementsReadyListener) {
            this.f1193a.a(list, list2, new C0047a(this, onSharedElementsReadyListener));
        }
    }

    public static c a() {
        return f1189c;
    }

    public static void b(Activity activity) {
        if (Build.VERSION.SDK_INT >= 21) {
            activity.finishAfterTransition();
        } else {
            activity.finish();
        }
    }

    public static void c(Activity activity) {
        if (Build.VERSION.SDK_INT >= 21) {
            activity.postponeEnterTransition();
        }
    }

    public static void d(Activity activity) {
        if (Build.VERSION.SDK_INT >= 28) {
            activity.recreate();
        } else {
            if (androidx.core.app.b.a(activity)) {
                return;
            }
            activity.recreate();
        }
    }

    public static void e(Activity activity) {
        if (Build.VERSION.SDK_INT >= 21) {
            activity.startPostponedEnterTransition();
        }
    }

    public static void a(Activity activity, Intent intent, int i, Bundle bundle) {
        if (Build.VERSION.SDK_INT >= 16) {
            activity.startActivityForResult(intent, i, bundle);
        } else {
            activity.startActivityForResult(intent, i);
        }
    }

    public static void b(Activity activity, l lVar) {
        if (Build.VERSION.SDK_INT >= 21) {
            activity.setExitSharedElementCallback(lVar != null ? new e(lVar) : null);
        }
    }

    public static void a(Activity activity, IntentSender intentSender, int i, Intent intent, int i2, int i3, int i4, Bundle bundle) {
        if (Build.VERSION.SDK_INT >= 16) {
            activity.startIntentSenderForResult(intentSender, i, intent, i2, i3, i4, bundle);
        } else {
            activity.startIntentSenderForResult(intentSender, i, intent, i2, i3, i4);
        }
    }

    public static void a(Activity activity) {
        if (Build.VERSION.SDK_INT >= 16) {
            activity.finishAffinity();
        } else {
            activity.finish();
        }
    }

    public static void a(Activity activity, l lVar) {
        if (Build.VERSION.SDK_INT >= 21) {
            activity.setEnterSharedElementCallback(lVar != null ? new e(lVar) : null);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void a(Activity activity, String[] strArr, int i) {
        c cVar = f1189c;
        if (cVar == null || !cVar.a(activity, strArr, i)) {
            if (Build.VERSION.SDK_INT >= 23) {
                if (activity instanceof d) {
                    ((d) activity).validateRequestPermissionsRequestCode(i);
                }
                activity.requestPermissions(strArr, i);
            } else if (activity instanceof b) {
                new Handler(Looper.getMainLooper()).post(new RunnableC0046a(strArr, activity, i));
            }
        }
    }

    public static boolean a(Activity activity, String str) {
        if (Build.VERSION.SDK_INT >= 23) {
            return activity.shouldShowRequestPermissionRationale(str);
        }
        return false;
    }
}
