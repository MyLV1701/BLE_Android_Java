package no.nordicsemi.android.mcp.connection.macro.domain;

import android.bluetooth.BluetoothAdapter;
import android.os.Build;
import android.text.TextUtils;
import no.nordicsemi.android.mcp.ble.IBluetoothLeConnection;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.core.PersistenceException;
import org.simpleframework.xml.core.Validate;

/* loaded from: classes.dex */
public class WaitForPhyUpdate extends Operation {

    @Element(required = false)
    AssertPhy assertValue;

    public WaitForPhyUpdate() {
    }

    @Validate
    private void validate() {
        if (Build.VERSION.SDK_INT >= 26) {
            if (!BluetoothAdapter.getDefaultAdapter().isLeCodedPhySupported() && !BluetoothAdapter.getDefaultAdapter().isLe2MPhySupported()) {
                throw new PersistenceException("wait-for-phy-update is not supported on this device", new Object[0]);
            }
            return;
        }
        throw new PersistenceException("wait-for-phy-update is available only on Android 8+ devices", new Object[0]);
    }

    @Override // no.nordicsemi.android.mcp.connection.macro.domain.Operation
    protected void execute(IBluetoothLeConnection iBluetoothLeConnection) {
        iBluetoothLeConnection.waitForPhyUpdate();
    }

    @Override // no.nordicsemi.android.mcp.domain.common.HasDescription
    public String getDescription() {
        AssertPhy assertPhy = this.assertValue;
        if (assertPhy != null && !TextUtils.isEmpty(assertPhy.getDescription())) {
            return super.getDescription() + "\n" + this.assertValue.getDescription();
        }
        return super.getDescription();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // no.nordicsemi.android.mcp.connection.macro.domain.Operation
    public Operation[] mirror(DatabaseHelper databaseHelper) {
        return this.assertValue != null ? new Operation[]{new SetPreferredPhy(this)} : new Operation[]{new Sleep("NOTE: Set preferred PHY. PHYs not provided.", 0L)};
    }

    @Override // no.nordicsemi.android.mcp.connection.macro.domain.Operation
    protected boolean validateResult(IBluetoothLeConnection iBluetoothLeConnection) {
        AssertPhy assertPhy = this.assertValue;
        if (assertPhy == null) {
            return true;
        }
        return assertPhy.validateResult(iBluetoothLeConnection.getCurrentPhy());
    }

    public WaitForPhyUpdate(int i, int i2) {
        setDescription("Wait for PHY Update");
        this.assertValue = new AssertPhy(i, i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public WaitForPhyUpdate(SetPreferredPhy setPreferredPhy) {
        setDescription("Wait for PHY Update");
        this.assertValue = setPreferredPhy.createValueAssert();
    }
}
