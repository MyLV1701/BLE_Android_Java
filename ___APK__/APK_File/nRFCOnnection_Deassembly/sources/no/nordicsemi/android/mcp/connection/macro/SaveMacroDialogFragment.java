package no.nordicsemi.android.mcp.connection.macro;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.appcompat.app.d;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.textfield.TextInputLayout;
import no.nordicsemi.android.mcp.R;
import no.nordicsemi.android.mcp.connection.macro.domain.Macro;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class SaveMacroDialogFragment extends androidx.fragment.app.c implements View.OnClickListener {
    private static final String GROUP_ID = "group_id";
    private static final String ICON = "icon";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String POSITION = "position";
    private static final String XML = "xml";
    private Adapter mAdapter;
    private EditText mNameView;
    private TextInputLayout mTextInputLayout;

    /* loaded from: classes.dex */
    private static class Adapter extends RecyclerView.g<ViewHolder> {
        private Macro.Icon mSelectedIcon;
        private int mSelectedPosition;

        /* loaded from: classes.dex */
        public class ViewHolder extends RecyclerView.d0 implements View.OnClickListener {
            private Macro.Icon icon;
            private ImageView iconView;

            public ViewHolder(View view) {
                super(view);
                this.iconView = (ImageView) view.findViewById(R.id.icon);
                this.iconView.setOnClickListener(this);
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Adapter.this.mSelectedIcon = this.icon;
                Adapter adapter = Adapter.this;
                adapter.notifyItemChanged(adapter.mSelectedPosition);
                Adapter.this.mSelectedPosition = getLayoutPosition();
                Adapter adapter2 = Adapter.this;
                adapter2.notifyItemChanged(adapter2.mSelectedPosition);
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public int getItemCount() {
            return Macro.Icon.values().length;
        }

        private Adapter(Macro.Icon icon) {
            this.mSelectedIcon = Macro.Icon.PLAY;
            this.mSelectedPosition = 6;
            if (icon != null) {
                this.mSelectedIcon = icon;
                this.mSelectedPosition = icon.ordinal();
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public void onBindViewHolder(ViewHolder viewHolder, int i) {
            Macro.Icon icon = Macro.Icon.values()[i];
            viewHolder.iconView.setImageResource(icon.getResourceId());
            viewHolder.iconView.setSelected(i == this.mSelectedPosition);
            viewHolder.icon = icon;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.dialog_macro_save_item, viewGroup, false));
        }
    }

    /* loaded from: classes.dex */
    public interface Listener {
        void onMacroCancelled();

        void onMacroSaved(int i, long j, String str, Macro.Icon icon);
    }

    public static SaveMacroDialogFragment getInstance(int i, long j, String str, Macro.Icon icon, String str2, long j2) {
        Bundle bundle = new Bundle();
        bundle.putInt(POSITION, i);
        bundle.putLong("id", j);
        bundle.putString("name", str);
        bundle.putSerializable(ICON, icon);
        bundle.putString("xml", str2);
        bundle.putLong("group_id", j2);
        SaveMacroDialogFragment saveMacroDialogFragment = new SaveMacroDialogFragment();
        saveMacroDialogFragment.setArguments(bundle);
        return saveMacroDialogFragment;
    }

    public /* synthetic */ void a(View view) {
        this.mTextInputLayout.setError(null);
        this.mNameView.setText((CharSequence) null);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
        Bundle arguments = getArguments();
        long j = arguments.getLong("id");
        String string = arguments.getString("name");
        String trim = this.mNameView.getText().toString().trim();
        if (trim.matches(".*[<>&'\"].*")) {
            this.mTextInputLayout.setError(getString(R.string.macros_name_invalid));
            return;
        }
        if (!trim.equals(string) && databaseHelper.macroExists(trim)) {
            this.mTextInputLayout.setError(getString(R.string.macros_name_exists));
            return;
        }
        int i = arguments.getInt(POSITION);
        long j2 = arguments.getLong("group_id");
        String replaceFirst = arguments.getString("xml").replaceFirst("name=\".+\"( +icon=\".+\")?", "name=\"" + trim + "\" icon=\"" + this.mAdapter.mSelectedIcon.name() + "\"");
        if (j == 0) {
            databaseHelper.addMacro(trim, replaceFirst, j2);
        } else {
            databaseHelper.renameMacro(string, trim, replaceFirst);
        }
        ((Listener) getParentFragment()).onMacroSaved(i, j, trim, this.mAdapter.mSelectedIcon);
        dismiss();
    }

    @Override // androidx.fragment.app.c
    public Dialog onCreateDialog(Bundle bundle) {
        final Bundle arguments = getArguments();
        long j = arguments.getLong("id");
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.dialog_macro_save, (ViewGroup) null);
        this.mTextInputLayout = (TextInputLayout) inflate.findViewById(R.id.name_layout);
        this.mNameView = (EditText) inflate.findViewById(R.id.display_name);
        this.mNameView.setText(arguments.getString("name"));
        inflate.findViewById(R.id.action_clear).setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.connection.macro.k
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SaveMacroDialogFragment.this.a(view);
            }
        });
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(android.R.id.list);
        recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(), 5));
        Adapter adapter = new Adapter((Macro.Icon) arguments.getSerializable(ICON));
        this.mAdapter = adapter;
        recyclerView.setAdapter(adapter);
        d.a aVar = new d.a(recyclerView.getContext());
        aVar.c(j == 0 ? R.string.macros_dialog_title_new_macro : R.string.macros_dialog_title_rename_macro);
        aVar.b(inflate);
        aVar.a(false);
        aVar.a(R.string.cancel, new DialogInterface.OnClickListener() { // from class: no.nordicsemi.android.mcp.connection.macro.j
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                SaveMacroDialogFragment.this.a(arguments, dialogInterface, i);
            }
        });
        aVar.c(R.string.save, null);
        androidx.appcompat.app.d c2 = aVar.c();
        c2.setCanceledOnTouchOutside(false);
        setCancelable(false);
        final Button b2 = c2.b(-1);
        b2.setOnClickListener(this);
        b2.setEnabled(this.mNameView.getText().toString().trim().length() > 0);
        this.mNameView.addTextChangedListener(new TextWatcher() { // from class: no.nordicsemi.android.mcp.connection.macro.SaveMacroDialogFragment.1
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                b2.setEnabled(editable.toString().trim().length() > 0);
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }
        });
        return c2;
    }

    public /* synthetic */ void a(Bundle bundle, DialogInterface dialogInterface, int i) {
        if (bundle.getLong("id") == 0) {
            ((Listener) getParentFragment()).onMacroCancelled();
        }
    }
}
