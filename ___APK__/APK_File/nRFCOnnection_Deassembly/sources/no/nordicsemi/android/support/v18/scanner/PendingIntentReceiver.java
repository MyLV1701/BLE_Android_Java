package no.nordicsemi.android.support.v18.scanner;

import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.ScanSettings;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import java.util.ArrayList;
import no.nordicsemi.android.support.v18.scanner.e;

/* loaded from: classes.dex */
public class PendingIntentReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        PendingIntent pendingIntent;
        e.a a2;
        e eVar;
        int i;
        if (context == null || intent == null || (pendingIntent = (PendingIntent) intent.getParcelableExtra("no.nordicsemi.android.support.v18.EXTRA_PENDING_INTENT")) == null) {
            return;
        }
        ArrayList parcelableArrayListExtra = intent.getParcelableArrayListExtra("no.nordicsemi.android.support.v18.EXTRA_FILTERS");
        ScanSettings scanSettings = (ScanSettings) intent.getParcelableExtra("no.nordicsemi.android.support.v18.EXTRA_SETTINGS");
        if (parcelableArrayListExtra == null || scanSettings == null) {
            return;
        }
        boolean booleanExtra = intent.getBooleanExtra("no.nordicsemi.android.support.v18.EXTRA_USE_HARDWARE_BATCHING", true);
        boolean booleanExtra2 = intent.getBooleanExtra("no.nordicsemi.android.support.v18.EXTRA_USE_HARDWARE_FILTERING", true);
        boolean booleanExtra3 = intent.getBooleanExtra("no.nordicsemi.android.support.v18.EXTRA_USE_HARDWARE_CALLBACK_TYPES", true);
        long longExtra = intent.getLongExtra("no.nordicsemi.android.support.v18.EXTRA_MATCH_LOST_TIMEOUT", 10000L);
        long longExtra2 = intent.getLongExtra("no.nordicsemi.android.support.v18.EXTRA_MATCH_LOST_INTERVAL", 10000L);
        int intExtra = intent.getIntExtra("no.nordicsemi.android.support.v18.EXTRA_MATCH_MODE", 1);
        int intExtra2 = intent.getIntExtra("no.nordicsemi.android.support.v18.EXTRA_NUM_OF_MATCHES", 3);
        a a3 = a.a();
        e eVar2 = (e) a3;
        ArrayList<k> c2 = eVar2.c(parcelableArrayListExtra);
        n a4 = eVar2.a(scanSettings, booleanExtra, booleanExtra2, booleanExtra3, longExtra, longExtra2, intExtra, intExtra2);
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        boolean isOffloadedScanBatchingSupported = defaultAdapter.isOffloadedScanBatchingSupported();
        boolean isOffloadedFilteringSupported = defaultAdapter.isOffloadedFilteringSupported();
        synchronized (a3) {
            try {
                a2 = eVar2.a(pendingIntent);
                if (a2 == null) {
                    eVar = eVar2;
                    i = 1;
                    e.a aVar = new e.a(isOffloadedScanBatchingSupported, isOffloadedFilteringSupported, c2, a4, pendingIntent);
                    eVar.a(pendingIntent, aVar);
                    a2 = aVar;
                } else {
                    eVar = eVar2;
                    i = 1;
                }
            } catch (IllegalStateException unused) {
                return;
            }
        }
        a2.o.a(context);
        ArrayList parcelableArrayListExtra2 = intent.getParcelableArrayListExtra("android.bluetooth.le.extra.LIST_SCAN_RESULT");
        if (parcelableArrayListExtra2 != null) {
            ArrayList<m> a5 = eVar.a(parcelableArrayListExtra2);
            if (a4.n() > 0) {
                a2.a(a5);
            } else if (!a5.isEmpty()) {
                a2.a(intent.getIntExtra("android.bluetooth.le.extra.CALLBACK_TYPE", i), a5.get(0));
            }
        } else {
            int intExtra3 = intent.getIntExtra("android.bluetooth.le.extra.ERROR_CODE", 0);
            if (intExtra3 != 0) {
                a2.a(intExtra3);
            }
        }
        a2.o.a(null);
    }
}
