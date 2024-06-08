package io.runtime.mcumgr.ble.callback;

import android.bluetooth.BluetoothDevice;
import io.runtime.mcumgr.McuMgrScheme;
import io.runtime.mcumgr.response.McuMgrResponse;
import no.nordicsemi.android.ble.callback.profile.ProfileDataCallback;
import no.nordicsemi.android.ble.callback.profile.a;
import no.nordicsemi.android.ble.data.Data;

/* loaded from: classes.dex */
public abstract class SmpDataCallback<T extends McuMgrResponse> implements ProfileDataCallback, SmpCallback<T> {
    private final Class<T> responseType;

    /* JADX INFO: Access modifiers changed from: protected */
    public SmpDataCallback(Class<T> cls) {
        this.responseType = cls;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // no.nordicsemi.android.ble.callback.DataReceivedCallback
    public void onDataReceived(BluetoothDevice bluetoothDevice, Data data) {
        try {
            onResponseReceived(bluetoothDevice, McuMgrResponse.buildResponse(McuMgrScheme.BLE, data.getValue(), this.responseType));
        } catch (Exception unused) {
            onInvalidDataReceived(bluetoothDevice, data);
        }
    }

    @Override // no.nordicsemi.android.ble.callback.profile.ProfileDataCallback
    public /* synthetic */ void onInvalidDataReceived(BluetoothDevice bluetoothDevice, Data data) {
        a.$default$onInvalidDataReceived(this, bluetoothDevice, data);
    }
}
