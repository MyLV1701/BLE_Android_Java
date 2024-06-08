package no.nordicsemi.android.mcp.test.domain.command;

import android.bluetooth.BluetoothGattCharacteristic;
import android.content.Context;
import no.nordicsemi.android.log.LogSession;
import no.nordicsemi.android.log.Logger;
import no.nordicsemi.android.mcp.test.ConstantsManager;
import no.nordicsemi.android.mcp.test.Result;
import no.nordicsemi.android.mcp.test.domain.CommOperation;
import no.nordicsemi.android.mcp.test.domain.common.AssertValue;
import no.nordicsemi.android.mcp.test.domain.enumeration.OperationResult;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root
/* loaded from: classes.dex */
public class Read extends CommOperation {

    @Element(required = false)
    private AssertValue assertValue;

    public Read() {
        super("Read");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // no.nordicsemi.android.mcp.test.domain.Operation
    public OperationResult execute(Context context, ConstantsManager constantsManager, Result result, LogSession logSession) {
        BluetoothGattCharacteristic characteristic = getCharacteristic(context, constantsManager, result, logSession);
        if (characteristic == null) {
            return toResult(-3);
        }
        int read = getTarget().read(characteristic, logSession);
        if (read != 0) {
            logFail(result, logSession, "Reading characteristic failed");
            return toResult(read);
        }
        logSuccess(result, logSession, "Reading characteristic succeeded");
        if (this.assertValue != null) {
            Logger.a(logSession, constantsManager.decode(this.assertValue.getDescription()) + "...");
            return this.assertValue.execute(context, characteristic, constantsManager, result, logSession);
        }
        return toResult(read);
    }

    public Read(@Attribute(name = "description") String str) {
        super(str);
    }
}
