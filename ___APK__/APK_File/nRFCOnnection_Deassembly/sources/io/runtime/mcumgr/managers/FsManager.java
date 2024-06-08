package io.runtime.mcumgr.managers;

import f.a.b;
import f.a.c;
import io.runtime.mcumgr.McuManager;
import io.runtime.mcumgr.McuMgrCallback;
import io.runtime.mcumgr.McuMgrErrorCode;
import io.runtime.mcumgr.McuMgrTransport;
import io.runtime.mcumgr.exception.InsufficientMtuException;
import io.runtime.mcumgr.exception.McuMgrErrorException;
import io.runtime.mcumgr.exception.McuMgrException;
import io.runtime.mcumgr.response.DownloadResponse;
import io.runtime.mcumgr.response.UploadResponse;
import io.runtime.mcumgr.response.fs.McuMgrFsDownloadResponse;
import io.runtime.mcumgr.response.fs.McuMgrFsUploadResponse;
import io.runtime.mcumgr.transfer.Download;
import io.runtime.mcumgr.transfer.DownloadCallback;
import io.runtime.mcumgr.transfer.TransferController;
import io.runtime.mcumgr.transfer.TransferManager;
import io.runtime.mcumgr.transfer.Upload;
import io.runtime.mcumgr.transfer.UploadCallback;
import io.runtime.mcumgr.util.CBOR;
import java.io.IOException;
import java.util.HashMap;

/* loaded from: classes.dex */
public class FsManager extends TransferManager {
    private static final int ID_FILE = 0;
    private static final b LOG = c.a((Class<?>) FsManager.class);
    public static final int STATE_DOWNLOADING = 2;
    public static final int STATE_NONE = 0;
    public static final int STATE_PAUSED = 3;
    public static final int STATE_UPLOADING = 1;
    private FileDownloadCallback mDownloadCallback;
    private final McuMgrCallback<McuMgrFsDownloadResponse> mDownloadCallbackImpl;
    private byte[] mFileData;
    private String mFileName;
    private int mOffset;
    private int mTransferState;
    private FileUploadCallback mUploadCallback;
    private final McuMgrCallback<McuMgrFsUploadResponse> mUploadCallbackImpl;

    /* loaded from: classes.dex */
    public class FileDownload extends Download {
        private String mName;

        protected FileDownload(String str, DownloadCallback downloadCallback) {
            super(downloadCallback);
            this.mName = str;
        }

        @Override // io.runtime.mcumgr.transfer.Download
        protected DownloadResponse read(int i) {
            return FsManager.this.download(this.mName, i);
        }
    }

    @Deprecated
    /* loaded from: classes.dex */
    public interface FileDownloadCallback {
        void onDownloadCanceled();

        void onDownloadFailed(McuMgrException mcuMgrException);

        void onDownloadFinished(String str, byte[] bArr);

        void onProgressChanged(int i, int i2, long j);
    }

    /* loaded from: classes.dex */
    public class FileUpload extends Upload {
        private String mName;

        protected FileUpload(String str, byte[] bArr, UploadCallback uploadCallback) {
            super(bArr, uploadCallback);
            this.mName = str;
        }

        @Override // io.runtime.mcumgr.transfer.Upload
        protected UploadResponse write(byte[] bArr, int i) {
            return FsManager.this.upload(this.mName, bArr, i);
        }
    }

    @Deprecated
    /* loaded from: classes.dex */
    public interface FileUploadCallback {
        void onProgressChanged(int i, int i2, long j);

        void onUploadCanceled();

        void onUploadFailed(McuMgrException mcuMgrException);

        void onUploadFinished();
    }

