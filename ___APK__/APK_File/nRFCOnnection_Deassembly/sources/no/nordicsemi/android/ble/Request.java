package no.nordicsemi.android.ble;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.os.ConditionVariable;
import android.os.Looper;
import no.nordicsemi.android.ble.callback.BeforeCallback;
import no.nordicsemi.android.ble.callback.FailCallback;
import no.nordicsemi.android.ble.callback.InvalidRequestCallback;
import no.nordicsemi.android.ble.callback.SuccessCallback;

/* loaded from: classes.dex */
public abstract class Request {
    BeforeCallback beforeCallback;
    final BluetoothGattCharacteristic characteristic;
    final BluetoothGattDescriptor descriptor;
    boolean enqueued;
    FailCallback failCallback;
    boolean finished;
    BeforeCallback internalBeforeCallback;
    FailCallback internalFailCallback;
    SuccessCallback internalSuccessCallback;
    InvalidRequestCallback invalidRequestCallback;
    private BleManager manager;
    SuccessCallback successCallback;
    final ConditionVariable syncLock;
    final Type type;

    /* loaded from: classes.dex */
    final class RequestCallback implements SuccessCallback, FailCallback, InvalidRequestCallback {
        static final int REASON_REQUEST_INVALID = -1000000;
        int status = 0;

