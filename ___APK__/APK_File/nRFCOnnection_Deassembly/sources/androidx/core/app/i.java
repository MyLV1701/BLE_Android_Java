package androidx.core.app;

import android.app.Notification;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import androidx.core.app.g;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class i {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f1240a = new Object();

    /* renamed from: b, reason: collision with root package name */
    private static Field f1241b;

    /* renamed from: c, reason: collision with root package name */
    private static boolean f1242c;

    public static SparseArray<Bundle> a(List<Bundle> list) {
        int size = list.size();
        SparseArray<Bundle> sparseArray = null;
        for (int i = 0; i < size; i++) {
            Bundle bundle = list.get(i);
            if (bundle != null) {
                if (sparseArray == null) {
                    sparseArray = new SparseArray<>();
                }
                sparseArray.put(i, bundle);
            }
        }
        return sparseArray;
    }

    public static Bundle a(Notification notification) {
        synchronized (f1240a) {
            if (f1242c) {
                return null;
            }
            try {
                if (f1241b == null) {
                    Field declaredField = Notification.class.getDeclaredField("extras");
                    if (!Bundle.class.isAssignableFrom(declaredField.getType())) {
                        Log.e("NotificationCompat", "Notification.extras field is not of type Bundle");
                        f1242c = true;
                        return null;
                    }
                    declaredField.setAccessible(true);
                    f1241b = declaredField;
                }
                Bundle bundle = (Bundle) f1241b.get(notification);
                if (bundle == null) {
                    bundle = new Bundle();
                    f1241b.set(notification, bundle);
                }
                return bundle;
            } catch (IllegalAccessException e2) {
                Log.e("NotificationCompat", "Unable to access notification extras", e2);
                f1242c = true;
                return null;
            } catch (NoSuchFieldException e3) {
                Log.e("NotificationCompat", "Unable to access notification extras", e3);
                f1242c = true;
                return null;
            }
        }
    }

    public static Bundle a(Notification.Builder builder, g.a aVar) {
        builder.addAction(aVar.e(), aVar.j(), aVar.a());
        Bundle bundle = new Bundle(aVar.d());
        if (aVar.g() != null) {
            bundle.putParcelableArray("android.support.remoteInputs", a(aVar.g()));
        }
        if (aVar.c() != null) {
            bundle.putParcelableArray("android.support.dataRemoteInputs", a(aVar.c()));
        }
        bundle.putBoolean("android.support.allowGeneratedReplies", aVar.b());
        return bundle;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Bundle a(g.a aVar) {
        Bundle bundle;
        Bundle bundle2 = new Bundle();
        bundle2.putInt("icon", aVar.e());
        bundle2.putCharSequence("title", aVar.j());
        bundle2.putParcelable("actionIntent", aVar.a());
        if (aVar.d() != null) {
            bundle = new Bundle(aVar.d());
        } else {
            bundle = new Bundle();
        }
        bundle.putBoolean("android.support.allowGeneratedReplies", aVar.b());
        bundle2.putBundle("extras", bundle);
        bundle2.putParcelableArray("remoteInputs", a(aVar.g()));
        bundle2.putBoolean("showsUserInterface", aVar.i());
        bundle2.putInt("semanticAction", aVar.h());
        return bundle2;
    }

    private static Bundle a(k kVar) {
        Bundle bundle = new Bundle();
        bundle.putString("resultKey", kVar.g());
        bundle.putCharSequence("label", kVar.f());
        bundle.putCharSequenceArray("choices", kVar.c());
        bundle.putBoolean("allowFreeFormInput", kVar.a());
        bundle.putBundle("extras", kVar.e());
        Set<String> b2 = kVar.b();
        if (b2 != null && !b2.isEmpty()) {
            ArrayList<String> arrayList = new ArrayList<>(b2.size());
            Iterator<String> it = b2.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next());
            }
            bundle.putStringArrayList("allowedDataTypes", arrayList);
        }
        return bundle;
    }

    private static Bundle[] a(k[] kVarArr) {
        if (kVarArr == null) {
            return null;
        }
        Bundle[] bundleArr = new Bundle[kVarArr.length];
        for (int i = 0; i < kVarArr.length; i++) {
            bundleArr[i] = a(kVarArr[i]);
        }
        return bundleArr;
    }
}
