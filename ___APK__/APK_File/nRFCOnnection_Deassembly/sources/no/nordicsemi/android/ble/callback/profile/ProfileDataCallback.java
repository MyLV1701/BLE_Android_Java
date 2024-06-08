package no.nordicsemi.android.ble.callback.profile;

import android.bluetooth.BluetoothDevice;
import no.nordicsemi.android.ble.callback.DataReceivedCallback;
import no.nordicsemi.android.ble.data.Data;

/* loaded from: classes.dex */
public interface ProfileDataCallback extends DataReceivedCallback {
    void onInvalidDataReceived(BluetoothDevice bluetoothDevice, Data data);
}
