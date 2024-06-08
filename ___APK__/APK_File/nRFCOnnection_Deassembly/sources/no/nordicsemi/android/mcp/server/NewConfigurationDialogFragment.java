package no.nordicsemi.android.mcp.server;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.appcompat.app.d;
import no.nordicsemi.android.mcp.R;

/* loaded from: classes.dex */
public class NewConfigurationDialogFragment extends androidx.fragment.app.c implements View.OnClickListener {
    private static final String DUPLICATE = "duplicate";
    private static final String NAME = "name";
    private EditText mEditText;

    public static androidx.fragment.app.c getInstance(String str, boolean z) {
        NewConfigurationDialogFragment newConfigurationDialogFragment = new NewConfigurationDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("name", str);
        bundle.putBoolean(DUPLICATE, z);
        newConfigurationDialogFragment.setArguments(bundle);
        return newConfigurationDialogFragment;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        String trim = this.mEditText.getText().toString().trim();
        if (TextUtils.isEmpty(trim)) {
            this.mEditText.setError(getString(R.string.server_alert_empty_name_error));
            return;
        }
        String string = getArguments().getString("name");
        boolean z = getArguments().getBoolean(DUPLICATE);
        ServerFragment serverFragment = (ServerFragment) getParentFragment();
        if (!z && !TextUtils.isEmpty(string)) {
            serverFragment.onRenameConfiguration(trim);
        } else {
            serverFragment.onNewConfiguration(trim, z);
        }
        dismiss();
    }

    @Override // androidx.fragment.app.c
    public Dialog onCreateDialog(Bundle bundle) {
        Bundle arguments = getArguments();
        int i = (arguments.getBoolean(DUPLICATE) || arguments.getString("name") == null) ? R.string.server_alert_new_configuration_title : R.string.server_alert_rename_configuration_title;
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_new_configuration, (ViewGroup) null);
        final EditText editText = (EditText) inflate.findViewById(R.id.display_name);
        this.mEditText = editText;
        editText.setText(arguments.getString("name"));
        inflate.findViewById(R.id.action_clear).setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.server.k0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                editText.setText((CharSequence) null);
            }
        });
        d.a aVar = new d.a(requireContext());
        aVar.c(i);
        aVar.b(inflate);
        aVar.a(R.string.cancel, (DialogInterface.OnClickListener) null);
        aVar.c(R.string.ok, null);
        aVar.a(false);
        androidx.appcompat.app.d c2 = aVar.c();
        c2.b(-1).setOnClickListener(this);
        return c2;
    }
}
