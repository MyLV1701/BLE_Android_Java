package no.nordicsemi.android.ble;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGattCharacteristic;
import no.nordicsemi.android.ble.Request;
import no.nordicsemi.android.ble.callback.BeforeCallback;
import no.nordicsemi.android.ble.callback.DataReceivedCallback;
import no.nordicsemi.android.ble.callback.FailCallback;
import no.nordicsemi.android.ble.callback.InvalidRequestCallback;
import no.nordicsemi.android.ble.callback.ReadProgressCallback;
import no.nordicsemi.android.ble.callback.SuccessCallback;
import no.nordicsemi.android.ble.callback.profile.ProfileReadResponse;
import no.nordicsemi.android.ble.data.Data;
import no.nordicsemi.android.ble.data.DataFilter;
import no.nordicsemi.android.ble.data.DataMerger;
import no.nordicsemi.android.ble.data.DataStream;
import no.nordicsemi.android.ble.exception.InvalidDataException;
import no.nordicsemi.android.ble.exception.RequestFailedException;

/* loaded from: classes.dex */
public final class WaitForValueChangedRequest extends TimeoutableValueRequest<DataReceivedCallback> implements Operation {
    static final int NOT_STARTED = -123456;
    static final int STARTED = -123455;
    private boolean bluetoothDisabled;
    private DataStream buffer;
    private int count;
    private DataMerger dataMerger;
    private boolean deviceDisconnected;
    private DataFilter filter;
    private ReadProgressCallback progressCallback;
    private Request trigger;
    private int triggerStatus;

    /* JADX INFO: Access modifiers changed from: package-private */
    public WaitForValueChangedRequest(Request.Type type, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        super(type, bluetoothGattCharacteristic);
        this.triggerStatus = 0;
        this.count = 0;
    }

    public /* synthetic */ void a(BluetoothDevice bluetoothDevice, int i) {
        this.triggerStatus = i;
        this.syncLock.open();
        notifyFail(bluetoothDevice, i);
    }

    public <E extends ProfileReadResponse> E awaitValid(E e2) {
        E e3 = (E) await((WaitForValueChangedRequest) e2);
        if (e3 == null || e3.isValid()) {
            return e3;
        }
        throw new InvalidDataException(e3);
    }

    public /* synthetic */ void b(BluetoothDevice bluetoothDevice) {
        this.triggerStatus = STARTED;
    }

    public /* synthetic */ void c(BluetoothDevice bluetoothDevice) {
        this.triggerStatus = 0;
    }

