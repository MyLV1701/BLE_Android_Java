package no.nordicsemi.android.mcp.test;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import c.b.a.a.a;
import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import no.nordicsemi.android.log.LogSession;
import no.nordicsemi.android.log.Logger;
import no.nordicsemi.android.mcp.domain.common.HasDescription;
import no.nordicsemi.android.mcp.test.domain.RunTestCommand;
import no.nordicsemi.android.mcp.test.domain.Test;

/* loaded from: classes.dex */
public class Result implements CharSequence {
    private static final String TAG = "Result";
    private final File mFile;
    private final DateFormat DATE_TIME_FORMAT = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.UK);
    private final StringBuilder mResultBuilder = new StringBuilder();
    private final StringBuilder mStatus = new StringBuilder();

    public Result(File file) {
        this.mFile = file;
    }

    public void aborted() {
        this.mResultBuilder.append("ABORTED\n");
        this.mStatus.setLength(0);
    }

    public Result append(String str) {
        this.mResultBuilder.append(str);
        return this;
    }

    public Result appendInfo(Context context) {
        StringBuilder sb = this.mResultBuilder;
        sb.append(this.DATE_TIME_FORMAT.format(Calendar.getInstance().getTime()));
        sb.append("\n");
        try {
            String str = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
            StringBuilder sb2 = this.mResultBuilder;
            sb2.append("Version: ");
            sb2.append(str);
            sb2.append("\n");
        } catch (Exception e2) {
            this.mResultBuilder.append("Reading version failed:\n");
            this.mResultBuilder.append(e2.getLocalizedMessage());
        }
        StringBuilder sb3 = this.mResultBuilder;
        sb3.append("Device: ");
        sb3.append(a.a());
        sb3.append(", Android version: ");
        sb3.append(Build.VERSION.RELEASE);
        sb3.append(" (");
        sb3.append(Build.DISPLAY);
        sb3.append(")");
        sb3.append("\n\n");
        return this;
    }

    public void appendOperationResult(String str) {
        StringBuilder sb = this.mResultBuilder;
        sb.append("\n");
        sb.append(str);
    }

    @Override // java.lang.CharSequence
    public char charAt(int i) {
        return this.mResultBuilder.charAt(i);
    }

    public boolean createOutputFile(LogSession logSession) {
        try {
            this.mFile.createNewFile();
            FileWriter fileWriter = new FileWriter(this.mFile);
            fileWriter.write(this.mResultBuilder.toString());
            fileWriter.flush();
            fileWriter.close();
            return true;
        } catch (Exception e2) {
            Log.w(TAG, "Error while preparing result file", e2);
            Logger.e(logSession, "Error while preparing result file");
            Logger.e(logSession, e2.getLocalizedMessage());
            Logger.setSessionMark(logSession, 3);
            return false;
        }
    }

    public Result enterTab() {
        this.mResultBuilder.append("\n   ");
        return this;
    }

    public void fail() {
        if (this.mStatus.length() > 0) {
            this.mResultBuilder.append("FAIL:\n");
            this.mResultBuilder.append((CharSequence) this.mStatus);
            this.mStatus.setLength(0);
            return;
        }
        this.mResultBuilder.append("FAIL\n");
    }

    @Override // java.lang.CharSequence
    public int length() {
        return this.mResultBuilder.length();
    }

    public void notImplemented() {
        this.mResultBuilder.append("NOT IMPLEMENTED\n");
        this.mStatus.setLength(0);
    }

    public Result report(ConstantsManager constantsManager, HasDescription hasDescription) {
        StringBuilder sb = this.mResultBuilder;
        sb.append(constantsManager.decode(hasDescription.getDescription()));
        sb.append("...");
        return this;
    }

    public Result reportInner(ConstantsManager constantsManager, HasDescription hasDescription) {
        StringBuilder sb = this.mResultBuilder;
        sb.append("\n   |- ");
        sb.append(constantsManager.decode(hasDescription.getDescription()));
        sb.append("...");
        return this;
    }

    public void setOperationStatus(String str) {
        StringBuilder sb = this.mStatus;
        sb.append("     - ");
        sb.append(str);
        sb.append("\n");
    }

    public void start(ConstantsManager constantsManager, RunTestCommand runTestCommand, Test test) {
        StringBuilder sb = this.mResultBuilder;
        sb.append("Starting '");
        sb.append(constantsManager.decode(test.getDescription()));
        sb.append("' for '");
        sb.append(constantsManager.decode(runTestCommand.getDescription()));
        sb.append("'\n");
    }

    @Override // java.lang.CharSequence
    public CharSequence subSequence(int i, int i2) {
        return this.mResultBuilder.subSequence(i, i2);
    }

    public void success() {
        this.mResultBuilder.append("OK\n");
        this.mStatus.setLength(0);
    }

    public Result tab() {
        this.mResultBuilder.append("   ");
        return this;
    }

    @Override // java.lang.CharSequence
    public String toString() {
        return this.mResultBuilder.toString();
    }

    public void warning() {
        if (this.mStatus.length() > 0) {
            this.mResultBuilder.append("WARNING:\n");
            this.mResultBuilder.append((CharSequence) this.mStatus);
            this.mStatus.setLength(0);
            return;
        }
        this.mResultBuilder.append("WARNING\n");
    }
}
