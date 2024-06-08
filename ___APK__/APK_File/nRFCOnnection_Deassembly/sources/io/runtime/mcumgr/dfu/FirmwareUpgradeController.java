package io.runtime.mcumgr.dfu;

/* loaded from: classes.dex */
public interface FirmwareUpgradeController {
    void cancel();

    boolean isInProgress();

    boolean isPaused();

    void pause();

    void resume();
}
