package no.nordicsemi.android.mcp.util;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import androidx.core.app.g;
import java.io.File;
import no.nordicsemi.android.log.LogContract;
import no.nordicsemi.android.mcp.R;

/* loaded from: classes.dex */
public class FileHelper {
    private static final String DEFINITIONS_JSON = "definitions.json";
    public static final String MACROS_FOLDER = "Macros";
    public static final String NORDIC_FOLDER = "Nordic Semiconductor";
    public static final String SERVER_FOLDER = "Server";
    private static final String TAG = "FileHelper";
    private static final String USER_DEFINITIONS_JSON = "user_definitions.json";

    public static Uri getContentUri(Context context, File file) {
        String absolutePath = file.getAbsolutePath();
        Uri contentUri = MediaStore.Files.getContentUri("external");
        Cursor query = context.getContentResolver().query(contentUri, new String[]{"_id"}, "_data=? ", new String[]{absolutePath}, null);
        if (query != null) {
            try {
                if (query.moveToFirst()) {
                    Uri withAppendedPath = Uri.withAppendedPath(contentUri, String.valueOf(query.getInt(query.getColumnIndex("_id"))));
                    if (query != null) {
                        query.close();
                    }
                    return withAppendedPath;
                }
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    if (query != null) {
                        try {
                            query.close();
                        } catch (Throwable unused) {
                        }
                    }
                    throw th2;
                }
            }
        }
        if (!file.exists()) {
            if (query != null) {
                query.close();
            }
            return null;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("_data", absolutePath);
        Uri insert = context.getContentResolver().insert(contentUri, contentValues);
        if (query != null) {
            query.close();
        }
        return insert;
    }

    public static File getDefinitionsFile(Context context, boolean z) {
        return new File(context.getExternalFilesDir(null), getDefinitionsFileName(z));
    }

    public static String getDefinitionsFileName(boolean z) {
        return z ? USER_DEFINITIONS_JSON : DEFINITIONS_JSON;
    }

    private static String getFileName(Context context, Uri uri) {
        String str = null;
        if (uri.getScheme() != null && uri.getScheme().equals(LogContract.Session.Content.CONTENT)) {
            Cursor query = context.getContentResolver().query(uri, null, null, null, null);
            if (query != null) {
                try {
                    if (query.moveToFirst()) {
                        str = query.getString(query.getColumnIndex("_display_name"));
                    }
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        if (query != null) {
                            try {
                                query.close();
                            } catch (Throwable unused) {
                            }
                        }
                        throw th2;
                    }
                }
            }
            if (query != null) {
                query.close();
            }
        }
        if (str != null || uri.getPath() == null) {
            return str;
        }
        String path = uri.getPath();
        int lastIndexOf = path.lastIndexOf(47);
        return lastIndexOf != -1 ? path.substring(lastIndexOf + 1) : path;
    }

    public static void showFileNotification(Context context, Uri uri, String str) {
        String fileName = getFileName(context, uri);
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setDataAndType(uri, str);
        intent.setFlags(1);
        PendingIntent activity = PendingIntent.getActivity(context, 451, intent, 0);
        g.d dVar = new g.d(context, NotificationHelper.CHANNEL_FILES);
        dVar.a(activity);
        dVar.c(fileName);
        dVar.b(context.getText(R.string.file_saved));
        dVar.a(true);
        dVar.e(true);
        dVar.e(context.getText(R.string.file_saved_title));
        dVar.d(android.R.drawable.stat_notify_sdcard);
        ((NotificationManager) context.getSystemService("notification")).notify(fileName, 1236, dVar.a());
    }
}
