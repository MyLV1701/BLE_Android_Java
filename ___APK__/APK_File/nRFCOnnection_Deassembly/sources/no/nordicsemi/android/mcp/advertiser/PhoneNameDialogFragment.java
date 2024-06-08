package no.nordicsemi.android.mcp.advertiser;

import android.app.Dialog;
import android.bluetooth.BluetoothAdapter;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.appcompat.app.d;
import no.nordicsemi.android.mcp.R;

/* loaded from: classes.dex */
public class PhoneNameDialogFragment extends androidx.fragment.app.c implements DialogInterface.OnClickListener {
    private EditText mEditText;

    /* loaded from: classes.dex */
    public interface DeviceNameListener {
        void onDeviceRenamed(String str);
    }

    public static androidx.fragment.app.c getInstance() {
        return new PhoneNameDialogFragment();
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        String trim = this.mEditText.getText().toString().trim();
        BluetoothAdapter.getDefaultAdapter().setName(trim);
        ((DeviceNameListener) getParentFragment()).onDeviceRenamed(trim);
    }

    @Override // androidx.fragment.app.c
    public Dialog onCreateDialog(Bundle bundle) {
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_change_phone_name, (ViewGroup) null);
        final EditText editText = (EditText) inflate.findViewById(R.id.display_name);
        this.mEditText = editText;
        editText.setText(BluetoothAdapter.getDefaultAdapter().getName());
        editText.setSelection(0, editText.getText().length());
        inflate.findViewById(R.id.action_clear).setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.advertiser.b0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                editText.setText((CharSequence) null);
            }
        });
        d.a aVar = new d.a(getContext());
        aVar.c(R.string.alert_change_phone_name_title);
        aVar.b(inflate);
        aVar.a(R.string.cancel, (DialogInterface.OnClickListener) null);
        aVar.c(R.string.rename, this);
        androidx.appcompat.app.d a2 = aVar.a();
        a2.setCanceledOnTouchOutside(true);
        a2.getWindow().setSoftInputMode(4);
        return a2;
    }
}
