package no.nordicsemi.android.mcp.ble.scanner;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.os.Build;
import android.preference.PreferenceManager;
import no.nordicsemi.android.mcp.settings.SettingsFragment;

/* loaded from: classes.dex */
public class BluetoothLeScannerCompatProvider {
    private BluetoothLeScannerCompatProvider() {
    }

    public static BluetoothLeScannerCompat getBluetoothScanner(Context context) {
        int parseInt = Integer.parseInt(PreferenceManager.getDefaultSharedPreferences(context).getString(SettingsFragment.SETTINGS_SCANNING_API, "18"));
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (Build.VERSION.SDK_INT >= 21 && parseInt >= 21 && defaultAdapter.getBluetoothLeScanner() != null) {
            if (Build.VERSION.SDK_INT >= 26) {
                return new OreoBluetoothLeScannerImpl(context);
            }
            return new LollipopBluetoothLeScannerImpl(context);
        }
        return new JBBluetoothLeScannerImpl(context);
    }
}
