package no.nordicsemi.android.mcp.scanner;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.appcompat.app.d;
import no.nordicsemi.android.mcp.R;

/* loaded from: classes.dex */
public class DeviceNameDialogFragment extends androidx.fragment.app.c implements DialogInterface.OnClickListener {
    private static final String ADDRESS = "address";
    private static final String NAME = "name";
    private EditText mEditText;

    /* loaded from: classes.dex */
    public interface DeviceNameListener {
        void onDeviceRenamed(String str, String str2);

        void refreshAndCloseActionMode();
    }

    public static androidx.fragment.app.c getInstance(String str, String str2) {
        DeviceNameDialogFragment deviceNameDialogFragment = new DeviceNameDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("address", str);
        bundle.putString("name", str2);
        deviceNameDialogFragment.setArguments(bundle);
        return deviceNameDialogFragment;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        String trim = this.mEditText.getText().toString().trim();
        ((DeviceNameListener) getParentFragment()).onDeviceRenamed(getArguments().getString("address"), trim);
    }

    @Override // androidx.fragment.app.c
    public Dialog onCreateDialog(Bundle bundle) {
        Bundle arguments = getArguments();
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_change_device_name, (ViewGroup) null);
        final EditText editText = (EditText) inflate.findViewById(R.id.display_name);
        this.mEditText = editText;
        editText.setText(arguments.getString("name"));
        inflate.findViewById(R.id.action_clear).setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.scanner.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                editText.setText((CharSequence) null);
            }
        });
        d.a aVar = new d.a(requireContext());
        aVar.c(R.string.alert_change_name_title);
        aVar.b(inflate);
        aVar.a(R.string.cancel, (DialogInterface.OnClickListener) null);
        aVar.c(R.string.ok, this);
        androidx.appcompat.app.d c2 = aVar.c();
        c2.setCanceledOnTouchOutside(false);
        return c2;
    }

    @Override // androidx.fragment.app.c, android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        ((DeviceNameListener) getParentFragment()).refreshAndCloseActionMode();
    }
}
