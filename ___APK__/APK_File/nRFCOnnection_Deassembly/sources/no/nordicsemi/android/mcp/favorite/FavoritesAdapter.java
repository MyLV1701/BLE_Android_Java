package no.nordicsemi.android.mcp.favorite;

import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DateFormat;
import java.util.Date;
import no.nordicsemi.android.mcp.R;
import no.nordicsemi.android.mcp.widget.AppearanceIconHelper;
import no.nordicsemi.android.mcp.widget.CursorRecyclerAdapter;
import no.nordicsemi.android.mcp.widget.RemovableViewHolder;

/* loaded from: classes.dex */
public class FavoritesAdapter extends CursorRecyclerAdapter<ViewHolder> {
    private DateFormat mDateFormat;

    /* loaded from: classes.dex */
    public class ViewHolder extends RemovableViewHolder {
        private final TextView addedView;
        private final TextView addressView;
        private final ImageView iconView;
        private final TextView lastSeenView;
        private final TextView nameView;
        private final TextView secondaryNameView;

        public ViewHolder(View view) {
            super(view);
            this.iconView = (ImageView) view.findViewById(R.id.icon);
            this.addressView = (TextView) view.findViewById(R.id.address);
            this.nameView = (TextView) view.findViewById(R.id.display_name);
            this.secondaryNameView = (TextView) view.findViewById(R.id.name_secondary);
            this.addedView = (TextView) view.findViewById(R.id.added);
            this.lastSeenView = (TextView) view.findViewById(R.id.last_seen);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public FavoritesAdapter(Cursor cursor) {
        super(cursor);
    }

    @Override // no.nordicsemi.android.mcp.widget.CursorRecyclerAdapter
    public void onBindViewHolderCursor(ViewHolder viewHolder, Cursor cursor) {
        String string = cursor.getString(1);
        String string2 = cursor.getString(2);
        int i = cursor.getInt(3);
        boolean z = !cursor.isNull(4);
        boolean isNull = true ^ cursor.isNull(5);
        ImageView imageView = viewHolder.iconView;
        imageView.setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.favorite.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Toast.makeText(view.getContext(), R.string.swipe_to_dismiss, 0).show();
            }
        });
        AppearanceIconHelper.assign(imageView, viewHolder.nameView, viewHolder.secondaryNameView, i, string2, cursor.getPosition());
        viewHolder.addressView.setText(string);
        if (!z) {
            viewHolder.addedView.setText(R.string.unknown);
        } else {
            viewHolder.addedView.setText(this.mDateFormat.format(new Date(cursor.getLong(4))));
        }
        if (!isNull) {
            viewHolder.lastSeenView.setText(R.string.unknown);
        } else {
            viewHolder.lastSeenView.setText(this.mDateFormat.format(new Date(cursor.getLong(5))));
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.favorite_item, viewGroup, false);
        if (this.mDateFormat == null) {
            this.mDateFormat = android.text.format.DateFormat.getDateFormat(viewGroup.getContext());
        }
        return new ViewHolder(inflate);
    }
}
