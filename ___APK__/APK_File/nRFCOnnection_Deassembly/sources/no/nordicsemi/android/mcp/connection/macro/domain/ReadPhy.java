package no.nordicsemi.android.mcp.connection.macro.domain;

import android.os.Build;
import android.text.TextUtils;
import no.nordicsemi.android.mcp.ble.IBluetoothLeConnection;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.core.PersistenceException;
import org.simpleframework.xml.core.Validate;

/* loaded from: classes.dex */
public class ReadPhy extends Operation {

    @Element(required = false)
    AssertPhy assertValue;
    private boolean result;

    public ReadPhy() {
    }

    @Validate
    private void validate() {
        if (Build.VERSION.SDK_INT < 26) {
            throw new PersistenceException("read-phy is available only on Android 8+ devices", new Object[0]);
        }
    }

    @Override // no.nordicsemi.android.mcp.connection.macro.domain.Operation
    protected void execute(IBluetoothLeConnection iBluetoothLeConnection) {
        this.result = iBluetoothLeConnection.readPhy();
    }

    @Override // no.nordicsemi.android.mcp.domain.common.HasDescription
    public String getDescription() {
        AssertPhy assertPhy = this.assertValue;
        if (assertPhy != null && !TextUtils.isEmpty(assertPhy.getDescription())) {
            return super.getDescription() + "\n" + this.assertValue.getDescription();
        }
        return super.getDescription();
    }

    @Override // no.nordicsemi.android.mcp.connection.macro.domain.Operation
    protected boolean validateResult(IBluetoothLeConnection iBluetoothLeConnection) {
        if (!this.result) {
            return false;
        }
        AssertPhy assertPhy = this.assertValue;
        if (assertPhy == null) {
            return true;
        }
        return assertPhy.validateResult(iBluetoothLeConnection.getCurrentPhy());
    }

    public ReadPhy(int i, int i2) {
        setDescription("Read PHY");
        this.assertValue = new AssertPhy(i, i2);
    }

    public ReadPhy(@Attribute(name = "description") String str) {
        setDescription(str);
    }
}
