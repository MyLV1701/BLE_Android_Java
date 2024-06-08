package io.runtime.mcumgr.transfer;

import android.os.ConditionVariable;
import io.runtime.mcumgr.exception.InsufficientMtuException;
import io.runtime.mcumgr.exception.McuMgrException;
import java.util.concurrent.Callable;

/* loaded from: classes.dex */
public class TransferCallable implements Callable<Transfer>, TransferController {
    private final ConditionVariable mPauseLock = new ConditionVariable(true);
    private State mState = State.NONE;
    private Transfer mTransfer;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public enum State {
        NONE,
        TRANSFER,
        PAUSED,
        CLOSED
    }

    public TransferCallable(Transfer transfer) {
        this.mTransfer = transfer;
    }

    private synchronized void completeTransfer() {
        this.mState = State.CLOSED;
        this.mTransfer.onCompleted();
    }

    private synchronized void failTransfer(McuMgrException mcuMgrException) {
        this.mState = State.CLOSED;
        this.mTransfer.onFailed(mcuMgrException);
    }

    @Override // io.runtime.mcumgr.transfer.TransferController
    public synchronized void cancel() {
        this.mState = State.CLOSED;
        this.mTransfer.onCanceled();
    }

    public State getState() {
        return this.mState;
    }

    public Transfer getTransfer() {
        return this.mTransfer;
    }

    @Override // io.runtime.mcumgr.transfer.TransferController
    public synchronized void pause() {
        if (this.mState == State.TRANSFER) {
            this.mPauseLock.close();
        }
    }

    @Override // io.runtime.mcumgr.transfer.TransferController
    public synchronized void resume() {
        if (this.mState == State.PAUSED) {
            this.mPauseLock.open();
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.concurrent.Callable
    public Transfer call() {
        if (this.mState == State.CLOSED) {
            return this.mTransfer;
        }
        while (!this.mTransfer.isFinished()) {
            this.mPauseLock.block();
            try {
                this.mTransfer.sendNext();
                synchronized (this) {
                    if (this.mState == State.CLOSED) {
                        return this.mTransfer;
                    }
                    if (this.mTransfer.getData() != null) {
                        this.mTransfer.onProgressChanged(this.mTransfer.getOffset(), this.mTransfer.getData().length, System.currentTimeMillis());
                    } else {
                        throw new NullPointerException("Transfer data is null!");
                    }
                }
            } catch (McuMgrException e2) {
                if (!(e2 instanceof InsufficientMtuException)) {
                    failTransfer(e2);
                    return this.mTransfer;
                }
                throw ((InsufficientMtuException) e2);
            }
        }
        completeTransfer();
        return this.mTransfer;
    }
}