        /* JADX INFO: Access modifiers changed from: package-private */
        public RequestCallback() {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public boolean isSuccess() {
            return this.status == 0;
        }

        @Override // no.nordicsemi.android.ble.callback.InvalidRequestCallback
        public void onInvalidRequest() {
            this.status = REASON_REQUEST_INVALID;
            Request.this.syncLock.open();
        }

        @Override // no.nordicsemi.android.ble.callback.SuccessCallback
        public void onRequestCompleted(BluetoothDevice bluetoothDevice) {
            Request.this.syncLock.open();
        }

        @Override // no.nordicsemi.android.ble.callback.FailCallback
        public void onRequestFailed(BluetoothDevice bluetoothDevice, int i) {
            this.status = i;
            Request.this.syncLock.open();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public enum Type {
        SET,
        CONNECT,
        DISCONNECT,
        CREATE_BOND,
        REMOVE_BOND,
        WRITE,
        READ,
        WRITE_DESCRIPTOR,
        READ_DESCRIPTOR,
        BEGIN_RELIABLE_WRITE,
        EXECUTE_RELIABLE_WRITE,
        ABORT_RELIABLE_WRITE,
        ENABLE_NOTIFICATIONS,
        ENABLE_INDICATIONS,
        DISABLE_NOTIFICATIONS,
        DISABLE_INDICATIONS,
        WAIT_FOR_NOTIFICATION,
        WAIT_FOR_INDICATION,
        READ_BATTERY_LEVEL,
        ENABLE_BATTERY_LEVEL_NOTIFICATIONS,
        DISABLE_BATTERY_LEVEL_NOTIFICATIONS,
        ENABLE_SERVICE_CHANGED_INDICATIONS,
        REQUEST_MTU,
        REQUEST_CONNECTION_PRIORITY,
        SET_PREFERRED_PHY,
        READ_PHY,
        READ_RSSI,
        REFRESH_CACHE,
        SLEEP
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Request(Type type) {
        this.type = type;
        this.characteristic = null;
        this.descriptor = null;
        this.syncLock = new ConditionVariable(true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void assertNotMainThread() {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new IllegalStateException("Cannot execute synchronous operation from the UI thread.");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ConnectRequest connect(BluetoothDevice bluetoothDevice) {
        return new ConnectRequest(Type.CONNECT, bluetoothDevice);
    }

    @Deprecated
    public static SimpleRequest createBond() {
        return new SimpleRequest(Type.CREATE_BOND);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static DisconnectRequest disconnect() {
        return new DisconnectRequest(Type.DISCONNECT);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static SimpleRequest newAbortReliableWriteRequest() {
        return new SimpleRequest(Type.ABORT_RELIABLE_WRITE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static SimpleRequest newBeginReliableWriteRequest() {
        return new SimpleRequest(Type.BEGIN_RELIABLE_WRITE);
    }

    @Deprecated
    public static ConnectionPriorityRequest newConnectionPriorityRequest(int i) {
        return new ConnectionPriorityRequest(Type.REQUEST_CONNECTION_PRIORITY, i);
    }

    @Deprecated
    public static WriteRequest newDisableBatteryLevelNotificationsRequest() {
        return new WriteRequest(Type.DISABLE_BATTERY_LEVEL_NOTIFICATIONS);
    }

    @Deprecated
    public static WriteRequest newDisableIndicationsRequest(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return new WriteRequest(Type.DISABLE_INDICATIONS, bluetoothGattCharacteristic);
    }

    @Deprecated
    public static WriteRequest newDisableNotificationsRequest(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return new WriteRequest(Type.DISABLE_NOTIFICATIONS, bluetoothGattCharacteristic);
    }

    @Deprecated
    public static WriteRequest newEnableBatteryLevelNotificationsRequest() {
        return new WriteRequest(Type.ENABLE_BATTERY_LEVEL_NOTIFICATIONS);
    }

    @Deprecated
    public static WriteRequest newEnableIndicationsRequest(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return new WriteRequest(Type.ENABLE_INDICATIONS, bluetoothGattCharacteristic);
    }

    @Deprecated
    public static WriteRequest newEnableNotificationsRequest(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return new WriteRequest(Type.ENABLE_NOTIFICATIONS, bluetoothGattCharacteristic);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static WriteRequest newEnableServiceChangedIndicationsRequest() {
        return new WriteRequest(Type.ENABLE_SERVICE_CHANGED_INDICATIONS);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static SimpleRequest newExecuteReliableWriteRequest() {
        return new SimpleRequest(Type.EXECUTE_RELIABLE_WRITE);
    }

    @Deprecated
    public static MtuRequest newMtuRequest(int i) {
        return new MtuRequest(Type.REQUEST_MTU, i);
    }

    @Deprecated
    public static ReadRequest newReadBatteryLevelRequest() {
        return new ReadRequest(Type.READ_BATTERY_LEVEL);
    }

    @Deprecated
    public static PhyRequest newReadPhyRequest() {
        return new PhyRequest(Type.READ_PHY);
    }

    @Deprecated
    public static ReadRequest newReadRequest(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return new ReadRequest(Type.READ, bluetoothGattCharacteristic);
    }

    @Deprecated
    public static ReadRssiRequest newReadRssiRequest() {
        return new ReadRssiRequest(Type.READ_RSSI);
    }

    @Deprecated
    public static SimpleRequest newRefreshCacheRequest() {
        return new SimpleRequest(Type.REFRESH_CACHE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ReliableWriteRequest newReliableWriteRequest() {
        return new ReliableWriteRequest();
    }

    @Deprecated
    public static PhyRequest newSetPreferredPhyRequest(int i, int i2, int i3) {
        return new PhyRequest(Type.SET_PREFERRED_PHY, i, i2, i3);
    }

    @Deprecated
    public static SleepRequest newSleepRequest(long j) {
        return new SleepRequest(Type.SLEEP, j);
    }

    @Deprecated
    public static WaitForValueChangedRequest newWaitForIndicationRequest(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return new WaitForValueChangedRequest(Type.WAIT_FOR_INDICATION, bluetoothGattCharacteristic);
    }

    @Deprecated
    public static WaitForValueChangedRequest newWaitForNotificationRequest(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return new WaitForValueChangedRequest(Type.WAIT_FOR_NOTIFICATION, bluetoothGattCharacteristic);
    }

    @Deprecated
    public static WriteRequest newWriteRequest(BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr) {
        return new WriteRequest(Type.WRITE, bluetoothGattCharacteristic, bArr, 0, bArr != null ? bArr.length : 0, bluetoothGattCharacteristic != null ? bluetoothGattCharacteristic.getWriteType() : 2);
    }

    @Deprecated
    public static SimpleRequest removeBond() {
        return new SimpleRequest(Type.REMOVE_BOND);
    }

    public Request before(BeforeCallback beforeCallback) {
        this.beforeCallback = beforeCallback;
        return this;
    }

    public Request done(SuccessCallback successCallback) {
        this.successCallback = successCallback;
        return this;
    }

    public void enqueue() {
        this.manager.enqueue(this);
    }

    public Request fail(FailCallback failCallback) {
        this.failCallback = failCallback;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void internalBefore(BeforeCallback beforeCallback) {
        this.internalBeforeCallback = beforeCallback;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void internalFail(FailCallback failCallback) {
        this.internalFailCallback = failCallback;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void internalSuccess(SuccessCallback successCallback) {
        this.internalSuccessCallback = successCallback;
    }

    public Request invalid(InvalidRequestCallback invalidRequestCallback) {
        this.invalidRequestCallback = invalidRequestCallback;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void notifyFail(BluetoothDevice bluetoothDevice, int i) {
        if (this.finished) {
            return;
        }
        this.finished = true;
        FailCallback failCallback = this.failCallback;
        if (failCallback != null) {
            failCallback.onRequestFailed(bluetoothDevice, i);
        }
        FailCallback failCallback2 = this.internalFailCallback;
        if (failCallback2 != null) {
            failCallback2.onRequestFailed(bluetoothDevice, i);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void notifyInvalidRequest() {
        if (this.finished) {
            return;
        }
        this.finished = true;
        InvalidRequestCallback invalidRequestCallback = this.invalidRequestCallback;
        if (invalidRequestCallback != null) {
            invalidRequestCallback.onInvalidRequest();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void notifyStarted(BluetoothDevice bluetoothDevice) {
        BeforeCallback beforeCallback = this.beforeCallback;
        if (beforeCallback != null) {
            beforeCallback.onRequestStarted(bluetoothDevice);
        }
        BeforeCallback beforeCallback2 = this.internalBeforeCallback;
        if (beforeCallback2 != null) {
            beforeCallback2.onRequestStarted(bluetoothDevice);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void notifySuccess(BluetoothDevice bluetoothDevice) {
        if (this.finished) {
            return;
        }
        this.finished = true;
        SuccessCallback successCallback = this.successCallback;
        if (successCallback != null) {
            successCallback.onRequestCompleted(bluetoothDevice);
        }
        SuccessCallback successCallback2 = this.internalSuccessCallback;
        if (successCallback2 != null) {
            successCallback2.onRequestCompleted(bluetoothDevice);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Request setManager(BleManager bleManager) {
        this.manager = bleManager;
        return this;
    }

    @Deprecated
    public static ReadRequest newReadRequest(BluetoothGattDescriptor bluetoothGattDescriptor) {
        return new ReadRequest(Type.READ_DESCRIPTOR, bluetoothGattDescriptor);
    }

    @Deprecated
    public static WriteRequest newWriteRequest(BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr, int i) {
        return new WriteRequest(Type.WRITE, bluetoothGattCharacteristic, bArr, 0, bArr != null ? bArr.length : 0, i);
    }

    @Deprecated
    public static WriteRequest newWriteRequest(BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr, int i, int i2) {
        return new WriteRequest(Type.WRITE, bluetoothGattCharacteristic, bArr, i, i2, bluetoothGattCharacteristic != null ? bluetoothGattCharacteristic.getWriteType() : 2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Request(Type type, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        this.type = type;
        this.characteristic = bluetoothGattCharacteristic;
        this.descriptor = null;
        this.syncLock = new ConditionVariable(true);
    }

    @Deprecated
    public static WriteRequest newWriteRequest(BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr, int i, int i2, int i3) {
        return new WriteRequest(Type.WRITE, bluetoothGattCharacteristic, bArr, i, i2, i3);
    }

    @Deprecated
    public static WriteRequest newWriteRequest(BluetoothGattDescriptor bluetoothGattDescriptor, byte[] bArr) {
        return new WriteRequest(Type.WRITE_DESCRIPTOR, bluetoothGattDescriptor, bArr, 0, bArr != null ? bArr.length : 0);
    }

    @Deprecated
    public static WriteRequest newWriteRequest(BluetoothGattDescriptor bluetoothGattDescriptor, byte[] bArr, int i, int i2) {
        return new WriteRequest(Type.WRITE_DESCRIPTOR, bluetoothGattDescriptor, bArr, i, i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Request(Type type, BluetoothGattDescriptor bluetoothGattDescriptor) {
        this.type = type;
        this.characteristic = null;
        this.descriptor = bluetoothGattDescriptor;
        this.syncLock = new ConditionVariable(true);
    }
}
