package no.nordicsemi.android.mcp.server;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;
import no.nordicsemi.android.mcp.R;

/* loaded from: classes.dex */
public class ServerConfigurationsAdapter extends CursorAdapter {
    private final Context mContext;
    private final ActionListener mListener;

    /* loaded from: classes.dex */
    public interface ActionListener {
        void onDisableServer();

        void onImportClick();

        void onNewConfigurationClick();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ServerConfigurationsAdapter(Context context, ActionListener actionListener, Cursor cursor) {
        super(context, cursor, 0);
        this.mContext = context;
        this.mListener = actionListener;
    }

    private View newToolbarView(Context context, ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.server_dropdown_title, viewGroup, false);
        inflate.findViewById(R.id.action_add).setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.server.n0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ServerConfigurationsAdapter.this.a(view);
            }
        });
        inflate.findViewById(R.id.action_import).setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.server.l0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ServerConfigurationsAdapter.this.b(view);
            }
        });
        inflate.findViewById(R.id.action_disable_server).setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.server.m0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ServerConfigurationsAdapter.this.c(view);
            }
        });
        return inflate;
    }

    public /* synthetic */ void a(View view) {
        this.mListener.onNewConfigurationClick();
    }

    public /* synthetic */ void b(View view) {
        this.mListener.onImportClick();
    }

    @Override // android.widget.CursorAdapter
    public void bindView(View view, Context context, Cursor cursor) {
        ((TextView) view).setText(cursor.getString(1));
    }

    public /* synthetic */ void c(View view) {
        this.mListener.onDisableServer();
    }

    @Override // android.widget.CursorAdapter, android.widget.Adapter
    public int getCount() {
        return super.getCount() + 1;
    }

    @Override // android.widget.CursorAdapter, android.widget.BaseAdapter, android.widget.SpinnerAdapter
    public View getDropDownView(int i, View view, ViewGroup viewGroup) {
        if (i == 0) {
            return newToolbarView(this.mContext, viewGroup);
        }
        if (view instanceof ViewGroup) {
            return super.getDropDownView(i - 1, null, viewGroup);
        }
        return super.getDropDownView(i - 1, view, viewGroup);
    }

    @Override // android.widget.CursorAdapter, android.widget.Adapter
    public long getItemId(int i) {
        if (i > 0) {
            return super.getItemId(i - 1);
        }
        return 0L;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:10:0x0021, code lost:
    
        if (r0.moveToNext() != false) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x001c, code lost:
    
        return r0.getPosition() + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0023, code lost:
    
        return 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x000c, code lost:
    
        if (r0.moveToFirst() == false) goto L13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0014, code lost:
    
        if (r0.getLong(0) != r6) goto L11;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int getItemPosition(long r6) {
        /*
            r5 = this;
            android.database.Cursor r0 = r5.getCursor()
            r1 = 0
            if (r0 != 0) goto L8
            return r1
        L8:
            boolean r2 = r0.moveToFirst()
            if (r2 == 0) goto L23
        Le:
            long r2 = r0.getLong(r1)
            int r4 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
            if (r4 != 0) goto L1d
            int r6 = r0.getPosition()
            int r6 = r6 + 1
            return r6
        L1d:
            boolean r2 = r0.moveToNext()
            if (r2 != 0) goto Le
        L23:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: no.nordicsemi.android.mcp.server.ServerConfigurationsAdapter.getItemPosition(long):int");
    }

    @Override // android.widget.CursorAdapter, android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (i == 0) {
            TextView textView = (TextView) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.simple_spinner_item, viewGroup, false);
            textView.setText(R.string.server_no_configuration);
            return textView;
        }
        return super.getView(i - 1, view, viewGroup);
    }

    @Override // android.widget.CursorAdapter, android.widget.BaseAdapter, android.widget.Adapter
    public boolean hasStableIds() {
        return true;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public boolean isEmpty() {
        return super.getCount() == 0;
    }

    @Override // android.widget.CursorAdapter
    public View newDropDownView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(this.mContext).inflate(R.layout.server_dropdown_item, viewGroup, false);
    }

    @Override // android.widget.CursorAdapter
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.simple_spinner_item, viewGroup, false);
    }
}
