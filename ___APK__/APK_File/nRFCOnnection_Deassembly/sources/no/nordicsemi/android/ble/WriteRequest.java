package no.nordicsemi.android.ble;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import java.util.Arrays;
import no.nordicsemi.android.ble.Request;
import no.nordicsemi.android.ble.callback.BeforeCallback;
import no.nordicsemi.android.ble.callback.DataSentCallback;
import no.nordicsemi.android.ble.callback.FailCallback;
import no.nordicsemi.android.ble.callback.InvalidRequestCallback;
import no.nordicsemi.android.ble.callback.SuccessCallback;
import no.nordicsemi.android.ble.callback.WriteProgressCallback;
import no.nordicsemi.android.ble.data.Data;
import no.nordicsemi.android.ble.data.DataSplitter;
import no.nordicsemi.android.ble.data.DefaultMtuSplitter;

/* loaded from: classes.dex */
public final class WriteRequest extends SimpleValueRequest<DataSentCallback> implements Operation {
    private static final DataSplitter MTU_SPLITTER = new DefaultMtuSplitter();
    private boolean complete;
    private int count;
    private byte[] currentChunk;
    private final byte[] data;
    private DataSplitter dataSplitter;
    private byte[] nextChunk;
    private WriteProgressCallback progressCallback;
    private final int writeType;

    /* JADX INFO: Access modifiers changed from: package-private */
    public WriteRequest(Request.Type type) {
        this(type, null);
    }

    private static byte[] copy(byte[] bArr, int i, int i2) {
        if (bArr == null || i > bArr.length) {
            return null;
        }
        int min = Math.min(bArr.length - i, i2);
        byte[] bArr2 = new byte[min];
        System.arraycopy(bArr, i, bArr2, 0, min);
        return bArr2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void forceSplit() {
        if (this.dataSplitter == null) {
            split();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte[] getData(int i) {
        if (this.dataSplitter != null && this.data != null) {
            int i2 = this.writeType != 4 ? i - 3 : i - 12;
            byte[] bArr = this.nextChunk;
            if (bArr == null) {
                bArr = this.dataSplitter.chunk(this.data, this.count, i2);
            }
            if (bArr != null) {
                this.nextChunk = this.dataSplitter.chunk(this.data, this.count + 1, i2);
            }
            if (this.nextChunk == null) {
                this.complete = true;
            }
            this.currentChunk = bArr;
            return bArr;
        }
        this.complete = true;
        byte[] bArr2 = this.data;
        this.currentChunk = bArr2;
        return bArr2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getWriteType() {
        return this.writeType;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean hasMore() {
        return !this.complete;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean notifyPacketSent(BluetoothDevice bluetoothDevice, byte[] bArr) {
        T t;
        WriteProgressCallback writeProgressCallback = this.progressCallback;
        if (writeProgressCallback != null) {
            writeProgressCallback.onPacketSent(bluetoothDevice, bArr, this.count);
        }
        this.count++;
        if (this.complete && (t = this.valueCallback) != 0) {
            ((DataSentCallback) t).onDataSent(bluetoothDevice, new Data(this.data));
        }
        return Arrays.equals(bArr, this.currentChunk);
    }

    public WriteRequest split(DataSplitter dataSplitter) {
        this.dataSplitter = dataSplitter;
        this.progressCallback = null;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public WriteRequest(Request.Type type, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        super(type, bluetoothGattCharacteristic);
        this.count = 0;
        this.complete = false;
        this.data = null;
        this.writeType = 0;
        this.complete = true;
    }

    @Override // no.nordicsemi.android.ble.Request
    public WriteRequest before(BeforeCallback beforeCallback) {
        super.before(beforeCallback);
        return this;
    }

    @Override // no.nordicsemi.android.ble.Request
    public WriteRequest done(SuccessCallback successCallback) {
        super.done(successCallback);
        return this;
    }

    @Override // no.nordicsemi.android.ble.Request
    public WriteRequest fail(FailCallback failCallback) {
        super.fail(failCallback);
        return this;
    }

    @Override // no.nordicsemi.android.ble.Request
    public WriteRequest invalid(InvalidRequestCallback invalidRequestCallback) {
        super.invalid(invalidRequestCallback);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // no.nordicsemi.android.ble.Request
    public WriteRequest setManager(BleManager bleManager) {
        super.setManager(bleManager);
        return this;
    }

    @Override // no.nordicsemi.android.ble.SimpleValueRequest
    public WriteRequest with(DataSentCallback dataSentCallback) {
        super.with((WriteRequest) dataSentCallback);
        return this;
    }

    public WriteRequest split(DataSplitter dataSplitter, WriteProgressCallback writeProgressCallback) {
        this.dataSplitter = dataSplitter;
        this.progressCallback = writeProgressCallback;
        return this;
    }

    public WriteRequest split() {
        this.dataSplitter = MTU_SPLITTER;
        this.progressCallback = null;
        return this;
    }

    public WriteRequest split(WriteProgressCallback writeProgressCallback) {
        this.dataSplitter = MTU_SPLITTER;
        this.progressCallback = writeProgressCallback;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public WriteRequest(Request.Type type, BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr, int i, int i2, int i3) {
        super(type, bluetoothGattCharacteristic);
        this.count = 0;
        this.complete = false;
        this.data = copy(bArr, i, i2);
        this.writeType = i3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public WriteRequest(Request.Type type, BluetoothGattDescriptor bluetoothGattDescriptor, byte[] bArr, int i, int i2) {
        super(type, bluetoothGattDescriptor);
        this.count = 0;
        this.complete = false;
        this.data = copy(bArr, i, i2);
        this.writeType = 2;
    }
}
