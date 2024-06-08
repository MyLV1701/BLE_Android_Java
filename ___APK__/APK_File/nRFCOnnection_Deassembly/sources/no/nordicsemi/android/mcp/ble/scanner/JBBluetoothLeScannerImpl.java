package no.nordicsemi.android.mcp.ble.scanner;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.os.SystemClock;

/* loaded from: classes.dex */
public class JBBluetoothLeScannerImpl extends BluetoothLeScannerCompat implements BluetoothAdapter.LeScanCallback {
    /* JADX INFO: Access modifiers changed from: package-private */
    public JBBluetoothLeScannerImpl(Context context) {
        super(context);
    }

    @Override // android.bluetooth.BluetoothAdapter.LeScanCallback
    public void onLeScan(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
        onDeviceDiscovered(bluetoothDevice, i, bArr, SystemClock.elapsedRealtimeNanos());
    }

    @Override // no.nordicsemi.android.mcp.ble.scanner.BluetoothLeScannerCompat
    protected void startLeScanInternal() {
        BluetoothAdapter bluetoothAdapter = this.mBluetoothAdapter;
        if (bluetoothAdapter != null) {
            bluetoothAdapter.startLeScan(this);
        }
    }

    @Override // no.nordicsemi.android.mcp.ble.scanner.BluetoothLeScannerCompat
    protected void stopLeScanInternal() {
        BluetoothAdapter bluetoothAdapter = this.mBluetoothAdapter;
        if (bluetoothAdapter != null) {
            bluetoothAdapter.stopLeScan(this);
        }
    }
}
