package no.nordicsemi.android.mcp.test.domain.command;

import android.os.SystemClock;
import android.text.TextUtils;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import no.nordicsemi.android.log.LogSession;
import no.nordicsemi.android.log.Logger;
import no.nordicsemi.android.mcp.ble.parser.utils.ParserUtils;
import no.nordicsemi.android.mcp.test.Result;
import no.nordicsemi.android.mcp.test.domain.Target;
import no.nordicsemi.android.mcp.test.domain.TimeoutOperation;
import no.nordicsemi.android.mcp.test.domain.common.HasBindTarget;
import no.nordicsemi.android.mcp.test.exception.TargetInUseException;
import no.nordicsemi.android.support.v18.scanner.a;
import no.nordicsemi.android.support.v18.scanner.j;
import no.nordicsemi.android.support.v18.scanner.m;
import org.simpleframework.xml.Attribute;

/* loaded from: classes.dex */
public class ScanFor extends TimeoutOperation implements HasBindTarget {
    private static final String DATA_REGEX = "^[0-9a-fA-F]+$";
    private static final int ERROR_TARGET_CONNECTED = -1;

    @Attribute(required = false)
    private String address;

    @Attribute(required = false)
    private String bindTarget;
    private Target bindTargetDevice;

    @Attribute(required = false)
    private String data;
    private String mAddressFilter;
    private a mBluetoothLeScanner;
    private String mDataFilter;
    private boolean mDeviceFound;
    private final Object mLock;
    private LogSession mLogSession;
    private Integer mMinimumRssiFilter;
    private String mNameFilter;
    private Result mResultBuilder;
    private j mScanCallback;
    private boolean mScanningStarted;
    private int mStatus;

    @Attribute(required = false)
    private String name;

    @Attribute(required = false)
    private String rssi;

