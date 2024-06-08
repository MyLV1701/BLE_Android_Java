package no.nordicsemi.android.mcp.connection.macro.domain;

import no.nordicsemi.android.mcp.ble.IBluetoothLeConnection;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.core.PersistenceException;
import org.simpleframework.xml.core.Validate;

/* loaded from: classes.dex */
public class Sleep extends Operation {
    private long longValue;

    @Attribute
    private String timeout;

    public Sleep() {
    }

    @Validate
    private void validate() {
        try {
            this.longValue = Long.parseLong(this.timeout);
        } catch (NumberFormatException unused) {
            throw new PersistenceException("'%s' is not valid time duration", this.timeout);
        }
    }

    @Override // no.nordicsemi.android.mcp.connection.macro.domain.Operation
    protected void execute(IBluetoothLeConnection iBluetoothLeConnection) {
        long j = this.longValue;
        if (j > 0) {
            iBluetoothLeConnection.sleep(j);
        }
    }

    public Sleep(long j) {
        this.timeout = String.valueOf(j);
        this.longValue = j;
        setDescription("Sleep " + this.timeout + " ms");
    }

    public Sleep(String str, long j) {
        this.timeout = String.valueOf(j);
        this.longValue = j;
        setDescription(str);
    }
}
