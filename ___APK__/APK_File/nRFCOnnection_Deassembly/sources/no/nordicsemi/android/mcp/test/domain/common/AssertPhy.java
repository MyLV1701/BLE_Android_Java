package no.nordicsemi.android.mcp.test.domain.common;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.os.Build;
import no.nordicsemi.android.log.LogSession;
import no.nordicsemi.android.log.Logger;
import no.nordicsemi.android.mcp.test.ConstantsManager;
import no.nordicsemi.android.mcp.test.Result;
import no.nordicsemi.android.mcp.test.domain.enumeration.OperationResult;
import no.nordicsemi.android.mcp.util.PhyHelper;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.core.PersistenceException;
import org.simpleframework.xml.core.Validate;

@Root
/* loaded from: classes.dex */
public class AssertPhy extends HasExpectedAndDescription {

    @Attribute(required = false)
    String rx;
    private int rxMaskRaw;

    @Attribute(required = false)
    String tx;
    private int txMaskRaw;

    public AssertPhy() {
        super("Assert-Phy");
    }

    public OperationResult execute(Context context, int i, int i2, ConstantsManager constantsManager, Result result, LogSession logSession) {
        Logger.v(logSession, "Asserting PHY...");
        int i3 = this.txMaskRaw;
        boolean z = true;
        boolean z2 = i3 == 0 || (i3 & i) != 0;
        int i4 = this.rxMaskRaw;
        if (i4 != 0 && (i4 & i2) == 0) {
            z = false;
        }
        if (z2 && z) {
            logSuccess(result, logSession, "TX and RX PHY match masks");
            return toResult(0);
        }
        if (!z2) {
            logFail(result, logSession, "TX PHY " + PhyHelper.phyMaskToString(i) + " does not match mask " + PhyHelper.phyMaskToString(this.txMaskRaw));
            return toResult(-1);
        }
        logFail(result, logSession, "RX PHY " + PhyHelper.phyMaskToString(i2) + " does not match mask " + PhyHelper.phyMaskToString(this.rxMaskRaw));
        return toResult(-1);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // no.nordicsemi.android.mcp.test.domain.common.HasExpectedAndDescription
    @Validate
    public void validate() {
        super.validate();
        if (Build.VERSION.SDK_INT >= 26) {
            if (!BluetoothAdapter.getDefaultAdapter().isLeCodedPhySupported() && !BluetoothAdapter.getDefaultAdapter().isLe2MPhySupported()) {
                throw new PersistenceException("assert-phy is not supported on this device", new Object[0]);
            }
            if (this.tx == null && this.rx == null) {
                throw new PersistenceException("At least one PHY (TX or RX) must be specified.", new Object[0]);
            }
            this.txMaskRaw = PhyHelper.phyMaskToInt(this.tx);
            this.rxMaskRaw = PhyHelper.phyMaskToInt(this.rx);
            return;
        }
        throw new PersistenceException("assert-phy is available only on Android 8+ devices", new Object[0]);
    }

    public AssertPhy(@Attribute(name = "description") String str) {
        super(str);
    }
}
