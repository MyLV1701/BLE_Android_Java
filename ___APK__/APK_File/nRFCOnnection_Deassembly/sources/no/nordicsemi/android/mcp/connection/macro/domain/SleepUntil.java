package no.nordicsemi.android.mcp.connection.macro.domain;

import android.bluetooth.BluetoothGattCharacteristic;
import android.text.TextUtils;
import java.util.Arrays;
import no.nordicsemi.android.mcp.ble.IBluetoothLeBasicConnection;
import no.nordicsemi.android.mcp.ble.IBluetoothLeConnection;
import no.nordicsemi.android.mcp.ble.parser.utils.ParserUtils;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.core.PersistenceException;
import org.simpleframework.xml.core.Validate;

/* loaded from: classes.dex */
public class SleepUntil extends CharacteristicOperation {
    private byte[] bytes;
    private long longValue;

    @Attribute(required = false)
    private boolean server;

    @Attribute(required = false)
    private String timeout;

    @Attribute(required = false)
    private String value;

    @Attribute(required = false)
    private String valueString;

    public SleepUntil() {
        this.timeout = "0";
        this.server = false;
    }

    @Override // no.nordicsemi.android.mcp.connection.macro.domain.Operation
    protected void execute(IBluetoothLeConnection iBluetoothLeConnection) {
        BluetoothGattCharacteristic characteristic = getCharacteristic(iBluetoothLeConnection);
        if (characteristic != null) {
            iBluetoothLeConnection.sleep(characteristic, this.bytes, true, this.longValue, this.server);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // no.nordicsemi.android.mcp.connection.macro.domain.CharacteristicOperation
    public boolean hasAccessToTemporaryServices() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // no.nordicsemi.android.mcp.connection.macro.domain.CharacteristicOperation
    public boolean isClientOperation() {
        return !this.server;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // no.nordicsemi.android.mcp.connection.macro.domain.Operation
    public Operation[] mirror(DatabaseHelper databaseHelper) {
        String str;
        if (this.longValue == 0) {
            str = "";
        } else {
            str = "for " + this.timeout + " ms ";
        }
        if (!TextUtils.isEmpty(this.value)) {
            return new Operation[]{new Sleep("NOTE: The other side is waiting " + str + "until the value of " + getName(databaseHelper) + " equals to 0x" + this.value, 0L)};
        }
        if (TextUtils.isEmpty(this.valueString)) {
            return new Operation[]{new Sleep("NOTE: The other side is waiting " + str + "until the value of " + getName(databaseHelper) + " is empty", 0L)};
        }
        return new Operation[]{new Sleep("NOTE: The other side is waiting " + str + "until the value of " + getName(databaseHelper) + " equals to '" + this.valueString + "'", 0L)};
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // no.nordicsemi.android.mcp.connection.macro.domain.CharacteristicOperation
    @Validate
    public void validate() {
        super.validate();
        if (this.value == null && this.valueString == null) {
            throw new PersistenceException("No value specified for sleep-until. Use 'value' for binary data or 'value-string' for text.", new Object[0]);
        }
        if (this.value != null && this.valueString != null) {
            throw new PersistenceException("Both value-string and value specified for sleep-until", new Object[0]);
        }
        try {
            this.longValue = Long.parseLong(this.timeout);
            String str = this.value;
            if (str != null) {
                this.bytes = hexStringToByteArray(str);
            } else {
                this.bytes = this.valueString.getBytes();
            }
        } catch (NumberFormatException unused) {
            throw new PersistenceException("'%s' is not valid time duration", this.timeout);
        }
    }

    @Override // no.nordicsemi.android.mcp.connection.macro.domain.Operation
    protected boolean validateResult(IBluetoothLeConnection iBluetoothLeConnection) {
        BluetoothGattCharacteristic characteristic = getCharacteristic(iBluetoothLeConnection);
        return characteristic != null && Arrays.equals(characteristic.getValue(), this.bytes);
    }

    public SleepUntil(DatabaseHelper databaseHelper, IBluetoothLeBasicConnection iBluetoothLeBasicConnection, BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr, long j, boolean z) {
        super(iBluetoothLeBasicConnection, bluetoothGattCharacteristic, !z);
        this.timeout = "0";
        this.server = false;
        this.bytes = bArr;
        this.value = ParserUtils.bytesToHex(bArr, false);
        this.timeout = String.valueOf(j);
        this.longValue = j;
        this.server = z;
        String str = "";
        if (TextUtils.isEmpty(this.value)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Sleep until ");
            sb.append(getName(databaseHelper));
            sb.append(" value is empty ");
            if (this.longValue > 0) {
                str = " for maximum " + this.timeout + " ms";
            }
            sb.append(str);
            setDescription(sb.toString());
            return;
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Sleep until ");
        sb2.append(getName(databaseHelper));
        sb2.append(" value equals 0x");
        sb2.append(this.value);
        if (this.longValue > 0) {
            str = " for maximum " + this.timeout + " ms";
        }
        sb2.append(str);
        setDescription(sb2.toString());
    }
}
