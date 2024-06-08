package io.runtime.mcumgr.ble.callback;

import android.bluetooth.BluetoothDevice;
import io.runtime.mcumgr.response.McuMgrResponse;
import no.nordicsemi.android.ble.data.Data;

/* loaded from: classes.dex */
public final class SmpResponse<T extends McuMgrResponse> extends SmpDataCallback<T> {
    private Data data;
    private T response;
    private boolean valid;

    public SmpResponse(Class<T> cls) {
        super(cls);
    }

    public Data getRawData() {
        return this.data;
    }

    public T getResponse() {
        return this.response;
    }

    public boolean isValid() {
        return this.valid;
    }

    @Override // io.runtime.mcumgr.ble.callback.SmpDataCallback, no.nordicsemi.android.ble.callback.DataReceivedCallback
    public void onDataReceived(BluetoothDevice bluetoothDevice, Data data) {
        this.data = data;
        super.onDataReceived(bluetoothDevice, data);
    }

    @Override // io.runtime.mcumgr.ble.callback.SmpDataCallback, no.nordicsemi.android.ble.callback.profile.ProfileDataCallback
    public void onInvalidDataReceived(BluetoothDevice bluetoothDevice, Data data) {
        this.valid = false;
    }

    @Override // io.runtime.mcumgr.ble.callback.SmpCallback
    public void onResponseReceived(BluetoothDevice bluetoothDevice, T t) {
        this.response = t;
        this.valid = true;
    }
}
