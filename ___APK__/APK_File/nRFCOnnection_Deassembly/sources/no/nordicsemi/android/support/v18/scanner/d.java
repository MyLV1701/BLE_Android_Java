package no.nordicsemi.android.support.v18.scanner;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.ScanSettings;

/* JADX INFO: Access modifiers changed from: package-private */
@TargetApi(23)
/* loaded from: classes.dex */
public class d extends c {
    @Override // no.nordicsemi.android.support.v18.scanner.c
    ScanSettings a(BluetoothAdapter bluetoothAdapter, n nVar, boolean z) {
        ScanSettings.Builder builder = new ScanSettings.Builder();
        if (z || (bluetoothAdapter.isOffloadedScanBatchingSupported() && nVar.p())) {
            builder.setReportDelay(nVar.n());
        }
        if (z || nVar.q()) {
            builder.setCallbackType(nVar.e()).setMatchMode(nVar.i()).setNumOfMatches(nVar.j());
        }
        builder.setScanMode(nVar.o());
        return builder.build();
    }
}
