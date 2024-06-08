package no.nordicsemi.android.mcp.log;

import android.net.Uri;
import no.nordicsemi.android.log.LogContract;

/* loaded from: classes.dex */
public class LocalLogContract extends LogContract {
    public static final String AUTHORITY = "no.nordicsemi.android.mcp.log";
    public static final Uri AUTHORITY_URI = Uri.parse("content://no.nordicsemi.android.mcp.log");

    /* loaded from: classes.dex */
    public static final class LocalSession {
        public static final Uri CONTENT_URI = Uri.withAppendedPath(LocalLogContract.AUTHORITY_URI, LogContract.Session.SESSION_CONTENT_DIRECTORY);
    }
}
