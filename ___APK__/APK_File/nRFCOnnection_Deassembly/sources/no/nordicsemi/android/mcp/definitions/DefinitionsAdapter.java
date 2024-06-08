package no.nordicsemi.android.mcp.definitions;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Locale;
import java.util.UUID;
import no.nordicsemi.android.mcp.R;
import no.nordicsemi.android.mcp.database.FormatColumns;
import no.nordicsemi.android.mcp.database.UuidColumns;
import no.nordicsemi.android.mcp.definitions.DefinitionsAdapter;
import no.nordicsemi.android.mcp.definitions.DefinitionsFragment;
import no.nordicsemi.android.mcp.widget.CursorRecyclerAdapter;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class DefinitionsAdapter extends CursorRecyclerAdapter<ViewHolder> {
    static final String[] PROJECTION = {"_id", "name", "msb", "lsb", "number", UuidColumns.SPECIFICATION_TYPE};
    static final String[] PROJECTION_WITH_FORMAT = {"_id", "name", "msb", "lsb", "number", UuidColumns.SPECIFICATION_TYPE, FormatColumns.FORMAT};
    private DefinitionsFragment.Type type;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class ViewHolder extends RecyclerView.d0 {
        private static final String URL = "https://www.bluetooth.com/wp-content/uploads/Sitecore-Media-Library/Gatt/Xml/TYPE/URI.xml";
        private final View buttonOpen;
        private final TextView format;
        private final View formatPrefix;
        private final TextView nameView;
        private String uti;
        private final TextView uuidView;

        public /* synthetic */ void a(View view) {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(URL.replace("URI", this.uti).replace("TYPE", DefinitionsAdapter.this.type.uriPart())));
            intent.addFlags(268435456);
            view.getContext().startActivity(intent);
        }

        void assign(Cursor cursor) {
            String string = cursor.getString(1);
            this.nameView.setText(string);
            if (!cursor.isNull(4)) {
                this.uuidView.setText(String.format(Locale.US, "0x%04X", Integer.valueOf(cursor.getInt(4))));
            } else {
                this.uuidView.setText(new UUID(cursor.getLong(2), cursor.getLong(3)).toString());
            }
            boolean z = !cursor.isNull(5);
            boolean z2 = string.startsWith("Binary") || string.startsWith("BSS") || string.startsWith("Emergency") || string.equals("Registered User") || string.equals("Client Supported Features") || string.equals("Database Hash");
            this.uti = cursor.getString(5);
            this.buttonOpen.setVisibility((!z || z2) ? 8 : 0);
            int columnIndex = cursor.getColumnIndex(FormatColumns.FORMAT);
            boolean z3 = columnIndex >= 0 && !cursor.isNull(columnIndex);
            if (z3 && cursor.getInt(columnIndex) == 1) {
                this.format.setText(R.string.format_text);
            }
            this.formatPrefix.setVisibility(z3 ? 0 : 8);
            this.format.setVisibility(z3 ? 0 : 8);
        }

        private ViewHolder(View view) {
            super(view);
            this.nameView = (TextView) view.findViewById(R.id.display_name);
            this.uuidView = (TextView) view.findViewById(R.id.uuid);
            this.formatPrefix = view.findViewById(R.id.format_prefix);
            this.format = (TextView) view.findViewById(R.id.format);
            this.buttonOpen = view.findViewById(R.id.action_open);
            this.buttonOpen.setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.definitions.c
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    DefinitionsAdapter.ViewHolder.this.a(view2);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DefinitionsAdapter(DefinitionsFragment.Type type) {
        super(null);
        this.type = type;
    }

    @Override // no.nordicsemi.android.mcp.widget.CursorRecyclerAdapter
    public void onBindViewHolderCursor(ViewHolder viewHolder, Cursor cursor) {
        viewHolder.assign(cursor);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.uuid_def, viewGroup, false));
    }
}