    public FsManager(McuMgrTransport mcuMgrTransport) {
        super(8, mcuMgrTransport);
        this.mTransferState = 0;
        this.mFileName = null;
        this.mOffset = 0;
        this.mUploadCallbackImpl = new McuMgrCallback<McuMgrFsUploadResponse>() { // from class: io.runtime.mcumgr.managers.FsManager.1
            @Override // io.runtime.mcumgr.McuMgrCallback
            public void onError(McuMgrException mcuMgrException) {
                if (mcuMgrException instanceof InsufficientMtuException) {
                    int mtu = ((InsufficientMtuException) mcuMgrException).getMtu();
                    if (((McuManager) FsManager.this).mMtu == mtu) {
                        mtu--;
                    }
                    if (FsManager.this.setUploadMtu(mtu)) {
                        FsManager.this.restartTransfer();
                        return;
                    }
                }
                FsManager.this.fail(mcuMgrException);
            }

            @Override // io.runtime.mcumgr.McuMgrCallback
            public void onResponse(McuMgrFsUploadResponse mcuMgrFsUploadResponse) {
                if (mcuMgrFsUploadResponse.rc != 0) {
                    FsManager.LOG.a("Upload failed due to McuManager error: {}", Integer.valueOf(mcuMgrFsUploadResponse.rc));
                    FsManager.this.fail(new McuMgrErrorException(McuMgrErrorCode.valueOf(mcuMgrFsUploadResponse.rc)));
                    return;
                }
                if (FsManager.this.mTransferState == 0) {
                    FsManager.LOG.e("Upload canceled!");
                    FsManager.this.resetTransfer();
                    FsManager.this.mUploadCallback.onUploadCanceled();
                    FsManager.this.mUploadCallback = null;
                    return;
                }
                FsManager.this.mOffset = mcuMgrFsUploadResponse.off;
                FsManager.this.mUploadCallback.onProgressChanged(FsManager.this.mOffset, FsManager.this.mFileData.length, System.currentTimeMillis());
                if (FsManager.this.mOffset == FsManager.this.mFileData.length) {
                    FsManager.LOG.e("Upload finished!");
                    FsManager.this.resetTransfer();
                    FsManager.this.mUploadCallback.onUploadFinished();
                    FsManager.this.mUploadCallback = null;
                    return;
                }
                FsManager fsManager = FsManager.this;
                fsManager.sendNext(fsManager.mOffset);
            }
        };
        this.mDownloadCallbackImpl = new McuMgrCallback<McuMgrFsDownloadResponse>() { // from class: io.runtime.mcumgr.managers.FsManager.2
            @Override // io.runtime.mcumgr.McuMgrCallback
            public void onError(McuMgrException mcuMgrException) {
                if (mcuMgrException instanceof InsufficientMtuException) {
                    int mtu = ((InsufficientMtuException) mcuMgrException).getMtu();
                    if (((McuManager) FsManager.this).mMtu == mtu) {
                        mtu--;
                    }
                    if (FsManager.this.setUploadMtu(mtu)) {
                        FsManager.this.restartTransfer();
                        return;
                    }
                }
                FsManager.this.fail(mcuMgrException);
            }

            @Override // io.runtime.mcumgr.McuMgrCallback
            public void onResponse(McuMgrFsDownloadResponse mcuMgrFsDownloadResponse) {
                if (mcuMgrFsDownloadResponse.rc != 0) {
                    FsManager.LOG.a("Download failed due to McuManager error: {}", Integer.valueOf(mcuMgrFsDownloadResponse.rc));
                    FsManager.this.fail(new McuMgrErrorException(McuMgrErrorCode.valueOf(mcuMgrFsDownloadResponse.rc)));
                    return;
                }
                if (FsManager.this.mTransferState == 0) {
                    FsManager.LOG.e("Download canceled!");
                    FsManager.this.resetTransfer();
                    FsManager.this.mDownloadCallback.onDownloadCanceled();
                    FsManager.this.mDownloadCallback = null;
                    return;
                }
                FsManager.this.mOffset = mcuMgrFsDownloadResponse.off;
                if (FsManager.this.mOffset == 0) {
                    FsManager.this.mFileData = new byte[mcuMgrFsDownloadResponse.len];
                }
                System.arraycopy(mcuMgrFsDownloadResponse.data, 0, FsManager.this.mFileData, FsManager.this.mOffset, mcuMgrFsDownloadResponse.data.length);
                FsManager.this.mOffset += mcuMgrFsDownloadResponse.data.length;
                FsManager.this.mDownloadCallback.onProgressChanged(FsManager.this.mOffset, FsManager.this.mFileData.length, System.currentTimeMillis());
                if (FsManager.this.mOffset == FsManager.this.mFileData.length) {
                    FsManager.LOG.e("Download finished!");
                    byte[] bArr = FsManager.this.mFileData;
                    String str = FsManager.this.mFileName;
                    FsManager.this.resetTransfer();
                    FsManager.this.mDownloadCallback.onDownloadFinished(str, bArr);
                    FsManager.this.mDownloadCallback = null;
                    return;
                }
                FsManager fsManager = FsManager.this;
                fsManager.requestNext(fsManager.mOffset);
            }
        };
    }

    private HashMap<String, Object> buildUploadPayload(String str, byte[] bArr, int i) {
        int min = Math.min(this.mMtu - calculatePacketOverhead(str, bArr, i), bArr.length - i);
        byte[] bArr2 = new byte[min];
        System.arraycopy(bArr, i, bArr2, 0, min);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("name", str);
        hashMap.put("data", bArr2);
        hashMap.put("off", Integer.valueOf(i));
        if (i == 0) {
            hashMap.put("len", Integer.valueOf(bArr.length));
        }
        return hashMap;
    }

