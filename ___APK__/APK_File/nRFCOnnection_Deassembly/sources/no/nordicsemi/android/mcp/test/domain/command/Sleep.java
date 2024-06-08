package no.nordicsemi.android.mcp.test.domain.command;

import android.content.Context;
import no.nordicsemi.android.log.LogSession;
import no.nordicsemi.android.log.Logger;
import no.nordicsemi.android.mcp.test.ConstantsManager;
import no.nordicsemi.android.mcp.test.Result;
import no.nordicsemi.android.mcp.test.domain.Target;
import no.nordicsemi.android.mcp.test.domain.TimeoutOperation;
import no.nordicsemi.android.mcp.test.domain.enumeration.OperationResult;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root
/* loaded from: classes.dex */
public class Sleep extends TimeoutOperation {
    private final Object mLock;

    public Sleep() {
        super("Sleep");
        this.mLock = new Object();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // no.nordicsemi.android.mcp.test.domain.Operation
    public OperationResult execute(Context context, ConstantsManager constantsManager, Result result, LogSession logSession) {
        synchronized (this.mLock) {
            try {
                Logger.d(logSession, "wait(" + getTimeout() + ")");
                while (true) {
                    this.mLock.wait();
                }
            } catch (InterruptedException unused) {
                Logger.v(logSession, "Wake up!");
                return OperationResult.SUCCESS;
            }
        }
    }

    @Override // no.nordicsemi.android.mcp.test.domain.Operation
    public boolean isTargetRequired() {
        return false;
    }

    @Override // no.nordicsemi.android.mcp.test.domain.Operation
    public void setTarget(Target target) {
    }

    public Sleep(@Attribute(name = "description") String str) {
        super(str);
        this.mLock = new Object();
    }
}