    public ScanFor() {
        super("Scan-For");
        this.mLock = new Object();
        this.mDeviceFound = false;
        this.mScanningStarted = false;
        this.mStatus = 8;
        this.mScanCallback = new j() { // from class: no.nordicsemi.android.mcp.test.domain.command.ScanFor.1
            @Override // no.nordicsemi.android.support.v18.scanner.j
            public void onBatchScanResults(List<m> list) {
            }

            @Override // no.nordicsemi.android.support.v18.scanner.j
            public void onScanFailed(int i) {
                ScanFor.this.mStatus = i;
                ScanFor scanFor = ScanFor.this;
                scanFor.logException(scanFor.mResultBuilder, ScanFor.this.mLogSession, "Scanning failed (" + i + ")");
                Thread.currentThread().interrupt();
            }

            @Override // no.nordicsemi.android.support.v18.scanner.j
            public void onScanResult(int i, m mVar) {
                if (ScanFor.this.mDeviceFound) {
                    return;
                }
                if (ScanFor.this.mAddressFilter == null || mVar.d().getAddress().equals(ScanFor.this.mAddressFilter)) {
                    if (ScanFor.this.mMinimumRssiFilter == null || mVar.e() >= ScanFor.this.mMinimumRssiFilter.intValue()) {
                        String advDataToHex = mVar.f() != null ? ParserUtils.advDataToHex(mVar.f().a(), false) : "";
                        if (ScanFor.this.mDataFilter == null || advDataToHex.contains(ScanFor.this.mDataFilter)) {
                            String b2 = mVar.f() != null ? mVar.f().b() : null;
                            if (ScanFor.this.mNameFilter != null) {
                                if (b2 == null) {
                                    if (!TextUtils.isEmpty(ScanFor.this.mNameFilter)) {
                                        return;
                                    }
                                } else if (!b2.contains(ScanFor.this.mNameFilter)) {
                                    return;
                                }
                            }
                            ScanFor.this.mDeviceFound = true;
                            ScanFor.this.stopScanning();
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTimeInMillis((System.currentTimeMillis() - SystemClock.elapsedRealtime()) + (mVar.g() / 1000000));
                            ScanFor.this.mResultBuilder.appendOperationResult(String.format(Locale.US, "%1$tR:%1$tS.%1$tL %2$s %3$d %4$s", calendar, mVar.d().getAddress(), Integer.valueOf(mVar.e()), advDataToHex));
                            ScanFor scanFor = ScanFor.this;
                            scanFor.logSuccess(scanFor.mResultBuilder, ScanFor.this.mLogSession, "Device found: " + mVar.d().getAddress());
                            if (ScanFor.this.bindTargetDevice != null) {
                                try {
                                    ScanFor.this.bindTargetDevice.setAddress(mVar.d().getAddress());
                                } catch (TargetInUseException e2) {
                                    ScanFor.this.mStatus = -1;
                                    ScanFor scanFor2 = ScanFor.this;
                                    scanFor2.logException(scanFor2.mResultBuilder, ScanFor.this.mLogSession, e2);
                                    Thread.currentThread().interrupt();
                                    return;
                                }
                            }
                            ScanFor.this.mStatus = 0;
                            synchronized (ScanFor.this.mLock) {
                                ScanFor.this.mLock.notifyAll();
                            }
                        }
                    }
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopScanning() {
        if (this.mScanningStarted) {
            this.mBluetoothLeScanner.a(this.mScanCallback);
            Logger.v(this.mLogSession, "Stopping scanning...");
            Logger.d(this.mLogSession, "scanner.stopScan(callback)");
            this.mScanningStarted = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:38:0x010b  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x015e A[EXC_TOP_SPLITTER, LOOP:0: B:44:0x015e->B:47:0x0162, LOOP_START, SYNTHETIC] */
    @Override // no.nordicsemi.android.mcp.test.domain.Operation
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public no.nordicsemi.android.mcp.test.domain.enumeration.OperationResult execute(android.content.Context r6, no.nordicsemi.android.mcp.test.ConstantsManager r7, no.nordicsemi.android.mcp.test.Result r8, no.nordicsemi.android.log.LogSession r9) {
        /*
            Method dump skipped, instructions count: 417
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: no.nordicsemi.android.mcp.test.domain.command.ScanFor.execute(android.content.Context, no.nordicsemi.android.mcp.test.ConstantsManager, no.nordicsemi.android.mcp.test.Result, no.nordicsemi.android.log.LogSession):no.nordicsemi.android.mcp.test.domain.enumeration.OperationResult");
    }

    @Override // no.nordicsemi.android.mcp.test.domain.common.HasBindTarget
    public Target getBindTarget() {
        return this.bindTargetDevice;
    }

    @Override // no.nordicsemi.android.mcp.test.domain.common.HasBindTarget
    public String getBindTargetId() {
        return this.bindTarget;
    }

    @Override // no.nordicsemi.android.mcp.test.domain.Operation
    public boolean isTargetRequired() {
        return false;
    }

    @Override // no.nordicsemi.android.mcp.test.domain.common.HasBindTarget
    public void setBindTarget(Target target) {
        this.bindTargetDevice = target;
    }

    @Override // no.nordicsemi.android.mcp.test.domain.Operation, no.nordicsemi.android.mcp.test.domain.common.HasTarget
    public boolean usesTestTarget() {
        return false;
    }

    public ScanFor(@Attribute(name = "description") String str) {
        super(str);
        this.mLock = new Object();
        this.mDeviceFound = false;
        this.mScanningStarted = false;
        this.mStatus = 8;
        this.mScanCallback = new j() { // from class: no.nordicsemi.android.mcp.test.domain.command.ScanFor.1
            @Override // no.nordicsemi.android.support.v18.scanner.j
            public void onBatchScanResults(List<m> list) {
            }

            @Override // no.nordicsemi.android.support.v18.scanner.j
            public void onScanFailed(int i) {
                ScanFor.this.mStatus = i;
                ScanFor scanFor = ScanFor.this;
                scanFor.logException(scanFor.mResultBuilder, ScanFor.this.mLogSession, "Scanning failed (" + i + ")");
                Thread.currentThread().interrupt();
            }

            @Override // no.nordicsemi.android.support.v18.scanner.j
            public void onScanResult(int i, m mVar) {
                if (ScanFor.this.mDeviceFound) {
                    return;
                }
                if (ScanFor.this.mAddressFilter == null || mVar.d().getAddress().equals(ScanFor.this.mAddressFilter)) {
                    if (ScanFor.this.mMinimumRssiFilter == null || mVar.e() >= ScanFor.this.mMinimumRssiFilter.intValue()) {
                        String advDataToHex = mVar.f() != null ? ParserUtils.advDataToHex(mVar.f().a(), false) : "";
                        if (ScanFor.this.mDataFilter == null || advDataToHex.contains(ScanFor.this.mDataFilter)) {
                            String b2 = mVar.f() != null ? mVar.f().b() : null;
                            if (ScanFor.this.mNameFilter != null) {
                                if (b2 == null) {
                                    if (!TextUtils.isEmpty(ScanFor.this.mNameFilter)) {
                                        return;
                                    }
                                } else if (!b2.contains(ScanFor.this.mNameFilter)) {
                                    return;
                                }
                            }
                            ScanFor.this.mDeviceFound = true;
                            ScanFor.this.stopScanning();
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTimeInMillis((System.currentTimeMillis() - SystemClock.elapsedRealtime()) + (mVar.g() / 1000000));
                            ScanFor.this.mResultBuilder.appendOperationResult(String.format(Locale.US, "%1$tR:%1$tS.%1$tL %2$s %3$d %4$s", calendar, mVar.d().getAddress(), Integer.valueOf(mVar.e()), advDataToHex));
                            ScanFor scanFor = ScanFor.this;
                            scanFor.logSuccess(scanFor.mResultBuilder, ScanFor.this.mLogSession, "Device found: " + mVar.d().getAddress());
                            if (ScanFor.this.bindTargetDevice != null) {
                                try {
                                    ScanFor.this.bindTargetDevice.setAddress(mVar.d().getAddress());
                                } catch (TargetInUseException e2) {
                                    ScanFor.this.mStatus = -1;
                                    ScanFor scanFor2 = ScanFor.this;
                                    scanFor2.logException(scanFor2.mResultBuilder, ScanFor.this.mLogSession, e2);
                                    Thread.currentThread().interrupt();
                                    return;
                                }
                            }
                            ScanFor.this.mStatus = 0;
                            synchronized (ScanFor.this.mLock) {
                                ScanFor.this.mLock.notifyAll();
                            }
                        }
                    }
                }
            }
        };
    }
}
