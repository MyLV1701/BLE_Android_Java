package no.nordicsemi.android.ble;

import no.nordicsemi.android.ble.Request;
import no.nordicsemi.android.ble.callback.BeforeCallback;
import no.nordicsemi.android.ble.callback.FailCallback;
import no.nordicsemi.android.ble.callback.InvalidRequestCallback;
import no.nordicsemi.android.ble.callback.SuccessCallback;

/* loaded from: classes.dex */
public class DisconnectRequest extends TimeoutableRequest {
    /* JADX INFO: Access modifiers changed from: package-private */
    public DisconnectRequest(Request.Type type) {
        super(type);
    }

    @Override // no.nordicsemi.android.ble.Request
    public DisconnectRequest before(BeforeCallback beforeCallback) {
        super.before(beforeCallback);
        return this;
    }

    @Override // no.nordicsemi.android.ble.Request
    public DisconnectRequest done(SuccessCallback successCallback) {
        super.done(successCallback);
        return this;
    }

    @Override // no.nordicsemi.android.ble.Request
    public DisconnectRequest fail(FailCallback failCallback) {
        super.fail(failCallback);
        return this;
    }

    @Override // no.nordicsemi.android.ble.Request
    public DisconnectRequest invalid(InvalidRequestCallback invalidRequestCallback) {
        super.invalid(invalidRequestCallback);
        return this;
    }

    @Override // no.nordicsemi.android.ble.TimeoutableRequest
    public DisconnectRequest timeout(long j) {
        super.timeout(j);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // no.nordicsemi.android.ble.TimeoutableRequest, no.nordicsemi.android.ble.Request
    public DisconnectRequest setManager(BleManager bleManager) {
        super.setManager(bleManager);
        return this;
    }
}
