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
public class RequestMTU extends TimeoutOperation {
    private int intValue;

    @Attribute
    private String value;

    public RequestMTU() {
        super("Request-MTU");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // no.nordicsemi.android.mcp.test.domain.Operation
    public OperationResult execute(Context context, ConstantsManager constantsManager, Result result, LogSession logSession) {
        int requestMtu = getTarget().requestMtu(this.intValue, logSession);
        if (requestMtu != 0) {
            logFail(result, logSession, "Requesting MTU failed");
            return toResult(requestMtu);
        }
        int mtu = getTarget().getMtu();
        if (mtu == this.intValue) {
            logSuccess(result, null, "MTU changed to " + mtu);
        } else {
            logFail(result, null, "MTU changed to " + mtu);
        }
        return toResult(mtu == this.intValue ? 0 : -1);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // no.nordicsemi.android.mcp.test.domain.common.HasExpectedAndDescription
    @Validate
    public void validate() {
        super.validate();
        if (Build.VERSION.SDK_INT >= 21) {
            try {
                this.intValue = Integer.parseInt(this.value);
                if (this.intValue < 23 || this.intValue > 517) {
                    throw new PersistenceException("MTU value must be in range <23, 517>", new Object[0]);
                }
                return;
            } catch (NumberFormatException unused) {
                throw new PersistenceException("'%s' is no a valid MTU value", this.value);
            }
        }
        throw new PersistenceException("request-mtu is available only on Android 5+ devices", new Object[0]);
    }

    public RequestMTU(@Attribute(name = "description") String str) {
        super(str);
    }
}
