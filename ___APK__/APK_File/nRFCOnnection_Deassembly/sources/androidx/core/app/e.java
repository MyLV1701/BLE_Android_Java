package androidx.core.app;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

/* loaded from: classes.dex */
public final class e {
    public static void a(Activity activity, Intent intent) {
        if (Build.VERSION.SDK_INT >= 16) {
            activity.navigateUpTo(intent);
            return;
        }
        intent.addFlags(67108864);
        activity.startActivity(intent);
        activity.finish();
    }

    public static boolean b(Activity activity, Intent intent) {
        if (Build.VERSION.SDK_INT >= 16) {
            return activity.shouldUpRecreateTask(intent);
        }
        String action = activity.getIntent().getAction();
        return (action == null || action.equals("android.intent.action.MAIN")) ? false : true;
    }

    public static String b(Activity activity) {
        try {
            return b(activity, activity.getComponentName());
        } catch (PackageManager.NameNotFoundException e2) {
            throw new IllegalArgumentException(e2);
        }
    }

    public static Intent a(Activity activity) {
        Intent parentActivityIntent;
        if (Build.VERSION.SDK_INT >= 16 && (parentActivityIntent = activity.getParentActivityIntent()) != null) {
            return parentActivityIntent;
        }
        String b2 = b(activity);
        if (b2 == null) {
            return null;
        }
        ComponentName componentName = new ComponentName(activity, b2);
        try {
            if (b(activity, componentName) == null) {
                return Intent.makeMainActivity(componentName);
            }
            return new Intent().setComponent(componentName);
        } catch (PackageManager.NameNotFoundException unused) {
            Log.e("NavUtils", "getParentActivityIntent: bad parentActivityName '" + b2 + "' in manifest");
            return null;
        }
    }

    public static String b(Context context, ComponentName componentName) {
        String string;
        String str;
        PackageManager packageManager = context.getPackageManager();
        int i = Build.VERSION.SDK_INT;
        int i2 = 640;
        int i3 = Build.VERSION.SDK_INT;
        if (i3 >= 29) {
            i2 = 269222528;
        } else if (i3 >= 24) {
            i2 = 787072;
        }
        ActivityInfo activityInfo = packageManager.getActivityInfo(componentName, i2);
        if (Build.VERSION.SDK_INT >= 16 && (str = activityInfo.parentActivityName) != null) {
            return str;
        }
        if (activityInfo.metaData == null || (string = activityInfo.metaData.getString("android.support.PARENT_ACTIVITY")) == null) {
            return null;
        }
        if (string.charAt(0) != '.') {
            return string;
        }
        return context.getPackageName() + string;
    }

    public static Intent a(Context context, ComponentName componentName) {
        String b2 = b(context, componentName);
        if (b2 == null) {
            return null;
        }
        ComponentName componentName2 = new ComponentName(componentName.getPackageName(), b2);
        if (b(context, componentName2) == null) {
            return Intent.makeMainActivity(componentName2);
        }
        return new Intent().setComponent(componentName2);
    }
}