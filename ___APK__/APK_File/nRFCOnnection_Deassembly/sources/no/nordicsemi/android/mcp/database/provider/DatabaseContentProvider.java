package no.nordicsemi.android.mcp.database.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import java.util.HashSet;
import java.util.Iterator;
import no.nordicsemi.android.mcp.database.CharacteristicContract;
import no.nordicsemi.android.mcp.database.DescriptorContract;
import no.nordicsemi.android.mcp.database.EditableColumns;
import no.nordicsemi.android.mcp.database.FormatColumns;
import no.nordicsemi.android.mcp.database.ServiceContract;
import no.nordicsemi.android.mcp.database.UuidColumns;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class DatabaseContentProvider extends ContentProvider {
    public static final String AUTHORITY = "no.nordicsemi.android.mcp";
    private static final int CHARACTERISTIC = 1110;
    private static final int CHARACTERISTIC_ID = 1111;
    private static final int DESCRIPTOR = 1210;
    private static final int DESCRIPTOR_ID = 1211;
    private static final int SERVICE = 1010;
    private static final int SERVICE_ID = 1011;
    private static final String TAG = "DatabaseContentProvider";
    private static final int USER_CHARACTERISTIC = 1112;
    private static final int USER_DESCRIPTOR = 1212;
    private static final int USER_SERVICE = 1012;
    private static final String UUID_SELECTION = "msb=? AND lsb=?";
    private static final ProjectionMap sCharacteristicColumns;
    private static final ProjectionMap sCountProjectionMap;
    private static final ProjectionMap sDescriptorColumns;
    private static final ProjectionMap sServiceColumns;
    private SQLiteDatabase mDatabase;
    private ThreadLocal<ContentValues> mValues = new ThreadLocal<>();
    public static final Uri AUTHORITY_URI = Uri.parse("content://no.nordicsemi.android.mcp");
    private static final UriMatcher sUriMatcher = new UriMatcher(-1);

    static {
        UriMatcher uriMatcher = sUriMatcher;
        uriMatcher.addURI("no.nordicsemi.android.mcp", ServiceContract.Service.SERVICE_CONTENT_DIRECTORY, SERVICE);
        uriMatcher.addURI("no.nordicsemi.android.mcp", "service/#", SERVICE_ID);
        uriMatcher.addURI("no.nordicsemi.android.mcp", ServiceContract.Service.USER_SERVICE_CONTENT_DIRECTORY, USER_SERVICE);
        uriMatcher.addURI("no.nordicsemi.android.mcp", CharacteristicContract.Characteristic.CHARACTERISTIC_CONTENT_DIRECTORY, CHARACTERISTIC);
        uriMatcher.addURI("no.nordicsemi.android.mcp", "characteristic/#", CHARACTERISTIC_ID);
        uriMatcher.addURI("no.nordicsemi.android.mcp", CharacteristicContract.Characteristic.USER_CHARACTERISTIC_CONTENT_DIRECTORY, USER_CHARACTERISTIC);
        uriMatcher.addURI("no.nordicsemi.android.mcp", DescriptorContract.Descriptor.DESCRIPTOR_CONTENT_DIRECTORY, DESCRIPTOR);
        uriMatcher.addURI("no.nordicsemi.android.mcp", "descriptor/#", DESCRIPTOR_ID);
        uriMatcher.addURI("no.nordicsemi.android.mcp", DescriptorContract.Descriptor.USER_DESCRIPTOR_CONTENT_DIRECTORY, USER_DESCRIPTOR);
        sServiceColumns = ProjectionMap.builder().add("_id").add("msb").add("lsb").add(UuidColumns.SPECIFICATION_TYPE).add("number").add("name").add(EditableColumns.EDITABLE).build();
        sCharacteristicColumns = ProjectionMap.builder().add("_id").add("msb").add("lsb").add(UuidColumns.SPECIFICATION_TYPE).add("number").add("name").add(FormatColumns.FORMAT).add(EditableColumns.EDITABLE).build();
        sDescriptorColumns = ProjectionMap.builder().add("_id").add("msb").add("lsb").add(UuidColumns.SPECIFICATION_TYPE).add("number").add("name").add(FormatColumns.FORMAT).add(EditableColumns.EDITABLE).build();
        sCountProjectionMap = ProjectionMap.builder().add("_count", "COUNT(*)").build();
    }

    private String[] appendSelectionArgs(String[] strArr, String... strArr2) {
        if (strArr == null) {
            return strArr2;
        }
        String[] strArr3 = new String[strArr.length + strArr2.length];
        System.arraycopy(strArr2, 0, strArr3, 0, strArr2.length);
        System.arraycopy(strArr, 0, strArr3, strArr2.length, strArr.length);
        return strArr3;
    }

    @Override // android.content.ContentProvider
    public int bulkInsert(Uri uri, ContentValues[] contentValuesArr) {
        int length = contentValuesArr.length;
        SQLiteDatabase sQLiteDatabase = this.mDatabase;
        HashSet hashSet = new HashSet();
        for (ContentValues contentValues : contentValuesArr) {
            hashSet.add(insertRow(uri, contentValues, sQLiteDatabase));
        }
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            notifyChange((Uri) it.next());
        }
        return length;
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        throw new UnsupportedOperationException("Deleting using Content Provider not supported");
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        int match = sUriMatcher.match(uri);
        if (match == SERVICE) {
            return ServiceContract.Service.CONTENT_TYPE;
        }
        if (match == SERVICE_ID) {
            return ServiceContract.Service.CONTENT_ITEM_TYPE;
        }
        if (match == CHARACTERISTIC) {
            return CharacteristicContract.Characteristic.CONTENT_TYPE;
        }
        if (match == CHARACTERISTIC_ID) {
            return CharacteristicContract.Characteristic.CONTENT_ITEM_TYPE;
        }
        if (match == DESCRIPTOR) {
            return DescriptorContract.Descriptor.CONTENT_TYPE;
        }
        if (match != DESCRIPTOR_ID) {
            return null;
        }
        return DescriptorContract.Descriptor.CONTENT_ITEM_TYPE;
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        Uri insertRow = insertRow(uri, contentValues, this.mDatabase);
        notifyChange(insertRow);
        return insertRow;
    }

    protected Uri insertRow(Uri uri, ContentValues contentValues, SQLiteDatabase sQLiteDatabase) {
        String str;
        ContentValues contentValues2 = this.mValues.get() != null ? this.mValues.get() : new ContentValues();
        this.mValues.set(contentValues2);
        contentValues2.clear();
        int match = sUriMatcher.match(uri);
        if (match == SERVICE) {
            str = DatabaseHelper.Tables.SERVICES;
        } else if (match == CHARACTERISTIC) {
            str = DatabaseHelper.Tables.CHARACTERISTICS;
        } else {
            if (match != DESCRIPTOR) {
                return null;
            }
            str = DatabaseHelper.Tables.DESCRIPTORS;
        }
        contentValues2.put("name", contentValues.getAsString("name"));
        if (match == CHARACTERISTIC || match == DESCRIPTOR) {
            if (contentValues.containsKey(FormatColumns.FORMAT)) {
                contentValues2.put(FormatColumns.FORMAT, contentValues.getAsInteger(FormatColumns.FORMAT));
            } else {
                contentValues2.putNull(FormatColumns.FORMAT);
            }
        }
        if (sQLiteDatabase.update(str, contentValues2, UUID_SELECTION, new String[]{contentValues.getAsString("msb"), contentValues.getAsString("lsb")}) == 0) {
            contentValues.put(EditableColumns.EDITABLE, (Boolean) true);
            sQLiteDatabase.insert(str, null, contentValues);
        }
        return uri;
    }

    protected void notifyChange(Uri uri) {
        getContext().getContentResolver().notifyChange(uri, (ContentObserver) null, false);
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        this.mDatabase = new DatabaseHelper(getContext()).getDatabase();
        return true;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        SQLiteDatabase sQLiteDatabase = this.mDatabase;
        SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
        int match = sUriMatcher.match(uri);
        int i = 0;
        switch (match) {
            case SERVICE /* 1010 */:
                sQLiteQueryBuilder.setTables(DatabaseHelper.Tables.SERVICES);
                sQLiteQueryBuilder.setProjectionMap(sServiceColumns);
                sQLiteQueryBuilder.appendWhere("editable=" + i);
                break;
            case SERVICE_ID /* 1011 */:
                String lastPathSegment = uri.getLastPathSegment();
                sQLiteQueryBuilder.setTables(DatabaseHelper.Tables.SERVICES);
                sQLiteQueryBuilder.setProjectionMap(sServiceColumns);
                sQLiteQueryBuilder.appendWhere("_id=?");
                strArr2 = appendSelectionArgs(strArr2, lastPathSegment);
                break;
            case USER_SERVICE /* 1012 */:
                i = 1;
                sQLiteQueryBuilder.setTables(DatabaseHelper.Tables.SERVICES);
                sQLiteQueryBuilder.setProjectionMap(sServiceColumns);
                sQLiteQueryBuilder.appendWhere("editable=" + i);
                break;
            default:
                switch (match) {
                    case CHARACTERISTIC /* 1110 */:
                        sQLiteQueryBuilder.setTables(DatabaseHelper.Tables.CHARACTERISTICS);
                        sQLiteQueryBuilder.setProjectionMap(sCharacteristicColumns);
                        sQLiteQueryBuilder.appendWhere("editable=" + i);
                        break;
                    case CHARACTERISTIC_ID /* 1111 */:
                        String lastPathSegment2 = uri.getLastPathSegment();
                        sQLiteQueryBuilder.setTables(DatabaseHelper.Tables.CHARACTERISTICS);
                        sQLiteQueryBuilder.setProjectionMap(sCharacteristicColumns);
                        sQLiteQueryBuilder.appendWhere("_id=?");
                        strArr2 = appendSelectionArgs(strArr2, lastPathSegment2);
                        break;
                    case USER_CHARACTERISTIC /* 1112 */:
                        i = 1;
                        sQLiteQueryBuilder.setTables(DatabaseHelper.Tables.CHARACTERISTICS);
                        sQLiteQueryBuilder.setProjectionMap(sCharacteristicColumns);
                        sQLiteQueryBuilder.appendWhere("editable=" + i);
                        break;
                    default:
                        switch (match) {
                            case DESCRIPTOR /* 1210 */:
                                sQLiteQueryBuilder.setTables(DatabaseHelper.Tables.DESCRIPTORS);
                                sQLiteQueryBuilder.setProjectionMap(sDescriptorColumns);
                                sQLiteQueryBuilder.appendWhere("editable=" + i);
                                break;
                            case DESCRIPTOR_ID /* 1211 */:
                                String lastPathSegment3 = uri.getLastPathSegment();
                                sQLiteQueryBuilder.setTables(DatabaseHelper.Tables.DESCRIPTORS);
                                sQLiteQueryBuilder.setProjectionMap(sDescriptorColumns);
                                sQLiteQueryBuilder.appendWhere("_id=?");
                                strArr2 = appendSelectionArgs(strArr2, lastPathSegment3);
                                break;
                            case USER_DESCRIPTOR /* 1212 */:
                                i = 1;
                                sQLiteQueryBuilder.setTables(DatabaseHelper.Tables.DESCRIPTORS);
                                sQLiteQueryBuilder.setProjectionMap(sDescriptorColumns);
                                sQLiteQueryBuilder.appendWhere("editable=" + i);
                                break;
                        }
                }
        }
        return query(uri, sQLiteDatabase, sQLiteQueryBuilder, strArr, str, strArr2, str2);
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        throw new UnsupportedOperationException("Updating using Content Provider not supported");
    }

    private Cursor query(Uri uri, SQLiteDatabase sQLiteDatabase, SQLiteQueryBuilder sQLiteQueryBuilder, String[] strArr, String str, String[] strArr2, String str2) {
        if (strArr != null && strArr.length == 1 && "_count".equals(strArr[0])) {
            sQLiteQueryBuilder.setProjectionMap(sCountProjectionMap);
        }
        Cursor query = sQLiteQueryBuilder.query(sQLiteDatabase, strArr, str, strArr2, null, null, str2);
        if (query != null) {
            query.setNotificationUri(getContext().getContentResolver(), uri);
        }
        return query;
    }
}
