package no.nordicsemi.android.mcp.connection.macro.loader;

import no.nordicsemi.android.mcp.connection.macro.domain.Macro;

/* loaded from: classes.dex */
public class MacroLoaderResult {
    public final Throwable exception;
    public final boolean isGroup;
    public final Macro macro;
    public final int position;

    private MacroLoaderResult(int i, Macro macro) {
        this.macro = macro;
        this.exception = null;
        this.position = i;
        this.isGroup = false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static MacroLoaderResult groupLoaded(int i) {
        return new MacroLoaderResult(i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static MacroLoaderResult loadingFailed(int i, Throwable th) {
        return new MacroLoaderResult(i, th);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static MacroLoaderResult macroLoaded(int i, Macro macro) {
        return new MacroLoaderResult(i, macro);
    }

    public boolean loadedSuccessfully() {
        return this.exception == null;
    }

    private MacroLoaderResult(int i, Throwable th) {
        this.macro = null;
        this.exception = th;
        this.position = i;
        this.isGroup = false;
    }

    private MacroLoaderResult(int i) {
        this.macro = null;
        this.exception = null;
        this.position = i;
        this.isGroup = true;
    }
}
