package no.nordicsemi.android.mcp.connection.macro.domain;

import android.bluetooth.BluetoothAdapter;
import android.os.Build;
import no.nordicsemi.android.mcp.ble.IBluetoothLeConnection;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;
import no.nordicsemi.android.mcp.util.PhyHelper;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.core.PersistenceException;
import org.simpleframework.xml.core.Validate;

/* loaded from: classes.dex */
public class SetPreferredPhy extends Operation {
    static final /* synthetic */ boolean $assertionsDisabled = false;

    @Attribute(required = false)
    private PhyOption option;
    private boolean result;

    @Attribute(required = false)
    private String rx;
    private int rxRaw;

    @Attribute(required = false)
    private String tx;
    private int txRaw;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: no.nordicsemi.android.mcp.connection.macro.domain.SetPreferredPhy$1, reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$no$nordicsemi$android$mcp$connection$macro$domain$SetPreferredPhy$PhyOption = new int[PhyOption.values().length];

        static {
            try {
                $SwitchMap$no$nordicsemi$android$mcp$connection$macro$domain$SetPreferredPhy$PhyOption[PhyOption.S2.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$no$nordicsemi$android$mcp$connection$macro$domain$SetPreferredPhy$PhyOption[PhyOption.S8.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

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

        String niceName() {
            int i = AnonymousClass1.$SwitchMap$no$nordicsemi$android$mcp$connection$macro$domain$SetPreferredPhy$PhyOption[ordinal()];
            return i != 1 ? i != 2 ? "No preferred" : "S8" : "S2";
        }
    }

    public SetPreferredPhy() {
        this.rx = PhyHelper.LE_1M;
        this.tx = PhyHelper.LE_1M;
        this.option = PhyOption.NO_PREFERRED;
    }

    @Validate
    private void validate() {
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

    /* JADX INFO: Access modifiers changed from: package-private */
    public AssertPhy createValueAssert() {
        return new AssertPhy(this.txRaw, this.rxRaw);
    }

    @Override // no.nordicsemi.android.mcp.connection.macro.domain.Operation
    protected void execute(IBluetoothLeConnection iBluetoothLeConnection) {
        this.result = iBluetoothLeConnection.setPreferredPhy(this.txRaw, this.rxRaw, this.option.value);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // no.nordicsemi.android.mcp.connection.macro.domain.Operation
    public Operation[] mirror(DatabaseHelper databaseHelper) {
        return new Operation[]{new WaitForPhyUpdate(this)};
    }

    @Override // no.nordicsemi.android.mcp.connection.macro.domain.Operation
    protected boolean validateResult(IBluetoothLeConnection iBluetoothLeConnection) {
        if (!this.result) {
            return false;
        }
        PhyHelper.Phy currentPhy = iBluetoothLeConnection.getCurrentPhy();
        if ((this.txRaw & currentPhy.tx) != 0) {
            return (currentPhy.rx & this.rxRaw) != 0;
        }
        return false;
    }

    public SetPreferredPhy(int i, int i2, int i3) {
        this.rx = PhyHelper.LE_1M;
        this.tx = PhyHelper.LE_1M;
        this.option = PhyOption.NO_PREFERRED;
        this.txRaw = i;
        this.rxRaw = i2;
        this.option = PhyOption.from(i3);
        this.tx = PhyHelper.phyMaskToString(i);
        this.rx = PhyHelper.phyMaskToString(i2);
        setDescription("Set preferred PHY (tx: " + this.tx + ", rx: " + this.rx + ", tx coded option: " + this.option.niceName() + ")");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SetPreferredPhy(WaitForPhyUpdate waitForPhyUpdate) {
        this.rx = PhyHelper.LE_1M;
        this.tx = PhyHelper.LE_1M;
        this.option = PhyOption.NO_PREFERRED;
        AssertPhy assertPhy = waitForPhyUpdate.assertValue;
        this.tx = assertPhy.tx;
        this.rx = assertPhy.rx;
        this.txRaw = assertPhy.txMaskRaw;
        this.rxRaw = assertPhy.rxMaskRaw;
        setDescription("Set preferred PHY (tx: " + this.tx + ", rx: " + this.rx + ", tx coded option: " + this.option.niceName() + ")");
    }
}
