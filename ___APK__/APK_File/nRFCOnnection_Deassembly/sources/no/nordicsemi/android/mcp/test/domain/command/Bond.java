package no.nordicsemi.android.mcp.test.domain.command;

import android.content.Context;
import no.nordicsemi.android.log.LogSession;
import no.nordicsemi.android.mcp.test.ConstantsManager;
import no.nordicsemi.android.mcp.test.Result;
import no.nordicsemi.android.mcp.test.domain.TimeoutOperation;
import no.nordicsemi.android.mcp.test.domain.enumeration.OperationResult;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root
/* loaded from: classes.dex */
public class Bond extends TimeoutOperation {
    public Bond() {
        super("Bond");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // no.nordicsemi.android.mcp.test.domain.Operation
    public OperationResult execute(Context context, ConstantsManager constantsManager, Result result, LogSession logSession) {
        return toResult(getTarget().bond(logSession));
    }

    public Bond(@Attribute(name = "description") String str) {
        super(str);
    }
}