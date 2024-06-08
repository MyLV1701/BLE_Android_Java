package no.nordicsemi.android.support.v18.scanner;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import no.nordicsemi.android.support.v18.scanner.n;

/* loaded from: classes.dex */
public class ScannerService extends Service {

    /* renamed from: b, reason: collision with root package name */
    private final Object f3907b = new Object();

    /* renamed from: c, reason: collision with root package name */
    private HashMap<PendingIntent, j> f3908c;

    /* renamed from: d, reason: collision with root package name */
    private Handler f3909d;

    private void a(List<k> list, n nVar, PendingIntent pendingIntent) {
        i iVar = new i(pendingIntent, nVar, this);
        synchronized (this.f3907b) {
            this.f3908c.put(pendingIntent, iVar);
        }
        try {
            a.a().a(list, nVar, iVar, this.f3909d);
        } catch (Exception e2) {
            Log.w("ScannerService", "Starting scanning failed", e2);
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.f3908c = new HashMap<>();
        this.f3909d = new Handler();
    }

    @Override // android.app.Service
    public void onDestroy() {
        a a2 = a.a();
        Iterator<j> it = this.f3908c.values().iterator();
        while (it.hasNext()) {
            try {
                a2.a(it.next());
            } catch (Exception unused) {
            }
        }
        this.f3908c.clear();
        this.f3908c = null;
        this.f3909d = null;
        super.onDestroy();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        boolean containsKey;
        boolean isEmpty;
        PendingIntent pendingIntent = (PendingIntent) intent.getParcelableExtra("no.nordicsemi.android.support.v18.EXTRA_PENDING_INTENT");
        boolean booleanExtra = intent.getBooleanExtra("no.nordicsemi.android.support.v18.EXTRA_START", false);
        boolean z = !booleanExtra;
        if (pendingIntent == null) {
            synchronized (this.f3907b) {
                isEmpty = this.f3908c.isEmpty();
            }
            if (isEmpty) {
                stopSelf();
            }
            return 2;
        }
        synchronized (this.f3907b) {
            containsKey = this.f3908c.containsKey(pendingIntent);
        }
        if (booleanExtra && !containsKey) {
            List<k> parcelableArrayListExtra = intent.getParcelableArrayListExtra("no.nordicsemi.android.support.v18.EXTRA_FILTERS");
            n nVar = (n) intent.getParcelableExtra("no.nordicsemi.android.support.v18.EXTRA_SETTINGS");
            if (parcelableArrayListExtra == null) {
                parcelableArrayListExtra = Collections.emptyList();
            }
            if (nVar == null) {
                nVar = new n.b().a();
            }
            a(parcelableArrayListExtra, nVar, pendingIntent);
        } else if (z && containsKey) {
            a(pendingIntent);
        }
        return 2;
    }

    @Override // android.app.Service
    public void onTaskRemoved(Intent intent) {
        super.onTaskRemoved(intent);
    }

    private void a(PendingIntent pendingIntent) {
        j remove;
        boolean isEmpty;
        synchronized (this.f3907b) {
            remove = this.f3908c.remove(pendingIntent);
            isEmpty = this.f3908c.isEmpty();
        }
        if (remove == null) {
            return;
        }
        try {
            a.a().a(remove);
        } catch (Exception e2) {
            Log.w("ScannerService", "Stopping scanning failed", e2);
        }
        if (isEmpty) {
            stopSelf();
        }
    }
}
