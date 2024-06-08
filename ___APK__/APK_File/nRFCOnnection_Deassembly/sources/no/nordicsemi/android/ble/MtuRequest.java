package no.nordicsemi.android.ble;

import android.bluetooth.BluetoothDevice;
import no.nordicsemi.android.ble.Request;
import no.nordicsemi.android.ble.callback.BeforeCallback;
import no.nordicsemi.android.ble.callback.FailCallback;
import no.nordicsemi.android.ble.callback.InvalidRequestCallback;
import no.nordicsemi.android.ble.callback.MtuCallback;
import no.nordicsemi.android.ble.callback.SuccessCallback;

/* loaded from: classes.dex */
public final class MtuRequest extends SimpleValueRequest<MtuCallback> implements Operation {
    private final int value;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MtuRequest(Request.Type type, int i) {
        super(type);
        int i2 = i >= 23 ? i : 23;
        this.value = i2 > 517 ? 517 : i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getRequiredMtu() {
        return this.value;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void notifyMtuChanged(BluetoothDevice bluetoothDevice, int i) {
        T t = this.valueCallback;
        if (t != 0) {
            ((MtuCallback) t).onMtuChanged(bluetoothDevice, i);
        }
    }

    @Override // no.nordicsemi.android.ble.Request
    public MtuRequest before(BeforeCallback beforeCallback) {
        super.before(beforeCallback);
        return this;
    }

    @Override // no.nordicsemi.android.ble.Request
    public MtuRequest done(SuccessCallback successCallback) {
        super.done(successCallback);
        return this;
    }

    @Override // no.nordicsemi.android.ble.Request
    public MtuRequest fail(FailCallback failCallback) {
        super.fail(failCallback);
        return this;
    }

    @Override // no.nordicsemi.android.ble.Request
    public MtuRequest invalid(InvalidRequestCallback invalidRequestCallback) {
        super.invalid(invalidRequestCallback);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // no.nordicsemi.android.ble.Request
    public MtuRequest setManager(BleManager bleManager) {
        super.setManager(bleManager);
        return this;
    }

    @Override // no.nordicsemi.android.ble.SimpleValueRequest
    public MtuRequest with(MtuCallback mtuCallback) {
        super.with((MtuRequest) mtuCallback);
        return this;
    }
}
