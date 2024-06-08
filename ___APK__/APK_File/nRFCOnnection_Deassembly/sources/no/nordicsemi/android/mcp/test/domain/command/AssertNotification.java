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
import org.simpleframework.xml.ElementUnion;

/* loaded from: classes.dex */
public class AssertNotification extends CommOperation {

    @Element(required = false)
    private AssertValue assertValue;

    @ElementUnion({@Element(name = "read", required = false, type = Read.class), @Element(name = "read-descriptor", required = false, type = ReadDescriptor.class), @Element(name = "write", required = false, type = Write.class), @Element(name = "write-descriptor", required = false, type = WriteDescriptor.class)})
    private CommOperation operation;

    public AssertNotification() {
        super("Assert-Notification");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // no.nordicsemi.android.mcp.test.domain.Operation
    public OperationResult execute(Context context, ConstantsManager constantsManager, Result result, LogSession logSession) {
        BluetoothGattCharacteristic characteristic = getCharacteristic(context, constantsManager, result, logSession);
        if (characteristic == null) {
            return toResult(-3);
        }
        getTarget().initWaitForNotification(characteristic, logSession);
        CommOperation commOperation = this.operation;
        if (commOperation != null) {
            if (commOperation.getTarget() == null) {
                this.operation.setTarget(getTarget());
            }
            result.tab().reportInner(constantsManager, this.operation);
            OperationResult run = this.operation.run(context, null, constantsManager, result, logSession);
            if (OperationResult.SUCCESS.equals(run)) {
                result.success();
            } else if (OperationResult.WARNING.equals(run)) {
                result.warning();
            } else {
                if (OperationResult.FAIL.equals(run)) {
                    result.fail();
                    result.tab();
                    return OperationResult.FAIL;
                }
                result.notImplemented();
            }
            result.tab();
        }
        int waitForNotification = getTarget().waitForNotification(logSession);
        if (waitForNotification != 0) {
            logFail(result, logSession, "Notification not received");
            return toResult(waitForNotification);
        }
        logSuccess(result, logSession, "Notification received");
        if (this.assertValue != null) {
            Logger.a(logSession, constantsManager.decode(this.assertValue.getDescription()) + "...");
            return this.assertValue.execute(context, characteristic, constantsManager, result, logSession);
        }
        return toResult(waitForNotification);
    }

    public AssertNotification(@Attribute(name = "description") String str) {
        super(str);
    }
}