    public WaitForValueChangedRequest filter(DataFilter dataFilter) {
        this.filter = dataFilter;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Request getTrigger() {
        return this.trigger;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean hasMore() {
        return this.count > 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isTriggerCompleteOrNull() {
        return this.triggerStatus != STARTED;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isTriggerPending() {
        return this.triggerStatus == NOT_STARTED;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean matches(byte[] bArr) {
        DataFilter dataFilter = this.filter;
        return dataFilter == null || dataFilter.filter(bArr);
    }

    public WaitForValueChangedRequest merge(DataMerger dataMerger) {
        this.dataMerger = dataMerger;
        this.progressCallback = null;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void notifyValueChanged(BluetoothDevice bluetoothDevice, byte[] bArr) {
        DataReceivedCallback dataReceivedCallback = (DataReceivedCallback) this.valueCallback;
        if (dataReceivedCallback == null) {
            return;
        }
        if (this.dataMerger == null) {
            dataReceivedCallback.onDataReceived(bluetoothDevice, new Data(bArr));
            return;
        }
        ReadProgressCallback readProgressCallback = this.progressCallback;
        if (readProgressCallback != null) {
            readProgressCallback.onPacketReceived(bluetoothDevice, bArr, this.count);
        }
        if (this.buffer == null) {
            this.buffer = new DataStream();
        }
        DataMerger dataMerger = this.dataMerger;
        DataStream dataStream = this.buffer;
        int i = this.count;
        this.count = i + 1;
        if (dataMerger.merge(dataStream, bArr, i)) {
            dataReceivedCallback.onDataReceived(bluetoothDevice, this.buffer.toData());
            this.buffer = null;
            this.count = 0;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public WaitForValueChangedRequest trigger(Operation operation) {
        if (operation instanceof Request) {
            this.trigger = (Request) operation;
            this.triggerStatus = NOT_STARTED;
            this.trigger.internalBefore(new BeforeCallback() { // from class: no.nordicsemi.android.ble.z
                @Override // no.nordicsemi.android.ble.callback.BeforeCallback
                public final void onRequestStarted(BluetoothDevice bluetoothDevice) {
                    WaitForValueChangedRequest.this.b(bluetoothDevice);
                }
            });
            this.trigger.internalSuccess(new SuccessCallback() { // from class: no.nordicsemi.android.ble.a0
                @Override // no.nordicsemi.android.ble.callback.SuccessCallback
                public final void onRequestCompleted(BluetoothDevice bluetoothDevice) {
                    WaitForValueChangedRequest.this.c(bluetoothDevice);
                }
            });
            this.trigger.internalFail(new FailCallback() { // from class: no.nordicsemi.android.ble.b0
                @Override // no.nordicsemi.android.ble.callback.FailCallback
                public final void onRequestFailed(BluetoothDevice bluetoothDevice, int i) {
                    WaitForValueChangedRequest.this.a(bluetoothDevice, i);
                }
            });
        }
        return this;
    }

    @Override // no.nordicsemi.android.ble.TimeoutableValueRequest
    public <E extends DataReceivedCallback> E await(E e2) {
        Request.assertNotMainThread();
        try {
            if (this.trigger != null && this.trigger.enqueued) {
                throw new IllegalStateException("Trigger request already enqueued");
            }
            super.await((WaitForValueChangedRequest) e2);
            return e2;
        } catch (RequestFailedException e3) {
            int i = this.triggerStatus;
            if (i != 0) {
                throw new RequestFailedException(this.trigger, i);
            }
            throw e3;
        }
    }

    @Override // no.nordicsemi.android.ble.Request
    public WaitForValueChangedRequest before(BeforeCallback beforeCallback) {
        super.before(beforeCallback);
        return this;
    }

    @Override // no.nordicsemi.android.ble.Request
    public WaitForValueChangedRequest done(SuccessCallback successCallback) {
        super.done(successCallback);
        return this;
    }

    @Override // no.nordicsemi.android.ble.Request
    public WaitForValueChangedRequest fail(FailCallback failCallback) {
        super.fail(failCallback);
        return this;
    }

    @Override // no.nordicsemi.android.ble.Request
    public WaitForValueChangedRequest invalid(InvalidRequestCallback invalidRequestCallback) {
        super.invalid(invalidRequestCallback);
        return this;
    }

    @Override // no.nordicsemi.android.ble.TimeoutableValueRequest
    public WaitForValueChangedRequest with(DataReceivedCallback dataReceivedCallback) {
        super.with((WaitForValueChangedRequest) dataReceivedCallback);
        return this;
    }

    public WaitForValueChangedRequest merge(DataMerger dataMerger, ReadProgressCallback readProgressCallback) {
        this.dataMerger = dataMerger;
        this.progressCallback = readProgressCallback;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // no.nordicsemi.android.ble.TimeoutableRequest, no.nordicsemi.android.ble.Request
    public WaitForValueChangedRequest setManager(BleManager bleManager) {
        super.setManager(bleManager);
        return this;
    }

    @Override // no.nordicsemi.android.ble.TimeoutableValueRequest, no.nordicsemi.android.ble.TimeoutableRequest
    public WaitForValueChangedRequest timeout(long j) {
        super.timeout(j);
        return this;
    }

    public <E extends ProfileReadResponse> E awaitValid(Class<E> cls) {
        E e2 = (E) await((Class) cls);
        if (e2 == null || e2.isValid()) {
            return e2;
        }
        throw new InvalidDataException(e2);
    }

    @Deprecated
    public <E extends ProfileReadResponse> E awaitValid(Class<E> cls, long j) {
        return (E) timeout(j).awaitValid(cls);
    }

    @Deprecated
    public <E extends ProfileReadResponse> E awaitValid(E e2, long j) {
        return (E) timeout(j).awaitValid((WaitForValueChangedRequest) e2);
    }
}
