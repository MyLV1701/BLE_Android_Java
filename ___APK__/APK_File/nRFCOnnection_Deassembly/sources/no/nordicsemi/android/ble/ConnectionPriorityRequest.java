package no.nordicsemi.android.ble;

import android.bluetooth.BluetoothDevice;
import no.nordicsemi.android.ble.Request;
import no.nordicsemi.android.ble.callback.BeforeCallback;
import no.nordicsemi.android.ble.callback.ConnectionPriorityCallback;
import no.nordicsemi.android.ble.callback.FailCallback;
import no.nordicsemi.android.ble.callback.InvalidRequestCallback;
import no.nordicsemi.android.ble.callback.SuccessCallback;

/* loaded from: classes.dex */
public final class ConnectionPriorityRequest extends SimpleValueRequest<ConnectionPriorityCallback> implements Operation {
    public static final int CONNECTION_PRIORITY_BALANCED = 0;
    public static final int CONNECTION_PRIORITY_HIGH = 1;
    public static final int CONNECTION_PRIORITY_LOW_POWER = 2;
    private final int value;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ConnectionPriorityRequest(Request.Type type, int i) {
        super(type);
        this.value = (i < 0 || i > 2) ? 0 : i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getRequiredPriority() {
        return this.value;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void notifyConnectionPriorityChanged(BluetoothDevice bluetoothDevice, int i, int i2, int i3) {
        T t = this.valueCallback;
        if (t != 0) {
            ((ConnectionPriorityCallback) t).onConnectionUpdated(bluetoothDevice, i, i2, i3);
        }
    }

    @Override // no.nordicsemi.android.ble.Request
    public ConnectionPriorityRequest before(BeforeCallback beforeCallback) {
        super.before(beforeCallback);
        return this;
    }

    @Override // no.nordicsemi.android.ble.Request
    public ConnectionPriorityRequest done(SuccessCallback successCallback) {
        super.done(successCallback);
        return this;
    }

    @Override // no.nordicsemi.android.ble.Request
    public ConnectionPriorityRequest fail(FailCallback failCallback) {
        super.fail(failCallback);
        return this;
    }

    @Override // no.nordicsemi.android.ble.Request
    public ConnectionPriorityRequest invalid(InvalidRequestCallback invalidRequestCallback) {
        super.invalid(invalidRequestCallback);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // no.nordicsemi.android.ble.Request
    public ConnectionPriorityRequest setManager(BleManager bleManager) {
        super.setManager(bleManager);
        return this;
    }

    @Override // no.nordicsemi.android.ble.SimpleValueRequest
    public ConnectionPriorityRequest with(ConnectionPriorityCallback connectionPriorityCallback) {
        super.with((ConnectionPriorityRequest) connectionPriorityCallback);
        return this;
    }

    @Override // no.nordicsemi.android.ble.SimpleValueRequest
    public <E extends ConnectionPriorityCallback> E await(Class<E> cls) {
        return (E) super.await((Class) cls);
    }

    @Override // no.nordicsemi.android.ble.SimpleValueRequest
    public <E extends ConnectionPriorityCallback> E await(E e2) {
        return (E) super.await((ConnectionPriorityRequest) e2);
    }
}
