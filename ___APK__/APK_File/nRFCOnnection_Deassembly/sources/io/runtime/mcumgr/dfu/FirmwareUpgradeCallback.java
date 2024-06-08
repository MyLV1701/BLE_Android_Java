package io.runtime.mcumgr.dfu;

import io.runtime.mcumgr.dfu.FirmwareUpgradeManager;
import io.runtime.mcumgr.exception.McuMgrException;

/* loaded from: classes.dex */
public interface FirmwareUpgradeCallback {
    void onStateChanged(FirmwareUpgradeManager.State state, FirmwareUpgradeManager.State state2);

    void onUpgradeCanceled(FirmwareUpgradeManager.State state);

    void onUpgradeCompleted();

    void onUpgradeFailed(FirmwareUpgradeManager.State state, McuMgrException mcuMgrException);

    void onUpgradeStarted(FirmwareUpgradeController firmwareUpgradeController);

    void onUploadProgressChanged(int i, int i2, long j);
}
