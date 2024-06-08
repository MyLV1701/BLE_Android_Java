package no.nordicsemi.android.ble;

import android.bluetooth.BluetoothDevice;
import no.nordicsemi.android.ble.Request;
import no.nordicsemi.android.ble.callback.BeforeCallback;
import no.nordicsemi.android.ble.callback.FailCallback;
import no.nordicsemi.android.ble.callback.InvalidRequestCallback;
import no.nordicsemi.android.ble.callback.RssiCallback;
import no.nordicsemi.android.ble.callback.SuccessCallback;

/* loaded from: classes.dex */
public final class ReadRssiRequest extends SimpleValueRequest<RssiCallback> implements Operation {
    /* JADX INFO: Access modifiers changed from: package-private */
    public ReadRssiRequest(Request.Type type) {
        super(type);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void notifyRssiRead(BluetoothDevice bluetoothDevice, int i) {
        T t = this.valueCallback;
        if (t != 0) {
            ((RssiCallback) t).onRssiRead(bluetoothDevice, i);
        }
    }

    @Override // no.nordicsemi.android.ble.Request
    public ReadRssiRequest before(BeforeCallback beforeCallback) {
        super.before(beforeCallback);
        return this;
    }

    @Override // no.nordicsemi.android.ble.Request
    public ReadRssiRequest done(SuccessCallback successCallback) {
        super.done(successCallback);
        return this;
    }

    @Override // no.nordicsemi.android.ble.Request
    public ReadRssiRequest fail(FailCallback failCallback) {
        super.fail(failCallback);
        return this;
    }

    @Override // no.nordicsemi.android.ble.Request
    public ReadRssiRequest invalid(InvalidRequestCallback invalidRequestCallback) {
        super.invalid(invalidRequestCallback);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // no.nordicsemi.android.ble.Request
    public ReadRssiRequest setManager(BleManager bleManager) {
        super.setManager(bleManager);
        return this;
    }

    @Override // no.nordicsemi.android.ble.SimpleValueRequest
    public ReadRssiRequest with(RssiCallback rssiCallback) {
        super.with((ReadRssiRequest) rssiCallback);
        return this;
    }
}
