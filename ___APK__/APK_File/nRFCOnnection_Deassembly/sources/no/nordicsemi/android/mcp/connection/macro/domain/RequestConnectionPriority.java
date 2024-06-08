package no.nordicsemi.android.mcp.connection.macro.domain;

import android.os.Build;
import no.nordicsemi.android.mcp.ble.IBluetoothLeConnection;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.core.PersistenceException;
import org.simpleframework.xml.core.Validate;

/* loaded from: classes.dex */
public class RequestConnectionPriority extends Operation {
    private boolean result;

    @Attribute
    private Type type;

    /* loaded from: classes.dex */
    public enum Type {
        LOW_POWER(2),
        HIGH(1),
        BALANCED(0);

        private int value;

        Type(int i) {
            this.value = i;
        }
    }

    public RequestConnectionPriority() {
        this.type = Type.BALANCED;
    }

    @Validate
    private void validate() {
        if (Build.VERSION.SDK_INT < 21) {
            throw new PersistenceException("request-connection-priority is available only on Android 5+ devices", new Object[0]);
        }
    }

    @Override // no.nordicsemi.android.mcp.connection.macro.domain.Operation
    protected void execute(IBluetoothLeConnection iBluetoothLeConnection) {
        this.result = iBluetoothLeConnection.requestConnectionPriority(this.type.value);
    }

    @Override // no.nordicsemi.android.mcp.connection.macro.domain.Operation
    protected boolean validateResult(IBluetoothLeConnection iBluetoothLeConnection) {
        return this.result;
    }

    public RequestConnectionPriority(int i) {
        Type type = Type.BALANCED;
        this.type = type;
        if (i == 1) {
            this.type = Type.HIGH;
        } else if (i != 2) {
            this.type = type;
        } else {
            this.type = Type.LOW_POWER;
        }
        setDescription("Request connection priority " + this.type.name());
    }
}
