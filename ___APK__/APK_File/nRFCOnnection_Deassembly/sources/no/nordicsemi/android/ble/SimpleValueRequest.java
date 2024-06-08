package no.nordicsemi.android.ble;

import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import no.nordicsemi.android.ble.Request;

/* loaded from: classes.dex */
public abstract class SimpleValueRequest<T> extends SimpleRequest {
    T valueCallback;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SimpleValueRequest(Request.Type type) {
        super(type);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <E extends T> E await(E e2) {
        Request.assertNotMainThread();
        T t = this.valueCallback;
        try {
            with(e2).await();
            return e2;
        } finally {
            this.valueCallback = t;
        }
    }

    public SimpleValueRequest<T> with(T t) {
        this.valueCallback = t;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SimpleValueRequest(Request.Type type, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        super(type, bluetoothGattCharacteristic);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SimpleValueRequest(Request.Type type, BluetoothGattDescriptor bluetoothGattDescriptor) {
        super(type, bluetoothGattDescriptor);
    }

    public <E extends T> E await(Class<E> cls) {
        Request.assertNotMainThread();
        try {
            return (E) await((SimpleValueRequest<T>) cls.newInstance());
        } catch (IllegalAccessException unused) {
            throw new IllegalArgumentException("Couldn't instantiate " + cls.getCanonicalName() + " class. Is the default constructor accessible?");
        } catch (InstantiationException unused2) {
            throw new IllegalArgumentException("Couldn't instantiate " + cls.getCanonicalName() + " class. Does it have a default constructor with no arguments?");
        }
    }
}
