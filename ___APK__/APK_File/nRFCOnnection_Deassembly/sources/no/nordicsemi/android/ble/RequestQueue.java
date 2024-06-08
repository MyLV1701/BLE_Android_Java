package no.nordicsemi.android.ble;

import java.util.LinkedList;
import java.util.Queue;
import no.nordicsemi.android.ble.Request;
import no.nordicsemi.android.ble.callback.BeforeCallback;
import no.nordicsemi.android.ble.callback.FailCallback;
import no.nordicsemi.android.ble.callback.InvalidRequestCallback;
import no.nordicsemi.android.ble.callback.SuccessCallback;

/* loaded from: classes.dex */
public class RequestQueue extends SimpleRequest {
    private final Queue<Request> requests;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RequestQueue() {
        super(Request.Type.SET);
        this.requests = new LinkedList();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public RequestQueue add(Operation operation) {
        if (operation instanceof Request) {
            Request request = (Request) operation;
            if (!request.enqueued) {
                this.requests.add(request);
                request.enqueued = true;
                return this;
            }
            throw new IllegalStateException("Request already enqueued");
        }
        throw new IllegalArgumentException("Operation does not extend Request");
    }

    public void cancelQueue() {
        this.requests.clear();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Request getNext() {
        try {
            return this.requests.remove();
        } catch (Exception unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean hasMore() {
        return !this.requests.isEmpty();
    }

    public boolean isEmpty() {
        return this.requests.isEmpty();
    }

    public int size() {
        return this.requests.size();
    }

    @Override // no.nordicsemi.android.ble.Request
    public RequestQueue before(BeforeCallback beforeCallback) {
        super.before(beforeCallback);
        return this;
    }

    @Override // no.nordicsemi.android.ble.Request
    public RequestQueue done(SuccessCallback successCallback) {
        super.done(successCallback);
        return this;
    }

    @Override // no.nordicsemi.android.ble.Request
    public RequestQueue fail(FailCallback failCallback) {
        super.fail(failCallback);
        return this;
    }

    @Override // no.nordicsemi.android.ble.Request
    public RequestQueue invalid(InvalidRequestCallback invalidRequestCallback) {
        super.invalid(invalidRequestCallback);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // no.nordicsemi.android.ble.Request
    public RequestQueue setManager(BleManager bleManager) {
        super.setManager(bleManager);
        return this;
    }
}
