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
import androidx.appcompat.app.d;
import com.google.android.material.textfield.TextInputLayout;
import no.nordicsemi.android.mcp.R;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class SaveGroupDialogFragment extends androidx.fragment.app.c implements View.OnClickListener {
    private static final String GROUP_ID = "group_id";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String POSITION = "position";
    private EditText mNameView;
    private TextInputLayout mTextInputLayout;

    /* loaded from: classes.dex */
    public interface Listener {
        void onMacroGroupSaved(int i);
    }

    public static SaveGroupDialogFragment getInstance(long j) {
        Bundle bundle = new Bundle();
        bundle.putInt(POSITION, -1);
        bundle.putLong("id", 0L);
        bundle.putLong("group_id", j);
        SaveGroupDialogFragment saveGroupDialogFragment = new SaveGroupDialogFragment();
        saveGroupDialogFragment.setArguments(bundle);
        return saveGroupDialogFragment;
    }

    public /* synthetic */ void a(View view) {
        this.mTextInputLayout.setError(null);
        this.mNameView.setText((CharSequence) null);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        DatabaseHelper databaseHelper = new DatabaseHelper(view.getContext());
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
        if (j == 0) {
            databaseHelper.addMacroGroup(trim, j2);
        } else {
            databaseHelper.renameMacro(string, trim, null);
        }
        ((Listener) getParentFragment()).onMacroGroupSaved(i);
        dismiss();
    }

    @Override // androidx.fragment.app.c
    public Dialog onCreateDialog(Bundle bundle) {
        Bundle arguments = getArguments();
        long j = arguments.getLong("id");
        View inflate = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_macro_group_save, (ViewGroup) null);
        this.mTextInputLayout = (TextInputLayout) inflate.findViewById(R.id.name_layout);
        this.mNameView = (EditText) inflate.findViewById(R.id.display_name);
        this.mNameView.setText(arguments.getString("name"));
        inflate.findViewById(R.id.action_clear).setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.connection.macro.i
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SaveGroupDialogFragment.this.a(view);
            }
        });
        d.a aVar = new d.a(requireContext());
        aVar.c(j == 0 ? R.string.macros_dialog_title_new_group : R.string.macros_dialog_title_rename_group);
        aVar.b(inflate);
        aVar.a(false);
        aVar.a(R.string.cancel, (DialogInterface.OnClickListener) null);
        aVar.c(R.string.save, null);
        androidx.appcompat.app.d c2 = aVar.c();
        final Button b2 = c2.b(-1);
        b2.setOnClickListener(this);
        b2.setEnabled(this.mNameView.getText().toString().trim().length() > 0);
        this.mNameView.addTextChangedListener(new TextWatcher() { // from class: no.nordicsemi.android.mcp.connection.macro.SaveGroupDialogFragment.1
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

    public static SaveGroupDialogFragment getInstance(int i, long j, String str, long j2) {
        Bundle bundle = new Bundle();
        bundle.putInt(POSITION, i);
        bundle.putLong("id", j);
        bundle.putString("name", str);
        bundle.putLong("group_id", j2);
        SaveGroupDialogFragment saveGroupDialogFragment = new SaveGroupDialogFragment();
        saveGroupDialogFragment.setArguments(bundle);
        return saveGroupDialogFragment;
    }
}
