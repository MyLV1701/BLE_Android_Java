package no.nordicsemi.android.mcp.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.appcompat.app.d;
import no.nordicsemi.android.mcp.R;

/* loaded from: classes.dex */
public class ShowOnceDialogFragment extends androidx.fragment.app.c implements DialogInterface.OnClickListener {
    private static final String ARG_MESSAGE = "ARG_MESSAGE";
    private static final String ARG_PROPERTY = "ARG_PROPERTY";
    private static final String ARG_TITLE = "ARG_TITLE";
    private CheckBox mNeverShowAgainBox;

    public static ShowOnceDialogFragment getInstance(int i, int i2, String str) {
        ShowOnceDialogFragment showOnceDialogFragment = new ShowOnceDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_TITLE, i);
        bundle.putInt(ARG_MESSAGE, i2);
        bundle.putString(ARG_PROPERTY, str);
        showOnceDialogFragment.setArguments(bundle);
        return showOnceDialogFragment;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        Bundle arguments = getArguments();
        if (this.mNeverShowAgainBox.isChecked()) {
            SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(getActivity()).edit();
            edit.putBoolean(arguments.getString(ARG_PROPERTY), true);
            edit.apply();
        }
    }

    @Override // androidx.fragment.app.c
    public Dialog onCreateDialog(Bundle bundle) {
        Bundle arguments = getArguments();
        d.a aVar = new d.a(requireContext());
        aVar.c(arguments.getInt(ARG_TITLE));
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_show_once, (ViewGroup) null);
        this.mNeverShowAgainBox = (CheckBox) inflate.findViewById(R.id.box);
        ((TextView) inflate.findViewById(R.id.message)).setText(arguments.getInt(ARG_MESSAGE));
        aVar.b(inflate);
        aVar.c(R.string.ok, this);
        return aVar.a();
    }
}
