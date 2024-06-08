package no.nordicsemi.android.support.v18.scanner;

import java.util.List;

/* loaded from: classes.dex */
public abstract class j {
    static final int NO_ERROR = 0;
    public static final int SCAN_FAILED_ALREADY_STARTED = 1;
    public static final int SCAN_FAILED_APPLICATION_REGISTRATION_FAILED = 2;
    public static final int SCAN_FAILED_FEATURE_UNSUPPORTED = 4;
    public static final int SCAN_FAILED_INTERNAL_ERROR = 3;
    public static final int SCAN_FAILED_OUT_OF_HARDWARE_RESOURCES = 5;
    public static final int SCAN_FAILED_SCANNING_TOO_FREQUENTLY = 6;

    public void onBatchScanResults(List<m> list) {
    }

    public void onScanFailed(int i) {
    }

    public void onScanResult(int i, m mVar) {
    }
}
