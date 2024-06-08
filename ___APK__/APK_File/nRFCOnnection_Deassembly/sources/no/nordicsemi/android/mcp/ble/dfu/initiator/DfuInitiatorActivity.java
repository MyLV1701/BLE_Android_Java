package no.nordicsemi.android.mcp.ble.dfu.initiator;

import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.e;
import no.nordicsemi.android.dfu.DfuBaseService;
import no.nordicsemi.android.mcp.ble.dfu.DfuService;
import no.nordicsemi.android.mcp.ble.dfu.initiator.ScannerFragment;

/* loaded from: classes.dex */
public class DfuInitiatorActivity extends e implements ScannerFragment.OnDeviceSelectedListener {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.e, androidx.fragment.app.d, androidx.activity.ComponentActivity, androidx.core.app.d, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (!getIntent().hasExtra(DfuBaseService.EXTRA_FILE_PATH)) {
            finish();
        }
        if (bundle == null) {
            ScannerFragment.getInstance(null).show(getSupportFragmentManager(), (String) null);
        }
    }

    @Override // no.nordicsemi.android.mcp.ble.dfu.initiator.ScannerFragment.OnDeviceSelectedListener
    public void onDeviceSelected(BluetoothDevice bluetoothDevice) {
        Intent intent = getIntent();
        String stringExtra = intent.getStringExtra(DfuBaseService.EXTRA_DEVICE_NAME);
        String stringExtra2 = intent.getStringExtra(DfuBaseService.EXTRA_FILE_PATH);
        String stringExtra3 = intent.getStringExtra(DfuBaseService.EXTRA_INIT_FILE_PATH);
        String address = bluetoothDevice.getAddress();
        if (stringExtra == null) {
            stringExtra = bluetoothDevice.getName();
        }
        int intExtra = intent.getIntExtra(DfuBaseService.EXTRA_FILE_TYPE, 0);
        Intent intent2 = new Intent(this, (Class<?>) DfuService.class);
        intent2.putExtra(DfuBaseService.EXTRA_DEVICE_ADDRESS, address);
        intent2.putExtra(DfuBaseService.EXTRA_DEVICE_NAME, stringExtra);
        intent2.putExtra(DfuBaseService.EXTRA_FILE_TYPE, intExtra);
        intent2.putExtra(DfuBaseService.EXTRA_FILE_PATH, stringExtra2);
        if (intent.hasExtra(DfuBaseService.EXTRA_INIT_FILE_PATH)) {
            intent2.putExtra(DfuBaseService.EXTRA_INIT_FILE_PATH, stringExtra3);
        }
        startService(intent2);
        finish();
    }

    @Override // no.nordicsemi.android.mcp.ble.dfu.initiator.ScannerFragment.OnDeviceSelectedListener
    public void onDialogCanceled() {
        finish();
    }
}
