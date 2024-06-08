package no.nordicsemi.android.mcp.test;

import android.content.Context;
import java.util.Iterator;
import java.util.Map;
import no.nordicsemi.android.log.LogSession;
import no.nordicsemi.android.log.Logger;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;
import no.nordicsemi.android.mcp.test.domain.Operation;
import no.nordicsemi.android.mcp.test.domain.RunTestCommand;
import no.nordicsemi.android.mcp.test.domain.Test;
import no.nordicsemi.android.mcp.test.domain.enumeration.OperationResult;

/* loaded from: classes.dex */
public class TestPerformer {
    private final Context mContext;
    private final DatabaseHelper mDatabaseHelper;
    private final Map<String, String> mGlobalVariables;
    private final LogSession mLogSession;
    private final Result mResult;
    private final Object mLock = new Object();
    private boolean mAborted = false;

    public TestPerformer(Context context, DatabaseHelper databaseHelper, Map<String, String> map, LogSession logSession, Result result) {
        this.mContext = context;
        this.mDatabaseHelper = databaseHelper;
        this.mLogSession = logSession;
        this.mResult = result;
        this.mGlobalVariables = map;
    }

    public void abort() {
        this.mAborted = true;
    }

    public boolean perform(RunTestCommand runTestCommand) {
        boolean z;
        ConstantsManager constantsManager = new ConstantsManager(this.mGlobalVariables, runTestCommand.getLocalVariables());
        Test test = runTestCommand.getTest();
        Context context = this.mContext;
        DatabaseHelper databaseHelper = this.mDatabaseHelper;
        LogSession logSession = this.mLogSession;
        Result result = this.mResult;
        Logger.a(logSession, "Starting '" + constantsManager.decode(test.getDescription()) + "' for '" + constantsManager.decode(runTestCommand.getDescription()) + "'");
        result.start(constantsManager, runTestCommand, test);
        Iterator<Operation> it = test.getOperations().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Operation next = it.next();
            Logger.a(logSession, constantsManager.decode(next.getDescription()) + "...");
            result.tab().report(constantsManager, next);
            OperationResult run = next.run(context, databaseHelper, constantsManager, result, logSession);
            if (OperationResult.SUCCESS.equals(run)) {
                result.success();
            } else if (OperationResult.WARNING.equals(run)) {
                result.warning();
            } else {
                if (OperationResult.FAIL.equals(run)) {
                    result.fail();
                    z = false;
                    break;
                }
                result.notImplemented();
            }
            if (this.mAborted) {
                result.aborted();
                break;
            }
        }
        z = true;
        Logger.v(logSession, "Cleaning up...");
        synchronized (this.mLock) {
            try {
                this.mLock.wait(2000L);
            } catch (InterruptedException unused) {
            }
        }
        if (test.getTarget() != null) {
            test.getTarget().close();
        }
        for (Operation operation : test.getOperations()) {
            if (operation.getTarget() != null) {
                operation.getTarget().close();
            }
        }
        int i = (!z || this.mAborted) ? 15 : 10;
        StringBuilder sb = new StringBuilder();
        sb.append("'");
        sb.append(constantsManager.decode(runTestCommand.getDescription()));
        sb.append("' ");
        sb.append(this.mAborted ? "aborted" : z ? "completed" : "failed");
        Logger.log(logSession, i, sb.toString());
        return z;
    }
}
