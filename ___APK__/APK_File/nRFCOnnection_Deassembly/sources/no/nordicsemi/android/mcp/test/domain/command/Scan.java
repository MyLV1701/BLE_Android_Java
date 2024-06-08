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
import no.nordicsemi.android.mcp.test.domain.TimeIntervalOperation;
import no.nordicsemi.android.mcp.test.domain.enumeration.OperationResult;
import no.nordicsemi.android.support.v18.scanner.a;
import no.nordicsemi.android.support.v18.scanner.j;
import no.nordicsemi.android.support.v18.scanner.m;
import org.simpleframework.xml.Attribute;

/* loaded from: classes.dex */
public class Scan extends TimeIntervalOperation {
    private static final String DATA_REGEX = "^[0-9a-fA-F]+$";

    @Attribute(required = false)
    private String address;

    @Attribute(required = false)
    private String data;
    private String mAddressFilter;
    private a mBluetoothLeScanner;
    private String mDataFilter;
    private LogSession mLogSession;
    private Integer mMinimumRssiFilter;
    private String mNameFilter;
    private int mNumberOfPacketsMatched;
    private int mNumberOfPacketsTotal;
    private Result mResultBuilder;
    private j mScanCallback;
    private boolean mScanningStarted;
    private int mStatus;

    @Attribute(required = false)
    private String name;

    @Attribute(required = false)
    private String rssi;

    public Scan() {
        super("Scan");
        this.mScanningStarted = false;
        this.mStatus = 0;
        this.mNumberOfPacketsTotal = 0;
        this.mNumberOfPacketsMatched = 0;
        this.mScanCallback = new j() { // from class: no.nordicsemi.android.mcp.test.domain.command.Scan.1
            @Override // no.nordicsemi.android.support.v18.scanner.j
            public void onBatchScanResults(List<m> list) {
            }

            @Override // no.nordicsemi.android.support.v18.scanner.j
            public void onScanFailed(int i) {
                Scan scan = Scan.this;
                scan.logFail(scan.mResultBuilder, Scan.this.mLogSession, "Scanning failed (" + i + ")");
                Scan.this.mStatus = i;
            }

            @Override // no.nordicsemi.android.support.v18.scanner.j
            public void onScanResult(int i, m mVar) {
                Scan.access$008(Scan.this);
                if (Scan.this.mAddressFilter == null || mVar.d().getAddress().equals(Scan.this.mAddressFilter)) {
                    if (Scan.this.mMinimumRssiFilter == null || mVar.e() >= Scan.this.mMinimumRssiFilter.intValue()) {
                        String advDataToHex = mVar.f() != null ? ParserUtils.advDataToHex(mVar.f().a(), false) : "";
                        if (Scan.this.mDataFilter == null || advDataToHex == null || advDataToHex.contains(Scan.this.mDataFilter)) {
                            String b2 = mVar.f() != null ? mVar.f().b() : null;
                            if (Scan.this.mNameFilter != null) {
                                if (b2 == null) {
                                    if (!TextUtils.isEmpty(Scan.this.mNameFilter)) {
                                        return;
                                    }
                                } else if (!b2.contains(Scan.this.mNameFilter)) {
                                    return;
                                }
                            }
                            Scan.access$508(Scan.this);
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTimeInMillis((System.currentTimeMillis() - SystemClock.elapsedRealtime()) + (mVar.g() / 1000000));
                            Scan.this.mResultBuilder.appendOperationResult(String.format(Locale.US, "%1$tR:%1$tS.%1$tL %2$s %3$d %4$s", calendar, mVar.d().getAddress(), Integer.valueOf(mVar.e()), advDataToHex));
                        }
                    }
                }
            }
        };
    }

    static /* synthetic */ int access$008(Scan scan) {
        int i = scan.mNumberOfPacketsTotal;
        scan.mNumberOfPacketsTotal = i + 1;
        return i;
    }

