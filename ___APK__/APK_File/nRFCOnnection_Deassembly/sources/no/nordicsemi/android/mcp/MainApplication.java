package no.nordicsemi.android.mcp;

import android.app.Application;
import android.os.Build;
import no.nordicsemi.android.mcp.util.NotificationHelper;

/* loaded from: classes.dex */
public class MainApplication extends Application {
    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationHelper.initChannels(this);
        }
    }
}
