package no.nordicsemi.android.mcp.connection.macro.domain;

import android.os.Build;
import no.nordicsemi.android.mcp.ble.IBluetoothLeConnection;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.core.PersistenceException;
import org.simpleframework.xml.core.Validate;

/* loaded from: classes.dex */
public class RequestMtu extends Operation {
    private int intValue;

    @Attribute
    private String value;

    public RequestMtu() {
    }

    @Validate
    private void validate() {
        if (Build.VERSION.SDK_INT >= 21) {
            try {
                this.intValue = Integer.parseInt(this.value);
                if (this.intValue < 23 || this.intValue > 517) {
                    throw new PersistenceException("MTU value must be in range <23, 517>", new Object[0]);
                }
                return;
            } catch (NumberFormatException unused) {
                throw new PersistenceException("'%s' is no a valid MTU value", this.value);
            }
        }
        throw new PersistenceException("request-mtu is available only on Android 5+ devices", new Object[0]);
    }

    @Override // no.nordicsemi.android.mcp.connection.macro.domain.Operation
    protected void execute(IBluetoothLeConnection iBluetoothLeConnection) {
        iBluetoothLeConnection.requestMtu(this.intValue);
    }

    @Override // no.nordicsemi.android.mcp.connection.macro.domain.Operation
    protected boolean validateResult(IBluetoothLeConnection iBluetoothLeConnection) {
        return this.intValue == iBluetoothLeConnection.getMtu();
    }

    public RequestMtu(int i) {
        this.value = String.valueOf(i);
        this.intValue = i;
        setDescription("Request MTU change to " + this.value);
    }
}
