package no.nordicsemi.android.mcp.ble.scanner;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.Context;
import android.os.SystemClock;
import android.util.Log;
import java.util.List;
import no.nordicsemi.android.mcp.settings.SettingsFragment;

@TargetApi(21)
/* loaded from: classes.dex */
public class LollipopBluetoothLeScannerImpl extends BluetoothLeScannerCompat {
    private static final long REPORT_DELAY = 500;
    private final ScanCallback callback;

    /* JADX INFO: Access modifiers changed from: package-private */
    public LollipopBluetoothLeScannerImpl(Context context) {
        super(context);
        this.callback = new ScanCallback() { // from class: no.nordicsemi.android.mcp.ble.scanner.LollipopBluetoothLeScannerImpl.1
            private long lastTimestamp;

            @Override // android.bluetooth.le.ScanCallback
            public void onBatchScanResults(List<ScanResult> list) {
                long elapsedRealtime = SystemClock.elapsedRealtime();
                if (this.lastTimestamp < elapsedRealtime - 450) {
                    LollipopBluetoothLeScannerImpl.this.onDevicesDiscovered(list);
                }
                this.lastTimestamp = elapsedRealtime;
            }

            @Override // android.bluetooth.le.ScanCallback
            public void onScanFailed(int i) {
                Log.w("LPBluetoothScannerImpl", "Scan failed: " + i);
            }

            @Override // android.bluetooth.le.ScanCallback
            public void onScanResult(int i, ScanResult scanResult) {
                LollipopBluetoothLeScannerImpl.this.onDeviceDiscovered(scanResult);
            }
        };
    }

    private int getScanningMode() {
        try {
            return Integer.parseInt(this.mPreferences.getString(SettingsFragment.SETTINGS_SCANNING_MODE_LOLLIPOP, String.valueOf(2)));
        } catch (NumberFormatException unused) {
            return 2;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ScanSettings.Builder getScanSettingsBuilder(BluetoothAdapter bluetoothAdapter) {
        ScanSettings.Builder builder = new ScanSettings.Builder();
        if (bluetoothAdapter.isOffloadedScanBatchingSupported() && this.mPreferences.getBoolean(SettingsFragment.SETTINGS_SCANNING_BATCHING, false)) {
            builder.setReportDelay(REPORT_DELAY);
        }
        builder.setScanMode(getScanningMode());
        return builder;
    }

    @Override // no.nordicsemi.android.mcp.ble.scanner.BluetoothLeScannerCompat
    protected void startLeScanInternal() {
        BluetoothAdapter bluetoothAdapter = this.mBluetoothAdapter;
        bluetoothAdapter.getBluetoothLeScanner().startScan((List<ScanFilter>) null, getScanSettingsBuilder(bluetoothAdapter).build(), this.callback);
    }

    @Override // no.nordicsemi.android.mcp.ble.scanner.BluetoothLeScannerCompat
    protected void stopLeScanInternal() {
        this.mBluetoothAdapter.getBluetoothLeScanner().stopScan(this.callback);
    }
}
