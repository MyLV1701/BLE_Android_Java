package no.nordicsemi.android.support.v18.scanner;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
class i extends j {

    /* renamed from: a, reason: collision with root package name */
    private final PendingIntent f3943a;

    /* renamed from: b, reason: collision with root package name */
    private Context f3944b;

    /* renamed from: c, reason: collision with root package name */
    private Context f3945c;

    /* renamed from: d, reason: collision with root package name */
    private long f3946d;

    /* renamed from: e, reason: collision with root package name */
    private long f3947e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public i(PendingIntent pendingIntent, n nVar) {
        this.f3943a = pendingIntent;
        this.f3947e = nVar.n();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(Context context) {
        this.f3944b = context;
    }

    @Override // no.nordicsemi.android.support.v18.scanner.j
    public void onBatchScanResults(List<m> list) {
        Context context = this.f3944b;
        if (context == null) {
            context = this.f3945c;
        }
        if (context == null) {
            return;
        }
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (this.f3946d > (elapsedRealtime - this.f3947e) + 5) {
            return;
        }
        this.f3946d = elapsedRealtime;
        try {
            Intent intent = new Intent();
            intent.putExtra("android.bluetooth.le.extra.CALLBACK_TYPE", 1);
            intent.putParcelableArrayListExtra("android.bluetooth.le.extra.LIST_SCAN_RESULT", new ArrayList<>(list));
            intent.setExtrasClassLoader(m.class.getClassLoader());
            this.f3943a.send(context, 0, intent);
        } catch (PendingIntent.CanceledException unused) {
        }
    }

    @Override // no.nordicsemi.android.support.v18.scanner.j
    public void onScanFailed(int i) {
        Context context = this.f3944b;
        if (context == null) {
            context = this.f3945c;
        }
        if (context == null) {
            return;
        }
        try {
            Intent intent = new Intent();
            intent.putExtra("android.bluetooth.le.extra.ERROR_CODE", i);
            this.f3943a.send(context, 0, intent);
        } catch (PendingIntent.CanceledException unused) {
        }
    }

    @Override // no.nordicsemi.android.support.v18.scanner.j
    public void onScanResult(int i, m mVar) {
        Context context = this.f3944b;
        if (context == null) {
            context = this.f3945c;
        }
        if (context == null) {
            return;
        }
        try {
            Intent intent = new Intent();
            intent.putExtra("android.bluetooth.le.extra.CALLBACK_TYPE", i);
            intent.putParcelableArrayListExtra("android.bluetooth.le.extra.LIST_SCAN_RESULT", new ArrayList<>(Collections.singletonList(mVar)));
            this.f3943a.send(context, 0, intent);
        } catch (PendingIntent.CanceledException unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public i(PendingIntent pendingIntent, n nVar, Service service) {
        this.f3943a = pendingIntent;
        this.f3947e = nVar.n();
        this.f3945c = service;
    }
}
