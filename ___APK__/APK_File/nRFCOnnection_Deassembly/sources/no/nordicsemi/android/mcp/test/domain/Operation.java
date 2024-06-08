package no.nordicsemi.android.mcp.test.domain;

import android.content.Context;
import no.nordicsemi.android.log.LogSession;
import no.nordicsemi.android.log.Logger;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;
import no.nordicsemi.android.mcp.test.ConstantsManager;
import no.nordicsemi.android.mcp.test.Result;
import no.nordicsemi.android.mcp.test.domain.common.HasExpectedAndDescription;
import no.nordicsemi.android.mcp.test.domain.common.HasTarget;
import no.nordicsemi.android.mcp.test.domain.enumeration.OperationResult;
import org.simpleframework.xml.Attribute;

/* loaded from: classes.dex */
public abstract class Operation extends HasExpectedAndDescription implements HasTarget {

    @Attribute(required = false)
    private String target;
    private Target targetDevice;

    public Operation(@Attribute(name = "description") String str) {
        super(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public OperationResult execute(Context context, ConstantsManager constantsManager, Result result, LogSession logSession) {
        Logger.w(logSession, "Operation not implemented.");
        return null;
    }

    @Override // no.nordicsemi.android.mcp.test.domain.common.HasTarget
    public Target getTarget() {
        return this.targetDevice;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getTargetId() {
        return this.target;
    }

    public boolean isTargetRequired() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean matchesTarget(Target target) {
        String str = this.target;
        return str != null && str.equals(target.getId());
    }

    public OperationResult run(Context context, DatabaseHelper databaseHelper, ConstantsManager constantsManager, Result result, LogSession logSession) {
        Target target = this.targetDevice;
        if (isTargetRequired() && !target.ensureCallback(context, databaseHelper, constantsManager, result, logSession)) {
            return OperationResult.FAIL;
        }
        try {
            return execute(context, constantsManager, result, logSession);
        } catch (Exception e2) {
            logException(result, logSession, e2);
            return OperationResult.FAIL;
        }
    }

    public void setTarget(Target target) {
        this.targetDevice = target;
    }

    @Override // no.nordicsemi.android.mcp.test.domain.common.HasTarget
    public boolean usesTestTarget() {
        return true;
    }
}
