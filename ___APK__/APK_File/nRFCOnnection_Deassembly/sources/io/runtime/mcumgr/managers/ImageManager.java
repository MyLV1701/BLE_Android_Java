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
import io.runtime.mcumgr.response.McuMgrResponse;
import io.runtime.mcumgr.response.UploadResponse;
import io.runtime.mcumgr.response.img.McuMgrCoreLoadResponse;
import io.runtime.mcumgr.response.img.McuMgrImageStateResponse;
import io.runtime.mcumgr.response.img.McuMgrImageUploadResponse;
import io.runtime.mcumgr.transfer.Download;
import io.runtime.mcumgr.transfer.DownloadCallback;
import io.runtime.mcumgr.transfer.TransferController;
import io.runtime.mcumgr.transfer.TransferManager;
import io.runtime.mcumgr.transfer.Upload;
import io.runtime.mcumgr.transfer.UploadCallback;
import io.runtime.mcumgr.util.CBOR;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;

/* loaded from: classes.dex */
public class ImageManager extends TransferManager {
    private static final int ID_CORELIST = 3;
    private static final int ID_CORELOAD = 4;
    private static final int ID_ERASE = 5;
    private static final int ID_ERASE_STATE = 6;
    private static final int ID_FILE = 2;
    private static final int ID_STATE = 0;
    private static final int ID_UPLOAD = 1;
    private static final int IMG_HASH_LEN = 32;
    private static final b LOG = c.a((Class<?>) ImageManager.class);
    public static final int STATE_NONE = 0;
    public static final int STATE_PAUSED = 2;
    public static final int STATE_UPLOADING = 1;
    private static final int TRUNCATED_HASH_LEN = 3;
    private byte[] mImageData;
    private ImageUploadCallback mUploadCallback;
    private final McuMgrCallback<McuMgrImageUploadResponse> mUploadCallbackImpl;
    private int mUploadOffset;
    private int mUploadState;

    /* loaded from: classes.dex */
    public class CoreDownload extends Download {
        protected CoreDownload(DownloadCallback downloadCallback) {
            super(downloadCallback);
        }

        @Override // io.runtime.mcumgr.transfer.Download
        public DownloadResponse read(int i) {
            return ImageManager.this.coreLoad(i);
        }
    }

    /* loaded from: classes.dex */
    public class ImageUpload extends Upload {
        protected ImageUpload(byte[] bArr, UploadCallback uploadCallback) {
            super(bArr, uploadCallback);
        }

        @Override // io.runtime.mcumgr.transfer.Upload
        protected UploadResponse write(byte[] bArr, int i) {
            return ImageManager.this.upload(bArr, i);
        }
    }

    @Deprecated
    /* loaded from: classes.dex */
    public interface ImageUploadCallback {
        void onProgressChanged(int i, int i2, long j);

        void onUploadCanceled();

        void onUploadFailed(McuMgrException mcuMgrException);

        void onUploadFinished();
    }

    public ImageManager(McuMgrTransport mcuMgrTransport) {
        super(1, mcuMgrTransport);
        this.mUploadState = 0;
        this.mUploadOffset = 0;
        this.mUploadCallbackImpl = new McuMgrCallback<McuMgrImageUploadResponse>() { // from class: io.runtime.mcumgr.managers.ImageManager.1
            @Override // io.runtime.mcumgr.McuMgrCallback
            public void onError(McuMgrException mcuMgrException) {
                if (mcuMgrException instanceof InsufficientMtuException) {
                    int mtu = ((InsufficientMtuException) mcuMgrException).getMtu();
                    if (((McuManager) ImageManager.this).mMtu == mtu) {
                        mtu--;
                    }
                    if (ImageManager.this.setUploadMtu(mtu)) {
                        ImageManager.this.restartUpload();
                        return;
                    }
                }
                ImageManager.this.failUpload(mcuMgrException);
            }

            @Override // io.runtime.mcumgr.McuMgrCallback
            public void onResponse(McuMgrImageUploadResponse mcuMgrImageUploadResponse) {
                if (mcuMgrImageUploadResponse.rc != 0) {
                    ImageManager.LOG.a("Upload failed due to McuManager error: {}", Integer.valueOf(mcuMgrImageUploadResponse.rc));
                    ImageManager.this.failUpload(new McuMgrErrorException(McuMgrErrorCode.valueOf(mcuMgrImageUploadResponse.rc)));
                    return;
                }
                ImageManager.this.mUploadOffset = mcuMgrImageUploadResponse.off;
                ImageManager.this.mUploadCallback.onProgressChanged(ImageManager.this.mUploadOffset, ImageManager.this.mImageData.length, System.currentTimeMillis());
                if (ImageManager.this.mUploadState == 0) {
                    ImageManager.LOG.e("Upload canceled!");
                    ImageManager.this.resetUpload();
                    ImageManager.this.mUploadCallback.onUploadCanceled();
                    ImageManager.this.mUploadCallback = null;
                    return;
                }
                if (ImageManager.this.mUploadOffset == ImageManager.this.mImageData.length) {
                    ImageManager.LOG.e("Upload finished!");
                    ImageManager.this.resetUpload();
                    ImageManager.this.mUploadCallback.onUploadFinished();
                    ImageManager.this.mUploadCallback = null;
                    return;
                }
                ImageManager imageManager = ImageManager.this;
                imageManager.sendNext(imageManager.mUploadOffset);
            }
        };
    }

