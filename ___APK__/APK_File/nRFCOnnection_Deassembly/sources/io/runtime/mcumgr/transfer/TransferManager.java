package io.runtime.mcumgr.transfer;

import io.runtime.mcumgr.McuManager;
import io.runtime.mcumgr.McuMgrTransport;
import io.runtime.mcumgr.exception.InsufficientMtuException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes.dex */
public class TransferManager extends McuManager {
    private ExecutorService mExecutor;

    /* JADX INFO: Access modifiers changed from: protected */
    public TransferManager(int i, McuMgrTransport mcuMgrTransport) {
        super(i, mcuMgrTransport);
    }

    private synchronized ExecutorService getTransferExecutor() {
        if (this.mExecutor == null) {
            this.mExecutor = Executors.newSingleThreadExecutor();
        }
        return this.mExecutor;
    }

    private synchronized TransferController startTransfer(Transfer transfer) {
        final TransferCallable transferCallable;
        transferCallable = new TransferCallable(transfer);
        getTransferExecutor().execute(new Runnable() { // from class: io.runtime.mcumgr.transfer.TransferManager.1
            private boolean mRetry = true;

            @Override // java.lang.Runnable
            public void run() {
                try {
                    transferCallable.call();
                } catch (InsufficientMtuException e2) {
                    if (!this.mRetry) {
                        transferCallable.getTransfer().onFailed(e2);
                        return;
                    }
                    int mtu = e2.getMtu();
                    if (((McuManager) TransferManager.this).mMtu == mtu) {
                        mtu--;
                    }
                    if (TransferManager.this.setUploadMtu(mtu)) {
                        transferCallable.getTransfer().reset();
                        this.mRetry = false;
                        run();
                        return;
                    }
                    transferCallable.getTransfer().onFailed(e2);
                }
            }
        });
        return transferCallable;
    }

    public TransferController startDownload(Download download) {
        return startTransfer(download);
    }

    public TransferController startUpload(Upload upload) {
        return startTransfer(upload);
    }
}
