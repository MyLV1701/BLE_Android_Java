package no.nordicsemi.android.mcp.test;

import a.f.d.b;
import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.IBinder;
import androidx.core.app.g;
import no.nordicsemi.android.log.LogSession;
import no.nordicsemi.android.log.Logger;
import no.nordicsemi.android.mcp.R;
import no.nordicsemi.android.mcp.util.NotificationHelper;

/* loaded from: classes.dex */
public class TestService extends IntentService {
    public static final String BROADCAST_ABORT_ACTION = "no.nordicsemi.android.test.broadcast.BROADCAST_ABORT_ACTION";
    private static final int ERROR_NOTIFICATION_ID = 35322;
    public static final String EXTRA_FILE_PATH = "no.nordicsemi.android.test.extra.EXTRA_FILE_PATH";
    private static final int ONGOING_NOTIFICATION_ID = 35321;
    private static final int STATUS_ABORTED = -7829368;
    private static final int STATUS_ERROR = -65536;
    private static final int STATUS_OK = -16730086;
    private static final String TAG = "TestService";
    private final BroadcastReceiver mAbortActionReceiver;
    private boolean mAborted;
    private LogSession mLogSession;
    private TestPerformer mTestPerformer;

    public TestService() {
        super(TAG);
        this.mAbortActionReceiver = new BroadcastReceiver() { // from class: no.nordicsemi.android.mcp.test.TestService.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                Logger.w(TestService.this.mLogSession, "[ACTION] Aborting...");
                TestService.this.mAborted = true;
                if (TestService.this.mTestPerformer != null) {
                    TestService.this.mTestPerformer.abort();
                }
            }
        };
    }

    private String getLogContent() {
        Cursor query = getContentResolver().query(this.mLogSession.getSessionContentUri(), null, null, null, null);
        if (query != null) {
            try {
                if (query.moveToNext()) {
                    String string = query.getString(0);
                    if (query != null) {
                        query.close();
                    }
                    return string;
                }
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    if (query != null) {
                        try {
                            query.close();
                        } catch (Throwable unused) {
                        }
                    }
                    throw th2;
                }
            }
        }
        if (query != null) {
            query.close();
        }
        return null;
    }

    private void showErrorNotification(String str) {
        showNotification(R.string.test_notification_error_title, str, STATUS_ERROR);
    }

    private void showNotification(int i, String str, int i2) {
        g.d dVar = new g.d(this, NotificationHelper.CHANNEL_TESTS);
        dVar.d(R.drawable.ic_stat_notify_nrf_connect);
        dVar.c(getString(i));
        dVar.c(false);
        dVar.a(true);
        dVar.b(str);
        LogSession logSession = this.mLogSession;
        if (logSession != null) {
            Intent intent = new Intent("android.intent.action.VIEW", logSession.getSessionUri());
            intent.setFlags(268435456);
            dVar.a(PendingIntent.getActivity(this, 0, intent, 268435456));
        }
        dVar.a(i2);
        ((NotificationManager) getSystemService("notification")).notify(ERROR_NOTIFICATION_ID, dVar.a());
    }

    private void startForeground() {
        g.d dVar = new g.d(this, NotificationHelper.CHANNEL_TESTS);
        dVar.d(R.drawable.ic_stat_notify_nrf_connect);
        dVar.c(getText(R.string.test_notification_title));
        dVar.c(true);
        LogSession logSession = this.mLogSession;
        if (logSession != null) {
            Intent intent = new Intent("android.intent.action.VIEW", logSession.getSessionUri());
            intent.setFlags(268435456);
            dVar.a(PendingIntent.getActivity(this, 0, intent, 268435456));
            dVar.b(getText(R.string.test_notification_text));
        } else {
            Intent intent2 = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=no.nordicsemi.android.log"));
            intent2.setFlags(268435456);
            dVar.a(PendingIntent.getActivity(this, 0, intent2, 268435456));
            dVar.b(getText(R.string.test_notification_text_no_logger));
        }
        dVar.a(R.drawable.ic_action_notify_cancel, getString(R.string.action_abort), PendingIntent.getBroadcast(this, 1, new Intent(BROADCAST_ABORT_ACTION), 134217728));
        dVar.a(b.a(this, R.color.nordicBlue));
        startForeground(ONGOING_NOTIFICATION_ID, dVar.a());
    }

    @Override // android.app.IntentService, android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.IntentService, android.app.Service
    public void onCreate() {
        super.onCreate();
        registerReceiver(this.mAbortActionReceiver, new IntentFilter(BROADCAST_ABORT_ACTION));
    }

    @Override // android.app.IntentService, android.app.Service
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(this.mAbortActionReceiver);
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x019f  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x01b0  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x01de  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x01f9  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x01d1  */
    @Override // android.app.IntentService
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void onHandleIntent(android.content.Intent r17) {
        /*
            Method dump skipped, instructions count: 631
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: no.nordicsemi.android.mcp.test.TestService.onHandleIntent(android.content.Intent):void");
    }
}
