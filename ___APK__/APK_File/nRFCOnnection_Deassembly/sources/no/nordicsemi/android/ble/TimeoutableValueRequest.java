package no.nordicsemi.android.ble;

import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import no.nordicsemi.android.ble.Request;

/* loaded from: classes.dex */
public abstract class TimeoutableValueRequest<T> extends TimeoutableRequest {
    T valueCallback;

    TimeoutableValueRequest(Request.Type type) {
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

    public TimeoutableValueRequest<T> with(T t) {
        this.valueCallback = t;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public TimeoutableValueRequest(Request.Type type, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        super(type, bluetoothGattCharacteristic);
    }

    @Override // no.nordicsemi.android.ble.TimeoutableRequest
    public TimeoutableValueRequest<T> timeout(long j) {
        super.timeout(j);
        return this;
    }

    TimeoutableValueRequest(Request.Type type, BluetoothGattDescriptor bluetoothGattDescriptor) {
        super(type, bluetoothGattDescriptor);
    }

    public <E extends T> E await(Class<E> cls) {
        Request.assertNotMainThread();
        try {
            return (E) await((TimeoutableValueRequest<T>) cls.newInstance());
        } catch (IllegalAccessException unused) {
            throw new IllegalArgumentException("Couldn't instantiate " + cls.getCanonicalName() + " class. Is the default constructor accessible?");
        } catch (InstantiationException unused2) {
            throw new IllegalArgumentException("Couldn't instantiate " + cls.getCanonicalName() + " class. Does it have a default constructor with no arguments?");
        }
    }

    @Deprecated
    public <E extends T> E await(Class<E> cls, long j) {
        return (E) timeout(j).await((Class) cls);
    }

    @Deprecated
    public <E extends T> E await(E e2, long j) {
        return (E) timeout(j).await((TimeoutableValueRequest<T>) e2);
    }
}
