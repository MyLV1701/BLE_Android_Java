package no.nordicsemi.android.mcp.connection;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import no.nordicsemi.android.mcp.R;

/* loaded from: classes.dex */
public class FileBrowserAppsAdapter extends BaseAdapter {
    private final LayoutInflater mInflater;
    private final Resources mResources;

    public FileBrowserAppsAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
        this.mResources = context.getResources();
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.mResources.getStringArray(R.array.dfu_app_file_browser).length;
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return this.mResources.getStringArray(R.array.dfu_app_file_browser_action)[i];
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = this.mInflater.inflate(R.layout.app_file_browser_item, viewGroup, false);
        }
        TextView textView = (TextView) view;
        textView.setText(this.mResources.getStringArray(R.array.dfu_app_file_browser)[i]);
        textView.getCompoundDrawablesRelative()[0].setLevel(i);
        return view;
    }
}