    static /* synthetic */ int access$508(Scan scan) {
        int i = scan.mNumberOfPacketsMatched;
        scan.mNumberOfPacketsMatched = i + 1;
        return i;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0109  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0154  */
    /* JADX WARN: Removed duplicated region for block: B:46:? A[RETURN, SYNTHETIC] */
    @Override // no.nordicsemi.android.mcp.test.domain.Operation
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public no.nordicsemi.android.mcp.test.domain.enumeration.OperationResult execute(android.content.Context r4, no.nordicsemi.android.mcp.test.ConstantsManager r5, no.nordicsemi.android.mcp.test.Result r6, no.nordicsemi.android.log.LogSession r7) {
        /*
            Method dump skipped, instructions count: 346
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: no.nordicsemi.android.mcp.test.domain.command.Scan.execute(android.content.Context, no.nordicsemi.android.mcp.test.ConstantsManager, no.nordicsemi.android.mcp.test.Result, no.nordicsemi.android.log.LogSession):no.nordicsemi.android.mcp.test.domain.enumeration.OperationResult");
    }

    @Override // no.nordicsemi.android.mcp.test.domain.Operation
    public boolean isTargetRequired() {
        return false;
    }

    @Override // no.nordicsemi.android.mcp.test.domain.TimeIntervalOperation
    protected OperationResult onIntervalFinished() {
        if (this.mScanningStarted) {
            this.mBluetoothLeScanner.a(this.mScanCallback);
            Logger.i(this.mLogSession, this.mNumberOfPacketsTotal + " packets received from which " + this.mNumberOfPacketsMatched + " matched filters");
            Logger.v(this.mLogSession, "Stopping scanning...");
            Logger.d(this.mLogSession, "scanner.stopScan(callback)");
            this.mScanningStarted = false;
        }
        this.mResultBuilder.enterTab();
        return toResult(this.mStatus);
    }

    @Override // no.nordicsemi.android.mcp.test.domain.Operation, no.nordicsemi.android.mcp.test.domain.common.HasTarget
    public boolean usesTestTarget() {
        return false;
    }

    public Scan(@Attribute(name = "description") String str) {
        super(str);
        this.mScanningStarted = false;
        this.mStatus = 0;
        this.mNumberOfPacketsTotal = 0;
        this.mNumberOfPacketsMatched = 0;
        this.mScanCallback = new j() { // from class: no.nordicsemi.android.mcp.test.domain.command.Scan.1
            @Override // no.nordicsemi.android.support.v18.scanner.j
            public void onBatchScanResults(List<m> list) {
            }

            @Override // no.nordicsemi.android.support.v18.scanner.j
            public void onScanFailed(int i) {
                Scan scan = Scan.this;
                scan.logFail(scan.mResultBuilder, Scan.this.mLogSession, "Scanning failed (" + i + ")");
                Scan.this.mStatus = i;
            }

            @Override // no.nordicsemi.android.support.v18.scanner.j
            public void onScanResult(int i, m mVar) {
                Scan.access$008(Scan.this);
                if (Scan.this.mAddressFilter == null || mVar.d().getAddress().equals(Scan.this.mAddressFilter)) {
                    if (Scan.this.mMinimumRssiFilter == null || mVar.e() >= Scan.this.mMinimumRssiFilter.intValue()) {
                        String advDataToHex = mVar.f() != null ? ParserUtils.advDataToHex(mVar.f().a(), false) : "";
                        if (Scan.this.mDataFilter == null || advDataToHex == null || advDataToHex.contains(Scan.this.mDataFilter)) {
                            String b2 = mVar.f() != null ? mVar.f().b() : null;
                            if (Scan.this.mNameFilter != null) {
                                if (b2 == null) {
                                    if (!TextUtils.isEmpty(Scan.this.mNameFilter)) {
                                        return;
                                    }
                                } else if (!b2.contains(Scan.this.mNameFilter)) {
                                    return;
                                }
                            }
                            Scan.access$508(Scan.this);
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTimeInMillis((System.currentTimeMillis() - SystemClock.elapsedRealtime()) + (mVar.g() / 1000000));
                            Scan.this.mResultBuilder.appendOperationResult(String.format(Locale.US, "%1$tR:%1$tS.%1$tL %2$s %3$d %4$s", calendar, mVar.d().getAddress(), Integer.valueOf(mVar.e()), advDataToHex));
                        }
                    }
                }
            }
        };
    }
}
