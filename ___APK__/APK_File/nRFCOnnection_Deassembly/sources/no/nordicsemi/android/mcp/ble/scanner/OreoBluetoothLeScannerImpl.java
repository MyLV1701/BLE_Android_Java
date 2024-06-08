package no.nordicsemi.android.mcp.ble.scanner;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.ScanSettings;
import android.content.Context;
import android.os.Build;
import no.nordicsemi.android.mcp.settings.SettingsFragment;

@TargetApi(26)
/* loaded from: classes.dex */
public class OreoBluetoothLeScannerImpl extends LollipopBluetoothLeScannerImpl {
    /* JADX INFO: Access modifiers changed from: package-private */
    public OreoBluetoothLeScannerImpl(Context context) {
        super(context);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // no.nordicsemi.android.mcp.ble.scanner.LollipopBluetoothLeScannerImpl
    public ScanSettings.Builder getScanSettingsBuilder(BluetoothAdapter bluetoothAdapter) {
        ScanSettings.Builder scanSettingsBuilder = super.getScanSettingsBuilder(bluetoothAdapter);
        if (Build.VERSION.SDK_INT >= 26) {
            scanSettingsBuilder.setLegacy(this.mPreferences.getBoolean(SettingsFragment.SETTINGS_SCANNING_COMPATIBILITY_MODE, false));
            scanSettingsBuilder.setPhy(255);
        }
        return scanSettingsBuilder;
    }
}
