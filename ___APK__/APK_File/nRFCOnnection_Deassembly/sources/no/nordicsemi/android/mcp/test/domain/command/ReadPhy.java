package no.nordicsemi.android.mcp.test.domain.command;

import android.content.Context;
import android.os.Build;
import no.nordicsemi.android.log.LogSession;
import no.nordicsemi.android.log.Logger;
import no.nordicsemi.android.mcp.test.ConstantsManager;
import no.nordicsemi.android.mcp.test.Result;
import no.nordicsemi.android.mcp.test.domain.TimeoutOperation;
import no.nordicsemi.android.mcp.test.domain.common.AssertPhy;
import no.nordicsemi.android.mcp.test.domain.enumeration.OperationResult;
import no.nordicsemi.android.mcp.util.PhyHelper;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.core.PersistenceException;
import org.simpleframework.xml.core.Validate;

@Root
/* loaded from: classes.dex */
public class ReadPhy extends TimeoutOperation {

    @Element(required = false)
    private AssertPhy assertValue;

    public ReadPhy() {
        super("Read-PHY");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // no.nordicsemi.android.mcp.test.domain.Operation
    public OperationResult execute(Context context, ConstantsManager constantsManager, Result result, LogSession logSession) {
        int readPhy = getTarget().readPhy(logSession);
        if (readPhy != 0) {
            logFail(result, logSession, "Reading PHY failed");
            return toResult(readPhy);
        }
        PhyHelper.Phy phy = getTarget().getPhy();
        logSuccess(result, null, "Reading PHY succeeded (" + phy + ")");
        if (this.assertValue != null) {
            Logger.a(logSession, constantsManager.decode(this.assertValue.getDescription()) + "...");
            return this.assertValue.execute(context, phy.tx, phy.rx, constantsManager, result, logSession);
        }
        return toResult(readPhy);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // no.nordicsemi.android.mcp.test.domain.common.HasExpectedAndDescription
    @Validate
    public void validate() {
        super.validate();
        if (Build.VERSION.SDK_INT < 26) {
            throw new PersistenceException("read-phy is available only on Android 8+ devices", new Object[0]);
        }
    }

    public ReadPhy(@Attribute(name = "description") String str) {
        super(str);
    }
}
