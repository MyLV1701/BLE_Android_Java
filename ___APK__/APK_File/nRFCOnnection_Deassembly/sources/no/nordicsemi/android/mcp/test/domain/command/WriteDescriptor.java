package no.nordicsemi.android.mcp.test.domain.command;

import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.content.Context;
import java.util.Iterator;
import java.util.UUID;
import no.nordicsemi.android.log.LogSession;
import no.nordicsemi.android.mcp.domain.common.exception.SyntaxException;
import no.nordicsemi.android.mcp.test.ConstantsManager;
import no.nordicsemi.android.mcp.test.Result;
import no.nordicsemi.android.mcp.test.domain.CommOperation;
import no.nordicsemi.android.mcp.test.domain.enumeration.OperationResult;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.core.PersistenceException;
import org.simpleframework.xml.core.Validate;

@Root
/* loaded from: classes.dex */
public class WriteDescriptor extends CommOperation {

    @Attribute(required = false)
    private String descriptorInstanceId;

    @Attribute
    private String uuid;

    @Attribute(required = false)
    private String value;

    @Attribute(required = false)
    private String valueString;

    public WriteDescriptor() {
        super("Write-Descriptor");
        this.descriptorInstanceId = "0";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // no.nordicsemi.android.mcp.test.domain.Operation
    public OperationResult execute(Context context, ConstantsManager constantsManager, Result result, LogSession logSession) {
        BluetoothGattCharacteristic characteristic = getCharacteristic(context, constantsManager, result, logSession);
        if (characteristic == null) {
            return toResult(-3);
        }
        String decode = constantsManager.decode(this.uuid);
        String decode2 = constantsManager.decode(this.descriptorInstanceId);
        try {
            int parseInt = Integer.parseInt(decode2);
            BluetoothGattDescriptor bluetoothGattDescriptor = null;
            int i = 0;
            Iterator<BluetoothGattDescriptor> it = characteristic.getDescriptors().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                BluetoothGattDescriptor next = it.next();
                if (decode.equalsIgnoreCase(next.getUuid().toString())) {
                    if (parseInt == i) {
                        bluetoothGattDescriptor = next;
                        break;
                    }
                    i++;
                }
            }
            if (bluetoothGattDescriptor == null) {
                logFail(result, logSession, "Descriptor (uuid=" + decode + ", instance-id=" + parseInt + ") not found");
                return toResult(-3);
            }
            String str = this.value;
            if (str != null) {
                try {
                    bluetoothGattDescriptor.setValue(hexStringToByteArray(constantsManager.decode(str)));
                } catch (PersistenceException e2) {
                    throw new SyntaxException(e2.getMessage());
                }
            } else {
                bluetoothGattDescriptor.setValue(constantsManager.decode(this.valueString).getBytes());
            }
            int write = getTarget().write(bluetoothGattDescriptor, logSession);
            if (write != 0) {
                logFail(result, logSession, "Writing descriptor failed");
                return toResult(write);
            }
            logSuccess(result, logSession, "Writing descriptor succeeded");
            return toResult(write);
        } catch (NumberFormatException unused) {
            throw new SyntaxException("The descriptor-instance-id must be a number: " + decode2);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // no.nordicsemi.android.mcp.test.domain.CommOperation, no.nordicsemi.android.mcp.test.domain.common.HasExpectedAndDescription
    @Validate
    public void validate() {
        super.validate();
        if (this.value == null && this.valueString == null) {
            throw new PersistenceException("No value specified for write. Use 'value' for binary data or 'value-string' for text.", new Object[0]);
        }
        if (this.value != null && this.valueString != null) {
            throw new PersistenceException("Both value-string and value specified for write", new Object[0]);
        }
        String str = this.value;
        if (str != null && !str.matches(ConstantsManager.CONSTANT_REGEX)) {
            hexStringToByteArray(this.value);
        }
        if (this.uuid.matches(ConstantsManager.CONSTANT_REGEX)) {
            return;
        }
        try {
            UUID.fromString(this.uuid);
        } catch (IllegalArgumentException unused) {
            throw new PersistenceException("'%s' is not a valid descriptor UUID", this.uuid);
        }
    }

    public WriteDescriptor(@Attribute(name = "description") String str) {
        super(str);
        this.descriptorInstanceId = "0";
    }
}
