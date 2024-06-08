package no.nordicsemi.android.mcp.connection;

import a.k.a.a;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import java.util.Calendar;
import no.nordicsemi.android.mcp.R;

/* loaded from: classes.dex */
public class LogAdapter extends a.g.a.a {
    private a.InterfaceC0025a<Cursor> mCallback;
    private final SparseIntArray mColors;
    private Fragment mFragment;
    private int mLogLevel;

    /* loaded from: classes.dex */
    private class ViewHolder {
        private TextView data;
        private TextView time;

        private ViewHolder() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public LogAdapter(Fragment fragment, a.InterfaceC0025a<Cursor> interfaceC0025a, Cursor cursor) {
        super(fragment.getContext(), cursor, 0);
        this.mColors = new SparseIntArray();
        this.mFragment = fragment;
        this.mCallback = interfaceC0025a;
        int[] intArray = fragment.getResources().getIntArray(R.array.log_color);
        this.mColors.put(0, intArray[0]);
        this.mColors.put(1, intArray[1]);
        this.mColors.put(5, intArray[2]);
        this.mColors.put(10, intArray[3]);
        this.mColors.put(15, intArray[4]);
        this.mColors.put(20, intArray[5]);
    }

    @Override // a.g.a.a
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(cursor.getLong(1));
        viewHolder.time.setText(context.getString(R.string.log, calendar));
        int i = cursor.getInt(2);
        viewHolder.data.setText(cursor.getString(3));
        viewHolder.data.setTextColor(this.mColors.get(i));
    }

    @Override // android.widget.BaseAdapter, android.widget.ListAdapter
    public boolean isEnabled(int i) {
        return false;
    }

    @Override // a.g.a.a
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.log_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder();
        viewHolder.time = (TextView) inflate.findViewById(R.id.time);
        viewHolder.data = (TextView) inflate.findViewById(R.id.data);
        inflate.setTag(viewHolder);
        return inflate;
    }

    public void onDestroy() {
        this.mFragment = null;
        this.mCallback = null;
    }

    public void reload() {
        Bundle bundle = new Bundle();
        bundle.putInt(DeviceDetailsFragment2.MIN_LEVEL_LOADER_ARG, this.mLogLevel);
        a.k.a.a.a(this.mFragment).b(1, bundle, this.mCallback);
    }

    public void setMinLevel(int i) {
        this.mLogLevel = i;
        reload();
    }
}
