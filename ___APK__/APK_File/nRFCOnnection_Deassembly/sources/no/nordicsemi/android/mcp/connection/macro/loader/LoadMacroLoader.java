package no.nordicsemi.android.mcp.connection.macro.loader;

import a.k.b.a;
import android.content.Context;
import no.nordicsemi.android.mcp.connection.macro.domain.Macro;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.stream.Format;
import org.simpleframework.xml.stream.HyphenStyle;

/* loaded from: classes.dex */
public class LoadMacroLoader extends a<MacroLoaderResult> {
    private final DatabaseHelper mDatabaseHelper;
    private final int mMacroId;
    private final int mPosition;
    private MacroLoaderResult mResult;

    public LoadMacroLoader(Context context, DatabaseHelper databaseHelper, int i, int i2) {
        super(context);
        this.mDatabaseHelper = databaseHelper;
        this.mMacroId = i;
        this.mPosition = i2;
    }

    @Override // a.k.b.c
    protected void onStartLoading() {
        MacroLoaderResult macroLoaderResult = this.mResult;
        if (macroLoaderResult != null) {
            deliverResult(macroLoaderResult);
        } else {
            forceLoad();
        }
    }

    @Override // a.k.b.a
    public MacroLoaderResult loadInBackground() {
        try {
            MacroLoaderResult macroLoaded = MacroLoaderResult.macroLoaded(this.mPosition, (Macro) new Persister(new Format(new HyphenStyle())).read(Macro.class, this.mDatabaseHelper.getMacroXml(this.mMacroId)));
            this.mResult = macroLoaded;
            return macroLoaded;
        } catch (Exception e2) {
            int i = this.mPosition;
            Throwable cause = e2.getCause();
            Throwable th = e2;
            if (cause != null) {
                th = e2.getCause();
            }
            MacroLoaderResult loadingFailed = MacroLoaderResult.loadingFailed(i, th);
            this.mResult = loadingFailed;
            return loadingFailed;
        }
    }
}
