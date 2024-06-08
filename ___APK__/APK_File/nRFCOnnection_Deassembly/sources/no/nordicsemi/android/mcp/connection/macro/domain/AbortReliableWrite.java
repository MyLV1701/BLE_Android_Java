package no.nordicsemi.android.mcp.connection.macro.domain;

import no.nordicsemi.android.mcp.ble.IBluetoothLeConnection;
import org.simpleframework.xml.Attribute;

/* loaded from: classes.dex */
public class AbortReliableWrite extends Operation {
    private boolean result;

    public AbortReliableWrite() {
    }

    @Override // no.nordicsemi.android.mcp.connection.macro.domain.Operation
    protected void execute(IBluetoothLeConnection iBluetoothLeConnection) {
        this.result = iBluetoothLeConnection.abortReliableWrite();
    }

    @Override // no.nordicsemi.android.mcp.connection.macro.domain.Operation
    protected boolean validateResult(IBluetoothLeConnection iBluetoothLeConnection) {
        return this.result;
    }

    public AbortReliableWrite(int i) {
        setDescription("Abort Reliable Write");
    }

    public AbortReliableWrite(@Attribute(name = "description") String str) {
        setDescription(str);
    }
}
