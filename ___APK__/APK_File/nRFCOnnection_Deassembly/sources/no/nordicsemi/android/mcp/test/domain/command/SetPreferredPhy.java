package no.nordicsemi.android.mcp.test.domain.command;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.os.Build;
import no.nordicsemi.android.log.LogSession;
import no.nordicsemi.android.mcp.test.ConstantsManager;
import no.nordicsemi.android.mcp.test.Result;
import no.nordicsemi.android.mcp.test.domain.TimeoutOperation;
import no.nordicsemi.android.mcp.test.domain.enumeration.OperationResult;
import no.nordicsemi.android.mcp.util.PhyHelper;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.core.PersistenceException;
import org.simpleframework.xml.core.Validate;

@Root
/* loaded from: classes.dex */
public class SetPreferredPhy extends TimeoutOperation {

    @Attribute(required = false)
    private PhyOption option;

    @Attribute(required = false)
    String rx;
    private int rxRaw;

    @Attribute(required = false)
    String tx;
    private int txRaw;

    /* loaded from: classes.dex */
    public enum PhyOption {
        NO_PREFERRED(0),
        S2(1),
        S8(2);

        private int value;

        PhyOption(int i) {
            this.value = i;
        }

        static PhyOption from(int i) {
            if (i == 1) {
                return S2;
            }
            if (i != 2) {
                return NO_PREFERRED;
            }
            return S8;
        }
    }

    public SetPreferredPhy() {
        super("Set-Preferred-Phy");
        this.tx = PhyHelper.LE_1M;
        this.rx = PhyHelper.LE_1M;
        this.option = PhyOption.NO_PREFERRED;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // no.nordicsemi.android.mcp.test.domain.Operation
    public OperationResult execute(Context context, ConstantsManager constantsManager, Result result, LogSession logSession) {
        int preferredPhy = getTarget().setPreferredPhy(logSession, this.txRaw, this.rxRaw, this.option.value);
        if (preferredPhy != 0) {
            logFail(result, logSession, "Setting preferred PHY failed");
            return toResult(preferredPhy);
        }
        logSuccess(result, null, "Preferred PHY requested");
        return toResult(0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // no.nordicsemi.android.mcp.test.domain.common.HasExpectedAndDescription
    @Validate
    public void validate() {
        super.validate();
        if (Build.VERSION.SDK_INT >= 26) {
            if (!BluetoothAdapter.getDefaultAdapter().isLeCodedPhySupported() && !BluetoothAdapter.getDefaultAdapter().isLe2MPhySupported()) {
                throw new PersistenceException("set-preferred-phy is not supported on this device", new Object[0]);
            }
            this.txRaw = PhyHelper.phyMaskToInt(this.tx);
            this.rxRaw = PhyHelper.phyMaskToInt(this.rx);
            return;
        }
        throw new PersistenceException("set-preferred-phy is available only on Android 8+ devices", new Object[0]);
    }

    public SetPreferredPhy(@Attribute(name = "description") String str) {
        super(str);
        this.tx = PhyHelper.LE_1M;
        this.rx = PhyHelper.LE_1M;
        this.option = PhyOption.NO_PREFERRED;
    }
}
