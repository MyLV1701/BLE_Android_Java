package no.nordicsemi.android.mcp.test.domain.command;

import android.content.Context;
import no.nordicsemi.android.log.LogSession;
import no.nordicsemi.android.mcp.test.ConstantsManager;
import no.nordicsemi.android.mcp.test.Result;
import no.nordicsemi.android.mcp.test.domain.Operation;
import no.nordicsemi.android.mcp.test.domain.enumeration.OperationResult;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root
/* loaded from: classes.dex */
public class BeginReliableWrite extends Operation {
    public BeginReliableWrite() {
        super("Begin-Reliable-Write");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // no.nordicsemi.android.mcp.test.domain.Operation
    public OperationResult execute(Context context, ConstantsManager constantsManager, Result result, LogSession logSession) {
        if (!getTarget().beginReliableWrite(logSession)) {
            logFail(result, logSession, "Beginning Reliable Write failed");
            return toResult(-1);
        }
        logSuccess(result, null, "Reliable Write requested");
        return toResult(0);
    }

    public BeginReliableWrite(@Attribute(name = "description") String str) {
        super(str);
    }
}
