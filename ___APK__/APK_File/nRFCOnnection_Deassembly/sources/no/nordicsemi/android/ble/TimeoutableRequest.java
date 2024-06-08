package no.nordicsemi.android.ble;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.os.Handler;
import no.nordicsemi.android.ble.Request;
import no.nordicsemi.android.ble.callback.FailCallback;
import no.nordicsemi.android.ble.callback.SuccessCallback;
import no.nordicsemi.android.ble.exception.BluetoothDisabledException;
import no.nordicsemi.android.ble.exception.DeviceDisconnectedException;
import no.nordicsemi.android.ble.exception.InvalidRequestException;
import no.nordicsemi.android.ble.exception.RequestFailedException;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class TimeoutableRequest extends Request {
    private Handler handler;
    protected long timeout;
    private Runnable timeoutCallback;
    private TimeoutHandler timeoutHandler;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TimeoutableRequest(Request.Type type) {
        super(type);
    }

    public /* synthetic */ void a(BluetoothDevice bluetoothDevice) {
        this.timeoutCallback = null;
        if (this.finished) {
            return;
        }
        notifyFail(bluetoothDevice, -5);
        this.timeoutHandler.onRequestTimeout(this);
    }

    public void await() {
        Request.assertNotMainThread();
        SuccessCallback successCallback = this.successCallback;
        FailCallback failCallback = this.failCallback;
        try {
            this.syncLock.close();
            Request.RequestCallback requestCallback = new Request.RequestCallback();
            done(requestCallback).fail(requestCallback).invalid(requestCallback).enqueue();
            if (this.syncLock.block(this.timeout)) {
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
            }
            throw new InterruptedException();
        } finally {
            this.successCallback = successCallback;
            this.failCallback = failCallback;
        }
    }

    @Override // no.nordicsemi.android.ble.Request
    public void enqueue() {
        super.enqueue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // no.nordicsemi.android.ble.Request
    public void notifyFail(BluetoothDevice bluetoothDevice, int i) {
        if (!this.finished) {
            this.handler.removeCallbacks(this.timeoutCallback);
            this.timeoutCallback = null;
        }
        super.notifyFail(bluetoothDevice, i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // no.nordicsemi.android.ble.Request
    public void notifyInvalidRequest() {
        if (!this.finished) {
            this.handler.removeCallbacks(this.timeoutCallback);
            this.timeoutCallback = null;
        }
        super.notifyInvalidRequest();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // no.nordicsemi.android.ble.Request
    public void notifyStarted(final BluetoothDevice bluetoothDevice) {
        long j = this.timeout;
        if (j > 0) {
            this.timeoutCallback = new Runnable() { // from class: no.nordicsemi.android.ble.y
                @Override // java.lang.Runnable
                public final void run() {
                    TimeoutableRequest.this.a(bluetoothDevice);
                }
            };
            this.handler.postDelayed(this.timeoutCallback, j);
        }
        super.notifyStarted(bluetoothDevice);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // no.nordicsemi.android.ble.Request
    public void notifySuccess(BluetoothDevice bluetoothDevice) {
        if (!this.finished) {
            this.handler.removeCallbacks(this.timeoutCallback);
            this.timeoutCallback = null;
        }
        super.notifySuccess(bluetoothDevice);
    }

    public TimeoutableRequest timeout(long j) {
        if (this.timeoutCallback == null) {
            this.timeout = j;
            return this;
        }
        throw new IllegalStateException("Request already started");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public TimeoutableRequest(Request.Type type, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        super(type, bluetoothGattCharacteristic);
    }

    @Deprecated
    public final void enqueue(long j) {
        timeout(j).enqueue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // no.nordicsemi.android.ble.Request
    public TimeoutableRequest setManager(BleManager bleManager) {
        super.setManager(bleManager);
        this.handler = bleManager.mHandler;
        this.timeoutHandler = bleManager;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public TimeoutableRequest(Request.Type type, BluetoothGattDescriptor bluetoothGattDescriptor) {
        super(type, bluetoothGattDescriptor);
    }

    @Deprecated
    public final void await(long j) {
        timeout(j).await();
    }
}
