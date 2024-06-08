package no.nordicsemi.android.mcp.test.domain.command;

import android.content.Context;
import no.nordicsemi.android.log.LogSession;
import no.nordicsemi.android.log.Logger;
import no.nordicsemi.android.mcp.test.ConstantsManager;
import no.nordicsemi.android.mcp.test.Result;
import no.nordicsemi.android.mcp.test.domain.TimeoutOperation;
import no.nordicsemi.android.mcp.test.domain.common.AssertValue;
import no.nordicsemi.android.mcp.test.domain.enumeration.OperationResult;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root
/* loaded from: classes.dex */
public class ReadRSSI extends TimeoutOperation {

    @Element(required = false)
    private AssertValue assertValue;

    public ReadRSSI() {
        super("Read-RSSI");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // no.nordicsemi.android.mcp.test.domain.Operation
    public OperationResult execute(Context context, ConstantsManager constantsManager, Result result, LogSession logSession) {
        int readRssi = getTarget().readRssi(logSession);
        if (readRssi != 0) {
            logFail(result, logSession, "Reading RSSI failed");
            return toResult(readRssi);
        }
        int rssi = getTarget().getRssi();
        logSuccess(result, null, "RSSI read: " + rssi + " dBm");
        if (this.assertValue != null) {
            Logger.a(logSession, constantsManager.decode(this.assertValue.getDescription()) + "...");
            return this.assertValue.execute(context, rssi, constantsManager, result, logSession);
        }
        return toResult(readRssi);
    }

    public ReadRSSI(@Attribute(name = "description") String str) {
        super(str);
    }
}
