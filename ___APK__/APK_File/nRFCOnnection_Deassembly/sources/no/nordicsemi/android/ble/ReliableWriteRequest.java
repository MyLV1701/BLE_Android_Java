package no.nordicsemi.android.ble;

import no.nordicsemi.android.ble.callback.BeforeCallback;
import no.nordicsemi.android.ble.callback.FailCallback;
import no.nordicsemi.android.ble.callback.InvalidRequestCallback;
import no.nordicsemi.android.ble.callback.SuccessCallback;

/* loaded from: classes.dex */
public final class ReliableWriteRequest extends RequestQueue {
    private boolean cancelled;
    private boolean closed;
    private boolean initialized;

    public void abort() {
        cancelQueue();
    }

    @Override // no.nordicsemi.android.ble.RequestQueue
    public void cancelQueue() {
        this.cancelled = true;
        super.cancelQueue();
    }

    @Override // no.nordicsemi.android.ble.RequestQueue
    Request getNext() {
        if (!this.initialized) {
            this.initialized = true;
            return Request.newBeginReliableWriteRequest();
        }
        if (super.isEmpty()) {
            this.closed = true;
            if (this.cancelled) {
                return Request.newAbortReliableWriteRequest();
            }
            return Request.newExecuteReliableWriteRequest();
        }
        return super.getNext();
    }

    @Override // no.nordicsemi.android.ble.RequestQueue
    boolean hasMore() {
        if (!this.initialized) {
            return super.hasMore();
        }
        return !this.closed;
    }

    @Override // no.nordicsemi.android.ble.RequestQueue
    public int size() {
        int size = super.size();
        if (!this.initialized) {
            size++;
        }
        return !this.closed ? size + 1 : size;
    }

    @Override // no.nordicsemi.android.ble.RequestQueue
    public ReliableWriteRequest add(Operation operation) {
        super.add(operation);
        if (operation instanceof WriteRequest) {
            ((WriteRequest) operation).forceSplit();
        }
        return this;
    }

    @Override // no.nordicsemi.android.ble.RequestQueue, no.nordicsemi.android.ble.Request
    public ReliableWriteRequest before(BeforeCallback beforeCallback) {
        super.before(beforeCallback);
        return this;
    }

    @Override // no.nordicsemi.android.ble.RequestQueue, no.nordicsemi.android.ble.Request
    public ReliableWriteRequest done(SuccessCallback successCallback) {
        super.done(successCallback);
        return this;
    }

    @Override // no.nordicsemi.android.ble.RequestQueue, no.nordicsemi.android.ble.Request
    public ReliableWriteRequest fail(FailCallback failCallback) {
        super.fail(failCallback);
        return this;
    }

    @Override // no.nordicsemi.android.ble.RequestQueue, no.nordicsemi.android.ble.Request
    public ReliableWriteRequest invalid(InvalidRequestCallback invalidRequestCallback) {
        super.invalid(invalidRequestCallback);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // no.nordicsemi.android.ble.RequestQueue, no.nordicsemi.android.ble.Request
    public ReliableWriteRequest setManager(BleManager bleManager) {
        super.setManager(bleManager);
        return this;
    }
}
