package no.nordicsemi.android.mcp.connection.macro.domain;

import a.l.a.a;
import android.content.Intent;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import no.nordicsemi.android.mcp.R;
import no.nordicsemi.android.mcp.ble.IBluetoothLeConnection;

/* loaded from: classes.dex */
public class MacroHandler {
    public static final String ACTION_MACRO_RUNNING_UPDATE = "_mru";
    public static final String ACTION_MACRO_UPDATE = "_mu";
    public static final String EXTRA_MACRO_ID = "id";
    public static final int NONE = -1;
    private final IBluetoothLeConnection mConnection;
    private final Macro mMacro;
    private final Intent mMacroRunningUpdateBroadcast;
    private OperationStatus[] mOperationStatuses;
    private int mOperationsCount;
    private final Intent mUpdateBroadcast;
    private final ExecutorService mExecutor = Executors.newSingleThreadExecutor();
    private boolean mMacroRunning = false;
    private boolean mLoop = false;
    private boolean mStopped = false;
    private int mLastCompletedOperationIndex = -1;
    private int mCurrentOperationIndex = -1;
    private final Runnable performOperations = new Runnable() { // from class: no.nordicsemi.android.mcp.connection.macro.domain.MacroHandler.1
        @Override // java.lang.Runnable
        public void run() {
            while (MacroHandler.this.mOperationStatuses != null && MacroHandler.this.mConnection.isConnected()) {
                MacroHandler macroHandler = MacroHandler.this;
                macroHandler.mCurrentOperationIndex = macroHandler.mLastCompletedOperationIndex + 1;
                if (MacroHandler.this.mCurrentOperationIndex == MacroHandler.this.mMacro.getOperations().size()) {
                    MacroHandler.this.reset();
                    MacroHandler.this.mCurrentOperationIndex = 0;
                }
                MacroHandler.this.mOperationStatuses[MacroHandler.this.mCurrentOperationIndex] = OperationStatus.IN_PROGRESS;
                MacroHandler.this.notifyObservers();
                int runOperation = MacroHandler.this.mMacro.runOperation(MacroHandler.this.mCurrentOperationIndex, MacroHandler.this.mConnection);
                if (MacroHandler.this.mOperationStatuses != null) {
                    if (runOperation == 0) {
                        MacroHandler.this.mOperationStatuses[MacroHandler.this.mCurrentOperationIndex] = OperationStatus.COMPLETED;
                        MacroHandler.access$608(MacroHandler.this);
                    } else if (runOperation != 1) {
                        MacroHandler.this.mOperationStatuses[MacroHandler.this.mCurrentOperationIndex] = MacroHandler.this.mStopped ? OperationStatus.NOT_STARTED : OperationStatus.FAILED;
                        MacroHandler.this.mMacroRunning = false;
                    } else {
                        MacroHandler.this.mOperationStatuses[MacroHandler.this.mCurrentOperationIndex] = OperationStatus.WARNING;
                        MacroHandler.access$608(MacroHandler.this);
                    }
                }
                MacroHandler.this.mCurrentOperationIndex = -1;
                MacroHandler.this.updateState();
                if (!MacroHandler.this.mMacroRunning) {
                    MacroHandler.this.notifyObservers();
                    return;
                }
            }
            MacroHandler.this.reset();
            MacroHandler.this.mMacroRunning = false;
            MacroHandler.this.mLoop = false;
        }
    };

    /* loaded from: classes.dex */
    public enum OperationStatus {
        NOT_STARTED(R.drawable.ic_macro_operation_not_started),
        IN_PROGRESS(R.drawable.ic_macro_operation_current),
        COMPLETED(R.drawable.ic_macro_operation_completed),
        WARNING(R.drawable.ic_macro_operation_warning),
        SKIPPED(R.drawable.ic_macro_operation_skipped),
        FAILED(R.drawable.ic_macro_operation_failed);

        public int res;

        OperationStatus(int i) {
            this.res = i;
        }
    }

