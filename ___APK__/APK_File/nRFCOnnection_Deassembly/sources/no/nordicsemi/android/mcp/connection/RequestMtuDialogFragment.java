package no.nordicsemi.android.mcp.connection;

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
public class RequestMtuDialogFragment extends androidx.fragment.app.c implements View.OnClickListener {
    private EditText mEditText;

    public static androidx.fragment.app.c getInstance() {
        return new RequestMtuDialogFragment();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int i;
        try {
            i = Integer.parseInt(this.mEditText.getText().toString().trim());
        } catch (NumberFormatException unused) {
            i = 0;
        }
        if (i >= 23 && i <= 517) {
            ((DeviceDetailsFragment2) getParentFragment()).requestMtu(i);
            dismiss();
        } else {
            this.mEditText.setError(getString(R.string.alert_request_mtu_invalid));
        }
    }

    @Override // androidx.fragment.app.c
    public Dialog onCreateDialog(Bundle bundle) {
        androidx.fragment.app.d activity = getActivity();
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_request_mtu, (ViewGroup) null);
        final EditText editText = (EditText) inflate.findViewById(R.id.mtu);
        this.mEditText = editText;
        inflate.findViewById(R.id.action_clear).setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.connection.d0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                editText.setText((CharSequence) null);
            }
        });
        d.a aVar = new d.a(activity);
        aVar.c(R.string.alert_request_mtu_title);
        aVar.b(inflate);
        aVar.a(R.string.cancel, (DialogInterface.OnClickListener) null);
        aVar.c(R.string.ok, null);
        androidx.appcompat.app.d c2 = aVar.c();
        c2.setCanceledOnTouchOutside(false);
        c2.b(-1).setOnClickListener(this);
        return c2;
    }
}
