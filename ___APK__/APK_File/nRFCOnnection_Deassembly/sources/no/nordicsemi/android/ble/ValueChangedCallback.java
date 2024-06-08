package no.nordicsemi.android.ble;

import android.bluetooth.BluetoothDevice;
import no.nordicsemi.android.ble.callback.DataReceivedCallback;
import no.nordicsemi.android.ble.callback.ReadProgressCallback;
import no.nordicsemi.android.ble.data.Data;
import no.nordicsemi.android.ble.data.DataFilter;
import no.nordicsemi.android.ble.data.DataMerger;
import no.nordicsemi.android.ble.data.DataStream;

/* loaded from: classes.dex */
public class ValueChangedCallback {
    private DataStream buffer;
    private int count = 0;
    private DataMerger dataMerger;
    private DataFilter filter;
    private ReadProgressCallback progressCallback;
    private DataReceivedCallback valueCallback;

    public ValueChangedCallback filter(DataFilter dataFilter) {
        this.filter = dataFilter;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ValueChangedCallback free() {
        this.valueCallback = null;
        this.dataMerger = null;
        this.progressCallback = null;
        this.buffer = null;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean matches(byte[] bArr) {
        DataFilter dataFilter = this.filter;
        return dataFilter == null || dataFilter.filter(bArr);
    }

    public ValueChangedCallback merge(DataMerger dataMerger) {
        this.dataMerger = dataMerger;
        this.progressCallback = null;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void notifyValueChanged(BluetoothDevice bluetoothDevice, byte[] bArr) {
        DataReceivedCallback dataReceivedCallback = this.valueCallback;
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

    public ValueChangedCallback with(DataReceivedCallback dataReceivedCallback) {
        this.valueCallback = dataReceivedCallback;
        return this;
    }

    public ValueChangedCallback merge(DataMerger dataMerger, ReadProgressCallback readProgressCallback) {
        this.dataMerger = dataMerger;
        this.progressCallback = readProgressCallback;
        return this;
    }
}
