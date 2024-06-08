package no.nordicsemi.android.log;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import no.nordicsemi.android.log.LogContract;

/* loaded from: classes.dex */
public class LocalLogSession implements ILogSession {
    private final Context context;
    private final Uri sessionUri;

    /* JADX INFO: Access modifiers changed from: package-private */
    public LocalLogSession(Context context, Uri uri) {
        this.context = context.getApplicationContext();
        this.sessionUri = uri;
    }

    public static LocalLogSession newSession(Context context, Uri uri, String str, String str2) {
        Uri build = uri.buildUpon().appendEncodedPath(LogContract.Session.SESSION_CONTENT_DIRECTORY).appendEncodedPath("key").appendEncodedPath(str).build();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", str2);
        try {
            Uri insert = context.getContentResolver().insert(build, contentValues);
            if (insert != null) {
                return new LocalLogSession(context, insert);
            }
            return null;
        } catch (Exception e2) {
            Log.e("LocalLogSession", "Error while creating a local log session.", e2);
            return null;
        }
    }

    public void delete() {
        try {
            this.context.getContentResolver().delete(this.sessionUri, null, null);
        } catch (Exception e2) {
            Log.e("LocalLogSession", "Error while deleting local log session.", e2);
        }
    }

    @Override // no.nordicsemi.android.log.ILogSession
    public Context getContext() {
        return this.context;
    }

    @Override // no.nordicsemi.android.log.ILogSession
    public Uri getSessionContentUri() {
        return this.sessionUri.buildUpon().appendEncodedPath("log").appendEncodedPath(LogContract.Session.Content.CONTENT).build();
    }

    @Override // no.nordicsemi.android.log.ILogSession
    public Uri getSessionEntriesUri() {
        return this.sessionUri.buildUpon().appendEncodedPath("log").build();
    }

    @Override // no.nordicsemi.android.log.ILogSession
    public Uri getSessionUri() {
        return this.sessionUri;
    }
}
