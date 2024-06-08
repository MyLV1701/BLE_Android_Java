package io.runtime.mcumgr.ble.callback;

import android.bluetooth.BluetoothDevice;
import io.runtime.mcumgr.response.McuMgrResponse;

/* loaded from: classes.dex */
public interface SmpCallback<T extends McuMgrResponse> {
    void onResponseReceived(BluetoothDevice bluetoothDevice, T t);
}
