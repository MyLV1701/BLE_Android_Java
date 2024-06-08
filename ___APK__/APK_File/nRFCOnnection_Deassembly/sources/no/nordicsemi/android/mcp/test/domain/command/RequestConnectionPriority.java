package no.nordicsemi.android.mcp.test.domain.command;

import android.content.Context;
import android.os.Build;
import no.nordicsemi.android.log.LogSession;
import no.nordicsemi.android.mcp.test.ConstantsManager;
import no.nordicsemi.android.mcp.test.Result;
import no.nordicsemi.android.mcp.test.domain.TimeoutOperation;
import no.nordicsemi.android.mcp.test.domain.enumeration.OperationResult;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.core.PersistenceException;
import org.simpleframework.xml.core.Validate;

@Root
/* loaded from: classes.dex */
public class RequestConnectionPriority extends TimeoutOperation {

    @Attribute
    private Type type;

    /* loaded from: classes.dex */
    public enum Type {
        LOW_POWER(2),
        HIGH(1),
        BALANCED(0);

        private int value;

        Type(int i) {
            this.value = i;
        }
    }

    public RequestConnectionPriority() {
        super("Request-Connection-Priority");
        this.type = Type.BALANCED;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // no.nordicsemi.android.mcp.test.domain.Operation
    public OperationResult execute(Context context, ConstantsManager constantsManager, Result result, LogSession logSession) {
        int requestConnectionPriority = getTarget().requestConnectionPriority(this.type.value, logSession);
        if (requestConnectionPriority != 0) {
            logFail(result, logSession, "Requesting connection priority failed");
            return toResult(requestConnectionPriority);
        }
        logSuccess(result, null, "Connection priority " + this.type.name() + " requested");
        return toResult(0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // no.nordicsemi.android.mcp.test.domain.common.HasExpectedAndDescription
    @Validate
    public void validate() {
        super.validate();
        if (Build.VERSION.SDK_INT < 21) {
            throw new PersistenceException("request-connection-priority is available only on Android 5+ devices", new Object[0]);
        }
    }

    public RequestConnectionPriority(@Attribute(name = "description") String str) {
        super(str);
        this.type = Type.BALANCED;
    }
}
