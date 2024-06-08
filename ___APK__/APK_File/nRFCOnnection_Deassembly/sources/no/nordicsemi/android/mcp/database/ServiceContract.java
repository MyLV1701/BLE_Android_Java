package no.nordicsemi.android.mcp.database;

import android.net.Uri;
import android.provider.BaseColumns;
import no.nordicsemi.android.mcp.database.provider.DatabaseContentProvider;

/* loaded from: classes.dex */
public class ServiceContract {

    /* loaded from: classes.dex */
    public static final class Service implements BaseColumns, NameColumns, UuidColumns, EditableColumns {
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/no.nordicsemi.android.mcp.service";
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/no.nordicsemi.android.mcp.service";
        public static final String SERVICE_CONTENT_DIRECTORY = "service";
        public static final Uri CONTENT_URI = Uri.withAppendedPath(DatabaseContentProvider.AUTHORITY_URI, SERVICE_CONTENT_DIRECTORY);
        public static final String USER_SERVICE_CONTENT_DIRECTORY = "service/user";
        public static final Uri USER_CONTENT_URI = Uri.withAppendedPath(DatabaseContentProvider.AUTHORITY_URI, USER_SERVICE_CONTENT_DIRECTORY);

        private Service() {
        }
    }
}