    private HashMap<String, Object> buildUploadPayload(byte[] bArr, int i) {
        int min = Math.min(this.mMtu - calculatePacketOverhead(bArr, i), bArr.length - i);
        byte[] bArr2 = new byte[min];
        System.arraycopy(bArr, i, bArr2, 0, min);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("data", bArr2);
        hashMap.put("off", Integer.valueOf(i));
        if (i == 0) {
            hashMap.put("len", Integer.valueOf(bArr.length));
            try {
                hashMap.put("sha", Arrays.copyOf(MessageDigest.getInstance("SHA-256").digest(bArr), 3));
            } catch (NoSuchAlgorithmException e2) {
                e2.printStackTrace();
            }
        }
        return hashMap;
    }

    private int calculatePacketOverhead(byte[] bArr, int i) {
        HashMap hashMap = new HashMap();
        hashMap.put("data", new byte[0]);
        hashMap.put("off", Integer.valueOf(i));
        if (i == 0) {
            hashMap.put("len", Integer.valueOf(bArr.length));
            hashMap.put("sha", new byte[3]);
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
    public synchronized void failUpload(McuMgrException mcuMgrException) {
        if (this.mUploadCallback != null) {
            this.mUploadCallback.onUploadFailed(mcuMgrException);
        }
        cancelUpload();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void resetUpload() {
        this.mUploadState = 0;
        this.mUploadOffset = 0;
        this.mImageData = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void restartUpload() {
        if (this.mImageData != null && this.mUploadCallback != null) {
            byte[] bArr = this.mImageData;
            ImageUploadCallback imageUploadCallback = this.mUploadCallback;
            resetUpload();
            upload(bArr, imageUploadCallback);
            return;
        }
        LOG.a("Could not restart upload: image data or callback is null!");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void sendNext(int i) {
        if (this.mUploadState != 1) {
            LOG.e("Image Manager is not in the UPLOADING state.");
        } else {
            upload(this.mImageData, i, this.mUploadCallbackImpl);
        }
    }

    @Deprecated
    public synchronized void cancelUpload() {
        if (this.mUploadState == 0) {
            LOG.e("Image upload is not in progress");
        } else if (this.mUploadState == 2) {
            LOG.e("Upload canceled!");
            resetUpload();
            this.mUploadCallback.onUploadCanceled();
            this.mUploadCallback = null;
        }
        this.mUploadState = 0;
    }

    public void confirm(byte[] bArr, McuMgrCallback<McuMgrImageStateResponse> mcuMgrCallback) {
        HashMap hashMap = new HashMap();
        hashMap.put("confirm", true);
        if (bArr != null) {
            hashMap.put("hash", bArr);
        }
        send(2, 0, hashMap, McuMgrImageStateResponse.class, mcuMgrCallback);
    }

    @Deprecated
    public synchronized void continueUpload() {
        if (this.mUploadState == 2) {
            LOG.e("Continuing upload.");
            this.mUploadState = 1;
            sendNext(this.mUploadOffset);
        } else {
            LOG.e("Upload is not paused.");
        }
    }

    public TransferController coreDownload(DownloadCallback downloadCallback) {
        return startDownload(new CoreDownload(downloadCallback));
    }

    public void coreErase(McuMgrCallback<McuMgrResponse> mcuMgrCallback) {
        send(2, 4, null, McuMgrResponse.class, mcuMgrCallback);
    }

    public void coreList(McuMgrCallback<McuMgrResponse> mcuMgrCallback) {
        send(0, 3, null, McuMgrResponse.class, mcuMgrCallback);
    }

    public void coreLoad(int i, McuMgrCallback<McuMgrCoreLoadResponse> mcuMgrCallback) {
        HashMap hashMap = new HashMap();
        hashMap.put("off", Integer.valueOf(i));
        send(0, 4, hashMap, McuMgrCoreLoadResponse.class, mcuMgrCallback);
    }

    public void erase(McuMgrCallback<McuMgrResponse> mcuMgrCallback) {
        send(2, 5, null, McuMgrResponse.class, mcuMgrCallback);
    }

    public void eraseState(McuMgrCallback<McuMgrResponse> mcuMgrCallback) {
        send(2, 6, null, McuMgrResponse.class, mcuMgrCallback);
    }

    @Deprecated
    public synchronized int getUploadState() {
        return this.mUploadState;
    }

    public TransferController imageUpload(byte[] bArr, UploadCallback uploadCallback) {
        return startUpload(new ImageUpload(bArr, uploadCallback));
    }

    public void list(McuMgrCallback<McuMgrImageStateResponse> mcuMgrCallback) {
        send(0, 0, null, McuMgrImageStateResponse.class, mcuMgrCallback);
    }

    @Deprecated
    public synchronized void pauseUpload() {
        if (this.mUploadState == 0) {
            LOG.e("Upload is not in progress.");
        } else {
            LOG.e("Upload paused.");
            this.mUploadState = 2;
        }
    }

    public void test(byte[] bArr, McuMgrCallback<McuMgrImageStateResponse> mcuMgrCallback) {
        HashMap hashMap = new HashMap();
        hashMap.put("hash", bArr);
        hashMap.put("confirm", false);
        send(2, 0, hashMap, McuMgrImageStateResponse.class, mcuMgrCallback);
    }

    public void upload(byte[] bArr, int i, McuMgrCallback<McuMgrImageUploadResponse> mcuMgrCallback) {
        send(2, 1, buildUploadPayload(bArr, i), McuMgrImageUploadResponse.class, mcuMgrCallback);
    }

    public McuMgrResponse coreErase() {
        return send(2, 4, null, McuMgrResponse.class);
    }

    public McuMgrResponse coreList() {
        return send(0, 3, null, McuMgrResponse.class);
    }

    public McuMgrResponse erase() {
        return send(2, 5, null, McuMgrResponse.class);
    }

    public McuMgrResponse eraseState() {
        return send(2, 6, null, McuMgrResponse.class);
    }

    public McuMgrImageStateResponse list() {
        return (McuMgrImageStateResponse) send(0, 0, null, McuMgrImageStateResponse.class);
    }

    public McuMgrImageUploadResponse upload(byte[] bArr, int i) {
        return (McuMgrImageUploadResponse) send(2, 1, buildUploadPayload(bArr, i), McuMgrImageUploadResponse.class);
    }

    public McuMgrCoreLoadResponse coreLoad(int i) {
        HashMap hashMap = new HashMap();
        hashMap.put("off", Integer.valueOf(i));
        return (McuMgrCoreLoadResponse) send(0, 4, hashMap, McuMgrCoreLoadResponse.class);
    }

    public McuMgrImageStateResponse confirm(byte[] bArr) {
        HashMap hashMap = new HashMap();
        hashMap.put("confirm", true);
        if (bArr != null) {
            hashMap.put("hash", bArr);
        }
        return (McuMgrImageStateResponse) send(2, 0, hashMap, McuMgrImageStateResponse.class);
    }

    public McuMgrImageStateResponse test(byte[] bArr) {
        HashMap hashMap = new HashMap();
        hashMap.put("hash", bArr);
        hashMap.put("confirm", false);
        return (McuMgrImageStateResponse) send(2, 0, hashMap, McuMgrImageStateResponse.class);
    }

    @Deprecated
    public synchronized boolean upload(byte[] bArr, ImageUploadCallback imageUploadCallback) {
        if (this.mUploadState == 0) {
            this.mUploadState = 1;
            this.mUploadCallback = imageUploadCallback;
            this.mImageData = bArr;
            sendNext(0);
            return true;
        }
        LOG.e("An image upload is already in progress");
        return false;
    }
}
