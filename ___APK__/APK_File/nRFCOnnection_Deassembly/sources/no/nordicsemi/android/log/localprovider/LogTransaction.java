package no.nordicsemi.android.log.localprovider;

import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
class LogTransaction {
    private final boolean mBatch;
    private boolean mYieldFailed;
    private final List<SQLiteDatabase> mDatabasesForTransaction = new ArrayList();
    private final Map<String, SQLiteDatabase> mDatabaseTagMap = new HashMap();
    private boolean mIsDirty = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    public LogTransaction(boolean z) {
        this.mBatch = z;
    }

    private boolean hasDbInTransaction(String str) {
        return this.mDatabaseTagMap.containsKey(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void finish(boolean z) {
        if (!this.mBatch || z) {
            for (SQLiteDatabase sQLiteDatabase : this.mDatabasesForTransaction) {
                if (!this.mYieldFailed || sQLiteDatabase.isDbLockedByCurrentThread()) {
                    sQLiteDatabase.endTransaction();
                }
            }
            this.mDatabasesForTransaction.clear();
            this.mDatabaseTagMap.clear();
            this.mIsDirty = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SQLiteDatabase getDbForTag(String str) {
        return this.mDatabaseTagMap.get(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isBatch() {
        return this.mBatch;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isDirty() {
        return this.mIsDirty;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void markDirty() {
        this.mIsDirty = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void markSuccessful(boolean z) {
        if (!this.mBatch || z) {
            Iterator<SQLiteDatabase> it = this.mDatabasesForTransaction.iterator();
            while (it.hasNext()) {
                it.next().setTransactionSuccessful();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void markYieldFailed() {
        this.mYieldFailed = true;
    }

    public SQLiteDatabase removeDbForTag(String str) {
        SQLiteDatabase sQLiteDatabase = this.mDatabaseTagMap.get(str);
        this.mDatabaseTagMap.remove(str);
        this.mDatabasesForTransaction.remove(sQLiteDatabase);
        return sQLiteDatabase;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void startTransactionForDb(SQLiteDatabase sQLiteDatabase, String str) {
        if (hasDbInTransaction(str)) {
            return;
        }
        this.mDatabasesForTransaction.add(0, sQLiteDatabase);
        this.mDatabaseTagMap.put(str, sQLiteDatabase);
        sQLiteDatabase.beginTransaction();
    }
}
