package no.nordicsemi.android.ble;

import no.nordicsemi.android.ble.Request;
import no.nordicsemi.android.ble.callback.BeforeCallback;
import no.nordicsemi.android.ble.callback.FailCallback;
import no.nordicsemi.android.ble.callback.InvalidRequestCallback;
import no.nordicsemi.android.ble.callback.SuccessCallback;

/* loaded from: classes.dex */
public final class SleepRequest extends SimpleRequest implements Operation {
    private long delay;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SleepRequest(Request.Type type, long j) {
        super(type);
        this.delay = j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long getDelay() {
        return this.delay;
    }

    @Override // no.nordicsemi.android.ble.Request
    public SleepRequest before(BeforeCallback beforeCallback) {
        super.before(beforeCallback);
        return this;
    }

    @Override // no.nordicsemi.android.ble.Request
    public SleepRequest done(SuccessCallback successCallback) {
        super.done(successCallback);
        return this;
    }

    @Override // no.nordicsemi.android.ble.Request
    public SleepRequest fail(FailCallback failCallback) {
        super.fail(failCallback);
        return this;
    }

    @Override // no.nordicsemi.android.ble.Request
    public SleepRequest invalid(InvalidRequestCallback invalidRequestCallback) {
        super.invalid(invalidRequestCallback);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // no.nordicsemi.android.ble.Request
    public SleepRequest setManager(BleManager bleManager) {
        super.setManager(bleManager);
        return this;
    }
}
