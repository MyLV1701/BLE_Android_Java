package no.nordicsemi.android.mcp.test.domain;

import android.content.Context;
import android.os.Looper;
import no.nordicsemi.android.log.LogSession;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;
import no.nordicsemi.android.mcp.test.ConstantsManager;
import no.nordicsemi.android.mcp.test.Result;
import no.nordicsemi.android.mcp.test.domain.enumeration.OperationResult;
import org.simpleframework.xml.Attribute;

/* loaded from: classes.dex */
public class TimeIntervalOperation extends Operation {
    private Exception mException;
    private Looper mLooper;
    private OperationResult mResult;
    private Thread mThread;

    @Attribute(required = false)
    private int timeout;

    public TimeIntervalOperation(@Attribute(name = "description") String str) {
        super(str);
        this.timeout = 5000;
        this.mResult = OperationResult.SUCCESS;
        this.mLooper = Looper.myLooper();
    }

    public /* synthetic */ void a(Context context, ConstantsManager constantsManager, Result result, LogSession logSession) {
        try {
            this.mResult = execute(context, constantsManager, result, logSession);
            if (OperationResult.FAIL.equals(this.mResult)) {
                this.mLooper.getThread().interrupt();
            }
        } catch (Exception e2) {
            this.mException = e2;
            this.mLooper.getThread().interrupt();
        }
    }

    protected OperationResult onIntervalFinished() {
        return OperationResult.SUCCESS;
    }

    @Override // no.nordicsemi.android.mcp.test.domain.Operation
    public OperationResult run(final Context context, DatabaseHelper databaseHelper, final ConstantsManager constantsManager, final Result result, final LogSession logSession) {
        OperationResult operationResult;
        OperationResult onIntervalFinished;
        Target target = getTarget();
        if (isTargetRequired() && !target.ensureCallback(context, databaseHelper, constantsManager, result, logSession)) {
            return OperationResult.FAIL;
        }
        try {
            try {
                this.mThread = new Thread(new Runnable() { // from class: no.nordicsemi.android.mcp.test.domain.a
                    @Override // java.lang.Runnable
                    public final void run() {
                        TimeIntervalOperation.this.a(context, constantsManager, result, logSession);
                    }
                });
                this.mThread.start();
                Thread.sleep(this.timeout);
                onIntervalFinished = onIntervalFinished();
            } catch (Exception e2) {
                e = e2;
                onIntervalFinished();
                if (this.mException != null) {
                    e = this.mException;
                }
                logException(result, logSession, e);
                operationResult = OperationResult.FAIL;
            }
            if (!OperationResult.FAIL.equals(this.mResult) && !OperationResult.FAIL.equals(onIntervalFinished)) {
                if (!OperationResult.WARNING.equals(this.mResult) && !OperationResult.WARNING.equals(onIntervalFinished)) {
                    operationResult = OperationResult.SUCCESS;
                    return operationResult;
                }
                operationResult = OperationResult.WARNING;
                return operationResult;
            }
            operationResult = OperationResult.FAIL;
            return operationResult;
        } finally {
            this.mThread.interrupt();
        }
    }
}