    private int calculatePacketOverhead(String str, byte[] bArr, int i) {
        HashMap hashMap = new HashMap();
        hashMap.put("name", str);
        hashMap.put("data", new byte[0]);
        hashMap.put("off", Integer.valueOf(i));
        if (i == 0) {
            hashMap.put("len", Integer.valueOf(bArr.length));
        }
        try {
            if (getScheme().isCoap()) {
                hashMap.put("_h", new byte[]{0, 0, 0, 0, 0, 0, 0, 0});
                return CBOR.toBytes(hashMap).length + 20 + 5;
            }
            return CBOR.toBytes(hashMap).length + 8 + 2;
        } catch (IOException e2) {
            LOG.a("Error while calculating packet overhead", (Throwable) e2);
            return -1;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void fail(McuMgrException mcuMgrException) {
        if (this.mUploadCallback != null) {
            this.mUploadCallback.onUploadFailed(mcuMgrException);
        } else if (this.mDownloadCallback != null) {
            this.mDownloadCallback.onDownloadFailed(mcuMgrException);
        }
        resetTransfer();
        this.mUploadCallback = null;
        this.mDownloadCallback = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void requestNext(int i) {
        if (this.mTransferState != 2) {
            LOG.e("Fs Manager is not in the DOWNLOADING state.");
        } else {
            download(this.mFileName, i, this.mDownloadCallbackImpl);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void resetTransfer() {
        this.mTransferState = 0;
        this.mFileName = null;
        this.mOffset = 0;
        this.mFileData = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void restartTransfer() {
        this.mTransferState = 0;
        if (this.mUploadCallback != null) {
            upload(this.mFileName, this.mFileData, this.mUploadCallback);
        } else if (this.mDownloadCallback != null) {
            download(this.mFileName, this.mDownloadCallback);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void sendNext(int i) {
        if (this.mTransferState != 1) {
            LOG.e("Fs Manager is not in the UPLOADING state.");
        } else {
            upload(this.mFileName, this.mFileData, i, this.mUploadCallbackImpl);
        }
    }

    @Deprecated
    public synchronized void cancelTransfer() {
        if (this.mTransferState == 0) {
            LOG.e("File transfer is not in progress");
        } else if (this.mTransferState == 3) {
            LOG.e("Upload canceled!");
            resetTransfer();
            if (this.mUploadCallback != null) {
                this.mUploadCallback.onUploadCanceled();
                this.mUploadCallback = null;
            }
            if (this.mDownloadCallback != null) {
                this.mDownloadCallback.onDownloadCanceled();
                this.mDownloadCallback = null;
            }
        } else {
            resetTransfer();
        }
    }

    @Deprecated
    public synchronized void continueTransfer() {
        if (this.mTransferState == 3) {
            LOG.e("Continuing transfer.");
            if (this.mDownloadCallback != null) {
                this.mTransferState = 2;
                requestNext(this.mOffset);
            } else {
                this.mTransferState = 1;
                sendNext(this.mOffset);
            }
        } else {
            LOG.e("Transfer is not paused.");
        }
    }

    public void download(String str, int i, McuMgrCallback<McuMgrFsDownloadResponse> mcuMgrCallback) {
        HashMap hashMap = new HashMap();
        hashMap.put("name", str);
        hashMap.put("off", Integer.valueOf(i));
        send(0, 0, hashMap, McuMgrFsDownloadResponse.class, mcuMgrCallback);
    }

    public TransferController fileDownload(String str, byte[] bArr, DownloadCallback downloadCallback) {
        return startDownload(new FileDownload(str, downloadCallback));
    }

    public TransferController fileUpload(String str, byte[] bArr, UploadCallback uploadCallback) {
        return startUpload(new FileUpload(str, bArr, uploadCallback));
    }

    @Deprecated
    public synchronized int getState() {
        return this.mTransferState;
    }

    @Deprecated
    public synchronized void pauseTransfer() {
        if (this.mTransferState == 0) {
            LOG.e("File transfer is not in progress.");
        } else {
            LOG.e("Upload paused.");
            this.mTransferState = 3;
        }
    }

    public void upload(String str, byte[] bArr, int i, McuMgrCallback<McuMgrFsUploadResponse> mcuMgrCallback) {
        send(2, 0, buildUploadPayload(str, bArr, i), McuMgrFsUploadResponse.class, mcuMgrCallback);
    }

    public McuMgrFsUploadResponse upload(String str, byte[] bArr, int i) {
        return (McuMgrFsUploadResponse) send(2, 0, buildUploadPayload(str, bArr, i), McuMgrFsUploadResponse.class);
    }

    public McuMgrFsDownloadResponse download(String str, int i) {
        HashMap hashMap = new HashMap();
        hashMap.put("name", str);
        hashMap.put("off", Integer.valueOf(i));
        return (McuMgrFsDownloadResponse) send(0, 0, hashMap, McuMgrFsDownloadResponse.class);
    }

    @Deprecated
    public synchronized void upload(String str, byte[] bArr, FileUploadCallback fileUploadCallback) {
        if (this.mTransferState == 0) {
            this.mTransferState = 1;
            this.mFileName = str;
            this.mFileData = bArr;
            this.mUploadCallback = fileUploadCallback;
            sendNext(0);
            return;
        }
        LOG.e("FsManager is not ready");
    }

    @Deprecated
    public synchronized void download(String str, FileDownloadCallback fileDownloadCallback) {
        if (this.mTransferState == 0) {
            this.mTransferState = 2;
            this.mFileName = str;
            this.mDownloadCallback = fileDownloadCallback;
            requestNext(0);
            return;
        }
        LOG.e("FsManager is not ready");
    }
}
