package no.nordicsemi.android.mcp.connection.macro.domain;

import no.nordicsemi.android.mcp.ble.IBluetoothLeConnection;
import org.simpleframework.xml.Attribute;

/* loaded from: classes.dex */
public class ExecuteReliableWrite extends Operation {
    private boolean result;

    public ExecuteReliableWrite() {
    }

    @Override // no.nordicsemi.android.mcp.connection.macro.domain.Operation
    protected void execute(IBluetoothLeConnection iBluetoothLeConnection) {
        this.result = iBluetoothLeConnection.executeReliableWrite();
    }

    @Override // no.nordicsemi.android.mcp.connection.macro.domain.Operation
    protected boolean validateResult(IBluetoothLeConnection iBluetoothLeConnection) {
        return this.result;
    }

    public ExecuteReliableWrite(int i) {
        setDescription("Execute Reliable Write");
    }

    public ExecuteReliableWrite(@Attribute(name = "description") String str) {
        setDescription(str);
    }
}
