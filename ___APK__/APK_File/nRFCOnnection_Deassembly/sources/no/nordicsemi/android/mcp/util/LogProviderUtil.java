package no.nordicsemi.android.mcp.util;

import android.content.ContentProviderClient;
import android.content.Context;
import android.os.Build;

/* loaded from: classes.dex */
public class LogProviderUtil {
    public static boolean logProviderExists(Context context) {
        ContentProviderClient acquireUnstableContentProviderClient = context.getContentResolver().acquireUnstableContentProviderClient("no.nordicsemi.android.log");
        if (acquireUnstableContentProviderClient == null) {
            return false;
        }
        if (Build.VERSION.SDK_INT < 24) {
            acquireUnstableContentProviderClient.release();
            return true;
        }
        acquireUnstableContentProviderClient.close();
        return true;
    }
}
