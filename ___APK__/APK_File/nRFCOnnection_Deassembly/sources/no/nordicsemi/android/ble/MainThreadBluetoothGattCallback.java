package no.nordicsemi.android.ble;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.Keep;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class MainThreadBluetoothGattCallback extends BluetoothGattCallback {
    private Handler mHandler;

    private void runOnUiThread(Runnable runnable) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            this.mHandler.post(runnable);
        } else {
            runnable.run();
        }
    }

    @Override // android.bluetooth.BluetoothGattCallback
    public final void onCharacteristicChanged(final BluetoothGatt bluetoothGatt, final BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        final byte[] value = bluetoothGattCharacteristic.getValue();
        runOnUiThread(new Runnable() { // from class: no.nordicsemi.android.ble.n
            @Override // java.lang.Runnable
            public final void run() {
                MainThreadBluetoothGattCallback.this.a(bluetoothGatt, bluetoothGattCharacteristic, value);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: onCharacteristicChangedSafe, reason: merged with bridge method [inline-methods] */
    public abstract void a(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr);

    @Override // android.bluetooth.BluetoothGattCallback
    public final void onCharacteristicRead(final BluetoothGatt bluetoothGatt, final BluetoothGattCharacteristic bluetoothGattCharacteristic, final int i) {
        final byte[] value = bluetoothGattCharacteristic.getValue();
        runOnUiThread(new Runnable() { // from class: no.nordicsemi.android.ble.p
            @Override // java.lang.Runnable
            public final void run() {
                MainThreadBluetoothGattCallback.this.a(bluetoothGatt, bluetoothGattCharacteristic, value, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: onCharacteristicReadSafe, reason: merged with bridge method [inline-methods] */
    public abstract void a(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr, int i);

    @Override // android.bluetooth.BluetoothGattCallback
    public final void onCharacteristicWrite(final BluetoothGatt bluetoothGatt, final BluetoothGattCharacteristic bluetoothGattCharacteristic, final int i) {
        final byte[] value = bluetoothGattCharacteristic.getValue();
        runOnUiThread(new Runnable() { // from class: no.nordicsemi.android.ble.r
            @Override // java.lang.Runnable
            public final void run() {
                MainThreadBluetoothGattCallback.this.b(bluetoothGatt, bluetoothGattCharacteristic, value, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: onCharacteristicWriteSafe, reason: merged with bridge method [inline-methods] */
    public abstract void b(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr, int i);

    @Override // android.bluetooth.BluetoothGattCallback
    public final void onConnectionStateChange(final BluetoothGatt bluetoothGatt, final int i, final int i2) {
        runOnUiThread(new Runnable() { // from class: no.nordicsemi.android.ble.v
            @Override // java.lang.Runnable
            public final void run() {
                MainThreadBluetoothGattCallback.this.a(bluetoothGatt, i, i2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: onConnectionStateChangeSafe, reason: merged with bridge method [inline-methods] */
    public abstract void a(BluetoothGatt bluetoothGatt, int i, int i2);

    @Keep
    public final void onConnectionUpdated(final BluetoothGatt bluetoothGatt, final int i, final int i2, final int i3, final int i4) {
        runOnUiThread(new Runnable() { // from class: no.nordicsemi.android.ble.s
            @Override // java.lang.Runnable
            public final void run() {
                MainThreadBluetoothGattCallback.this.a(bluetoothGatt, i, i2, i3, i4);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: onConnectionUpdatedSafe, reason: merged with bridge method [inline-methods] */
    public abstract void a(BluetoothGatt bluetoothGatt, int i, int i2, int i3, int i4);

    @Override // android.bluetooth.BluetoothGattCallback
    public final void onDescriptorRead(final BluetoothGatt bluetoothGatt, final BluetoothGattDescriptor bluetoothGattDescriptor, final int i) {
        final byte[] value = bluetoothGattDescriptor.getValue();
        runOnUiThread(new Runnable() { // from class: no.nordicsemi.android.ble.x
            @Override // java.lang.Runnable
            public final void run() {
                MainThreadBluetoothGattCallback.this.a(bluetoothGatt, bluetoothGattDescriptor, value, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: onDescriptorReadSafe, reason: merged with bridge method [inline-methods] */
    public abstract void a(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, byte[] bArr, int i);

    @Override // android.bluetooth.BluetoothGattCallback
    public final void onDescriptorWrite(final BluetoothGatt bluetoothGatt, final BluetoothGattDescriptor bluetoothGattDescriptor, final int i) {
        final byte[] value = bluetoothGattDescriptor.getValue();
        runOnUiThread(new Runnable() { // from class: no.nordicsemi.android.ble.w
            @Override // java.lang.Runnable
            public final void run() {
                MainThreadBluetoothGattCallback.this.b(bluetoothGatt, bluetoothGattDescriptor, value, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: onDescriptorWriteSafe, reason: merged with bridge method [inline-methods] */
    public abstract void b(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, byte[] bArr, int i);

    @Override // android.bluetooth.BluetoothGattCallback
    public final void onMtuChanged(final BluetoothGatt bluetoothGatt, final int i, final int i2) {
        runOnUiThread(new Runnable() { // from class: no.nordicsemi.android.ble.l
            @Override // java.lang.Runnable
            public final void run() {
                MainThreadBluetoothGattCallback.this.b(bluetoothGatt, i, i2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: onMtuChangedSafe, reason: merged with bridge method [inline-methods] */
    public abstract void b(BluetoothGatt bluetoothGatt, int i, int i2);

    @Override // android.bluetooth.BluetoothGattCallback
    public final void onPhyRead(final BluetoothGatt bluetoothGatt, final int i, final int i2, final int i3) {
        runOnUiThread(new Runnable() { // from class: no.nordicsemi.android.ble.m
            @Override // java.lang.Runnable
            public final void run() {
                MainThreadBluetoothGattCallback.this.a(bluetoothGatt, i, i2, i3);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: onPhyReadSafe, reason: merged with bridge method [inline-methods] */
    public abstract void a(BluetoothGatt bluetoothGatt, int i, int i2, int i3);

    @Override // android.bluetooth.BluetoothGattCallback
    public final void onPhyUpdate(final BluetoothGatt bluetoothGatt, final int i, final int i2, final int i3) {
        runOnUiThread(new Runnable() { // from class: no.nordicsemi.android.ble.q
            @Override // java.lang.Runnable
            public final void run() {
                MainThreadBluetoothGattCallback.this.b(bluetoothGatt, i, i2, i3);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: onPhyUpdateSafe, reason: merged with bridge method [inline-methods] */
    public abstract void b(BluetoothGatt bluetoothGatt, int i, int i2, int i3);

    @Override // android.bluetooth.BluetoothGattCallback
    public final void onReadRemoteRssi(final BluetoothGatt bluetoothGatt, final int i, final int i2) {
        runOnUiThread(new Runnable() { // from class: no.nordicsemi.android.ble.u
            @Override // java.lang.Runnable
            public final void run() {
                MainThreadBluetoothGattCallback.this.c(bluetoothGatt, i, i2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: onReadRemoteRssiSafe, reason: merged with bridge method [inline-methods] */
    public abstract void c(BluetoothGatt bluetoothGatt, int i, int i2);

    @Override // android.bluetooth.BluetoothGattCallback
    public final void onReliableWriteCompleted(final BluetoothGatt bluetoothGatt, final int i) {
        runOnUiThread(new Runnable() { // from class: no.nordicsemi.android.ble.o
            @Override // java.lang.Runnable
            public final void run() {
                MainThreadBluetoothGattCallback.this.a(bluetoothGatt, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: onReliableWriteCompletedSafe, reason: merged with bridge method [inline-methods] */
    public abstract void a(BluetoothGatt bluetoothGatt, int i);

    @Override // android.bluetooth.BluetoothGattCallback
    public final void onServicesDiscovered(final BluetoothGatt bluetoothGatt, final int i) {
        runOnUiThread(new Runnable() { // from class: no.nordicsemi.android.ble.t
            @Override // java.lang.Runnable
            public final void run() {
                MainThreadBluetoothGattCallback.this.b(bluetoothGatt, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: onServicesDiscoveredSafe, reason: merged with bridge method [inline-methods] */
    public abstract void b(BluetoothGatt bluetoothGatt, int i);

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setHandler(Handler handler) {
        this.mHandler = handler;
    }
}
