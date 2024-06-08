package no.nordicsemi.android.mcp.test.domain.command;

import android.bluetooth.BluetoothGattCharacteristic;
import android.content.Context;
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
public class Write extends CommOperation {

    @Attribute(required = false)
    private Type type;

    @Attribute(required = false)
    private String value;

    @Attribute(required = false)
    private String valueString;

    /* loaded from: classes.dex */
    public enum Type {
        WRITE_REQUEST(2),
        WRITE_COMMAND(1);

        private int value;

        Type(int i) {
            this.value = i;
        }
    }

    public Write() {
        super("Write");
        this.type = Type.WRITE_REQUEST;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // no.nordicsemi.android.mcp.test.domain.Operation
    public OperationResult execute(Context context, ConstantsManager constantsManager, Result result, LogSession logSession) {
        BluetoothGattCharacteristic characteristic = getCharacteristic(context, constantsManager, result, logSession);
        if (characteristic == null) {
            return toResult(-3);
        }
        String str = this.value;
        if (str != null) {
            try {
                characteristic.setValue(hexStringToByteArray(constantsManager.decode(str)));
            } catch (PersistenceException e2) {
                throw new SyntaxException(e2.getMessage());
            }
        } else {
            characteristic.setValue(constantsManager.decode(this.valueString).getBytes());
        }
        int write = getTarget().write(characteristic, this.type.value, logSession);
        if (write != 0) {
            logFail(result, logSession, "Writing characteristic failed");
            return toResult(write);
        }
        logSuccess(result, logSession, "Writing characteristic succeeded");
        return toResult(write);
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
        if (str == null || str.matches(ConstantsManager.CONSTANT_REGEX)) {
            return;
        }
        hexStringToByteArray(this.value);
    }

    public Write(@Attribute(name = "description") String str) {
        super(str);
        this.type = Type.WRITE_REQUEST;
    }
}
