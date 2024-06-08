package no.nordicsemi.android.mcp.connection.macro.domain;

import android.bluetooth.BluetoothAdapter;
import android.os.Build;
import no.nordicsemi.android.mcp.domain.common.HasDescription;
import no.nordicsemi.android.mcp.util.PhyHelper;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.core.PersistenceException;
import org.simpleframework.xml.core.Validate;

@Root
/* loaded from: classes.dex */
public class AssertPhy extends HasDescription {

    @Attribute(required = false)
    String rx;
    int rxMaskRaw;

    @Attribute(required = false)
    String tx;
    int txMaskRaw;

    public AssertPhy() {
    }

    @Validate
    private void validate() {
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
        throw new PersistenceException("set-preferred-phy is available only on Android 8+ devices", new Object[0]);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean validateResult(PhyHelper.Phy phy) {
        if (this.tx != null && (this.txMaskRaw & phy.tx) != 0) {
            return false;
        }
        if (this.rx != null) {
            if ((phy.rx & this.rxMaskRaw) == 0) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AssertPhy(int i, int i2) {
        this.txMaskRaw = i;
        this.rxMaskRaw = i2;
        this.tx = PhyHelper.phyMaskToString(i);
        this.rx = PhyHelper.phyMaskToString(i2);
        if (i != 0 && i2 != 0) {
            setDescription("Assert TX PHY is " + this.tx + " and RX PHY is " + this.rx);
            return;
        }
        if (i != 0) {
            setDescription("Assert TX PHY is " + this.tx);
            return;
        }
        setDescription("Assert RX PHY is " + this.rx);
    }
}
