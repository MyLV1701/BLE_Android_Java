package no.nordicsemi.android.mcp;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.appcompat.app.d;

/* loaded from: classes.dex */
public class PermissionRationaleFragment extends androidx.fragment.app.c {
    private static final String ARG_ACTION = "ARG_ACTION";
    private static final String ARG_PERMISSION = "ARG_PERMISSION";
    private static final String ARG_TEXT = "ARG_TEXT";
    private PermissionDialogListener mListener;

    /* loaded from: classes.dex */
    public interface PermissionDialogListener {
        void onRequestPermission(String str, int i);
    }

    public static PermissionRationaleFragment getInstance(int i, String str, int i2) {
        PermissionRationaleFragment permissionRationaleFragment = new PermissionRationaleFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_TEXT, i);
        bundle.putString(ARG_PERMISSION, str);
        bundle.putInt(ARG_ACTION, i2);
        permissionRationaleFragment.setArguments(bundle);
        return permissionRationaleFragment;
    }

    public /* synthetic */ void a(Bundle bundle, DialogInterface dialogInterface, int i) {
        if (getParentFragment() instanceof PermissionDialogListener) {
            this.mListener = (PermissionDialogListener) getParentFragment();
        }
        this.mListener.onRequestPermission(bundle.getString(ARG_PERMISSION), bundle.getInt(ARG_ACTION));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.fragment.app.c, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof PermissionDialogListener) {
            this.mListener = (PermissionDialogListener) context;
        }
    }

    @Override // androidx.fragment.app.c
    public Dialog onCreateDialog(Bundle bundle) {
        final Bundle arguments = getArguments();
        StringBuilder sb = new StringBuilder(getString(arguments.getInt(ARG_TEXT)));
        d.a aVar = new d.a(getActivity());
        aVar.c(R.string.rationale_title);
        aVar.a(sb);
        aVar.a(R.string.cancel, (DialogInterface.OnClickListener) null);
        aVar.c(R.string.ok, new DialogInterface.OnClickListener() { // from class: no.nordicsemi.android.mcp.o
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                PermissionRationaleFragment.this.a(arguments, dialogInterface, i);
            }
        });
        return aVar.a();
    }

    @Override // androidx.fragment.app.c, androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
        this.mListener = null;
    }
}
