package no.nordicsemi.android.mcp.connection.macro.domain;

import no.nordicsemi.android.mcp.ble.IBluetoothLeConnection;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;
import org.simpleframework.xml.Attribute;

/* loaded from: classes.dex */
public class ReadRSSI extends Operation {
    private boolean result;

    public ReadRSSI() {
    }

    @Override // no.nordicsemi.android.mcp.connection.macro.domain.Operation
    protected void execute(IBluetoothLeConnection iBluetoothLeConnection) {
        this.result = iBluetoothLeConnection.readRemoteRssi();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // no.nordicsemi.android.mcp.connection.macro.domain.Operation
    public Operation[] mirror(DatabaseHelper databaseHelper) {
        return new Operation[]{new ReadRSSI(getDescription())};
    }

    @Override // no.nordicsemi.android.mcp.connection.macro.domain.Operation
    protected boolean validateResult(IBluetoothLeConnection iBluetoothLeConnection) {
        return this.result;
    }

    public ReadRSSI(int i) {
        setDescription("Read RSSI");
    }

    public ReadRSSI(@Attribute(name = "description") String str) {
        setDescription(str);
    }
}
