package no.nordicsemi.android.ble;

import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import no.nordicsemi.android.ble.Request;
import no.nordicsemi.android.ble.callback.FailCallback;
import no.nordicsemi.android.ble.callback.SuccessCallback;
import no.nordicsemi.android.ble.exception.BluetoothDisabledException;
import no.nordicsemi.android.ble.exception.DeviceDisconnectedException;
import no.nordicsemi.android.ble.exception.InvalidRequestException;
import no.nordicsemi.android.ble.exception.RequestFailedException;

/* loaded from: classes.dex */
public class SimpleRequest extends Request {
    /* JADX INFO: Access modifiers changed from: package-private */
    public SimpleRequest(Request.Type type) {
        super(type);
    }

    public final void await() {
        Request.assertNotMainThread();
        SuccessCallback successCallback = this.successCallback;
        FailCallback failCallback = this.failCallback;
        try {
            this.syncLock.close();
            Request.RequestCallback requestCallback = new Request.RequestCallback();
            done(requestCallback).fail(requestCallback).invalid(requestCallback).enqueue();
            this.syncLock.block();
            if (requestCallback.isSuccess()) {
                return;
            }
            if (requestCallback.status != -1) {
                if (requestCallback.status != -100) {
                    if (requestCallback.status == -1000000) {
                        throw new InvalidRequestException(this);
                    }
                    throw new RequestFailedException(this, requestCallback.status);
                }
                throw new BluetoothDisabledException();
            }
            throw new DeviceDisconnectedException();
        } finally {
            this.successCallback = successCallback;
            this.failCallback = failCallback;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SimpleRequest(Request.Type type, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        super(type, bluetoothGattCharacteristic);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SimpleRequest(Request.Type type, BluetoothGattDescriptor bluetoothGattDescriptor) {
        super(type, bluetoothGattDescriptor);
    }
}
