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
public class TimeoutOperation extends Operation {
    private Runnable mCancelTask;
    private Looper mLooper;
    private Thread mThread;

    @Attribute(required = false)
    private int timeout;

    public TimeoutOperation(@Attribute(name = "description") String str) {
        super(str);
        this.timeout = 5000;
        this.mCancelTask = new Runnable() { // from class: no.nordicsemi.android.mcp.test.domain.TimeoutOperation.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    Thread.sleep(TimeoutOperation.this.timeout);
                    TimeoutOperation.this.mLooper.getThread().interrupt();
                    if (TimeoutOperation.this.getTarget() != null) {
                        TimeoutOperation.this.getTarget().abortOperation();
                    }
                } catch (InterruptedException unused) {
                }
            }
        };
        this.mLooper = Looper.myLooper();
    }

    private void cancelTimeout() {
        this.mThread.interrupt();
    }

    private void initTimeout() {
        this.mThread = new Thread(this.mCancelTask);
        this.mThread.start();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getTimeout() {
        return this.timeout;
    }

    @Override // no.nordicsemi.android.mcp.test.domain.Operation
    public OperationResult run(Context context, DatabaseHelper databaseHelper, ConstantsManager constantsManager, Result result, LogSession logSession) {
        Target target = getTarget();
        if (isTargetRequired() && !target.ensureCallback(context, databaseHelper, constantsManager, result, logSession)) {
            return OperationResult.FAIL;
        }
        initTimeout();
        try {
            return execute(context, constantsManager, result, logSession);
        } catch (Exception e2) {
            logException(result, logSession, e2);
            return OperationResult.FAIL;
        } finally {
            cancelTimeout();
        }
    }
}
