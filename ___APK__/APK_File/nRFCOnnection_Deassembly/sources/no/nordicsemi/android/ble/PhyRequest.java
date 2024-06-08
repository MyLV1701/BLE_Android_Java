package no.nordicsemi.android.ble;

import android.bluetooth.BluetoothDevice;
import no.nordicsemi.android.ble.Request;
import no.nordicsemi.android.ble.callback.BeforeCallback;
import no.nordicsemi.android.ble.callback.FailCallback;
import no.nordicsemi.android.ble.callback.InvalidRequestCallback;
import no.nordicsemi.android.ble.callback.PhyCallback;
import no.nordicsemi.android.ble.callback.SuccessCallback;

/* loaded from: classes.dex */
public final class PhyRequest extends SimpleValueRequest<PhyCallback> implements Operation {
    public static final int PHY_LE_1M_MASK = 1;
    public static final int PHY_LE_2M_MASK = 2;
    public static final int PHY_LE_CODED_MASK = 4;
    public static final int PHY_OPTION_NO_PREFERRED = 0;
    public static final int PHY_OPTION_S2 = 1;
    public static final int PHY_OPTION_S8 = 2;
    private final int phyOptions;
    private final int rxPhy;
    private final int txPhy;

    /* JADX INFO: Access modifiers changed from: package-private */
    public PhyRequest(Request.Type type) {
        super(type);
        this.txPhy = 0;
        this.rxPhy = 0;
        this.phyOptions = 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getPreferredPhyOptions() {
        return this.phyOptions;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getPreferredRxPhy() {
        return this.rxPhy;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getPreferredTxPhy() {
        return this.txPhy;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void notifyLegacyPhy(BluetoothDevice bluetoothDevice) {
        T t = this.valueCallback;
        if (t != 0) {
            ((PhyCallback) t).onPhyChanged(bluetoothDevice, 1, 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void notifyPhyChanged(BluetoothDevice bluetoothDevice, int i, int i2) {
        T t = this.valueCallback;
        if (t != 0) {
            ((PhyCallback) t).onPhyChanged(bluetoothDevice, i, i2);
        }
    }

    @Override // no.nordicsemi.android.ble.Request
    public PhyRequest before(BeforeCallback beforeCallback) {
        super.before(beforeCallback);
        return this;
    }

    @Override // no.nordicsemi.android.ble.Request
    public PhyRequest done(SuccessCallback successCallback) {
        super.done(successCallback);
        return this;
    }

    @Override // no.nordicsemi.android.ble.Request
    public PhyRequest fail(FailCallback failCallback) {
        super.fail(failCallback);
        return this;
    }

    @Override // no.nordicsemi.android.ble.Request
    public PhyRequest invalid(InvalidRequestCallback invalidRequestCallback) {
        super.invalid(invalidRequestCallback);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // no.nordicsemi.android.ble.Request
    public PhyRequest setManager(BleManager bleManager) {
        super.setManager(bleManager);
        return this;
    }

    @Override // no.nordicsemi.android.ble.SimpleValueRequest
    public PhyRequest with(PhyCallback phyCallback) {
        super.with((PhyRequest) phyCallback);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PhyRequest(Request.Type type, int i, int i2, int i3) {
        super(type);
        i = (i & (-8)) > 0 ? 1 : i;
        i2 = (i2 & (-8)) > 0 ? 1 : i2;
        i3 = (i3 < 0 || i3 > 2) ? 0 : i3;
        this.txPhy = i;
        this.rxPhy = i2;
        this.phyOptions = i3;
    }
}
