package no.nordicsemi.android.dfu;

import android.bluetooth.BluetoothGatt;
import android.content.Intent;
import java.io.InputStream;

/* loaded from: classes.dex */
interface DfuService extends DfuCallback {
    boolean initialize(Intent intent, BluetoothGatt bluetoothGatt, @FileType int i, InputStream inputStream, InputStream inputStream2);

    boolean isClientCompatible(Intent intent, BluetoothGatt bluetoothGatt);

    void performDfu(Intent intent);

    void release();
}
