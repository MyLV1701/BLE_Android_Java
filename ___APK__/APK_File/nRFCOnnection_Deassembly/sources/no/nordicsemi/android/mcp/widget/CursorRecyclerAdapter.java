package no.nordicsemi.android.mcp.widget;

import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.os.Handler;
import android.widget.Filter;
import android.widget.FilterQueryProvider;
import android.widget.Filterable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.d0;
import no.nordicsemi.android.mcp.widget.CursorFilter;

/* loaded from: classes.dex */
public abstract class CursorRecyclerAdapter<VH extends RecyclerView.d0> extends RecyclerView.g<VH> implements Filterable, CursorFilter.CursorFilterClient {
    private CursorRecyclerAdapter<VH>.ChangeObserver mChangeObserver;
    private Cursor mCursor;
    private CursorFilter mCursorFilter;
    private DataSetObserver mDataSetObserver;
    private boolean mDataValid;
    private FilterQueryProvider mFilterQueryProvider;
    private int mRowIDColumn;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class ChangeObserver extends ContentObserver {
        public ChangeObserver() {
            super(new Handler());
        }

        @Override // android.database.ContentObserver
        public boolean deliverSelfNotifications() {
            return true;
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            CursorRecyclerAdapter.this.onContentChanged();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class MyDataSetObserver extends DataSetObserver {
        private MyDataSetObserver() {
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            CursorRecyclerAdapter.this.mDataValid = true;
            CursorRecyclerAdapter.this.notifyDataSetChanged();
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            CursorRecyclerAdapter.this.mDataValid = false;
            CursorRecyclerAdapter cursorRecyclerAdapter = CursorRecyclerAdapter.this;
            cursorRecyclerAdapter.notifyItemRangeRemoved(0, cursorRecyclerAdapter.getItemCount());
        }
    }

    public CursorRecyclerAdapter(Cursor cursor) {
        init(cursor);
    }

    @Override // no.nordicsemi.android.mcp.widget.CursorFilter.CursorFilterClient
    public void changeCursor(Cursor cursor) {
        Cursor swapCursor = swapCursor(cursor);
        if (swapCursor != null) {
            swapCursor.close();
        }
    }

    @Override // no.nordicsemi.android.mcp.widget.CursorFilter.CursorFilterClient
    public CharSequence convertToString(Cursor cursor) {
        return cursor == null ? "" : cursor.toString();
    }

    @Override // no.nordicsemi.android.mcp.widget.CursorFilter.CursorFilterClient
    public Cursor getCursor() {
        return this.mCursor;
    }

    @Override // android.widget.Filterable
    public Filter getFilter() {
        if (this.mCursorFilter == null) {
            this.mCursorFilter = new CursorFilter(this);
        }
        return this.mCursorFilter;
    }

    public FilterQueryProvider getFilterQueryProvider() {
        return this.mFilterQueryProvider;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public int getItemCount() {
        Cursor cursor;
        if (!this.mDataValid || (cursor = this.mCursor) == null) {
            return 0;
        }
        return cursor.getCount();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public long getItemId(int i) {
        Cursor cursor;
        if (this.mDataValid && (cursor = this.mCursor) != null && cursor.moveToPosition(i)) {
            return this.mCursor.getLong(this.mRowIDColumn);
        }
        return 0L;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public int getItemViewType(int i) {
        Cursor cursor;
        if (this.mDataValid && (cursor = this.mCursor) != null && cursor.moveToPosition(i)) {
            return getItemViewType(this.mCursor);
        }
        return 0;
    }

    public int getItemViewType(Cursor cursor) {
        return 0;
    }

    void init(Cursor cursor) {
        boolean z = cursor != null;
        this.mCursor = cursor;
        this.mDataValid = z;
        this.mRowIDColumn = z ? cursor.getColumnIndexOrThrow("_id") : -1;
        this.mChangeObserver = new ChangeObserver();
        this.mDataSetObserver = new MyDataSetObserver();
        if (z) {
            CursorRecyclerAdapter<VH>.ChangeObserver changeObserver = this.mChangeObserver;
            if (changeObserver != null) {
                cursor.registerContentObserver(changeObserver);
            }
            DataSetObserver dataSetObserver = this.mDataSetObserver;
            if (dataSetObserver != null) {
                cursor.registerDataSetObserver(dataSetObserver);
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public void onBindViewHolder(VH vh, int i) {
        if (this.mDataValid) {
            if (this.mCursor.moveToPosition(i)) {
                onBindViewHolderCursor(vh, this.mCursor);
                return;
            }
            throw new IllegalStateException("couldn't move cursor to position " + i);
        }
        throw new IllegalStateException("this should only be called when the cursor is valid");
    }

    public abstract void onBindViewHolderCursor(VH vh, Cursor cursor);

    protected void onContentChanged() {
    }

    @Override // no.nordicsemi.android.mcp.widget.CursorFilter.CursorFilterClient
    public Cursor runQueryOnBackgroundThread(CharSequence charSequence) {
        FilterQueryProvider filterQueryProvider = this.mFilterQueryProvider;
        if (filterQueryProvider != null) {
            return filterQueryProvider.runQuery(charSequence);
        }
        return this.mCursor;
    }

    public void setFilterQueryProvider(FilterQueryProvider filterQueryProvider) {
        this.mFilterQueryProvider = filterQueryProvider;
    }

    public Cursor swapCursor(Cursor cursor) {
        Cursor cursor2 = this.mCursor;
        if (cursor == cursor2) {
            return null;
        }
        if (cursor2 != null) {
            CursorRecyclerAdapter<VH>.ChangeObserver changeObserver = this.mChangeObserver;
            if (changeObserver != null) {
                cursor2.unregisterContentObserver(changeObserver);
            }
            DataSetObserver dataSetObserver = this.mDataSetObserver;
            if (dataSetObserver != null) {
                cursor2.unregisterDataSetObserver(dataSetObserver);
            }
        }
        this.mCursor = cursor;
        if (cursor != null) {
            CursorRecyclerAdapter<VH>.ChangeObserver changeObserver2 = this.mChangeObserver;
            if (changeObserver2 != null) {
                cursor.registerContentObserver(changeObserver2);
            }
            DataSetObserver dataSetObserver2 = this.mDataSetObserver;
            if (dataSetObserver2 != null) {
                cursor.registerDataSetObserver(dataSetObserver2);
            }
            this.mRowIDColumn = cursor.getColumnIndexOrThrow("_id");
            this.mDataValid = true;
        } else {
            this.mRowIDColumn = -1;
            this.mDataValid = false;
        }
        return cursor2;
    }
}
