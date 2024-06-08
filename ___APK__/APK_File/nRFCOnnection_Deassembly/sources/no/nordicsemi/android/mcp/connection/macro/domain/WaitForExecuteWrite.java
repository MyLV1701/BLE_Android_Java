package no.nordicsemi.android.mcp.connection.macro.domain;

import no.nordicsemi.android.mcp.ble.IBluetoothLeConnection;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;
import org.simpleframework.xml.Attribute;

/* loaded from: classes.dex */
public class WaitForExecuteWrite extends Operation {

    @Attribute
    private boolean execute;

    public WaitForExecuteWrite() {
    }

    @Override // no.nordicsemi.android.mcp.connection.macro.domain.Operation
    protected void execute(IBluetoothLeConnection iBluetoothLeConnection) {
        iBluetoothLeConnection.waitForExecuteWrite(this.execute);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // no.nordicsemi.android.mcp.connection.macro.domain.Operation
    public Operation[] mirror(DatabaseHelper databaseHelper) {
        return this.execute ? new Operation[]{new ExecuteReliableWrite(1)} : new Operation[]{new AbortReliableWrite(1)};
    }

    @Override // no.nordicsemi.android.mcp.connection.macro.domain.Operation
    protected boolean validateResult(IBluetoothLeConnection iBluetoothLeConnection) {
        Boolean writeExecuteResult = iBluetoothLeConnection.getWriteExecuteResult();
        return writeExecuteResult != null && writeExecuteResult.booleanValue() == this.execute;
    }

    public WaitForExecuteWrite(boolean z) {
        this.execute = z;
        if (z) {
            setDescription("Wait for Execute Reliable Write");
        } else {
            setDescription("Wait for Abort Reliable Write");
        }
    }
}
