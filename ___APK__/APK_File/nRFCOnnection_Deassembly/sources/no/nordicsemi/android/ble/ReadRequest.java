package no.nordicsemi.android.ble;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
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

/* loaded from: classes.dex */
public final class ReadRequest extends SimpleValueRequest<DataReceivedCallback> implements Operation {
    private DataStream buffer;
    private int count;
    private DataMerger dataMerger;
    private DataFilter filter;
    private ReadProgressCallback progressCallback;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ReadRequest(Request.Type type) {
        super(type);
        this.count = 0;
    }

    public <E extends ProfileReadResponse> E awaitValid(Class<E> cls) {
        E e2 = (E) await((Class) cls);
        if (e2.isValid()) {
            return e2;
        }
        throw new InvalidDataException(e2);
    }

    public ReadRequest filter(DataFilter dataFilter) {
        this.filter = dataFilter;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean hasMore() {
        return this.count > 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean matches(byte[] bArr) {
        DataFilter dataFilter = this.filter;
        return dataFilter == null || dataFilter.filter(bArr);
    }

    public ReadRequest merge(DataMerger dataMerger) {
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

    @Override // no.nordicsemi.android.ble.Request
    public ReadRequest before(BeforeCallback beforeCallback) {
        super.before(beforeCallback);
        return this;
    }

    @Override // no.nordicsemi.android.ble.Request
    public ReadRequest done(SuccessCallback successCallback) {
        super.done(successCallback);
        return this;
    }

    @Override // no.nordicsemi.android.ble.Request
    public ReadRequest fail(FailCallback failCallback) {
        super.fail(failCallback);
        return this;
    }

    @Override // no.nordicsemi.android.ble.Request
    public ReadRequest invalid(InvalidRequestCallback invalidRequestCallback) {
        super.invalid(invalidRequestCallback);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // no.nordicsemi.android.ble.Request
    public ReadRequest setManager(BleManager bleManager) {
        super.setManager(bleManager);
        return this;
    }

    @Override // no.nordicsemi.android.ble.SimpleValueRequest
    public ReadRequest with(DataReceivedCallback dataReceivedCallback) {
        super.with((ReadRequest) dataReceivedCallback);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ReadRequest(Request.Type type, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        super(type, bluetoothGattCharacteristic);
        this.count = 0;
    }

    public ReadRequest merge(DataMerger dataMerger, ReadProgressCallback readProgressCallback) {
        this.dataMerger = dataMerger;
        this.progressCallback = readProgressCallback;
        return this;
    }

    public <E extends ProfileReadResponse> E awaitValid(E e2) {
        await((ReadRequest) e2);
        if (e2.isValid()) {
            return e2;
        }
        throw new InvalidDataException(e2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ReadRequest(Request.Type type, BluetoothGattDescriptor bluetoothGattDescriptor) {
        super(type, bluetoothGattDescriptor);
        this.count = 0;
    }
}
