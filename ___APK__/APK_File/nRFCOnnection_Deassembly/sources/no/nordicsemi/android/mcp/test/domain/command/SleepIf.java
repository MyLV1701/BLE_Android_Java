package no.nordicsemi.android.mcp.test.domain.command;

import android.bluetooth.BluetoothGattCharacteristic;
import android.content.Context;
import no.nordicsemi.android.log.LogSession;
import no.nordicsemi.android.mcp.ble.parser.utils.ParserUtils;
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
public class SleepIf extends CommOperation {
    private byte[] bytes;

    @Attribute(required = false)
    private String value;

    @Attribute(required = false)
    private String valueString;

    public SleepIf() {
        super("Sleep-If");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // no.nordicsemi.android.mcp.test.domain.Operation
    public OperationResult execute(Context context, ConstantsManager constantsManager, Result result, LogSession logSession) {
        BluetoothGattCharacteristic characteristic = getCharacteristic(context, constantsManager, result, logSession);
        if (characteristic == null) {
            return toResult(-3);
        }
        int sleep = getTarget().sleep(characteristic, this.bytes, false, logSession);
        if (sleep != 0) {
            logFail(result, logSession, "Characteristic value equals " + ParserUtils.bytesToHex(this.bytes, true));
            return toResult(sleep);
        }
        logSuccess(result, logSession, "Characteristic value not equal to " + ParserUtils.bytesToHex(this.bytes, true));
        return toResult(sleep);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // no.nordicsemi.android.mcp.test.domain.CommOperation, no.nordicsemi.android.mcp.test.domain.common.HasExpectedAndDescription
    @Validate
    public void validate() {
        super.validate();
        if (this.value == null && this.valueString == null) {
            throw new PersistenceException("No value specified for sleep-if. Use 'value' for binary data or 'value-string' for text.", new Object[0]);
        }
        if (this.value != null && this.valueString != null) {
            throw new PersistenceException("Both value-string and value specified for sleep-if", new Object[0]);
        }
        String str = this.value;
        if (str != null) {
            this.bytes = hexStringToByteArray(str);
        } else {
            this.bytes = this.valueString.getBytes();
        }
    }

    public SleepIf(@Attribute(name = "description") String str) {
        super(str);
    }
}
