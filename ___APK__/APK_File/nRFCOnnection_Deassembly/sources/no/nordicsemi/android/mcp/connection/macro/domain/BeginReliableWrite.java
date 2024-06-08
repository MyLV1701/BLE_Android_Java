package no.nordicsemi.android.mcp.connection.macro.domain;

import no.nordicsemi.android.mcp.ble.IBluetoothLeConnection;
import org.simpleframework.xml.Attribute;

/* loaded from: classes.dex */
public class BeginReliableWrite extends Operation {
    private boolean result;

    public BeginReliableWrite() {
    }

    @Override // no.nordicsemi.android.mcp.connection.macro.domain.Operation
    protected void execute(IBluetoothLeConnection iBluetoothLeConnection) {
        this.result = iBluetoothLeConnection.beginReliableWrite();
    }

    @Override // no.nordicsemi.android.mcp.connection.macro.domain.Operation
    protected boolean validateResult(IBluetoothLeConnection iBluetoothLeConnection) {
        return this.result;
    }

    public BeginReliableWrite(int i) {
        setDescription("Begin Reliable Write");
    }

    public BeginReliableWrite(@Attribute(name = "description") String str) {
        setDescription(str);
    }
}
