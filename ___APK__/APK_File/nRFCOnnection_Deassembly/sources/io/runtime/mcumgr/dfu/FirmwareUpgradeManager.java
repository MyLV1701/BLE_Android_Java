package io.runtime.mcumgr.dfu;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import f.a.b;
import f.a.c;
import io.runtime.mcumgr.McuMgrCallback;
import io.runtime.mcumgr.McuMgrScheme;
import io.runtime.mcumgr.McuMgrTransport;
import io.runtime.mcumgr.exception.McuMgrErrorException;
import io.runtime.mcumgr.exception.McuMgrException;
import io.runtime.mcumgr.exception.McuMgrTimeoutException;
import io.runtime.mcumgr.image.McuMgrImage;
import io.runtime.mcumgr.managers.DefaultManager;
import io.runtime.mcumgr.managers.ImageManager;
import io.runtime.mcumgr.response.McuMgrResponse;
import io.runtime.mcumgr.response.img.McuMgrImageStateResponse;
import io.runtime.mcumgr.transfer.TransferController;
import io.runtime.mcumgr.transfer.UploadCallback;
import java.util.Arrays;
import java.util.concurrent.Executor;

/* loaded from: classes.dex */
public class FirmwareUpgradeManager implements FirmwareUpgradeController {
    private static final b LOG = c.a((Class<?>) FirmwareUpgradeManager.class);
    private FirmwareUpgradeCallback mCallback;
    private McuMgrCallback<McuMgrImageStateResponse> mConfirmCallback;
    private DefaultManager mDefaultManager;
    private int mEstimatedSwapTime;
    private byte[] mHash;
    private byte[] mImageData;
    private ImageManager mImageManager;
    private UploadCallback mImageUploadCallback;
    private McuMgrCallback<McuMgrImageStateResponse> mImageValidateCallback;
    private FirmwareUpgradeCallback mInternalCallback;
    private Mode mMode;
    private boolean mPaused;
    private McuMgrTransport.ConnectionCallback mReconnectCallback;
    private McuMgrCallback<McuMgrResponse> mResetCallback;
    private McuMgrTransport.ConnectionObserver mResetObserver;
    private long mResetResponseTime;
    private State mState;
    private McuMgrCallback<McuMgrImageStateResponse> mTestCallback;
    private boolean mUiThreadCallbacks;
    private TransferController mUploadController;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: io.runtime.mcumgr.dfu.FirmwareUpgradeManager$9, reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass9 {
        static final /* synthetic */ int[] $SwitchMap$io$runtime$mcumgr$dfu$FirmwareUpgradeManager$Mode;
        static final /* synthetic */ int[] $SwitchMap$io$runtime$mcumgr$dfu$FirmwareUpgradeManager$State = new int[State.values().length];

        static {
            try {
                $SwitchMap$io$runtime$mcumgr$dfu$FirmwareUpgradeManager$State[State.NONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$runtime$mcumgr$dfu$FirmwareUpgradeManager$State[State.VALIDATE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$runtime$mcumgr$dfu$FirmwareUpgradeManager$State[State.RESET.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$io$runtime$mcumgr$dfu$FirmwareUpgradeManager$State[State.UPLOAD.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$io$runtime$mcumgr$dfu$FirmwareUpgradeManager$State[State.TEST.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$io$runtime$mcumgr$dfu$FirmwareUpgradeManager$State[State.CONFIRM.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            $SwitchMap$io$runtime$mcumgr$dfu$FirmwareUpgradeManager$Mode = new int[Mode.values().length];
            try {
                $SwitchMap$io$runtime$mcumgr$dfu$FirmwareUpgradeManager$Mode[Mode.CONFIRM_ONLY.ordinal()] = 1;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$io$runtime$mcumgr$dfu$FirmwareUpgradeManager$Mode[Mode.TEST_AND_CONFIRM.ordinal()] = 2;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$io$runtime$mcumgr$dfu$FirmwareUpgradeManager$Mode[Mode.TEST_ONLY.ordinal()] = 3;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class MainThreadExecutor implements Executor {
        private Handler mainThreadHandler;

        private MainThreadExecutor() {
            this.mainThreadHandler = new Handler(Looper.getMainLooper());
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            this.mainThreadHandler.post(runnable);
        }
    }

    /* loaded from: classes.dex */
    public enum Mode {
        TEST_ONLY,
        CONFIRM_ONLY,
        TEST_AND_CONFIRM
    }

    /* loaded from: classes.dex */
    public enum State {
        NONE,
        VALIDATE,
        UPLOAD,
        TEST,
        RESET,
        CONFIRM,
        SUCCESS;

        public boolean isInProgress() {
            return this == VALIDATE || this == UPLOAD || this == TEST || this == RESET || this == CONFIRM;
        }
    }

    public FirmwareUpgradeManager(McuMgrTransport mcuMgrTransport) {
        this(mcuMgrTransport, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void cancelled(State state) {
        LOG.d("Upgrade cancelled!");
        this.mState = State.NONE;
        this.mPaused = false;
        this.mInternalCallback.onUpgradeCanceled(state);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void confirm() {
        setState(State.CONFIRM);
        if (!this.mPaused) {
            this.mImageManager.confirm(this.mHash, this.mConfirmCallback);
        }
    }

    private synchronized void currentState() {
        if (this.mPaused) {
            return;
        }
        switch (AnonymousClass9.$SwitchMap$io$runtime$mcumgr$dfu$FirmwareUpgradeManager$State[this.mState.ordinal()]) {
            case 1:
                return;
            case 2:
                validate();
                break;
            case 3:
                reset();
                break;
            case 4:
                this.mUploadController.resume();
                break;
            case 5:
                test();
                break;
            case 6:
                confirm();
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void fail(McuMgrException mcuMgrException) {
        State state = this.mState;
        this.mState = State.NONE;
        this.mPaused = false;
        this.mInternalCallback.onUpgradeFailed(state, mcuMgrException);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void reset() {
        setState(State.RESET);
        if (!this.mPaused) {
            this.mDefaultManager.getTransporter().addObserver(this.mResetObserver);
            this.mDefaultManager.reset(this.mResetCallback);
        }
    }

    private synchronized void setState(State state) {
        State state2 = this.mState;
        this.mState = state;
        if (state != state2) {
            LOG.b("Moving from state {} to state {}", state2.name(), state.name());
            this.mInternalCallback.onStateChanged(state2, state);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void success() {
        this.mState = State.NONE;
        this.mPaused = false;
        this.mInternalCallback.onUpgradeCompleted();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void test() {
        setState(State.TEST);
        if (!this.mPaused) {
            this.mImageManager.test(this.mHash, this.mTestCallback);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void upload() {
        setState(State.UPLOAD);
        if (!this.mPaused) {
            this.mUploadController = this.mImageManager.imageUpload(this.mImageData, this.mImageUploadCallback);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void validate() {
        setState(State.VALIDATE);
        if (!this.mPaused) {
            this.mImageManager.list(this.mImageValidateCallback);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void verify() {
        setState(State.CONFIRM);
        if (!this.mPaused) {
            this.mImageManager.confirm(null, this.mConfirmCallback);
        }
    }

    @Override // io.runtime.mcumgr.dfu.FirmwareUpgradeController
    public synchronized void cancel() {
        if (this.mState == State.VALIDATE) {
            this.mState = State.NONE;
            this.mPaused = false;
        } else if (this.mState == State.UPLOAD) {
            this.mUploadController.cancel();
            this.mPaused = false;
        }
    }

    public synchronized int getMtu() {
        return this.mImageManager.getMtu();
    }

    public McuMgrScheme getScheme() {
        return this.mImageManager.getScheme();
    }

    public State getState() {
        return this.mState;
    }

    public McuMgrTransport getTransporter() {
        return this.mImageManager.getTransporter();
    }

    @Override // io.runtime.mcumgr.dfu.FirmwareUpgradeController
    public synchronized boolean isInProgress() {
        boolean z;
        if (this.mState.isInProgress()) {
            z = isPaused() ? false : true;
        }
        return z;
    }

    @Override // io.runtime.mcumgr.dfu.FirmwareUpgradeController
    public synchronized boolean isPaused() {
        return this.mPaused;
    }

    @Override // io.runtime.mcumgr.dfu.FirmwareUpgradeController
    public synchronized void pause() {
        if (this.mState.isInProgress()) {
            this.mPaused = true;
            if (this.mState == State.UPLOAD) {
                this.mUploadController.pause();
            }
        }
    }

    @Override // io.runtime.mcumgr.dfu.FirmwareUpgradeController
    public synchronized void resume() {
        if (this.mPaused) {
            this.mPaused = false;
            currentState();
        }
    }

    public void setCallbackOnUiThread(boolean z) {
        this.mUiThreadCallbacks = z;
    }

    public void setEstimatedSwapTime(int i) {
        this.mEstimatedSwapTime = Math.max(i, 0);
    }

    public void setFirmwareUpgradeCallback(FirmwareUpgradeCallback firmwareUpgradeCallback) {
        this.mCallback = firmwareUpgradeCallback;
    }

    public void setMode(Mode mode) {
        if (this.mState != State.NONE) {
            LOG.b("Firmware upgrade is already in progress");
        } else {
            this.mMode = mode;
        }
    }

    public void setUploadMtu(int i) {
        this.mImageManager.setUploadMtu(i);
    }

    public synchronized void start(byte[] bArr) {
        if (this.mState != State.NONE) {
            LOG.b("Firmware upgrade is already in progress");
            return;
        }
        this.mImageData = bArr;
        this.mHash = McuMgrImage.getHash(bArr);
        this.mInternalCallback.onUpgradeStarted(this);
        validate();
    }

    public FirmwareUpgradeManager(McuMgrTransport mcuMgrTransport, FirmwareUpgradeCallback firmwareUpgradeCallback) {
        this.mMode = Mode.TEST_AND_CONFIRM;
        this.mPaused = false;
        this.mUiThreadCallbacks = true;
        this.mEstimatedSwapTime = 0;
        this.mImageValidateCallback = new McuMgrCallback<McuMgrImageStateResponse>() { // from class: io.runtime.mcumgr.dfu.FirmwareUpgradeManager.1
            @Override // io.runtime.mcumgr.McuMgrCallback
            public void onError(McuMgrException mcuMgrException) {
                FirmwareUpgradeManager.this.fail(mcuMgrException);
            }

            @Override // io.runtime.mcumgr.McuMgrCallback
            public void onResponse(McuMgrImageStateResponse mcuMgrImageStateResponse) {
                FirmwareUpgradeManager.LOG.b("Validation response: {}", mcuMgrImageStateResponse.toString());
                if (!mcuMgrImageStateResponse.isSuccess()) {
                    FirmwareUpgradeManager.this.fail(new McuMgrErrorException(mcuMgrImageStateResponse.getReturnCode()));
                    return;
                }
                if (FirmwareUpgradeManager.this.mState == State.NONE) {
                    FirmwareUpgradeManager.this.cancelled(State.VALIDATE);
                    return;
                }
                McuMgrImageStateResponse.ImageSlot[] imageSlotArr = mcuMgrImageStateResponse.images;
                if (imageSlotArr == null) {
                    FirmwareUpgradeManager.LOG.a("Missing images information: {}", mcuMgrImageStateResponse.toString());
                    FirmwareUpgradeManager.this.fail(new McuMgrException("Missing images information"));
                    return;
                }
                if (imageSlotArr.length > 0 && Arrays.equals(FirmwareUpgradeManager.this.mHash, imageSlotArr[0].hash)) {
                    if (imageSlotArr[0].confirmed) {
                        FirmwareUpgradeManager.this.success();
                        return;
                    }
                    int i = AnonymousClass9.$SwitchMap$io$runtime$mcumgr$dfu$FirmwareUpgradeManager$Mode[FirmwareUpgradeManager.this.mMode.ordinal()];
                    if (i == 1 || i == 2) {
                        FirmwareUpgradeManager.this.confirm();
                        return;
                    } else {
                        if (i != 3) {
                            return;
                        }
                        FirmwareUpgradeManager.this.success();
                        return;
                    }
                }
                if (imageSlotArr.length > 1 && imageSlotArr[1].confirmed) {
                    FirmwareUpgradeManager.this.mImageManager.confirm(imageSlotArr[0].hash, new McuMgrCallback<McuMgrImageStateResponse>() { // from class: io.runtime.mcumgr.dfu.FirmwareUpgradeManager.1.1
                        @Override // io.runtime.mcumgr.McuMgrCallback
                        public void onError(McuMgrException mcuMgrException) {
                            FirmwareUpgradeManager.this.fail(mcuMgrException);
                        }

                        @Override // io.runtime.mcumgr.McuMgrCallback
                        public void onResponse(McuMgrImageStateResponse mcuMgrImageStateResponse2) {
                            if (!mcuMgrImageStateResponse2.isSuccess()) {
                                FirmwareUpgradeManager.this.fail(new McuMgrErrorException(mcuMgrImageStateResponse2.getReturnCode()));
                            } else {
                                FirmwareUpgradeManager.this.validate();
                            }
                        }
                    });
                    return;
                }
                if (imageSlotArr.length > 1 && imageSlotArr[1].pending) {
                    FirmwareUpgradeManager.this.mDefaultManager.getTransporter().addObserver(FirmwareUpgradeManager.this.mResetObserver);
                    FirmwareUpgradeManager.this.mDefaultManager.reset(FirmwareUpgradeManager.this.mResetCallback);
                    return;
                }
                if (imageSlotArr.length <= 1 || !Arrays.equals(FirmwareUpgradeManager.this.mHash, imageSlotArr[1].hash)) {
                    FirmwareUpgradeManager.this.upload();
                    return;
                }
                if (!imageSlotArr[1].pending) {
                    int i2 = AnonymousClass9.$SwitchMap$io$runtime$mcumgr$dfu$FirmwareUpgradeManager$Mode[FirmwareUpgradeManager.this.mMode.ordinal()];
                    if (i2 == 1) {
                        FirmwareUpgradeManager.this.confirm();
                        return;
                    } else {
                        if (i2 == 2 || i2 == 3) {
                            FirmwareUpgradeManager.this.test();
                            return;
                        }
                        return;
                    }
                }
                if (imageSlotArr[1].permanent) {
                    int i3 = AnonymousClass9.$SwitchMap$io$runtime$mcumgr$dfu$FirmwareUpgradeManager$Mode[FirmwareUpgradeManager.this.mMode.ordinal()];
                    if (i3 == 1 || i3 == 2) {
                        FirmwareUpgradeManager.this.reset();
                        return;
                    } else {
                        if (i3 != 3) {
                            return;
                        }
                        FirmwareUpgradeManager.this.fail(new McuMgrException("Image already confirmed. Can't be tested."));
                        return;
                    }
                }
                int i4 = AnonymousClass9.$SwitchMap$io$runtime$mcumgr$dfu$FirmwareUpgradeManager$Mode[FirmwareUpgradeManager.this.mMode.ordinal()];
                if (i4 == 1) {
                    FirmwareUpgradeManager.this.confirm();
                } else if (i4 == 2 || i4 == 3) {
                    FirmwareUpgradeManager.this.reset();
                }
            }
        };
        this.mTestCallback = new McuMgrCallback<McuMgrImageStateResponse>() { // from class: io.runtime.mcumgr.dfu.FirmwareUpgradeManager.2
            @Override // io.runtime.mcumgr.McuMgrCallback
            public void onError(McuMgrException mcuMgrException) {
                FirmwareUpgradeManager.this.fail(mcuMgrException);
            }

            @Override // io.runtime.mcumgr.McuMgrCallback
            public void onResponse(McuMgrImageStateResponse mcuMgrImageStateResponse) {
                FirmwareUpgradeManager.LOG.b("Test response: {}", mcuMgrImageStateResponse.toString());
                if (!mcuMgrImageStateResponse.isSuccess()) {
                    FirmwareUpgradeManager.this.fail(new McuMgrErrorException(mcuMgrImageStateResponse.getReturnCode()));
                    return;
                }
                McuMgrImageStateResponse.ImageSlot[] imageSlotArr = mcuMgrImageStateResponse.images;
                if (imageSlotArr.length != 2) {
                    FirmwareUpgradeManager.this.fail(new McuMgrException("Test response does not contain enough info"));
                } else if (!imageSlotArr[1].pending) {
                    FirmwareUpgradeManager.this.fail(new McuMgrException("Tested image is not in a pending state."));
                } else {
                    FirmwareUpgradeManager.this.reset();
                }
            }
        };
        this.mResetObserver = new McuMgrTransport.ConnectionObserver() { // from class: io.runtime.mcumgr.dfu.FirmwareUpgradeManager.3
            @Override // io.runtime.mcumgr.McuMgrTransport.ConnectionObserver
            public void onConnected() {
            }

            @Override // io.runtime.mcumgr.McuMgrTransport.ConnectionObserver
            public void onDisconnected() {
                FirmwareUpgradeManager.this.mDefaultManager.getTransporter().removeObserver(FirmwareUpgradeManager.this.mResetObserver);
                FirmwareUpgradeManager.LOG.d("Device disconnected.");
                Runnable runnable = new Runnable() { // from class: io.runtime.mcumgr.dfu.FirmwareUpgradeManager.3.1
                    @Override // java.lang.Runnable
                    public void run() {
                        FirmwareUpgradeManager.this.mDefaultManager.getTransporter().connect(FirmwareUpgradeManager.this.mReconnectCallback);
                    }
                };
                long elapsedRealtime = FirmwareUpgradeManager.this.mEstimatedSwapTime - (SystemClock.elapsedRealtime() - FirmwareUpgradeManager.this.mResetResponseTime);
                if (elapsedRealtime > 0) {
                    FirmwareUpgradeManager.LOG.b("Waiting for estimated swap time {}ms", Integer.valueOf(FirmwareUpgradeManager.this.mEstimatedSwapTime));
                    new Handler().postDelayed(runnable, elapsedRealtime);
                } else {
                    runnable.run();
                }
            }
        };
        this.mReconnectCallback = new McuMgrTransport.ConnectionCallback() { // from class: io.runtime.mcumgr.dfu.FirmwareUpgradeManager.4
            public void continueUpgrade() {
                int i = AnonymousClass9.$SwitchMap$io$runtime$mcumgr$dfu$FirmwareUpgradeManager$State[FirmwareUpgradeManager.this.mState.ordinal()];
                if (i == 1) {
                    FirmwareUpgradeManager.this.cancelled(State.VALIDATE);
                    return;
                }
                if (i == 2) {
                    FirmwareUpgradeManager.this.validate();
                    return;
                }
                if (i != 3) {
                    return;
                }
                int i2 = AnonymousClass9.$SwitchMap$io$runtime$mcumgr$dfu$FirmwareUpgradeManager$Mode[FirmwareUpgradeManager.this.mMode.ordinal()];
                if (i2 != 1) {
                    if (i2 == 2) {
                        FirmwareUpgradeManager.this.verify();
                        return;
                    } else if (i2 != 3) {
                        return;
                    }
                }
                FirmwareUpgradeManager.this.success();
            }

            @Override // io.runtime.mcumgr.McuMgrTransport.ConnectionCallback
            public void onConnected() {
                FirmwareUpgradeManager.LOG.d("Reconnect successful.");
                continueUpgrade();
            }

            @Override // io.runtime.mcumgr.McuMgrTransport.ConnectionCallback
            public void onDeferred() {
                FirmwareUpgradeManager.LOG.d("Reconnect deferred.");
                continueUpgrade();
            }

            @Override // io.runtime.mcumgr.McuMgrTransport.ConnectionCallback
            public void onError(Throwable th) {
                FirmwareUpgradeManager.LOG.d("Reconnect failed.");
                FirmwareUpgradeManager.this.fail(new McuMgrException(th));
            }
        };
        this.mResetCallback = new McuMgrCallback<McuMgrResponse>() { // from class: io.runtime.mcumgr.dfu.FirmwareUpgradeManager.5
            @Override // io.runtime.mcumgr.McuMgrCallback
            public void onError(McuMgrException mcuMgrException) {
                FirmwareUpgradeManager.this.fail(mcuMgrException);
            }

            @Override // io.runtime.mcumgr.McuMgrCallback
            public void onResponse(McuMgrResponse mcuMgrResponse) {
                if (!mcuMgrResponse.isSuccess()) {
                    FirmwareUpgradeManager.this.fail(new McuMgrErrorException(mcuMgrResponse.getReturnCode()));
                    return;
                }
                FirmwareUpgradeManager.this.mResetResponseTime = SystemClock.elapsedRealtime();
                FirmwareUpgradeManager.LOG.d("Reset request success. Waiting for disconnect...");
            }
        };
        this.mConfirmCallback = new McuMgrCallback<McuMgrImageStateResponse>() { // from class: io.runtime.mcumgr.dfu.FirmwareUpgradeManager.6
            private static final int MAX_ATTEMPTS = 2;
            private int mAttempts = 0;

            @Override // io.runtime.mcumgr.McuMgrCallback
            public void onError(McuMgrException mcuMgrException) {
                if (mcuMgrException instanceof McuMgrTimeoutException) {
                    int i = this.mAttempts;
                    this.mAttempts = i + 1;
                    if (i < 2) {
                        FirmwareUpgradeManager.LOG.b("Connection timeout. Retrying...");
                        FirmwareUpgradeManager.this.verify();
                        return;
                    }
                }
                FirmwareUpgradeManager.this.fail(mcuMgrException);
            }

            @Override // io.runtime.mcumgr.McuMgrCallback
            public void onResponse(McuMgrImageStateResponse mcuMgrImageStateResponse) {
                this.mAttempts = 0;
                FirmwareUpgradeManager.LOG.b("Confirm response: {}", mcuMgrImageStateResponse.toString());
                if (!mcuMgrImageStateResponse.isSuccess()) {
                    FirmwareUpgradeManager.this.fail(new McuMgrErrorException(mcuMgrImageStateResponse.getReturnCode()));
                    return;
                }
                if (mcuMgrImageStateResponse.images.length == 0) {
                    FirmwareUpgradeManager.this.fail(new McuMgrException("Confirm response does not contain enough info"));
                    return;
                }
                int i = AnonymousClass9.$SwitchMap$io$runtime$mcumgr$dfu$FirmwareUpgradeManager$Mode[FirmwareUpgradeManager.this.mMode.ordinal()];
                if (i == 1) {
                    McuMgrImageStateResponse.ImageSlot[] imageSlotArr = mcuMgrImageStateResponse.images;
                    if (imageSlotArr.length != 2) {
                        FirmwareUpgradeManager.this.fail(new McuMgrException("Confirm response does not contain enough info"));
                        return;
                    } else if (!imageSlotArr[1].pending) {
                        FirmwareUpgradeManager.this.fail(new McuMgrException("Image is not in a confirmed state."));
                        return;
                    } else {
                        FirmwareUpgradeManager.this.reset();
                        return;
                    }
                }
                if (i != 2) {
                    return;
                }
                if (!Arrays.equals(FirmwareUpgradeManager.this.mHash, mcuMgrImageStateResponse.images[0].hash)) {
                    FirmwareUpgradeManager.this.fail(new McuMgrException("Device failed to boot into new image"));
                } else if (!mcuMgrImageStateResponse.images[0].confirmed) {
                    FirmwareUpgradeManager.this.fail(new McuMgrException("Image is not in a confirmed state."));
                } else {
                    FirmwareUpgradeManager.this.success();
                }
            }
        };
        this.mImageUploadCallback = new UploadCallback() { // from class: io.runtime.mcumgr.dfu.FirmwareUpgradeManager.7
            @Override // io.runtime.mcumgr.transfer.UploadCallback
            public void onUploadCanceled() {
                FirmwareUpgradeManager.this.cancelled(State.UPLOAD);
            }

            @Override // io.runtime.mcumgr.transfer.UploadCallback
            public void onUploadCompleted() {
                int i = AnonymousClass9.$SwitchMap$io$runtime$mcumgr$dfu$FirmwareUpgradeManager$Mode[FirmwareUpgradeManager.this.mMode.ordinal()];
                if (i == 1) {
                    FirmwareUpgradeManager.this.confirm();
                } else if (i == 2 || i == 3) {
                    FirmwareUpgradeManager.this.test();
                }
            }

            @Override // io.runtime.mcumgr.transfer.UploadCallback
            public void onUploadFailed(McuMgrException mcuMgrException) {
                FirmwareUpgradeManager.this.fail(mcuMgrException);
            }

            @Override // io.runtime.mcumgr.transfer.UploadCallback
            public void onUploadProgressChanged(int i, int i2, long j) {
                FirmwareUpgradeManager.this.mInternalCallback.onUploadProgressChanged(i, i2, j);
            }
        };
        this.mInternalCallback = new FirmwareUpgradeCallback() { // from class: io.runtime.mcumgr.dfu.FirmwareUpgradeManager.8
            private MainThreadExecutor mMainThreadExecutor;

            private MainThreadExecutor getMainThreadExecutor() {
                if (this.mMainThreadExecutor == null) {
                    this.mMainThreadExecutor = new MainThreadExecutor();
                }
                return this.mMainThreadExecutor;
            }

            @Override // io.runtime.mcumgr.dfu.FirmwareUpgradeCallback
            public void onStateChanged(final State state, final State state2) {
                if (FirmwareUpgradeManager.this.mCallback == null) {
                    return;
                }
                if (!FirmwareUpgradeManager.this.mUiThreadCallbacks) {
                    FirmwareUpgradeManager.this.mCallback.onStateChanged(state, state2);
                } else {
                    getMainThreadExecutor().execute(new Runnable() { // from class: io.runtime.mcumgr.dfu.FirmwareUpgradeManager.8.2
                        @Override // java.lang.Runnable
                        public void run() {
                            FirmwareUpgradeManager.this.mCallback.onStateChanged(state, state2);
                        }
                    });
                }
            }

            @Override // io.runtime.mcumgr.dfu.FirmwareUpgradeCallback
            public void onUpgradeCanceled(final State state) {
                if (FirmwareUpgradeManager.this.mCallback == null) {
                    return;
                }
                if (!FirmwareUpgradeManager.this.mUiThreadCallbacks) {
                    FirmwareUpgradeManager.this.mCallback.onUpgradeCanceled(state);
                } else {
                    getMainThreadExecutor().execute(new Runnable() { // from class: io.runtime.mcumgr.dfu.FirmwareUpgradeManager.8.5
                        @Override // java.lang.Runnable
                        public void run() {
                            FirmwareUpgradeManager.this.mCallback.onUpgradeCanceled(state);
                        }
                    });
                }
            }

            @Override // io.runtime.mcumgr.dfu.FirmwareUpgradeCallback
            public void onUpgradeCompleted() {
                if (FirmwareUpgradeManager.this.mCallback == null) {
                    return;
                }
                if (!FirmwareUpgradeManager.this.mUiThreadCallbacks) {
                    FirmwareUpgradeManager.this.mCallback.onUpgradeCompleted();
                } else {
                    getMainThreadExecutor().execute(new Runnable() { // from class: io.runtime.mcumgr.dfu.FirmwareUpgradeManager.8.3
                        @Override // java.lang.Runnable
                        public void run() {
                            FirmwareUpgradeManager.this.mCallback.onUpgradeCompleted();
                        }
                    });
                }
            }

            @Override // io.runtime.mcumgr.dfu.FirmwareUpgradeCallback
            public void onUpgradeFailed(final State state, final McuMgrException mcuMgrException) {
                if (FirmwareUpgradeManager.this.mCallback == null) {
                    return;
                }
                if (!FirmwareUpgradeManager.this.mUiThreadCallbacks) {
                    FirmwareUpgradeManager.this.mCallback.onUpgradeFailed(state, mcuMgrException);
                } else {
                    getMainThreadExecutor().execute(new Runnable() { // from class: io.runtime.mcumgr.dfu.FirmwareUpgradeManager.8.4
                        @Override // java.lang.Runnable
                        public void run() {
                            FirmwareUpgradeManager.this.mCallback.onUpgradeFailed(state, mcuMgrException);
                        }
                    });
                }
            }

            @Override // io.runtime.mcumgr.dfu.FirmwareUpgradeCallback
            public void onUpgradeStarted(final FirmwareUpgradeController firmwareUpgradeController) {
                if (FirmwareUpgradeManager.this.mCallback == null) {
                    return;
                }
                if (!FirmwareUpgradeManager.this.mUiThreadCallbacks) {
                    FirmwareUpgradeManager.this.mCallback.onUpgradeStarted(firmwareUpgradeController);
                } else {
                    getMainThreadExecutor().execute(new Runnable() { // from class: io.runtime.mcumgr.dfu.FirmwareUpgradeManager.8.1
                        @Override // java.lang.Runnable
                        public void run() {
                            FirmwareUpgradeManager.this.mCallback.onUpgradeStarted(firmwareUpgradeController);
                        }
                    });
                }
            }

            @Override // io.runtime.mcumgr.dfu.FirmwareUpgradeCallback
            public void onUploadProgressChanged(final int i, final int i2, final long j) {
                if (FirmwareUpgradeManager.this.mCallback == null) {
                    return;
                }
                if (!FirmwareUpgradeManager.this.mUiThreadCallbacks) {
                    FirmwareUpgradeManager.this.mCallback.onUploadProgressChanged(i, i2, j);
                } else {
                    getMainThreadExecutor().execute(new Runnable() { // from class: io.runtime.mcumgr.dfu.FirmwareUpgradeManager.8.6
                        @Override // java.lang.Runnable
                        public void run() {
                            FirmwareUpgradeManager.this.mCallback.onUploadProgressChanged(i, i2, j);
                        }
                    });
                }
            }
        };
        this.mState = State.NONE;
        this.mImageManager = new ImageManager(mcuMgrTransport);
        this.mDefaultManager = new DefaultManager(mcuMgrTransport);
        this.mCallback = firmwareUpgradeCallback;
    }
}