    public MacroHandler(Macro macro, long j, IBluetoothLeConnection iBluetoothLeConnection) {
        this.mMacro = macro;
        this.mOperationsCount = macro.getOperations().size();
        this.mOperationStatuses = new OperationStatus[this.mOperationsCount];
        this.mConnection = iBluetoothLeConnection;
        this.mUpdateBroadcast = new Intent(ACTION_MACRO_UPDATE + iBluetoothLeConnection.getDevice().getAddress(null));
        this.mUpdateBroadcast.putExtra("id", j);
        this.mMacroRunningUpdateBroadcast = new Intent(ACTION_MACRO_RUNNING_UPDATE + iBluetoothLeConnection.getDevice().getAddress(null));
        this.mMacroRunningUpdateBroadcast.putExtra("id", j);
        reset();
    }

    static /* synthetic */ int access$608(MacroHandler macroHandler) {
        int i = macroHandler.mLastCompletedOperationIndex;
        macroHandler.mLastCompletedOperationIndex = i + 1;
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyObservers() {
        if (this.mMacroRunning && this.mCurrentOperationIndex == -1) {
            return;
        }
        a.a(this.mConnection.getContext()).a(this.mUpdateBroadcast);
    }

    private void notifyObserversAboutRunningState() {
        a.a(this.mConnection.getContext()).a(this.mMacroRunningUpdateBroadcast);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reset() {
        this.mCurrentOperationIndex = -1;
        this.mLastCompletedOperationIndex = -1;
        int i = 0;
        this.mStopped = false;
        if (this.mOperationStatuses == null) {
            return;
        }
        while (true) {
            OperationStatus[] operationStatusArr = this.mOperationStatuses;
            if (i >= operationStatusArr.length) {
                return;
            }
            operationStatusArr[i] = OperationStatus.NOT_STARTED;
            i++;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateState() {
        if (this.mMacroRunning && this.mLastCompletedOperationIndex == this.mOperationsCount - 1) {
            if (!this.mLoop) {
                this.mMacroRunning = false;
                notifyObserversAboutRunningState();
            } else {
                reset();
            }
        }
    }

    public void close() {
        this.mMacroRunning = false;
        this.mLoop = false;
        this.mStopped = true;
        this.mOperationStatuses = null;
        this.mLastCompletedOperationIndex = -1;
        this.mCurrentOperationIndex = -1;
    }

    public int getCurrentOperationIndex() {
        return this.mCurrentOperationIndex;
    }

    public int getLastCompletedOperationIndex() {
        return this.mLastCompletedOperationIndex;
    }

    public Macro getMacro() {
        return this.mMacro;
    }

    public OperationStatus getOperationStatus(int i) {
        OperationStatus[] operationStatusArr = this.mOperationStatuses;
        if (operationStatusArr != null) {
            return operationStatusArr[i];
        }
        return OperationStatus.NOT_STARTED;
    }

    public boolean isFinished() {
        return !this.mMacroRunning && this.mLastCompletedOperationIndex == this.mOperationsCount - 1;
    }

    public boolean isMacroRunning() {
        return this.mMacroRunning;
    }

    public boolean isOperationInProgress() {
        return this.mCurrentOperationIndex != -1;
    }

    public void play(boolean z) {
        this.mMacroRunning = true;
        this.mLoop = z;
        notifyObserversAboutRunningState();
        step();
    }

    public void skip() {
        if (this.mLastCompletedOperationIndex + 1 == this.mMacro.getOperations().size()) {
            reset();
        }
        this.mLastCompletedOperationIndex++;
        this.mOperationStatuses[this.mLastCompletedOperationIndex] = OperationStatus.SKIPPED;
        notifyObservers();
    }

    public void step() {
        this.mStopped = false;
        this.mExecutor.execute(this.performOperations);
    }

    public void stop() {
        this.mMacroRunning = false;
        this.mLoop = false;
        this.mStopped = true;
        this.mMacro.abortOperation(this.mConnection);
        notifyObserversAboutRunningState();
    }
}
