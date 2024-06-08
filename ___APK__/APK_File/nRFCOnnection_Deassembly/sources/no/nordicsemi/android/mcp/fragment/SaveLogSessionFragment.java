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
import no.nordicsemi.android.mcp.ble.model.Device;
import no.nordicsemi.android.mcp.settings.SettingsFragment;

/* loaded from: classes.dex */
public class SaveLogSessionFragment extends androidx.fragment.app.c implements DialogInterface.OnClickListener {
    private static final String ARG_DEVICE = "device";
    private static final String ARG_DISCONNECT_AND_CLOSE = "disconnectAndClose";
    private CheckBox mNeverShowAgainBox;

    /* loaded from: classes.dex */
    public interface DialogListener {
        void onSaveLogSession(Device device, boolean z, boolean z2);
    }

    public static androidx.fragment.app.c getInstance(Device device, boolean z) {
        SaveLogSessionFragment saveLogSessionFragment = new SaveLogSessionFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("device", device);
        bundle.putBoolean(ARG_DISCONNECT_AND_CLOSE, z);
        saveLogSessionFragment.setArguments(bundle);
        return saveLogSessionFragment;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        if (this.mNeverShowAgainBox.isChecked()) {
            SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(getActivity()).edit();
            if (i != -2) {
                edit.putString(SettingsFragment.SETTINGS_LOGGER, "always");
            } else {
                edit.putString(SettingsFragment.SETTINGS_LOGGER, "never");
            }
            edit.apply();
        }
        ((DialogListener) getParentFragment()).onSaveLogSession((Device) getArguments().getParcelable("device"), i == -1, getArguments().getBoolean(ARG_DISCONNECT_AND_CLOSE));
    }

    @Override // androidx.fragment.app.c
    public Dialog onCreateDialog(Bundle bundle) {
        d.a aVar = new d.a(requireContext());
        aVar.c(R.string.log_alert_title);
        View inflate = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_show_once, (ViewGroup) null);
        this.mNeverShowAgainBox = (CheckBox) inflate.findViewById(R.id.box);
        ((TextView) inflate.findViewById(R.id.message)).setText(R.string.log_alert_message);
        aVar.b(inflate);
        aVar.b(R.string.cancel, (DialogInterface.OnClickListener) null);
        aVar.a(R.string.log_remove, this);
        aVar.c(R.string.log_save, this);
        return aVar.a();
    }
}
